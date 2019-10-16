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

import com.ait.gm.business.WasteServices;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.safe.business.JobHealthServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangliwei (wangliwei@ait.net.cn)
 * @Date 2006-12-22 下午01:03:53
 * @version 1.0
 * 
 */
public class SearchEmployeeInformation implements Command {

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
    	HrmServices services = HrmServices.getInstance();
    	
    	String empID = request.getParameter("empID");
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", admin.getAdminID()) ;
		parameterObject.setString("CONDITION", request.getParameter("condition"));
		
		parameterObject.setString("empID", empID) ;
		
		
		JobHealthServices jobHealthServices = new JobHealthServices();
		
    	List empInformation = jobHealthServices.empInformation(parameterObject);
		
		
    	response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		
		if(empInformation.size()>0){
			Iterator iter = empInformation.iterator();
			while(iter.hasNext()){
				BasicInfo basic = (BasicInfo) iter.next();
				str.append(basic.getDepartment()+","+basic.getJoinDate()+","+basic.getBirth_ymd());
			}
		}
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}