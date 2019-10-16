package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class GetEmpDivisionCmd implements Command {

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
//		if(request.getParameter("loginId") != null && !request.getParameter("loginId").equals(""))
//			admin.setUsername(request.getParameter("loginId"));
		
		//AD登录验证
		if(request.getParameter("loginId") != null && !request.getParameter("loginId").equals(""))
			admin.setAd_User_Id(request.getParameter("loginId"));
	
		SysService service = SysService.getInstance();
		SimpleMap parameterObject = new SimpleMap();
		
//		parameterObject.setString("adminid", admin.getUsername());
		parameterObject.setString("adminid", admin.getAd_User_Id());
		
		parameterObject.setString("cpnyId", admin.getCpnyId());

		try {

			List empDiff = service.getEmpDiff(parameterObject);

			JSONObject object = new JSONObject();
			JSONArray paramArray = new JSONArray();
			JSONObject paramObject = new JSONObject();

			paramArray.put(0, empDiff);
			paramObject.put("diffCnt", empDiff.size());
			paramObject.put("cpnyId", admin.getCpnyId());
			
			object.put("empDiff", empDiff);
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
