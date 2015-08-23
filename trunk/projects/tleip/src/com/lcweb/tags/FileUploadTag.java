package com.lcweb.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FileUploadTag extends BodyTagSupport {

	private static final long serialVersionUID = 8693931331379180532L;

	private String action = null;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setBodyContent(BodyContent bc) {
		super.setBodyContent(bc);
	}

	public int doAfterBody()
	  {
	    try 
	    {    
	      BodyContent bodyContent = super.getBodyContent();
	      JspWriter   out         = bodyContent.getEnclosingWriter();
	      StringBuffer context = new StringBuffer();
	      context.append("<div class=\"demo\" style=\"display:none\">");
	      context.append("<form action="+action+" enctype=\"multipart/form-data\" method='post' id='uploadForm'>");
	      context.append("<div id=\"dialog\" title=\"文件上传\">");
	      context.append("<p>");
	      context.append("请选择你要上传的文件:");
	      context.append("</p>");
	      context.append("<input id=\"upload\" type=\"file\" size='50'/>");
	      context.append("</div>");
	      context.append("</form>");
	      context.append("</div>");
	      out.print(context);
	      bodyContent.clear(); // empty buffer for next evaluation
	    }
	    catch (IOException e) 
	    {
	      System.out.println("Error in BodyContentTag.doAfterBody()" + e.getMessage());
	      e.printStackTrace();
	    } 

	    return SKIP_BODY;
	  }
}
