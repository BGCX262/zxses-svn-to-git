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
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script language="JavaScript" type="text/JavaScript"> 
		  function update()
		  {		    
		    /*var personCode = document.getElementsByName("basicPerson.personCode")[0].value;
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
            }*/
            sysUserForm.submit();
		  }
		  function back()
          {
		     window.location.href="<%=basePath%>view/sysuser.do";
		  }	
		</script>
	</head>
	<body>		
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left">
							<tr>
								<td>
								<table class="pathbg">
										<tr>
											<!-- 修改导航条信息 -->
											<td height="24" class="dh1">
												系统管理-&gt;用户管理-&gt;
												<span class="chuti lv">修改用户</span>${showMsg }
											</td>	
											<td align="right">
												<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径-->
												<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysUserManage','update') }">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:update();" value="确定" />
												&nbsp;
												</c:if>
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
								<!-- 修改form action路径 -->
								<html:form action="view/sysuser.do?method=update" method="post">
									<!-- 主键id -->
									<input type="hidden" name="id" value="${sysUserInfo.id }"/>
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>登录帐号:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="username" id="username" value="${sysUserInfo.username }" readonly="readonly" size="30" maxlength="20" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>用户姓名:
											</td>
											<td width="30%" class="td_right">
												 <input type="text" name="realname" id="realname" value="${sysUserInfo.realname }" size="30" maxlength="20" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>性别:
											</td>
											<td width="30%" class="td_right">
												<select id="sex" name="gender" style="width: 100px">
													<option value="男"${sysUserInfo.gender=="男"?" selected=\"selected\"":"" }>男</option>
													<option value="女"${sysUserInfo.gender=="女"?" selected=\"selected\"":"" }>女</option>	
												</select>
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>电话:
											</td>
											<td width="30%" class="td_right">
												  <input type="text" name="phone" id="phone" value="${sysUserInfo.phone }" size="30" maxlength="20" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>邮箱:
											</td>
											<td width="30%" class="td_right">
												  <input type="text" name="userEmail" id="userEmail" value="${sysUserInfo.userEmail }" size="30" maxlength="20" />
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
	</body>
</html>







