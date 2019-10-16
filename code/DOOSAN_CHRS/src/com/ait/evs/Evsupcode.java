package com.ait.evs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class Evsupcode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServiceLocator services;

	private static final Logger logger = Logger.getLogger(Evsupcode.class) ;
	
	private Integer pkNo;
	
	public Integer getPkNo() {
		return pkNo;
	}

	public void setPkNo(Integer pkNo) {
		this.pkNo = pkNo;
	}

	public Integer getPkNo1() {
		return pkNo1;
	}

	public void setPkNo1(Integer pkNo1) {
		this.pkNo1 = pkNo1;
	}
	private Integer pkNo1;
	
	private String SETUPCODENO;  //序列唯一

	private String CRAFT; //工种

	private String LINE;  //Line线

	private String AIRCRAFT;  //机种

	private String PROCESS; //工序
	
	private String JOBCONTENT; //作业内容

	private String SKILLTYPE; //技能类型
	
	private String TYPEOPERATION; //作业类型
	
	private String QUALIFICATION; //职业资格
	
	private String SKILLSCORE; //技能积分
	
	private String PURPOSE; //是否跨工种
	
	private String EVS_EMPID; //工号

	private String EV_PERIOD_ID; //评价区间ID

	private String EV_TYPE_ID; //被评价者类型

	private String PROFICIENCY; //熟练度分数
	
	private String SHEOPERCYQ; //是否一线通

	private String OPERATIONCOM; //作业基准书遵守分数
	
	private String QUALITYOFWORK; //作业品质分数 	

	private String STANDARDACTION;//标准动作分数
	
	private String COMPOSITE; //综合得分
	
	private String SKILLLEVEL; //技能等级
	
	private String descriptio;
	
	private String codeflag;
	
	private String deptke; //技能等级
	
	private String deptzhi;
	
	private String deptzu;
	
	public String getDescriptio() {
		return descriptio;
	}

	public void setDescriptio(String descriptio) {
		this.descriptio = descriptio;
	}
	private String REMARK; //主要问题点
	
	private String SUMSCORE; //累积积分

	private String CREATED_BY;//创建人
	
	private String CREATE_DATE; //创建日期
	
	private String CONFIRMDATE; //复审日期
	
	private String PROVEBY; //认证人
	
	private String PROVEDATE; //认证时间
	
	private String STATUS;
	
	
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
	
	
	

	public Evsupcode() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Evsupcode(String CRAFT, String LINE,
			String AIRCRAFT, String PROCESS,String JOBCONTENT, String SKILLTYPE, String TYPEOPERATION,  String QUALIFICATION, String SKILLSCORE, String PURPOSE,String EVS_EMPID,
			String EV_PERIOD_ID, String EV_TYPE_ID,String PROFICIENCY,String SHEOPERCYQ, String OPERATIONCOM, String QUALITYOFWORK, String STANDARDACTION,
			String COMPOSITE, String SKILLLEVEL,String REMARK) {
			this.CRAFT = CRAFT;
			this.LINE = LINE;
			this.AIRCRAFT = AIRCRAFT;
			this.PROCESS = PROCESS;
			this.JOBCONTENT = JOBCONTENT;
			this.SKILLTYPE = SKILLTYPE;
			this.TYPEOPERATION = TYPEOPERATION;
			this.QUALIFICATION = QUALIFICATION;
			this.SKILLSCORE = SKILLSCORE;
			this.PURPOSE = PURPOSE;
			this.EVS_EMPID = EVS_EMPID;
			this.EV_PERIOD_ID = EV_PERIOD_ID;
			this.EV_TYPE_ID = EV_TYPE_ID; 
			this.PROFICIENCY = PROFICIENCY;
			this.SHEOPERCYQ = SHEOPERCYQ;
			this.OPERATIONCOM = OPERATIONCOM;
			this.QUALITYOFWORK = QUALITYOFWORK;
			this.STANDARDACTION = STANDARDACTION;
			this.COMPOSITE = COMPOSITE;
			this.SKILLLEVEL = SKILLLEVEL;
			this.REMARK = REMARK;
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 保存评价信息 以前评价分数
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public void addEvsetupCode() throws SQLException, DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String ADD_EV_SETUCODE = " INSERT INTO evs_setupcode(SETUPCODENO,CRAFT,LINE,AIRCRAFT,PROCESS,JOBCONTENT,SKILLTYPE,TYPEOPERATION,QUALIFICATION,SKILLSCORE,PURPOSE," +
				                       " EVS_EMPID,EV_PERIOD_ID,EV_TYPE_ID,PROFICIENCY,SHEOPERCYQ,OPERATIONCOM,QUALITYOFWORK,STANDARDACTION," +
				                       " COMPOSITE,SKILLLEVEL,REMARK) VALUES(EVS_CODE_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			int i=0;
			pstmt = con.prepareStatement(ADD_EV_SETUCODE);
			pstmt.setString(++i, this.CRAFT);
			pstmt.setString(++i, this.LINE);
			pstmt.setString(++i, this.AIRCRAFT);
			pstmt.setString(++i, this.PROCESS);
			pstmt.setString(++i, this.JOBCONTENT);
			pstmt.setString(++i, this.SKILLTYPE);
			pstmt.setString(++i, this.TYPEOPERATION);
			pstmt.setString(++i, this.QUALIFICATION);
			pstmt.setString(++i, this.SKILLSCORE);
			pstmt.setString(++i, this.PURPOSE);
			pstmt.setString(++i, this.EVS_EMPID);
			pstmt.setString(++i, this.EV_PERIOD_ID);
			pstmt.setString(++i, this.EV_TYPE_ID);
			pstmt.setString(++i, this.PROFICIENCY);
			pstmt.setString(++i, this.SHEOPERCYQ);
			pstmt.setString(++i, this.OPERATIONCOM);
			pstmt.setString(++i, this.QUALITYOFWORK);
			pstmt.setString(++i, this.STANDARDACTION);
			pstmt.setString(++i, this.COMPOSITE);
			pstmt.setString(++i, this.SKILLLEVEL);
			pstmt.setString(++i, this.REMARK); 
			pstmt.executeUpdate();
			//this.addEvsPeriodType(con);// 
			con.commit();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addPeriod exception; ", ex);
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for adding addPeriod");
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	
	
	/**
	 *  提交评价成绩更新状态设置
	 * @throws DataAccessException
	 */
	public void updateEvsSetupcodeById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE evs_setupcode t  set  status ='2',	 "
			+"t.updated_by = ? ,update_date =  sysdate WHERE SETUPCODENO=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			
					
			pstmt.setString(++i, this.CREATED_BY);
			pstmt.setString(++i, this.SETUPCODENO);
			
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
	 *  提交工匠技师更新状态设置
	 * @throws DataAccessException
	 */
	public void updateEvsGjjsRemarkById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE EVS_GXJSDJ t  set  status ='2',	 "
			+"t.updated_by = ? ,update_date =  sysdate WHERE CODE_NO=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			
					
			pstmt.setString(++i, this.CREATED_BY);
			pstmt.setString(++i, this.SETUPCODENO);
			
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
	 * 
	 * 删除评价信息 以前评价分数
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	private final static String DEL_EV_SETUCODE = " DELETE evs_setupcode t WHERE t.evs_empid=? and t.ev_period_id=? and t.ev_type_id=?";
	 public void DelEvsetupCode(String ev_emp_id,String ev_period_id,String ev_type_id) throws DataAccessException {
			PreparedStatement pstmt = null;
			Connection con = null;
			
			try {
				con = services.getConnection();
				pstmt = con.prepareStatement(DEL_EV_SETUCODE);
				pstmt.setString(1, ev_emp_id);
				pstmt.setString(2, ev_period_id);
				pstmt.setString(3, ev_type_id);
				pstmt.executeUpdate();
			} catch (SQLException ex) {
				try {
					con.rollback();
				} catch (SQLException e) {
				}
				ex.printStackTrace();
				throw new DataAccessException("cant  delEvPeriod exception; ", ex);

			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			} finally {
				SqlUtil.close(null, pstmt);
			}

		}
	
	 private final static String GET_EVEMP_SETUPCODE = "select t.ev_emp_id, t.ev_emp_name, t.ev_dept_id, t.ev_dept_name, t.ev_period_id, " +
	 		" t.ev_type_id, t.ev_process_id,  r.setupcodeno, r.craft as CRAFT, r.skilltype AS SKILLTYPE, r.line AS LINE,  r.aircraft AS AIRCRAFT, " +
	 		" r.process AS PROCESS, r.jobcontent AS JOBCONTENT, r.typeoperation AS TYPEOPERATION,r.proficiency AS PROFICIENCY," +
	 		" r.sheopercyq AS SHEOPERCYQ,r.operationcom AS OPERATIONCOM, r.qualityofwork AS QUALITYOFWORK, r.standardaction AS STANDARDACTION," +
	 		" r.composite AS COMPOSITE,  r.skilllevel AS SKILLLEVAL, r.remark AS REMARK,r.QUALIFICATION,r.SKILLSCORE,r.PURPOSE from evs_emp t, evs_setupcode r " +
	 		" where t.ev_period_id(+) = r.ev_period_id and t.ev_emp_id(+)  = r.evs_empid  " +
	 		" and t.ev_type_id(+)  = r.ev_type_id ";

	/**
     * 评价者中的评价类型列表
     * 
     * @return
     * @throws DataAccessException
     */
		public static List getEvSetUpCodeList() throws DataAccessException {
		List lEvSetUpcode = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
		    con = ServiceLocator.getInstance().getConnection();
		    pstmt = con.prepareStatement(GET_EVEMP_SETUPCODE);
		    rs = pstmt.executeQuery();
		    while (rs.next()) {
		    Evsupcode evsupcode = new Evsupcode();
		    evsupcode.setCRAFT("CRAFT");
		    evsupcode.setSKILLTYPE("SKILLTYPE");
		    evsupcode.setLINE("LINE");
		    evsupcode.setAIRCRAFT("AIRCRAFT");
		    evsupcode.setPROCESS("PROCESS");
		    evsupcode.setJOBCONTENT("JOBCONTENT");
		    evsupcode.setTYPEOPERATION("TYPEOPERATION");
		    evsupcode.setQUALIFICATION("QUALIFICATION");
		    evsupcode.setSKILLSCORE("SKILLSCORE");
		    evsupcode.setPURPOSE("PURPOSE");
		    evsupcode.setPROFICIENCY("PROFICIENCY");
		    evsupcode.setSHEOPERCYQ("SHEOPERCYQ");
		    evsupcode.setOPERATIONCOM("OPERATIONCOM");
		    evsupcode.setQUALITYOFWORK("QUALITYOFWORK");
		    evsupcode.setSTANDARDACTION("STANDARDACTION");
		    evsupcode.setCOMPOSITE("COMPOSITE");
		    evsupcode.setSKILLLEVEL("SKILLLEVAL");
		    evsupcode.setREMARK("REMARK");
		    lEvSetUpcode.add(evsupcode);
		    }
		} catch (SQLException sqle) {
		    sqle.printStackTrace();
		    throw new DataAccessException(
			    "cant execute query for getEvEmpTypeList statistics", sqle);
		} catch (ServiceLocatorException sle) {
		    throw new DataAccessException(
			    "cant get connection for getEvEmpTypeList statistics", sle);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    SqlUtil.close(rs, pstmt, con);
		}
		return lEvSetUpcode;
		}


	
	public String getSETUPCODENO() {
		return SETUPCODENO;
	}

	public void setSETUPCODENO(String sETUPCODENO) {
		SETUPCODENO = sETUPCODENO;
	}

	public String getCRAFT() {
		return CRAFT;
	}

	public void setCRAFT(String cRAFT) {
		CRAFT = cRAFT;
	}

	public String getLINE() {
		return LINE;
	}

	public void setLINE(String lINE) {
		LINE = lINE;
	}

	public String getAIRCRAFT() {
		return AIRCRAFT;
	}

	public void setAIRCRAFT(String aIRCRAFT) {
		AIRCRAFT = aIRCRAFT;
	}

	public String getPROCESS() {
		return PROCESS;
	}

	public void setPROCESS(String pROCESS) {
		PROCESS = pROCESS;
	}

	public String getJOBCONTENT() {
		return JOBCONTENT;
	}

	public void setJOBCONTENT(String jOBCONTENT) {
		JOBCONTENT = jOBCONTENT;
	}

	public String getSKILLTYPE() {
		return SKILLTYPE;
	}

	public void setSKILLTYPE(String sKILLTYPE) {
		SKILLTYPE = sKILLTYPE;
	}

	public String getTYPEOPERATION() {
		return TYPEOPERATION;
	}

	public void setTYPEOPERATION(String tYPEOPERATION) {
		TYPEOPERATION = tYPEOPERATION;
	}

	public String getEVS_EMPID() {
		return EVS_EMPID;
	}

	public void setEVS_EMPID(String eVSEMPID) {
		EVS_EMPID = eVSEMPID;
	}

	public String getEV_PERIOD_ID() {
		return EV_PERIOD_ID;
	}

	public void setEV_PERIOD_ID(String eVPERIODID) {
		EV_PERIOD_ID = eVPERIODID;
	}

	public String getEV_TYPE_ID() {
		return EV_TYPE_ID;
	}

	public void setEV_TYPE_ID(String eVTYPEID) {
		EV_TYPE_ID = eVTYPEID;
	}

	public String getPROFICIENCY() {
		return PROFICIENCY;
	}

	public void setPROFICIENCY(String pROFICIENCY) {
		PROFICIENCY = pROFICIENCY;
	}

	public String getOPERATIONCOM() {
		return OPERATIONCOM;
	}

	public void setOPERATIONCOM(String oPERATIONCOM) {
		OPERATIONCOM = oPERATIONCOM;
	}

	public String getQUALITYOFWORK() {
		return QUALITYOFWORK;
	}

	public void setQUALITYOFWORK(String qUALITYOFWORK) {
		QUALITYOFWORK = qUALITYOFWORK;
	}

	public String getSTANDARDACTION() {
		return STANDARDACTION;
	}

	public void setSTANDARDACTION(String sTANDARDACTION) {
		STANDARDACTION = sTANDARDACTION;
	}

	public String getCOMPOSITE() {
		return COMPOSITE;
	}

	public void setCOMPOSITE(String cOMPOSITE) {
		COMPOSITE = cOMPOSITE;
	}

	public String getSKILLLEVEL() {
		return SKILLLEVEL;
	}

	public void setSKILLLEVEL(String sKILLLEVEL) {
		SKILLLEVEL = sKILLLEVEL;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getSHEOPERCYQ() {
		return SHEOPERCYQ;
	}

	public void setSHEOPERCYQ(String sHEOPERCYQ) {
		SHEOPERCYQ = sHEOPERCYQ;
	}

	public String getQUALIFICATION() {
		return QUALIFICATION;
	}

	public void setQUALIFICATION(String qualification) {
		QUALIFICATION = qualification;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String purpose) {
		PURPOSE = purpose;
	}

	public String getSKILLSCORE() {
		return SKILLSCORE;
	}

	public void setSKILLSCORE(String skillscore) {
		SKILLSCORE = skillscore;
	}

	public String getSUMSCORE() {
		return SUMSCORE;
	}

	public void setSUMSCORE(String sUMSCORE) {
		SUMSCORE = sUMSCORE;
	}

	public String getCREATED_BY() {
		return CREATED_BY;
	}

	public void setCREATED_BY(String cREATEDBY) {
		CREATED_BY = cREATEDBY;
	}

	public String getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(String cREATEDATE) {
		CREATE_DATE = cREATEDATE;
	}

	public String getCONFIRMDATE() {
		return CONFIRMDATE;
	}

	public void setCONFIRMDATE(String cONFIRMDATE) {
		CONFIRMDATE = cONFIRMDATE;
	}

	public String getPROVEBY() {
		return PROVEBY;
	}

	public void setPROVEBY(String pROVEBY) {
		PROVEBY = pROVEBY;
	}

	public String getPROVEDATE() {
		return PROVEDATE;
	}

	public void setPROVEDATE(String pROVEDATE) {
		PROVEDATE = pROVEDATE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
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

	public String getCodeflag() {
		return codeflag;
	}

	public void setCodeflag(String codeflag) {
		this.codeflag = codeflag;
	}

	public String getDeptke() {
		return deptke;
	}

	public void setDeptke(String deptke) {
		this.deptke = deptke;
	}

	public String getDeptzhi() {
		return deptzhi;
	}

	public void setDeptzhi(String deptzhi) {
		this.deptzhi = deptzhi;
	}

	public String getDeptzu() {
		return deptzu;
	}

	public void setDeptzu(String deptzu) {
		this.deptzu = deptzu;
	}



}