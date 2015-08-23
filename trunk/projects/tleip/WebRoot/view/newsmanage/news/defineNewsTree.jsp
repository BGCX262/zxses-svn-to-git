<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>view/newsmanage/news/zTree/zTreeStyle/zTreeStyle.css" />
		<script type="text/javascript"
			src="<%=basePath%>view/newsmanage/news/zTree/js/jquery-1.4.2.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>view/newsmanage/news/zTree/js/jquery-ztree-2.5.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>view/newsmanage/news/zTree/js/demoTools.js"></script>
		${createZTree}
		<script language="javascript">
		  <!-- 
		  		var zTree;
				var setting;
				setting = {
					editable : false,
					edit_removeBtn : false,
					expandSpeed: false,
					showLine: true,
					
					callback : 
					{
						click: zTreeOnClick,
						remove: zTreeOnRemove,
						rename: zTreeOnRename
						
					}
				};
			$(document).ready(function(){
				var setting1 = clone(setting);
				var zNodes1 = clone(zNodes);
				$("#defaultStyle").addClass("focus");
				zTree = $("#treeDemo").zTree(setting1, zNodes1);
			});
			function zTreeOnClick(event, treeId, treeNode) {
				var classId = "";
				var typeId = "";
				classId = treeNode.classId;
				typeId = treeNode.typeId;
				if (classId != null){
			    	window.parent.right.location = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage"+"&classId="+classId;
				}
				if(typeId != null){
			   		 window.parent.right.location = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage"+"&classId="+classId+"&typeId="+typeId;
				}
		    }
		    function expandAll(expandSign) {
				zTree.expandAll(expandSign);
			}
		    function zTreeOnRename(event, treeId, treeNode) {
				var classId = "";
				var typeId = "";
				var className= treeNode.name;
				var typeName= treeNode.name;
				classId = treeNode.classId;
				typeId = treeNode.typeId;
				document.getElementsByName('className')[0].value = className;
				document.getElementsByName('typeName')[0].value = typeName;
				
				if(typeId != null){
				treeForm.action = "<%=basePath%>view/newsmanage.do?method=updateItemSmall&typeId="+typeId;
				treeForm.submit();
				}
				else if(classId != null){
				treeForm.action = "<%=basePath%>view/newsmanage.do?method=updateItemBig&classId="+classId;
				treeForm.submit();
				}
			}
			function zTreeOnRemove(event, treeId, treeNode) {
				var classId = "";
				var typeId = "";
				classId = treeNode.classId;
				typeId = treeNode.typeId;
				
				if(typeId != null){
				treeForm.action = "<%=basePath%>view/newsmanage.do?method=deleteItemSmall&typeId="+typeId;
				treeForm.submit();
				}
				else if(classId != null){
				treeForm.action = "<%=basePath%>view/newsmanage.do?method=deleteItemBig&classId="+classId;
				treeForm.submit();
				}
			}
			
			function changeYxdm(obj){
				var yxdm = obj.value;
				if (yxdm == null || yxdm == ""){
					yxdm = "10579";
				}
				treeForm.action = "<%=basePath%>view/newsmanage.do?method=findNewsItemsBigTree&yxdm="+yxdm;
		        treeForm.submit();
			}
		  //-->
		  </script>
	</head>
	<body>
		<form action="view/newsmanage.do?method=findNewsContentManage"
			method="post" id="treeForm">
			<table cellspacing="0" cellpadding="0" width="100%" border="0"
				align="center" >
				<input type="hidden" name="classId" size="6" maxlength="6"
					value="${ newsItemBig.classId}" />
				<input type="hidden" name="className" />
				<input type="hidden" name="typeName" />
				<tr>
					<td valign="top">
						<table cellspacing="0" cellpadding="0" border="0" width="100%"
							align="left">
							<tr>
								<td>
									<select name="yxdm" style="width: 170px; text-align: left;"
										onchange="changeYxdm(this)">
										${allYxdmSelect}
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%">
							<!--  -->
							<tr>
								<td valign="middle">
								<br/>
									&nbsp;&nbsp;<a href="javascript: expandAll(true);" style="cursor:pointer; ">全部展开</a> | <a href="javascript: expandAll(false);" style="cursor:pointer; ">全部折叠</a>
								</td>
							</tr>
							
							<tr>
								<td align="center">
									<div class="zTreeDemoBackground">
										<ul id="treeDemo" class="tree"></ul>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
