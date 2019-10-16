package com.ait.gm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;

public class GmAndShiftAdd extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String shift_id = (String) request.getAttribute("shift_id");
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("shift_id", shift_id);
			
			// retrieve attendance record list
			List gmType =  gm.gmType(parameterObject);
			List shift = gm.shift(parameterObject);
			
			List listMM=DateUtil.getTimeMMList();
			
			List listHH=DateUtil.getTimePerHourList();

			request.setAttribute("listMM",listMM);
			request.setAttribute("listHH",listHH);
			
			request.setAttribute("gmType", gmType);
			request.setAttribute("shift", shift) ;

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshift by No Exception. ",
					e);
		}

		return "gm/typeandshiftAdd.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

}
