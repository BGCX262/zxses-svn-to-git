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
		<title>修改号码段信息</title>
		<%@ include file="/inc/common.inc"%>
	</head>

	<body class="centerbg">
		<s:form action="numManage!updateNumSeg.action" method="post">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="updateNumSeg.jsp"></s:hidden>
			<s:hidden name="numSegment.id"></s:hidden>
			<s:hidden name="manufacture.id"></s:hidden>
			<s:hidden name="numSegment.staNumber"></s:hidden>
			<s:hidden name="numSegment.endNumber"></s:hidden>
			<s:hidden name="numSegment.manufacture.prefix"></s:hidden>
			<s:hidden name="numSegment.manufacture.suffix"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft2zx">
							箱号：
						</td>
						<td class="tdright2zx" colspan="3">
							<s:textfield name="numSegment.boxNo" cssClass="zd2Solid"
								readonly="true" cssStyle="color:gray;" />
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							起始号：
						</td>
						<td class="tdright2zx">
							<s:textfield name="numSegStaNumber" cssClass="zd2Solid"
								readonly="true" cssStyle="color:gray;"
								value="%{numSegment.manufacture.prefix+numSegment.staNumber+numSegment.manufacture.suffix}" />
						</td>
						<td class="tdleft2zx">
							终止号：
						</td>
						<td class="tdright2zx">
							<s:textfield name="numSegEndNumber" cssClass="zd2Solid"
								readonly="true" cssStyle="color:gray;"
								value="%{numSegment.manufacture.prefix+numSegment.endNumber+numSegment.manufacture.suffix}" />
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							数量：
						</td>
						<td class="tdright2zx">
							<s:textfield name="numQuantity" cssClass="zd2Solid" />
							<s:fielderror theme="simple">
								<s:param>numQuantity</s:param>
							</s:fielderror>
						</td>
						<td class="tdleft2zx">
							重量：
						</td>
						<td class="tdright2zx">
							<s:textfield name="numWeight" cssClass="zd2Solid" />
							<s:fielderror theme="simple">
								<s:param>numWeight</s:param>
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
								onclick="window.location.href='view/numManage!entryUpdateNumSeg.action?numSegment.id=${numSegment.id }&manufacture.id=${manufacture.id}&theme=simple&paginate.offset=${paginate.offset }'" />
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
								value="返回"
								onclick="window.location.href='view/numManage!listNumSegment.action?manufacture.id=${manufacture.id}&theme=simple&paginate.offset=${paginate.offset }'" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
