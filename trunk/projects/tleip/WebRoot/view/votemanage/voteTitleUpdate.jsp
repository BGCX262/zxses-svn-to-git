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
		<title>投票主题修改</title>
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
		<style>
			//标识样式--用户删除按钮
			.removeButton{
			
			}
		</style>
		<script type="text/javascript">
		function back(){                 
		    window.location.href="<%=basePath%>view/votemanage.do?method=queryVoteTitle";
		}
		</script>
	</head>
	<script type="text/javascript" src="<%=basePath%>view/votemanage/jsfiles/voteTitleUpdate.js"></script>
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
											<span class="lv chuti">投票主题修改</span>${showMsg }
										</td>
										<td align="right">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:add();" value="确定" />
											&nbsp;
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
											<html:form action="view/votemanage.do?method=updateVoteTitle"
												method="post">
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" align="center">
													<tr>
														<td class="td_left" width="40%">
															<font color="#ff0000">* </font>投票主题名称
															<input type="hidden" id="voteId" name="voteId" value="${voteTitleInfo.voteId}"/>
															<!-- 记录投票主题选线的个数 -->
															<input type="hidden" id="itemsCount" value="${fn:length(voteTitleInfo.voteItemses)}"/>
														</td>
														<td class="td_right" width="60%">
															<input type="text" name="voteName" readonly="readonly" value="${voteTitleInfo.voteName }" id="voteName" size="30" maxlength="30" />
														</td>
													</tr>
													<tr>
														<td class="td_left" width="40%">
															<font color="#ff0000">* </font>投票类型 
														</td>
														<td class="td_right" width="60%">
															<select name="voteType" id="voteType">
																<option value="1"${voteTitleInfo.voteType=="1"?" selected=\"selected\"":"" }>单选</option>
																<option value="2"${voteTitleInfo.voteType=="2"?" selected=\"selected\"":"" }>多选</option>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="40%">
															<font color="#ff0000">* </font>投票选项
														</td>
														<td class="td_right" width="60%">
															<div id="itemArea">
																<c:forEach items="${voteTitleInfo.voteItemses}" var="voteItem" varStatus="status">
																	<c:choose>
																		<c:when test="${status.index == 0}">
																			<div id="item1" style="display:inline;"><span>选项${ status.index + 1}：</span><input type="text" name="itemName" value="${voteItem.itemName }"/><input type="button" value="新增选项" onclick="addItem();" /><br/></div>
																		</c:when>
																		<c:when test="${status.last == true}">
																			<div style="display:inline;" id="item${status.index + 1}"><span>选项${ status.index + 1}：</span><input type="text" name="itemName" value="${voteItem.itemName }"/><input id="itemButton${ status.index + 1}" class="removeButton" type="button" onclick="removeItem('${ status.index + 1}')" value="删除选项"/><br/></div>
																		</c:when>
																		<c:otherwise>
																			<div style="display:inline;" id="item${status.index + 1}"><span>选项${ status.index + 1}：</span><input type="text" name="itemName" value="${voteItem.itemName }"/><input id="itemButton${ status.index + 1}" class="removeButton" type="button" onclick="removeItem('${ status.index + 1}')" style="display: none;" value="删除选项"/><br/></div>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</div>
														</td>
													</tr>
												</table>
											</html:form>
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
