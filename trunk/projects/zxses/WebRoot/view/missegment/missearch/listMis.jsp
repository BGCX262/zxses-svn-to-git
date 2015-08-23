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
		<title>缺失号码查询</title>
		<%@ include file="/inc/common.inc"%>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="返回--view/misSearch!listMan.action?theme=simple&paginate.offset=${paginate.offset }" />
		</jsp:include>
		<table id="mytable">
			<tr>
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
					补号
				</th>
			</tr>
			<s:iterator value="misSegments" status="i">
				<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
					onMouseOut="this.className='tr${i.index%2+1}'">
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
						<s:if test="isFilling==0">
							<span style="color: blue;">未补号</span>
						</s:if>
						<s:else>
							<span style="color: red;"> 已补号</span>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</table>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/misSearch!listMis.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>