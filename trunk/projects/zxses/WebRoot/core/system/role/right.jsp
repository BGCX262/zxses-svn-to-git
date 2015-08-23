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
		<title>菜单管理</title>
		<%@ include file="/inc/common.inc"%>
	</head>

	<body>
		<center>
			<div id="msgdiv" style="line-height: 20px; color: white;">
				<s:if test="#attr.messageInfo!=null">
					<span
						style="vertical-align: middle; padding: 10px 0px 5px 5px; background-color: #68AF02;"><s:property
							value="%{#attr.messageInfo}" /> </span>
				</s:if>
				<s:if test="#attr.messageError!=null">
					<span
						style="vertical-align: middle; padding: 10px 0px 5px 5px; background-color: #eaa000"><s:property
							value="%{#attr.messageError}" /> </span>
				</s:if>
			</div>
		</center>
		<s:form action="sysRole!saveUserMenu.action" method="post">
			<s:hidden name="roleId"></s:hidden>
			<s:hidden name="checkNodeIds"></s:hidden>
			<div class="percentage100 tdbk">
					<jsp:include page="/tag/tree/ztree.jsp">
						<jsp:param name="listName" value="menuList" />
						<jsp:param name="nodeId" value="id" />
						<jsp:param name="parentNodeId" value="sysMenu.id" />
						<jsp:param name="nodeName" value="name" />
						<jsp:param name="theme" value="check" />
						<jsp:param name="open" value="true" />
						<jsp:param name="checkNodeIds" value="${checkNodeIds}"/>
					</jsp:include>
			</div>
		</s:form>
	</body>
</html>
