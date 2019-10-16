/*
 * @(#)RetrievePresentListCmd.java 1.0 2009-7-13 下午03:25:19
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.check;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.pa.dao.report.PaImportPayBankDAO;
import com.ait.pa.servlet.PaImportPayBankCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author (shihuili@ait.net.cn)
 * @Date 2015-4-10 下午14:39:17
 * @version 1.0
 * 
 */
public class RetrieveCheckCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	private static final Logger logger = Logger.getLogger(PaImportPayBankCommand.class);
	private static final FileItemFactory factory = new DiskFileItemFactory();
	private String content = null;
	private AdminBean admin = null ;
	private List<Map<String,String>> errorList = null ;
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String returnPage = null;
		content = request.getParameter("content");// 从request中得到想要查看的内容

		
		if (content != null) {
			if (content.equals("BankAccount")) {
				returnPage = this.viewCheckBank(request);
			}
			else if (content.equals("addBankAccount")) {
				returnPage = this.addCheckBank(request);
			}
			else if (content.equals("deleteBankAccount")) {
				returnPage = this.deleteCheckBank(request);
			}
			else if (content.equals("CheckAccount")) {
				returnPage = this.viewCheckAccount(request);
			}
			else if (content.equals("addCheckAccount")) {
				returnPage = this.addCheckAccount(request);
			}
			else if (content.equals("updateCheckAccount")) {
				returnPage = this.updateCheckAccount(request);
			}
			else if (content.equals("deleteCheckAccount")) {
				returnPage = this.deleteCheckAccount(request);
			}
			else if (content.equals("updateSaveCheckAccount")) {
				returnPage = this.updateSaveCheckAccount(request);
			}
			else if (content.equals("searchCheckAccount")) {
				returnPage = this.searchCheckAccount(request);
			}
			else if (content.equals("searchCheckBank")) {
				String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
				request.setAttribute("cpnyId", cpnyId);
				returnPage = "/ga_add_checkAccount.jsp?menu_code="+request.getParameter("menu_code");
			}
		} else {
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	private String viewCheckBank(HttpServletRequest request) {
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		try {
			GaServices services = new GaServices();
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			//parameterObject.setString("adminId", admin.getAdminID());
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.setString("cpnyId", cpnyId);
			
			// retrieve record list
			List recordList = services.retrieveBankAccountList(parameterObject, currentPage, pageSize);
			Object recordCount = services.retrieveBankAccountListCnt(parameterObject);

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));

			// search parameter
			request.setAttribute("personId", parameterObject.getString("personId"));
			request.setAttribute("empId", parameterObject.getString("empId"));
			request.setAttribute("presentId", parameterObject.getString("presentId"));
			request.setAttribute("dataType", parameterObject.getString("dataType"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("cpnyId", cpnyId);
			
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve CheckBank list by paging Exception. ", e);
		}

		return "/ga_view_check.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

	private String viewCheckAccount(HttpServletRequest request) {
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		try {
			GaServices services = new GaServices();
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			//parameterObject.setString("adminId", admin.getAdminID());
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.setString("cpnyId", cpnyId);
			
			// retrieve record list
			List recordList = services.retrieveCheckAccountList(parameterObject, currentPage, pageSize);
			Object recordCount = services.retrieveCheckAccountListCnt(parameterObject);

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));

			// search parameter
			request.setAttribute("personId", parameterObject.getString("personId"));
			request.setAttribute("empId", parameterObject.getString("empId"));
			request.setAttribute("presentId", parameterObject.getString("presentId"));
			request.setAttribute("dataType", parameterObject.getString("dataType"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("cpnyId", cpnyId);
			
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve CheckAccount list by paging Exception. ", e);
		}

		return "/ga_view_checkaccount.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String addCheckBank(HttpServletRequest request) {
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.setString("cpnyId", cpnyId);
			
			//insert CheckBank
			services.insertCheckBank(parameterObject);	
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert insertCheckBank Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=CheckManager&content=BankAccount&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
	private String addCheckAccount(HttpServletRequest request) {
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.setString("cpnyId", cpnyId);
			
			//insert CheckBank
			services.insertCheckAccount(parameterObject);	
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert insertCheckAccount Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=CheckManager&content=CheckAccount&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
	private String updateSaveCheckAccount(HttpServletRequest request) {
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			
			//update CheckBank
			services.updateSaveCheckAccount(parameterObject);	
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert insertCheckAccount Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","gaControlServlet?operation=CheckManager&content=CheckAccount&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
	private String updateCheckAccount(HttpServletRequest request) {
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		Object result = null;

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.setString("cpnyId", cpnyId);
			
			// retrieve present by sequence No
			List list = services.retrieveUpCheckAccount(parameterObject);

			if(list.size() > 0)
				result = list.get(0);

			request.setAttribute("result", result);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve data for update checkAccount Exception. ", e);
		}

		return "/ga_modify_checkAccount.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String deleteCheckBank(HttpServletRequest request) {
		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		List paramList = new ArrayList();
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setInt("ACTIVITY", 0);
			parameterObject.setString("adminId", admin.getAdminID());
			
			services.updateCheckBank(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Delete CheckBank Exception. ", e);
		}

		// 删除成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","gaControlServlet?operation=CheckManager&content=BankAccount&menu_code=" + parameterObject.getString("menu_code")
								   + "&personId=" + parameterObject.getString("personId")
								   + "&empId=" + parameterObject.getString("empId")
								   + "&cpnyId=" + parameterObject.getString("cpnyId")
								   + "&startDate=" + parameterObject.getString("startDate")
								   + "&endDate=" + parameterObject.getString("endDate"));

		return "/inc/alertMessage.jsp";
	}
	
	private String deleteCheckAccount(HttpServletRequest request) {
		MessageSource messageSource ;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			services.deleteCheckAccount(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Delete CheckBank Exception. ", e);
		}

		// 删除成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.delete_successfully"));
		request.setAttribute("url","gaControlServlet?operation=CheckManager&content=CheckAccount&menu_code=" + parameterObject.getString("menu_code")
								   + "&personId=" + parameterObject.getString("personId")
								   + "&empId=" + parameterObject.getString("empId")
								   + "&cpnyId=" + parameterObject.getString("cpnyId")
								   + "&startDate=" + parameterObject.getString("startDate")
								   + "&endDate=" + parameterObject.getString("endDate"));

		return "/inc/alertMessage.jsp";
	}
	
	private String searchCheckAccount(HttpServletRequest request) {
		SimpleMap parameterObject;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		try {
			GaServices services = new GaServices();
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			//新添加   状态  12-09  
			String cheType = StringUtil.checkNull(request
					.getParameter("cheType"), "5");		
			
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			//parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			parameterObject.setString("cheType", cheType);
			
			// retrieve record list
			List recordList = services.retrieveCheckAccountList(parameterObject, currentPage, pageSize);
			Object recordCount = services.retrieveCheckAccountListCnt(parameterObject);

			request.setAttribute("recordList", recordList);
			request.setAttribute("cpnyId", cpnyId);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));

			// search parameter
			request.setAttribute("CHECKBANK_TYPE", parameterObject.getString("CHECKBANK_TYPE"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			//新添加
			request.setAttribute("cheType", parameterObject.getString("cheType"));

			request.setAttribute("cpnyId", cpnyId);
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve CheckAccount list by paging Exception. ", e);
		}

		return "/ga_view_checkaccount.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
}

