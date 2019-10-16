/*
 * @(#)UpdatePostGradeCmd.java 1.0 2007-9-7 上午12:02:31
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.postgrade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class UpdatePostGradeCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource;
		PaServices paService = PaServices.getInstance();
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();

		try {
			String year = request.getParameter("year");
			String gradeid = request.getParameter("gradeid");
			String postgradename = request.getParameter("postgradename");
			String postgradefirstno = request.getParameter("postgradefirstno");
			String postgradefirstmoney = request.getParameter("postgradefirstmoney");
			String postgradevalue = request.getParameter("postgradevalue");
			
			parameterObject.put("POST_GRADE_YEAR", year);
			parameterObject.put("UPDATEDBY", admin.getAdminID());
			parameterObject.put("CPNY_ID", admin.getCpnyId());
			parameterObject.put("GRADEID", gradeid);
			parameterObject.put("POSTGRADENAME", postgradename);
			parameterObject.put("POSTGRADEFIRSTNO", postgradefirstno);
			parameterObject.put("POSTGRADEFIRSTMONEY", postgradefirstmoney);
			parameterObject.put("POSTGRADEVALUE", postgradevalue);
						
			paService.updatePostGrade(parameterObject);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update hr_post_grade  Exception. ", e);
		}
		//			修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url", "paControlServlet?operation=viewpostgradecmd&menu_code=" + request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}
