<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!-- 左边 begin -->
<div class="bl_left fl">
	<div class="bl_resourse">
		<div class="bl_title">
			<h1>个人资料</h1>
		</div>
		<div class="bl_cont">
			<p class="bl_img"><img src="images/xxz.jpg" /></p>
			<p class="bl_link">
				等级<span>36级</span>
				<img src="${basePath }res/client/css/img/user-4.gif" alt="钻石用户" title="钻石用户" />
				<img src="${basePath }res/client/css/img/user-3.gif" alt="黄金用户" title="黄金用户" />
				<img src="${basePath }res/client/css/img/user-2.gif" alt="白银用户" title="白银用户"/>
				<img src="${basePath }res/client/css/img/user-1.gif" alt="普通用户" title="普通用户" />
			</p>
			<p class="bl_link">
				<a href="http://blog.sina.com.cn/u/1660501074" title="新浪微博"><img src="${basePath }res/client/css/img/sina.jpg" alt="新浪微博" /></a>
			</p>
			<p>访问：<span>2008次</span></p>
			<p>性别：<span>男</span></p>
			<p>来自：<span>深圳</span></p>
		</div>
	</div>
	<div class="bl_resourse">
		<div class="bl_title">
			<h1>文章分类</h1>
		</div>
		<div class="bl_cont">
			<ul>
				<c:forEach>
				
				</c:forEach>
				<li><a href="">【杂谈】（21）</a></li>
				<li><a href="">【文学精华】（10）</a></li>
				<li><a href="">【休养生息】（7）</a></li>
			</ul>
		</div>
	</div>
	<div class="bl_resourse">
		<div class="bl_title">
			<h1>存档分类</h1>
		</div>
		<div class="bl_cont">
			<ul>
				<li><a href="">2012-05-29（12）</a></li>
				<li><a href="">2012-04-18（8）</a></li>
				<li><a href="">2012-03-06（3）</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- 左边 end -->
