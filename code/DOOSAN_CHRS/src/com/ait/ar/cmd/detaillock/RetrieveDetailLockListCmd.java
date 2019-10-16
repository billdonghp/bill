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
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2008-3-4 上午11:46:19
 * @version 1.0
 * 
 */
public class RetrieveDetailLockListCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute("admin"));
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());
			parameterObject.set("cpnyId", admin.getCpnyId());
			parameterObject.setString("CPNY_ID", admin.getCpnyId());
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
			
			parameterObject.set("AR_MONTH_STR", arMonth);
			parameterObject.setString("PA_MONTH_STR", arMonth);
			
			String ATT_MO_FLAG = "0" ;
			List statusList = services.retrieveMonthlyStatusList(parameterObject);
			if (!statusList.isEmpty()){
				ATT_MO_FLAG = ((SimpleMap)statusList.get(0)).getString("ATT_MO_FLAG") ;
			}
			
			// retrieve attendance record list
			List detailLockList = services.retrieveDetailLockList(parameterObject);
			List calendarList = new ArrayList() ;
			
			CalendarDay day = null ;
			
			
			for (int i = 0 ; i < detailLockList.size() ; ++i )
			{
				day = new CalendarDay() ;
				SimpleMap dateMap = (SimpleMap)detailLockList.get(i);
				GregorianCalendar calendarDay = new GregorianCalendar() ;
				
				calendarDay.setTime(new SimpleDateFormat("yyyy/MM/dd").parse(dateMap.getString("AR_DATE_STR"))) ;
				
				day.setCalendarDay(calendarDay) ;
				day.setDayTypeId(dateMap.getInt("TYPEID")) ;
				day.setArDateStr(dateMap.getString("AR_DATE_STR")) ;
				day.setLockFlag(dateMap.getInt("LOCK_FLAG")) ;
				
				calendarList.add(day) ;
			}
			

			// search parameter
			request.setAttribute("year", parameterObject.getString("year"));
			request.setAttribute("month", parameterObject.getString("month"));
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode"));
			request.setAttribute("calendarList", calendarList);
			request.setAttribute("ATT_MO_FLAG", ATT_MO_FLAG);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve attendance detail lock list Exception. ", e);
		}

		return "/ar_view_detail_lock.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}

