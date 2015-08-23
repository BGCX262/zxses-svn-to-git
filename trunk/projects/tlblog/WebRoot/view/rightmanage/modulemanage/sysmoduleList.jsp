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

		<title>模块管理</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script type="text/javascript">
           
        function add(){
            window.location.href="<%=basePath%>view/sysmodule.do?method=addInput&parentId=${sysModuleForm.parentId }";
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
             window.location.href="<%=basePath%>view/sysmodule.do?method=updateInput&parentId=${sysModuleForm.parentId }&sysModuleId="+ editvalue;
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
	             sysModuleForm.action="<%=basePath%>view/sysmodule.do?method=del";
	             sysModuleForm.submit();
             }
          }
          else
          {
             return false;
          }
        } 
        
        function returnParent()
        {
        	window.location.href="<%=basePath%>view/sysmodule.do?parentId=${ppid }";
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
											系统管理-&gt;模块管理-&gt;
											<span class="chuti lv">模块列表</span>${showMsg }
										</td>
										<td align="right">
											<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径 加上权限控制验证-->
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysModuleManage','add') }">
											<img src="<%=basePath%>res/admin/img/add.gif"
												onclick="javascript:add()" style="cursor: pointer" alt="新增"
												title="新增" />
											&nbsp;
											</c:if>
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysModuleManage','update') }">
											<img src="<%=basePath%>res/admin/img/update.gif"
												onclick="javascript:update()" style="cursor: pointer"
												alt="修改" title="修改" />
											&nbsp;
											</c:if>
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysModuleManage','delete') }">
											<img src="<%=basePath%>res/admin/img/delete.gif"
												onclick="javascript:del()" style="cursor: pointer" alt="删除"
												title="删除" />
											&nbsp;
											</c:if>
											&nbsp;
											<input type="button" class="an" onclick="returnParent()" value="返回" title="返回"/>
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
								<html:form action="view/sysmodule.do" method="post">
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
															模块编号
														</td>
														<td class="chutibai tdbk">
															模块名称
														</td>
														<td class="chutibai tdbk">
															模块编码
														</td>
														<td class="chutibai tdbk">
															父模块名称
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
																		<!-- 系统只支持二级模块，如果有父id，这不允许点击模块进入下级模块列表 -->
																		<c:choose>
																			<c:when test="${!empty entity.parent}">
																				${entity.name }
																			</c:when>
																			<c:otherwise>
																				<a href="sysmodule.do?parentId=${entity.id }">${entity.name }</a>
																			</c:otherwise>
																		</c:choose>
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.sn}
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.parent.name}
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
																		<c:choose>
																			<c:when test="${!empty entity.parent}">
																				${entity.name }
																			</c:when>
																			<c:otherwise>
																				<a href="sysmodule.do?parentId=${entity.id }">${entity.name }</a>
																			</c:otherwise>
																		</c:choose>
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.sn}
																	</td>
																	<td class="tdcenter tdbk">
																		${entity.parent.name}
																	</td>
														        </tr>
													        </c:if>
												        </c:forEach>
													</c:if>
													<!-- 在列表数据为空的时候，要显示的提示信息 **!!根据标题列数修改colspan大小 -->
												    <c:if test="${empty qs.resultlist}">
													    <tr class="trcolor" onMouseOver="this.className='cstd2'" onMouseOut="this.className='trcolor'">
													    	<td colspan="5" align="center">
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
												<!-- 最少有一条记录才显示分页导航条(否则出现首页和尾页点击尾页报错！) !!修改对应的url-->
												<c:if test="${qs.totalrecord > 0 }">
										       		<pg:pager items="${qs.totalrecord }" url="sysmodule.do" export="currentPageNumber=pageNumber">
														<pg:param name="parentId"/>
														<pg:first>
															<a href="${pageUrl }">首页</a>
														</pg:first>
														<pg:prev>
															<a href="${pageUrl }">前页</a>
														</pg:prev>
														<pg:pages>
															<c:choose>
																<c:when test="${currentPageNumber eq pageNumber}">
																	<font color="red">${pageNumber }</font>
																</c:when>
																<c:otherwise>
																	<a href="${pageUrl }">${pageNumber }</a>
																</c:otherwise>
															</c:choose>
														</pg:pages>
														<pg:next>
															<a href="${pageUrl }">后页</a>
														</pg:next>
														<pg:last>
															<a href="${pageUrl }">尾页</a>
														</pg:last>
													</pg:pager>
												</c:if>
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
