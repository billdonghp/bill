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
public class UpdateDetailSupervisorLockCmd extends ArAbstractCommand {

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
			String actionType = request.getParameter("actionType") ;
			
			if (actionType.equals("search"))
			{
				// bind parameter
				String[] days = request.getParameterValues("days") ;
				String[] supervisorIds = request.getParameterValues("supervisorId") ;
				String lockFlag = request.getParameter("lockFlag") ;
				
				for (int i = 0 ; i < supervisorIds.length ; ++ i)
				{
					for (int j = 0 ; j < days.length ; ++ j)
					{
						parameterObject = new SimpleMap() ;
						
						parameterObject.setString("SUPERVISOR_ID", supervisorIds[i]) ;
						parameterObject.setString("LOCK_DATE_STR", days[j]) ;
						parameterObject.setString("LOCK_FLAG" , lockFlag);
						parameterObject.setString("ADMIN_ID" , admin.getAdminID());
						
						paramList.add(parameterObject) ;
					}
				}
			}
			else 
			{
				String supervisorId = request.getParameter("personId") ;
				String[] days = request.getParameterValues("days") ;
				
				for (int i = 0 ; i < days.length ; ++ i)
				{
					parameterObject = new SimpleMap() ;
					
					parameterObject.setString("SUPERVISOR_ID", supervisorId) ;
					parameterObject.setString("LOCK_DATE_STR", days[i]) ;
					parameterObject.setString("LOCK_FLAG", request.getParameter("lockFlag_" + days[i])) ;
					parameterObject.setString("ADMIN_ID" , admin.getAdminID());
					
					paramList.add(parameterObject) ;
				}
			}
			
			// update detail Supervisor lock data  
			services.updateDetailSupervisorLock(paramList);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update detail lock data  Exception. ", e);
		}

//		修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url",
									"arControlServlet?operation=retrieveDetailSupervisorLockList&actionType=search&menu_code=" + request.getParameter("menu_code")
									+ "&year=" + request.getParameter("year") + "&month=" + request.getParameter("month") + "&key=" + request.getParameter("key")
									+ "&statTypeCode=" + request.getParameter("statTypeCode") + "&deptid=" + request.getParameter("deptid")
									+ "&personId=" + request.getParameter("personId") + "&key=" + request.getParameter("key")
									
							);
		return "/inc/alertMessage.jsp";
	}

}

