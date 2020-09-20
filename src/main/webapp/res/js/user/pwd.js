$(function() {	
	/*
	 * 1. 给注册按钮添加submit()事件，完成表单校验
	 */
	$("#submit").submit(function(){
		$("#msg").text("");
		var bool = true;
		$(".input").each(function() {
			var inputName = $(this).attr("name");
			if(!invokeValidateFunction(inputName)) {
				bool = false;
			}
		});
		return bool;
	});
	
	/*
	 * 3. 输入框推动焦点时进行校验
	 */
	$(".input").blur(function() {
		var inputName = $(this).attr("name");
		invokeValidateFunction(inputName);
	});
});

/*
 * 输入input名称，调用对应的validate方法。
 * 例如input名称为：loginname，那么调用validateLoginname()方法。
 */
function invokeValidateFunction(inputName) {
	inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
	var functionName = "validate" + inputName;
	return eval(functionName + "()");	
}

/*
 * 校验密码
 */
function validatePassword() {
	var bool = true;
	$("#passwordError").css("display", "none");
	var value = $("#password").val();
	if(!value) {// 非空校验
		$("#passwordError").css("display", "");
		$("#passwordError").text("密码不能为空！");
		bool = false;
	} else if(value.length < 3 || value.length > 20) {//长度校验
		$("#passwordError").css("display", "");
		$("#passwordError").text("密码长度必须在3 ~ 20之间！");
		bool = false;
		
	}	
	return bool;	
}

// 校验新密码
function validateNewpass() {
	var bool = true;
	$("#newPasswordError").css("display", "none");
	var value = $("#newPassword").val();
	if(!value) {// 非空校验
		$("#newPasswordError").css("display", "");
		$("#newPasswordError").text("新密码不能为空！");
		bool = false;
	} else if(value.length < 3 || value.length > 20) {//长度校验
		$("#newPasswordError").css("display", "");
		$("#newPasswordError").text("新密码长度必须在3 ~ 20之间！");
		bool = false;
	}
	return bool;
}

/*
 * 校验确认密码
 */
function validateRePassword() {
	var bool = true;
	$("#rePasswordError").css("display", "none");
	var value = $("#rePassword").val();
	if(!value) {// 非空校验
		$("#rePasswordError").css("display", "");
		$("#rePasswordError").text("确认密码不能为空！");
		bool = false;
	} else if(value != $("#newPassword").val()) {//两次输入是否一致
		$("#rePasswordError").css("display", "");
		$("#rePasswordError").text("两次密码输入不一致！");
		bool = false;
	}
	return bool;	
}

/*
 * 校验验证码
 */
function validateVerifyCode() {
	var bool = true;
	$("#verifyCodeError").css("display", "none");
	var value = $("#verifyCode").val();
	if(!value) {//非空校验
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("验证码不能为空！");
		bool = false;
	} else if(value.length != 4) {//长度不为4就是错误的
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("错误的验证码！");
		bool = false;
	} else {//验证码是否正确
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {verifyCode: value},
			url: "./verifyCode.do",
			success: function(flag) {
				if(!flag) {
					$("#verifyCodeError").css("display", "");
					$("#verifyCodeError").text("错误的验证码！");
					bool = false;					
				}
			}
		});
	}
	return bool;
}