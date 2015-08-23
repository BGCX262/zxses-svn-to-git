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
		<title>生产单管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript">
		
		function setFocusInput(focusInputId){
			if(focusInputId==""){
				document.getElementById("manStaNumber").focus();
				document.getElementById("manStaNumber").select();
			}else{
				document.getElementById(focusInputId).focus();
				document.getElementById(focusInputId).select();
			}
		}
		
		/**全选功能**/
		function setAllManCheck(allCheck) {
			for (var i = 0; i < $("input[name=checkManIds]").length; i++) {
				$("input[name=checkManIds]")[i].checked = allCheck.checked;
			}
		}
		
		/**获得选中复选框的值**/
		function getCheckValues() {
			var checkValues = "";
			for (var i = 0; i < $("input[name=checkManIds]:checked").length; i++) {
				if (i == 0) {
					checkValues += $("input[name=checkManIds]:checked")[i].value;
				} else {
					checkValues += ("," + $("input[name=checkManIds]:checked")[i].value);
				}
			}
			return checkValues;
		}

		function addNumMis(actionUrl){
			if ($("input[name=checkManIds]:checked").length == 0) {
				alert("请选择需要操作的记录！");
				return;
			}
			document.location.href = actionUrl+"&checkManIds="+getCheckValues();
		}
		
		function exportManMisNum(actionUrl){
			if ($("input[name=checkManIds]:checked").length == 0) {
				alert("请选择需要操作的记录！");
				return;
			}
			document.location.href = actionUrl+"?checkManIds="+getCheckValues();
		}
		</script>
	</head>
	<body class="centerbg" onload="setFocusInput('${focusInputId }')">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="录入缺失号--javascript:addNumMis('view/misManage!listMis.action?theme=simple')
						,导出--javascript:exportManMisNum('view/misManage!exportMisData.action')" />
		</jsp:include>

		<s:form action="misManage!listMan.action" method="post" id="myform">
			<s:hidden name="focusInputId"></s:hidden>
			<div class="queryheader">
				起始号：
				<s:textfield name="manufactureOp.staNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="manStaNumber"></s:textfield>
				&nbsp;&nbsp; 终止号：
				<s:textfield name="manufactureOp.endNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="manEndNumber"></s:textfield>
				&nbsp;&nbsp;生产单号：
				<s:textfield name="manufactureOp.manNumber" cssClass="zd1Solid"
					cssStyle="width:17%"></s:textfield>
				&nbsp;
				<s:submit cssClass="an" value="查询"></s:submit>
			</div>
			<hr />
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllManCheck(this)" />
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
							<s:checkbox name="checkManIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							<a href="view/misManage!listMis.action?checkManIds=${id }"
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
								href="view/misManage!entryInfoMan.action?manufacture.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">详情</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/misManage!listMan.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
