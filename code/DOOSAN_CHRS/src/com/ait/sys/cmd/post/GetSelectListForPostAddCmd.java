/*
 * @(#)GetSelectListForPostAdd.java 1.0 2007-9-9 下午07:38:12
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.sys.cmd.post;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.sy.service.SysService;
import com.ait.web.Command;


public class GetSelectListForPostAddCmd implements Command{
	
	
	public void putPostGroup(HttpServletRequest request) throws GlRuntimeException {

		try {

			SysService service = SysService.getInstance();

			List list = service.retrievePostGroupList();
          
			request.setAttribute("postgrouplist", list);     	               
            
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"HrmAbstractCommand put post tree to request Exception.", e);
		}
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		      this.putPostGroup(request);
		      
		      return "sy/postadd.jsp";    
	}
}

