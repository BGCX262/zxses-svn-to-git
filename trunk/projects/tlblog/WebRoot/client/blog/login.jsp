<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${basePath }res/client/css/css.css" />
<title>博客主页</title>
</head>

<body bgcolor="#e5e8e7">
<div class="blogout">
	<div class="bloginner">
		<!-- 头部 begin -->
		<jsp:include page="/client/blog/head.jsp"/>
		<!-- 头部 end -->
		
		<div class="wcon">
			<h1>用户登陆</h1>
			<div class="wform">
				<div class="recicle">
					<ul>
						<li>
							<span>账号：</span>
							<input type="text" value="" />
						</li>
						<li>
							<span>密码：</span>
							<input type="password" value="" />
							<a href=""><span>忘了密码？</span></a>
						</li>
						<li>
							<a href="" class="jxdl"><span>登陆</span></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 底部 begin -->
		<jsp:include page="/client/blog/bottom.jsp"/>
		<!-- 底部 end -->
	</div>
</div>
</body>
</html>