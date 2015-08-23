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
	<body class="centerbg">

		<table style="margin-top: 80px;" align="center">
			<tr>
				<td>
					<Img src="<%=basePath%>res/theme/blue/images/MainCenter.jpg" />
				</td>
			</tr>
		</table>
	</body>
</html>




