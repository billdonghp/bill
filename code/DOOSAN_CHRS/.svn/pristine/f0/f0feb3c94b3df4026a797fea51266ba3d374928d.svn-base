package com.ait.ess.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class AbstractCommand implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/** 基本验证
	 * @param request
	 * @param response
	 * @return String
	 * @throws ServletException
	 * @throws IOException
	 */
	public String basicValidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
	    if(session == null){
	      return "/expired.jsp";
	    }
	    AdminBean admin = (AdminBean)session.getAttribute("admin");
	    if (admin == null) {
	     return "/expired.jsp";
	   }
	    return null;
	}

}
