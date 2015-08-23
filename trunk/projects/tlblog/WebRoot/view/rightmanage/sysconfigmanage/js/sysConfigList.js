function update() {
	var sinaWeibo = $("#sinaWeibo").val();
	var onlineQq = $("#onlineQq").val();
	
	if($.trim(sinaWeibo) == ""){
		alert("请输入新浪微博地址!");
		return false;
	}
	if($.trim(onlineQq) == ""){
		alert("请输入QQ在线地址!");
		return false;
	}
	sysConfigForm.submit();
}

