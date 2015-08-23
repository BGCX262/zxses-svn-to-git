$(function () {
	$("#frontLogin").click(function(){
		checkSubmit();
	});
});

function checkSubmit() {
	var username = $("#username");
	var password = $("#password");
	
	if($.trim(username.val()) == ""){
		$.alert("请输入用户名！");
		return false;
	}
	if($.trim(password.val()) == ""){
		$.alert("请输入密码！");
		return false;
	}
	$.ajax( {
		url : $.basePath + "client/index.do?method=login",
		data : {
			username : username.val(),
			password : password.val()
		},
		async : true,
		type : "POST",
		success : function(data) {
			if (data) {
				if(data == "fail"){
					$.alert("用户名、密码或者用户类型错误!");
				}else if(data == "pageExpired"){
					$.alert("页面过期,请点击重新获得验证码!");
				}else if(data == "codeFail"){
					$.alert("验证码错误,请重新填写或点击刷新!");
				}else if(data == "userDisable"){
					$.alert("用户已禁用,请与管理员联系!");
				}else{
					$('#changeInfo').empty().append(data);
				}
			}
		}
	});
}

