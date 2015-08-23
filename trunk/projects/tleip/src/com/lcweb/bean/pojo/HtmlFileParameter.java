package com.lcweb.bean.pojo;

import java.util.Map;


public class HtmlFileParameter {
	private NewsContentManage newsContent;
	private String outputPath;
	private String templateFilePath;
	private String basePath;
	private Map<String,Object> action;

	public HtmlFileParameter(NewsContentManage newsContent,
			String outputPath, String templateFilePath, String basePath,
			Map<String,Object> action) {
		this.newsContent = newsContent;
		this.outputPath = outputPath;
		this.templateFilePath = templateFilePath;
		this.basePath = basePath;
		this.action = action;
	}
	public HtmlFileParameter(){}

	public NewsContentManage getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(NewsContentManage newsContent) {
		this.newsContent = newsContent;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Map<String,Object> getAction() {
		return action;
	}

	public void setAction(Map<String,Object> action) {
		this.action = action;
	}

	public String getTemplateFilePath() {
		return templateFilePath;
	}

	public void setTemplateFilePath(String templateFilePath) {
		this.templateFilePath = templateFilePath;
	}
}