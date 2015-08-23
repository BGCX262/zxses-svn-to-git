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
		
		</script>
	</head>
	<body class="centerbg" onload="setFocusInput('${focusInputId }')">
		<jsp:include page="/core/header.jsp" />

		<s:form action="misSearch!listMan.action" method="post" id="myform">
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
							<a href="view/misSearch!listMis.action?manufacture.id=${id }"
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
								href="view/misSearch!entryInfoMan.action?manufacture.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">详情</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/misSearch!listMan.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
