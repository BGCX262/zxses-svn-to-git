package com.zx.core.base.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.lcsoft.control.tag.page.util.PaginationUtils;

/*******************************************************************************
 * 基础DAO类的实现
 * 
 * @author maolujun
 * 
 */
public class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {

    private Class<T> entityClass;
    
    @SuppressWarnings("unchecked")
    public BaseDAO() {
	this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }
    
    /**
     * 根据主键id加载一个对象
     * 
     * @param id:需要加载的对象的id
     * @return:返回与id相对应的对象
     */
    @SuppressWarnings("unchecked")
    public Object load(Class clx, Serializable id) throws Exception {
        return getHibernateTemplate().load(clx, id);
    }

    /**
     * 添加一个对象到数据库中
     * 
     * @param obj：要添加的对象
     */
    public void save(Object obj) throws Exception {
        getHibernateTemplate().save(obj);
    }

    /***************************************************************************
     * 保存一个集合的数据
     * 
     * @param collection
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void saveOrUpdate(Collection collection) throws Exception {
        this.getHibernateTemplate().saveOrUpdateAll(collection);
    }

    /**
     * 根据对象从数据库中删除此对象
     * 
     * @param obj：所要刪除的对象
     */
    public void delete(Object obj) throws Exception {
        getHibernateTemplate().delete(obj);
    }

    /***************************************************************************
     * 删除一个集合的数据
     * 
     * @param collection
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void deleteCollection(Collection collection) throws Exception {
        this.getHibernateTemplate().deleteAll(collection);
    }

    /**
     * 对数据库中的此对象进行更新
     * 
     * @param obj：要更改后的对象
     */
    public void update(Object obj) throws Exception {
        getHibernateTemplate().update(obj);
    }

    /**
     * 根据hql以及参数进行查询
     * 
     * @param hql：需要执行的HQL语句
     * @param values：HQL语句中所需的参数
     * @return：返回满足条件的对象集合
     */
    @SuppressWarnings("unchecked")
    public List find(String hql, Object[] values) throws Exception {
        return getHibernateTemplate().find(hql, values);
    }

    /**
     * 根据hql进行查询
     * 
     * @param hql：需要执行的HQL语句
     * @return：返回满足条件的对象集合
     */
    @SuppressWarnings("unchecked")
    public List find(String hql) throws Exception {
        return getHibernateTemplate().find(hql);
    }

    /**
     * 根据hql语句查询出一个对象
     * 
     * @param hql：HQL语句
     * @return：返回一个满足条件的对象
     */
    @SuppressWarnings("unchecked")
    public Object findUniqueResult(String hql) throws Exception {
        return getSession().createQuery(hql).uniqueResult();
    }

    /**
     * 根据hql语句以及参数查询出一个对象
     * 
     * @param hql：HQL语句
     * @param values：HQL语句中的参数
     * @return：返回一个满足条件的对象
     */
    @SuppressWarnings("unchecked")
    public Object findUniqueResult(String hql, Object[] values)
            throws Exception {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query.uniqueResult();
    }
    
    public List<T> findObjects(Map<String, Object> parameters) throws Exception {
	String hql = "from " + entityClass.getName() + " model where model.recordStatus = 1";
    	if(parameters!=null){
    	    if(hql.indexOf("where")!=-1){
    		Iterator<String> keys = parameters.keySet().iterator();
    		while (keys.hasNext()) {
    		    String k = keys.next();
    		    hql += " and " + k + (k.endsWith("name")?" like :":" = :") +((k.indexOf(".")==-1)?k:k.substring(k.lastIndexOf(".")+1, k.length()));
    		}
    	    } else {
    		hql += " where ";
    		int i = 0;
    		Iterator<String> keys = parameters.keySet().iterator();
    		while (keys.hasNext()) {
    		    if(i>0){
    			hql += " and ";
    		    }
    		    String k = keys.next();
    		    hql += k + " = :" +((k.indexOf(".")==-1)?k:k.substring(k.lastIndexOf(".")+1, k.length()));
    		    hql += " and " + k + (k.endsWith("name")?" like :":" = :") +((k.indexOf(".")==-1)?k:k.substring(k.lastIndexOf(".")+1, k.length()));
    		    i++;
    		}
    	    }
    	}
    	if(paginate!=null){
    	    Object[] objs = new Object[parameters.size()];
    	    Iterator<String> keys = parameters.keySet().iterator();
    	    int i = 0;
    	    while (keys.hasNext()) {
    		objs[i] = parameters.get(keys);
    		i++;
    	    }
    	    PaginationUtils.getPaginationUtils().searchPaginate(hql, objs,paginate);
    	    return paginate.getDatas();
    	} else {
    	    Query query = getSession().createQuery(hql);
    	    if (parameters != null) {
    		Iterator<String> keys = parameters.keySet().iterator();
    		while (keys.hasNext()) {
    			String k = keys.next();
    			Object v = (Object) parameters.get(k);
    			query.setParameter(((k.indexOf(".")==-1)?k:k.substring(k.lastIndexOf(".")+1, k.length())),v);
    		}
    	    }
    	    return query.list();
    	}
    }

    private Paginate paginate = null;
    
    public Paginate getPaginate() {
	return paginate;
    }

    public void setPaginate(Paginate paginate) {
	this.paginate = paginate;
    }
}
