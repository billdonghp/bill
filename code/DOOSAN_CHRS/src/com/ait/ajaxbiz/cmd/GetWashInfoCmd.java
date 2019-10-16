/*
 * @(#)GetWashInfoCmd.java 1.0 2009-7-23 下午03:56:51
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
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-23 下午03:56:51
 * @version 1.0
 * 
 */
public class GetWashInfoCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		GaServices service = new GaServices();
		SimpleMap parameterObject = ObjectBindUtil.getData(request);
		parameterObject.setString("cpnyId", admin.getCpnyId());
		JSONObject object = new JSONObject();
		//JSONArray paramArray = new JSONArray();
		JSONObject paramObject = new JSONObject();
		
		try {

			// get clothing information list
			List clothingInfoList = service.retrieveClothingList(parameterObject);
			SimpleMap clothingInfo;
			if (clothingInfoList.size() > 0) {
				
				clothingInfo = (SimpleMap)clothingInfoList.get(0);
				paramObject.put("unit", StringUtil.checkNull(clothingInfo.getString("UNIT_NAME"),"&nbsp;"));
				paramObject.put("unitPrice", clothingInfo.getString("UNIT_PRICE"));
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

			throw new GlRuntimeException("Get wash information Exception.", e);
		}
		return "";
	}

}

