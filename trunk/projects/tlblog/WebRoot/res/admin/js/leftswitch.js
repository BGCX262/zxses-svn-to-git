function MM_preloadimage() { //v3.0
	var d=document; if(d.image){ if(!d.MM_p) d.MM_p=new Array();
		var i,j=d.MM_p.length,a=MM_preloadimage.arguments; for(i=0; i<a.length; i++)
		if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}

	var ttt
	ttt=2;
	function oa_tool1(){
	if(ttt==2)
	{
		ttt=1;
		frameshow1.src="../res/admin/theme/blue/images/mainleftspico2.gif";
		frameshow1.alt="显示"
		window.parent.middle.cols="0,9,*";
	}
	else
	{
		ttt=2;
		window.parent.middle.cols="200,12,*";
		frameshow1.src="../res/admin/theme/blue/images/mainleftspico.gif";
		frameshow1.alt="隐藏"
	}
	}