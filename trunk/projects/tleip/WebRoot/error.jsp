<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>服务器或者内部错误</title>
		<link href="<%=basePath%>res/admin/warnpage/warn.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	      function relogin(){
	        parent.location.href="login.do?method=loginOut";
     	 }
   		</script>
	</head>
	<body>
		<div class="error_page">
			<img src="<%=basePath%>res/admin/warnpage/error.jpg" />
			<h1>
				服务器或者内部错误！
			</h1>
			<div class="top">
				<strong><a href="#" onclick="relogin()" >重新登录</a></strong>&nbsp;&nbsp;&nbsp;&nbsp;
				
				<strong><a href="javascript:history.back(-1)">返回</a></strong>
				
			</div>
		</div>
	</body>
</html>
