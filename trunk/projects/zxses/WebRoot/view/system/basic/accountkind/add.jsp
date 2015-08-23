<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>户口性质新增</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript">
		function clearForm(){
			document.getElementById("checkAlgName").value="";
			document.getElementById("checkAlgContent").value="";
		}
		
		function setFocusInput(){
			document.getElementById("checkAlgName").focus();
		}
		</script>
	</head>

	<body class="centerbg">
		<s:form action="accountKind!save.action" method="post" target="0">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="add.jsp"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft1zx">
							户口编号：
						</td>
						<td class="tdright">
							<s:textfield name="accountKind.accountKindCode"
								cssClass="zd1Solid" id="accountKindCode" />
							<s:fielderror theme="simple">
								<s:param>accountKind.accountKindCode</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft1zx">
							户口名称：
						</td>
						<td class="tdright">
							<s:textarea name="accountKind.accountKindName"
								id="accountKindName" cssStyle="width:80%" rows="10"></s:textarea>
							<s:fielderror theme="simple">
								<s:param>accountKind.accountKindName</s:param>
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
							<s:reset cssStyle="width: 50px;" value="重置"></s:reset>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
