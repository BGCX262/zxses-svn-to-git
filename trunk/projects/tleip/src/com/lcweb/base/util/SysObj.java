package com.lcweb.base.util;

public class SysObj {
	public static String createMassageBox(String msg){
		StringBuffer sb = new StringBuffer();
		if ( msg != null && !msg.trim().equalsIgnoreCase("")){
			sb.append("<script language='JavaScript' type='text/JavaScript'>\n");
			sb.append("alert('"+msg+"');\n");
			sb.append("</script>\n");
		}
		return sb.toString();
	}
	public static String createDeleteMassageBox(int delRecords,String msg){
		String str = "";
		if (msg != null && !"".equalsIgnoreCase(msg)){
			str = "["+msg +"]存在关联，不能删除！"; 
		}else{
			str = "删除"+delRecords+"条记录成功!";
		}
		return createMassageBox(str);
	}
	public static String createAddMassageBox(String name){
		String str = "";
		if (name!= null && !"".equalsIgnoreCase(name)){
			str = "新增["+name+"]成功！"; 
		}else{
			str = "新增成功!";
		}
		return createMassageBox(str);
	}
	public static String createLogicDeleteMassageBox(int delRecords){
		String str = "删除"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createEnableMassageBox(int delRecords){
		String str = "启用"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createPublishMassageBox(int delRecords){
		String str = "发布"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createUnPublishMassageBox(int delRecords){
		String str = "反发布"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createDisableMassageBox(int delRecords){
		String str = "禁用"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createApproveMassageBox(int delRecords){
		String str = "审批"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createUnApproveMassageBox(int delRecords){
		String str = "反审批"+delRecords+"条记录成功!";
		return createMassageBox(str);
	}
	
	public static String createEditMassageBox(String name){
		String str = "";
		if (name!= null && !"".equalsIgnoreCase(name)){
			str = "修改["+name+"]成功！"; 
		}else{
			str = "修改成功!";
		}
		return createMassageBox(str);
	}
	public static String createAddPersonMassageBox(String name){
		String str = "";
		if (name!= null && !"".equalsIgnoreCase(name)){
			str = "添加["+name+"]成功！"; 
		}else{
			str = "添加成功!";
		}
		return createMassageBox(str);
	}
	public static String createExistsMassageBox(String name){
		String str = "";
		if (name!= null && !"".equalsIgnoreCase(name)){
			str = "["+name+"]已经被使用，请重新输入！"; 
		}
		return createMassageBox(str);
	}
	public static String createGenerateMessageBox(int delRecords,String msg){
		String str = "";
		if (msg!= null && !"".equalsIgnoreCase(msg)){
			str = "共生成"+delRecords+"条新闻页面成功!"; 
		}else{
			str = "共生成"+delRecords+"条新闻页面成功!"; 
		}
		return createMassageBox(str);
	}
	public static String createSumbitMassageBox(){
		String str = "提交完成!";
		return createMassageBox(str);
	}
	public static String sendMessageMassageBox(){
		String str = "信息发送成功!";
		return createMassageBox(str);
	}
}
