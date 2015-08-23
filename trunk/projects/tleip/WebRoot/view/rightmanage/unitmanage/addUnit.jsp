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
		<title>新增单位</title>
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
	<script type="text/javascript">
       function add()
       {
           var dm = document.getElementsByName("basicUnit.unitCode")[0].value;
           if(checkText())
           {
                $.post("<%=basePath%>view/rightManage.do?method=checkCode",{dm:dm,code:"unitCode",object:"BasicUnit"},function(response)
                {
		            if(response=="exist"){
		                alert("此编码已存在，请重新输入！");
		            }else{                            
		               rightManageForm.submit();
		            }
	            });
           }
       }
       
       function checkEmail(str){
          var strEmail = str;
          if(strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
              return true;
		  }else{
		      alert("错误提示：请输入有效的邮箱地址！");		           
		      return false;
		  }
       }
       function checkText()
       {
		   var unitCode = document.getElementsByName("basicUnit.unitCode")[0].value;
		   var unitName = document.getElementsByName("basicUnit.unitName")[0].value;
		   var strEmail = document.getElementsByName("basicUnit.email")[0].value;
		   if(unitCode == "" || unitName==""){		   
		       alert("带*号的不能为空！");
		       return false;
		   }else{
		       if(strEmail !=""){
			       if(checkEmail(strEmail)){
			           return true;
			       }
		       }else{
		           return true;
		       }	       
		   }
	   }
	   function back(){
		 	window.location = "<%=basePath%>view/rightManage.do?method=queryUnit";
		 }
     </script>
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
											<span class="chuti lv">新增单位</span>${showMsg }
										</td>
										<td align="right">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:add();" value="保存" />
											&nbsp;
											<input name="button2" type="button" class="an" id="button2"
												value="返回" onclick="javascript:back();" />
										</td>
										<td width="30"></td>
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
											<html:form action="view/rightManage.do?method=addUnit"
												method="post">
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0" align="center">
													<tr>
														<td class="td_left">
															<font color="#ff0000">* </font>学校编号
														</td>
														<td class="td_right">
															<html:text property="basicUnit.unitCode" maxlength="20"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															<font color="#ff0000">* </font>学校名称
														</td>
														<td class="td_right">
															<html:text property="basicUnit.unitName" maxlength="50"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															负责人
														</td>
														<td class="td_right">
															<html:text property="basicUnit.unitMaster" maxlength="20"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															邮编
														</td>
														<td class="td_right">
															<html:text property="basicUnit.postcode" maxlength="10"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															联系人
														</td>
														<td class="td_right">
															<html:text property="basicUnit.linkman" maxlength="30"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															电话
														</td>
														<td class="td_right">
															<html:text property="basicUnit.telephone" maxlength="15"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															传真
														</td>
														<td class="td_right">
															<html:text property="basicUnit.fax" maxlength="15"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															Email
														</td>
														<td class="td_right">
															<html:text property="basicUnit.email" maxlength="40"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															主页地址
														</td>
														<td class="td_right">
															<html:text property="basicUnit.homepage" maxlength="50"></html:text>
														</td>
													</tr>
													<tr>
														<td class="td_left">
															简介
														</td>
														<td class="td_right">
															<html:textarea property="basicUnit.remark"
																style="height: 120px ; width:100%"></html:textarea>
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
