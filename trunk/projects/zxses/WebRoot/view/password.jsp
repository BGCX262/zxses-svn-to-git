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
		<title>密码修改</title>
		<%@ include file="/inc/theme.inc"%>
	</head>

	<body class="centerbg">
		<s:form action="main!savePassword.action" method="post">
			<s:token></s:token>
			<jsp:include page="/core/header.jsp"/>
			<s:hidden name="inputPage" value="add.jsp"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft">
							旧密码：
						</td>
						<td class="tdright">
							<s:password name="password" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>password</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							新密码：
						</td>
						<td class="tdright">
							<s:password  name="newpassword" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>newpassword</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							确认密码：
						</td>
						<td class="tdright">
							<s:password name="agnewpassword" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>agnewpassword</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<input name="button" type="submit" class="an" id="button"
								value="保存" />
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
								value="清空"
								onclick="window.location='<%=basePath%>view/password.jsp'"" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
