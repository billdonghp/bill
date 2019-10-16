package com.ait.ar.cmd.arDetailBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

public class RetrieveArDetialBackListCmd extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		ArServices arServices = new ArServices();
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject;
		String url = "" ;
		
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());
			
			String flag = request.getParameter("flag") != null ? request.getParameter("flag") : "search";
			if (flag.equals("search")) {
				url = "/ar_detailBack.jsp?menu_code=" + parameterObject.getString("menu_code") ;
				
				/* paging logic */
				UserConfiguration config = UserConfiguration.getInstance("/system.properties");
				int pageSize = config.getInt("page.style1.pagesize");
				int pageGroupSize = config.getInt("page.style1.pagegroupsize");
				int currentPage = 0;
				// if has currentpage set value into currentPage
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
				int resultCount = 0 ;
				List detailList = new ArrayList() ;
				if ((parameterObject.getString("sDate") != null && !parameterObject.getString("sDate").equals("")
					&& parameterObject.getString("eDate") != null && !parameterObject.getString("eDate").equals("") )
				 || (parameterObject.getString("sDateU") != null && !parameterObject.getString("sDateU").equals("")
					&& parameterObject.getString("eDateU") != null && !parameterObject.getString("eDateU").equals("") )
				){
					resultCount = arServices.getDetailBackCnt(parameterObject);	
				
					// 如果"当前页"大于最大页数,取最后一页
					if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {
		
						currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
					}
				
					parameterObject.set("startRownum", currentPage * pageSize);
					parameterObject.set("endRownum", (currentPage+1) * pageSize);
					detailList = arServices.getDetailBackList(parameterObject, currentPage, pageSize);
				}
				
				List arItemList = arServices.getItemList();
	
				request.setAttribute("key", parameterObject.get("key"));
				request.setAttribute("personId", parameterObject.get("personId"));
				request.setAttribute("deptid", parameterObject.get("deptid"));
				request.setAttribute("sDate", parameterObject.getString("sDate"));
				request.setAttribute("eDate", parameterObject.getString("eDate"));
				request.setAttribute("sDateU", parameterObject.get("sDateU"));
				request.setAttribute("eDateU", parameterObject.get("eDateU"));
				request.setAttribute("arItemList", arItemList);
				request.setAttribute("detailList", detailList);
				request.setAttribute("itemNo", parameterObject.get("itemNo")) ;
				request.setAttribute("sLength", parameterObject.get("sLength")) ;
				request.setAttribute("eLength", parameterObject.get("eLength")) ;
				request.setAttribute("empType", parameterObject.get("empType")) ;
				
				// paging parameter
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
			}
			else if (flag.equals("report")) {
				List datalist = new ArrayList() ;
				if ((parameterObject.getString("sDate") != null && !parameterObject.getString("sDate").equals("")
						&& parameterObject.getString("eDate") != null && !parameterObject.getString("eDate").equals("") )
					 || (parameterObject.getString("sDateU") != null && !parameterObject.getString("sDateU").equals("")
						&& parameterObject.getString("eDateU") != null && !parameterObject.getString("eDateU").equals("") )
					){
					datalist = arServices.getDetailBackList(parameterObject);
				}
				request.setAttribute("datalist", datalist);
				url = "reports/ar_report/arDetailBackReport.jsp";
			}
			

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}

		return url ;
	}
}
