package com.ait.ess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.dao.AnnualBean;
import com.ait.core.config.Configuration;
import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.EssUtil;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssMatchBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DateUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

/**
 * EssArDAO 辅助类
 * 
 * @author Pennix
 */

public class EssArDAOHelper implements EssStrategyIntf {

	/**
	 * hashMap为检查操作后返回值,errcode为0时为操作正常(检查全部通过)
	 */
	private HashMap hashMap = null;

	private GregorianCalendar startTime = new GregorianCalendar();

	private GregorianCalendar endTime = new GregorianCalendar();	

	protected final String otWorkPath = "/configuration/em2/syscode/ot-type-normal";
	
	protected final String otWeekendPath = "/configuration/em2/syscode/ot-type-weekend";
	
	protected final String otHolidayPath = "/configuration/em2/syscode/ot-type-holiday";
	
	protected String otWorkCode ;//WorkingOtType01
	
	protected String otWeekendCode ;//WorkingOtType02
	
	protected String otHolidayCode ;//WorkingOtType03
	
	EssSysparam essSysparam=new EssSysparam();

	public EssArDAOHelper() {
		this.hashMap = new HashMap();
		this.hashMap.put("errcode", new Integer(0));
		
		 essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,ApplicationContext.getTheadLocal().getCpnyId());
		if (this.essSysparam == null)
			this.essSysparam = new EssSysparam();
		startTime.clear();
		endTime.clear();
		
