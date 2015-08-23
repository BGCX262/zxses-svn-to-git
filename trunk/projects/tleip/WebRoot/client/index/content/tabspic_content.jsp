<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<ul>
<logic:iterate id="content" name="contentManageList" indexId="index">
	<c:set var="character" value="${newsItemConfig.titleCharacterCount}" />
	<c:if test="${content.announceType==0}">
		<c:choose>
			<c:when test="${content.htmlFileName!=null}">
				<li><a href='<%=basePath%>${content.htmlFileName}'
					<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content.newsTitle}">${fn:substring(content.newsTitle,0,character)}<c:if
						test="${fn:length(content.newsTitle)>character}">...</c:if> </a></li>
			</c:when>
			<c:otherwise>
				<li><a
					href="<%=basePath%>client/newsClient.do?method=getNewsContentManageByNewsId&newsId=${content.newsId}"
					<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content.newsTitle}">${fn:substring(content.newsTitle,
					0, character)}<c:if
						test="${fn:length(content.newsTitle)>character}">...</c:if>
				</a></li>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${content.announceType==1}">
		<li><a href="${content.httpUrl}"
			<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content.newsTitle}">${fn:substring(content.newsTitle,0,
			character)}<c:if test="${fn:length(content.newsTitle)>character}">...</c:if>
		</a></li>
	</c:if>
	<c:if test="${content.announceType==2}">
		<li><a href="<%=basePath%>${content.annexAddress}"
			<c:if test="${newsItemConfig.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content.newsTitle}">${fn:substring(content.newsTitle, 0,
			character)}<c:if test="${fn:length(content.newsTitle)>character}">...</c:if>
		</a></li>
	</c:if>
</logic:iterate>
