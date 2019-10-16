package com.ait.ess.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.bean.ArDetailBack;
import com.ait.ar.business.ArServices;
import com.ait.ar.dao.AnnualBean;
import com.ait.core.config.Configuration;
import com.ait.core.config.ConfigurationException;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.bean.PageControl;
import com.ait.ess.business.EssServices;
import com.ait.ess.dao.EssArDAO;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.jdbc.core.SQLResult;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.util.SysCodeParser;
import com.ait.utils.ConnBean;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

public class EssViewCommand implements Command {

	private EssServices essServices;

	private ArServices arServices;

	private HrmServices hrmServices;

	private String content;

	private String empID;

	private String cpnyId;

	private EssSysparam essSysparam = null;

	private static final Logger logger = Logger
			.getLogger(SQLMapConfigManager.class);
	private Map<String, Object> map = new HashMap<String, Object>();

	public EssViewCommand() {
		essServices = new EssServices();
		arServices = new ArServices();
		hrmServices = HrmServices.getInstance();
		map.put("1", "通过");
		map.put("0", "未决裁");
		map.put("2", "否决");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		AdminBean admin = (AdminBean) session.getAttribute("admin");

		content = StringUtil.checkNull(request.getParameter("content"));
		empID = StringUtil.checkNull(request.getParameter("empID"), admin.getAdminID());
		cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		try {

			BasicInfo basic = (BasicInfo) hrmServices.retrieveBasicInfo(empID);
			request.setAttribute("basic", basic);
			this.putToolbarInfo(request);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
			ex.printStackTrace();
			return "/error.jsp";
		}
		
		/* 察看员工自己的年假信息 */
		if (content.equals("annual"))             
			return this.viewArAnnual(request, response);

		/* 查看有考勤权限的员工的事假信息 */
		else if (content.equals("affairleave"))
			return this.viewArAffairLeave(request, response);

		/* 员工查看加班 */
		else if (content.equals("otview"))
			return this.viewOvertimeInfo(request, response);
		/* 员工查看加班 */
		else if (content.equals("otviewExcel"))
			return this.viewOvertimeInfoExcel(request, response);
		
		/* 员工查看加班上限 */
		else if (content.equals("ottoplimitview"))
			return this.viewOverTimeTopLimitInfo(request, response);

		/* 员工查看休假 */
		else if (content.equals("leaveview"))
			return this.viewLeaveInfo(request, response);
		
		/* 员工查看考勤修改 */
		else if (content.equals("armodifyview"))
			return this.viewArModifyInfo(request, response);

		/* 员工申请加班 */
		else if (content.equals("otapply"))
			return this.viewOvertimeApply(request, response);

		/* 员工批量申请加班 */
		else if (content.equals("otapply_batch"))
			return this.viewOvertimeApplyBatch(request, response);

		/* 员工批量加班上限申请 */
		else if (content.equals("ot_toplimitapply_batch"))
			return this.viewOvertimeTopLimitApplyBatch(request, response);
		
		/* 员工申请休假 */
		else if (content.equals("leaveapply"))
			return this.viewLeaveApply(request, response);

		/* 员工批量申请休假 */
		else if (content.equals("leaveapply_batch"))
			return this.viewLeaveApplyBatch(request, response);

		/* 决裁者查看加班申请信息 */
		else if (content.equals("otaffirm"))
			return this.viewOvertimeAffirm(request, response);

		/* 决裁者设置决裁代理 */
		else if (content.equals("agent"))
			return this.viewAgent(request, response);
		
		/* 决裁者查看加班上限申请信息 */
		else if (content.equals("ottoplimitaffirm"))
			return this.viewOvertimeTopLimitAffirm(request, response);

		/* 决裁者查看休假申请信息 */
		else if (content.equals("leaveaffirm"))
			return this.viewLeaveAffirm(request, response); 
		/* 决裁者查看考勤修改信息 */
		else if (content.equals("armodifyaffirm"))
			return this.viewArModifyAffirm(request, response); 
		/* 人事查看加班确认信息 */  
		else if (content.equals("otconfirm"))
			return this.viewOvertimeConfirm(request, response);

		/* 人事查看休假确认信息 */
		else if (content.equals("leaveconfirm"))
			return this.viewLeaveConfirm(request, response);
		
		/* 支社考勤确认信息 */
		else if (content.equals("attendanceconfirm"))
			return this.viewAttendanceConfirm(request, response);
		
		/* 考勤明细修改确认信息 */
		else if (content.equals("ardetailconfirm"))
			return this.viewArDetailConfirm(request, response);
		
		/* 代决裁设置 */
		else if (content.equals("insteadAffirm"))
			return this.viewInsteadAffirm(request, response);
		
		/* 代委任设置 */
		else if (content.equals("insteadAffirmAppoint"))
			return this.viewInsteadAffirmAppoint(request, response);
		
		/*加班信息报表*/
		else if(content.equals("exportExcel"))	
			return this.overtimeViewExcel(request, response);
		/*加班确认报表*/
		else if(content.equals("exportConfirmExcel"))
			return this.overtimeConfirmExcel(request, response);
		/*休假确认报表*/
		else if(content.equals("exportLeaveConfirmExcel"))
			return this.leaveConfirmExcel(request, response);
		/*休假信息报表*/
		else if(content.equals("exportLeaveViewExcel"))
			return this.leaveViewExcel(request, response);
		/*加班决裁报表*/
		else if(content.equals("overtimeAffirmExcel"))
			return this.overtimeAffirmExcel(request, response);
		/*休假决裁报表*/
		else if(content.equals("leaveAffirmExcel"))
			return this.leaveAffirmExcel(request, response);
		else return "/error.jsp";
	}

	/**
	 * put toolbar information to request
	 * 
	 * @param request
	 * @throws GlRuntimeException
	 */
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

