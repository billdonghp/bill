package com.ait.ga.cmd.certificate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDataForCertificateHistoryCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute(
				"admin");
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId",admin.getCpnyId());

			// retrieve record list
			List recordList = services.getCertificateUpdateHistory(parameterObject);


			request.setAttribute("recordList", recordList);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieve certificate list cmd Exception. ", e);
		}
		this.putToolbarInfo(request);

		return "/ga_certificate_history.jsp?menu_code="+ parameterObject.getString("menu_code");
	}


}
