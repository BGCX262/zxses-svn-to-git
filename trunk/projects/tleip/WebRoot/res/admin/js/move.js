  function MoveItemToText(SelectList,myShowText,myValueText){       
  	   var imark=1;	
       for (i=0;i<=SelectList.options.length-1;i++){
            if (SelectList.options[i].selected){
               	var element=document.createElement("Option");
               	myValueText.value = myValueText.value + SelectList.options[i].text;
               	myShowText.value = myShowText.value + SelectList.options[i].text;
	       	   	imark=imark+1;
             }                
       }  
       if (imark==1){
			alert("请选择要删除的附件！");
			return ;
       }
       /*
       var j = 0 ;         
       for (i=0;i<SelectList.options.length;i++){
            if (SelectList.options[i].selected){
                SelectList.remove(i);
                i = i - 1 ;
              }                
      }  */
   }    
  function MoveAllItem(SelectList,addList){
	var i=0;
	var num=SelectList.length; 
	var objSelected;
	while(i<num)
	{
		objSelected= new Option(SelectList[0].text, SelectList[0].value);
		addList.add(objSelected); 
		SelectList.remove(0); 
		i++;
	}	
  }
  function MoveItem(SelectList,addList){       
  	   var imark=1;	
       for (i=0;i<=SelectList.options.length-1;i++){
            if (SelectList.options[i].selected){
               	var element=document.createElement("Option");
               	element.text=SelectList.options[i].text;
               	element.value=SelectList.options[i].value;
               	addList.add(element);
	       	   	imark=imark+1;
             }                
       }  
       if (imark==1){
			alert("请选择一项来移动！");
			return ;
       }
       var j = 0 ;         
       for (i=0;i<SelectList.options.length;i++){
            if (SelectList.options[i].selected){
                SelectList.remove(i);
                i = i - 1 ;
              }                
      }  
   }    
   /*
  function MoveUp(SelectList)
  {
	var nIndex = SelectList.selectedIndex;
	if (nIndex == -1)
	{
		alert("请选择一项来移动！");
		return ;
	}
	if (nIndex == 0)
		return ;
	var objSelected = new Option(SelectList[nIndex].text, SelectList[nIndex].value);
	var objPrevious = new Option(SelectList[nIndex-1].text, SelectList[nIndex-1].value);
	SelectList.options[nIndex] = objPrevious;
	SelectList.options[nIndex-1] = objSelected;
	SelectList.options[nIndex-1].selected = true;
  }*/
  function MoveUp(SelectList){
  		var i=0;
  		var selList= new Array;
  		var iMark  = 1;
  		var iLen   = SelectList.length;
		for (i=1;i<iLen;i++)
		{
			if (SelectList.options[i].selected == true){
				iMark++;
				var nIndex = i;//SelectList.selectedIndex;
				var objSelected = new Option(SelectList[nIndex].text, SelectList[nIndex].value);
				var objPrevious = new Option(SelectList[nIndex-1].text, SelectList[nIndex-1].value);
				SelectList.options[nIndex] = objPrevious;
				SelectList.options[nIndex-1] = objSelected;
				SelectList.options[nIndex-1].selected = false;
				selList[iMark-1] = nIndex-1;
			}
		}
		if (iMark == 1 && iLen>0 && SelectList.options[0].selected ){
			alert("对不起，已移动到最顶部！");
			return ;
		}
		if ( iMark <= 1 ){
			alert("请选择一项来移动！");
			return ;
		}
		for(i=0;i<selList.length;i++){
			SelectList.options[selList[i]].selected = true;
		}
  }
  /*
  function MoveDown(SelectList)
  { 
	var nIndex = SelectList.selectedIndex;
	if (nIndex == -1)
	{
		alert("请选择一项来移动！");
		return ;
	}
	if (nIndex == SelectList.options.length-1)
		return ;
	var objSelected = new Option(SelectList[nIndex].text, SelectList[nIndex].value);
	var objPrevious = new Option(SelectList[nIndex+1].text, SelectList[nIndex+1].value);
	SelectList.options[nIndex] = objPrevious;
	SelectList.options[nIndex+1] = objSelected;
	SelectList.options[nIndex+1].selected = true;
  }*/
  function MoveDown(SelectList){
  		var i=0;
  		var selList = new Array;
  		var iMark 	= 1;
  		var iLen   	= SelectList.length;
  		for (i = iLen-1;i>0;i--)
		{
			if (SelectList.options[i-1].selected == true){
				iMark++;
				var nIndex = i-1;//SelectList.selectedIndex;
				var objSelected = new Option(SelectList[nIndex].text, SelectList[nIndex].value);
				var objPrevious = new Option(SelectList[nIndex+1].text, SelectList[nIndex+1].value);
				SelectList.options[nIndex] = objPrevious;
				SelectList.options[nIndex+1] = objSelected;
				SelectList.options[nIndex+1].selected = false;
				selList[iMark-1] = nIndex+1;
			}
		}
		if (iMark == 1 && iLen>0 && SelectList.options[iLen-1].selected ){
			alert("对不起，已移动到最底部！");
			return ;
		}

		if ( iMark <= 1 ){
			alert("请选择一项来移动！");
			return ;
		}
		for(i=0;i<selList.length;i++){
			SelectList.options[selList[i]].selected = true;
		}

	}
	function MoveLast(SelectList){
  		var iLen = SelectList.length;
  		var selectedIndex = SelectList.selectedIndex;
  		if (selectedIndex<0){
  			alert("请选择一项来移动！");
			return ;
  		}
  		if (iLen == 1){
  			alert("对不起，已移动到最底部！");
  			return;
  		}
  		var objSelected = new Option(SelectList[selectedIndex].text, SelectList[selectedIndex].value);
  		removeItem(SelectList);
  		SelectList.add(objSelected);
  		SelectList.options[iLen-1].selected = true;
   }
   function MoveFirst(SelectList){
  		var iLen = SelectList.length;
  		var selectedIndex = SelectList.selectedIndex;
  		if (selectedIndex<0){
  			alert("请选择一项来移动！");
			return ;
  		}
  		if (iLen == 1){
  			alert("对不起，已移动到最顶部！");
  			return;
  		}
  		var objSelected = new Option(SelectList[selectedIndex].text, SelectList[selectedIndex].value);
  		SelectList.remove(selectedIndex);
        SelectList.add(objSelected,0);
        SelectList.options[0].selected=true;
  	
   }
   function selectDefaultFirst(SelectList){
  		var iLen = SelectList.length;
  		var iMark = 1;
  		for (i = iLen-1;i>0;i--)
		{
			if (SelectList.options[i-1].selected == true){
				iMark++;
			}
		}
		if (iMark == 1){
  			SelectList.options[0].selected = true;
  		}
  }
  function selectDefaultLast(SelectList){
  		var iLen = SelectList.length;
  		var iMark = 1;
  		for (i = iLen-1;i>0;i--)
		{
			if (SelectList.options[i-1].selected == true){
				iMark++;
			}
		}
		if (iMark == 1){
  			SelectList.options[len-1].selected = true;
  		}
  }
  function getAllSelectedValues(SelectList,split){
  		var iLen = SelectList.length;
  		var values = "";
  		for (i = 0;i<iLen;i++)
		{
			if ( i == 0 ){
				values = SelectList.options[i].value;
			}else{
				values = values +split+SelectList.options[i].value
			}
		}
		return values;
  }
 //向下拉框添加一项
 function addItem(text,addList)
 {
	var objSelected;
	objSelected= new Option(text,text);
	addList.add(objSelected); 
 }
 //向下拉框添加一项
 function addItemByKeyAndText(value,text,addList)
 {
	var objSelected;
	objSelected= new Option(text,value);
	addList.add(objSelected); 
 }
 //移出下拉框一项
 function removeItem(SelectList)
 {
       var i = 0 ;         
       var imark = 1;
       for (i=0;i<SelectList.options.length;i++)
       {
            if (SelectList.options[i].selected)
              {
                SelectList.remove(i);
                i = i - 1 ;
                imark = imark +1;
              }                
        }  
       if (imark==1){
			alert("请选择一项进行操作！");
			return ;
       }
 }
//移出下拉框所有项
 function removeAllItem(SelectList)
 {
       var i = 0 ;         
       for (i=0;i<SelectList.options.length;i++)
          {
                SelectList.remove(i);
                i = i - 1 ;
            
          }  
 }
 function allSelectCheckBox(objSelect,flag){
 	if (objSelect.length == null || objSelect.length == 0 ){
 		return false;
 	}
	for (var i = 0;i<objSelect.length;i++){
		objSelect.checked = flag;
	}
	return true;
}