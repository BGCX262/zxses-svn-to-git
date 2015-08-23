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
				var classId = document.getElementById("classId");
				var adTypeName = document.getElementById("adTypeName");
				var adHeight = document.getElementById("adHeight");
				var adWidth = document.getElementById("adWidth");
				if ( checkNull2(classId,"大类编号不能为空!") == false  ){
					classId.focus();
					return false;
				}
				
				if ( checkNull2(adTypeName,"广告类型名称不能为空!") == false  ){
					adTypeName.focus();
					return false;
				}
				if (!checkNumberTemp("adHeight")){
					return false;
				}
				if ( adHeight.value == 0){
					alert("高度必须大于0，请重新输入！");
					adHeight.focus();
					return false;
				}
				if (!checkNumberTemp("adWidth") ){
					return false;
				}
				if ( adWidth.value == 0 ){
					alert("宽度大于0，请重新输入！");
					adWidth.focus();
					return false;
				}

				//document.forms[0].submit();
				showloading("<%=basePath%>commons/ajaxload.jsp",90,90);  
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=amendNewsAdType";
	            newsmanageForm.submit();
	            //return false;
	    	}
	    	
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
	    		var classId = document.getElementById("classId").value;
			 	window.location = "<%=basePath%>view/newsmanage.do?method=findNewsAdType&currentPage=1&classId="+classId+"&yxdm="+yxdm;
			 }
			 
			 function checkNumberTemp(objString){
			 	var obj = document.getElementById(objString);
				if (obj.value != ""){
					if ( checkNumber(objString,obj.value)== false ){
						obj.focus();
						return false;;
					}
				}else{
					obj.value = 0;
				}
			 	return true;
			 }
		</script>
	</head>
	<body>
		<html:form action="view/newsmanage.do?method=addNewsAdType"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td align="left" valign="top">
									<table class="pathbg">
										<tr>
											<td  height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">广告类型修改</span>${showMsg }
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
											<td>
												<table class="table" cellSpacing="1" cellPadding="0" width="100%" border="0" >
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>大类编号:&nbsp;

														</td>
														<td  class="td_right" width="50%">
															<input type="hidden" name="classId" id="classId" value="${newsAdType.newsItemBig.classId}">
															<input type="hidden" name="adTypeId" id="adTypeId" value="${newsAdType.adTypeId}">
															<input type="hidden" name="yxdm" id="yxdm" value="${yxdm}">
															<input type="hidden" name="optMark" id="optMark" value="${optMark}">
															<font color="#ff0000"><b>${newsAdType.newsItemBig.classId}&nbsp;&nbsp;[${newsAdType.newsItemBig.className}]</b></font>
														</td>
													</tr>
													<tr>
														<td  class="td_left" width="50%">
															<font color="#ff0000">* </font>广告类型名称 :&nbsp;
														</td>
														<td  class="td_right" width="50%">
															<input type="text" name="adTypeName" value="${newsAdType.adTypeName}" id="adTypeName">
														</td>
													</tr>
													<tr>
														<td  class="td_left" width="50%">
															广告种类 :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<select name="adTypeTag" style="width: 150px; text-align: center"  id="adTypeTag">
																<option value="0" <c:if test="${newsAdType.adTypeTag == 0}">selected</c:if>>广告</option>
																<option value="1" <c:if test="${newsAdType.adTypeTag == 1}">selected</c:if>>logo</option>
																<option value="2" <c:if test="${newsAdType.adTypeTag == 2}">selected</c:if>>banner</option>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															宽度 :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adWidth" maxlength="4" id="adWidth" value="${newsAdType.adWidth}"><font size="2"><em><font color="#c0c0c0">建议宽度:735</font></em></font>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															高度 :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adHeight"  maxlength="4" id="adHeight" value="${newsAdType.adHeight}">
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







