package com.lcweb.service.member;

import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.dao.member.MemberManageDao;
import com.lcweb.service.base.BaseServiceImpl;

public class MemberManageServiceImpl extends BaseServiceImpl implements MemberManageService{
	private MemberManageDao memberManageDao;

	/**
	 * 重写父类方法，逻辑删除会员（前端用户）
	 */
	public void deleteMembers(String[] ids) {
		 for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontUser o = (FrontUser)this.queryObjectById(FrontUser.class, id);
			 o.setRecordStatus((short)0);
			 memberManageDao.updateObject(o);
		 }			
	}
	
	/**
	 * 审批会员
	 */
	public int approveMembers(String[] ids) {
		int effectCount = 0;
		for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontUser o = (FrontUser)this.queryObjectById(FrontUser.class, id);
			 if(o.getApproveStatus() == (short)0){
				 o.setApproveStatus((short)1);
				 effectCount ++;
			 }
			 memberManageDao.updateObject(o);
		 }	
		return effectCount;
	}
	
	/**
	 * 反审批会员
	 */
	public int unApproveMembers(String[] ids) {
		int effectCount = 0;
		for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontUser o = (FrontUser)this.queryObjectById(FrontUser.class, id);
			 if(o.getApproveStatus() == (short)1){
				 o.setApproveStatus((short)0);
				 effectCount ++;
			 }
			 memberManageDao.updateObject(o);
		 }			
		return effectCount;
	}
	
	/**
	 * 启用会员
	 */
	public int enableMembers(String[] ids) {
		int effectCount = 0;
		 for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontUser o = (FrontUser)this.queryObjectById(FrontUser.class, id);
			 if(o.getAvailable() == (short)0){
				 o.setAvailable((short)1);
				 effectCount ++;
			 }
			 memberManageDao.updateObject(o);
		 }	
		 return effectCount;
	}
	
	/**
	 * 禁用会员
	 */
	public int disableMembers(String[] ids) {
		 int effectCount = 0;
		 for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontUser o = (FrontUser)this.queryObjectById(FrontUser.class, id);
			 if(o.getAvailable() == (short)1){
				 o.setAvailable((short)0);
				 effectCount ++;
			 }
			 memberManageDao.updateObject(o);
		 }	
		 return effectCount;
	}
	
	public void setMemberManageDao(MemberManageDao memberManageDao) {
		this.memberManageDao = memberManageDao;
	}
}
