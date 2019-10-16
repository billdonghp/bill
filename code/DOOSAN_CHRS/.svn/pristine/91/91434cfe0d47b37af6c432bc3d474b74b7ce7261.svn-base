/*
 * @(#)RetrieveDetailLockListCmd.java 1.0 2008-3-4 上午11:46:19
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.detaillock;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.CalendarDay;
import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2008-3-4 上午11:46:19
 * @version 1.0
 * 
 */
public class RetrieveDetailSupervisorLockListCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute("admin"));
		SimpleMap parameterObject;
		String url = "" ;
		
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());
			parameterObject.set("cpnyId", admin.getCpnyId());
			parameterObject.setString("CPNY_ID", admin.getCpnyId());
			//针对DICC支社部分人要对所属部门的考勤员进行锁定和开锁
		    if( admin.getPersonId().equals("1464169")||
				admin.getPersonId().equals("122558")|| 
				admin.getPersonId().equals("214201")|| 
				admin.getPersonId().equals("214247")|| 
				admin.getPersonId().equals("214266")|| 
				admin.getPersonId().equals("1464747")|| 
				admin.getPersonId().equals("210208")|| 
				admin.getPersonId().equals("210891")|| 
				admin.getPersonId().equals("212475")|| 
				admin.getPersonId().equals("213543")|| 
				admin.getPersonId().equals("221052")|| 
				admin.getPersonId().equals("98107")|| 
				admin.getPersonId().equals("214272")|| 
				admin.getPersonId().equals("121117")|| 
				admin.getPersonId().equals("430908")||
				admin.getPersonId().equals("4466057")||
				admin.getPersonId().equals("222710")
			  ){
				parameterObject.setString("deptid", admin.getDeptID());
			   }
			
			parameterObject.setString("STAT_TYPE_CODE", parameterObject.getString("statTypeCode"));
			
			// 取日期参数
			GregorianCalendar currentDay = new GregorianCalendar();
			int m = currentDay.get(Calendar.MONTH) + 1;
			int y = currentDay.get(Calendar.YEAR);
			String month = ("0" + String.valueOf(m)).substring(("0" + String
					.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
			String year = String.valueOf(y);
			String arMonth = year + month;
			if (parameterObject.getString("month") != null && parameterObject.getString("year") != null) {
					month = parameterObject.getString("month");
					year = parameterObject.getString("year");
					arMonth = year + month;
			}
			parameterObject.setString("AR_MONTH_STR", arMonth);
			parameterObject.setString("PA_MONTH_STR", arMonth);
			
			
			// 日考勤锁定
			List calendarList = new ArrayList() ;
			CalendarDay day = null ;
			StringBuffer arCalenderSql = new StringBuffer(500) ;
			List detailLockList = services.retrieveDetailLockList(parameterObject);
			for (int i = 0 ; i < detailLockList.size(); ++i)
			{
				SimpleMap calenderMap = (SimpleMap)detailLockList.get(i);
				arCalenderSql.append(" MAX(DECODE(LOCK_DATE_STR,'" + calenderMap.getString("AR_DATE_STR"))
				.append("', NVL(LOCK_FLAG, ").append(calenderMap.getString("LOCK_FLAG")).append("), ")
				.append(calenderMap.getString("LOCK_FLAG")).append(")) AS \"").append(calenderMap.getString("AR_DATE_STR")).append("\", ") ;
				
				if (!parameterObject.getString("actionType").equals("search")){
					day = new CalendarDay() ;
					GregorianCalendar calendarDay = new GregorianCalendar() ;
					calendarDay.setTime(new SimpleDateFormat("yyyy/MM/dd").parse(calenderMap.getString("AR_DATE_STR"))) ;
					day.setCalendarDay(calendarDay) ;
					day.setDayTypeId(calenderMap.getInt("TYPEID")) ;
					day.setArDateStr(calenderMap.getString("AR_DATE_STR")) ;
					calendarList.add(day) ;
				}
			}
			
			parameterObject.setString("CALENDER_SQL", arCalenderSql.toString());
			parameterObject.setString("S_DATE", ((SimpleMap)detailLockList.get(0)).getString("AR_DATE_STR"));
			parameterObject.setString("E_DATE", ((SimpleMap)detailLockList.get(detailLockList.size() - 1)).getString("AR_DATE_STR"));
			
			
			if (parameterObject.getString("actionType").equals("search")){
				url = "ar_view_detail_supervisor_lock.jsp" ;
				
				/* paging logic */
				UserConfiguration config = UserConfiguration.getInstance("/system.properties");
				int pageSize = config.getInt("page.style1.pagesize");
				int pageGroupSize = config.getInt("page.style1.pagegroupsize");
				int currentPage = 0;
				// if has currentpage set value into currentPage
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
				int resultCount = services.retrieveDetailSupervisorLockCnt(parameterObject);
				
				// 如果"当前页"大于最大页数,取最后一页
				if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {
					currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
				}
				
				// 考勤员锁定
				List detailSupervisorLockList = services.retrieveDetailSupervisorLockList(parameterObject, currentPage, pageSize);
				
				// 取得,总锁定表的,明细锁定标示
				String ATT_MO_FLAG = "0" ;
				List statusList = services.retrieveMonthlyStatusList(parameterObject);
				if (!statusList.isEmpty()){
					ATT_MO_FLAG = ((SimpleMap)statusList.get(0)).getString("ATT_MO_FLAG") ;
				}
				
				// search parameter
				
				request.setAttribute("ATT_MO_FLAG", ATT_MO_FLAG);
				request.setAttribute("detailLockList", detailLockList);
				request.setAttribute("detailSupervisorLockList", detailSupervisorLockList);
				
				// paging parameter
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);

			}
			else
			{
				url = "ar_modify_detail_supervisor_lock.jsp" ;
				
				// 考勤员锁定
				List detailSupervisorLockList = services.retrieveDetailSupervisorLockList(parameterObject);
				request.setAttribute("detailSupervisorLockList", detailSupervisorLockList);
				
				request.setAttribute("calendarList", calendarList);
				request.setAttribute("supervisorLock", detailSupervisorLockList.get(0));
			}
			
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("year", parameterObject.getString("year"));
			request.setAttribute("month", parameterObject.getString("month"));
			request.setAttribute("deptid", parameterObject.getString("deptid"));
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode"));
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve attendance detail lock list Exception. ", e);
		}
		
		return "/" + url +  "?menu_code=" + parameterObject.getString("menu_code");
		
	}

}

