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
		<title>用户修改</title>
		<%@ include file="/inc/common.inc"%>

	</head>

	<body class="centerbg">
		<s:form action="hrUser!update.action" method="post">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="theme" value="%{#parameters.theme[0]}"></s:hidden>
			<s:hidden name="paginate.offset"></s:hidden>
			<s:hidden name="inputPage" value="update.jsp"></s:hidden>
			<s:hidden name="hrUser.id"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					 
					<tr>
						<td class="tdleft">
							登录名：
						</td>
						<td class="tdright">
							<s:textfield name="hrUser.account" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>hrUser.account</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							用户名：
						</td>
						<td class="tdright">
							<s:textfield name="hrUser.name" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>hrUser.name</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							邮箱：
						</td>
						<td class="tdright">
							<s:textfield name="hrUser.email" cssClass="zd1" />
							<s:fielderror theme="simple">
								<s:param>hrUser.email</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							是否启用：
						</td>
						<td class="tdright">
							<s:checkbox name="isEnable"></s:checkbox>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							用户类型：
						</td>
						<td class="tdright">
							<s:select name="hrUser.hrUserType.id" list="hrUserTypes"
								listKey="id" listValue="content"></s:select>
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
								onclick="window.location.href='view/hrUser!list.action?theme=simple&paginate.offset=${paginate.offset }'" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
