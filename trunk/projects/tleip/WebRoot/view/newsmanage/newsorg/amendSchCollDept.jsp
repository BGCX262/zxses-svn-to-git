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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>信息发布管理系统</title>
		<script language="JavaScript" type="text/JavaScript"> 
		
			function check(){
			
				var yxdm = document.getElementById("yxdm");
				var yxmc = document.getElementById("yxmc");

				if ( checkNull2(yxdm,"系院编号不能为空!") == false  ){
					yxdm.focus();
					return;
				}
				if ( checkNull2(yxmc,"系院名称不能为空!") == false  ){
					yxmc.focus();
					return;
				}
				document.forms[0].submit();
	    	}
	    	
	    	function back(){
	    		var xxdm = document.getElementById("xxdm").value;
			 	window.location = "<%=path%>/view/newsorg.do?method=findSchCollegeDepartment&currentPage=1&xxdm="+xxdm;
			 }
			function chagneDate(obj){
				var value = obj.value;
				alert(value);
			}
		</script>
	</head>
	<body>
		<html:form action="view/newsorg.do?method=amendNewsCollege"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td background="<%=basePath%>/image/dh_bg.gif" width="100%"
									height="31">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%">
										<tr>
											<td width="10" align="center">
												<img src="<%=basePath%>/image/dian1.gif">
											</td>
											<td width="514" height="24" class="dh1">
												院系中心维护→ <font color="#ff6600">院系中心管理</font>
												&nbsp;&nbsp;&nbsp;
												${showMsg}
											</td>
											<td width="692" align="right">
											 <img src="<%=basePath%>/image/icon_3.gif" onclick="return check()" style="cursor:pointer"  border="0" alt="确定">
											 <img src="<%=basePath%>/image/icon_5.gif" onclick="return back();" style="cursor:pointer" border="0"  alt="返回" > 
											</td>		
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
													width="100%" border="0" height="151">

													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>院系所部中心号:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="hidden" name="xxdm" size =5 maxlength=5 value="${xxdm}">
															<input type="text" name="yxdm" size =5 maxlength=5 value="${yxdm}" readonly>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>院系所部中心名称 :&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="yxmc" maxlength=60 size =30 value=${schCollDept.yxmc }>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															院系所部中心英文名称:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="yxsywmc" name="yxsywmc" maxlength=180 size =30 value=${schCollDept.yxsywmc }>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															院系所部中心简称:
														</td>
														<td width="30%" class="td_right">
															<input type="yxsjc" name="yxsjc" value="${schCollDept.yxsjc }" maxlength=20 size =20>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															办别码:
														</td>
														<td width="30%" class="td_right">
															<select name="yxsbbm" style="width: 150px; text-align: center" >
																<option value="1"> test </option>
															</select>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															类别码 :
														</td>
														<td width="30%" class="td_right">
															<select name="yxslbm" style="width: 150px; text-align: center" >
																<option value="1"> test </option>
															</select>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															行政负责人 :
														</td>
														<td width="30%" class="td_right">
															<input type="xzfzr" name="xzfzr" value=${schCollDept.xzfzr }>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															党务负责人 :
														</td>
														<td width="30%" class="td_right">
															<input type="dwfzr" name="dwfzr"  value=${schCollDept.dwfzr }>
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															建立年月 :
														</td>
														<td width="30%" class="td_right">
															<input type="jlny" name="jlny"  readonly size = 6 value=${schCollDept.jlny }>
															<img style="cursor:pointer" src="<%=basePath%>/image/Calendar.gif" onclick="calendarByformat('jlny',1)">
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







