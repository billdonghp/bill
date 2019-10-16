package com.ait.ar.dao.factorycalendar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArFactoryCalendarBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

/**
 * 
 * <p>
 * Title: Add
 * </p>
 * <p>
 * Description: excute add for factory calendar
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: ait
 * </p>
 * 
 * @author kelly
 * @version 2.0
 */
public class Add implements Command {
	private String returnR = null;

	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdminBean admin=(AdminBean)request.getSession(true).getAttribute("admin");

		String from_date = request.getParameter("from_date");
		String to_date = request.getParameter("to_date");
		int work_shift = Integer.parseInt(request.getParameter("work_shift"));
		int rest_shift = Integer.parseInt(request.getParameter("rest_shift"));
		String menu_code = request.getParameter("menu_code");
		String cpnyId=admin.getCpnyId();
		ArFactoryCalendarBean ar = new ArFactoryCalendarBean();

		try {

			ar.addFactory(from_date, to_date, work_shift, rest_shift,cpnyId);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Add factory calendar Exception. " + e.toString());
			throw new GlRuntimeException("Add factory calendar Exception. ", e);
		}

		returnR = "ar/factorycalendar.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page is : " + returnR);
		return returnR;
	}
}
