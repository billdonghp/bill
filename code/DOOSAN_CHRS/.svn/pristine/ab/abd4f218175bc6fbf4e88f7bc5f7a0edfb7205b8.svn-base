package com.ait.ar.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Condition;
import com.ait.ar.dao.ConditionDAO;
import com.ait.ar.dao.DBConnection;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

public class ConditionDAOImpl implements ConditionDAO {

	private String loginID = null;

	private DBConnection db = new DBConnection();

	public ConditionDAOImpl() {
	}

	//设定登陆人ID
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * getOptions
	 * <br>
	 * 根据输入的条件号，找到关联于此条件的所有可能值。
	 * @param conditionNo int
	 * @return List
	 */
	public List getOptions(int conditionNo,String cpny_id){
		List list = new ArrayList();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sql = findTableAndField(conditionNo, cpny_id);
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				Condition condition = new Condition();
				condition.setField_id(rs.getString(1));
				list.add(condition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
	}

	//甬兴，用于显示选择加入动态组的用户列表
	public List getOptions(int conditionNo, String language,String cpny_id) {
		String sqlString = null;
		List returnList = null;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		//使用工号选择
		sqlString = "SELECT EMPID, CHINESENAME, PINYINNAME, DEPTID, DEPARTMENT, PERSON_ID FROM AR_EMP_INFO_V where cpny_id="+ cpny_id +" ORDER BY DEPTID,CHINESENAME";
		Logger.getLogger(getClass()).debug(sqlString);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sqlString);
			if (rs != null)
				returnList = new ArrayList();
			while (rs.next()) {
				if (language.equals("zh")) {
					returnList.add(rs.getString("EMPID") + "|"
							+ rs.getString("CHINESENAME") + "|"
							+ rs.getString("DEPARTMENT") + "|"
							+ rs.getString("PERSON_ID"));
				} else {
					returnList.add(rs.getString("EMPID") + "|"
							+ rs.getString("CHINESENAME") + "|"
							+ rs.getString("DEPARTMENT") + "|"
							+ rs.getString("PERSON_ID"));
				}
			}
		} catch (SQLException sqlex) {
			Logger.getLogger(getClass()).error(sqlex.toString());
		} finally {
			try {
				rs.close();
				state.close();
				conn.close();
			} catch (NullPointerException npex) {
				Logger.getLogger(getClass()).error(npex.toString());
			} catch (SQLException sqlex) {
				Logger.getLogger(getClass()).error(sqlex.toString());
			}
		}
		return returnList;
	}

	private String findTableAndField(int conditionNo ,String cpny_id) {
		String sss = null;
		try {
			sss = db.getArPopedom(loginID);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();
		sql.append(
								"SELECT " + 
									"FIELD_ID, " +
									"FIELD_NAME," + 
									"TABLE_NAME " +
								"FROM AR_CONDITION_LIST " + 
								"WHERE CONDITION_NO='" + conditionNo + "' "
		);
		
		try {
			con = ConnBean.getConn();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				sql2.append(
						"SELECT DISTINCT " + rs.getString("FIELD_ID") +  
						" FROM " + rs.getString("TABLE_NAME") + " WHERE " +
						rs.getString("FIELD_ID") + " IS NOT NULL AND CPNY_ID = '" + cpny_id + "'");
				if (conditionNo == 1 || conditionNo == 2 || conditionNo == 3)
					sql2.append(
						"AND PA_HR_V.person_id IN(" + sss + ")"
				); /*联合考勤员权限*/
			}
			Logger.getLogger(getClass()).debug(sql2);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		//Logger.getLogger(getClass()).debug("return SQL : " + sql);
		return sql2.toString();
	}

	/**
	 * getAllConditions
	 * <br>
	 * 找到系统中所有提供的可供选择的条件。
	 * @param conditionNo int
	 * @return List
	 */
	public List getAllConditions(){
		List list = new ArrayList();
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = 
								"SELECT " +
									"CONDITION_NO, " +
									"FIELD_ID, " +
									"FIELD_NAME, " +
									"FIELD_EN_NAME, " +
									"TABLE_NAME " +
								"FROM AR_CONDITION_LIST " +
								"ORDER BY CONDITION_NO ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Condition condition = new Condition();
				condition.setCondition_no(rs.getInt("CONDITION_NO"));
				condition.setField_id(StringUtil.checkNull(rs.getString("FIELD_ID")));
				condition.setField_name(StringUtil.checkNull(rs.getString("FIELD_NAME")));
				condition.setField_en_name(StringUtil.checkNull(rs.getString("FIELD_EN_NAME")));
				condition.setTable_name(StringUtil.checkNull(rs.getString("TABLE_NAME")));
				list.add(condition);
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		return list;
	}

	/**
	 * getConditionMapping
	 * <br>
	 * 从系统中找到所有条件，并且找到所有条件所关联的可选择的值，将这些值与相应的条件建立关联。
	 * @return Map
	 */
	public Map getConditionMapping(){

		return null;
	}

	//获得已存在的人员列表
	public StringBuffer getExistEmpList(String groupNo, String fieldName) {
		StringBuffer returnStr = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sqlString = 
											"SELECT " +
												"VALUE1, " +
												"VALUE2 " + 
											"FROM AR_GROUP_CONDITIONS ";
		Logger.getLogger(getClass()).debug(sqlString);
		try {
			con = ConnBean.getConn();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlString);
			if (rs != null) {
				returnStr = new StringBuffer();
				while(rs.next()){
					returnStr.append(rs.getString("VALUE1") + ",");
					returnStr.append(rs.getString("VALUE2") + ",");
				}
			}
		} catch (SQLException sqlex) {
			Logger.getLogger(getClass()).error(sqlex.toString());
		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		Logger.getLogger(getClass()).debug(returnStr);
		return returnStr;
	}
}
