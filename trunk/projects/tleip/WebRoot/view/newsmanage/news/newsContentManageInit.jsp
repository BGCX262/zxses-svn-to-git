<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息发布管理系统</title>
	</head>
	<table Height="100%" Width="100%">
		<tr>
			<td Height="100%" Width="18%">
				<iframe name="newsItemsBigTree" scrolling="yes" frameBorder="0"
					Height="100%" Width="100%" 
					src="<%=basePath%>view/newsmanage.do?method=findNewsItemsBigTree"></iframe>
			</td>
			<td Height="100%" Width="82%">
				<iframe name="newsList" scrolling="yes" frameBorder="0"
					Height="100%" Width="100%" src="<%=basePath%>view/right.jsp"></iframe>
			</td>
		</tr>
	</table>
</html>
