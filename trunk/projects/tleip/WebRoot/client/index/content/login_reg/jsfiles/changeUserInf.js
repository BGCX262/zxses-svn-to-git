function checkSubmit() {
	var userName = $("#userName");
	var email = $("#email");
	
	if($.trim(userName.val()) == ""){
		$.alert("请输入真实姓名！");
		return false;
	}
	if($.trim(email.val()) == ""){
		$.alert("请输入邮箱地址！");
		return false;
	}
	//邮箱格式验证
	if(!isEmail($.trim(email.val()))){
		$.alert("请输入正确的邮箱地址！");
		return false;
	}
	//前端简单验证注册信息
	return true;
}