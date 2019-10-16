package com.ait.ar.dao.sydept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.SydeptBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class update implements Command {
	private String returnR = null;

	public update() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("str");
		String[] empids = str.split("-");
		String mubiao = request.getParameter("mubiao");
		String sql = null;
		ArrayList sqlValues = new ArrayList();
		SydeptBean dao = new SydeptBean();
		String menu_code = request.getParameter("menu_code");
		for (int i = 0; i < empids.length; i++) {
			sql = "update hr_employee set deptid='" + mubiao
					+ "' where empid='" + empids[i].toString() + "'";
			Logger.getLogger(getClass()).debug("add sql : " + sql);
			sqlValues.add(sql);
		}

		try {

			dao.updateDeptDD(sqlValues);

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Update employee department Exception. " + e.toString());
			throw new GlRuntimeException(
					"Update employee department Exception. ", e);
		}

		returnR = "/sy_dept_dd.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page is : " + returnR);
		return returnR;
	}
}
