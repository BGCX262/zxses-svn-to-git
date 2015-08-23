<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<ul class="ui-tabs-nav">
	<li class="ui-tabs-selected">
		<a target="_blank" href="<%=basePath%>client/itembig${itemBig_words.classId}/itemsmall${itemSmall_words.typeId}.html">${itemSmall_words.typeName}</a>
	</li>
	<li>
		<a target="_blank" href="<%=basePath%>client/itembig${itemBig_news.classId}/itemsmall${itemSmall_news.typeId}.html">${itemSmall_news.typeName}</a>
	</li>
	<li>
		<a target="_blank" href="<%=basePath%>client/itembig${itemBig_notice.classId}/itemsmall${itemSmall_notice.typeId}.html">${itemSmall_notice.typeName}</a>
	</li>
</ul>
<div class="ui-tabs-panel">
	<logic:iterate id="content_words" name="contentManageList_words" indexId="index" length="1">
	<c:if test="${content_words.announceType==0}">
			<a href="<%=basePath%>${content_words.htmlFileName}" target="_blank" title="${content_words.newsTitle }">
			${fn:substring(content_words.newsContent, 0, newsItemConfig_words.moreTitleCount)} 
			<c:if test="${fn:length(content_words.newsContent)>newsItemConfig_words.moreTitleCount}">
			...</c:if></a>
	</c:if>
	</logic:iterate>
</div>
<div class="ui-tabs-panel ui-tabs-hide">
	<ul class="list_li">
	<logic:iterate id="content_news" name="contentManageList_news">
	<c:set var="character_news" value="${newsItemConfig_news.titleCharacterCount}" />
	<c:if test="${content_news.announceType==0}">
		<c:choose>
			<c:when test="${content_news.htmlFileName!=null}">
				<li><a href='<%=basePath%>${content_news.htmlFileName}'
					<c:if test="${newsItemConfig_news.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content_news.newsTitle}">${fn:substring(content_news.newsTitle,0,character_news)}<c:if
						test="${fn:length(content_news.newsTitle)>character_news}">...</c:if> </a></li>
			</c:when>
			<c:otherwise>
				<li><a
					href="<%=basePath%>client/newsClient.do?method=getNewsContentManageByNewsId&newsId=${content_news.newsId}"
					<c:if test="${newsItemConfig_news.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content_news.newsTitle}">${fn:substring(content_news.newsTitle,
					0, character_news)}<c:if
						test="${fn:length(content_news.newsTitle)>character_news}">...</c:if>
				</a></li>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${content_news.announceType==1}">
		<li><a href="${content_news.httpUrl}"
			<c:if test="${newsItemConfig_news.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content_news.newsTitle}">${fn:substring(content_news.newsTitle,0,
			character_news)}<c:if test="${fn:length(content_news.newsTitle)>character_news}">...</c:if>
		</a></li>
	</c:if>
	<c:if test="${content_news.announceType==2}">
		<li><a href="<%=basePath%>${content_news.annexAddress}"
			<c:if test="${newsItemConfig_news.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content_news.newsTitle}">${fn:substring(content_news.newsTitle, 0,
			character_news)}<c:if test="${fn:length(content_news.newsTitle)>character_news}">...</c:if>
		</a></li>
	</c:if>
</logic:iterate>	
	</ul>
</div>
<div class="ui-tabs-panel  ui-tabs-hide">
<ul class="list_li">
	<logic:iterate id="content_notice" name="contentManageList_notice">
	<c:set var="character_notice" value="${newsItemConfig_notice.titleCharacterCount}" />
	<c:if test="${content_notice.announceType==0}">
		<c:choose>
			<c:when test="${content_notice.htmlFileName!=null}">
				<li><a href='<%=basePath%>${content_notice.htmlFileName}'
					<c:if test="${newsItemConfig_notice.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content_notice.newsTitle}">${fn:substring(content_notice.newsTitle,0,character_notice)}<c:if
						test="${fn:length(content_notice.newsTitle)>character_notice}">...</c:if> </a></li>
			</c:when>
			<c:otherwise>
				<li><a
					href="<%=basePath%>client/newsClient.do?method=getNewsContentManageByNewsId&newsId=${content_notice.newsId}"
					<c:if test="${newsItemConfig_notice.ifPopWindow =='1'}"> target='_blank'</c:if>
					title="${content_notice.newsTitle}">${fn:substring(content_notice.newsTitle,
					0, character_notice)}<c:if
						test="${fn:length(content_notice.newsTitle)>character_notice}">...</c:if>
				</a></li>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${content_notice.announceType==1}">
		<li><a href="${content_notice.httpUrl}"
			<c:if test="${newsItemConfig_notice.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content_notice.newsTitle}">${fn:substring(content_notice.newsTitle,0,
			character_notice)}<c:if test="${fn:length(content_notice.newsTitle)>character_notice}">...</c:if>
		</a></li>
	</c:if>
	<c:if test="${content_notice.announceType==2}">
		<li><a href="${content_notice.httpUrl}"
			<c:if test="${newsItemConfig_notice.ifPopWindow =='1'}"> target='_blank'</c:if>
			title="${content_notice.newsTitle}">${fn:substring(content_notice.newsTitle, 0,
			character_notice)}<c:if test="${fn:length(content_notice.newsTitle)>character_notice}">...</c:if>
		</a></li>
	</c:if>
</logic:iterate>	
	</ul>
</div>