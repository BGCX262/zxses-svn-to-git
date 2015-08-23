package com.agilefly.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.commons.QueryResult;
import com.agilefly.commons.web.SystemContext;
import com.agilefly.utils.GenericsUtils;

/**
 * @author boleyn_renlei
 * @date May 13, 2012 4:34:40 PM 
 */
@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	//为父类HibernateDaoSupport注入sessionFactory的值
	@Resource(name="sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	
	public void delete(Serializable ... entityids) {
		for(Serializable id : entityids){
			getHibernateTemplate().delete(getHibernateTemplate().load(this.entityClass, id));
		}
	}
	
	public void delete(String[] entityids){
		int entitySize = entityids.length;
		Serializable[] items = new Serializable[entitySize];
		for(int i = 0; i < entitySize; i++){
			items[i] = Integer.parseInt(entityids[i]);
		}
		delete(items);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId==null) throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		//有些主键不是Inter类型，需要在前台全转换类型
		/*if(entityId instanceof String) {
			entityId = Integer.parseInt((String)entityId);
		}*/
		//return (T)getHibernateTemplate().get(this.entityClass, entityId);
		return (T)getHibernateTemplate().load(this.entityClass, entityId);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	@Deprecated
	public T getForTest(Serializable entityId) {
		if(entityId==null) throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		//有些主键不是Inter类型，需要在前台全转换类型
		/*if(entityId instanceof String) {
			entityId = Integer.parseInt((String)entityId);
		}*/
		//return (T)getHibernateTemplate().get(this.entityClass, entityId);
		return (T)getHibernateTemplate().get(this.entityClass, entityId);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<T> findByCondition(String whereHql,Object[] queryParams,LinkedHashMap<String, String> orderby){
		String entityname = getEntityName(this.entityClass);
		Query query = getSession().createQuery("select o from "+ entityname+ " o "+(whereHql==null || "".equals(whereHql.trim())? "": "where "+ whereHql)+ buildOrderby(orderby));
		
		//限制条件 参数值
		setQueryParams(query, queryParams);
		List<T> resultList = query.list();
		return resultList;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<T> findByCondition(String wherejpql, Object[] queryParams){
		return findByCondition(wherejpql, queryParams, null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<T> findByCondition(LinkedHashMap<String, String> orderby){
		return findByCondition(null, null, orderby);
	}

	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}
	
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount() {
		return (Long)getSession().createQuery("select count(*) from " + getEntityName(this.entityClass)+ " o").uniqueResult();
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstindex,maxresult,null,null,orderby);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getScrollData(firstindex,maxresult,wherejpql,queryParams,null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql) {
		return getScrollData(firstindex,maxresult,wherejpql,null,null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) {
		return getScrollData(firstindex,maxresult,null,null,null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1);
	}
	
	//从当前线程中获得pager.offset 和 pagesize
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollDataByThread() {
		return getScrollData(SystemContext.getOffset(), SystemContext.getPagesize());
	}
	
	//设置查询条件和参数
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollDataByThread(String wherejpql, Object[] queryParams) {
		return getScrollData(SystemContext.getOffset(), SystemContext.getPagesize(), wherejpql, queryParams);
	}
	
	//只设置查询条件
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollDataByThread(String wherejpql) {
		return getScrollData(SystemContext.getOffset(), SystemContext.getPagesize(), wherejpql, null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollDataByThread(LinkedHashMap<String, String> orderby) {
		return getScrollData(SystemContext.getOffset(), SystemContext.getPagesize(), orderby);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollDataByThread(String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby) {
		return getScrollData(SystemContext.getOffset(), SystemContext.getPagesize(), wherejpql, queryParams, orderby);
	}

	//分页查询全部参数
	//where key1=?1 and key2=?2 这种方式无效
	//使用where o.property=? and o.xx like ? 查询限制
	//使用LinkedHashMap 按照添加的顺序排序
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult
			, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby) {
		QueryResult qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);
		Query query = getSession().createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
		//限制条件 参数值
		setQueryParams(query, queryParams);
		
		if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex).setMaxResults(maxresult);
		qr.setResultlist(query.list());
		query = getSession().createQuery("select count(*) from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long)query.uniqueResult());
		return qr;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby){
		return getScrollData(-1, -1, wherejpql, queryParams, orderby);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams){
		return getScrollData(wherejpql, queryParams, null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(LinkedHashMap<String, String> orderby){
		return getScrollData(null, null, orderby);
	}
	
	protected static void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				//hibernate 从0开始
				query.setParameter(i, queryParams[i]);
			}
		}
	}
	/**
	 * 组装order by语句
	 * @param orderby
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	/**
	 * 获取实体的名称
	 * @param <E>
	 * @param clazz 实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz){
		String entityname = clazz.getSimpleName();
		return entityname;
	}
}
