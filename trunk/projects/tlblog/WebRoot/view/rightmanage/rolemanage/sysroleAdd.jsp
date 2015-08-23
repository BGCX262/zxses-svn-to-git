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
		<title>新增角色</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script language="JavaScript" type="text/JavaScript"> 
		  function add()
		  {		    
		    var roleName = $("#roleName")[0].value;
		    
		    if(roleName !="")
		    {
		    	var roleNameObj = $("#roleName");
		    	var roleNameUsedObj = $("#roleNameUsed");
				//ajax检查角色名是否已经使用
				if($.trim($(roleNameObj).val()) != ""){
					$.ajax( {
						url : "<%=basePath%>view/sysrole.do?method=checkRoleName",
						data : {
							roleName : roleNameObj.val()
						},
						async : false,
						type : "POST",
						success : function(data) {
							if (data == "used") {
								alert("该角色名已使用,请更换！");
								roleNameUsedObj.val("true");
							}else{
								//alert("<font color='green'>用户帐号可用！</font>");
								roleNameUsedObj.val("false");
							}
						}
					});
				}
		    	
		    	if(roleNameUsedObj.val()=="true"){
		    		return;
		    	}
		    	sysRoleForm.submit();
            }
            else
            {
                alert("带*号的不能为空！");
                return;
            }
		  }
		  function back()
          {
		     window.location.href="<%=basePath%>view/sysrole.do";
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
												系统管理-&gt;角色管理-&gt;
												<span class="chuti lv">新增角色</span>${showMsg }
											</td>	
											<td align="right">
												<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径-->
												<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysRoleManage','add') }">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:add();" value="确定" />
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
								<!-- 修改对应的aciton路径 -->
								<html:form action="view/sysrole.do?method=add" method="post">
									<!-- 记录用户名是否已使用,js验证使用 -->
									<input type="hidden" id="roleNameUsed" value="false"/>
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<!-- 具体录入字段入口 -->
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>角色名称:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="roleName" id="roleName" size="30" maxlength="20" />
														</td>
													</tr>
												</table>
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







