package com.ait.ga.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.EatingCardServices;
import com.ait.ga.business.GaServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

public class GaViewCommand implements Command {
	private GaServices gaServices;

	private SimpleMap parameterObject;

	private String content = null;
	
	private EatingCardServices eatingCardes;

	public GaViewCommand() {
		gaServices = new GaServices();
		
		eatingCardes= new EatingCardServices();
	}
	
	
	
	AdminBean admin = null;

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		content = request.getParameter("content");// 从request中得到想要查看的内容
		 admin = (AdminBean)request.getSession().getAttribute("admin");
		
			if (content.equals("visitlist") && content != null) {
				returnPage = viewVisit(request);
			} else if (content.equals("supervisorInfo") && content != null) {
				returnPage = viewVisit(request);
			}else if (content.equals("cardapplicationview") && content != null) {
				returnPage = cardapplicationview(request);
			}else if (content.equals("temporarycardapplicationview") && content != null) {
				returnPage = tempcardapplicationview(request);
			}else if (content.equals("checkapplicationview") && content != null) {//支票担当确认
				returnPage = checkapplicationview(request);
			}else if(content.equals("oingConfirm") && content != null){
				this.oingConfirm(request);
				returnPage = cardapplicationview(request);
			}else if(content.equals("oingTempCardConfirm") && content != null){
				this.oingTempCardConfirm(request);
				returnPage = tempcardapplicationview(request);
			}else if(content.equals("oingCheckConfirm") && content != null){//支票确认（确认、作废）
				this.oingCheckConfirm(request);
				returnPage = checkapplicationview(request);
			}else if(content.equals("exceltemporarycardapplicationview") && content != null){
				returnPage = this.tempCardExce(request, admin);
			}else if(content.equals("excelcheckapplicationview") && content != null){//支票担当确认 Excel导出
				returnPage = this.checkExce(request, admin);
			}else if(content.equals("excelCheckAccountview") && content != null){//基础：支票号 Excel导出
				returnPage = this.checkAccountExce(request, admin);
			}else if(content.equals("balanceCheckAccountview") && content != null){//基础：支票号 结余 盘点
				returnPage = this.checkAccountBalance(request, admin);
			}else if(content.equals("cardProvideView") && content != null){
				returnPage =this.cardProvideView(request);
			}else if(content.equals("tempCardProvideView") && content != null){
				returnPage =this.tempCardProvideView(request);
			}else if(content.equals("cardProvideAdd") && content != null){
				returnPage =this.cardProvideAdd(request);
			}else if(content.equals("cardProvideUpdate") && content != null){
				returnPage =this.cardProvideUpdate(request);
			}else if(content.equals("tempCardSave") && content != null){
				returnPage =this.tempCardSave(request);
			}else if(content.equals("cardProvideDelete") && content != null){
				returnPage =this.cardProvideDelete(request);
			}else{
			Logger.getLogger(getClass()).error("get content parameter fail!");
			returnPage = "/error.jsp";
		   }
		Logger.getLogger(getClass()).debug("return Page : " + returnPage);
		return returnPage;
	}

	public void putToolbarInfo(HttpServletRequest request) throws GlRuntimeException {

		try {
			SimpleMap map = new SimpleMap();

			// get paramter from request object         
			List toolMenuList = null;
			List menuentList = null;
			ToolMenu toolmenu = new ToolMenu();
			MenuBiz menubiz = new MenuBiz();
			String menu_code = request.getParameter("menu_code");
			String operation = request.getParameter("operation");
			AdminBean admin = (AdminBean) (request.getSession().getAttribute("admin"));
			String rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";

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
			throw new GlRuntimeException("ArAbstractCommand put toolbar information to request Exception.", e);
		}
	}

	
	private String viewVisit(HttpServletRequest request) {
		this.putToolbarInfo(request);

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			String in_time;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			if (request.getParameter("in_date") != null && !request.getParameter("in_date").equals("") && request.getParameter("hour") != null && !request.getParameter("hour").equals("") && request.getParameter("minute") != null && !request.getParameter("minute").equals(""))
				in_time = request.getParameter("in_date")+" "+request.getParameter("hour")+":"+request.getParameter("minute")+":00";
			else
				in_time = "";
			parameterObject.set("in_time", in_time);
			parameterObject.set("visitor_name", request.getParameter("s_visitor_name"));
			parameterObject.set("visited_name", request.getParameter("s_visited_name"));
			// visit record list
			List visitList = gaServices.visitRecordList(parameterObject, currentPage, pageSize);
			Object visitCount = gaServices.visitRecordListCount(parameterObject);

			request.setAttribute("visitList", visitList);
			request.setAttribute("visitCount", Integer.parseInt(visitCount.toString()));

			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("general visit record list by paging Exception. ", e);
		}

		return "/ga_view_visit.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String cardapplicationview(HttpServletRequest request) {
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		request.setAttribute("adminId", adminId);	
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("adminId", adminId);
			String serialNumber = request.getParameter("serialNumber");
			String cardNumber = request.getParameter("cardNumber");
			String provideFlag = request.getParameter("provideFlag");
			String qryType = request.getParameter("qryType") != null ? request.getParameter("qryType") : "0";
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String deptid = request.getParameter("deptid") != null ? request.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject.set("qryType", qryType);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);
			parameterObject.set("serialNumber", serialNumber);
			parameterObject.set("cardNumber", cardNumber);
			parameterObject.set("provideFlag", provideFlag);
			parameterObject.set("cpnyId", cpnyId);

			// bind parameter
			SimpleMap dataMap = new SimpleMap();
			int resultCount = eatingCardes.getEateryConfirmListNumber(parameterObject);
			List EateryConfirmList=eatingCardes.getEateryConfirmList(parameterObject,currentPage,pageSize);
			for(int i=0;i<EateryConfirmList.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)EateryConfirmList.get(i);			
				parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
				List affirmorList=eatingCardes.getEatingCardAffirmorList(parameterObject1);		
				dataMap.set("EateryTypeName",this.getEateryTypeName(dataMap.getString("EATERY_TYPE"), admin));
				dataMap.set("affirmorList", affirmorList);			
			}
			
			request.setAttribute("EateryConfirmList", EateryConfirmList);
			request.setAttribute("serialNumber", serialNumber);
			request.setAttribute("cardNumber", cardNumber);
			request.setAttribute("provideFlag", provideFlag);
			request.setAttribute("qryType", qryType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);		
			request.setAttribute("resultCount", resultCount);					
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
			request.setAttribute("cpnyId", cpnyId);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("GaViewCommand by paging Exception. ", e);
		}

		return "/ga_card_application_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String tempcardapplicationview(HttpServletRequest request) {
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		request.setAttribute("adminId", adminId);	
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("adminId", adminId);
			String provideFlag = request.getParameter("provideFlag");
			String qryType = request.getParameter("qryType") != null ? request.getParameter("qryType") : "0";
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String deptid = request.getParameter("deptid") != null ? request.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.set("qryType", qryType);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);
			parameterObject.set("provideFlag", provideFlag);
			parameterObject.set("cpnyId", cpnyId);

			// bind parameter
			SimpleMap dataMap = new SimpleMap();
			int resultCount = eatingCardes.getTempCardConfirmListNumber(parameterObject);
			List TempCardConfirmList=eatingCardes.getTempCardConfirmList(parameterObject,currentPage,pageSize);
			for(int i=0;i<TempCardConfirmList.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)TempCardConfirmList.get(i);			
				parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
				List affirmorList=eatingCardes.getTempCardAffirmorList(parameterObject1);		
				dataMap.set("permissionsTypeName",this.getPermissionsTypeName(dataMap.getString("PERMISSIONS_TYPE"), admin));	
				dataMap.set("affirmorList", affirmorList);				
			}
			
			request.setAttribute("TempCardConfirmList", TempCardConfirmList);
			request.setAttribute("provideFlag", provideFlag);
			request.setAttribute("qryType", qryType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);		
			request.setAttribute("resultCount", resultCount);					
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
			request.setAttribute("cpnyId", cpnyId);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("GaViewCommand by paging Exception. ", e);
		}

		return "/ga_tempcard_application_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String checkapplicationview(HttpServletRequest request) {
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		request.setAttribute("adminId", adminId);	
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("adminId", adminId);
			String qryType = request.getParameter("qryType") != null ? request.getParameter("qryType") : "0";
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String deptid = request.getParameter("deptid") != null ? request.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject.set("qryType", qryType);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);
			parameterObject.set("cpnyId", cpnyId);

			// bind parameter
			SimpleMap dataMap = new SimpleMap();
			int resultCount = eatingCardes.getCheckConfirmListNumber(parameterObject);
			List CheckConfirmList=eatingCardes.getCheckConfirmList(parameterObject,currentPage,pageSize);
			for(int i=0;i<CheckConfirmList.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)CheckConfirmList.get(i);			
				parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
				List affirmorList=eatingCardes.getCheckAffirmorList(parameterObject1);		
				dataMap.set("affirmorList", affirmorList);				
			}
			
			request.setAttribute("CheckConfirmList", CheckConfirmList);
			request.setAttribute("qryType", qryType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);		
			request.setAttribute("resultCount", resultCount);					
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
			request.setAttribute("cpnyId", cpnyId);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("GaViewCommand by paging Exception. ", e);
		}

		return "/ga_check_application_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

	public String tempCardExce(HttpServletRequest request, AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("adminId", adminId);
			String provideFlag = request.getParameter("provideFlag");
			String qryType = request.getParameter("qryType") != null ? request.getParameter("qryType") : "0";
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String deptid = request.getParameter("deptid") != null ? request.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request.getParameter("key") : "";
			parameterObject.set("qryType", qryType);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);
			parameterObject.set("provideFlag", provideFlag);

			SimpleMap dataMap = new SimpleMap();
			List tempCardList = eatingCardes.tempCardExcelList(parameterObject);
			for(int i=0;i<tempCardList.size();i++){
				dataMap=(SimpleMap)tempCardList.get(i);					
				dataMap.set("permissionsTypeName",this.getPermissionsTypeName(dataMap.getString("PERMISSIONS_TYPE"), admin));	
			}
			request.setAttribute("tempCardList", tempCardList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		//this.getAllWaitConfirmList(request, admin);
		return "/ga_tempCard_Exce.jsp";

	}

	public String checkExce(HttpServletRequest request, AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("adminId", adminId);
			String qryType = request.getParameter("qryType") != null ? request.getParameter("qryType") : "0";
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String deptid = request.getParameter("deptid") != null ? request.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request.getParameter("key") : "";
			parameterObject.set("qryType", qryType);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			List checkList = eatingCardes.checkExcelList(parameterObject);
			request.setAttribute("checkList", checkList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		//this.getAllWaitConfirmList(request, admin);
		return "/ga_check_Exce.jsp";

	}

	public String checkAccountExce(HttpServletRequest request, AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		//String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		//String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", cpnyId);
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String CHECKBANK_TYPE = request.getParameter("CHECKBANK_TYPE") != null ? request.getParameter("CHECKBANK_TYPE") : "";
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("CHECKBANK_TYPE", CHECKBANK_TYPE);

			List checkAccountList = eatingCardes.checkAccountExcelList(parameterObject);
			request.setAttribute("checkAccountList", checkAccountList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		//this.getAllWaitConfirmList(request, admin);
		return "/ga_checkAccount_Exce.jsp";

	}

	public String checkAccountBalance(HttpServletRequest request, AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		//String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", cpnyId);
			String startDate = request.getParameter("startDate") != null ? request.getParameter("startDate"): sdf.format(today.getTime());
			String endDate = request.getParameter("endDate") != null ? request.getParameter("endDate") : sdf.format(today.getTime());
			String CHECKBANK_TYPE = request.getParameter("CHECKBANK_TYPE") != null ? request.getParameter("CHECKBANK_TYPE") : "";
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			//parameterObject.set("CHECKBANK_TYPE", CHECKBANK_TYPE);

			List checkAccountBalanceList = eatingCardes.checkAccountBalanceList(parameterObject);
			request.setAttribute("checkAccountBalanceList", checkAccountBalanceList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		//this.getAllWaitConfirmList(request, admin);
		return "/ga_checkAccount_Balance.jsp";

	}
	
	public void oingConfirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		List confirmList = new ArrayList();
		try{
		String countsArr[]=request.getParameterValues("counts");
			for(int i=0;i<countsArr.length;i++){
				SimpleMap parameterObject =  new SimpleMap();
				parameterObject.set("Flag", request.getParameter("Flag"));
				parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
				parameterObject.set("confirmorIdea", request.getParameter("confirmorIdea_"+countsArr[i]));
				parameterObject.set("adminId", admin.getAdminID());
				confirmList.add(parameterObject);
			}		
		eatingCardes.oingConfirm(confirmList);	
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
	}
	public void oingTempCardConfirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		List confirmTempCardList = new ArrayList();
		try{
		String countsArr[]=request.getParameterValues("counts");
			for(int i=0;i<countsArr.length;i++){
				SimpleMap parameterObject =  new SimpleMap();
				parameterObject.set("Flag", request.getParameter("Flag"));
				parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
				parameterObject.set("confirmorIdea", request.getParameter("confirmorIdea_"+countsArr[i]));
				parameterObject.set("adminId", admin.getAdminID());
				confirmTempCardList.add(parameterObject);
			}		
		eatingCardes.oingTempCardConfirm(confirmTempCardList);	
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
	}
	public void oingCheckConfirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		List confirmCheckList = new ArrayList();
		try{
		String countsArr[]=request.getParameterValues("counts");
			for(int i=0;i<countsArr.length;i++){
				SimpleMap parameterObject =  new SimpleMap();
				parameterObject.set("Flag", request.getParameter("Flag"));
				parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
				parameterObject.set("checkAccount", request.getParameter("checkAccount_"+countsArr[i]));
				parameterObject.set("confirmorIdea", request.getParameter("confirmorIdea_"+countsArr[i]));
				parameterObject.set("adminId", admin.getAdminID());
				confirmCheckList.add(parameterObject);
			}			
		eatingCardes.oingCheckConfirm(confirmCheckList);
		eatingCardes.oingUpdateCheckAccount(confirmCheckList);	//担当确认同时更新支票状态（确认1-已使用1；作废2-作废2）
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
	}
	public String cardProvideAdd(HttpServletRequest request) {
		SimpleMap parameterObject = null;	
		try{
		parameterObject = ObjectBindUtil.getData(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("cpnyId", admin.getCpnyId());
		eatingCardes.cardProvideAdd(parameterObject);	
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 
		return this.cardProvideView(request);
	}
	public String cardProvideUpdate(HttpServletRequest request) {
		SimpleMap parameterObject =  new SimpleMap();
		try{
		String counts=request.getParameter("iindex");
		parameterObject.set("pkNo", request.getParameter("pkNo_"+counts));
		parameterObject.set("flag", request.getParameter("flag_"+counts));
		parameterObject.set("cardType", request.getParameter("cardType_"+counts));
		parameterObject.set("cardNumber", request.getParameter("cardNumber_"+counts));
		parameterObject.set("serialNumber", request.getParameter("serialNumber_"+counts));	
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("cpnyId", admin.getCpnyId());
		eatingCardes.cardProvideUpdate(parameterObject);	
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 
		return this.cardProvideView(request);
	}
	public String tempCardSave(HttpServletRequest request) {
		SimpleMap parameterObject =  new SimpleMap();
		try{
		parameterObject.set("applyNO", request.getParameter("applyNO"));
		parameterObject.set("cardNumber", request.getParameter("cardNumber"));
		parameterObject.set("adminName", request.getParameter("adminName"));
		parameterObject.set("returnYn", request.getParameter("returnYn"));
		parameterObject.set("returnDate", request.getParameter("returnDate"));	
		eatingCardes.tempCardSave(parameterObject);	
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 
		return this.tempcardapplicationview(request);
	}
	public String cardProvideDelete(HttpServletRequest request) {
		SimpleMap parameterObject = null;	
		try{
		parameterObject = ObjectBindUtil.getData(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("cpnyId", admin.getCpnyId());
		eatingCardes.cardProvideDelete(parameterObject);	
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 
		return this.cardProvideView(request);
	}
	public String cardProvideView(HttpServletRequest request) {
		SimpleMap parameterObject = null;	
		try{
		parameterObject = ObjectBindUtil.getData(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("cpnyId", admin.getCpnyId());
		List cardTypeList=eatingCardes.getCardType(parameterObject);
		List cardProvidList=eatingCardes.cardProvidList(parameterObject);
		request.setAttribute("cardProvidList", cardProvidList);
		request.setAttribute("cardTypeList",cardTypeList);
		request.setAttribute("applyNo", request.getParameter("applyNo"));
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 	
		
		
		return "/ga_card_provide.jsp";
	}
	public String tempCardProvideView(HttpServletRequest request) {
		SimpleMap parameterObject = null;	
		try{
		parameterObject = ObjectBindUtil.getData(request);
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("cpnyId", admin.getCpnyId());
		List tempCardProvidList=eatingCardes.tempCardProvidList(parameterObject);
		request.setAttribute("tempCardProvidList", tempCardProvidList);
		request.setAttribute("applyNo", request.getParameter("applyNo"));
		request.setAttribute("name", admin.getChineseName());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 	
		return "/ga_tempcard_provide.jsp";
	}
	public String getEateryTypeName(String eateryType,AdminBean admin) {
		/*String eateryTypeStr[]=eateryType.split(",");
		String InPameter="";
		for(int i =0;i<eateryTypeStr.length;i++){
			InPameter =InPameter+"'"+eateryTypeStr+"',";
			
		}*/
		String returnStr="";
		SimpleMap  sm = new SimpleMap();
		sm.set("eateryType", eateryType);
		sm.set("cpnyId", admin.getCpnyId());
		try{
			List  result = eatingCardes.getEateryTypeName(sm);
			for(int i=0;i<result.size();i++){
				SimpleMap simpleMap =(SimpleMap)result.get(i);
				returnStr +=simpleMap.getString("GM_TYPE")+"，";
				
			}
			
	} catch (Exception e) {
		
		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getEateryTypeName error ", e);
	}
		return returnStr.substring(0,returnStr.length()-1);
	}
	public String getPermissionsTypeName(String PERMISSIONS_TYPE,AdminBean admin) {
		/*String eateryTypeStr[]=eateryType.split(",");
		String InPameter="";
		for(int i =0;i<eateryTypeStr.length;i++){
			InPameter =InPameter+"'"+eateryTypeStr+"',";
			
		}*/
		String returnStr="";
		SimpleMap  sm = new SimpleMap();
		sm.set("PERMISSIONS_TYPE", PERMISSIONS_TYPE);
		sm.set("cpnyId", admin.getCpnyId());
		try{
			List  result = eatingCardes.getTempCardTypeName(sm);
			for(int i=0;i<result.size();i++){
				SimpleMap simpleMap =(SimpleMap)result.get(i);
				returnStr +=simpleMap.getString("GM_TYPE")+"，";
				
			}
	 if(returnStr.equals("")){
		 returnStr="无，";
	 }
			
	} catch (Exception e) {
		
		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getEateryTypeName error ", e);
	}
		return returnStr.substring(0,returnStr.length()-1);
	}
}