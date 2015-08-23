package com.lcweb.bean.pojo;

/**
 * VoteItems entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VoteItems implements java.io.Serializable {

	// Fields

	private Long itemId;
	private VoteTitle voteTitle;
	private String itemName;
	private Integer itemBallot;

	// Constructors

	/** default constructor */
	public VoteItems() {
	}

	/** minimal constructor */
	public VoteItems(VoteTitle voteTitle) {
		this.voteTitle = voteTitle;
	}

	/** full constructor */
	public VoteItems(VoteTitle voteTitle, String itemName, Integer itemBallot) {
		this.voteTitle = voteTitle;
		this.itemName = itemName;
		this.itemBallot = itemBallot;
	}

	// Property accessors

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public VoteTitle getVoteTitle() {
		return this.voteTitle;
	}

	public void setVoteTitle(VoteTitle voteTitle) {
		this.voteTitle = voteTitle;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemBallot() {
		return this.itemBallot;
	}

	public void setItemBallot(Integer itemBallot) {
		this.itemBallot = itemBallot;
	}

}