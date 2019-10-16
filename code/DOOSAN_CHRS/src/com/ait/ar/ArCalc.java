package com.ait.ar;

import java.lang.Thread.State;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;

import com.ait.ar.business.ArServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.PaCalcUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
              
public class ArCalc {      

	public boolean isDebug = false;

	private ArServices services;

	public ArCalc() {

		services = new ArServices();
	}

	public ArCalc(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public String CalcMonth(String armonth,String statTypeCode, AdminBean admin,MessageSource messageSource) {
		//计算失败
		String fail="";
		if(admin.getLanguagePreference().equals("zh")){
			fail="[计算失败]";
		}else if(admin.getLanguagePreference().equals("ko")){
			fail="[计算失败]";
		}else{
			fail="["+messageSource.getMessage("display.att.maintenance.calculation.fail")+"]";
		}
		// validate monthly status
		if (!this.validateMonthlyStatus(armonth, admin,statTypeCode)) {
			return "<font color='Red'>对不起，月考勤已锁定，汇总计算不能进行</font>";
		}
		
		String result = "";
		if (PaCalcUtil.getPaCalcFlag() == 0 )
		{
			Connection conn = ConnBean.getConn();
			CallableStatement cs = null;
			String sql = "{CALL AR_MONTH_CAL(?,?,?,?)}";
			Logger.getLogger(getClass()).debug(sql);
			Logger.getLogger(getClass()).debug(armonth);
			Logger.getLogger(getClass()).debug(statTypeCode);
			Logger.getLogger(getClass()).debug(admin.getCpnyId());
			Logger.getLogger(getClass()).debug(admin.getAdminID());
			try {
				cs = conn.prepareCall(sql);
				cs.setString(1, armonth);
				cs.setString(2, statTypeCode);
				cs.setString(3, admin.getCpnyId());
				cs.setString(4, admin.getAdminID());
				
				cs.registerOutParameter(4, Types.VARCHAR);
				if (cs.executeUpdate() == 1)
					result = cs.getString(4);
				else
					result = fail+"<br>未知错误";
			} catch (SQLException e) {
				result = fail + "<br>" + this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
				Logger.getLogger(getClass()).error(this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", ""));
			} finally {
				SqlUtil.close(cs, conn);
				PaCalcUtil.setPaCalcFlag(0) ;
			}
		}
		else
		{
			result = "<font color='Red'>对不起，月考勤正在计算中，请稍后进行汇总计算</font>";
		}
		
		return result;
	}
	
	public ArrayList<String> CalcDetail(String startdate, String enddate, String calType, String supervisorId, String deptId, String empId, AdminBean admin) {

		long startTime = System.currentTimeMillis();
		int calcThreadListSize = 0;

		GregorianCalendar sDate = DateUtil.ParseGregorianCalendar(startdate);
		GregorianCalendar eDate = DateUtil.ParseGregorianCalendar(enddate);
		int cnt = (int)Float.parseFloat(String.valueOf(DateUtil.DateDiff(sDate, eDate, "DAY"))) + 1;
		List<CalMultiTread> calcThreadList = new ArrayList<CalMultiTread>(cnt);

		ArrayList<String> resultList = new ArrayList<String>();

		CalMultiTread cmt;
		// 据不同日期生成一个线程用于调用明细计算存储
		for (int i = 0; i < cnt; i++) {

			GregorianCalendar date = new GregorianCalendar();
			date.setTimeInMillis(sDate.getTimeInMillis());
			cmt = new CalMultiTread("thread " + String.valueOf(i + 1));
			
			// 添加进程
			calcThreadList.add(cmt);
			
			cmt.setStartdate(new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.DateAdd(date, "DAY", i).getTime()));
			cmt.setEnddate(cmt.getStartdate());
			Logger.getLogger(getClass()).debug("date: " + cmt.getStartdate());
			cmt.setCnpyId(admin.getCpnyId());
			cmt.setCalType(calType);
			cmt.setSupervisorId( calType.equals("SUPERVISOR") ? supervisorId : admin.getAdminID() );
			cmt.setDeptId(deptId);
			cmt.setEmpId(empId);
			cmt.calc();
			
		}

		// 无限循环,calcThreadListSize,等于线程数时(cnt),退出
		while (calcThreadListSize < cnt) {

			try {
				if (calType.equals("EMP")) { // EMP(员工别)
					Thread.currentThread().sleep(1000);// 静态方法,使当前正在执行的线程睡眠(1000)一秒
				} else {
					Thread.currentThread().sleep(10000);// 静态方法,使当前正在执行的线程睡眠(10000)十秒
				}
			} catch (InterruptedException ie) {
				ie.printStackTrace();
				Logger.getLogger(this.getClass()).error("线程被中断");
			}
			
			for (int i = 0; i < calcThreadList.size(); ++i) {
				CalMultiTread calThread = (CalMultiTread) calcThreadList.get(i);
				// 结束状态,删去
				if (calThread.getState() == State.TERMINATED) {
					++calcThreadListSize;
					if (calThread.getResult() != null && calThread.getResult().length() > 0) {
						resultList.add(calThread.getResult()) ;
					}
					calcThreadList.remove(i);
				}
			}
		}
		
		
		if (resultList.isEmpty()) {
			resultList.add( cnt + "天 明细计算完成,用时 " + Math.floor(((double) (System.currentTimeMillis() - startTime) / 1000 / 60) * 10 + 0.5) / 10 + " 分钟");
		}
		
		
		return resultList;
	}
	
	public String CalcDetail(String startdate, String enddate, String calType,
			String supervisorId, String deptId, String empId) {
		Logger.getLogger(this.getClass()).debug(
				startdate + "---" + enddate + "---" + calType + "---"
						+ supervisorId + "---" + deptId + "---" + empId);
		Connection conn = ConnBean.getConn();
		CallableStatement cs = null;
		String result = "";
		String sql = "{CALL AR_DETAIL_CAL(?, ?, ?, ?, ?, ?)}";
		try {
			cs = conn.prepareCall(sql);
			cs.setString(1, startdate);
			cs.setString(2, enddate);
			cs.setString(3, calType);
			cs.setString(4, supervisorId);
			cs.setString(5, deptId);
			cs.setString(6, empId);
			cs.registerOutParameter(6, Types.VARCHAR);
			Logger.getLogger(getClass()).debug(startdate);
			Logger.getLogger(getClass()).debug(enddate);
			if (cs.executeUpdate() == 1)
				result = "<br>" + cs.getString(6);
			else
				result = "[计算失败]<br>未知错误";
			Logger.getLogger(getClass()).debug(result);
		} catch (SQLException e) {
			result = "[计算失败]<br>"
					+ this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):",
							"<br>");
			Logger.getLogger(getClass()).error(
					this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", ""));
		} finally {
			SqlUtil.close(cs, conn);
		}
		return result;
	}
	
