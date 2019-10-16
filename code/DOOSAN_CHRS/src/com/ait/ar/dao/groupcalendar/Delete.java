package com.ait.ar.dao.groupcalendar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArFactoryCalendarBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Delete implements Command {
	private String returnR = null;

	public Delete() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String menu_code = request.getParameter("menu_code");
		int groupID = Integer.parseInt(request.getParameter("pkNo"));
		String pkDate = request.getParameter("pkDate");

		ArFactoryCalendarBean dao = new ArFactoryCalendarBean();

		try {

			dao.delGroupCalendar(groupID, pkDate);

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Delete dynamic group calendar Exception. " + e.toString());
			throw new GlRuntimeException(
					"Delete dynamic group calendar Exception. ", e);
		}

		returnR = "ar/groupcalendar.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
