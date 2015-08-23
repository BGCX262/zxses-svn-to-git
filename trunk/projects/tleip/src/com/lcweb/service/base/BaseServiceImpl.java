package com.lcweb.service.base;

import java.io.File;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lcweb.dao.base.BaseDao;

public class BaseServiceImpl  implements BaseService {
     private BaseDao baseDao;	
	 
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public Object queryObjectById(Object object,Integer id) {
		return baseDao.queryObjectById(object,id);
	}
	public synchronized void saveObject(Object object) {
		this.baseDao.saveObject(object);
	}
	public void saveObjects(List<Object> objects) {
		this.baseDao.saveObjects(objects);
	}
	public void updateObject(Object object) {
	    this.baseDao.updateObject(object); 
	}
	public void updateObjects(List list) {
		this.baseDao.updateObjects(list);
	}
	public void deleteObjects(Object object, String[] ids) {
		this.baseDao.deleteObjects(object, ids); 
	}
	public void deleteObjectsForInteger(Object object, String[] ids) {
		this.baseDao.deleteObjectsForInteger(object, ids); 
	}
	public void deleteObject(Object object) {
		this.baseDao.deleteObject(object);
	}
	public void deleteObjectsAndChilds(Object object, String queryChildHql,
			String[] ids) {
		this.baseDao.deleteObjectsAndChilds(object, queryChildHql, ids);
	}
	
	public void deleteAll(String hql){
		this.baseDao.deleteAll(hql);
	}
	
	public List queryObjectList(String hql) {
		return  this.baseDao.queryObjectList(hql);
	}
	
	public List queryByPage(String hql, int offset, int pageSize) {
		return baseDao.queryByPage(hql, offset, pageSize);
	}
	public Object queryObjectByLongId(Object object, Long id) {
		return baseDao.queryObjectByLongId(object, id);
	}
	
	public Object queryObjectByLongId(Class cls, Long id) {
		return baseDao.queryObjectByLongId(cls, id);
	}
	 
	public void saveOrUpdate(Object object) {
		baseDao.saveOrUpdate(object);
	}
	public Object queryObjectById(Object object,Object id){
		return baseDao.queryObjectById(object, id);
	}
	
	public Object queryObjectById(Class cls,Object id){
		return baseDao.queryObjectById(cls, id);
	}
	
	public Object queryObjectById(Class cls, Serializable id){
		return baseDao.queryObjectById(cls, id);
	}
	
	public String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		return basePath;
	}
	 public String getUploadFiles(String path,String dir,HttpServletRequest request,boolean isdelete){
		 StringBuffer returnFileNames = new StringBuffer();
		 if(path==null){
			 path = request.getSession().getServletContext().getRealPath("/uploadfiles/")+"/"+dir;
		 }
		   try{
			    String contextPath = request.getContextPath();
			    File existfile = new File(path);
		        File[] getfiles = existfile.listFiles();
		        for(int i=0;i<getfiles.length;i++){
		        	 File f = (File)getfiles[i];
		        	 String url = contextPath+"/view/commons.do?method=downFile&dir="+dir+"&file="+URLEncoder.encode(f.getName(),"UTF-8");
		             String delfile =URLEncoder.encode(f.getName(),"UTF-8");
		             returnFileNames.append("<div id=\"file"+i+"\">");
		             returnFileNames.append("<a href=\""+url+"\">");
		             returnFileNames.append(f.getName());
		             if(isdelete){
		            	 returnFileNames.append("</a>");
			             returnFileNames.append("&nbsp;&nbsp;&nbsp;<a href=\"javascript:deleteUploadFile('"+dir+"','"+delfile+"')\">[ɾ��]<br>");
			             returnFileNames.append("</a>");
		             }
		            
		             returnFileNames.append("</div>");
		        }
		     request.setAttribute("uploadFileList", returnFileNames.toString());   
		   }catch (Exception ex){
			   
		   }
		   return returnFileNames.toString();
	 }
}
