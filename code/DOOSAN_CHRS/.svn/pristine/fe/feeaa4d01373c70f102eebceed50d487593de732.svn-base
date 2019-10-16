package com.ait.ar.cmd.overtimeplan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;

public class DeleteOTPlanCmd extends ArAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArServices services = new ArServices();
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);

			// retrieve OT Plan by No 
			services.deleteOTPlan(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve OT Plan by No Exception. ", e);
		}
		
		return "arControlServlet?operation=ar_otplan&menu_code="+parameterObject.getString("menu_code");
	}

}
