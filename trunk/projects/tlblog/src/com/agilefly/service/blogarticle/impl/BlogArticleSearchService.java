package com.agilefly.service.blogarticle.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.compass.core.Compass;
import org.compass.spring.CompassDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.bean.BlogArticle;
import com.agilefly.commons.QueryResult;
import com.agilefly.commons.web.SystemContext;
import com.agilefly.service.blogarticle.IBlogArticleSearchService;
import com.agilefly.utils.StringUtil;

/**
 * @author boleyn_renlei
 * @date Jun 4, 2012 10:18:14 AM
 * 博客文章搜素service
 */
@Service @Transactional
public class BlogArticleSearchService extends CompassDaoSupport implements IBlogArticleSearchService{
	@Resource
	public void setSuperCompass(Compass compass){
		super.setCompass(compass);
	}
	
	@Override
	public QueryResult<BlogArticle> search(String key, int startIndex, int maxResult) {
		return this.getCompassTemplate().execute(new BlogArticleCallback(key, startIndex, maxResult));
	}

	@Override
	public QueryResult<BlogArticle> searchByThread(String key) {
		if(StringUtil.getNullString(key).length() == 0){
			List<BlogArticle> articles = new ArrayList<BlogArticle>();
			QueryResult<BlogArticle> qr = new QueryResult<BlogArticle>();
			qr.setResultlist(articles);
			qr.setTotalrecord(0);//设置查询到的总记录数
			return qr;
		}
		return this.getCompassTemplate().execute(new BlogArticleCallback(key, SystemContext.getOffset(), SystemContext.getPagesize()));
	}
}
