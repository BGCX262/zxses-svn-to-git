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

		<title>角色管理</title>
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
		<script type="text/javascript">
           
        function add(){                 
            window.location.href="<%=basePath%>view/rightManage.do?method=enterAddRole";
        }
        
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
        
        function update()
        { 
          if(checkNum())
          {
              
             window.location.href="<%=basePath%>/view/rightManage.do?method=enterUpdateRole&roleId="+ editvalue;
          }
          else
          {
             return false;
          }
        }
        
        function del()
        {
          if(checkDelNum())
          {
             if(window.confirm("确定删除这些数据吗？"))
             {
             rightManageForm.action="<%=basePath%>/view/rightManage.do?method=deleteRole";
             rightManageForm.submit();
             }
          }
          else
          {
             return false;
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
											系统管理-&gt;角色管理-&gt;
											<span class="chuti lv">角色列表</span>${showMsg }
										</td>
										<td align="right">
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
							<td class="height8"></td>
						</tr>

						<tr>
							<td class="x"></td>
						</tr>

						<tr>
							<td>
								<html:form
									action="view/rightManage.do?method=queryRole&currentPage=1"
									method="post">
									<table cellSpacing="0" cellPadding="0" border="0" width="97%"
										align="center">
										<tr>
											<td>
												<table bgcolor="#32AEF4" class="percentage100">
													<tr>
														<td class="chutibai tdwidth45 tdbk">
															<input type="checkbox" onclick="checkAll(this,'check');"
																id="checkall" alt="全选">
														</td>
														<td class="chutibai tdbk">
															角色编号
														</td>
														<td class="chutibai tdbk">
															角色名称
														</td>
													</tr>

													<logic:iterate id="r" name="pageList" property="list"
														indexId="roleIndex">
														<c:if test="${(roleIndex+1)%2 == 0}">
															<tr class="cstd1" onMouseOver="this.className='cstd2'"
																onMouseOut="this.className='cstd1'">
																<td class="tdcenter tdbk">
																	<input type="checkbox" name="check" value="${r.roleId}">
																</td>
																<td class="tdcenter tdbk">
																	${r.roleId}
																</td>
																<td class="tdcenter tdbk">
																	${r.roleName}
																</td>
															</tr>
														</c:if>
														<c:if test="${(roleIndex+1)%2 !=0}">
															<tr class="trcolor" onMouseOver="this.className='cstd2'"
																onMouseOut="this.className='trcolor'">
																<td class="tdcenter tdbk">
																	<input type="checkbox" name="check" value="${r.roleId}">
																</td>
																<td class="tdcenter tdbk">
																	${r.roleId}
																</td>
																<td class="tdcenter tdbk">
																	${r.roleName}
																</td>
															</tr>
														</c:if>
													</logic:iterate>
												</table>
											</td>
										</tr>
										<tr>
											<td align="right">
												${pageList.view }
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
