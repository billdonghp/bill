/*
 * @(#)RetrieveDataForUpdateRewardCmd.java 1.0 2006-12-19 下午05:12:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.reward;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class RetrieveDataForUpdateRewardCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putBasicInfo(request);
		this.putToolbarInfo(request);

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		SimpleMap sMap = new SimpleMap();
		sMap.put("ADMINID", adminId);
		sMap.put("empID", empID);
		
		SimpleMap punishMentMap = new SimpleMap();
		punishMentMap.put("ADMINID", adminId);
		punishMentMap.put("EMPID", empID);
		
		List punishMentList = services.getPunishMent(punishMentMap);
		List rewardList = services.retrieveRewardInfo(sMap);

		request.setAttribute("punishMentList", punishMentList);
		request.setAttribute("punishMentListCnt", punishMentList.size());
		request.setAttribute("rewardList", rewardList);
		request.setAttribute("rewardCnt", rewardList.size());
		request.setAttribute("empID", empID);
		request.setAttribute("adminId", adminId);

		return "/hrm/hrm_modify_reward.jsp";
	}

}
