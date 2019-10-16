/*
 * @(#)InsertAttRecordCmd.java 1.0 2007-9-27 上午09:24:03
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.vacation_emp;

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
public class InsertVacationEmpCmd extends ArAbstractCommand {

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
			
			String vac_tp = StringUtil.checkNull(parameterObject.getString("vac_tp")) ;
			if(vac_tp.equals("VacType10")){
				parameterObject.set("vac_id", parameterObject.get("year"));
			}else if(vac_tp.equals("VacType20")){
				parameterObject.set("vac_id", parameterObject.get("year").toString() + parameterObject.get("month").toString());
			}else if(vac_tp.equals("VacType30")){
				parameterObject.set("vac_id",parameterObject.get("year"));
			}else if(vac_tp.equals("VacType40")){
				parameterObject.set("vac_id",parameterObject.get("year"));
			}else if(vac_tp.equals("VacType50")){
				parameterObject.set("vac_id",parameterObject.get("year"));
			}
			parameterObject.set("supervisor", admin.getAdminID());
			
			
			//validate empid and vac_tp and strt_date and end date
			int result = Integer.parseInt(services.validateVacationEmp(parameterObject).toString());
			if (result != 0) {
//				此人的当前的休假已存在
				messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
				request.setAttribute("alert", messageSource.getMessage("alert.att.vacation_exist"));
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
			
			
			// insert attendance record  
			services.insertVacationEmp(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert vacationEmp Exception. ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","arControlServlet?operation=ar_vacation_emp&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

