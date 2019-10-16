/*
 * @(#)RetrieveDataForUpdateVisitCardCmd.java 1.0 2007-9-26 ����10:36:20
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
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-9-26 ����10:36:20
 * @version 1.0
 * 
 */
public class RetrieveDataForUpdateVisitCardCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// retrieve visit card data  by No 
			Object result = services.retrieveDataForUpdateVisitCard(parameterObject);

			request.setAttribute("result", result);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve visit card data by No Exception. ", e);
		}

		return "/ar_modify_visitcard.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}
