package com.ait.ar.dao.factorycalendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArFactoryCalendar;
import com.ait.ar.dao.ArFactoryCalendarBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class UpdateBatch implements Command {
	private String returnR = null;

	private int day;

	private ArrayList values = new ArrayList();

	private ArFactoryCalendar factoryCalendar;

	public UpdateBatch() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdminBean admin=(AdminBean)request.getSession(true).getAttribute("admin");
		int year = Integer.parseInt(request.getParameter("hyear"));
		int month = Integer.parseInt(request.getParameter("hmonth"));
		int workflag = Integer.parseInt(request.getParameter("work"));
		int type = 1;
		if (!StringUtil.checkNull(request.getParameter("type")).equals(""))
			type = Integer.parseInt(request.getParameter("type"));
		int shift = Integer.parseInt(request.getParameter("shift"));
		String menu_code = request.getParameter("menu_code");
		String cpnyId=admin.getCpnyId();

		ArFactoryCalendarBean ar = new ArFactoryCalendarBean();

		int endday = ar.getEndDay(year, month);
		for (int i = 1; i <= 31; i++) {
			if (request.getParameter("day" + i) != null) {
				day = i;
				factoryCalendar = new ArFactoryCalendar();
				factoryCalendar.setDay(day);
				factoryCalendar.setWorkdayflag(workflag);
				factoryCalendar.setType(type);
				factoryCalendar.setShift_no(shift);
				factoryCalendar.setCpnyId(cpnyId);
				if (endday < day) {
					if ((month - 1) != 0) {
						factoryCalendar.setYear(year);
						factoryCalendar.setMonth(month - 1);
					} else {
						factoryCalendar.setYear(year - 1);
						factoryCalendar.setMonth(12);
					}
				} else {
					factoryCalendar.setYear(year);
					factoryCalendar.setMonth(month);
				}
				values.add(factoryCalendar);
			}
		}

		try {

			ar.editFactoryAll(values);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Update factory calendar by batch Exception. "
							+ e.toString());
			throw new GlRuntimeException(
					"Update factory calendar by batch Exception. ", e);
		}

		returnR = "ar/factorycalendar.jsp?menu_code=" + menu_code + "&year_="
				+ year + "&month_=" + month;

		Logger.getLogger(getClass()).debug("return page is : " + returnR);
		return returnR;
	}
}
