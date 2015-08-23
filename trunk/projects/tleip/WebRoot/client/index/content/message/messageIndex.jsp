<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/commons/tags.inc"%>
<%@ include file="/inc/include.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>深圳市南山区塘朗小学-在线留言板</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="深圳,南山,塘朗,小学" />
		<meta http-equiv="description" content="深圳市南山区塘朗小学" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/client/css/css.css" />
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
		<%@ include file="/inc/resources.jsp"%>
		<script type="text/javascript"
			src="<%=basePath%>client/index/content/login_reg/jsfiles/login.js"></script>
		<script type="text/javascript">
			$(function () {
				$("#toAddMsg").click(function(){
					//ajax登录验证
					$.ajax( {
						url : $.basePath + "front/message.do?method=checkToAddMsg",
						async : true,
						type : "POST",
						success : function(data) {
							if (data) {
								if(data == "unLogin"){
									$.alert("只有登录用户可发表留言，请登录!");
									return;
								}else if(data == "success"){
									 window.location.href="${basePath }front/message.do?method=toAddMessage";
								}
							}
						}
					});
				});
			});
		</script>
		<style>
			/*简单修改符合塘朗网站的功能*/
			.wrap{
				border-bottom: 1px solid #2095A6;
			    border-left: 1px solid #2095A6;
			    border-right: 1px solid #2095A6;
			    border-top: 1px solid #2095A6;
			    clear: left;
			    font-size: 12px;
			    margin: 0 auto;
			    padding-bottom: 30px;
			    padding-top: 10px;
			}
			.userInfo{
				border: 1px solid #2095A6;
			}
			.postdate{
			    border-right: 1px solid #2095A6;
			    border-top: 1px solid #2095A6;
			}
			.content{
				border-bottom: 1px solid #2095A6;
    			border-right: 1px solid #2095A6;
    			border-top: 1px solid #2095A6;
			}
		</style>
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
						<img align="middle" src="<%=basePath%>res/client/images/second_list.jpg" width="970" height="204"/>
						</div>
					</div>
				</div>
			</div>
			<!--end-->
			<!--web-->
			<div id="web">
				<div class="page_line">
					首页&gt;&gt;在线留言
				</div>
				<div id="left" class="fl">
					<!-- 左列 -->
					<div class="left_list">
						<h2>留言投票</h2>
						<ul>
						<li>
							<ul>
							</ul>
						</li>
						</ul>
					</div>
					
					<%--<a href="<%=basePath%>front/vote.do?method=queryVoteList" target="_blank">网络投票</a>
					
					--%>
					<div class="voteList">
						<!--voteList-->
						<bean:include id="queryVoteListInMessage"
							page="/front/vote.do?method=queryVoteListInMessage" />
						<bean:write name="queryVoteListInMessage" filter="false" />
					</div>
					<br/>
					<div class="regit">
						<!--index_login-->
						<bean:include id="queryLogin"
							page="/client/newsClient.do?method=queryLogin" />
						<bean:write name="queryLogin" filter="false" />
					</div>
					<div>
						<bean:include id="queryContact"
							page="/client/newsClient.do?method=queryContact" />
						<bean:write name="queryContact" filter="false" />
					</div>
					<%--
					<bean:include id="queryLeft"
						page="/client/newsClient.do?method=queryLeft&classId=${classId}" />
					<bean:write name="queryLeft" filter="false" />
					--%>
				</div>
				
				<html:form action="front/message.do?method=queryMessage"
					method="post">
				<div id="right" class="fr">
					<div class="art_title">
						<span>在线留言版</span>
					</div>
					<div class="wrap">
						<div>
							<table width="100%" border="0" align="center" >
								<tr>
									<td align="center" valign="top">
										<img id="toAddMsg" style="cursor: hand;" src="${basePath }res/client/images/message/write.gif" border="0"></img>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${basePath }front/message.do?method=queryMessage"><img
												src="${basePath }res/client/images/message/read.gif" border="0"></img> </a>
									</td>
								</tr>
							</table>
						</div>
						<div style="height: 10px;"></div>
						<logic:iterate id="column" name="pageList" property="list"
							indexId="newsIndex">
						<div>
							<table width="100%" border="0" align="center">
								<tr>
									<td align="center">
										<table width="90%" align='center'  cellspacing="0"
											style='word-break: break-all; ' >
											<tr>
												<td valign='top' width='25%' rowspan='2'
													align='center' class="userInfo">
													<div align='center' style='width: 80%'>
														<img src='${basePath }res/client/images/message/face/${column.headPic}' />
													</div>
													<div align='left' style='width: 80%'>
														姓名：${column.frontUser.userAccount}
														<br/>
														<%--来自：127.0.0.1**
														
														<br/>
														--%>
														邮件：
														<a href="mailto:${column.frontUser.email}"><img src="${basePath }res/client/images/message/mail.gif"
																border="0" alt="${column.frontUser.email}"/></a>
														<br/>
														<%--
														主页：
														<a href='http://' target='_blank'><img src="${basePath }res/client/images/message/home.gif"
																border="0" alt='http://'/></a>
														--%>
													</div>
												</td>
												<td width='75%' height='20px' class="postdate" align="left">
													<img border=0 src="${basePath }res/client/images/message/face/${column.facePic}"/>
													发表于：${column.messageDate}
												</td>
											</tr>
											<tr>
												<td valign='top' bgColor='#ebebeb' width='75%' class="content"
													onMouseOver="bgColor='#FFffff'" onmouseout="bgColor='#ebebeb'" align="left">
													${column.messageContent}
													<br/>
													<br/>
													<c:if test="${fn:length(column.replyContent) > 0}">
													<div align="center" style="width: 100%">
													<table width='90%'
														align='center' bgcolor='darkgray' border='0'>
														<tr>
															<td valign='top' align="center" bgColor='#f7f7f7'>
																<font color=red>coolszy回复：<br/>sa</font>
															</td>
														</tr>
													</table>
													<div>
													</c:if>
													<br/>
												</td>
											</tr>
										</table>
										<table width='680' align='center'
											bgcolor='#FFFFFF' border='0'>
											<tr>
												<td height=8>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						</logic:iterate>
						<div align="right">
						${pageList.view }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<br />
					</div>
					</html:form>
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
			<!--xiaoxun-->
			<div id=sidenav style="POSITION: absolute; top: 100px; right: 0;">
				<img src="<%=basePath%>res/client/images/xiaoxun.gif" />
			</div>
	</body>
</html>