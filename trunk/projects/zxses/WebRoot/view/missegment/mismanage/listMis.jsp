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
		<title>缺失号码管理</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript">
		function setFillings(actionUrl){
			if ($("input[name=checkIds]:checked").length == 0) {
				alert("请选择需要操作的记录！");
				return;
			}
			if (confirm("是否确认补号？")) {
				$("form")[0].action = actionUrl;
				$("form")[0].submit();
			}
		}
		function setFillings0(actionUrl){
			if ($("input[name=checkIds]:checked").length == 0) {
				alert("请选择需要操作的记录！");
				return;
			}
			if (confirm("是否确认反补号？")) {
				$("form")[0].action = actionUrl;
				$("form")[0].submit();
			}
		}
		
		function setFilling(actionUrl,isFilling){
			if(isFilling=="1"){
				if (confirm("是否确认补号？")) {
					for (var i = 0; i < $("input[name=checkIds]").length; i++) {
						if($("input[name=checkIds]")[i].checked){
							$("input[name=checkIds]")[i].checked = false;
						}
					}
					$("form")[0].action = actionUrl;
					$("form")[0].submit();
				}
			}
			if(isFilling=="0"){
				if (confirm("是否确认反补号？")) {
					for (var i = 0; i < $("input[name=checkIds]").length; i++) {
						if($("input[name=checkIds]")[i].checked){
							$("input[name=checkIds]")[i].checked = false;
						}
					}
					$("form")[0].action = actionUrl;
					$("form")[0].submit();
				}
			}
		}
		
		function setFocusInput(focusInputId){
			if(focusInputId==""){
				document.getElementById("misStaNumber").focus();
				document.getElementById("misStaNumber").select();
			}else{
				document.getElementById(focusInputId).focus();
				document.getElementById(focusInputId).select();
			}
		}
		</script>
	</head>
	<body class="centerbg" onload="setFocusInput('${focusInputId }')">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="补号--javascript:setFillings('view/misManage!setIsFilling.action?isFilling=1&theme=simple&paginate.offset=${paginate.offset }')
					,反补号--javascript:setFillings0('view/misManage!setIsFilling.action?isFilling=0&theme=simple&paginate.offset=${paginate.offset }')
					,删除--javascript:deleteChecks('view/misManage!deleteMis.action?theme=simple&paginate.offset=${paginate.offset }')
					,返回--view/misManage!listMan.action?theme=simple" />
		</jsp:include>

		<s:form action="misManage!saveMis.action" method="post" id="myform">
			<s:hidden name="inputPage" value="listMis.jsp"></s:hidden>
			<s:hidden name="focusInputId"></s:hidden>
			<s:hidden name="checkManIds"></s:hidden>
			<div class="queryheader">
				起始号：
				<s:textfield name="misStaNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="misStaNumber"></s:textfield>
				&nbsp;&nbsp; 终止号：
				<s:textfield name="misEndNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="misEndNumber"></s:textfield>
				&nbsp;
				<s:submit cssClass="an" value="保存"></s:submit>
			</div>
			<hr />
			<table id="mytable">
				<tr>
					<th width="45">
						<input type="checkbox" name="allCheck" id="allCheck"
							onclick="setAllCheck(this)" />
					</th>
					<th>
						生产单号
					</th>
					<th>
						客户名称
					</th>
					<th>
						产品名称
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
						补号
					</th>
					<th width="60">
						操作
					</th>
				</tr>
				<s:iterator value="misSegments" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:checkbox name="checkIds" fieldValue="%{id}"></s:checkbox>
						</td>
						<td>
							<s:property value="manufacture.manNumber" />
						</td>
						<td>
							<s:property value="manufacture.cusName" />
						</td>
						<td>
							<s:property value="manufacture.proName" />
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
							<s:if test="isFilling==0">
								<a
									href="javascript:setFilling('view/misManage!setIsFilling.action?checkIds=${id}&isFilling=1&theme=simple&paginate.offset=${paginate.offset }','1')"
									style="color: blue;">未补号</a>
							</s:if>
							<s:else>
								<a
									href="javascript:setFilling('view/misManage!setIsFilling.action?checkIds=${id}&isFilling=0&theme=simple&paginate.offset=${paginate.offset }','0')"
									style="color: red;"> 已补号</a>
							</s:else>
						</td>
						<td>
							<a
								href="javascript:deleteRecord('view/misManage!deleteMis.action?checkIds=${id}&theme=simple&paginate.offset=${paginate.offset }')"
								class="opertion">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/misManage!listMis.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
