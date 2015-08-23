<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>信息发布管理系统</title>
		<link href="<%=basePath%>res/theme/blue/css/css.css" type="text/css" rel="stylesheet" />
	</head>
	<body>

		<!-- left menu -->
		<logic:iterate id="maps" name="modules">
			<logic:iterate id="map" name="maps">
				<table class="lefttable">
					<tr>
						<td class="leftmenu">
							<bean:write name="map" property="key" />
						</td>
					</tr>
					<tr>
						<td class="top">

							<bean:size id="listsize" collection="${map.value}" />
							
							<logic:iterate id="list" name="map" property="value" indexId="listIndex">

								<logic:notEqual value="${listsize-1}" name="listIndex">
									<table>
										<tr>
											<td class="leftcenterline"></td>
											<td>
												<div class="lefticon">
													<a href="${list.url}" class="leftmeun1" target="right">${list.moduleName}</a>
												</div>
											</td>
										</tr>
									</table>
								</logic:notEqual>

								<logic:equal value="${listsize-1}" name="listIndex">
									<table>
										<tr>
											<td class="leftbottomline"></td>
											<td>
												<div class="lefticon">
													<a href="${list.url}" class="leftmeun1" target="right">${list.moduleName}</a>
												</div>
											</td>
										</tr>
									</table>
								</logic:equal>
								
							</logic:iterate>

						</td>
					</tr>
				</table>
			</logic:iterate>
		</logic:iterate>

	</body>
</html>

