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
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp"></jsp:include>
		<s:form action="sysLog!findLoginLogsByUser.action" method="post"
			id="myform">
			<div class="queryheader">
				用户名：
				<input type="text" name="userName" id="userName" value="${userName}" />
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
						用户名
					</th>
					<th>
						账户
					</th>
					<th width="150">
						登录时间
					</th>
					<th width="150">
						登录IP
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
							${hrUserLog.hrUser.name}
						</td>
						<td>
							${hrUserLog.hrUser.account}
						</td>
						<td>
							<s:date name="#attr.hrUserLog.updateTime"
								format="yyyy-MM-dd HH:mm:ss" nice="false" />
						</td>
						<td>
							${hrUserLog.ip}
						</td>

					</tr>
				</c:forEach>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="${selectUrl}" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
