package com.lcweb.dao.rightmanage;

import java.util.List;

import org.hibernate.Session;

import com.lcweb.bean.pojo.BasicDepartment;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.SysModule;
import com.lcweb.bean.pojo.SysRole;
import com.lcweb.bean.pojo.SysRoleModule;
import com.lcweb.bean.pojo.SysRolePerson;
import com.lcweb.dao.base.BaseDaoImpl;

public class RightManageDaoImpl extends BaseDaoImpl implements RightManageDao {
	public void addRight(String[] funs, String roleId) {

		deleteAll("from SysRoleModule srm where srm.sysRole.roleId=" + roleId);
		if (funs != null) {
			List allParent = getHibernateTemplate().find(
					"select sm  from  SysModule sm where sm.parent.parent.upModule='0000000000'");
			for (int i = 0; i < allParent.size(); i++) {
				SysModule sm = (SysModule) allParent.get(i);
				SysRoleModule srm = new SysRoleModule();
				srm.setSysModule(sm);
				SysRole sr = new SysRole();
				sr.setRoleId(Long.parseLong(roleId));
				srm.setSysRole(sr);
				getHibernateTemplate().save(srm);
			}

			for (int j = 0; j < funs.length; j++) {
				String moduleId = funs[j];
				SysModule sm = new SysModule();
				sm.setModuleId(moduleId);
				SysRole sr = new SysRole();
				sr.setRoleId(Long.parseLong(roleId));
				SysRoleModule srm = new SysRoleModule();
				srm.setSysModule(sm);
				srm.setSysRole(sr);
				getHibernateTemplate().save(srm);
			}
		}
	}

	public void addRight(String[] parentModuleIds, String[] operatorModuleId, String roleId, String yxdm) {

		deleteAll("from SysRoleModule srm  where  srm.sysRole.roleId=" + roleId);
		if ((operatorModuleId != null)) {
			String querySysModuleSql = "select sm  from  SysModule sm where sm.parent.parent.upModule='0000000000'";
			List allParent = getHibernateTemplate().find(querySysModuleSql);
			for (int i = 0; i < allParent.size(); i++) {
				SysModule sm = (SysModule) allParent.get(i);
				SysRoleModule srm = new SysRoleModule();
				srm.setSysModule(sm);
				SysRole sr = new SysRole();
				sr.setRoleId(Long.parseLong(roleId));
				srm.setSysRole(sr);
				getHibernateTemplate().save(srm);
			}
			if (parentModuleIds != null) {
				for (int i = 0; i < parentModuleIds.length; i++) {
					String parentModuleId = parentModuleIds[i];
					SysModule parentSysModule = new SysModule();
					parentSysModule.setModuleId(parentModuleId);
					SysRole role = new SysRole();
					role.setRoleId(Long.parseLong(roleId));
					SysRoleModule parentSysRoleModule = new SysRoleModule();
					parentSysRoleModule.setSysModule(parentSysModule);
					parentSysRoleModule.setSysRole(role);
					getHibernateTemplate().save(parentSysRoleModule);
				}
			}
			for (int j = 0; j < operatorModuleId.length; j++) {
				String moduleId = operatorModuleId[j];
				SysModule sm = new SysModule();
				sm.setModuleId(moduleId);
				SysRole sr = new SysRole();
				sr.setRoleId(Long.parseLong(roleId));
				SysRoleModule srm = new SysRoleModule();
				srm.setSysModule(sm);
				srm.setSysRole(sr);
				getHibernateTemplate().save(srm);
			}
		}
	}

	/**
	 * ����Ȩ��
	 */
	public void addAssignRight(String[] roleIds, String personId) {
		deleteAll("from SysRolePerson s where s.basicPerson.personId=" + personId);
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				BasicPerson basicPerson = new BasicPerson();
				basicPerson.setPersonId(Long.parseLong(personId));
				SysRole sysRole = new SysRole();
				sysRole.setRoleId(Long.parseLong(roleIds[i]));
				SysRolePerson srp = new SysRolePerson();
				srp.setBasicPerson(basicPerson);
				srp.setSysRole(sysRole);
				getHibernateTemplate().save(srp);
			}
		}
	}

	/**
	 * ����������Ȩ��
	 */
	public void addBatchAssignUserRight(String[] personIds, String roleId) {
		deleteAll("from SysRolePerson s where s.sysRole.roleId=" + roleId);
		if ((personIds != null) && !personIds.equals("")) {
			for (int i = 0; i < personIds.length; i++) {
				BasicPerson basicPerson = new BasicPerson();
				basicPerson.setPersonId(Long.parseLong(personIds[i]));
				SysRole sysRole = new SysRole();
				sysRole.setRoleId(Long.parseLong(roleId));
				SysRolePerson srp = new SysRolePerson();
				srp.setBasicPerson(basicPerson);
				srp.setSysRole(sysRole);
				getHibernateTemplate().save(srp);
			}
		}
	}

	/**
	 * ����Ա�ӽ�ɫ��ɾ��������Ȩ�ޣ�
	 */
	public void deleteFromRole(String roleId, String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			deleteAll("from SysRolePerson s where s.sysRole.roleId=" + roleId + " and s.basicPerson.personId=" + ids[i]);
		}
	}

	public void addPerToDep(Long deptId, String[] ids) {
		if (deptId != null) {
			Session session = null;
			if (ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					Long id = Long.parseLong(ids[i]);
					BasicPerson bp = (BasicPerson) getHibernateTemplate().load(BasicPerson.class, id);
					BasicDepartment dept = new BasicDepartment();
					dept.setDeptId(deptId);
					bp.setBasicDepartment(dept);
					getHibernateTemplate().update(bp);
				}
			}
		}
	}

	public void deletePerFromDep(String id) {
		if (id != null) {
			BasicPerson bp = (BasicPerson) getHibernateTemplate().get(BasicPerson.class, Long.parseLong(id));
			bp.setBasicDepartment(null);
			getHibernateTemplate().update(bp);
		}
	}

	public List queryPersonRole(BasicPerson person) {
		String querySql = "from SysRolePerson s where s.basicPerson.personId=?";
		List rolePersonList = null;
		if ((person != null) && (person.getPersonId() != null)) {
			rolePersonList = getHibernateTemplate().find(querySql, new Object[] { person.getPersonId() });
		}
		return rolePersonList;
	}
}
