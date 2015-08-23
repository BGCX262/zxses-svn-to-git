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
		<title>缺失号码段导入</title>
		<%@ include file="/inc/common.inc"%>
	</head>

	<body class="centerbg">
		<jsp:include page="/core/header.jsp" />
		<s:form action="misManage!importMisData.action" id="myForm"
			method="post" name="myForm" namespace="/view" theme="simple"
			enctype="multipart/form-data">
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft">
							文件模版：
						</td>
						<td class="tdright">
							<a href="view/misManage!downMisExcel.action"
								class="linkhong chuti textindent3">下载模版请点击</a>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							操作提示：
						</td>
						<td class="tdright">
							1、模板中标红的字段为必填字段!
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							&nbsp;
						</td>
						<td class="tdright">
							2、导入的文件中,如有与数据库中相同的记录,则进行修改操作!
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							文件路径：
						</td>
						<td class="tdright">
							<s:file name="importFile" id="importFileId"></s:file>
							<s:fielderror theme="simple">
								<s:param>importFileFileName</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:submit cssClass="an" value="导入"></s:submit>
						</td>
					</tr>
				</table>
			</div>
			<table class="edittable">
				<s:iterator value="actionMessages">
					<tr>
						<td class="tdright" style="color: #eaa000; height: 25px;">
							<s:property />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
	</body>
</html>
