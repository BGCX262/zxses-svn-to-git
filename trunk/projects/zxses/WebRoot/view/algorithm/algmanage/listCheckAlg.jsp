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
		<title>检码算法管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript">
		function setEnable(tmpSpan,checkAlgId){
			var tmpSpanText=tmpSpan.innerText;
			var isEnable;
			var confirmStr="";
			if("禁用"==tmpSpanText){
				isEnable=1;
				confirmStr="是否确认启用？";
			}else{
				isEnable=0;
				confirmStr="是否确认禁用？";
			}
			if(confirm(confirmStr)){
				$.ajax({
					url:"view/algManage!updateIsEnable.action?checkAlg.id="+checkAlgId+"&isEnable="+isEnable,
					async:false,
					cache:false,
					success:function(responseText){
						if(responseText=="1"){
							tmpSpan.style.color="blue";
							tmpSpan.innerHTML="启用";
						}else if(responseText=="0"){
							tmpSpan.style.color="red";
							tmpSpan.innerHTML="禁用";
						}
					}
				});
			}
		}
		</script>
	</head>
	<body class="centerbg">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="删除--javascript:deleteChecks('view/algManage!deleteCheckAlg.action?theme=simple&paginate.offset=${paginate.offset }')" />
		</jsp:include>

		<s:form action="algManage!listCheckAlgs.action" method="post"
			id="myform">
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th>
						算法名称
					</th>
					<th width="120">
						是否启用
					</th>
					<th width="120">
						操作
					</th>
				</tr>
				<s:iterator value="checkAlgs" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							<s:property value="algName" />
						</td>
						<td>
							<s:if test="isEnable==0">
								<span onclick="setEnable(this,${id })"
									style="color: red; cursor: pointer">禁用</span>
							</s:if>
							<s:else>
								<span onclick="setEnable(this,${id })"
									style="cursor: pointer; color: blue">启用</span>
							</s:else>
						</td>
						<td>
							<a
								href="view/algManage!entryInfoCheckAlg.action?checkAlg.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">详情</a>
							<a
								href="view/algManage!entryUpdateCheckAlg.action?checkAlg.id=${id}&theme=simple&paginate.offset=${paginate.offset }"
								class="opertion">修改</a>
							<a
								href="javascript:deleteRecord('view/algManage!deleteCheckAlg.action?checkIds=${id}&theme=simple&paginate.offset=${paginate.offset }')"
								class="opertion">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/algManage!listCheckAlgs.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
