/*
 * @(#)UpdateVisitCardCmd.java 1.0 2007-9-27 ����09:24:30
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


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-27 ����09:24:29
 * @version 1.0
 * 
 */
public class UpdateVacationEmpCmd extends ArAbstractCommand {

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
//			bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			float tot_vac_cnt = 0 ;
			try {
				tot_vac_cnt = Float.parseFloat(parameterObject.getString("tot_vac_cnt")) ;
			} catch (Exception e) {
				// TODO: handle exception
			}
//			validate empid and tot_vac_cnt
			int result = Integer.parseInt(services.validateVacationEmpByUpdate(parameterObject).toString());
			if (tot_vac_cnt < result) {
//				修改此人的休假长度异常
				messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
				request.setAttribute("alert", messageSource.getMessage("alert.att.edit_vacation"));
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}
					
			parameterObject.setString("updated_by", admin.getAdminID());
			
			// update vacationEmp relation
			services.updateVacationEmp(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update vacationEmp relation Exception. ", e);
		}
//		修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","arControlServlet?operation=ar_vacation_emp&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

