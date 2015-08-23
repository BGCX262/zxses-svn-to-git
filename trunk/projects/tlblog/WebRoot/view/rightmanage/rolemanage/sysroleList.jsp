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
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script type="text/javascript">
           
        function add(){
            window.location.href="<%=basePath%>view/sysrole.do?method=addInput";
        }
        
        var editvalue;
        function checkNum(){
            var chkbs = document.getElementsByName("check");   
            var chkNum = 0;   
            for(i=0;i<chkbs.length;i++)
            {
              if(chkbs[i].checked){
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
              if(chkbs[i].checked)
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
             window.location.href="<%=basePath%>view/sysrole.do?method=updateInput&sysRoleId="+ editvalue;
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
	             sysRoleForm.action="<%=basePath%>view/sysrole.do?method=del";
	             sysRoleForm.submit();
             }
          }
          else
          {
             return false;
          }
        }
        
        function assignPrivilege()
        {
          if(checkNum())
          {
             window.location.href="<%=basePath%>view/sysrole.do?method=assignPrivilegeInput&sysRoleId="+ editvalue;
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
										<!-- 修改导航条信息 -->
										<td height="24">
											系统管理-&gt;角色管理-&gt;
											<span class="chuti lv">角色列表</span>${showMsg }
										</td>
										<td align="right">
											<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径 加上权限控制验证-->
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysRoleManage','add') }">
											<img src="<%=basePath%>res/admin/img/add.gif"
												onclick="javascript:add()" style="cursor: pointer" alt="新增"
												title="新增" />
											&nbsp;
											</c:if>
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysRoleManage','update') }">
											<img src="<%=basePath%>res/admin/img/update.gif"
												onclick="javascript:update()" style="cursor: pointer" alt="修改" title="修改" />
											&nbsp;
											</c:if>
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysRoleManage','delete') }">
											<img src="<%=basePath%>res/admin/img/delete.gif"
												onclick="javascript:del()" style="cursor: pointer" alt="删除"
												title="删除" />
											&nbsp;
											</c:if>
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysRoleManage','assignPrivilege') }">
											&nbsp;
											<input type="button" class="an" style="width: 80px;" onclick="assignPrivilege()" value="分配权限" title="分配权限"/>
											&nbsp;
											</c:if>
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
								<!-- 修改对应的aciton路径 -->
								<html:form action="view/sysrole.do" method="post">
									<table cellSpacing="0" cellPadding="0" border="0" width="97%"
										align="center">
										<tr>
											<td>
												<table bgcolor="#32AEF4" class="percentage100">
													<!-- 列表标题栏 -->
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
													<!-- 列表数据栏 -->
											        <c:if test="${!empty qs.resultlist}">
												        <c:forEach items="${qs.resultlist }" var="entity" varStatus="status">
													        <c:if test="${(status.index + 1)%2 == 0}">
															    <tr class="cstd1" onMouseOver="this.className='cstd2'" onMouseOut="this.className='cstd1'">
															    	<td class="tdcenter tdbk">
																		<input type="checkbox" name="check" value="${entity.id}">
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.id}
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.roleName}
																	</td>
														        </tr>
													        </c:if>
													        <c:if test="${(status.index+1)%2 !=0}">
															    <tr class="trcolor" onMouseOver="this.className='cstd2'" onMouseOut="this.className='trcolor'">
															    	<td class="tdcenter tdbk">
																		<input type="checkbox" name="check" value="${entity.id}">
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.id}
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.roleName}
																	</td>
														        </tr>
													        </c:if>
												        </c:forEach>
													</c:if>
													<!-- 在列表数据为空的时候，要显示的提示信息 **!!根据标题列数修改colspan大小-->
												    <c:if test="${empty qs.resultlist}">
													    <tr class="trcolor" onMouseOver="this.className='cstd2'" onMouseOut="this.className='trcolor'">
													    	<td colspan="3" align="center">
													    	没有找到相应的记录!
													    	</td>
													    </tr>
												    </c:if>
												</table>
											</td>
										</tr>
										<tr>
											<td align="right">
												<br/>
												<!-- 在这里插入分页导航条 -->
												
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
