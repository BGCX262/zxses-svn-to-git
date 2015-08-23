<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
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
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script type="text/javascript">
			function add(){
				var classId = document.getElementById('classId').value;
				if ( classId == 0){
					alert("请选择大类类型！");
					document.getElementById('classId').focus();
					return false;
				}
				var adTypeId = document.getElementById('adTypeId').value;
				if ( adTypeId == 0){
					alert("请选择广告类型！");
					document.getElementById('adTypeId').focus();
					return false;
				}
				var yxdm = document.getElementById('yxdm').value;
				window.location = "<%=basePath%>view/newsmanage.do?method=findAddNewsAdManage&classId="+classId+"&adTypeId="+adTypeId+"&yxdm="+yxdm;
				return false;
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
	        
	       function edit(adId)
	        { 
	        /*
	          	 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findAmendNewsAdManage&adId="+ adId;
	             newsmanageForm.submit();
	             return false;
	             */
	             window.location = "<%=basePath%>view/newsmanage.do?method=findAmendNewsAdManage&adId="+ adId;
	        }
	        function browse(adId)
	        { 
				 window.location = "<%=basePath%>view/newsmanage.do?method=findAmendNewsAdManage&adId="+ adId+"&optMark=N";
	        }
	        function edit(){
	        	var adId = "";
	        	var chkbs = document.getElementsByName("check");   
	            var chkNum = 0;   
	            for(i=0;i<chkbs.length;i++){
	              if(chkbs(i).checked){
	            	chkNum++;
	            	adId = chkbs(i).value;
	              }
	            }
	            if(chkNum<1){
	              	alert("请选择一条记录来修改!");
	              	return false;
	            }else if (chkNum > 1){
	            	alert("只能选择一条记录来修改!");
	              	return false;
	            }else{
	 				 window.location = "<%=basePath%>view/newsmanage.do?method=findAmendNewsAdManage&yxdm=${yxdm}&adId="+ adId+"&optMark=Y";
	            }
	        }
	        function del()
	        {
	          if(checkDelNum())
	          {	if (confirm("确定删除这些记录吗？")==false) return false;
	             showloading("<%=basePath%>commons/ajaxload.jsp",90,90); 
	          	 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=deleteNewsAdManage";
	             newsmanageForm.submit();
	             Form.disable($("newsmanageForm"));
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
			function changeYxdm(){
				var yxdm = document.getElementById('yxdm').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsAdManage&yxdm="+yxdm;
	            newsmanageForm.submit();
	            return false;
			}
			
			function changeClassId(){
				var yxdm = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsAdManage&yxdm="+yxdm+"&classId="+classId;
	            newsmanageForm.submit();
	            return false;
			}
			function changeAdType(){
				var yxdm = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				var adTypeId = document.getElementById('adTypeId').value;
				
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsAdManage&yxdm="+yxdm+"&classId="+classId+"&adTypeId="+adTypeId;
	            newsmanageForm.submit();
	            return false;
			}
		</script>
	</head>

	<body class="centerbg">
		<html:form action="view/newsmanage.do?method=findNewsAdManage"
			method="post">
			<table class="pathbg">
				<tr align="left">
					<td>
						新闻广告类型管理-&gt;
						<span class="chuti lv">广告管理</span>${showMsg }
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
						所属单位：
						<select name="yxdm" style="width: 170px; text-align: left;"
							onchange="changeYxdm()" id="yxdm">
							${allYxdmSelect }
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp; 大类：
						<select name="classId" style="width: 150px; text-align: center"
							onchange="changeClassId()" id="classId">
							${allClassIdSelect }
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp; 广告类型：
						<select name="adTypeId" style="width: 150px; text-align: center"
							onchange="changeAdType()"  id="adTypeId">
							${allAdTypeSelect }
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
									名称
								</td>
								<td class="chutibai tdbk">
									类型
								</td>
								<td class="chutibai tdbk">
									显示位置
								</td>
								<td class="chutibai tdbk">
									图片URL
								</td>
								<td class="chutibai tdbk">
									链接URL
								</td>
								<td class="chutibai tdbk">
									发布人
								</td>
								<td class="chutibai tdbk">
									发布日期
								</td>
								<td class="chutibai tdbk">
									有效期
								</td>
								<td class="chutibai tdbk">
									是否显示
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.adId}" name="check" id="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											&nbsp;&nbsp;
											<a href='#'
												onclick="browse('${column.adId }'); return false;">${column.adName
												}</a>
										</td>
										<td class="tdcenter tdbk">
											${column.newsAdType.adTypeName}
										</td>
										<td class="tdcenter tdbk">
											${column.adPosition }
										</td>
										<td class="tdcenter tdbk">
											${column.adImgUrl }
										</td>
										<td class="tdcenter tdbk">
											${column.adHttpUrl }
										</td>
										<td class="tdcenter tdbk">
											${column.personCode }
										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.adAnnounceDate }"
												pattern="yyyy-MM-dd" />
										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.adValidityDate }"
												pattern="yyyy-MM-dd" />
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.ifDisplay == 0}">否</c:if>
											<c:if test="${column.ifDisplay == 1}">是</c:if>
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.adId}" name="check" id="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											&nbsp;&nbsp;
											<a href='#'
												onclick="browse('${column.adId }'); return false;">${column.adName
												}</a>
										</td>
										<td class="tdcenter tdbk">
											${column.newsAdType.adTypeName}
										</td>
										<td class="tdcenter tdbk">
											${column.adPosition }
										</td>
										<td class="tdcenter tdbk">
											${column.adImgUrl }
										</td>
										<td class="tdcenter tdbk">
											${column.adHttpUrl }
										</td>
										<td class="tdcenter tdbk">
											${column.personCode }
										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.adAnnounceDate }"
												pattern="yyyy-MM-dd" />
										</td>
										<td class="tdcenter tdbk">
											<fmt:formatDate value="${column.adValidityDate }"
												pattern="yyyy-MM-dd" />
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.ifDisplay == 0}">否</c:if>
											<c:if test="${column.ifDisplay == 1}">是</c:if>
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
