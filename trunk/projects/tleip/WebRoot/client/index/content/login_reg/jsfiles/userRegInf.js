$(function () {
	var userAccount = $("#userAccount");
	//ajax检查用户名是否已经使用
	userAccount.blur(function(){
		$("#userAccount-hint").html("提示：由英文字母、数字和下划线或邮箱构成");
		if($.trim($(userAccount).val()) != ""){
			//$("#userAccount-hint").html("<font color='red'>检查用户名！</font>");
			$.ajax( {
				url : $.basePath + "front/login.do?method=checkUserAccount",
				data : {
					userAccount : userAccount.val()
				},
				async : true,
				type : "POST",
				success : function(data) {
					var userAccountUsed = $("#userAccountUsed");
					if (data == "used") {
						$("#userAccount-hint").html("<font color='red'>该用户帐号已使用,请更换！</font>");
						userAccountUsed.val("true");
					}else{
						$("#userAccount-hint").html("<font color='green'>用户帐号可用！</font>");
						userAccountUsed.val("false");
					}
				}
			});
		}
	});
	
	//
});

function checkSubmit() {
	var userAccount = $("#userAccount");
	var userAccountUsed = $("#userAccountUsed");
	var userName = $("#userName");
	var password = $("#password");
	var rePassword = $("#rePassword");
	var email = $("#email");
	
	if($.trim(userAccount.val()) == ""){
		$.alert("请输入用户名！");
		return false;
	}
	if($.trim(userAccountUsed.val()) == "true"){
		$.alert("用户名已使用,请更换用户名！");
		return false;
	}
	if($.trim(userName.val()) == ""){
		$.alert("请输入真实姓名！");
		return false;
	}
	if($.trim(password.val()) == ""){
		$.alert("请输入密码！");
		return false;
	}
	if($.trim(password.val()).length < 6){
		$.alert("请输入至少6位的密码！");
		return false;
	}
	if($.trim(rePassword.val()) == ""){
		$.alert("请输入确认密码！");
		return false;
	}
	if($.trim(password.val()) != $.trim(rePassword.val())){
		$.alert("密码和确认密码不一致！");
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

