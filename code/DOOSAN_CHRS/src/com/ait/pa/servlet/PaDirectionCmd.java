/*
 * @(#)PaDirectionCmd.java 1.0 2007-1-23 下午01:29:25
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

public class PaDirectionCmd implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			SimpleMap sMap = new SimpleMap();
		    sMap = ObjectBindUtil.getData(request);
		    
		    Set s=sMap.keySet();
		    Iterator iter=s.iterator();
		    while(iter.hasNext()){
			String key = (String)iter.next();
			request.setAttribute(key, sMap.get(key));
		    }
////		    String adminId = ((AdminBean) request.getSession(false).getAttribute(
////			"admin")).getAdminID();
////		    String empID = request.getParameter("empID") != null ? request
////			.getParameter("empID") : adminId;
////			
////			SimpleMap parameterObject = new SimpleMap();
////			parameterObject.set("EMPID", empID);
////			
////			HrmServices services = HrmServices.getInstance();
////
////			List list = services.retrieveDeptTree(parameterObject);
////
////			request.setAttribute("dept_list", list);
//
//			
////			HrmServices services = HrmServices.getInstance();
////			SimpleMap simpleMap = new SimpleMap();
////			simpleMap = ObjectBindUtil.getData(request);
////			
////			if (request.getParameter("DEPTID") != null) {
////				List list = services.retrieveEmpList(simpleMap);
////				request.setAttribute("emp_list", list);
////			}
			
		}catch(Exception e){
			e.printStackTrace();
		    Logger.getLogger(this.getClass()).error(e.toString());
		}
		String core;
		core = request.getParameter("destination");
		String url;
		url = "/hrm/"+core+".jsp";
		
		return url;
	}
}

