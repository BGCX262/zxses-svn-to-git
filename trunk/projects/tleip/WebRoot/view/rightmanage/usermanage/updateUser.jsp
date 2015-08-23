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
		<title>修改用户</title>
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
		  function sure()
		  {
		  		var deptId = document.getElementsByName("basicPerson.basicDepartment.deptId")[0].value;
		  		rightManageForm.action="<%=basePath%>view/rightManage.do?method=updateUserDo&deptId="+deptId;
		                  rightManageForm.submit();
		    
		  }
		  function back()
          {
             var deptId = document.getElementsByName("basicPerson.basicDepartment.deptId")[0].value;
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
										<td  height="24" class="dh1">
											系统管理-&gt;<span class="chuti lv">修改用户</span>${showMsg }
										</td>
											<td align="right">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:sure();" value="确定" />
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

								<html:form action="view/rightManage.do?method=updateUserDo"
									method="post">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" height="151">
													<tr>
													  <td class="td_right" colspan="2">
													  当前部门：<font color="#ff6600" id="dpname">${basicPerson.basicDepartment.deptName}</font>
													  </td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>人员编码:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<html:hidden property="basicPerson.personId" />
															<html:hidden
																property="basicPerson.basicDepartment.deptId" />
															<html:text property="basicPerson.personCode"
																maxlength="20"></html:text>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>人员名称:

														</td>
														<td width="30%" class="td_right">
															<html:text property="basicPerson.personName"
																maxlength="30"></html:text>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>登陆帐号:

														</td>
														<td width="30%" class="td_right">
															<input type="hidden" id="hidAccount"
																value="${rightManageForm.basicPerson.personAccount}">
															<html:text property="basicPerson.personAccount"
																maxlength="20"></html:text>
														</td>
													</tr>
													
													<!--  -->
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>密码:

														</td>
														<td width="30%" class="td_right">
															<html:password property="basicPerson.password" style="width: 155px;"></html:password>
														</td>
													</tr>
													
													<!--  
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>人员类型:

														</td>
														<td width="30%" class="td_right">
															<html:select property="basicPerson.personType"
																style="width: 120px; text-align: center">
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







