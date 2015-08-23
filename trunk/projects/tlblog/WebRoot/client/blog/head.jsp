<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<div class="bl_head">
	<!-- 菜单导航 begin -->
	<div class="bl_nav">
		<a href="${basePath }index.html" title="首页" class="hover">首页</a>|
		<a href="${basePath }client/blog.do?method=searchUser&currentUserName=${frontUserInfo.username }" title="博客">博客</a>|
		<a href="${basePath }client/blogarticle/class.jsp" title="阅读">阅读</a>|
		<a href="${basePath }client/blogpic/pic.jsp" title="相册">相册</a>|
		<a href="${basePath }client/blog/login.jsp" title="登陆">登陆</a>|
		<a href="${basePath }client/blogarticle/class.jsp" title="精华">精华</a>
	</div>
	<!-- 菜单导航 end -->
	<!-- 博客搜索框 begin -->
	<div class="bl_search">
		<!-- 修改对应的aciton路径 -->
		<html:form action="/client/index/search.do" method="post">
			<input type="text" id="word" name="word" value="${param.word }" />
			<input type="submit" class="bl_sure" value="" />
		</html:form>
	</div>
	<!-- 博客搜索框 end -->
</div>
