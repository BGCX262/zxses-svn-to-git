package com.agilefly.service.blogarticle.impl;

import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;

import com.agilefly.bean.BlogArticle;
import com.agilefly.commons.QueryResult;
import com.agilefly.utils.HtmlUtils;

public class BlogArticleCallback implements CompassCallback<QueryResult<BlogArticle>> {
	private String key;
	private int startIndex;
	private int maxResult;
	
	@Override
	public QueryResult<BlogArticle> doInCompass(CompassSession compass) throws CompassException {
		List<BlogArticle> articles = new ArrayList<BlogArticle>();
		
		
		//CompassQueryBuilder queryBuilder = compass.queryBuilder();
		//CompassBooleanQueryBuilder boolQuery = queryBuilder.bool();
		//boolQuery.addMust(queryBuilder.queryString("id:9").toQuery());
		//boolQuery.addShould(queryBuilder.queryString(key).toQuery());
		
		
		/*
		CompassHits hits = session.find(key);
		int lastIndex = startIndex + maxResult - 1;
		if(lastIndex>(hits.length()-1)) lastIndex = hits.length()-1;
		for(int i=startIndex; i<=lastIndex; i++){
			ProductInfo product = (ProductInfo) hits.data(i);
			if(hits.highlighter(i).fragment("productName")!=null){//处理高亮显示
				product.setName(hits.highlighter(i).fragment("productName"));
			}
			products.add(product);
		}
		QueryResult<ProductInfo> qr = new QueryResult<ProductInfo>();
		qr.setResultlist(products);
		qr.setTotalrecord(hits.length());//设置查询到的总记录数
		return qr;
		*/
		
		
		
		//String queryStr = boolQuery.toQuery().toString();
		//System.out.println(queryBuilder.queryString("id:9").toQuery().toString());
		//String test = "+id:9 '学习'";
		CompassHits hits = compass.find(key);//compass.find(queryBuilder.queryString("id:9").toQuery().toString());//compass.find(key);
		int lastIndex = startIndex + maxResult - 1;
		if(lastIndex > (hits.length()-1)){
			lastIndex = hits.length()-1;
		}
		for(int i = startIndex; i <= lastIndex; i++){
			BlogArticle article = (BlogArticle) hits.data(i);
			//System.out.println(article.getId());
			String hlArticleTitle = hits.highlighter(i).fragment("articleTitle");
			String hlArticleContent = hits.highlighter(i).fragment("articleContent");
			if(hlArticleTitle != null){
				article.setArticleTitle(hlArticleTitle);
			}
			if(hlArticleContent != null){
				article.setArticleContent(hlArticleContent);
			}
			//不管是否在内容索引到项，最后去掉html标记，截取部分内容
			article.setArticleContent(HtmlUtils.splitAndFilterString(article.getArticleContent(),250));
			
			articles.add(article);
		}
		QueryResult<BlogArticle> qr = new QueryResult<BlogArticle>();
		qr.setResultlist(articles);
		qr.setTotalrecord(hits.length());//设置查询到的总记录数
		return qr;
	}
	
	public BlogArticleCallback(String key, int startIndex, int maxResult){
		this.key = key;
		this.startIndex = startIndex;
		this.maxResult = maxResult;
	}
}