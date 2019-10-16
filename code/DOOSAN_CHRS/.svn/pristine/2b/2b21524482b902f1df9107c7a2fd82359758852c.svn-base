package com.ait.pa.cmd.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-19
 * 
 */
public class PayBankCode extends PaAbstractCommand {

	private PaServices paServices = null;

	public PayBankCode() {
		paServices = PaServices.getInstance();
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("") && content.equals("PayBankCodeForSearch")) {
			returnPage = this.PayBankCodeForSearch(request, admin);
		} else if (!content.equals("") && content.equals("PayBankCodeForUpdate")) {
			returnPage = this.PayBankCodeForUpdate(request, admin);
		} else if (!content.equals("") && content.equals("PayBankCodePage")) {
			int pageSize = 20;
			int pageGroupSize = 10;
			int currentPage = 0;
			int resultCount = 0;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			returnPage = "/pa0209.jsp";
		} else if (!content.equals("") && content.equals("SearchCardno")) {
			returnPage = this.SearchCardno(request, admin);
		} else if (!content.equals("") && content.equals("SaveCardno")) {
			returnPage = this.SaveCardno(request, admin);
		} else {
			returnPage = "error.jsp";
		}
		return returnPage;
	}

	public String PayBankCodeForSearch(HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			String deptID = parameterObject.getString("deptID");
			parameterObject.put("deptID", deptID);
			parameterObject.set("paMonth", parameterObject.getString("year") + parameterObject.getString("month"));
			parameterObject.put("cpny_id", admin.getCpnyId());
			if (parameterObject.getString("person_id") == null || parameterObject.getString("person_id").equals("")) {
				parameterObject.put("person_id", "");
			}
			int pageSize = 20;
			int pageGroupSize = 10;
			int currentPage = 0;

			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = NumberUtil.parseInt(request.getParameter("currentPage"));

			int resultCount = 0;
			List employeeListForFlag = new ArrayList();

			resultCount = paServices.PayBankCodeForSearchNumberPa(parameterObject);
			employeeListForFlag = paServices.PayBankCodeForSearchPa(parameterObject, currentPage, pageSize);

			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("person_id", parameterObject.getString("person_id"));
			request.setAttribute("tableName", parameterObject.getString("tableName"));
			//request.setAttribute("bonusTypeCode", parameterObject.getString("bonusTypeCode"));
			//request.setAttribute("bonusNo", parameterObject.getString("bonusNo"));
			request.setAttribute("year", parameterObject.getString("year"));
			request.setAttribute("month", parameterObject.getString("month"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("deptID", deptID);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("employeeListForFlag", employeeListForFlag);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearch happens Exception. ", e);
		}

		return "/pa0209.jsp";

	}

	public String PayBankCodeForUpdate(HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			String[] args = request.getParameterValues("selectC");
			int size = args != null ? args.length : 0;
			for (int i = 0; i < size; i++) {
				parameterObject = new SimpleMap();
				parameterObject.set("empid", request.getParameter("empid" + args[i]));
				parameterObject.set("person_id", request.getParameter("person_id" + args[i]));
				parameterObject.set("flag", request.getParameter("calcFlag" + args[i]));
				parameterObject.set("bankCardName", request.getParameter("bankCardName" + args[i]));
				paServices.PayBankCodeForUpdate(parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("PayBankCodeForUpdate happens Exception. ", e);
		}

		return this.PayBankCodeForSearch(request, admin);

	}

	public String SearchCardno(HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			SimpleMap sm = new SimpleMap();
			String EMPID = "";
			String person_id = "";
			String CHINESENAME = "";
			String cardno = "";
			List employeeList = paServices.SearchCardno(parameterObject);
			for (int i = 0; i < employeeList.size(); i++) {
				sm = (SimpleMap) employeeList.get(i);
				EMPID = StringUtil.checkNull(sm.get("EMPID"));
				person_id = StringUtil.checkNull(sm.get("PERSON_ID"));
				CHINESENAME = StringUtil.checkNull(sm.get("CHINESENAME"));
				cardno = StringUtil.checkNull(sm.get("BANK_CARD_NO"));

			}
			request.setAttribute("empid", EMPID);
			request.setAttribute("person_id", person_id);
			request.setAttribute("chinesename", CHINESENAME);
			request.setAttribute("cardno", cardno);
			request.setAttribute("key", request.getParameter("key"));
			int pageSize = 20;
			int pageGroupSize = 10;
			int currentPage = 0;
			int resultCount = 0;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("PayBankCodeForUpdate happens Exception. ", e);
		}

		return "/pa0209.jsp";

	}

	public String SaveCardno(HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			SimpleMap sm = new SimpleMap();
			String EMPID = "";
			String CHINESENAME = "";
			String cardno = "";
			paServices.SaveCardno(parameterObject);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("PayBankCodeForUpdate happens Exception. ", e);
		}

		return this.SearchCardno(request, admin);

	}

}
