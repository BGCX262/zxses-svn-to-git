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
		<script type="text/javascript" src="${basePath}client/index/content/login_reg/jsfiles/changePw.js"></script>
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
					首页&gt;&gt;修改密码
				</div>
				<div id="left" class="fl">
					<!-- 左列 -->
					<ul>
					<%--
					<li><a href="">教师文萃</a>
						<ul>
							<li><a href="">班主任工作的探索</a></li>
							<li><a href="">班主任工作的探索</a></li>
							<li><a href="">班主任工作的探索</a></li>
						</ul>
					</li>
					<li><a href="">最新图片</a></li>
					--%>
					</ul>
					<div>
						<bean:include id="queryContact"
							page="/client/newsClient.do?method=queryContact" />
						<bean:write name="queryContact" filter="false" />
					</div>
					<div class="message">
						<a href="" class="textbg">校长信箱</a>	
						<a href="">留言请进</a>	
					</div>
					<%--
					<bean:include id="queryLeft"
						page="/client/newsClient.do?method=queryLeft&classId=${classId}" />
					<bean:write name="queryLeft" filter="false" />
					--%>
				</div>
				<div id="right" class="fr">
					<div class="art_title">
						<span>修改密码</span>
					</div>
					<form name="changeUserPass" id="changeUserPass" method="post" action="${basePath}front/post.do"
						onsubmit="javascript:return checkSubmit();">
					<input type="hidden" name="method" value="changePw"/>
					<input type="hidden" name="userAccount" value="${frontUserInfo.userAccount }"/>
					<div class="">
						<div>
							<table width="100%" border="0" align="center">
								<tr>
									<td colspan="3" style="height:40px;"
										valign="bottom">
										<span id="lblError" style="color:Red;"></span>
									</td>
								</tr>
								<tr>
									<td colspan="3" style=" height:40px;"
										valign="bottom">
										<table cellspacing="4" cellpadding=4 width="100%" border=0 class="panelInclude">
										<tbody>
		                    			<tr>
			                      			<td valign="top">
			                              		<table border="0" cellpadding="4" cellspacing="4" width="100%">
			                               		 <tbody>
					                                <tr align="left">
					                                  <td colspan="3" class="font-error"></td>
					                                </tr>
					                                <tr align="left">
					                                  <td class="font12b" align="right" width="21%">原始密码:</td>
					                                  <td width="31%"><input type="password" id="password" name="password" maxlength="16" size="30"/></td>
					                                  <td class="font9" width="48%"></td>
					                                </tr>
					                                <tr align="left">
					                                  <td class="font12b" align="right" width="21%">新密码:</td>
					                                  <td width="31%"><input type="password" id="newPassword" name="newPassword" maxlength="16" size="30"/></td>
					                                  <td class="font9" width="48%"></td>
					                                </tr>
					                                <tr align="left">
					                                  <td class="font12b" align="right">重新输入新密码:</td>
					                                  <td><input type="password" id="confirm_password" name="confirm_password" maxlength="16" size="30"/></td>
					                                  
					                                </tr>
					                                <tr align="left">
					                                  <td>&nbsp;</td>
					                                  <td align="right">
					                                  	<input type="image" alt="完成" src="${basePath}res/client/images/az-finish.gif" border="0" name="image1" id="Image1"/>
					                                  </td>
					                                  <td>&nbsp;</td>
					                                </tr>
			                                  	 </tbody>
											   </table>
											</td>
										</tr>
										</tbody>
										</table>
									</td>
								</tr>
							</table>
						
						</div>
						<br />
					</div>
					</form>
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
		</div>
	</body>
</html>