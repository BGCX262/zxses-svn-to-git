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
		<title>新增用户</title>
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
		
		<script language="JavaScript" type="text/JavaScript"> 
		  function add()
		  {		    
		    var personCode = document.getElementsByName("basicPerson.personCode")[0].value;
		    var personName = document.getElementsByName("basicPerson.personName")[0].value;
		    var password = document.getElementsByName("basicPerson.password")[0].value;
		    var personAccount = document.getElementsByName("basicPerson.personAccount")[0].value;
		    var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
		    if(personCode !="" && personName !="" && password !="" && personAccount !="")
		    {
		    $.post("rightManage.do?method=checkUserAccount",{personAccount:personAccount,deptId:deptId},function(response){
               if(response=="exist"){
                  alert("此登录账号已存在，请重新输入！");
               }else{ 
                rightManageForm.submit();
               }
            });
            }
            else
            {
                alert("带*号的不能为空！")
            }
		  }
		  function back()
          {
             var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
             rightManageForm.action="<%=basePath%>view/rightManage.do?method=queryPerson&deptId="+deptId;
             rightManageForm.submit();             
          } 
		</script>
	</head>
	<body>		
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td>
								<table class="pathbg">
										<tr>
											<td height="24" class="dh1">
												系统管理-&gt;<span class="chuti lv">添加用户</span>${showMsg }
											</td>	
											<td align="right">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:add();" value="确定" />
											&nbsp;
											<input name="button2" type="button" class="an" id="button2"
												value="返回" onclick="javascript:back();" />
											</td>	
											<td width="15"></td>									
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
								
								<html:form action="view/rightManage.do?method=addUser" method="post">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" height="151">
													<tr>
													<html:hidden property="basicDepartment.deptId" />
													  <td class="td_right" colspan="2">
													  当前部门：<font color="#ff6600" id="dpname">${rightManageForm.basicDepartment.deptName}</font>
													  </td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>人员编码:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<html:text property="basicPerson.personCode" maxlength="20"></html:text>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>人员名称:
                                                               
														</td>
														<td width="30%" class="td_right">
															 <html:text property="basicPerson.personName" maxlength="30"></html:text>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>登陆帐号:
                                                              
														</td>
														<td width="30%" class="td_right">
															  <html:text property="basicPerson.personAccount" maxlength="20"></html:text>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>密&nbsp;&nbsp;&nbsp;&nbsp;码:
                                                             
														</td>
														<td width="30%" class="td_right">
															   <html:password property="basicPerson.password" maxlength="20" style="width: 155px;"></html:password>
														</td>
													</tr>
													<!-- 
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>人员类型:
                                                             
														</td>
														<td width="30%" class="td_right">
															   <html:select property="basicPerson.personType" style="width: 120px; text-align: center">
															       <html:option value="1">学生</html:option>
															       <html:option value="2">教师</html:option>
															   </html:select>
														</td>
													</tr>												
													 -->
													 
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
	</body>
</html>







