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
		<jsp:include page="/blog/client/head.jsp"/>
		<!-- 头部 end -->
		
		
		<div class="bl_main">
			<!-- 左边 begin -->
			<jsp:include page="/client/left.jsp"/>
			<!-- 左边 end -->
			
			<!-- 右边文章列表 begin -->
			<div class="bl_right fr">
				<div class="bl_r_title">
					<h1>相片详细</h1>
					<a href="">上传相片</a>
				</div>
				<div class="bl_r_cont">
					<div class="weblist">
						<div class="pic_show">
							<h1>相片名称</h1>
							<img src="images/04_banner.jpg" />	
						</div>
					</div>
				</div>
			</div>
			<!-- 右边文章列表 end -->
			
			<div class="clear"></div>
		</div>
		
		
		<!-- 底部 begin -->
		<jsp:include page="/client/bottom.jsp"/>
		<!-- 底部 end -->
	</div>
</div>
</body>
</html>