<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息发布管理系统</title>
		<script language="javascript" type="text/javascript" src="<%=basePath%>res/admin/js/leftswitch.js"></script>
	</head>
	<body leftMargin=0 topMargin=0 marginwidth="0" marginheight="0"
		onload="MM_preloadimage('<%=basePath%>res/admin/theme/blue/images/mainleftspico.gif','<%=basePath%>res/admin/theme/blue/images/mainleftspico2.gif')">
		<div onclick=oa_tool1() align=center>
			<table cellSpacing=0 cellPadding=0 width="12" border=0 height="100%">
				<tr>
					<td width="12" align="center"
						background="<%=basePath%>res/admin/theme/blue/images/main09.gif">
						<img src="<%=basePath%>res/admin/theme/blue/images/mainleftspico.gif"
							alt="隐藏" name="frameshow1" id=frameshow1 style="CURSOR: hand"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>