package com.ait.etn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.PageControl;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class EtnGroupWhole {
	private String etnEduItemCode;

	private String etnEduItemName;

	private String etnPlanClassType;

	private String etnPlanCode;

	private String etnFitGroup;

	private String fitDepartmentId;

	private String fitDepartmentName;

	private String etnGroupWholeNO;

	private String remark;

	private String manager ;

	public EtnGroupWhole() {
	}

	public void addEtnGroupWhole(EtnGroupWhole etngroupwhole) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// select_sql 的作用是先校验本次添加的信息是否为重复的信息
		String select_sql = " SELECT  COUNT(*) RESULT FROM  ETN_PLAN_KINDS WHERE PLAN_CODE = ? ";

		String sql = " INSERT INTO ETN_PLAN_KINDS (" + "SEQ_PLAN_KINDS, "
				+ "PLAN_EDU_ITEM_CODE, " + "PLAN_CLASS_TYPE_CODE, "
				+ "PLAN_CODE,ETN_FIT_GROUP,REMARK)VALUES("
				+ "ETN_PLAN_KINDS_SEQ.NEXTVAL, " + "'"
				+ etngroupwhole.getEtnEduItemCode() + "', " + "'"
				+ etngroupwhole.getEtnPlanClassType() + "', " + "'"
				+ etngroupwhole.getEtnPlanCode() + "', " + "'"
				+ etngroupwhole.getEtnFitGroup() + "', " + "'"
				+ etngroupwhole.getRemark() + "'" + ")";
		Logger.getLogger(getClass()).debug("查询SQL " + select_sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setString(1, etngroupwhole.getEtnPlanCode().trim());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (Integer.parseInt(rs.getString("RESULT")) == 0) {
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					etngroupwhole.setMessager("添加成功！");
					Logger.getLogger(getClass()).debug(sql);
				} else {
					etngroupwhole.setMessager("添加失败！课程类型编号已存在，请检查！");
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public int addFitDepartment(String[] deptId, String planCode) {
		int affRow = 0;
		Connection conn = null;
		Statement state = null;
		String sql = null;
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			for (int i = 0; i < deptId.length; i++) {
				sql = "INSERT INTO ETN_YEARPLAN_FIT_DEPT(" + "PLAN_CODE, "
						+ "FIT_DEPARTMENT_ID" + ")VALUES (" + "'" + planCode
						+ "', " + "'" + deptId[i] + "'" + ")";
				state.addBatch(sql);
			}
			int[] affRowArray = state.executeBatch();
			for (int i = 0; i < affRowArray.length; i++) {
				affRow += affRowArray[i];
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(state, conn);
		}
		return affRow;
	}

	public Vector getEtnGroupWhole(PageControl pc) {
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ETN_PLAN_KINDS_V ORDER BY PLAN_CODE asc ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pc.seti();
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();
			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) {
				vector.add(this.createEtnGroupWhole(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public EtnGroupWhole getEtnGroupWholeByPlanCode(String plancode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ETN_PLAN_KINDS_V where  PLAN_CODE =? ORDER BY SEQ_PLAN_KINDS DESC ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, plancode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return this.createEtnGroupWhole(rs);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return null;
	}

	public Vector getEtnGroupWhole() {
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ETN_PLAN_KINDS_V ORDER BY SEQ_PLAN_KINDS DESC ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vector.add(this.createEtnGroupWhole(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	private EtnGroupWhole createEtnGroupWhole(ResultSet rs) {
		EtnGroupWhole etngroupwhole = new EtnGroupWhole();
		try {
			etngroupwhole
					.setEtnEduItemCode(rs.getString("PLAN_EDU_ITEM_CODE") != null ? rs
							.getString("PLAN_EDU_ITEM_CODE")
							: "");
			etngroupwhole
					.setEtnEduItemName(rs.getString("PLAN_EDU_ITEM_NAME") != null ? rs
							.getString("PLAN_EDU_ITEM_NAME")
							: "");
			etngroupwhole
					.setEtnFitGroup(rs.getString("ETN_FIT_GROUP") != null ? rs
							.getString("ETN_FIT_GROUP") : "");
			etngroupwhole.setEtnPlanClassType(rs
					.getString("PLAN_CLASS_TYPE_CODE") != null ? rs
					.getString("PLAN_CLASS_TYPE_CODE") : "");
			etngroupwhole.setEtnPlanCode(rs.getString("PLAN_CODE") != null ? rs
					.getString("PLAN_CODE") : "");
			etngroupwhole.setRemark(rs.getString("REMARK") != null ? rs
					.getString("REMARK") : "");
			etngroupwhole.setMessager(this.manager);
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return etngroupwhole;
	}

	public Vector getFitDepartment(String plancode) {
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select DEPTNAME,fit_department_id from ETN_YEARPLAN_FIT_DEPT eyfd,HR_DEPARTMENT hd where eyfd.FIT_DEPARTMENT_ID = hd.DEPTID(+) and plan_code = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, plancode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtnGroupWhole etngroupwhole = new EtnGroupWhole();
				etngroupwhole.setFitDepartmentId(rs
						.getString("fit_department_id") != null ? rs
						.getString("fit_department_id") : "");
				etngroupwhole
						.setFitDepartmentName(rs.getString("DEPTNAME") != null ? rs
								.getString("DEPTNAME")
								: "");
				vector.add(etngroupwhole);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public void updateEtnGroupWhole(EtnGroupWhole etngroupwhole) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ETN_PLAN_KINDS SET PLAN_EDU_ITEM_CODE=?, PLAN_CLASS_TYPE_CODE=?, "
				+ "ETN_FIT_GROUP=? where PLAN_CODE=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, etngroupwhole.getEtnEduItemCode());
			pstmt.setString(2, etngroupwhole.getEtnPlanClassType());
			pstmt.setString(3, etngroupwhole.getEtnFitGroup());
			pstmt.setString(4, etngroupwhole.getEtnPlanCode());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteFitDepartment(String planCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE ETN_YEARPLAN_FIT_DEPT WHERE PLAN_CODE = '"
				+ planCode + "'  ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteEtnGroupWhole(String planCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE ETN_PLAN_KINDS WHERE PLAN_CODE = '" + planCode
				+ "' ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public String getEtnEduItemCode() {
		return etnEduItemCode;
	}

	public void setEtnEduItemCode(String etnEduItemCode) {
		this.etnEduItemCode = etnEduItemCode;
	}

	public String getEtnEduItemName() {
		return etnEduItemName;
	}

	public void setEtnEduItemName(String etnEduItemName) {
		this.etnEduItemName = etnEduItemName;
	}

	public String getEtnPlanClassType() {
		return etnPlanClassType;
	}

	public void setEtnPlanClassType(String etnPlanClassType) {
		this.etnPlanClassType = etnPlanClassType;
	}

	public String getEtnPlanCode() {
		return etnPlanCode;
	}

	public void setEtnPlanCode(String etnPlanCode) {
		this.etnPlanCode = etnPlanCode;
	}

	public String getEtnFitGroup() {
		return etnFitGroup;
	}

	public void setEtnFitGroup(String etnFitGroup) {
		this.etnFitGroup = etnFitGroup;
	}

	public String getFitDepartmentId() {
		return fitDepartmentId;
	}

	public void setFitDepartmentId(String fitDepartmentId) {
		this.fitDepartmentId = fitDepartmentId;
	}

	public String getFitDepartmentName() {
		return fitDepartmentName;
	}

	public void setFitDepartmentName(String fitDepartmentName) {
		this.fitDepartmentName = fitDepartmentName;
	}

	public String getEtnGroupWholeNO() {
		return etnGroupWholeNO;
	}

	public void setEtnGroupWholeNO(String etnGroupWholeNO) {
		this.etnGroupWholeNO = etnGroupWholeNO;
	}

	public void setRemark(String remark) {
		this.remark = remark; 
	}

	public String getRemark() {
		return remark;
	}

	// 辅助方法，用于提示客户添加教育体系管理的成功或失败的提示信息。
	public void setMessager(String manager) {
		this.manager = manager;
	}

	public String getMessager() {
		return this.manager;
	}
}
