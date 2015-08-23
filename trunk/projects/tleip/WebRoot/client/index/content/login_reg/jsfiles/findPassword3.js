function checkSubmit() {
	var password = $("#password");
	var confirm_password = $("#confirm_password");
	
	if($.trim(password.val()) == ""){
		$.alert("请输入密码！");
		return false;
	}
	if($.trim(password.val()).length < 6){
		$.alert("请输入至少6位的密码！");
		return false;
	}
	if($.trim(confirm_password.val()) == ""){
		$.alert("请输入确认密码！");
		return false;
	}
	if($.trim(password.val()) != $.trim(confirm_password.val())){
		$.alert("密码和确认密码不一致！");
		return false;
	}
	return true;
}

