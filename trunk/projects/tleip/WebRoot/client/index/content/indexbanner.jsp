<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<SCRIPT type="text/javascript" src="<%=basePath%>res/client/js/jquery.cycle.js"></SCRIPT>
<style type="text/css">
 .r-a { margin-left:185px;}
 *html .r-a{at; margin-left:200px;}
 .r-h { margin-left:185px;}
 *html .r-h{ margin-left:200px;}
</style> 
<!--»ÃµÆÕÚÕÖ-->
<div class="mask"></div>
<!--Í¼Æ¬»ÃµÆ-->
<div class=frame>
	<div class=frames id=zzjs_net>
			<div id="f1" style="background: url(<%=basePath%>res/client/images/01_banner.jpg); overflow: hidden; width: 1006px; position: absolute; height: 399px">
			</div>
			<div class="frame-2" id=f2></div>
			<div class="frame-3" id=f3></div> 
			<div class="frame-4" id=f4></div>
			 <div class="frame-5" id=f5></div>
	</div>
	<div class=controls>
		<div class="arrow l-a" onmouseover=highlightA(this); onclick=prevF();
			onmouseout=dehighlightA(this);></div>
		<div class=off id=control1 onmouseover=hover(1) onclick=press(1,false)
			onmouseout=out(1)></div>
		<div class=off id=control2 onmouseover=hover(2) onclick=press(2,false)
			onmouseout=out(2)></div>
		<div class=off id=control3 onmouseover=hover(3) onclick=press(3,false)
			onmouseout=out(3)></div>
		<div class=off id=control4 onmouseover=hover(4) onclick=press(4,false)
			onmouseout=out(4)></div>
		<div class="arrow r-a" onmouseover=highlightA(this); onclick=nextF();
			onmouseout=dehighlightA(this);></div>
	</div>
	<INPUT id=numFrame type=hidden value=4></INPUT> 
	<a href="#" target="_blank"><INPUT id=images1 type=hidden value="<%=basePath%>res/client/images/01_banner.jpg"></INPUT></a> 
	<a href="#" target="_blank"><INPUT id=images2 type=hidden value="<%=basePath%>res/client/images/02_banner.jpg"></INPUT></a> 
	<a href="#" target="_blank"><INPUT id=images3 type=hidden value="<%=basePath%>res/client/images/03_banner.jpg"></INPUT></a> 
	<a href="#" target="_blank"><INPUT id=images4 type=hidden value="<%=basePath%>res/client/images/04_banner.jpg"></INPUT></a>
</div>
<div class="clear"></div>
<SCRIPT type="text/javascript" src="<%=basePath%>res/client/js/cycle.js"></SCRIPT>