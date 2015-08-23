<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />   
  <title>搜索列表页</title>
<%@ include file="/res/common/inc/resources.jsp"%>
<style type="text/css">
	body{ background:url(${basePath }res/client/css/img/wx_bg.jpg) repeat;}
</style>
</head>

<body>
<div id="wx_wrap">
<div class="wx_bg">
	<!-- 头部 begin -->
	<jsp:include page="/client/index/head.jsp"/>
	<!-- 头部 end -->
	
	<div id="wx_main">
		<div class="art_title"><span>搜索结果列表页</span></div>
		<div class="bl_r_cont">
					<div class="weblist">
						<ul>
							<!-- 列表数据栏 -->
							<c:if test="${!empty qs.resultlist}">
								<c:forEach items="${qs.resultlist }" var="entity"
									varStatus="status">
									<li>
										<div class="title">
											<a href="article.html">${entity.articleTitle }</a>
										</div>
										<div class="clear"></div>
										<div class="content">
										${entity.articleContent }
										</div>
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
							<div class="clear"></div>
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
					<!-- 插入分页 end -->
					
					
					
				</div>
	</div>
	<!-- 底部 begin -->
	<jsp:include page="/client/index/bottom.jsp"/>
	<!-- 底部 end -->
</div>
</div>
</body>
</html>