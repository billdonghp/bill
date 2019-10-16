/*
 * @(#)ViewTranslateCmd.java 1.0 2006-12-19 下午05:16:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.translate;

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
public class ViewTranslateCmd extends HrmAbstractCommand {

    @SuppressWarnings("unchecked")
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	try {
	    this.putBasicInfo(request);
	    this.putToolbarInfo(request);

	    HrmServices services = HrmServices.getInstance();
	    // get employee ID
	    String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
	    String empID = request.getParameter("empID") != null ? request.getParameter("empID") : adminId;

	    SimpleMap sMap = new SimpleMap();
	    sMap.put("ADMINID", adminId);
	    sMap.put("EMPID", empID);
	    List suspendList = services.getSuspension(sMap);
	    List pluralityList = services.getPlurality(sMap);
	    List dispatchList = services.getDispatch(sMap);
	    request.setAttribute("suspendList", suspendList);
	    request.setAttribute("suspendListCnt", suspendList.size());
	    request.setAttribute("pluralityList", pluralityList);
	    request.setAttribute("pluralityListCnt", pluralityList.size());
	    request.setAttribute("dispatchList", dispatchList);
	    request.setAttribute("dispatchListCnt", dispatchList.size());
	    request.setAttribute("empID", empID);
	} catch (Exception e) {
	    Logger.getLogger(getClass()).error(e.toString());
	    throw new GlRuntimeException("查讯员工发令信息失败!", e);
	}
	return "/hrm/hrm_view_translate.jsp";
    }

}
