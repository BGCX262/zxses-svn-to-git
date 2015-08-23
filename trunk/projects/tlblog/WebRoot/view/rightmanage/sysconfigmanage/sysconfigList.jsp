<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>系统配置</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		<script type="text/javascript" src="${basePath}view/rightmanage/sysconfigmanage/js/sysConfigList.js"></script>
		
	</head>
	<body>		
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left">
							<tr>
								<td>
								<table class="pathbg">
										<tr>
											<!-- 修改导航条信息 -->
											<td height="24" class="dh1">
												系统管理-&gt;系统设置-&gt;
												<span class="chuti lv">系统配置</span>${showMsg }
											</td>	
											<td align="right">
												<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径 加上权限控制验证-->
												<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysConfigManage','update') }">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:update();" value="修改" />
												&nbsp;
												</c:if>
											</td>	
											<td width="15"></td>									
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
								<!-- 修改form action路径 -->
								<html:form action="view/sysconfig.do?method=update" method="post">
									<!-- 主键id -->
									<input type="hidden" name="sysConfigCode" value="${sysConfigInfo.sysConfigCode }"/>
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>新浪微博地址:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="sinaWeibo" id="sinaWeibo" value="${sysConfigInfo.sinaWeibo }" size="50" maxlength="150" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>QQ在线地址:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="onlineQq" id="onlineQq" value="${sysConfigInfo.onlineQq }" size="50" maxlength="150" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>QQ图标地址:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="picQq" id="picQq" value="${sysConfigInfo.picQq }" size="50" maxlength="150" />
											</td>
										</tr>
									</table>
								</html:form>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
	</body>
</html>







