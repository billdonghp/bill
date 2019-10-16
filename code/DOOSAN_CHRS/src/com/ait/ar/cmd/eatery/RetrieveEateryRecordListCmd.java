/*
 * @(#)RetrieveEateryRecordListCmd.java 1.0 2007-9-26 下午09:37:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.eatery;

import java.io.IOException;
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

import com.ait.util.StringUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-26 下午09:37:58
 * @version 1.0
 * 
 */
public class RetrieveEateryRecordListCmd extends ArAbstractCommand {

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
			String CARD_TYPE = StringUtil.checkNull(parameterObject.getString("CARD_TYPE")) ;
			if (CARD_TYPE.equals("CardType05")){
				parameterObject.setString("CardType05", "CardType05") ;
			}else if(CARD_TYPE.equals("CardType10")) {
				parameterObject.setString("CardType10", "CardType10") ;
			}
				
			
			// retrieve eatery record list
			List recordList = services.retrieveEateryRecordList(parameterObject, currentPage, pageSize);
			Object recordCount = services.retrieveEateryRecordListCount(parameterObject);

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));

			// search parameter
			request.setAttribute("CARD_TYPE", parameterObject.getString("CARD_TYPE"));
			request.setAttribute("R_TIME", parameterObject.getString("R_TIME"));
			request.setAttribute("DEPTID", parameterObject.getString("DEPTID"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("REPAST_TYPE", parameterObject.getString("REPAST_TYPE"));
			request.setAttribute("INSERT_BY", parameterObject.getString("INSERT_BY"));

			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve eatery record list by paging Exception. ", e);
		}

		return "/ar_view_eatery.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
