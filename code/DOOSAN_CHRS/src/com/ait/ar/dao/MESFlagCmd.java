package com.ait.ar.dao;

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

public class MESFlagCmd implements Command {

    private PaServices paServices = null;
	
	PaServices paService = PaServices.getInstance();

	public MESFlagCmd() {
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
		if(!content.equals("") && content.equals("view")){
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			int resultCount=0;			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			returnPage="/ar_mesflag.jsp";
		}else if(!content.equals("") && content.equals("search")){
			returnPage=this.MESFlagForSearch(request, admin);
		}else if(!content.equals("") && content.equals("update")){
			returnPage=this.MESFlagForUpdate(request, admin);
		}else{
		 return "error.jsp";
		}
		return returnPage;
	}
	public String MESFlagForSearch(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			parameterObject=ObjectBindUtil.getData(request);
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int resultCount = paServices.MESFlagForSearchNumber(parameterObject);
			List departmentListForFlag=paServices.MESFlagForSearch(parameterObject,currentPage,pageSize);
			request.setAttribute("deptID", request.getParameter("deptID")!=null?request.getParameter("deptID"):"");			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("departmentListForFlag", departmentListForFlag);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"MESFlagForSearch happens Exception. ", e);
		}

		return "/ar_mesflag.jsp";

	}

	public String MESFlagForUpdate(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			String[] args = request.getParameterValues("selectC");
			int size=args!=null?args.length:0;
			for (int i=0;i<size;i++) {
				parameterObject= new SimpleMap();
				parameterObject.set("deptid",request.getParameter("deptid"+args[i]));
				parameterObject.set("flag",request.getParameter("MESFlag"+args[i]));	
				parameterObject.set("activeFlag",request.getParameter("activeFlag"+args[i]));		
				paServices.MESFlagForUpdate(parameterObject);				
			}
			request.setAttribute("deptID", request.getParameter("deptID")!=null?request.getParameter("deptID"):"");			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"MESFlagForUpdate happens Exception. ", e);
		}
		return this.MESFlagForSearch(request, admin);
	}


}
