package com.lcweb.base.util;

import java.util.ArrayList;
import java.util.List;

import com.lcweb.service.base.BaseService;

public class PageList {
	private int currentPage=1;
    private int pageSize=10;
    private int recordCounts;
    private int pageCount;
    private String home;
    private String pageup;
    private String pagedown;
    private String end;
    private String path;
    private String formBeanName;
    @SuppressWarnings("unchecked")
	private List list = new ArrayList();

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCounts() {
		return recordCounts;
	}

	public void setRecordCounts(int recordCounts) {
		this.recordCounts = recordCounts;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getPageup() {
		return pageup;
	}

	public void setPageup(String pageup) {
		this.pageup = pageup;
	}

	public String getPagedown() {
		return pagedown;
	}

	public void setPagedown(String pagedown) {
		this.pagedown = pagedown;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

 
	public PageList(int currentPage,int recordCounts,String path,String pagesize,String formBeanName) {
		this.currentPage = currentPage;
		this.recordCounts = recordCounts;
		this.path = path;
		if(pagesize!=null && !pagesize.equals("")){
			this.pageSize= Integer.parseInt(pagesize);
		}
		
		this.formBeanName = formBeanName;
		init();
	}
	private void init() {
		 pageCount = recordCounts%pageSize==0 ? recordCounts/pageSize : recordCounts/pageSize+1;
		 if(currentPage<0){
			 currentPage = 1;
		 }
		 if(currentPage>pageCount){
			 currentPage = pageCount;
		 }
		 if(currentPage!=1){
			 home = path+"&currentPage=1";
			 pageup = path+"&currentPage="+(currentPage-1);
			}
		if(currentPage!=pageCount&&pageCount!=0){
			pagedown = path+"&currentPage="+(currentPage+1);
			end = path+"&currentPage="+pageCount;
		}
	}
	public String getView() {
  		String param = "";
  		if(this.recordCounts==0)param="<font color='#CC3300'>对不起，没有你要的数据!</font>";
  		else
  		{
	  		param+="共<font color=\"#FF6600\"><b>"+this.recordCounts+"</b></font>条&nbsp;&nbsp;第<font color=\"#FF6600\"><b>"+this.currentPage+"</b></font>页 共<font color=\"#FF6600\"><b>"+this.pageCount+"</b></font>页&nbsp;&nbsp;每页<font color=\"#FF6600\"><b>"+this.pageSize+"</b></font>条";
	  		
	  	    if(currentPage==1){
	  	    	param+="<img src=\"../res/admin/img/first.gif\" border=\"0\" alt=\"首页\" style=\"cursor: pointer\">&nbsp;&nbsp;";
	  	    	param+="<img src=\"../res/admin/img/pre.gif\" border=\"0\" alt=\"上页\" style=\"cursor: pointer\">&nbsp;&nbsp;&nbsp;&nbsp;";
	        }else{
	        	param+="<img onclick=home() src=\"../res/admin/img/first.gif\" border=\"0\" alt=\"首页\" style=\"cursor:pointer\">&nbsp;&nbsp;";
	        	param+="<img onclick=pageup() src=\"../res/admin/img/pre.gif\" border=\"0\" alt=\"上页\" style=\"cursor:pointer\">&nbsp;&nbsp;&nbsp;&nbsp;";
	        }
	        
	        if(currentPage==pageCount){
	        	param+="<img src=\"../res/admin/img/next.gif\" border=\"0\" alt=\"下页\" style=\"cursor: pointer\">&nbsp;&nbsp;";
	        	param+="<img src=\"../res/admin/img/last.gif\" border=\"0\" alt=\"末页\" style=\"cursor: pointer\">&nbsp;&nbsp;&nbsp;";
	        }else{
	        	param+="<img onclick=pagedown() src=\"../res/admin/img/next.gif\" border=\"0\" alt=\"下页\" style=\"cursor:pointer\">&nbsp;&nbsp;";
	        	param+="<img onclick=end() src=\"../res/admin/img/last.gif\" border=\"0\" alt=\"末页\" style=\"cursor:pointer\">&nbsp;&nbsp;&nbsp;";
	        }
	  		
	        param+=" 每页显示<input type=\"text\" name=\"pagesize\" size=\"1\" maxlength=\"4\" value=\""+pageSize+"\" onkeypress=\"xiangying(event)\"> <input type=\"button\"  onclick=\"gopage()\" class=\"an\" style=\"cursor:pointer\" value=\"GO\">	";
	        
	        
	        param+=" <script type=\"text/javascript\">    ";
	        param+="function home(){";
	        param+=formBeanName+".action=\""+home+"\";";
	        param+=formBeanName+".submit();";
	        param+="}";
	        param+="function pageup(){";
	        param+=formBeanName+".action=\""+pageup+"\";";
	        param+=formBeanName+".submit();";
	        param+="}";
	        param+="function pagedown(){";
	        param+=formBeanName+".action=\""+pagedown+"\";";
	        param+=formBeanName+".submit();";
	        param+="}";
	        param+="function end(){";
	        param+=formBeanName+".action=\""+end+"\";";
	        param+=formBeanName+".submit();";
	        param+="}";
	  
	        param+="function gopage(){";
	        param+=" var v = document.getElementsByName(\"pagesize\")[0].value;";
	        param+="var   r   =   /^\\+?[1-9][0-9]*$/;";
	        param+="if(!r.test(v)){";
	        param+="alert(\"每页显示记录条数必须是正整数\");";
	        param+="return false;";
	        param+="}";
	        param+=formBeanName+".action=\""+path+"&currentPage=1\";";
	        param+=formBeanName+".submit();";
	        param+="}";
	        param+="function xiangying(event){";
	        param+="if(event.keyCode==13){";
	        param+=" gopage()"; 
	        param+="}";
	        param+="}	 ";
	        param+="</script><input type=\"text\" style=\"display:none\">";
	        
	        
	         
  		}
	     return param;
	}
	/*
	 * 通用分页
	 * sqlCount：查询总记录条数的语句
	 * sql     ：查询语句
	 * currentPage ：当前页
	 * pagesize：每页显示大小,为null则为默认10
	 * path    ：用于分页的跳转路径
	 * service ：基础service 所有业务层的service应继承基础service，这里传入的是本action 的service
	 * formBeanName ：本action对应的actionform的名字
	 */
	@SuppressWarnings("unchecked")
	public static PageList page(String sqlCount,String sql,String currentPage,String pagesize,String path,BaseService service,String formBeanName){
		 Object obj = service.queryObjectList(sqlCount).get(0);
	     int recordCounts = 0 ;
	     if(obj != null){
	     	recordCounts = (Integer) obj; 
	     }
	     
	     if("".equals(currentPage)||currentPage==null){
	    	 currentPage = "1";
	     }
	     PageList pageList;
	 	 
	 	 pageList = new PageList(Integer.parseInt(currentPage),recordCounts,path,pagesize,formBeanName);
	 	 
	      
	    int  cp = Integer.parseInt(currentPage);
		List list = service.queryByPage(sql, (cp-1)*pageList.getPageSize(), pageList.getPageSize());
		pageList.setList(list);
		return 	pageList;
	 }
	
	
	
	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}
 
	public String getFormBeanName() {
		return formBeanName;
	}

	public void setFormBeanName(String formBeanName) {
		this.formBeanName = formBeanName;
	}
}
