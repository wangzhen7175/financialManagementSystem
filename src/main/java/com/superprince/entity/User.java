package com.superprince.entity;

/**
 * 用户模块实体类
 * @author 
 *
 */
/*
 * 属性哪里来
 * 1. t_user表：因为我们需要把t_user表查询出的数据封装到User对象中
 * 2. 该模块所有表单：因为我们需要把表单数据封装到User对象中
 */
public class User {
	// 对应数据库表
	private String userId;//主键
	private String userName;//登录名
	private String password;//登录密码
	private String email;//邮箱
	private String status;//状态，1表示已激活 0未激活
	private String activationCode;//激活码，它是唯一值！即每个用户的激活码是不同的！
	
	// 注册表单
	private String reloginPass;//确认密码
	private String verifyCode;//验证码
	
	// 修改密码表单
	private String newPass;//新密码

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getReloginPass() {
		return reloginPass;
	}

	public void setReloginPass(String reloginPass) {
		this.reloginPass = reloginPass;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", status='" + status + '\'' +
				", activationCode='" + activationCode + '\'' +
				", reloginPass='" + reloginPass + '\'' +
				", verifyCode='" + verifyCode + '\'' +
				", newPass='" + newPass + '\'' +
				'}';
	}
}
