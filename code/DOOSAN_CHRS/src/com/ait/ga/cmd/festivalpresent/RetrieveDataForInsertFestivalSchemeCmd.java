package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class RetrieveDataForInsertFestivalSchemeCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();
		GaServices services = new GaServices();
		try {
			parameterObject.setString("menu_code", request.getParameter("menu_code"));
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			List presentNameList = services.getFestivalPresentName(parameterObject);
			
			request.setAttribute("presentNameList", presentNameList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for insert festival scheme Exception. ", e);
		}

		return "/ga_add_festival_scheme.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
}
