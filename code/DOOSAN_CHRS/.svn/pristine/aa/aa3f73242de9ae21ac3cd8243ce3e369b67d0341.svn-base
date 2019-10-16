/*
 * @(#)ViewPostGroupCmd.java 1.0 2007-9-6 下午11:59:32
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postgroup;               

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;


public class ViewPostGroupCmd implements   Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    SysService syservice=SysService.getInstance();
      
    List listdata=syservice.getPostGroup();    
   
    request.setAttribute("postgrouplist",listdata);
       
		return "sy/postgroup.jsp";               
	}  
	
}

