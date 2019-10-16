package com.ait.ar.dao.ardate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArDateBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Delete implements Command {
	private String returnR = null;

	public Delete() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArDateBean dao = new ArDateBean();
		String menu_code = request.getParameter("menu_code");
		int statNo = Integer.parseInt(request.getParameter("statNo"));

		try {

			dao.delArDate(statNo);
			returnR = "/ardatelist.jsp?menu_code=" + menu_code;
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Delete attendance date Exception." + e.toString());
			throw new GlRuntimeException("Delete attendance date Exception.", e);
		}

		Logger.getLogger(getClass()).debug("returnpage is :" + returnR);
		return returnR;
	}

}
