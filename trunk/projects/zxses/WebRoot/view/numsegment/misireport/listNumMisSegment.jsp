<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<title>号码段打印</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>js/core.js"></script>
		<script type="text/javascript">
		    function misSegmentPrint(actionUrl){
		    	if(isAcrobatPluginInstall()){
		    		document.myform.action= actionUrl;
       				document.myform.target="_blank";
		    		document.myform.submit();
		    	}else{
		    		if(confirm("没有安装预览插件,是否下载？")){
       					document.myform.action= 'tools/pdftool/AdbeRdr930_zh_CN.exe';
       					document.myform.target="_blank";
		    			document.myform.submit();
       				}
       			}
       		}
		 </script>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="打印--javascript:misSegmentPrint('view/misIReport!manPrint.action?manufacture.id=${manufacture.id }'),
				返回--view/misIReport!listMan.action?theme=simple" />
		</jsp:include>

		<s:form action="misIReport!listNumMisSegment.action" method="post"
			id="myform">
			<table id="mytable">
				<tr>
					<th width="120">
						箱号
					</th>
					<th>
						起始号
					</th>
					<th>
						检码
					</th>
					<th>
						终止号
					</th>
					<th>
						检码
					</th>
				</tr>
				<s:iterator value="numMisSegments" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:if
								test="!(#i.index gt 0 &&  numMisSegments[#i.index-1].numSegment.boxNo==numSegment.boxNo)">
							第
							<s:property value="numSegment.boxNo" />
							箱
							</s:if>
						</td>
						<td>
							<s:property
								value="numSegment.manufacture.prefix+staNumber+numSegment.manufacture.suffix" />
						</td>
						<td>
							<s:property value="staCheckCode" />
						</td>
						<td>
							<s:property
								value="numSegment.manufacture.prefix+endNumber+numSegment.manufacture.suffix" />
						</td>
						<td>
							<s:property value="endCheckCode" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url"
				value="view/misIReport!listNumMisSegment.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
