package com.ait.evs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class Gxjsdj {
	
	private Integer pkNo;
	
	private Integer pkNo1;
	
	private String empID;

	private String empName;

	private String korEmpName;
	
	private String deptName;
	
	private String PJWD1;

	private String PJWD2;
	  
	private String PJWD3;

	private String PJWD4;

	private String SUMSTORE;
	
	private String codeid;
	
	private Integer itemNo;

	private String status;
		
	private String createBy ;
	
	private String createDate ;	
	
	private String uempid ;
	
	private String updatetime ;
	
	public String getUempid() {
		return uempid;
	}

	public void setUempid(String uempid) {
		this.uempid = uempid;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}


	private int flag;

	private String evperiodid;
	
	public String getEvperiodid() {
		return evperiodid;
	}

	public void setEvperiodid(String evperiodid) {
		this.evperiodid = evperiodid;
	}


	private String remark;
	
	private Integer affirmLevel;
	
	private Integer affirm_flag;
	
	private Integer up_flag;
	
	private Integer next_flag;
	
	private Integer maxLevel_flag;
	
	private Integer max_affirm_flag;
	
	private String chineseName;
	
    private Integer opFlag = -1; // 可操作状态 -1 不可操作; 0 可通过/否决; 1 可通过; 2 可否决
	
	private ArrayList AffirmorList; // 决裁者列表

	private Integer affirmorCount; // 决裁者数量

	private static ServiceLocator services;
	
	public Gxjsdj() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据工种取得相应匠师
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsGxjsDJCode(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT  t.* " 
					      + " FROM EVS_GXJSDJ T  WHERE status!='0'   " 
					      + "   ";
		if (!code_name1.trim().equals("") || !code_name.trim().equals("")) {
			if(!code_name1.equals("") && code_name1 != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.EV_PERIOD_ID ='" + code_name1 + "'";
			}
			if(!code_name.equals("") && code_name != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.code_ID='" + code_name + "'";
			}
		}
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsGxjsdj(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}	
	public List getEvsGxjsDJCodes(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT  t.* " 
					      + " FROM EVS_GXJSDJ T  WHERE status ='3'   " 
					      + "   ";
		if (!code_name1.trim().equals("") || !code_name.trim().equals("")) {
			if(!code_name1.equals("") && code_name1 != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.EV_PERIOD_ID ='" + code_name1 + "'";
			}
			if(!code_name.equals("") && code_name != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.code_ID='" + code_name + "'";
			}
		}
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsGxjsdj(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}	
	/**
	 * 取得相应匠师评价规则
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsGxjsDJRemark(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT  t.* " 
					      + " FROM EVS_GJJSDJ_remark T   " 
					      + "   ";
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsGxjsdjRemark(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}	
	/**
	 *  更新工匠技师设置
	 * @throws DataAccessException
	 */
	public void updateEvsGxjsdjById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE EVS_GXJSDJ t  set  t.PJWD1 = ? ,t.PJWD2 = ? ,t.PJWD3 = ? ,t.PJWD4 = ? ,t.SUMSTORE = ? ,"
			+"t.updated_by = ? ,update_date =  sysdate WHERE t.code_no=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			
			
			pstmt.setString(++i, this.PJWD1);
			pstmt.setString(++i, this.PJWD2);
			pstmt.setString(++i, this.PJWD3);
			pstmt.setString(++i, this.PJWD4);
			pstmt.setString(++i, this.SUMSTORE);
			pstmt.setString(++i, this.uempid);
			pstmt.setInt(++i, this.codeNo);
			pstmt.executeUpdate();

			con.commit();
			//this.addEvsPeriodType();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvTypeByCode statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvTypeByCode statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	/**
	 *  更新工匠技师设置
	 * @throws DataAccessException
	 */
	public void updateEvsGxjsdjRemark() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE EVS_GJJSDJ_remark t  set  t.Remark = ? ,"
			+"t.updated_by = ? ,update_date =  sysdate  ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			
			
			pstmt.setString(++i, this.remark);
			pstmt.setString(++i, this.uempid);
			
			pstmt.executeUpdate();

			con.commit();
			//this.addEvsPeriodType();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvTypeByCode statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvTypeByCode statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	/**
	 * 初始化
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public Gxjsdj createEvsGxjsdj(ResultSet rs) throws DataAccessException {
		Gxjsdj levsPeriod = new Gxjsdj();
		try {
			levsPeriod.setCodeNo(rs.getInt("code_no"));
			levsPeriod.setEmpName((rs.getString("CHINESENAME") != null) ? rs.getString("CHINESENAME") : "");
			levsPeriod.setCodeid((rs.getString("code_id") != null) ? rs.getString("code_id") : "");
			levsPeriod.setPJWD1((rs.getString("PJWD1") != null) ? rs.getString("PJWD1"): "");
			levsPeriod.setPJWD2((rs.getString("PJWD2") != null) ? rs.getString("PJWD2"): "");
			levsPeriod.setPJWD3((rs.getString("PJWD3") != null) ? rs.getString("PJWD3"): "");
			levsPeriod.setPJWD4((rs.getString("PJWD4") != null) ? rs.getString("PJWD4"): "");
			levsPeriod.setSUMSTORE((rs.getString("SUMSTORE") != null) ? rs.getString("SUMSTORE"): "");
			levsPeriod.setEvperiodid((rs.getString("EV_PERIOD_ID") != null) ? rs.getString("EV_PERIOD_ID"): "");
			
			levsPeriod.setCreateBy((rs.getString("CREATED_BY") != null) ? rs.getString("CREATED_BY"): "");
			levsPeriod.setCreateDate((rs.getString("create_date") != null) ? rs.getString("create_date"): "");
			levsPeriod.setEmpID((rs.getString("EMPID") != null) ? rs.getString("EMPID"): "");
			levsPeriod.setUpdatetime((rs.getString("update_date") != null) ? rs.getString("update_date"): "");
			levsPeriod.setUempid((rs.getString("updated_by") != null) ? rs.getString("updated_by"): "");
			levsPeriod.setStatus((rs.getString("status") != null) ? rs.getString("status"): "");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return levsPeriod;
	}
	
	public Gxjsdj createEvsGxjsdjRemark(ResultSet rs) throws DataAccessException {
		Gxjsdj levsPeriod = new Gxjsdj();
		try {
			
			levsPeriod.setRemark((rs.getString("REMARK") != null) ? rs.getString("REMARK") : "");
			levsPeriod.setEvperiodid((rs.getString("EV_PERIOD_ID") != null) ? rs.getString("EV_PERIOD_ID"): "");
			
			levsPeriod.setCreateBy((rs.getString("CREATED_BY") != null) ? rs.getString("CREATED_BY"): "");
			levsPeriod.setCreateDate((rs.getString("create_date") != null) ? rs.getString("create_date"): "");
	
			levsPeriod.setUpdatetime((rs.getString("update_date") != null) ? rs.getString("update_date"): "");
			levsPeriod.setUempid((rs.getString("updated_by") != null) ? rs.getString("updated_by"): "");
			levsPeriod.setStatus((rs.getString("status") != null) ? rs.getString("status"): "");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return levsPeriod;
	}

	public int codeNo = 0;
	public int getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(int codeNo) {
		this.codeNo = codeNo;
	}
	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	public String getKorEmpName() {
		return korEmpName;
	}

	public void setKorEmpName(String korEmpName) {
		this.korEmpName = korEmpName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	

	public Integer getPkNo() {
		return pkNo;
	}

	public void setPkNo(Integer pkNo) {
		this.pkNo = pkNo;
	}




	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPJWD1() {
		return PJWD1;
	}

	public void setPJWD1(String pJWD1) {
		PJWD1 = pJWD1;
	}

	public String getPJWD2() {
		return PJWD2;
	}

	public void setPJWD2(String pJWD2) {
		PJWD2 = pJWD2;
	}

	public String getPJWD3() {
		return PJWD3;
	}

	public void setPJWD3(String pJWD3) {
		PJWD3 = pJWD3;
	}

	public String getPJWD4() {
		return PJWD4;
	}

	public void setPJWD4(String pJWD4) {
		PJWD4 = pJWD4;
	}

	public String getSUMSTORE() {
		return SUMSTORE;
	}

	public void setSUMSTORE(String sUMSTORE) {
		SUMSTORE = sUMSTORE;
	}

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}

	public Integer getPkNo1() {
		return pkNo1;
	}

	public void setPkNo1(Integer pkNo1) {
		this.pkNo1 = pkNo1;
	}

	public Integer getAffirmLevel() {
		return affirmLevel;
	}

	public void setAffirmLevel(Integer affirmLevel) {
		this.affirmLevel = affirmLevel;
	}

	public Integer getAffirm_flag() {
		return affirm_flag;
	}

	public void setAffirm_flag(Integer affirmFlag) {
		affirm_flag = affirmFlag;
	}

	public Integer getUp_flag() {
		return up_flag;
	}

	public void setUp_flag(Integer upFlag) {
		up_flag = upFlag;
	}

	public Integer getNext_flag() {
		return next_flag;
	}

	public void setNext_flag(Integer nextFlag) {
		next_flag = nextFlag;
	}

	public Integer getMaxLevel_flag() {
		return maxLevel_flag;
	}

	public void setMaxLevel_flag(Integer maxLevelFlag) {
		maxLevel_flag = maxLevelFlag;
	}

	public Integer getMax_affirm_flag() {
		return max_affirm_flag;
	}

	public void setMax_affirm_flag(Integer maxAffirmFlag) {
		max_affirm_flag = maxAffirmFlag;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public ArrayList getAffirmorList() {
		return AffirmorList;
	}

	public void setAffirmorList(ArrayList affirmorList) {
		AffirmorList = affirmorList;
	}

	public Integer getAffirmorCount() {
		return affirmorCount;
	}

	public void setAffirmorCount(Integer affirmorCount) {
		this.affirmorCount = affirmorCount;
	}

	
}
