/*
 * @(#)DeletePostCmd.java 1.0 2007-9-8 上午02:02:45
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.sy.service.SysService;
import com.ait.sy.servlet.SysAbstractCommand;


public class DeletePostCmd extends SysAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("postid");
        SysService syservice = SysService.getInstance();
		syservice.deletePost(id);                                        
		return "syControlServlet?operation=viewpostcmd";             
	}
}

