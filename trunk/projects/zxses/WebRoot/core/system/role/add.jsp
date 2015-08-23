<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>角色新增</title>
		<%@ include file="/inc/common.inc"%>
	</head>

	<body class="centerbg">
		<s:form action="sysRole!saveSysRole.action" method="post">
			<jsp:include page="/core/header.jsp"/>
			<s:hidden name="inputPage" value="add.jsp"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					 
					<tr>
						<td class="tdleft">
							编码：
						</td>
						<td class="tdright">
							<s:textfield name="sysRole.code" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>sysRole.code</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							名称：
						</td>
						<td class="tdright">
							<s:textfield name="sysRole.name" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>sysRole.name</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:submit cssClass="an" value="保存"></s:submit>
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
								value="返回"
								onclick="window.location.href='sysRole!listSysRole.action'" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
