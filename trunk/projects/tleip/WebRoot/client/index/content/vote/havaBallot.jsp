<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%@ include file="/inc/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="${basePath }res/client/css/css.css" />
		<link rel="stylesheet" type="text/css"
			href="${basePath }res/client/css/skin.css" />
		<script type="text/javascript"
			src="${basePath }res/client/js/jquery-1.4.3.min.js"></script>
		<%@ include file="/inc/resources.jsp"%>
		<title>投票失败</title>
		
	</head>
	<body>
		<script type="text/javascript">
			$.alert("已投票，不可重复投票!",function(){
				window.location.href="${basePath }front/message.do?method=queryMessage";
			});
		</script>
	</body>
</html>