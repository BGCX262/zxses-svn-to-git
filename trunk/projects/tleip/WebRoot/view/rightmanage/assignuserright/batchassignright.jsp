<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>批量分配用户权限</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css">
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=basePath%>res/admin/js/dtree.js"></script>
		<script type="text/javascript">
    
    function sure(){
	 	var id = document.getElementById('sureid').value;
		if ( id == '')
		 {
		 	alert("请选择您要操作的角色！");
		 
		 }else{
		 	ajaxform.submit();}
		 }
    
    
        function check(str,obj)
        {
	       
		   var inputs = document.getElementsByTagName("input"); 
		   for(var i = 0; i < inputs.length; i++)
		   {
		      if(inputs[i].type=="checkbox")
		      {
			    if(inputs[i].id.indexOf(str) == 0)
				   inputs[i].checked = obj.checked;
			  }	
		   }
	    }
		function showRequest(formData, jqForm, options) { 
		    var queryString = $.param(formData); 
		     
		    return true; 
		} 
		 
		function showResponse(responseText, statusText)  { 
		 alert("用户授权成功");  
		} 
    </script>
	</head>

	<body>
		<div id="out" style="display: none"></div>
		<form id="ajaxform"
			action="<%=basePath%>view/rightManage.do?method=addBatchAssignUserRight"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="center">
							<tr>
								<td>
									<table class="pathbg">
										<tr>
											<td height="24" class="dh1">
												系统管理-&gt;
												<span class="chuti lv">批量分配用户权限</span>${showMsg }
											</td>
											<td align="right">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:sure();" value="确定" />
												&nbsp;
											</td>
											<td width="15"></td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td height="10"></td>
							</tr>

							<tr>
								<td>
									<table width="100%" border="0">
										<tr>
											<td>
												<table width="100%" height="100%" border="0">

													<tr>
														<td width="1%"></td>
														<td width="20%" valign="top">
															<table bgcolor="#32AEF4" cellSpacing="1" cellPadding="0"
																width="100%" border="0">
																<tr><td class="chutibaileft">&nbsp;&nbsp;角色名称：</td></tr>
																<logic:iterate id="rl" name="rightManageForm"
																	property="roleList">
																	<tr class="trcolor"
																		onMouseOver="this.className='cstd2'"
																		onMouseOut="this.className='trcolor'">
																		<td  class="td_right">
																			<img src="<%=basePath%>res/admin/img/tou.jpg">
																			<a
																				href="<%=basePath%>view/rightManage.do?method=batchAssignUserRight&roleId=${rl.roleId}">
																				${rl.roleName}</a>
																		</td>
																	</tr>
																</logic:iterate>

															</table>
														</td>
														<td width="1%"></td>
														<td width="78%" valign="top">

															<input type="hidden" name="roleId" id="sureid"
																value="${rightManageForm.sysRole.roleId }">
															<table bgcolor="#32AEF4" cellSpacing="1" cellPadding="0"
																width="98%" border="0">
																<tr>
																	<td class="chutibaileft">
																		&nbsp;&nbsp;当前角色:
																		<font color="#ff6600">${rightManageForm.sysRole.roleName
																			}</font>
																	</td>
																	<td align="right">
																		全选
																		<input type="checkbox"
																			onclick="check('${parent.moduleId}',this)">
																	</td>
																</tr>
																<logic:iterate id="d" name="rightManageForm"
																	property="deptList" indexId="sq">
																			<tr class="cstd1" onMouseOver="this.className='cstd2'"
																				onMouseOut="this.className='cstd1'">
																		<td align="left" height="30" colspan="2">
																			&nbsp;&nbsp;部门名称：<font color="#ff6600">${d.deptName }
																			<input id="${d.deptId}_${sq}" type="checkbox"
																				name="deptbox" value="${d.deptId }"
																				onclick="check('${d.deptId}',this)"></font>
																		</td>
																	</tr>
																	<logic:iterate id="b" name="d" property="basicPersons">
																		<tr class="trcolor" onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='trcolor'">
																			<td align="left" height="30">
																				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人员名：${b.personName}
																			</td>
																			<td align="right" height="30">
																				<input id="${d.deptId}_${sq}" type="checkbox"
																					name="personbox"
																					${b.isChecked} value="${b.personId }">
																			</td>
																		</tr>
																	</logic:iterate>
																</logic:iterate>
															</table>
														</td>
													</tr>
												</table>
										</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
	</body>
</html>
