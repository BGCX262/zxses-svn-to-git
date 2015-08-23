<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript"
	src="<%=basePath%>tools/myFocus/js/myfocus-1.2.4.min.js"></script>
<!--引入myFocus库-->
<script type="text/javascript"
	src="<%=basePath%>tools/myFocus/js/pattern/mF_expo2010.js"></script>
<!--引入风格应用js-->
<link rel="stylesheet"
	href="<%=basePath%>tools/myFocus/js/pattern/mF_expo2010.css" />
<!--引入风格应用css-->
<script type="text/javascript">
myFocus.set({
    id:'boxID',//焦点图盒子ID
    pattern:'mF_expo2010',//风格应用的名称
    time:3,//切换时间间隔(秒)
    trigger:'mouseover',//触发切换模式:'click'(点击)/'mouseover'(悬停)
    width:980,//设置图片区域宽度(像素)
    height:405,//设置图片区域高度(像素)
    txtHeight:'0',//文字层高度设置(像素),'default'为默认高度，0为隐藏
    path:'<%=basePath%>tools/myFocus/js/pattern/',
	autoZoom:'true'
});
</script>
<div id="boxID" style="visibility: hidden">
	<!--焦点图盒子-->
	<div class="loading">
	</div>
	<!--载入画面(可删除)-->
	<ul class="pic">
		<!--内容列表-->
		<logic:iterate id="content" name="contentManageList">
			<c:choose>
				<c:when test="${content.imgNewsAddress != null && content.imgNewsAddress != ''}">
					<li>
						<img src="<%=basePath%>${content.imgNewsAddress}" />
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<img src="<%=basePath%>res/client/images/example.jpg" />
					</li>
				</c:otherwise>
			</c:choose>
		</logic:iterate>
	</ul>
</div>