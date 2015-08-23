<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
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
	              alert("请选择一条或多条记录!");
	              return false;
	            }
	            else{
	               return true;
	            }  
	        }
	      
	        function edit(){
	        	if (checkDelNum()){
	        	    showloading("<%=basePath%>commons/ajaxload.jsp",200,200); 
					newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=updateNewsItemSmallCheckFlag";
		            newsmanageForm.submit();
		            return false;
	            }
	        }
			function changeYxdm(){
				var yxdm = document.getElementById('yxdm').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsItemSmallCheckFlag&yxdm="+yxdm;
	            newsmanageForm.submit();
	            return false;
			}
			
			function changeClassId(){
				var yxdm = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsItemSmallCheckFlag&yxdm="+yxdm+"&classId="+classId;
	            newsmanageForm.submit();
	            return false;
			}
		</script>
	</head>

	<body>
		<html:form action="view/newsmanage.do?method=findNewsItemSmall"
			method="post">
			<table class="pathbg">
				<tr>
					<td>
						新闻广告类型管理-&gt;
						<span class="chuti lv">审核设置</span>${showMsg }
					</td>
					<td align="right">
						<input name="button" type="button" class="an" id="button"
							onclick="javascript:edit();" value="保存" />
					</td>
					<td width="20"></td>
				</tr>
			</table>
			<table class="percentage98">
				<tr>
					<td class="height8"></td>
				</tr>

				<tr>
					<td class="tdcenter">
						所属单位：
						<select name="yxdm" style="width: 170px; text-align: left;"
							onchange="changeYxdm()">
							${allYxdmSelect }
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp; 大类：
						<select name="classId" style="width: 150px; text-align: center"
							onchange="changeClassId()">
							${allClassIdSelect }
						</select>
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
									小类名称
								</td>
								<td class="chutibai tdbk">
									所属的大类名称
								</td>
								<td class="chutibai tdbk">
									审核标志
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.typeId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.typeName }
										</td>
										<td class="tdcenter tdbk">
											${column.newsItemBig.className }
										</td>
										<td class="tdcenter tdbk">
											<input type="radio" name="${column.typeId}" value="1"
												<c:if test="${column.checkFlag == 1 || column.checkFlag == null }">
																		checked="checked"
																	</c:if>>
											自动审核
											<input type="radio" name="${column.typeId}" value="0"
												<c:if test="${column.checkFlag == 0}">
																		checked="checked"
																	</c:if>>
											手工审核
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.typeId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.typeName }
										</td>
										<td class="tdcenter tdbk">
											${column.newsItemBig.className }
										</td>
										<td class="tdcenter tdbk">
											<input type="radio" name="${column.typeId}" value="1"
												<c:if test="${column.checkFlag == 1 || column.checkFlag == null }">
																		checked="checked"
																	</c:if>>
											自动审核
											<input type="radio" name="${column.typeId}" value="0"
												<c:if test="${column.checkFlag == 0}">
																		checked="checked"
																	</c:if>>
											手工审核
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
