/*
 * @(#)InsertAttRecordCmd.java 1.0 2007-9-27 上午09:24:03
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.vacation;

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
import com.ait.util.StringUtil;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-27 上午09:24:03
 * @version 1.0
 * 
 */
public class InsertVacationCmd extends ArAbstractCommand {

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
			String vac_tp =  StringUtil.checkNull(parameterObject.getString("vac_tp"));
			String strt_month = StringUtil.checkNull(parameterObject.getString("strt_month"));
			String end_month = StringUtil.checkNull(parameterObject.getString("end_month"));
			String vac_day_cnt = StringUtil.checkNull(parameterObject.getString("vac_day_cnt"));
			
			parameterObject.setString("supervisor", admin.getAdminID());
			parameterObject.setString("vac_tp", vac_tp);
			parameterObject.setString("strt_month", strt_month);
			parameterObject.setString("end_month", end_month);
			parameterObject.setInt("vac_day_cnt", Integer.parseInt(vac_day_cnt));
			
			// insert attendance record  
			services.insertVacation(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert vacation Exception. ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","arControlServlet?operation=ar_vacation&page=vacation_v&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

