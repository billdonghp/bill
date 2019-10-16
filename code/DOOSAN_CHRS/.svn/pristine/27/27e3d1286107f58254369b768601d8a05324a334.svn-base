package com.ait.ar.dao.shift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArShift020Bean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Delete implements Command {
	private String returnR = null;

	public Delete() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArShift020Bean shiftbean = new ArShift020Bean();
		String menu_code = request.getParameter("menu_code");
		int shiftNo = Integer.parseInt(request.getParameter("shiftNo"));
		try {

			shiftbean.delShift010(shiftNo);
			shiftbean.delShift020(shiftNo);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Delete shift information Exception. " + e.toString());
			throw new GlRuntimeException(
					"Delete shift information Exception. ", e);
		}

		returnR = "ar/shiftlist.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}

}
