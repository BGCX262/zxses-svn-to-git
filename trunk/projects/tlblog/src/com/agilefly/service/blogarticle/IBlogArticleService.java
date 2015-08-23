package com.agilefly.service.blogarticle;

import java.util.List;

import com.agilefly.bean.BlogArticle;
import com.agilefly.service.base.BaseDao;

public interface IBlogArticleService extends BaseDao<BlogArticle> {
	public List<BlogArticle> getArticleByType(int systypeid);
}
