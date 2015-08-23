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
		<script language="javascript" type="text/javascript"> 
		
			function check(){
				var iLen = newsmanageForm.allClassIdOrderSelect.length;
				if (iLen > 0 ){
				    showloading("<%=basePath%>commons/ajaxload.jsp",200,200); 
					newsmanageForm.classIdOrderSelectValues.value = getAllSelectedValues(newsmanageForm.allClassIdOrderSelect,"-");
					document.forms[0].submit();
				}
	    	}
	    	
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
	    		var ifIndex = document.getElementById("ifIndex").value;
	    		if (ifIndex == 1){
	    			window.location = "<%=basePath%>view/newsmanage.do?method=findFirstPage&currentPage=1&yxdm="+yxdm;
	    		}else{
			 		window.location = "<%=basePath%>view/newsmanage.do?method=findNewsItemBig&currentPage=1&yxdm="+yxdm;
			 	}
			}
			function changeType(obj){
	    		var yxdm = obj.value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findOrderItemBig&yxdm="+yxdm;
	            newsmanageForm.submit();
	            return false;
			}
		</script>
	</head>
	<body>
		<html:form action="view/newsmanage.do?method=updateOrderItemBig"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td  align="left" valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td  align="left" valign="top">
									<table class="pathbg">
										<tr>
											<td  height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">大类排序</span>${showMsg }
											</td>
											<td align="right">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:check();" value="保存" />
												&nbsp;
												<input name="button2" type="button" class="an" id="button2"
													value="返回" onclick="javascript:back();" />
											</td>
											<td width="30"></td>	
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellSpacing="0" cellPadding="0" border="0" width="100%" align="center">
										<tr>
											<td height="5"></td>
										</tr>
										<tr>
									        <td class="tdcenter">
										        所属单位：
												<select name="yxdm" style="width: 170px; text-align: left;" onchange="changeType(this)">
													${allYxdmSelect }
												</select>
									          </td>
									      </tr>
										<tr>
											<td height="5"></td>
										</tr>
										<tr>
											<td align="center">
												<table class="table" cellSpacing="1" cellPadding="0" width="60%" border="0" height="151">
													<tr>
														<td width="25%" class="td_center">
															<table  height="151">
																<tr height="25%" ><td>大</td></tr>
																<tr height="25%" ><td>类</td></tr>
																<tr height="25%" ><td>排</td></tr>
																<tr height="25%" ><td>序</td></tr>															
															</table>
														</td>
														<td width="50%" class="td_center" valign="top">
															<select name="allClassIdOrderSelect" style="width: 200px;height:250px; text-align: center"  multiple >
																	${allClassIdOrderSelect }
															</select>
														</td>
														<td width="25%" class="td_right">
															<table  height="151">
																<tr height="25%" ><td>
																	<img src="<%=basePath%>res/admin/img/order_top.gif" onclick="javascript:MoveFirst(newsmanageForm.allClassIdOrderSelect)" style="cursor:pointer"  border="0" alt="顶部">
															    </td></tr>
																<tr height="25%" ><td>
																	<img src="<%=basePath%>res/admin/img/order_up.gif" onclick="javascript:MoveUp(newsmanageForm.allClassIdOrderSelect)" style="cursor:pointer"  border="0" alt="上移">
															    </td></tr>
															    <tr height="25%" ><td>
																	<img src="<%=basePath%>res/admin/img/order_next.gif" onclick="javascript:MoveDown(newsmanageForm.allClassIdOrderSelect)" style="cursor:pointer"  border="0" alt="下移">
															    </td></tr>
															    <tr height="25%" ><td>
																	<img src="<%=basePath%>res/admin/img/order_bottom.gif" onclick="javascript:MoveLast(newsmanageForm.allClassIdOrderSelect)" style="cursor:pointer"  border="0" alt="底部">
															    </td></tr>															
															</table>
													<input type="hidden" name="classId" size =6 maxlength=6 value="${classId}"/>
													<input type="hidden" name="yxdm" value="${yxdm }" />
													<input type="hidden" name="ifIndex" value="${ifIndex }" />
													<input type="hidden" name="classIdOrderSelectValues" value="" />
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
<script language="javascript" type="text/javascript"> 
	selectDefaultFirst(newsmanageForm.allClassIdOrderSelect);
</script>




