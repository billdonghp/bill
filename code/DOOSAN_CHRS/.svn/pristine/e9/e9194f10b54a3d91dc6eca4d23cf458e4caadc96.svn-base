/*
 * @(#)RetrievedataForUpdatePostLevelCmd.java 1.0 2007-9-9 下午03:12:50
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.postlevel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.service.SysService;
import com.ait.util.StringUtil;
import com.ait.web.Command;


public class RetrievedataForUpdatePostLevelCmd  implements  Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    SysService syservice=SysService.getInstance();
    String postlevelid=  StringUtil.toCN(request.getParameter("postlevelid"));
    request.getSession(false).setAttribute("XID",postlevelid);
    SimpleMap simplemap=(SimpleMap) syservice.getPostLevelById(postlevelid);
    request.setAttribute("simplemap",simplemap);  
	return "sy/postgradelevelupdate.jsp";                 
	}

}
  
