<div id="fixed">
<table class="pathbg">
			<tr>
				<td align="left">
					${navigationBar} 
				</td>
				<td>
					<div class="rightan">
				  <%
				   String buttonString="";
				  if(request.getParameter("buttons")!=null){
				 	 String buttons = request.getParameter("buttons") ; 
				     String[] buttonsArray = buttons.split(",");
				     for(int i=0;i<buttonsArray.length;i++){
				       String buttonElement =  buttonsArray[i];
				       String[] button = buttonElement.split("--");
				       buttonString+="<a href="+button[1]+" class=\"leftmeun chuti font12\">"+button[0]+"</a>&nbsp;&nbsp;";
				     }
				    }%>
				    <%=buttonString %>
					</div>
				</td>
			</tr>
		</table>
</div>
<div style="height:30px "></div>

<center>
	<div id="msgdiv" style="line-height: 22px; color: white;">
		<%
			if (request.getAttribute("messageInfo") != null) {
		%>
		<span
			style="vertical-align: middle; padding: 3px 25px 3px 25px; background-color: #68AF02;">${messageInfo}</span>
		<%
			}
		%>
		<%
			if (request.getAttribute("messageError") != null) {
		%>
		<span
			style="vertical-align: middle; padding: 3px 25px 3px 25px;  background-color: #eaa000">${messageError}</span>
		<%
			}
		%>
	</div>
</center>
