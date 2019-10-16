package com.ait.ar.dao.staitem;

//import java.io.UnsupportedEncodingException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArStaItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Delete implements Command {
	private String returnR = null;

	public Delete() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		String menu_code = request.getParameter("menu_code");
		ArStaItemBean dao = new ArStaItemBean();
		try {
			
			dao.DelStaItem(itemNo);

		} catch (Exception e) {

			Logger.getLogger(getClass())
					.debug(
							"Delete attendance summary item Exception. "
									+ e.toString());
			throw new GlRuntimeException(
					"Delete attendance summary item Exception. ", e);
		}

		returnR = "/arstaitem.jsp?menu_code=" + menu_code + "&seq="
				+ System.currentTimeMillis();
		Logger.getLogger(getClass()).debug("return page is : " + returnR);
		return returnR;
	}
}
