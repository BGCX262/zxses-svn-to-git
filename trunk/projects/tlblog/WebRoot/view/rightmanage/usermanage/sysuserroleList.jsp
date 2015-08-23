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

		<title>用户角色管理</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script type="text/javascript">
        function sure(){
			sysUserForm.action="<%=basePath%>view/sysuser.do?method=assignRole";
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
						align="center">
						<tr>
							<td>
								<table class="pathbg">
									<tr>
										<!-- 修改导航条信息 -->
										<td height="24">
											系统管理-&gt;用户管理-&gt;
											<span class="chuti lv">用户角色分配</span>${showMsg }
										</td>
										<td align="right">
											<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径-->
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysUserManage','assignRole') }">
											<input name="button" type="button" class="an" id="button"
													onclick="javascript:sure();" value="确定" />
											&nbsp;
											</c:if>
											<input name="button2" type="button" class="an" id="button2"
												value="返回" onclick="javascript:back();" />
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
								<!-- 修改对应的aciton路径 -->
								<html:form action="view/sysuser.do" method="post">
									<input type="hidden" name="id" value="${sysUserInfo.id }" />
									<table cellSpacing="0" cellPadding="0" border="0" width="97%"
										align="center">
										<tr>
											<td>
												<table bgcolor="#32AEF4" class="percentage100">
													<!-- 列表标题栏 -->
													<tr>
														<td class="chutibai tdwidth45 tdbk">
															<input type="checkbox" onclick="checkAll(this,'roleIds');"
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
																		<input type="checkbox" name="roleIds" value="${entity.id}"<c:forEach items="${sysUserRoles}" var="ur"><c:if test="${ur==entity}"> checked="checked"</c:if></c:forEach>/>
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
																		<input type="checkbox" name="roleIds" value="${entity.id}"<c:forEach items="${sysUserRoles}" var="ur"><c:if test="${ur==entity}"> checked="checked"</c:if></c:forEach>/>
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
