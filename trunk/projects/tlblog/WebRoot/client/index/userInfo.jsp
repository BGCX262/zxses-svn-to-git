<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.agilefly.utils.DateUtils"%>
<%@ include file="/commons/tags.inc"%>

<div id="headFirst" style="display: inline;float: left;padding-left:45px;">
	<span>欢迎您：</span>
	<span style="padding: 0px;"><a href="${basePath }client/blog.do?method=searchUser&currentUserName=${frontUserInfo.username }">${frontUserInfo.username }</a></span>
	<span>今天是：<%=DateUtils.getChinaDate()%></span>
	<span style="padding: 0px;"><a href="${basePath}loginOut">【注销】</a></span>
</div>
<div style="display: inline;float: right; padding-right: 45px;">
	<!-- 修改对应的aciton路径 -->
	<html:form action="/client/index/search.do" method="post" onsubmit="return checkSearchSubmit();">
		<span>快速搜索：</span><input type="text" id="word" name="word" value="${param.word }" /><input id="searchBtn" type="submit" value="搜索" />
		<span><a href="${bathPath }signup">注册博客</a></span>
	</html:form>
</div>