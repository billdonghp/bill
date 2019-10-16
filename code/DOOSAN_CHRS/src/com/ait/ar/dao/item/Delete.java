package com.ait.ar.dao.item;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Delete implements Command {
	private String returnR = null;

	public Delete() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArItemBean dao = new ArItemBean();
		String menu_code = request.getParameter("menu_code");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));

		try {

			dao.delItem(itemNo);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Delete attendance item Exception. " + e.toString());
			throw new GlRuntimeException("Delete attendance item Exception.", e);
		}

		returnR = "/aritemlist.jsp?menu_code=" + menu_code;
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
