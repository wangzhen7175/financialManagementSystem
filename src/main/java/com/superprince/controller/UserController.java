package com.superprince.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.superprince.entity.Menu;
import com.superprince.entity.User;
import com.superprince.exception.UserException;
import com.superprince.service.MenuService;
import com.superprince.service.UserService;
import com.superprince.util.MenuUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    /**
     * 进入登录页面 .jsp
     * @return
     */
    @RequestMapping("toLogin.do")
    public String loginPage() {
        //返回的字符串会对应一个/jsp/index.jsp页面
        return "login";
    }
    /**
     * 前往修改密码页面
     * @return
     */
    @RequestMapping("toPwd.do")
    public String toPwd() {
        //返回的字符串会对应一个/jsp/index.jsp页面
        return "pwd";
    }
	/**
	 * 用户登录功能,进入首页
	 *
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/index.do")
	public String login(Model model, HttpServletRequest req, HttpServletResponse resp, @RequestParam String userName,
						@RequestParam String password
	) throws ServletException, IOException {

	    if(userName == "" || userName == null){
            return "login";
        }
		//获取真实验证码
		String vcode = (String) req.getSession().getAttribute("vCode");
		//获取用户输入的验证码
		String verifyCode = req.getParameter("verifyCode");
		//进行忽略大小写比较，得到结果
		//boolean b = verifyCode.equalsIgnoreCase(vcode);
        boolean b = true; //不使用验证码
		if (!b) {
			req.setAttribute("msg", "验证码错误!");
			return "login";
		}

        User user = userService.login(userName);//根据用户名获取user对象
		/*
		 * 开始判断
		 */
		if (user == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			req.setAttribute("user", userName);
			return "login";
		} else {
			if (user.getStatus().equals("0")) {
				req.setAttribute("msg", "您还没有激活！");
				req.setAttribute("user", userName);
				return "login";
			} else {
				// 保存用户到session
				req.getSession().setAttribute("sessionUser", userName);
				// 获取用户名保存到cookie中
				userName = URLEncoder.encode(userName, "utf-8");
				Cookie cookie = new Cookie("userName", userName);
				cookie.setMaxAge(60 * 60 * 24 * 10);//保存10天
				resp.addCookie(cookie);
				//构建菜单
				List<Menu> menuList = menuService.getMenuList();
				String menus = MenuUtils.buildMenus(menuList);
				model.addAttribute("menus", menus);
				return "index";//重定向到主页

			}
		}
	}


    /**
     * ajax用户名是否注册校验
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/checkUserName.do")
    public String checkUserName(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        /*
         * 1. 获取用户名
         */
        String loginName = req.getParameter("loginName");
        /*
         * 2. 发给客户端
         */
        resp.getWriter().print(userService.checkUserName(loginName));
        return null;
    }

    /**
     * ajax Email是否注册校验
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/checkEmail.do")
    public String checkEmail(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        /*
         * 1. 获取Email
         */
        String email = req.getParameter("email");
        /*
         * 2. 发给客户端
         */
        resp.getWriter().print(userService.checkEmail(email));
        return null;
    }

    /**
     * ajax验证码是否正确校验
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */

    @RequestMapping("/checkverifyCode.do")
    public String checkverifyCode(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 获取输入框中的验证码
         */
        String verifyCode = req.getParameter("verifyCode");
        System.out.println(new Date().toString() + ":用户输入验证码：" + verifyCode);
        /*
         * 2. 获取图片上真实的校验码
         */
        String vcode = (String) req.getSession().getAttribute("vCode");
        /*
         * 3. 进行忽略大小写比较，得到结果
         */
        boolean b = verifyCode.equalsIgnoreCase(vcode);
        /*
         * 4. 发送给客户端
         */
        resp.getWriter().print(b);
        return null;
    }

    @RequestMapping("/toRegist.do")
    public String registPage() {
        System.out.println(new Date().toString() + "用户进入注册页面");
        return "regist";
    }

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws MessagingException
     * @throws AddressException
     */
    @RequestMapping("/regist.do")
    public String regist(HttpServletRequest req, HttpServletResponse resp,
                         @RequestParam String loginName,
                         @RequestParam String loginPass,
                         @RequestParam String reloginPass,
                         @RequestParam String email,
                         @RequestParam String verifyCode
    )
            throws ServletException, IOException, AddressException, MessagingException {
        /*
         * 1. 封装表单数据
         */
        User user = new User();
        user.setEmail(email);
        user.setUserName(loginName);
        user.setPassword(loginPass);
        user.setReloginPass(reloginPass);
        user.setVerifyCode(verifyCode);
        /*
         * 2. 校验之, 如果校验失败，保存错误信息，返回到regist.jsp显示
         */
        Map<String, String> errors = validateRegist(user, req.getSession());
        if (errors.size() > 0) {
            req.setAttribute("form", user);
            req.setAttribute("errors", errors);
            return "regist";
        }
        /*
         * 3. 使用service完成业务
         */
        userService.regist(user);
        /*
         * 4. 保存成功信息，转发到msg.jsp显示！
         */
        req.setAttribute("code", "success");
        req.setAttribute("msg", "注册成功，请到邮箱激活！");
        return "msg";
    }

    /*
     * 注册校验
     * 对表单的字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中
     * 返回map
     */
    private Map<String, String> validateRegist(User user, HttpSession session) {
        Map<String, String> errors = new HashMap<String, String>();
        /*
         * 1. 校验登录名
         */
        String loginname = user.getUserName();
        if (loginname == null || loginname.trim().isEmpty()) {
            errors.put("loginName", "用户名不能为空！");
        } else if (loginname.length() < 3 || loginname.length() > 20) {
            errors.put("loginName", "用户名长度必须在3~20之间！");
        } else if (userService.checkUserName(loginname) == false) {
            errors.put("loginName", "用户名已被注册！");
        }

        /*
         * 2. 校验登录密码
         */
        String loginpass = user.getPassword();
        if (loginpass == null || loginpass.trim().isEmpty()) {
            errors.put("loginPass", "密码不能为空！");
        } else if (loginpass.length() < 3 || loginpass.length() > 20) {
            errors.put("loginPass", "密码长度必须在3~20之间！");
        }

        /*
         * 3. 确认密码校验
         */
        String reloginpass = user.getReloginPass();
        if (reloginpass == null || reloginpass.trim().isEmpty()) {
            errors.put("reloginpass", "确认密码不能为空！");
        } else if (!reloginpass.equals(loginpass)) {
            errors.put("reloginpass", "两次输入不一致！");
        }

        /*
         * 4. 校验email
         */
        String email = user.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空！");
        } else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
            errors.put("email", "Email格式错误！");
        } else if (userService.checkEmail(email) == false) {
            errors.put("email", "Email已被注册！");
        }

        /*
         * 5. 验证码校验
         */
        String verifyCode = user.getVerifyCode();
        String vcode = (String) session.getAttribute("vCode");
        if (verifyCode == null || verifyCode.trim().isEmpty()) {
            errors.put("verifyCode", "验证码不能为空！");
        } else if (!verifyCode.equalsIgnoreCase(vcode)) {
            errors.put("verifyCode", "验证码错误！");
        }

        return errors;
    }

    /**
     * 激活功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/activation.do")
    public String activation(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 获取参数激活码
         * 2. 用激活码调用service方法完成激活
         *   > service方法有可能抛出异常, 把异常信息拿来，保存到request中，转发到msg.jsp显示
         * 3. 保存成功信息到request，转发到msg.jsp显示。
         */
        String code = req.getParameter("activationCode");
        try {
            userService.activatioin(code);
            req.setAttribute("code", "success");//通知msg.jsp显示对号
            req.setAttribute("msg", "恭喜，激活成功，请马上登录！");
        } catch (UserException e) {
            // 说明service抛出了异常
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("code", "error");//通知msg.jsp显示X
        }
        return "msg";
    }

    /**
     * 修改密码
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/updatePassword.do")
    public String updatePassword(HttpServletRequest req, HttpServletResponse resp,
                                 @RequestParam String loginpass,
                                 @RequestParam String newpass,
                                 @RequestParam String reloginpass,
                                 @RequestParam String verifyCode)
            throws ServletException, IOException {
        /*
         * 1. 封装表单数据到user中
         * 2. 从session中获取uid
         * 3. 使用uid和表单中的oldPass和newPass来调用service方法
         *   > 如果出现异常，保存异常信息到request中，转发到pwd.jsp
         * 4. 保存成功信息到rquest中
         * 5. 转发到msg.jsp
         */
        if (!newpass.equals(reloginpass)) {
            return "pwd";
        }
        Map user = (Map) req.getSession().getAttribute("sessionUser");
        try {
            userService.updatePassword((String) user.get("loginname"), newpass, loginpass);
            req.setAttribute("msg", "修改密码成功");
            req.setAttribute("code", "success");
            return "msg";
        } catch (UserException e) {
            req.setAttribute("msg", e.getMessage());//保存异常信息到request
            return "pwd";
        }
    }

    /**
     * 退出功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/quit.do")
    public String quit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        return "login";
    }


}
