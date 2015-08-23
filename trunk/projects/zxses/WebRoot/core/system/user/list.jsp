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
		<title>用户管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript">

		function resetPasswordBatch(actionUrl){
			if ($("input[name=checkIds]:checked").length == 0) {
				alert("请选择需要操作的记录！");
				return;
			}
			if (confirm("是否确认重置？")) {
				$("form")[0].action = actionUrl;
				$("form")[0].submit();
			}
		}
		
		function resetPasswordRecord(actionUrl){
			if (confirm("是否确认重置？")) {
				$("form")[0].action = actionUrl;
				$("form")[0].submit();
			}
		}
		</script>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="新增--view/hrUser!entryAdd.action,删除--javascript:deleteChecks('view/hrUser!delete.action?theme=simple&paginate.offset=${paginate.offset }')
				,重置密码--javascript:resetPasswordBatch('view/hrUser!resetPassword.action?theme=simple&paginate.offset=${paginate.offset }')
				,导入--view/hrUser!importHrUserIndex.action,导出--view/hrUser!exportHrUserData.action" />
		</jsp:include>

		<s:form action="hrUser!list.action" method="post" id="myform">
			<div class="queryheader">
				用户类型：
				<s:select name="hrUserType.id" list="hrUserTypes" listKey="id"
					listValue="content" headerKey="-1" headerValue="--全部--"></s:select>
				&nbsp;&nbsp; 登录名：
				<s:textfield name="hrUserOp.account" size="10"></s:textfield>
				&nbsp;&nbsp;用户名：
				<s:textfield name="hrUserOp.name" size="10"></s:textfield>
				&nbsp;
				<s:submit cssClass="an" value="查询"></s:submit>
			</div>
			<hr />
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
					<th width="120">
						状态
					</th>
					<th width="120">
						操作
					</th>
				</tr>
				<s:iterator value="hrUsers" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:if test="#attr.loginer.id!=id">
								<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
							</s:if>
						</td>
						<td>
							<s:property value="account" />
						</td>
						<td>
							<s:property value="name" />
						</td>
						<td>
							<s:if test="isEnable==0">
								<span style="color: #eaa000"> 禁用 </span>
							</s:if>
							<s:else>
										启用
									</s:else>
						</td>
						<td width="180">
							<a
								href="view/hrUser!entryUpdate.action?hrUser.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">修改</a>
							<s:if test="#attr.loginer.id!=id">
								<a
									href="javascript:deleteRecord('view/hrUser!delete.action?checkIds=${id}&theme=simple&paginate.offset=${paginate.offset }')"
									class="opertion">删除</a>
							</s:if>
							<a
								href="javascript:resetPasswordRecord('view/hrUser!resetPassword.action?checkIds=${id}&theme=simple&paginate.offset=${paginate.offset }')"
								class="opertion">密码重置</a>
							<a
								href="view/hrUser!listUserRole.action?hrUser.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">分配角色</a>

						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/hrUser!list.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
