<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${basePath }res/client/css/css.css" />
<title>博客主页</title>
</head>

<body bgcolor="#e5e8e7">
<div class="blogout">
	<div class="bloginner">
		<div class="bl_head">
			<div class="bl_nav">
				<a href="starts.html" title="首页" class="hover">首页</a>|
				<a href="class.html" title="博客">博客</a>|
				<a href="dl.html" title="登陆">登陆</a>|
				<a href="class.html" title="阅读">阅读</a>|
				<a href="pic.html" title="相册">相册</a>|
				<a href="class.html" title="精华">精华</a>
			</div>
			<div class="bl_search">
				<input type="text" value="请输入搜索内容" />
				<input type="submit" class="bl_sure" value="" />
			</div>
		</div>
		<div class="bl_main">
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
							<a href="http://blog.sina.com.cn/u/1660501074" title="新浪微博"><img src="css/img/sina.jpg" alt="新浪微博" /></a>
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
							<li><a href="">【杂谈】（21）</a></li>
							<li><a href="">【文学精华】（10）</a></li>
							<li><a href="">【休养生息】（7）</a></li>
						</ul>
					</div>
					<div class="bl_cont">
						<ul>
							<li><a href="">草稿箱（0）</a></li>
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
			<div class="bl_right fr">
				<div class="bl_r_title">
					<h1>博文</h1>
					<span class="addIcon">
						<a href="${basePath }blog/article.do?method=addInput">发表新文章</a>
					</span>
					<span class="manageIcon">
						<a href="${basePath }blog/article.do?method=addInput">管理我的博客</a>
					</span>
				</div>
				<div class="bl_r_cont">
					<div>
						<ul>
							<li>
								<h1>多读一点书，就高一点点。。。
									<p>标签：<span>杂谈</span></p>
								</h1>
								<p>高高在上的猴子小姐：“多读一本书，就高一点点……”（知识能改变对快乐的感知、对痛苦的耐受力、乃至于世界观、命运、人生、还有家庭地位神马的……没文化在家里说话也没底气啊…………）</p>
								<div class="page">
									阅读<span>（6）</span>|
									<a href="">评论</a><span>（0）</span>|
									<a href="">收藏</a>|
									<span>2012-05-28 10:09:27</span>
									<a href="" class="bl_more" title="查看更多">查看更多》</a>
								</div>
							</li>
							<li>
								<h1>多读一点书，就高一点点。。。
									<p>标签：<span>杂谈</span></p>
								</h1>
								<p>高高在上的猴子小姐：“多读一本书，就高一点点……”（知识能改变对快乐的感知、对痛苦的耐受力、乃至于世界观、命运、人生、还有家庭地位神马的……没文化在家里说话也没底气啊…………）</p>
								<div class="page">
									阅读<span>（6）</span>|
									<a href="">评论</a><span>（0）</span>|
									<a href="">收藏</a>|
									<span>2012-05-28 10:09:27</span>
									<a href="" class="bl_more" title="查看更多">查看更多》</a>
								</div>
							</li>
							<li>
								<h1>多读一点书，就高一点点。。。
									<p>标签：<span>杂谈</span></p>
								</h1>
								<p>高高在上的猴子小姐：“多读一本书，就高一点点……”（知识能改变对快乐的感知、对痛苦的耐受力、乃至于世界观、命运、人生、还有家庭地位神马的……没文化在家里说话也没底气啊…………）</p>
								<div class="page">
									阅读<span>（6）</span>|
									<a href="">评论</a><span>（0）</span>|
									<a href="">收藏</a>|
									<span>2012-05-28 10:09:27</span>
									<a href="" class="bl_more" title="查看更多">查看更多》</a>
								</div>
							</li>
						</ul>
					</div>
					<div class="digg4" style=" text-align:center;">
						<ul >
							<li ><a href="" class="diga">首页</a></li>
							<li><a href="">上一页</a></li>
							<li class="diggcurrent">1</li>
							<li><a href="">下一页</a></li>
							<li><a href="">未页</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="bl_foot">
			<p>版权所有 Copyright@ 2002-2004 南山区塘朗小学 联系电话：0755-86007697 QQ:324651(黑马）</p>
			<p>备案序号:粤ICP备05084369号 本站技术支持：深圳市智翔信息技术有限公司</p>
		</div>
	</div>
</div>
</body>
</html>
