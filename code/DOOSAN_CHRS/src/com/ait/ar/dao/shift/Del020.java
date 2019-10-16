package com.ait.ar.dao.shift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArShift020Bean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Del020 implements Command {
	private String returnR = null;

	public Del020() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pkno = Integer.parseInt(request.getParameter("pkno"));
		int shiftNo = Integer.parseInt(request.getParameter("shiftNo"));
		int last = Integer.parseInt(request.getParameter("last"));
		String menu_code = request.getParameter("menu_code");
		ArShift020Bean ar = new ArShift020Bean();
		try {
			ar.delArShift020(pkno);
			if (last == 0)
				returnR = "ar/shiftEdit.jsp?shiftNo=" + shiftNo + "&menu_code="
						+ menu_code;
			if (last == 1) {

				ar.delShift010(shiftNo);
				returnR = "ar/shiftlist.jsp?menu_code=" + menu_code;
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Delete shift information Exception. " + e.toString());
			throw new GlRuntimeException(
					"Delete shift information Exception. ", e);
		}

		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;

	}
}
