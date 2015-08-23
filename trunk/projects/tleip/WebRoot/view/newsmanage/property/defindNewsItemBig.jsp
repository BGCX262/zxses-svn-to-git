<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css" />
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
				var yxdm = document.getElementById('yxdm').value;
				if ( yxdm == 0){
					alert("请选择院系！");
					document.getElementById('yxdm').focus();
					return false;
				}
				window.location = "<%=basePath%>view/newsmanage.do?method=findNewsItemBigById&yxdm="+yxdm;
				return false;
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
	        
	        function browse(classId)
	        { 
				 window.location =  "<%=basePath%>view/newsmanage.do?method=findAmendItemBigById&classId="+classId+"&optMark=N";
	        }
	        function edit(){
	      		var className="";
	        	var classNames=document.getElementsByName('className');
	        	var classId = ""; 
	        	var chkbs = document.getElementsByName("check"); 
	            var chkNum = 0;   
	            for(i=0;i<chkbs.length;i++){
	              if(chkbs(i).checked){
	            	chkNum++;
	            	classId = chkbs(i).value;
	            	className=classNames(i).value;
	              }
	            }
	            if(chkNum<1){
	              	alert("请选择一条记录来修改!");
	              	return false;
	            }else if (chkNum > 1){
	            	alert("只能选择一条记录来修改!");
	              	return false;
	            }else{
				 	newsmanageForm.action =  "<%=basePath%>view/newsmanage.do?method=findAmendItemBigById&optMark=Y&classId="+classId+"&className="+className;
	               newsmanageForm.submit();
	            }
	        }
	        function order()
	        { 	
	        	 var yxdm = document.getElementById('yxdm').value;
				 //window.location =  "<%=basePath%>view/newsmanage.do?method=findOrderItemBig&yxdm="+yxdm;
				 showloading("<%=basePath%>commons/ajaxload.jsp",200,200); 
				 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findOrderItemBig&yxdm="+yxdm;
	             newsmanageForm.submit();
	        }
	        function del()
	        {
	           if(checkDelNum())
	          {   
	          	var yxdm = document.getElementById('yxdm').value;
	             if (confirm("确定删除这些记录吗？")==false) return false;
	             showloading("<%=basePath%>commons/ajaxload.jsp",200,200);  
	          	 newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=deleteNewsItemBig&yxdm="+yxdm;
	             newsmanageForm.submit();
	             return false;
	          }
	          else
	          {
	             return false;
	          }
	        } 
			function changeType(obj){
			  
				newsmanageForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsItemBig&yxdm="+obj.value;
	           
	           if(obj.value=="10579")
			  {
	             window.location="<%=basePath%>view/newsmanage.do?method=findNewsItemBig";
	             }
	            newsmanageForm.submit();
	            return false;
			}
		</script>
	</head>

	<body  class="centerbg">
		<html:form action="view/newsmanage.do?method=findNewsItemBig"
			method="post">
			<table class="pathbg">
				<tr align="left">
					<td>
						新闻发布管理-&gt;<span class="lv chuti">大类管理</span>${showMsg }
					</td>
					<td>
						<div class="right_operate">
							<img src="<%=basePath%>res/admin/img/add.gif"
								onclick="javascript:add()" style="cursor: pointer" alt="新增"
								title="新增" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/update.gif"
								onclick="javascript:edit()" style="cursor: pointer" alt="修改"
								title="修改" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/delete.gif"
								onclick="javascript:del()" style="cursor: pointer" alt="删除"
								title="删除" />
							&nbsp;
							<img src="<%=basePath%>res/admin/img/order.gif" 
							onclick="javascript:order()" style="cursor: pointer" alt="排序" title="排序" />
							&nbsp;
						</div>
					</td>
				</tr>
			</table>
			
			<input type="hidden" name="ifIndex" value="0" />
			<input type="hidden" id="yxdm" name="yxdm" value="${yxdm}"/>
			
			<table class="percentage98">
				<tr>
					<td class="height8"></td>
				</tr>

		<tr>
	        <td class="tdcenter">
		        所属单位：
				<select name="yxdm" style="width: 170px; text-align: left;" onchange="changeType(this)">
					${allYxdmSelect}
				</select>
	          </td>
	      </tr>

				<tr>
					<td class="x"></td>
				</tr>
				<tr>
					<td>
						<table bgcolor="#32AEF4" class="percentage100">

							<tr>
								<td class="chutibai tdwidth45 tdbk">
									<input type="checkbox" onclick="checkAll(this,'check');"
							id="checkall" alt="全选"/>
								</td>
								<td class="chutibai tdwidth45 tdbk">
									序号
								</td>

								<td class="chutibai tdbk">
									大类编号
								</td>
								<td class="chutibai tdbk">
									大类名称
								</td>
								<td class="chutibai tdbk">
									显示顺序
								</td>
								<td class="chutibai tdbk">
									是否显示
								</td>
								<td class="chutibai tdwidth160 tdbk">
									是否在导航菜单显示
								</td>

							</tr>

							<logic:iterate id="column" name="pageList" property="list" indexId="newsIndex">
							<c:if test="${(newsIndex+1)%2 == 0}">
								<tr class="cstd1" onmouseover="this.className='cstd2'"
									onmouseout="this.className='cstd1'">
								<td class="tdcenter tdwidth45 tdbk">
										<input type="checkbox" value="${column.classId}" name="check" />
									</td>
									<td class="tdcenter tdbk">
										${newsIndex+1}
									</td>

									<td class="tdcenter tdbk">
										<a href='#'  onclick ="browse('${column.classId }');return false; ">${column.classId }</a>
									</td>
									<td class="tdcenter tdbk">
										<a href='#'  onclick ="browse('${column.classId }');return false; ">${column.className }</a>
										<input type="hidden" id="className" name="className" value="${column.className }"/>
									</td>
									<td class="tdcenter tdbk">
										${column.orderId }
									</td>
									<td class="tdcenter tdbk">
										<span style="cursor: pointer; text-decoration: underline; color: ${ column.ifDisplay ==   1 ?"blue" : "red"}"
									 onclick="changeStatusSyn(this,'<%=basePath%>view/newsmanage.do?method=displayItemBig&classId=${column.classId}')">${column.ifDisplay
												== 1 ? "是" : "否"}</span>
									</td>
									<td class="tdcenter tdbk">
										<span style="cursor: pointer; text-decoration: underline; color: ${ column.ifIndex ==   1 ?"blue" : "red"}"
									 onclick="changeStatusSyn(this,'<%=basePath%>view/newsmanage.do?method=indexItemBig&classId=${column.classId}')">${column.ifIndex
												== 1 ? "是" : "否"}</span>
									</td>
								</tr>	
							</c:if>		
							<c:if test="${(newsIndex+1)%2 !=0}">
								<tr class="trcolor" onmouseover="this.className='cstd2'"
									onmouseout="this.className='trcolor'">
									<td class="tdcenter tdwidth45 tdbk">
										<input type="checkbox" value="${column.classId}" name="check"/>
									</td>
									<td class="tdcenter tdbk">
										${newsIndex+1}
									</td>

									<td class="tdcenter tdbk">
										<a href='#'  onclick ="browse('${column.classId }');return false; ">${column.classId }</a>
									</td>
									<td class="tdcenter tdbk">
										<a href='#'  onclick ="browse('${column.classId }');return false; ">${column.className }</a>
										<input type="hidden" id="className" name="className" value="${column.className }"/>
									</td>
									<td class="tdcenter tdbk">
										${column.orderId }
									</td>
									<td class="tdcenter tdbk">
										<span style="cursor: pointer; text-decoration: underline; color: ${ column.ifDisplay ==   1 ?"blue" : "red"}"
									 onclick="changeStatusSyn(this,'<%=basePath%>view/newsmanage.do?method=displayItemBig&classId=${column.classId}')">${column.ifDisplay
												== 1 ? "是" : "否"}</span>
									</td>
									<td class="tdcenter tdbk">
										<span style="cursor: pointer; text-decoration: underline; color: ${ column.ifIndex ==   1 ?"blue" : "red"}"
									 onclick="changeStatusSyn(this,'<%=basePath%>view/newsmanage.do?method=indexItemBig&classId=${column.classId}')">${column.ifIndex
												== 1 ? "是" : "否"}</span>
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
