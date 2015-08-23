package com.lcweb.bean.pojo;

/**
 * FrontMessage entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FrontMessage implements java.io.Serializable {

	// Fields

	private Long messageId;
	private FrontUser frontUser;
	private String messageContent;
	private String messageDate;
	private String facePic;
	private String headPic;
	private String replyContent;
	private String replyDate;
	private Short approveStatus;
	private Short isTop;

	private String approveStatusStr;
	
	public String getApproveStatusStr() {
		if(approveStatus == 1){
			return "<font color='green'>已审批</font>";
		}else{
			return "<font color='red'>未审批</font>";
		}
	}
	
	// Constructors

	/** default constructor */
	public FrontMessage() {
	}

	/** minimal constructor */
	public FrontMessage(FrontUser frontUser) {
		this.frontUser = frontUser;
	}

	/** full constructor */
	public FrontMessage(FrontUser frontUser, String messageContent, String messageDate, String facePic, String headPic,
			String replyContent, String replyDate, Short approveStatus, Short isTop) {
		this.frontUser = frontUser;
		this.messageContent = messageContent;
		this.messageDate = messageDate;
		this.facePic = facePic;
		this.headPic = headPic;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.approveStatus = approveStatus;
		this.isTop = isTop;
	}

	// Property accessors

	public Long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public FrontUser getFrontUser() {
		return this.frontUser;
	}

	public void setFrontUser(FrontUser frontUser) {
		this.frontUser = frontUser;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageDate() {
		return this.messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	public String getFacePic() {
		return this.facePic;
	}

	public void setFacePic(String facePic) {
		this.facePic = facePic;
	}

	public String getHeadPic() {
		return this.headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyDate() {
		return this.replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public Short getApproveStatus() {
		return this.approveStatus;
	}

	public void setApproveStatus(Short approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Short getIsTop() {
		return isTop;
	}

	public void setIsTop(Short isTop) {
		this.isTop = isTop;
	}

}