<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>深圳市塘朗小学页尾</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>res/client/css/css.css" />
</head>


<body>
	<div class="clear"></div>
	<div id="foot">
		<div class="foot_bot">
			<div class="foot_inner">
				<div class="foot_logo fl"><img src="<%=basePath%>res/client/css/img/foot-logo.gif" /></div>
				<div class="foot_cont fl">
					<p><a href="" style="padding-left:0;">设为首页</a>|<a href="">加入收藏</a>|<a href="">联系站长</a>|<a href="">友情链接</a>|<a href="">关于我们</a>|</p>
					<p>版权所有 Copyright &copy; 2002-2004 南山区塘朗小学 联系电话：0755-86007697 QQ:324651(黑马）</p>
					<p>备案序号:粤ICP备05084369号  本站技术支持：深圳市智翔信息技术有限公司<img src="<%=basePath%>res/client/css/img/foot-icon.gif" /></p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>
