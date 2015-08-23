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
		<title>深圳市南山区塘朗小学</title>
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
	        
	        function del()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定删除这些记录吗？")==false) return false;
	             messageForm.action="<%=basePath%>/view/messagemanage.do?method=deleteMessage";
             	 messageForm.submit(); 
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
	             messageForm.action="<%=basePath%>/view/messagemanage.do?method=approveMessage";
             	 messageForm.submit(); 
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
	             messageForm.action="<%=basePath%>/view/messagemanage.do?method=unApproveMessage";
             	 messageForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function viewDetails()
	        { 
	          if(checkNum())
	          {
	             window.location.href="<%=basePath%>view/messagemanage.do?method=viewDetails&messageId=" + editvalue;
	          }
	          else
	          {
	             return false;
	          }
	        }
		</script>
	</head>

	<body>
		<html:form action="view/messagemanage.do?method=queryMessage"
			method="post">
			<table class="pathbg">
				<tr>
					<td>
						会员管理-&gt;<span class="chuti lv">留言管理</span>${showMsg }
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
							<input type="button" class="an" onclick="viewDetails()" value="查看详情" title="查看详情"/>
							&nbsp;
							<input type="button" class="an" onclick="approveMember()" value="审批" title="审批"/>
							&nbsp;
							<input type="button" class="an" onclick="unApproveMember()" value="反审批" title="反审批"/>
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
									留言人
								</td>
								<td class="chutibai tdbk">
									留言内容
								</td>
								<td class="chutibai tdbk">
									留言时间
								</td>
								<td class="chutibai tdbk">
									是否审核
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.messageId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.frontUser.userAccount }
										</td>
										<td class="tdcenter tdbk" style="text-align: left;">
											<c:choose>
									          <c:when test="${fn:length(column.messageContent) > 60}">
									              <c:out value="${fn:substring(column.messageContent, 0, 60)}..." />
									          </c:when>
									       	<c:otherwise>
									            <c:out value="${column.messageContent}" />
									          </c:otherwise>
									     	</c:choose>
									     	<%--
											${column.messageContent }
											--%>
										</td>
										<td class="tdcenter tdbk">
											${column.messageDate }
										</td>
										<td class="tdcenter tdbk">
											${column.approveStatusStr }
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.messageId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.frontUser.userAccount }
										</td>
										<td class="tdcenter tdbk" style="text-align: left;">
											<c:choose>
									          <c:when test="${fn:length(column.messageContent) > 60}">
									              <c:out value="${fn:substring(column.messageContent, 0, 60)}..." />
									          </c:when>
									       	<c:otherwise>
									            <c:out value="${column.messageContent}" />
									          </c:otherwise>
									     	</c:choose>
										</td>
										<td class="tdcenter tdbk">
											${column.messageDate }
										</td>
										<td class="tdcenter tdbk">
											${column.approveStatusStr }
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
