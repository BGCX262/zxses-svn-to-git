<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%--<a href="<%=basePath%>client/itembig${classId}.html"><img src="<%=basePath%>res/client/css/img/contact.gif" /></a>
--%>

<div class="contact">
	<logic:iterate id="itemSmall" name="newsItemList" >
		<c:choose>
			<c:when test="${itemSmall.announceType==0}">
				<logic:iterate id="smallConfig" name="itemSmall" length="1"
					property="newsItemConfigs">
					<c:if test="${itemSmall.ifDisplay =='1'}">
						<c:if test="${smallConfig.ifPopWindow =='1'}">
							<a target="_blank"
								href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html"><span>${itemSmall.typeName}</span></a>
						</c:if>
						<c:if test="${smallConfig.ifPopWindow !='1'}">
							<a
								href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html"><span>${itemSmall.typeName}</span></a>
						</c:if>
					</c:if>
				</logic:iterate>
			</c:when>

			<c:when test="${itemSmall.announceType==1}">
				<logic:iterate id="smallConfig" name="itemSmall" length="1"
					property="newsItemConfigs">
					<c:if test="${itemSmall.ifDisplay =='1'}">
						<c:if test="${smallConfig.ifPopWindow =='1'}">
							<a target="_blank" href="${itemSmall.httpUrl}"><span>${itemSmall.typeName}</span></a>
						</c:if>
						<c:if test="${smallConfig.ifPopWindow !='1'}">
							<a href="${itemSmall.httpUrl}"><span>${itemSmall.typeName}</span></a>
						</c:if>
					</c:if>
				</logic:iterate>
			</c:when>

			<c:when test="${itemSmall.announceType==2}">
				<logic:iterate id="smallConfig" name="itemSmall" length="1"
					property="newsItemConfigs">
					<c:if test="${itemSmall.ifDisplay =='1'}">
						<c:if test="${smallConfig.ifPopWindow =='1'}">
							<a target="_blank"
								href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html"><span>${itemSmall.typeName}</span></a>
						</c:if>
						<c:if test="${smallConfig.ifPopWindow !='1'}">
							<a
								href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html"><span>${itemSmall.typeName}</span></a>
						</c:if>
					</c:if>
				</logic:iterate>
			</c:when>
		</c:choose>
	</logic:iterate>
</div>

 