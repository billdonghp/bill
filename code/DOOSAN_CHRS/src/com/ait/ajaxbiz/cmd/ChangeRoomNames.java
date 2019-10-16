/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.i18n.MessageSource;
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
public class ChangeRoomNames implements Command {

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
		SimpleMap simpleMap = new SimpleMap();
		StringBuffer str = new StringBuffer(5000) ;
		
    	int TypeId = Integer.parseInt(request.getParameter("TypeId"));
    	String recordid = "";
    	if(request.getParameter("recordid") != null && !request.getParameter("recordid").equals(""))
    		recordid = request.getParameter("recordid");
    	try
    	{
    		// create parameter object
    		SimpleMap parameterObject = new SimpleMap();
    		parameterObject.setString("ADMINID", admin.getAdminID()) ;
    		parameterObject.setString("CONDITION", request.getParameter("condition"));
    		parameterObject.setInt("TypeId", TypeId) ;
    		ExpressApplyAndAffirmServices eaaServices = new ExpressApplyAndAffirmServices();
    		simpleMap = (SimpleMap)eaaServices.equipList(parameterObject) ;
    		String[] equipArray = null;
    		if(simpleMap.getString("EQUIP") != null){
    			equipArray = simpleMap.getString("EQUIP").split("　");
    		
    		
    		
        	response.setContentType("text/xml;charset=UTF-8");
        	response.setHeader("Cache-Control", "no-cache");
    		PrintWriter out = response.getWriter();

    		str.append(" <input type='hidden' name='peoples_allow1' value='"+simpleMap.getString("PEOPLES")+"'/>") ;
    		for (int i = 0 ; i < equipArray.length ; ++i)
    		{
    			str.append(" <input type='checkbox' name='UP"+recordid+"' value='"+equipArray[i]+"'/>&nbsp;") ;
    			str.append(equipArray[i]+"<br>") ;
    		}
    		
    		out.println(str.toString());
            out.close();}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace() ;
    	}

        return "" ;
	}
}