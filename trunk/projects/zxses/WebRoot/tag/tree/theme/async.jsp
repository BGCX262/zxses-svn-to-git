<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="tree" uri="http://lcsoft.com/control/tag/tree"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="tag/tree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<style type="text/css">
.ztree li button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -112px 0;
	vertical-align: top;
	vertical-align: middle;
}
</style>

<script type="text/javascript" src="tag/tree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="tag/tree/js/jquery.ztree.core-3.0.js"></script>
<script type="text/javascript"
	src="tag/tree/js/jquery.ztree.excheck-3.0.js"></script>
<script type="text/javascript"
	src="tag/tree/js/jquery.ztree.exedit-3.0.js"></script>
<script type="text/javascript">
		<!--
		var setting = {
			async: {
				enable: true,
				url:"${param.asyncUrl}",
				autoParam:["id=parentNodeIdValue"],
				otherParam:{"nodeId":"${param.nodeId}","parentNodeId":"${param.parentNodeId}"
							,"nodeName":"${param.nodeName}","listName":"${param.listName}"
							,"childs":"${param.childs}"
							,"url":"${param.url}","target":"${param.target}"}
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
			}
		};

		var zNodes = <s:action name="zTree" namespace="/tree">
					<s:param name="nodeId">${param.nodeId}</s:param>
					<s:param name="parentNodeId">${param.parentNodeId}</s:param>
					<s:param name="nodeName">${param.nodeName}</s:param>
					<s:param name="listName">${param.listName}</s:param>
					<s:param name="childs">${param.childs}</s:param>
					<s:param name="url">${param.url}</s:param>
					<s:param name="target">${param.target}</s:param>
					</s:action>;
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//-->
	</script>
<ul id="treeDemo" class="ztree"></ul>