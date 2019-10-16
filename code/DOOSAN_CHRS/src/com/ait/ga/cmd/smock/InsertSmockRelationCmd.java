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
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class InsertSmockRelationCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();

		try {

			// bind parameter
			for(int i=0;i<=Integer.parseInt(request.getParameter("maxRowNum"));i++){
				
				parameterObject.setString("cpnyId", admin.getCpnyId());
				parameterObject.setString("personId",request.getParameter("personId"));
				parameterObject.setString("smockNo",request.getParameter("smockName"+i));
				parameterObject.setString("modelNumber", request.getParameter("modelNumber"+i));
				parameterObject.setString("sizeType",request.getParameter("sizeType"+i));
				parameterObject.setString("periodNum", request.getParameter("periodNum"+i));
				parameterObject.setString("periodUnit", request.getParameter("periodUnit"+i));
				parameterObject.setString("remark", request.getParameter("remark"+i));
				// insert present
				services.insertSmockRelation(parameterObject);
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert smock relation Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveSmockRelation&menu_code=" + request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}

}
