package com.agilefly.web.action.index;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.service.blogarticle.IBlogArticleReIndexService;
import com.agilefly.service.blogarticle.IBlogArticleSearchService;
import com.agilefly.web.form.BlogArticleQueryForm;

@Controller("/client/index/reindex")
public class BlogArticleIndexAction extends Action {
	@Resource
	IBlogArticleReIndexService blogArticleReIndexService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("重建索引开始.....");
		blogArticleReIndexService.reIndex();
		System.out.println("重建索引结束.....");
		return null;
	}
}
