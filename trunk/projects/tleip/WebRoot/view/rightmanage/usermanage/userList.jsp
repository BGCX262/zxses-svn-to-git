<%@ page language="java" import="com.lcweb.base.util.JsTree,java.util.*"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户管理</title>
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
    
    var editvalue;
        function checkNum(){
            var chkbs = document.getElementsByName("check");   
            var chkNum = 0;   
            for(i=0;i<chkbs.length;i++)
            {
              if(chkbs(i).checked){
                chkNum++;
                editvalue=chkbs[i].value;
                }
            }
            if(chkNum<1)
            {
              alert("请选择一条记录!");
              return false;
            }
            else if(chkNum>1)
            {
               alert("您一次只能选择一条记录！");
            }
            else{
               return true;
            }          
        }
        
        
        function checkDelNum()
        {
            var chkbs = document.getElementsByName("check");   
            var chkNum = 0;   
            for(i=0;i<chkbs.length;i++)
            {
              if(chkbs(i).checked)
                chkNum++;
            }
            if(chkNum<1)
            {
              alert("请选择一条记录!");
              return false;
            }
            else{
               return true;
            }  
        }
        function add()
	    {     
	     	var deptId = document.getElementsByName('basicDepartment.deptId')[0].value; 
	     	rightManageForm.action="<%=basePath%>view/rightManage.do?method=enterAddUser&deptId="+deptId;
	        rightManageForm.submit();
	    }
        function update(){ 
       
          if(checkNum())
          {  
          	 var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
          	 rightManageForm.action="<%=basePath%>view/rightManage.do?method=updateUser&personId="+editvalue+"&deptId="+deptId;             
             rightManageForm.submit();      
          }
          else
          {
             return false;
          }
        }
        
        function del(){
        var deptId = document.getElementsByName('basicDepartment.deptId')[0].value; 
          if(checkDelNum()){
             if(window.confirm("确定删除这些数据吗？")){
                 rightManageForm.action="<%=basePath%>view/rightManage.do?method=deleteUser&deptId="+deptId;
	             rightManageForm.submit();
             }else{
             	return false;
             }
          }
          else{
             return false;
          }
        }   
        
        function browse(personId)
	        {   var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
				 rightManageForm.action="<%=basePath%>view/rightManage.do?method=updateUser&personId="+personId+"&deptId="+deptId;             
             	 rightManageForm.submit();
	        }
        
        function updatePassword(){
            var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
            if(checkNum()){ 
                rightManageForm.action="<%=basePath%>view/rightManage.do?method=updatePassword&personId="+editvalue+"&deptId="+deptId;             
                rightManageForm.submit();
            }          
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
											<span class="chuti lv">用户管理</span>${showMsg }
										</td>
										<td id="td1" align="right" style="display: ${rightManageForm.style}">
											<img src="<%=basePath%>res/admin/img/add.gif"
												onclick="javascript:add()" style="cursor: pointer" alt="新增"
												title="新增" />
											&nbsp;
											<img src="<%=basePath%>res/admin/img/update.gif"
												onclick="javascript:update()" style="cursor: pointer"
												alt="修改" title="修改" />
											&nbsp;
											<img src="<%=basePath%>res/admin/img/delete.gif"
												onclick="javascript:del()" style="cursor: pointer" alt="删除"
												title="删除" />
											&nbsp;
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
													<td width="1%"></td>
													<td width="88%" valign="top">
														<html:form action="view/rightManage.do?method=queryPerson"
															method="post">
															<table cellSpacing="0" cellPadding="0" width="90%"
																border="0" style="display: ${rightManageForm.style}">
																<tr>
																	<td>
																		当前部门:
																		<html:hidden property="basicDepartment.deptId" />
																		<font color="#ff6600" id="dpname">${rightManageForm.basicDepartment.deptName}</font>
																	</td>
																</tr>
															</table>
															<table bgcolor="#32AEF4" cellSpacing="0" cellPadding="0"
																width="90%" border="0" style="display: ${rightManageForm.style}">
																<tr>
																	<td class="chutibai tdwidth45 tdbk">
																		<input type="checkbox"
																			onclick="checkAll(this,'check');" id="checkall"
																			alt="全选">
																	</td>
																	<td class="chutibai tdbk">
																		人员编码
																	</td>
																	<td class="chutibai tdbk">
																		人员名称
																	</td>
																	<td class="chutibai tdbk">
																		登录账号
																	</td>
																</tr>
																<logic:iterate id="r" name="personList"
																	indexId="personIndex">
																	<c:if test="${(personIndex+1)%2 == 0}">
																		<tr class="cstd1" onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='cstd1'">
																			<td class="tdcenter tdbk">
																				<input type="checkbox" name="check"
																					value="${r.personId}">
																			</td>
																			<td class="tdcenter tdbk">
																				<a href='#' onclick="browse('${r.personId }'); return false;">${r.personCode}</a>
																			</td>
																			<td class="tdcenter tdbk">
																				${r.personName}
																			</td>
																			<td class="tdcenter tdbk">
																				${r.personAccount}
																			</td>
																		</tr>
																	</c:if>
																	<c:if test="${(personIndex+1)%2 !=0}">
																		<tr class="trcolor"
																			onMouseOver="this.className='cstd2'"
																			onMouseOut="this.className='trcolor'">
																			<td class="tdcenter tdbk">
																				<input type="checkbox" name="check"
																					value="${r.personId}">
																			</td>
																			<td class="tdcenter tdbk">
																				<a href='#' onclick="browse('${r.personId }'); return false;">${r.personCode}</a>
																			</td>
																			<td class="tdcenter tdbk">
																				${r.personName}
																			</td>
																			<td class="tdcenter tdbk">
																				${r.personAccount}
																			</td>
																		</tr>
																	</c:if>

																</logic:iterate>
															</table>
														</html:form>
													</td>
												</tr>
												<tr>
													<td align="right">
														${pageList.view }
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
