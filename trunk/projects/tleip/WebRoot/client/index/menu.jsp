<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="top">
	<div class="fl">
		<a href="<%=basePath%>index.html"><img
			src="<%=basePath%>res/client/css/img/logo.gif" alt="深圳市塘朗小学" /> </a>
	</div>
	<div class="menu fl">
		<p>
			<a href="<%=basePath%>client/itembig1000013.html">联系我们</a> | <a href="#"
				onClick="this.style.behavior='url(#default#homepage)';window.external.addFavorite('http://www.sztlxx.com','深圳市南山区塘朗小学');">收藏我们</a>
				|<a href="http://www.sztlxx.com" target="_blank">进入旧网站</a>
		</p>
		<div class="nav">
			<ul class="menu_nav">
				<li><a href="<%=basePath%>index.html"><span>首页</span> </a>
				</li>
				<logic:iterate id="itemBig" name="newsItemBigs">
					<li onmouseover="showSubLevel(this)"
						onmouseout="hideSubLevel(this)"><a
						href="<%=basePath%>client/itembig${itemBig.classId}.html">${itemBig.className}</a>
						<div class="hover_out">
							<div class="hover_inner">
								<div class="hover_center">
									<logic:iterate id="itemSmall" name="itemBig"
										property="newsItemSmalls">
										<c:choose>
											<c:when test="${itemSmall.announceType==0}">
												<logic:iterate id="smallConfig" name="itemSmall" length="1"
													property="newsItemConfigs">
													<c:if test="${itemSmall.ifDisplay =='1'}">
														<c:if test="${smallConfig.ifPopWindow =='1'}">
															<a target="_blank"
																href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html">${itemSmall.typeName}</a>
														</c:if>
														<c:if test="${smallConfig.ifPopWindow !='1'}">
															<a
																href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html">${itemSmall.typeName}</a>
														</c:if>
													</c:if>
												</logic:iterate>
											</c:when>

											<c:when test="${itemSmall.announceType==1}">
												<logic:iterate id="smallConfig" name="itemSmall" length="1"
													property="newsItemConfigs">
													<c:if test="${itemSmall.ifDisplay =='1'}">
														<c:if test="${smallConfig.ifPopWindow =='1'}">
															<a target="_blank" href="${itemSmall.httpUrl}">${itemSmall.typeName}</a>
														</c:if>
														<c:if test="${smallConfig.ifPopWindow !='1'}">
															<a href="${itemSmall.httpUrl}">${itemSmall.typeName}</a>
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
																href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html">${itemSmall.typeName}</a>
														</c:if>
														<c:if test="${smallConfig.ifPopWindow !='1'}">
															<a
																href="<%=basePath%>client/itembig${itemBig.classId}/itemsmall${itemSmall.typeId}.html">${itemSmall.typeName}</a>
														</c:if>
													</c:if>
												</logic:iterate>
											</c:when>
										</c:choose>
									</logic:iterate>
								</div>
							</div>
						</div>
					</li>
				</logic:iterate>
				<li><a href="http://www.baidu.com"><span>满天星文学社</span> </a>
				</li>
				<div class="clear"></div>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>


