<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
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
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<script type="text/javascript">
			function add(){
				window.location = "<%=path%>/view/newstemplate/addNewsTemplate.jsp";
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
	              alert("请选择要删除的模板!");
	              return false;
	            }
	            else{
	               return true;
	            }  
	        }
	        
	        function browse(templateId)
	        { 
	        	
				 window.location ="<%=path%>/view/newsTemplate.do?method=findAmendTemplate&templateId="+ templateId+"&optMark=N";
	        }
	        
	       function edit(templateId)
	        { 
	        	var templateId = "";
	        	var templateName="";
	        	var templateNames=document.getElementsByName('templateName');
	        	var chkbs = document.getElementsByName("check");   
	            var chkNum = 0;   
	            for(i=0;i<chkbs.length;i++){
	              if(chkbs(i).checked){
	            	chkNum++;
	            	templateId = chkbs(i).value;
	            	templateName=templateNames(i).value;
	              }
	            }
	            if(chkNum<1){
	              	alert("请选择一条记录来修改!");
	              	return false;
	            }else if (chkNum > 1){
	            	alert("只能选择一条记录来修改!");
	              	return false;
	            }else{
				 	   newsTemplateForm.action=  "<%=path%>/view/newsTemplate.do?method=findAmendTemplate&templateId="+templateId+"&optMark=Y&templateName="+templateName;
	                 newsTemplateForm.submit();
	            }
	           
	        }
	        function del()
	        {
		          if(checkDelNum())
		          {
		          	if(confirm("确定进行删除?")){
			          	 newsTemplateForm.action = "<%=path%>/view/newsTemplate.do?method=removeNewsTemplate";
			             newsTemplateForm.submit();
			             return false;
		             }
		          }
		          
	        } 
		</script>
		<html:form action="view/newsTemplate.do?method=forwardQuery"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="center">
							<tr>
								<td background="<%=basePath%>/image/dh_bg.gif" width="100%"
									height="31">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%">
										<tr>
											<td width="7"></td>
											<td width="23" align="center">
												<img src="<%=basePath%>/image/dian1.gif">
											</td>
											<td width="514" height="24" class="dh1">
												模板管理→
												<font color="#ff6600">模板列表</font> &nbsp;&nbsp;&nbsp;&nbsp;
												<font color="#ff0000">${msg}</font>
											</td>
											<td width="692" align="right">
												<img src="<%=basePath%>/image/icon_1.gif" onclick="add()"
													style="cursor: pointer" border="0" alt="新增">
												&nbsp;
												<img src="<%=basePath%>/image/icon_7.gif" onclick="del()"
													style="cursor: pointer" border="0" alt="删除">
												&nbsp;
												<img src="<%=basePath%>/image/icon_6.gif" onclick="edit()"
													style="cursor: pointer" border="0" alt="修改">
												&nbsp;</td>
											<td width="12"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
													<tr class="tr_title">
														<td width="8%">
															<b>选择</b>
														</td>
														<td>
															<b>模板名称</b>
														</td>
														<td>
															<b>网页结构</b>
														</td>
														<td>
															<b>栏目菜单样式</b>
														</td>
													</tr>
													<logic:iterate id="column" name="pageList" property="list">
														<tr class="tr_1" align="center"
															onmouseover="this.style.backgroundColor='#E1F4FC';return true;"
															onmouseout="this.style.backgroundColor='#F2F9FC';">
															<td>
															<input type="hidden" name="templateId" id="templateId"> 
																<input type="checkbox" value="${column.templateId}"
																	name="check">
															</td>
															<td>
																<a href='#' onclick="browse('${column.templateId }'); return false;">${column.templateName}</a>
																<input type="hidden" name="templateName" id="templateName" value="${column.templateName }">
															</td>
															<td>
																${column.templateStructure }
															</td>
															<td>
																${column.templateMenuStyle }
															</td>
														</tr>
													</logic:iterate>

													<tr class="page">
														<td align="center">
															全选
															<input type="checkbox" onclick="checkAll(this,'check');"
																id="checkall" alt="全选">
														</td>
														<td colspan="6" align="right">
															${pageList.view }
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
