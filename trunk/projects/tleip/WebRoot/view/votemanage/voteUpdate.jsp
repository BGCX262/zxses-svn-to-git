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
		<title>投票主题修改</title>
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
	</head>
	<script type="text/javascript">
       function update(){
          var name = document.getElementsByName("sysRole.roleName")[0].value;
          if(name==""){
            alert("角色名称不能为空");
            return false;
          }
	          
	   $.post("<%=basePath %>view/rightManage.do?method=updateRoleExist",{rolename:name},function(response){
	               rightManageForm.submit();
	     });
       }
       function back(){                 
            window.location.href="<%=basePath%>view/rightManage.do?method=queryRole";
        }
     </script>
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
										<td width="514" height="24">
											系统管理-&gt;角色权限-&gt;
											<span class="lv chuti">新增角色</span>${showMsg }
										</td>
										<td align="right">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:update();" value="确定" />
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
							<td height="20"></td>
						</tr>

						<tr>
							<td>
								<table cellSpacing="0" cellPadding="0" border="0" width="97%"
									align="center">
									<tr>
										<td>
											<html:form action="view/rightManage.do?method=updateRole"
												method="post">
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" align="center">

													<tr>
														<td class="td_left">
															角色名称
														</td>
														<td class="td_right">
															<html:hidden property="sysRole.roleId" />
															<html:text property="sysRole.roleName" />
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
