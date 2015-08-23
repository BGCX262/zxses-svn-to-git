package com.agilefly.service.blogarticle.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agilefly.bean.BlogArticle;
import com.agilefly.service.base.BaseDaoImpl;
import com.agilefly.service.blogarticle.IBlogArticleService;

@Service
@Transactional
public class BlogArticleService extends BaseDaoImpl<BlogArticle> implements IBlogArticleService {
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<BlogArticle> getArticleByType(int systypeid) {
		String whereHql = "o.sysType.id=? and o.approveStatus=?";
		Object[] params = new Object[]{systypeid,(byte)1};
		
		//按照时间排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("postTime", "desc");
		
		return findByCondition(whereHql, params, orderby);
	}
	
}
