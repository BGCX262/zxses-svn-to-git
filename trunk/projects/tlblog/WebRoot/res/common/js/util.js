/****
	boleyn_renlei js工具类
**/

//前端刷新验证码
function changeimage(obj){
	var date = new Date();
	var new_src = $.basePath + "codeImageAction.do?frontUserCode=yes&timestamp=" + date.getTime();
	$(obj).attr("src",new_src);
}
/*
复选框全选
function checkboxs_all(obj,cName){
	var checkboxs = document.getElementsByName(cName);
    for(var i=0;i<checkboxs.length;i++){
		checkboxs[i].checked = obj.checked;
	}	
}*/

//字符串去除前后空格可以使用就query方法-->$.trim() 然后传入验证方法
var Validate = function(){}
Validate.prototype =  {
    // 验证字符串是否为空 
    testString: function(str) {  
        return str.replace(/^\s+|\s+$/g, "") != "";  
    },  
      
    // 验证整数  
    testIntegerNumber: function(num) {  
        if (!this.testString(num)) {  
            return false;  
        }  
        return /^[0-9]+$/.test(num);  
    },  
    
	// 验证整数范围 
    testIntegerNumberScope: function(num,minvalue,maxvalue) {  
        if (!this.testString(num)) {
            return false;  
        }
        if (!this.testIntegerNumber(num)){
        	return false;  
        } 
        if (minvalue > num || num > maxvalue){
        	return false;
        }
        return true;  
    },
      
    // 验证只能是字母和数字 
    testNumAndLetter: function(str) {  
        if (!this.testString(str)) {  
            return false;  
        }  
        return /^[0-9a-zA-Z]*$/.test(str);  
    },
    
    // 验证由数字、26个英文字母或者下划线组成的字符串 
    testNumAndLetterUnder: function(str) {  
        if (!this.testString(str)) {  
            return false;  
        }  
        return /^\w+$/.test(str);  
    },
      
    // 验证浮点数  
    testDoubleNumber: function(num) {  
        if (!this.testString(num)) {  
            return false;  
        }  
        return /^[0-9]+(\.[0-9]+)?$/.test(num);  
    },  
      
    // 验证日期(yyyy/MM/dd)  
    testDate: function(date) {  
        if (!this.testString(date)) {  
                return false;  
        }  
        var reg = /^[1-2]\d{3}\/(0?[1-9]|1[0-2])\/(0?[1-9]|[12][0-9]|3[0-1])$/;   
        return reg.test(date);  
    },  
    
	// 验证邮政编码  
    testZipcode: function(code) {  
        if (!this.testString(code)) {  
            return false;  
        }  
        return /^\d{6}$/.test(code);  
    },
    
    // 验证Email  
    testEmail: function(email) {  
        if (!this.testString(email)) {  
            return false;  
        }  
        var reg = /^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|edu|int|name|asia)$/; // /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;  
        return reg.test(email);  
    },  
      
    // 验证固定电话号码  
    testPhone: function(phone) {  
        if (!this.testString(phone)) {  
            return false;  
        }  
        return /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(phone);  
    }, 
    
	// 验证手机号码(检验13,15,18开头的手机号！) 
    testTelPhone: function(telphone) {  
        if (!this.testString(telphone)) {  
            return false;  
        }  
        return /^[1][358]\d{9}$/.test(telphone);  
    },
    
    // 验证中文  
    testChinese: function(str) {  
        if (!this.testString(str)) {  
            return false;  
        }  
        return !/^[u4E00-u9FA5]+$/.test(str);  ///^[\u0391-\uFFE5]+$/
    },
    
    //验证是否为图片格式
    testImage: function(imageStr) {  
    	var allowType = new Array("gif","jpg","bmp","png");
    	var ext = imageStr.substring(imageStr.lastIndexOf(".")+1).toLowerCase();
		for (var i = 0; i < allowType.length; i++) {
            if (allowType[i] == ext){
                return true; 
            }
        }
        return false;
    }
}

//实例化
var validate = new Validate();