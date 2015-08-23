
/**全选功能**/
function setAllCheck(allCheck) {
	for (var i = 0; i < $("input[name=checkIds]").length; i++) {
		$("input[name=checkIds]")[i].checked = allCheck.checked;
	}
}
/**获得选中复选框的值**/
function getCheckValues() {
	var checkValues = "";
	for (var i = 0; i < $("input[name=checkIds]:checked").length; i++) {
		if (i == 0) {
			checkValues += $("input[name=checkIds]:checked")[i].value;
		} else {
			checkValues += ("," + $("input[name=checkIds]:checked")[i].value);
		}
	}
	return checkValues;
}
/**批量删除**/
function deleteChecks(actionUrl) {
	if ($("input[name=checkIds]:checked").length == 0) {
		alert("请选择需要操作的记录！");
		return;
	}
	
	if (confirm("是否确认删除？")) {
		$("form")[0].action = actionUrl;
		$("form")[0].submit();
	}
}

/**单个删除**/
function deleteRecord(actionUrl) {
	if (confirm("是否确认删除？")) {
		$("form")[0].action = actionUrl;
		$("form")[0].submit();
	}
}


//检查是否安装pdf
function isAcrobatPluginInstall(){ 
	//如果是firefox浏览器 
	if (navigator.plugins && navigator.plugins.length) { 
		for (x=0; x<navigator.plugins.length;x++) { 
			if (navigator.plugins[x].name== 'Adobe Acrobat') 
				return true; 
		} 
	} 
	//下面代码都是处理IE浏览器的情况 
	else if (window.ActiveXObject) 
	{ 
		for (x=2; x<10; x++) 
		{ 
			try{ 
				oAcro=eval("new ActiveXObject('PDF.PdfCtrl."+x+"');"); 
				if (oAcro) 
				{ 
					return true; 
				} 
			} 
			catch(e) {} 
		} 
		try 
		{ 
			oAcro4=new ActiveXObject('PDF.PdfCtrl.1'); 
			if (oAcro4) 
				return true; 
		} 
		catch(e) {} 
		try 
		{ 
			oAcro7=new ActiveXObject('AcroPDF.PDF.1'); 
			if (oAcro7) 
				return true; 
		} 
		catch(e) {} 
	} 
}
