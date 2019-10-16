/*
 * @(#)DeletePresentCmd.java 1.0 2009-7-13 下午05:14:39
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * @Date 2009-7-13 下午05:14:39
 * @version 1.0
 * 
 */
public class DeletePresentCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List paramList = new ArrayList();
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setInt("ACTIVITY", 0);
			parameterObject.setString("adminId", admin.getAdminID());
			
			services.updatePresent(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Delete present Exception. ", e);
		}

		// 删除成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrievePresentList&menu_code=" + parameterObject.getString("menu_code")
								   + "&personId=" + parameterObject.getString("personId")
								   + "&dataType=" + parameterObject.getString("dataType")
								   + "&empId=" + parameterObject.getString("empId")
								   + "&cpnyId=" + parameterObject.getString("cpnyId")
								   + "&startDate=" + parameterObject.getString("startDate")
								   + "&endDate=" + parameterObject.getString("endDate"));

		return "/inc/alertMessage.jsp";
	}

}

