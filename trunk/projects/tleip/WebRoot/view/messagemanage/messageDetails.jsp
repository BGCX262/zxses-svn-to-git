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
		<title>留言详情</title>
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
		    window.location.href="<%=basePath%>view/messagemanage.do?method=queryMessage";
		}
		</script>
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
											会员管理-&gt;留言管理-&gt;
											<span class="lv chuti">留言详情</span>${showMsg }
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
											<html:form action="view/votemanage.do?method=updateVoteTitle"
												method="post">
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" align="center">
													<tr>
														<td class="td_left" width="40%">
															留言人：
														</td>
														<td class="td_right" width="60%">
															<input type="text" readonly="readonly" value="${messageInfo.frontUser.userAccount}" size="30" maxlength="30" />
														</td>
													</tr>
													<tr>
														<td class="td_left" width="40%">
															留言时间：
														</td>
														<td class="td_right" width="60%">
															<input type="text" readonly="readonly" value="${messageInfo.messageDate }" size="30" maxlength="30" />
														</td>
													</tr>
													<tr>
														<td class="td_left" width="40%">
															留言内容：
														</td>
														<td class="td_right" width="60%">
															<textarea id="content" name="messageContent" rows="10" cols="45" readonly="readonly">${messageInfo.messageContent }</textarea>
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
