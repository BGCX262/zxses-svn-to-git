<%@ page language="java" import="com.lcweb.base.util.JsTree,java.util.*"
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
		<title>分配用户权限</title>
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
		<script type="text/javascript"
			src="<%=basePath%>res/admin/js/dtree.js"></script>

		<script type="text/javascript">
    function sure(){
	 	var id = document.getElementById('personId').value;
		if ( id == '')
		 {
		 	alert("请选择用户！");
		 
		 }else{
		 	ajaxform.submit();}
		 }
     function checkAll(form)
     {
        for ( var i = 0; i < form.elements.length; i++) 
        {
		      var e = form.elements[i];
		      if (e.Name != 'cbox' && e.disabled == false)
			   e.checked = form.cbox.checked;
	    }
     }
 
	function showRequest(formData, jqForm, options) { 
	    var queryString = $.param(formData); 
	    var id ;
	        id = document.getElementById("personId").value;
	        if(id=="")
	        {
	          alert("没有选择用户不能保存！");
	        } 
	    return true; 
	} 
    </script>

	</head>

	<body>
		<form id="ajaxform" name="rightManageForm"
			action="<%=basePath%>view/rightManage.do?method=assignUserRight"
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
												<span class="chuti lv">分配用户权限</span>${showMsg }
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
								<td height="10">
								</td>
							</tr>
							<input type="hidden" name="personId"
								value="${rightManageForm.basicPerson.personId }">
							<tr>
								<td>
									<table width="100%" border="0">
										<tr>
											<td valign="top">
												<table class="percentage100">
													<tr>
														<td width="1%"></td>
														<td valign="top">
														<table width="140">
															<tr>
																<td>
																	&nbsp;<a href="javascript: d.openAll();">全部展开</a> |
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
													</tr>
												</table>
											</td>
											<td width="85%" valign="top">
												<table bgcolor="#32AEF4" width="80%">
													<tr>
														<td class="chutibai tdbk">
															用户名：${rightManageForm.basicPerson.personName }
														</td>
														<td class="chutibai tdbk">
															全选 &nbsp;&nbsp;
															<input type="checkbox" name="cbox" value="checkbox"
																onclick="checkAll(this.form)">
														</td>

													</tr>
													<tr class="cstd1">
														<td class="tdcenter tdbk">
															角色名称
														</td>
														<td class="tdcenter tdbk">
															操作
														</td>
													</tr>
													<logic:iterate id="r" name="rightManageForm"
														property="roleList" indexId="index">
														<c:if test="${(index+1)%2 == 0}">
															<tr class="cstd1" onMouseOver="this.className='cstd2'"
																onMouseOut="this.className='cstd1'">
																<td class="tdcenter tdbk">
																	&nbsp;${r.roleName}
																</td>
																<td class="tdcenter tdbk">
																	<input type="checkbox" name="checkbox"
																		${r.isChecked} value="${r.roleId }">
																</td>
															</tr>
														</c:if>
														<c:if test="${(index+1)%2 !=0}">
															<tr class="trcolor" onMouseOver="this.className='cstd2'"
																onMouseOut="this.className='trcolor'">
																<td class="tdcenter tdbk">
																	&nbsp;${r.roleName}
																</td>
																<td class="tdcenter tdbk">
																	<input type="checkbox" name="checkbox"
																		${r.isChecked} value="${r.roleId }">
																</td>
															</tr>
														</c:if>
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
			<div id="out" style="display: none"></div>
	</body>
</html>
