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
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script type="text/javascript">
	        /*function sure(){
		 	var id = document.getElementById('roleId').value;
			if ( id == '')
			 {
			 	
			 	alert("请选择您要操作的角色！");
			 
			 }else{
			 	myfrom.action="<%=basePath%>view/rightManage.do?method=addRoleRight";
		        myfrom.submit();}
			 }*/
			function sure(){
				sysRoleForm.action="<%=basePath%>view/sysrole.do?method=assignPrivilege";
		        sysRoleForm.submit();
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
			
			function CheckAll(obj,childStyle) {
				//var childDiv = document.getElementById(childId);  
				var subChildChecks = $("."+childStyle);
				//alert(subChildChecks.length);
				//var subChildChecks = childDiv.getElementsByTagName("input");  
				if(obj.checked){  
					for(var i = 0 ; i < subChildChecks.length; i++){  
						if(subChildChecks[i].type == 'checkbox'){
							subChildChecks[i].checked = true;  
						}
					}  
				}else{  
					for(var i = 0 ; i < subChildChecks.length; i++){  
						if(subChildChecks[i].type == 'checkbox'){
							subChildChecks[i].checked = false;  
						}
					}  
				}  
			}
			
			function CheckCenter(obj,parentId,childStyle) {				
				//var childDiv = document.getElementById(childId);  
				var subChildChecks = $("."+childStyle);//childDiv.getElementsByTagName("input"); 
				
				if(obj.checked){  
					for(var i = 0 ; i < subChildChecks.length; i++){  
						if(subChildChecks[i].type == 'checkbox'){
							subChildChecks[i].checked = true;  
						}
					}  
				}else{  
					for(var i = 0 ; i < subChildChecks.length; i++){  
						if(subChildChecks[i].type == 'checkbox'){
							subChildChecks[i].checked = false;  
						}
					}  
				}
			
				
				var parent = document.getElementById(parentId);
				//var parentDiv = document.getElementById("div" + parentId);
				var subParentChecks = $(".divFirst"+parentId);//parentDiv.getElementsByTagName("input");
				
				//根据逻辑判断父checkbox是否选中，如果子checkbox都为未选，则为未选，否则不改变
				var subParentNum = 0;
				if(obj.checked){  
					parent.checked = true;
				}else{  
					for(var i = 0 ; i < subParentChecks.length; i++){  
						if(subParentChecks[i].type == 'checkbox' && subParentChecks[i].checked == true){
							subParentNum ++;  
						}
					} 
					if(subParentNum == 0){
						parent.checked = false;
					}
				}				
			}
			
			function CheckOnlyLast(obj,grandPaId,parentId) {
				var parent = document.getElementById(parentId);
				//var parentDiv = document.getElementById("divSecond" + parentId);
				var subParentChecks = $(".divSecond"+parentId);//parentDiv.getElementsByTagName("input");
			
				var grandPa = document.getElementById(grandPaId);
				//var grandPaDiv = document.getElementById("div" + grandPaId);
				var subGrandPaChecks = $(".divFirst"+grandPaId);//grandPaDiv.getElementsByTagName("input");
			
				
				
				//根据逻辑判断父checkbox是否选中，如果子checkbox都为未选，则为未选，否则不改变
				var subParentNum = 0;
				var subGrandPaNum = 0;
				if(obj.checked){  
					parent.checked = true;
					grandPa.checked = true;
				}else{  
					for(var i = 0 ; i < subParentChecks.length; i++){  
						if(subParentChecks[i].type == 'checkbox' && subParentChecks[i].checked == true){
							subParentNum ++;  
						}
					} 
					if(subParentNum == 0){
						parent.checked = false;
						//alert(parent.checked);
						//alert(subGrandPaChecks.length);
						//父checkbox如果为祖父子checkbox最后一个为选中checkbox，则需根据逻辑判断祖父checkbox是否选中
						
						for(var k = 0; k < subGrandPaChecks.length; k++){
							if(subGrandPaChecks[k].type == 'checkbox' && subGrandPaChecks[k].checked == true){
								//alert("值wei:" + subGrandPaNum);
								subGrandPaNum ++;
							}
						}
						if(subGrandPaNum == 0){
							grandPa.checked = false;
						}
					}
				}
			}
			
			//初始化checkbox状态
			$(function(){
				initCheckBox();
			});
			
			function initCheckBox(){
				var selectCheckBoxs = $("input[type=checkbox][name=sysPrivileges]:checked");
				for(var i = 0 ; i < selectCheckBoxs.length; i++){  
					var selectCheckBox = selectCheckBoxs[i];
					var parentId = selectCheckBox.id.substring(0, selectCheckBox.id.lastIndexOf("_"));
					var GrandPaId = selectCheckBox.id.substring(0, selectCheckBox.id.indexOf("_"));
					document.getElementById(parentId).checked = true;
					document.getElementById(GrandPaId).checked = true;
				} 
			}
			
			function back()
	        {
			   window.location.href="<%=basePath%>view/sysrole.do";
			}	
        </script>
	</head>
	<body>
		<html:form action="view/sysrole.do" method="post">
			<input type="hidden" name="method" value="assignPrivilege"/>
			<input type="hidden" name="id" value="${sysRoleInfo.id }" />
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
												系统管理-&gt;角色管理-&gt;
												<span class="chuti lv">角色权限分配</span>${showMsg }
											</td>
											<td align="right">
												<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径-->
												<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysRoleManage','assignPrivilege') }">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:sure();" value="确定" />
												&nbsp;
												</c:if>
												<input name="button2" type="button" class="an" id="button2"
													value="返回" onclick="javascript:back();" />
												&nbsp;
											</td>
											<td width="15"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="height8"></td>
							</tr>
							<tr>
								<td class="x"></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0">
										<tr>
											<td>
												<table width="97%" height="100%" border="0" align="center">
													<tr>
														<td valign="top">
															<table width="100%" height="" cellspacing="1" cellpadding="0" border="0" bgcolor="#32aef4">
																<tbody>
																	<tr>
																		<td class="chutibaileft">
																			&nbsp;&nbsp;当前角色:
																			<font color="#ff6600">${sysRoleInfo.roleName }</font>
																		</td>
																		<td align="right">
																			全选
																			<input type="checkbox" onclick="check('',this)">
																			&nbsp;&nbsp;
																		</td>
																	</tr>
																	<!-- 系统模块列表 -->
																	<!-- 系统模块一级列表 begin-->
																	<c:forEach items="${sysModules}" var="sysFirstModule" varStatus="statusFirst">
																		<tr onmouseout="this.className='cstd2'" onmouseover="this.className='trcolor'" class="cstd2">
																			<td colspan="2" class="td_right">
																				<font color="#ff6600">
																					&nbsp;&nbsp;${sysFirstModule.name }
																					<input id="${statusFirst.index}" class="divFirst" onclick="CheckAll(this,'divFirst${statusFirst.index}');" type="checkbox">
																				</font>
																			</td>
																		</tr>
																	
																		<!-- 系统模块二级列表 begin-->
																		<c:forEach items="${sysFirstModule.children}" var="sysSecondModule" varStatus="statusSecond">
																			<tr onmouseout="this.className='trcolor'" onmouseover="this.className='cstd2'" class="trcolor">
																				<td height="30" align="left">
																					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${sysSecondModule.name }
																					<input id="${statusFirst.index}_${statusSecond.index}" class="divSecond divFirst${statusFirst.index}" onclick="CheckCenter(this,'${statusFirst.index}','divSecond${statusFirst.index}_${statusSecond.index}')"  type="checkbox">
																				</td>
																				<td height="30" align="right">
																					<!-- 具体的权限值 -->
																					<c:forEach items="${sysPrivileges}" var="sysPrivilege" varStatus="statusThird">
																						<c:if test="${sysPrivilege.id.model == sysSecondModule.sn}">
																							${sysPrivilege.name}
																			            	<input id="${statusFirst.index}_${statusSecond.index}_${statusThird.index}" class="divThird divFirst${statusFirst.index} divSecond${statusFirst.index}_${statusSecond.index}" onclick="CheckOnlyLast(this,'${statusFirst.index}','${statusFirst.index}_${statusSecond.index}')"  type="checkbox" value="${sysPrivilege.id.model},${sysPrivilege.id.privilegeValue}" name="sysPrivileges" 
																			            	<c:forEach items="${sysRolePrivileges}" var="sp"><c:if test="${sp==sysPrivilege}"> checked="checked"</c:if></c:forEach>/>
																						</c:if>
																					</c:forEach>
																					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																				</td>
																			</tr>
																		</c:forEach>
																		<!-- 系统模块二级列表 end-->
																	</c:forEach>
																</tbody>
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
		</html:form>
	</body>
</html>
