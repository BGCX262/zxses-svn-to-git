package com.agilefly.web.action.index;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.service.blogarticle.IBlogArticleSearchService;
import com.agilefly.web.form.BlogArticleQueryForm;

@Controller("/client/index/search")
public class BlogArticleSearchAction extends Action {
	@Resource
	IBlogArticleSearchService blogArticleSearchService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BlogArticleQueryForm formbean = (BlogArticleQueryForm)form;
		
		request.setAttribute("qs", blogArticleSearchService.searchByThread(formbean.getWord()));
		
		return mapping.findForward("list");
	}
}
