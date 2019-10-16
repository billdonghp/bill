package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import DSGAuthPkg.Auth;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class CheckADLoginCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		if(admin == null) 
			admin = new AdminBean();
		if(request.getParameter("userId") != null && !request.getParameter("userPwd").equals(""))
			admin.setUsername(request.getParameter("userId"));
		    admin.setPassword(request.getParameter("userPwd"));

		try {
			
			//SysService service = SysService.getInstance();
			//SimpleMap parameterObject = new SimpleMap();
			
			//parameterObject.setString("userId", admin.getUsername());
			//parameterObject.setString("userPwd", admin.getPassword());

			//List empDiff = service.getEmpDiff(parameterObject);
			
			Auth oAuth = new Auth("authsj.corp.doosan.com", "dsg\\"+admin.getUsername(), admin.getPassword());
			Boolean bRet = oAuth.validateUser(admin.getUsername(), admin.getPassword());  
			String message = "";
			if (bRet) {
				message = "OK";
			} else {
				message = "NG";
			}

			JSONObject object = new JSONObject();
			JSONObject paramObject = new JSONObject();

			paramObject.put("message", message);
			object.put("param", paramObject);
			
	    	response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");

			PrintWriter out = response.getWriter();
			String str = object.toString();
			
	    	out.println(str);
	        out.close();
		} catch (Exception e) {

			throw new GlRuntimeException("Get employee division Exception.", e);
		}
		return "";
	}
}
