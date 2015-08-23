<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="left_list">
	<h2>
		${itemBig.className }
	</h2>
	<ul>
		<li>
			<div class="left_title">${itemBig.className }</div>
			<ul>
				<logic:iterate id="itemSmall" name="itemBig"
					property="newsItemSmalls">
					<li>
						<c:choose>
							<c:when test="${itemSmall.announceType==0}">
								<logic:iterate id="smallConfig" name="itemSmall" length="1"
									property="newsItemConfigs">
									<c:if test="${itemSmall.ifDisplay =='1'}">
										<a class="gray"
											href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html">${itemSmall.typeName}</a>
									</c:if>
								</logic:iterate>
							</c:when>

							<c:when test="${itemSmall.announceType==1}">
								<logic:iterate id="smallConfig" name="itemSmall" length="1"
									property="newsItemConfigs">
									<c:if test="${itemSmall.ifDisplay =='1'}">
										<a class="gray" href="${itemSmall.httpUrl}">${itemSmall.typeName}</a>
									</c:if>
								</logic:iterate>
							</c:when>

							<c:when test="${itemSmall.announceType==2}">
								<logic:iterate id="smallConfig" name="itemSmall" length="1"
									property="newsItemConfigs">
									<c:if test="${itemSmall.ifDisplay =='1'}">
										<a class="gray"
											href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html">${itemSmall.typeName}</a>
									</c:if>
								</logic:iterate>
							</c:when>
						</c:choose>
					</li>
				</logic:iterate>
			</ul>
		</li>
	</ul>
</div>
<div>
	<!--contact-->
		<bean:include id="queryContact"
			page="/client/newsClient.do?method=queryContact" />
		<bean:write name="queryContact" filter="false" />
</div>


