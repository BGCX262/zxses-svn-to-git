<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="${basePath }res/client/css/css.css" />
		<title>搜索结果</title>
	</head>

	<body bgcolor="#e5e8e7">
		<div class="blogout">
			<div class="bloginner">
				<!-- 头部 begin -->
				<jsp:include page="/client/blog/head.jsp" />
				<!-- 头部 end -->


				<div class="bl_main">
					<!-- 左边 begin -->
					<jsp:include page="/client/blog/left.jsp" />
					<!-- 左边 end -->

					<!-- 右边文章列表 begin -->
					<div class="bl_right fr">
						<div class="bl_r_title">
							<h1>
								搜索结果
							</h1>
						</div>
						<div class="bl_r_cont">
							<div>
								<ul>
									<!-- 列表数据栏 -->
									<c:if test="${!empty qs.resultlist}">
										<c:forEach items="${qs.resultlist }" var="entity"
											varStatus="status">
											<li>
												<h1>
													${entity.articleTitle }
												</h1>
												<p>
													${entity.articleContent }
												</p>
											</li>
										</c:forEach>
									</c:if>
									<!-- 在列表数据为空的时候，要显示的提示信息 **!!根据标题列数修改colspan大小-->
									<c:if test="${empty qs.resultlist}">
										<tr class="trcolor" onMouseOver="this.className='cstd2'"
											onMouseOut="this.className='trcolor'">
											<td colspan="3" align="center">
												没有找到相应的记录!
											</td>
										</tr>
									</c:if>
								</ul>
							</div>

							<!-- 插入分页 begin -->
							<div class="digg4" style="text-align: center;">
								<!-- 在这里插入分页导航条 -->
								<!-- 最少有一条记录才显示分页导航条(否则出现首页和尾页点击尾页报错！) !!修改对应的url-->
								<c:if test="${qs.totalrecord > 0 }">
						       		<pg:pager items="${qs.totalrecord }" url="search.do" export="currentPageNumber=pageNumber">
										<pg:param name="word"/>
										<pg:first>
											<a href="${pageUrl }">首页</a>
										</pg:first>
										<pg:prev>
											<a href="${pageUrl }">前页</a>
										</pg:prev>
										<pg:pages>
											<c:choose>
												<c:when test="${currentPageNumber eq pageNumber}">
													<font color="red">${pageNumber }</font>
												</c:when>
												<c:otherwise>
													<a href="${pageUrl }">${pageNumber }</a>
												</c:otherwise>
											</c:choose>
										</pg:pages>
										<pg:next>
											<a href="${pageUrl }">后页</a>
										</pg:next>
										<pg:last>
											<a href="${pageUrl }">尾页</a>
										</pg:last>
									</pg:pager>
								</c:if>
							</div>
							<!-- 插入分页 begin -->

						</div>
						<!-- 右边文章列表 end -->
					</div>
					<div class="clear"></div>
				</div>

				<!-- 底部 begin -->
				<jsp:include page="/client/blog/bottom.jsp" />
				<!-- 底部 end -->
			</div>
		</div>
	</body>
</html>