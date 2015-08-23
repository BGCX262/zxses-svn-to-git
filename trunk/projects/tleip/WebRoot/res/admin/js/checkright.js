function checkright(parentModuleFlag,operationModuleFlag){ 
   var flag=false;
   $.ajaxSetup({ 
    async: false 
   }); 
   $.post("../login.do?method=checkRight",{parentModuleFlag:parentModuleFlag,operationModuleFlag:operationModuleFlag},back);
     
   function back(data){
            if(data!="true"){
               alert("没有权限");
            }else{
               flag=true;
            }
     }  
     return flag;    
}

function checkright(basepath,parentModuleFlag,operationModuleFlag){ 
   var flag=false;
   $.ajaxSetup({ 
    async: false 
   }); 
   $.post(basepath+"/login.do?method=checkRight",{parentModuleFlag:parentModuleFlag,operationModuleFlag:operationModuleFlag},back);
     
   function back(data){
            if(data!="true"){
               alert("没有权限");
            }else{
               flag=true;
            }
     }  
     return flag;    
}
function checkRightForNews(basepath,parentModuleFlag,operationModuleFlag){ 
   var flag=false;
   $.ajaxSetup({ 
    async: false 
   }); 
   $.post(basepath+"/login.do?method=checkRightForNews",{parentModuleFlag:parentModuleFlag,operationModuleFlag:operationModuleFlag},back);
     
   function back(data){
            if(data!="true"){
               alert('对不起，您没有权限访问!');
            }else{
               flag=true;
            }
     }  
     return flag;    
}



