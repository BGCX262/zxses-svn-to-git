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
		<title>修改文章类型</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>

		<script language="JavaScript" type="text/JavaScript"> 
		  function update()
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
											<span class="chuti lv">修改文章类型</span>${showMsg }
										</td>
										<td align="right">
											<!-- 工具栏按钮 注意在js函数中修改form名字，及提交路径 加上权限控制验证-->
											<c:if test="${agilefly:hasPermission(sysUserLoginInfo.id,'sysBlogTypeManage','update') }">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:update();" value="确定" />
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
								<!-- 修改form action路径 -->
								<html:form action="view/sysblogtype.do?method=update"
									method="post">
									<!-- 主键id -->
									<input type="hidden" name="id" value="${sysBlogTypeInfo.id }" />
									<input type="hidden" name="typeCode"
										value="${sysBlogTypeInfo.typeCode }" />
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>类型名称:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="typeName" id="typeName"
													value="${sysBlogTypeInfo.typeName }" size="30"
													maxlength="20" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												类型描述:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="typeDes" id="typeDes"
													value="${sysBlogTypeInfo.typeDes }" size="30"
													maxlength="20" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>排序号:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<input type="text" name="typeOrder" id="typeOrder"
													value="${sysBlogTypeInfo.typeOrder }" size="30"
													maxlength="20" />
											</td>
										</tr>
										<tr>
											<td width="20%" class="td_left">
												<font color="#ff0000">* </font>用户类型:&nbsp;
											</td>
											<td width="30%" class="td_right">
												<select id="extFirst" name="extFirst" style="width: 100px">
													<option value="student" ${sysBlogTypeInfo.extFirst==
														"student"?" selected=\"selected\"":"" }>
														学生
													</option>
													<option value="teacher" ${sysBlogTypeInfo.extFirst==
														"teacher"?" selected=\"selected\"":"" }>
														教师
													</option>
												</select>
											</td>
										</tr>
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







