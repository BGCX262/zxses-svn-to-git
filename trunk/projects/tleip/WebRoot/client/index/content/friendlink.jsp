<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<h4>
	<a target="_blank" href="<%=basePath%>client/itembig${itemBig.classId}.html">${itemBig.className }</a>
</h4>
<logic:iterate id="content" name="contentManageList" indexId="index">
	<c:set var="character" value="${newsItemConfig.titleCharacterCount}" />
	<c:if test="${content.announceType==0}">
		<c:choose>
			<c:when test="${content.htmlFileName!=null}">
				<a href='<%=basePath%>${content.htmlFileName}'
					<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content.newsTitle}">${fn:substring(content.newsTitle,0,character)}<c:if
						test="${fn:length(content.newsTitle)>character}">...</c:if> </a>
			</c:when>
			<c:otherwise>
				<a
					href="<%=basePath%>client/newsClient.do?method=getNewsContentManageByNewsId&newsId=${content.newsId}"
					<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content.newsTitle}">${fn:substring(content.newsTitle,
					0, character)}<c:if
						test="${fn:length(content.newsTitle)>character}">...</c:if>
				</a>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${content.announceType==1}">
		<a href="${content.httpUrl}"
			<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content.newsTitle}">${fn:substring(content.newsTitle,0,
			character)}<c:if test="${fn:length(content.newsTitle)>character}">...</c:if>
		</a>
	</c:if>
	<c:if test="${content.announceType==2}">
		<a href="<%=basePath%>${content.annexAddress}"
			<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content.newsTitle}">${fn:substring(content.newsTitle, 0,
			character)}<c:if test="${fn:length(content.newsTitle)>character}">...</c:if>
		</a>
	</c:if>
</logic:iterate>
