/*
 * @(#)UpdateDetailLockCmd.java 1.0 2008-3-4 上午11:47:38
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.detaillock;

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
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2008-3-4 上午11:47:38
 * @version 1.0
 * 
 */
public class UpdateDetailLockCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MessageSource messageSource ;
		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		List paramList = new ArrayList() ;
		
		try {

			// bind parameter
			String[] days = request.getParameterValues("days") ;
			
			for (int i = 0 ; i < days.length ; ++ i)
			{
				parameterObject = new SimpleMap() ;
				
				parameterObject.setString("STAT_TYPE_CODE", request.getParameter("statTypeCode")) ;
				
				parameterObject.setString("AR_DATE_STR", days[i]) ;
				
				parameterObject.setString("ATT_MO_FLAG", request.getParameter("lockFlag_" + days[i])) ;
				
				parameterObject.set("cpnyId", admin.getCpnyId());
				
				paramList.add(parameterObject) ;
			}
			
			
			
			// update detail lock data  
			services.updateDetailLock(paramList);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update detail lock data  Exception. ", e);
		}

//		修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url",
									"arControlServlet?operation=retrieveDetailLockList&menu_code=" + request.getParameter("menu_code")
									+ "&year=" + request.getParameter("year") + "&month=" + request.getParameter("month")
									+ "&statTypeCode=" + request.getParameter("statTypeCode")
							);
		return "/inc/alertMessage.jsp";
	}

}

