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
		<title>用户角色</title>
		<%@ include file="/inc/common.inc"%>
		<s:head />
	</head>

	<body class="centerbg">
		<s:form action="hrUser!saveUserRole.action" method="get">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="userRole.jsp"></s:hidden>
			<s:hidden name="hrUser.id"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" style="font-weight: bold;">
							<s:optiontransferselect name="a" leftTitle="待选角色"
								rightTitle="已选角色" list="leftSysRole" listKey="id"
								listValue="name" multiple="true" doubleList="rightSysRole"
								doubleListKey="id" doubleListValue="name"
								doubleName="selectSysRole.id" doubleMultiple="true"
								allowUpDownOnLeft="false" allowUpDownOnRight="false"
								buttonCssClass="an"
								buttonCssStyle="margin-left: 10px;margin-right: 10px;"
								cssStyle="width:250px;" doubleCssStyle="width:250px;color:#68af02;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td height="32" align="center">
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