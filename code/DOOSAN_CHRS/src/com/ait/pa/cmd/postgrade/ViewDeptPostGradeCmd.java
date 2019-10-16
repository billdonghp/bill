/*
 * @(#)ViewPostGradeCmd.java 1.0 2007-9-7 上午12:03:01
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.postgrade;
  
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;


public class ViewDeptPostGradeCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PaServices paService = PaServices.getInstance();
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		SimpleMap parameterObject;
		
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.put("cpny_id", adminBean.getCpnyId());
			List deptpostgradedata = paService.getDeptPostGradeList(parameterObject);
			List postgradeYear = paService.getPostGradeYear(parameterObject);
	    	
			request.setAttribute("deptpostgradedata",deptpostgradedata); 
			request.setAttribute("postgradeYearList",postgradeYear);
			request.setAttribute("DEPTID",parameterObject.getString("DEPTID")) ;
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve dept postGrade list by paging Exception. ", e);
		}
		
 
		
		return "/pa_dept_post_grade.jsp";                   
	}                  
}
