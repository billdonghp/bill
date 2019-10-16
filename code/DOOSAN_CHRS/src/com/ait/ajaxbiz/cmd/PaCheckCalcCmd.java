/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.pa.business.PaServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class PaCheckCalcCmd implements Command {

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
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String resultStr="";
		String calcStr = "No" ;
		String arStr = "No" ;
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			int result = 0 ;
			
			int arFlag=0;
		
			if (parameterObject.getString("calType") != null && parameterObject.getString("calType").equals("normal"))
			{
				result = services.paCheckNormalCalc(parameterObject) ;
			}
			else if (parameterObject.getString("calType") != null && parameterObject.getString("calType").equals("special"))
			{
				result = services.paCheckSpecialCalc(parameterObject) ;
			}
			arFlag = services.arStatusFlag(parameterObject) ;
			
			if(result != 0)
			{
				calcStr = "Yes" ;
			}
			if(arFlag!=0){
				arStr = "Yes" ;
			}
			resultStr=calcStr+","+arStr;
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace() ;
		}
    				
    	out.println(resultStr);
        out.close();
        
        return "" ;
	}
}
