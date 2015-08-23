<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>角色授权管理</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript">
		
		function zTreeOnClick(event, treeId, treeNode) {
    		$("#rightIframe").attr("src","view/sysRole!right.action?roleId="+treeNode.id);
		};
		
		
		function addSysMenu(){
			var treeObj = window.rightIframe.$.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			var tmpCheckNodeIds=",";
			for (var i=0;i<nodes.length;i++) {
				if(nodes[i].checked){
					tmpCheckNodeIds+=nodes[i].id+",";
				}
			}
			$("input[name=checkNodeIds]",window.rightIframe.document)[0].value=tmpCheckNodeIds;
			window.rightIframe.$("form")[0].submit();
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
							<a href="javascript:addSysMenu()" class="leftmeun chuti font12">保存</a>
							<a
								href="view/sysRole!listSysRole.action?theme=simple&paginate.offset=${paginate.offset }"
								class="leftmeun chuti font12"> 返回 </a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="height: 30px"></div>
		<div>
			<table width="100%" height="100%">
				<tr>
					<td class="height8" colspan="2" valign="top" width="10%">
						<jsp:include page="/tag/tree/ztree.jsp">
							<jsp:param name="listName" value="sysRoles" />
							<jsp:param name="nodeId" value="id" />
							<jsp:param name="nodeName" value="name" />
							<jsp:param name="theme" value="simple" />
							<jsp:param name="target" value="rightIframe" />
							<jsp:param name="selectNode" value="${roleId}" />
						</jsp:include>
					</td>
					<td valign="top">
						<iframe name="rightIframe" frameborder="0"
							src="view/sysRole!right.action?roleId=${roleId}" width="96%"
							height='590' id="rightIframe" style="background-color: white;"></iframe>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
