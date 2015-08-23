package com.lcweb.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.JsTree;
import com.lcweb.base.util.PageList;
import com.lcweb.base.util.SysObj;
import com.lcweb.bean.pojo.BasicDepartment;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.BasicUnit;
import com.lcweb.bean.pojo.SysModule;
import com.lcweb.bean.pojo.SysRole;
import com.lcweb.bean.pojo.SysRoleModule;
import com.lcweb.bean.pojo.SysRolePerson;
import com.lcweb.commons.CheckRight;
import com.lcweb.commons.CipherUtil;
import com.lcweb.commons.GlobalConst;
import com.lcweb.service.rightmanage.RightManageService;
import com.lcweb.struts.form.RightManageForm;

/**
 * 系统管理
 * 
 * 
 */
public class RightManageAction extends DispatchAction {

	private RightManageService rightManageService;
	private String contextpath;
	private CheckRight checkRight;

	public CheckRight getCheckRight() {
		return checkRight;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}

	public void setRightManageService(RightManageService rightManageService) {

		this.rightManageService = rightManageService;
	}

	@SuppressWarnings("unchecked")
	public ActionForward checkCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		String dm = request.getParameter("dm");
		String object = request.getParameter("object");
		List list = rightManageService.queryObjectList("from " + object + " where " + code + "='" + dm + "'");
		if (list.size() > 0) {
			try {
				response.getWriter().write("exist");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward queryUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String sql = "from BasicUnit";
		String sqlCount = "select count(*) from BasicUnit";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		contextpath = request.getContextPath();
		String path = contextpath + "/view/rightManage.do?method=queryUnit";
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, rightManageService,
				"rightManageForm");
		request.setAttribute("pageList", pageList);
		return mapping.findForward("unitList");
	}

	public ActionForward queryUnitDetailed(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		BasicUnit basicUnit = new BasicUnit();
		Long id = Long.parseLong(request.getParameter("unitId"));
		basicUnit = (BasicUnit) rightManageService.queryObjectByLongId(basicUnit, id);
		rightManageForm.setBasicUnit(basicUnit);
		return mapping.findForward("unitDetailed");
	}

