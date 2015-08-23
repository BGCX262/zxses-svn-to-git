<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.agilefly.utils.DateUtils"%>
<%@ include file="/commons/tags.inc"%>
<script type="text/javascript" src="${basePath}client/index/jsfiles/head.js"></script>
<script type="text/javascript" src="${basePath}client/index/jsfiles/searchAllList.js"></script>
<script type="text/javascript">
	function reIndex(){
		window.location.href="${basePath}client/index/reindex.do";
	}
</script>
<div id="wx_head">
	<div class="flash">
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="767" height="198">
             <param name="movie" value="${basePath }res/client/flash/start.swf" />
             <param name="quality" value="high" />
             <param name="vmode" value="transparent" />
             <embed src="${basePath }res/client/flash/start.swf" width="767" height="198" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" vmode="transparent"></embed>
	  </object>
	</div>
	<div class="wx_logo"><a href="${basePath }"><img src="${basePath }res/client/css/img/wx_logo.png" /></a><img src="${basePath }res/client/css/img/wx_logo_b.png" /></div>
	<div id="changeInfo" class="wx_nav">
		<c:if test="${frontUserInfo == null}">
			<div id="headFirst" style="display: inline;float: left;padding-left: 45px;">
				<span>用户名：</span><input name="username" id="username" type="text"/>
				<span>密码：</span><input name="password" id="password" type="password"/>
				<input name="frontLogin" id="frontLogin" type="button" value="登陆" />
				<%--<span style="padding: 0px;"><a href="${basePath}loginOut">忘记密码?</a></span>--%>
			</div>
			<div style="display: inline;float: right; padding-right: 45px;">
				<!-- 修改对应的aciton路径 -->
				<html:form action="/client/index/search.do" method="post" onsubmit="return checkSearchSubmit();">
					<span>快速搜索：</span><input type="text" id="word" name="word" value="${param.word }" /><input id="searchBtn" type="submit" value="搜索" />
					<span><a href="${bathPath }signup">注册博客</a></span>
				</html:form>
			</div>
		</c:if>
		<c:if test="${frontUserInfo != null}">
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
		</c:if>
	</div>
</div>
