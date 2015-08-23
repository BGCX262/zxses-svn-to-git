<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
				var iLen = newsmanageForm.allSmallOrderSelect.length;
				if (iLen > 0 ){
				    showloading("<%=basePath%>commons/ajaxload.jsp",200,200); 
					newsmanageForm.smallOrderSelectValues.value = getAllSelectedValues(newsmanageForm.allSmallOrderSelect,"-");
					document.forms[0].submit();
				}
	    	}
	    	
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
	    		var ifIndex = document.getElementById("ifIndex").value;
	    		var classId = document.getElementById("classId").value;
	    		if (ifIndex == 1){
	    			window.location = "<%=basePath%>view/newsmanage.do?method=findSmallFirstPage&currentPage=1&yxdm="+yxdm+"&classId="+classId;
	    		}else{
			 		window.location = "<%=basePath%>view/newsmanage.do?method=findNewsItemSmall&currentPage=1&yxdm="+yxdm+"&classId="+classId;
			 	}
			}
			function changeType(){
	    		var yxdm = document.getElementById("yxdm").value;
				var classId = document.getElementById("classId").value;
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findOrderItemSmall&yxdm="+yxdm+"&classId="+classId;
	            newsmanageForm.submit();
			}
		</script>
	</head>
	<body>
		<html:form action="view/newsmanage.do?method=updateOrderItemSmall"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td  align="left" valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td>
									<table class="pathbg">
										<tr>
											<td height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">小类排序</span>${showMsg }
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
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td height="5"></td>
										</tr>
										<tr>
											<td class="tdcenter">
												所属大类型:
												<select name="classId"
													style="width: 150px; text-align: center"
													onchange="changeType()" id="classId">
													${allClassIdOrderSelect }
												</select>
											</td>
										</tr>
									</table>
							<tr>
								<td height="5"></td>
							</tr>
							<tr>
								<td align="center">
									<table class="table" cellSpacing="1" cellPadding="0"
										width="60%" border="0" height="151">
										<tr>
											<td width="25%" class="td_center">
												<table height="151">
													<tr height="25%">
														<td>
															小
														</td>
													</tr>
													<tr height="25%">
														<td>
															类
														</td>
													</tr>
													<tr height="25%">
														<td>
															排
														</td>
													</tr>
													<tr height="25%">
														<td>
															序
														</td>
													</tr>
												</table>
											</td>
											<td width="50%" class="td_center" valign="top">
												<select name="allSmallOrderSelect" id="allSmallOrderSelect"
													style="width: 200px; height: 250px; text-align: center"
													multiple>
													${allSmallOrderSelect }
												</select>
											</td>
											<td width="25%" class="td_right">
												<table height="151">
													<tr height="25%">
														<td>
															<img src="<%=basePath%>res/admin/img/order_top.gif"
																onclick="javascript:MoveFirst(newsmanageForm.allSmallOrderSelect)"
																style="cursor: pointer" border="0" alt="最上">
														</td>
													</tr>
													<tr height="25%">
														<td>
															<img src="<%=basePath%>res/admin/img/order_up.gif"
																onclick="javascript:MoveUp(newsmanageForm.allSmallOrderSelect)"
																style="cursor: pointer" border="0" alt="上移">
														</td>
													</tr>
													<tr height="25%">
														<td>
															<img src="<%=basePath%>res/admin/img/order_next.gif"
																onclick="javascript:MoveDown(newsmanageForm.allSmallOrderSelect)"
																style="cursor: pointer" border="0" alt="下移">
														</td>
													</tr>
													<tr height="25%">
														<td>
															<img src="<%=basePath%>res/admin/img/order_bottom.gif"
																onclick="javascript:MoveLast(newsmanageForm.allSmallOrderSelect)"
																style="cursor: pointer" border="0" alt="最下">
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<input type="hidden" name="classId" id="classId"
											value="${classId}" />
										<input type="hidden" name="yxdm" value="${yxdm }" id="yxdm" />
										<input type="hidden" name="ifIndex" value="${ifIndex }" id="ifIndex" />
										<input type="hidden" name="smallOrderSelectValues" value="" id="smallOrderSelectValues" />
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
<script language="JavaScript" type="text/JavaScript"> 
	selectDefaultFirst(newsmanageForm.allSmallOrderSelect);
</script>




