<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="tree" uri="http://lcsoft.com/control/tag/tree"%>
<link rel="stylesheet" href="tag/tree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="tag/tree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="tag/tree/js/jquery.ztree.core-3.0.js"></script>
<script type="text/javascript">
		<!--
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				selectedMulti: false
			},
			callback: {
				onClick: zTreeOnClick
			}
		};

	<tree:tree listName="${param.listName}" nodeId="${param.nodeId}"
		parentNodeId="${param.parentNodeId}" nodeName="${param.nodeName}" url="${param.url}" target="${param.target}"
		open="${param.open}">
	</tree:tree>
	
		$(document).ready(function(){
			var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var nodes = zTreeObj.getNodes();
			for(var i=0;i<nodes.length;i++){
				if(${param.selectNode}==nodes[i].id){
					zTreeObj.selectNode(nodes[i]);
				}
			}
		});

		//-->
	</script>

<ul id="treeDemo" class="ztree"></ul>
