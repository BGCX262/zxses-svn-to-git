package com.lcweb.commons;

public class CheckRight {
	private JDBCTemplate jt;

	public boolean moduleOperationRight(String loginName, String parentModuleFlag, String operationModuleFlag) {
		if ((loginName == null) || "".equals(loginName)) {
			return false;
		}
		if ("admin".equals(loginName)) {
			return true;
		}
		boolean isHasPermission = false;
		String sql = "";
		if ("".equals(operationModuleFlag) || (operationModuleFlag == null)) {
			sql = " select * from sys_module  zsm where zsm.module_id in ("
					+ " select DISTINCT module_id  from sys_role_module  zm where zm.role_id in ( "
					+ " select role_id from  sys_role_person  ze  inner join basic_person bp on ze.person_id = bp.person_id where bp.person_account='"
					+ loginName + "')" + " ) and  module_flag='" + parentModuleFlag + "'";
		} else {
			sql = "select * from sys_module mo  " + "inner join sys_role_module zm  "
					+ "on mo.module_id= zm.module_id  " + "where zm.role_id in " + " (select role_id "
					+ "from sys_role_person ze inner join basic_person bp on ze.person_id = bp.person_id"
					+ " where bp.person_account='" + loginName + "') " + " and mo.module_flag='" + operationModuleFlag
					+ "' " + "and mo.up_module =( select DISTINCT mo.module_id from sys_module mo  "
					+ "inner join sys_role_module zm " + "on mo.module_id= zm.module_id  " + "where zm.role_id in "
					+ " (select role_id "
					+ "from sys_role_person ze inner join basic_person bp on ze.person_id = bp.person_id"
					+ " where bp.person_account='" + loginName + "') " + " and mo.module_flag='" + parentModuleFlag
					+ "')";

		}
		isHasPermission = (jt.jdbcQuery(sql, null).size() > 0);
		return isHasPermission;
	}

	public boolean moduleOperationRightForNews(String loginName, String parentModuleFlag, String operationModuleFlag) {
		boolean isHasPermission = false;
		if ((loginName == null) || "".equals(loginName)) {
			isHasPermission = false;
		}
		if ("admin".equals(loginName)) {
			isHasPermission = true;
		}
		String sql = "";
		if ("".equals(operationModuleFlag) || (operationModuleFlag == null)) {
			sql = " select * from sys_module zsm where zsm.module_id in ("
					+ " select DISTINCT module_id from sys_role_module zm where zm.role_id in ( "
					+ " select role_id from sys_role_person ze inner join basic_person bp on ze.person_id = bp.person_id where bp.person_account='"
					+ loginName + "')" + " ) and module_flag='" + parentModuleFlag + "'";
		} else {
			sql = "select * from sys_module sysM "
					+ " inner join sys_module parentSysM on parentSysM.module_id =sysM.up_module"
					+ " inner join sys_role_module sysRm on sysM.module_id =sysRm.module_id"
					+ " inner join sys_role_person sysRp on sysRm.role_id =  sysRp.role_Id"
					+ " inner join basic_person bp on bp.person_id = sysRp.person_id and  bp.person_account = '"
					+ loginName + "'" + " where sysM.module_flag='" + operationModuleFlag
					+ "' and parentSysM.module_flag='" + parentModuleFlag + "'";

		}
		isHasPermission = (jt.jdbcQuery(sql, null).size() > 0);
		return isHasPermission;
	}

	public JDBCTemplate getJt() {
		return jt;
	}

	public void setJt(JDBCTemplate jt) {
		this.jt = jt;
	}
}
