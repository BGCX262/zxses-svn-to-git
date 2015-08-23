package com.lcweb.struts.form;

import org.apache.struts.action.ActionForm;

public class VoteForm extends ActionForm{
	private Long voteId;
	private String voteName;
	private Byte voteType;
	
	private String[] itemName;

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public Byte getVoteType() {
		return voteType;
	}

	public void setVoteType(Byte voteType) {
		this.voteType = voteType;
	}

	public String[] getItemName() {
		return itemName;
	}

	public void setItemName(String[] itemName) {
		this.itemName = itemName;
	}

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
}
