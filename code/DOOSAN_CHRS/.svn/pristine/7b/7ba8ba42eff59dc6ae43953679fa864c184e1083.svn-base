package com.ait.etn;

import java.sql.*;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.util.*;
import com.ait.utils.ConnBean;

public class EtnPlan {
	private String etnPlanItemCode;
	private String etnPlanClassType;
	private String etnPlanPerrior;
	private String etnPlanEduType;
	private String etnPlanEduPlace;
	private String etnPlanStartDate;
	private String etnPlanEndDate;
	private String etnPlanEduGroup;
	private String etnPlanFactGroup;
	private String etnPlanEduTime;
	private String etnPlanColor;
	private String etnPlanAppraise;
	private String etnPlanItemName;
	private String etnPlanEduTypeName;
	private String etnPlanNo;
	private String etnPlanCode;
	private String etnPlanChineseName;
	private String etnPlanEmpId;
	private String yearPlanNO;
	private String teacherNo;
	private String teacherName;
	private String teacherFeeHour;
	private String etnDepartment;
	private String etnPosition;
	private String etnPlanSex;
	private Integer etnPlanEduTimeDanWei;

	private String hegelv;
	private String bukaotongguolv;
	private String tuisherenshu;

	public EtnPlan() {
	}

	public String getHegelv() {
		return hegelv;
	}

	public String getBukaotongguolv() {
		return bukaotongguolv;
	}

	public String getTuisherenshu() {
		return tuisherenshu;
	}

	public void setHegelv(String hegelv) {
		this.hegelv = hegelv;
	}

	public void setBukaotongguolv(String bukaotongguolv) {
		this.bukaotongguolv = bukaotongguolv;
	}

	public void setTuisherenshu(String tuisherenshu) {
		this.tuisherenshu = tuisherenshu;
	}

