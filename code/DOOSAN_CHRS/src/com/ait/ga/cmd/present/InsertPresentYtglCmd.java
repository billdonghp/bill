/*
 * @(#)InsertPresentCmd.java 1.0 2009-7-13 下午03:25:48
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-13 下午03:25:19
 * @version 1.0
 * 
 */
public class InsertPresentYtglCmd extends GaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			if(parameterObject.getString("DATA_TYPE").equals("StockType001"))
				parameterObject.remove("APPLY_DEPT");
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			
			// insert present
			//services.insertPresent(parameterObject);
			Iterator keys = parameterObject.keySet().iterator();
			while(keys.hasNext()){
				String key = (String)keys.next();
				if("PRESENT_ID1".equals(key)){	
					services.insertPresent1(parameterObject);	
					}else if("PRESENT_ID2".equals(key)){
						services.insertPresent2(parameterObject);
					}else if("PRESENT_ID3".equals(key)){
						services.insertPresent3(parameterObject);
					}	
				}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert present Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=ytglPresentList&menu_code=" + parameterObject.getString("menu_code")+"&cpnyId="+cpnyId);

		return "/inc/alertMessage.jsp";
	}

}

