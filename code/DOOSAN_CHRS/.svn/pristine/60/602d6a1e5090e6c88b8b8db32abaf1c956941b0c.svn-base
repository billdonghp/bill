/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.business.WasteServices;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
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
public class ChangeWasteaTypeShowUnitPrice implements Command {

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
    	
    	String TypeId = request.getParameter("TypeId");
    	
    	if(TypeId.equals("0")){
    		response.setContentType("text/xml;charset=UTF-8");
        	response.setHeader("Cache-Control", "no-cache");
    		PrintWriter out = response.getWriter();
    		StringBuffer str = new StringBuffer(5000) ;
    		
    		str.append("");
    		str.append("");
    		str.append("<input type='hidden' name='UP' value=''/>");
    		str.append("<input type='hidden' name='UNITS' value=''/>");
        	out.println(str.toString());
            out.close();
            
            return "" ;
    	}
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", admin.getAdminID()) ;
		parameterObject.setString("CONDITION", request.getParameter("condition"));
		parameterObject.setString("TypeId", TypeId) ;
		WasteServices ws = new WasteServices();
		
		double unitPrice = ws.getUnitPrice(parameterObject);
		SimpleMap units = ws.getUnits(parameterObject);
		
		JSONObject object = new JSONObject();
		JSONObject paramObject = new JSONObject();
		
		try {
			paramObject.put("unitPrice", unitPrice);
			paramObject.put("units", units.getString("WASTE_SET_UNITS"));
			paramObject.put("unitsName", units.getString("WASTE_SET_UNITS_NAME"));
	
			object.put("param", paramObject);
			
	    	response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");
	
			PrintWriter out = response.getWriter();
			String str = object.toString();
			
	    	out.println(str);
	        out.close();
		}
		catch(Exception e){
			throw new GlRuntimeException("Get UnitPrice Exception.", e);
		}
        return "" ;
	}
}