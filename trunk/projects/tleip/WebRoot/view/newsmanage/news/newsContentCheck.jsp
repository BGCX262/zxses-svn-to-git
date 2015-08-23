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
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css">
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
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
	        
	       function edit()
	        { 
	        	if (checkDelNum()){
					 var yxdm = document.getElementById('yxdm').value;
					 var classId = document.getElementById('classId').value;
		          	 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=upateNewsContentCheck&classId="+classId+"&yxdm="+yxdm;
		             newsmanageForm.submit();
		             return false;
	            }
	        }
			function changeYxdm(){
				var yxdm = document.getElementById('yxdm').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsContentCheck&yxdm="+yxdm;
	            newsmanageForm.submit();
	            return false;
			}
			
			function changeClassId(){
				var yxdm = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsContentCheck&yxdm="+yxdm+"&classId="+classId;
	            newsmanageForm.submit();
	            return false;
			}
			function changeTypeId(){
				var yxdm    = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				var typeId  = document.getElementById('typeId').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsContentCheck&yxdm="+yxdm+"&classId="+classId+"&typeId="+typeId;
	            newsmanageForm.submit();
	            return false;
			}

		</script>
	</head>

	<body>
		<html:form action="view/newsmanage.do?method=findNewsContentCheck"
			method="post">
			<table class="pathbg">
				<tr>
					<td>
						信息发布管理-&gt;
						<span class="chuti lv">新闻审核</span>${showMsg }
					</td>
					<td align="right">
						<input name="button" type="button" class="an1" id="button"
							onclick="javascript:edit();" value="审核通过" />
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
						<select name="yxdm" style="width: 170px; text-align: center"
							onchange="changeYxdm()">
							${allYxdmSelect }
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp; 大类：
						<select name="classId" style="width: 140px; text-align: center"
							onchange="changeClassId()">
							${allClassIdSelect }
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp; 小类：
						<select name="typeId" style="width: 140px; text-align: center"
							onchange="changeTypeId()">
							${allTypeSelect }
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
									新闻标题
								</td>
								<td class="chutibai tdbk">
									小类
								</td>
								<td class="chutibai tdbk">
									大类
								</td>
								<td class="chutibai tdbk">
									发布人
								</td>
								<td class="chutibai tdbk">
									发布日期
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.newsId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.newsTitle }
										</td>
										<td class="tdcenter tdbk">
											${item.typeName }
										</td>
										<td class="tdcenter tdbk">
											<div
												title="<logic:iterate id="item" name="column" property="newsItemSmalls">
									                         ${item.newsItemBig.className }</logic:iterate>">
												<logic:iterate id="item" name="column"
													property="newsItemSmalls" length="2">
									                         ${item.newsItemBig.className }&nbsp;&nbsp;</logic:iterate>
												<bean:size id="smaillSize" name="column"
													property="newsItemSmalls" />
												<c:if test="${smaillSize>2}">
									                         ....
									                         </c:if>
											</div>
										</td>

										<td class="tdcenter tdbk">
											${column.announcePerson }
										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.newsDate }"
												pattern="yyyy-MM-dd" />
										</td>


									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.newsId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.newsTitle }
										</td>
										<td class="tdcenter tdbk">
											${item.typeName }
										</td>
										<td class="tdcenter tdbk">
											<div
												title="<logic:iterate id="item" name="column" property="newsItemSmalls">
									                         ${item.newsItemBig.className }</logic:iterate>">
												<logic:iterate id="item" name="column"
													property="newsItemSmalls" length="2">
									                         ${item.newsItemBig.className }&nbsp;&nbsp;</logic:iterate>
												<bean:size id="smaillSize" name="column"
													property="newsItemSmalls" />
												<c:if test="${smaillSize>2}">
									                         ....
									                         </c:if>
											</div>
										</td>

										<td class="tdcenter tdbk">
											${column.announcePerson }
										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.newsDate }"
												pattern="yyyy-MM-dd" />
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
