function gbcount(){
	var max = $("#total").val();
	var messageObj = $("#content");
	if(messageObj.val().length > max){
		messageObj.val(messageObj.val().substring(0,max));
		$("#used").val(max);
		$("#remain").val(0);
		$.alert('留言不能超过 500 个字!');
	}else{
   	 	$("#used").val(messageObj.val().length);
   	 	$("#remain").val(max - $("#used").val());
    }
}

function validateSubmit(){
	//简单验证
	var content = $("#content").val();
	
	if($.trim(content) == ""){
		$.alert("请输入留言内容！");
		return false;
	}
	return true;
}