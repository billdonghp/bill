/**
 * @author qinxd
 * @date 2006-10-10
 */
package com.ait.ess.dao;

import java.sql.SQLException;

import com.ait.core.exception.GlRuntimeException;
import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.ParameterType;
import com.ait.jdbc.core.SQLResult;
import com.ait.jdbc.core.SqlParameter;

/**
 * 
 * @version 1.0
 */
public class AffairLeaveDAO {
	/**
	 * 事假查看
	 * @param adminId
	 * @param year
	 * @param searchcontent
	 * @return
	 */
	public SQLResult getAffairLeave(String adminId, Integer year, String searchcontent){
		String sql = 
			"select a.工号, a.姓名, a.部门, b.Date_Started, a.事假"
			+ " from ar_affair_leave_v a join ("
		    + " SELECT HR_EMPLOYEE.EMPID, HR_EMPLOYEE.Date_Started"
			+ " FROM HR_EMPLOYEE, HR_DEPARTMENT, AR_SUPERVISOR_INFO " 
			+ " WHERE HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID " 
			+ "  AND AR_SUPERVISOR_INFO.SUPERVISED_DEPTID = HR_DEPARTMENT.DEPTID " 
			+ "  AND AR_SUPERVISOR_INFO.AR_SUPERVISOR_ID = ? " 
			+ "  AND (HR_EMPLOYEE.EMPID LIKE ? "
			+ "       OR HR_EMPLOYEE.CHINESENAME LIKE ? " 
			+ "       OR HR_EMPLOYEE.CHINESE_PINYIN LIKE ? " 
			+ "       OR HR_EMPLOYEE.DEPTID = ? " 
			+ "       OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
			+ " UNION "
			+ " SELECT EMP.EMPID, EMP.Date_Started "
			+ "  FROM HR_EMPLOYEE EMP, HR_DEPARTMENT DEP "
			+ "  WHERE EMP.DEPTID = DEP.DEPTID "
			+ "  AND(EMP.EMPID LIKE ? OR EMP.CHINESENAME LIKE ? OR EMP.CHINESE_PINYIN LIKE ? ) "
			+ "  AND EMP.DEPTID " 
			+ "      IN (SELECT B.ADMIN_DEPTID FROM SY_ADMIN_DEPTID B, SY_ADMIN C WHERE B.ADMIN_NO = C.ADMINNO AND C.USERNAME = ?)"
		  + ") b on a.工号 = b.EMPID" 
		  + " where a.年 = ? order by a.工号";
		SqlParameter[] params = new SqlParameter[11];
		params[0] = new SqlParameter(ParameterType.STRING, adminId);
		params[1] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[2] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[3] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[4] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[5] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[6] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[7] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[8] = new SqlParameter(ParameterType.STRING, searchcontent + "%");
		params[9] = new SqlParameter(ParameterType.STRING, adminId);
		params[10] = new SqlParameter(ParameterType.INTEGER, year);
		try {
		  return JdbcUtil.executeQuery(sql, params);
		} catch (SQLException ex){
			ex.printStackTrace();
			throw new GlRuntimeException("查询员工事假触发数据库异常！");
		}
	}
	/**
	 * 事假查看
	 * @param adminId
	 * @param year
	 * @return
	 */
	public SQLResult getAffairLeave(String adminId, Integer year){
		String sql = 
			"select a.工号, a.姓名, a.部门, b.Date_Started, a.事假"
			+ " from ar_affair_leave_v a join HR_EMPLOYEE b on a.工号 = b.EMPID"
			  + " where a.工号 = ? and a.年 = ? ";
		SqlParameter[] params = new SqlParameter[2];
		params[0] = new SqlParameter(ParameterType.STRING, adminId);
		params[1] = new SqlParameter(ParameterType.INTEGER, year);
		try {
			  return JdbcUtil.executeQuery(sql, params);
			} catch (SQLException ex){
				ex.printStackTrace();
				throw new GlRuntimeException("查询员工事假触发数据库异常！");
			}
	}

}
