function loginCheck(){
	//简单验证
	var userAccount = $("#userAccount").val();
	var password = $("#password").val();
	var userType = $("#userType").val();
	var checkCode = $("#checkCode").val();
	
	if($.trim(userAccount) == ""){
		$.alert("请输入用户名！");
		return false;
	}
	if($.trim(password) == ""){
		$.alert("请输入密码！");
		return false;
	}
	if($.trim(checkCode) == ""){
		$.alert("请输入验证码！");
		return false;
	}
	
	//ajax登录验证
	$.ajax( {
		url : $.basePath + "front/login.do?method=frontLogin",
		data : {
			userAccount : userAccount,
			password : password,
			userType : userType,
			checkCode : checkCode
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

//改变登录用户类型
function changeUserType(value,obj){
	//remove all class
	$("#teacherType").removeClass("user_hover");
	$("#studentType").removeClass("user_hover");
	$("#parentType").removeClass("user_hover");
	$("#normalType").removeClass("user_hover");
	if(value == "teacher"){
		$("#teacherType").addClass("user_hover");
	}else if(value == "student"){
		$("#studentType").addClass("user_hover");
	}else if(value == "parent"){
		$("#parentType").addClass("user_hover");
	}else if(value == "normal"){
		$("#normalType").addClass("user_hover");
	}
	$("#userType").val(value);
}