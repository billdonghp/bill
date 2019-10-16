package com.ait.ar.dao.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArRecordsBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	public Update() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int affRow = 0;
		ArRecordsBean dao = new ArRecordsBean();
		String sDate = request.getParameter("rTime");// 原来的时间
		Logger.getLogger(getClass()).debug(sDate);
		String tDateTime = // 修改后的时间
		request.getParameter("date") + " " + request.getParameter("hh") + ":"
				+ request.getParameter("mm") + ":" + request.getParameter("ss");
		Logger.getLogger(getClass()).debug(tDateTime);
		String empId = request.getParameter("empId");// 被修改的工号
		Logger.getLogger(getClass()).debug(empId);
		String modifyEmpId = request.getParameter("modifyEmpId");// 修改后的工号
		Logger.getLogger(getClass()).debug(modifyEmpId);
		String active = request.getParameter("active");
		String doorType = request.getParameter("doorType");// 修改进出们
		List values = new ArrayList();
		values.add(0, tDateTime);// 修改后的时间
		values.add(1, doorType);// 修改后的进门或者出门
		values.add(2, active);// 是否可以读取
		values.add(3, modifyEmpId);// 修改后的工号
		values.add(4, empId);// 被修改的记录的工号，作为条件where
		values.add(5, sDate);// 原来的时间，作为条件where

		try {

			affRow = dao.editRescords(values);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Update ID Card data Exception." + e.toString());
			throw new GlRuntimeException("Update ID Card data Exception.", e);
		}

		returnR = "/arrecordslist.jsp?menu_code="
				+ request.getParameter("menu_code") + "&affRow=" + affRow;
		Logger.getLogger(getClass()).debug("return page : " + returnR);
		return returnR;
	}
}
