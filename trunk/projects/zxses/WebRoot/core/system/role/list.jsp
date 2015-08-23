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
		<title>角色管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>

	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="新增--view/sysRole!entryAdd.action,删除--javascript:deleteChecks('view/sysRole!deleteSysRole.action?theme=simple&paginate.offset=${paginate.offset }')" />
		</jsp:include>

		<s:form action="hrUser!list.action" method="post" id="myform">
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th width="120">
						编码
					</th>
					<th>
						名称
					</th>
					<th width="120">
						操作
					</th>
				</tr>
				<s:iterator value="sysRoles" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							<s:property value="code" />
						</td>
						<td>
							<s:property value="name" />
						</td>
						<td width="180">
							<a
								href="view/sysRole!entryUpdate.action?sysRole.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">修改</a>
							<a
								href="javascript:deleteRecord('view/sysRole!deleteSysRole.action?checkIds=${id}&theme=simple&paginate.offset=${paginate.offset}')"
								class="opertion">删除</a>
							<a
								href="view/sysRole!main.action?roleId=${id}&theme=simple&paginate.offset=${paginate.offset}"
								class="opertion">角色授权</a>
							<a
								href="view/sysRole!roleUsers.action?roleId=${id}&theme=simple&paginate.offset=${paginate.offset}"
								class="opertion">角色人员</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/sysRole!listSysRole.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
