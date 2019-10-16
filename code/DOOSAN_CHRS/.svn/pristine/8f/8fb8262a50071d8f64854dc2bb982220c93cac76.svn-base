/*
 * @(#)InsertEvaluateCmd.java 1.0 2007-9-21 上午09:38:55
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.evaluate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.hrm.bean.Health;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.hrm.bean.EvBean;
import com.ait.i18n.MessageSource;
   
public class InsertEvaluateCmd extends HrmAbstractCommand {
   
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		// declare bind object     
		List list = new ArrayList();                     

		// bind family form data to list object
		ObjectBindUtil.setFormBean(request, new EvBean(), list, 3, "_");   
  
		// filtrate data    
		this.filtrateData(list, request.getParameter("evSign").split(","));    
		services.insertEvaluate(list,true);                   
             
		String msg = "";
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		msg = messageSource.getMessage("alert.mutual.save_successfully");
		
		request.setAttribute("alert", msg);  
		request.setAttribute("url",
				"/hrmControlServlet?operation=viewEvaluate&menu_code="
						+ request.getParameter("menu_code")+"&empID="+empID);    

		
		return "/inc/alertMessage.jsp";                    

	}

	/**
	 * filtrate bean object
	 * 
	 * @param list
	 * @param row
	 */
	private void filtrateData(List list, String[] row) {

		// remove bean object by row number array
		for (int i = row.length; i > 0; i--) {

			if (row[i - 1].equals(""))
				return;

			list.remove(Integer.parseInt(row[i - 1]) - 1);
		}
	}

}
	