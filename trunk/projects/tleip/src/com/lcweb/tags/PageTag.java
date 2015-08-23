package com.lcweb.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.lcweb.base.util.PageList;

public class PageTag extends BodyTagSupport {

	private static final long serialVersionUID = -2567663035417428747L;

	PageList page = null;

	String url = null;

	private int tableSize = 1000;

	int pageSize = 10;

	public final static int FIRST_CURRENTPAGE = 1;
	public final static int LEFTANDRIGHT_SHOW = 3;
	public final static int ONCE_SHOW = 5;

	public void setBodyContent(BodyContent bc) {
		super.setBodyContent(bc);
	}

	public int doAfterBody() {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			paginationTagBody(request);
			bodyContent.clear();
		} catch (IOException e) {
			System.out.println("Error in BodyContentTag.doAfterBody()"
					+ e.getMessage());
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	private void paginationTagBody(HttpServletRequest request)
			throws IOException {
		BodyContent bodyContent = super.getBodyContent();
		JspWriter out = bodyContent.getEnclosingWriter();
		if (null != this.getPage()) {
			Integer currentPageInt = Integer.valueOf(this.getPage()
					.getCurrentPage()); // currentPage
			Integer totalPagesInt = Integer.valueOf(this.getPage()
					.getPageCount()); // totalPages
			String action = getPage().getPath();
			out.print("<div id='pageMenu' with=" + tableSize + ">");
			out.print("<ul>");
			out.print("<li>");
			out.print("<span>当前页数" + "<font color='red'>"
					+ this.getPage().getCurrentPage() + "</font>");
			out.print("/" + "<font color='#000000'>"
					+ this.getPage().getPageCount() + "</font> </span>");
			out.print("<span align='center'>");
			if (totalPagesInt == FIRST_CURRENTPAGE) {
				out.print("<strong>1</strong>");
			} else {
				if (currentPageInt > FIRST_CURRENTPAGE) {
					out.print("<a href='" + action + "&currentPage=1&pageSize="
							+ pageSize + "'>首页</a> ");
					out.print("<a href='" + action + "&currentPage="
							+ (currentPageInt - FIRST_CURRENTPAGE)
							+ "&pageSize=" + pageSize + "'> 上一页</a> ");
				}
				int registerCurrentPage = (currentPageInt - LEFTANDRIGHT_SHOW) > 0 ? (currentPageInt - LEFTANDRIGHT_SHOW)
						: FIRST_CURRENTPAGE;
				int opinionTotalPages = (currentPageInt + LEFTANDRIGHT_SHOW) < totalPagesInt ? (currentPageInt + LEFTANDRIGHT_SHOW)
						: totalPagesInt;
				if (opinionTotalPages == totalPagesInt) {
					registerCurrentPage = totalPagesInt - ONCE_SHOW;
					registerCurrentPage = registerCurrentPage++;
				} else if (registerCurrentPage == FIRST_CURRENTPAGE
						&& opinionTotalPages < totalPagesInt) {
					opinionTotalPages = totalPagesInt > ONCE_SHOW ? ONCE_SHOW
							: totalPagesInt;
				}
				for (int i = registerCurrentPage; i <= opinionTotalPages; i++) {
					if (currentPageInt == i) {
						out.print("<strong>" + i + "</strong>");
					} else {
						if (i > 0) {
							out.print("<a  href='" + action + "&currentPage="
									+ (i) + "&pageSize=" + pageSize + "'>" + i
									+ "</a>");
						}
					}
				}
				if (currentPageInt < totalPagesInt) {
					out.print("<a href='" + action + "&currentPage="
							+ (currentPageInt + FIRST_CURRENTPAGE)
							+ "&pageSize=" + pageSize + "'>下一页</a>");

					out.print("<a href='" + action + "&currentPage="
							+ totalPagesInt + "&pageSize=" + pageSize
							+ "'>最后一页</a>");
				}
			}
			out.print("</span>");
			out.print("</li>");
			out.print("</ul>");
			out.print("</div>");
		}
	}

	public PageList getPage() {
		return page;
	}

	public void setPage(PageList page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTableSize() {
		return tableSize;
	}

	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}