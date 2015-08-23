<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css">
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/move.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script language="javascript" type="text/javascript">	
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
	          if (confirm("确定删除吗？")==false) return false;
	          	 showloading("<%=basePath%>commons/ajaxload.jsp",90,90); 
	          	 monitorManageForm.action = "<%=basePath%>view/monitor.do?method=delete"+"&parentModuleFlag="+'${parentModuleFlag }';
	             monitorManageForm.submit();
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        }
	        
	        
	      function queryByDate(){
	     	 var startDate = document.getElementById("startDate").value;
	     	 var endDate = document.getElementById("endDate").value;
				if(startDate > endDate){
					var tmp = startDate;
					startDate = endDate;
					endDate = tmp;
				}
				 showloading("<%=basePath%>commons/ajaxload.jsp",300,300);
				 monitorManageForm.action = "<%=basePath%>view/monitor.do?method=queryByDate&flag=1"+"&startDate="+startDate+"&endDate="+endDate;
		         monitorManageForm.submit();
			}   
	     </script>
	</head>

	<body class="centerbg">
		<html:form action="view/monitor.do?method=list" method="post">
			<table class="pathbg">
				<tr align="left">
					<td>
						监控管理-&gt;
						<span class="lv chuti">监控列表</span>${showMsg }
					</td>
					<td>
						<div class="right_operate">

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
					<td class="tdcenter">
						起始时间：
						<input class="Wdate" type="text" id="startDate" name="startDate"
							value="${startDate}" readonly="readonly"
							onfocus="WdatePicker({firstDayOfWeek:1})" />
						&nbsp;&nbsp; 截止时间：
						<input class="Wdate" type="text" id="endDate" name="endDate"
							value="${endDate}" readonly="readonly"
							onfocus="WdatePicker({firstDayOfWeek:1})" />
						&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="an" id="button"
							value="查询" onclick="javascript:queryByDate()" />
					</td>
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
									日志内容
								</td>
								<td class="chutibai tdbk">
									操作大类
								</td>
								<td class="chutibai tdbk">
									操作小类
								</td>
								<td class="chutibai tdwidth155 tdbk">
									操作时间
								</td>
								<td class="chutibai tdbk">
									操作人员
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.id}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>
										<td class="tdcenter tdbk">
											${column.loginfo}
										</td>
										<td class="tdcenter tdbk">
											${column.className }
										</td>
										<td class="tdcenter tdbk">
											${column.typeName }
										</td>
										<td class="tdcenter tdwidth155 tdbk">
											<fmt:formatDate value="${column.operateTime }" type="both" />
										</td>
										<td class="tdcenter tdbk">
											${column.operator }
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">

										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.id}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>
										<td class="tdcenter tdbk">
											${column.loginfo}
										</td>
										<td class="tdcenter tdbk">
											${column.className }
										</td>
										<td class="tdcenter tdbk">
											${column.typeName }
										</td>
										<td class="tdcenter tdwidth155 tdbk">
											<fmt:formatDate value="${column.operateTime }" type="both" />
										</td>
										<td class="tdcenter tdbk">
											${column.operator }
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
