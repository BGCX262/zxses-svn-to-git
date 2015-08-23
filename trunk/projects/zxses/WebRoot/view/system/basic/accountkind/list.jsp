<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>户口性质管理</title>
		<%@ include file="/inc/common.inc"%>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="新增--view/accountKind!entryAdd.action
				,删除--javascript:deleteChecks('view/accountKind!delete.action?theme=simple&paginate.offset=${paginate.offset }')" />
		</jsp:include>

		<s:form action="accountKind!list.action" method="post" id="myform">
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th>
						编号
					</th>
					<th width="120">
						户口名称
					</th>
					<th width="120">
						操作
					</th>
				</tr>
				<s:iterator value="accountKinds" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							<s:property value="accountKindCode" />
						</td>
						<td>
							<s:property value="accountKindName" />
						</td>
						<td>
							<a
								href="view/accountKind!entryUpdate.action?accountKind.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">修改</a>
							<a
								href="javascript:deleteRecord('view/accountKind!delete.action?checkIds=${id}&theme=simple&paginate.offset=${paginate.offset }')"
								class="opertion">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/accountKind!list.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
