package com.ait.core.channel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.core.SysConstants;

public class DefaultServlet extends AbstractServlet{

	private static final long serialVersionUID = -5055113329621559599L;

	public DefaultServlet() {
		super();
	}
	
	protected void catchService(HttpServletRequest req, HttpServletResponse res) {
		try{
			process(req,res);
		}
		catch(Exception ex){
			req.setAttribute(SysConstants.ExceptionParamName,ex);
			dispatchRequest(req,res,"/sys/errorpage.jsp"); 
		}
	}

}
