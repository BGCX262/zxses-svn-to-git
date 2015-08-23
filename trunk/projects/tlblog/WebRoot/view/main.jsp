<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>塘朗小学文学社管理系统</title>
	</head>

	<frameset rows="125,*,21" id="ldy" name="ldy" cols="*" frameborder="no"
		border="0" framespacing="0">
		<frame src="<%=basePath%>sys/login.do?method=topList" name="top" scrolling="no" noresize="noresize" id="top" />
		<frameset id="middle" cols="200,12,*" frameborder="no" border="0" framespacing="0">
			<frame src="<%=basePath%>sys/login.do?method=leftList" name="left" noresize="noresize"  scrolling="auto" id="left" />
			<frame src="<%=basePath%>view/MLeftSwitch.jsp" name="MLeftSwitch" scrolling="no" border="0" noresize="noresize" id="MLeftSwitch" />
			<frame src="<%=basePath%>view/right.jsp" name="right" id="right" scrolling="auto" />
		</frameset>
		<frame src="<%=basePath%>view/bottom.jsp" name="bottom" scrolling="No" noresize="noresize" id="bottom" />
	</frameset>
	<noframes><body></body></noframes>
	
