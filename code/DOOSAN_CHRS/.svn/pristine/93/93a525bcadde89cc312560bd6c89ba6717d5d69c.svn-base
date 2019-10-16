package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.business.ArServices;
import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.EssUtil;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class AnnualBean {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(AnnualBean.class);

	public AnnualBean() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * 返回某人在当天的年假信息
	 * 
	 * @author Pennix
	 * @date 2006-04-11
	 * @param empId
	 *            员工号
	 */
	public Annual empAnnual(String empId) {
		return empAnnual(empId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

	/**
	 * 返回某人在某日的年假信息
	 * 
	 * @author Pennix
	 * @date 2006-04-11
	 * @param empId
	 *            员工号
	 * @param dateStr
	 *            日期字串(yyyy-MM-dd)
	 */
	public Annual empAnnual(String empId, String dateStr) {
		ArServices arServices =new ArServices();
		Logger.getLogger(getClass()).debug(empId);
		Logger.getLogger(getClass()).debug(dateStr);
		Annual info = new Annual();
		info.setEmpID(empId);
		info.setArdatestr(dateStr);	
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		SimpleMap sm = new SimpleMap();
		SimpleMap sm1 = new SimpleMap();
        sm.setString("adminId", empId);
        sm.setString("sysYear", new java.text.SimpleDateFormat("yyyy").format(new GregorianCalendar().getTime()));
        Double allTotVaction=0.0;       
        String vacationSdate="";
        String vacationDdate="";
		List  list= arServices.retrieveVacationEmpREST_VAC_CNT(sm) ;
		for (int i=0;i<list.size();i++){
			sm1=(SimpleMap)list.get(i);
			allTotVaction=Double.parseDouble((sm1.get("ALLTOTVACTION") != null ? sm1.get("ALLTOTVACTION").toString() : "0")) ;			
			vacationSdate=sm1.getString("STRT_DATE");
			vacationDdate=sm1.getString("END_DATE");
		}	
		///info.setAnnualHours(allTotVaction*8);
		
		try {
			conn = ConnBean.getConn();
			String sql="";				
					sql = "SELECT SUM(QUANTITY) FROM AR_DETAIL t WHERE EMPID = ? AND AR_ITEM_NO = 14 AND (TO_DATE(T.AR_DATE_STR,'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD') ) ";
					Logger.getLogger(getClass()).debug(sql);
					ps = conn.prepareStatement(sql);
					ps.setString(1, empId);
					ps.setString(2, vacationSdate);
					ps.setString(3, vacationDdate);
					Logger.getLogger(getClass()).debug("" +vacationSdate+",,,,,,,,,,,, END "+vacationDdate);
					rs = ps.executeQuery();
					if (rs.next()) {
						info.setAnnualHoursUsed(rs.getDouble(1));
						// 取ESS申请中的未决年假信息
						sql =      " select T.LEAVE_NO  "+ 
						           "   from ess_leave_apply_tb t "+ 
								   "  WHERE NOT EXISTS (SELECT 1 "+ 
								   "         FROM ESS_AFFIRM "+ 
								   "         WHERE APPLY_TYPE = 'LeaveApply' "+ 
								   "          AND APPLY_NO = LEAVE_NO "+ 
								   "         AND AFFIRM_FLAG = 2) "+ 
								   "    AND ACTIVITY = 0 "+ 
								   "    AND LEAVE_EMPID = ? "+ 
								   "    AND LEAVE_TYPE = ? "+ 
								   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD') AND  TO_DATE(?,'YYYY-MM-DD')) "+ 
								   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_TO_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD')  BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD')) ";
						Logger.getLogger(getClass()).debug(sql);
						ps = conn.prepareStatement(sql);
						ps.setString(1, empId);
						ps.setString(2,  Configuration.getInstance().getString("/configuration/em2/syscode/annual-code", ""));
						ps.setString(3, vacationSdate);
						ps.setString(4, vacationDdate);
						ps.setString(5, vacationSdate);
						ps.setString(6, vacationDdate);
						rs = ps.executeQuery();
						while (rs.next()) {
							info.setAnnualHoursUsed(info.getAnnualHoursUsed() + EssUtil.getActualLeaveHour(rs.getInt(1)));
						}
					} else {
						info.setAnnualHoursUsed(Double.valueOf(0));
					}
					info.setAnnualHoursLeft(allTotVaction*8 - info.getAnnualHoursUsed());			
					Logger.getLogger(getClass()).debug("" + (allTotVaction*8 - info.getAnnualHoursUsed()));
			
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, ps, conn);
		}
		return info;
	}
	
	/**
	 * 返回某人剩余年假信息
	 * 
	 * @author yangxiaohui
	 * @date 2006-04-11
	 * @param empId
	 *            员工号
	 * @param dateStr
	 *            日期字串(yyyy-MM-dd)
	 */
	public Annual empAnnualLeft(String empId, String dateStr) {
		ArServices arServices =new ArServices();
		Logger.getLogger(getClass()).debug(empId);
		Logger.getLogger(getClass()).debug(dateStr);
		Annual info = new Annual();
		info.setEmpID(empId);
		info.setArdatestr(dateStr);	
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		SimpleMap sm = new SimpleMap();
		SimpleMap sm1 = new SimpleMap();
        sm.setString("adminId", empId);
        sm.setString("sysYear", new java.text.SimpleDateFormat("yyyy").format(new GregorianCalendar().getTime()));
        Double allTotVaction=0.0;       
        float workYear = 0 ;
        String vacationSdate="";
        String vacationDdate="";
		List  list= arServices.retrieveVacationEmpREST_VAC_CNT(sm) ;
		for (int i=0;i<list.size();i++){
			sm1=(SimpleMap)list.get(i);
			allTotVaction=Double.parseDouble((sm1.get("ALLTOTVACTION") != null ? sm1.get("ALLTOTVACTION").toString() : "0")) ;
			workYear = Float.parseFloat((sm1.get("WORK_YEAR") != null ? sm1.get("WORK_YEAR").toString() : "0")) ;
			vacationSdate=sm1.getString("STRT_DATE");
			vacationDdate=sm1.getString("END_DATE");
		}	
		info.setAnnualHours(allTotVaction);
		info.setWorkYear(workYear) ;
		
		try {
			conn = ConnBean.getConn();
			String sql="";				
					sql = "SELECT SUM(QUANTITY) FROM AR_DETAIL t WHERE EMPID = ? AND AR_ITEM_NO = 14 AND (TO_DATE(T.AR_DATE_STR,'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD') ) ";
					Logger.getLogger(getClass()).debug(sql);
					ps = conn.prepareStatement(sql);
					ps.setString(1, empId);
					ps.setString(2, vacationSdate);
					ps.setString(3, vacationDdate);
					Logger.getLogger(getClass()).debug("" +vacationSdate+",,,,,,,,,,,, END "+vacationDdate);
					rs = ps.executeQuery();
					if (rs.next()) {
						info.setAnnualHoursUsed(rs.getDouble(1));
						info.setArAnnualHoursUsed(rs.getDouble(1));
						// 取ESS申请中的未决年假信息
						sql =      " select T.LEAVE_NO  "+ 
						           "   from ess_leave_apply_tb t "+ 
								   "  WHERE NOT EXISTS (SELECT 1 "+ 
								   "         FROM ESS_AFFIRM "+ 
								   "         WHERE APPLY_TYPE = 'LeaveApply' "+ 
								   "          AND APPLY_NO = LEAVE_NO "+ 
								   "         AND AFFIRM_FLAG = 2) "+ 
								   "    AND ACTIVITY = 0 "+ 
								   "    AND LEAVE_EMPID = ? "+ 
								   "    AND LEAVE_TYPE = ? "+ 
								   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD') AND  TO_DATE(?,'YYYY-MM-DD')) "+ 
								   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_TO_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD')  BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD')) ";
						Logger.getLogger(getClass()).debug(sql);
						ps = conn.prepareStatement(sql);
						ps.setString(1, empId);
						ps.setString(2,  Configuration.getInstance().getString("/configuration/em2/syscode/annual-code", ""));
						ps.setString(3, vacationSdate);
						ps.setString(4, vacationDdate);
						ps.setString(5, vacationSdate);
						ps.setString(6, vacationDdate);
						rs = ps.executeQuery();
						while (rs.next()) {
							info.setAnnualHoursUsed(info.getAnnualHoursUsed() + EssUtil.getActualLeaveHour(rs.getInt(1)));
						}
					} else {
						info.setAnnualHoursUsed(Double.valueOf(0));
					}
					info.setAnnualHoursLeft(Double.parseDouble(NumberUtil.formatNumber((allTotVaction*8 - info.getAnnualHoursUsed())/8,"0.0")));
					Logger.getLogger(getClass()).debug("parseDouble " + NumberUtil.formatNumber((allTotVaction*8 - info.getAnnualHoursUsed())/8,"0.0"));
			
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, ps, conn);
		}
		return info;
	}
	
	
	
	
	/**
	 * 返回某人剩余有薪病假信息
	 * 
	 * @author wangbin
	 * @date 2009-09-7
	 * @param empId(员工号)
	 * @param dateStr 日期字串(yyyy-MM-dd)
	 */
	public SimpleMap retriveSickLeave(String empId)throws GlRuntimeException{
		Logger logger = Logger.getLogger(getClass());
		SimpleMap simpleMap = new SimpleMap();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArServices services = new ArServices();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("adminId", empId);
		parameterObject.setString("sysYear", new java.text.SimpleDateFormat("yyyy").format(new GregorianCalendar().getTime()));
		parameterObject.setString("VacType", "sickLeave");
		List totSickLeave = services.retrieveVacationEmpREST_VAC_CNT(parameterObject);
		Iterator iter = totSickLeave.iterator();
		Double totalSickLeaveHours = 0.0;
		String start_date = "";
		String end_date = "";
		for(;iter.hasNext();){
			SimpleMap resultObject = (SimpleMap)iter.next();
			totalSickLeaveHours = Double.parseDouble((resultObject.getString("ALLTOTVACTION")!=null? resultObject.getString("ALLTOTVACTION"):"0"));
			start_date = resultObject.getString("STRT_DATE");
			end_date = resultObject.getString("END_DATE");
		}
		simpleMap.setDouble("totalSickLeaveHours", totalSickLeaveHours);
		logger.debug("totalSickLeaveHours: "+simpleMap.getDouble("totalSickLeaveHours"));
		try{
			conn = ConnBean.getConn();
			String sql = "SELECT SUM(QUANTITY) FROM AR_DETAIL t WHERE EMPID = ? AND AR_ITEM_NO = 72 AND (TO_DATE(T.AR_DATE_STR,'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD') )";
			logger.debug(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ps.setString(2, start_date);
			ps.setString(3, end_date);
			rs = ps.executeQuery();
			logger.debug("empid:"+empId);
			logger.debug("start_date:"+start_date);
			logger.debug("end_date:"+end_date);
			if(rs.next()){
				simpleMap.setDouble("sickLeaveUsed",rs.getDouble(1));
				logger.debug("sickLeave:"+rs.getDouble(1));
//				 取ESS申请中的未决年假信息
				sql =      " select T.LEAVE_NO  "+ 
				           "   from ess_leave_apply_tb t "+ 
						   "  WHERE NOT EXISTS (SELECT 1 "+ 
						   "         FROM ESS_AFFIRM "+ 
						   "         WHERE APPLY_TYPE = 'LeaveApply' "+ 
						   "          AND APPLY_NO = LEAVE_NO "+ 
						   "         AND AFFIRM_FLAG = 2) "+ 
						   "    AND ACTIVITY = 0 "+ 
						   "    AND LEAVE_EMPID = ? "+ 
						   "    AND LEAVE_TYPE = ? "+ 
						   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD') AND  TO_DATE(?,'YYYY-MM-DD')) "+ 
						   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_TO_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD')  BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD')) ";
				logger.debug(sql);
				ps = conn.prepareStatement(sql);
				ps.setString(1, empId);
				ps.setString(2, Configuration.getInstance().getString("/configuration/em2/syscode/sickLeave", ""));
				logger.debug("em2.syscode.sickLeave:"+Configuration.getInstance().getString("/configuration/em2/syscode/sickLeave", ""));
				ps.setString(3, start_date);
				ps.setString(4, end_date);
				ps.setString(5, start_date);
				ps.setString(6, end_date);
				rs = ps.executeQuery();
				while(rs.next()){
					simpleMap.setDouble("sickLeaveUsed", simpleMap.getDouble("sickLeaveUsed") + EssUtil.getActualLeaveHour(rs.getInt(1)));
					logger.debug("sickLeaveUsed:"+simpleMap.getDouble("sickLeaveUsed"));
				}
			}else{
				simpleMap.setDouble("sickLeaveUsed", Double.valueOf(0));
				logger.debug("sickLeaveUsed:"+simpleMap.getDouble("sickLeaveUsed"));
			}
			simpleMap.setDouble("sickLeaveLeft",Double.parseDouble(NumberUtil.formatNumber((totalSickLeaveHours*8 - simpleMap.getDouble("sickLeaveUsed"))/8,"0")));
			logger.debug("sickLeaveLeft:"+simpleMap.getDouble("sickLeaveLeft"));
			
			sql = "SELECT T.CPNY_ID FROM SY_CODE T WHERE T.CODE_ID = 'H29'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				simpleMap.setString("cpnyId", rs.getString(1));
				logger.debug("cpnyId: "+rs.getString(1));
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve sick leave data Exception.",e);
		}finally{
			SqlUtil.close(rs,ps,conn);
		}
		
		return simpleMap;
	}

	/**
	 * 返回某人剩余有团聚假信息
	 * 
	 * @author zhangji
	 * @date 2011-01-11
	 * @param empId(员工号)
	 * @param dateStr 日期字串(yyyy-MM-dd)
	 */
	public SimpleMap retriveReuniteLeave(String empId)throws GlRuntimeException{
		Logger logger = Logger.getLogger(getClass());
		SimpleMap simpleMap = new SimpleMap();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArServices services = new ArServices();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("adminId", empId);
		parameterObject.setString("sysYear", new java.text.SimpleDateFormat("yyyy").format(new GregorianCalendar().getTime()));
		parameterObject.setString("VacType", "reuniteLeave");
		List totReuniteLeave = services.retrieveVacationEmpREST_VAC_CNT(parameterObject);
		Iterator iter = totReuniteLeave.iterator();
		Double totalReuniteLeaveHours = 0.0;
		String start_date = "";
		String end_date = "";
		for(;iter.hasNext();){
			SimpleMap resultObject = (SimpleMap)iter.next();
			totalReuniteLeaveHours = Double.parseDouble((resultObject.getString("ALLTOTVACTION")!=null? resultObject.getString("ALLTOTVACTION"):"0"));
			start_date = resultObject.getString("STRT_DATE");
			end_date = resultObject.getString("END_DATE");
		}
		simpleMap.setDouble("totalReuniteLeaveHours", totalReuniteLeaveHours);
		logger.debug("totalReuniteLeaveHours: "+simpleMap.getDouble("totalReuniteLeaveHours"));
		try{
			conn = ConnBean.getConn();
			String sql = "SELECT SUM(QUANTITY) FROM AR_DETAIL t WHERE EMPID = ? AND AR_ITEM_NO = 76 AND (TO_DATE(T.AR_DATE_STR,'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD') )";
			logger.debug(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ps.setString(2, start_date);
			ps.setString(3, end_date);
			rs = ps.executeQuery();
			logger.debug("empid:"+empId);
			logger.debug("start_date:"+start_date);
			logger.debug("end_date:"+end_date);
			if(rs.next()){
				simpleMap.setDouble("reuniteLeaveUsed",rs.getDouble(1));
				logger.debug("reuniteLeave:"+rs.getDouble(1));
//				 取ESS申请中的未决团聚假信息
				sql =      " select T.LEAVE_NO  "+ 
				           "   from ess_leave_apply_tb t "+ 
						   "  WHERE NOT EXISTS (SELECT 1 "+ 
						   "         FROM ESS_AFFIRM "+ 
						   "         WHERE APPLY_TYPE = 'LeaveApply' "+ 
						   "          AND APPLY_NO = LEAVE_NO "+ 
						   "         AND AFFIRM_FLAG = 2) "+ 
						   "    AND ACTIVITY = 0 "+ 
						   "    AND LEAVE_EMPID = ? "+ 
						   "    AND LEAVE_TYPE = ? "+ 
						   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD') AND  TO_DATE(?,'YYYY-MM-DD')) "+ 
						   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_TO_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD')  BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD')) ";
				logger.debug(sql);
				ps = conn.prepareStatement(sql);
				ps.setString(1, empId);
				ps.setString(2, Configuration.getInstance().getString("/configuration/em2/syscode/reuniteLeave", ""));
				logger.debug("em2.syscode.reuniteLeave:"+Configuration.getInstance().getString("/configuration/em2/syscode/reuniteLeave", ""));
				ps.setString(3, start_date);
				ps.setString(4, end_date);
				ps.setString(5, start_date);
				ps.setString(6, end_date);
				rs = ps.executeQuery();
				while(rs.next()){
					simpleMap.setDouble("reuniteLeaveUsed", simpleMap.getDouble("reuniteLeaveUsed") + EssUtil.getActualLeaveHour(rs.getInt(1)));
					logger.debug("reuniteLeaveUsed:"+simpleMap.getDouble("reuniteLeaveUsed"));
				}
			}else{
				simpleMap.setDouble("reuniteLeaveUsed", Double.valueOf(0));
				logger.debug("reuniteLeaveUsed:"+simpleMap.getDouble("reuniteLeaveUsed"));
			}
			simpleMap.setDouble("reuniteLeaveLeft",Double.parseDouble(NumberUtil.formatNumber((totalReuniteLeaveHours*8 - simpleMap.getDouble("reuniteLeaveUsed"))/8,"0")));
			logger.debug("reuniteLeaveLeft:"+simpleMap.getDouble("reuniteLeaveLeft"));
			
			sql = "SELECT T.CPNY_ID FROM SY_CODE T WHERE T.CODE_ID = 'H29'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				simpleMap.setString("cpnyId", rs.getString(1));
				logger.debug("cpnyId: "+rs.getString(1));
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve reunite leave data Exception.",e);
		}finally{
			SqlUtil.close(rs,ps,conn);
		}
		
		return simpleMap;
	}
	
	/**
	 * 返回某人上年剩余年假信息
	 * 
	 * @author zhangji
	 * @date 2011-01-11
	 * @param empId(员工号)
	 * @param dateStr 日期字串(yyyy-MM-dd)
	 */
	public SimpleMap lastAnnualVacation(String empId)throws GlRuntimeException{
		Logger logger = Logger.getLogger(getClass());
		SimpleMap simpleMap = new SimpleMap();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArServices services = new ArServices();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("adminId", empId);
		parameterObject.setString("sysYear", new java.text.SimpleDateFormat("yyyy").format(new GregorianCalendar().getTime()));
		parameterObject.setString("VacType", "lastAnnualVacation");
		List toLastAnnualVacationLeave = services.retrieveVacationEmpREST_VAC_CNT(parameterObject);
		Iterator iter = toLastAnnualVacationLeave.iterator();
		Double totalLastAnnualVacation = 0.0;
		String start_date = "";
		String end_date = "";
		for(;iter.hasNext();){
			SimpleMap resultObject = (SimpleMap)iter.next();
			totalLastAnnualVacation = Double.parseDouble((resultObject.getString("ALLTOTVACTION")!=null? resultObject.getString("ALLTOTVACTION"):"0"));
			start_date = resultObject.getString("STRT_DATE");
			end_date = resultObject.getString("END_DATE");
		}
		simpleMap.setDouble("totalLastAnnualVacation", totalLastAnnualVacation);
		logger.debug("totalLastAnnualVacation: "+simpleMap.getDouble("totalLastAnnualVacation"));
		try{
			conn = ConnBean.getConn();
			String sql = "SELECT SUM(QUANTITY) FROM AR_DETAIL t WHERE EMPID = ? AND AR_ITEM_NO = 83 AND (TO_DATE(T.AR_DATE_STR,'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD') )";
			logger.debug(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ps.setString(2, start_date);
			ps.setString(3, end_date);
			rs = ps.executeQuery();
			logger.debug("empid:"+empId);
			logger.debug("start_date:"+start_date);
			logger.debug("end_date:"+end_date);
			if(rs.next()){
				simpleMap.setDouble("lastAnnualVacationUsed",rs.getDouble(1));
				logger.debug("lastAnnualVacation:"+rs.getDouble(1));
//				 取ESS申请中的未决上年剩余年假信息
				sql =      " select T.LEAVE_NO  "+ 
				           "   from ess_leave_apply_tb t "+ 
						   "  WHERE NOT EXISTS (SELECT 1 "+ 
						   "         FROM ESS_AFFIRM "+ 
						   "         WHERE APPLY_TYPE = 'LeaveApply' "+ 
						   "          AND APPLY_NO = LEAVE_NO "+ 
						   "         AND AFFIRM_FLAG = 2) "+ 
						   "    AND ACTIVITY = 0 "+ 
						   "    AND LEAVE_EMPID = ? "+ 
						   "    AND LEAVE_TYPE = ? "+ 
						   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN  TO_DATE(?,'YYYY-MM-DD') AND  TO_DATE(?,'YYYY-MM-DD')) "+ 
						   " 	 AND ( TO_DATE(TO_CHAR(T.LEAVE_TO_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD')  BETWEEN  TO_DATE(?,'YYYY-MM-DD')  AND  TO_DATE(?,'YYYY-MM-DD')) ";
				logger.debug(sql);
				ps = conn.prepareStatement(sql);
				ps.setString(1, empId);
				ps.setString(2, "H91");
			
				ps.setString(3, start_date);
				ps.setString(4, end_date);
				ps.setString(5, start_date);
				ps.setString(6, end_date);
				rs = ps.executeQuery();
				while(rs.next()){
					simpleMap.setDouble("lastAnnualVacationUsed", simpleMap.getDouble("lastAnnualVacationUsed") + EssUtil.getActualLeaveHour(rs.getInt(1)));
					logger.debug("lastAnnualVacationUsed:"+simpleMap.getDouble("lastAnnualVacationUsed"));
				}
			}else{
				simpleMap.setDouble("lastAnnualVacationUsed", Double.valueOf(0));
				logger.debug("lastAnnualVacationUsed:"+simpleMap.getDouble("lastAnnualVacationUsed"));
			}
			simpleMap.setDouble("lastAnnualVacationLeft",Double.parseDouble(NumberUtil.formatNumber((totalLastAnnualVacation*8 - simpleMap.getDouble("lastAnnualVacationUsed"))/8,"0")));
			logger.debug("lastAnnualVacationLeft:"+simpleMap.getDouble("lastAnnualVacationLeft"));
			
			sql = "SELECT T.CPNY_ID FROM SY_CODE T WHERE T.CODE_ID = 'H91'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				simpleMap.setString("cpnyId", rs.getString(1));
				logger.debug("cpnyId: "+rs.getString(1));
			}
			
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve reunite leave data Exception.",e);
		}finally{
			SqlUtil.close(rs,ps,conn);
		}
		
		return simpleMap;
	}
	
	
	/**
	 * retrieve annual holidy list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveAnnualHolidyList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAnnualHolidyList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve annual holidy list Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve annual holidy list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveAnnualHolidy(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAnnualHolidyList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve annual holidy data Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve annual holidy list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveAnnualHolidyCnt(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery("ar.common.RetrieveAnnualHolidyCnt", parameterObject).toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve annual holidy list count Exception. ", e);
		}
		return result;
	}

	/**
	 * insert annual holidy
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertAnnualHolidy(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ar.common.insertAnnualHolidy", parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert annual holidy Exception. ", e);
		}
	}

	/**
	 * insert annual holidy
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAnnualHolidy(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertAnnualHolidy", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert annual holidy Exception. ", e);
		}
	}

	/**
	 * update annual holidy
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateAnnualHolidy(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("ar.common.updateAnnualHolidy", parameterObject, isAutoTrans);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update annual holidy Exception. ", e);
		}
	}

	/**
	 * update annual holidy
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAnnualHolidy(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.common.updateAnnualHolidy", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update annual holidy Exception. ", e);
		}
	}

	/**
	 * delete imported annual temp Data
	 * 
	 * @throws GlRuntimeException
	 */
	public void deleteAnnualImportTempData() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.delete("ar.common.deleteAnnualImportTempData");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete imported annual temp Data Exception. ", e);
		}
	}

	/**
	 * insert annual holidy by import data
	 * 
	 * @throws GlRuntimeException
	 */
	public void insertAnnualHolidyByImport() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ar.common.insertAnnualHolidyByImport");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert annual holidy by import data Exception. ", e);
		}
	}

	/**
	 * update annual holidy by import data
	 * 
	 * @throws GlRuntimeException
	 */
	public void updateAnnualHolidyByImport() throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ar.common.updateAnnualHolidyByImport");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update annual holidy by import data Exception. ", e);
		}
	}

	/**
	 * retrieve annual holidy report data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAnnualHolidyReport(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveAnnualHolidyReport", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve annual holidy report data Exception. ", e);
		}
		return list;
	}

	public String checkTime(String str) {
		return str.substring(0, str.indexOf(" "));
	}
}
