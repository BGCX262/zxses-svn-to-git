<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>系统日志</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>theme/blue/css/css.css" />
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>js/coolwindow.js"></script>
		<script language="javascript" type="text/javascript">
		function view(id){
         showwindow("<%=basePath%>view/sysLog!viewErrorLogs.action?logId="+id,600,400);
       }
	     </script>

	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="删除--javascript:deleteChecks('view/sysLog!deleteSysLogs.action?theme=simple&paginate.offset=${paginate.offset }')" />
		</jsp:include>
		<s:form action="sysLog!findErrorLogsByDate.action" method="post"
			id="myform">
			<div class="queryheader">
				开始日期：
				<s:textfield id="startDate" name="startDate" size="18"
					readonly="true" cssClass="BigInput"></s:textfield>
				<img onclick="WdatePicker({el:'startDate'})"
					style="cursor: pointer;"
					src="<%=basePath%>tools/My97DatePicker/skin/datePicker.gif" />
				&nbsp;&nbsp;结束日期：
				<s:textfield name="endDate" id="endDate" size="18" readonly="true"
					cssClass="BigInput"></s:textfield>
				<img onclick="WdatePicker({el:'endDate'})" style="cursor: pointer;"
					src="<%=basePath%>tools/My97DatePicker/skin/datePicker.gif" />
				&nbsp;&nbsp;
				<s:submit cssClass="an" value="查询"></s:submit>
			</div>
			<hr />
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th width="45">
						序号
					</th>
					<th>
						错误消息
					</th>
					<th width="150">
						时间
					</th>
					<th width="80">
						操作
					</th>
				</tr>
				<c:forEach var="hrUserLog" items="${hrUserLogs}" varStatus="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<input type="checkbox" name="checkIds" id="checkbox"
								value="${hrUserLog.id}" />
						</td>

						<td>
							${i.index+1}
						</td>
						<td>
							${hrUserLog.title}
						</td>
						<td>
							<s:date name="#attr.hrUserLog.updateTime"
								format="yyyy-MM-dd HH:mm:ss" nice="false" />
						</td>
						<td>
							<a href="javascript:view('${hrUserLog.id}')" class="opertion">详情</a>
							<a
								href="javascript:deleteRecord('view/sysLog!deleteSysLogs.action?checkIds=${hrUserLog.id}&theme=simple&paginate.offset=${paginate.offset }')"
								class="opertion">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="${errorLogUrl}" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>


