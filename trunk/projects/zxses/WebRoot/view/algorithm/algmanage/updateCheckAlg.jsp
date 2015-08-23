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
		<title>修改检码算法信息</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript">
		function setFocusInput(){
			document.getElementById("checkAlgName").focus();
		}
		</script>
	</head>

	<body class="centerbg" onload="setFocusInput()">
		<s:form action="algManage!updateCheckAlg.action" method="post">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="updateCheckAlg.jsp"></s:hidden>
			<s:hidden name="checkAlg.id"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft1zx">
							算法名称：
						</td>
						<td class="tdright">
							<s:textfield name="checkAlg.algName" cssClass="zd1Solid"
								id="checkAlgName" />
							<s:fielderror theme="simple">
								<s:param>checkAlg.algName</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft1zx">
							算法公式：
						</td>
						<td class="tdright">
							<s:textarea name="checkAlg.algContent" id="checkAlgContent"
								cssStyle="width:80%" rows="10"></s:textarea>
							<s:fielderror theme="simple">
								<s:param>checkAlg.algContent</s:param>
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
								onclick="window.location.href='view/algManage!entryUpdateCheckAlg.action?checkAlg.id=${checkAlg.id}&theme=simple&paginate.offset=${paginate.offset }'" />
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
								value="返回"
								onclick="window.location.href='view/algManage!listCheckAlgs.action?theme=simple&paginate.offset=${paginate.offset }'" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
