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
		<title>部门管理</title>
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
     var departid;    
     var tempLever = "0";
     function add()
     {  
        var deptName=document.getElementsByName("basicDepartment.deptName")[0].value;
        var deptCode=document.getElementsByName("basicDepartment.deptCode")[0].value;  
        var lever = tempLever;
        if(deptCode=="" || deptName=="")
        {
            alert("带*号的不能为空");
        }    
        else
        {  
            if(lever=="0")
            {
                alert("您未进行任何更改操作，不需要保存！"); 
                rightManageForm.action="<%=basePath%>view/rightManage.do?method=queryDepartment";
				rightManageForm.submit();               
            }
            if(lever=="3")
            {
                rightManageForm.action="<%=basePath%>view/rightManage.do?method=addDepartmentDo&deptId="+departid+"&leverNo="+lever;
				rightManageForm.submit();
            }
            else
            {
                $.post("<%=basePath%>view/rightManage.do?method=checkCode",{code:"deptCode",dm:deptCode,object:"BasicDepartment"},function(response)
                {	            
		            if(response=="exist")
		            {
		                alert("部门编码已存在，请重新输入！");		                
		            }		            
		            else{ 
		                $.post("<%=basePath%>view/rightManage.do?method=checkCode",{code:"deptName",dm:deptName,object:"BasicDepartment"},function(response)
                        {
                            if(response=="exist"){
		                        alert("部门名称已存在，请重新输入！");
		                    }else{
		                        //执行新增操作
				                rightManageForm.action="<%=basePath%>view/rightManage.do?method=addDepartmentDo&deptId="+departid+"&leverNo="+lever;
				                rightManageForm.submit();
		                    }
                        });                       
		            }
	            });                
            }
        }         
     }
     
     function addnew(){
	    
      var deptName=document.getElementsByName("basicDepartment.deptName")[0].value;
      var deptCode=document.getElementsByName("basicDepartment.deptCode")[0].value;  
     if(deptCode=="" || deptName=="")
        {
            alert("带*号的不能为空");
        }    
        else
            {
                $.post("<%=basePath%>view/rightManage.do?method=checkCode",{code:"deptCode",dm:deptCode,object:"BasicDepartment"},function(response)
                {	            
		            if(response=="exist")
		            {
		                alert("部门编码已存在，请重新输入！");		                
		            }		            
		            else{ 
		                $.post("<%=basePath%>view/rightManage.do?method=checkCode",{code:"deptName",dm:deptName,object:"BasicDepartment"},function(response)
                        {
                            if(response=="exist"){
		                        alert("部门名称已存在，请重新输入！");
		                    }else{
		                        //执行新增操作
				                rightManageForm.action="<%=basePath%>view/rightManage.do?method=addNewDepartment";
				                rightManageForm.submit();
		                    }
                        });                       
		            }
	            });                
            }
        } 
        
     function operation(depid,depname,depcode,depremark){
        departid = depid;
        tempLever = "3";
        document.getElementById("td1").style.display="block";
        document.getElementById("departname").innerHTML="<font color=\"#ff6600\">"+depname+"<font>";
        document.getElementsByName("basicDepartment.deptName")[0].value=depname;
        document.getElementsByName("basicDepartment.deptCode")[0].value=depcode;
        document.getElementsByName("basicDepartment.remark")[0].value=depremark;        
     }     
     
     function addDepart(lever)
     {
        var deptName = document.getElementById("departname").innerHTML;
        if(deptName != "")
        {
	        document.getElementById("td1").style.display="block";
	       
	        tempLever=lever;       
	        if(lever=="1"){
	           document.getElementById("addstyle").style.display="";
	           document.getElementById("s").style.color="#ff6600";    
	           document.getElementById("n").style.color="blue"; 
	        }else{
	           document.getElementById("addstyle").style.display="none";
	           document.getElementById("s").style.color="blue";    
	           document.getElementById("n").style.color="#ff6600"; 
	        }        
	        document.getElementsByName("basicDepartment.deptName")[0].value="";
	        document.getElementsByName("basicDepartment.deptCode")[0].value="";
	        document.getElementsByName("basicDepartment.remark")[0].value="";
        }
        else
        {
            alert("请选择您要操作的部门!");
        }
     }
      
     function del(){
      var deptCode=document.getElementsByName("basicDepartment.deptCode")[0].value;
        if(deptCode != "")
        {
	     if(window.confirm("确定删除所选择的部门？"))
		 {
		      $.post("rightManage.do?method=deleteCheckDepPerson",{deptId:departid},function(response)
		       {
	                 if(response=="exist")
	                 {
	                     alert("此部门下存在人员，不能删除！如要删除请先移走人员！");
	                 }
	                 else
	                 {
	                    rightManageForm.action="<%=basePath%>view/rightManage.do?method=deleteDepartment&deptId="+departid;
	                    rightManageForm.submit();
	                 }
	           });  
	      }     
     }
      else
       {
           alert("请选择您要操作的部门!");
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
										<td>
											系统管理-&gt;
											<span class="chuti lv">部门管理</span>${showMsg }
										</td>
										<td id="td1" align="right">
											<img src="<%=basePath%>res/admin/img/add.gif"
												onclick="javascript:addnew()" style="cursor: pointer"
												alt="新增" title="新增" id="addstyle" />
											&nbsp;
											<img src="<%=basePath%>res/admin/img/update.gif"
												onclick="javascript:add()" style="cursor: pointer" alt="修改"
												title="修改" />
											&nbsp;
											<img src="<%=basePath%>res/admin/img/delete.gif"
												onclick="javascript:del()" style="cursor: pointer" alt="删除"
												title="删除" />
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
													<td  valign="top">
														&nbsp;
														<a href="javascript: d.openAll();">全部展开</a> |
														<a href="javascript: d.closeAll();">全部折叠</a>
														<script type="text/javascript">														 
															d = new dTree('d');
													       <% 
													         List treeList = (List)request.getAttribute("treeList");
													          for(int i=0;i<treeList.size();i++){
													          JsTree  tree = (JsTree)treeList.get(i); 											          
													       %>
													       d.add(<%=tree.getId()%>,<%=tree.getParentId()%>,'<%=tree.getName()%>',"<%=tree.getUrl()%>",'','','<%=tree.getImage()%>');    
													       <%}%>
															document.write(d);													  
														</script>
													</td>
													<td valign="top">
														<html:form
															action="view/rightManage.do?method=addDepartmentDo"
															method="post">
															<table id="t1" cellSpacing="1" width="80%"
																cellPadding="0" border="0" bgcolor="#32AEF4">
																<tr>
																	<td nowrap="nowrap" class="chutibaileft">
																		&nbsp;&nbsp;当前部门：
																		<html:hidden property="basicDepartment.deptId" />
																		<span id="departname"></span>
																	</td>
																</tr>
															</table>

															<!--  
															<table id="t2" class="table" cellSpacing="1"
																cellPadding="0" width="80%" border="0">
																<tr class="cstd1">
																	<td class="tdcenter">
																		可执行操作
																	</td>
																	<td class="tdcenter">
																		<a href="javascript:addDepart('1')"> <font id="s"
																			style="color: blue">增加同一级部门</font> </a>
																	</td>
																	<td class="tdcenter">
																		<a href="javascript:addDepart('2')"> <font id="n"
																			color="blue">增加下一级部门</font> </a>
																	</td>
																</tr>
															</table>
															-->
															
															<br>

															<table id="t3" class="table" cellSpacing="1"
																cellPadding="0" width="80%" border="0">
																<tr class="tr_1" align="center">
																	<td class="td_left">
																		<font color="#ff0000">* </font> 部门编码：
																	</td>
																	<td class="td_right">
																		<html:text property="basicDepartment.deptCode"
																			maxlength="20" style="width:80%"></html:text>
																	</td>
																</tr>
																<tr class="tr_1" align="center">
																	<td class="td_left">
																		<font color="#ff0000">* </font>部门名称：
																	</td>
																	<td class="td_right">
																		<html:text property="basicDepartment.deptName"
																			style="width:80%"></html:text>
																	</td>
																</tr>
																<tr class="tr_1" align="center">
																	<td class="td_left">
																		简介：
																	</td>
																	<td class="td_right">
																		<html:textarea property="basicDepartment.remark"
																			rows="10" cols="50"></html:textarea>
																	</td>
																</tr>
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

					<script type="text/javascript">
				     if('${bd.deptId}'!=""){
				       operation('${bd.deptId}','${bd.deptName}','${bd.deptCode}','${bd.remark}');
				     }
				  </script>
				</td>
			</tr>
		</table>
	</body>

</html>
