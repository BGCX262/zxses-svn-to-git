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
				var yxdm = document.getElementById('yxdm').value;
				if ( classId == 0){
					alert("请选择大类！");
					document.getElementById('classId').focus();
					return false;
				}
				window.location = "<%=basePath%>view/newsmanage.do?method=findAddNewsAdType&classId="+classId+"&yxdm="+yxdm;
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
	        
	        function browse(adTypeId)
	        { 		
	        	 var yxdm = document.getElementById('yxdm').value;
				 window.location = "<%=basePath%>view/newsmanage.do?method=findAmendNewsAdType&adTypeId="+ adTypeId+"&optMark=N"+"&yxdm="+yxdm;
	        }
	        function edit(){
	        	var adTypeId = "";
	        	var yxdm = document.getElementById('yxdm').value;
	        	var chkbs = document.getElementsByName("check");   
	            var chkNum = 0;   
	            for(i=0;i<chkbs.length;i++){
	              if(chkbs(i).checked){
	            	chkNum++;
	            	adTypeId = chkbs(i).value;
	              }
	            }
	            if(chkNum<1){
	              	alert("请选择一条记录来修改!");
	              	return false;
	            }else if (chkNum > 1){
	            	alert("只能选择一条记录来修改!");
	              	return false;
	            }else{
	 				 window.location = "<%=basePath%>view/newsmanage.do?method=findAmendNewsAdType&adTypeId="+ adTypeId+"&optMark=Y"+"&yxdm="+yxdm;
	            }
	        }	        
	        function del()
	        {
	          if(checkDelNum())
	          {	if (confirm("确定删除这些记录吗？")==false) return false;
	             showloading("<%=basePath%>commons/ajaxload.jsp",90,90);  
	          	 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=deleteNewsAdType";
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
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsAdType&yxdm="+yxdm;
	            newsmanageForm.submit();
	            return false;
			}
			
			function changeClassId(){
				var yxdm = document.getElementById('yxdm').value;
				var classId = document.getElementById('classId').value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsAdType&yxdm="+yxdm+"&classId="+classId;
	            newsmanageForm.submit();
	            return false;
			}
		</script>
	</head>

	<body class="centerbg">
		<html:form action="view/newsmanage.do?method=findNewsAdType"
			method="post">
			<table class="pathbg">
				<tr align="left">
					<td>
						新闻广告类型管理-&gt;
						<span class="chuti lv">广告类型</span>${showMsg }
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
									类型名称
								</td>
								<td class="chutibai tdbk">
									种类
								</td>
								<td class="chutibai tdbk">
									高度
								</td>
								<td class="chutibai tdbk">
									宽度
								</td>
							</tr>

							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.adTypeId}"
												name="check" id="checkbox">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											&nbsp;&nbsp;
											<a href='#'
												onclick="browse('${column.adTypeId }'); return false;">${column.adTypeName
												}</a>
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.adTypeTag == 0}">广告</c:if>
											<c:if test="${column.adTypeTag == 1}">logo</c:if>
											<c:if test="${column.adTypeTag == 2}">banner</c:if>
										</td>
										<td class="tdcenter tdbk">
											${column.adHeight }
										</td>
										<td class="tdcenter tdbk">
											${column.adWidth }
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdwidth45 tdbk">
											<input type="checkbox" value="${column.adTypeId}"
												name="check" id="checkbox"> 
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											&nbsp;&nbsp;
											<a href='#'
												onclick="browse('${column.adTypeId }'); return false;">${column.adTypeName
												}</a>
										</td>
										<td class="tdcenter tdbk">
											<c:if test="${column.adTypeTag == 0}">广告</c:if>
											<c:if test="${column.adTypeTag == 1}">logo</c:if>
											<c:if test="${column.adTypeTag == 2}">banner</c:if>
										</td>
										<td class="tdcenter tdbk">
											${column.adHeight }
										</td>
										<td class="tdcenter tdbk">
											${column.adWidth }
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