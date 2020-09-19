package com.superprince.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.superprince.entity.User;
import com.superprince.exception.UserException;
import com.superprince.mapper.UserMapper;
import com.superprince.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	/**
	 * 登录功能
	 * @param userName
	 * @return
	 */
	@Override
	public User login(String userName) {
		User user = userMapper.getUserByName(userName);
		return user;
	}
	/**
	 * 修改密码
	 * @param newPass
	 * @param oldPass
	 * @throws UserException 
	 */
	@Override
	public void updatePassword(String loginname, String newPass, String oldPass) throws UserException {
		/*
		 * 1. 校验老密码
		 */
		int bool = userMapper.findByUidAndPassword(loginname, oldPass);
		if(bool==0) {//如果老密码错误
			throw new UserException("原密码错误！");
		}
		/*
		 * 2. 修改密码
		 */
		userMapper.updatePassword(loginname, newPass);
	}

	/**
	 * 激活功能
	 * @param code
	 * @throws UserException 
	 */
	@Override
	public void activatioin(String code) throws UserException {
		/*
		 * 1. 通过激活码查询用户
		 * 2. 如果User为null，说明是无效激活码，抛出异常，给出异常信息（无效激活码）
		 * 3. 查看用户状态是否为true，如果为true，抛出异常，给出异常信息（请不要二次激活）
		 * 4. 修改用户状态为true
		 */
			User user = userMapper.findByCode(code);
			if(user == null) throw new UserException("无效的激活码!");
			if(user.getStatus().equals("1")) throw new UserException("您已经激活过了，请不要二次激活");

		userMapper.updateStatus(code);//修改为已激活状态
	}
	/**
	 * 用户名注册校验
	 * @param loginName
	 * @return
	 */
	@Override
	public boolean checkUserName(String loginName) {
		boolean isExist = true;
		int resultNum = userMapper.ajaxValidateLoginname(loginName);
		if (resultNum != 0) {
			isExist = false;
		}
		return isExist;
	}
	/**
	 * Email校验
	 * @param email
	 * @return
	 */
	@Override
	public boolean checkEmail(String email) {
		boolean isExist = true;
		int resultNum = userMapper.ajaxValidateEmail(email);
		if (resultNum != 0) {
			isExist = false;
		}
		return isExist;
	}
	/**
	 * 注册功能
	 * @param user
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	@Override
	public void regist(User user) throws AddressException, MessagingException {
		/*
		 * 1. 数据的补齐
		 */
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		//随机生成数字，并添加到字符串
		for(int i=0;i<8;i++){
		    str.append(random.nextInt(10));
		}
		user.setUserId(str.toString());
		user.setStatus("0");
		user.setActivationCode(str.toString());
		/*
		 * 2. 向数据库插入
		 */
		userMapper.add(user);

		/*
		 * 3. 发邮件
		 */
		 Properties properties = new Properties();  
	        properties.setProperty("mail.transport.protocol", "smtp");//发送邮件协议  
	        properties.setProperty("mail.smtp.auth", "true");//需要验证  
	         //properties.setProperty("mail.debug", "true");//设置debug模式 后台输出邮件发送的过程  
	        Session session = Session.getInstance(properties);  
	        session.setDebug(true);//debug模式  
	        //邮件信息  
	        Message message = new MimeMessage(session);  
	        message.setFrom(new InternetAddress("979748480@qq.com"));//设置发送人  
	        try {
	        	properties.load(this.getClass().getClassLoader().getResourceAsStream("com/lovecj/configuration/email/email_template.properties"));
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}
	        String email = properties.getProperty("email");
	        int  port = Integer.parseInt(properties.getProperty("port"));
	        String username = properties.getProperty("username");
	        String password = properties.getProperty("password");
	        String url = properties.getProperty("url");
	        
	        String newurl = url+user.getActivationCode();
	        String content =
	        "<html lang='zh-CN'><head><meta charset='utf-8'>"
	        + "</head><body>您正在注册王振和常君的家庭财务管理系统<br>"
	        + "<a href='"+newurl+"'>请点击这里</a>以完成激活<br>如你的浏览器不支持,请复制下面的链接到您的浏览器完成激活:<br>"+newurl+"</body></html>";
	        message.setContent(content,"text/html;charset=utf-8");
	        //message.setText(content);//设置邮件内容  
	        message.setSubject("王振和常君的家庭财务管理系统用户激活");//设置邮件主题  
	        //发送邮件  
	        Transport tran = session.getTransport();  
	         tran.connect(email, port, username, password);//连接到qq邮箱服务器  
	         String to = user.getEmail();
	        tran.sendMessage(message, new Address[]{ new InternetAddress(to)});//设置邮件接收人  
	        tran.close();  
		
	}
	@Override
	public String isStatus(Map user) {
		
		return userMapper.status(user);
	}
	@Override
	public String uid(String loginname) {
		
		return userMapper.findUid(loginname);
	}

}

