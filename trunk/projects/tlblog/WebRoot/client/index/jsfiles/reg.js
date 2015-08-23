$(function () {
	var username = $("#reg_username");
	//ajax检查用户名是否已经使用
	username.blur(function(){
		$("#username_tip").html("由英文字母、数字和下划线或邮箱构成");
		if($.trim(username.val()) != ""){
			//用户名只能由英文字母、数字和下划线或邮箱构成
			if(!validate.testNumAndLetterUnder($.trim(username.val())) && !validate.testEmail($.trim(username.val()))){
				$.alert("用户名由英文字母、数字和下划线或邮箱构成！");
				return false;
			}
			//$("#userAccount-hint").html("<font color='red'>检查用户名！</font>");
			$.ajax( {
				url : $.basePath + "client/index.do?method=checkUserName",
				data : {
					username : username.val()
				},
				async : true,
				type : "POST",
				success : function(data) {
					var usernameUsed = $("#usernameUsed");
					if (data == "used") {
						$("#username_tip").html("<font color='red'>该用户帐号已使用,请更换！</font>");
						usernameUsed.val("true");
					}else{
						$("#username_tip").html("<font color='green'>用户帐号可用！</font>");
						usernameUsed.val("false");
					}
				}
			});
		}
	});
});

function checkRegSubmit() {
	var username = $("#reg_username");
	var usernameUsed = $("#usernameUsed");
	var password = $("#reg_password");
	var rePassword = $("#repass");
	var realname = $("#realname");
	var userHeadpic = $("#userHeadpic");
	var userPenname = $("#userPenname");
	var userBlogdes = $("#userBlogdes");
	var userBirthday = $("#userBirthday");
	var userEmail = $("#userEmail");
	var userQq = $("#userQq");
	var phone = $("#phone");
	
	//不为空
	if($.trim(username.val()) == ""){
		$.alert("请输入用户名！");
		return false;
	}
	if($.trim(usernameUsed.val()) == "true"){
		$.alert("用户名已使用,请更换用户名！");
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
	if($.trim(realname.val()) == ""){
		$.alert("请输入真实姓名！");
		return false;
	}
	var userHeadpicValue = $.trim(userHeadpic.val());
	if(userHeadpicValue == ""){
		$.alert("请选择头像照片！");
		return false;
	}else{
		//检查上传文件是否为图片格式
		/*if(!validate.testImage(userHeadpicValue)){
			$.alert("只允许上传gif、jpg、bmp、png图片格式！");
			return false;
		}*/
	}
	if($.trim(userPenname.val()) == ""){
		$.alert("请输入笔名！");
		return false;
	}
	if($.trim(userBlogdes.val()) == ""){
		$.alert("请输入博客描述！");
		return false;
	}
	if($.trim(userEmail.val()) == ""){
		$.alert("请输入邮箱地址！");
		return false;
	}else{
		//邮箱格式验证
		if(!validate.testEmail($.trim(userEmail.val()))){
			$.alert("请输入正确的邮箱地址！");
			return false;
		}
	}
	//qq号码只能是数字
	if($.trim(userQq.val()) != ""){
		if(!validate.testIntegerNumber($.trim(userQq.val()))){
			$.alert("请输入正确的QQ号码(数字)！");
			return false;
		}
	}
	//联系电话只能是数字
	if($.trim(phone.val()) != ""){
		if(!validate.testTelPhone($.trim(phone.val())) && !validate.testPhone($.trim(phone.val()))){
			$.alert("请输入正确的联系电话！");
			return false;
		}
	}
	//前端简单验证注册信息
	return true;
}

