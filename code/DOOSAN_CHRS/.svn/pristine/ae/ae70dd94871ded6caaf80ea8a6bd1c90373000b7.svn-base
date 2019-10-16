/*
 * @(#)InsertVisitCardCmd.java 1.0 2007-9-27 ����09:24:03
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.visitcard;

import java.io.IOException;

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
 * @Date 2007-9-27 ����09:24:03
 * @version 1.0
 * 
 */
public class InsertVisitCardCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("CREATED_BY", admin.getAdminID());
			parameterObject.setString("UPDATED_BY", admin.getAdminID());
			
			MessageSource messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
			String message = messageSource.getMessage("alert.att.maintenance.start_end_match");
			
			// validate start date and end date
			int result = Integer.parseInt(services.validateVisitCardDate(parameterObject).toString());
			if (result != 0) {
				request.setAttribute("alert", message);
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			// insert visit card relation  
			services.insertVisitCard(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert visit card relation Exception. ", e);
		}
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		String message1 = messageSource.getMessage("alert.mutual.save_successfully");
		request.setAttribute("alert", message1); //保存成功 
		request.setAttribute("url","arControlServlet?operation=retrieveVisitCardList&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

