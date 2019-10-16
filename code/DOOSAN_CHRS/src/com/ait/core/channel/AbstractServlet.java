package com.ait.core.channel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.core.SysConstants;
import com.ait.core.exception.GlRuntimeException;

public abstract class AbstractServlet extends HttpServlet {

     public AbstractServlet(){
        super();
    }
     
    protected abstract void catchService(HttpServletRequest req, HttpServletResponse res);

    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        catchService(req, res) ;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        catchService(req, res) ;
    }

	protected void process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String todo = req.getParameter(SysConstants.CommandParamName);
		String returnUrl = CommandEngine.executeCommand(req, res, todo);
		if (returnUrl != null)
			dispatchRequest(req,res,returnUrl);
	}

	protected void dispatchRequest(HttpServletRequest req,HttpServletResponse res,String url){
		try{
			req.getRequestDispatcher(url).forward(req,res);
		}
		catch(Exception ex){
			throw new GlRuntimeException("页面派遣失败，非法的url："+url);
		}
	}
}
