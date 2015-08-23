<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/include.jsp"%>

	<%--
<ul class="for_form_sec">
	<li>欢迎 ${frontUserFrom.userAccount }</li>
	<li><a href="${basePath}front/login.do?method=toChangeInfo">修改个人信息</a></li>
	<li><a href="${basePath}client/index/content/login_reg/changePw.jsp">修改密码</a></li>
	<li><a href="${basePath}front/login.do?method=loginOut">注销</a></li>
</ul>
--%>
<div class="for_form_sec">
	<p>
		<span>欢迎您：${frontUserInfo.userAccount }</span>
		<a href="${basePath}front/login.do?method=toChangeInfo">【修改个人信息】</a>
		<a href="${basePath}client/index/content/login_reg/changePw.jsp">【修改密码】</a>
		<a href="${basePath}front/login.do?method=loginOut">【注销】</a>
	</p>		
</div>
