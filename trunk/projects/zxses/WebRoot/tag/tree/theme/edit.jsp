<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="tree" uri="http://lcsoft.com/control/tag/tree"%>
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
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
				beforeDrag:beforeDrag
			}
		};

	<tree:tree listName="${param.listName}" nodeId="${param.nodeId}"
		parentNodeId="${param.parentNodeId}" nodeName="${param.nodeName}" url="${param.url}" target="${param.target}"
		open="${param.open}">
	</tree:tree>
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});

		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if ($("#addBtn_"+treeNode.id).length>0) return;
			var addStr = "<button type='button' class='add' id='addBtn_" + treeNode.id
				+ "' title='add node' onfocus='this.blur();'></button>";
			sObj.append(addStr);
			var btn = $("#addBtn_"+treeNode.id);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.id).unbind().remove();
		};
		
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		//-->
	</script>
<ul id="treeDemo" class="ztree"></ul>