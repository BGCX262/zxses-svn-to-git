<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/page" prefix="page"%>
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
		<title>深圳市南山区塘朗小学-${itemBig.className }</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="深圳,南山,塘朗,小学" />
		<meta http-equiv="description" content="深圳市南山区塘朗小学" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/client/css/css.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/client/css/page.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/client/css/skin.css" />
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/client/js/js_whol.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>res/client/js/jquery-1.4.3.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>res/client/js/jquery.jcarousel.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>res/client/js/util.js"></script>
	</head>
	<body>
		<div id="warp">
			<!--head-->
			<div class="header">
				<div id="warp">
					<div id="head">
						<bean:include id="queryMenu"
							page="/client/newsClient.do?method=queryMenu" />
						<bean:write name="queryMenu" filter="false" />
						<div style="text-align: center;">
							<img align="middle"
								src="<%=basePath%>res/client/images/second_list.jpg" width="970"
								height="204" />
						</div>
					</div>
				</div>
			</div>
			<!--end-->
			<!--web-->
			<div id="web">
				<div class="page_line">
					首页&gt;&gt;${itemBig.className}
					&gt;&gt;${itemSmall.typeName }
				</div>
				<div id="left" class="fl">
					<!-- 左列 -->
					<bean:include id="queryLeft"
						page="/client/newsClient.do?method=queryLeft&classId=${classId}" />
					<bean:write name="queryLeft" filter="false" />
				</div>
				<div id="right" class="fr">
					<div class="art_title">
						<span>${itemSmall.typeName }</span>
					</div>
					<div style="text-align: center;">
							<img align="middle"
								src="<%=basePath%>res/client/images/second_top.jpg" />
					</div>
					<div class="list_top">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新闻列表
					</div>
					<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<logic:iterate id="newsContentManage" name="newsContentList"
										property="list">
										<c:set var="newsConfig"
											value="${newsItemConfig.moreTitleCount}"></c:set>
										<tr>
											<td class="border_dashed">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="2%" valign="middle">
															<img src="<%=basePath%>res/client/images/icon_01.gif"
																width="5" height="5" />
														</td>
														<td width="85%">
															<c:if test="${newsContentManage.announceType==0}">
																<c:choose>
																	<c:when test="${newsContentManage.htmlFileName!=null}">
																		<c:if test="${newsItemConfig.ifPopWindow =='1'}">
																			<a
																				href="<%=basePath%>${newsContentManage.htmlFileName}"
																				class="black" target='_blank'>
																				${fn:substring(newsContentManage.newsTitle, 0,
																				newsConfig)} <c:if
																					test="${fn:length(newsContentManage.newsTitle)>newsConfig}">...</c:if>
																			</a>
																		</c:if>
																		<c:if test="${newsItemConfig.ifPopWindow !='1'}">
																			<a
																				<c:if test="${newsItemConfig.ifPopWindow=='1' }">target="_blank"</c:if>
																				href="<%=basePath%>${newsContentManage.htmlFileName}"
																				class="black">
																				${fn:substring(newsContentManage.newsTitle, 0,
																				newsConfig)} <c:if
																					test="${fn:length(newsContentManage.newsTitle)>newsConfig}">...</c:if>
																			</a>
																		</c:if>
																	</c:when>
																</c:choose>
															</c:if>
															<c:if test="${newsContentManage.announceType==1}">
																<a href='${newsContentManage.httpUrl}' class="black"
																	<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>>
																	${fn:substring(newsContentManage.newsTitle, 0,
																	newsConfig)} <c:if
																		test="${fn:length(newsContentManage.newsTitle)>newsConfig}">...</c:if>
																</a>
															</c:if>
															<c:if test="${newsContentManage.announceType==2}">
																<a href='<%=basePath%>${newsContentManage.annexAddress}' class="black"
																	<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>>
																	${fn:substring(newsContentManage.newsTitle, 0,
																	newsConfig)} <c:if
																		test="${fn:length(newsContentManage.newsTitle)>newsConfig}">...</c:if>
																</a>
															</c:if>
														</td>
														<td width="13%" class="grey_zi">
															<bean:write name="newsContentManage" property="newsDate"
																format="yyyy-MM-dd" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>

						<tr>
							<td align="left" style="line-height: 25px; color: #48698a">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<bean:size id="size" name="newsContentList" property="list" />
									<c:if test="${size>0}">
										<page:pageTag page="${newsContentList}" tableSize="800">1</page:pageTag>
									</c:if>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="clear"></div>
			</div>
			<!--end-->

			<!--foot-->
			<div class="footer">
				<bean:include id="queryFootInfo"
					page="/client/newsClient.do?method=queryFootInfo" />
				<bean:write name="queryFootInfo" filter="false" />
			</div>
			<!--end-->
			<!--xiao_xun-->
			<div id=sidenav style="POSITION: absolute; top: 100px; right: 0;">
				<a href="<%=basePath%>"><img src="<%=basePath%>res/client/images/xiaoxun.gif" /></a>
			</div>
	</body>
</html>