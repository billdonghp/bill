/*
 * @(#)RetrieveDataPostForUpdate.java 1.0 2007-9-8 上午02:04:05
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService;
import com.ait.web.Command;  

public class RetrievedataPostForUpdate implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  String id=request.getParameter("postid");  
		  
		  SysService syservice=SysService.getInstance();
		  
		  SimpleMap map = (SimpleMap) syservice.getPostById(id);
		  
		  List list = syservice.retrievePostGroupList();
          
			request.setAttribute("postgrouplist", list);     
			
		    request.setAttribute("simplemap",map);                            
		                                        
				return "sy/postupdate.jsp";    
	}               
}