	public ActionForward enterAddUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addUnit");
	}

	public ActionForward addUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		BasicUnit bu = new BasicUnit();
		bu = rightManageForm.getBasicUnit();
		rightManageService.saveObject(bu);
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return queryUnit(mapping, form, request, response);
	}

	public ActionForward enterUpdateUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		BasicUnit basicUnit = new BasicUnit();
		Long id = Long.parseLong(request.getParameter("unitId"));
		basicUnit = (BasicUnit) rightManageService.queryObjectByLongId(basicUnit, id);
		rightManageForm.setBasicUnit(basicUnit);
		return mapping.findForward("enterUpdateUnit");
	}

	public ActionForward updateUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		rightManageService.updateObject(rightManageForm.getBasicUnit());
		request.setAttribute("showMsg", SysObj.createEditMassageBox(""));
		return mapping.findForward("unitListDo");
	}

	public ActionForward deleteUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicUnit basicUnit = new BasicUnit();
		String[] ids = request.getParameterValues("check");
		int records = ids.length;
		rightManageService.deleteObjects(basicUnit, ids);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(records,""));
		return queryUnit(mapping, form, request, response);
	}

	@SuppressWarnings("unchecked")
	public ActionForward queryDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		String deptId = request.getParameter("deptId");
		if ((deptId != null) && !"".equals(deptId)) {
			BasicDepartment dept = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(),
					(Long.parseLong(deptId)));
			rightManageForm.setDepartmentName(dept.getDeptName());
			rightManageForm.setBasicDepartment(dept);
		}

		List deptList = rightManageService.queryObjectList("from BasicDepartment d where  d.upDeptCode=0");
		List treeList = new ArrayList();
		int id = 0;
		JsTree root = new JsTree();
		root.setId(0);
		root.setParentId(-1);
		root.setName("部门");
		root.setUrl("javascript:d.openAll()");
		treeList.add(root);
		for (int i = 0; i < deptList.size(); i++) {
			id++;
			BasicDepartment basicDepartment = (BasicDepartment) deptList.get(i);
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(0);
			parent.setName(basicDepartment.getDeptName());
			parent.setUrl("javascript:operation('" + basicDepartment.getDeptId() + "','"
					+ basicDepartment.getDeptName() + "','" + basicDepartment.getDeptCode() + "','"
					+ basicDepartment.getRemark() + "')");
			parent.setImage("../res/admin/img/folder.gif");
			treeList.add(parent);
			int rid = digui(basicDepartment.getChilds(), id, treeList);
			id = rid;
		}
		// if (deptId != null) {
		// rightManageForm.setStyle("none");
		// }
		request.setAttribute("treeList", treeList);
		rightManageForm.setDeptList(deptList);
		return mapping.findForward("deptList");
	}

	@SuppressWarnings("unchecked")
	public int digui(Set departs, int id, List treeList) {
		int rid = id;
		int pid = id;
		Iterator iter = departs.iterator();
		while (iter.hasNext()) {
			id++;
			BasicDepartment dep = (BasicDepartment) iter.next();
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(pid);
			parent.setName(dep.getDeptName());
			parent.setUrl("javascript:operation('" + dep.getDeptId() + "','" + dep.getDeptName() + "','"
					+ dep.getDeptCode() + "','" + dep.getRemark() + "')");
			parent.setImage("../res/admin/img/folder.gif");
			treeList.add(parent);
			if (dep.getChilds().size() > 0) {
				id = digui(dep.getChilds(), id, treeList);
				rid = id;
			} else {
				rid = id;
			}
		}
		return rid;
	}

	// 部门新增
	public ActionForward addNewDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		BasicDepartment bd = new BasicDepartment();
		bd.setDeptCode(rightManageForm.getBasicDepartment().getDeptCode());
		bd.setDeptName(rightManageForm.getBasicDepartment().getDeptName());
		bd.setRemark(rightManageForm.getBasicDepartment().getRemark());
		bd.setBasicUnit(null);
		bd.setUpDeptCode("0");
		rightManageService.saveObject(bd);
		request.setAttribute("bd", bd);
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return queryDepartment(mapping, form, request, response);
	}

	public ActionForward addDepartmentDo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		String deptId = request.getParameter("deptId");
		String leverNo = request.getParameter("leverNo");
		BasicDepartment bd;

		if ((deptId != null) && !"".equals(deptId)) {
			BasicDepartment dept = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(),
					(Long.parseLong(deptId)));
			if (dept != null) {
				if ("1".equals(leverNo)) {
					rightManageForm.getBasicDepartment().setUpDeptCode(dept.getUpDeptCode());
					rightManageForm.getBasicDepartment().setBasicUnit(null);
					bd = rightManageForm.getBasicDepartment();
					rightManageService.saveObject(bd);
					request.setAttribute("bd", bd);
					request.setAttribute("showMsg", SysObj.createEditMassageBox(dept.getDeptName()));
					return queryDepartment(mapping, form, request, response);
				} else if ("2".equals(leverNo)) {
					rightManageForm.getBasicDepartment().setUpDeptCode(dept.getDeptId().toString());
					rightManageForm.getBasicDepartment().setBasicUnit(null);
					bd = rightManageForm.getBasicDepartment();
					rightManageService.saveObject(bd);
					request.setAttribute("bd", bd);
					request.setAttribute("showMsg", SysObj.createEditMassageBox(dept.getDeptName()));
					return queryDepartment(mapping, form, request, response);
				} else {
					dept.setDeptCode(rightManageForm.getBasicDepartment().getDeptCode());
					dept.setDeptName(rightManageForm.getBasicDepartment().getDeptName());
					dept.setRemark(rightManageForm.getBasicDepartment().getRemark());
					rightManageService.updateObject(dept);
					request.setAttribute("bd", dept);
					request.setAttribute("showMsg", SysObj.createEditMassageBox(dept.getDeptName()));
					return queryDepartment(mapping, form, request, response);
				}
			}

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward deleteDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String deptId = request.getParameter("deptId");
		if ((deptId != null) && !"".equals(deptId)) {
			List deptList = rightManageService.queryObjectList("from BasicDepartment b where b.upDeptCode="
					+ Long.parseLong(deptId));
			if (deptList.size() > 0) {
				request.setAttribute("showMsg", SysObj.createDeleteMassageBox(0,""));
				return null;
			} else { 
				BasicDepartment basicDepartment = new BasicDepartment();
				basicDepartment = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(), Long
						.parseLong(deptId));
				rightManageService.deleteObject(basicDepartment);
			}
		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(0,""));
		return new ActionForward("/view/rightManage.do?method=queryDepartment",true);
	}

	@SuppressWarnings("unchecked")
	public ActionForward deleteCheckDepPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String deptId = request.getParameter("deptId");
		List list = rightManageService.queryObjectList("from BasicPerson b where b.basicDepartment.deptId=" + deptId);
		if (list.size() > 0) {
			try {
				response.getWriter().write("exist");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward queryDepartmentPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		String deptId = request.getParameter("deptId");
		String sql = "";
		List personList = new ArrayList();

		if ((deptId == null) || "".equals(deptId)) {
			List list = rightManageService.queryObjectList("from BasicDepartment d where  d.upDeptCode=0");
			BasicDepartment basicDepart = new BasicDepartment();
			if (list.size() > 0) {
				basicDepart = (BasicDepartment) list.get(0);
				deptId = basicDepart.getDeptId().toString();
			}
			sql = "from BasicPerson b where b.basicDepartment.deptId=" + deptId;
			personList = rightManageService.queryObjectList(sql);
			request.setAttribute("personList", personList);
			rightManageForm.setBasicDepartment(basicDepart);
			rightManageForm.setStyle("block");
		} else {
			BasicDepartment dept = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(),
					(Long.parseLong(deptId)));
			sql = "from BasicPerson b where b.basicDepartment.deptId=" + deptId;
			personList = rightManageService.queryObjectList(sql);
			request.setAttribute("personList", personList);
			rightManageForm.setBasicDepartment(dept);
			rightManageForm.setStyle("block");
		}

		List deptList = rightManageService.queryObjectList("from BasicDepartment d where  d.upDeptCode=0");
		List treeList = new ArrayList();
		int id = 0;
		JsTree root = new JsTree();
		root.setId(0);
		root.setParentId(-1);
		root.setName("部门人员");
		root.setUrl("javascript:d.openAll()");
		treeList.add(root);
		contextpath = request.getContextPath();
		for (int i = 0; i < deptList.size(); i++) {
			id++;
			BasicDepartment basicDepartment = (BasicDepartment) deptList.get(i);
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(0);
			parent.setName(basicDepartment.getDeptName());
			parent.setUrl(contextpath + "/view/rightManage.do?method=queryDepartmentPerson&deptId="
					+ basicDepartment.getDeptId());
			parent.setImage("../res/admin/img/folder.gif");
			treeList.add(parent);
			int rid = diguiDepartmentPerson(basicDepartment.getChilds(), id, treeList);
			id = rid;
		}
		request.setAttribute("treeList", treeList);
		rightManageForm.setDeptList(deptList);
		return mapping.findForward("departmentPersonList");
	}

	@SuppressWarnings("unchecked")
	public int diguiDepartmentPerson(Set departs, int id, List treeList) {
		int rid = id;
		int pid = id;
		Iterator iter = departs.iterator();
		while (iter.hasNext()) {
			id++;
			BasicDepartment dep = (BasicDepartment) iter.next();
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(pid);
			parent.setName(dep.getDeptName());
			parent.setUrl(contextpath + "/view/rightManage.do?method=queryDepartmentPerson&deptId=" + dep.getDeptId());
			parent.setImage("../res/admin/img/folder.gif");
			treeList.add(parent);
			if (dep.getChilds().size() > 0) {
				id = diguiDepartmentPerson(dep.getChilds(), id, treeList);
				rid = id;
			} else {
				rid = id;
			}
		}
		return rid;
	}

	public ActionForward queryPersonList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		String deptId = request.getParameter("deptId");
		if ((deptId != null) && !"".equals(deptId)) {
			BasicDepartment bd = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(), Long
					.parseLong(deptId));
			rightManageForm.setBasicDepartment(bd);
		}
		contextpath = request.getContextPath();
		String sql = "from BasicPerson b where  b.basicDepartment.deptId is null or  b.basicDepartment.deptId<>"
				+ deptId;
		String sqlCount = "select count(*) from BasicPerson b where  b.basicDepartment.deptId is null or  b.basicDepartment.deptId<>"
				+ deptId;
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		String path = contextpath + "/view/rightManage.do?method=queryPersonList&deptId=" + deptId;
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, rightManageService,
				"rightManageForm");
		request.setAttribute("pageList", pageList);
		if (pageList.getRecordCounts() == 0) {
			rightManageForm.setStyle("none");
		} else {
			rightManageForm.setStyle("block");
		}
		return mapping.findForward("personList");
	}
	/**
	 * 添加人员
	 */
	public ActionForward addPersonToDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Long deptId = Long.parseLong(request.getParameter("deptId"));
		String[] ids = request.getParameterValues("check");
		rightManageService.addPerToDep(deptId, ids);
		request.setAttribute("showMsg", SysObj.createAddPersonMassageBox(""));
		return new ActionForward("/view/rightManage.do?method=queryDepartmentPerson&deptId="+deptId,true);