	public void deleteEmpFact(String etnplanno, String delFact[]) {
		Connection conn;
		PreparedStatement pstmt;
		String sql;
		conn = null;
		pstmt = null;
		sql = "delete ETN_FACT_PLAN where SEQ_ETN_PLAN =? and empid=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			//System.out.println("--"+delFact.length);
			for (int i = 0; i < delFact.length; i++) {
				pstmt.setString(2, delFact[i]);
				pstmt.executeUpdate();
				//System.out.println("success");
			}

		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteEmpPlan(String etnplanno, String delPlan[]) {
		Connection conn;
		PreparedStatement pstmt;
		String sql;
		conn = null;
		pstmt = null;
		sql = "delete ETN_PLAN_GROUP where SEQ_ETN_PLAN =? and empid=?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			//System.out.println("--"+delPlan.length);
			for (int i = 0; i < delPlan.length; i++) {
				pstmt.setString(2, delPlan[i]);
				pstmt.executeUpdate();
				//System.out.println("success");
			}

		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void addEtnPlan(EtnPlan etnplan){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_PLAN (SEQ_ETN_PLAN, PLAN_EDU_ITEM_CODE, PLAN_CLASS_TYPE, " + "PLAN_EDU_TYPE_CODE, PLAN_EDU_PLACE, " + " PLAN_START_DATE, PLAN_END_DATE, PLAN_EDU_TIME, "
				+ "PLAN_APPRAISE_CONTENT,PLAN_CODE,YEAR_PLAN_NO,PLAN_EDU_TIME_DANWEI) " + " values (ETN_PLAN_SEQ.NEXTVAL,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, etnplan.getEtnPlanItemCode());
			pstmt.setString(2, etnplan.getEtnPlanClassType());
			pstmt.setString(3, etnplan.getEtnPlanEduType());
			pstmt.setString(4, etnplan.getEtnPlanEduPlace());
			pstmt.setString(5, etnplan.getEtnPlanStartDate());
			pstmt.setString(6, etnplan.getEtnPlanEndDate());
			pstmt.setString(7, etnplan.getEtnPlanEduTime());
			pstmt.setString(8, etnplan.getEtnPlanAppraise());
			pstmt.setString(9, etnplan.getEtnPlanCode());
			pstmt.setString(10, etnplan.getYearPlanNO());
			pstmt.setInt(11, etnplan.getEtnPlanEduTimeDanWei().intValue());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void addEtnPlanTeacher(String etnplanno, String[] teacherno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_PLAN_TEACHER (ETN_PLAN_NO, TEACHER_NO) values(?,?)";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			// System.out.println("-add teacher"+teacherno.length);
			for (int i = 0; i < teacherno.length; i++) {
				pstmt.setInt(2, Integer.parseInt(teacherno[i]));
				pstmt.executeUpdate();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public Vector getEtnPlanTeacher(String etnplanseq){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select ETN_PLAN_NO, TEACHER_NO ,TEACHER_NAME, OUT_TEACHER_FEE " + " from ETN_PLAN_TEACHER_V where  ETN_PLAN_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, etnplanseq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtnPlan etnplan = new EtnPlan();
				etnplan.setEtnPlanNo(rs.getString("ETN_PLAN_NO") != null ? rs.getString("ETN_PLAN_NO") : "");
				etnplan.setTeacherNo(rs.getString("TEACHER_NO") != null ? rs.getString("TEACHER_NO") : "");
				etnplan.setTeacherName(rs.getString("TEACHER_NAME") != null ? rs.getString("TEACHER_NAME") : "");
				etnplan.setTeacherFeeHour(rs.getString("OUT_TEACHER_FEE") != null ? rs.getString("OUT_TEACHER_FEE") : "");
				vector.add(etnplan);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public Vector getEtnPlan(){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ETN_PLAN_V order by SEQ_ETN_PLAN desc";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vector.add(this.createEtnPlan(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public Vector getEtnPlan(PageControl pc){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ETN_PLAN_V order by PLAN_START_DATE desc";
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
				vector.add(this.createEtnPlan(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public Vector getEtnPlanByToday(String today){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ETN_PLAN_V where PLAN_START_DATE<=to_date(?,'yyyy-mm-dd') and  PLAN_END_DATE>=to_date(?,'yyyy-mm-dd') " + " order by SEQ_ETN_PLAN desc";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, today);
			pstmt.setString(2, today);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vector.add(this.createEtnPlan(rs));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public EtnPlan getEtnPlanByNo(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ETN_PLAN_V where SEQ_ETN_PLAN =? order by SEQ_ETN_PLAN desc";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return this.createEtnPlan(rs);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return null;
	}

	//2005-11-9重载

	public EtnPlan getEtnPlanByNo(String etnplanno, boolean b){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		EtnPlan etnplan = null;
		String sql = "select * from ETN_PLAN_V where SEQ_ETN_PLAN =" + etnplanno + " order by SEQ_ETN_PLAN desc";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				etnplan = this.createEtnPlan(rs);

			/**		
			 （1）合格率=所有考试成绩/人数
			 （2）补考通过率=所有补考成绩/补考人数
			 （3）退社人数=所有成绩为D的人--即分数低于60的人
			 TO_CHAR(199.999999999,'999.99')
			 */
			if (b) {

				sql = "select TO_CHAR((SUM(exam_score)/count(EMPID)),'999.99') AS R FROM " + "(SELECT   SEQ_ETN_PLAN   ,EMPID ,exam_score  FROM ETN_FACT_PLAN " + " union "
						+ " SELECT   e.PLAN_NO  ,TO_CHAR(e.PNO),o.exam_score  " + " FROM  ETN_PLAN_OTHER e ,ETN_OPSCORE o where e.pno=o.pno) " + " GROUP BY SEQ_ETN_PLAN having   SEQ_ETN_PLAN=" + etnplanno;
		     Logger.getLogger(getClass()).debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next())
					etnplan.setHegelv(rs.getString("R"));
				sql = "select TO_CHAR((SUM(makeup_score)/count(EMPID)),'999.99') AS R FROM " + "(SELECT   SEQ_ETN_PLAN   ,EMPID ,makeup_score  FROM ETN_FACT_PLAN " + " union "
						+ " SELECT   e.PLAN_NO  ,TO_CHAR(e.PNO),o.makeup_score  " + " FROM  ETN_PLAN_OTHER e ,ETN_OPSCORE o where e.pno=o.pno) " + " GROUP BY SEQ_ETN_PLAN having   SEQ_ETN_PLAN=" + etnplanno;
		     Logger.getLogger(getClass()).debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next())
					etnplan.setBukaotongguolv(rs.getString("R"));
				sql = "SELECT COUNT(*) R  FROM " + "(SELECT   SEQ_ETN_PLAN   ,EMPID ,exam_score  FROM ETN_FACT_PLAN  " + "union  " + "SELECT   e.PLAN_NO  ,TO_CHAR(e.PNO),o.exam_score   "
						+ "FROM  ETN_PLAN_OTHER e ,ETN_OPSCORE o where e.pno=o.pno) " + "WHERE exam_score<60 AND SEQ_ETN_PLAN=" + etnplanno;
		     Logger.getLogger(getClass()).debug(sql);
				rs = stmt.executeQuery(sql);
				if (rs.next())
					etnplan.setTuisherenshu(rs.getString("R"));

			}

			return etnplan;

		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return null;
	}

	private EtnPlan createEtnPlan(ResultSet rs){
		EtnPlan etnplan = new EtnPlan();
		try {
			etnplan.setEtnPlanNo(rs.getString("SEQ_ETN_PLAN") != null ? rs.getString("SEQ_ETN_PLAN") : "");
			etnplan.setEtnPlanItemCode(rs.getString("PLAN_EDU_ITEM_CODE") != null ? rs.getString("PLAN_EDU_ITEM_CODE") : "");
			etnplan.setEtnPlanItemName(rs.getString("PLAN_EDU_ITEM_NAME") != null ? rs.getString("PLAN_EDU_ITEM_NAME") : "");
			etnplan.setEtnPlanClassType(rs.getString("PLAN_CLASS_TYPE") != null ? rs.getString("PLAN_CLASS_TYPE") : "");
			etnplan.setEtnPlanEduType(rs.getString("PLAN_EDU_TYPE_CODE") != null ? rs.getString("PLAN_EDU_TYPE_CODE") : "");
			etnplan.setEtnPlanEduTypeName(rs.getString("PLAN_EDU_TYPE_NAME") != null ? rs.getString("PLAN_EDU_TYPE_NAME") : "");
			etnplan.setEtnPlanEduPlace(rs.getString("PLAN_EDU_PLACE") != null ? rs.getString("PLAN_EDU_PLACE") : "");
			etnplan.setEtnPlanStartDate(rs.getString("PLAN_START_DATE") != null ? rs.getString("PLAN_START_DATE").substring(0, 10) : "");
			etnplan.setEtnPlanEndDate(rs.getString("PLAN_END_DATE") != null ? rs.getString("PLAN_END_DATE").substring(0, 10) : "");
			etnplan.setEtnPlanEduTime(rs.getString("PLAN_EDU_TIME") != null ? rs.getString("PLAN_EDU_TIME") : "");
			etnplan.setEtnPlanAppraise(rs.getString("PLAN_APPRAISE_CONTENT") != null ? rs.getString("PLAN_APPRAISE_CONTENT") : "");
			etnplan.setEtnPlanCode(rs.getString("PLAN_CODE") != null ? rs.getString("PLAN_CODE") : "");
			etnplan.setYearPlanNO(rs.getString("YEAR_PLAN_NO") != null ? rs.getString("YEAR_PLAN_NO") : "");
			etnplan.setEtnPlanEduTimeDanWei(rs.getString("PLAN_EDU_TIME_DANWEI") != null ? Integer.valueOf(rs.getString("PLAN_EDU_TIME_DANWEI")) : Integer.valueOf("0"));
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return etnplan;
	}

	public String getEtnPlanSeq(){
		String lastNumber = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select last_number as LASTNUMBER from user_sequences where sequence_name='ETN_PLAN_SEQ'";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastNumber = rs.getString("LASTNUMBER");
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return lastNumber;
	}

	public void addPlanEmpID(String[] empid, String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_PLAN_GROUP (SEQ_ETN_PLAN, EMPID)values (?,?)";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			//System.out.println("-add"+empid.length);
			for (int i = 0; i < empid.length; i++) {
				pstmt.setString(2, empid[i]);
				pstmt.executeUpdate();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void addFactEmpID(String[] empid, String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_FACT_PLAN (SEQ_ETN_PLAN, EMPID)values (?,?)";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			//System.out.println("-add"+empid.length);
			for (int i = 0; i < empid.length; i++) {
				pstmt.setString(2, empid[i]);
				pstmt.executeUpdate();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public Vector getPlanGroup(String etnplanseq){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select EMPID, chinesename,DEPARTMENT,SEX, POSITION from ETN_PLAN_GROUP_V where SEQ_ETN_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, etnplanseq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtnPlan etnplan = new EtnPlan();
				etnplan.setEtnPlanChineseName(rs.getString("chinesename") != null ? rs.getString("chinesename") : "");
				etnplan.setEtnPlanEmpId(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
				etnplan.setEtnDepartment(rs.getString("DEPARTMENT") != null ? rs.getString("DEPARTMENT") : "");
				etnplan.setEtnPlanSex(rs.getString("SEX") != null ? rs.getString("SEX") : "");
				etnplan.setEtnPosition(rs.getString("POSITION") != null ? rs.getString("POSITION") : "");
				vector.add(etnplan);

			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public Vector getFactGroup(String etnplanseq){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select EMPID, chinesename,DEPARTMENT, POSITION,SEX from ETN_FACT_GROUP_V where SEQ_ETN_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, etnplanseq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EtnPlan etnplan = new EtnPlan();
				etnplan.setEtnPlanChineseName(rs.getString("chinesename") != null ? rs.getString("chinesename") : "");
				etnplan.setEtnPlanEmpId(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
				etnplan.setEtnDepartment(rs.getString("DEPARTMENT") != null ? rs.getString("DEPARTMENT") : "");
				etnplan.setEtnPlanSex(rs.getString("SEX") != null ? rs.getString("SEX") : "");
				etnplan.setEtnPosition(rs.getString("POSITION") != null ? rs.getString("POSITION") : "");
				vector.add(etnplan);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	/**
	 *
	 *
	 * */
	public void updateEtnPlan(EtnPlan etnplan){
		System.out.println("etnplan   in etnplan  update   " + etnplan);
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update ETN_PLAN set PLAN_EDU_ITEM_CODE=?, PLAN_CLASS_TYPE=?, " +
        " PLAN_EDU_TYPE_CODE=?, PLAN_EDU_PLACE=?, "+ 
        " PLAN_START_DATE=to_date(?,'yyyy-mm-dd'), PLAN_END_DATE=to_date(?,'yyyy-mm-dd'), PLAN_EDU_TIME=?, "+
        " PLAN_APPRAISE_CONTENT=?, PLAN_CODE=?, YEAR_PLAN_NO=?,PLAN_EDU_TIME_DANWEI=? where SEQ_ETN_PLAN=? ";
    Logger.getLogger(getClass()).debug(sql);
    Logger.getLogger("开始添加参数！！！！");
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
            Logger.getLogger("开始添加参数！！！！");
			pstmt.setString(1, etnplan.getEtnPlanItemCode());
			pstmt.setString(2, etnplan.getEtnPlanClassType());
			pstmt.setString(3, etnplan.getEtnPlanEduType());
			pstmt.setString(4, etnplan.getEtnPlanEduPlace());
			pstmt.setString(5, etnplan.getEtnPlanStartDate());
			pstmt.setString(6, etnplan.getEtnPlanEndDate());
			pstmt.setInt(7, Integer.parseInt(etnplan.getEtnPlanEduTime()));
			pstmt.setString(8, etnplan.getEtnPlanAppraise());
			pstmt.setString(9, etnplan.getEtnPlanCode());
			pstmt.setInt(10, Integer.parseInt(etnplan.getYearPlanNO()));
			pstmt.setInt(11, etnplan.getEtnPlanEduTimeDanWei().intValue());
			pstmt.setInt(12, Integer.parseInt(etnplan.getEtnPlanNo()));

			pstmt.executeUpdate();
            Logger.getLogger("成功更新！！！！");
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error("更新失败"+ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deletePlanGroup(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_PLAN_GROUP where SEQ_ETN_PLAN =?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteFactGroup(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_FACT_PLAN where SEQ_ETN_PLAN =?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteEtnPlan(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_PLAN where SEQ_ETN_PLAN =?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void updateFactGroupSatisfact(String etnplanno, String empid, String satisfactrate){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update ETN_FACT_PLAN set SATISFACT_RATE=? where SEQ_ETN_PLAN=? and  EMPID=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, satisfactrate);
			pstmt.setString(2, etnplanno);
			pstmt.setString(3, empid);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteEtnPlanTeacher(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_PLAN_TEACHER where ETN_PLAN_NO =?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public String getCountFactGroup(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) as COUNTGROUP from ETN_FACT_PLAN where SEQ_ETN_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getString("COUNTGROUP") != null ? rs.getString("COUNTGROUP") : "";
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return "";
	}

	public int getAllCountPeoples(String etnplanno){
		int r = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " select count(*) as c from ETN_FACT_PLAN where SEQ_ETN_PLAN=" + etnplanno + " union " + " select count(*) as d from ETN_PLAN_OTHER where plan_no=" + etnplanno;
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				r = r + Integer.parseInt(rs.getString("c"));
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return r;
	}

	public String getCountPlanGroup(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) as COUNTGROUP from ETN_PLAN_GROUP where SEQ_ETN_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getString("COUNTGROUP") != null ? rs.getString("COUNTGROUP") : "";
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return "";
	}

	public String getEtnPlancode(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select plan_code from ETN_PLAN_V where SEQ_ETN_PLAN=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getString("plan_code") != null ? rs.getString("plan_code") : "";
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return "";
	}

	public String getFactGroupSatisfact(String etnplanno, String empid){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select SATISFACT_RATE from ETN_FACT_PLAN where SEQ_ETN_PLAN=? and  EMPID=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.setString(2, empid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("SATISFACT_RATE");
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return "";
	}

	public void deleteEndResult(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_WHOLE_RESULT where ETN_PLAN_NO =?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteTeacherSatisfact(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_TEACHER_SATISFACT where ETN_PLAN_NO =?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void addTeacherGroupByClass(String etnplanno, String classno, String teacherno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_TEACHER_GROUP_BY_CLASS (ETN_PLAN_NO, CLASS_NO, TEACHER_NO)values(?,?,?)";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.setInt(2, Integer.parseInt(classno));
			pstmt.setInt(3, Integer.parseInt(teacherno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public Vector getEtnTeacherGroupByClass(String etnplanno, String classno){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select TEACHER_NAME from ETN_TEACHER_GROUP_V where ETN_PLAN_NO=? and CLASS_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.setString(2, classno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vector.add(rs.getString("TEACHER_NAME"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public String getEtnTeacherExist(String etnplanno, String classno, String teacherno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select TEACHER_NO from ETN_TEACHER_GROUP_V where ETN_PLAN_NO=? and CLASS_NO=? and TEACHER_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.setString(2, classno);
			pstmt.setString(3, teacherno);
			System.out.println("etnplanno  " + etnplanno + "   classno   " + classno + "   teacherno   " + teacherno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("TEACHER_NO") != null ? rs.getString("TEACHER_NO") : "";
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return "";
	}

	public void deleteWholeSatisfaction(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_WHOLE_SATISFACTION where ETN_PLAN_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void updateTeacherGroupByClass(String etnplanno, String classno, String teacherno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update ETN_TEACHER_GROUP_BY_CLASS set TEACHER_NO =? where ETN_PLAN_NO=? and CLASS_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(teacherno));
			pstmt.setInt(2, Integer.parseInt(etnplanno));
			pstmt.setInt(3, Integer.parseInt(classno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteTeacherFee(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_TEACHER_FEE where ETN_PLAN_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteEtnTeacherGroupByClass(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_TEACHER_GROUP_BY_CLASS where ETN_PLAN_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void deleteEtnFee(String etnplanno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete ETN_FEE where ETN_PLAN_NO=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void addOtherPeople(String etnplanno, String[] pno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_PLAN_OTHER (SEQ_NO,PLAN_NO,PNO)values (SEQ_ETN_PLAN_OTHER.nextval,?,?)";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			for (int i = 0; i < pno.length; i++) {
				pstmt.setString(2, pno[i]);
				pstmt.executeUpdate();
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public Vector getEtnPlanByClassType(PageControl pc, String classtype){
		Vector vector = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ETN_PLAN_V WHERE PLAN_CLASS_TYPE LIKE '" + classtype + "%' order by PLAN_START_DATE desc";
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
				vector.add(this.createEtnPlan(rs));
				pc.setiii();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return vector;
	}

	public Vector getOtherGroup(String etnplanno) {
		Vector vector = new Vector();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		//String sql ="SELECT SEQ_PNO,PNAME,PSOURCE,TO_CHAR(CREATE_TIME, 'YYYY-Mon-DD') as CREATE_TIME FROM ETN_OTHER_PEOPLES";

		String sql = " select op.seq_pno,op.pname,op.psource from ETN_OTHER_PEOPLES op,ETN_PLAN_OTHER ep " + " where op.seq_pno=ep.pno and ep.plan_no=" + etnplanno;
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				OtherPeople op = new OtherPeople();
				op.setSeq_pno(rs.getString("seq_pno"));
				op.setPname(rs.getString("pname"));
				if (rs.getString("psource") == null)
					op.setPsource("");
				else
					op.setPsource(rs.getString("psource"));
				//op.setCreatetime(rs.getString("create_time"));
				vector.add(op);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}

		finally {
			SqlUtil.close(stmt, conn);
		}
		return vector;

	}

	public void insPnos(String etnplanno, String[] insPno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into ETN_PLAN_OTHER values (SEQ_ETN_PLAN_OTHER.nextval,?,?)";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			for (int i = 0; i < insPno.length; i++) {
				pstmt.setInt(2, Integer.parseInt(insPno[i]));
				pstmt.executeUpdate();
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void delPnos(String etnplanno, String delPno[]) {
		Connection conn;
		PreparedStatement pstmt;
		String sql;
		conn = null;
		pstmt = null;
		sql = "delete from ETN_PLAN_OTHER where plan_no =? and pno=?";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(etnplanno));
			for (int i = 0; i < delPno.length; i++) {
				pstmt.setInt(2, Integer.parseInt(delPno[i]));
				pstmt.executeUpdate();
			}

		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void delAllOtherPeople(String no) {
		String sql = "delete from ETN_PLAN_OTHER where PLAN_NO=" + no;
    Logger.getLogger(getClass()).debug(sql);
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "delete from ETN_OPSCORE where PLAN_NO=" + no;
	     Logger.getLogger(getClass()).debug(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}finally {
			SqlUtil.close(stmt, conn);
		}

	}

	public boolean hasChildDate(String no) {

		boolean b = false;
		int l_r = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select count(*) as a from ETN_OPSCORE where PLAN_NO=" + no + "  union  " + "select count(*) as a from ETN_FACT_PLAN where SEQ_ETN_PLAN=" + no
				+ "   and (exam_score is not null or makeup_score is not null) ";
    Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ConnBean.getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				l_r = l_r + rs.getInt("a");
			}

			if (l_r > 0)
				b = true;

		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, stmt, conn);

		}

		return b;

	}

	public String getEtnPlanItemCode() {
		return etnPlanItemCode;
	}

	public void setEtnPlanItemCode(String etnPlanItemCode) {
		this.etnPlanItemCode = etnPlanItemCode;
	}

	public String getEtnPlanClassType() {
		return etnPlanClassType;
	}

	public void setEtnPlanClassType(String etnPlanClassType) {
		this.etnPlanClassType = etnPlanClassType;
	}

	public String getEtnPlanPerrior() {
		return etnPlanPerrior;
	}

	public void setEtnPlanPerrior(String etnPlanPerrior) {
		this.etnPlanPerrior = etnPlanPerrior;
	}

	public String getEtnPlanEduType() {
		return etnPlanEduType;
	}

	public void setEtnPlanEduType(String etnPlanEduType) {
		this.etnPlanEduType = etnPlanEduType;
	}

	public String getEtnPlanEduPlace() {
		return etnPlanEduPlace;
	}

	public void setEtnPlanEduPlace(String etnPlanEduPlace) {
		this.etnPlanEduPlace = etnPlanEduPlace;
	}

	public String getEtnPlanStartDate() {
		return etnPlanStartDate;
	}

	public void setEtnPlanStartDate(String etnPlanStartDate) {
		this.etnPlanStartDate = etnPlanStartDate;
	}

	public String getEtnPlanEndDate() {
		return etnPlanEndDate;
	}

	public void setEtnPlanEndDate(String etnPlanEndDate) {
		this.etnPlanEndDate = etnPlanEndDate;
	}

	public String getEtnPlanEduGroup() {
		return etnPlanEduGroup;
	}

	public void setEtnPlanEduGroup(String etnPlanEduGroup) {
		this.etnPlanEduGroup = etnPlanEduGroup;
	}

	public String getEtnPlanFactGroup() {
		return etnPlanFactGroup;
	}

	public void setEtnPlanFactGroup(String etnPlanFactGroup) {
		this.etnPlanFactGroup = etnPlanFactGroup;
	}

	public String getEtnPlanEduTime() {
		return etnPlanEduTime;
	}

	public void setEtnPlanEduTime(String etnPlanEduTime) {
		this.etnPlanEduTime = etnPlanEduTime;
	}

	public String getEtnPlanColor() {
		return etnPlanColor;
	}

	public void setEtnPlanColor(String etnPlanColor) {
		this.etnPlanColor = etnPlanColor;
	}

	public String getEtnPlanAppraise() {
		return etnPlanAppraise;
	}

	public void setEtnPlanAppraise(String etnPlanAppraise) {
		this.etnPlanAppraise = etnPlanAppraise;
	}

	public String getEtnPlanItemName() {
		return etnPlanItemName;
	}

	public void setEtnPlanItemName(String etnPlanItemName) {
		this.etnPlanItemName = etnPlanItemName;
	}

	public String getEtnPlanEduTypeName() {
		return etnPlanEduTypeName;
	}

	public void setEtnPlanEduTypeName(String etnPlanEduTypeName) {
		this.etnPlanEduTypeName = etnPlanEduTypeName;
	}

	public String getEtnPlanNo() {
		return etnPlanNo;
	}

	public void setEtnPlanNo(String etnPlanNo) {
		this.etnPlanNo = etnPlanNo;
	}

	public String getEtnPlanCode() {
		return etnPlanCode;
	}

	public void setEtnPlanCode(String etnPlanCode) {
		this.etnPlanCode = etnPlanCode;
	}

	public String getEtnPlanChineseName() {
		return etnPlanChineseName;
	}

	public void setEtnPlanChineseName(String etnPlanChineseName) {
		this.etnPlanChineseName = etnPlanChineseName;
	}

	public String getEtnPlanEmpId() {
		return etnPlanEmpId;
	}

	public void setEtnPlanEmpId(String etnPlanEmpId) {
		this.etnPlanEmpId = etnPlanEmpId;
	}

	public String getYearPlanNO() {
		return yearPlanNO;
	}

	public void setYearPlanNO(String yearPlanNO) {
		this.yearPlanNO = yearPlanNO;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherFeeHour() {
		return teacherFeeHour;
	}

	public void setTeacherFeeHour(String teacherFeeHour) {
		this.teacherFeeHour = teacherFeeHour;
	}

	public String getEtnDepartment() {
		return etnDepartment;
	}

	public void setEtnDepartment(String etnDepartment) {
		this.etnDepartment = etnDepartment;
	}

	public String getEtnPosition() {
		return etnPosition;
	}

	public void setEtnPosition(String etnPosition) {
		this.etnPosition = etnPosition;
	}

	public String getEtnPlanSex() {
		return etnPlanSex;
	}

	public void setEtnPlanSex(String etnPlanSex) {
		this.etnPlanSex = etnPlanSex;
	}

	public Integer getEtnPlanEduTimeDanWei() {
		return etnPlanEduTimeDanWei;
	}

	public void setEtnPlanEduTimeDanWei(Integer etnPlanEduTimeDanWei) {
		this.etnPlanEduTimeDanWei = etnPlanEduTimeDanWei;
	}
}
