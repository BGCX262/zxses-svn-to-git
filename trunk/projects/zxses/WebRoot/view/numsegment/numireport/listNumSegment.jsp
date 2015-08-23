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
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>js/core.js"></script>
		<script type="text/javascript">
		    function numSegmentPrint(actionUrl){
       			if(isAcrobatPluginInstall()){
       				if ($("input[name=checkIds]:checked").length == 0) {
						alert("请选择需要操作的记录！");
						return;
					}
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
       		
       		function updatePurAddress(actionUrl){
       			if ($("input[name=checkIds]:checked").length == 0) {
						alert("请选择需要操作的记录！");
						return;
				}
				document.myform.target="_self";
				document.myform.action= actionUrl;
		    	document.myform.submit();
       		}
		    </script>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="出货单打印--javascript:numSegmentPrint('view/numIReport!manPrint.action'),
				包装清单打印--javascript:numSegmentPrint('view/numIReport!numSegListPrint.action'),
				录入收货地址--javascript:updatePurAddress('view/numIReport!entryUpdatePuress.action?manufacture.id=${manufacture.id}'),
				返回--view/numIReport!listMan.action?theme=simple" />
		</jsp:include>

		<s:form action="numIReport!listNumSegment.action" method="post"
			id="myform">
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th width="120">
						箱号
					</th>
					<th>
						收货地址
					</th>
					<th>
						数量
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
					<th width="120">
						重量(kg)
					</th>
				</tr>
				<s:iterator value="numSegments" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							第
							<s:property value="boxNo" />
							箱
						</td>
						<td>
							<s:property value="puriAddress" />
						</td>
						<td>
							<s:property value="quantity" />
						</td>
						<td>
							<s:property
								value="manufacture.prefix+staNumber+manufacture.suffix" />
						</td>
						<td>
							<s:property value="staCheckCode" />
						</td>
						<td>
							<s:property
								value="manufacture.prefix+endNumber+manufacture.suffix" />
						</td>
						<td>
							<s:property value="endCheckCode" />
						</td>
						<td>
							<s:property value="weight" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
	</body>
</html>
