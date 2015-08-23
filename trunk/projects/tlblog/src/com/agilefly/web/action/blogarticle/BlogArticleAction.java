package com.agilefly.web.action.blogarticle;

import java.util.Date;
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
import com.agilefly.service.blogarticle.IBlogArticleService;
import com.agilefly.service.systemuser.ISysUserService;
import com.agilefly.service.systype.ISysTypeService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.SysObj;
import com.agilefly.web.action.BaseAction;
import com.agilefly.web.form.BlogArticleForm;

/**
 * @author boleyn_renlei
 * @date Jun 3, 2012 6:58:32 PM
 */

@Controller("/blog/article")
public class BlogArticleAction extends BaseAction {
	@Resource
	private IBlogArticleService blogArticleService;
	@Resource
	private ISysTypeService sysTypeService;
	@Resource
	private ISysUserService sysUserService;
	
	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public void setBlogArticleService(IBlogArticleService blogArticleService) {
		this.blogArticleService = blogArticleService;
	}
	
	public void setSysTypeService(ISysTypeService sysTypeService) {
		this.sysTypeService = sysTypeService;
	}

	/**
	 * 打开博客文章列表界面 默认不指定mothod方法名 示例：blog/blogarticle.do
	 * 默认列表显示方法调用 不可修改 或者新建一个单独的列表显示action继承Action
	 */
	//@Permission(model="sysModuleManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取博客文章列表，并传递到界面
		BlogArticleForm baf = (BlogArticleForm)form;
		
		request.setAttribute("qs", blogArticleService.getScrollDataByThread());
		
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
		SysUser frontUser = (SysUser)request.getSession().getAttribute("frontUserInfo");
		if(frontUser == null){
			return mapping.findForward("clientLogin");
		}
		//根据登录用户类型加载文章类型下拉框值
		List<SysType> sysBlogTypeList = sysTypeService.searchSysBlogTypes(frontUser.getUserType());
		request.setAttribute("stuArticleTypes", sysBlogTypeList);
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
		SysUser frontUser = (SysUser)request.getSession().getAttribute("frontUserInfo");
		
		if(frontUser == null){
			return mapping.findForward("clientLogin");
		}
		
		BlogArticleForm baf = (BlogArticleForm)form;
		BlogArticle article = new BlogArticle();
		
		BeanUtilEx.copyProperties(article, baf);
		if(article.getCommentStatus() == null){
			article.setCommentStatus((byte)0);
		}
		SysType articleType = sysTypeService.find(baf.getArticleTypeId());
		article.setSysType(articleType);
		article.setUserId(frontUser.getId());
		article.setPostTime(new Date());
		//获取编辑器内容
		//article.setArticleContent(request.getParameter("editor_k"));
		
		blogArticleService.save(article);
		
		//request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		//获取用户博客信息
		//SysUserForm suf = (SysUserForm)form;
		request.setAttribute("blogUser", frontUser);
		
		return new ActionForward("",true);//mapping.findForward("blogindex");
		//return unspecified(mapping, form, request,response);
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
		BlogArticle article = blogArticleService.find(Integer.parseInt(blogArticleId));
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
		
		article = blogArticleService.find(baf.getId());
		
		String messageEntity = "";//article.getName();
		
		BeanUtilEx.copyProperties(article, baf);
		
		blogArticleService.update(article);
		
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
		blogArticleService.delete(ids);
		//return mapping.findForward("pub_del_success");
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return unspecified(mapping, form, request,response);
	}
}
