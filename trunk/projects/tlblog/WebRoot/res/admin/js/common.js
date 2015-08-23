//全选js     
function checkAll(e, itemName) {
	var aa = document.getElementsByName(itemName);
	for (var i = 0; i < aa.length; i++) {
		aa[i].checked = e.checked;
	}
}


//****************************************************************
//* 名　　称：isNULL
//* 功    能：检查输入字符串是否为空或者全部都是空格
//* 入口参数：str：输入的字符串
//* 出口参数：是true，否false
//***************************************************************** 
function isNULL(str) {
	if (trim(str) == "") {
		return true;
	}
	var re = /^[ ]+$/g; 
  //var re = new RegExp(regu); 
	return re.test(trim(str));
}
/*
 * 校验是否数字
 */
function checkNumber(name, TempS) {
	for (Count = 0; Count < TempS.length; Count++) {
		TempChar = TempS.substring(Count, Count + 1);
		RefString = "0123456789";
		if (RefString.indexOf(TempChar, 0) == -1) {
			alert("\u8bf7\u8f93\u5165\u6570\u5b57");
			eval("document.all." + name).focus();
			return false;
		}
	}
}
/*
 * 是否有非法字符
 */
function checkString(str) {
	var regStr1 = "<[a-z|A-z]|%|[']";
	var re = new RegExp(regStr1, "i");
	if (re.test(str)) {
		alert("\u8f93\u5165\u542b\u6709\u975e\u6cd5\u5b57\u7b26\uff01");
		return true;
	}
	return false;
}
/*
* 是否是正确邮箱地址
*/
function checkemail() {
	var temp = document.getElementById("tbEmail");
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (temp.value != "") {
		if (!myreg.test(temp.value)) {
			alert("\u63d0\u793a\n\n\u8bf7\u8f93\u5165\u6709\u6548\u7684E_mail\uff01");
			document.formData.tbEmail.focus();
			return false;
		}
	}
}
/*
 *是否是正确的手机号码
 */
function checkmobile(mobile) {
	if (mobile.length == 0) {
		return true;
	}
	if (mobile.length != 11) {
		alert("\u8bf7\u8f93\u516511\u4f4d\u5408\u6cd5\u7684\u624b\u673a\u53f7\u7801\uff01");
		document.formData.tbCellphone.focus();
		return false;
	}
	var myreg = /^(((13[0-9]{1})|159|(15[0-9]{1}))+\d{8})$/;
	if (!myreg.test(mobile)) {
		alert("\u8bf7\u8f93\u516511\u4f4d\u5408\u6cd5\u7684\u624b\u673a\u53f7\u7801\uff01");
		document.formData.tbCellphone.focus();
		return false;
	}
	return true;
}

//5****************************************************************
//* 名　　称：isChinese
//* 功    能：判断是不是全是中文
//* 入口参数：str：输入的字符串
//* 出口参数：true非全中文，false全中文
//***************************************************************** 
function isChinese(str) {
	var regu = /[^\u4e00-\u9fa5]/ig;
	return regu.test(trim(str));
}


//6****************************************************************
//* 名　　称：isIP
//* 功    能：判断是不是IP地址
//* 入口参数：str：输入的字符串
//* 出口参数：是true，否false
//***************************************************************** 
function isIP(str) {
	var regu = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/;
	return regu.test(trim(str));
}
/*
 * 去掉左边空格
 */
function LTrim(str) {
	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		var j = 0, i = s.length;
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
			j++;
		}
		s = s.substring(j, i);
	}
	return s;
}
/*
 * 去掉右边空格
 */
function RTrim(str) {
	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		var i = s.length - 1;
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
			i--;
		}
		s = s.substring(0, i + 1);
	}
	return s;
}
/*
 * 去掉两边空格
 
function Trim(str) {
	return RTrim(LTrim(str));
}
*/

	String.prototype.trim = function() {
	    return this.replace(/^\s+/, '').replace(/\s+$/, '');
	  }

/*
 * 替换字符串
 */
function replaceStr(str) {
	var re = "/( )/gi";
	str = str.replace(re, "");
	re = "/</gi";
	str = str.replace(re, "&lt;");
	return str;
}

//****************************************************************
//* 名　　称：checkNull
//* 功    能：检查输入字符串是否为空或者全部都是空格,并且弹出框体提示
//* 入口参数：str：输入的字符串,message:弹出的提示信息
//* 出口参数：是true，否false
//***************************************************************** 
function checkNull(node, message) {
	if (node.value.trim() == "") {
		alert(message);
		return false;
	}
	return true;
}

//****************************************************************
//* 名　　称：checkNull
//* 功    能：检查输入字符串是否为空或者全部都是空格,并且弹出框体提示
//* 入口参数：str：输入的字符串,message:弹出的提示信息
//* 出口参数：是true，否false
//***************************************************************** 
function checkNull2(node, message) {
	if (node.value.trim() == "") {
		alert(message);
		return false;
	}
	return true;
}
function deleteUploadFile(path, dir, file) {
	if (window.confirm("\u786e\u8ba4\u5220\u9664\u8be5\u6587\u4ef6")) {
		$.post("/lcoa/login.do?method=deleteFile", {realpath:path, dir:dir, file:file}, function (response) {
			document.getElementById("filelist").innerHTML = response;
		});
	}
}

//**$(document).ready(function () {
//	var options = {target:"#out", beforeSubmit:showRequest, success:showResponse};
//	$("#ajaxform").submit(function () {
//		$(this).ajaxSubmit(options);
//		return false;
//	});
//});
//

	function showRequest(formData, jqForm, options) {
		return true;
	}
	function showResponse(responseText, statusText) {
	}
	
//****************************************************************
//* 名　　称：updateDisplaySyn
//* 功    能：更改状态如：显示状态
//* 入口参数：obj：状态字符,url:更改方法的地址
//* 出口参数：url
//***************************************************************** 
	function updateDisplaySyn(obj,url){
		if (confirm("确定更改状态吗？")==false) return false;
		var isDisplayText=obj.innerText;
		var displayStatus;
		if(isDisplayText=='是'){
			displayStatus=1;	
		}else{
			displayStatus=0;
		}
		url=url+"&displayStatus="+displayStatus;
		$.ajax({ 
			type : "post",
        	url : url,
        	success : function(displayStatus) { 
        		if(displayStatus==1){
        			obj.style.color="red";
        			obj.innerText="是";
        		}else{
        			obj.style.color="blue";
        			obj.innerText="否";
        		}
       		}
    	});  
	}

	
		function changeStatusSyn(obj,url){
		if (confirm("确定更改状态吗？")==false) return false;
		var isDisplayText=obj.innerText;
		var displayStatus;
		if(isDisplayText=='是'){
			displayStatus=1;	
		}else{
			displayStatus=0;
		}
		url=url+"&displayStatus="+displayStatus;
		$.ajax({ 
			type : "post",
        	url : url,
        	success : function(displayStatus) { 
        		if(displayStatus==1){
        			obj.style.color="blue";
        			obj.innerText="是";
        		}else{
        			obj.style.color="red";
        			obj.innerText="否";
        		}
       		}
    	});  
	}