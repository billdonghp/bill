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
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class UpdateFestivalSchemeCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId",admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			services.updateFestivalScheme(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Update festival scheme Exception. ", e);
		}

		// 修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveFestivalScheme&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}
