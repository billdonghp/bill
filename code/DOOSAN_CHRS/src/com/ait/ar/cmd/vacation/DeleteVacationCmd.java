/*
 * @(#)DeleteVacationCmd.java 1.0 2007-10-23 上午10:58:46
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */

package com.ait.ar.cmd.vacation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * @Date 2007-9-27 上午09:24:53
 * @version 1.0
 * 
 */
public class DeleteVacationCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		List parameterObject = new ArrayList();
		SimpleMap paramters;
		try {

			// bind parameter
			paramters = ObjectBindUtil.getData(request);
			String[] vacationsArray = request.getParameterValues("vacations");
			for (String vacNo:vacationsArray) {
				
				parameterObject.add(Integer.parseInt(vacNo));
			}

			// delete attendance record by batch 
			services.deleteVacation(parameterObject, true);
			
			// search parameter
			request.setAttribute("vac_tp", paramters.getString("vac_tp"));
			request.setAttribute("strt_month", paramters.getString("strt_month"));

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("delete vacation by batch Exception. ", e);
		}
		
//		删除成功
		AdminBean admin = (AdminBean) (request.getSession().getAttribute("admin"));
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","arControlServlet?operation=ar_vacation&page=vacation_v&menu_code=" + request.getParameter("menu_code")
				+"&vac_tp=" + paramters.getString("vac_tp") +"&strt_month=" + paramters.getString("strt_month"));

		return "/inc/alertMessage.jsp";
		}
}
