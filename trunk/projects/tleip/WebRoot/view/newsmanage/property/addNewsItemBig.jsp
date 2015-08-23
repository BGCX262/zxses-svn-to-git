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
			href="<%=basePath%>res/theme/blue/css/css.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css" />	
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/move.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/calendar.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script language="javascript" type="text/javascript"> 
		
			function check(){
				var orderId = document.getElementById("orderId");
				var className = document.getElementById("className");
				var yxdm = document.getElementById("yxdm");
				var classId = document.getElementById("classId");
				
				if ( checkNull2(className,"大类名称不能为空!") == false  ){
					classId.focus();
					return;
				}
				if ( checkNull2(orderId,"显示顺序不能为空!") == false  ){
					orderId.focus();
					return;
				}				
				if ( checkNumber("orderId",orderId.value)== false ){
					orderId.focus();
					return;
				}
				if ( checkNull2(yxdm,"院系所部中心号不能为空！") == false  ){
					yxdm.focus();
					return;
				}
				showloading("<%=basePath%>commons/ajaxload.jsp",200,200); 
				document.forms[0].submit();
	    	}
	    	
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
			    window.location = "<%=basePath%>view/newsmanage.do?method=findNewsItemBig&currentPage=1&yxdm="+yxdm;
			 }
		</script>
	</head>
	<body>
		<html:form action="view/newsmanage.do?method=addNewsItemBig"
			method="post">
			 <input type="hidden" name="ifIndex" id="ifIndex" value="${ifIndex }" />
			<table cellspacing="0" cellpadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellspacing="0" cellpadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td  align="left" valign="top">
									<table class="pathbg">
										<tr>
											<td  height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">大类新增</span>${showMsg }
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
									<table cellpacing="0" cellpadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<table class="table" cellspacing="1" cellpadding="0"
													width="100%" border="0" height="151">

													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>大类编号：

														</td>
														<td width="30%" class="td_right">
															<input type="text" name="classId" size =6 maxlength=6 value="${classId}" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>大类名称：
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="className" id="className" value="${className}" size="50" maxlength=50 />
															<input type="hidden" name="mondeId" value="${mondeId}" id="mondeId" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>显示顺序：
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="orderId" id="orderId" value="${orderId }" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>是否显示：
														</td>
														<td width="30%" class="td_right">
															<input type="radio" name="ifDisplay" value="1"
																checked="checked" />
															是
															<input type="radio" name="ifDisplay" value="0" />
															否
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>是否首页显示：
														</td>
														<td width="30%" class="td_right">
															<input type="radio" name="ifIndex" value="1"
																checked="checked" />
															是
															<input type="radio" name="ifIndex" value="0" />
															否
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>学校名称：

														</td>
														<td width="30%" class="td_right">
															<input type="hidden" name="yxdm" id="yxdm" value="${schCollDept.yxdm}" />
															${schCollDept.yxmc}
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







