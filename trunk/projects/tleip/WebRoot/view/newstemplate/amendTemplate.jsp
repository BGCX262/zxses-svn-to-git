<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/commons/taglibs.inc"%>
<%@page import="com.lcweb.commons.GlobalConst"%>
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
		<script type="text/javascript">
 			 function check(){
			  alert('ddd');
				var templateName = document.getElementById('newsTemplate.templateName');
				 alert(templateName.value);
				if(!isEmpty(templateName.value)){
					newsTemplateForm.submit();
				}else{
					alert("模板名称不能为空!");
					return;
				}
				showloading("<%=path%>/commons/ajaxload.jsp",90,90); 
				window.location = "<%=path%>/view/newsTemplate.do?method=amendNewsTemplate";
				
	    	}
	    	function back(){
				window.location = "<%=path%>/view/newsTemplate.do?method=forwardQuery&currentPage=1";
			}
</script>
	</head>

	<body>
		<script type="text/javascript" src="./view/js/jscolor.js"></script>
		<html:form action="view/newsTemplate.do?method=amendNewsTemplate"
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
												模板管理→
												<font color="#ff6600">模板定义</font>
											</td>
											<td width="692" align="right">
												<c:if test="${optMark == 'Y'}">
													<img src="<%=basePath%>/image/icon_3.gif"
														onclick="check();" style="cursor: pointer" border="0"
														alt="确定" />
												</c:if>
												<img src="<%=basePath%>/image/icon_5.gif" onclick="back();"
													style="cursor: pointer" border="0" alt="返回" />
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
													width="100%" border="0">
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>模板名称:&nbsp;
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="${newsTemplate.templateName }" maxlength=6
																value="${templateInfo.templateName }">
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>模板背景图 :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<file:fileUploadTag action="servlet/FileUploadServlet">1</file:fileUploadTag>
															<input type='button' id='clickBtn' value='上传' />
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															网页结构 :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<select name="pageStructure"
																style="width: 150px; text-align: center">
																<c:if test="${templateInfo.templateStructure==1 }">
																	<option value="1" selected="selected">
																		结构1
																	</option>
																	<option value="2">
																		结构2
																	</option>
																	<option value="3">
																		结构3
																	</option>
																</c:if>
																<c:if test="${templateInfo.templateStructure==2 }">
																	<option value="1">
																		结构1
																	</option>
																	<option value="2" selected="selected">
																		结构2
																	</option>
																	<option value="3">
																		结构3
																	</option>
																</c:if>
																<c:if test="${templateInfo.templateStructure==3 }">
																	<option value="1">
																		结构1
																	</option>
																	<option value="2">
																		结构2
																	</option>
																	<option value="3" selected="selected">
																		结构3
																	</option>
																</c:if>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															栏目菜单样式 :
														</td>
														<td class="td_right" width="50%">
															<select name="menuStyle"
																style="width: 150px; text-align: center">
																<c:if test="${templateInfo.templateMenuStyle==1}">
																	<option value="1" selected="selected">
																		样式1
																	</option>
																	<option value="2">
																		样式2
																	</option>
																	<option value="3">
																		样式3
																	</option>
																</c:if>
																<c:if test="${templateInfo.templateMenuStyle==2}">
																	<option value="1">
																		样式1
																	</option>
																	<option value="2" selected="selected">
																		样式2
																	</option>
																	<option value="3">
																		样式3
																	</option>
																</c:if>
																<c:if test="${templateInfo.templateMenuStyle==3}">
																	<option value="1">
																		样式1
																	</option>
																	<option value="2">
																		样式2
																	</option>
																	<option value="3" selected="selected">
																		样式3
																	</option>
																</c:if>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															网站页面完整图 :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="perferImg"
																value="${newsContentManage.imgNewsAddress}" readonly
																size=40>
															<a href="/lcweb/view/newsmanage/news/up/uploadimg_2.html"
																onClick="window.open(this.href,'','width=450,top=120,left=150,height=170,statusbar=no,toolbar=no,resize=no,scrollbars=no');return false"
																title=图片上载><font color="#FF0000">点击这里上传图片</font> </a>(注:图片宽为120,高为100)
															<div align="center" style="display: none" name="imgDiv">
																<img name=faceImg
																	src="${newsContentManage.imgNewsAddress}" width="120"
																	height="100" />
															</div>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															是否选择 :
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="ifSelect" value="1"
																checked="checked" />
															是
															<input type="radio" name="ifSelect" value="0" />
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
			<table class="table" cellSpacing="1" cellPadding="0" width="100%"
				border="0">
				<tr>
					<td class="td_center">
						<b><span>模板文字样式定义</span> </b>
					</td>
				</tr>
				<tr>
					<td class="td_center">
						<table class="table" cellSpacing="1" cellPadding="0" width="50%"
							border="0" align="center">
							<tr>
								<td class="td_left">
									<span>普通文字尺寸:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="generalFontSize"
										value=${templateStyle.ordinaryWordSize } />
								</td>
								<td class="td_left">
									<span>普通文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="generalFontColor" class="color"
										value="${templateStyle.ordinaryWordColor }" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>导航栏文字尺寸:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="navFontSize"
										value="${templateStyle.guidanceWordSzie }" />
								</td>
								<td class="td_left">
									<span>导航栏文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="navFontColor" class="color"
										value="${templateStyle.guidanceWordColor }" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>有连接的文字的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="likeWordColor" class="color"
										value="${templateStyle.likeWordColor }" readonly />
								</td>
								<td class="td_left">
									<span>鼠标经过的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="mouseRollColor" class="color"
										value="${templateStyle.mouseAfterColor }" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>已经访问的连接的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="aHasedColor" class="color"
										value="${templateStyle.visitLinkColor }" readonly />
								</td>
								<td class="td_left">
									<span>活动连接的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="aActiveColor" class="color"
										value="${templateStyle.activeLinkColor }" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>表格边框颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="tBorderColor" class="color"
										value="${templateStyle.tableFrameColor }" readonly />
								</td>
								<td class="td_left">
									<span>表格标题背景颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="tTiltleBgColor"
										value="${templateStyle.tableBackgroundColor }" class="color" />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>表格标题下划线颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="tTiltleUColor" class="color"
										value="${templateStyle.tableUnderlineColor }" readonly />
								</td>
								<td class="td_left">
									<span>表格标题文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="tTitleFontColor"
										value="${templateStyle.tableWordColor }" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>表格内容背景颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="tContentBgColor"
										value="${templateStyle.tableContentBackgroundColor }"
										class="color" readonly />
								</td>
								<td class="td_left">
									<span>表格内容文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="tContentFontColor"
										value="${templateStyle.tableContentWordColor }" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>内容列表标题文字尺寸大小:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="contentTitleFontSize"
										value=${templateStyle.contentTitleSize } />
								</td>
								<td class="td_left">
									<span>内容列表标题文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="contentTitleFontColor"
										value="${templateStyle.contentTitleColor }" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>在线调查标题文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="onlineTitleFontColor"
										value="${templateStyle.onlineTitleColor }" class="color"
										readonly />
								</td>
								<td class="td_left">
								</td>
								<td class="td_right">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
