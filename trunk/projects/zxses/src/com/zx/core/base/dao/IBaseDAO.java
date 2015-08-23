package com.zx.core.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.lcsoft.control.tag.page.pojo.Paginate;

/*******************************************************************************
 * 基础DAO的接口
 * 
 * @author maolujun
 * 
 */
public interface IBaseDAO<T> {
    
    /**
     *根据主键id加载一个对象 
     * @param id:需要加载的对象的id
     * @return:返回与id相对应的对象
     */
    public Object load(Class clx, Serializable id) throws Exception;

    /**
     * 添加一个对象到数据库中
     * 
     * @param obj：要添加的对象
     */
    public void save(Object obj) throws Exception;

    /**
     * 根据对象从数据库中删除此对象
     * 
     * @param obj：所要刪除的对象
     */
    public void delete(Object obj) throws Exception;

    /**
     * 对数据库中的此对象进行更新
     * 
     * @param obj：要更改后的对象
     */
    public void update(Object obj) throws Exception;
    /***************************************************************************
     * 保存一个集合的数据
     * 
     * @param collection
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void saveOrUpdate(Collection collection) throws Exception ;

    /**
     * 根据hql以及参数进行查询
     * 
     * @param hql：需要执行的HQL语句
     * @param values：HQL语句中所需的参数
     * @return：返回满足条件的对象集合
     */
    public List find(String hql, Object[] values) throws Exception;

    /**
     * 根据hql进行查询
     * 
     * @param hql：需要执行的HQL语句
     * @return：返回满足条件的对象集合
     */
    public List find(String hql) throws Exception;

    /**
     * 根据hql语句查询出一个对象
     * 
     * @param hql：HQL语句
     * @return：返回一个满足条件的对象
     */
    public Object findUniqueResult(String hql) throws Exception;

    /**
     * 根据hql语句以及参数查询出一个对象
     * 
     * @param hql：HQL语句
     * @param values：HQL语句中的参数
     * @return：返回一个满足条件的对象
     */
    public Object findUniqueResult(String hql, Object[] values) throws Exception;

    /**   
     * @param parameters
     * @return
     * @throws Exception
     * @Description: 返回满足条件的对象集合
     */
    public List<T> findObjects(Map<String, Object> parameters) throws Exception ;
    
    public Paginate getPaginate();

    public void setPaginate(Paginate paginate);
}
