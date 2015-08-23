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
		<title>新增生产单信息</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript">
		function clearForm(){
			document.getElementById("manOrderNumber").value="";
			document.getElementById("manManNumber").value="";
			document.getElementById("manCusName").value="";
			document.getElementById("manProName").value="";
			document.getElementById("manNumType").value="";
			document.getElementById("manPuriAddress").value="";
			document.getElementById("manVerNumber").value="";
			document.getElementById("manMatCode").value="";
			document.getElementById("staDate").value="";
			document.getElementById("manQuaTotal").value="";
			document.getElementById("manAveQuantity").value="";
			document.getElementById("manAveWeight").value="";
			document.getElementById("manManufacturer").value="";
			document.getElementById("manPhone").value="";
			document.getElementById("manProAddress").value="";
		}
		
		function backAddNum(){
			$("form")[0].action = 'view/numManage!entryAddNum.action';
			$("form")[0].submit();
		}
		
		function isCon(){
			document.getElementById("hiddenManCheckAlgorithm").value=document.getElementById("manCheckAlgorithm").value;
			document.myform.submit();
		}
		
		function isNoCon(){
			document.getElementById("isContinue").value="";
			document.myform.action="view/numManage!entryAddMan.action";
			document.myform.submit();
		}
		</script>
	</head>

	<body class="centerbg">
		<s:form action="saveMan" method="post" id="myform">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="addMan.jsp"></s:hidden>
			<s:hidden name="manufacture.staNumber"></s:hidden>
			<s:hidden name="manufacture.endNumber"></s:hidden>
			<s:hidden name="manufacture.prefix"></s:hidden>
			<s:hidden name="manufacture.suffix"></s:hidden>
			<s:hidden name="isContinue"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft2zx">
							订单号：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.orderNumber" cssClass="zd2Solid"
								id="manOrderNumber"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>manufacture.orderNumber</s:param>
							</s:fielderror>
						</td>
						<td class="tdleft2zx">
							生产单号：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.manNumber" cssClass="zd2Solid"
								id="manManNumber"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>manufacture.manNumber</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							客户名称：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.cusName" cssClass="zd2Solid"
								id="manCusName"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>manufacture.cusName</s:param>
							</s:fielderror>
						</td>
						<td class="tdleft2zx">
							产品名称：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.proName" cssClass="zd2Solid"
								id="manProName"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>manufacture.proName</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							号码类型：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.numType" cssClass="zd2Solid"
								id="manNumType"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
						<td class="tdleft2zx">
							收货地址：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.puriAddress" cssClass="zd2Solid"
								id="manPuriAddress"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							检验码类型：
						</td>
						<td class="tdright2zx">
							<s:if test="isContinue!=1 && isContinue!=2">
								<s:select name="manufacture.checkAlgorithm.id" list="checkAlgs"
									listKey="id" listValue="algName" cssClass="zd2Solid"
									id="manCheckAlgorithm"></s:select>
							</s:if>
							<s:else>
								<s:select name="manufacture.checkAlgorithm.id" list="checkAlgs"
									listKey="id" listValue="algName" cssClass="zd2Solid"
									id="manCheckAlgorithm" disabled="true"></s:select>
								<s:hidden id="hiddenManCheckAlgorithm"
									name="manufacture.checkAlgorithm.id"></s:hidden>
							</s:else>
						</td>
						<td class="tdleft2zx">
							版本号：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.verNumber" cssClass="zd2Solid"
								id="manVerNumber"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							物料编码：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.matCode" cssClass="zd2Solid"
								id="manMatCode"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
						<td class="tdleft2zx">
							生产日期：
						</td>
						<td class="tdright2zx">
							<s:textfield id="staDate" name="staDate" size="18"
								readonly="true" cssClass="zd2Solid"></s:textfield>
							<s:if test="isContinue!=1 && isContinue!=2">
								<img
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',el:'staDate'})"
									style="cursor: pointer;"
									src="tools/My97DatePicker/skin/datePicker.gif" width="16"
									height="22" />
							</s:if>
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							产品数量：
						</td>
						<td class="tdright2zx" colspan="3">
							<s:textfield name="tmpQuaTotal" cssClass="zd2Solid"
								id="manQuaTotal"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>tmpQuaTotal</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							每箱数量：
						</td>
						<td class="tdright2zx">
							<s:textfield name="tmpAveQuantity" cssClass="zd2Solid"
								id="manAveQuantity"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>tmpAveQuantity</s:param>
							</s:fielderror>
						</td>
						<td class="tdleft2zx">
							每箱重量：
						</td>
						<td class="tdright2zx">
							<s:textfield name="tmpAveWeight" cssClass="zd2Solid"
								id="manAveWeight"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>tmpAveWeight</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							制造商：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.manufacturer" cssClass="zd2Solid"
								id="manManufacturer"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
						<td class="tdleft2zx">
							电话：
						</td>
						<td class="tdright2zx">
							<s:textfield name="manufacture.phone" cssClass="zd2Solid"
								id="manPhone"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
					</tr>
					<tr>
						<td class="tdleft2zx">
							生产地址：
						</td>
						<td class="tdright2zx" colspan="3">
							<s:textfield name="manufacture.proAddress" cssClass="zd2Solid"
								id="manProAddress"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:if test="isContinue!=1 && isContinue!=2">
								<s:submit cssClass="an" value="保存"></s:submit>
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
									value="返回" onclick="backAddNum()" />
							&nbsp;
							<button class="an" onclick="clearForm()" type="button">
									重置
								</button>
							</s:if>
							<s:else>
								<s:submit cssClass="an" value="保存" disabled="true"></s:submit>
							&nbsp;
							<input name="button2" type="button" class="an" id="button2"
									value="返回" onclick="backAddNum()" disabled="disabled" />
							&nbsp;
							<button class="an" onclick="clearForm()" type="button"
									disabled="disabled">
									重置
								</button>
							</s:else>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
