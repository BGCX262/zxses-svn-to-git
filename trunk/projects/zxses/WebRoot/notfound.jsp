<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>页面不存在</title>
		<link href="<%=basePath%>theme/warnpage/warn.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="error_page">
			<img src="<%=basePath%>theme/warnpage/notfound.gif" />
			<h1>
				Sorry，您所访问的页面不存在！
			</h1>
			<p>
				<strong><a href="javascript:history.back(-1)">返回</a></strong>
			</p>
		</div>
	</body>
</html>