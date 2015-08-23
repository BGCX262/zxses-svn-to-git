<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.agilefly.bean.SysConfig"/>
<%@ include file="/commons/tags.inc"%>

<div id='floatDivr' style='position: absolute;' class='floatonline_1'>
	<div class="float">
		<a href="${sysConfig_App.sinaWeibo }" title="新浪微博"><img src="${basePath}res/client/images/ico_sina.gif" alt="新浪微博" />新浪微博</a>
		<a target="_blank" href="${sysConfig_App.onlineQq }"><img border="0" src="${sysConfig_App.picQq }" alt="点击这里给我发消息" title="点击这里给我发消息"></a>
		<div class="mqq"><p>在线交流</p><p class="grey">communication</p></div>
	</div>
</div>