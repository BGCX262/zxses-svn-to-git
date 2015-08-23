<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>系统管理</title>
		<%@ include file="/inc/common.inc"%>
	</head>
	<body>
		<table class="pathbg">
			<tr>
				<td align="left">
					${navigationBar}
				</td>
				<td align="right">
					<img src="<%=basePath%>theme/blue/images/close.gif"
						onclick="window.parent.hidewindow();" style="cursor: pointer"
						border="0" alt="关闭" />
				</td>
			</tr>
		</table>
		<table width="100%" style="margin-top: 3px;">
			<tr>
				<td style="padding-left: 5px; text-indent: 3px;" align="left">
					<strong>错误消息：</strong>${hrUserLog.title}
				</td>
			</tr>

			<tr>
				<td style="padding-left: 5px; text-indent: 3px;" align="left">
					${hrUserLog.content}
				</td>
			</tr>
		</table>
	</body>
</html>
