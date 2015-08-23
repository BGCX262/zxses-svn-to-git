<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%@ include file="/inc/include.jsp"%>
<script>
function reset(){
	 document.getElementById("userAccount").value='';
   	 document.getElementById("password").value='';
   	 document.getElementById("checkCode").value='';
}

$(function(){
	$.ajax( {
		url : $.basePath + "front/login.do?method=checkLoginStatus",
		async : true,
		type : "POST",
		success : function(data) {
			if (data) {
				if(data == "unLogin"){
					$('#changeInfo').empty().append(data);
				}else{
					$('#changeInfo').empty().append(data);
				}
			}
		}
	});
});
</script>
<div id="changeInfo">
</div>