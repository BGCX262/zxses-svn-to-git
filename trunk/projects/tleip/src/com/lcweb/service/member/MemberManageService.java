package com.lcweb.service.member;

import com.lcweb.service.base.BaseService;

public interface MemberManageService extends BaseService{
	/**
	 * 逻辑删除会员
	 */
	public void deleteMembers(String[] ids);
	
	/**
	 * 审批会员
	 */
	public int approveMembers(String[] ids) ;
	
	/**
	 * 反审批会员
	 */
	public int unApproveMembers(String[] ids) ;
	
	/**
	 * 启用会员
	 */
	public int enableMembers(String[] ids) ;
	
	/**
	 * 禁用会员
	 */
	public int disableMembers(String[] ids) ;
}
