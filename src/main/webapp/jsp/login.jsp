<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./res/css/user/login.css">
	<script type="text/javascript" src="./res/js/jquery-1.5.1.js"></script>
      <script type="text/javascript" src="./res/js/user/login.js"></script>
      <script src="./res/js/common.js"></script>
<script type="text/javascript">
	$(function() {// *Map<String(Cookie名称),Cookie(Cookie本身)>*/
		// 获取cookie中的用户名
		var userName = window.decodeURI("${cookie.userName.value}");
		if("${requestScope.user.userName}") {
            userName = "${requestScope.user.userName}";
		}
		$("#userName").val(userName);
	});
</script>
  </head>
  
  <body>
	<div class="main">
	 <h1>欢迎使用家庭财务管理系统</h1>
	  <div>
	    <div class="imageDiv"><img class="img" src="./res/images/login.png"/></div>
        <div class="login1">
	      <div class="login2">
            <div class="loginTopDiv">
              <span class="loginTop">用户登录</span>
              <span>
                <a href="./toRegist.do" method="get" class="registBtn"></a>
              </span>
            </div>
            <div>
              <form target="_top" action="./index.do" method="post" id="loginForm">
                <input type="hidden" name="method" value="login" />
                  <table>
                    <tr>
                      <td width="50"></td>
                      <td><label class="error" id="msg">${msg }</label></td>
                    </tr>
                    <tr>
                      <td width="50">用户名</td>
                      <td><input class="input" type="text" name="userName" id="userName"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td><label id="userNameError" class="error"></label></td>
                    </tr>
                    <tr>
                      <td>密　码</td>
                      <td><input class="input" type="password" name="password" id="password" value="${user.loginpass }"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td><label id="passwordError" class="error"></label></td>
                    </tr>
                  <%--  <tr>
                      <td>验证码</td>
                      <td>
                        <input class="input yzm" type="text" name="verifyCode" id="verifyCode" value="${user.verifyCode }"/>
                        <img id="vCode" src="/money/VerifyCodeServlet"/>
                        <a id="verifyCode" href="javascript:_change()">看不清，换一张</a>
                      </td>
                    </tr>--%>
                    <tr>
                      <td height="20px">&nbsp;</td>
                      <td><label id="verifyCodeError" class="error"></label></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="left">
                        <input type="image" id="submit" src="./res/images/login1.jpg" class="loginBtn"/>
                      </td>
                    </tr>																				
                 </table>
              </form>
            </div>
          </div>
        </div>
      </div>
	</div>
  </body>
</html>
	