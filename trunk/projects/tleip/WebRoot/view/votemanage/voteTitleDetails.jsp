<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>投票主题详情</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css">
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.form.js"></script>
		<script type="text/javascript">
			function back(){                 
			    window.location.href="<%=basePath%>view/votemanage.do?method=queryVoteTitle";
			}
		</script>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>client/index/content/vote/app.platform.vote.v3.css" />
	</head>
	<body>

		<table cellSpacing="0" cellPadding="0" width="100%" border="0"
			align="center">
			<tr>
				<td valign="top">
					<table cellSpacing="0" cellPadding="0" border="0" width="100%"
						align="center">
						<tr>
							<td>
								<table class="pathbg">
									<tr>
										<td width="514" height="24">
											会员管理-&gt;投票管理-&gt;
											<span class="lv chuti">投票主题详情</span>${showMsg }
										</td>
										<td align="right">
											<input name="button2" type="button" class="an" id="button2"
												value="返回" onclick="javascript:back();" />
										</td>
										<td width="15"></td>
									</tr>
								</table>
							</td>
						</tr>

						<tr>
							<td height="20"></td>
						</tr>

						<tr>
							<td>
								<table cellSpacing="0" cellPadding="0" border="0" width="97%"
									align="center">
									<tr>
										<td>
											<table class="table" cellSpacing="1" cellPadding="0"
												width="100%" border="0" align="center">
												<tr>
													<td class="td_left" width="90%">
														<table width="100%" border="0" align="center">
															<tr>
																<td style=" height:40px;" align="center"
																	valign="bottom">
																	<div id="content" class="" style="margin-right: 20px;">
																		<div id="body">
																			<table width="100%" align="center" cellspacing="0" cellpadding="0" border="0" style="visibility: visible; " id="hot">
																				<tbody>
																					<tr>
																						<td>&nbsp;</td>
																						<td width="50" id="border"><nobr><b>人气值：</b><span id="hot_value"><fmt:formatNumber value="${voteTitle.voteHot }" pattern="#,##0" type="number"/></span></nobr></td>
																					</tr>
																				</tbody>
																			</table>
																			<div id="main">
																				<div id="question_361707" class="question result">
																					<div class="title f14px">
																						<button class="icon_vote">&nbsp;</button>${voteTitle.voteName } 
																					</div>
																					<div class="content line">
																					<div class="nodetail"></div>
																					<table width="100%" align="center" cellspacing="0" cellpadding="0" border="0">
																						<tbody>
																							<c:set var="colorCount" value="0" scope="page"/>
																							<c:forEach items="${voteTitle.voteItemses}" var="items" varStatus="status">
																								<c:if test="${colorCount >= 9}">
																									<c:set var="colorCount" value="0" scope="page"/>
																								</c:if>
																								<tr>
																									<td width="430" style="padding-right:20px;">
																										<label><input${voteTitle.voteType=="1"?" type=\"radio\"":" type=\"checkbox\"" }" onclick="" value="${items.itemId}" disabled="disabled" name="itemsIds"/>
																										<span class="option">${items.itemName }</span></label>
																									</td>
																									<td width="170">
																										<div id="result_bar_361707_25592" style="display: block; " class="process">
																										<div style="width: ${items.itemBallot * 170/ totalCount }px;" class="style${colorCount }" id="process_bar_361707_25592"></div></div>
																									</td>
																									<td width="110" id="process_txt_361707_25592" style="display: block; " class="black">
																										<nobr><fmt:formatNumber value="${items.itemBallot }" pattern="#,##0" type="number"/>票 (占<fmt:formatNumber value="${items.itemBallot * 100 / totalCount}" pattern="0.00" type="number"/>%)</nobr>
																									</td>
																								</tr>
																								<c:set var="colorCount" value="${colorCount + 1}" scope="page"/>
																							</c:forEach>
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																	<br />
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

	</body>
</html>
