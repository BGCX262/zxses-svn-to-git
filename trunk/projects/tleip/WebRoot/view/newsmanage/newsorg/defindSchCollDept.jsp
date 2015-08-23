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
		<script type="text/javascript">
			function add(){
				var xxdm = document.getElementById('xxdm').value;
				if ( xxdm == 0){
					alert("请选择学校！");
					document.getElementById('xxdm').focus();
					return false;
				}
				window.location = "<%=path%>/view/newsorg.do?method=findAddNewsCollege&xxdm="+xxdm;
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
	        
	       function edit(yxdm)
	        { 
	             newsOrgMangeForm.action = "<%=path%>/view/newsorg.do?method=findAddNewsCollege&yxdm="+yxdm;
	             newsOrgMangeForm.submit();
				 return true;
	        }
	        function del()
	        {
	           if(checkDelNum())
	          {   
	             if (confirm("确定删除这些记录吗？")==false) return false; 
	          	 newsOrgMangeForm.action = "<%=path%>/view/newsorg.do?method=deleteNewsCollege";
	             newsOrgMangeForm.submit();
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
			function changeType(){
				var xxdm = document.getElementById('xxdm').value;
				newsOrgMangeForm.action = "<%=path%>/view/newsorg.do?method=findSchCollegeDepartment&xxdm="+xxdm;
	            newsOrgMangeForm.submit();
	            return false;
			}

		
		</script>
	</head>

	<body>
		<html:form action="/view/newsorg.do?method=findSchCollegeDepartment"
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
												人员介绍管理→
												<font color="#ff6600">院系所部中心管理</font>&nbsp;&nbsp;&nbsp;
												${showMsg }
											</td>
											<td width="692" align="right">
												<img src="<%=basePath%>/image/icon_1.gif" onclick="add()"
													style="cursor: pointer" order="0" alt="新增">
												&nbsp;
												<img src="<%=basePath%>/image/icon_7.gif" onclick="del()"
													style="cursor: pointer" border="0" alt="删除">
												&nbsp;
											<td width="12"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellSpacing="0" cellPadding="0" border="0" width="97%"
										align="center">
										<tr>
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td class="tr_cx" colspan="4">
															学校:
															<select name="xxdm" style="width: 150px; text-align: center" onchange="changeType()">
																${allXxdmSelect }
															</select>
														</td>
														<td class="tr_cx" colspan="8"></td>
													</tr>
													<tr class="tr_title">
														<td width="8%">
															<b>选择</b>
														</td>
														<td >
															<b>院系所部中心号</b>
														</td>
														<td >
															<b>院系所部中心名称</b>
														</td>
														<td >
															<b>院系所部中心英文名称</b>
														</td>
														<td >
															<b>院系所部中心简称</b>
														</td>
														<td >
															<b>办别码</b>
														</td>
														<td >
															<b>类别码</b>
														</td>
														<td >
															<b>行政负责人</b>
														</td>
														<td >
															<b>党务负责人</b>
														</td>
													</tr>
													<logic:iterate id="column" name="pageList" property="list">
														<tr class="tr_1" align="center"
															onmouseover="this.style.backgroundColor='#E1F4FC';return true;"
															onmouseout="this.style.backgroundColor='#F2F9FC';">
															<td>
																<input type="checkbox" value="${column.yxdm}" name="check">
															</td>
															<td>
																${column.yxdm }
															</td>
															<td>
																<a href='#'  onclick ="edit('${column.yxdm }');return false; ">${column.yxmc }</a>
															</td>
															<td >
																${column.yxsywmc }
															</td>
															<td>
																${column.yxsjc }
															</td>
															<td>
																${column.yxsbbm }
															</td>
															<td>
																${column.yxslbm }
															</td>
															<td>
																${column.xzfzr }
															</td>
															<td>
																${column.dwfzr }
															</td>
														</tr>
													</logic:iterate>

													<tr class="page">
														<td align="center">
															全选
															<input type="checkbox" onclick="checkAll(this,'check');"
																id="checkall" alt="全选">
														</td>
														<td colspan="8" align="right">
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
