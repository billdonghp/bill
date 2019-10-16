package com.ait.ga.cmd.smock;

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

public class RetrieveDataForSmockProideHistoryRecordCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		GaServices services = new GaServices();
		SimpleMap parameterObject;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId",admin.getCpnyId());
			parameterObject.setString("personId", request.getParameter("personId"));

			// retrieve record list
			List recordList = services.selectSmockProvideHistoryRecord(parameterObject);
			Object recordCount = services.selectSmockProvideCut(parameterObject);
			
			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));
			
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"retrieve data for smock provide history record data Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "/ga_view_historyRecord.jsp?menu_code="+ parameterObject.getString("menu_code");
	}

}
