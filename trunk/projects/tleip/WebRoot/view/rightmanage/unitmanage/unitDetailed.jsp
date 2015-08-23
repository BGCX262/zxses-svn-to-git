<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>查看单位详细信息</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css">
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
	</head>

	<body>

		<table cellSpacing="0" cellPadding="0" width="100%" border="0"
			align="center">
			<tr>
				<td valign="top">
					<table cellSpacing="0" cellPadding="0" border="0" width="100%"
						align="center">
						<tr>
							<td>
								<table class="pathbg">
									<tr>
										<td>
											系统管理-&gt;
											<span class="chuti lv">单位详细信息</span>
										</td>
										<td>
											<div class="right_operate">
												<img src="<%=basePath%>res/admin/img/close.gif"
													onclick="window.parent.hidewindow();"
													style="cursor: pointer" border="0" alt="关闭">
											</div>
										</td>
									</tr>
								</table>
						</td>
						</tr>

						<tr>
							<td height="20"></td>
						</tr>

						<tr>
							<td>
								<table cellSpacing="0" cellPadding="0" border="0" width="97%"
									align="center">
									<tr>
										<td>
											<html:form action="view/rightManage.do?method=updateUnit"
												method="post">
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" align="center">
													<tr>
														<td class="td_left" width="30%">
															单位编号
														</td>
														<td class="td_right">
															${rightManageForm.basicUnit.unitCode}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															单位名称
														</td>
														<td class="td_right">
															${rightManageForm.basicUnit.unitName}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															负责人
														</td>
														<td class="td_right">
															${rightManageForm.basicUnit.unitMaster}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															邮编
														</td>
														<td class="td_right">
															${rightManageForm.basicUnit.postcode}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															联系人
														</td>
														<td class="td_right">
															${rightManageForm.basicUnit.linkman}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															电话
														</td>
														<td class="td_right">
															${rightManageForm.basicUnit.telephone}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															传真
														</td>
														<td class="td_right">
															${systemManageForm.basicUnit.fax}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															Email
														</td>
														<td class="td_right">
															${systemManageForm.basicUnit.email}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															主页地址
														</td>
														<td class="td_right">
															${systemManageForm.basicUnit.homepage}
														</td>
													</tr>
													<tr>
														<td class="td_left" width="30%">
															简介
														</td>
														<td class="td_right">
															<html:textarea property="basicUnit.remark"
																style="height: 120px ; width:100%" disabled="disabled"
																readonly="true" />
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
				</td>
			</tr>
		</table>

	</body>
</html>
