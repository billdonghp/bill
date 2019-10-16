/*
 * @(#)RetrievedataForUpdatePostGradeCmd.java 1.0 2007-9-7 上午12:03:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.cmd.postgrade;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;            
import javax.servlet.http.HttpServletResponse;   

import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService;
import com.ait.web.Command;


public class RetrievedataForUpdatePostGradeCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PaServices paService = PaServices.getInstance();
	    
	    SimpleMap parameterObject = new SimpleMap() ;
	    
	    String year = request.getParameter("year");
		String gradeid = request.getParameter("postgradeid");
		
		parameterObject.set("POST_GRADE_YEAR", year);
		parameterObject.set("GRADEID", gradeid);
	    
	    SimpleMap map = (SimpleMap) paService.getPostGradeById(parameterObject);
	                   
	    request.setAttribute("simplemap",map);                   
	                                      
			return "/pa_post_grade_update.jsp";        
		}                           
}