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
		<jsp:include page="/client/blog/head.jsp" />
		<!-- 头部 end -->
		
		
		<div class="bl_main">
			<!-- 左边 begin -->
			<jsp:include page="/client/blog/left.jsp"/>
			<!-- 左边 end -->
			
			<!-- 右边文章列表 begin -->
			<div class="bl_right fr">
				<div class="bl_r_title">
					<h1>文章列表</h1>
					<a href="">发表新文章</a>
				</div>
				<div class="bl_r_cont">
					<div class="weblist">
						<h1>
							国家旅游局建议中国游客近期避免前往日本受灾地区旅游
							<span>2011年03月16日</span>
						</h1>
						<p>		日本东北地区发生强烈地震并引发核电站事故以来，国家旅游局一直密切关注灾情发展并动态评估灾情对我国赴日旅游活动的影响。本着对游客人
			身安全的考虑，国家旅游局建议我国游客近期避免前往日本受灾地区旅游。 
						</p>
						<pre>来源：国家旅游局</pre>
						<pre>最后修改日期 : 2011-03-16 </pre>
					</div>
					<div class="bl_r_title">
						<h1>发表评论：</h1>
					</div>
					<div class="weblist">
						<form action="" method="post">
							<p>用户名：<input type="text" /></p>
							<p>标&nbsp;&nbsp;&nbsp;&nbsp;题：<input type="text" /></p>
							<p>
								内&nbsp;&nbsp;&nbsp;容：
								<textarea></textarea>
							</p>
							<p><input type="submit" id="mesid" value="" /></p>
						</form>
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