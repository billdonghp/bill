/*
 * @(#)DeleteWashApplyCmd.java 1.0 2009-7-23 下午05:07:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.washhouse;

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
 * @Date 2009-7-23 下午05:07:32
 * @version 1.0
 * 
 */
public class DeleteWashApplyCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.putToolbarInfo(request);
		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List recordList = new ArrayList();

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setInt("ACTIVITY", 0);
			
			// delete wash apply
			services.updateWashApply(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Delete wash apply Exception. ", e);
		}

		// 删除成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveWashApplyInfo&menu_code=" + parameterObject.getString("menu_code")
								   + "&personId=" + parameterObject.getString("personId")
								   + "&empId=" + parameterObject.getString("empId")
								   + "&flag=" + parameterObject.getString("flag")
								   + "&deptId=" + parameterObject.getString("deptId")
								   + "&startDate=" + parameterObject.getString("startDate")
								   + "&endDate=" + parameterObject.getString("endDate"));

		return "/inc/alertMessage.jsp";
	}

}

