package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ait.commons.dao.CommonDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.web.Command;

public class GetevsSetupempcountCmd2 implements Command {

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
		SimpleMap parameterObject = new SimpleMap();
		String craft = StringUtils.trimToEmpty(request.getParameter("craft"));
		String deptke = StringUtils.trimToEmpty(request.getParameter("deptke"));
		String deptzhi = StringUtils.trimToEmpty(request.getParameter("deptzhi"));
		String deptzu = StringUtils.trimToEmpty(request.getParameter("deptzu"));
		String evDeptId = StringUtils.trimToEmpty(request.getParameter("evDeptId"));
		parameterObject.put("evDeptId", evDeptId);
		parameterObject.put("craft", craft);
		parameterObject.put("deptke", deptke);
		parameterObject.put("deptzhi", deptzhi);
		parameterObject.put("deptzu", deptzu);
		CommonDAO commonDAO = new CommonDAO();
		StringBuffer htmlPosition = new StringBuffer();
        
		try {
			
						
			List empDiff =null;
			if(evDeptId!=null)		
			  empDiff = commonDAO.retrieveSetupempcount2(parameterObject);

				JSONObject object = new JSONObject();				
				JSONObject paramObject = new JSONObject();

				
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
