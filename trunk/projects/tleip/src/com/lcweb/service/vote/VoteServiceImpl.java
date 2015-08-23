package com.lcweb.service.vote;

import java.util.List;

import com.lcweb.bean.pojo.FrontMessage;
import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.bean.pojo.VoteItems;
import com.lcweb.bean.pojo.VoteTitle;
import com.lcweb.service.base.BaseServiceImpl;

public class VoteServiceImpl extends BaseServiceImpl implements VoteService {
	/**
	 * 删除投票主题
	 */
	public void deleteVoteTitles(String[] ids) {
		this.deleteObjects(new VoteTitle(), ids);
	}
	
	public void deleteVoteTitleItems(String id){
		this.deleteAll("from VoteItems c where c.voteTitle.voteId = " + id);
	}

	@Override
	public VoteTitle queryNewVoteTitle() {
		List list = this.queryObjectList("from VoteTitle v where v.publishStatus = 1 order by v.voteDate desc");
		if (list != null && list.size() > 0) {
			return (VoteTitle) list.get(0);
		}
		return null;
	}

	@Override
	public Integer getItemsBallotCount(Long id) {
		Object obj = this.queryObjectList("select sum(c.voteItemses.itemBallot) from VoteTitle c where c.voteId = " + id).get(0);
		int recordCounts = 0;
		if (obj != null) {
			recordCounts = (Integer) obj;
		}
		return recordCounts;
	}

	@Override
	public void updateItemsBallotCount(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Long id = Long.parseLong(ids[i]);
			VoteItems o = (VoteItems) this.queryObjectById(VoteItems.class, id);
			o.setItemBallot(o.getItemBallot() + 1);
			this.updateObject(o);
		}
	}

	public void updateTitleVoteCount(String id) {
		Long voteTitleId = Long.parseLong(id);
		VoteTitle o = (VoteTitle) this.queryObjectById(VoteTitle.class, voteTitleId);
		o.setVoteHot(o.getVoteHot() + 1);
		this.updateObject(o);
	}

	@Override
	public int publishVoteTitle(String[] ids) {
		int effectCount = 0;
		for (int i = 0; i < ids.length; i++) {
			Long id = Long.parseLong(ids[i]);
			VoteTitle o = (VoteTitle) this.queryObjectById(VoteTitle.class, id);
			if (o.getPublishStatus() == (byte) 0) {
				o.setPublishStatus((byte) 1);
				effectCount++;
			}
			this.updateObject(o);
		}
		return effectCount;
	}

	@Override
	public int unPublishVoteTitle(String[] ids) {
		int effectCount = 0;
		for (int i = 0; i < ids.length; i++) {
			Long id = Long.parseLong(ids[i]);
			VoteTitle o = (VoteTitle) this.queryObjectById(VoteTitle.class, id);
			if (o.getPublishStatus() == (byte) 1) {
				o.setPublishStatus((byte) 0);
				effectCount++;
			}
			this.updateObject(o);
		}
		return effectCount;
	}
}
