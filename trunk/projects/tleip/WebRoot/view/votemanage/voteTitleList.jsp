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
	             voteForm.action="<%=basePath%>/view/votemanage.do?method=deleteVoteTitle";
             	 voteForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function publishVote()
	        {
	           if(checkDelNum())
	          {   
	          	 if (confirm("确定发布这些记录吗？")==false) return false;
	             voteForm.action="<%=basePath%>/view/votemanage.do?method=publishVoteTitle";
             	 voteForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function unPublishVote()
	        {
	           if(checkDelNum())
	          {   
	          	 if (confirm("确定反发布这些记录吗？")==false) return false;
	             voteForm.action="<%=basePath%>/view/votemanage.do?method=unPublishVoteTitle";
             	 voteForm.submit(); 
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
	        
	        function addVote()
	        {
	             window.location.href="<%=basePath%>view/votemanage.do?method=toAddVoteTitle";
	        } 
	        
	        function updateVote()
	        { 
	          if(checkNum())
	          {
	          	//发布状态的投票主题不可修改
	          	var publishStr = $("input[type=checkbox][value="+editvalue+"]").parent().siblings(".publishStatus").children("font").html();
	          	if(publishStr == "发布"){
	          		alert("发布状态的投票主题不能修改!");
	          		return;
	          	}
	             window.location.href="<%=basePath%>view/votemanage.do?method=enterUpdateVoteTitle&voteTitleId=" + editvalue;
	          }
	          else
	          {
	             return false;
	          }
	        }
		</script>
		<style>
			//标识发布状态--获取其值用于提示用户发布状态的投票主题不可修改
			.publishStatus{
			}
		</style>
	</head>

	<body>
		<html:form action="view/votemanage.do?method=queryVote"
			method="post">
			<table class="pathbg">
				<tr>
					<td>
						会员管理-&gt;<span class="chuti lv">投票管理</span>${showMsg }
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
							<input type="button" class="an" onclick="addVote()" value="新增" title="新增"/>
							&nbsp;
							<input type="button" class="an" onclick="updateVote()" value="修改" title="修改"/>
							&nbsp;
							<input type="button" class="an" onclick="publishVote()" value="发布" title="发布"/>
							&nbsp;
							<input type="button" class="an" onclick="unPublishVote()" value="反发布" title="反发布"/>
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
									投票主题
								</td>
								<td class="chutibai tdbk">
									投票类型
								</td>
								<td class="chutibai tdbk">
									投票时间
								</td>
								<td class="chutibai tdbk">
									发布状态
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.voteId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											<a href="${basePath }view/votemanage.do?method=queryVoteItems&voteId=${column.voteId }">${column.voteName }</a>
										</td>
										<td class="tdcenter tdbk">
											${column.voteTypeStr }
										</td>
										<td class="tdcenter tdbk">
											${column.voteDate }
										</td>
										<td class="tdcenter tdbk publishStatus">
											${column.publishStatusStr }
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.voteId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											<a href="${basePath }view/votemanage.do?method=queryVoteItems&voteId=${column.voteId }">${column.voteName }</a>
										</td>
										<td class="tdcenter tdbk">
											${column.voteTypeStr }
										</td>
										<td class="tdcenter tdbk">
											${column.voteDate }
										</td>
										<td class="tdcenter tdbk publishStatus">
											${column.publishStatusStr }
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
