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
		
		
		<div class="bl_main">
			<!-- 左边 begin -->
			<jsp:include page="/client/blog/left.jsp"/>
			<!-- 左边 end -->
			
			<!-- 右边文章列表 begin -->
			<div class="bl_right fr">
				<div class="bl_r_title">
					<h1>相册</h1>
					<a href="">上传相片</a>
				</div>
				<div class="bl_r_cont">
					<div class="product">
						<ul>
							<li>
								<a href="showpic.html"><img src="${basePath }res/client/images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<li>
								<a href="showpic.html"><img src="images/xxz.jpg" /></a>
								<p><a href="showpic.html">111</a></p>
							</li>
							<div class="clear"></div>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="digg4" style=" text-align:center;">
						<ul >
							<li ><a href="" class="diga">首页</a></li>
							<li><a href="">上一页</a></li>
							<li class="diggcurrent">1</li>
							<li><a href="">下一页</a></li>
							<li><a href="">未页</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="clear"></div>
		</div>
		<!-- 右边文章列表 end -->
		
		<!-- 底部 begin -->
		<jsp:include page="/client/blog/bottom.jsp"/>
		<!-- 底部 end -->
	</div>
</div>
</body>
</html>