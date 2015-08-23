package com.lcweb.struts.action;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.PageList;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemConfig;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.commons.GlobalConst;
import com.lcweb.commons.SystemConst;
import com.lcweb.service.newsclient.NewsClientService;

/**
 * 
 * @Title: NewsClientAction
 * @Description:
 * @Author: feng
 * @Time: Apr 22, 2011
 */
public class NewsClientAction extends DispatchAction {

	private NewsClientService newsClientService;
	private static String PAGESIZE = "pagesize";
	private static String CURRENTPAGE = "currentPage";

	/**
	 * @return the pAGESIZE
	 */
	public static String getPAGESIZE() {
		return PAGESIZE;
	}

	/**
	 * @param pagesize
	 *            the pAGESIZE to set
	 */
	public static void setPAGESIZE(String pagesize) {
		PAGESIZE = pagesize;
	}

	/**
	 * @return the cURRENTPAGE
	 */
	public static String getCURRENTPAGE() {
		return CURRENTPAGE;
	}

	/**
	 * @param currentpage
	 *            the cURRENTPAGE to set
	 */
	public static void setCURRENTPAGE(String currentpage) {
		CURRENTPAGE = currentpage;
	}

	public NewsClientService getNewsClientService() {
		return newsClientService;
	}

	public void setNewsClientService(NewsClientService newsClientService) {
		this.newsClientService = newsClientService;
	}

