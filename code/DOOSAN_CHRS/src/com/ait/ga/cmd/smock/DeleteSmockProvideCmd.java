package com.ait.ga.cmd.smock;

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

public class DeleteSmockProvideCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();

		try {
			// bind parameter
			String [] seqNoStrings = request.getParameterValues("affirmno");
			for(int i=0;i<seqNoStrings.length;i++){
				parameterObject.setString("seqNo", seqNoStrings[i].toString());
				services.deleteSmockProvide(parameterObject);
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Delete smock provide Exception. ", e);
		}

		// 删除成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveSmockProvideList&menu_code=" + request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}
