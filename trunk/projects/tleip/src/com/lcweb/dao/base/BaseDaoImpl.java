package com.lcweb.dao.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.RequestScope;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
		private SqlMapClient sqlMapClient;
		
		public void setSqlMapClient(SqlMapClient sqlMapClient) {
	        this.sqlMapClient = sqlMapClient;
	    }
/*
		private SqlMapClientTemplate sqlMapClientTemplate;
		
		public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
	        this.sqlMapClientTemplate = sqlMapClientTemplate;
	    }
	*/	
		public void saveObject(Object object) {
		   this.getHibernateTemplate().save(object);
		}
		
		public void saveObjects(List<Object> objects) {
			for(int i=0; i<objects.size(); i++) {
				this.getHibernateTemplate().saveOrUpdate(objects.get(i));
			}
		}

		public void updateObject(Object object) {
		   this.getHibernateTemplate().saveOrUpdate(object);
		}
		
		public void updateObjects(List list) {
			this.getHibernateTemplate().update(list);
		}
		
		
		public List  queryObjectList(String hql) {
			return getHibernateTemplate().find(hql);
		}
		
		public List queryByPage(final String hql,final int offset,final int pageSize)
		{
		   List list=this.getHibernateTemplate().executeFind(new HibernateCallback()
		   {
		    public Object doInHibernate(Session session)
		      throws HibernateException, SQLException {
		     List result=session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		     return result;
		    }
		   }
		   );
		   return list;
		}		
		
		public void deleteObjects(Object object, String[] ids) {
			 for(int i=0;i<ids.length;i++){
				 Long  id = Long.parseLong(ids[i]);
				 Object o = this.queryObjectById(object, id);
				 this.getHibernateTemplate().delete(o);
			 }			
		}
		
		public void deleteObjectsForInteger(Object object, String[] ids) {
			 for(int i=0;i<ids.length;i++){
				 Integer id = Integer.parseInt(ids[i]);
				 Object o = this.queryObjectById(object, id);
				 this.getHibernateTemplate().delete(o);
			 }			
		}
		
		public void deleteObjectsAndChilds(Object object, String queryChildHql,
				String[] ids) {
			 for(int i=0;i<ids.length;i++){
				 Integer id = Integer.parseInt(ids[i]);
				 this.getHibernateTemplate().deleteAll(this.queryObjectList(queryChildHql+ids[i]));
				 Object o = this.queryObjectById(object, id);
				 this.getHibernateTemplate().delete(o);
			 }						
		}		

		public void deleteObject(Object object) {
	        this.getHibernateTemplate().delete(object);		
		}

		

		public void saveOrUpdate(Object object) {
			this.getHibernateTemplate().saveOrUpdate(object);			
		}

		public Object queryObjectByLongId(Object object, Long id) {
			return this.getHibernateTemplate().get(object.getClass(), id);
		}
		
		public Object queryObjectByLongId(Class cls, Long id) {
			return this.getHibernateTemplate().get(cls, id);
		}
		/*
		public Object queryObjectById(Object object,Integer id) {
			return this.getHibernateTemplate().get(object.getClass(),id);
		}
	   */
		public void deleteAll(String hql) {
			 List list = this.getHibernateTemplate().find(hql);
			 this.getHibernateTemplate().deleteAll(list);			
		}
		//add common method   start ---
		public Object queryObjectById(Object object, Object id) {
			if (id instanceof Long){
				return this.getHibernateTemplate().get(object.getClass(), (Long)id);
			}else if (id instanceof Integer){
				return this.getHibernateTemplate().get(object.getClass(), (Integer)id);
			}else {
				return this.getHibernateTemplate().get(object.getClass(), (String)id);
			}
		}
		
		public Object queryObjectById(Class cls, Serializable id) {
			return this.getHibernateTemplate().get(cls, id);
		}
		
		public Object queryObjectById(Class cls, Object id) {
			if (id instanceof Long){
				return this.getHibernateTemplate().get(cls, (Long)id);
			}else if (id instanceof Integer){
				return this.getHibernateTemplate().get(cls, (Integer)id);
			}else {
				return this.getHibernateTemplate().get(cls, (String)id);
			}
		}
		
		public String getSQLStatementById(String sqlId, Object paramObject) {
		    	if (sqlMapClient == null) {
		            throw new RuntimeException("No IBATIS sqlMapClient setted!");
		        }
		        String sql = null;
		        ExtendedSqlMapClient extendedSqlMapClient = (ExtendedSqlMapClient) sqlMapClient;
		        MappedStatement mappedStatement = extendedSqlMapClient.getMappedStatement(sqlId);
		        if (mappedStatement != null) {
		            RequestScope request = new RequestScope();
		            request.setStatement(mappedStatement);
		            sql = mappedStatement.getSql().getSql(request, paramObject);
		            //sql = this.parseSqlWithDBScheme(sql);
		        }
		        return sql;
		}
		public List findListByHSQLId(final String sqlId, final Map paraMap) {
	        String hsql = getSQLStatementById(sqlId, paraMap);
	        if (hsql == null) {
	            throw new RuntimeException("No configed HSQL-Statement in SqlMap files, ID:" + sqlId);
	        }
	        return findListByHSQL(hsql, paraMap);
	    }
		public List findListBySQLId(final String sqlId, final Map paraMap) {
	        String sql = getSQLStatementById(sqlId, paraMap);
	        if (sql == null) {
	            throw new RuntimeException("No configed SQL-Statement in SqlMap files, ID:" + sqlId);
	        }
	        return findListBySQL(sql, paraMap);
	    }
		private List findListByHSQL(final String hsql, final Map paraMap) {
	        return findListByHSQLOrSQL(hsql, true, paraMap);
	    }
		private List findListBySQL(final String hsql, final Map paraMap) {
	        return findListByHSQLOrSQL(hsql, false, paraMap);
	    }
		private List findListByHSQLOrSQL(final String sql, final boolean isHSQL, final Map paraMap) {
	        Query query;
	        if (isHSQL) {
	            query = getSession().createQuery( sql );
	        } else {
	            query = getSession().createSQLQuery( sql );
	        }
	        setQueryParameters(query, paraMap);
	        return query.list();
	    }
		public List findPageByHSQLId(final String sqlId, final Map paraMap,final int offset,final int pageSize) {
	        String hsql = getSQLStatementById(sqlId, paraMap);
	        if (hsql == null) {
	            throw new RuntimeException("No configed HSQL-Statement in SqlMap files, ID:" + sqlId);
	        }
	        return findPageByHSQLOrSQL(hsql, true,paraMap,offset,pageSize);
	    }
		
		public List findPageBySQLId(final String sqlId, final Map paraMap,final int offset,final int pageSize) {
	        String hsql = getSQLStatementById(sqlId, paraMap);
	        if (hsql == null) {
	            throw new RuntimeException("No configed HSQL-Statement in SqlMap files, ID:" + sqlId);
	        }
	        return findPageByHSQLOrSQL(hsql, false,paraMap,offset,pageSize);
	    }

		private List findPageByHSQLOrSQL(final String sql, final boolean isHSQL, final Map paraMap,final int offset,final int pageSize) {
	        Query query;
	        if (isHSQL) {
	            query = getSession().createQuery( sql ).setFirstResult(offset).setMaxResults(pageSize);
	        } else {
	            query = getSession().createSQLQuery( sql ).setFirstResult(offset).setMaxResults(pageSize);;
	        }
	        setQueryParameters(query, paraMap);
	        return query.list();
	    }
		private void setQueryParameters(Query query, Map paraMap) {
	        if (query != null && paraMap != null && !paraMap.isEmpty()) {
	            List namedParms = Arrays.asList(query.getNamedParameters());
	            Iterator iter = paraMap.keySet().iterator();
	            while (iter.hasNext()) {
	                String paraName = (String) iter.next();
	                if (namedParms.contains(paraName)) {
	                    Object value = paraMap.get(paraName);
	                    if (value instanceof List) {
	                        query.setParameterList(paraName, (List) value);
	                    } else {
	                        query.setParameter(paraName, value);
	                    }
	                }
	            }
	        }
	    }
}		//add common method  --- end---
