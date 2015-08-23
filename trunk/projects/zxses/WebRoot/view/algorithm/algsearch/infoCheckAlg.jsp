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
		<title>检码算法详情</title>
		<%@ include file="/inc/common.inc"%>
	</head>

	<body class="centerbg">
		<s:form action="algSearch!entryInfoCheckAlg.action" method="post">
			<jsp:include page="/core/header.jsp" />
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft1zx">
							算法名称：
						</td>
						<td class="tdright">
							<s:textfield name="checkAlg.algName" cssClass="zd1Solid"
								id="checkAlgName" readonly="true" />
						</td>
					</tr>
					<tr>
						<td class="tdleft1zx">
							算法公式：
						</td>
						<td class="tdright">
							<s:textarea name="checkAlg.algContent" id="checkAlgContent"
								cssStyle="width:80%" rows="10" readonly="true"></s:textarea>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<input name="button2" type="button" class="an" id="button2"
								value="返回"
								onclick="window.location.href='view/algSearch!listCheckAlgs.action?theme=simple&paginate.offset=${paginate.offset }'" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
