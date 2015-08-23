package com.agilefly.service.blogarticle.impl;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agilefly.bean.BlogArticle;
import com.agilefly.bean.SysType;
import com.agilefly.commons.QueryResult;
import com.agilefly.commons.SysConstant;
import com.agilefly.service.blogarticle.IBlogArticleSearchService;
import com.agilefly.service.systype.ISysTypeService;

public class BlogArticleSearchServiceTest {
	private static ApplicationContext cxt;
	private static IBlogArticleSearchService blogArticleSearchService;
	private static ISysTypeService sysTypeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("application*.xml");
			blogArticleSearchService = (IBlogArticleSearchService)cxt.getBean("blogArticleSearchService");
			sysTypeService = (ISysTypeService)cxt.getBean("sysTypeService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearch(){
		QueryResult<BlogArticle> articles = blogArticleSearchService.search("人民", 0, 5);
		for (BlogArticle article : articles.getResultlist()) {
			System.out.println(article.getArticleTitle());
			System.out.println(article.getArticleContent());
			System.out.println("****************************");
		}
	}
	
	@Test
	public void testAddSystype(){
		//益智谜语
		SysType yizhiMiyu = new SysType();
		yizhiMiyu.setTypeSymbol(SysConstant.ARTICLE_TYPE);
		yizhiMiyu.setTypeName("益智谜语");
		yizhiMiyu.setTypeDes("教师益智谜语博客文章类型");
		yizhiMiyu.setTypeCode("yizhiMiyu");
		yizhiMiyu.setExtFirst("teacher");
		yizhiMiyu.setTypeOrder(6);
		yizhiMiyu.setAvailable((byte)1);
		sysTypeService.save(yizhiMiyu);
	}
}