		try
		{
			otWorkCode = Configuration.getInstance().getString(this.otWorkPath, "");//WorkingOtType01
			otWeekendCode = Configuration.getInstance().getString(this.otWeekendPath, "");//WorkingOtType02
			otHolidayCode = Configuration.getInstance().getString(this.otHolidayPath, "");//WorkingOtType03
		}
		catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
	}

	protected void setStartTime(GregorianCalendar startTime) {
		this.startTime = startTime;
	}

	protected GregorianCalendar getStartTime() {
		return startTime;
	}

	protected void setEndTime(GregorianCalendar endTime) {
		this.endTime = endTime;
	}

	protected GregorianCalendar getEndTime() {
		return endTime;
	}

	protected EssSysparam getEssSysparam() {
		return essSysparam;
	}

	protected HashMap getHashMap() {

		return hashMap;

	}

	// 日历类型:1平时,2周末,3节假日
	protected int getDataType(EssOverTimeBean essOverTimeBean) {
		AdminBean  admin =(AdminBean)ApplicationContext.getTheadLocal();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select ar_get_datetype(?,TO_CHAR(TO_DATE(?,'YYYY-MM-DD') - ?,'YYYY/MM/DD'),?) from dual ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn = ServiceLocator.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, essOverTimeBean.getOtDate());
			pstmt.setInt(3, essOverTimeBean.getOtNightFlag());
			pstmt.setString(4, admin.getCpnyId());
			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			else
				throw new GlRuntimeException("获取日期类型错误,没有此人的个人日历");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GlRuntimeException("获取日期类型错误", ex);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 检查加班申请是否合格, 并返回错误代码
	 * 
	 * @param essOverTimeBean
	 * @return 返回 HashMap
	 */
	public HashMap otApplyChecker(EssOverTimeBean essOverTimeBean) {
		try {
			this.setStartTime(DateUtil.ParseGregorianCalendar(essOverTimeBean.getOtDate() + " " + essOverTimeBean.getOtFromTime()));
			this.setEndTime(DateUtil.ParseGregorianCalendar(essOverTimeBean.getOtDate() + " " + essOverTimeBean.getOtToTime()));
			this.endTime.add(GregorianCalendar.DAY_OF_MONTH, essOverTimeBean.getOtNextDays());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss"); 
			
			Logger.getLogger(getClass()).debug("startTime : " + sdf.format(startTime.getTime()));
			Logger.getLogger(getClass()).debug("endTime : " + sdf.format(endTime.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			hashMap.put("errcode", new Integer(1));
		}
		try {
			// 开始结束时间检查
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckOtApplyStartEndTime())
			{
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
					this.otApplyStartEndTimeCheck(essOverTimeBean);
			}
			
			// 加班时间与班次时间检查 不检测节假日加班申请
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckOtApplyShiftConflict()) {
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
					this.otConflictWithArShift(essOverTimeBean);
			}			
			
			// 检测加班的申请类型
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && !essOverTimeBean.getOtTypeCode().equals("WorkingOtType0" + getDataType(essOverTimeBean)) ) {
				this.hashMap.put("erremp", essOverTimeBean.getChineseName());
				this.hashMap.put("errcode", new Integer(16));
			}

			// 加班时间与之前申请加班时间检查
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckOtApplyOtConflict())
			{
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
				{
					this.otConflictWithOtApply(essOverTimeBean);
				}
				else
				{
					this.otConflictWithOtApply2(essOverTimeBean);
				}
					
			}
				
			// 加班时间与之前申请休假时间检查
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckOtApplyLeaveConflict())
			{
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
					this.otConflictWithLeaveApply(essOverTimeBean);
			}
				
			// 加班时间与之前申请值班时间检查
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckOtApplyMatchConflict())
			{
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
					this.otConflictWithMatchApply(essOverTimeBean);
			}
				
			// 申请加班的时间有效性检查
			if (((Integer) hashMap.get("errcode")).intValue() == 0)
			{
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
					this.otCheckOtApplyDaysBeforeAfter(essOverTimeBean, essSysparam.getOtApplyDaysBefore(), essSysparam.getOtApplyDaysAfter());
			}
				
			// 检查月累计加班上限限制
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.getOtApplyMaxHours() )
				this.otCheckOtMaxHours(essOverTimeBean);
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			hashMap.put("errcode", new Integer(-1));
			hashMap.put("errmsg", e.toString());
		}
		return hashMap;
	}

	/**
	 * 检查休假申请是否合格, 并返回错误代码
	 * 
	 * @param essOverTimeBean
	 * @return 返回 HashMap
	 */
	public HashMap leaveApplyChecker(EssLeaveBean essLeaveBean) {
		try {
			this.startTime = DateUtil.ParseGregorianCalendar(essLeaveBean.getLeaveFromDate() + " " + essLeaveBean.getLeaveFromTime());
			this.endTime = DateUtil.ParseGregorianCalendar(essLeaveBean.getLeaveToDate() + " " + essLeaveBean.getLeaveToTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm"); 
			
			Logger.getLogger(getClass()).debug("startTime : " + sdf.format(startTime.getTime()));
			Logger.getLogger(getClass()).debug("endTime : " + sdf.format(endTime.getTime()));
			Logger.getLogger(getClass()).debug("startTime : " + essLeaveBean.getLeaveFromDate() + " " + essLeaveBean.getLeaveFromTime());
			Logger.getLogger(getClass()).debug("endTime : " + essLeaveBean.getLeaveToDate() + " " + essLeaveBean.getLeaveToTime());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			hashMap.put("errcode", new Integer(1));
		}
		// 开始结束时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckLeaveApplyStartEndTime())
			this.leaveApplyStartEndTimeCheck(essLeaveBean);
		// 休假时间与之前申请加班时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckLeaveApplyOtConflict())
			this.leaveConflictWithOtApply(essLeaveBean);
		// 休假时间与之前申请休假时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckLeaveApplyLeaveConflict())
			this.leaveConflictWithLeaveApply(essLeaveBean);
		// 休假时间与之前申请值班时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckLeaveApplyMatchConflict())
			this.leaveConflictWithMatchApply(essLeaveBean);
		
		// 加班期间公出是否在已有加班申请时间内检查
		String ot8Leave = "OT8";
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && essLeaveBean.getLeaveTypeCode().equals(ot8Leave))
			this.leaveConflictWithOt8(essLeaveBean);

		// 如果是年假申请,检查年假数 -- 
		try {
			String annualCode = Configuration.getInstance().getString("/configuration/em2/syscode/annual-code", "");
			if (((Integer) hashMap.get("errcode")).intValue() == 0 && essLeaveBean.getLeaveTypeCode().equals(annualCode))
				this.leaveApplyAnnualCheck(essLeaveBean);
			
			//如果是有薪病假,检查剩余有薪病假数
			String sickLeave = Configuration.getInstance().getString("/configuration/em2/syscode/sickLeave", "");
			if(((Integer)hashMap.get("errcode")).intValue() == 0 && essLeaveBean.getLeaveTypeCode().equals(sickLeave)){
				this.leaveApplySickCheck(essLeaveBean);
			}
			
			//如果是团聚假,检查剩余团聚数
			String reuniteLeave = Configuration.getInstance().getString("/configuration/em2/syscode/reuniteLeave", "");
			if(((Integer)hashMap.get("errcode")).intValue() == 0 && essLeaveBean.getLeaveTypeCode().equals(reuniteLeave)){
				this.leaveApplyReuniteCheck(essLeaveBean);
			}
			
			//如果是上年剩余年假,检查上年剩余年假
			String lastAnnualLeave = "H91";
			if(((Integer)hashMap.get("errcode")).intValue() == 0 && essLeaveBean.getLeaveTypeCode().equals(lastAnnualLeave)){
				this.leaveApplylastAnnualCheck(essLeaveBean);
			}
			
		} catch (ConfigurationException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("取年假代码错误", e);
		}
		
		// 休假必须在上班时间内才能进行申请,只判断在同一天之内的申请
		String flag = getLeaveTypeParentCode(essLeaveBean.getLeaveTypeCode());
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && flag.equals("LeaveTypeCode") 
				&& essLeaveBean.getLeaveFromDate().equals(essLeaveBean.getLeaveToDate())&& !essLeaveBean.getLeaveTypeCode().equals("OT8"))
			this.leaveCheckValidity(essLeaveBean);
		
		return hashMap;
	}

	private String getLeaveTypeParentCode(String code) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String parentcode = "";
		String sql = "select parent_code from sy_code where code_id=?";
		Logger.getLogger(getClass()).debug(sql);

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				parentcode = rs.getString(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).debug(ex.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return parentcode;
	}

	private void leaveCheckValidity(EssLeaveBean essLeaveBean) {
		AdminBean admin=(AdminBean)ApplicationContext.getTheadLocal();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		String sql = " select count(*) from ( select MIN(TO_CHAR(TO_DATE(?,'YYYY-MM-DD') + BEGIN_DAY_OFFSET,'YYYY-MM-DD') ||' '|| to_char(FROM_TIME,'HH24:MI:SS'))FROMTIME, "
			       + " MAX(TO_CHAR(TO_DATE(?,'YYYY-MM-DD')+END_DAY_OFFSET,'YYYY-MM-DD')||' '|| to_char(TO_TIME,'HH24:MI:SS'))TOTIME  FROM ar_shift020 WHERE " 
			       + " ar_shift020.SHIFT_NO=AR_GET_SHIFTNO(?, TO_CHAR(to_date(?,'YYYY-MM-DD'),'YYYY/MM/DD'),?))A " 
			       + " WHERE TO_CHAR(to_date(?,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS') between FROMTIME and TOTIME "
			       + " AND TO_CHAR(to_date(?,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS') between FROMTIME and TOTIME " ;
		Logger.getLogger(getClass()).debug(sql);
		
		Logger.getLogger(getClass()).debug(essLeaveBean.getLeaveFromDate());
		Logger.getLogger(getClass()).debug(essLeaveBean.getLeaveToDate());
		Logger.getLogger(getClass()).debug(essLeaveBean.getPerson_id());
		Logger.getLogger(getClass()).debug(essLeaveBean.getLeaveFromDate());
		Logger.getLogger(getClass()).debug(essLeaveBean.getLeaveFromDate() + " " + essLeaveBean.getLeaveFromTime());
		Logger.getLogger(getClass()).debug(essLeaveBean.getLeaveToDate() + " " + essLeaveBean.getLeaveToTime());

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essLeaveBean.getLeaveFromDate());
			pstmt.setString(2, essLeaveBean.getLeaveToDate());
			pstmt.setString(3, essLeaveBean.getPerson_id());
			pstmt.setString(4, essLeaveBean.getLeaveFromDate());
			pstmt.setString(5, admin.getCpnyId());
			pstmt.setString(6, essLeaveBean.getLeaveFromDate() + " " + essLeaveBean.getLeaveFromTime());
			pstmt.setString(7, essLeaveBean.getLeaveToDate() + " " + essLeaveBean.getLeaveToTime());


			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
				if (num == 0) {
					this.hashMap.put("errcode", new Integer(13));
					this.hashMap.put("erremp", essLeaveBean.getChineseName());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).debug(ex.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", ex.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 检查值班申请是否合格, 并返回错误代码
	 * 
	 * @param essOverTimeBean
	 * @return 返回 HashMap
	 */
	public HashMap matchApplyChecker(EssMatchBean essMatchBean) {
		try {
			this.startTime = DateUtil.ParseGregorianCalendar(essMatchBean.getMatchFromTime());
			this.endTime = DateUtil.ParseGregorianCalendar(essMatchBean.getMatchToTime());
			Logger.getLogger(getClass()).debug("startTime : " + startTime.toString());
			Logger.getLogger(getClass()).debug("endTime : " + endTime.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			hashMap.put("errcode", new Integer(1));
		}
		// 开始结束时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckMatchApplyStartEndTime())
			this.matchApplyStartEndTimeCheck(essMatchBean);
		// 值班时间与之前申请加班时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckMatchApplyOtConflict())
			this.matchConflictWithOtApply(essMatchBean);
		// 值班时间与之前申请休假时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckMatchApplyLeaveConflict())
			this.matchConflictWithLeaveApply(essMatchBean);
		// 值班时间与之前申请值班时间检查
		if (((Integer) hashMap.get("errcode")).intValue() == 0 && this.essSysparam.isCheckMatchApplyMatchConflict())
			this.matchConflictWithMatchApply(essMatchBean);
		return hashMap;
	}

	/** **************************具体检查的方法************************************** */

	/**
	 * 加班申请始末时间是否正常
	 * 
	 * @param essOverTimeBean
	 */
	protected void otApplyStartEndTimeCheck(EssOverTimeBean essOverTimeBean) {
		if (startTime.after(endTime) || startTime.equals(endTime))
			hashMap.put("errcode", new Integer(2));
			hashMap.put("erremp", essOverTimeBean.getChineseName());
	}

	/**
	 * 加班申请是否与班次冲突
	 * 
	 * @param essOverTimeBean
	 */
	protected void otConflictWithArShift(EssOverTimeBean essOverTimeBean) {
		AdminBean admin=ApplicationContext.getTheadLocal();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(? || TO_CHAR(AR_SHIFT020.FROM_TIME, ' HH24:MI'), 'YYYY-MM-DD HH24:MI') - ? + AR_SHIFT020.BEGIN_DAY_OFFSET + ?, " 
			       + "TO_DATE(? || TO_CHAR(AR_SHIFT020.TO_TIME, ' HH24:MI'), 'YYYY-MM-DD HH24:MI') - ? + AR_SHIFT020.END_DAY_OFFSET + ? " 
			       + "FROM AR_SHIFT010, AR_SHIFT020 " 
			       + "WHERE AR_SHIFT010.SHIFT_NO = AR_SHIFT020.SHIFT_NO " 
			       + "AND AR_SHIFT020.SHIFT_NO = AR_GET_SHIFTNO(?, TO_CHAR(TO_DATE(?,'YYYY-MM-DD') - ? + ?,'YYYY/MM/DD'),?) " 
			       + "AND AR_SHIFT010.DATATYPE = 1";
		for(int i = 0 ; i < essOverTimeBean.getOtNextDays() ; i ++)
		{
			sql = sql + " UNION ALL " + sql ;
		}
		
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			int j = 1 ;
			for(int i = 0 ; i <= essOverTimeBean.getOtNextDays() ; i ++)
			{
				
				pstmt.setString(j++, essOverTimeBean.getOtDate());
				pstmt.setInt(j++, essOverTimeBean.getOtNightFlag());
				pstmt.setInt(j++,i);
				pstmt.setString(j++, essOverTimeBean.getOtDate());
				pstmt.setInt(j++, essOverTimeBean.getOtNightFlag());
				pstmt.setInt(j++,i);
				pstmt.setString(j++, essOverTimeBean.getPerson_id());
				pstmt.setString(j++, essOverTimeBean.getOtDate());
				pstmt.setInt(j++, essOverTimeBean.getOtNightFlag());
				pstmt.setInt(j++,i);
				pstmt.setString(j++, admin.getCpnyId());
				
				Logger.getLogger(getClass()).debug("getOtNightFlag=" + essOverTimeBean.getOtNightFlag());
				Logger.getLogger(getClass()).debug("getOtDate=" + essOverTimeBean.getOtDate());
				Logger.getLogger(getClass()).debug("getPerson_id=" + essOverTimeBean.getPerson_id());
				Logger.getLogger(getClass()).debug("nextDate=" + i);
				Logger.getLogger(getClass()).debug("cpnyId=" + admin.getCpnyId());
			}
	
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar shiftFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				Logger.getLogger(getClass()).debug("shiftFrom : " + shiftFrom.toString());
				GregorianCalendar shiftTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				Logger.getLogger(getClass()).debug("shiftTo : " + shiftTo.toString());
				if (DateUtil.DateCross(this.startTime, this.endTime, shiftFrom, shiftTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essOverTimeBean.getChineseName());
					this.hashMap.put("errcode", new Integer(3));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 加班申请是否与已有加班申请冲突
	 * 
	 * @param essOverTimeBean
	 */
	protected void otConflictWithOtApply(EssOverTimeBean essOverTimeBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME, 'YYYY-MM-DD HH24:MI'), " + "TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG, 'YYYY-MM-DD ') || OT_TO_TIME, 'YYYY-MM-DD HH24:MI') FROM ESS_APPLY_OT WHERE ACTIVITY < 2 AND EMPID = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar applyFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar applyTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, applyFrom, applyTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essOverTimeBean.getChineseName());
					this.hashMap.put("errcode", new Integer(4));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	
	/**
	 * 周末和节假日加班申请只能申请一次
	 * 
	 * @param essOverTimeBean
	 */
	protected void otConflictWithOtApply2(EssOverTimeBean essOverTimeBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT OT.EMPID FROM ESS_APPLY_OT OT WHERE OT.ACTIVITY < 2 AND OT.EMPID = ? AND OT.APPLY_OT_TYPE_CODE <> 'WorkingOtType01' AND OT.APPLY_OT_DATE = TO_DATE(?,'YYYY-MM-DD') - ? " 
				   + " AND NOT EXISTS (SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = APPLY_OT_NO AND APPLY_TYPE = 'OtApply' AND AFFIRM_FLAG = 2)";
		Logger.getLogger(getClass()).debug(sql);
		//Logger.getLogger(getClass()).debug(essOverTimeBean.getEmpId());
		//Logger.getLogger(getClass()).debug(essOverTimeBean.getOtDate());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, essOverTimeBean.getOtDate());
			pstmt.setInt(3, essOverTimeBean.getOtNightFlag());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				this.hashMap.put("erremp", essOverTimeBean.getChineseName());
				this.hashMap.put("errcode", new Integer(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	

	/**
	 * 加班申请是否与已有休假申请冲突
	 * 
	 * @param essOverTimeBean
	 */
	protected void otConflictWithLeaveApply(EssOverTimeBean essOverTimeBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT LEAVE_FROM_TIME, LEAVE_TO_TIME " 
			       + "FROM ESS_LEAVE_APPLY_TB " 
			       + "WHERE LEAVE_EMPID = ? " 
			       + "AND NOT EXISTS " 
			       + "(SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = LEAVE_NO AND APPLY_TYPE = 'LeaveApply' AND AFFIRM_FLAG = 2) " 
			       + "AND ACTIVITY = 0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getEmpId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar leaveFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar leaveTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, leaveFrom, leaveTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essOverTimeBean.getChineseName());
					this.hashMap.put("errcode", new Integer(5));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 加班申请是否与已有休假申请冲突
	 * 
	 * @param essOverTimeBean
	 */
	protected void otConflictWithMatchApply(EssOverTimeBean essOverTimeBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " SELECT MATCH_FROM_TIME, MATCH_TO_TIME " + "FROM ESS_MATCH_APPLY_TB " + "WHERE MATCH_EMPID = ? " + "AND NOT EXISTS " + "(SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = MATCH_NO AND APPLY_TYPE = 'MatchApply' AND AFFIRM_FLAG = 2) " + "AND ACTIVITY = 0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getEmpId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar matchFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar matchTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, matchFrom, matchTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essOverTimeBean.getChineseName());
					this.hashMap.put("errcode", new Integer(6));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 加班申请是否在可以申请日期范围内
	 * 
	 * @param essOverTimeBean
	 * @param otApplyDaysBefore
	 *            可申请日期范围上限
	 * @param otApplyDaysAfter
	 *            可申请日期范围下限
	 */
	protected void otCheckOtApplyDaysBeforeAfter(EssOverTimeBean essOverTimeBean, int otApplyDaysBefore, int otApplyDaysAfter) {
		String otSort = essOverTimeBean.getOtSort();
		GregorianCalendar otDate = DateUtil.ParseGregorianCalendar(essOverTimeBean.getOtDate());
		GregorianCalendar createDate = DateUtil.ParseGregorianCalendar(essOverTimeBean.getCreateDate());
		Logger.getLogger(getClass()).debug(otDate);
		if (otApplyDaysBefore >= 0) {
			GregorianCalendar dayStart = DateUtil.DateAdd(createDate, "DAY", -1 * otApplyDaysBefore);
			Logger.getLogger(getClass()).debug(dayStart);
			if (otSort.equals("normal")) {
				if (otDate.before(dayStart)) {
					this.hashMap.put("errcode", new Integer(7));
					this.hashMap.put("applydaysbefore", String.valueOf(otApplyDaysBefore));
				}
			} else if (otSort.equals("emergency")) {
				if (!otDate.before(dayStart)) {
					this.hashMap.put("errcode", new Integer(12));
					this.hashMap.put("applydaysbefore", String.valueOf(otApplyDaysBefore));
				}
			}
		}
		if (otApplyDaysAfter >= 0) {
			GregorianCalendar dayEnd = DateUtil.DateAdd(createDate, "DAY", otApplyDaysAfter);
			Logger.getLogger(getClass()).debug(dayEnd);
			if (otDate.after(dayEnd)) {
				this.hashMap.put("errcode", new Integer(8));
				this.hashMap.put("applydaysafter", String.valueOf(otApplyDaysAfter));
			}
		}
	}

	/**
	 * 休假申请始末时间是否正常
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveApplyStartEndTimeCheck(EssLeaveBean essLeaveBean) {
		if (startTime.after(endTime) || startTime.equals(endTime))
		{
			hashMap.put("errcode", new Integer(2));
			hashMap.put("erremp", essLeaveBean.getChineseName());
		}
			
	}

	/**
	 * 休假申请是否与已有加班申请冲突
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveConflictWithOtApply(EssLeaveBean essLeaveBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME, 'YYYY-MM-DD HH24:MI'), TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG, 'YYYY-MM-DD ') || OT_TO_TIME, 'YYYY-MM-DD HH24:MI') FROM ESS_APPLY_OT " 
			       + " WHERE ACTIVITY = 0 AND NOT EXISTS (SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = APPLY_OT_NO AND APPLY_TYPE = 'OtApply' AND AFFIRM_FLAG = 2) AND EMPID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essLeaveBean.getPerson_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar applyFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar applyTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, applyFrom, applyTo, "MILLISECOND") > 0 && !essLeaveBean.getLeaveTypeCode().equals("OT8")) {
					this.hashMap.put("erremp", essLeaveBean.getChineseName());
					this.hashMap.put("errcode", new Integer(4));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	
	/**
	 * 加班期间公出是否在已有加班申请时间内
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveConflictWithOt8(EssLeaveBean essLeaveBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql ="SELECT COUNT(*) FROM (SELECT TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME, 'YYYY-MM-DD HH24:MI') OT_FROM_TIME , " +
			        "TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG, 'YYYY-MM-DD ') || OT_TO_TIME, 'YYYY-MM-DD HH24:MI') OT_TO_TIME FROM ESS_APPLY_OT E " +
			        "WHERE E.ACTIVITY < 2  AND E.EMPID = ? ) A  WHERE A.OT_FROM_TIME <= ? AND A.OT_TO_TIME >= ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essLeaveBean.getPerson_id());
			pstmt.setTimestamp(2, new Timestamp(this.startTime.getTimeInMillis()) ) ;
			pstmt.setTimestamp(3, new Timestamp(this.endTime.getTimeInMillis()) ) ;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
				if (num == 0) {
					this.hashMap.put("errcode", new Integer(21));
					this.hashMap.put("erremp", essLeaveBean.getChineseName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 休假申请是否与已有休假申请冲突
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveConflictWithLeaveApply(EssLeaveBean essLeaveBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT (CASE APPLYLEAVETYPE WHEN 'ApplyLeaveType01' THEN LEAVE_FROM_TIME ELSE " +
          " (CASE WHEN ? < LEAVE_TO_TIME AND ? > LEAVE_FROM_TIME " +
          " THEN TO_DATE(? || TO_CHAR(LEAVE_FROM_TIME, 'HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') + " + 
          " DECODE(?, ?, 0, 1) ELSE LEAVE_FROM_TIME END) END) AS LEAVE_FROM_TIME, " +
          " (CASE APPLYLEAVETYPE WHEN 'ApplyLeaveType01' THEN LEAVE_TO_TIME ELSE " +
          " (CASE WHEN ? < LEAVE_TO_TIME AND ? > LEAVE_FROM_TIME THEN " +
          " TO_DATE(? || TO_CHAR(LEAVE_TO_TIME, 'HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS') + " +
          " DECODE(?, ?, 0, 1) ELSE LEAVE_TO_TIME END) END) AS LEAVE_TO_TIME, " +
          " LEAVE_FROM_TIME AS LEAVE_FROM_TIME2, LEAVE_TO_TIME AS LEAVE_TO_TIME2, APPLYLEAVETYPE FROM ESS_LEAVE_APPLY_TB " +
          " WHERE LEAVE_EMPID = ? AND ACTIVITY < 2" ;
		try {
			String startTimeStr = DateUtil.formatDate(this.startTime.getTime(), "yyyy-MM-dd") ;
			String endTimeStr = DateUtil.formatDate(this.endTime.getTime(), "yyyy-MM-dd") ; ;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setTimestamp(1, new Timestamp(this.startTime.getTimeInMillis()) ) ;
			pstmt.setTimestamp(2, new Timestamp(this.endTime.getTimeInMillis()) ) ;
			pstmt.setString(3, startTimeStr);
			pstmt.setString(4, startTimeStr);
			pstmt.setString(5, endTimeStr);
			
			pstmt.setTimestamp(6, new Timestamp(this.startTime.getTimeInMillis()) ) ;
			pstmt.setTimestamp(7, new Timestamp(this.endTime.getTimeInMillis()) ) ;
			pstmt.setString(8, startTimeStr);
			pstmt.setString(9, startTimeStr);
			pstmt.setString(10, endTimeStr);
			
			pstmt.setString(11, essLeaveBean.getPerson_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar leaveFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar leaveTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				
				Logger.getLogger(getClass()).debug("leaveFrom : " + leaveFrom.getTime());
				Logger.getLogger(getClass()).debug("leaveTo : " + leaveTo.getTime());
				
				if (DateUtil.DateCross(this.startTime, this.endTime, leaveFrom, leaveTo, "MILLISECOND") > 0) {
					// 针对QPSS没有开始结束时间的情况，调用重载的计算重叠时间的方法
					// if (DateUtil.DateCross(this.startTime, this.endTime,
					// leaveFrom, leaveTo) > 0) {
					this.hashMap.put("erremp", essLeaveBean.getChineseName());
					this.hashMap.put("errcode", new Integer(5));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 休假申请是否与已有值班申请冲突
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveConflictWithMatchApply(EssLeaveBean essLeaveBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT MATCH_FROM_TIME, MATCH_TO_TIME " + "FROM ESS_MATCH_APPLY_TB " + "WHERE MATCH_EMPID = ? " + "AND NOT EXISTS " + "(SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = MATCH_NO AND APPLY_TYPE = 'MatchApply' AND AFFIRM_FLAG = 2) " + "AND ACTIVITY = 0";

		// " select MIN(TO_CHAR(TO_DATE(?,'YYYY-MM-DD
		// HH24:MI:SS')+BEGIN_DAY_OFFSET,'YYYY-MM-DD')||' '|| "+
		// " to_char(FROM_TIME,'HH24:MI:SS'))FROMTIME, "+
		// " MAX(TO_CHAR(TO_DATE(?,'YYYY-MM-DD
		// HH24:MI:SS')+END_DAY_OFFSET,'YYYY-MM-DD')||' '|| "+
		// " to_char(TO_TIME,'HH24:MI:SS'))TOTIME "+
		// " FROM ar_shift020 where shift_no=select
		// Ar_Get_Shiftno(?,to_char(?,'YYYY/MM/DD')) from dual ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essLeaveBean.getEmpId());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GregorianCalendar matchFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar matchTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, matchFrom, matchTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essLeaveBean.getChineseName());
					this.hashMap.put("errcode", new Integer(6));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 检查年假是否超过
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveApplyAnnualCheck(EssLeaveBean essLeaveBean) {
		Annual annual = (new AnnualBean()).empAnnual(essLeaveBean.getPerson_id());
		Logger.getLogger(getClass()).debug("Annual Left : " + annual.getAnnualHoursLeft());//剩余可修年假小时数
		double leaveLength = EssUtil.getActualLeaveHour(essLeaveBean.getPerson_id(), (essLeaveBean.getLeaveFromDate()+" "+essLeaveBean.getLeaveFromTime()), (essLeaveBean.getLeaveToDate()+" "+essLeaveBean.getLeaveToTime()),essLeaveBean.getLeaveTypeCode(),essLeaveBean.getApplyLeaveType());//实际申请的年假小时数 没问题
		Logger.getLogger(getClass()).debug("Leave Length : " + leaveLength);
		if (annual.getAnnualHoursLeft() < leaveLength) {
			this.hashMap.put("errcode", new Integer(3));
			this.hashMap.put("erremp", essLeaveBean.getChineseName());
		}
	}
	
	/**
	 * 检查有薪病假是否超过
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveApplySickCheck(EssLeaveBean essLeaveBean){
		SimpleMap simpleMap = new AnnualBean().retriveSickLeave(essLeaveBean.getPerson_id());
		double leaveLength = EssUtil.getActualLeaveHour(essLeaveBean.getPerson_id(), (essLeaveBean.getLeaveFromDate()+" "+essLeaveBean.getLeaveFromTime()), (essLeaveBean.getLeaveToDate()+" "+essLeaveBean.getLeaveToTime()),essLeaveBean.getLeaveTypeCode(),essLeaveBean.getApplyLeaveType());
		Logger.getLogger(getClass()).debug("leaveLength:"+leaveLength);
		Logger.getLogger(getClass()).debug("sickLeaveLeft:"+(simpleMap.getDouble("sickLeaveLeft")*8));
		if((simpleMap.getDouble("sickLeaveLeft")*8)< leaveLength){
			this.hashMap.put("errcode", new Integer(15));
			this.hashMap.put("erremp", essLeaveBean.getChineseName());
		}
	}
	

	/**
	 * 检查团聚假是否超过
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveApplyReuniteCheck(EssLeaveBean essLeaveBean){
		SimpleMap simpleMap = new AnnualBean().retriveReuniteLeave(essLeaveBean.getPerson_id());
		double leaveLength = EssUtil.getActualLeaveHour(essLeaveBean.getPerson_id(), (essLeaveBean.getLeaveFromDate()+" "+essLeaveBean.getLeaveFromTime()), (essLeaveBean.getLeaveToDate()+" "+essLeaveBean.getLeaveToTime()),essLeaveBean.getLeaveTypeCode(),essLeaveBean.getApplyLeaveType());
		Logger.getLogger(getClass()).debug("leaveLength:"+leaveLength);
		Logger.getLogger(getClass()).debug("reuniteLeaveLeft:"+(simpleMap.getDouble("reuniteLeaveLeft")*8));
		if((simpleMap.getDouble("reuniteLeaveLeft")*8)< leaveLength){
			this.hashMap.put("errcode", new Integer(17));
			this.hashMap.put("erremp", essLeaveBean.getChineseName());
		}
	}
	
	/**
	 * 检查上年剩余年假是否超过
	 * 
	 * @param essLeaveBean
	 */
	protected void leaveApplylastAnnualCheck(EssLeaveBean essLeaveBean){
		SimpleMap simpleMap = new AnnualBean().lastAnnualVacation(essLeaveBean.getPerson_id());
		double leaveLength = EssUtil.getActualLeaveHour(essLeaveBean.getPerson_id(), (essLeaveBean.getLeaveFromDate()+" "+essLeaveBean.getLeaveFromTime()), (essLeaveBean.getLeaveToDate()+" "+essLeaveBean.getLeaveToTime()),essLeaveBean.getLeaveTypeCode(),essLeaveBean.getApplyLeaveType());
		Logger.getLogger(getClass()).debug("leaveLength:"+leaveLength);
		Logger.getLogger(getClass()).debug("lastAnnualVacationLeft:"+(simpleMap.getDouble("lastAnnualVacationLeft")*8));
		if((simpleMap.getDouble("lastAnnualVacationLeft")*8)< leaveLength){
			this.hashMap.put("errcode", new Integer(18));
			this.hashMap.put("erremp", essLeaveBean.getChineseName());
		}
	}
	/**
	 * 值班申请始末时间是否正常
	 * 
	 * @param essMatchBean
	 */
	protected void matchApplyStartEndTimeCheck(EssMatchBean essMatchBean) {
		if (startTime.after(endTime) || startTime.equals(endTime))
			hashMap.put("errcode", new Integer(2));
	}

	/**
	 * 值班申请是否与已有加班申请冲突
	 * 
	 * @param essLeaveBean
	 */
	protected void matchConflictWithOtApply(EssMatchBean essMatchBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME, 'YYYY-MM-DD HH24:MI'), " + "TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG, 'YYYY-MM-DD ') || OT_TO_TIME, 'YYYY-MM-DD HH24:MI') " + "FROM ESS_APPLY_OT " + "WHERE ACTIVITY = 0 " + "AND NOT EXISTS " + "(SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = APPLY_OT_NO AND APPLY_TYPE = 'OtApply' AND AFFIRM_FLAG = 2) " + "AND EMPID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essMatchBean.getEmpID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar applyFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar applyTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, applyFrom, applyTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essMatchBean.getChineseName());
					this.hashMap.put("errcode", new Integer(4));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 值班申请是否与已有休假申请冲突
	 * 
	 * @param essLeaveBean
	 */
	protected void matchConflictWithLeaveApply(EssMatchBean essMatchBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT LEAVE_FROM_TIME, LEAVE_TO_TIME " + "FROM ESS_LEAVE_APPLY_TB " + "WHERE LEAVE_EMPID = ? " + "AND NOT EXISTS " + "(SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = LEAVE_NO AND APPLY_TYPE = 'LeaveApply' AND AFFIRM_FLAG = 2) " + "AND ACTIVITY = 0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essMatchBean.getEmpID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar leaveFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar leaveTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, leaveFrom, leaveTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essMatchBean.getChineseName());
					this.hashMap.put("errcode", new Integer(5));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 值班申请是否与已有休假申请冲突
	 * 
	 * @param essLeaveBean
	 */
	protected void matchConflictWithMatchApply(EssMatchBean essMatchBean) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT MATCH_FROM_TIME, MATCH_TO_TIME " + "FROM ESS_MATCH_APPLY_TB " + "WHERE MATCH_EMPID = ? " + "AND NOT EXISTS " + "(SELECT * FROM ESS_AFFIRM WHERE APPLY_NO = MATCH_NO AND APPLY_TYPE = 'MatchApply' AND AFFIRM_FLAG = 2) " + "AND ACTIVITY = 0";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essMatchBean.getEmpID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GregorianCalendar matchFrom = DateUtil.ParseGregorianCalendar(rs.getString(1));
				GregorianCalendar matchTo = DateUtil.ParseGregorianCalendar(rs.getString(2));
				if (DateUtil.DateCross(this.startTime, this.endTime, matchFrom, matchTo, "MILLISECOND") > 0) {
					this.hashMap.put("erremp", essMatchBean.getChineseName());
					this.hashMap.put("errcode", new Integer(6));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	private void otCheckOtMaxHours(EssOverTimeBean essOverTimeBean) {
		double otHours = 0.0; // 加班小时数
		double maxHours = 0.0 ; // 限制小时数
		String arMonth = "" ;
		String arStartDate = "" ;
		String arEndDate = "" ;
		String planMonth = "" ;
		
		EssOverTimeBean ot = null ;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "" ;
		
		
		
		try {
			
			 //得到考勤月，开始日期，结束日期
			sql = " SELECT TO_CHAR(ar_get_startdate_empid(AR_MONTH, ?),'YYYY-MM-DD') AS STARTDATE, " 
				+ " 	   TO_CHAR(ar_get_enddate_empid(AR_MONTH, ?),'YYYY-MM-DD') AS ENDDATE,AR_MONTH "
				+ " FROM DUAL,(SELECT get_armonth_empid(TO_DATE(?, 'YYYY-MM-DD') - ?, ?) AS AR_MONTH FROM DUAL) T" ;			
			Logger.getLogger(getClass()).debug("sql : " + sql);
			Logger.getLogger(getClass()).debug("Get Overtime OtDate : " + essOverTimeBean.getOtDate());
			Logger.getLogger(getClass()).debug("Get Overtime EmpId : " + essOverTimeBean.getPerson_id());
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, essOverTimeBean.getPerson_id());		
			pstmt.setString(3, essOverTimeBean.getOtDate());
			pstmt.setInt(4, essOverTimeBean.getOtNightFlag());
     		pstmt.setString(5, essOverTimeBean.getPerson_id());
			
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				arMonth = rs.getString("AR_MONTH") ;
				arStartDate = rs.getString("STARTDATE") ;
				arEndDate = rs.getString("ENDDATE") ;
			}
			
			
			// 得到加班上限
			sql = " SELECT OT.EMPID, OT.LIMIT_OT FROM AR_OVERTIME_PLAN OT WHERE OT.EMPID = ? AND (? BETWEEN OT.START_MONTH AND  OT.END_MONTH)" ;
			Logger.getLogger(getClass()).debug("sql : " + sql);
			Logger.getLogger(getClass()).debug("Get Overtime EmpId : " + essOverTimeBean.getPerson_id());
			Logger.getLogger(getClass()).debug("Get Overtime PlanMonth : " + arMonth);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, arMonth);
			
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				ot = new EssOverTimeBean() ;
				ot.setEmpId(rs.getString("EMPID")) ;
				ot.setLimit_ot(rs.getInt("LIMIT_OT")) ;
				ot.setOtPlanMonth(arMonth) ;
				maxHours = ot.getLimit_ot();
			}
			
			
			Logger.getLogger(getClass()).debug("maxHours : " + maxHours);
			if(ot!=null){
				// 取出加班小时数
				sql = " SELECT SUM(CASE WHEN NVL(AR.QUANTITY,-1) >= 0 THEN  AR.QUANTITY ELSE NVL(APPLY.OT_LENGTH,0) END ) OT_SUM "
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
					+ "        (SELECT AC.DDATE FROM AR_CALENDER AC WHERE AC.DDATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') AND AC.CPNY_ID=?) AR_DATE "
					+ "   WHERE AR_DATE.DDATE = APPLY.APPLY_DATE(+) AND AR_DATE.DDATE = AR.AR_DATE_STR(+) ";
				
				Logger.getLogger(getClass()).debug("Get Overtime Hours From AR_DETAIL : " + sql);
				Logger.getLogger(getClass()).debug("Get arStartDate : " + arStartDate);
				Logger.getLogger(getClass()).debug("Get arEndDate : " + arEndDate);
				Logger.getLogger(getClass()).debug("Get OtDate : " + essOverTimeBean.getOtDate());
				Logger.getLogger(getClass()).debug("Get empId : " + essOverTimeBean.getPerson_id());
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, essOverTimeBean.getPerson_id());
				pstmt.setString(2, arStartDate);
				pstmt.setString(3, arEndDate);
				pstmt.setString(4, essOverTimeBean.getPerson_id());
				pstmt.setString(5, ot.getOtPlanMonth());
				pstmt.setString(6, Configuration.getInstance().getString("/configuration/em2/ar-item-shortname/overtime", ""));
				pstmt.setString(7, arStartDate);
				pstmt.setString(8, arEndDate);
				pstmt.setString(9, ApplicationContext.getTheadLocal().getCpnyId());
				rs = pstmt.executeQuery();
				if (rs.next())
					otHours += rs.getDouble(1);
				Logger.getLogger(getClass()).debug("otHours : " + otHours);
				

				// 取出ess中，未确认和未被否决的申请加班小时数
				sql =  "SELECT NVL(SUM( CASE ESS.APPLY_OT_TYPE_CODE WHEN 'WorkingOtType01' THEN (TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG,'YYYY-MM-DD ') || OT_TO_TIME,'YYYY-MM-DD HH24:MI') - "
	                  +" TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME,'YYYY-MM-DD HH24:MI')) * 24 ELSE ESS.OT_LENGTH END - OT_DEDUCT_TIME),0)"
					  +" FROM (SELECT E.APPLY_OT_NO, E.APPLY_OT_DATE,E.APPLY_OT_FLAG,E.OT_TO_TIME,E.OT_FROM_TIME,E.OT_LENGTH,E.APPLY_OT_TYPE_CODE,E.OT_DEDUCT_TIME,E.ACTIVITY FROM ESS_APPLY_OT E WHERE EMPID = ?) ESS "
					  +" WHERE APPLY_OT_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') "
					  +" AND ACTIVITY = 0 ";
				Logger.getLogger(getClass()).debug("Get Overtime Hours From UnConfirmed Apply : " + sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, essOverTimeBean.getPerson_id());
				pstmt.setString(2, arStartDate);
				pstmt.setString(3, arEndDate);
				
				rs = pstmt.executeQuery();
				if (rs.next())
					otHours += rs.getDouble(1);
				
				Logger.getLogger(getClass()).debug("otHours : " + otHours);
				
				otHours -= essOverTimeBean.getOtDeduct();
				
				if (essOverTimeBean.getOtTypeCode().equals(otWorkCode))
				{
					otHours += DateUtil.DateDiff(startTime, endTime, "HOUR") ;
				}
				else
				{
					otHours += essOverTimeBean.getOtLength() ;
				}
				
				Logger.getLogger(getClass()).debug("otHours + applyHours  : " + otHours);			
					
				if (Integer.parseInt(hashMap.get("errcode").toString()) == 0 && maxHours - otHours < 0 ) {
						this.hashMap.put("errcode", new Integer(11));
						this.hashMap.put("erremp", essOverTimeBean.getChineseName());
						this.hashMap.put("planMonth", ot.getOtPlanMonth());
						this.hashMap.put("maxothour", String.valueOf(maxHours));
				}
			}
			else{
				this.hashMap.put("errcode", new Integer(18));
				this.hashMap.put("erremp", essOverTimeBean.getChineseName());
				this.hashMap.put("planMonth", arMonth);
				this.hashMap.put("maxothour", String.valueOf(maxHours));
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
			this.hashMap.put("errcode", new Integer(-1));
			this.hashMap.put("errmsg", e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

}
