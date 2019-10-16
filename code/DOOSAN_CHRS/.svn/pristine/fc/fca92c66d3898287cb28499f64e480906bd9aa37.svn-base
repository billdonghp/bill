/*
 * @(#)DeleteRewardCmd.java 1.0 2007-1-4 上午09:12:59
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

public class DeleteRewardCmd extends HrmAbstractCommand {

	@Override
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
		ObjectBindUtil.setFormBean(request, new Reward(), rewardInfoList,
				Integer.parseInt(request.getParameter("rewardCnt")), "_");

		// filtrate data
		this.filtrateData(rewardInfoList, request, "reward");

		services.deleteRewardInfo(rewardInfoList, true);

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
	 * @param request
	 * @param type
	 */
	private void filtrateData(List list, HttpServletRequest request, String type) {

		for (int i = list.size(); i > 0; i--) {

			if (request.getParameter(type + "_" + i) == null
					|| request.getParameter(type + "_" + i).equals("")) {

				list.remove(i - 1);
			}

		}

	}
}
