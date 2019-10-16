/*
 * @(#)InsertPostCmd.java 1.0 2007-9-8 上午02:01:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.essleave;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;
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


public class InsertEssLeaveParamCmd extends ArAbstractCommand {
	SimpleMap parameterObject;	
	SysService syservice = SysService.getInstance();
	MessageSource messageSource ;
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		String flag=request.getParameter("flag");		
		if(flag!=null && flag.equals("add")){
			return this.addEssLeaveParam(request,admin);
		}else if(flag!=null && flag.equals("view")){
			return this.viewEssLeaveParamPage(request,admin);
		}else{
			return "error.jsp";
		}
	}
	private String addEssLeaveParam(HttpServletRequest request,AdminBean admin) {
		// TODO Auto-generated method stub
		SimpleMap parameterObject;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request) ;
			parameterObject.setString("CREATED_BY", admin.getAdminID()) ;
            // validate R_TIEM
			int result = syservice.validateInsertEssLeaveParam(parameterObject);
			if (result != 0) {
				request.setAttribute("alert", "插入的条件已存在!!!");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}		
			           
			// insert attendance record  
			syservice.insertEssLeaveParam(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert EssLeaveParam Exception. ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","syControlServlet?operation=essLeaveParam&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
		}
	private String viewEssLeaveParamPage(HttpServletRequest request,AdminBean admin) {
		// TODO Auto-generated method stub
		SimpleMap parameterObject;
		List list;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request) ;			
		    list=syservice.EssLeaveParam(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert EssLeaveParam Exception. ", e);
		}
		request.setAttribute("cpnyId", request.getParameter("cpnyId"));
		request.setAttribute("leaveParam", list);
		return "/sy_add_essleave.jsp";
		}
	
}

