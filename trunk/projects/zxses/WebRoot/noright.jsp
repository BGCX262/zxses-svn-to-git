<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>没有权限</title>
		<link href="<%=basePath%>theme/warnpage/warn.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	      function relogin(){
	        window.parent.location.href="login!logout.action";
     	 }
   		</script>
	</head>
	<body>
		<div class="error_page">
			<img src="<%=basePath%>theme/warnpage/noright.gif" />
			<h1>
				Sorry，您没有权限！
			</h1>
			<p>
				<strong><a onclick="relogin()" >重新登录</a></strong>
			</p>
		</div>
	</body>
</html>
