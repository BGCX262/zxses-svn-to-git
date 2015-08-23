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
		<title>操作日志列表</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp" />

		<s:form action="opLog!list.action" method="post" id="myform">
			<div class="queryheader">
				用户名：
				<s:textfield name="hrUserOp.name" size="10"></s:textfield>
				&nbsp;&nbsp; 生产起始日期：
				<s:textfield id="staDate" name="staDate" size="18" readonly="true"
					cssClass="BigInput"></s:textfield>
				<img
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'staDate',maxDate:'#F{$dp.$D(\'endDate\')}'})"
					style="cursor: pointer;"
					src="tools/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" />
				&nbsp;&nbsp; 生产结束日期：
				<s:textfield name="endDate" id="endDate" size="18" readonly="true"
					cssClass="BigInput"></s:textfield>
				<img
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endDate',minDate:'#F{$dp.$D(\'staDate\')}'})"
					style="cursor: pointer;"
					src="tools/My97DatePicker/skin/datePicker.gif" width="16"
					height="22" />
				<s:submit cssClass="an" value="查询"></s:submit>
			</div>
			<hr />
			<table id="mytable">
				<tr>
					<th width="120">
						用户名
					</th>
					<th>
						操作信息
					</th>
					<th>
						IP
					</th>
					<th width="120">
						操作时间
					</th>
				</tr>
				<s:iterator value="hrUserLogs" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:property value="hrUser.name" />
						</td>
						<td>
							<s:property value="title" />
						</td>
						<td>
							<s:property value="ip" />
						</td>
						<td>
							<s:date name="createTime" format="yyyy-MM-dd HH:mm" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/opLog!list.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
