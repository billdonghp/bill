/*
 * @(#)UpdatePostCmd.java 1.0 2007-9-8 上午02:03:26
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.essovertime;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class UpdateEssOvertimeParamCmd extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		SysService syservice = SysService.getInstance();
		MessageSource messageSource ;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request) ;
			parameterObject.setString("UPDATED_BY", admin.getAdminID()) ;
            // validate R_TIEM
			int result = syservice.validateUpdateEssOvertimeParam(parameterObject);
			if (result != 0) {
				request.setAttribute("alert", "修改的条件已存在!!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}		
			           
			// update
			syservice.updateEssOvertimeParam(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update EssOvertimeParam Exception. ", e);
		}
//		修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","syControlServlet?operation=essOvertimeParam&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}
