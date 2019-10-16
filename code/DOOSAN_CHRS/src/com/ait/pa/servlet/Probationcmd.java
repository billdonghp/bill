package com.ait.pa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-12-11
 * 
 */
public class Probationcmd implements Command {
	private PaServices paServices = null;

	public Probationcmd() {
		paServices = PaServices.getInstance();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("") && content.equals("probationPage")) {
			returnPage = "/pa0507.jsp";
		} else if(!content.equals("") && content.equals("probationAdd")){
			returnPage =this.PayProbationSave(request, admin);
		}else if(!content.equals("") && content.equals("probationSearch")){
			returnPage =this.probationSearch(request, admin);
		}else if(!content.equals("") && content.equals("probationDelete")){
			returnPage=this.probationDelete(request, admin);
		}else if(!content.equals("") && content.equals("updateARHistoryData")){
			returnPage=this.updateARHistoryData(request, admin);
		}else{
			
			returnPage="/error.jsp";
		}
		return returnPage;
	}
	public String PayProbationSave(HttpServletRequest request,AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			parameterObject=ObjectBindUtil.getData(request);
			paServices.PayProbationSave(parameterObject);				

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"PayProbationSave happens Exception. ", e);
		}

		return this.probationSearch(request, admin);

	}
	public String probationSearch(HttpServletRequest request,AdminBean admin) {
		try {
			List list=null;

			SimpleMap parameterObject = null;
			parameterObject=ObjectBindUtil.getData(request);
			list=paServices.probationSearch(parameterObject);	
			request.setAttribute("EmloyeeList", list);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"probationSearch happens Exception. ", e);
		}

		return "/pa0507.jsp";

	}
	public String probationDelete(HttpServletRequest request,AdminBean admin) {
		try {			
			
			SimpleMap parameterObject = null;
			parameterObject=ObjectBindUtil.getData(request);			
			String[]empList=request.getParameterValues("empid");
			for(int i=0;i<empList.length;i++){				
				parameterObject.set("empno",empList[i]);
				paServices.probationDelete(parameterObject);				
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"probationDelete happens Exception. ", e);
		}

		return this.probationSearch(request, admin);

	}
	public String updateARHistoryData(HttpServletRequest request,AdminBean admin) {
		try {			
			
			SimpleMap parameterObject = null;
			parameterObject=ObjectBindUtil.getData(request);	
			parameterObject.set("paMonth", request.getParameter("year")+request.getParameter("month"));
             paServices.updateARHistoryData(parameterObject);	


		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"updateARHistoryData happens Exception. ", e);
		}
		request.setAttribute("alert", "修改数据成功");
		request.setAttribute("url", "/pa/pa0507.jsp?menu_code=pa0507");
		return "/inc/alertMessage.jsp";

	}

}
