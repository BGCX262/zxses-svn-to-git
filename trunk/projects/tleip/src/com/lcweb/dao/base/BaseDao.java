package com.lcweb.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao {
	//ͨ�÷���
	public void saveObject(Object object);
	public void saveObjects(List<Object> objects);
	public void updateObject(Object object);
	public void updateObjects(List list);
	public void saveOrUpdate(Object object);
	public Object queryObjectById(Object object,Object id);
	public Object queryObjectById(Class cls,Object id);
	public Object queryObjectById(Class cls,Serializable id);
	public Object queryObjectByLongId(Object object,Long id);
	public Object queryObjectByLongId(Class cls,Long id);
	public List queryObjectList(String hql);
	public List queryByPage(final String hql,final int offset,final int pageSize);
	public void deleteObjects(Object object,String[] ids);
	public void deleteObjectsForInteger(Object object, String[] ids);
	public void deleteObjectsAndChilds(Object object,String queryChildHql,String[] ids);
	public void deleteObject(Object object);
	public void deleteAll(String hql);

	//public List findListByHSQL(final String hsql, final Map paraMap);
	//public List findListBySQL(final String hsql, final Map paraMap) ;
	public List findListByHSQLId(final String sqlId, final Map paraMap);
	public List findListBySQLId(final String sqlId, final Map paraMap);
	public List findPageByHSQLId(final String sqlId, final Map paraMap,final int offset,final int pageSize);
	public List findPageBySQLId(final String sqlId, final Map paraMap,final int offset,final int pageSize);
}
