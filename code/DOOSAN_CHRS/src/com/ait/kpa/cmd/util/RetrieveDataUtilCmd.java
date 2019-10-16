/*
 * @(#)RetreveDataUtil.java 1.0 2007-11-10 下午08:08:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDataUtilCmd extends PaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.pa.servlet.PaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		
		List pa_item_list = new ArrayList() ;
		List pa_param_item_list = new ArrayList() ; 
		List pa_distinct_list = new ArrayList() ;
		
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			pa_item_list = services.retrievePa_Item_List_Month() ;
			pa_param_item_list = services.retrievePa_param_item_list(parameterObject);
			pa_distinct_list = services.retrievePa_distinct_list(parameterObject) ;
			
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode")) ;
			request.setAttribute("pa_item_list", pa_item_list);
			request.setAttribute("pa_param_item_list", pa_param_item_list);
			request.setAttribute("pa_distinct_list", pa_distinct_list);
			request.setAttribute("admin", admin.getAdminID());


		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve daily formular data Exception. ", e);
		}

		return "util/pa_util.jsp" ;
	}

}

