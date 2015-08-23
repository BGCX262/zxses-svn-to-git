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
		<script language="javascript" type="text/javascript">
			function add(){
				var classId = document.getElementById('classId');
				if(!checkRightForNews('<%=basePath%>','${parentModuleFlag}',"add")){
					return false;
				}
				if ( classId.value == 0){
					alert("请选择大类！");
					document.getElementById('classId').focus();
					return false;
				}
				var yxdm = document.getElementById('yxdm').value;
				var typeId = document.getElementById('typeId').value;
				var classId = document.getElementById('classId').value;
				window.location = "<%=basePath%>view/newsmanage.do?method=findAddNewsContentManage&classId="+classId+"&typeId="+typeId+"&parentModuleFlag="+'${parentModuleFlag}'+"&yxdm="+yxdm;
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
	        
	        function browse(newsId)
	        {   var classId = document.getElementById('classId').value;
	            var typeId = document.getElementById('typeId').value;
				 window.location ="<%=basePath%>view/newsmanage.do?method=findAmendNewsContentManage&newsId="+ newsId+"&optMark=Y"+"&parentModuleFlag="+'${parentModuleFlag }'
	   			    				+"&classId="+classId+"&typeId="+typeId;
	        }
	        function edit(){
	        	var newsId = "";
	        	var chkbs = document.getElementsByName("check");   
	            var chkNum = 0;
	            var classId = document.getElementById('classId').value;
	            var typeId = document.getElementById('typeId').value;
	            if(!checkRightForNews('<%=basePath%>','${parentModuleFlag}',"update")){
					return false;
				}  
	            for(i=0;i<chkbs.length;i++){
	              if(chkbs(i).checked){
	            	chkNum++;
	            	newsId = chkbs(i).value;
	              }
	            }
	            if(chkNum<1){
	              	alert("请选择一条记录来修改!");
	              	return false;
	            }else if (chkNum > 1){
	            	alert("只能选择一条记录来修改!");
	              	return false;
	            }else{
	   			    window.location ="<%=basePath%>view/newsmanage.do?method=findAmendNewsContentManage&newsId="+ newsId+"&optMark=Y"+"&parentModuleFlag="+'${parentModuleFlag }'
	   			    				+"&classId="+classId+"&typeId="+typeId;
	            }
	        }
	        function del()
	        {
			 var typeId = document.getElementById('typeId').value;
			 var classId = document.getElementById('classId').value;
	          if(checkDelNum())
	          {	
	          if (confirm("此操作会删除所有分类中的这条新闻,确定删除吗？")==false) return false;
	          	 showloading("<%=basePath%>commons/ajaxload.jsp",90,90); 
	          	 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=deleteNewsContentManage"+"&typeId="+typeId+"&classId="+classId+"&parentModuleFlag="+'${parentModuleFlag }';
	             newsmanageForm.submit();
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
			function changeYxdm(){
				var yxdm = document.getElementById('yxdm').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage&yxdm="+yxdm;
	            newsmanageForm.submit();
	            return false;
			}
			
			function changeClassId(){
				var yxdm = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage&yxdm="+yxdm+"&classId="+classId;
	            newsmanageForm.submit();
	            return false;
			}
			function changeTypeId(){
				var yxdm    = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				var typeId  = document.getElementById('typeId').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage&yxdm="+yxdm+"&classId="+classId+"&typeId="+typeId;
	            newsmanageForm.submit();
	            return false;
			}
			/*Add the generate select item to  html file
			 *add by steven 2009-11-27 11:35 
			 */
			function generateSelectItem(){
			var classId = document.getElementById('classId').value;
				if(checkDelNum()){
					 showloading("<%=basePath%>commons/ajaxload.jsp",90,90);
					 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=generateItemToHtmlFile&classId="+classId;
	             	 newsmanageForm.submit();
				}
			}
			
			function allItem(){
					var yxdm  = document.getElementById('yxdm').value;
					 if(confirm("生成所有新闻项，需要花费较长时间，如果您只需要生成几条新闻，建议选择“生成选择项”？")) 
					{
					 showloading("<%=basePath%>commons/ajaxload.jsp",90,90); 
					 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=allItemToHtmlFile&yxdm="+yxdm;
	             	 newsmanageForm.submit();
					}
					
			}
			
		</script>
	</head>
	<body class="centerbg">
		<html:form action="view/newsmanage.do?method=findNewsContentManage"
			method="post">
			<table class="pathbg">
				<tr>
					<td>
						新闻发布管理-&gt;新闻发布-&gt;
						<span class="chuti lv">${newsItemBig.className}</span>${showMsg }
					</td>
					<td>
						<div class="right_operate">
							<img src="<%=basePath%>res/admin/img/add.gif"
								onclick="javascript:add()" style="cursor: pointer" alt="新增"
								title="新增" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/update.gif"
								onclick="javascript:edit()" style="cursor: pointer" alt="修改"
								title="修改" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/delete.gif"
								onclick="javascript:del()" style="cursor: pointer" alt="删除"
								title="删除" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/scxzx.gif"
								onclick="javascript:generateSelectItem()"
								style="cursor: pointer" alt="生成静态项" title="生成静态项" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/scsyx.gif"
								onclick="javascript:allItem()" style="cursor: pointer"
								alt="生成所有项" title="生成所有项" />
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
								<td class="chutibai tdwidth30 tdbk">
									<input type="checkbox" onclick="checkAll(this,'check');"
										id="checkall" alt="全选">
								</td>
								<td class="chutibai tdwidth45 tdbk">
									序号
								</td>

								<td class="chutibai  tdbk">
									新闻标题
								</td>
								<td class="chutibai  tdbk">
									栏目类别
								</td>
								<td class="chutibai tdwidth100 tdbk">
									审核标志
								</td>
								<td class="chutibai tdwidth100 tdbk">
									发布人
								</td>
								<td class="chutibai tdwidth45 tdbk">
									置顶
								</td>
								<td class="chutibai tdwidth160  tdbk">
									发布日期
								</td>
								<td class="chutibai tdwidth45 tdbk">
									预览
								</td>

							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth30 tdbk">
											<input type="checkbox" value="${column.newsId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											&nbsp;&nbsp;
											<a href='#'
												onclick="browse('${column.newsId }'); return false;">${column.newsTitle
												}</a>
										</td>
										<td class="tdcenter tdbk">
											<div
												title="<logic:iterate id="item" name="column" property="newsItemSmalls">&nbsp;${item.typeName }</logic:iterate>">
												<logic:iterate id="item" name="column"
													property="newsItemSmalls" length="2">${item.typeName }&nbsp;&nbsp;</logic:iterate>
												<bean:size id="smaillSize" name="column"
													property="newsItemSmalls" />
												<c:if test="${smaillSize>2}">
								               ....
								               </c:if></div>
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.checkFlag == 1}">已审核</c:if>
											<c:if
												test="${column.checkFlag == 0 || column.checkFlag == null}">未审核</c:if>
										</td>
										<td class="tdcenter tdbk">
											${column.basicPerson.personName }
										</td>
										<td class="tdcenter tdbk">
											<span style="cursor: pointer; text-decoration: underline; color: ${ column.ifTopRow ==  1 ?"red" : "blue"}"
									 onclick="updateDisplaySyn(this,'<%=basePath%>view/newsmanage.do?method=updateIsEnable&newsId=${column.newsId}')">${column.ifTopRow
												== 1 ? "是" : "否"}</span>

										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.newsDate }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.announceType == 0}">

												<a href="<%=basePath%>${column.htmlFileName}"
													target='_blank'><img
														src="<%=basePath%>res/admin/img/blue_view.gif" title="预览"
														alt="预览" />
												</a>

											</c:if>
										</td>
									</tr>

								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth30 tdbk">
											<input type="checkbox" value="${column.newsId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											&nbsp;&nbsp;
											<a href='#'
												onclick="browse('${column.newsId }'); return false;">${column.newsTitle
												}</a>
										</td>
										<td class="tdcenter tdbk">
											<div
												title="<logic:iterate id="item" name="column" property="newsItemSmalls">&nbsp;${item.typeName }</logic:iterate>">
												<logic:iterate id="item" name="column"
													property="newsItemSmalls" length="2">${item.typeName }&nbsp;&nbsp;</logic:iterate>
												<bean:size id="smaillSize" name="column"
													property="newsItemSmalls" />
												<c:if test="${smaillSize>2}">
								               ....
								               </c:if>
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.checkFlag == 1}">已审核</c:if>
											<c:if
												test="${column.checkFlag == 0 || column.checkFlag == null}">未审核</c:if>
										</td>
										<td class="tdcenter tdbk">
											${column.basicPerson.personName }
										</td>
										<td class="tdcenter tdbk">

											<span
												style="cursor: pointer; text-decoration: underline; color: ${ column.ifTopRow ==   1 ?"red" : "blue"}"
									 onclick="updateDisplaySyn(this,'<%=basePath%>view/newsmanage.do?method=updateIsEnable&newsId=${column.newsId}')">${column.ifTopRow
												== 1 ? "是" : "否"}</span>

										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.newsDate }"
												pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.announceType == 0}">

												<a href="<%=basePath%>${column.htmlFileName}"
													target='_blank'><img
														src="<%=basePath%>res/admin/img/blue_view.gif" title="预览"
														alt="预览" />
												</a>

											</c:if>
										</td>
									</tr>
								</c:if>

							</logic:iterate>
						</table>
						<input type="hidden" id="classId" name="classId" value="${newsItemBig.classId}" />
						<input type="hidden" id="yxdm" name="yxdm" value="${yxdm}" />
						<input type="hidden" id="typeId" name="typeId" value="${typeId}" />
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
