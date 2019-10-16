/*
 * @(#)DeleteEateryRecordCmd.java 1.0 2007-9-27 上午09:24:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.eatery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-27 上午09:24:53
 * @version 1.0
 * 
 */
public class DeleteEateryRecordCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		List parameterObject = new ArrayList();
		SimpleMap paramters;
		try {

			// bind parameter
			paramters = ObjectBindUtil.getData(request);
			String[] recordNoArray = request.getParameterValues("records");
			for (String recordNo:recordNoArray) {
				
				parameterObject.add(Integer.parseInt(recordNo));
			}

			// delete eatery record by batch 
			services.deleteEateryRecord(parameterObject, true);
			
			// search parameter
//			request.setAttribute("R_TIME", paramters.getString("R_TIME"));
//			request.setAttribute("DEPTID", paramters.getString("DEPTID"));
//			request.setAttribute("key", paramters.getString("key"));
//			request.setAttribute("REPAST_TYPE", paramters.getString("REPAST_TYPE"));
//			request.setAttribute("INSERT_BY", paramters.getString("INSERT_BY"));

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("delete eatery record by batch Exception. ", e);
		}
		//删除成功
		AdminBean admin = (AdminBean) (request.getSession().getAttribute("admin"));
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","arControlServlet?operation=retrieveEateryRecordList&menu_code=" + request.getParameter("menu_code")
				+"&R_TIME=" + paramters.getString("R_TIME") +"&DEPTID=" + paramters.getString("DEPTID")
				+ "&key=" + paramters.getString("key") + "&REPAST_TYPE" + paramters.getString("REPAST_TYPE")
				+ "&INSERT_BY=" + paramters.getString("INSERT_BY"));

		return "/inc/alertMessage.jsp";
	}

}

