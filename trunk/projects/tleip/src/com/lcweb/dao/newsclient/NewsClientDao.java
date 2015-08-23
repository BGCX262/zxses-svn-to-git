package com.lcweb.dao.newsclient;

import java.util.List;
import java.util.Map;

import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.dao.base.BaseDao;

public interface NewsClientDao extends BaseDao {
	public List<NewsContentManage> queryAllNewsContentManagerForPage(Map paraMap, int frist, int max);

	public List<NewsItemBig> queryAllNewsItemBigForPage(Map paraMap, int frist, int max);

	public List<NewsItemSmall> queryAllNewsItemSmallForPage(Map paraMap, int frist, int max);
	
	public String queryAllNumber();
}
