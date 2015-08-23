package com.lcweb.struts.form;

import org.apache.struts.action.ActionForm;

public class MessageForm extends ActionForm {
	private Long messageId;
	private Long frontUserId;
	private String messageContent;
	private String facePic;
	private String headPic;
	
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getFrontUserId() {
		return frontUserId;
	}
	public void setFrontUserId(Long frontUserId) {
		this.frontUserId = frontUserId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getFacePic() {
		return facePic;
	}
	public void setFacePic(String facePic) {
		this.facePic = facePic;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
}
