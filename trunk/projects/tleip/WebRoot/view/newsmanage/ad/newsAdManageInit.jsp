<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息发布管理系统</title>
	</head>
	<table Height="100%" Width="100%">
		<tr>
			<td Height="100%" Width="15%">
				<iframe name="newsAdTree" scrolling="yes" Height="100%" Width="100%"
					noResize src="<%=basePath%>view/newsmanage.do?method=findAdMangeTree">
				</iframe>
			</td>
			<td Height="100%" Width="85%">
				<iframe name="newsAdManageList" scrolling="yes" Height="100%"
					Width="100%" noresize
					src="<%=basePath%>view/newsmanage.do?method=findNewsAdManage">
				</iframe>
			</td>
		</tr>
	</table>
</html>