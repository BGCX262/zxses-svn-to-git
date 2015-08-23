package com.lcweb.bean.pojo;

/**
 * VoteUserId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VoteUserId implements java.io.Serializable {

	// Fields

	private Long voteId;
	private Long userId;

	// Constructors

	/** default constructor */
	public VoteUserId() {
	}

	/** full constructor */
	public VoteUserId(Long voteId, Long userId) {
		this.voteId = voteId;
		this.userId = userId;
	}

	// Property accessors

	public Long getVoteId() {
		return this.voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VoteUserId))
			return false;
		VoteUserId castOther = (VoteUserId) other;

		return ((this.getVoteId() == castOther.getVoteId()) || (this.getVoteId() != null && castOther.getVoteId() != null && this
				.getVoteId().equals(castOther.getVoteId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getVoteId() == null ? 0 : this.getVoteId().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}