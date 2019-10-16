/*
 * @(#)ArVacationCommand.java 1.0 2007-10-22 下午07:59:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.arDetailView;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetail;
import com.ait.ar.business.ArServices;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.business.HrmServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;

public class ArDetailViewCmd extends ArAbstractCommand {
	private ArServices arServices ;
	public ArDetailViewCmd() {
		arServices = new ArServices();
		
	}
	
	
	public String execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		 TODO Auto-generated method stub
		

		
		this.putToolbarInfo(request);
		ArServices services = new ArServices();
		//AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		//SimpleMap parameterObject;

		String sDate = StringUtil.checkNull(request.getParameter("sDate"));
		String eDate = StringUtil.checkNull(request.getParameter("eDate"));
		String personId = StringUtil.checkNull(request.getParameter("personId"));
		String deptid = StringUtil.checkNull(request.getParameter("deptid"));
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		String itemNo = StringUtil.checkNull(request.getParameter("itemNo"));
		String sLength = StringUtil.checkNull(request.getParameter("sLength"));
		String eLength = StringUtil.checkNull(request.getParameter("eLength"));
		String empType = StringUtil.checkNull(request.getParameter("empType")); 
		String tableName = StringUtil.checkNull(request.getParameter("tableName"),"AR_DETAIL");
		String key = StringUtil.checkNull(request.getParameter("key"));
		String name = StringUtil.checkNull(request.getParameter("name"));
		
		if("".equals(key) || key==null){
			 key = admin.getUsername();
			 //name = admin.getChineseName();
			 
		}
		
		if (sDate.equals("")) {
			Calendar arDateStart = Calendar.getInstance() ;
			Calendar arDateEnd = Calendar.getInstance() ;
			arDateStart.add(Calendar.DAY_OF_YEAR, -8) ;
			arDateEnd.add(Calendar.DAY_OF_YEAR, -1) ;
			
			sDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(arDateStart.getTime());
			eDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(arDateEnd.getTime());

		}
		//String flag = request.getParameter("flag") != null ? request.getParameter("flag") : "search";
		//String url = "/ar_detail.jsp";
		
			try {
				/* paging logic */
				UserConfiguration config = UserConfiguration.getInstance("/system.properties");
				int pageSize = config.getInt("page.style1.pagesize");
				int pageGroupSize = config.getInt("page.style1.pagegroupsize");
				int currentPage = 0;
				// if has currentpage set value into currentPage
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));

				List arItemList = arServices.getItemList();
				int resultCount = arServices.getDetailPersonListCnt(sDate, eDate, key, admin, deptid, itemNo, sLength, eLength, empType, personId, tableName);

				// 如果"当前页"大于最大页数,取最后一页
				if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {

					currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
				}

				List detailList = arServices.getArDetailPersonList(sDate, eDate, key, admin, deptid, itemNo, sLength, eLength, currentPage, pageSize, empType, personId, tableName);

				
				request.setAttribute("key", key);
				//request.setAttribute("name", name);
				
				
				request.setAttribute("personId", personId);
				request.setAttribute("deptid", deptid);
				request.setAttribute("sDate", sDate);
				request.setAttribute("eDate", eDate);
				request.setAttribute("arItemList", arItemList);
				request.setAttribute("detailList", detailList);
				request.setAttribute("itemNo", itemNo) ;
				request.setAttribute("sLength", sLength) ;
				request.setAttribute("eLength", eLength) ;
				request.setAttribute("empType", empType) ;
				request.setAttribute("tableName", tableName) ;
				
				// paging parameter
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage + "");
				request.setAttribute("pageSize", pageSize + "");
				request.setAttribute("pageGroupsize", pageGroupSize + "");

			} catch (Exception e) {
				e.printStackTrace() ;
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get detail list by paging Exception. ", e);
			}
		

		

		return "/ar_detail_person.jsp" ;
				//"?menu_code=" + parameterObject.getString("menu_code");

		} 
}
