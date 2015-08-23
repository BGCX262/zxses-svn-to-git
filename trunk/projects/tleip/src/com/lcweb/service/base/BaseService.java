package com.lcweb.service.base;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BaseService {
	public void saveObject(Object object);
	public void saveObjects(List<Object> objects);
	public void updateObject(Object object);
	public void updateObjects(List list);
	public void saveOrUpdate(Object object);
	public List queryByPage(final String hql,final int offset,final int pageSize);
	public Object queryObjectById(Object object,Integer id);
	public Object queryObjectByLongId(Object object,Long id);
	public Object queryObjectByLongId(Class cls, Long id);
	public Object queryObjectById(Class cls, Serializable id);
	public void deleteObjects(Object object,String[] ids);
	public void deleteObjectsForInteger(Object object, String[] ids);
	public void deleteObjectsAndChilds(Object object,String queryChildHql,String[] ids);
	public void deleteObject(Object object);
	public void deleteAll(String hql);
	public List queryObjectList(String hql);
	
	public Object queryObjectById(Object object,Object id);
	public Object queryObjectById(Class cls,Object id);
	public String getBasePath(HttpServletRequest request);
	/* 
	 */
	public String getUploadFiles(String path,String dir,HttpServletRequest request,boolean isdelete);
	 
}
