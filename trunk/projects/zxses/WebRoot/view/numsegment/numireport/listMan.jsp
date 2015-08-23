<%@ page language="java" pageEncoding="utf-8"%>
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
		<title>出货单打印</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>js/core.js"></script>
		<script type="text/javascript">
			function searchMan(){
				document.myform.action= 'view/numIReport!listMan.action';
				document.myform.target="_self";
		    	document.myform.submit();
			}
			
		    function numSegmentsPrint(actionUrl){
       			if(isAcrobatPluginInstall()){
       				if ($("input[name=checkIds]:checked").length == 0) {
						alert("请选择需要操作的记录！");
						return;
					}
		    		document.myform.action= actionUrl;
		    		document.myform.target="_blank";
		    		document.myform.submit();
		    	}else{
		    		if(confirm("没有安装预览插件,是否下载？")){
       					document.myform.action= 'tools/pdftool/AdbeRdr930_zh_CN.exe';
       					document.myform.target="_blank";
		    			document.myform.submit();
       				}
       			}
       		}
       		
       		function setFocusInput(focusInputId){
				if(focusInputId==""){
					document.getElementById("manStaNumber").focus();
					document.getElementById("manStaNumber").select();
				}else{
					document.getElementById(focusInputId).focus();
					document.getElementById(focusInputId).select();
				}
			}
		    </script>
	</head>
	<body class="centerbg" onload="setFocusInput('${focusInputId }')">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="出货单打印--javascript:numSegmentsPrint('view/numIReport!mansPrint.action')
					,包装清单打印--javascript:numSegmentsPrint('view/numIReport!numSegsListPrint.action')" />
		</jsp:include>

		<s:form action="numIReport!listMan.action" method="post" id="myform"
			onsubmit="return searchMan()">
			<s:hidden name="focusInputId"></s:hidden>
			<table class="queryheader" width="100%">
				<tr>
					<td width="5%" height="30px">
						&nbsp;
					</td>
					<td width="75%" align="right">
						起始号：
						<s:textfield name="manufactureOp.staNumber" cssClass="zd1Solid"
							cssStyle="width:22%" id="manStaNumber" tabindex="1"></s:textfield>
						&nbsp;&nbsp; 终止号：
						<s:textfield name="manufactureOp.endNumber" cssClass="zd1Solid"
							cssStyle="width:22%" id="manEndNumber" tabindex="2"></s:textfield>
						&nbsp;&nbsp;生产单号：
						<s:textfield name="manufactureOp.manNumber" cssClass="zd1Solid"
							cssStyle="width:22%" tabindex="3"></s:textfield>
					</td>
					<td rowspan="2" align="left" style="margin-left: 10px;">
						&nbsp;
						<s:submit cssClass="an" value="查询"></s:submit>
					</td>
				</tr>
				<tr>
					<td height="30px">
						&nbsp;
					</td>
					<td align="center">
						生产起始日期：
						<input type="text" id="staDate" name="staDate" size="18"
							class="zd1Solid" readonly="readonly" style="width: 17%"
							tabindex="4"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',el:'staDate',maxDate:'#F{$dp.$D(\'endDate\')}'})"
							value="${staDate }" />
						<img
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'staDate',maxDate:'#F{$dp.$D(\'endDate\')}'})"
							style="cursor: pointer;"
							src="tools/My97DatePicker/skin/datePicker.gif" width="16"
							height="22" />
						&nbsp;&nbsp; 生产结束日期：
						<input type="text" name="endDate" id="endDate" size="18"
							readonly="readonly" class="zd1Solid" style="width: 17%"
							tabindex="5"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endDate',minDate:'#F{$dp.$D(\'staDate\')}'})"
							value="${endDate }" />
						<img
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endDate',minDate:'#F{$dp.$D(\'staDate\')}'})"
							style="cursor: pointer;"
							src="tools/My97DatePicker/skin/datePicker.gif" width="16"
							height="22" />
					</td>
				</tr>
			</table>

			<hr />
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th width="120">
						生产单号
					</th>
					<th>
						客户名称
					</th>
					<th>
						产品名称
					</th>
					<th>
						起始号
					</th>
					<th>
						终止号
					</th>
					<th width="120">
						产品数量
					</th>
					<th width="60">
						操作
					</th>
				</tr>
				<s:iterator value="manufactures" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							<a
								href="view/numIReport!listNumSegment.action?manufacture.id=${id }"
								style="color: blue;"><s:property value="manNumber" /> </a>
						</td>
						<td>
							<s:property value="cusName" />
						</td>
						<td>
							<s:property value="proName" />
						</td>
						<td>
							<s:property value="prefix+staNumber+suffix" />
						</td>
						<td>
							<s:property value="prefix+endNumber+suffix" />
						</td>
						<td>
							<s:property value="quaTotal" />
						</td>
						<td>
							<a
								href="view/numIReport!entryInfoMan.action?manufacture.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">详情</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/numIReport!listMan.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
