<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.apache.commons.lang.xwork.StringUtils"%>
<!-- 
需要传递过来的参数如下：
	listName:		显示树的数据集合(*必填)
	nodeId:			树节点的Id,也就是需要显示listName集合中的哪个属性名称(*必填)
	parentNodeId:	节点的父节点Id,也就是需要显示listName集合中的哪个属性名称(*必填)
	nodeName:		显示树节点的名称,也就是需要显示listName集合中的哪个属性名称(*必填)
	childs			注：是用于async主题的，此值是对象的子对象名称(可选)。(此主题时必填)。
	theme:			选择显示树的主题样式，主要包括:simple、check风格(可选)
	url:			指定节点被点击后的跳转页面url地址(可选)
	target:			用于设置点击节点后 url 跳转的目标(可选)
	open:			用于记录 treeNode 节点的 展开 / 折叠 状态(可选)
	inputName:		注：是针对select主题的，选择值之后的值id，选择的值可以在后台同此值的名称接收(可选)
	selectNode:		默认选中某一个节点的Id值(可选)
	checkNodeIds:	默认复选框选中某些节点(可选)
 -->
<%
	String theme = request.getParameter("theme");
	if (StringUtils.isEmpty(theme)) {
		theme = "simple";
	}
%>
<%
	if ("simple".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/simple.jsp" flush="true" />
<%
	} else if ("check".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/check.jsp" flush="true" />
<%
	} else if ("drag".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/drag.jsp" flush="true" />
<%
	} else if ("edit".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/edit.jsp" flush="true" />
<%
	} else if ("dragedit".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/dragedit.jsp" flush="true" />
<%
	} else if ("async".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/async.jsp" flush="true" />
<%
	} else if ("select".equals(theme)) {
%>
<jsp:include page="/tag/tree/theme/select.jsp" flush="true" />
<%
	} else {
%>
<jsp:include page="/tag/tree/theme/simple.jsp" flush="true" />
<%
	}
%>