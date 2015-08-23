<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css">
	</head>
	<body>
		<table class="percentage100">
			<tr>
				<td class="framefootleft"></td>
				<td class="framefootcenter">
					&nbsp;
				</td>
				<td class="framefootright"></td>
			</tr>
		</table>
	</body>
</html>