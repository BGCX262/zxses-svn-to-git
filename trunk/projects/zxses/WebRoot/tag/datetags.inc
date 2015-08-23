<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>tools/jscal/css/jscal2.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>tools/jscal/css/border-radius.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>tools/jscal/css/steel/steel.css" />
<script language="javascript" type="text/javascript" src="<%=basePath%>tools/jscal/js/jscal2.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>tools/jscal/js/lang/cn.js"></script>

