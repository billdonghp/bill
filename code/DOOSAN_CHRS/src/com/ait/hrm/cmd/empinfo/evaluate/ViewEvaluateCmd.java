/*
 * @(#)ViewEvaluateCmd.java 1.0 2006-12-19 下午04:51:56
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.evaluate;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sy.bean.AdminBean;

/**   
 * Copyright: AIT (c) Company: AIT   
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-19 下午05:16:53
 * @version 1.0
 * 
 */                         
public class ViewEvaluateCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.putBasicInfo(request);
		this.putToolbarInfo(request);
		HrmServices services = HrmServices.getInstance();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request.getParameter("empID") : adminId;
		List evaluateList = services.retrieveEvaluate(empID);  
		request.setAttribute("evaluateList", evaluateList);
		request.setAttribute("evaluateListCnt", evaluateList.size());
		request.setAttribute("empID", empID);
		return "/hrm/hrm_view_evaluate.jsp";
	}
}
