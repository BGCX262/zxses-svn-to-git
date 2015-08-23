package com.agilefly.web.form;

import org.apache.struts.action.ActionForm;

public class BlogArticleQueryForm extends ActionForm {
	private String word;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
