package com.ait.ar.dao.groupcalendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArFactoryCalendarBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	public Update() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String arMonth = request.getParameter("arMonth");
		int year = Integer.parseInt(arMonth.substring(0, 4));
		int month = Integer.parseInt(arMonth.substring(arMonth.length() - 2));
		int day = Integer.parseInt(request.getParameter("pk"));
		int workflag = Integer.parseInt(request.getParameter("work"));
		int type = Integer.parseInt(request.getParameter("type"));
		int shift = Integer.parseInt(request.getParameter("shift"));
		int groupID = Integer.parseInt(request.getParameter("groupID"));
		String menu_code = request.getParameter("menu_code");
		ArFactoryCalendarBean ar = new ArFactoryCalendarBean();

		try {
			int endday = ar.getEndDay(year, month);

			ArrayList values = new ArrayList();
			if (endday < day) {
				if ((month - 1) != 0) {
					values.add(new Integer(year));
					values.add(new Integer(month - 1));
				} else {
					values.add(new Integer(year - 1));
					values.add(new Integer(12));
				}
			} else {
				values.add(new Integer(year));
				values.add(new Integer(month));
			}

			values.add(new Integer(day));
			values.add(new Integer(workflag));
			values.add(new Integer(type));
			values.add(new Integer(groupID));
			values.add(new Integer(shift));

			ar.editGroup(values);

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Update dynamic group calendar Exception. " + e.toString());
			throw new GlRuntimeException(
					"Update dynamic group calendar Exception. ", e);
		}

		returnR = "ar/groupcalendar_m.jsp?menu_code=" + menu_code
				+ "&groupNo_=" + groupID + "&arMonth_=" + arMonth;
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
