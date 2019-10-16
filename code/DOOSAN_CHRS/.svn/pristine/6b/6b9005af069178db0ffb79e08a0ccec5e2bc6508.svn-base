/*
 * @(#)ViewTrainingCmd.java 1.0 2006-12-19 下午05:14:14
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.training;

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
public class ViewTrainingCmd extends HrmAbstractCommand {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			this.putBasicInfo(request);
			this.putToolbarInfo(request);
			HrmServices services = HrmServices.getInstance();
			// get employee ID
			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String empID = request.getParameter("empID") != null ? request
					.getParameter("empID") : adminId;

			// 研修
			SimpleMap sMap = new SimpleMap();
			sMap.put("ADMINID", adminId);
			sMap.put("EMPID", empID);
			sMap.put("TRANSCODE", "TransTraining01");
			sMap.put("ACTIVITY", 1);

			// 留学
			SimpleMap sMap2 = new SimpleMap();
			sMap2.put("ADMINID", adminId);
			sMap2.put("EMPID", empID);
			sMap2.put("TRANSCODE", "TransTraining02");
			sMap2.put("ACTIVITY", 1);

			List studyList = services.getStudying(sMap);
			List study2List = services.getStudying(sMap2);
			// get training inside information and training outside information
			List trainingInsideList = services.retrieveTrainingInside(empID);
			List trainingOutsideList = services.retrieveTrainingOutside(empID);

			request.setAttribute("studyList", studyList);
			request.setAttribute("studyListCnt", studyList.size());
			request.setAttribute("study2List", study2List);
			request.setAttribute("study2ListCnt", study2List.size());
			request.setAttribute("trainingInsideList", trainingInsideList);
			request
					.setAttribute("trainingInsideCnt", trainingInsideList
							.size());  
			request.setAttribute("trainingOutsideList", trainingOutsideList);
			request.setAttribute("trainingOutsideCnt", trainingOutsideList
					.size());
			request.setAttribute("empID", empID);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("查讯员工培训信息失败!", e);
		}
		return "/hrm/hrm_view_training.jsp";
	}

}
