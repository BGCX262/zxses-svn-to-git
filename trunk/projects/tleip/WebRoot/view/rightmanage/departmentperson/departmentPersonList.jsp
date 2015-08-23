<%@ page language="java" import="java.util.*,com.lcweb.base.util.JsTree"
	pageEncoding="UTF-8"%>
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
		<title>部门人员管理</title>
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
		<link rel="StyleSheet" href="<%=basePath%>style/dtree.css"
			type="text/css" />
		<script type="text/javascript"
			src="<%=basePath%>res/admin/js/dtree.js"></script>
		<script type="text/javascript">		   
		   function addPerson()
		   {
		       var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
		       if(deptId==""){
		       	alert("请选择部门！");
		       	return false;
		       }
		       rightManageForm.action="<%=basePath%>view/rightManage.do?method=queryPersonList&deptId="+deptId;
		       rightManageForm.submit();
		   }
		   function move(id)
		   { 
		       $.post("<%=basePath%>view/rightManage.do?method=deletePerFromDep",{personId:id},function(response)
		       {
                  if(response=="ok")
                  {
                      window.location.reload();
                  }
               });
		   }
		</script>
	</head>

	<body>
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
											<span class="chuti lv">部门人员管理</span>${showMsg }
										</td>
										<td style="display: ${rightManageForm.style}" align="right">
											<img src="<%=basePath%>res/admin/img/addperson.gif"
												onclick="addPerson()" style="cursor: pointer" border="0"
												alt="添加人员" />
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
											<table class="percentage100">

												<tr>
													<td width="1%"></td>
													<td valign="top">
														<table width="140">
															<tr>
																<td>
																	<a href="javascript: d.openAll();">全部展开</a> |
																	<a href="javascript: d.closeAll();">全部折叠</a>
																	<script type="text/javascript">														 
																d = new dTree('d');
														       <% 
														         List treeList = (List)request.getAttribute("treeList");
														          for(int i=0;i<treeList.size();i++){
														          JsTree  tree = (JsTree)treeList.get(i); 											          
														       %>
														       d.add(<%=tree.getId()%>,<%=tree.getParentId()%>,'<%=tree.getName()%>','<%=tree.getUrl()%>','','','<%=tree.getImage()%>');    
														       <%}%>
																document.write(d);													  
															</script>
																</td>
															</tr>
														</table>
													</td>

													<td width="79%" valign="top">
														<html:form action="view/rightManage" method="post">
															<table cellSpacing="0" width="99%" cellPadding="0"
																border="0" style="display: ${rightManageForm.style}">
																<tr>
																	<td>
																		当前部门:
																		<font color="#ff6600"><html:hidden
																				property="basicDepartment.deptId" />${rightManageForm.basicDepartment.deptName}</font>
																	</td>
																</tr>

															</table>
															<table bgcolor="#32AEF4" cellSpacing="0" cellPadding="0"
																width="100%" border="0"
																style="display: ${rightManageForm.style}">
																<tr>
																	<td class="chutibai tdbk">
																		人员名称
																	</td>
																	<td class="chutibai tdbk">
																		所属部门
																	</td>
																	<td class="chutibai tdbk">
																		操作
																	</td>
																</tr>
																<logic:iterate id="r" name="personList" indexId="index">
																	<c:if test="${(index+1)%2 == 0}">
																		<tr class="cstd1" onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='cstd1'">
																			<td class="tdcenter tdbk">
																				${r.personName}
																			</td>
																			<td class="tdcenter tdbk">
																				${r.basicDepartment.deptName}
																			</td>
																			<td class="tdcenter tdbk">
																				<a href="javascript:move('${r.personId}')"> <font
																					id="move" color="blue">移除人员</font> </a>
																			</td>
																		</tr>	
																	</c:if>
																	<c:if test="${(index+1)%2 !=0}">
																		<tr class="trcolor"
																			onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='trcolor'">
																			<td class="tdcenter tdbk">
																				${r.personName}
																			</td>
																			<td class="tdcenter tdbk">
																				${r.basicDepartment.deptName}
																			</td>
																			<td class="tdcenter tdbk">
																				<a href="javascript:move('${r.personId}')"> <font
																					id="move" color="blue">移除人员</font> </a>
																			</td>
																		</tr>
																	</c:if>

																</logic:iterate>
															</table>
														</html:form>
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
	</body>
</html>
