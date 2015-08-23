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
		<title>户口性质修改</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript">
		function setFocusInput(){
			document.getElementById("checkAlgName").focus();
		}
		</script>
	</head>

	<body class="centerbg">
		<s:form action="accountKind!update.action" method="post">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="update.jsp"></s:hidden>
			<s:hidden name="accountKind.id"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft1zx">
							算法名称：
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
							算法公式：
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
							<input name="button3" type="button" class="an" id="button3"
								value="重置"
								onclick="window.location.href='view/accountKind!entryUpdate.action?accountKind.id=${checkAlg.id}&theme=simple&paginate.offset=${paginate.offset }'" />
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
								value="返回"
								onclick="window.location.href='view/accountKind!list.action?theme=simple&paginate.offset=${paginate.offset }'" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
