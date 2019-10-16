/*
 * @(#)InsertPostGradeCmd.java 1.0 2007-9-7 上午12:02:06
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.postgrade;

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
import com.ait.sy.service.SysService;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class InsertPostGradeCmd implements Command {

	private boolean flag = false;

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaServices paService = PaServices.getInstance();
		MessageSource messageSource;
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		PaServices paServices = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		try {
			String year = request.getParameter("year");
			String gradeid = request.getParameter("gradeid");
			String postgradename = request.getParameter("postgradename");
			String postgradefirstno = request.getParameter("postgradefirstno");
			String postgradefirstmoney = request.getParameter("postgradefirstmoney");
			String postgradevalue = request.getParameter("postgradevalue");
			
			parameterObject.put("POST_GRADE_YEAR", year);
			parameterObject.put("CREATEDBY", admin.getAdminID());
			parameterObject.put("GRADEID", gradeid);
			parameterObject.put("POSTGRADENAME", postgradename);
			parameterObject.put("POSTGRADEFIRSTNO", postgradefirstno);
			parameterObject.put("POSTGRADEFIRSTMONEY", postgradefirstmoney);
			parameterObject.put("POSTGRADEVALUE", postgradevalue);
			
			
			// validate 
			int result = paServices.validateInsertPostGrade(parameterObject);
			if (result == 1) {
				request.setAttribute("alert", "添加的数据已存在,添加失败!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}	

			paServices.insertPostGrade(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert hr_post_grade Exception. ", e);
		}
		// 添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url", "paControlServlet?operation=viewpostgradecmd&menu_code=" + request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}