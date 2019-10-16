/*
 * @(#)RetrievedataForUpdatePostGradeCmd.java 1.0 2007-9-7 上午12:03:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.cmd.postgrade;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.pa.business.PaServices;
import com.ait.web.Command;


public class RetrievedataForInsertPostGradeCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PaServices paService = PaServices.getInstance();
	    
	    String menu_code = request.getParameter("menu_code");  
	    
	    //List postGradeTypeList = paService.getPostGradeTypeList();
	                   
	    //request.setAttribute("postGradeTypeList",postGradeTypeList);                   
	                                      
		return "/pa_post_grade_add.jsp?menu_code=" + menu_code ;        
	}                           
}