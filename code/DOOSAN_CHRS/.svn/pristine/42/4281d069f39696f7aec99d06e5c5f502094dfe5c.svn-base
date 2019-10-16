package com.ait.ar.dao.groupcalendar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArFactoryCalendarBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Add implements Command {
	private String returnR = null;

	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String menu_code = request.getParameter("menu_code");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int groupID = Integer.parseInt(request.getParameter("groupId"));
		ArFactoryCalendarBean dao = new ArFactoryCalendarBean();

		try {
			int sum = dao.getGroupXTSum(year, month, groupID);
			if (sum > 0) {
				returnR = "ar/groupcalendar_a.jsp?menu_code=" + menu_code
						+ "&errMsg=不允许重复设置";
			} else {
				dao.addGroupCalendar(year, month, groupID);
				returnR = "ar/groupcalendar.jsp?menu_code=" + menu_code;
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Add dynamic group calendar Exception. " + e.toString());
			throw new GlRuntimeException(
					"Add dynamic group calendar Exception. ", e);
		}

		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
