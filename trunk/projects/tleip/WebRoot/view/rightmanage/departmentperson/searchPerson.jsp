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
		<title>人员列表</title>
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
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.form.js"></script>
		<script type="text/javascript">   
		function checkDelNum()
        {
            var chkbs = document.getElementsByName("check");   
            var chkNum = 0;   
            for(i=0;i<chkbs.length;i++)
            {
              if(chkbs(i).checked)
                chkNum++;
            }
            if(chkNum<1)
            {
              alert("没有选择记录，不能保存!");
              return false;
            }
            else{
               return true;
            }  
        }
                
        function add()
        {  
            if(checkDelNum()){
	            var deptId = document.getElementsByName("basicDepartment.deptId")[0].value;          
	            rightManageForm.action="<%=basePath%>view/rightManage.do?method=addPersonToDepartment&deptId="+deptId;
	            rightManageForm.submit();
            }
        }
        
        function back()
        {
            var deptId = document.getElementsByName("basicDepartment.deptId")[0].value; 
            rightManageForm.action="<%=basePath%>view/rightManage.do?method=queryDepartmentPerson&deptId="+deptId;
            rightManageForm.submit();
        }
        </script>
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
										<td width="514" height="24" class="dh1">
											系统管理-&gt;部门人员-&gt;
											<span class="chuti lv">人员列表</span>${showMsg }
										</td>
										<td align="right">
											<input name="button" type="button" class="an" id="button"
												onclick="javascript:add();" value="确定" />
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
							<td class="x"></td>
						</tr>

						<tr>
							<td>
								<html:form action="view/rightManage.do?method=queryPerson"
									method="post">

									<table cellSpacing="0" cellPadding="0" border="0" width="97%"
										align="center">
										<tr>
											<td>
												<table cellSpacing="0" cellPadding="0" width="100%"
													border="0">
													<tr align="left">
														<td>
															当前部门:
															<font color="#ff6600"> <html:hidden
																	property="basicDepartment.deptId" />
																${rightManageForm.basicDepartment.deptName} </font>
														</td>
													</tr>

												</table>
												<table bgcolor="#32AEF4" cellSpacing="0" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td class="chutibai tdwidth45 tdbk">
															<input type="checkbox" onclick="checkAll(this,'check');"
																id="checkall" alt="全选">
														</td>
														<td class="chutibai tdbk">
															人员编号
														</td>
														<td class="chutibai tdbk">
															所属部门
														</td>

														<td class="chutibai tdbk">
															人员名称
														</td>

													</tr>

													<logic:iterate id="r" name="pageList" property="list"
														indexId="index">
														<c:if test="${(index+1)%2 == 0}">
															<tr class="cstd1" onMouseOver="this.className='cstd2'"
																onMouseOut="this.className='cstd1'">
																<td class="tdcenter tdbk">
																	<input type="checkbox" name="check"
																		value="${r.personId}">
																</td>
																<td class="tdcenter tdbk">
																	${r.personCode}
																</td>
																<td class="tdcenter tdbk">
																	${r.basicDepartment.deptName}
																</td>
																<td class="tdcenter tdbk">
																	${r.personName}
																</td>
															</tr>
														</c:if>
														<c:if test="${(index+1)%2 !=0}">
															<tr class="trcolor" onMouseOver="this.className='cstd2'"
																onMouseOut="this.className='trcolor'">
																<td class="tdcenter tdbk">
																	<input type="checkbox" name="check"
																		value="${r.personId}">
																</td>
																<td class="tdcenter tdbk">
																	${r.personCode}
																</td>
																<td class="tdcenter tdbk">
																	${r.basicDepartment.deptName}
																</td>
																<td class="tdcenter tdbk">
																	${r.personName}
																</td>
															</tr>
														</c:if>
													</logic:iterate>
												</table>
											</td>
										</tr>
										<tr>
											<td align="right">
												${pageList.view }
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