	/**
	 * 
	 * @Description: 首页底部
	 * @Author: feng
	 * 
	 */
	public ActionForward queryFootInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new ActionForward("/client/index/footinfo.jsp");
	}

	/**
	 * 
	 * @Description: 顶部菜单
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<NewsItemBig> newsitemBigs = newsClientService.queryAllNewsItemBigByYxdm(GlobalConst.SchoolCode,
				GlobalConst.IS_DISPLAY, GlobalConst.IS_INDEX);
		if (newsitemBigs.size() > 0) {
			request.setAttribute("newsItemBigs", newsitemBigs);
			return new ActionForward("/client/index/menu.jsp");
		}
		return null;
	}

	/**
	 * 
	 * @Description: 首页轮播图片
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryRollImage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String classId = SystemConst.TL_PHOTOLINK;
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId);
		String typeId = SystemConst.TL_PHOTOLINK_ROLLPIC;
		if (typeId != null && itemBig != null) {
			NewsItemSmall itemSmall = newsClientService.getNewsItemSmallByTypeId(Integer.parseInt(typeId));
			Set configSet = itemSmall.getNewsItemConfigs();
			NewsItemConfig newsItemConfig = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList = newsClientService.queryNewsContentManagerByTypeIdForPage(
					itemSmall.getTypeId(), 0, newsItemConfig.getDisplayRowCount());
			if (contentManageList.size() > 0) {
				request.setAttribute("itemBig", itemBig);
				request.setAttribute("contentManageList", contentManageList);
				return new ActionForward("/client/index/content/rollimage.jsp");
			}
		}
		return null;
	}
	/**
	 * 
	 * @Description: 首页图片轮播
	 * @Author: feng
	 * 
	 */
	public ActionForward queryIndexBanner(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new ActionForward("/client/index/content/indexbanner.jsp");
	}

	/**
	 * 
	 * @Description: queryIndexContact联系我们
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryIndexContact(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String classId = SystemConst.TL_WORDLINK;
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId);
		List<NewsItemSmall> newsItemList = newsClientService.queryNewsItemSmallByClassId(classId,
				GlobalConst.IS_DISPLAY);
		if (itemBig != null && newsItemList.size() > 0) {
				request.setAttribute("itemBig", itemBig);
				request.setAttribute("newsItemList", newsItemList);
				return new ActionForward("/client/index/content/contact.jsp");
		}
		return null;
	}
	/**
	 * 
	 * @Description: 联系我们
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryContact(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String classId = SystemConst.TL_CONTACT;
		request.setAttribute("classId", classId);
		return new ActionForward("/client/index/content/contact_second.jsp");
	}
	
	/**
	 * 
	 * @Description: 首页登陆静态化处理
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryLoginForStatic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new ActionForward("/client/index/content/loginForStatic.jsp");
	}
	
	/**
	 * 
	 * @Description: 首页登陆
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new ActionForward("/client/index/content/login.jsp");
	}

	/**
	 * 
	 * @Description: 首页tabs
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryTabs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String wordsId = SystemConst.TL_ALL;
		String words_typeId = SystemConst.TL_ALL_WORDS;
		String newsId = SystemConst.TL_NEWS;
		String noticeId = SystemConst.TL_NOTICE;

		NewsItemBig itemBig_words = newsClientService.getNewsItemBigByClassId(wordsId);
		if (words_typeId != null && itemBig_words != null) {
			NewsItemSmall itemSmall_words = newsClientService.getNewsItemSmallByTypeId(Integer.parseInt(words_typeId));
			Set configSet = itemSmall_words.getNewsItemConfigs();
			NewsItemConfig newsItemConfig_words = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList_words = newsClientService.queryNewsContentManagerByTypeIdForPage(
					itemSmall_words.getTypeId(), 0, newsItemConfig_words.getDisplayRowCount());
			if (contentManageList_words.size() > 0) {
				request.setAttribute("itemBig_words", itemBig_words);
				request.setAttribute("itemSmall_words", itemSmall_words);
				request.setAttribute("newsItemConfig_words", newsItemConfig_words);
				request.setAttribute("contentManageList_words", contentManageList_words);
			}
		}

		NewsItemBig itemBig_news = newsClientService.getNewsItemBigByClassId(newsId);
		List<NewsItemSmall> newsItemList_news = newsClientService.queryNewsItemSmallByClassId(newsId,
				GlobalConst.IS_DISPLAY);
		if (itemBig_news != null && newsItemList_news.size() > 0) {
			NewsItemSmall itemSmall_news = newsItemList_news.get(0);
			Set configSet = itemSmall_news.getNewsItemConfigs();
			NewsItemConfig newsItemConfig_news = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList_news = newsClientService.queryNewsContentManagerByTypeIdForPage(
					itemSmall_news.getTypeId(), 0, newsItemConfig_news.getDisplayRowCount());
			if (contentManageList_news.size() > 0) {
				request.setAttribute("itemBig_news", itemBig_news);
				request.setAttribute("itemSmall_news", itemSmall_news);
				request.setAttribute("newsItemConfig_news", newsItemConfig_news);
				request.setAttribute("contentManageList_news", contentManageList_news);
			}

		}

		NewsItemBig itemBig_notice = newsClientService.getNewsItemBigByClassId(noticeId);
		List<NewsItemSmall> newsItemList_notice = newsClientService.queryNewsItemSmallByClassId(noticeId,
				GlobalConst.IS_DISPLAY);
		if (itemBig_notice != null && newsItemList_notice.size() > 0) {
			NewsItemSmall itemSmall_notice = newsItemList_notice.get(0);
			Set configSet = itemSmall_notice.getNewsItemConfigs();
			NewsItemConfig newsItemConfig_notice = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList_notice = newsClientService
					.queryNewsContentManagerByTypeIdForPage(itemSmall_notice.getTypeId(), 0, newsItemConfig_notice
							.getDisplayRowCount());
			if (contentManageList_notice.size() > 0) {
				request.setAttribute("itemBig_notice", itemBig_notice);
				request.setAttribute("itemSmall_notice", itemSmall_notice);
				request.setAttribute("newsItemConfig_notice", newsItemConfig_notice);
				request.setAttribute("contentManageList_notice", contentManageList_notice);
			}
		}
		return new ActionForward("/client/index/content/tabs.jsp");
	}

	/**
	 * 
	 * @Description: 首页tabspic
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryTabspic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String classId = SystemConst.TL_MORAL;
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId);
		List<NewsItemSmall> newsItemList = newsClientService.queryNewsItemSmallByClassId(classId,
				GlobalConst.IS_DISPLAY);
		if (itemBig != null && newsItemList.size() > 0) {
			request.setAttribute("itemBig", itemBig);
			request.setAttribute("newsItemList", newsItemList);
			return new ActionForward("/client/index/content/tabspic.jsp");
		}
		return null;
	}

	/**
	 * 
	 * @Description: 首页tabspic_content
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryTabspicContent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String typeId = request.getParameter("typeId");
		if (typeId != null && !"".equals(typeId)) {
			NewsItemSmall itemSmall = newsClientService.getNewsItemSmallByTypeId(Integer.parseInt(typeId));
			Set configSet = itemSmall.getNewsItemConfigs();
			NewsItemConfig newsItemConfig = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList = newsClientService.queryNewsContentManagerByTypeIdForPage(
					itemSmall.getTypeId(), 0, newsItemConfig.getDisplayRowCount());
			if (contentManageList.size() > 0) {
				request.setAttribute("newsItemConfig", newsItemConfig);
				request.setAttribute("contentManageList", contentManageList);
				return new ActionForward("/client/index/content/tabspic_content.jsp");
			}
		}
		return null;
	}

	/**
	 * 
	 * @Description: 首页FriendLink
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryFriendLink(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String classId = SystemConst.TL_LINK;
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId);
		List<NewsItemSmall> newsItemList = newsClientService.queryNewsItemSmallByClassId(classId,
				GlobalConst.IS_DISPLAY);
		if (itemBig != null && newsItemList.size() > 0) {
			NewsItemSmall itemSmall = newsItemList.get(0);
			Set configSet = itemSmall.getNewsItemConfigs();
			NewsItemConfig newsItemConfig = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList = newsClientService.queryNewsContentManagerByTypeIdForPage(
					itemSmall.getTypeId(), 0, newsItemConfig.getDisplayRowCount());
			if (contentManageList.size() > 0) {
				request.setAttribute("itemBig", itemBig);
				request.setAttribute("newsItemConfig", newsItemConfig);
				request.setAttribute("contentManageList", contentManageList);
				return new ActionForward("/client/index/content/friendlink.jsp");
			}
		}
		return null;
	}

	/**
	 * 
	 * @Description: 首页SkyStars
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward querySkyStars(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new ActionForward("/client/index/content/skystars.jsp");
	}

	/**
	 * 
	 * @Description: 首页headmasters
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryHeadmasters(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String classId = SystemConst.TL_PHOTOLINK;
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId);
		String typeId_xz = SystemConst.TL_PHOTOLINK_XZ;
		String typeId_fxz = SystemConst.TL_PHOTOLINK_FXZ;
		if (itemBig != null) {
			request.setAttribute("itemBig", itemBig);
			if (typeId_xz != null && typeId_fxz != null) {
				List<NewsContentManage> content_xz = newsClientService.queryNewsContentManagerByTypeIdForPage(
						Integer.parseInt(typeId_xz), 0, 1);
				List<NewsContentManage> content_fxz = newsClientService.queryNewsContentManagerByTypeIdForPage(
						Integer.parseInt(typeId_fxz), 0, 1);
				if (content_xz.size() > 0 && content_fxz.size() > 0 ) {
					request.setAttribute("content_xz", content_xz);
					request.setAttribute("content_fxz", content_fxz);
					request.setAttribute("typeId_xz", typeId_xz);
					request.setAttribute("typeId_fxz", typeId_fxz);
					return new ActionForward("/client/index/content/headmasters.jsp");
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Description: 首页资源库
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryLib(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String classId = SystemConst.TL_LIBRARY;
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId);
		List<NewsItemSmall> newsItemList = newsClientService.queryNewsItemSmallByClassId(classId,
				GlobalConst.IS_DISPLAY);
		if (itemBig != null && newsItemList.size() > 0) {
			NewsItemSmall itemSmall = newsItemList.get(0);
			Set configSet = itemSmall.getNewsItemConfigs();
			NewsItemConfig newsItemConfig = (NewsItemConfig) configSet.toArray()[0];
			List<NewsContentManage> contentManageList = newsClientService.queryNewsContentManagerByTypeIdForPage(
					itemSmall.getTypeId(), 0, newsItemConfig.getDisplayRowCount());
			if (contentManageList.size() > 0) {
				request.setAttribute("itemBig", itemBig);
				request.setAttribute("newsItemConfig", newsItemConfig);
				request.setAttribute("contentManageList", contentManageList);
				return new ActionForward("/client/index/content/library.jsp");
			}
		}
		return null;
	}

	// ---------------------second-----------------------------------
	/**
	 * 
	 * @Description: 左列
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryLeft(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String classId = request.getParameter("classId");
		NewsItemBig itemBig = newsClientService.getNewsItemBigByClassId(classId, GlobalConst.IS_DISPLAY);
		if ((itemBig != null) || ("".equals(itemBig))) {
			request.setAttribute("itemBig", itemBig);
			return new ActionForward("/client/second/left.jsp");
		}
		return null;
	}

	/**
	 * 
	 * @Description: 新闻浏览次数
	 * @Author:
	 * 
	 */
	public ActionForward newsVisitCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		NewsContentManage newsContent = (NewsContentManage) newsClientService.queryObjectByLongId(
				new NewsContentManage(), id);
		if ((newsContent != null) && (newsContent.getVisitCount() != null)) {
			newsContent.setVisitCount(newsContent.getVisitCount() + 1);
			newsClientService.updateObject(newsContent);
			response.getWriter().write(newsContent.getVisitCount().toString());
		}
		return null;
	}

	/**
	 * 
	 * @Description: new新闻标记
	 * @Author: feng
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<?> newsContentToVo(List contentManageList, String newFlagTime) {
		Calendar calendar = Calendar.getInstance(); // 当前日期
		int day = calendar.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(newFlagTime);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		calendar.set(year, month, day, hourOfDay, minute, second);
		if (contentManageList.size() > 0) {
			for (Iterator<?> iterator = contentManageList.iterator(); iterator.hasNext();) {
				NewsContentManage newsContent = (NewsContentManage) iterator.next();
				newsContent.setIsNew(String.valueOf(calendar.getTime().before(newsContent.getNewsDate())));
			}
		}
		return contentManageList;
	}

	/*
	 * getSecondNewsContentForPage 导航页面的详细跳转
	 */
	@SuppressWarnings("unchecked")
	public ActionForward getSecondNewsContentForPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classId = request.getParameter("classId");
		String typeId = request.getParameter("typeId");
		String currentPage = request.getParameter("currentPage");
		if (currentPage == null) {
			currentPage = "1";
		}
		NewsItemBig itemBig = new NewsItemBig();
		NewsItemSmall itemSmall = new NewsItemSmall();
		if ((classId != null) && (classId.trim().length() > 0)) {
			itemBig = newsClientService.getNewsItemBigByClassId(classId);
		}
		if ((typeId != null) && (typeId.trim().length() > 0)) {
			itemSmall = newsClientService.getNewsItemSmallByTypeId(Integer.parseInt(typeId));
		}
		if ((typeId == null) || "".equals(typeId)) {
			List<NewsItemSmall> newsItemList = newsClientService.queryNewsItemSmallByClassId(classId,
					GlobalConst.IS_DISPLAY);
			if (newsItemList.size() > 0) {
				itemSmall = newsItemList.get(0);
				typeId = itemSmall.getTypeId().toString();
			}
		}
		Set configSet = itemSmall.getNewsItemConfigs();
		NewsItemConfig newsItemConfig = (NewsItemConfig) configSet.toArray()[0];
		request.setAttribute("typeId", typeId);
		request.setAttribute("classId", classId);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("itemBig", itemBig);
		request.setAttribute("itemSmall", itemSmall);
		request.setAttribute("newsItemConfig", newsItemConfig);
		PageList pageList = getMoreNewsContentsForPage(request, response, Integer.valueOf(typeId));
		if (pageList.getList().size() > 0) {
			request.setAttribute("newsContentList", pageList);
			if ("2".equals(itemSmall.getAnnounceType())) {
				return new ActionForward("/client/second/secondcontent.jsp");
			}
			return new ActionForward("/client/second/secondlist.jsp");
		}
		return new ActionForward("/client/second/secondnone.jsp");

	}

	/**
	 * 
	 * @Description: getMoreNewsContentsForPage
	 * @Author: feng
	 * 
	 */
	private PageList getMoreNewsContentsForPage(HttpServletRequest request, HttpServletResponse response, Integer typeId)
			throws Exception {
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		String classId = request.getParameter("classId");
		String sql = null;
		String sqlCount = null;
		String path = null;
		if (typeId == null) {
			sql = "select ncm from NewsContentManage ncm join ncm.newsItemSmalls s where s.newsItemBig.classId= '"
					+ classId + "' and ncm.checkFlag='1' order by ncm.ifTopRow desc,ncm.newsDate desc";
			sqlCount = "select count(ncm.newsId) from NewsContentManage ncm join ncm.newsItemSmalls s where s.newsItemBig.classId= '"
					+ classId + "' and ncm.checkFlag='1'";
		} else {
			sql = "select ncm from NewsContentManage ncm join ncm.newsItemSmalls s where s.typeId =" + typeId
					+ " and ncm.checkFlag='1' order by ncm.ifTopRow desc,ncm.newsDate desc";
			sqlCount = "select count(ncm.newsId) from NewsContentManage ncm join ncm.newsItemSmalls s where s.typeId ="
					+ typeId + " and ncm.checkFlag='1'";
		}
		path = request.getContextPath() + "/client/newsClient.do?method=getSecondNewsContentForPage&typeId=" + typeId
				+ "&classId=" + classId;

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsClientService,
				"newsmanagerForm");
		return pageList;
	}

	/*
	 * getNewsContentManageByNewsId news.jsp页面未处理。。。。
	 */
	public ActionForward getNewsContentManageByNewsId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		long newsId = Long.parseLong(request.getParameter("newsId"));
		NewsContentManage newsContent = newsClientService.getNewsContentManageByNewsId(newsId);
		if (null == newsContent.getVisitCount()) {
			newsContent.setVisitCount(0);
		}
		newsContent.setNewsContent(newsContent.getNewsContent().replace("?", " "));
		newsContent.setVisitCount(newsContent.getVisitCount().intValue() + 1);
		request.setAttribute("newsContents", newsContent);
		return new ActionForward("/client/index/news.jsp");
	}
}