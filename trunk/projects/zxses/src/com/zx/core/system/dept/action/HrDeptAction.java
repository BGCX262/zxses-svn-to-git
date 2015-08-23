package com.zx.core.system.dept.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.model.HrDept;
import com.zx.core.system.dept.service.IHrDeptService;

/*******************************************************************************
 * 部门管理
 * 
 * @author maolujun
 * 
 */
public class HrDeptAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private IHrDeptService hrDeptService;
	private List<HrDept> deptList;
	private HrDept hrDept;
	private HrDept selectHrDept;

	// 新增成功之后,js用于添加父节点还是当前节点下面
	private String addType;

	// 拖拽
	private String dragType;// 拖拽类型
	private Long sourceNodeId;// 源节点
	private Long targetNodeId;// 目标节点

	public IHrDeptService getHrDeptService() {
		return hrDeptService;
	}

	public void setHrDeptService(IHrDeptService hrDeptService) {
		this.hrDeptService = hrDeptService;
	}

	public List<HrDept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<HrDept> deptList) {
		this.deptList = deptList;
	}

	public HrDept getHrDept() {
		return hrDept;
	}

	public void setHrDept(HrDept hrDept) {
		this.hrDept = hrDept;
	}

	public Long getSourceNodeId() {
		return sourceNodeId;
	}

	public void setSourceNodeId(Long sourceNodeId) {
		this.sourceNodeId = sourceNodeId;
	}

	public Long getTargetNodeId() {
		return targetNodeId;
	}

	public void setTargetNodeId(Long targetNodeId) {
		this.targetNodeId = targetNodeId;
	}

	public String getDragType() {
		return dragType;
	}

	public void setDragType(String dragType) {
		this.dragType = dragType;
	}

	public HrDept getSelectHrDept() {
		return selectHrDept;
	}

	public void setSelectHrDept(HrDept selectHrDept) {
		this.selectHrDept = selectHrDept;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	/***************************************************************************
	 * 转跳到部门管理的主页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String deptMain() {
		try {
			deptList = this.hrDeptService.findHrDepts();
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionMessage("find.dept");
		}
		return "main";
	}

	/***************************************************************************
	 * 左页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String deptRight() {
		return "entryAdd";
	}

	/***************************************************************************
	 * 新增部门信息
	 * 
	 * @return
	 */
	public String saveHrDept() {
		try {
			boolean isExist = this.hrDeptService.isExistHrDeptCode(hrDept);
			if (isExist) {
				this.addFieldError("hrDept.code", this.getText("code.isExist"));
				return "entryAdd";
			}
			this.hrDeptService.saveHrDept(hrDept);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("save.fail");
		}
		this.addMessageInfo("save.success");
		// 如果此值不为空则说明新增成功!
		setSelectHrDept(hrDept);
		setHrDept(null);
		return "entryAdd";
	}

	/***************************************************************************
	 * 转跳到修改页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String entryUpdate() {
		try {
			setHrDept(this.hrDeptService.findHrDept(hrDept.getId()));
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("find.fail");
		}
		return "entryUpdate";
	}

	/***************************************************************************
	 * 修改部门信息
	 * 
	 * @return
	 */
	public String updateHrDept() {
		try {
			if (this.hrDeptService.isExistHrDeptCode(hrDept)) {
				this.addFieldError("hrDept.code", getText("code.isExist"));
				return "entryUpdate";
			}
			this.hrDeptService.updateHrDept(hrDept);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionError("update.failure");
			return "entryUpdate";
		}
		addMessageInfo("update.success");
		// 如果此值不为空则说明修改成功!
		setSelectHrDept(hrDept);
		return "entryUpdate";
	}

	/***************************************************************************
	 * 删除部门信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String deleteHrDept() {
		try {
			this.hrDeptService.deleteHrDepts(checkIds);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			try {
				getResponse().getWriter().write("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			getResponse().getWriter().write("success");
		} catch (IOException e) {
		}
		return null;
	}

	/***************************************************************************
	 * 拖拽部门信息
	 * 
	 * @return
	 */
	@SkipValidation
	public String updateDragHrDept() {
		try {
			this.hrDeptService.updateDragHrDepts(dragType, sourceNodeId,
					targetNodeId);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			try {
				getResponse().getWriter().write("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			getResponse().getWriter().write("success");
		} catch (IOException e) {
		}
		return null;
	}

}
