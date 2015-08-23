<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css" />
		
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/move.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
			
		<script charset="utf-8" src="<%=basePath%>tools/kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="<%=basePath%>tools/kindeditor/lang/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
			var options = {
			        uploadJson : '<%=basePath%>tools/kindeditor/jsp/upload_json.jsp',
					fileManagerJson : '<%=basePath%>tools/kindeditor/jsp/file_manager_json.jsp',
					allowFileManager : true				
			 };
			editor = K.create('textarea[name="editor_k"]', options);
			});
		</script>
		
		
		<!--[if gte IE 7]><!-->
		<link href="<%=basePath%>tools/artdialog/skins/blue.css"
			rel="stylesheet" />
		<!--<![endif]-->
		<!--[if lt IE 7]>
		    <link href="<%=basePath%>tools/artdialog/skins/default.css" rel="stylesheet" />
		<![endif]-->
		<script src="<%=basePath%>tools/artdialog/js/artDialog.js"></script>
		<script src="<%=basePath%>tools/artdialog/js/iframeTools.js"></script>
		
		<script language="javascript" type="text/javascript"> 
			function check(){
				var announceTypeArray = document.getElementsByName("announceType");
				var announceTypeValue;
				for(i=0;i<announceTypeArray.length;i++)
			　　 {
			    　　 if(announceTypeArray[i].checked){
			       		announceTypeValue = announceTypeArray[i].value;
			       	}
			　　 }
				var classId = document.getElementById('classId');
				var yxdm = document.getElementById('yxdm').value;
				var typeId = document.getElementById('typeId');
				var newsTitle= document.getElementById("newsTitle"+announceTypeValue);
				var imgNewsTitle= document.getElementById("imgNewsTitle"+announceTypeValue);
				var newsKeyword = document.getElementById("newsKeyword");
				var parentModuleFlag = document.getElementById('parentModuleFlag').value;
				var addNewsContentForm = document.getElementById('addNewsContentForm');
				if ($.trim(typeId.value).length<=0){
					alert("所属的栏目不能为空!");
					typeId.focus();
					return ;
				}
				if (announceTypeValue == 1){
					if ($.trim(newsTitle.value).length<=0){
						alert("链接标题不能为空!");
						newsTitle.focus();
						return ;
					}
				}else{
					if ($.trim(newsTitle.value).length<=0){
						alert("新闻标题不能为空!");
						newsTitle.focus();
						return ;
					}
				}
				if (announceTypeValue == 2){
					var uploadFileValue = "";
					var len = document.addNewsContentForm.file_annex_url.options.length;
					if (len != null && len > 0 ){
						for (var i = 0;i<len;i++){
							uploadFileValue = uploadFileValue + document.addNewsContentForm.file_annex_url.options[i].value;
						}
					}else{
						alert("附件不能为空！");
						return ;
					}
					document.addNewsContentForm.file_annex_url.value = uploadFileValue ;
				}
				var selStr="";
				var str = document.getElementById("str");
				for(var i=0;i<str.options.length;i++){
					if(str.options[i].selected == true){
						selStr+=str.options[i].value+",";
					}
				}
				
				var checkFlag="";
				var checks=document.getElementsByName("checkFlag");
				for(var i=0;i<checks.length;i++){
					if(checks[i].checked==true){
						checkFlag=checks[i].value;
					}
				}
				editor.sync();
				showloading("<%=basePath%>commons/ajaxload.jsp",90,90); 
				addNewsContentForm.action = "<%=basePath%>view/newsmanageWebNote.do?method=addNewsContentManage"+"&checkFlag="+checkFlag+"&selStr="+selStr+"&classId="+classId.value+"&yxdm="+yxdm+"&parentModuleFlag="+parentModuleFlag;
				addNewsContentForm.submit();
	    	}
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
	    		var classId = document.getElementById("classId").value;
	    		var typeId = document.getElementById('typeId').value;
			 	window.location = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage&currentPage=1&classId="+classId+"&typeId="+typeId+"&yxdm="+yxdm+"&parentModuleFlag="+'<%=request.getParameter("parentModuleFlag")%>';
			 }
			 
			 function checkNumberTemp(objString){
			 	var obj = document.getElementById(objString);
				if (obj.value != ""){
					if ( checkNumber(objString,obj.value)== false ){
						obj.focus();
						return false;
					}
				}else{
					obj.value = 0;
				}
			 	return true;
			 }
			 function selectLink(obj){
				var value = obj.value;
				
				if (value == 1){
					document.getElementById("newsPageNoLink").style.display ='none';
					document.getElementById("newsPageLink").style.display ='';	
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPage").style.display ='none';
					document.getElementById("myEditor").style.display ='none';
					document.getElementById("idHttpUrl").style.display ='';
				}else if (value == 2){
					document.getElementById("newsPageNoLink").style.display ='none';
					document.getElementById("newsPageLink").style.display ='none';	
					document.getElementById("newsPageUpload").style.display ='';
					document.getElementById("newsPage").style.display ='none';
					document.getElementById("myEditor").style.display ='none';
					document.getElementById("idHttpUrl").style.display ='none';
				}else{
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPageNoLink").style.display ='';
					document.getElementById("newsPageLink").style.display ='none';
					document.getElementById("newsPage").style.display ='';	
					document.getElementById("myEditor").style.display ='none';
					document.getElementById("idHttpUrl").style.display ='';
				}
			 }
			 function changeInput(mark){
				if ( mark == 0 ){
					loadSle();
					document.getElementById("selDiv").style.display='none';
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPageNoLink").style.display ='';
					document.getElementById("newsPageLink").style.display ='none';
					document.getElementById("newsPage").style.display ='';
					document.getElementById("newsProperty").style.display ='';	
					document.getElementById("newsStyle").style.display ='';	
					document.getElementById("myEditor").style.display ='none';
					document.getElementById("idHttpUrl").style.display ='';
					document.getElementById("titleSet").innerText ='标题设置';
				}else{
					document.getElementById("selDiv").style.display='none';
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPageNoLink").style.display ='none';
					document.getElementById("newsPageLink").style.display ='none';
					document.getElementById("newsPage").style.display ='';	
					document.getElementById("newsProperty").style.display ='none';	
					document.getElementById("newsStyle").style.display ='none';	
					document.getElementById("myEditor").style.display ='';
					 
					document.getElementById("idHttpUrl").style.display ='';
					document.getElementById("titleSet").innerText ='标题设置';
				}
				return false;
			}
			 
			 function showImgNews(obj){
			 	if ( obj.checked == true ){
			 		document.getElementById("ifImgNewsTr1").style.display ='';
			 	}else{
			 		document.getElementById("ifImgNewsTr1").style.display ='none';
			 	}
			 }
			 function showImgNewsLink(obj){
			 	if ( obj.checked == true ){
			 		document.getElementById("ifImgNewsTrLink").style.display ='';
			 	}else{
			 		document.getElementById("ifImgNewsTrLink").style.display ='none';
			 	}
			 }
			 
			function removeFileItem(){
				var delSelect = document.addNewsContentForm.file_annex_url;
				removeItem(delSelect);
				}
			function showDiv()
			{
				if(document.getElementById("selDiv").style.display=='none'){
					document.getElementById("selDiv").style.display='block';
				}else{
					document.getElementById("selDiv").style.display='none';
				}
			}
			function loadSle(){
				var typeIDSel=document.getElementById("typeId");
				var typeId="";
				for(var i=0;i<typeIDSel.options.length;i++){
					if(typeIDSel.options[i].selected==true){
						typeId=typeIDSel.options[i].value;
					}
				}
				var sel=document.getElementById("str");
				for(var i=0;i<sel.options.length;i++){
					if(typeId==''+sel.options[i].value){  
	          			sel.options[i].selected=true;         
	          		}
        		} 
			}
			
			function changeTypeId(){
				var typeIDSel=document.getElementById("typeId");
				var sel=document.getElementById("str");
				var typeId="";
					for(var i=0;i<sel.options.length;i++){
	          		sel.options[i].selected=false;         
	        	}
	        	for(var i=0;i<typeIDSel.options.length;i++){
					if(typeIDSel.options[i].selected==true){
						typeId=typeIDSel.options[i].value;
					}
				}
				var sel=document.getElementById("str");
					for(var i=0;i<sel.options.length;i++){
					if(typeId==''+sel.options[i].value){  
	          		sel.options[i].selected=true;         
	          		}
	        	}
			}
			function showArtDialog(){
				var announceTypeArray = document.getElementsByName("announceType");
				var announceTypeValue;
				for(i=0;i<announceTypeArray.length;i++)
			　　 {
			    　　 if(announceTypeArray[i].checked){
			       		announceTypeValue = announceTypeArray[i].value;
			       	}
			　　 }
			(function (config) {
			    config['title'] = '上传文件';
			    config['lock'] = true;
			})(art.dialog.defaults);
				art.dialog.data('announceType', announceTypeValue);
				art.dialog.open('<%=basePath%>commons/upload.jsp',null,false);
			}
		</script>

	</head>
	<body onload="loadSle();">
		<form action="view/newsmanageWebNote.do?method=addNewsContentManage"
			method="post" enctype="multipart/form-data" name="addNewsContentForm"
			id="addNewsContentForm">
			<input type="hidden" name="parentModuleFlag" id="parentModuleFlag"
				value="${parentModuleFlag}" />
			<input type="hidden" name="newsTemplate" value="1" />
			<table cellspacing="0" cellpadding="0" width="100%" border="0"
				align="center">

				<tr>
					<td valign="top">

						<table cellspacing="0" cellpadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td width="100%" align="left" valign="top">
									<table class="pathbg">
										<tr>
											<td width="514" height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">新增新闻</span>${showMsg }
											</td>
											<td align="right">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:check();" value="保存" />
												&nbsp;
												<input name="button2" type="button" class="an" id="button2"
													value="返回" onclick="javascript:back();" />
											</td>
											<td width="30"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="table" cellspacing="0" cellpadding="0"
										width="100%" border="0">
										<tr>
											<td class="td_center">
												<table class="table" cellspacing="0" cellpadding="0"
													width="100%" border="0">

													<tr id="newsProperty">
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>所属栏目：
														</td>
														<td class="td_right" width="50%">
															<input type="hidden" id="classId" name="classId"
																value="${classId}" />
															<input type="hidden" id="yxdm" name="yxdm" size="6"
																maxlength="6" value="${yxdm}" />

															<%-- ${typeList.typeName } --%>

															<select name="typeId" id="typeId"
																onchange="changeTypeId()">
																<c:forEach items="${allTypeList}" var="table">
																	<c:if test="${typeId == table.typeId}">
																		<option value="${table.typeId}" selected="selected">
																			${table.typeName}
																		</option>
																	</c:if>
																	<c:if test="${typeId != table.typeId}">
																		<option value="${table.typeId}">
																			${table.typeName}
																		</option>
																	</c:if>
																</c:forEach>
															</select>
															<%--
															&nbsp;&nbsp;&nbsp;
															<a href="javascript:showDiv()">发布到其他栏目</a>
															--%>
														</td>

													</tr>
													<%----%>
													<tr id="selDiv" style="display: none;">
														<td class="td_left" width="50%"></td>
														<td class="td_right" width="50%">
															<select multiple="multiple" name="str" id="str"
																style="width: 40%; height: 140px; text-align: center;">
																${buffer }
															</select>
															<font color="orangered">注：按住 ctrl 键盘多选</font>
														</td>
													</tr>
													

													<tr id="newsPage">
														<td class="td_left" width="50%">
															<a href="#" onclick="changeInput('0'); " id="titleSet">标题设置&nbsp;&nbsp;</a>
														</td>
														<td class="td_right" width="50%">
															<a href="#" onclick="changeInput('1'); ">&nbsp;&nbsp;输入正文内容</a>
														</td>
													</tr>

													<tr>
														<td class="td_left" width="50%">
															审核标志：
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="checkFlag" value="1"
																<c:if test="${itemSmall.checkFlag == 1 || itemSmall.checkFlag == null }">
																		checked="checked"
																</c:if> />
															自动审核
															<input type="radio" name="checkFlag" value="0"
																<c:if test="${itemSmall.checkFlag == 0}">
																		checked="checked"
																	</c:if> />
															手工审核
														</td>
													</tr>

													<tr id="newsStyle">
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>发布方式：
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="announceType" id="announceType"
																onclick="selectLink(this);" value="0" checked="checked" />
															非地址链接式
															<input type="radio" name="announceType" id="announceType"
																onclick="selectLink(this);" value="1" />
															地址链接式
															<input type="radio" name="announceType" id="announceType"
																onclick="selectLink(this);" value="2" />
															上传附件
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															发布部门：
														</td>
														<td class="td_right" width="50%">
															<select name="publishDept">
																<logic:iterate id="department" name="departmentList">
																	<option value="${department.deptId}">
																		${department.deptName}
																	</option>
																</logic:iterate>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															发布时间：
														</td>
														<td class="td_right" width="50%">
															<input class="Wdate" type="text" id="newsDate"
																name="newsDate" value="${newsDate}" readonly="readonly"
																onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
														</td>
													</tr>
												</table>
												<!-- 设置非地址链接式新闻明细开始 -->
												<div id="newsPageNoLink">
													<table class="table" cellspacing="0" cellpadding="0"
														width="100%" border="0">

														<tr>
															<td class="td_left" width="50%">
																<font color="#ff0000">* </font>新闻标题：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsTitle0" id="newsTitle0"
																	size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																关键字：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsKeyword" id="newsKeyword"
																	size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																作者：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsWriter" />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																来源：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsSource" size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																是否置顶：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifTopRow0" />
																置顶
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																图片新闻：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifImgNews0"
																	onclick="showImgNews(this);" />
																图片新闻
															</td>
														</tr>
														<tr id="ifImgNewsTr1" style="display: none">
															<td class="td_left" width="100%" colspan=2>
																<table cellspacing="0" cellpadding="0" width="100%"
																	border="0">
																	<tr>
																		<td class="td_left" width="50%">
																			调用图片：
																		</td>
																		<td class="td_right" width="50%">
																			<input type="text" name="imgNewsAddress0"
																				id="imgNewsAddress0" value="" readonly size=40 />
																			<br />
																			<a href="#" onclick="showArtDialog()"><font
																				color="#FF0000">点击这里上传图片</font> </a>(注:预览图片宽为120,高为100)
																		</td>
																	</tr>
																	<tr>
																		<td class="td_left" width="50%">
																			图片预览：
																		</td>
																		<td class="td_right" width="50%">
																			<div align="left">
																				<img name="faceImg0" id="faceImg0"
																					src="<%=basePath%>res/admin/img/wu.jpg" width="120"
																					height="100" />
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td class="td_left" width="50%">
																			调用文字：
																		</td>
																		<td class="td_right" width="50%">
																			<textarea cols=40 rows=5 id=imgnewstitle0
																				name="imgnewstitle0"></textarea>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
												<div id="myEditor" style="display: none;">
													
													<textarea id="editor_k" name="editor_k" style="width:100%;height:400px;visibility:hidden;"></textarea>
															
												</div>
												<!-- 非地址链接式新闻明细结束 -->

												<!-- 设置地址链接式新闻明细开始 -->
												<div id="newsPageLink" style="display: none">
													<table class="table" cellspacing="0" cellpadding="0"
														width="100%" border="0">
														<tr>
															<td class="td_left" width="50%">
																<font color="#ff0000">* </font>新闻标题：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsTitle1" id="newsTitle1"
																	size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																链接地址：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="httpUrl" size=60 />
															</td>
														</tr>

														<tr>
															<td class="td_left" width="50%">
																是否置顶：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifTopRow1" />
																置顶
															</td>
														</tr>

														<tr>
															<td class="td_left" width="50%">
																是否有图片：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifImgNews1"
																	onclick="showImgNewsLink(this);" />
																图片新闻
															</td>
														</tr>
														<tr id="ifImgNewsTrLink" style="display: none">
															<td class="td_left" width="100%" colspan=2>
																<table class="table" cellspacing="0" cellpadding="0"
																	width="100%" border="0">
																	<tr>
																		<td class="td_left" width="50%">
																			调用图片：
																		</td>
																		<td class="td_right" width="50%">
																			<input type="text" name="imgNewsAddress1" id="imgNewsAddress1" value=""
																				readonly size=50 />
																			<br />
																			<a href="#" onclick="showArtDialog()"><font
																				color="#FF0000">点击这里上传图片</font> </a>(注:预览图片宽为120,高为100)
																		</td>
																	</tr>
																	<tr>
																		<td class="td_left" width="50%">
																			图片预览：
																		</td>
																		<td class="td_right" width="50%">
																			<div align="left">
																				<img name="faceImg1"
																					src="<%=basePath%>res/admin/img/wu.jpg" width="120"
																					height="100" />
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td class="td_left" width="50%">
																			调用文字：
																		</td>
																		<td class="td_right" width="50%">
																			<textarea cols=40 rows=5 id=imgnewstitle1
																				name="imgnewstitle1"></textarea>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>

													<div id="idHttpUrl">
													</div>
												</div>
												<!-- 地址链接式新闻明细结束 -->


												<!-- 设置上传新闻明细开始 -->
												<div id="newsPageUpload" style="display: none">
													<table class="table" cellspacing="0" cellpadding="0"
														width="100%" border="0">
														<tr>
															<td class="td_left" width="50%">
																<font color="#ff0000">* </font>新闻标题：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsTitle2" id="newsTitle2"
																	size=60 />
															</td>
														</tr>

														<tr>
															<td class="td_left" width="50%">
																是否置顶：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifTopRow2" />
																置顶
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																附件：<br />
															</td>
															<td class="td_right" width="50%">
																<select name="file_annex_url" id="file_annex_url"
																	size="3" multiple style="width: 395px"></select>
																<a href="#" onclick="showArtDialog()">
																<br/>
																<font color="#FF0000">上传附件</font></a>&nbsp;
																<a href="#" onclick="javascript:removeFileItem();"
																	title="删除附件"><font color="#FF0000">删除附件</font></a>(注:附件只允许上传一个)
															</td>
														</tr>
													</table>
												</div>
												<!-- 上传新闻明细结束 -->
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
