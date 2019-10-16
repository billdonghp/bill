package com.ait.ga.cmd.smock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class InsertSmockProvideCmd extends GaAbstractCommand{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();

		try {

			// bind parameter
			String [] affStrings = request.getParameterValues("affirmno");
			parameterObject.setString("conPersonId", admin.getPersonId());
			
			for(int i=0;i<affStrings.length;i++){
				parameterObject.setString("seqNo", affStrings[i].toString());
				services.insertSmockProvide(parameterObject);
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert smock relation Exception. ", e);
		}

		request.setAttribute("alert", "已确认发放!");
		request.setAttribute("url","gaControlServlet?operation=retrieveSmockProvideList&menu_code=" + request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}
