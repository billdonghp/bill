/*
 * @(#)RetrievedataForUpdatePostGroupCmd.java 1.0 2007-9-7 上午12:01:30
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postgroup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService; 
import com.ait.web.Command;


public class RetrievedataForUpdatePostGroupCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    SysService syservice=SysService.getInstance();
    
    String id=request.getParameter("postgroupid");
    
    request.getSession(false).setAttribute("XID",id);
    
    SimpleMap map = (SimpleMap) syservice.getPostGroupForUpdate(id);  
           
    request.setAttribute("simplemap",map);                   
                                      
		return "sy/postgroupupdate.jsp";        
	}                                                                                                                 
}
