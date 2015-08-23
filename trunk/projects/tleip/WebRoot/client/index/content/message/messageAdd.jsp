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
		<script type="text/javascript" src="${basePath}client/index/content/message/jsfiles/messageAdd.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>client/index/content/login_reg/jsfiles/login.js"></script>
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
			.allwrap{
				border: 1px solid #2095A6;
			}
		</style>
		<script type="text/javascript">
			
		</script>
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
						<h2>发表留言</h2>
						<ul>
						<li>
							<ul>
							</ul>
						</li>
						</ul>
					</div>
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
				<div id="right" class="fr">
					<div class="art_title">
						<span>在线留言版</span>
					</div>
					<div class="wrap">
						<div>
							<table width="100%" border="0" align="center" >
								<tr>
									<td align="center" valign="top">
										<a href="${basePath }front/message.do?method=queryMessage"><img
												src="${basePath }res/client/images/message/read.gif" border="0"></img> </a>
									</td>
								</tr>
							</table>
						</div>
						<div style="height: 10px;"></div>
						<div align="center">
							<form name="addMessageForm" id="addMessageForm" action="${basePath }front/message.do?method=addMessage" method="post" onsubmit="return doValidate('addMessageForm');">
			  					<input type="hidden" name="frontUserId" value="${frontUserInfo.userId}"/>
			  					<table border="0" cellspacing="0" cellpadding="0" width="95%">
					  				<tr>
					    				<td width="16%" align=right style="border-left: 1px solid #2095A6;border-top: 1px solid #2095A6;">
					    					留言内容：<br/>
					    					<font color=red>（<span id="wordsNumber">500</span>字以内）</font>
					    				</td>
					    				<td width="84%"  style="border-left: 1px solid #2095A6;border-top: 1px solid #2095A6;border-right: 1px solid #2095A6;padding: 1px">
					    					<textarea id="content" name="messageContent" rows="7" cols="66" style="overflow:auto;width: 99%" onkeyup="gbcount();"  onkeydown="gbcount();"></textarea>
											<div align="left">
											最多字数：
											<input value="500" size="3" id="total" name="total" maxlength="4" disabled="disabled"/>
											已用字数：
											<input value="0" size="3" id="used" name="used" maxlength="4" disabled="disabled"/>
											剩余字数：
											<input value="500" size="3" id="remain" name="remain" maxlength="4" disabled="disabled"/>
					   						</div>
					   					</td>
					 				</tr>
					 				<tr>
									    <td width="16%" align="right" style="border-left: 1px solid #2095A6;border-top: 1px solid #2095A6;">请选择表情：</td>
									    <td align="left" style="border-left: 1px solid #2095A6;border-top: 1px solid #2095A6;border-right: 1px solid #2095A6;padding: 1px">
											<input type="radio" value="face1.gif" name="facePic" checked="checked"/><img border=0 src="${basePath }res/client/images/message/face/face1.gif"/> 
											<input type="radio" value="face2.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face2.gif"/>
											<input type="radio" value="face3.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face3.gif"/>
											<input type="radio" value="face4.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face4.gif"/>
											<input type="radio" value="face5.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face5.gif"/>
											<input type="radio" value="face6.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face6.gif"/> 
											<input type="radio" value="face7.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face7.gif"/>
											<input type="radio" value="face8.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face8.gif"/>
											<input type="radio" value="face9.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face9.gif"/>
											<input type="radio" value="face10.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face10.gif"/>
											<b/>
											<input type="radio" value="face11.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face11.gif"/> 
											<input type="radio" value="face12.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face12.gif"/>
											<input type="radio" value="face13.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face13.gif"/>
											<input type="radio" value="face14.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face14.gif"/>
											<input type="radio" value="face15.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face15.gif"/>
											<input type="radio" value="face16.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face16.gif"/> 
											<input type="radio" value="face17.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face17.gif"/>
											<input type="radio" value="face18.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face18.gif"/>
											<input type="radio" value="face19.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face19.gif"/>
											<input type="radio" value="face20.gif" name="facePic"/><img border=0 src="${basePath }res/client/images/message/face/face20.gif"/>
									    </td>
					  				</tr>
					  				<tr>
									    <td width="16%" align=right style="border-left: 1px solid #2095A6;border-top: 1px solid #2095A6;">请选择头像：</td>
									    <td align="left" style="border-left: 1px solid #2095A6;border-top: 1px solid #2095A6;border-right: 1px solid #2095A6;">
											<input type="radio" value="pic1.gif" name="headPic" checked="checked"/><img border=0 src="${basePath }res/client/images/message/face/pic1.gif" width="60"/>
											<input type="radio" value="pic2.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic2.gif" width="60"/>
											<input type="radio" value="pic3.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic3.gif" width="60"/>
											<input type="radio" value="pic4.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic4.gif" width="60"/>
											<input type="radio" value="pic5.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic5.gif" width="60"/>
											<br/>
											<input type="radio" value="pic6.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic6.gif" width="60"/>
											<input type="radio" value="pic7.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic7.gif" width="60"/>
											<input type="radio" value="pic8.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic8.gif" width="60"/>
											<input type="radio" value="pic9.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic9.gif" width="60"/>
											<input type="radio" value="pic10.gif" name="headPic"/><img border=0 src="${basePath }res/client/images/message/face/pic10.gif" width="60"/>
									    </td>
					 				</tr>
					 				<tr>
					    				<td colspan="2" align="center" style="border: 1px solid #2095A6;">
					    					<input type="submit" class="uniformButton" value="提交留言" onclick="return validateSubmit();"/> 
					     					<input type="reset" class="uniformButton" value="重新填写"/>
					     				</td>
					  				</tr>
			  					</table>
		  					</form>
						</div>
					</div>
					<br />
					</div>
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