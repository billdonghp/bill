/*
 * @(#)InsertDetailLockCmd.java 1.0 2008-3-4 上午11:47:03
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.detaillock;

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
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2008-3-4 上午11:47:03
 * @version 1.0
 * 
 */
public class InsertDetailLockCmd extends ArAbstractCommand {

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
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("supervisor", admin.getAdminID());
			parameterObject.setString("START_DATE", parameterObject.getString("AR_DATE_STR"));
			parameterObject.setString("END_DATE", parameterObject.getString("AR_DATE_STR"));

            // validate detail lock data 
			int result = Integer.parseInt(services.validateDetailLockData(parameterObject).toString());
			if (result != 0) {
				
				messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
				request.setAttribute("alert", messageSource.getMessage("alert.att.valid_detail_lock"));
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}		
			           
			// insert detail lock data  
			//services.insertDetailLock(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert detail lock data Exception. ", e);
		}
		
		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","arControlServlet?operation=retrieveDetailLockList&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

