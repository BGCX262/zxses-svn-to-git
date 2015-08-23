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
		<title>号码段管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript">
		
		function setFocusInput(focusInputId){
			if(focusInputId==""){
				document.getElementById("numStaNumber").focus();
				document.getElementById("numStaNumber").select();
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
				value="返回--view/numManage!listMan.action?theme=simple" />
		</jsp:include>

		<s:form action="numManage!listNumSegment.action" method="post"
			id="myform">
			<s:hidden name="focusInputId"></s:hidden>
			<s:hidden name="manufacture.id"></s:hidden>
			<div class="queryheader">
				起始号：
				<s:textfield name="numSegmentOp.staNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="numStaNumber"></s:textfield>
				&nbsp;&nbsp; 终止号：
				<s:textfield name="numSegmentOp.endNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="numEndNumber"></s:textfield>
				&nbsp;&nbsp;箱号：
				<s:textfield name="numSegmentOp.boxNo" cssClass="zd1Solid"
					cssStyle="width:17%"></s:textfield>
				&nbsp;
				<s:submit cssClass="an" value="查询"></s:submit>
			</div>
			<hr />
			<table id="mytable">
				<tr>
					<th width="120">
						箱号
					</th>
					<th>
						收货地址
					</th>
					<th>
						数量
					</th>
					<th>
						起始号
					</th>
					<th>
						检码
					</th>
					<th>
						终止号
					</th>
					<th>
						检码
					</th>
					<th width="120">
						重量(kg)
					</th>
					<th width="60">
						操作
					</th>
				</tr>
				<s:iterator value="numSegments" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							第
							<s:property value="boxNo" />
							箱
						</td>
						<td>
							<s:property value="puriAddress" />
						</td>
						<td>
							<s:property value="quantity" />
						</td>
						<td>
							<s:property
								value="manufacture.prefix+staNumber+manufacture.suffix" />
						</td>
						<td>
							<s:property value="staCheckCode" />
						</td>
						<td>
							<s:property
								value="manufacture.prefix+endNumber+manufacture.suffix" />
						</td>
						<td>
							<s:property value="endCheckCode" />
						</td>
						<td>
							<s:property value="weight" />
						</td>
						<td>
							<a
								href="view/numManage!entryUpdateNumSeg.action?numSegment.id=${id }&manufacture.id=${manufacture.id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">修改</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/numManage!listNumSegment.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
