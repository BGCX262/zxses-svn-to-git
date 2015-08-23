function checkSubmit() {
	var password = $("#password");
	var newPassword = $("#newPassword");
	var confirm_password = $("#confirm_password");
	
	if($.trim(password.val()) == ""){
		$.alert("请输入原始密码！");
		return false;
	}
	if($.trim(newPassword.val()) == ""){
		$.alert("请输入新密码！");
		return false;
	}
	if($.trim(newPassword.val()).length < 6){
		$.alert("请输入至少6位的新密码！");
		return false;
	}
	if($.trim(confirm_password.val()) == ""){
		$.alert("请输入确认密码！");
		return false;
	}
	if($.trim(newPassword.val()) != $.trim(confirm_password.val())){
		$.alert("新密码和确认密码不一致！");
		return false;
	}
	return true;
}

