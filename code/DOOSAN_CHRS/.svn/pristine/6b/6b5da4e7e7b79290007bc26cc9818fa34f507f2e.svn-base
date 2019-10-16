/*
 * @(#)RetrievePresentApplyInfoCmd.java 1.0 2009-7-16 上午10:03:25
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-16 上午10:03:25
 * @version 1.0
 * 
 */
public class RetrievePresentApplyInfoCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.putToolbarInfo(request);
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List recordList = new ArrayList();

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("applicant", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("applicant", "");
			parameterObject.set("ADMINID", admin.getAdminID());
			}
		}
			
			// retrieve apply list
			List<SimpleMap> applyList = services.retrievePresentApplyList(parameterObject, currentPage, pageSize);
			Object applyCount = services.retrievePresentApplyCnt(parameterObject);
			
			List detailList;
			List affirmList;
			for (SimpleMap applyInfo : applyList) {
				
				parameterObject.setString("applyNo", applyInfo.getString("SEQ_NO"));
				// retrieve detail list
				detailList = services.retrievePresentApplyDetail(parameterObject);
				// retrieve affirm list
				affirmList = services.retrievePresentAffirmList(parameterObject);
				
				applyInfo.set("detailList", detailList);
				applyInfo.set("affirmList", affirmList);
				recordList.add(applyInfo);
			}

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(applyCount.toString()));

			// search parameter
			request.setAttribute("personId", parameterObject.getString("personId"));
			request.setAttribute("empId", parameterObject.getString("empId"));
			request.setAttribute("flag", parameterObject.getString("flag"));
			request.setAttribute("deptId", parameterObject.getString("deptId"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("adminID", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve present apply information Exception. ", e);
		}

		return "/ga_present_apply_info.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

}