			int insertMenu = 0;
			int updateMenu = 0;
			int deleteMenu = 0;
			for (int i = 0; i < toolMenuList.size(); i++) {

				toolmenu = (ToolMenu) toolMenuList.get(i);

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
					" put toolbar information to request Exception.", e);
		}
	}

	/**
	 * 加班上限信息查看页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String viewOverTimeTopLimitInfo(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();

		try {
			String year = StringUtil.checkNull(request.getParameter("year"));
			String month = StringUtil.checkNull(request.getParameter("month"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));

			parameterObject.set("supervisor", adminId);
			parameterObject.put("planMonth", year + month);
			parameterObject.put("deptID", deptID);
			parameterObject.put("key", key);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = essServices
					.retrievePersonalOtTopLimitInfoListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List overTimeList = essServices.retrievePersonalOtTopLimitInfoList(
					parameterObject, currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				// && (adminId.equals(essOverTimeBean.getCreatedBy()) ||
				// adminId.equals(essOverTimeBean.getEmpId()))
				// , 且申请人或加班人是当前登录者时,可以进行删除
				if (essOverTimeBean.getAffirm_flag() == 0
						&& essOverTimeBean.getActivity() == 0) {
					// 决裁者与人事都未操作时
					essOverTimeBean.setOpFlag(3);
				}
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtTopLimitApply"));
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (overTimeList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("overTimeList", overTimeList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).error(ex.toString());
		}

		return "/ess_overtime_toplimit_info.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/**
	 * 加班信息查看页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String viewOvertimeInfo(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		try {
			String today = DateUtil.formatDate(
					Calendar.getInstance().getTime(), "yyyy-MM-dd");
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String qryType = StringUtil.checkNull(request
					.getParameter("qryType"), "0");
			///sDate = StringUtil.checkNull(request.getParameter("sDate"));
			///eDate = StringUtil.checkNull(request.getParameter("eDate"));
			SimpleMap parameterObjectDate = new SimpleMap();
			parameterObjectDate.set("cpnyId", cpnyId);
			if ("".equals(sDate) || "".equals(eDate)) {
				//SimpleMap smap = new SimpleMap();				
				//smap = (SimpleMap) essServices.getOtViewListFirstDate(parameterObjectDate);
				//if (smap.getString("MINDATE") == null) {
					sDate = arServices.getStartDateStr();
					eDate = arServices.getEndDateStr();
//				} else {
//					sDate = smap.getString("MINDATE");
//					eDate = smap.getString("MAXDATE");
//				}
			}

			parameterObject.setString("sDate", sDate);
			parameterObject.setString("eDate", eDate);
			parameterObject.setString("key", key);
			parameterObject.setString("deptID", deptID);
			parameterObject.setString("supervisor", adminId);
			parameterObject.setString("qryType", qryType);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style3.pagesize");
			int pageGroupSize = config.getInt("page.style3.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = essServices
					.retrievePersonalOtInfoListCnt(parameterObject);

			List overTimeList = essServices.retrievePersonalOtInfoList(
					parameterObject, currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				// && (adminId.equals(essOverTimeBean.getCreatedBy()) ||
				// adminId.equals(essOverTimeBean.getEmpId()))
				// , 且申请人或加班人是当前登录者时,可以进行删除
				if (!essSysparam.isOtAutoConfirm()
						&& (essOverTimeBean.getActivity() == 0 || essOverTimeBean
								.getAffirm_flag() == 2)) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(0); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getActivity() == 1) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(1); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getActivity() == 2
						&& essOverTimeBean.getAffirm_flag() == 1) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(2); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getActivity() == 2
						&& essOverTimeBean.getAffirm_flag() == 2) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(0); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getAffirm_flag() == 0
						&& essOverTimeBean.getActivity() == 0) {
					// 决裁者与人事都未操作时
					essOverTimeBean.setDel_flag(1);
					essOverTimeBean.setOpFlag(3);
				}
				if (essOverTimeBean.getAffirm_flag() == 0
						&& essOverTimeBean.getActivity() == 0) {
					essOverTimeBean.setDel_flag(1);// 可删除标志
				}
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtApply"));
				
				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTimeBean);
				
				essArDAO.getPersonOtTime(essOverTimeBean, arMonthObj, "arDate") ;
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (overTimeList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("overTimeList", overTimeList);
			request.setAttribute("qryType", qryType);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).error(ex.toString());
		}

		return "/ess_overtime_info.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/**
	 * 加班信息查看页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String viewOvertimeInfoExcel(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		try {
			String today = DateUtil.formatDate(
					Calendar.getInstance().getTime(), "yyyy-MM-dd");
			String sDate = StringUtil.checkNull(request.getParameter("sDate"),
					today);
			String eDate = StringUtil.checkNull(request.getParameter("eDate"),
					today);
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));

			parameterObject.setString("sDate", sDate);
			parameterObject.setString("eDate", eDate);
			parameterObject.setString("key", key);
			parameterObject.setString("deptID", deptID);
			parameterObject.setString("supervisor", adminId);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			List overTimeList = essServices
					.retrievePersonalOtInfoList(parameterObject);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				// && (adminId.equals(essOverTimeBean.getCreatedBy()) ||
				// adminId.equals(essOverTimeBean.getEmpId()))
				// , 且申请人或加班人是当前登录者时,可以进行删除
				if (essOverTimeBean.getAffirm_flag() == 0
						&& essOverTimeBean.getActivity() == 0) {
					// 决裁者与人事都未操作时
					essOverTimeBean.setOpFlag(3);
				}
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtApply"));
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (overTimeList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("overTimeList", overTimeList);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getLogger(getClass()).error(ex.toString());
		}

		return "/reports/ar_report/ar_overtime_info_excel.jsp";
	}

	/**
	 * 休假信息查看页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String viewLeaveInfo(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		try {
			String today = DateUtil.formatDate(
					Calendar.getInstance().getTime(), "yyyy-MM-dd");
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String qryType = StringUtil.checkNull(request
					.getParameter("qryType"), "0");
			
			String ss = StringUtil.checkNull(request.getParameter("stsType"));
			//新添加  出入查询
			String stsType = StringUtil.checkNull(request
					.getParameter("stsType"), "0");
			
			if ("".equals(sDate) || "".equals(eDate)) {
					sDate = arServices.getStartDateStr();
					eDate = arServices.getEndDateStr();
			}

			parameterObject.setString("sDate", sDate);
			parameterObject.setString("eDate", eDate);
			parameterObject.setString("key", key);
			parameterObject.setString("deptID", deptID);
			parameterObject.setString("supervisor", adminId);
			parameterObject.setString("qryType", qryType);
			parameterObject.setString("stsType", stsType);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style3.pagesize");
			int pageGroupSize = config.getInt("page.style3.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = essServices
					.retrievePersonalLeaveInfoListCnt(parameterObject);

			List leaveList = essServices.retrievePersonalLeaveInfoList(
					parameterObject, currentPage, pageSize);
			EssLeaveBean essLeaveBean = new EssLeaveBean();
			for (int i = 0; i < leaveList.size(); i++) {
				essLeaveBean = (EssLeaveBean) leaveList.get(i);
				// && (adminId.equals(essLeaveBean.getCreatedBy()) ||
				// adminId.equals(essLeaveBean.getEmpId()))
				// , 且申请人或加班人是当前登录者时,可以进行删除
				if (!essSysparam.isLeaveAutoConfirm()
						&& (essLeaveBean.getActivity() == 0 || essLeaveBean
								.getAffirm_flag() == 2)) {
					essLeaveBean.setDel_flag(0);
					essLeaveBean.setOpFlag(0); // 设置人事决裁标示
				}
				if (!essSysparam.isLeaveAutoConfirm()
						&& essLeaveBean.getActivity() == 1) {
					essLeaveBean.setDel_flag(0);
					essLeaveBean.setOpFlag(1); // 设置人事决裁标示
				}
				if (!essSysparam.isLeaveAutoConfirm()
						&& essLeaveBean.getActivity() == 2
						&& essLeaveBean.getAffirm_flag() == 1) {
					essLeaveBean.setDel_flag(0);
					essLeaveBean.setOpFlag(2); // 设置人事决裁标示
				}
				if (!essSysparam.isLeaveAutoConfirm()
						&& essLeaveBean.getActivity() == 2
						&& essLeaveBean.getAffirm_flag() == 2) {
					essLeaveBean.setDel_flag(0);
					essLeaveBean.setOpFlag(0); // 设置人事决裁标示
				}
				if (!essSysparam.isLeaveAutoConfirm()&& essLeaveBean.getAffirm_flag()!= null
						&& essLeaveBean.getAffirm_flag() == 0
						&& essLeaveBean.getActivity() == 0) {
					essLeaveBean.setDel_flag(1);
					// 决裁者与人事都未操作时
					essLeaveBean.setOpFlag(3);
				}
				if ( essLeaveBean.getAffirm_flag()!= null  && essLeaveBean.getAffirm_flag() == 0
						&& essLeaveBean.getActivity() == 0) {
					essLeaveBean.setDel_flag(1);
				}
				essLeaveBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essLeaveBean.getLeaveNo().intValue(),
								"LeaveApply"));
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (leaveList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("leaveList", leaveList);
			request.setAttribute("qryType", qryType);
			request.setAttribute("stsType", stsType);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}
		return "/ess_leave_info.jsp?menu_code="
				+ request.getParameter("menu_code");
	}
	
	/**
	 * 考勤修改信息查看页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String viewArModifyInfo(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		try {
			String today = DateUtil.formatDate(
					Calendar.getInstance().getTime(), "yyyy-MM-dd");
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String qryType = StringUtil.checkNull(request
					.getParameter("qryType"), "0");
			if ("".equals(sDate) || "".equals(eDate)) {
					sDate = arServices.getStartDateStr();
					eDate = arServices.getEndDateStr();
			}

			parameterObject.setString("sDate", sDate);
			parameterObject.setString("eDate", eDate);
			parameterObject.setString("key", key);
			parameterObject.setString("deptID", deptID);
			parameterObject.setString("supervisor", adminId);
			parameterObject.setString("qryType", qryType);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style3.pagesize");
			int pageGroupSize = config.getInt("page.style3.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = essServices
					.retrievePersonalArModifyInfoListCnt(parameterObject);

			List arModifyList = essServices.retrievePersonalArModifyInfoList(
					parameterObject, currentPage, pageSize);
			ArDetailBack arModfiyBean = new ArDetailBack();
			for (int i = 0; i < arModifyList.size(); i++) {
				arModfiyBean = (ArDetailBack) arModifyList.get(i);
				
//				// && (adminId.equals(essLeaveBean.getCreatedBy()) ||
//				// adminId.equals(essLeaveBean.getEmpId()))
//				// , 且申请人或加班人是当前登录者时,可以进行删除
//				if (!essSysparam.isLeaveAutoConfirm()
//						&& (essLeaveBean.getActivity() == 0 || essLeaveBean
//								.getAffirm_flag() == 2)) {
//					essLeaveBean.setDel_flag(0);
//					essLeaveBean.setOpFlag(0); // 设置人事决裁标示
//				}
//				if (!essSysparam.isLeaveAutoConfirm()
//						&& essLeaveBean.getActivity() == 1) {
//					essLeaveBean.setDel_flag(0);
//					essLeaveBean.setOpFlag(1); // 设置人事决裁标示
//				}
//				if (!essSysparam.isLeaveAutoConfirm()
//						&& essLeaveBean.getActivity() == 2
//						&& essLeaveBean.getAffirm_flag() == 1) {
//					essLeaveBean.setDel_flag(0);
//					essLeaveBean.setOpFlag(2); // 设置人事决裁标示
//				}
//				if (!essSysparam.isLeaveAutoConfirm()
//						&& essLeaveBean.getActivity() == 2
//						&& essLeaveBean.getAffirm_flag() == 2) {
//					essLeaveBean.setDel_flag(0);
//					essLeaveBean.setOpFlag(0); // 设置人事决裁标示
//				}
//				if (!essSysparam.isLeaveAutoConfirm()
//						&& essLeaveBean.getAffirm_flag() == 0
//						&& essLeaveBean.getActivity() == 0) {
//					essLeaveBean.setDel_flag(1);
//					// 决裁者与人事都未操作时
//					essLeaveBean.setOpFlag(3);
//				}
//				if (essLeaveBean.getAffirm_flag() == 0
//						&& essLeaveBean.getActivity() == 0) {
//					essLeaveBean.setDel_flag(1);
//				}

				arModfiyBean.setAffirmorList((ArrayList) essArDAO.getArModifyAffirmorList(arModfiyBean.getPkNo1()));
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (arModifyList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("arModifyList", arModifyList);
			request.setAttribute("qryType", qryType);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}
		return "/ess_ar_modify_info.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/* 加班申请页 */
	private String viewOvertimeApply(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			essOverTimeBean.setEmpId(StringUtil.checkNull(request
					.getParameter("empId"), empID));
			essOverTimeBean.setChineseName((String) hrmServices
					.getChineseNameByEmpId(essOverTimeBean.getEmpId()));
			essOverTimeBean.setCreateDate(new java.text.SimpleDateFormat(
					"yyyy-MM-dd").format(new GregorianCalendar().getTime()));
			essOverTimeBean.setOtDate(StringUtil.checkNull(request
					.getParameter("otDate"), essOverTimeBean.getCreateDate()));
			essOverTimeBean.setOtFromTime(StringUtil.checkNull(request
					.getParameter("fromTime")));
			essOverTimeBean.setOtToTime(StringUtil.checkNull(request
					.getParameter("toTime")));
			essOverTimeBean.setOtNextDays(NumberUtil.parseInt(request
					.getParameter("otNextDays"), 0));
			essOverTimeBean.setOtDeduct(NumberUtil.parseDouble(request
					.getParameter("otDeduct"), 0));
			essOverTimeBean.setOtTypeCode(StringUtil.checkNull(request
					.getParameter("otTypeCode")));
			essOverTimeBean.setOtSort(StringUtil.checkNull(request
					.getParameter("otsort"), "normal"));
			essOverTimeBean.setOtSortCode(StringUtil.checkNull(request
					.getParameter("otSortCode")));
			essOverTimeBean.setOtRemark(StringUtil.toCN(request
					.getParameter("otRemark")));
			// private String otSort = "normal"; //加班类别: normal 普通申请; emergency
			// 紧急申请; overmax 超时申请
			if (essOverTimeBean.getOtSort().equals("emergency"))
				essOverTimeBean.setAffirmorList((ArrayList) essServices
						.getAffirmorList(essOverTimeBean.getEmpId(),
								"TOTApply", 99, essSysparam.isContainsOwner()));
			else if (essOverTimeBean.getOtSort().equals("overmax"))
				essOverTimeBean.setAffirmorList((ArrayList) essServices
						.getAffirmorList(essOverTimeBean.getEmpId(),
								"SOTApply", 99, essSysparam.isContainsOwner()));
			else
				essOverTimeBean.setAffirmorList((ArrayList) essServices
						.getAffirmorList(essOverTimeBean.getEmpId(), "OtApply",
								99, essSysparam.isContainsOwner()));

			request.setAttribute("errorMsg", "");
			request.setAttribute("deductList", NumberUtil
					.getDoubleSerialList(10));
			if (essOverTimeBean.getOtSort().equals("emergency"))
				request
						.setAttribute(
								"applyTypeVector",
								SysCodeParser
										.getCode(
												Configuration
														.getInstance()
														.getString(
																"/configuration/em2/syscode-parent/otapply-emergency",
																""), 1));
			else if (essOverTimeBean.getOtSort().equals("overmax"))
				request
						.setAttribute(
								"applyTypeVector",
								SysCodeParser
										.getCode(
												Configuration
														.getInstance()
														.getString(
																"/configuration/em2/syscode-parent/otapply-overmax",
																""), 1));
			else
				request
						.setAttribute(
								"applyTypeVector",
								SysCodeParser
										.getCode(
												Configuration
														.getInstance()
														.getString(
																"/configuration/em2/syscode-parent/otapply",
																""), 1));
			String otApplySortPCode = Configuration.getInstance().getString(
					"/configuration/em2/syscode-parent/otsort", "");
			request.setAttribute("applySortVector", SysCodeParser.getCode(
					otApplySortPCode, 1));
			request.setAttribute("essOverTimeBean", essOverTimeBean);
			return "/ess_overtime_apply.jsp";
		} catch (Exception e) {
			throw new GlRuntimeException("", e);
		}
	}

	/**
	 * view overtime apply by batch
	 * 
	 * @param request
	 * @param response
	 * @return URL
	 */
	private String viewOvertimeApplyBatch(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute(
				"admin"));
		String adminId = admin.getAdminID();
		SimpleMap parameterObject;

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style3.pagesize");
			int pageGroupSize = config.getInt("page.style3.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Calendar date = Calendar.getInstance();

			parameterObject = ObjectBindUtil.getData(request);

			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpnyId", admin.getCpnyId());
			parameterObject.set("applyDate", dateFormat.format(date.getTime()));
			parameterObject.set("ar_month", essServices.retrieveApplyArMonth(parameterObject));

			// 取得数据行数
			int resultCount = essServices
					.retrieveApplyOTPersonCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List applyList = essServices.retrieveApplyOTPerson(parameterObject,
					currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			essOverTimeBean.setOtDate(dateFormat.format(date.getTime()));
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				essOverTimeBean = (EssOverTimeBean) applyList.get(i);
				logger.debug("essOverTimeBean : " + essOverTimeBean.getPerson_id()) ;
				
				List affirmorList = null;
				if(!admin.getCpnyId().equals("60000000")){
				     affirmorList = essServices.getAffirmorList(essOverTimeBean
						.getPerson_id(), "OtApply", 99, essSysparam
						.isContainsOwner());
				}else{
					 affirmorList = essServices.getAffirmorListDIY(essOverTimeBean
							.getPerson_id(), "OtApply", 99, essSysparam
							.isContainsOwner());
				}
				
				EssAffirmor essAffirmor = new EssAffirmor();
				String AffirmData = "";
				for (int j = 0; j < affirmorList.size(); j++) {
					essAffirmor = (EssAffirmor) affirmorList.get(j);
//					AffirmData += essAffirmor.getAffirmLevel()
//							+ essAffirmor.getAffirmorName() + "<br>";
					AffirmData += essAffirmor.getAffirmLevelOriginal()
					+ essAffirmor.getAffirmorName() + "<br>";
				}
				if (AffirmData.equals("")) {
					essOverTimeBean.setAffirmData(null);
				} else {
					essOverTimeBean.setAffirmData(AffirmData.substring(0,
							AffirmData.length() - 4));
				}
				
				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTimeBean);
				
				if (this.essSysparam.getOtApplyMaxHours()) {
					essOverTimeBean.setLimit_ot(essArDAO
							.getOtMaxHours(essOverTimeBean,arMonthObj));
				} else {
					essOverTimeBean.setLimit_ot(-1);
				}

				essArDAO.getPersonOtTime(essOverTimeBean,arMonthObj, "arMonth") ;
			}

			request.setAttribute("applyList", applyList);
			request.setAttribute("deductList", NumberUtil
					.getDoubleSerialList(24));
			request.setAttribute("admin", adminId);
			request.setAttribute("key", StringUtil.checkNull(request
					.getParameter("key")));
			request.setAttribute("deptID", StringUtil.checkNull(request
					.getParameter("deptID")));
			request.setAttribute("sysDateTime", dateFormat1.format(date
					.getTime()));

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			logger.error("View employee apply overtime by batch Exception. "
					+ e);
			throw new GlRuntimeException(
					"View employee apply overtime by batch Exception. ", e);
		}
		return "/ess_overtime_apply_batch.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/**
	 * view overtime toplimit apply by batch
	 * 
	 * @param request
	 * @param response
	 * @return URL
	 */
	private String viewOvertimeTopLimitApplyBatch(HttpServletRequest request,
			HttpServletResponse response) {

		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap parameterObject;

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);

			List planMonthList = essServices
					.retrieveApplyOTTopLimitMonthList(parameterObject);

			// 取得数据行数
			int resultCount = essServices
					.retrieveApplyOTTopLimitPersonCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List applyList = essServices.retrieveApplyOTTopLimitPerson(
					parameterObject, currentPage, pageSize);

			request.setAttribute("applyList", applyList);
			request.setAttribute("admin", adminId);
			request.setAttribute("planMonth", parameterObject
					.getString("planMonth"));
			request.setAttribute("planMonthList", planMonthList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			logger
					.error("View employee apply overtime toplimit by batch Exception. "
							+ e);
			throw new GlRuntimeException(
					"View employee apply overtime toplimit by batch Exception. ",
					e);
		}

		return "/ess_overtime_toplimit_apply_batch.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	/**
	 * view instead_affirm
	 * 
	 * @param request
	 * @param response
	 * @return URL
	 */
	private String viewInsteadAffirm(HttpServletRequest request,
			HttpServletResponse response) {

		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap parameterObject = new SimpleMap();

		try {

			parameterObject.set("supervisor", adminId);
			Object insteadAffirm = essServices
					.retrieveInsteadAffirm(parameterObject);

			request.setAttribute("insteadAffirm", insteadAffirm);
			request.setAttribute("admin", adminId);

		} catch (Exception e) {

			logger.error("View instead_affirm Exception. " + e);
			throw new GlRuntimeException("View instead_affirm Exception. ", e);
		}

		return "/ess_instead_affirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/**
	 * 委任页面
	 * 
	 * @param request
	 * @param response
	 * @return URL
	 */
	private String viewInsteadAffirmAppoint(HttpServletRequest request,
			HttpServletResponse response) {

		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap parameterObject = new SimpleMap();

		try {

			parameterObject.set("supervisor", adminId);
			parameterObject.set("appointType", request.getParameter("appointType"));
			Object insteadAffirm = essServices
					.retrieveInsteadAffirmAppoint(parameterObject);

			request.setAttribute("insteadAffirm", insteadAffirm);
			request.setAttribute("appoint", request.getParameter("appointType"));
			request.setAttribute("admin", adminId);

		} catch (Exception e) {

			logger.error("View instead_affirm Exception. " + e);
			throw new GlRuntimeException("View instead_affirm Exception. ", e);
		}

		return "/ess_instead_affirm_appoint.jsp?menu_code="
				+ request.getParameter("menu_code");
	}
	
	/* 休假申请页 */
	private String viewLeaveApply(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			SimpleMap sm = new SimpleMap();
			String empId = "";
			String chinesename = "";
			HttpSession session = request.getSession(false);
			AdminBean admin = (AdminBean) session.getAttribute("admin");
			String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			EssLeaveBean essLeaveBean = new EssLeaveBean();
			List list = essServices.getPerson_id(StringUtil.checkNull(request
					.getParameter("person_id"), admin.getAdminID()));
			for (int i = 0; i < list.size(); i++) {
				sm = (SimpleMap) list.get(i);
				empId = sm.getString("EMPID");
				chinesename = sm.getString("CHINESENAME");

			}
			essLeaveBean.setEmpId(empId);
			essLeaveBean.setChineseName(chinesename);
			essLeaveBean.setCreateDate(new java.text.SimpleDateFormat(
					"yyyy-MM-dd").format(new GregorianCalendar().getTime()));

			essLeaveBean.setLeaveFromDate(essLeaveBean.getCreateDate());
			essLeaveBean.setLeaveToDate(essLeaveBean.getCreateDate());
			if("59000000".equals(admin.getCpnyId())){
				essLeaveBean.setLeaveFromTime("08:30");
				essLeaveBean.setLeaveToTime("17:30");
			}else{
				essLeaveBean.setLeaveFromTime("08:00");
				essLeaveBean.setLeaveToTime("17:00");
			}
			
			essLeaveBean.setLeaveTypeCode(StringUtil.checkNull(request
					.getParameter("leaveTypeCode")));
			essLeaveBean.setLeaveReason(StringUtil.checkNull(request
					.getParameter("leaveReason")));
			essLeaveBean.setPerson_id(StringUtil.checkNull(request
					.getParameter("person_id"), admin.getPersonId()));
			essLeaveBean.setApplyLeaveType("ApplyLeaveType01");

			/*
			 * // get affirmor list essLeaveBean.setAffirmorList((ArrayList)
			 * essServices.getAffirmorList(essLeaveBean.getEmpId(),
			 * "LeaveApply"));
			 * 
			 * //取本月倒休天数
			 * essLeaveBean.setLeaveThisMonth(essServices.getTurnHolidays(essLeaveBean.getEmpId(),
			 * "this")); //取下月倒休天数
			 * essLeaveBean.setLeaveNextMonth(essServices.getTurnHolidays(essLeaveBean.getEmpId(),
			 * "next"));
			 */
			System.out.print(request.getParameter("leaveFromDate"));
			AnnualBean annualBean = new AnnualBean();
			Annual annual = (Annual) annualBean.empAnnualLeft(StringUtil
					.checkNull(request.getParameter("person_id"), admin
							.getAdminID()), request.getParameter("leaveFromDate")); /// 201890103修改 去假期开始日期
							///new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			double allTotVaction = annual.getAnnualHours();
			double lastTotVaction = annual.getAnnualHoursLeft();//可用年假
			request.setAttribute("errorMsg", "");
			request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("allTotVaction", allTotVaction);
			request.setAttribute("lastTotVaction", lastTotVaction);
			// String leaveApplyTypePCode =
			// Configuration.getInstance().getString("/configuration/em2/syscode-parent/leaveapply",
			// "");
			// request.setAttribute("applyTypeVector",
			// SysCodeParser.getCode(leaveApplyTypePCode, 1));
			request.setAttribute("essLeaveBean", essLeaveBean);
			request.setAttribute("cpnyId", cpnyId);

			// 有薪病假
			SimpleMap simpleMap = (SimpleMap) annualBean
					.retriveSickLeave(StringUtil.checkNull(request
							.getParameter("person_id"), admin.getAdminID()));
			request.setAttribute("totalSickleave", simpleMap
					.getDouble("totalSickLeaveHours"));
			request.setAttribute("sickLeaveLeft", simpleMap
					.getDouble("sickLeaveLeft"));
			if (simpleMap.getString("cpnyId") == admin.getCpnyId()
					|| simpleMap.getString("cpnyId").equals(admin.getCpnyId())) {
				request.setAttribute("flag", "true");
			} else {
				request.setAttribute("flag", "false");
			}
			
			// 团聚假
			SimpleMap simpleMap1 = (SimpleMap) annualBean
					.retriveReuniteLeave(StringUtil.checkNull(request
							.getParameter("person_id"), admin.getAdminID()));
			request.setAttribute("totalReuniteleave", simpleMap1
					.getDouble("totalReuniteLeaveHours"));
			request.setAttribute("reuniteLeaveLeft", simpleMap1
					.getDouble("reuniteLeaveLeft"));//可用团聚假
//			if (simpleMap1.getString("cpnyId") == admin.getCpnyId()
//					|| simpleMap1.getString("cpnyId").equals(admin.getCpnyId())) {
//				request.setAttribute("flagReunite", "true");
//			} else {
//				request.setAttribute("flagReunite", "false");
//			}
			// 上年剩余年假
			SimpleMap simpleMap2 = (SimpleMap) annualBean
					.lastAnnualVacation(StringUtil.checkNull(request
							.getParameter("person_id"), admin.getAdminID()));
			request.setAttribute("totalLastAnnualVacation", simpleMap2
					.getDouble("totalLastAnnualVacation"));
			request.setAttribute("lastAnnualVacationLeft", simpleMap2
					.getDouble("lastAnnualVacationLeft"));//可用上年剩余年假
			request.setAttribute("adminId", admin.getAdminID());

		} catch (Exception e) {
			throw new GlRuntimeException("", e);
		}
		
		return "/ess_leave_apply.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/**
	 * view leave apply by batch
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	public String viewLeaveApplyBatch(HttpServletRequest request,
			HttpServletResponse response) {
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		List deptList = new ArrayList();

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();

			parameterObject = ObjectBindUtil.getData(request);

			parameterObject.set("supervisor", adminId);
			parameterObject
					.set("apply_date", dateFormat.format(date.getTime()));

			// 得到决裁者的部门
			deptList = essServices.retrieveAffirmDeptList(parameterObject);

			// 取得数据行数
			int resultCount = essServices
					.retrieveApplyPersonCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List applyList = essServices.retrieveApplyLeavePerson(
					parameterObject, currentPage, pageSize);
			EssLeaveBean essLeaveBean = new EssLeaveBean();
			for (int i = 0; i < applyList.size(); i++) {
				essLeaveBean = (EssLeaveBean) applyList.get(i);
				AnnualBean annualBean = new AnnualBean();
				SimpleMap vactionList = new SimpleMap();
				Annual annual = (Annual) annualBean.empAnnualLeft(essLeaveBean
						.getPerson_id(), new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				double allTotVaction = annual.getAnnualHours();
				double lastTotVaction = annual.getAnnualHoursLeft();
				vactionList.set("allTotVaction", StringUtil
						.checkNull(allTotVaction + "天"));
				vactionList.set("lastTotVaction", StringUtil
						.checkNull(lastTotVaction + "天"));
				List list = new ArrayList();
				list.add(vactionList);
				essLeaveBean.setVactionList(list);

			}

			request.setAttribute("applyList", applyList);
			request.setAttribute("cpnyId", cpnyId);
			request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("deptList", deptList);
			request.setAttribute("admin", adminId);
			request.setAttribute("key", StringUtil.checkNull(request
					.getParameter("key")));
			request.setAttribute("deptID", StringUtil.checkNull(request
					.getParameter("deptID")));

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception e) {
			e.printStackTrace();

			logger.error("View employee apply leave by batch Exception. " + e);
			throw new GlRuntimeException(
					"View employee apply leave by batch Exception. ", e);
		}

		return "/ess_leave_apply_batch.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/* 加班决裁页 */
	private String viewOvertimeAffirm(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		List deptList = new ArrayList();

		try {
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			// int maxLevel =
			// Integer.parseInt(essServices.getLogOnPersonAffirmMaxLevel(adminId));

			// parameterObject.put("MAXLEVEL", maxLevel);
			parameterObject.set("supervisor", adminId);
			parameterObject.put("sDate", sDate);
			parameterObject.put("eDate", eDate);
			parameterObject.put("deptID", deptID);
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style4.pagesize");
			int pageGroupSize = config.getInt("page.style4.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 得到决裁者的部门
			deptList = essServices.retrieveAffirmDeptList(parameterObject);

			// 取得数据行数
			int resultCount = essServices
					.retrieveOtAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List overTimeList = essServices.retrieveOtAffirmList(
					parameterObject, currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				parameterObject.setInt("applyNo", essOverTimeBean.getOtNo());
				parameterObject.setInt("level", essOverTimeBean
						.getAffirmLevel());
				// essOverTimeBean.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
				// ;
				if ((essOverTimeBean.getNext_flag() == 0
						&& essOverTimeBean.getUp_flag() == 1 && essOverTimeBean
						.getActivity() != 2)
						|| essOverTimeBean.getMaxLevel_flag() == 1) {
					// 上级决裁者通过, 且人事未否决时才能进行操作
					if (essOverTimeBean.getAffirm_flag() == 0)
						essOverTimeBean.setOpFlag(0);
					else if (essSysparam.isOtModifiedAfterAffirm()
							&& essOverTimeBean.getAffirm_flag() == 1)
						essOverTimeBean.setOpFlag(2);
					else if (essSysparam.isOtModifiedAfterAffirm()
							&& essOverTimeBean.getAffirm_flag() == 2)
						essOverTimeBean.setOpFlag(1);
				}
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtApply"));
				
				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTimeBean);
				
				// 取得个人已申请小时数
				essArDAO.getPersonOtTime(essOverTimeBean, arMonthObj, "arDate") ;
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (overTimeList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptID", deptID);
			request.setAttribute("deptList", deptList);
			request.setAttribute("key", key);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("overTimeList", overTimeList);
			request.setAttribute("otLengthList", NumberUtil
					.getDoubleSerialList(24));

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception ex) {
			logger.error("View employee overtime affirm Exception. " + ex);
			throw new GlRuntimeException(
					"View employee overtime affirm Exception. ", ex);
		}
		return "/ess_overtime_affirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}


	/* 决裁代理 */
	private String viewAgent(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		List deptList = new ArrayList();

		try {
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			// int maxLevel =
			// Integer.parseInt(essServices.getLogOnPersonAffirmMaxLevel(adminId));

			// parameterObject.put("MAXLEVEL", maxLevel);
			parameterObject.set("supervisor", adminId);
			parameterObject.put("sDate", sDate);
			parameterObject.put("eDate", eDate);
			parameterObject.put("deptID", deptID);
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style4.pagesize");
			int pageGroupSize = config.getInt("page.style4.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 得到决裁者的部门
			deptList = essServices.retrieveAffirmDeptList(parameterObject);

			// 取得数据行数
			int resultCount = essServices
					.retrieveOtAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List overTimeList = essServices.retrieveOtAffirmList(
					parameterObject, currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				parameterObject.setInt("applyNo", essOverTimeBean.getOtNo());
				parameterObject.setInt("level", essOverTimeBean
						.getAffirmLevel());
				// essOverTimeBean.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
				// ;
				if ((essOverTimeBean.getNext_flag() == 0
						&& essOverTimeBean.getUp_flag() == 1 && essOverTimeBean
						.getActivity() != 2)
						|| essOverTimeBean.getMaxLevel_flag() == 1) {
					// 上级决裁者通过, 且人事未否决时才能进行操作
					if (essOverTimeBean.getAffirm_flag() == 0)
						essOverTimeBean.setOpFlag(0);
					else if (essSysparam.isOtModifiedAfterAffirm()
							&& essOverTimeBean.getAffirm_flag() == 1)
						essOverTimeBean.setOpFlag(2);
					else if (essSysparam.isOtModifiedAfterAffirm()
							&& essOverTimeBean.getAffirm_flag() == 2)
						essOverTimeBean.setOpFlag(1);
				}
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtApply"));
				
				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTimeBean);
				
				// 取得个人已申请小时数
				essArDAO.getPersonOtTime(essOverTimeBean, arMonthObj, "arDate") ;
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (overTimeList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptID", deptID);
			request.setAttribute("deptList", deptList);
			request.setAttribute("key", key);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("overTimeList", overTimeList);
			request.setAttribute("otLengthList", NumberUtil
					.getDoubleSerialList(24));

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception ex) {
			logger.error("View employee overtime affirm Exception. " + ex);
			throw new GlRuntimeException(
					"View employee overtime affirm Exception. ", ex);
		}
		return "/ess_overtime_affirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}
	
	/* 加班上限决裁页 */
	private String viewOvertimeTopLimitAffirm(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();

		try {
			String year = StringUtil.checkNull(request.getParameter("year"));
			String month = StringUtil.checkNull(request.getParameter("month"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			parameterObject.set("supervisor", adminId);
			parameterObject.put("planMonth", year + month);
			parameterObject.put("deptID", deptID);
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = essServices
					.retrieveOtTopLimitAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List overTimeList = essServices.retrieveOtTopLimitAffirmList(
					parameterObject, currentPage, pageSize);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			for (int i = 0; i < overTimeList.size(); i++) {
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
				parameterObject.setInt("applyNo", essOverTimeBean.getOtNo());
				parameterObject.setInt("level", essOverTimeBean
						.getAffirmLevel());
				essOverTimeBean.setNext_flag(essServices
						.retrieveApplyAffirmNextFlag(parameterObject));
				if (essOverTimeBean.getNext_flag() == 0
						&& essOverTimeBean.getActivity() != 2) {
					// 上级决裁者通过, 且人事未否决时才能进行操作
					if (essOverTimeBean.getAffirm_flag() == 0)
						essOverTimeBean.setOpFlag(0);
					else if (essSysparam.isOtModifiedAfterAffirm()
							&& essOverTimeBean.getAffirm_flag() == 1)
						essOverTimeBean.setOpFlag(2);
					else if (essSysparam.isOtModifiedAfterAffirm()
							&& essOverTimeBean.getAffirm_flag() == 2)
						essOverTimeBean.setOpFlag(1);
				}
				essOverTimeBean.setAffirmorList((ArrayList) essArDAO
						.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
								"OtTopLimitApply"));
				// 取得个人加班参考数据
				// essOverTimeBean.setOtDetail(essArDAO.getOverTimeDetail(essOverTimeBean));
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (overTimeList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("overTimeList", overTimeList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception ex) {
			logger.error("View employee overtime affirm Exception. " + ex);
			throw new GlRuntimeException(
					"View employee overtime top limit affirm Exception. ", ex);
		}
		return "/ess_overtime_toplimit_affirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/* 加班确认页 */
	public String viewOvertimeConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String sDate = "";
			String eDate = "";
			List overTimeList = new ArrayList();

			sDate = StringUtil.checkNull(request.getParameter("sDate"));
			eDate = StringUtil.checkNull(request.getParameter("eDate"));
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("cpnyId", cpnyId);
			if (sDate.equals("") || eDate.equals("")) {
				//SimpleMap smap = new SimpleMap();				
				//smap = (SimpleMap) essServices.getOtConfirmListFirstDate(parameterObject);
				//if (smap.getString("MINDATE") == null) {
					sDate = arServices.getStartDateStr();
					eDate = arServices.getEndDateStr();
					
				//} else {
				//	sDate = smap.getString("MINDATE");
				//	eDate = smap.getString("MAXDATE");
				//}
			}
			AdminBean admin = (AdminBean) request.getSession(false)
					.getAttribute("admin");
			String cpnyId = admin.getCpnyId();

			String key = request.getParameter("key") != null ? StringUtil
					.toCN(request.getParameter("key")) : "";
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			if (deptID.equals(""))
				deptID = cpnyId;
			int currentPage = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("currentPage"), "1"));
			String s_qryType = request.getParameter("qryType");
			int qryType = s_qryType == null ? 0 : Integer.parseInt(s_qryType);
			PageControl pageControl = new PageControl();
			pageControl.setCurrentPage(currentPage);
			essServices.setPageControl(pageControl);
			overTimeList = essServices.getOtConfirmList(sDate, eDate, key,
					qryType, deptID);
			pageControl = essServices.getPageControl();
			Logger.getLogger(getClass()).debug(
					"PageSize : " + pageControl.getPageSize());
			Map statusMap = new HashMap();
			statusMap.put("0", "未决裁");
			statusMap.put("1", "已通过");
			statusMap.put("2", "已否决");

			Map colorMap = new HashMap();
			colorMap.put("0", "#990099");
			colorMap.put("1", "#00CC00");
			colorMap.put("2", "#CC0000");

			// AdminBean admin =(AdminBean)
			// request.getSession().getAttribute("admin");
			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");
			Map confirmMap = new HashMap();
			confirmMap.put("0", message1);
			confirmMap.put("1", message2);
			confirmMap.put("2", message3);

			request.setAttribute("pageControl", pageControl);
			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("s_qryType", s_qryType);
			request.setAttribute("key", key);
			request.setAttribute("deptID", deptID);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("confirmMap", confirmMap);
			request.setAttribute("overTimeList", overTimeList);
			request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("deductList", NumberUtil
					.getDoubleSerialList(10));

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}

		return "/ess_overtime_confirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}

	/* 休假决裁页面 */
	private String viewLeaveAffirm(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		List deptList = new ArrayList();

		try {
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			parameterObject.put("sDate", sDate);
			parameterObject.put("eDate", eDate);
			parameterObject.put("deptID", deptID);
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);
			
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style4.pagesize");
			int pageGroupSize = config.getInt("page.style4.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			parameterObject.set("supervisor", adminId);
			int resultCount = essServices
					.retrieveLeaveAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			// 得到决裁者的部门
			deptList = essServices.retrieveAffirmDeptList(parameterObject);

			List leaveList = essServices.retrieveLeaveAffirmList(parameterObject, currentPage, pageSize);
			
			for (int i = 0; i < leaveList.size(); i++) {
				EssLeaveBean leave = (EssLeaveBean) leaveList.get(i);
				parameterObject.setInt("applyNo", leave.getLeaveNo());
				parameterObject.setInt("level", leave.getAffirmLevel());
				// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
				// ;
				// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
				if ((leave.getNext_flag() == 0 && leave.getUp_flag() == 1 && leave
						.getActivity() != 2)
						|| leave.getMaxLevel_flag() == 1) {
					// 决裁状态为"待决裁"
					if (leave.getAffirm_flag() == 0)
						// 可进行"通过"和"否决"
						leave.setOpFlag(0);
					// 决裁后可进行修改并且状态为"已通过"
					else if (essSysparam.isLeaveModifiedAfterAffirm()
							&& leave.getAffirm_flag() == 1)
						// 可进行"否决"
						leave.setOpFlag(2);
					// 决裁后可进行修改并且状态为"否决"
					else if (essSysparam.isLeaveModifiedAfterAffirm()
							&& leave.getAffirm_flag() == 2)
						// 可进行"通过"
						leave.setOpFlag(1);
				}
				leave.setAffirmorList((ArrayList) essArDAO.getAffirmorList(
						leave.getLeaveNo(), "LeaveApply"));
				
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (leaveList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptID", deptID);
			request.setAttribute("deptList", deptList);
			request.setAttribute("key", key);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("leaveList", leaveList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}

		return "/ess_leave_affirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}
	
	/* 考勤修改决裁页面 */
	private String viewArModifyAffirm(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		String cpnyId = admin.getCpnyId();
		SimpleMap parameterObject = new SimpleMap();
		List deptList = new ArrayList();

		try {
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request
					.getParameter("qryType"), "2"));

			parameterObject.put("sDate", sDate);
			parameterObject.put("eDate", eDate);
			parameterObject.put("deptID", deptID);
			parameterObject.put("key", key);
			parameterObject.put("qryType", qryType);
			parameterObject.put("cpnyId", cpnyId);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style4.pagesize");
			int pageGroupSize = config.getInt("page.style4.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			parameterObject.set("supervisor", adminId);
			int resultCount = essServices
					.retrieveArModifyAffirmListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {
				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			// 得到决裁者的部门
			deptList = essServices.retrieveAffirmDeptList(parameterObject);

			List arModifyList = essServices.retrieveArModifyAffirmList(parameterObject, currentPage, pageSize);
			
			for (int i = 0; i < arModifyList.size(); i++) {
				ArDetailBack arModify = (ArDetailBack) arModifyList.get(i);
				//parameterObject.setInt("applyNo", leave.getLeaveNo());
				///parameterObject.setInt("level", leave.getAffirmLevel());
				
				// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
				// ;
				// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
				if ((arModify.getNext_flag() == 0 && arModify.getUp_flag() == 1 
				//		&& arModify.getActivity() != 2
						)
						|| arModify.getMaxLevel_flag() == 1) {
					// 决裁状态为"待决裁"
					if (arModify.getAffirm_flag() == 0)
						// 可进行"通过"和"否决"
						arModify.setOpFlag(0);
					// 决裁后可进行修改并且状态为"已通过"
					else if (essSysparam.isLeaveModifiedAfterAffirm()
							&& arModify.getAffirm_flag() == 1)
						// 可进行"否决"
						arModify.setOpFlag(2);
					// 决裁后可进行修改并且状态为"否决"
					else if (essSysparam.isLeaveModifiedAfterAffirm()
							&& arModify.getAffirm_flag() == 2)
						// 可进行"通过"
						arModify.setOpFlag(1);
				}
				arModify.setAffirmorList((ArrayList) essArDAO.getArModifyAffirmorList(arModify.getPkNo1()));
				
			}

			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map statusMap = new HashMap();
			Map colorMap = new HashMap();
			if (arModifyList.size() > 0) {
				statusMap.put("0", message1);
				statusMap.put("1", message2);
				statusMap.put("2", message3);

				colorMap.put("0", "#990099");
				colorMap.put("1", "#00CC00");
				colorMap.put("2", "#CC0000");
			}

			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptID", deptID);
			request.setAttribute("deptList", deptList);
			request.setAttribute("key", key);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("arModifyList", arModifyList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		}

		return "/ess_ar_modify_affirm.jsp?menu_code="
				+ request.getParameter("menu_code");
	}		
	/* 休假确认页面 */
	public String viewLeaveConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		String sDate = StringUtil.checkNull(request.getParameter("sDate"));
		String eDate = StringUtil.checkNull(request.getParameter("eDate"));
		String deptID = StringUtil.checkNull(request.getParameter("deptID"));
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("cpnyId", cpnyId);
		parameterObject.set("LeaveApply", "LeaveApply");
		if (sDate.equals("") || eDate.equals("")) {
			//SimpleMap smap = new SimpleMap();			
			//smap = (SimpleMap) essServices
			//		.getConfirmListFirstDate(parameterObject);
			//if (smap.getString("MINDATE") == null) {
				sDate = arServices.getStartDateStr();
				eDate = arServices.getEndDateStr();
			//} else {
			//	sDate = smap.getString("MINDATE");
			//	eDate = smap.getString("MAXDATE");
			//}
		}
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getCpnyId();
		if (deptID.equals(""))
			deptID = cpnyId;
		String key = request.getParameter("key") != null ? StringUtil
				.toCN(request.getParameter("key")) : " ";
		String s_qryType = request.getParameter("qryType");
		int qryType = s_qryType == null ? 0 : Integer.parseInt(s_qryType);
		int currentPage = Integer.parseInt(StringUtil.checkNull(request
				.getParameter("currentPage"), "1"));
		PageControl pageControl = new PageControl();
		pageControl.setCurrentPage(currentPage);
		essServices.setAdminId(adminId);
		essServices.setPageControl(pageControl);

		List leaveList = essServices.getLeaveConfirmList(sDate, eDate, key,
				qryType, deptID);

		pageControl = essServices.getPageControl();
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Map statusMap = new HashMap();
		statusMap.put("0", "未决裁");
		statusMap.put("1", "已通过");
		statusMap.put("2", "已否决");

		Map colorMap = new HashMap();
		colorMap.put("0", "#990099");
		colorMap.put("1", "#00CC00");
		colorMap.put("2", "#CC0000");

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		MessageSource messageSource = new MessageSource("ess", admin
				.getLocale(), "UTF-8");
		String message1 = messageSource
				.getMessage("display.ess.approval.pending");
		String message2 = messageSource
				.getMessage("display.ess.approval.approved");
		String message3 = messageSource
				.getMessage("display.ess.approval.rejected");

		Map confirmMap = new HashMap();
		confirmMap.put("0", message1);
		confirmMap.put("1", message2);
		confirmMap.put("2", message3);

		request.setAttribute("pageControl", pageControl);
		request.setAttribute("sDate", sDate);
		request.setAttribute("eDate", eDate);
		request.setAttribute("deptID", deptID);
		request.setAttribute("key", key);
		request.setAttribute("statusMap", statusMap);
		request.setAttribute("colorMap", colorMap);
		request.setAttribute("confirmMap", confirmMap);
		request.setAttribute("leaveList", leaveList);
		return "/ess_leave_confirm.jsp";
	}
	
	/* 支社考勤确认页面 */
	public String viewAttendanceConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			

			String empid = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getAdminID();
			//BasicInfo basic = (BasicInfo) hrmServices.retrieveBasicInfo(empID);
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
            
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("empid", empid);

			// parameterObject.set("LeaveApply", "LeaveApply");

			// parameterObject.setString("condition", key);
			// parameterObject.setString("personId", personId);
			// parameterObject.setString("deptid", deptid);
			String personId = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getPersonId();
	       
			parameterObject.setString("supervisor", personId);
			//parameterObject.setString("cnpyId", basic.getCpnyId());
			// parameterObject.setString("itemNo", itemNo);
			// parameterObject.setString("sLength", sLength);
			// parameterObject.setString("eLength", eLength);
			// parameterObject.setString("empType", empType);
			// parameterObject.setString("tableName", tableName);
			// parameterObject.set("startRownum", currentPage * pageSize);
			// parameterObject.set("endRownum", (currentPage + 1) * pageSize);

			if (sDate.equals("") || eDate.equals("")) {

				sDate = arServices.getStartDateStr();
				eDate = arServices.getEndDateStr();

			}
			
			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));

			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String cpnyId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getCpnyId();
			if (deptID.equals(""))
				deptID = cpnyId;
			String key = StringUtil.checkNull(request.getParameter("key"));
			// String key = request.getParameter("key") != null ? StringUtil
			// .toCN(request.getParameter("key")) : " ";
			parameterObject.setString("condition", key);

			String s_qryType = request.getParameter("qryType");

			int qryType = s_qryType == null ? 0 : Integer.parseInt(s_qryType);
			parameterObject.setInt("qryType", qryType);

			// int currentPage = Integer.parseInt(StringUtil.checkNull(request
			// .getParameter("currentPage"), "1"));
			// PageControl pageControl = new PageControl();
			// pageControl.setCurrentPage(currentPage);
			// essServices.setAdminId(adminId);
			// essServices.setPageControl(pageControl);

			
			
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize;

			pageSize = config.getInt("page.style1.pagesize");

			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			List arItemList = arServices.getItemList();
			// int resultCount = arServices.getDetailListCnt(sDate, eDate, key,
			// admin, deptid, itemNo, sLength, eLength, empType, personId,
			// tableName);
			List detailListCount = essServices
					.getAttendanceConfirmList(parameterObject);
			int resultCount = detailListCount.size();
			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {

				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}
			parameterObject.set("startRownum", currentPage * pageSize);
			parameterObject.set("endRownum", (currentPage + 1) * pageSize);

			List detailList = essServices
					.getAttendanceConfirmList(parameterObject);
			// List leaveList = essServices.getLeaveConfirmList(sDate, eDate,
			// key,
			// qryType, deptID);

			// pageControl = essServices.getPageControl();
			// Logger.getLogger(getClass()).debug(
			// "PageSize : " + pageControl.getPageSize());
			Map statusMap = new HashMap();
			statusMap.put("0", "未决裁");
			statusMap.put("1", "已通过");
			statusMap.put("2", "已否决");

			Map colorMap = new HashMap();
			colorMap.put("0", "#990099");
			colorMap.put("1", "#00CC00");
			colorMap.put("2", "#CC0000");

			AdminBean admin = (AdminBean) request.getSession().getAttribute(
					"admin");
			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map confirmMap = new HashMap();
			confirmMap.put("0", message1);
			confirmMap.put("1", message2);
			confirmMap.put("2", message3);

			// request.setAttribute("pageControl", pageControl);
			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("qryType", qryType);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("confirmMap", confirmMap);
			request.setAttribute("detailList", detailList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");

		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/ess_attendance_confirm.jsp?menu_code="
		+ request.getParameter("menu_code");
	}
	
	
	/* 考勤明细维护确认页面 */
	public String viewArDetailConfirm(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			

			String empid = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getAdminID();
			//BasicInfo basic = (BasicInfo) hrmServices.retrieveBasicInfo(empID);
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
            
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil
					.checkNull(request.getParameter("deptID"));
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("cpnyId", cpnyId);
			parameterObject.set("empid", empid);

			// parameterObject.set("LeaveApply", "LeaveApply");

			// parameterObject.setString("condition", key);
			// parameterObject.setString("personId", personId);
			// parameterObject.setString("deptid", deptid);
			String personId = ((AdminBean) request.getSession(false).getAttribute(
			"admin")).getPersonId();
	       
			parameterObject.setString("supervisor", personId);
			//parameterObject.setString("cnpyId", basic.getCpnyId());
			// parameterObject.setString("itemNo", itemNo);
			// parameterObject.setString("sLength", sLength);
			// parameterObject.setString("eLength", eLength);
			// parameterObject.setString("empType", empType);
			// parameterObject.setString("tableName", tableName);
			// parameterObject.set("startRownum", currentPage * pageSize);
			// parameterObject.set("endRownum", (currentPage + 1) * pageSize);

			if (sDate.equals("") || eDate.equals("")) {

				sDate = arServices.getStartDateStr();
				eDate = arServices.getEndDateStr();

			}
			
			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));

			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String cpnyId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getCpnyId();
			if (deptID.equals(""))
				deptID = cpnyId;
			String key = StringUtil.checkNull(request.getParameter("key"));
			// String key = request.getParameter("key") != null ? StringUtil
			// .toCN(request.getParameter("key")) : " ";
			parameterObject.setString("condition", key);

			String s_qryType = request.getParameter("qryType");

			int qryType = s_qryType == null ? 0 : Integer.parseInt(s_qryType);
			parameterObject.setInt("qryType", qryType);

			// int currentPage = Integer.parseInt(StringUtil.checkNull(request
			// .getParameter("currentPage"), "1"));
			// PageControl pageControl = new PageControl();
			// pageControl.setCurrentPage(currentPage);
			// essServices.setAdminId(adminId);
			// essServices.setPageControl(pageControl);

			
			
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize;

			pageSize = config.getInt("page.style1.pagesize");

			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			List arItemList = arServices.getItemList();
			// int resultCount = arServices.getDetailListCnt(sDate, eDate, key,
			// admin, deptid, itemNo, sLength, eLength, empType, personId,
			// tableName);
			List detailListCount = essServices
					.getArDetailConfirmList(parameterObject);
			int resultCount = detailListCount.size();
			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {

				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}
			parameterObject.set("startRownum", currentPage * pageSize);
			parameterObject.set("endRownum", (currentPage + 1) * pageSize);

			List detailList = essServices
					.getArDetailConfirmList(parameterObject);
			// List leaveList = essServices.getLeaveConfirmList(sDate, eDate,
			// key,
			// qryType, deptID);

			// pageControl = essServices.getPageControl();
			// Logger.getLogger(getClass()).debug(
			// "PageSize : " + pageControl.getPageSize());
			Map statusMap = new HashMap();
			statusMap.put("0", "未决裁");
			statusMap.put("1", "已通过");
			statusMap.put("2", "已否决");

			Map colorMap = new HashMap();
			colorMap.put("0", "#990099");
			colorMap.put("1", "#00CC00");
			colorMap.put("2", "#CC0000");

			AdminBean admin = (AdminBean) request.getSession().getAttribute(
					"admin");
			MessageSource messageSource = new MessageSource("ess", admin
					.getLocale(), "UTF-8");
			String message1 = messageSource
					.getMessage("display.ess.approval.pending");
			String message2 = messageSource
					.getMessage("display.ess.approval.approved");
			String message3 = messageSource
					.getMessage("display.ess.approval.rejected");

			Map confirmMap = new HashMap();
			confirmMap.put("0", message1);
			confirmMap.put("1", message2);
			confirmMap.put("2", message3);

			// request.setAttribute("pageControl", pageControl);
			request.setAttribute("sDate", sDate);
			request.setAttribute("eDate", eDate);
			request.setAttribute("deptID", deptID);
			request.setAttribute("key", key);
			request.setAttribute("qryType", qryType);
			request.setAttribute("statusMap", statusMap);
			request.setAttribute("colorMap", colorMap);
			request.setAttribute("confirmMap", confirmMap);
			request.setAttribute("detailList", detailList);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");

		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/ess_ar_detail_confirm.jsp?menu_code="
		+ request.getParameter("menu_code");
	}

	/**
	 * viewSuspension
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 */

	private String viewArAnnual(HttpServletRequest request,
			HttpServletResponse response) {
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		BasicInfo employee = new BasicInfo();
		GregorianCalendar date = new GregorianCalendar();
		String key = StringUtil.toCN(request.getParameter("key"), admin
				.getAdminID());
		int year = Integer.parseInt(StringUtil.checkNull(request
				.getParameter("year"), String.valueOf(date
				.get(GregorianCalendar.YEAR))));
		if (year < date.get(GregorianCalendar.YEAR)) {
			date.set(GregorianCalendar.YEAR, year + 1);
			date.set(GregorianCalendar.MONTH, 0);
			date.set(GregorianCalendar.DAY_OF_MONTH, 1);
			date.add(GregorianCalendar.DAY_OF_MONTH, -1);
		} else if (year > date.get(GregorianCalendar.YEAR)) {
			date.set(GregorianCalendar.YEAR, year);
			date.set(GregorianCalendar.MONTH, 0);
			date.set(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		String dateStr = new java.text.SimpleDateFormat("yyyy-MM-dd")
				.format(date.getTime());
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("CONDITION", key);
		parameterObject.setString("SUPERVISOR_ID", admin.getAdminID());
		List empList = hrmServices.retrieveEmpList(parameterObject);
		List annualList = new ArrayList();
		boolean noSelfFlag = true;
		for (int i = 0; i < empList.size(); i++) {
			employee = (BasicInfo) empList.get(i);
			if (employee.getEmpID().equals(admin.getAdminID()))
				noSelfFlag = false;
			Annual annual = (Annual) arServices.getAnnual(employee.getEmpID(),
					dateStr);
			annual.setChineseName(employee.getChineseName());
			annual.setReason(employee.getDepartment());
			annualList.add(annual);
		}
		if (noSelfFlag && key.equals("")) {
			Annual annual = (Annual) arServices.getAnnual(admin.getAdminID(),
					dateStr);
			annual.setChineseName(admin.getChineseName());
			annualList.add(annual);
		}
		request.setAttribute("excel", StringUtil.checkNull(request
				.getParameter("excel")));
		request.setAttribute("date", date);
		request.setAttribute("annualList", annualList);
		return "/ess_annual.jsp";
	}

	public String viewArAffairLeave(HttpServletRequest request,
			HttpServletResponse response) {
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String key = StringUtil.toCN(request.getParameter("key"), admin
				.getAdminID());
		String str_year = request.getParameter("year");
		Integer year = StringUtil.isNotBlank(str_year) ? new Integer(str_year)
				: new Integer(DateUtil.getYear(new Date()));
		boolean isAll = "1".equals(request.getParameter("isAll")) ? true
				: false;
		SQLResult sqlResult = isAll ? essServices.getAffairLeave(admin
				.getAdminID(), year, key) : essServices.getAffairLeave(admin
				.getAdminID(), year);
		request.setAttribute("sqlResult", sqlResult);
		return "/ess_affairleave.jsp";
	}

	
	
	

	private String overtimeViewExcel(HttpServletRequest request,
			HttpServletResponse response) {
		EssArDAO essArDAO = new EssArDAO();
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute(
				"admin"));
		SimpleMap parameterObject;
		List list = new ArrayList();

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());

			List overTimeList = essServices
					.retrievePersonalOtInfoList(parameterObject);
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			Iterator iter = overTimeList.iterator();
			for (; iter.hasNext();) {
				essOverTimeBean = (EssOverTimeBean) iter.next();
				SimpleMap simple = new SimpleMap();
				simple.setString("empId", essOverTimeBean.getEmpId());
				simple.setString("chineseName", essOverTimeBean
						.getChineseName());
				simple.setString("deptName", essOverTimeBean.getDeptName());
				simple.setString("fourthDeptName", essOverTimeBean
						.getFourthDeptName());
				simple.setString("postGradeCode", essOverTimeBean.getPostGradeCode());
				simple.setString("createDate", essOverTimeBean.getCreateDate());
				simple.setString("otDate", essOverTimeBean.getOtDate());
				simple.setString("otTypeCode", essOverTimeBean.getOtTypeName());
				if (essOverTimeBean.getOtTypeCode().equals("WorkingOtType01")) {
					simple.setString("from_toTime", essOverTimeBean
							.getOtFromTime()
							+ " 至 " + essOverTimeBean.getOtToTime());
				} else {
					simple
							.setString("from_toTime", essOverTimeBean
									.getOtDate());
				}
				simple.setString("otLength", essOverTimeBean.getOtLength().toString());
				
				simple.setString("otRemark", essOverTimeBean.getOtRemark());
				
				if (!essSysparam.isOtAutoConfirm()
						&& (essOverTimeBean.getActivity() == 0 || essOverTimeBean
								.getAffirm_flag() == 2)) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(0); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getActivity() == 1) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(1); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getActivity() == 2
						&& essOverTimeBean.getAffirm_flag() == 1) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(2); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getActivity() == 2
						&& essOverTimeBean.getAffirm_flag() == 2) {
					essOverTimeBean.setDel_flag(0);
					essOverTimeBean.setOpFlag(0); // 设置人事决裁标示
				}
				if (!essSysparam.isOtAutoConfirm()
						&& essOverTimeBean.getAffirm_flag() == 0
						&& essOverTimeBean.getActivity() == 0) {
					// 决裁者与人事都未操作时
					essOverTimeBean.setDel_flag(1);
					essOverTimeBean.setOpFlag(3);
				}
				if (essOverTimeBean.getAffirm_flag() == 0
						&& essOverTimeBean.getActivity() == 0) {
					essOverTimeBean.setDel_flag(1);// 可删除标志
				}
				List essAffirmorList  =(ArrayList) essArDAO
				.getAffirmorList(essOverTimeBean.getOtNo().intValue(),
						"OtApply");
				String affrims = "";
				if(essAffirmorList.size()>0){
					for(int j=0;j<essAffirmorList.size();j++){
						EssAffirmor essAffirmor = (EssAffirmor) essAffirmorList.get(j);
						affrims+=essAffirmor.getAffirmorName()+ "---"+map.get(String.valueOf(essAffirmor.getAffirmFlag()))+" || ";
					}
				}
				simple.set("affrimList",affrims);
				
				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTimeBean);
				essArDAO.getPersonOtTime(essOverTimeBean, arMonthObj, "arDate") ;
//				SimpleMap arMonthObj = new EssArDAO().getArMonthDate(essOverTimeBean);
//				new EssArDAO().getPersonOtTime(essOverTimeBean, arMonthObj, "arDate") ;
				
				if (admin.getCpnyId().equals("60000000")){
					simple.setString("apply_to", essOverTimeBean.getAppply_ot().toString());
				}else{
					simple.setString("apply_to", essOverTimeBean.getOtTotal().toString());
				}

				
			
				list.add(simple);
			}

			SimpleMap columns = new SimpleMap();
			columns.setString("工号", "empId");
			columns.setString("加班人", "chineseName");
			columns.setString("课组", "deptName");
			columns.setString("部门", "fourthDeptName");
			columns.setString("职级", "postGradeCode");
			columns.setString("申请日期", "createDate");
			columns.setString("班次日期", "otDate");
			columns.setString("加班类型", "otTypeCode");
			columns.setString("加班时段", "from_toTime");
			columns.setString("预计长度", "otLength");
			columns.setString("已申请加班", "apply_to");
			columns.setString("工作内容", "otRemark");
			columns.setString("决裁情况", "affrimList");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("apply_to", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("APPPLE_OVERTIME_REPORT_DATA.xls");
			paramBean.setSheetname("OVERTIME_DATA");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("加班信息报表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export apple overtime report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
	
	public String overtimeConfirmExcel(HttpServletRequest request,
			HttpServletResponse response) {

		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute("admin"));
		SimpleMap parameterObject;
		List list = new ArrayList();

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());
			
			
			String sDate = "";
			String eDate = "";
			List overTimeList = new ArrayList();

			sDate = StringUtil.checkNull(request.getParameter("sDate"));
			eDate = StringUtil.checkNull(request.getParameter("eDate"));			
			if (sDate.equals("") || eDate.equals("")) {
				SimpleMap smap = new SimpleMap();
				smap = (SimpleMap) essServices.getOtConfirmListFirstDate(parameterObject);
				if (smap.getString("MINDATE") == null) {
					sDate = arServices.getStartDateStr();
					eDate = arServices.getEndDateStr();
				} else {
					sDate = smap.getString("MINDATE");
					eDate = smap.getString("MAXDATE");
				}
			}
			String cpnyId = admin.getCpnyId();

			String key = request.getParameter("key") != null ? StringUtil.toCN(request.getParameter("key")) : "";
			String deptID = StringUtil.checkNull(request.getParameter("deptID"));
			if (deptID.equals(""))
				deptID = cpnyId;
			
			String s_qryType = request.getParameter("qryType");
			int qryType = s_qryType == null ? 0 : Integer.parseInt(s_qryType);
			
			EssArDAO arDAO = new EssArDAO();
			PageControl control = new PageControl();
			control.setCurrentPage(1);
			control.setPageCount(1);
			control.setPageSize(1000000);
			arDAO.setPageControl(control);
			overTimeList = arDAO.getOtConfirmList(sDate, eDate, key, qryType,deptID);
			 
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			Iterator iter = overTimeList.iterator();
			for (; iter.hasNext();) {
				essOverTimeBean = (EssOverTimeBean) iter.next();
				SimpleMap simple = new SimpleMap();
				simple.setString("empId", essOverTimeBean.getEmpId());
				simple.setString("chineseName", essOverTimeBean.getChineseName());
				simple.setString("deptName", essOverTimeBean.getDeptName());
				simple.setString("fourthDeptName", essOverTimeBean.getFourthDeptName());
				simple.setString("createDate", essOverTimeBean.getCreateDate());
				simple.setString("otDate", essOverTimeBean.getOtDate());
				simple.setString("otTypeCode", essOverTimeBean.getOtTypeName());
				
					simple.setString("from_toTime", essOverTimeBean.getOtDate()+" "+essOverTimeBean.getOtFromTime()+ " 至 " 
													+essOverTimeBean.getOtToDate()+" "+essOverTimeBean.getOtToTime());
//				} else {
//					simple.setString("from_toTime", essOverTimeBean.getOtDate());
//				}
				simple.setString("otLength", essOverTimeBean.getOtLength().toString());
				simple.setString("otRemark", essOverTimeBean.getOtRemark());
				list.add(simple);
			}

			SimpleMap columns = new SimpleMap();
			columns.setString("工号", "empId");
			columns.setString("加班人", "chineseName");
			columns.setString("课组", "deptName");
			columns.setString("部门", "fourthDeptName");
			columns.setString("申请日期", "createDate");
			columns.setString("班次日期", "otDate");
			columns.setString("加班类型", "otTypeCode");
			columns.setString("加班时段", "from_toTime");
			columns.setString("预计长度", "otLength");
			columns.setString("工作内容", "otRemark");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("CONFIRM_OVERTIME_REPORT_DATA.xls");
			paramBean.setSheetname("OVERTIME_DATA");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("加班确认报表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export confirm overtime report Exception. ", e);
		}

		return "/inc/commonReport.jsp";
	}
	
	public String leaveConfirmExcel(HttpServletRequest request,HttpServletResponse response){
		
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute("admin"));
		SimpleMap parameterObject;
		List list = new ArrayList();
		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", admin.getAdminID());
		
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil.checkNull(request.getParameter("deptID"));
			if (sDate.equals("") || eDate.equals("")) {
				SimpleMap smap = new SimpleMap();
				smap = (SimpleMap) essServices
						.getConfirmListFirstDate("LeaveApply");
				if (smap.getString("MINDATE") == null) {
					sDate = arServices.getStartDateStr();
					eDate = arServices.getEndDateStr();
				} else {
					sDate = smap.getString("MINDATE");
					eDate = smap.getString("MAXDATE");
				}
			}
			String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
			String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			if (deptID.equals(""))
				deptID = cpnyId;
			String key = request.getParameter("key") != null ? StringUtil.toCN(request.getParameter("key")) : " ";
			String s_qryType = request.getParameter("qryType");
			int qryType = s_qryType == null ? 0 : Integer.parseInt(s_qryType);
			essServices.setAdminId(adminId);
			
			EssArDAO arDAO = new EssArDAO();
			PageControl control = new PageControl();
			control.setCurrentPage(1);
			control.setPageCount(1);
			control.setPageSize(1000000);
			arDAO.setPageControl(control);
			List leaveList = arDAO.getLeaveConfirmList(sDate, eDate, key, qryType, deptID);
			
			
			EssLeaveBean essLeaveBean = new EssLeaveBean();
			Iterator iter = leaveList.iterator();
			for (; iter.hasNext();) {
				essLeaveBean = (EssLeaveBean) iter.next();
				SimpleMap simple = new SimpleMap();
				simple.setString("empId", essLeaveBean.getEmpId());
				simple.setString("chineseName", essLeaveBean.getChineseName());
				simple.setString("deptName", essLeaveBean.getDeptName());
				simple.setString("fourthDeptName", essLeaveBean.getFourthDeptName());
				simple.setString("createDate", essLeaveBean.getCreateDate());
				simple.setString("otDate", essLeaveBean.getLeaveTypeName());
				simple.setString("otTypeCode", essLeaveBean.getApplyLeaveType());
				simple.setString("from_toTime", essLeaveBean.getLeaveFromTime()+ " 至 " + essLeaveBean.getLeaveToTime());
				simple.setString("otLength", essLeaveBean.getLeaveReason());
				list.add(simple);
			}
	
			SimpleMap columns = new SimpleMap();
			columns.setString("工号", "empId");
			columns.setString("休假人", "chineseName");
			columns.setString("课组", "deptName");
			columns.setString("部门", "fourthDeptName");
			columns.setString("申请日期", "createDate");
			columns.setString("休假类型", "otDate");
			columns.setString("记假方式", "otTypeCode");
			columns.setString("休假时段", "from_toTime");
			columns.setString("休假原因", "otLength");
	
			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);
	
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("CONFIRM_LEAVETIME_REPORT_DATA.xls");
			paramBean.setSheetname("LEAVETIME_DATA");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("加班确认报表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
	
			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export confirm overtime report Exception. ", e);
		}
		
		return "/inc/commonReport.jsp";
	}
	
	public String leaveViewExcel(HttpServletRequest request ,HttpServletResponse response){
		
		AdminBean admin = ((AdminBean) request.getSession(false).getAttribute("admin"));
		SimpleMap parameterObject = new SimpleMap();
		List list = new ArrayList();
		try {

			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			String deptID = StringUtil.checkNull(request.getParameter("deptID"));
			String qryType = StringUtil.checkNull(request.getParameter("qryType"), "0");
			String stsType = StringUtil.checkNull(request.getParameter("stsType"), "0");

			parameterObject.setString("sDate", sDate);
			parameterObject.setString("eDate", eDate);
			parameterObject.setString("key", key);
			parameterObject.setString("deptID", deptID);
			parameterObject.setString("supervisor", admin.getAdminID());
			parameterObject.setString("qryType", qryType);
			parameterObject.setString("stsType", stsType);
			
			List leaveList = essServices.retrievePersonalLeaveInfoList(parameterObject);
	
			EssLeaveBean essLeaveBean = new EssLeaveBean();
			Iterator iter = leaveList.iterator();
			for (; iter.hasNext();) {
				essLeaveBean = (EssLeaveBean) iter.next();
				SimpleMap simple = new SimpleMap();
				simple.setString("empId", essLeaveBean.getEmpId());
				simple.setString("chineseName", essLeaveBean.getChineseName());
				simple.setString("deptName", essLeaveBean.getDeptName());
				simple.setString("fourthDeptName", essLeaveBean.getFourthDeptName());
				//simple.setString("position", essLeaveBean.getPosition());
				simple.setString("postGradeCode", essLeaveBean.getPostGradeCode());
				simple.setString("createDate", essLeaveBean.getCreateDate());
				simple.setString("otDate", essLeaveBean.getLeaveTypeName());
				simple.setString("otTypeCode", essLeaveBean.getApplyLeaveType());
				simple.setString("from_toTime", essLeaveBean.getLeaveFromTime()+ " 至 " + essLeaveBean.getLeaveToTime());
				simple.setString("otLength", essLeaveBean.getLeaveReason());
				//新添加出入状态
				
				if(admin.getCpnyId().equals("63000000") || admin.getCpnyId().equals("78000000") ){
				simple.setString("sts", essLeaveBean.getSts());
				}
				list.add(simple);
			}
	
			SimpleMap columns = new SimpleMap();
			columns.setString("工号", "empId");
			columns.setString("休假人", "chineseName");
			columns.setString("课组", "deptName");
			columns.setString("部门", "fourthDeptName");
			columns.setString("职级", "postGradeCode");
			columns.setString("申请日期", "createDate");
			columns.setString("休假类型", "otDate");
			columns.setString("记假方式", "otTypeCode");
			columns.setString("休假时段", "from_toTime");
			columns.setString("休假原因", "otLength");
			
			//新添加  出入状态 
			if(admin.getCpnyId().equals("63000000") || admin.getCpnyId().equals("78000000") ){
			columns.setString("出入状态", "sts");
			}
			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);
	
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("VIEW_LEAVETIME_REPORT_DATA.xls");
			paramBean.setSheetname("LEAVETIME_DATA");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("休假信息报表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
	
			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export view leave report Exception. ", e);
		}
		return "/inc/commonReport.jsp";
	}
	
	public String overtimeAffirmExcel(HttpServletRequest request, HttpServletResponse response){
		
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String adminId = admin.getAdminID();
		SimpleMap parameterObject = new SimpleMap();
		List list = new ArrayList();

		try {
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request.getParameter("qryType"), "0"));

			parameterObject.set("supervisor", adminId);
			parameterObject.set("sDate", sDate);
			parameterObject.set("eDate", eDate);
			parameterObject.set("deptID", deptID);
			parameterObject.set("key", key);
			parameterObject.set("qryType", qryType);


			List overTimeList = essServices.retrieveOtAffirmList(parameterObject);
			
			
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			Iterator iter = overTimeList.iterator();
			for (; iter.hasNext();) {
				essOverTimeBean = (EssOverTimeBean) iter.next();
				SimpleMap simple = new SimpleMap();
				simple.setString("empId", essOverTimeBean.getEmpId());
				simple.setString("chineseName", essOverTimeBean.getChineseName());
				simple.setString("deptName", essOverTimeBean.getDeptName());
				simple.setString("fourthDeptName", essOverTimeBean.getFourthDeptName());
				simple.setString("createDate", essOverTimeBean.getCreateDate());
				simple.setString("otDate", essOverTimeBean.getOtDate());
				simple.setString("otTypeCode", essOverTimeBean.getOtTypeName());
//				if ("WorkingOtType01".equals(essOverTimeBean.getOtTypeCode())) {
					simple.setString("from_toTime", essOverTimeBean.getOtFromTime()+ " 至 " + essOverTimeBean.getOtToTime());
//				} else {
//					simple.setString("from_toTime", essOverTimeBean.getOtDate());
//				}
				simple.setString("otLength", essOverTimeBean.getOtLength().toString());
				simple.setString("otRemark", essOverTimeBean.getOtRemark());
				list.add(simple);
			}

			SimpleMap columns = new SimpleMap();
			columns.setString("工号", "empId");
			columns.setString("加班人", "chineseName");
			columns.setString("课组", "deptName");
			columns.setString("部门", "fourthDeptName");
			columns.setString("申请日期", "createDate");
			columns.setString("班次日期", "otDate");
			columns.setString("加班类型", "otTypeCode");
			columns.setString("加班时段", "from_toTime");
			columns.setString("预计长度", "otLength");
			columns.setString("工作内容", "otRemark");

			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);

			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("OVERTIME_AFFIRM_REPORT_DATA.xls");
			paramBean.setSheetname("OVERTIME_DATA");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("加班决裁报表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);

			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
			
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export overtime affirm report Exception. ", e);
		}
		
		return "/inc/commonReport.jsp";
	}

	public String leaveAffirmExcel(HttpServletRequest request,HttpServletResponse response){
		
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();
		List list = new ArrayList();

		try {
			String sDate = StringUtil.checkNull(request.getParameter("sDate"));
			String eDate = StringUtil.checkNull(request.getParameter("eDate"));
			String deptID = StringUtil.checkNull(request.getParameter("deptID"));
			String key = StringUtil.checkNull(request.getParameter("key"));
			int qryType = Integer.parseInt(StringUtil.checkNull(request.getParameter("qryType"), "0"));

			parameterObject.set("sDate", sDate);
			parameterObject.set("eDate", eDate);
			parameterObject.set("deptID", deptID);
			parameterObject.set("key", key);
			parameterObject.set("qryType", qryType);
			parameterObject.set("supervisor", admin.getAdminID());

			List leaveList = essServices.retrieveLeaveAffirmList(parameterObject);
	
			EssLeaveBean essLeaveBean = new EssLeaveBean();
			Iterator iter = leaveList.iterator();
			for (; iter.hasNext();) {
				essLeaveBean = (EssLeaveBean) iter.next();
				SimpleMap simple = new SimpleMap();
				simple.setString("empId", essLeaveBean.getEmpId());
				simple.setString("chineseName", essLeaveBean.getChineseName());
				simple.setString("deptName", essLeaveBean.getDeptName());
				simple.setString("fourthDeptName", essLeaveBean.getFourthDeptName());
				simple.setString("postGradeCode", essLeaveBean.getPostGradeCode());
				simple.setString("createDate", essLeaveBean.getCreateDate());
				simple.setString("otDate", essLeaveBean.getLeaveTypeName());
				//simple.setString("otTypeCode", essLeaveBean.getApplyLeaveType());
				simple.setString("from_toTime", essLeaveBean.getLeaveFromTime()+ " 至 " + essLeaveBean.getLeaveToTime());
				simple.setString("otLength", essLeaveBean.getLeaveReason());
				list.add(simple);
			}
	
			SimpleMap columns = new SimpleMap();
			columns.setString("工号", "empId");
			columns.setString("休假人", "chineseName");
			columns.setString("课组", "deptName");
			columns.setString("部门", "fourthDeptName");
			columns.setString("职级", "postGradeCode");
			columns.setString("申请日期", "createDate");
			columns.setString("休假类型", "otDate");
			//columns.setString("记假方式", "otTypeCode");
			columns.setString("休假时段", "from_toTime");
			columns.setString("休假原因", "otLength");
	
			// 定义列类型
			SimpleMap columnType = new SimpleMap();
			columnType.setInt("empId", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("createDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otDate", ReportConstant.CELL_TYPE_TEXT);
			columnType.setInt("otLength", ReportConstant.CELL_TYPE_TEXT);
	
			// 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("LEAVETIME_AFFIRM_REPORT_DATA.xls");
			paramBean.setSheetname("LEAVETIME_DATA");
			paramBean.setReportData(list);
			paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 添加报表图片
			// paramBean.setImageCol(columns.size() - 4);
			// paramBean.setImageRow(recordList.size()+ 5);
			// paramBean.setImageHeight(2);
			// paramBean.setImageWidth(4);
			// paramBean.setImageFile(new File(request.getRealPath("/") +
			// "images/report_logo.png"));
			// 设置页眉
			// paramBean.setHeadContent("资产记录");
			// 设置内嵌表头
			// LSFC个人年假记录表
			paramBean.setInLineHeadContent("休假决裁报表");
			paramBean.setInLineHeadMergeSize(columns.size());
			// 设置EXCEL表头的显示方向
			paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
	
			// make attendance record report
			ReportUtil.makeReport(request, paramBean);
		} catch (Exception e) {
	
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"export leave affirm report Exception. ", e);
		}
		return "/inc/commonReport.jsp";
	}
}


















