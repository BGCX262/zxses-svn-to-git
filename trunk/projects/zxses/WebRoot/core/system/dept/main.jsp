<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>部门管理</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript">
		function beforeRemove(treeId, treeNode) {
			var returnState=true;
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.selectNode(treeNode);
			if(confirm("是否确认删除！")){
				$.ajax({
					type : "post",
					async : false,
					data : "checkIds="+treeNode.id,
					url:"view/hrDept!deleteHrDept.action",
					success:function(msg) {
						if("fail"==msg){
							alert('删除失败!');
							returnState = false;
						}else{
							$("#rightIframe").attr("src","view/hrDept!deptRight.action");
						}
					}
				});
			}else{
				return false;
			}
			return returnState;
		}
		
		function beforeDrop(treeId, treeNodes, targetNode, moveType) {
			var returnState=true;
			$.ajax({
				type : "post",
				async : false,
				data : "dragType="+moveType+"&sourceNodeId="+treeNodes[0].id+"&targetNodeId="+targetNode.id,
				url:"view/hrDept!updateDragHrDept.action",
				success:function(msg) {
					if("fail"==msg){
						alert('拖拽失败!');
						returnState = false;
					}
				}
			});
			return returnState;
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
    		$("#rightIframe").attr("src","view/hrDept!entryUpdate.action?hrDept.id="+treeNode.id);
		}
		
		function addHrDept(){
			$("#rightIframe").attr("src","view/hrDept!deptRight.action");
		}
		</script>
	</head>

	<body class="centerbg">
		<div id="fixed">
			<table class="pathbg">
				<tr>
					<td align="left">
						${navigationBar}
					</td>
					<td>
						<div class="rightan">
							<a href="javascript:addHrDept()" class="leftmeun chuti font12">新增</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="height: 30px"></div>
		<div>
			<table width="100%" height="100%">
				<tr>
					<td class="height8" colspan="2" valign="top" width="20%">
						<jsp:include page="/tag/tree/ztree.jsp">
							<jsp:param name="listName" value="deptList" />
							<jsp:param name="nodeId" value="id" />
							<jsp:param name="parentNodeId" value="hrDept.id" />
							<jsp:param name="nodeName" value="name" />
							<jsp:param name="theme" value="dragedit" />
							<jsp:param name="open" value="true" />
						</jsp:include>
					</td>
					<td valign="top">
						<iframe name="rightIframe" frameborder="0"
							src="view/hrDept!deptRight.action" width="96%" height='300'
							id="rightIframe" style="background-color: white;"></iframe>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
