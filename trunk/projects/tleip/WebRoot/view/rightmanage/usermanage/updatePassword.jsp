<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修改密码</title>
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
		     var newId = document.getElementById("password1").value;
		     var id = document.getElementById("password2").value;
		     var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;
		     if(newId == "" || id == "")
		     {
		         alert("带*号的不能为空！");
		     }
		     else
		     {
			     if(newId != id)
			     {
			       alert("您两次输入的密码不一至！");
			     }
			     else
			     {
			        rightManageForm.action="<%=basePath%>view/rightManage.do?method=updatePasswordDo&deptId="+deptId;             
                    rightManageForm.submit();
			     }
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
										<td width="514" height="24">
											系统管理-&gt;
											<span class="lv chuti">修改用户密码</span>${showMsg }
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

								<html:form action="view/rightManage.do?method=updatePasswordDo"
									method="post">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" height="151">
													<tr>
														<html:hidden property="basicPerson.personId" />
														<html:hidden property="basicDepartment.deptId" />
														<td class="td_right" colspan="2">
															用户名称：
															<font color="#ff6600">${rightManageForm.basicPerson.personName}</font>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>请输入新密码:
														</td>
														<td width="30%" class="td_right">
															<input type="password" id="Password1" value=""
																maxlength="20">
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>请再次输入新密码:

														</td>
														<td width="30%" class="td_right">
															<input type="password" id="password2" name="password2"
																value="" maxlength="20">
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
	</body>
</html>







