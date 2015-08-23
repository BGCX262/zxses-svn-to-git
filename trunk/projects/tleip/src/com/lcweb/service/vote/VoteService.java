package com.lcweb.service.vote;

import com.lcweb.bean.pojo.VoteTitle;
import com.lcweb.service.base.BaseService;

public interface VoteService extends BaseService{
	/**
	 * 删除投票
	 */
	public void deleteVoteTitles(String[] ids);
	
	/**
	 * 删除指定的投票主题下的投票选线
	 */
	public void deleteVoteTitleItems(String id);
	
	/**
	 * 查询最新的投票
	 */
	public VoteTitle queryNewVoteTitle();
	
	/**
	 * 发布投票
	 */
	public int publishVoteTitle(String[] ids);
	
	/**
	 * 反发布投票
	 */
	public int unPublishVoteTitle(String[] ids);
	
	/**
	 * 获得投票选项的投票值
	 */
	public Integer getItemsBallotCount(Long id);
	
	/**
	 * 更新投票选项的投票值 投票一次加一
	 */
	public void updateItemsBallotCount(String[] ids);
	
	/**
	 * 更新投票主题的人气值 投票一次加一
	 */
	public void updateTitleVoteCount(String id);
}
