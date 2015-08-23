<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>塘朗小学文学社管理系统</title>
		<link href="<%=basePath%>res/admin/theme/blue/css/css.css" type="text/css" rel="stylesheet" />
	</head>
	<body>

		<!-- left menu -->
		
		

	
		<table class="lefttable">
			<tbody>
				<tr>
					<td class="leftmenu">
						${sysFirstModuleTitle }
					</td>
				</tr>
				<tr>
					<td class="top">
						<c:if test="${!empty sysSecondModules}">
						<c:forEach items="${sysSecondModules }" var="entity" varStatus="status">
							<c:choose>
								<c:when test="${fn:length(sysSecondModules) - 1 != status.index}">
									<table>
										<tbody>
											<tr>
												<td class="leftcenterline"></td>
												<td>
													<div class="lefticon">
														<a target="right" class="leftmeun1"
															href="<%=basePath %>${entity.url }">${entity.name }</a>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<table>
										<tbody>
											<tr>
												<td class="leftbottomline"></td>
												<td>
													<div class="lefticon">
														<a target="right" class="leftmeun1"
															href="<%=basePath %>${entity.url }">${entity.name }</a>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>

