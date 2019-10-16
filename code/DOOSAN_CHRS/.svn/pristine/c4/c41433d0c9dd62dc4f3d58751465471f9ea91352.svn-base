/*
 * @(#)InsertRewardCmd.java 1.0 2006-12-19 下午05:08:09
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.cmd.empinfo.reward;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.hrm.bean.Reward;
import com.ait.hrm.business.HrmServices;
import com.ait.hrm.servlet.HrmAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
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
public class InsertRewardCmd extends HrmAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HrmServices services = HrmServices.getInstance();
		// get employee ID
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String empID = request.getParameter("empID") != null ? request
				.getParameter("empID") : adminId;

		SimpleMap sMap = new SimpleMap();
		sMap.put("ADMINID", adminId);
		sMap.put("empID", empID);

		// declare bind object
		List rewardInfoList = new ArrayList();

		// bind reward form data to list object
		ObjectBindUtil.setFormBean(request, new Reward(), rewardInfoList, 3,
				"_");

		// filtrate data
		this.filtrateData(rewardInfoList, request.getParameter("rewardSign")
				.split(","));

		services.insertRewardInfo(rewardInfoList, true);

		request.setAttribute("alert", "保存成功");
		request.setAttribute("url",
				"/hrmControlServlet?operation=viewReward&menu_code="
						+ request.getParameter("menu_code")+"&empID="+empID);

		return "/inc/alertMessage.jsp";
	}

	/**
	 * filtrate bean object
	 * 
	 * @param list
	 * @param row
	 */
	private void filtrateData(List list, String[] row) {

		// remove bean object by row number array
		for (int i = row.length; i > 0; i--) {

			if (row[i - 1].equals(""))
				return;

			list.remove(Integer.parseInt(row[i - 1]) - 1);
		}
	}

}
