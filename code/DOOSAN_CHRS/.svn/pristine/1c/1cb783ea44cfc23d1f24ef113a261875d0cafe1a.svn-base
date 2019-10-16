package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class InsertFestivalSchemeCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("personId", admin.getPersonId());
		parameterObject.setString("cpnyId",admin.getCpnyId());
		try {
			
			parameterObject.setString("schemeName", request.getParameter("schemeName"));
			parameterObject.setString("schemeRemark", request.getParameter("schemeRemark"));
			parameterObject.setString("maxRowNum", request.getParameter("maxRowNum"));
			
			for(int i=0;i<=Integer.parseInt(request.getParameter("maxRowNum"));i++){
				
				parameterObject.setString("presentNo"+i,request.getParameter("presentName"+i));
				parameterObject.setString("quentity"+i, request.getParameter("quentity"+i));

			}
//			 insert present
			services.insertFestivalScheme(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert festival scheme relation Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveFestivalScheme&menu_code=" + request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}
