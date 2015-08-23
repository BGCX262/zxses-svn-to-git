package com.agilefly.service.blogarticle;

import com.agilefly.bean.BlogArticle;
import com.agilefly.commons.QueryResult;

public interface IBlogArticleSearchService {
	public QueryResult<BlogArticle> search(String key, int startIndex, int maxResult);
	public QueryResult<BlogArticle> searchByThread(String key);
}
