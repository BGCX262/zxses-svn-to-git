<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>深圳市南山区塘朗小学</title>
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
			src="${basePath}client/index/content/login_reg/jsfiles/login.js"></script>
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
						<bean:include id="queryIndexBanner"
							page="/client/newsClient.do?method=queryIndexBanner" />
						<bean:write name="queryIndexBanner" filter="false" />
					</div>
				</div>
			</div>
		
			<!--end-->
			<div id="main">
				<div class="scor_left fl">
					<div class="regit">
						<!--index_login-->
						<bean:include id="queryLogin"
							page="/client/newsClient.do?method=queryLoginForStatic" />
						<bean:write name="queryLogin" filter="false" />
					</div>
					<div class="libry">
							<!--library-->
							<bean:include id="queryLib"
								page="/client/newsClient.do?method=queryLib" />
							<bean:write name="queryLib" filter="false" />
					</div>
				<div class="contact">
					<!--queryIndexContact-->
					<bean:include id="queryIndexContact"
						page="/client/newsClient.do?method=queryIndexContact" />
					<bean:write name="queryIndexContact" filter="false" />
				</div>
			</div>
			<div class="scor_mid fl">
				<div class="windows">
					<!--tabs-->
					<bean:include id="queryTabs"
						page="/client/newsClient.do?method=queryTabs" />
					<bean:write name="queryTabs" filter="false" />
				</div>
				<div class="education">
					<!--tabspic-->
					<bean:include id="queryTabspic"
						page="/client/newsClient.do?method=queryTabspic" />
					<bean:write name="queryTabspic" filter="false" />
				</div>
				<div class="stars">
					<div class="friendlink fl">
						<!--FriendLink-->
						<bean:include id="queryFriendLink"
							page="/client/newsClient.do?method=queryFriendLink" />
						<bean:write name="queryFriendLink" filter="false" />
					</div>
					<div class="sky_stars fl">
						<!--SkyStars-->
						<bean:include id="querySkyStars"
							page="/client/newsClient.do?method=querySkyStars" />
						<bean:write name="querySkyStars" filter="false" />
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="scor_right fl">
				<!--headmasters-->
				<bean:include id="queryHeadmasters"
					page="/client/newsClient.do?method=queryHeadmasters" />
				<bean:write name="queryHeadmasters" filter="false" />
			</div>
			<div class="clear"></div>
		</div>
		<!--foot-->
		<div class="footer">
			<bean:include id="queryFootInfo"
				page="/client/newsClient.do?method=queryFootInfo" />
			<bean:write name="queryFootInfo" filter="false" />
		</div>
		<!--end-->
		<!--xiaoxun-->
		<div id=sidenav style="POSITION: absolute; top: 100px; right: 0; z-index: 101;">
			<img src="<%=basePath%>res/client/images/xiaoxun.gif" />
		</div>
		</div>	
	</body>
</html>


