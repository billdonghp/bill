/*
 * @(#)ViewPromoteCmd.java 1.0 2006-12-19 下午05:01:12
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.promote;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-19 下午05:16:53
 * @version 1.0
 * 
 */
public class ViewPromoteCmd extends HrmAbstractCommand {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    try{
	    	this.putBasicInfo(request);
		this.putToolbarInfo(request);

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request.getParameter("empID") : adminId;

		//职务等级 升级  特别升级/定期升级
		SimpleMap sMap = new SimpleMap();
		sMap.put("ADMINID", adminId);
		sMap.put("EMPID", empID);
		sMap.put("LIKE_UPGRADE", "TransCode3");
		sMap.put("ACTIVITY", 1);
		
		//司内经历 包含 所有发令信息
		SimpleMap sMap2 = new SimpleMap();
		sMap2.put("ADMINID", adminId);
		sMap2.put("EMPID", empID);
		//sMap2.put("NOT_LIKE_UPGRADE", "TransCode3");
		sMap2.put("ACTIVITY", 1);
		List expInsideList = services.getExpInside(sMap2);
		List upGradeList = services.getExpInside(sMap);
		request.setAttribute("expInsideList", expInsideList);
		request.setAttribute("expInsideListCnt", expInsideList.size());
		request.setAttribute("upGradeList", upGradeList);
		request.setAttribute("upGradeListCnt", upGradeList.size());
		request.setAttribute("empID", empID);
	    }catch(Exception e){
		  Logger.getLogger(getClass()).error(e.toString());
		  throw new GlRuntimeException("查讯员工发令信息失败!", e);
	    }
		return "/hrm/hrm_view_promote.jsp";
	}

}

