package com.lcweb.dao.newsclient;

import java.util.List;
import java.util.Map;

import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.dao.base.BaseDaoImpl;

public class NewsClientDaoImpl extends BaseDaoImpl implements NewsClientDao {
	private NewsClientDao newsClientDao;

	@SuppressWarnings("unchecked")
	public List<NewsContentManage> queryAllNewsContentManagerForPage(Map hmPara, int frist, int max) {
		return this.findPageByHSQLId("queryAllNewsContentManage", hmPara, frist, max);
	}

	@SuppressWarnings("unchecked")
	public List<NewsItemBig> queryAllNewsItemBigForPage(Map paraMap, int frist, int max) {
		return this.findPageByHSQLId("queryAllNewsItemBig", paraMap, frist, max);
	}

	@SuppressWarnings("unchecked")
	public List<NewsItemSmall> queryAllNewsItemSmallForPage(Map paraMap, int frist, int max) {
		return this.findPageByHSQLId("queryAllNewsItemSmall", paraMap, frist, max);
	}

	@SuppressWarnings("unchecked")
	public String queryAllNumber() {
		String hql = "select sum(a.visitCount) from NewsContentManage a";
		List list = this.getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			if (list.get(0) != null) {
				return list.get(0).toString();
			}
		}
		return null;
	}


	/**
	 * @return the newsClientDao
	 */
	public NewsClientDao getNewsClientDao() {
		return newsClientDao;
	}

	/**
	 * @param newsClientDao
	 *            the newsClientDao to set
	 */
	public void setNewsClientDao(NewsClientDao newsClientDao) {
		this.newsClientDao = newsClientDao;
	}

}
