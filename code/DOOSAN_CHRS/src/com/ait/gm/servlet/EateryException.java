package com.ait.gm.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;

public class EateryException extends ArAbstractCommand {
	SimpleMap parameterObject =new SimpleMap();
	GMDAO services = new GMDAO();
	String adminId = "";
	String cpnyId="";

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String content =request.getParameter("content");
		parameterObject.set("cpnyId",cpnyId);
		SimpleDateFormat  s = new SimpleDateFormat("yyyy-MM-dd");		
		String returnpage="";
		if(content.equals("view")){
			
			returnpage=this.getViewException(request,response);
		}else if(content.equals("addView")){
			List gmType = services.Gm_Type(parameterObject);
			request.setAttribute("gmType", gmType);
			request.setAttribute("today", s.format(new Date()));
			returnpage="gm/gm_add_eateryException.jsp?menu_code=" + parameterObject.getString("menu_code");
		}else if(content.equals("addInfo")){
			returnpage=this.addInfo(request, response);
		}else{
			returnpage="error.jsp";
		}
		return returnpage;		
			
		}
	public String getViewException(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject.set("listDDS", request.getParameter("listDDS"));
			parameterObject.set("listDDE", request.getParameter("listDDE"));
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			
			
			List gmType = services.Gm_Type(parameterObject);
//			 取得数据行数
			int resultCount = services.LookEateryExceptionCount(parameterObject);

			List LookList = services.LookEateryExceptionList(parameterObject,currentPage, pageSize);
			
			request.setAttribute("LookList", LookList);
			request.setAttribute("resultCount", NumberUtil
					.parseInt(resultCount));
			request.setAttribute("gmType",gmType);
			request.setAttribute("listDDS", request.getParameter("listDDS"));
			request.setAttribute("listDDE", request.getParameter("listDDE"));
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getViewException Exception. ", e);
		}

		return "gm/gm_EateryException.jsp?menu_code=" + request.getParameter("menu_code");
	}	
	
public String addInfo(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		try {			
			parameterObject.set("listDD", request.getParameter("listDD"));
			parameterObject.set("gmtype", request.getParameter("gmtype"));
			parameterObject.set("Remarks", request.getParameter("Remarks"));
			parameterObject.set("adminId", adminId);	
			
			services.add_eateryExceptionInformation(parameterObject);
			request.setAttribute("error", "添加成功");
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addInfo Exception. ", e);
		}

		return this.getViewException(request, response);
	}			
		
		
}
