package com.agilefly.service.systype;

import java.util.List;

import com.agilefly.bean.SysType;
import com.agilefly.service.base.BaseDao;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:39:16 PM
 * 包含用户博客信息的操作
 */
public interface ISysTypeService extends BaseDao<SysType> {
	/**
	 * 根据用户身份编码(typecode-->教师[teacher]、学生[student])查询文章类型
	 * (教师和学生发表文章选择的类别不同)
	 * @param parentId
	 * @return
	 */
	public List<SysType> searchSysBlogTypes(String typecode);
}
