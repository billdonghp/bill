/*
 * @(#)RetrieveAttRecordListCmd.java 1.0 2007-9-26 下午09:37:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.idcard;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-26 下午09:37:58
 * @version 1.0
 * 
 */
public class RetrieveAttRecordListCmd extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("endRownum", (currentPage+1)*pageSize);
			parameterObject.set("startRownum",currentPage*pageSize);
			
			if (parameterObject.getString("TABLE_NAME") == null || parameterObject.getString("TABLE_NAME").length() == 0)
			{
				parameterObject.set("TABLE_NAME", "AR_MAC_RECORDS");
			}
			String nextDay="";
			String beforeDay="";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			today.setTime(Calendar.getInstance().getTime());
			today.add(today.DATE, 1);	
			nextDay=sdf.format(today.getTime());	
			Calendar today1 = Calendar.getInstance();
			today1.setTime(Calendar.getInstance().getTime());
			today1.add(today.DATE, -1);	
			beforeDay=sdf.format(today1.getTime());	
			// retrieve attendance record list
			List recordList = services.retrieveAttRecordList(parameterObject);
			Object recordCount = services.retrieveAttRecordListCount(parameterObject);

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));

			// search parameter
			request.setAttribute("R_START_TIME", parameterObject.getString("R_START_TIME")!=null?parameterObject.getString("R_START_TIME"):beforeDay);
			request.setAttribute("R_END_TIME", parameterObject.getString("R_END_TIME")!=null?parameterObject.getString("R_END_TIME"):nextDay);
			request.setAttribute("DEPTID", parameterObject.getString("DEPTID"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("personId", parameterObject.get("personId"));
			request.setAttribute("DOOR_TYPE", parameterObject.getString("DOOR_TYPE"));
			request.setAttribute("INSERT_BY", parameterObject.getString("INSERT_BY"));
			request.setAttribute("TABLE_NAME", parameterObject.getString("TABLE_NAME"));
			
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve attendance record list by paging Exception. ", e);
		}

		return "/ar_view_record.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
