<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./res/css/css.css">
	<link rel="stylesheet" type="text/css" href="./res/css/user/pwd.css">
	<script type="text/javascript" src="./res/js/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="./res/js/user/pwd.js"></script>
	<script src="./res/js/common.js"></script>
  </head>
  
  <body>
    <div class="div0">
    	<span>修改密码</span>
    </div>

	<div class="div1">
		<form action="./updatePassword.do" method="post" target="_top">
			<input type="hidden" name="method" value="updatePassword"/>
		<table>
			<tr>
				<td><label class="error">${msg }</label></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td align="right">原密码:</td>
				<td><input class="input" type="password" name="password" id="password" value="${user.password }"/></td>
				<td><label id="passwordError" class="error"></label></td>
			</tr>
			<tr>
				<td align="right">新密码:</td>
				<td><input class="input" type="password" name="newPassword" id="newPassword" value="${user.newPassword }"/></td>
				<td><label id="newPasswordError" class="error"></label></td>
			</tr>
			<tr>
				<td align="right">确认密码:</td>
				<td><input class="input" type="password" name="rePassword" id="rePassword" value="${user.rePassword }"/></td>
				<td><label id="rePasswordError" class="error"></label></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td>
				  <img id="vCode" src="./VerifyCodeServlet" border="1"/>
		    	  <a  href="javascript:_change();">看不清，换一张</a>
				</td>
			</tr>
			<tr>
				<td align="right">验证码:</td>
				 <td> <input class="input" type="text" name="verifyCode" id="verifyCode" value="${user.verifyCode }"/></td>
				<td><label id="verifyCodeError" class="error"></label></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input id="submit" type="submit" value="提交修改"/></td>
			</tr>
		</table>
		</form>
	</div>
  </body>
</html>
