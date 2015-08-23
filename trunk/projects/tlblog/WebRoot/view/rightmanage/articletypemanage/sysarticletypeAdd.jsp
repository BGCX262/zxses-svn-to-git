<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.agilefly.commons.SysConstant"%>
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
		<title>新增博客类型</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script language="JavaScript" type="text/JavaScript"> 
		  function add()
		  {		    
		  	 if(check()){
	    	 	sysBlogTypeForm.submit();
	    	 }
		  }
		  function back()
          {
		     window.location.href="<%=basePath%>view/sysblogtype.do";
		  }	
		  function check(){
		  	//简单验证
			var typeName = $("#typeName").val();
			var typeOrder = $("#typeOrder").val();
			//var checkCode = $("#extFirst").val();
			
			if($.trim(typeName) == ""){
				alert("请输入类型名称！");
				return false;
			}
			if($.trim(typeOrder) == ""){
				alert("请输入排序号！");
				return false;
			}else{
				if(!validate.testIntegerNumber($.trim(typeOrder))){
					alert("请输入整数！");
					return false;
				}
			}
			return true;
		  }
		</script>
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
												系统管理-&gt;博客管理-&gt;
												<span class="chuti lv">新增文章类型</span>${showMsg }
											</td>	
											<td align="right">
												<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径 加上权限控制验证-->
												<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysBlogTypeManage','add') }">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:add();" value="确定" />
												&nbsp;
												</c:if>
												<input name="button2" type="button" class="an" id="button2"
													value="返回" onclick="javascript:back();" />
											</td>	
											<td width="15"></td>									
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
								<!-- 修改对应的aciton路径 -->
								<html:form action="view/sysblogtype.do?method=add" method="post">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<!-- 具体录入字段入口 -->
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>类型名称:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="typeName" id="typeName" size="30" maxlength="20" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>类型编码:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="typeCode" id="typeCode" size="30" maxlength="20" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															类型描述:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="typeDes" id="typeDes" size="30" maxlength="20" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>排序号:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<input type="text" name="typeOrder" id="typeOrder" size="30" maxlength="20" />
														</td>
													</tr>
													<tr>
														<td width="20%" class="td_left">
															<font color="#ff0000">* </font>用户类型:&nbsp;
														</td>
														<td width="30%" class="td_right">
															<select id="extFirst" name="extFirst" style="width: 100px">
																<option value="student">学生</option>
																<option value="teacher">教师</option>
															</select>
														</td>
													</tr>
												</table>
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







