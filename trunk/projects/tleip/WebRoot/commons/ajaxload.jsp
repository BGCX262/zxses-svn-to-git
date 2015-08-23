<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>load</title>

	</head>

	<body style="background-color: white;">
		<center>
			<img src="<%=basePath%>res/admin/img/ajax-loading.gif" width="66" height="66" />
		</center>
	</body>
</html>
