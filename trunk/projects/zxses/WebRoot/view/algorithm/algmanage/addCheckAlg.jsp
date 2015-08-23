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
		<title>新增检码算法</title>
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

	<body class="centerbg" onload="setFocusInput()">
		<s:form action="algManage!saveCheckAlg.action" method="post">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="addCheckAlg.jsp"></s:hidden>
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
					<tr>
						<td>
							&nbsp;
						</td>
						<td class="tdright">
							<span style="color: red">注：【算法公式】中的号码段变量务必用"<b>\${var1}</b>"表示</span>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:submit cssClass="an" value="保存"></s:submit>
							&nbsp;
							<button class="an" onclick="clearForm()" type="button"
								style="width: 50px;">
								重置
							</button>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