//		return queryDepartmentPerson(mapping, form, request, response);
	}

	/**
	 * 从部门里删除用户
	 */
	public ActionForward deletePerFromDep(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("personId");
		try {
			rightManageService.deletePerFromDep(id);
			response.getWriter().write("ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("departmentPersonListDo");
	}

	/**
	 * 查询用户
	 */
	@SuppressWarnings("unchecked")
	public ActionForward queryPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm systemManageForm = (RightManageForm) form;
		String deptId = request.getParameter("deptId");
		String sql = "";
		List personList = new ArrayList();

		if ((deptId == null) || "".equals(deptId)) {
			List list = rightManageService.queryObjectList("from BasicDepartment d where  d.upDeptCode=0");
			BasicDepartment basicDepartment = new BasicDepartment();
			if (list.size() > 0) {
				basicDepartment = (BasicDepartment) list.get(0);
				deptId = basicDepartment.getDeptId().toString();
			}
			sql = "from BasicPerson b where b.basicDepartment.deptId=" + deptId;
			personList = rightManageService.queryObjectList(sql);
			request.setAttribute("personList", personList);
			systemManageForm.setBasicDepartment(basicDepartment);
			systemManageForm.setStyle("block");
		} else {
			BasicDepartment dept = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(),
					(Long.parseLong(deptId)));
			sql = "from BasicPerson b where b.basicDepartment.deptId=" + deptId;
			personList = rightManageService.queryObjectList(sql);
			request.setAttribute("personList", personList);
			systemManageForm.setDepartmentName(dept.getDeptName());
			systemManageForm.setBasicDepartment(dept);
			systemManageForm.setStyle("block");
		}
		List deptList = rightManageService.queryObjectList("from BasicDepartment d where  d.upDeptCode=0");
		List treeList = new ArrayList();
		int id = 0;
		JsTree root = new JsTree();
		root.setId(0);
		root.setParentId(-1);
		root.setName("部门人员");
		root.setUrl("javascript:d.openAll()");
		treeList.add(root);
		contextpath = request.getContextPath();
		for (int i = 0; i < deptList.size(); i++) {
			id++;
			BasicDepartment basicDepartment = (BasicDepartment) deptList.get(i);
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(0);
			parent.setName(basicDepartment.getDeptName());
			parent
					.setUrl(contextpath + "/view/rightManage.do?method=queryPerson&deptId="
							+ basicDepartment.getDeptId());
			parent.setImage("../res/admin/img/folder.gif");
			treeList.add(parent);
			int rid = diguiPerson(basicDepartment.getChilds(), id, treeList);
			id = rid;
		}
		request.setAttribute("treeList", treeList);
		systemManageForm.setDeptList(deptList);
		return mapping.findForward("userList");
	}

	@SuppressWarnings("unchecked")
	public int diguiPerson(Set departs, int id, List treeList) {
		int rid = id;
		int pid = id;
		Iterator iter = departs.iterator();
		while (iter.hasNext()) {
			id++;
			BasicDepartment dep = (BasicDepartment) iter.next();
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(pid);
			parent.setName(dep.getDeptName());
			parent.setUrl(contextpath + "/view/rightManage.do?method=queryPerson&deptId=" + dep.getDeptId());
			parent.setImage("../res/admin/img/folder.gif");
			treeList.add(parent);
			if (dep.getChilds().size() > 0) {
				id = diguiPerson(dep.getChilds(), id, treeList);
				rid = id;
			} else {
				rid = id;
			}
		}
		return rid;
	}

	public ActionForward enterAddUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm systemManageForm = (RightManageForm) form;
		Long deptId = Long.parseLong(request.getParameter("deptId"));
		BasicDepartment basicDepartment = (BasicDepartment) rightManageService.queryObjectByLongId(
				new BasicDepartment(), deptId);
		systemManageForm.setBasicDepartment(basicDepartment);
		return mapping.findForward("enterAddUser");
	}

	/**
	 * 验证用户名 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward checkUserAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String personAccount = request.getParameter("personAccount");
		List list = rightManageService
				.queryObjectList("select b.personAccount from BasicPerson b where b.personAccount='" + personAccount
						+ "'");
		if (list.size() > 0) {
			try {
				response.getWriter().write("exist");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 新增用户
	 */
	public ActionForward addUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm systemManageForm = (RightManageForm) form;
		BasicPerson basicPerson = new BasicPerson();
		Long deptId = systemManageForm.getBasicDepartment().getDeptId();
		//Long deptId = Long.parseLong(request.getParameter("basicDepartment.deptId"));
		basicPerson = systemManageForm.getBasicPerson();
		basicPerson.setPassword(CipherUtil.generatePassword(basicPerson.getPassword()));
		BasicDepartment bd = new BasicDepartment();
		bd = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(), deptId);
		basicPerson.setBasicDepartment(bd);
		rightManageService.saveObject(basicPerson);
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return new ActionForward("/view/rightManage.do?method=queryPerson&deptId="+deptId);
//		return queryPerson(mapping, form, request, response);
	}

	/**
	 * ޸修改用户密码
	 */
	public ActionForward updatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm systemManageForm = (RightManageForm) form;
		Long personId = Long.parseLong(request.getParameter("personId"));
		String deptId = request.getParameter("deptId");
		systemManageForm.getBasicDepartment().setDeptId(Long.parseLong(deptId));
		BasicPerson person = (BasicPerson) rightManageService.queryObjectByLongId(new BasicPerson(), personId);
		if (person != null) {
			systemManageForm.setBasicPerson(person);
		}
		return mapping.findForward("updatePassword");
	}

	/**
	 * 修改用户密码之后跳转的页面
	 */
	public ActionForward updatePasswordDo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm systemManageForm = (RightManageForm) form;
		String password = request.getParameter("password2");
		Long personId = systemManageForm.getBasicPerson().getPersonId();
		Long deptId = systemManageForm.getBasicDepartment().getDeptId();
		systemManageForm.setBasicPerson((BasicPerson) rightManageService.queryObjectByLongId(new BasicPerson(),
				personId));
		systemManageForm.getBasicPerson().setPassword(CipherUtil.generatePassword(password));
		rightManageService.updateObject(systemManageForm.getBasicPerson());
		request.setAttribute("showMsg", SysObj.createEditMassageBox(systemManageForm.getBasicPerson().getPersonName()));
		return new ActionForward("/view/rightManage.do?method=queryPerson&deptId="+deptId);
	}

	/**
	 * 修改用户方法
	 */
	public ActionForward updateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm systemManageForm = (RightManageForm) form;
		Long personId = Long.parseLong(request.getParameter("personId"));
		Long deptId = systemManageForm.getBasicDepartment().getDeptId();
		
		BasicPerson basicPerson = (BasicPerson) rightManageService.queryObjectByLongId(new BasicPerson(),personId);
		BasicDepartment bd = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(), deptId);
		basicPerson.setBasicDepartment(bd);
		
		systemManageForm.setBasicPerson(basicPerson);
		return mapping.findForward("updateUser");
	}

	/**
	 * 修改用户之后跳转到的页面
	 */
	public ActionForward updateUserDo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		RightManageForm systemManageForm = (RightManageForm) form;
		BasicPerson basicPerson = new BasicPerson();
		Long deptId = Long.parseLong(request.getParameter("deptId"));
		basicPerson = systemManageForm.getBasicPerson();
		basicPerson.setPassword(CipherUtil.generatePassword(basicPerson.getPassword()));
		BasicDepartment bd = new BasicDepartment();
		bd = (BasicDepartment) rightManageService.queryObjectByLongId(new BasicDepartment(), deptId);
		basicPerson.setBasicDepartment(bd);
		rightManageService.updateObject(basicPerson);
		request.setAttribute("showMsg", SysObj.createEditMassageBox(basicPerson.getPersonName()));
		return queryPerson(mapping, form, request, response);
	}

	/**
	 * 删除用户
	 */
	@SuppressWarnings("unchecked")
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = new BasicPerson();
		RightManageForm systemManageForm = (RightManageForm) form;
		String[] personIds = request.getParameterValues("check");
		Long deptId = systemManageForm.getBasicDepartment().getDeptId();
		int delRecord =0;
		for (int i = 0; i < personIds.length; i++) {
			basicPerson = (BasicPerson) rightManageService.queryObjectById(basicPerson, Long.valueOf(personIds[i]));
			List rolePersonList = rightManageService.queryPersonRole(basicPerson);
			if ((rolePersonList.size() > 0)) {
				for (int j = 0; j < rolePersonList.size(); j++) {
					rightManageService.deleteObject(rolePersonList.get(j));
				}
			}
			rightManageService.deleteObject(basicPerson);
			delRecord++;
		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecord,""));
		return new ActionForward("/view/rightManage.do?method=queryPerson&deptId="+deptId);
	}

	/**
	 * 查询角色
	 */
	public ActionForward queryRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String sql = "from SysRole";
		String sqlCount = "select count(*) from SysRole";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		String path = basePath + "view/rightManage.do?method=queryRole";
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, rightManageService,
				"rightManageForm");
		request.setAttribute("pageList", pageList);
		return mapping.findForward("roleList");
	}

	/**
	 * 新增角色
	 */
	public ActionForward enterAddRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("roleAdd");
	}

	public ActionForward addRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm roleManageForm = (RightManageForm) form;
		String roleName = roleManageForm.getSysRole().getRoleName();
		if ((roleName != null) && !"".equals(roleName)) {
			rightManageService.saveObject(roleManageForm.getSysRole());
		}
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return queryRole(mapping, form, request, response);
	}

	public ActionForward enterUpdateRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm roleManageForm = (RightManageForm) form;
		SysRole role = new SysRole();
		Long id = Long.parseLong(request.getParameter("roleId"));
		role = (SysRole) rightManageService.queryObjectByLongId(role, id);
		roleManageForm.setSysRole(role);
		return mapping.findForward("enterUpdateRole");
	}

	@SuppressWarnings("unchecked")
	public ActionForward updateRoleExist(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String roleName = request.getParameter("rolename");
		List list = rightManageService.queryObjectList("select sr.roleName from SysRole sr where sr.roleName='"
				+ roleName + "'");
		if (list.size() > 0) {
			try {
				response.getWriter().write("exist");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward updateRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm roleManageForm = (RightManageForm) form;
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "jsgl", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String roleName = roleManageForm.getSysRole().getRoleName();
		if ((roleName != null) && !"".equals(roleName)) {
			rightManageService.updateObject(roleManageForm.getSysRole());
		}
		request.setAttribute("showMsg", SysObj.createEditMassageBox(roleName));
		return mapping.findForward("roleListdo");
	}

	public ActionForward deleteRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "jsgl", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		SysRole role = new SysRole();
		String[] ids = request.getParameterValues("check");
		rightManageService.deleteObjects(role, ids);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length,""));
		return queryRole(mapping, form, request, response);
	}

	public ActionForward queryRoleUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "jsgl", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String roleId = request.getParameter("roleId");
		String sql = "from SysRolePerson s where s.sysRole.roleId=" + roleId;
		String sqlCount = "select count(*) from SysRolePerson s where s.sysRole.roleId=" + roleId;
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		String path = "view/rightManage.do?method=queryRoleUser";
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, rightManageService,
				"rightManageForm");
		request.setAttribute("pageList", pageList);
		return mapping.findForward("roleUserList");
	}

	public ActionForward enterAddRoleUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "jsgl", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String sql = "select b.personId, b.personCode,b.personName from BasicPerson b";
		String sqlCount = "select count(*) from BasisPerson";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		String path = "view/rightManage.do?method=enterAddRoleUser";
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, rightManageService,
				"rightManageForm");
		request.setAttribute("pageList", pageList);
		return mapping.findForward("roleUserList");
	}

	@SuppressWarnings("unchecked")
	public ActionForward queryRoleRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		String roleId = request.getParameter("roleId");
		String yxdms = request.getParameter("yxdm");
		if ("".equals(yxdms) || (yxdms == null)) {
			yxdms = GlobalConst.SchoolCode;
		}
		if (roleId == null) {
			List roles = rightManageService.queryObjectList("from SysRole ");
			if (roles.size() > 0) {
				roleId = ((SysRole) roles.get(0)).getRoleId().toString();
			}

		}
		List roleList = this.rightManageService.queryObjectList(" from SysRole");
		for(int rl=0;rl<roleList.size();rl++){
			SysRole sr = (SysRole)roleList.get(rl);
			if(Long.parseLong(roleId)==sr.getRoleId()){
				rightManageForm.setSysRole(sr);
				break;
			}
		}
		List moduleList = this.rightManageService.queryObjectList("from SysModule sm  where sm.upModule='0000000000' and sm.moduleId<>'0100000000'");
		List functionList = this.rightManageService.queryObjectList("from SysRoleModule srm where srm.sysRole.roleId="+roleId);
		//循环upModule为0000000000的模块
		for(int i=0;i<moduleList.size();i++)
		{
		    SysModule sm1 = (SysModule)moduleList.get(i);
		    Set fl1 = sm1.getChildModules();
		    Iterator iter1 = fl1.iterator();
		    // 循环0000000000的子模块
		    while(iter1.hasNext())
		    {
		    	SysModule sm2 = (SysModule)iter1.next();
		    	Set fl2  = sm2.getChildModules();
		    	Iterator iter2 = fl2.iterator();
		    	int sm2Count = 0;
		    	// 循环具体模块
		    	while(iter2.hasNext())
		    	{
		    		SysModule sm3 = (SysModule)iter2.next();
		  	    	Set fl3 = sm3.getChildModules();
		  	    	Iterator iter3 = fl3.iterator();
		  	    	int sm3Count = 0;
		  	    	while(iter3.hasNext())
		  	    	{
		  	  	    	SysModule sm4 = (SysModule)iter3.next();
		  	  	    	for(int j=0;j<functionList.size();j++){
		  	    			SysRoleModule srm = (SysRoleModule)functionList.get(j);
		  	    			// 判断该角色是否拥有该增删改查的功能
		  	    			if(srm.getSysModule().getModuleId().equals(sm4.getModuleId())){
		  	    				sm4.setIsCheck("checked");
		  	    				sm3Count++;
		  	    			} 
		  	    		}
		  	    	}
		  	    	if(sm3Count>0 ){
		  	    		sm3.setIsCheck("checked");
		  	    		sm2Count ++;
		  	    	}
		    	}
		    	if(sm2Count>0){
		    		sm2.setIsCheck("checked");
		    	}
		    }
		}
		rightManageForm.setModuleList(moduleList);
		rightManageForm.setRoleList(roleList); 
		return mapping.findForward("roleRightList");
	}

	/**
	 * ����Ȩ��
	 */
	public ActionForward addRoleRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String[] operatorModuleId = request.getParameterValues("checkbox");
		String[] parentModuleIds = request.getParameterValues("parentCheckbox");
		String yxdm = request.getParameter("yxdms");
		rightManageService.giveRight(parentModuleIds, operatorModuleId, roleId, yxdm);
		request.setAttribute("result", "result");
		try {

			response.getWriter().write(
					"<script>alert('授权成功');window.location='rightManage.do?method=queryRoleRight'</script>");
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * ��������û�Ȩ��
	 */
	@SuppressWarnings("unchecked")
	public ActionForward enterAssignUserRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		List sysRole = rightManageService.queryObjectList("from SysRole");

		List deptList = rightManageService.queryObjectList("from BasicDepartment");
		List treeList = new ArrayList();
		int id = 0;
		JsTree root = new JsTree();
		root.setId(0);
		root.setParentId(-1);
		root.setName("部门人员");
		treeList.add(root);

		for (int i = 0; i < deptList.size(); i++) {
			id++;
			BasicDepartment basicDepartment = (BasicDepartment) deptList.get(i);
			Set persons = basicDepartment.getBasicPersons();
			Iterator iter = persons.iterator();
			JsTree parent = new JsTree();
			parent.setId(id);
			parent.setParentId(0);
			parent.setName(basicDepartment.getDeptName());
			parent.setImage("../res/admin/img/folder.gif");
			contextpath = request.getContextPath();
			treeList.add(parent);
			while (iter.hasNext()) {
				id++;
				BasicPerson bp = (BasicPerson) iter.next();
				JsTree tree = new JsTree();
				tree.setId(id);
				tree.setParentId(parent.getId());
				tree.setName(bp.getPersonName());
				tree.setUrl(contextpath + "/view/rightManage.do?method=enterAssignUserRight&personid="
						+ bp.getPersonId());
				tree.setImage("../res/admin/img/page.gif");
				treeList.add(tree);
			}

		}
		request.setAttribute("treeList", treeList);
		String personid = request.getParameter("personid");
		if (personid == null) {
			List tempPerson = rightManageService.queryObjectList("from BasicPerson");
			if (tempPerson.size() != 0) {
				BasicPerson bp = (BasicPerson) tempPerson.get(0);
				personid = bp.getPersonId() + "";
				rightManageForm.setBasicPerson(bp);
			}
		} else {
			BasicPerson bp = (BasicPerson) rightManageService.queryObjectByLongId(new BasicPerson(), Long
					.parseLong(personid));
			rightManageForm.setBasicPerson(bp);
		}

		if (personid != null) {
			List sysRolePerson = rightManageService
					.queryObjectList("from SysRolePerson s where s.basicPerson.personId=" + personid);

			for (int j = 0; j < sysRole.size(); j++) {
				SysRole sr = (SysRole) sysRole.get(j);
				for (int i = 0; i < sysRolePerson.size(); i++) {
					SysRolePerson srp = (SysRolePerson) sysRolePerson.get(i);

					if (sr.getRoleId().equals(srp.getSysRole().getRoleId())) {
						sr.setIsChecked("checked");
					}
				}
			}
		}

		rightManageForm.setRoleList(sysRole);
		rightManageForm.setDeptList(deptList);

		return mapping.findForward("userRightList");
	}

	/**
	 * �����û�Ȩ��
	 */
	public ActionForward assignUserRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String pId = request.getParameter("personId");
		String[] roleIds = request.getParameterValues("checkbox");
		rightManageService.assignRight(roleIds, pId);
		try {
			response.getWriter().write(
					"<script>alert('分配权限成功！');window.location='rightManage.do?method=enterAssignUserRight'</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ������Ȩ��
	 */
	@SuppressWarnings("unchecked")
	public ActionForward batchAssignUserRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RightManageForm rightManageForm = (RightManageForm) form;
		String roleId = request.getParameter("roleId");
		if (roleId == null) {

			List list = rightManageService.queryObjectList("from SysRole");

			if (list.size() > 0) {
				SysRole role = (SysRole) list.get(0);
				roleId = role.getRoleId().toString();
			}
		}
		List roleList = rightManageService.queryObjectList("from SysRole");
		for (int rl = 0; rl < roleList.size(); rl++) {
			SysRole sr = (SysRole) roleList.get(rl);
			if (Long.parseLong(roleId) == sr.getRoleId()) {
				rightManageForm.setSysRole(sr);
			}
		}
		List deptList = rightManageService.queryObjectList("from BasicDepartment");
		List rolePerson = rightManageService.queryObjectList("from SysRolePerson s where s.sysRole.roleId=" + roleId);
		Iterator detpIter = deptList.iterator();
		while (detpIter.hasNext()) {
			BasicDepartment basicDepartment = (BasicDepartment) detpIter.next();
			Set personSet = basicDepartment.getBasicPersons();
			Iterator personIter = personSet.iterator();
			while (personIter.hasNext()) {
				BasicPerson basicPerson = (BasicPerson) personIter.next();
				for (int i = 0; i < rolePerson.size(); i++) {
					SysRolePerson sysRolePerson = (SysRolePerson) rolePerson.get(i);
					if (basicPerson.getPersonId().equals(sysRolePerson.getBasicPerson().getPersonId())) {
						basicPerson.setIsChecked("checked");
					}
				}
			}
		}
		rightManageForm.setRoleList(roleList);
		rightManageForm.setDeptList(deptList);
		return mapping.findForward("batchAssignRight");
	}

	public ActionForward addBatchAssignUserRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String[] personIds = request.getParameterValues("personbox");
		rightManageService.addBatchAssignUserRight(personIds, roleId);
		try {
			response.getWriter().write(
					"<script>alert('授权成功！');window.location='rightManage.do?method=batchAssignUserRight'</script>");
		} catch (Exception e) {
		}
		return null;
	}

	public ActionForward deleteFromRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String[] personIds = request.getParameterValues("check");
		rightManageService.deleteFromRole(roleId, personIds);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(personIds.length, ""));
		return batchAssignUserRight(mapping, form, request, response);
	}
}