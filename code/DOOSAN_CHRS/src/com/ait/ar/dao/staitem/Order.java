package com.ait.ar.dao.staitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArStaItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Order implements Command {
	private String returnR = null;

	public Order() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		int order = Integer.parseInt(request.getParameter("order"));
		int calOrder = 0;
		String menu_code = request.getParameter("menu_code");
		ArStaItemBean dao = new ArStaItemBean();
		String type = request.getParameter("type");
		if (type.equals("up")) {
			calOrder = order - 1;
			try {

				dao.OrderStaItem(itemNo, order, calOrder);
				returnR = "/arstaitem.jsp?menu_code=" + menu_code;
			} catch (Exception e) {

				Logger.getLogger(getClass()).debug(
						"Attendance summary item order Exception. "
								+ e.toString());
				throw new GlRuntimeException(
						"Attendance summary item order Exception. ", e);
			}
		}

		if (type.equals("down")) {
			calOrder = order + 1;
			try {

				dao.OrderStaItem(itemNo, order, calOrder);
				returnR = "/arstaitem.jsp?menu_code=" + menu_code;
			} catch (Exception e) {

				Logger.getLogger(getClass()).debug(
						"Attendance summary item order Exception. "
								+ e.toString());
				throw new GlRuntimeException(
						"Attendance summary item order Exception. ", e);
			}
		}

		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
