package com.ait.ga.cmd.smock;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-23
 * 
 */
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

public class DeleteSmockRelationCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setInt("ACTIVITY", 0);
			parameterObject.setString("adminId", admin.getAdminID());
			
			
			services.deleteSmockRelation(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Delete smock relation Exception. ", e);
		}

		// 删除成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveSmockRelation&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}
