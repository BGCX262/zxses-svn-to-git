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
		<title>角色用户管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="新增人员--view/sysRole!listUsers.action?roleId=${roleId}&theme=simple&paginate.offset=${paginate.offset},删除--javascript:deleteChecks('view/sysRole!deleteRoleUsers.action?roleId=${roleId}&theme=simple&paginate.offset=${paginate.offset }'),返回--view/sysRole!listSysRole.action?theme=simple&paginate.offset=${paginate.offset }"/>
		</jsp:include>

		<s:form action="sysRole!list.action" method="post" id="form">
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th width="120">
						登录名
					</th>
					<th>
						用户名
					</th>
					
				</tr>
				<s:iterator value="hrUserRoles" status="i" >
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
							onMouseOut="this.className='tr${i.index%2+1}'">
							<td>
								<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
							</td>
							<td>
								<s:property value="hrUser.account" />
							</td>
							<td>
								<s:property value="hrUser.name" />
							</td>
							
						</tr>
				</s:iterator>
			</table>
		</s:form>
	</body>
</html>
