<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>满天星文学社</title>
<%@ include file="/res/common/inc/resources.jsp"%>
<style type="text/css">
	body{ background:url(${basePath }res/client/css/img/wx_bg.jpg) repeat;}
</style>
<script type="text/javascript" src="${basePath}res/client/js/indexjava.js"></script>
</head>

<body>
	<div id="wx_wrap">
		<div class="wx_bg">
			<!-- 头部 begin -->
			<jsp:include page="/client/index/head.jsp"/>
			<!-- 头部 end -->
			<div id="wx_main">
				<!-- 左侧优秀作文推荐博客 -->
				<div class="wx_left fl">
					<!-- 优秀作文 -->
					<div class="supper">
						<ul class="sup_list">
							<li><a href="">多与老师沟通，多与孩子... </a></li>
							<li><a href="">家长要学会欣赏孩子的优点 </a></li>
							<li><a href="">家长要学会欣赏孩子的优点 </a></li>
							<li><a href="">早期教育三忌 </a></li>
							<li><a href="">建立良好的亲子关系 关键... </a></li>
							<li><a href="">诱拐学生失踪 </a></li>
							<li class="listp"><p><a href="">更多...</a></p></li>
						</ul>
					</div>
					<!-- 推荐博客 -->
					<div class="learn">
						<h1><span>推荐博客</span></h1>
						<ul class="sup_list">
							<li><a href="">雷电现象、种类及雷云的... </a></li>
							<li><a href="">七年级学生的心理问题及对策 </a></li>
							<li><a href="">如何对中考生进行心理调整 </a></li>
							<li><a href="">高中语文答题公式总结 </a></li>
							<li><a href="">突破基础 注重积累 </a></li>
							<li><a href="">掌握好学习方法达到初中... </a></li>
							<li><a href="">如何培养自己的高中思维... </a></li>
							<li><a href="">关于家长学校 </a></li>
							<li class="listp"><p><a href="">更多...</a></p></li>
						</ul>	
					</div>
				</div>
				<!-- 右侧教师文章类型主要模块 -->
				<div class="wx_right fr">
					<div class="wx_inner">
						<c:choose> 
							<c:when test="${fn:length(teacherBlogTypeList)==0}"> 
								<div style="margin-left:20px;">
									没有教师文章类型！
								</div>
								<div class="clear"></div>
							</c:when> 
							<c:when test="${fn:length(teacherBlogTypeList)>0}">
								<div class="page1">
									<div class="page_list1 fl">
										<h1><a href="">【${wenxueNews.sysType.typeName }】</a></h1>
										<ul class="list1">
											<c:choose>
												<c:when test="${fn:length(wenxueNews.blogArticleList)>0 }">
													<c:forEach items="${wenxueNews.blogArticleList}" var="blogArticle">
														<li><a href="">${blogArticle.articleTitle }</a></li>
													</c:forEach>
													<c:if test="${fn:length(wenxueNews.blogArticleList)>6 }">
														<li class="listp"><p><a href="">更多...</a></p></li>
													</c:if>
												</c:when>
												<c:otherwise>
													没有相关文章！
												</c:otherwise>
											</c:choose>
										</ul>
									</div>
									<div class="page_list1 fl">
										<h1><a href="">【${writeInstruct.sysType.typeName }】</a></h1>
										<ul class="list1">
											<c:choose>
												<c:when test="${fn:length(writeInstruct.blogArticleList)>0 }">
													<c:forEach items="${writeInstruct.blogArticleList}" var="blogArticle">
														<li><a href="">${blogArticle.articleTitle }</a></li>
													</c:forEach>
													<c:if test="${fn:length(writeInstruct.blogArticleList)>6 }">
														<li class="listp"><p><a href="">更多...</a></p></li>
													</c:if>
												</c:when>
												<c:otherwise>
													没有相关文章！
												</c:otherwise>
											</c:choose>
										</ul>
									</div>
									<div class="clear"></div>		
								</div>
								<div class="page2">
									<div class="page_list1 fl">
										<h1><a href="">【${writeInstruct.sysType.typeName }】</a></h1>
										<dl>
											<dt><a href="">西南民族大学与四川甘孜州开展战略合作 </a></dt>
											<dd>
												<a href=""><img src="${basePath}res/client/images/wx1.jpg" /></a>
												<a href="" class="text">绵阳网校为孩子们搭建了安全</a>
												<li><a href="">七年级学生的心理问题及对策 </a></li>
												<li><a href="">论中学生的嫉妒因及其应对方法 </a></li>
												<li class="listp"><p><a href="">更多...</a></p></li>
											</dd>
											
										</dl>
									</div>
									<div class="page_list1 fl">
										<h1><a href="">【文学快车】</a></h1>
										<dl>
											<dd>
												<a href=""><img src="${basePath}res/client/images/wx1.jpg" /></a>
												<ul class="list2">
													<li><a href="">有钱没钱回家过年 </a><li>
													<li><a href="">如何对中考生进行心理调整 </a><li>
													<li><a href="">论中学生的嫉妒因及其应对方法 </a></li>
													<li><a href="">论中学生的嫉妒心理的成因及其应对方法 </a></li>
													<li><a href="">七年级学生的心理问题及对策 </a></li>
													<li><a href="">论中学生的嫉妒因及其应对方法 </a></li>
													<li class="listp"><p><a href="">更多...</a></p></li>
												</ul>
												
											</dd>
										</dl>
									</div>
									<div class="clear"></div>	
								</div>
								<div class="page3">
									<div class="page_list1 fl">
										<h1><a href="">【网络教室】</a></h1>
										<ul class="list1 list3">
											<li><a href="">校门口的凉粉 </a></li>
											<li><a href="">我学会了骑自行车 </a></li>
											<li><a href="">人在初三 </a></li>
											<li><a href="">风雨中，坚定脚步 </a></li>
											<li><a href="">往事随风 </a></li>
											<li><a href="">成都高考艺体加分测试 成都考... </a></li>
										</ul>
									</div>
									<div class="page_list1 fl">
										<h1><a href="">【益智谜语】</a></h1>
										<ul  class="list1 list3">
											<li><a href="">小学阶段除了语文和数学以外，其余科目均没有学习任务和压力</a></li>
											<li><a href="">考试都在90分以上，甚至可以得满分</a></li>
											<li><a href="">还有历史、地理、生物和政治等。每一门课程老师</a></li>
											<li><a href="">激烈的竞争以及家长过高</a></li>
											<li><a href="">小学阶段老师要求不严，学习任务不重，对英语的重视不够。</a></li>
											<li><a href="">好学生担心失掉“尖子”地位，受到老师</a></li>
										</ul>
									</div>
									<div class="clear"></div>		
								</div>
							</c:when>
						</c:choose>
						<%--
						==========================================================
				==========================================
						<div class="page1">
							<div class="page_list1 fl">
								<h1><a href="">【文学新闻】</a></h1>
								<ul class="list1">
									<li><a href="">前行需要勇气前行需要勇气前行需要勇气前行需要勇气前行需要勇气前行需要勇气前行需要勇气前行需要勇气 </a></li>
									<li><a href="">美丽的新中卫告别青涩 </a></li>
									<li><a href="">论中学生的嫉妒心理的成因及其应对方法 </a></li>
									<li><a href="">告别青涩 </a></li>
									<li><a href="">风雨中，坚定脚步 </a></li>
									<li><a href="">成都高考艺体加分测试 </a></li>
									<li class="listp"><p><a href="">更多...</a></p></li>
								</ul>
							</div>
							<div class="page_list1 fl">
								<h1><a href="">【写作指导】</a></h1>
								<ul  class="list1">
									<li><a href="">西南民族大学与四川甘孜州开展战略合作 </a></li>
									<li><a href="">这个冬天不太冷 </a></li>
									<li><a href="">如何对中考生进行心理调整 </a></li>
									<li><a href="">风雨中，坚定脚步 </a></li>
									<li><a href="">心情日记—催眠的童话 </a></li>
									<li><a href="">好学生担心失掉“尖子”地位，受到老师 </a></li>
									<li class="listp"><p><a href="">更多...</a></p></li>
								</ul>
							</div>
							<div class="clear"></div>		
						</div>
						<div class="page2">
							<div class="page_list1 fl">
								<h1><a href="">【名著回廊】</a></h1>
								<dl>
									<dt><a href="">西南民族大学与四川甘孜州开展战略合作 </a></dt>
									<dd>
										<a href=""><img src="${basePath}res/client/images/wx1.jpg" /></a>
										<a href="" class="text">绵阳网校为孩子们搭建了安全教育平台，在这里孩子们可接受到更多的安全教育，获取更多的安全防范知识，提高自我保护能力。绵阳网校为孩子们搭建了安全教育平台，在这里孩子们可接受到更多的安全教育，获取更多的安全防范知识，提高自我保护能力。</a>
									</dd>
									
								</dl>
							</div>
							<div class="page_list1 fl">
								<h1><a href="">【文学快车】</a></h1>
								<dl>
									<dt><a href="">有钱没钱回家过年 </a></dt>
									<dd>
										<a href=""><img src="${basePath}res/client/images/wx1.jpg" /></a>
										<ul class="list2">
											<li><a href="">如何对中考生进行心理调整 </a></li>
											<li><a href="">论中学生的嫉妒因及其应对方法 </a></li>
											<li><a href="">论中学生的嫉妒心理的成因及其应对方法 </a></li>
											<li><a href="">七年级学生的心理问题及对策 </a></li>
											<li><a href="">论中学生的嫉妒因及其应对方法 </a></li>
										</ul>
									</dd>
								</dl>
							</div>
							<div class="clear"></div>	
						</div>
						<div class="page3">
							<div class="page_list1 fl">
								<h1><a href="">【网络教室】</a></h1>
								<ul class="list1 list3">
									<li><a href="">校门口的凉粉 </a></li>
									<li><a href="">我学会了骑自行车 </a></li>
									<li><a href="">人在初三 </a></li>
									<li><a href="">风雨中，坚定脚步 </a></li>
									<li><a href="">往事随风 </a></li>
									<li><a href="">成都高考艺体加分测试 成都考... </a></li>
								</ul>
							</div>
							<div class="page_list1 fl">
								<h1><a href="">【益智谜语】</a></h1>
								<ul  class="list1 list3">
									<li><a href="">小学阶段除了语文和数学以外，其余科目均没有学习任务和压力</a></li>
									<li><a href="">考试都在90分以上，甚至可以得满分</a></li>
									<li><a href="">还有历史、地理、生物和政治等。每一门课程老师</a></li>
									<li><a href="">激烈的竞争以及家长过高</a></li>
									<li><a href="">小学阶段老师要求不严，学习任务不重，对英语的重视不够。</a></li>
									<li><a href="">好学生担心失掉“尖子”地位，受到老师</a></li>
								</ul>
							</div>
							<div class="clear"></div>		
						</div>
					--%>
					</div>
					<div class="wx_sidbar"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!-- 底部 begin -->
			<jsp:include page="/client/index/bottom.jsp"/>
			<!-- 底部 end -->
		</div>
	</div>
	<!--floater onlin-->
	<jsp:include page="/client/index/floater.jsp"/>
</body>
</html>