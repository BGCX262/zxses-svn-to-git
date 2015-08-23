<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>角色权限列表</title>
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
		<script type="text/javascript">
        function sure(){
	 	var id = document.getElementById('roleId').value;
		if ( id == '')
		 {
		 	
		 	alert("请选择您要操作的角色！");
		 
		 }else{
		 	myfrom.action="<%=basePath%>view/rightManage.do?method=addRoleRight";
	        myfrom.submit();}
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
				showloading("<%=basePath%>commons/ajaxload.jsp",90,90);
			     
			    return true; 
			} 
			function showResponse(responseText, statusText)  {
				window.hidewindow(); 
			 	alert("授权成功"); 
			}
		    function ckboxSelect(root,parentId,obj)
			{
	        	var inputs = document.getElementsByTagName("input"); 
	        	var index = 0;
				var ckbGroups = new Array();
				var lastIndex = parentId.lastIndexOf("_");
				var ckbs = parentId.substring(0,lastIndex);
				
				for(var i = 0; i < inputs.length; i++)
				{
					if(inputs[i].type=="checkbox")
					{
						var ckbId = inputs[i].id;
						var selectCkbId = obj.id;
						var selectCkbIndex = selectCkbId.lastIndexOf("_");
						
					    if(ckbId.indexOf(selectCkbId.substring(0,selectCkbIndex)) == 0){
						   inputs[i].checked = obj.checked;
							if(obj.checked){
						   		document.getElementById(parentId).checked = obj.checked;
						   		if(root!=""){
						   			document.getElementById(root).checked = obj.checked;
						   		}
						   }
						}
						
						if(!obj.checked){
							if(ckbId.indexOf(ckbs)>=0 && ckbId!=parentId){
								ckbGroups[index] = ckbId;
								index++;
							}
						}
				 	}	
				}
				var hasChecked = false;
				for(var j = 0; j < ckbGroups.length; j++)
				{
					var checkBox = document.getElementById(ckbGroups[j]);
					if(!checkBox.checked){
						hasChecked = false;
					}else{
						hasChecked = true;
						break;
					}
				}
				if(!obj.checked && !hasChecked){
			   		document.getElementById(parentId).checked = obj.checked;
			   		if(root!=""){//sysmodule3
			   			ckboxSelect('',root,document.getElementById(parentId));
			   		}else if( root!=""){//sysmodule4
			   			ckboxSelect(root,'',secondId,obj);
			   		}
			   }
		    } 
        </script>
	</head>
	<body>
		<div id="out" style="display: none"></div>
		<form id="ajaxform"
			action="<%=basePath%>view/rightManage.do?method=addRoleRight"
			method="post" name="myfrom">
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
											<td height="24">
												系统管理-&gt;
												<span class="chuti lv">角色权限</span>${showMsg }
											</td>
											<td align="right">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:sure();" value="确定" />
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
														<td width="20%" valign="baseline">
															<table bgcolor="#32AEF4" cellSpacing="1" cellPadding="0"
																width="100%" border="0">
																<tr>
																	<td class="chutibaileft">
																		&nbsp;&nbsp;角色名称：
																	</td>
																</tr>
																<logic:iterate id="rl" name="rightManageForm"
																	property="roleList" indexId="roleIndex">
																	<c:if test="${(roleIndex+1)%2 == 0}">
																		<tr class="cstd1" onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='cstd1'">
																			<td class="td_right">
																				<img src="<%=basePath%>res/admin/img/tou.jpg" />
																				<a
																					href="<%=basePath%>view/rightManage.do?method=queryRoleRight&roleId=${rl.roleId}">
																					${rl.roleName}</a>
																			</td>
																		</tr>
																	</c:if>
																	<c:if test="${(roleIndex+1)%2 !=0}">
																		<tr class="trcolor"
																			onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='trcolor'">
																			<td class="td_right">
																				<img src="<%=basePath%>res/admin/img/tou.jpg" />
																				<a
																					href="<%=basePath%>view/rightManage.do?method=queryRoleRight&roleId=${rl.roleId}">
																					${rl.roleName}</a>
																			</td>
																		</tr>
																	</c:if>

																</logic:iterate>
															</table>
														</td>
														<td width="1%"></td>
														<td width="78%" valign="top">

															<input type="hidden" name="roleId"
																value="${rightManageForm.sysRole.roleId }" />

															<input type="hidden" name="yxdm" />

															<table bgcolor="#32AEF4" cellSpacing="1" cellPadding="0"
																width="99%" border="0" height="">
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
																		&nbsp;&nbsp;
																	</td>
																</tr>
																<logic:iterate id="parent" name="rightManageForm"
																	property="moduleList">
																	<logic:iterate id="child" name="parent"
																		property="childModules" indexId="sq">
																		<tr class="trcolor"
																			onMouseOver="this.className='trcolor'"
																			onMouseOut="this.className='cstd2'">
																			<td class="td_right" colspan="2">
																				<font color="#ff6600">
																					&nbsp;&nbsp;${child.moduleName } 
																					<input type="checkbox" name="moduleId" 
																					id="${child.moduleId}_${sq}" ${child.isCheck } 
																					value="${child.moduleId}" 
																					onclick="check('${child.moduleId}',this)"/>
																				</font>
																			</td>
																		</tr>
																		<logic:iterate id="t" name="child"
																			property="childModules" indexId="sq1">
																			<c:if test="${(sq+1)%2 == 0}">
																				<tr class="cstd1"
																					onMouseOver="this.className='cstd2'"
																					onMouseOut="this.className='cstd1'">
																					<td align="left" height="30">
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${t.moduleName }
																						<input id="${child.moduleId}_${t.moduleId}_${sq1}" type="checkbox" name="checkbox"
																							${t.isCheck } value="${t.moduleId }" 
																							onclick="ckboxSelect('',
																								'${child.moduleId}_${sq}',
																								this)"/>
																					</td>
																					<td align="right" height="30">
																						<logic:iterate id="fun" name="t" property="childModules" indexId="sq2">
																			              ${fun.moduleName }
																			              <input id="${child.moduleId}_${t.moduleId}_${fun.moduleId}_${sq2}" type="checkbox" name="checkbox"
																								${fun.isCheck } value="${fun.moduleId }"
																								onclick="ckboxSelect('${child.moduleId}_${sq}',
																								'${child.moduleId}_${t.moduleId}_${sq1}'
																								,this)"/>
																						</logic:iterate>
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																					</td>
																				</tr>
																			</c:if>
																			<c:if test="${(sq+1)%2 !=0}">
																				<tr class="trcolor"
																					onMouseOver="this.className='cstd2'"
																					onMouseOut="this.className='trcolor'">
																					<td align="left" height="30">
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${t.moduleName }
																						<input id="${child.moduleId}_${t.moduleId}_${sq1}" type="checkbox" name="checkbox"
																							${t.isCheck } value="${t.moduleId }" 
																							onclick="ckboxSelect('',
																								'${child.moduleId}_${sq}',
																								this)"/>
																					</td>
																					<td align="right" height="30">
																						<logic:iterate id="fun" name="t" property="childModules" indexId="sq2">
																			              ${fun.moduleName }
																			              <input id="${child.moduleId}_${t.moduleId}_${fun.moduleId}_${sq2}" type="checkbox" name="checkbox"
																								${fun.isCheck } value="${fun.moduleId }"
																								onclick="ckboxSelect('${child.moduleId}_${sq}',
																								'${child.moduleId}_${t.moduleId}_${sq1}'
																								,this)"/>
																						</logic:iterate>
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																					</td>
																				</tr>
																			</c:if>
																		</logic:iterate>
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
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
