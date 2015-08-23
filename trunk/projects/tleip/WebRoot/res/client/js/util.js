jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel();
});

$(function() { 
	$(".ui-tabs-nav > li > a").mouseover(function(e) { 
		if (e.target == this) { 
			var tabs = $(this).parent().parent().children("li"); 
			var panels = $(this).parent().parent().parent().children(".ui-tabs-panel"); 
			var index = $.inArray(this, $(this).parent().parent().find("a")); 
			if (panels.eq(index)[0]) { 
				tabs.removeClass("ui-tabs-selected").eq(index).addClass("ui-tabs-selected"); 
				panels.addClass("ui-tabs-hide").eq(index).removeClass("ui-tabs-hide"); 
			} 
		} 
	}); 
	
	$(".login > li > a").click(function(f) { 
		if (f.target == this) { 
			var tabs = $(this).parent().parent().children("li"); 
			var index = $.inArray(this, $(this).parent().parent().find("a")); 
		    tabs.removeClass("hover").eq(index).addClass("hover"); 
		} 
	}); 
	
	function staticNav() { 
		var sidenavHeight = $("#sidenav").height();
		var winHeight = $(window).height();
		var browserIE6 = (navigator.userAgent.indexOf("MSIE 6")>=0) ? true : false;

		if (browserIE6) {
			$("#sidenav").css({'position' : 'absolute'});
		} else {
			$("#sidenav").css({'position' : 'fixed'});
		}
	
		if (sidenavHeight > winHeight) {
			$("#sidenav").css({'position' : 'static'});
		}
	}
	
	staticNav();
	
	$(window).resize(function () { //Each time the viewport is adjusted/resized, execute the function
		staticNav();
	});
	
}); 

$(document).ready(function(){ 
	
	$("#nav ul.child").removeClass("child");
	
	$("#nav li").has("ul").hover(function(){
		$(this).addClass("current").children("ul").fadeIn();
	}, function() {
		$(this).removeClass("current").children("ul").hide();
	});
		
});

//前端刷新验证码
function changeimage(obj){
	var date = new Date();
	var new_src = $.basePath + "codeImageAction.do?frontUserCode=yes&timestamp=" + date.getTime();
	$(obj).attr("src",new_src);
}

//判断是否是Email
function isEmail(str)
{
	var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|com|gov|mil|org|edu|int|name|asia)$";
	var re = new RegExp( regu );
	if( str.search( re ) == -1 )
	{
		return false;
	}
	else
	{
		return true;
	}
}
