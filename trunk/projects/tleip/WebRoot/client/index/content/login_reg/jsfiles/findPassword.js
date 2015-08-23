function checkSubmit(){
	var userAccount = $("#userAccount").val();
	if($.trim(userAccount) == ""){
		$.alert("请输入用户名！");
		return false;
	}
	
	return true;
}

$(function(){
	$("#continue").click(function(){
		var userAccount = $("#userAccount").val();
		if($.trim(userAccount) == ""){
			$.alert("请输入用户名！");
			return;
		}
		$('#content').empty().append("<img align='center' src='"+$.basePath+"res/client/images/jerichoload.gif'/>邮件发送中...");
		$.ajax( {
			url : $.basePath + "front/post.do",
			data : {
				method : "getpassword",
				userAccount : userAccount
			},
			async : true,
			type : "POST",
			success : function(data) {
				if(data == "userAccountError"){
					$('#content').empty().append("用户帐号有误！<a href='"+$.basePath + "client/index/content/login_reg/findPassword.jsp"+"'>重新输入用户帐号</a>");
				}else if(data == "emailSended"){
					window.location.href=$.basePath + "client/index/content/login_reg/findPassword2.jsp";
				}
			}
		});	
	});
});