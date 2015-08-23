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
		<title>检码算法管理</title>
		<%@ include file="/inc/common.inc"%>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp" />

		<s:form action="algSearch!listCheckAlgs.action" method="post"
			id="myform">
			<table id="mytable">
				<tr>
					<th>
						算法名称
					</th>
					<th width="120">
						是否启用
					</th>
					<th width="60">
						操作
					</th>
				</tr>
				<s:iterator value="checkAlgs" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:property value="algName" />
						</td>
						<td>
							<s:if test="isEnable==0">
								<span style="color: red;">禁用</span>
							</s:if>
							<s:else>
								<span>启用</span>
							</s:else>
						</td>
						<td>
							<a
								href="view/algSearch!entryInfoCheckAlg.action?checkAlg.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">详情</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/algSearch!listCheckAlgs.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
