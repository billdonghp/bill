/*
 * @(#)SearchEmpHistory.java 1.0 2007-9-24 上午02:45:18
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.hrcommand;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;


public class SearchEmpHistoryCmd  extends HrmAbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String empid=request.getParameter("empID");
		
		  HrmServices services = HrmServices.getInstance();
		    
		List listdata=  services.searchEmpHistoryById(empid);
		
		request.getSession().setAttribute("listdata", listdata);       
		  
		return "/hrm/showemphistoryproperty.jsp";  
	}
}