	public String CalcExceptionDetail(String startdate, String enddate, AdminBean admin) {
		Logger.getLogger(this.getClass()).debug(startdate + "---" + enddate );
		Connection conn = ConnBean.getConn();
		CallableStatement cs = null;
		String result = "";
		String sql = "{CALL AR_DETAIL_EXCEPTION_CAL(?, ?, ?, ?)}";
		try {
			cs = conn.prepareCall(sql);
			cs.setString(1, startdate);
			cs.setString(2, enddate);
			cs.setString(3, admin.getCpnyId());
			cs.registerOutParameter(4, Types.VARCHAR);
			if (cs.executeUpdate() == 1)
				result = "<br>" + cs.getString(4);
			else
				result = "[计算失败]<br>未知错误";
			Logger.getLogger(getClass()).debug(result);
		} catch (SQLException e) {
			result = "[计算失败]<br>"
					+ this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):",
							"<br>");
			Logger.getLogger(getClass()).error(
					this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", ""));
		} finally {
			SqlUtil.close(cs, conn);
		}
		return result;
	}
	
	public String CalcDetail(String startdate, String enddate, String supervisorId) {
		
		Logger.getLogger(this.getClass()).debug(
				startdate + "---" + enddate + "---" + supervisorId );
		
		List<SimpleMap> calcList = new ArrayList<SimpleMap>();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("startDate", startdate);
		parameterObject.setString("endDate", enddate);
		parameterObject.setString("supervisor", supervisorId);

		try {
			
			List<SimpleMap> dateList = services.retrieveDateListForCalc(parameterObject);
			
			for (SimpleMap dateMap : dateList) {
				
				parameterObject.setString("date_str", dateMap.getString("DATE_STR"));
				List<SimpleMap> empList = services.retrieveEmpListBySupervisor(parameterObject);
				
				for (SimpleMap empMap : empList) {
					
					SimpleMap obj = new SimpleMap();
					obj.setString("date", dateMap.getString("DATE_STR"));
					obj.setString("empId", empMap.getString("EMPID"));
					obj.setString("message", "");
					calcList.add(obj);
				}
			}
			
			// calculate attendance detail by batch
			services.calculateAttDetail(calcList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Calculate fail!";
		}
		
		return "Calculate Success!";
	}

	public String CalcProcess(String armonth, String empid) {
		Connection conn = ConnBean.getConn();
		CallableStatement cs = null;
		String result = null;
		String sql = "{CALL AR_MONTH_CAL(?,?)}";
		Logger.getLogger(getClass()).debug(sql);
		try {
			cs = conn.prepareCall(sql);
			cs.setString(1, armonth);
			cs.setString(2, empid);
			Logger.getLogger(getClass()).debug(armonth);
			Logger.getLogger(getClass()).debug(empid);
			cs.registerOutParameter(2, Types.VARCHAR);
			if (cs.executeUpdate() == 1)
				result = cs.getString(2);
			else
				result = "[计算失败]<br>未知错误";
		} catch (SQLException e) {
			result = "[计算失败]<br>"
					+ this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):",
							"<br>");
			Logger.getLogger(getClass()).error(
					this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", ""));
		} finally {
			SqlUtil.close(cs, conn);
		}
		return result;
	}

	private String Message(String s, String reg, String to) {
		RE re = new RE(reg);
		while (re.match(s)) {
			s = s.replaceAll(re.getParen(0), to);
		}
		return s;
	}

	/**
	 * validate monthly status
	 * 
	 * @param armonth
	 * @param adminID
	 * @return boolean
	 */
	private boolean validateMonthlyStatus(String armonth, AdminBean admin,String statTypeCode) {

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("PA_MONTH_STR", armonth);
		parameterObject.setString("LOCK_USER_ID", admin.getAdminID());
		parameterObject.setString("CPNY_ID", admin.getCpnyId());
		parameterObject.setString("STAT_TYPE_CODE", statTypeCode);
		
		int result;

		try {
			// 插入月状态信息
			services.insertMonthlyStatus(parameterObject);
		} catch (Exception e) {

		}

		// 取得月考勤状态
		List list = services.retrieveMonthlyStatusList(parameterObject);

		return Integer.parseInt(((SimpleMap) list.get(0))
				.getString("ATT_MO_LOCK_FLAG")) == 0 ? true : false;
	}

}
