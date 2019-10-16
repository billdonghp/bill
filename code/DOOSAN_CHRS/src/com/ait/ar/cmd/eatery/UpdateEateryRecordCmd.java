/*
 * @(#)UpdateEateryRecordCmd.java 1.0 2007-9-27 上午09:24:30
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.eatery;

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
 * @Date 2007-9-27 上午09:24:29
 * @version 1.0
 * 
 */
public class UpdateEateryRecordCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
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
			String date = parameterObject.getString("R_TIME");
			String hour = parameterObject.getString("hour");
			String minute = parameterObject.getString("minute");
			parameterObject.setString("OPERATOR_ID", admin.getAdminID());
			parameterObject.setString("R_TIME", date + hour + minute);
			parameterObject.setString("R_DATE", date);
			
			// validate eatery record date
			int result = Integer.parseInt(services.validateEateryRecordDate(parameterObject).toString());
			if (result == 0) {
				//"消费卡有效日期与使用日期不符."
				messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
				request.setAttribute("alert", messageSource.getMessage("alert.att.punch_effective"));
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			// update eatery record  
			services.updateEateryRecord(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update eatery record  Exception. ", e);
		}
		//修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","arControlServlet?operation=retrieveEateryRecordList&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

