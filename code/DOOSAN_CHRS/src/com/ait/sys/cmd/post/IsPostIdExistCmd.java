/*
 * @(#)IsPostIdExistCmd.java 1.0 2007-9-11 下午06:08:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sy.service.SysService;
import com.ait.web.Command;


public class IsPostIdExistCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   SysService syservice=SysService.getInstance();
	    
	   String postid=request.getParameter("postid");
	              
	  boolean isPostIdExist= syservice.isPostIdExist(postid);
	  
	  if(isPostIdExist==true)               
	  {  
		  request.setAttribute("message","Inputed id is already Exist,Please change another one");     
	  }    
	  else{
		  request.setAttribute("postid",postid);        
	  } 
	  List list = syservice.retrievePostGroupList();
      
	 request.setAttribute("postgrouplist", list);  
	
      return "sy/postadd.jsp";  
	}	              
}

