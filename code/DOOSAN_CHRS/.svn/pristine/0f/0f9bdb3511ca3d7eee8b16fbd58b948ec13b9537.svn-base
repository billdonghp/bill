/*
 * @(#)ViewPostLevelCmd.java 1.0 2007-9-9 下午03:12:05
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postlevel;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sy.service.SysService;
import com.ait.web.Command;


public class ViewPostLevelCmd implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    SysService syservice=SysService.getInstance();
   
    List listdata= syservice.getPostLevelList();   
                                    
   
    request.setAttribute("postlevellist",listdata);
       
		return "sy/postgradelevel.jsp";             
	}      
}
