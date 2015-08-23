package com.lcweb.bean.pojo;

/**
 * VoteUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VoteUser implements java.io.Serializable {

	// Fields

	private VoteUserId id;

	// Constructors

	/** default constructor */
	public VoteUser() {
	}

	/** full constructor */
	public VoteUser(VoteUserId id) {
		this.id = id;
	}

	// Property accessors

	public VoteUserId getId() {
		return this.id;
	}

	public void setId(VoteUserId id) {
		this.id = id;
	}

}