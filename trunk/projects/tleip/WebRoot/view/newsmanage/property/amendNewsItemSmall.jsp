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
				var typeName = document.getElementById("typeName");
				var orderId = document.getElementById("orderId");
				var typeId =  document.getElementById("typeId").value;
				var newFlagTime =  document.getElementById("newFlagTime").value;
				
				if ( checkNull(classId,"大类编号不能为空!") == false  ){
					classId.focus();
					return false;
				}
				if ( checkNull(typeName,"小类名称不能为空!") == false  ){
					typeName.focus();
					return ;
				}
				if (!checkNumberTemp("newFlagTime")){
					return ;
				}
				if (!checkNumberTemp("orderId")){
					return ;
				}
				
				if (!checkNumberTemp("displayRowCount")){
					return ;
				}
				
				if (!checkNumberTemp("titleCharacterCount")){
					return ;
				}

				if (!checkNumberTemp("moreTitleCount")){
					return ;
				}
				showloading("<%=basePath%>commons/ajaxload.jsp",200,200); 
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=amendNewsItemSmall&typeId="+ typeId;
	            newsmanageForm.submit();
	            return false;
	    	}
	    	
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
	    		var classId = document.getElementById("classId").value;
			 	window.location = "<%=basePath%>view/newsmanage.do?method=findNewsItemSmall&currentPage=1&yxdm="+yxdm+"&classId="+classId;
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
			/*
			 * Add by steven lee
			 * add the newsItemSmall 's announceType
			 */
			 function selectLink(obj){
				var value = obj.value;
				if (value == 1){
					document.getElementById("newsPageLink").style.display ='block';	
				}else if (value == 2){
					document.getElementById("newsPageLink").style.display ='none';	
				}else{
					document.getElementById("newsPageLink").style.display ='none';
				}
			 }
		</script>
	</head>
	<body>
		<html:form action="view/newsmanage.do?method=amendNewsItemSmall"
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
											<td  height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">小类修改</span>${showMsg }
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
															<font color="#ff0000">* </font>大类编号：

														</td>
														<td class="td_right" width="50%">
															<input type="hidden" name="yxdm" value="${newsItemBig.yxdm}" id="yxdm"/>
															<input type="hidden" name="className" value="${newsItemBig.className}" id="className"/>
															<input type="hidden" name="classId" value="${newsItemBig.classId}" id="classId"/>
															<input type="hidden" name="optMark" value="${optMark}" id="optMark"/>
															<font color="#ff0000"><b>${newsItemBig.classId}&nbsp;&nbsp;[${newsItemBig.className}]</b>
															</font>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>小类名称：
															<input type="hidden" name="maxModuelId"
																value="${maxModuelId}" id="maxModuelId"/>
															<input type="hidden" name="moduel2_int"
																value="${moduel2_int}" id="moduel2_int"/>
															<input type="hidden" name="upMondel" value="${upMondel}" id="upMondel" />
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="typeName" id="typeName" 
																value="${newsItemSmall.typeName}" size="50" maxlength=50>
															<input type="hidden" name="typeId"  id="typeId"
																value="${newsItemSmall.typeId}">
															<font color="#ff0000">${msg}</font>
														</td>
													</tr>
													<tr id="newsStyle">
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>发布方式：
														</td>
														<td class="td_right" width="50%">
															<c:if
																test="${newsItemSmall.announceType==0||newsItemSmall.announceType==null}">
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="0" checked="checked">新闻方式
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="1">地址连接
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="2">正文方式
															</c:if>
															<c:if test="${newsItemSmall.announceType==1}">
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="0">新闻方式
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="1" checked="checked">地址连接
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="2">正文方式
															</c:if>
															<c:if test="${newsItemSmall.announceType==2}">
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="0">新闻方式
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="1">地址连接
																<input type="radio" name="announceType"
																	onclick="selectLink(this);" value="2" checked="checked">正文方式
															</c:if>
														</td>
													</tr>

													<c:if test="${newsItemSmall.announceType==1}">
														<tr id="newsPageLink" style="display: block">
															<td class="td_left" width="50%">
																链接地址：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="httpUrl" size=60
																	value="${newsItemSmall.httpUrl}">
															</td>
														</tr>
													</c:if>
													<c:if test="${newsItemSmall.announceType!=1}">
														<tr id="newsPageLink" style="display: none">
															<td class="td_left" width="50%">
																链接地址：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="httpUrl" size=60>
															</td>
														</tr>
													</c:if>
													<!-- 设置地址链接式小类明细结束 -->
													<tr>
														<td class="td_left" width="50%">
															栏目排序：
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="orderId" id="orderId"
																value="${newsItemSmall.orderId}"/>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															是否显示：
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="ifDisplay" value="1"
																<c:if test="${newsItemSmall.ifDisplay == 1}">
																	checked="checked"
																</c:if>/>
															是
															<input type="radio" name="ifDisplay" value="0"
																<c:if test="${newsItemSmall.ifDisplay == 0}">
																	checked="checked"
																</c:if>/>
															否
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															审核标志：
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="checkFlag" value="1"
																<c:if test="${newsItemSmall.checkFlag == 1 || newsItemSmall.checkFlag == null }">
																		checked="checked"
																	</c:if>/>
															自动审核
															<input type="radio" name="checkFlag" value="0"
																<c:if test="${newsItemSmall.checkFlag == 0}">
																		checked="checked"
																	</c:if> />
															手工审核
														</td>
													</tr>
												</table>
												<br>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td class="td_center">
															<b>栏目显示属性配置</b>
														</td>
													</tr>

													<tr>
														<td class="td_center">
															<table class="table" cellSpacing="1" cellPadding="0"
																width="100%" border="0">
																<tr>
																	<td class="td_left" width="50%">
																		显示文章行数：
																	</td>
																	<td class="td_right" width="50%">
																		<input type="text" name="displayRowCount" id="displayRowCount"
																			value="${newsItemConfig.displayRowCount}"/>
																	</td>
																</tr>

																<tr>
																	<td class="td_left" width="50%">
																		显示标题字数：
																	</td>
																	<td class="td_right" width="50%">
																		<input type="text" name="titleCharacterCount" id="titleCharacterCount"
																			value="${newsItemConfig.titleCharacterCount}"/>
																	</td>
																</tr>
																
																<tr>
																	<td class="td_left" width="50%">
																		新闻new标记显示天数：
																	</td>
																	<td class="td_right" width="50%">
																		<input type="text" name="newFlagTime" maxlength="4" id="newFlagTime"
																			value="${newsItemConfig.newFlagTime}"/>
																	</td>
																</tr>


																<tr>
																	<td class="td_left" width="50%">
																		更多页面标题字数：
																	</td>
																	<td class="td_right" width="50%">
																		<input type="text" name="moreTitleCount" id="moreTitleCount"
																			value="${newsItemConfig.moreTitleCount}"/>
																	</td>
																</tr>

																<tr>
																	<td class="td_left" width="50%">
																		是否弹出窗口显示：
																	</td>
																	<td class="td_right" width="50%">
																		<input type="radio" name="ifPopWindow" value="1" id="ifPopWindow"
																			<c:if test="${newsItemConfig.ifPopWindow == 1}">
																				checked="checked"
																			</c:if>/>
																		是
																		<input type="radio" name="ifPopWindow" value="0" id="ifPopWindow"
																			<c:if test="${newsItemConfig.ifPopWindow == 0}">
																				checked="checked"
																			</c:if>/>
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
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
