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
			
		<script type="text/javascript">
	    var GB_ROOT_DIR = "<%=basePath%>tools/greybox/";
		</script>
		<script type="text/javascript"
			src="<%=basePath%>tools/greybox/AJS.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>tools/greybox/AJS_fx.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>tools/greybox/gb_scripts.js"></script>
		<link href="<%=basePath%>tools/greybox/gb_styles.css"
			rel="stylesheet" type="text/css" />	
		<script language="javascript" type="text/javascript"> 
		
			function check(){
				var adTypeId = document.getElementById("adTypeId");
				var adName = document.getElementById("adName");
				var adPosition = document.getElementById("adPosition");
				var classId = document.getElementById("classId");
				if (adTypeId.value == null || adTypeId.value == 0 ){
					alert("请选择广告类型！");
					adTypeId.focus();
					return false;
				}
				if ( checkNull2(adName,"广告名称不能为空!") == false  ){
					adName.focus();
					return false;
				}
				showloading("<%=basePath%>commons/ajaxload.jsp",66,66); 
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=addNewsAdManage&classId="+classId.value;
	            newsmanageForm.submit();
	            //return false;
	    	}
	    	
	    	function back(){
	    		var classId = document.getElementById('classId').value;
				var adTypeId = document.getElementById('adTypeId').value;
				var yxdm = document.getElementById('yxdm').value;
			 	window.location = "<%=basePath%>view/newsmanage.do?method=findNewsAdManage&currentPage=1&classId="+classId+"&adTypeId="+adTypeId+"&yxdm="+yxdm;
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
		function view(flag){
	         showwindow("<%=basePath%>commons/uploadimg_ad.html",500,155);
       	}		 
		</script>
	</head>
	<body>
		<html:form action="view/newsmanage.do?method=addNewsAdManage"
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
												<span class="lv chuti">广告新增</span>${showMsg }
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
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>所属广告类型:&nbsp;

														</td>
														<td class="td_right" width="50%">
															<input type="hidden" name="yxdm" id="yxdm"
																value="${yxdm}">
															<input type="hidden" name="classId" id="classId"
																value="${classId}">
															<select name="adTypeId" id="adTypeId"
																style="width: 200px; text-align: center">
																${allAdTypeSelect}
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>广告名称 :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adName" id="adName">
														</td>
													</tr>

													<!--  
													<tr>
														<td  class="td_left" width="50%">
															广告显示位置  :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adPosition" value=1>
														</td>
													</tr>
													-->

													<tr>
														<td class="td_left" width="50%">
															广告图片URL :
														</td>
														<td class="td_right" width="50%">
															<table  cellSpacing="0" cellPadding="0" border="0">
																<tr>
																	<td class="td_right" width="50%">
																		<input type="text" name="adImgUrl" size="60" id="adImgUrl" readonly>
																		<br>
																		
																		<a href="<%=basePath%>commons/uploadimg.jsp?id=adImgUrl&faceImg=faceImg"
																			rel="gb_page[430, 200]"><font
																			color="#FF0000">点击这里上传图片</font> </a>(注:预览图片图片宽为120,高为100)
																		
																	</td>
																</tr>
																<tr>
																	<td>
																		<div align="left">
																			<img name="faceImg" id="faceImg"
																					src="<%=basePath%>res/admin/img/wu.jpg" width="120"
																					height="100">
																		</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															广告链接URL :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adHttpUrl" size=60 id="adHttpUrl" >
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															发布人 :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="personCode" id="personCode" readonly
																value="${logininfo.personAccount }">
														</td>
													</tr>
													<!-- 
													<tr>
														<td class="td_left" width="50%">
															发布日期  :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adAnnounceDate" readonly=true>
															<img style="cursor:pointer" src="<%=basePath%>/image/Calendar.gif" onclick="calendar('adAnnounceDate')">
														</td>
													</tr> 
													<tr>
														<td class="td_left" width="50%">
															有效期  :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="adValidityDate" readonly=true>
															<img style="cursor:pointer" src="<%=basePath%>/image/Calendar.gif" onclick="calendar('adValidityDate')">
														</td>
													</tr>
													 -->

													<tr>
														<td class="td_left" width="50%">
															是否显示 :
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="ifDisplay" value="1" id="ifDisplay"
																checked="checked">
															是
															<input type="radio" name="ifDisplay" value="0" id="ifDisplay">
															否
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







