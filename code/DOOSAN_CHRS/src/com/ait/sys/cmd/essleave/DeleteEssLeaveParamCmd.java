/*
 * @(#)DeleteAttRecordCmd.java 1.0 2007-9-27 上午09:24:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.essleave;

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
import com.ait.sy.service.SysService;


public class DeleteEssLeaveParamCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		SysService syservice = SysService.getInstance();
		SimpleMap parameterObject;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			syservice.deleteEssLeaveParam(parameterObject) ;
			

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("delete EssLeaveParam by batch Exception. ", e);
		}


//		删除成功
		AdminBean admin = (AdminBean) (request.getSession().getAttribute("admin"));
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","syControlServlet?operation=essLeaveParam&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}

