/*
 * @(#)UpdatePostGradeCmd.java 1.0 2007-9-7 上午12:02:31
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.vouchertype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class UpdateDeptVoucherTypeCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource;
		PaServices paService = PaServices.getInstance();
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject ;
		List updateList = new ArrayList() ;
		
		try {
			String[] selectC = request.getParameterValues("selectC") ;

			for (int i = 0 ; i < selectC.length; ++i )
			{
				parameterObject = new SimpleMap();
				parameterObject.setString("DEPTID", selectC[i].split("_")[0]) ;
				parameterObject.setString("VOUCHER_TYPE", request.getParameter(selectC[i] + "_VOUCHER_TYPE")) ;
				
				updateList.add(parameterObject) ;
			}
			
						
			paService.updateDeptVoucherType(updateList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update dept voucher type  Exception. ", e);
		}
		//			修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url", "paControlServlet?operation=viewdeptvouchertypecmd&menu_code=" + request.getParameter("menu_code") + "&DEPTID=" + request.getParameter("DEPTID") 
							);

		return "/inc/alertMessage.jsp";
	}
}
