/*
 * @(#)RetrieveVisitCardListCmd.java 1.0 2007-9-26 ����09:37:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.visitcard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (wangliwei@ait.net.cn)
 * @Date 2007-9-26 ����09:37:58
 * @version 1.0
 * 
 */
public class RetrieveVisitCardListCmd extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap parameterObject;

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// retrieve visit card list
			List visitCardList = services.retrieveVisitCardList(parameterObject, currentPage, pageSize);
			Object visitCardCount = services.retrieveVisitCardListCnt(parameterObject);

			request.setAttribute("visitCardList", visitCardList);
			request.setAttribute("visitCardCount", Integer.parseInt(visitCardCount.toString()));

			// search parameter
			request.setAttribute("CARD_NO", parameterObject.getString("CARD_NO"));
			request.setAttribute("NAME", parameterObject.getString("NAME"));
			request.setAttribute("USE_DATE", parameterObject.getString("USE_DATE"));

			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve visit card list by paging Exception. ", e);
		}

		return "/ar_view_visitcard.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
