/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.safe.bean.PositionInfo;
import com.ait.safe.business.JobHealthServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangliwei (wangliwei@ait.net.cn)
 * @Date 2006-12-22 下午01:03:53
 * @version 1.0
 * 
 */
public class OpsitionIdCounterpartsDisease implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource message = new MessageSource(admin.getLocale(), "UTF-8"); 
		String language = admin.getLanguagePreference() ;
    	
		String opsitionId = request.getParameter("opsitionId");
		
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", admin.getAdminID()) ;
		parameterObject.setString("opsitionId", opsitionId) ;
		
		JobHealthServices jobHealthServices = new JobHealthServices();
		
		PositionInfo positionInfo  = (PositionInfo)jobHealthServices.getDisease(parameterObject);
		
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		
		str.append(positionInfo.getDISEASE());
		
		str.append("<input type='hidden' name='JOB_DISEASE_CODE' value='"+positionInfo.getDISEASECODE()+"'>");
		
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}
