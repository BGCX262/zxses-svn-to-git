package com.lcweb.service.message;

import com.lcweb.service.base.BaseService;

public interface MessageService extends BaseService{
	/**
	 * 删除留言
	 */
	public void deleteMessages(String[] ids);
	
	/**
	 * 审批留言
	 */
	public int approveMessages(String[] ids) ;
	
	/**
	 * 反审批留言
	 */
	public int unApproveMessages(String[] ids) ;
}
