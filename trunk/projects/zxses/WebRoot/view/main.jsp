<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title></title>
     

  </head>
  
<frameset rows="125,*,21"    cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="view/main!top.action" id="top" name="top" scrolling="no" noresize="noresize" />
	
		<frameset  cols="167,12,*" frameborder="no" border="0" framespacing="0"  id="frame2" >
			<frame src="view/left.jsp" id="left" name="left" noresize="noresize"/>
			<frame src="view/scroll.html" id="scroll" name="scroll" scrolling="no"  noresize="noresize" />
			<frame src="view/center.html" id="center" name="center"/>
		</frameset>
		
	<frame src="view/bottom.html" id="bottom"name="bottom" scrolling="no" noresize="noresize"/>
</frameset>


<noframes><body>
</body>
</noframes>
</html>
