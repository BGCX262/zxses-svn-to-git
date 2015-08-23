<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="tree" uri="http://lcsoft.com/control/tag/tree"%>
<link rel="stylesheet" href="tag/tree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="tag/tree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="tag/tree/js/jquery.ztree.core-3.0.js"></script>
<style type="text/css">
.ztree{
margin-top: 10px;border: 1px solid #617775;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;
}
</style>
<script type="text/javascript">
		<!--
		var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

	<tree:tree listName="${param.listName}" nodeId="${param.nodeId}"
		parentNodeId="${param.parentNodeId}" nodeName="${param.nodeName}" url="${param.url}" target="${param.target}"
		open="${param.open}">
	</tree:tree>
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		function beforeClick(treeId, treeNode) {
			var check = (treeNode && !treeNode.isParent);
			if (!check) alert("只能选择子节点...");
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "",h = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				h += nodes[i].id + ",";
			}
			if (v.length > 0 ) {
				v = v.substring(0, v.length-1);
				h = h.substring(0, h.length-1);
			}
			var dataObj = $("#dataSel");
			var hiddenValueObj = $("#hiddenValue");
			hiddenValueObj.attr("value",h);
			dataObj.attr("value", v);
			hideMenu();
		}

		function showMenu() {
			var dataObj = $("#dataSel");
			var dataOffset = $("#dataSel").offset();
			$("#menuContent").css({left:dataOffset.left + "px", top:dataOffset.top + dataObj.outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent"
				|| event.target.id == "dataSel" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		//-->
	</script>
<input id="dataSel" type="text" readonly value="" style="width: 160px;" onfocus="showMenu()"/>
<input type="hidden" name="${param.inputName}" id="hiddenValue"/>
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
</div>