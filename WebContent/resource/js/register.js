function checkUsername() {

	var username = $("#username").val();
	if (username == null || username.length <2||username.length>=8) {
		$("#usernameMessage").html('<font color="red">用户名格式错误，请重新输入</font>');
		return false;
	}
	var flag = true;
	$.post("/checkUsername.do", {
		'username' : username
	}, function(data, textStatus) {
		var message = '';
		if (data == "\"{rs:-1}\"") {
			message = '<font color="red">用户名格式错误，请重新输入</font>';
			flag = false;
		}
		if (data == "\"{rs:0}\"") {
			message = '<font color="green">未注册用户</font>';
		}
		if (data == "\"{rs:1}\"") {
			message = '<font color="red">该用户名已经被注册，请重新输入</font>';
			flag = false;
		}
		$("#usernameMessage").html(message);
	}, "html");
	return flag;
}

function checkEmail() {
	var username = $("#email").val();
	var flag = true;
	$.post("/checkEmail.do", {
		'email' : username
	}, function(data, textStatus) {
		var message = '';
		if (data == "\"{rs:-1}\"") {
			message = '<font color="red">email格式错误，请重新输入</font>';
			flag = false;
		}
		if (data == "\"{rs:0}\"") {
			message = '<font color="green">未注册用户</font>';
		}
		if (data == "\"{rs:1}\"") {
			message = '<font color="red">该email已经被使用，请重新输入</font>';
			flag = false;
		}
		$("#emailMessage").html(message);
	}, "html");
	return flag;
}

function checkPwd() {
	var reg = /^\w{6,18}$/;
	var pwd = $("#password").val();
	var flag = true;
	var message = '';
	if (pwd == '') {
		message = '<font color="red">密码不可空</font>';
		flag = false;
	}
	if (reg.test(pwd) == false) {
		message = '<font color="red">密码必须为6-18位</font>';
		flag = false;
	}
	$("#pwdMessage").html(message);
	return flag;
}

function checkPwd2() {
	var pwd = $("#password").val();
	var pwd2 = $("#password2").val();
	var flag = true;
	var message = '';
	if (pwd2 == '') {
		message = '<font color="red">密码不可空</font>';
		flag = false;
	}
	var reg = /^\w{6,18}$/;

	if (reg.test(pwd2) == false) {
		message = '<font color="red">密码必须为6-10位</font>';
		flag = false;
	}
	if (pwd != pwd2) {
		message = '<font color="red">两次输入的密码不一致</font>';
		flag = false;
	}
	$("#pwd2Message").html(message);
	return flag;
}

function checkAll() {
	if (checkEmail() && checkUsername() && checkPwd() && checkPwd2())
		return true;
	else
		return false;
}
