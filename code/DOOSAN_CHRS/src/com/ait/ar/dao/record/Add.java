package com.ait.ar.dao.record;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArRecordsBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class Add implements Command {
	private String returnR = null;

	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String menu_code = request.getParameter("menu_code");
		String empId = request.getParameter("empID");// 员工ID

		String enterDate = request.getParameter("enterDate");// 进门日期
		String inH = request.getParameter("InH");// 进门小时
		String inM = request.getParameter("InM");// 进门分钟
		String inS = request.getParameter("InS");// 进门秒

		String outDate = request.getParameter("outDate");// 进门日期
		String outH = request.getParameter("outH");// 出门小时
		String outM = request.getParameter("outM");// 出门分钟
		String outS = request.getParameter("outS");// 出门秒

		int insertEnterAffRow = 0;
		int insertOutAffRow = 0;

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute(
				"admin");
		String loginUserId = admin.getAdminID();

		try {

			// 正确获取进门参数
			if (enterDate != null && !enterDate.equalsIgnoreCase("")
					&& inH != null && !inH.equalsIgnoreCase("") && inM != null
					&& !inM.equalsIgnoreCase("") && inS != null
					&& !inS.equalsIgnoreCase("")) {

				insertEnterAffRow = ArRecordsBean.addRescords(empId, // 工号
						enterDate.trim() + " " + inH.trim() + ":" + inM.trim()
								+ ":" + inS.trim(), // 补录打卡时间按照 YYYY-MM-DD
						// HH24:MI:SS格式
						"IN", // 进门或出门 IN 进门卡 OUT 出门卡 仅限这两种
						loginUserId);// 录入者ID(工号)
			}

			// 正常获取出门参数
			if (outDate != null && !outDate.equalsIgnoreCase("")
					&& outH != null && !outH.equalsIgnoreCase("")
					&& outM != null && !outM.equalsIgnoreCase("")
					&& outS != null && !outS.equalsIgnoreCase("")) {
				// state = is out
				// "TO_DATE('" + outt + "','YYYY-MM-DD HH24:MI:SS')";
				insertOutAffRow = ArRecordsBean.addRescords(empId, // 工号
						outDate.trim() + " " + outH.trim() + ":" + outM.trim()
								+ ":" + outS.trim(), // 补录打卡时间按照 YYYY-MM-DD
						// HH24:MI:SS格式
						"OUT", // 进门或出门 IN 进门卡 OUT 出门卡 仅限这两种
						loginUserId);// 录入者ID(工号)
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Insert ID Card data Exception." + e.toString());
			throw new GlRuntimeException("Insert ID Card data Exception.", e);
		}

		returnR = "/arrecords_a.jsp?menu_code=" + menu_code + "&insertIn="
				+ insertEnterAffRow + // 插入进门卡记录影响的行数
				"&insertOut=" + insertOutAffRow + // 插入出门卡记录影响的行数
				"&seq=" + System.currentTimeMillis();// 刷页面用的
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
