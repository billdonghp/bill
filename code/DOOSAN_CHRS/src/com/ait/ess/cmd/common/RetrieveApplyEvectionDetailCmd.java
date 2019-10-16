/*
 * @(#)RetrieveApplyLeaveDetailCmd.java 1.0 2007-6-20 下午03:14:05
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ess.cmd.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-6-20 下午03:14:05
 * @version 1.0
 * 
 */
public class RetrieveApplyEvectionDetailCmd implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		EssServices essServices = new EssServices();

		String applyLeaveNo = request.getParameter("leaveNo");

		SimpleMap applyLeaveDetail = (SimpleMap) essServices
				.retrieveApplyLeaveDetail(applyLeaveNo);
		
		SimpleMap smap = new SimpleMap();
		smap.setString("LEAVE_NO",applyLeaveNo);
		smap.setString("TYPE", "EvectionApply");
		
		List applyAffirmor = (List)essServices.retrieveApplyAffirmor(smap);

		applyLeaveDetail.setString("LEAVE_REASON", applyLeaveDetail.getString(
				"LEAVE_REASON") == null ? null : applyLeaveDetail.getString("LEAVE_REASON").replaceAll("\r", "<br>"));

		request.setAttribute("applyLeaveDetail", applyLeaveDetail);
		request.setAttribute("applyAffirmor", applyAffirmor);
		request.setAttribute("size", applyAffirmor.size());

		return "/ess_evection_apply_detail.jsp";
	}

}
