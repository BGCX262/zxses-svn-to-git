<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%@ include file="/inc/include.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息发布管理系统</title>
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
		<script type="text/javascript">
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
	        
	        function del()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定删除这些记录吗？")==false) return false;
	             memberManageForm.action="<%=basePath%>/view/membermanage.do?method=deleteMember";
             	 memberManageForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function approveMember()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定审批这些记录吗？")==false) return false;
	             memberManageForm.action="<%=basePath%>/view/membermanage.do?method=approveMember";
             	 memberManageForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function unApproveMember()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定反审批这些记录吗？")==false) return false;
	             memberManageForm.action="<%=basePath%>/view/membermanage.do?method=unApproveMember";
             	 memberManageForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function enableMember()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定启用这些记录吗？")==false) return false;
	             memberManageForm.action="<%=basePath%>/view/membermanage.do?method=enableMember";
             	 memberManageForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function disableMember()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定禁用这些记录吗？")==false) return false;
	             memberManageForm.action="<%=basePath%>/view/membermanage.do?method=disableMember";
             	 memberManageForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
		</script>
	</head>

	<body>
		<html:form action="view/membermanage.do?method=queryMember"
			method="post">
			<table class="pathbg">
				<tr>
					<td>
						会员管理-&gt;<span class="chuti lv">前端用户管理</span>${showMsg }
					</td>
					<td>
						<div class="right_operate">
							<%--
							<img src="<%=basePath%>res/admin/img/update.gif"
								onclick="javascript:disableMember()" style="cursor: pointer" alt="禁用"
								title="禁用" />
							&nbsp;
							<input name="button" type="button" class="an" id="approveMember"
								onclick="javascript:approveMember()" value="审批" />
							&nbsp;
							--%>
							<input type="button" class="an" onclick="approveMember()" value="审批" title="审批"/>
							&nbsp;
							<input type="button" class="an" onclick="unApproveMember()" value="反审批" title="反审批"/>
							&nbsp;
							<input type="button" class="an" onclick="enableMember()" value="启用" title="启用"/>
							&nbsp;
							<input type="button" class="an" onclick="disableMember()" value="禁用" title="禁用"/>
							&nbsp;
							<img src="<%=basePath%>res/admin/img/delete.gif"
								onclick="javascript:del()" style="cursor: pointer" alt="删除"
								title="删除" />
							&nbsp;
						</div>
					</td>
				</tr>
			</table>
			<table class="percentage98">
				<tr>
					<td class="height8"></td>
				</tr>
				<tr>
					<td class="x"></td>
				</tr>
				<tr>
					<td>
						<table bgcolor="#32AEF4" class="percentage100">

							<tr>
								<td class="chutibai tdwidth45 tdbk">
									<input type="checkbox" onclick="checkAll(this,'check');"
										id="checkall" alt="全选">
								</td>
								<td class="chutibai tdwidth45 tdbk">
									序号
								</td>
								<td class="chutibai tdbk">
									登录帐号
								</td>
								<td class="chutibai tdbk">
									真实姓名
								</td>
								<td class="chutibai tdbk">
									用户类型
								</td>
								<td class="chutibai tdbk">
									是否审核
								</td>
								<td class="chutibai tdbk">
									是否启用
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.userId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.userAccount }
										</td>
										<td class="tdcenter tdbk">
											${column.userName }
										</td>
										<td class="tdcenter tdbk">
											${column.userType }
										</td>
										<td class="tdcenter tdbk">
											${column.approveStatusStr }
										</td>
										<td class="tdcenter tdbk">
											${column.availableStr }
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.userId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.userAccount }
										</td>
										<td class="tdcenter tdbk">
											${column.userName }
										</td>
										<td class="tdcenter tdbk">
											${column.userType }
										</td>
										<td class="tdcenter tdbk">
											${column.approveStatusStr }
										</td>
										<td class="tdcenter tdbk">
											${column.availableStr }
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
	</body>
</html>
