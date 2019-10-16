/*
 * @(#)GetPresentInfoCmd.java 1.0 2009-7-15 下午04:05:41
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-15 下午04:05:41
 * @version 1.0
 * 
 */
public class GetPresentInfoCmd implements Command {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		GaServices service = new GaServices();
		SimpleMap parameterObject = ObjectBindUtil.getData(request);
		JSONObject object = new JSONObject();
		//JSONArray paramArray = new JSONArray();
		JSONObject paramObject = new JSONObject();
		
		try {
			//if(parameterObject.getString("presentId").contains("GLBLP"))
			//   parameterObject.setString("cpnyId", "78888888");
		//	else 
				parameterObject.setString("cpnyId", admin.getCpnyId());
			// get present information list
			List presentInfoList = service.retrievePresentInformation(parameterObject);
			SimpleMap presentInfo;
			if (presentInfoList.size() > 0) {
				
				presentInfo = (SimpleMap)presentInfoList.get(0);
				paramObject.put("unit", StringUtil.checkNull(presentInfo.getString("UNIT_NAME"),"&nbsp;"));
				paramObject.put("unitPrice", presentInfo.getString("UNIT_PRICE"));
				paramObject.put("stock", presentInfo.getString("PRESENT_STOCK"));

			}else{
				paramObject.put("unit", "&nbsp;");
				paramObject.put("unitPrice","&nbsp;");
				paramObject.put("stock", "0");
			}
			
			
			// get present apply quentity list
			List presentApplyQuentityList = service.retrievePresentApplyQuentity(parameterObject);
			SimpleMap applyQuentity;
			if (presentApplyQuentityList.size() > 0) {
				
			    applyQuentity = (SimpleMap)presentApplyQuentityList.get(0);
			    paramObject.put("applyQuentity", applyQuentity.getString("APPLY_QUENTITY"));
			}else{
			    paramObject.put("applyQuentity", 0);
			}
			
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

			throw new GlRuntimeException("Get present information Exception.", e);
		}
		return "";
	}

}

