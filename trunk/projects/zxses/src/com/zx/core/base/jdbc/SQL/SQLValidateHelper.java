package com.zx.core.base.jdbc.SQL;

import java.util.Hashtable;

import com.zx.core.base.jdbc.SQL.SQLCommandBuilder.SQLCommandType;
import com.zx.core.exception.ManageException;

public class SQLValidateHelper {
    
    /**
     * 验证SQL命令字符串及其参数列表的方法
     * @param sqlCmdType SQL命令类型
     * @param tableName 要操作的特定数据库表名称
     * @param columnName 要操作的特定数据库列名称清单
     * @param param SQL参数列表
     * @param condition SQL条件列表
     * @return true-验证通过/false-验证失败
     * @throws ManageException SQL参数异常
     */
    public static void ValidateSQLAndParam(SQLCommandType sqlCmdType, String tableName, String[] columnName, Object[] param, Hashtable<String, Object> condition)
	    throws ManageException {

	// 消息提示后缀
	String notNullMsg = "空值和空字串值！";
	String resetParam = "请重新给定正确配对参数！";
	String entryTypeMsg = "请确认您使用的SQL命令类型是否正确！";

	// 如果SQL命令类型为空
	if (sqlCmdType == null) {
	    // 验证失败
	    throw new ManageException("SQL命令类型参数不能为空！");
	}
	// 如果要操作的特定数据库表名称为空
	if (tableName == null || tableName.equals("")) {
	    // 验证失败
	    throw new ManageException("要操作的特定数据库表名称不能为" + notNullMsg);
	}
	// 检查特定数据库列名称清单中是否包含null和空字串值
	if (columnName != null) {
	    for (int i = 0; i < columnName.length; i++) {
		// 移除参数中由使用者添加的方(中)括号(“[”和“]”)
		columnName[i] = columnName[i].replace('[', '\u0020').trim().replace(']', '\u0020').trim();
		if (columnName[i] == null || columnName[i].equals("")) {
		    // 验证失败
		    throw new ManageException("要操作的特定数据库列名称清单中不能包含" + notNullMsg);
		}
	    }
	}
	// 检查SQL参数列表中是否包含null和空字串值
	if (param != null) {
	    for (int i = 0; i < param.length; i++) {
		if (param[i] == null || param[i].equals("")) {
		    // 验证失败
		    throw new ManageException("SQL参数列表中不能包含" + notNullMsg);
		}
	    }
	}
	// 进行特殊的参数检查
	switch (sqlCmdType) {
	case SELECT:
	    // 如果给定了SQL参数列表
	    if (param != null && param.length > 0) {
		// 验证失败
		throw new ManageException("Select语句中无需指定SQL参数列表，" + entryTypeMsg);
	    }
	    break;
	case INSERT:
	    // 如果列名与参数个数不一致
	    if (columnName.length != param.length) {
		// 验证失败
		throw new ManageException("Insert语句中给定的列名与SQL参数数量不匹配，" + resetParam);
	    }
	    // 如果给定了SQL条件列表
	    if (condition != null && condition.size() > 0) {
		// 验证失败
		throw new ManageException("Insert语句中无需指定SQL条件列表，" + entryTypeMsg);
	    }
	    break;
	case UPDATE:
	    // 如果列名与参数个数不一致
	    if (columnName.length != param.length) {
		// 验证失败
		throw new ManageException("Update语句中给定的列名与SQL参数数量不匹配，" + resetParam);
	    }
	    break;
	case DELETE:
	    // 如果给定了要操作的特定数据库列名称清单
	    if (columnName != null && columnName.length > 0) {
		// 验证失败
		throw new ManageException("Delete语句中无需指定要操作的特定数据库列名称清单，" + entryTypeMsg);
	    }
	    // 如果给定了SQL参数列表
	    if (param != null && param.length > 0) {
		// 验证失败
		throw new ManageException("Delete语句中无需指定SQL参数列表，" + entryTypeMsg);
	    }
	    break;
	}

    }

}
