package com.ait.ar.dao.formular;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArStaFormularBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Delete implements Command {
	public Delete() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int formularNo = Integer.parseInt(request.getParameter("formularNo"));
		ArStaFormularBean r = new ArStaFormularBean();

		try {

			r.Delformular(formularNo);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Delete summary item formular Exception. " + e.toString());
			throw new GlRuntimeException(
					"Delete summary item formular Exception. ", e);
		}

		Logger.getLogger(getClass()).debug("return #");
		return "#";
	}
}
