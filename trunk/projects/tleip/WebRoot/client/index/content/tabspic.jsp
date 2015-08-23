<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/tags.inc"%>
<%@ include file="/inc/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }res/client/css/tabspic.css" />
<script type="text/javascript" src="${basePath }res/client/js/tabspic.js"></script>
<div class="container">
  	
  	<logic:iterate id="itemSmall" name="newsItemList" indexId="index">
  	<div>
		  <div class="handle" id="tabspic_${index+1 }"><p class="rotate">${itemSmall.typeName }</p></div>
		  <div class="slide">
		  	<h3>
				"${itemSmall.typeName }"
			</h3>
			<bean:include id="queryMenu"
				page="/client/newsClient.do?method=queryTabspicContent&typeId=${itemSmall.typeId}" />
			<bean:write name="queryMenu" filter="false" />
		  </div>
	</div>
	</logic:iterate>
	
	
	<div class="clear"></div>
</div>