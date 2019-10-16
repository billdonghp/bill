package com.ait.pu.cmd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.ga.business.VisiterApplicationsServices;
import com.ait.ga.cmd.visit.VisiterApplications;
import com.ait.ga.dao.visiter.VisiterDAO;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.pu.services.VisiterManagementServices;
import com.ait.safe.business.RulesServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class VisiterManagement implements Command {
	private VisiterManagementServices visiterManagementServices;
	private VisiterApplicationsServices visiterApplicationsServices;
	
	private ExpressApplyAndAffirmServices eaaServices = null;
	
	private Mail mail = new Mail() ;
	
	private SimpleMap parameterObject;

	private String content = null;

	public VisiterManagement() {
		visiterManagementServices = new VisiterManagementServices();
		visiterApplicationsServices = new VisiterApplicationsServices();
		eaaServices = new ExpressApplyAndAffirmServices();
	}

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
			String returnPage = null;
			String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
			content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			if (content.equals("visiterManagementView")) {
				returnPage = this.visiterManagementView(request);
			}else if (content.equals("visiterManagementModifyView")) {
				returnPage = this.visiterManagementModifyView(request);
			}else if(content.equals("visiterManagementModify")){
				returnPage = this.visiterManagementModify(request);
			}else if(content.equals("visiterSearch")){
				returnPage = this.visiterSearch(request);
			}else if(content.equals("visiterTypeSearch")){
				returnPage = this.visiterTypeSearch(request);
			}else if(content.equals("updateModifyFlag")){
				returnPage = this.updateModifyFlag(request);
			}else if(content.equals("deleteVisiterManagement")){
				returnPage=this.deleteVisiterManagement(request);
				
			}
		} else {
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

	
	private String visiterManagementView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap map = null; 
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String flag = request.getParameter("flag");
		String visiterFlag = StringUtil.checkNull(request.getParameter("visiterFlag"),"");
		String countryFlag = StringUtil.checkNull(request.getParameter("countryFlag"),"");
		String visiterTypeSearch = request.getParameter("visiterType");
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
			if(adminId.equals("1463771"))
				adminId = "9999901";
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("visiterTypeSearch", visiterTypeSearch);
			String  falg1 = parameterObject.getString("qryType")!=null?parameterObject.getString("qryType"):"0";
			parameterObject.put("falg", falg1);
			parameterObject.put("ADMIN_ID", adminId);
			parameterObject.put("cpnyId", cpnyId);
			// retrieve attendance record list
			int allVisiterManagementInformationCount = visiterManagementServices.SearchAllVisiterManagementInformationCount(parameterObject);
			request.setAttribute("recordCount", NumberUtil.parseInt(allVisiterManagementInformationCount)) ;
			
			//得到参观者管理全部显示信息
			List allVisiterManagementInformation = visiterManagementServices.SearchAllVisiterManagementInformation(parameterObject, currentPage, pageSize);
			
			for(int i=0 ; i<allVisiterManagementInformation.size() ; i++){
				map = (SimpleMap) allVisiterManagementInformation.get(i);
				String ga_visiter_id = map.getString("GA_VISITER_APPLY_ID");
				parameterObject.set("ga_visiter_id", ga_visiter_id);
				List allVisiterManagementInformationApply = visiterManagementServices.allVisiterManagementInformationApply(parameterObject);
				map.set("allVisiterManagementInformationApply", allVisiterManagementInformationApply);
			}
			request.setAttribute("allVisiterManagementInformation", allVisiterManagementInformation) ;
			
			List visiterCountry = visiterManagementServices.visiterCountry(parameterObject);
			request.setAttribute("visiterCountry", visiterCountry);
			request.setAttribute("visiterCountrySize", visiterCountry.size()+"");
			
			// paging parameter
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("startDate", parameterObject.get("startDate"));
			request.setAttribute("endDate", parameterObject.get("endDate"));
			request.setAttribute("country", parameterObject.get("country"));
			request.setAttribute("deptid", parameterObject.get("deptid"));
			request.setAttribute("qryType", falg1);
			request.setAttribute("key", parameterObject.get("key"));
			request.setAttribute("visiterType", visiterTypeSearch);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId());
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}
		if(flag == null || flag.equals("1")){
			List filelist=new VisiterApplications().getFileOrPhotoAddress();
			request.setAttribute("filelist", filelist);
			return "/pu_Visiter_Management.jsp?menu_code=" + parameterObject.getString("menu_code");
		}
		if(flag.equals("2")){
		   if(visiterFlag.equals("")){
			   
			    List allVisiterManagementInformation = visiterManagementServices.SearchAllVisiterManagementInformation(parameterObject, 0, 1000000);
				request.setAttribute("allVisiterManagementInformation", allVisiterManagementInformation) ;
				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4"+visiterFlag);
				
				return "/reports/pu_report/pu_Visiter_report.jsp";
		   }else if (visiterFlag.equals("2")){
			   List visiterViewDetail1 = visiterApplicationsServices.visiterViewDetailReport(parameterObject);
			   request.setAttribute("visiterViewDetail1", visiterViewDetail1);
			   return "/reports/pu_report/pu_Visiter_viewDetail_report.jsp";
		   }
			
		}
		return "";
	}
	
	private String visiterManagementModifyView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();

		String visiterManagementId = request.getParameter("visiterManagementId");
		String returnFlag = request.getParameter("returnFlag");
		
		request.setAttribute("visiterManagementId", visiterManagementId);
		request.setAttribute("applyno", visiterManagementId);
		
		request.setAttribute("returnFlag", returnFlag);
		
		SimpleMap map = null;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("visiterManagementId", visiterManagementId);
			parameterObject.set("applyno", visiterManagementId);
			// retrieve attendance record list
			
			List allVisiterManagementModifyView = visiterManagementServices.allVisiterManagementModifyView(parameterObject);
			int allVisiterManagementModifyViewSize = allVisiterManagementModifyView.size();
			
			List allDevice = visiterApplicationsServices.allDevice(parameterObject);
			request.setAttribute("allDevice", allDevice);
			
			List deptList = visiterApplicationsServices.getDept(parameterObject);
			request.setAttribute("deptList", deptList);
			
			for(int i=0 ; i<allVisiterManagementModifyViewSize ; i++){
				map = (SimpleMap) allVisiterManagementModifyView.get(i);
				String visit_item = map.getString("VISIT_ITEM");
				String file1 = map.getString("FILE1");
				String jiangjie = map.getString("JIANGJIE");
				request.setAttribute("jiangjie", jiangjie);
				request.setAttribute("visit_item", visit_item);
				request.setAttribute("file1", file1);
				String ApplyId = map.getString("GA_VISITER_APPLY_ID");
				String person_id = map.getString("FRONT_EMP_ID");
				String visiter_type = map.getString("VISITER_TYPE");
				String visiter_country = map.getString("VISITER_COUNTRY");
				String ApplyDuty = map.getString("VISITER_DUTY");
				String ApplyObjective = map.getString("VISITER_OBJECTIVE");
				String ApplyCountry = map.getString("VISITER_COUNTRY");
				String visiterDate = map.getString("VISITER_DATE");
				request.setAttribute("visiterDate", visiterDate);
				String empName = map.getString("CHINESENAME");
				request.setAttribute("empName", empName);
				request.setAttribute("person_id", person_id);
				String ComeTime = map.getString("VISITER_COME_TIME");
				String visiterComeTime[] = ComeTime.split(":");
				request.setAttribute("hour", visiterComeTime[0]);
				request.setAttribute("min", visiterComeTime[1]);
				
				String EndTime = map.getString("VISITER_END_TIME");
				String visiterEndTime[] = EndTime.split(":");
				request.setAttribute("endhour", visiterEndTime[0]);
				request.setAttribute("endmin", visiterEndTime[1]);
				
				String VisiterCarNumber = map.getString("VISITER_CARNUMBER");
				String visiter_carnum = map.getString("VISITER_CARNUM");
				String contactTel = map.getString("VISITER_CONTACT_TEL");
				String gift = map.getString("VISITER_GIFT_NAME");
				String giftNumber = map.getString("VISITER_GIFT_NUMBER");
				String apiLanguage = map.getString("API_LANGUAGE");
				
				String api_language_id = map.getString("API_LANGUAGE_ID");
				
				String dept = map.getString("DEPTNAME");
				request.setAttribute("dept", dept);
				String chinesename = map.getString("CHINESENAME");
				
				String playApply = map.getString("PLAY_APPLY");
				String playApplyName = map.getString("PLAY_APPLY");
				
				String visiter_Id = map.getString("GA_VISITER_ID");
				String visiter_people_num=map.getString("VISITER_PEOPLE_NUM");
				request.setAttribute("visiter_people_num", visiter_people_num);
				request.setAttribute("visiter_Id", visiter_Id);
				
				String welcome = map.getString("WELCOME");
				request.setAttribute("welcome", welcome);
				
				
				String distiniction = map.getString("VISITER_DISTINICTION");
				request.setAttribute("distiniction", distiniction);
				
				String distinictionid = map.getString("VISITER_DISTINICTION_ID");
				request.setAttribute("distinictionid", distinictionid);
				
				List allDistiniction = visiterManagementServices.allDistiniction();
				request.setAttribute("allDistiniction", allDistiniction);
				
				request.setAttribute("ApplyId", ApplyId);
				request.setAttribute("visiter_type", visiter_type);
				request.setAttribute("visiter_country", visiter_country);
				request.setAttribute("ApplyDuty", ApplyDuty);
				request.setAttribute("ApplyObjective", ApplyObjective);
				request.setAttribute("ApplyCountry", ApplyCountry);
				List listMM=DateUtil.getTimePerMMList();
				List listHH=DateUtil.getTimePerHourList();
				request.setAttribute("listMM",listMM);
				request.setAttribute("listHH",listHH);
				request.setAttribute("VisiterCarNumber", VisiterCarNumber);
				request.setAttribute("visiter_carnum", visiter_carnum);
				request.setAttribute("contactTel", contactTel);
				request.setAttribute("gift", gift);
				request.setAttribute("giftNumber", giftNumber);
				
				if(apiLanguage==null){
					request.setAttribute("apiLanguage", "");
				}else{
					request.setAttribute("apiLanguage", apiLanguage);
				}
				
				
				if(api_language_id==null){
					request.setAttribute("api_language_id", "");
				}else{
					request.setAttribute("api_language_id", api_language_id);
				}
				
				request.setAttribute("dept", dept);
				request.setAttribute("chinesename", chinesename);
				request.setAttribute("playApply", playApply);
				request.setAttribute("playApplyName", playApplyName);
				
				String huiyishi_applyid = map.getString("HUIYISHI_APPLYID");
				request.setAttribute("huiyishi_applyid", huiyishi_applyid);
				String device = map.getString("DEVICE");
				request.setAttribute("device", device);
				List visiterViewDetail1 = visiterApplicationsServices.visiterViewDetail1(parameterObject);
				request.setAttribute("visiterViewDetail1", visiterViewDetail1);
				
				List visiterViewDetail2 = visiterApplicationsServices.visiterViewDetail2(parameterObject);
				request.setAttribute("visiterViewDetail2", visiterViewDetail2);
				
				List visiterViewDetail3 = visiterApplicationsServices.visiterViewDetail3(parameterObject);
				request.setAttribute("visiterViewDetail3", visiterViewDetail3);
				
				int visiterViewDetail1Int = visiterViewDetail1.size();
				request.setAttribute("visiterViewDetail1Int", visiterViewDetail1Int+"");
				
				int visiterViewDetail2Int = visiterViewDetail2.size();
				request.setAttribute("visiterViewDetail2Int", visiterViewDetail2Int+"");
				
				int visiterViewDetail3Int = visiterViewDetail3.size();
				request.setAttribute("visiterViewDetail3Int", visiterViewDetail3Int+"");
				
				
				
				
				String VISITER_CAR_MODEL = map.getString("VISITER_CAR_MODEL");
				request.setAttribute("VISITER_CAR_MODEL", VISITER_CAR_MODEL);
				
//				String VISITER_EATRY_FLAG = map.getString("VISITER_EATRY_FLAG");
//				request.setAttribute("VISITER_EATRY_FLAG", VISITER_EATRY_FLAG);
//				String VISITER_EATRY_DISTINCTION = map.getString("VISITER_EATRY_DISTINCTION");
//				request.setAttribute("VISITER_EATRY_DISTINCTION", VISITER_EATRY_DISTINCTION);
//				String VISITER_EATRY_NAME = map.getString("VISITER_EATRY_NAME");
//				request.setAttribute("VISITER_EATRY_NAME", VISITER_EATRY_NAME);
//				String VISITER_EATRY_DATE = map.getString("VISITER_EATRY_DATE");
//				request.setAttribute("VISITER_EATRY_DATE", VISITER_EATRY_DATE);
//				String VISITER_EATRY_TIME[] = StringUtil.checkNull(map.getString("VISITER_EATRY_TIME")).split(":");
//				if(VISITER_EATRY_TIME.length >1){
//				request.setAttribute("VISITER_EATRY_HOUR", VISITER_EATRY_TIME[0]);
//				request.setAttribute("VISITER_EATRY_MIN", VISITER_EATRY_TIME[1]);
//				}
//				String VISITER_EATRY_TYPE = map.getString("VISITER_EATRY_TYPE");
//				request.setAttribute("VISITER_EATRY_TYPE", VISITER_EATRY_TYPE);
//				String VISITER_EATRY_NUMBER = map.getString("VISITER_EATRY_NUMBER");
//				request.setAttribute("VISITER_EATRY_NUMBER", VISITER_EATRY_NUMBER);
//				String VISITER_EATRY_MEMO = map.getString("VISITER_EATRY_MEMO");
//				request.setAttribute("VISITER_EATRY_MEMO", VISITER_EATRY_MEMO);
				String VISITER_VOITURE_FLAG = map.getString("VISITER_VOITURE_FLAG");
				request.setAttribute("VISITER_VOITURE_FLAG", VISITER_VOITURE_FLAG);
				String VISITER_VOITURE_NUMBER = map.getString("VISITER_VOITURE_NUMBER");
				request.setAttribute("VISITER_VOITURE_NUMBER", VISITER_VOITURE_NUMBER);
				String VISITER_VOITURE_IN_DATE = map.getString("VISITER_VOITURE_IN_DATE");
				request.setAttribute("VISITER_VOITURE_IN_DATE", VISITER_VOITURE_IN_DATE);
				//String VISITER_VOITURE_IN_TIME = map.getString("VISITER_VOITURE_IN_TIME");
				String VISITER_VOITURE_IN_TIME[] = StringUtil.checkNull(map.getString("VISITER_VOITURE_IN_TIME")).split(":");
				if(VISITER_VOITURE_IN_TIME.length >1){
				request.setAttribute("VISITER_VOITURE_IN_HOUR", VISITER_VOITURE_IN_TIME[0]);
				request.setAttribute("VISITER_VOITURE_IN_MIN", VISITER_VOITURE_IN_TIME[1]);
				}
				String VISITER_VOITURE_OUT_DATE = map.getString("VISITER_VOITURE_OUT_DATE");
				request.setAttribute("VISITER_VOITURE_OUT_DATE", VISITER_VOITURE_OUT_DATE);
				//String VISITER_VOITURE_OUT_TIME = map.getString("VISITER_VOITURE_OUT_TIME");
				String VISITER_VOITURE_OUT_TIME[] = StringUtil.checkNull(map.getString("VISITER_VOITURE_OUT_TIME")).split(":");
				if(VISITER_VOITURE_OUT_TIME.length >1){
				request.setAttribute("VISITER_VOITURE_OUT_HOUR", VISITER_VOITURE_OUT_TIME[0]);
				request.setAttribute("VISITER_VOITURE_OUT_MIN", VISITER_VOITURE_OUT_TIME[1]);
				}
				String VISITER_VOITURE_START_AREA = map.getString("VISITER_VOITURE_START_AREA");
				request.setAttribute("VISITER_VOITURE_START_AREA", VISITER_VOITURE_START_AREA);
				String VISITER_VOITURE_PASS_AREA = map.getString("VISITER_VOITURE_PASS_AREA");
				request.setAttribute("VISITER_VOITURE_PASS_AREA", VISITER_VOITURE_PASS_AREA);
				String VISITER_VOITURE_END_AREA = map.getString("VISITER_VOITURE_END_AREA");
				request.setAttribute("VISITER_VOITURE_END_AREA", VISITER_VOITURE_END_AREA);
				String VISITER_VOITURE_MEMO = map.getString("VISITER_VOITURE_MEMO");
				request.setAttribute("VISITER_VOITURE_MEMO", VISITER_VOITURE_MEMO);
				String VISITER_CONFERENCE_FLAG = map.getString("VISITER_CONFERENCE_FLAG");
				request.setAttribute("VISITER_CONFERENCE_FLAG", VISITER_CONFERENCE_FLAG);
				String VISITER_CONFERENCE_ROOM = map.getString("VISITER_CONFERENCE_ROOM");
				request.setAttribute("VISITER_CONFERENCE_ROOM", VISITER_CONFERENCE_ROOM);
				String VISITER_CONFERENCE_LANGUAGE = StringUtil.checkNull(map.getString("VISITER_CONFERENCE_LANGUAGE"));
				request.setAttribute("VISITER_CONFERENCE_LANGUAGE", VISITER_CONFERENCE_LANGUAGE);
				String VISITER_CONFERENCE_PEOPLE = map.getString("VISITER_CONFERENCE_PEOPLE");
				request.setAttribute("VISITER_CONFERENCE_PEOPLE", VISITER_CONFERENCE_PEOPLE);
				String VISITER_CONFERENCE_MANAGE = map.getString("VISITER_CONFERENCE_MANAGE");
				request.setAttribute("VISITER_CONFERENCE_MANAGE", VISITER_CONFERENCE_MANAGE);
				String VISITER_CONFERENCE_DATE = map.getString("VISITER_CONFERENCE_DATE");
				request.setAttribute("VISITER_CONFERENCE_DATE", VISITER_CONFERENCE_DATE);
				//String VISITER_CONFERENCE_TIME = map.getString("VISITER_CONFERENCE_TIME");
				String VISITER_CONFERENCE_TIME[] = StringUtil.checkNull(map.getString("VISITER_CONFERENCE_TIME")).split(":");
				if(VISITER_CONFERENCE_TIME.length >1){
				request.setAttribute("VISITER_CONFERENCE_HOUR", VISITER_CONFERENCE_TIME[0]);
				request.setAttribute("VISITER_CONFERENCE_MIN", VISITER_CONFERENCE_TIME[1]);
				}
				String VISITER_CONFERENCE_PURPOSE = map.getString("VISITER_CONFERENCE_PURPOSE");
				request.setAttribute("VISITER_CONFERENCE_PURPOSE", VISITER_CONFERENCE_PURPOSE);
				String VISITER_FACTORY_FLAG = map.getString("VISITER_FACTORY_FLAG");
				request.setAttribute("VISITER_FACTORY_FLAG", VISITER_FACTORY_FLAG);
				String VISITER_FACTORY_NUMBER = map.getString("VISITER_FACTORY_NUMBER");
				request.setAttribute("VISITER_FACTORY_NUMBER", VISITER_FACTORY_NUMBER);
				String VISITER_FACTORY_METHOD = map.getString("VISITER_FACTORY_METHOD");
				request.setAttribute("VISITER_FACTORY_METHOD", VISITER_FACTORY_METHOD);
				String VISITER_FACTORY_ROUTE = map.getString("VISITER_FACTORY_ROUTE");
				request.setAttribute("VISITER_FACTORY_ROUTE", VISITER_FACTORY_ROUTE);
				String VISITER_FACTORY_DEVICE = map.getString("VISITER_FACTORY_DEVICE");
				request.setAttribute("VISITER_FACTORY_DEVICE", VISITER_FACTORY_DEVICE);
				String VISITER_FACTORY_HELMET = map.getString("VISITER_FACTORY_HELMET");
				request.setAttribute("VISITER_FACTORY_HELMET", VISITER_FACTORY_HELMET);
				String VISITER_FACTORY_HELMET_NUMBER = map.getString("VISITER_FACTORY_HELMET_NUMBER");
				request.setAttribute("VISITER_FACTORY_HELMET_NUMBER", VISITER_FACTORY_HELMET_NUMBER);
				String VISITER_FACTORY_SYSTEM = map.getString("VISITER_FACTORY_SYSTEM");
				request.setAttribute("VISITER_FACTORY_SYSTEM", VISITER_FACTORY_SYSTEM);
				String VISITER_FACTORY_C_NUMBER = map.getString("VISITER_FACTORY_C_NUMBER");
				request.setAttribute("VISITER_FACTORY_C_NUMBER", VISITER_FACTORY_C_NUMBER);
				String VISITER_FACTORY_K_NUMBER = map.getString("VISITER_FACTORY_K_NUMBER");
				request.setAttribute("VISITER_FACTORY_K_NUMBER", VISITER_FACTORY_K_NUMBER);
				String VISITER_FACTORY_E_NUMBER = map.getString("VISITER_FACTORY_E_NUMBER");
				request.setAttribute("VISITER_FACTORY_E_NUMBER", VISITER_FACTORY_E_NUMBER);
				String VISITER_FACTORY_PEOPLE = map.getString("VISITER_FACTORY_PEOPLE");
				request.setAttribute("VISITER_FACTORY_PEOPLE", VISITER_FACTORY_PEOPLE);
				String VISITER_FACTORY_WELCOME_SPEECH = map.getString("VISITER_FACTORY_WELCOME_SPEECH");
				request.setAttribute("VISITER_FACTORY_WELCOME_SPEECH", VISITER_FACTORY_WELCOME_SPEECH);
				String VISITER_FACTORY_LANGUAGE = map.getString("VISITER_FACTORY_LANGUAGE");
				request.setAttribute("VISITER_FACTORY_LANGUAGE", VISITER_FACTORY_LANGUAGE);
				String VISITER_FACTORY_MEMO = map.getString("VISITER_FACTORY_MEMO");
				request.setAttribute("VISITER_FACTORY_MEMO", VISITER_FACTORY_MEMO);
				String VISITER_TOURISM_FLAG = map.getString("VISITER_TOURISM_FLAG");
				request.setAttribute("VISITER_TOURISM_FLAG", VISITER_TOURISM_FLAG);
				String VISITER_TOURISM = map.getString("VISITER_TOURISM");
				request.setAttribute("VISITER_TOURISM", VISITER_TOURISM);
				String VISITER_TOURISM_DATE = map.getString("VISITER_TOURISM_DATE");
				request.setAttribute("VISITER_TOURISM_DATE", VISITER_TOURISM_DATE);
				//String VISITER_TOURISM_TIME = map.getString("VISITER_TOURISM_TIME");
				String VISITER_TOURISM_TIME[] = StringUtil.checkNull(map.getString("VISITER_TOURISM_TIME")).split(":");
				if(VISITER_TOURISM_TIME.length >1){
				request.setAttribute("VISITER_TOURISM_HOUR", VISITER_TOURISM_TIME[0]);
				request.setAttribute("VISITER_TOURISM_MIN", VISITER_TOURISM_TIME[1]);
				}
				String VISITER_TOURISM_TYPE = map.getString("VISITER_TOURISM_TYPE");
				request.setAttribute("VISITER_TOURISM_TYPE", VISITER_TOURISM_TYPE);
				String VISITER_TOURISM_FEE = map.getString("VISITER_TOURISM_FEE");
				request.setAttribute("VISITER_TOURISM_FEE", VISITER_TOURISM_FEE);
				String VISITER_TOURISM_MEMO = map.getString("VISITER_TOURISM_MEMO");
				request.setAttribute("VISITER_TOURISM_MEMO", VISITER_TOURISM_MEMO);
				
				
				
				
			}
			
			
			
			List visiterType = visiterManagementServices.visiterType(parameterObject);
			
			request.setAttribute("visiterType", visiterType);
			
			parameterObject.put("cpny_id", ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId());
			//宣传片
			List file = visiterApplicationsServices.file(parameterObject);
			request.setAttribute("visit_pro", file);
			
			//参观项目
			List visit_item = visiterApplicationsServices.visit_item(parameterObject);
			request.setAttribute("visit_pian", visit_item);
			//会议室
			List roomname = eaaServices.roomnameList(parameterObject);
			request.setAttribute("roomname", roomname);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pu_Visiter_Management_Modify_View error ", e);
		}
		return "/pu_Visiter_Management_Modify_View.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String visiterManagementModify(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String adminName = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		SimpleMap map = null;
		parameterObject = ObjectBindUtil.getData(request);
		String visiterManagementId = request.getParameter("visiterManagementId");
		String returnFlag = request.getParameter("returnFlag");
		String returnPage = "";
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
			
			parameterObject.set("supervisor", adminId);
			
			
			
			parameterObject.set("visiterManagementId", visiterManagementId);
			parameterObject.set("hour", parameterObject.getString("hour")+":"+parameterObject.getString("min"));
			parameterObject.set("Outhour", parameterObject.getString("Outhour")+":"+parameterObject.getString("Outmin"));
			
			//parameterObject.put("visiterEatryTime", parameterObject.getString("visiterEatryHour")+":"+parameterObject.getString("visiterEatryMin"));
			parameterObject.put("visiterVoitureInTime", parameterObject.getString("visiterVoitureInHour")+":"+parameterObject.getString("visiterVoitureInMin"));
			parameterObject.put("visiterVoitureOutTime", parameterObject.getString("visiterVoitureOutHour")+":"+parameterObject.getString("visiterVoitureOutMin"));
			parameterObject.put("visiterConferenceTime", parameterObject.getString("visiterConferenceHour")+":"+parameterObject.getString("visiterConferenceMin"));
			parameterObject.put("visiterTourismTime", parameterObject.getString("visiterTourismHour")+":"+parameterObject.getString("visiterTourismMin"));
			
			
			
			visiterManagementServices.updateVisiterManagementModify(parameterObject);
			
			if("affirm".equals(returnFlag)){//决裁页面的修改(决裁和确认页面共用的修改功能)
				//ADMIN_ID parameterObject.se
				SimpleMap map2 = null;
				SimpleMap map1 = null;
				int allvisiterApplyInformationCount = visiterApplicationsServices.allvisiterApplyInformationCount(parameterObject);
				request.setAttribute("allvisiterApplyInformationCount", allvisiterApplyInformationCount+"");
				
				List allvisiterApplyInformation = visiterApplicationsServices.allvisiterApplyInformation(parameterObject, currentPage, pageSize);
				
				int allvisiterApplyInformationSize = allvisiterApplyInformation.size();
				for(int i=0 ; i<allvisiterApplyInformationSize ; i++){
					map2 = (SimpleMap) allvisiterApplyInformation.get(i);
					String applyId = map2.getString("GA_VISITER_APPLY_ID");
					parameterObject.set("applyId", applyId);
					map2.set("applyId", applyId);
					List allVisiterApproval = visiterApplicationsServices.allVisiterApproval(parameterObject);
					String affirmNo = "";
					String empid = "";
					String tempapplyId = "";
					for(int j=0 ; j<allVisiterApproval.size() ; j++){
						map1 = (SimpleMap) allVisiterApproval.get(j);
						affirmNo = map1.getString("GA_AFFIRM_NO");
						empid = map1.getString("AFFIRMOR_ID");
						if(empid.equals(adminId.toString())){
							map2.set("affirmNo", affirmNo);
						}
						tempapplyId = map1.getString("APPLY_NO");
					}
					request.setAttribute("empid", empid);
					request.setAttribute("adminName", adminName);
					request.setAttribute("tempapplyId", tempapplyId);
					map2.set("allVisiterApproval", allVisiterApproval);
					request.setAttribute("allvisiterApplyInformation", allvisiterApplyInformation);
					request.setAttribute("recordCount", NumberUtil
							.parseInt(allvisiterApplyInformationCount));
				}
				
				returnPage="ga_visiter_Approval.jsp";
			}else{//确认页面的修改(决裁和确认页面共用的修改功能)
				
				// retrieve attendance record list
				SimpleMap simpleMap = new SimpleMap();
				simpleMap.set("ADMIN_ID", adminId);
				int allVisiterManagementInformationCount = visiterManagementServices.SearchAllVisiterManagementInformationCount(simpleMap);
				request.setAttribute("recordCount", NumberUtil.parseInt(allVisiterManagementInformationCount)) ;
				String  falg = parameterObject.getString("qryType")!=null?parameterObject.getString("qryType"):"0";
				simpleMap.put("falg", falg);
				request.setAttribute("qryType", falg);
				//得到参观者管理全部显示信息
				List allVisiterManagementInformation = visiterManagementServices.SearchAllVisiterManagementInformation(simpleMap, currentPage, pageSize);
				
				request.setAttribute("allVisiterManagementInformation", allVisiterManagementInformation) ;
				
				List filelist=new VisiterApplications().getFileOrPhotoAddress();
				request.setAttribute("filelist", filelist);
				
				returnPage="pu_Visiter_Management.jsp";
			}
			
			
			
			//修改发送邮件
			String playApply = request.getParameter("playApply");
			String playApplyName = request.getParameter("play_apply");
			parameterObject.set("empid", playApply);
			String toEmail = visiterManagementServices.getVisiterApplyEmail(parameterObject);
			if(!toEmail.equals("") && toEmail!=null ){				
				this.sendVisiterConfirmMail(
						"参观者申请修改提醒",
						visiterManagementId,						 
						playApplyName,
						parameterObject.getString("visiterTime"), 
						parameterObject.getString("hour")+":"+parameterObject.getString("min"),
						parameterObject.getString("Outhour")+":"+parameterObject.getString("Outmin"), 
						parameterObject.getString("visit_count"), 
						toEmail,
						cpnyId
						);
				}
			
			
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}
//		return this.visiterManagementView(request);
		
		return "/"+returnPage+"?menu_code=" + parameterObject.getString("menu_code");
	}
	
	private String visiterSearch(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();

		String flag = request.getParameter("flag");
		
		String excelflag = StringUtil.checkNull(request.getParameter("excelflag"),"1");
		
		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		
		
		SimpleMap map = null;
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
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			String date = format.format(Calendar.getInstance().getTime());
			if(flag.equals("1")){
				parameterObject.set("Starttime", date);
				parameterObject.set("Endtime", date);
				
				request.setAttribute("StartDate", date+"-01");
				request.setAttribute("EndDate", DateFormat.getDateInstance().format(DateUtil.getLastDayOfMonth(new Date())));
			}
			if(flag.equals("2")){
				parameterObject.set("StartDate", StartDate);
				parameterObject.set("EndDate", EndDate);
				request.setAttribute("StartDate", StartDate);
				request.setAttribute("EndDate", EndDate);
			}
			parameterObject.set("ADMIN_ID", adminId);
			// retrieve attendance record list
			
			//得到中国的来访人数
			int Visiter_Chinese = visiterManagementServices.visiterChinese(parameterObject);
			//得到韩国的来访人数
			int Visiter_Korea = visiterManagementServices.visiterKorea(parameterObject);
			
//			得到其他国家的来访人数
			int Visiter_Annather = visiterManagementServices.visiterAnnather(parameterObject);
			
			//合计
			int totalVisiter = Visiter_Chinese+Visiter_Korea+Visiter_Annather;
			
			
//			得到中国的来访次数
			int Visiter_Chinese_Num = visiterManagementServices.visiterChineseNum(parameterObject);
			
			//得到韩国的来访次数
			int Visiter_Korea_Num = visiterManagementServices.visiterKoreaNum(parameterObject);
			
//			得到其他国家的来访次数
			int Visiter_Annather_Num = visiterManagementServices.visiterAnnatherNum(parameterObject);
			
			//合计次数
			int totalVisiter_Num = Visiter_Chinese_Num+Visiter_Korea_Num+Visiter_Annather_Num;
			
			request.setAttribute("Visiter_Chinese", Visiter_Chinese+"");
			request.setAttribute("Visiter_Korea", Visiter_Korea+"");
			request.setAttribute("Visiter_Annather", Visiter_Annather+"");
			request.setAttribute("totalVisiter", totalVisiter+"");
			
			request.setAttribute("Visiter_ChineseNum", Visiter_Chinese_Num+"");
			request.setAttribute("Visiter_KoreaNum", Visiter_Korea_Num+"");
			request.setAttribute("Visiter_AnnatherNum", Visiter_Annather_Num+"");
			request.setAttribute("totalVisiterNum", totalVisiter_Num+"");
			
			
			
			/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			String date = format.format(Calendar.getInstance().getTime());*/
//			if(flag.equals("1")){
//				parameterObject.set("Starttime", date);
//				parameterObject.set("Endtime", date);
//			}
//			if(flag.equals("2")){
//				parameterObject.set("StartDate", StartDate);
//				parameterObject.set("EndDate", EndDate);
//			}
			
			// retrieve attendance record list
			
			//得到所有参观者类别
			List all_Visiter_Type = visiterManagementServices.allVisiterType(parameterObject);
			int totalPeopleNum = 0;
			int totalPeopleTypeNum = 0;
			for(int i=0 ; i<all_Visiter_Type.size() ; i++){
				map = (SimpleMap) all_Visiter_Type.get(i);
				String VisiterTypeId = map.getString("CODE_ID");
				parameterObject.set("VisiterTypeId", VisiterTypeId);
				
				//人数
				List all_Visiter_Type_Num = visiterManagementServices.allVisiterTypeNum(parameterObject);
				
				Iterator it = all_Visiter_Type_Num.iterator();
				if(it.hasNext()){
					SimpleMap sm = (SimpleMap) it.next();
					totalPeopleTypeNum += NumberUtil.parseInt(sm.getString("NUM"));
				}
				
				//来访次数
				List all_Visiter_Type_VisiterNum = visiterManagementServices.allVisiterTypeVisiterNum(parameterObject);
		
				Iterator it1 = all_Visiter_Type_VisiterNum.iterator();
				if(it1.hasNext()){
					SimpleMap sm = (SimpleMap) it1.next();
					totalPeopleNum += NumberUtil.parseInt(sm.getString("NUM"));
				}
				
				map.set("all_Visiter_Type_Num", all_Visiter_Type_Num);
				map.set("all_Visiter_Type_VisiterNum", all_Visiter_Type_VisiterNum);
			}
			
			request.setAttribute("totalPeopleTypeNum", totalPeopleTypeNum+"");
			request.setAttribute("totalPeopleNum", totalPeopleNum+"");
			request.setAttribute("all_Visiter_Type", all_Visiter_Type);
			
			
			
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}
		if(excelflag.equals("1")){
			return "/pu_visiter_Search.jsp?menu_code=" + parameterObject.getString("menu_code");
		}
		if(excelflag.equals("2")){
			return "reports/pu_report//pu_Visiter_Total_report.jsp";
		}
		return excelflag;
	}
	
	private String visiterTypeSearch(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();

		String flag =request.getParameter("flag");
		
		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		
		SimpleMap map = null;
		SimpleMap map1 = null;
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			String date = format.format(Calendar.getInstance().getTime());
			if(flag.equals("1")){
				parameterObject.set("Starttime", date);
				parameterObject.set("Endtime", date);
			}
			if(flag.equals("2")){
				parameterObject.set("StartDate", StartDate);
				parameterObject.set("EndDate", EndDate);
			}
			
			// retrieve attendance record list
			
			//得到所有参观者类别
			List all_Visiter_Type = visiterManagementServices.allVisiterType(parameterObject);
			int totalPeopleNum = 0;
			int totalPeopleTypeNum = 0;
			for(int i=0 ; i<all_Visiter_Type.size() ; i++){
				map = (SimpleMap) all_Visiter_Type.get(i);
				String VisiterTypeId = map.getString("CODE_ID");
				parameterObject.set("VisiterTypeId", VisiterTypeId);
				
				//人数
				List all_Visiter_Type_Num = visiterManagementServices.allVisiterTypeNum(parameterObject);
				
				Iterator it = all_Visiter_Type_Num.iterator();
				if(it.hasNext()){
					SimpleMap sm = (SimpleMap) it.next();
					totalPeopleTypeNum += NumberUtil.parseInt(sm.getString("NUM"));
				}
				
				//来访次数
				List all_Visiter_Type_VisiterNum = visiterManagementServices.allVisiterTypeVisiterNum(parameterObject);
		
				Iterator it1 = all_Visiter_Type_VisiterNum.iterator();
				if(it1.hasNext()){
					SimpleMap sm = (SimpleMap) it1.next();
					totalPeopleNum += NumberUtil.parseInt(sm.getString("NUM"));
				}
				
				map.set("all_Visiter_Type_Num", all_Visiter_Type_Num);
				map.set("all_Visiter_Type_VisiterNum", all_Visiter_Type_VisiterNum);
			}
			
			request.setAttribute("totalPeopleTypeNum", totalPeopleTypeNum+"");
			request.setAttribute("totalPeopleNum", totalPeopleNum+"");
			request.setAttribute("all_Visiter_Type", all_Visiter_Type);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterManagementView error ", e);
		}
		return "/pu_visiter_byType_Search.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	
	private String updateModifyFlag(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();

		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		
		String[] modifyFlag = request.getParameterValues("affirmno");
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			for(int i=0;i<modifyFlag.length;i++){
				String affirmorIdea = request.getParameter("affirmorIdea_"+modifyFlag[i]);
				parameterObject.set("affirmorIdea", affirmorIdea);
				parameterObject.set("modifyFlag", modifyFlag[i]);
				parameterObject.set("adminId", adminId);
				visiterManagementServices.UpdateModifyFlag(parameterObject);
				String applyNo = modifyFlag[i];
				String empid = request.getParameter("empid_"+modifyFlag[i]);
				parameterObject.set("empid", empid);
				String toEmail = visiterManagementServices.getVisiterApplyEmail(parameterObject);
				if(!toEmail.equals("") && toEmail!=null){				
					this.sendVisiterConfirmMail(
							"参观者申请已确认",
							applyNo,						 
							request.getParameter("APPLYNAME_"+modifyFlag[i]),
							request.getParameter("VISITER_DATE_"+modifyFlag[i]), 
							request.getParameter("VISITER_COME_TIME_"+modifyFlag[i]), 
							request.getParameter("VISITER_END_TIME_"+modifyFlag[i]),
							request.getParameter("VISIT_COUNT_"+modifyFlag[i]),
							toEmail,
							admin.getCpnyId()
							);
					}
			
			}
			// bind parameter
			
			
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateModifyFlag error ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "确认成功！");
		request.setAttribute("url","/puControlServlet?operation=visiterManagement&content=visiterManagementView&flag=1&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	private String deleteVisiterManagement(HttpServletRequest request) {	
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			visiterManagementServices.deleteVisiterManagement(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateModifyFlag error ", e);
		}
		return this.visiterManagementView(request);
	
	}
	
	private void sendVisiterConfirmMail(
			String title,
		    String applyNo,
			String applerId,
            String visiterComeDate,
			String visiterComeTime,
			String visiterEndTime,
			String peopleNum,
			String email,
			String cpny_id) throws Exception {

			SimpleMap inputData = new SimpleMap();
			
			inputData.set("编号：", applyNo);
			inputData.set("申请人：", applerId);
			inputData.set("来访日期：", visiterComeDate);
			inputData.set("到达时间：", visiterComeTime);
			inputData.set("离开时间：", visiterEndTime);
			inputData.set("来访人数：", peopleNum);
			
			mail.gaSendMail(inputData,cpny_id,email,title) ;

	}
	
	private void sendVisiterUpdateMail(
			String title,
		    String applyNo,
			String applerId,
            String visiterComeDate,
			String visiterComeTime,
			String visiterEndTime,
			String peopleNum,
			String email,
			String cpny_id) throws Exception {

			SimpleMap inputData = new SimpleMap();
			
			inputData.set("编号：", applyNo);
			inputData.set("申请人：", applerId);
			inputData.set("来访日期：", visiterComeDate);
			inputData.set("到达时间：", visiterComeTime);
			inputData.set("离开时间：", visiterEndTime);
			inputData.set("来访人数：", peopleNum);
			
			mail.gaSendMail(inputData,cpny_id,email,title) ;

	}
}




