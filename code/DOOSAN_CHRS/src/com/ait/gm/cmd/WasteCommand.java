package com.ait.gm.cmd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.business.WasteServices;
import com.ait.i18n.MessageSource;
import com.ait.reports.servlet.CrystalCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.FormUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.crystaldecisions.sdk.occa.report.data.Fields;

public class WasteCommand implements Command {
	private WasteServices wasteServices;

	private SimpleMap parameterObject;

	private String content = null;

	public WasteCommand() {
		wasteServices = new WasteServices();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("wasteView")) {
				returnPage = this.wasteView(request);
			}else if (content.equals("wasteView1")) {
				returnPage = this.wasteView1(request);
			}else if (content.equals("addWasteView")) {
				returnPage = this.addWasteView(request);
			}else if (content.equals("addWasteView1")) {
				returnPage = this.addWasteView1(request);
			}else if (content.equals("addWaste")) {
				returnPage = this.addWaste(request);
			}else if (content.equals("addWaste1")) {
				returnPage = this.addWaste1(request);
			}else if (content.equals("updateWasteView")) {
				returnPage = this.updateWasteView(request);
			}else if (content.equals("updateWasteView1")) {
				returnPage = this.updateWasteView1(request);
			}else if (content.equals("updateWaste")) {
				returnPage = this.updateWaste(request);
			} else if (content.equals("updateWaste1")) {
				returnPage = this.updateWaste1(request);
			}else if (content.equals("deleteWaste")) {
				returnPage = this.deleteWaste(request);
			}else if (content.equals("deleteWaste1")) {
				returnPage = this.deleteWaste1(request);
			}else if (content.equals("monthClearingSearch")) {
				returnPage = this.monthClearingSearch(request);
			}else if (content.equals("monthClearingSearch1")) {
				returnPage = this.monthClearingSearch1(request);
			}else if (content.equals("BasicInformation1")) {
				returnPage = this.BasicInformationView1(request);
			}else if (content.equals("BasicInformation")) {
				returnPage = this.BasicInformationView(request);
			}else if (content.equals("addWasteBasicInformationView")) {
				returnPage = this.addWasteBasicInformationView(request);
			}else if (content.equals("addWasteBasicInformationView1")) {
				returnPage = this.addWasteBasicInformationView1(request);
			}else if (content.equals("addWasteBasicInformation")) {
				returnPage = this.addWasteBasicInformation(request);
			}else if (content.equals("addWasteBasicInformation1")) {
				returnPage = this.addWasteBasicInformation1(request);
			}else if (content.equals("updateWasteBasicInformationView")) {
				returnPage = this.updateWasteBasicInformationView(request);
			}else if (content.equals("updateWasteBasicInformationView1")) {
				returnPage = this.updateWasteBasicInformationView1(request);
			}else if (content.equals("updateWasteBasicInformation")) {
				returnPage = this.updateWasteBasicInformation(request);
			}else if (content.equals("updateWasteBasicInformation1")) {
				returnPage = this.updateWasteBasicInformation1(request);
			}else if(content.equals("deleteWasteBasicInformation")){
				returnPage = this.deleteWasteBasicInformation(request);
			}else if(content.equals("deleteWasteBasicInformation1")){
				returnPage = this.deleteWasteBasicInformation1(request);
			}else{
				returnPage = "/error.jsp";
			}
			
		} else {
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	public void putToolbarInfo(HttpServletRequest request)
			throws GlRuntimeException {

		try {
			SimpleMap map = new SimpleMap();

			// get paramter from request object
			List toolMenuList = null;
			List menuentList = null;
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();
			String menu_code = request.getParameter("menu_code");
			String operation = request.getParameter("operation");
			AdminBean admin = (AdminBean) (request.getSession()
					.getAttribute("admin"));
			String rodeLevel = admin.getScreenGrantNo() != null ? admin
					.getScreenGrantNo() : "";

			// get process popedom list
			toolMenuList = menubiz.toolMenuList(menu_code, rodeLevel);
			menuentList = menubiz.thirdmenulist(menu_code, rodeLevel);

			int selectMenu = 0;
			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < toolMenuList.size(); i++) {

				toolmenu = (ToolMenu) toolMenuList.get(i);

				if (toolmenu.getSelect() == 1) {

					selectMenu = 1;
				}
				if (toolmenu.getInsertr() == 1) {

					insertMenu = 1;
				}
				if (toolmenu.getUpdate() == 1) {

					updateMenu = 1;
				}
				if (toolmenu.getDelect() == 1) {

					deleteMenu = 1;
				}
			}
			// save insert,update,delete popedom
			map.setInt("selectMenu", selectMenu);
			map.setInt("insertMenu", insertMenu);
			map.setInt("updateMenu", updateMenu);
			map.setInt("deleteMenu", deleteMenu);
			// save MenuEnt object list
			map.set("menuentList", menuentList);
			// save menu code
			map.setString("menu_code", menu_code);
			map.setString("operation", operation);

			request.setAttribute("toolbarInfo", map);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"ArAbstractCommand put toolbar information to request Exception.",
					e);
		}
	}

	// 废品销售查询页面
	private String wasteView(HttpServletRequest request) {

		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String Company_Customers = request.getParameter("Company_Customers");
		String wasteType = request.getParameter("wasteType");
		String NO = request.getParameter("NO");
		String StartTime = request.getParameter("StartTime");
		String EndTime = request.getParameter("EndTime");

		
		String flag = StringUtil.checkNull(request.getParameter("flag"),"1");
		
		
		String addViewJumpWasteViewFlag = request.getParameter("addViewJumpWasteViewFlag");
		String seq = request.getParameter("seq");
		
		request.setAttribute("addViewJumpWasteViewFlag", addViewJumpWasteViewFlag);
		request.setAttribute("seq", seq);
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);			
			// retrieve attendance record list

			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", admin.getCpnyId());
			int allWasteInformationCount = NumberUtil.parseInt(wasteServices.allWasteInformationCount(parameterObject));
			
			List allWasteInformation = wasteServices.wasteView(parameterObject, currentPage, pageSize);
			
			List wasteTypeList = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteType);
			request.setAttribute("NO", NO);
			request.setAttribute("wasteTypeList", wasteTypeList);
			request.setAttribute("Company_Customers", Company_Customers);
			request.setAttribute("allWasteInformation", allWasteInformation);
			request.setAttribute("StartTime", StartTime);
			request.setAttribute("EndTime", EndTime);
			request.setAttribute("recordCount", NumberUtil
					.parseInt(allWasteInformationCount));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		if(flag.equals("1")){
			return "/gm_waste_View.jsp?menu_code=" + parameterObject.getString("menu_code");
		}
		if(flag.equals("2")){
			List allWasteInformationForReport = wasteServices.wasteView(parameterObject);
			request.setAttribute("allWasteInformationForReport", allWasteInformationForReport);
			return "/reports/gm_report/gm_waste_report.jsp?StartTime="+StartTime+"&EndTime="+EndTime;
		}
		return flag;
	}
	
	// 废品清理查询页面
	private String wasteView1(HttpServletRequest request) {

		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String Company_Customers = request.getParameter("Company_Customers");
		String wasteType = request.getParameter("wasteType");
		String NO = request.getParameter("NO");
		String StartTime = request.getParameter("StartTime");
		String EndTime = request.getParameter("EndTime");

		
		String flag = StringUtil.checkNull(request.getParameter("flag"),"1");
		
		
		String addViewJumpWasteViewFlag = request.getParameter("addViewJumpWasteViewFlag");
		String seq = request.getParameter("seq");
		
		request.setAttribute("addViewJumpWasteViewFlag", addViewJumpWasteViewFlag);
		request.setAttribute("seq", seq);
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);			
			// retrieve attendance record list

			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", admin.getCpnyId());
			int allWasteInformationCount = NumberUtil.parseInt(wasteServices.allWasteInformationCount(parameterObject));
			
			List allWasteInformation = wasteServices.wasteView(parameterObject, currentPage, pageSize);
			
			List wasteTypeList = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteType);
			request.setAttribute("NO", NO);
			request.setAttribute("wasteTypeList", wasteTypeList);
			request.setAttribute("Company_Customers", Company_Customers);
			request.setAttribute("allWasteInformation", allWasteInformation);
			request.setAttribute("StartTime", StartTime);
			request.setAttribute("EndTime", EndTime);
			request.setAttribute("recordCount", NumberUtil
					.parseInt(allWasteInformationCount));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		if(flag.equals("1")){
			return "/gm_waste_View1.jsp?menu_code=" + parameterObject.getString("menu_code");
		}
		if(flag.equals("2")){
			List allWasteInformationForReport = wasteServices.wasteView(parameterObject);
			request.setAttribute("allWasteInformationForReport", allWasteInformationForReport);
			return "/reports/gm_report/gm_waste_report.jsp?StartTime="+StartTime+"&EndTime="+EndTime;
		}
		return flag;
	}

	// 废品销售添加页面
	private String addWasteView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", admin.getCpnyId());
			//废品类别
			List wasteType = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteType);
			
			List allCompanyCustomers = wasteServices.allCompanyCustomers(parameterObject);
			request.setAttribute("allCompanyCustomers", allCompanyCustomers);
			
			List revenue_approach = wasteServices.getRevenue_approach(parameterObject);
			request.setAttribute("revenue_approach", revenue_approach);
			
			request.setAttribute("recordCount", NumberUtil
					.parseInt(eatLookCount));
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/gm_addwaste_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	// 废品清理添加页面
	private String addWasteView1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list
			Object eatLookCount = 0;
			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List wasteType = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteType);
			
			List allCompanyCustomers = wasteServices.allCompanyCustomers(parameterObject);
			request.setAttribute("allCompanyCustomers", allCompanyCustomers);
			
			List revenue_approach = wasteServices.getRevenue_approach(parameterObject);
			request.setAttribute("revenue_approach", revenue_approach);
			
			request.setAttribute("recordCount", NumberUtil
					.parseInt(eatLookCount));
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/gm_addwaste_View1.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	// 废品销售添加页面 保存
	private String addWaste(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String wasteType = request.getParameter("wasteType");
		String Units = request.getParameter("Units");
		
		
		String wasteUnitPrice = request.getParameter("wasteUnitPrice");
		String wasteNumber = request.getParameter("wasteNumber");
		String total = request.getParameter("total");
		String Company_Customers = request.getParameter("Company_Customers");
		String remarks = request.getParameter("remarks");
		
		String revenue_approach = request.getParameter("revenue_approach");
		String OUT_STAFF = request.getParameter("OUT_STAFF");
		String CAR_NO = request.getParameter("CAR_NO");
		int seq = 0;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			String NO=FormUtil.getApplyDocumentid("waste_no", "gm_waste", "FP");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(Calendar.getInstance().getTime());
			String subDate = date.substring(2, 8);
		    parameterObject.set("NUMBERS", NO);		
			parameterObject.set("wasteType", wasteType);
			parameterObject.set("Units", Units);
			parameterObject.set("wasteUnitPrice", wasteUnitPrice);
			parameterObject.set("wasteNumber", wasteNumber);
			parameterObject.set("total", total);
			parameterObject.set("Company_Customers", Company_Customers);
			parameterObject.set("remarks", remarks);
			
			parameterObject.set("revenue_approach", revenue_approach);
			parameterObject.set("OUT_STAFF", OUT_STAFF);
			parameterObject.set("CAR_NO", CAR_NO);
			parameterObject.set("cpny_id", admin.getCpnyId());
			
			// retrieve attendance record list
			Object eatLookCount = 0;

			seq = wasteServices.getSeq(parameterObject);
			
			wasteServices.addWaste(parameterObject);

			request.setAttribute("recordCount", NumberUtil
					.parseInt(eatLookCount));
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		request.setAttribute("alert", "添加成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=wasteView&addViewJumpWasteViewFlag=1&seq="+seq+"&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	// 废品清理添加页面 保存
	private String addWaste1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String wasteType = request.getParameter("wasteType");
		String Units = request.getParameter("Units");
		
		
		String wasteUnitPrice = request.getParameter("wasteUnitPrice");
		String wasteNumber = request.getParameter("wasteNumber");
		String total = request.getParameter("total");
		String Company_Customers = request.getParameter("Company_Customers");
		String remarks = request.getParameter("remarks");
		
		String revenue_approach = request.getParameter("revenue_approach");
		String OUT_STAFF = request.getParameter("OUT_STAFF");
		String CAR_NO = request.getParameter("CAR_NO");
		int seq = 0;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			String NO=FormUtil.getApplyDocumentid("waste_no", "gm_waste", "FP");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date = format.format(Calendar.getInstance().getTime());
			String subDate = date.substring(2, 8);
		    parameterObject.set("NUMBERS", NO);		
			parameterObject.set("wasteType", wasteType);
			parameterObject.set("Units", Units);
			parameterObject.set("wasteUnitPrice", wasteUnitPrice);
			parameterObject.set("wasteNumber", wasteNumber);
			parameterObject.set("total", total);
			parameterObject.set("Company_Customers", Company_Customers);
			parameterObject.set("remarks", remarks);
			
			parameterObject.set("revenue_approach", revenue_approach);
			parameterObject.set("OUT_STAFF", OUT_STAFF);
			parameterObject.set("CAR_NO", CAR_NO);
			parameterObject.set("cpny_id", admin.getCpnyId());
			
			// retrieve attendance record list
			Object eatLookCount = 0;

			seq = wasteServices.getSeq(parameterObject);
			
			wasteServices.addWaste(parameterObject);

			request.setAttribute("recordCount", NumberUtil
					.parseInt(eatLookCount));
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		request.setAttribute("alert", "添加成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=wasteView1&addViewJumpWasteViewFlag=1&seq="+seq+"&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}

	// 废品销售修改页面
	private String updateWasteView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String wasteId = request.getParameter("wasteId");
		request.setAttribute("wasteId", wasteId);
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			List allUpdateWasteInformation = wasteServices
					.allUpdateWasteInformation(parameterObject);
			int allUpdateWasteInformationSize = allUpdateWasteInformation
					.size();
			
			String wasteType = "";
			String wasteTypeId = "";
			String wasteUnits = "";
			String wasteUNITPRICE = "";
			String wasteNumber = "";
			String wasteTotal = "";
			String company_customers = "";
			String company_customers_id = "";
			String wasteRemark = "";
			
			String REVENUE_APPROACH_NAME = "";
			String REVENUE_APPROACH_ID = "";
			String OUT_STAFF = "";
			String CAR_NO = "";
			String COMPANY_OTHER = "";
			
			for (int i = 0; i < allUpdateWasteInformationSize; i++) {
				map = (SimpleMap) allUpdateWasteInformation.get(i);
				wasteType = map.getString("WASTE_TYPE_NAME");
				wasteTypeId = map.getString("WASTE_TYPE");
				wasteUnits = map.getString("WASTE_UNITS");
				wasteUNITPRICE = map.getString("WASTE_UNITPRICE");
				wasteNumber = map.getString("WASTE_NUMBER");
				wasteTotal = map.getString("WASTE_TOTAL");
				company_customers = map.getString("COMPANY_CUSTOMERS");
				company_customers_id = map.getString("COMPANY_CUSTOMERS_ID");
				wasteRemark = map.getString("REMARK");
				COMPANY_OTHER = map.getString("COMPANY_OTHER");
				
				REVENUE_APPROACH_NAME = map.getString("REVENUE_APPROACH_NAME");
				REVENUE_APPROACH_ID = map.getString("REVENUE_APPROACH_ID");
				OUT_STAFF = map.getString("OUT_STAFF");
				CAR_NO = map.getString("CAR_NO");
				
				map.set("wasteType", wasteType);
				map.set("wasteTypeId", wasteTypeId);
				map.set("wasteUnits", wasteUnits);
				map.set("wasteUNITPRICE", wasteUNITPRICE);
				map.set("wasteNumber", wasteNumber);
				map.set("wasteTotal", wasteTotal);
				map.set("company_customers", company_customers);
				map.set("company_customers_id", company_customers_id);
				map.set("wasteRemark", wasteRemark);
				
				map.set("REVENUE_APPROACH_NAME", REVENUE_APPROACH_NAME);
				map.set("REVENUE_APPROACH_ID", REVENUE_APPROACH_ID);
				map.set("OUT_STAFF", OUT_STAFF);
				map.set("CAR_NO", CAR_NO);
				map.set("COMPANY_OTHER", COMPANY_OTHER);
			}

			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List wasteAllType = wasteServices.wasteType(parameterObject);

			
			request.setAttribute("allUpdateWasteInformation",
					allUpdateWasteInformation);
			request.setAttribute("wasteAllType", wasteAllType);

			List revenue_approach = wasteServices.getRevenue_approach(parameterObject);
			request.setAttribute("revenue_approach", revenue_approach);
			
			
			List wasteTypes = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteTypes);
			
			List allCompanyCustomers = wasteServices.allCompanyCustomers(parameterObject);
			request.setAttribute("allCompanyCustomers", allCompanyCustomers);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/gm_Updatewaste_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	// 废品清理修改页面
	private String updateWasteView1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String wasteId = request.getParameter("wasteId");
		request.setAttribute("wasteId", wasteId);
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			List allUpdateWasteInformation = wasteServices
					.allUpdateWasteInformation(parameterObject);
			int allUpdateWasteInformationSize = allUpdateWasteInformation
					.size();
			
			String wasteType = "";
			String wasteTypeId = "";
			String wasteUnits = "";
			String wasteUNITPRICE = "";
			String wasteNumber = "";
			String wasteTotal = "";
			String company_customers = "";
			String company_customers_id = "";
			String wasteRemark = "";
			
			String REVENUE_APPROACH_NAME = "";
			String REVENUE_APPROACH_ID = "";
			String OUT_STAFF = "";
			String CAR_NO = "";
			String COMPANY_OTHER = "";
			
			for (int i = 0; i < allUpdateWasteInformationSize; i++) {
				map = (SimpleMap) allUpdateWasteInformation.get(i);
				wasteType = map.getString("WASTE_TYPE_NAME");
				wasteTypeId = map.getString("WASTE_TYPE");
				wasteUnits = map.getString("WASTE_UNITS");
				wasteUNITPRICE = map.getString("WASTE_UNITPRICE");
				wasteNumber = map.getString("WASTE_NUMBER");
				wasteTotal = map.getString("WASTE_TOTAL");
				company_customers = map.getString("COMPANY_CUSTOMERS");
				company_customers_id = map.getString("COMPANY_CUSTOMERS_ID");
				wasteRemark = map.getString("REMARK");
				COMPANY_OTHER = map.getString("COMPANY_OTHER");
				
				REVENUE_APPROACH_NAME = map.getString("REVENUE_APPROACH_NAME");
				REVENUE_APPROACH_ID = map.getString("REVENUE_APPROACH_ID");
				OUT_STAFF = map.getString("OUT_STAFF");
				CAR_NO = map.getString("CAR_NO");
				
				map.set("wasteType", wasteType);
				map.set("wasteTypeId", wasteTypeId);
				map.set("wasteUnits", wasteUnits);
				map.set("wasteUNITPRICE", wasteUNITPRICE);
				map.set("wasteNumber", wasteNumber);
				map.set("wasteTotal", wasteTotal);
				map.set("company_customers", company_customers);
				map.set("company_customers_id", company_customers_id);
				map.set("wasteRemark", wasteRemark);
				
				map.set("REVENUE_APPROACH_NAME", REVENUE_APPROACH_NAME);
				map.set("REVENUE_APPROACH_ID", REVENUE_APPROACH_ID);
				map.set("OUT_STAFF", OUT_STAFF);
				map.set("CAR_NO", CAR_NO);
				map.set("COMPANY_OTHER", COMPANY_OTHER);
			}

			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List wasteAllType = wasteServices.wasteType(parameterObject);

			
			request.setAttribute("allUpdateWasteInformation",
					allUpdateWasteInformation);
			request.setAttribute("wasteAllType", wasteAllType);

			List revenue_approach = wasteServices.getRevenue_approach(parameterObject);
			request.setAttribute("revenue_approach", revenue_approach);
			
			
			List wasteTypes = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteTypes);
			
			List allCompanyCustomers = wasteServices.allCompanyCustomers(parameterObject);
			request.setAttribute("allCompanyCustomers", allCompanyCustomers);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/gm_Updatewaste_View1.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}


	// 废品销售修改
	private String updateWaste(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteId = request.getParameter("wasteId");
		
		String wasteType = request.getParameter("wasteType");
		String Units = request.getParameter("Units");
		String unitPrice = request.getParameter("wasteUnitPrice");
		String wasteNumber = request.getParameter("wasteNumber");
		String total = request.getParameter("total");
		String Company_Customers = request.getParameter("Company_Customers");
		String remarks = request.getParameter("remarks");
		
		String revenue_approach = request.getParameter("revenue_approach");
		String OUT_STAFF = request.getParameter("OUT_STAFF");
		String CAR_NO = request.getParameter("CAR_NO");
		String COMPANY_OTHER = request.getParameter("COMPANY_OTHER");
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);

			parameterObject.set("wasteId", wasteId);
			parameterObject.set("wasteType", wasteType);
			parameterObject.set("Units", Units);
			parameterObject.set("unitPrice", unitPrice);
			parameterObject.set("wasteNumber", wasteNumber);
			parameterObject.set("total", total);
			parameterObject.set("Company_Customers", Company_Customers);
			parameterObject.set("remarks", remarks);
			
			parameterObject.set("revenue_approach", revenue_approach);
			parameterObject.set("OUT_STAFF", OUT_STAFF);
			parameterObject.set("COMPANY_OTHER", COMPANY_OTHER);
			parameterObject.set("CAR_NO", CAR_NO);
			
			// retrieve attendance record list

			wasteServices.updateWaste(parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateWaste error ", e);
		}
		request.setAttribute("alert", "修改成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=wasteView&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	// 废品清理修改
	private String updateWaste1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteId = request.getParameter("wasteId");
		
		String wasteType = request.getParameter("wasteType");
		String Units = request.getParameter("Units");
		String unitPrice = request.getParameter("wasteUnitPrice");
		String wasteNumber = request.getParameter("wasteNumber");
		String total = request.getParameter("total");
		String Company_Customers = request.getParameter("Company_Customers");
		String remarks = request.getParameter("remarks");
		
		String revenue_approach = request.getParameter("revenue_approach");
		String OUT_STAFF = request.getParameter("OUT_STAFF");
		String CAR_NO = request.getParameter("CAR_NO");
		String COMPANY_OTHER = request.getParameter("COMPANY_OTHER");
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);

			parameterObject.set("wasteId", wasteId);
			parameterObject.set("wasteType", wasteType);
			parameterObject.set("Units", Units);
			parameterObject.set("unitPrice", unitPrice);
			parameterObject.set("wasteNumber", wasteNumber);
			parameterObject.set("total", total);
			parameterObject.set("Company_Customers", Company_Customers);
			parameterObject.set("remarks", remarks);
			
			parameterObject.set("revenue_approach", revenue_approach);
			parameterObject.set("OUT_STAFF", OUT_STAFF);
			parameterObject.set("CAR_NO", CAR_NO);
			parameterObject.set("COMPANY_OTHER", COMPANY_OTHER);
			
			// retrieve attendance record list

			wasteServices.updateWaste(parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateWaste error ", e);
		}
		request.setAttribute("alert", "修改成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=wasteView1&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	/**
	 * 删除销售废品信息
	 * @param request
	 * @return
	 */
	private String deleteWaste(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteId = request.getParameter("wasteId");
	

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			
			
			wasteServices.deleteWaste(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		request.setAttribute("alert", "删除成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=BasicInformation&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	/**
	 * 删除清理废品信息
	 * @param request
	 * @return
	 */
	private String deleteWaste1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteId = request.getParameter("wasteId");
	

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			
			
			wasteServices.deleteWaste(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		request.setAttribute("alert", "删除成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=BasicInformation1&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
//	 月别废品清理结算查询
	private String monthClearingSearch(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap map = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar  today = Calendar.getInstance();	
		String StartTime = request.getParameter("StartTime");
		String EndTime = request.getParameter("EndTime");
		
//		if(request.getParameter("StartTime")!=null&& request.getParameter("EndTime")!=null){
//			StartTime = request.getParameter("StartTime");
//			EndTime = request.getParameter("EndTime");
//		}else{
//			StartTime =sdf.format(today.getTime());
//			EndTime = sdf.format(today.getTime());
//		}

		String flag = StringUtil.checkNull(request.getParameter("flag"),"1");
		try {
			// bind parameter
			UserConfiguration config = UserConfiguration
			.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			parameterObject = ObjectBindUtil.getData(request);
			String wasteType = parameterObject.getString("wasteType");
			parameterObject.set("supervisor", adminId);
			parameterObject.set("StartTime", StartTime);
			parameterObject.set("EndTime", EndTime);
			
			// retrieve attendance record list
			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List allInformation = null;
			if(flag.equals("1")){
				allInformation = wasteServices.allInformation(parameterObject,currentPage, pageSize);
			}
			if(flag.equals("2")){
				allInformation = wasteServices.allInformation1(parameterObject);
			}
			request.setAttribute("allInformation", allInformation);
			double total= 0;
			for(int i=0 ; i<allInformation.size() ; i++){
				map = (SimpleMap) allInformation.get(i);
				String temp = map.getString("WASTE_TOTAL");
				total = total+NumberUtil.parseDouble(temp);
			}

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			
			request.setAttribute("StartTime", StartTime);
			request.setAttribute("EndTime", EndTime);
			request.setAttribute("wasteType", request.getParameter("wasteType"));
			request.setAttribute("total", total+"");
			List wasteAllType = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteTypeList", wasteAllType);
			request.setAttribute("wasteType", wasteType);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("monthClearingSearch error ", e);
		}
		request.setAttribute("StartTime", StartTime);
		request.setAttribute("EndTime", EndTime);
		if(flag.equals("1")){
			return "/gm_monthClearingSearch_View.jsp?menu_code="
			+ parameterObject.getString("menu_code");
		}
		if(flag.equals("2")){
			return "/reports/gm_report/gm_waste_month_report.jsp?StartTime="+StartTime+"&EndTime="+EndTime;
		}
		return flag;
	}
	
//	 月别废品销售结算查询
	private String monthClearingSearch1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap map = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar  today = Calendar.getInstance();	
		String StartTime = request.getParameter("StartTime");
		String EndTime = request.getParameter("EndTime");
//		if(request.getParameter("StartTime")!=null&& request.getParameter("EndTime")!=null){
//			StartTime = request.getParameter("StartTime");
//			EndTime = request.getParameter("EndTime");
//		}else{
//			StartTime =sdf.format(today.getTime());
//			EndTime = sdf.format(today.getTime());
//		}
		

		String flag = StringUtil.checkNull(request.getParameter("flag"),"1");
		try {
			UserConfiguration config = UserConfiguration
			.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			String wasteType = parameterObject.getString("wasteType");
			parameterObject.set("supervisor", adminId);
			parameterObject.set("StartTime", StartTime);
			parameterObject.set("EndTime", EndTime);
			
			// retrieve attendance record list
			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List allInformation = null;
			if(flag.equals("1")){
				allInformation = wasteServices.allInformation(parameterObject,currentPage, pageSize);
			}
			if(flag.equals("2")){
				allInformation = wasteServices.allInformation1(parameterObject);
			}
			request.setAttribute("allInformation", allInformation);
			double total= 0;
			for(int i=0 ; i<allInformation.size() ; i++){
				map = (SimpleMap) allInformation.get(i);
				String temp = map.getString("WASTE_TOTAL");
				total = total+NumberUtil.parseDouble(temp);
			}
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			
			request.setAttribute("StartTime", StartTime);
			request.setAttribute("EndTime", EndTime);
			request.setAttribute("wasteType", parameterObject.get("wasteType"));
			request.setAttribute("total", total+"");
			List wasteAllType = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteTypeList", wasteAllType);
			request.setAttribute("wasteType", wasteType);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("monthClearingSearch error ", e);
		}
		request.setAttribute("StartTime", StartTime);
		request.setAttribute("EndTime", EndTime);
		if(flag.equals("1")){
			return "/gm_monthClearingSearch_View1.jsp?menu_code="
			+ parameterObject.getString("menu_code");
		}
		if(flag.equals("2")){
			return "/reports/gm_report/gm_waste_month_report.jsp?StartTime="+StartTime+"&EndTime="+EndTime;
		}
		return flag;
	}
	
//	 废品销售基本信息查询界面
	private String BasicInformationView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		SimpleMap map = null;

		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
		String today=timeFormatter.format(d);
		String today1=timeFormatter1.format(d);
		
//		String StartTime = request.getParameter("StartTime")!=null?request.getParameter("StartTime"):today;
//		String EndTime = request.getParameter("EndTime")!=null?request.getParameter("EndTime"):today;
		
		String StartTime = request.getParameter("StartTime");
		String EndTime = request.getParameter("EndTime");

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("StartTime", StartTime);
			parameterObject.set("EndTime", EndTime);
			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", cpny_id);
			// retrieve attendance record list
			
			int basicInformationInt = wasteServices.getBasicInformationInt(parameterObject);
			List basicInformation = wasteServices.getBasicInformation(parameterObject, currentPage, pageSize);
			
			request.setAttribute("basicInformation", basicInformation);
			
			List wasteType = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteType);
			
			request.setAttribute("waste_nameID", request.getParameter("waste_name"));
			request.setAttribute("StartTime", StartTime);
			request.setAttribute("EndTime", EndTime);
			
			request.setAttribute("recordCount", basicInformationInt);

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			
			request.setAttribute("content1", this.content);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/gm_BasicInformation_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
//	 废品清理基本信息查询界面
	private String BasicInformationView1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		SimpleMap map = null;

		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
		String today=timeFormatter.format(d);
		String today1=timeFormatter1.format(d);
		
//		String StartTime = request.getParameter("StartTime")!=null?request.getParameter("StartTime"):today;
//		String EndTime = request.getParameter("EndTime")!=null?request.getParameter("EndTime"):today;

		String StartTime = request.getParameter("StartTime");
		String EndTime = request.getParameter("EndTime");
		String waste_nameID =  request.getParameter("waste_name");
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("StartTime", StartTime);
			parameterObject.set("EndTime", EndTime);
			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", cpny_id);
			// retrieve attendance record list
			
			int basicInformationInt = wasteServices.getBasicInformationInt(parameterObject);
			List basicInformation = wasteServices.getBasicInformation(parameterObject, currentPage, pageSize);
			
			request.setAttribute("basicInformation", basicInformation);
			
			List wasteType = wasteServices.wasteType(parameterObject);
			request.setAttribute("wasteType", wasteType);
			
			request.setAttribute("waste_nameID",waste_nameID);
			request.setAttribute("StartTime", StartTime);
			request.setAttribute("EndTime", EndTime);
			
			request.setAttribute("recordCount", basicInformationInt);

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			
			request.setAttribute("content1", this.content);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		return "/gm_BasicInformation_View1.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
//	 废品销售基本信息添加页面
	private String addWasteBasicInformationView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", admin.getCpnyId());
			// retrieve attendance record list

			List wasteType = wasteServices.wasteType(parameterObject);

			request.setAttribute("wasteType", wasteType);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWasteBasicInformationView error ", e);
		}
		return "/gm_addWasteBasicInformation_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
//	 废品清理基本信息添加页面
	private String addWasteBasicInformationView1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap map = null;

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", admin.getCpnyId());
			// retrieve attendance record list
			Object eatLookCount = 0;

			List wasteType = wasteServices.wasteType(parameterObject);

			request.setAttribute("wasteType", wasteType);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWasteBasicInformationView error ", e);
		}
		return "/gm_addWasteBasicInformation_View1.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
//	 废品销售基本信息添加
	private String addWasteBasicInformation(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteName = request.getParameter("wasteName");
		String CalType = request.getParameter("CalType");
		String wasteUnitPrice = request.getParameter("wasteUnitPrice");
		String WASTE_SET_APPLICABLE_DATE = request.getParameter("WASTE_SET_APPLICABLE_DATE");
		String WASTE_SET_QI_AN_ACCORDING_NO = request.getParameter("WASTE_SET_QI_AN_ACCORDING_NO");
		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("wasteName", wasteName);
			parameterObject.set("CalType", CalType);
			parameterObject.set("UnitPrice", wasteUnitPrice);
			parameterObject.set("WASTE_SET_APPLICABLE_DATE", WASTE_SET_APPLICABLE_DATE);
			parameterObject.set("WASTE_SET_QI_AN_ACCORDING_NO", WASTE_SET_QI_AN_ACCORDING_NO);
			parameterObject.set("cpny_id", admin.getCpnyId());
			// retrieve attendance record list

			wasteServices.addWasteBasicInformation(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWasteBasicInformation error ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","/gmControlServlet?operation=waste&content=BasicInformation&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
//	 废品清理基本信息添加
	private String addWasteBasicInformation1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteName = request.getParameter("wasteName");
		String CalType = request.getParameter("CalType");
		String wasteUnitPrice = request.getParameter("wasteUnitPrice");
		String WASTE_SET_APPLICABLE_DATE = request.getParameter("WASTE_SET_APPLICABLE_DATE");
		String WASTE_SET_QI_AN_ACCORDING_NO = request.getParameter("WASTE_SET_QI_AN_ACCORDING_NO");
		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("wasteName", wasteName);
			parameterObject.set("CalType", CalType);
			parameterObject.set("UnitPrice", wasteUnitPrice);
			parameterObject.set("WASTE_SET_APPLICABLE_DATE", WASTE_SET_APPLICABLE_DATE);
			parameterObject.set("WASTE_SET_QI_AN_ACCORDING_NO", WASTE_SET_QI_AN_ACCORDING_NO);
			parameterObject.set("cpny_id", admin.getCpnyId());
			// retrieve attendance record list

			wasteServices.addWasteBasicInformation(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWasteBasicInformation error ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","/gmControlServlet?operation=waste&content=BasicInformation1&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
//	 废品销售基本信息修改页面
	private String updateWasteBasicInformationView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String wasteId = request.getParameter("wasteId");
		
		request.setAttribute("wasteId", wasteId);
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			List wasteTypeSingle = wasteServices.wasteTypeSingle(parameterObject);
			
			String WASTE_SET_NAME = "";
			String WASTE_SET_UNITS = "";
			String WASTE_SET_UNITPRICE = "";
			String WASTE_SET_APPLICABLE_DATE = "";
			String WASTE_SET_QI_AN_ACCORDING_NO = "";
			for(int i=0 ; i<wasteTypeSingle.size() ; i++){
				map = (SimpleMap) wasteTypeSingle.get(i);
				WASTE_SET_NAME = map.getString("WASTE_SET_NAME_ID");
				WASTE_SET_UNITS = map.getString("WASTE_SET_UNITS");
				WASTE_SET_UNITPRICE = map.getString("WASTE_SET_UNITPRICE");
				WASTE_SET_APPLICABLE_DATE = map.getString("WASTE_SET_APPLICABLE_DATE");
				WASTE_SET_QI_AN_ACCORDING_NO = map.getString("WASTE_SET_QI_AN_ACCORDING_NO");
			}
			request.setAttribute("WASTE_SET_NAME", WASTE_SET_NAME);
			request.setAttribute("WASTE_SET_UNITS", WASTE_SET_UNITS);
			request.setAttribute("WASTE_SET_UNITPRICE", WASTE_SET_UNITPRICE);
			request.setAttribute("WASTE_SET_APPLICABLE_DATE", WASTE_SET_APPLICABLE_DATE);
			request.setAttribute("WASTE_SET_QI_AN_ACCORDING_NO", WASTE_SET_QI_AN_ACCORDING_NO);
			parameterObject.set("type", "Waste_Type");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List wasteType = wasteServices.wasteType(parameterObject);
			
			request.setAttribute("wasteType", wasteType);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWasteBasicInformationView error ", e);
		}
		return "/gm_updateWasteBasicInformation_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
//	 废品清理基本信息修改页面
	private String updateWasteBasicInformationView1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String wasteId = request.getParameter("wasteId");
		
		request.setAttribute("wasteId", wasteId);
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			List wasteTypeSingle = wasteServices.wasteTypeSingle(parameterObject);
			
			String WASTE_SET_NAME = "";
			String WASTE_SET_UNITS = "";
			String WASTE_SET_UNITPRICE = "";
			String WASTE_SET_APPLICABLE_DATE = "";
			String WASTE_SET_QI_AN_ACCORDING_NO = "";
			for(int i=0 ; i<wasteTypeSingle.size() ; i++){
				map = (SimpleMap) wasteTypeSingle.get(i);
				WASTE_SET_NAME = map.getString("WASTE_SET_NAME_ID");
				WASTE_SET_UNITS = map.getString("WASTE_SET_UNITS");
				WASTE_SET_UNITPRICE = map.getString("WASTE_SET_UNITPRICE");
				WASTE_SET_APPLICABLE_DATE = map.getString("WASTE_SET_APPLICABLE_DATE");
				WASTE_SET_QI_AN_ACCORDING_NO = map.getString("WASTE_SET_QI_AN_ACCORDING_NO");
			}
			request.setAttribute("WASTE_SET_NAME", WASTE_SET_NAME);
			request.setAttribute("WASTE_SET_UNITS", WASTE_SET_UNITS);
			request.setAttribute("WASTE_SET_UNITPRICE", WASTE_SET_UNITPRICE);
			request.setAttribute("WASTE_SET_APPLICABLE_DATE", WASTE_SET_APPLICABLE_DATE);
			request.setAttribute("WASTE_SET_QI_AN_ACCORDING_NO", WASTE_SET_QI_AN_ACCORDING_NO);
			parameterObject.set("type", "Clear_Wstes");
			parameterObject.set("cpny_id", admin.getCpnyId());
			List wasteType = wasteServices.wasteType(parameterObject);
			
			request.setAttribute("wasteType", wasteType);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addWasteBasicInformationView error ", e);
		}
		return "/gm_updateWasteBasicInformation_View1.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	/**
	 * 销售废品删除
	 * @param request
	 * @return
	 */
	private String deleteWasteBasicInformation(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteId = request.getParameter("wasteId");
	

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			
			
			wasteServices.deleteWasteBasicInformation(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		request.setAttribute("alert", "删除成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=wasteView&flag=1&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
	/**
	 * 清理废品删除
	 * @param request
	 * @return
	 */
	private String deleteWasteBasicInformation1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteId = request.getParameter("wasteId");
	

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			
			
			wasteServices.deleteWasteBasicInformation(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		request.setAttribute("alert", "删除成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=wasteView1&flag=1&menu_code="+ parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
	
//	 废品销售基本信息修改
	private String updateWasteBasicInformation(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteName = request.getParameter("wasteName");
		String CalType = request.getParameter("CalType");
		String wasteUnitPrice = request.getParameter("wasteUnitPrice");
		String WASTE_SET_APPLICABLE_DATE = request.getParameter("WASTE_SET_APPLICABLE_DATE");
		String WASTE_SET_QI_AN_ACCORDING_NO = request.getParameter("WASTE_SET_QI_AN_ACCORDING_NO");
		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String wasteId = request.getParameter("wasteId");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("wasteName", wasteName);
			parameterObject.set("CalType", CalType);
			parameterObject.set("UnitPrice", wasteUnitPrice);
			parameterObject.set("WASTE_SET_APPLICABLE_DATE", WASTE_SET_APPLICABLE_DATE);
			parameterObject.set("WASTE_SET_QI_AN_ACCORDING_NO", WASTE_SET_QI_AN_ACCORDING_NO);
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			wasteServices.updateWasteBasicInformation(parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateWasteBasicInformation error ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "修改成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=BasicInformation&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
//	 废品清理基本信息修改
	private String updateWasteBasicInformation1(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String wasteName = request.getParameter("wasteName");
		String CalType = request.getParameter("CalType");
		String wasteUnitPrice = request.getParameter("wasteUnitPrice");
		String WASTE_SET_APPLICABLE_DATE = request.getParameter("WASTE_SET_APPLICABLE_DATE");
		String WASTE_SET_QI_AN_ACCORDING_NO = request.getParameter("WASTE_SET_QI_AN_ACCORDING_NO");
		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String wasteId = request.getParameter("wasteId");
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			parameterObject.set("wasteName", wasteName);
			parameterObject.set("CalType", CalType);
			parameterObject.set("UnitPrice", wasteUnitPrice);
			parameterObject.set("WASTE_SET_APPLICABLE_DATE", WASTE_SET_APPLICABLE_DATE);
			parameterObject.set("WASTE_SET_QI_AN_ACCORDING_NO", WASTE_SET_QI_AN_ACCORDING_NO);
			
			parameterObject.set("wasteId", wasteId);
			// retrieve attendance record list

			wasteServices.updateWasteBasicInformation(parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateWasteBasicInformation error ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "修改成功");
		request.setAttribute("url","/gmControlServlet?operation=waste&content=BasicInformation1&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}