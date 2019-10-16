/*
 * @(#)RetrieveDataForUpdatePresentCmd.java 1.0 2009-7-13 下午03:41:05
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-13 下午03:41:05
 * @version 1.0
 * 
 */
public class RetrieveDataForUpdatePresentCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		Object result = null;
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			
			// retrieve present by sequence No
			List list = services.retrievePresentList(parameterObject);

			if(list.size() > 0)
				result = list.get(0);

			request.setAttribute("result", result);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for update present Exception. ", e);
		}
		request.setAttribute("cpnyId", cpnyId);
		return "/ga_modify_present.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}

