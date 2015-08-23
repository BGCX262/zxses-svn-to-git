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
		<title>生产单信息详细</title>
		<%@ include file="/inc/common.inc"%>
	</head>

	<body class="centerbg">
		<jsp:include page="/core/header.jsp" />
		<div class="editdiv">
			<table class="edittable">
				<tr>
					<td class="tdleft2zx">
						起始号：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.staNumber" cssClass="zd2Solid"
							id="manStaNumber" readonly="true"
							value="%{manufacture.staNumber}" />
					</td>
					<td class="tdleft2zx">
						终止号：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.endNumber" cssClass="zd2Solid"
							id="manEndNumber" readonly="true"
							value="%{manufacture.endNumber}" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						订单号：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.orderNumber" cssClass="zd2Solid"
							id="manOrderNumber" readonly="true" />
					</td>
					<td class="tdleft2zx">
						生产单号：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.manNumber" cssClass="zd2Solid"
							id="manManNumber" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						客户名称：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.cusName" cssClass="zd2Solid"
							id="manCusName" readonly="true" />
					</td>
					<td class="tdleft2zx">
						产品名称：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.proName" cssClass="zd2Solid"
							id="manProName" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						号码类型：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.numType" cssClass="zd2Solid"
							id="manNumType" readonly="true" />
					</td>
					<td class="tdleft2zx">
						收货地址：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.puriAddress" cssClass="zd2Solid"
							id="manPuriAddress" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						检验码类型：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.checkAlgorithm.algName"
							cssClass="zd2Solid" id="manCheckAlgorithm" readonly="true" />
					</td>
					<td class="tdleft2zx">
						版本号：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.verNumber" cssClass="zd2Solid"
							id="manVerNumber" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						前缀：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.prefix" cssClass="zd2Solid"
							id="manPrefix" readonly="true" />
					</td>
					<td class="tdleft2zx">
						后缀：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.suffix" cssClass="zd2Solid"
							id="manSuffix" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						物料编码：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.matCode" cssClass="zd2Solid"
							id="manMatCode" readonly="true" />
					</td>
					<td class="tdleft2zx">
						生产日期：
					</td>
					<td class="tdright2zx">
						<s:textfield name="staDate" cssClass="zd2Solid" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						产品数量：
					</td>
					<td class="tdright2zx" colspan="3">
						<s:textfield name="manufacture.quaTotal" cssClass="zd2Solid"
							id="manQuaTotal" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						每箱数量：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.aveQuantity" cssClass="zd2Solid"
							id="manAveQuantity" readonly="true" />
					</td>
					<td class="tdleft2zx">
						每箱重量：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.aveWeight" cssClass="zd2Solid"
							id="manAveWeight" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						制造商：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.manufacturer" cssClass="zd2Solid"
							id="manManufacturer" readonly="true" />
					</td>
					<td class="tdleft2zx">
						电话：
					</td>
					<td class="tdright2zx">
						<s:textfield name="manufacture.phone" cssClass="zd2Solid"
							id="manPhone" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="tdleft2zx">
						生产地址：
					</td>
					<td class="tdright2zx" colspan="3">
						<s:textfield name="manufacture.proAddress" cssClass="zd2Solid"
							id="manProAddress" readonly="true" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td height="32" align="left">
						<input name="button2" type="button" class="an" id="button2"
							value="返回"
							onclick="window.location.href='view/misSearch!listMan.action?theme=simple&paginate.offset=${paginate.offset }'" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
