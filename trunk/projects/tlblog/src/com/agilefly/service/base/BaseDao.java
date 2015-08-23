package com.agilefly.service.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.agilefly.commons.QueryResult;

/**
 * @author boleyn_renlei
 * @date May 13, 2012 3:02:00 PM 
 */
public interface BaseDao<T> {
	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void update(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	public void delete(Serializable... entityids);
	
	/**
	 * 删除实体(通过checkbox删除)
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	public void delete(String[] entityids);

	/**
	 * 获取实体
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public T find(Serializable entityId);
	
	/**
	 * 获取实体--用户Junit测试
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return
	 */
	public T getForTest(Serializable entityId);
	
	/**
	 * 通过指定条件、排序方式获取实体列表 如sysUser的username获得sysUser列表
	 * @param whereHql 无-->设置为null
	 * @param queryParams 无-->设置为null
	 * @param orderby 无-->设置为null
	 * @return
	 */
	public List<T> findByCondition(String whereHql,Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	/**
	 * 通过指定条件获取实体列表 如sysUser的username获得sysUser列表
	 * where key1=?1 and key2=?2 
	 * where o.property=? and o.xx like ? 查询限制
	 * @param whereHql 
	 * @param queryParams
	 * @return
	 */
	public List<T> findByCondition(String whereHql,Object[] queryParams);
	
	/**
	 * 按照指定的排序方式
	 * @param orderby
	 * @return
	 */
	public List<T> findByCondition(LinkedHashMap<String, String> orderby);

	/**
	 * 获取记录总数
	 * @param entityClass 实体类
	 * @return
	 */
	public long getCount();
	
	/**
	 * 获取分页数据完整参数(查询条件、参数和排序方式)
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);

	/**
	 * 获取列表数据 不设置分页(设置查询条件、参数和排序方式)
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @return
	 */
	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 不分页查询
	 * @return
	 */
	public QueryResult<T> getScrollData();
	
	/**
	 * 获取列表数据 不设置分页(设置查询条件、参数)
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @return
	 */
	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams);
	
	/**
	 * 获取列表数据 不设置分页(设置排序方式)
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @return
	 */
	public QueryResult<T> getScrollData(LinkedHashMap<String, String> orderby);
	
	/**
	 * 设置查询条件和参数
	 * @param firstindex
	 * @param maxresult
	 * @param wherejpql
	 * @param queryParams
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
	
	/**
	 * 只设置查询条件
	 * @param firstindex
	 * @param maxresult
	 * @param wherejpql
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql);

	/**
	 * 设置多个排序条件(按照传入的字段顺序排序)
	 * @param firstindex
	 * @param maxresult
	 * @param orderby
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	/**
	 * 无查询条件和排序
	 * @param firstindex
	 * @param maxresult
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult);
	
	
	//*************************************用户列表显示方法(结合pager-taglib分页组件使用)*********************************************************
	
	/**
	 * 从当前线程中获得pager.offset 和 pagesize(从pager-taglib组件中获得) -->无需设置firstindex, maxresult,与以上方法功能一样
	 * @return
	 */
	public QueryResult<T> getScrollDataByThread();
	
	/**
	 * 设置查询条件和参数
	 * 从当前线程中获得pager.offset 和 pagesize(从pager-taglib组件中获得) -->无需设置firstindex, maxresult,与以上方法功能一样
	 * @param wherejpql
	 * @param queryParams
	 * @return
	 */
	public QueryResult<T> getScrollDataByThread(String wherejpql, Object[] queryParams);
	
	/**
	 * 只设置查询条件
	 * 从当前线程中获得pager.offset 和 pagesize(从pager-taglib组件中获得) -->无需设置firstindex, maxresult,与以上方法功能一样
	 * @param wherejpql
	 * @return
	 */
	public QueryResult<T> getScrollDataByThread(String wherejpql);
	
	/**
	 * 设置多个排序条件(按照传入的字段顺序排序)
	 * 从当前线程中获得pager.offset 和 pagesize(从pager-taglib组件中获得) -->无需设置firstindex, maxresult,与以上方法功能一样
	 * @param orderby
	 * @return
	 */
	public QueryResult<T> getScrollDataByThread(LinkedHashMap<String, String> orderby);
	
	/**
	 * 获取分页数据完整参数(查询条件、参数和排序方式)
	 * 从当前线程中获得pager.offset 和 pagesize(从pager-taglib组件中获得) -->无需设置firstindex, maxresult,与以上方法功能一样
	 * @param wherejpql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public QueryResult<T> getScrollDataByThread(String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
}