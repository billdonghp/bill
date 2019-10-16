package com.ait.api.dao;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.bean.PageControl;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.*;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ApiDAO {

	/**
	 * 所有与考勤相关的申请、决裁、确认、查看操作
	 * 
	 * @author Pennix
	 */
	private PageControl pageControl = new PageControl();

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static ServiceLocator services;

	private static final Logger logger = Logger.getLogger(ApiDAO.class);

	public ApiDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
		try {
			services = ServiceLocator.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PageControl getPageControl() {
		return this.pageControl;
	}

	public void setPageControl(PageControl pageControl) {
		this.pageControl = pageControl;
	}

	public List getAffirmList(Object parameterObject, int currentPage, int pageSize, String target) throws GlRuntimeException {
		List result = null;
		try {
		    System.out.println(pageSize);
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api." + target, parameterObject,
					currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getInfoList(Object parameterObject, String target) throws GlRuntimeException {
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api." + target, parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List login(Object parameterObject){
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api.login" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List checkPassword(Object parameterObject){
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api.checkPassword" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List loginForAd(Object parameterObject){
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api.loginForAd" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List loginForWorker(Object parameterObject){
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api.loginForWorker" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getLoginInfo(){
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti(
					"com.api.getLoginInfo" );
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public int modifyPassword(Object parameterObject){
		try {
			commonSQLMapAdapter.update("com.api.modifyPassword" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			return 0;
		}
		return 1;
	}

	public String isAD(Object parameterObject){
		String result = null;
		try {
			result = StringUtil.checkNull( commonSQLMapAdapter.executeQuery(
					"com.api.isAD",parameterObject) );
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
		return result;
	}

	public int resetPassword(){
		try {
			commonSQLMapAdapter.update("com.api.resetPassword" );
		} catch (Exception e) {
			logger.error(e.toString());
			return 0;
		}
		return 1;
	}

	public Map getCount(Map map, String target ) {
		Map result = null;
		try {
			result = (SimpleMap) commonSQLMapAdapter.executeQuery("com.api." + target, map);
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return result;
	}
	/**
	 * 得到考勤月，开始日期，结束日期
	 *
	 * @param essOverTimeBean
	 * @return
	 */
	public SimpleMap getArMonthDate(SimpleMap otObj) {

		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SimpleMap result = new SimpleMap();

		try {

			// 得到考勤月，开始日期，结束日期
			sql = " SELECT TO_CHAR(ar_get_startdate_empid(AR_MONTH, ?),'YYYY-MM-DD') AS STARTDATE, "
					+ " 	   TO_CHAR(ar_get_enddate_empid(AR_MONTH, ?),'YYYY-MM-DD') AS ENDDATE,AR_MONTH "
					+ " FROM DUAL,(SELECT get_armonth_empid(TO_DATE(?, 'YYYY-MM-DD'), ?) AS AR_MONTH FROM DUAL) T";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, otObj.getString("PERSON_ID"));
			pstmt.setString(2, otObj.getString("PERSON_ID"));
			pstmt.setString(3, otObj.getString("OTDATE"));
			pstmt.setString(4, otObj.getString("PERSON_ID"));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result.setString("arMonth", rs.getString("AR_MONTH"));
				result.setString("arStartDate", rs.getString("STARTDATE"));
				result.setString("arEndDate", rs.getString("ENDDATE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());

		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}

		return result;
	}
	/*
	 * 得到员工当月已经申请的加班小时数
	 *
	 * String paramType('arMonth','arDate')
	 */
	public double getPersonOtTime(SimpleMap otObj) {
		double applyOtHours = 0.0; // 已加班小时数,ess和考勤明细,结合的加班小时数
		double otHours = 0.0; // 加班小时数,考勤明细信息
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " ";

		try {
			SimpleMap arMonthObj = this.getArMonthDate(otObj);
			// 取出加班小时数
			sql = " SELECT SUM(CASE WHEN NVL(AR.QUANTITY,-1) >= 0 THEN  AR.QUANTITY ELSE NVL(APPLY.OT_LENGTH,0) END ) OT_SUMAPPLY_OT_SUM,SUM(NVL(AR.QUANTITY,0)) AS OT_SUM "
					+ "   FROM "
					+ "       (SELECT ESS.APPLY_DATE,ESS.EMPID,SUM(CASE ESS.APPLY_TYPE WHEN 'WorkingOtType01' THEN (ESS.TO_TIME - ESS.FROM_TIME) * 24 ELSE ESS.OT_LENGTH END) AS OT_LENGTH "
					+ "          FROM AR_APPLY_RESULT ESS "
					+ "         WHERE ESS.EMPID = ? AND ESS.APPLY_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') "
					+ "         GROUP BY ESS.APPLY_DATE, ESS.EMPID) APPLY, "
					+ "       (SELECT TO_DATE(AD.AR_DATE_STR,'YYYY-MM-DD') AS AR_DATE_STR,AD.EMPID, SUM(AD.QUANTITY) AS QUANTITY "
					+ "          FROM AR_DETAIL AD "
					+ "         WHERE AD.EMPID = ? AND AD.AR_MONTH_STR=? "
					+ "           AND EXISTS (SELECT * FROM AR_ITEM T WHERE AD.AR_ITEM_NO = T.ITEM_NO AND T.ITEM_GROUP_CODE= ? ) "
					+ "         GROUP BY AD.AR_DATE_STR,AD.EMPID) AR, "
					+ "        (SELECT AC.DDATE FROM AR_CALENDER AC WHERE AC.DDATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') AND AC.CPNY_ID=? ) AR_DATE "
					+ "   WHERE AR_DATE.DDATE = APPLY.APPLY_DATE(+) AND AR_DATE.DDATE = AR.AR_DATE_STR(+) ";

			Logger.getLogger(getClass()).debug(
					"Get Overtime Hours From AR_DETAIL : " + sql);
			Logger.getLogger(getClass()).debug(
					"Get arStartDate : " + arMonthObj.getString("arStartDate"));
			Logger.getLogger(getClass()).debug(
					"Get arEndDate : " + arMonthObj.getString("arEndDate"));
			Logger.getLogger(getClass()).debug(
					"Get arMonth : " + arMonthObj.getString("arMonth"));
			Logger.getLogger(getClass()).debug(
					"Get OtDate : " + otObj.getString("OTDATE"));
			Logger.getLogger(getClass()).debug(
					"Get empId : " + otObj.getString("PERSON_ID"));
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, otObj.getString("PERSON_ID"));
			pstmt.setString(2, arMonthObj.getString("arStartDate"));

			pstmt.setString(3, otObj.getString("OTDATE"));

			pstmt.setString(4, otObj.getString("PERSON_ID"));
			pstmt.setString(5, arMonthObj.getString("arMonth"));
			pstmt.setString(6, Configuration.getInstance().getString(
					"/configuration/em2/ar-item-shortname/overtime", ""));
			pstmt.setString(7, arMonthObj.getString("arStartDate"));

			pstmt.setString(8, otObj.getString("OTDATE"));

			pstmt.setString(9, otObj.getString("CPNY_ID"));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				applyOtHours += rs.getDouble(1);
				otHours += rs.getDouble(2);

			}

			// 取出ess中，未确认和未被否决的申请加班小时数
			sql = "SELECT NVL(SUM( CASE ESS.APPLY_OT_TYPE_CODE WHEN 'WorkingOtType01' THEN (TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG,'YYYY-MM-DD ') || OT_TO_TIME,'YYYY-MM-DD HH24:MI') - "
					+ " TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME,'YYYY-MM-DD HH24:MI')) * 24 ELSE ESS.OT_LENGTH END - OT_DEDUCT_TIME),0)"
					+ " FROM (SELECT E.APPLY_OT_NO, E.APPLY_OT_DATE,E.APPLY_OT_FLAG,E.OT_TO_TIME,E.OT_FROM_TIME,E.OT_LENGTH,E.APPLY_OT_TYPE_CODE,E.OT_DEDUCT_TIME,E.ACTIVITY FROM ESS_APPLY_OT E WHERE EMPID = ?) ESS "
					+ " WHERE APPLY_OT_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') "
					+ " AND ACTIVITY = 0 ";
			Logger.getLogger(getClass()).debug(
					"Get Overtime Hours From UnConfirmed Apply : " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, otObj.getString("PERSON_ID"));
			pstmt.setString(2, arMonthObj.getString("arStartDate"));

			pstmt.setString(3, otObj.getString("OTDATE"));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				applyOtHours += rs.getDouble(1);
			}

			Logger.getLogger(getClass()).debug("otHours : " + otHours);

			otObj.set("applyOtHours",applyOtHours);
			otObj.set("otHours",otHours);

			// Logger.getLogger(getClass()).debug("otHours : " + otHours);

			// otHours -= essOverTimeBean.getOtDeduct();

			Logger.getLogger(getClass()).debug("applyHours : " + applyOtHours);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());

		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return applyOtHours;
	}


	public int insertSms(Object parameterObject){
		try {
			commonSQLMapAdapter.update("com.api.insertSms" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			return 0;
		}
		return 1;
	}

	public int insertNotice(Object parameterObject){
		try {
			commonSQLMapAdapter.update("com.api.insertNotice" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			return 0;
		}
		return 1;
	}

	public int updateExp(Object parameterObject){
		try {
			commonSQLMapAdapter.update("com.api.updateExp" , parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			return 0;
		}
		return 1;
	}

	public String getString(Map map, String target ) {
		String result = null;
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("com.api." + target, map));
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return result;
	}
}