package com.agilefly.web.action.blog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.BlogArticle;
import com.agilefly.bean.SysType;
import com.agilefly.bean.SysUser;
import com.agilefly.bean.TypeArticle;
import com.agilefly.commons.QueryResult;
import com.agilefly.service.blogarticle.IBlogArticleService;
import com.agilefly.service.systemuser.ISysUserService;
import com.agilefly.service.systype.ISysTypeService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.SysObj;
import com.agilefly.web.action.BaseAction;
import com.agilefly.web.form.BlogArticleForm;

/**
 * @author boleyn_renlei
 * @date Jun 14, 2012 5:08:05 PM
 */

@Controller("/client/blog")
public class BlogAction extends BaseAction {
	@Resource
	private ISysUserService sysUserService;
	@Resource
	private IBlogArticleService blogArticleService;
	@Resource
	private ISysTypeService sysTypeService;
	
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	
	public void setBlogArticleService(IBlogArticleService blogArticleService) {
		this.blogArticleService = blogArticleService;
	}

	/**
	 * 搜索用户博客主页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取用户博客信息
		//SysUserForm suf = (SysUserForm)form;
		String username = request.getParameter("currentUserName");
		
		SysUser blogUser = sysUserService.findByUname(username);
		
		request.setAttribute("blogUser", blogUser);
		//获取用户的文章--如果博客用户为当前登录用户查询博客用户隐藏的文章
		SysUser frontUser = (SysUser)request.getSession().getAttribute("frontUserInfo");
		
		if(frontUser != null){
			if(username.equals(frontUser.getUsername())){
				//查询所有文章--按照发表时间
				String whereHql = "o.userId=? ";
				Object[] params = new Object[]{blogUser.getId()};
				LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
				orderby.put("postTime", "desc");
				QueryResult<BlogArticle> blogArticleQs = blogArticleService.getScrollDataByThread(whereHql,params,orderby);
				request.setAttribute("qs", blogArticleQs);
			}
		}else{
			//查询所有公开文章--按照发表时间
			String whereHql = "o.userId=? and o.publicStatus=? ";
			Object[] params = new Object[]{blogUser.getId(),(byte)1};
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("postTime", "desc");
			QueryResult<BlogArticle> blogArticleQs = blogArticleService.getScrollDataByThread(whereHql,params,orderby);
			request.setAttribute("qs", blogArticleQs);
		}
		//获得文章类型
		//根据博客用户类型获得文章类型
		List<SysType> userBlogTypeList = new ArrayList<SysType>();
		
		userBlogTypeList = sysTypeService.searchSysBlogTypes(blogUser.getUserType());
		
		request.setAttribute("userBlogTypeList", userBlogTypeList);
		return mapping.findForward("blogindex");
	}
	
	/**
	 * 打开博客文章列表界面 默认不指定mothod方法名 示例：blog/blogarticle.do
	 * 默认列表显示方法调用 不可修改 或者新建一个单独的列表显示action继承Action
	 */
	//@Permission(model="sysModuleManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取博客文章列表，并传递到界面
		BlogArticleForm baf = (BlogArticleForm)form;
		
		request.setAttribute("qs", sysUserService.getScrollDataByThread());
		
		return mapping.findForward("list");
	}
	
	/**
	 * 打开发表文章录入界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@Permission(model="sysModuleManage", privilegeValue="add")
	public ActionForward addInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add_input");
	}
	
	/**
	 * 发表文章
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@Permission(model="sysModuleManage", privilegeValue="add")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BlogArticleForm baf = (BlogArticleForm)form;
		BlogArticle article = new BlogArticle();
		
		BeanUtilEx.copyProperties(article, baf);
		//获取编辑器内容
		article.setArticleContent(request.getParameter("editor_k"));
		
		//sysUserService.save(article);
		
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 打开博客文章修改界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@Permission(model="sysModuleManage", privilegeValue="update")
	public ActionForward updateInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String blogArticleId = request.getParameter("blogArticleId");
		BlogArticle article = null;//sysUserService.find(blogArticleId);
		request.setAttribute("blogArticleInfo", article);
		return mapping.findForward("update_input");
	}
	
	/**
	 * 修改博客文章
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@Permission(model="sysModuleManage", privilegeValue="update")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BlogArticleForm baf = (BlogArticleForm)form;
		
		BlogArticle article = null;
		
		article = null;//sysUserService.find(baf.getId());
		
		String messageEntity = "";//article.getName();
		
		BeanUtilEx.copyProperties(article, baf);
		
		//sysUserService.update(article);
		
		request.setAttribute("showMsg", SysObj.createEditMassageBox(messageEntity));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 删除博客文章
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//@Permission(model="sysModuleManage", privilegeValue="delete")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		sysUserService.delete(ids);
		//return mapping.findForward("pub_del_success");
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return unspecified(mapping, form, request,response);
	}
}
