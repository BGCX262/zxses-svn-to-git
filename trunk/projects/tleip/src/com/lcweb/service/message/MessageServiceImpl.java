package com.lcweb.service.message;

import com.lcweb.bean.pojo.FrontMessage;
import com.lcweb.service.base.BaseServiceImpl;

public class MessageServiceImpl extends BaseServiceImpl implements
		MessageService {
	/**
	 * 删除留言
	 */
	public void deleteMessages(String[] ids) {
		this.deleteObjects(new FrontMessage(), ids);
	}
	
	/**
	 * 审批留言
	 */
	public int approveMessages(String[] ids) {
		int effectCount = 0;
		for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontMessage o = (FrontMessage)this.queryObjectById(FrontMessage.class, id);
			 if(o.getApproveStatus() == (short)0){
				 o.setApproveStatus((short)1);
				 effectCount ++;
			 }
			 this.updateObject(o);
		 }	
		return effectCount;
	}
	
	/**
	 * 反审批留言
	 */
	public int unApproveMessages(String[] ids) {
		int effectCount = 0;
		for(int i=0;i<ids.length;i++){
			 Long  id = Long.parseLong(ids[i]);
			 FrontMessage o = (FrontMessage)this.queryObjectById(FrontMessage.class, id);
			 if(o.getApproveStatus() == (short)1){
				 o.setApproveStatus((short)0);
				 effectCount ++;
			 }
			 this.updateObject(o);
		 }			
		return effectCount;
	}
}
