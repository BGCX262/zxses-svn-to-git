<%@ page language="java" pageEncoding="UTF-8"%>
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
		<title>单位信息</title>
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
		<script type="text/javascript">
           
        function add(){
          if(!checkright('<%=basePath%>',"dwxx","add")){
             return false;
          }         
             window.location.href="<%=basePath%>view/rightManage.do?method=enterAddUnit";
        }
        
        var editvalue;
        function checkNum(){
            var chkbs = document.getElementsByName("check");   
            var chkNum = 0;   
            for(i=0;i<chkbs.length;i++)
            {
              if(chkbs(i).checked){
                chkNum++;
                editvalue=chkbs[i].value;
                }
            }
            if(chkNum<1)
            {
              alert("请选择一条记录!");
              return false;
            }
            else if(chkNum>1)
            {
               alert("您一次只能选择一条记录！");
            }
            else{
               return true;
            }          
        }
        
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
              alert("请选择一条记录!");
              return false;
            }
            else{
               return true;
            }  
        }
        
        function update()
        { 
          if(checkNum())
          {
             if(!checkright('<%=basePath%>',"dwxx","update"))
             {
                return false;
             }
             window.location.href="<%=basePath%>view/rightManage.do?method=enterUpdateUnit&unitId="+ editvalue;
          }
          else
          {
             return false;
          }
        }
        
        function del()
        {
          if(checkDelNum())
          {
             if(!checkright('<%=basePath%>',"dwxx","delete"))
             {
                return false;
             }
             if(window.confirm("确定删除这些数据吗？"))
             {
             rightManageForm.action="<%=basePath%>view/rightManage.do?method=deleteUnit";
             rightManageForm.submit();
             }
             else
             {
             return false;
             }
          }
          else
          {
             return false;
          }
        } 
 
       function view(id){
         showwindow("<%=basePath%>view/rightManage.do?method=queryUnitDetailed&unitId="+id,700,500);
       }
     </script>
	</head>
	<body>
		<html:form action="view/rightManage.do?method=queryUnit" method="post">
			<table class="pathbg">
				<tr>
					<td>
						系统管理-&gt;
						<span class="chuti lv">单位信息列表</span>${showMsg }
					</td>
					<td>
						<div class="right_operate">
							<img src="<%=basePath%>res/admin/img/add.gif"
								onclick="javascript:add()" style="cursor: pointer" alt="新增"
								title="新增" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/update.gif"
								onclick="javascript:update()" style="cursor: pointer" alt="修改"
								title="修改" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/delete.gif"
								onclick="javascript:del()" style="cursor: pointer" alt="删除"
								title="删除" />
							&nbsp;
						</div>
					</td>
				</tr>
			</table>
			<table class="percentage98">
				<tr>
					<td class="height8"></td>
				</tr>

				<tr>
					<td class="x"></td>
				</tr>
				<tr>
					<td>
						<table bgcolor="#32AEF4" class="percentage100">

							<tr>
								<td class="chutibai tdwidth30 tdbk">
									<input type="checkbox" onclick="checkAll(this,'check');"
										id="checkall" alt="全选">
								</td>
								<td class="chutibai tdwidth45 tdbk">
									序号
								</td>

								<td class="chutibai tdbk">
									单位编号
								</td>
								<td class="chutibai tdbk">
									单位名称
								</td>
								<td class="chutibai tdbk">
									负责人
								</td>
								<td class="chutibai tdbk">
									操作
								</td>
							</tr>
							<logic:iterate id="column" name="pageList" property="list"
								indexId="newsIndex">
								<c:if test="${(newsIndex+1)%2 == 0}">
									<tr class="cstd1" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='cstd1'">
										<td class="tdcenter tdbk">
											<input type="checkbox" value="${column.unitId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.unitCode }
										</td>
										<td class="tdcenter tdbk">
											${column.unitName }
										</td>
										<td class="tdcenter tdbk">
											${column.unitMaster }
										</td>
										<td class="tdcenter tdbk">
											<a href="javascript:view('${column.unitId}')"> <font
												color="blue">查看详细</font>
										</td>
									</tr>
								</c:if>
								<c:if test="${(newsIndex+1)%2 !=0}">
									<tr class="trcolor" onMouseOver="this.className='cstd2'"
										onMouseOut="this.className='trcolor'">
										<td class="tdcenter tdbk">
											<input type="checkbox" value="${column.unitId}" name="check">
										</td>
										<td class="tdcenter tdbk">
											${newsIndex+1}
										</td>

										<td class="tdcenter tdbk">
											${column.unitCode }
										</td>
										<td class="tdcenter tdbk">
											${column.unitName }
										</td>
										<td class="tdcenter tdbk">
											${column.unitMaster }
										</td>
										<td class="tdcenter tdbk">
											<a href="javascript:view('${column.unitId}')"> <font
												color="blue">查看详细</font>
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
	</body>
</html>
