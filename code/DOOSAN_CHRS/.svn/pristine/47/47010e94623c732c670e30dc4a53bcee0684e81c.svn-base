package com.ait.ar.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetail;
import com.ait.ar.bean.Supervisor;
import com.ait.ar.business.ArServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ExcelBatchProcessor;
import com.ait.excel.util.ExcelParameterBean;
import com.ait.excel.util.ReportConstant;
import com.ait.excel.util.ReportUtil;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.CommonUtil;
import com.ait.util.StringUtil;
import com.ait.util.ViewOptionUtil;
import com.ait.web.Command;

public class ArPageControlCommand implements Command {

	private ArServices arServices;

	private HrmServices hrmServices;

	// private String adminID;

	private String page; // 要转向的页面

	public ArPageControlCommand() {
		arServices = new ArServices();
		hrmServices = HrmServices.getInstance();
	}
	
	

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
        HttpSession session = request.getSession(true);
 		AdminBean admin = (AdminBean) session.getAttribute("admin");  
		page = StringUtil.checkNull(request.getParameter("page"));// 从request中得到的页面名称
		if (page.equals("dynamicGroup")) { // 导向动态组的查看页面
			returnPage = this.dynamicGroup(request);
		} else if (page.equals("dynamicGroupAdd")) { // 导向动态组的查看页面
			returnPage = this.dynamicGroupAdd(request);
		} else if (page.equals("ms_v")) {// supervisor maintenance 查看
			returnPage = this.supervisor(request);
		} else if (page.equals("ms_a")) { // supervisor maintenance 添加
			returnPage = this.supervisorAdd(request,admin);
		} else if (page.equals("ms_d")) { // supervisor maintenance 删除
			returnPage = this.supervisorDelete(request);
		} else if (page.equals("ms_m")) { // supervisor maintenance 修改
			returnPage = this.supervisorUpdate(request,admin);
		} else if (page.equals("ec_v")) { // 员工日历查看
			returnPage = this.empCalendar(request);
		} else if (page.equals("detail_v")) { // 日考勤分页查看
			returnPage = this.detail(request);
		} else if (page.equals("monthly_v")) { // 月汇总分页查看
			returnPage = this.monthly(request);
		} else if (page.equals("annual_v")) { // 年假查看
			returnPage = this.annual(request);
		} else if (page.equals("annualExcelTmp_v")) { // 年假Excel模板导出
			returnPage = this.annualExcelTmp(request);
		} else if (page.equals("annualExcelImport_v")) { // 年假Excel导入
			returnPage = this.annualExcelImport(request);
		} else if (page.equals("attViewByPersonal")) { // 个人别考勤查看
			returnPage = this.attViewByPersonal(request);
		} else if (page.equals("overTimeViewByPersonal")) { // 个人别加班信息查看
			returnPage = this.overTimeViewByPersonal(request);
		} else if (page.equals("monthViewByAll")) { // 全职员月汇总查看
			returnPage = this.monthViewByAll(request);
		} else if (page.equals("monthlyAttReport")) { // 全职员月汇总报表
			returnPage = this.monthlyAttReport(request);
		} else if (page.equals("attStatus_v")) { // 考勤状态查看
			returnPage = this.attStatusView(request);
		} else if (page.equals("attReport")) { // 考勤报表
			returnPage = this.attReport(request);
		} else if(page.equals("attViewByPersonalExcelImport")){
			returnPage = this.attViewByPersonalExcelImport(request);
		} else if (page.equals("downloadEmpShiftTemplate")) { // 下载排班模板
			returnPage = this.downloadEmpShiftTemplate(request);
		} else if (page.equals("downloadArOtDetailTemplate")) { // 下载考勤明细加班导入模板
			returnPage = this.downloadArOtDetailTemplate(request);
		}else{
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("returnPage : " + returnPage);
		return returnPage;
	}

	/**
	 * dynamicGroup
	 * 
	 * @return String
	 */
	private String dynamicGroup(HttpServletRequest request) {
		try {
			AdminBean adminBean = new AdminBean();
			adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
			List dynamicGroupList = arServices.getGroupList(adminBean.getCpnyId());
			request.setAttribute("dynamicGroupList", dynamicGroupList);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_set_dynamicgroup.jsp";
	}

	/**
	 * get detail list by paging
	 * 
	 * @param request
	 * @return
	 */
	private String detail(HttpServletRequest request) {
		String sDate = StringUtil.checkNull(request.getParameter("sDate"));
		String eDate = StringUtil.checkNull(request.getParameter("eDate"));
		String key = StringUtil.checkNull(request.getParameter("key"));
		String personId = StringUtil.checkNull(request.getParameter("personId"));
		String deptid = StringUtil.checkNull(request.getParameter("deptid"));
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		String itemNo = StringUtil.checkNull(request.getParameter("itemNo"));
		String sLength = StringUtil.checkNull(request.getParameter("sLength"));
		String eLength = StringUtil.checkNull(request.getParameter("eLength"));
		String empType = StringUtil.checkNull(request.getParameter("empType")); 
		String tableName = StringUtil.checkNull(request.getParameter("tableName"),"AR_DETAIL");
		
		if (sDate.equals("")) {
			Calendar arDate = Calendar.getInstance() ;
			arDate.add(Calendar.DAY_OF_YEAR, -1) ;
			sDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(arDate.getTime());
			eDate = sDate;
		}
		String flag = request.getParameter("flag") != null ? request.getParameter("flag") : "search";
		String url = "/ar_detail.jsp";
		if (flag.equals("search")) {
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
				int resultCount = arServices.getDetailListCnt(sDate, eDate, key, admin, deptid, itemNo, sLength, eLength, empType, personId, tableName);

				// 如果"当前页"大于最大页数,取最后一页
				if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {

					currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
				}

				List detailList = arServices.getDetailList(sDate, eDate, key, admin, deptid, itemNo, sLength, eLength, currentPage, pageSize, empType, personId, tableName);

				request.setAttribute("key", key);
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
		} else if (flag.equals("report")) {
			List datalist = arServices.getArDetailForExecl(sDate, eDate, key, admin.getAdminID(), deptid, itemNo, sLength, eLength, empType, tableName);
			request.setAttribute("datalist", datalist);
			url = "reports/ar_report/arDetailReport.jsp";

		} else if (flag.equals("arEmaiSend")) {
			
			List datalist = arServices.getArDetailByEmail(sDate, eDate, key, admin.getAdminID(), deptid, itemNo, sLength, eLength, empType, tableName);
			request.setAttribute("datalist", datalist);
			String name = ((AdminBean) request.getSession().getAttribute("admin")).getChineseName();
			url = "/ar_detail.jsp";
			if (datalist.size()>0) {
				ArDetail are = (ArDetail)datalist.get(0);
				are.getDeptId();
				// get first affimor mail
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.setString("deptId", are.getDeptId());
				parameterObject.setString("flag", "1");
				String emial = arServices.getArEmail(parameterObject);
			 
				
				// send mail
				Mail mailUtil = null;
				mailUtil = new Mail();
				SimpleMap inputData = new SimpleMap();
				inputData.setString("主题", "考勤明细");
				inputData.setString("申请人",name);
				//inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
				//inputData.setString("申请内容", affirmObj.getString("PRESENT_OBJECT") + "  " + affirmObj.getString("REASON"));
				Iterator dataIterator =datalist.iterator();
					
				while (dataIterator.hasNext()) {
					ArDetail ar = (ArDetail)dataIterator.next();
					
					inputData.setString(ar.getEmpID(), ar.getEmpName() + "  " +ar.getAr_date_str() + "  " + ar.getItemName() );
					
				}
				//request.setAttribute("info", datalist.size()+"条考勤明细申请成功");		
				try {
					mailUtil.arSendMail(inputData, admin.getCpnyId(), emial, "考勤明细");
					request.setAttribute("info", datalist.size()+"条考勤明细申请成功(不包含已通过考勤)");
					
				} catch (Exception e) {
					request.setAttribute("info", "考勤明细申请失败");			
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				request.setAttribute("info", "没有符合条件的考勤记录");			
			}

		}
		
		return url;
	}

	/**
	 * retrieve monthly attendance list
	 * 
	 * @param request
	 * @return String
	 */
	private String monthly(HttpServletRequest request) {

		// 取日期参数
		GregorianCalendar currentDay = new GregorianCalendar();
		int m = currentDay.get(Calendar.MONTH) + 1;
		int y = currentDay.get(Calendar.YEAR);

		String month = ("0" + String.valueOf(m)).substring(("0" + String
				.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
		String year = String.valueOf(y);

		String arMonth = year + month;

		if (request.getParameter("month") != null
				&& request.getParameter("year") != null) {
			try {
				month = request.getParameter("month");
				year = request.getParameter("year");

				arMonth = year + month;

			} catch (Exception e) {
			}
		}

		String key = StringUtil.checkNull((request.getParameter("key")));
		String deptID = StringUtil.checkNull((request.getParameter("deptID")));
		String empDiffType = StringUtil.checkNull((request.getParameter("empDiffType")));
		
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");

		SimpleMap parameterObject = new SimpleMap();

		parameterObject.setString("deptID", deptID);
		parameterObject.setString("empDiffType", empDiffType);
		parameterObject.setString("arMonth", arMonth);
		parameterObject.setString("condition", key);
		parameterObject.setString("supervisor", admin.getAdminID());
		parameterObject.setString("cpnyId", admin.getCpnyId());
		parameterObject.setString("sqlStatement", arServices.getArColumns2(admin.getCpnyId()));
		// set parameter for view monthly status
		parameterObject.setString("PA_MONTH_STR", arMonth);

		int currentPage = 0;
		// if has currentpage set value into currentPage
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style2.pagesize");
			int pageGroupSize = config.getInt("page.style2.pagegroupsize");

			// 取得汇总列表头
			List<SimpleMap> columns = arServices.getArColumns(admin.getCpnyId());
			// 取得数据行数
			int resultCount = arServices.getArDataCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {

				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}
			
			List arData = arServices.getArData(parameterObject, currentPage, pageSize);

			request.setAttribute("deptID", deptID);
			request.setAttribute("empDiffType", empDiffType);
			request.setAttribute("arMonth", arMonth + "");
			request.setAttribute("year", year + "");
			request.setAttribute("month", month + "");
			request.setAttribute("key", key);
			request.setAttribute("columns", columns);
			request.setAttribute("arData", arData);

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"get monthly attendance list by paging Exception. ", e);
		}
		return "/arhistory.jsp";
	}

	/**
	 * retrieve annual holidy list
	 * 
	 * @param request
	 * @return String
	 */
	private String annual(HttpServletRequest request) {

		// 取年份参数
		GregorianCalendar currentDay = new GregorianCalendar();
		int year = currentDay.get(Calendar.YEAR);
		if (request.getParameter("year") != null) {
			try {
				year = Integer.parseInt(request.getParameter("year"));

			} catch (Exception e) {

				year = currentDay.get(Calendar.YEAR);
			}
		}
		// 取关键字参数
		String key = StringUtil.toCN(request.getParameter("key") == null ? ""
				: request.getParameter("key"));

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		// 取分页参数
		int currentPage = 0;
		// if has currentpage set value into currentPage
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("supervisor", admin.getAdminID());
		parameterObject.setString("holidyDate", year + "");
		parameterObject.setString("condition", key);

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");

			// 取得数据行数
			int resultCount = arServices
					.retrieveAnnualHolidyCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount
					/ pageSize - 1 : resultCount / pageSize)
					&& resultCount != 0) {

				currentPage = resultCount % pageSize == 0 ? resultCount
						/ pageSize - 1 : resultCount / pageSize;
			}

			List annualHolidyList = arServices.retrieveAnnualHolidyList(
					parameterObject, currentPage, pageSize);

			request.setAttribute("key", key);
			request.setAttribute("year", year + "");
			request.setAttribute("annualHolidyList", annualHolidyList);
			request.setAttribute("resultCount", resultCount);
			request
					.setAttribute("menu_code", request
							.getParameter("menu_code"));
			request.setAttribute("currentListCnt", annualHolidyList.size());
			request.setAttribute("admin", admin.getAdminID());

			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"retrieve annual holidy list by paging Exception. ", e);
		}

		return "/arAnnualHolidy.jsp";
	}

	/**
	 * download annual holidy excel template
	 * 
	 * @param request
	 * @return String
	 */
	private String annualExcelTmp(HttpServletRequest request) {

		String fileName = "Att_Annual_Data.xls";

		String sheetName = "ATT_ANNUAL_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString("工号", "");
		columns.setString("年假天数", "");
		columns.setString("年份", "");

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";

	}

	/**
	 * annual Excel import
	 * 
	 * @param request
	 * @return String
	 */
	private String annualExcelImport(HttpServletRequest request) {

		String message;

		SimpleMap parameters = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		ExcelBatchProcessor processor = new ExcelBatchProcessor();

		try {

			// 附加表字段
			SimpleMap appendColumn = new SimpleMap();
			appendColumn.set("HOLIDAY_NO", "AR_ANNUAL_SEQ.Nextval");
			appendColumn.set("CREATE_DATE", "SYSDATE");
			appendColumn.set("CREATED_BY", "'" + admin.getAdminID() + "'");

			// Excel列映射的数据库字段数组
			SimpleMap mapColumn = new SimpleMap();
			mapColumn.set("EMPID", ExcelBatchProcessor.CHAR);
			mapColumn.set("CURR_Y_HOLIDAY", ExcelBatchProcessor.NUMBER);
			mapColumn.set("HOLIDAY_DATE", ExcelBatchProcessor.CHAR);

			// 导入表的关键字段
			String pkStr = "EMPID,HOLIDAY_DATE";

			// 添加参数
			// parameters.set("tableName", "I_AR_ANNUAL_HOLIDAY");
			parameters.set("tableName", "AR_ANNUAL_HOLIDAY");
			parameters.set("sheetName", "ATT_ANNUAL_DATA");
			parameters.set("appendColumn", appendColumn);
			parameters.set("mappingColumn", mapColumn);
			parameters.set("pk_str", pkStr);

			// 插入导入数据到临时表
			message = processor.process(request, parameters);

			// message = processor.process(request, parameters, true);

			// 更新导入数据到年假表
			// arServices.updateAnnualHolidyByImport();

			// 插入导入数据到年假表
			// arServices.insertAnnualHolidyByImport();

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"annual import to temp table is fail.", e);
		}

		request.setAttribute("alert", message);
		request.setAttribute("reload",
				"parent.opener.location.reload();parent.close()");

		return "/inc/alertMessage.jsp";
	}

	/**
	 * view attendance data by personal
	 * 
	 * @param request
	 * @return String
	 */
	private String attViewByPersonal(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		List annualHolidayList = null;
		Object monthlyData = null;
		BasicInfo basic = null;
		List detailList = null;
		List specialItemList = null;
		List overTimeList = null;
		List itemList = null ;
		String dataTable = "" ;
		
		// get search parameter
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		String supervisor = admin.getAdminID() ;
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String arMonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
		String ar_month_str = arMonth;
		String empid = request.getParameter("empID");
		String personId = request.getParameter("personId");

		try {

			parameterObject.setString("cpnyId", admin.getCpnyId());
			parameterObject.setString("arMonth", arMonth);
			parameterObject.setString("ar_month_str", ar_month_str);
			parameterObject.setString("supervisor", supervisor);
			parameterObject.setString("empID", empid);
			if (personId == null || personId.equals(""))
				personId = CommonUtil.getPersonId(parameterObject);
			parameterObject.setString("empID", personId);

			// retrieve employee basic information
			basic = (BasicInfo) hrmServices.retrieveBasicInfo(personId);

			if (empid != null && !empid.equals("")) {
				
				// retrieve attendance detail data
				detailList = arServices.retrieveAttDetailForView(parameterObject);
				
				// retrieve special item data
				specialItemList = arServices.retrieveSpecialItemForViewForView(parameterObject);

				// retrieve over time data
				overTimeList = arServices.retrieveOverTimeByView(parameterObject);
				
				dataTable = new ViewOptionUtil().makeDataTable(request.getParameter("menu_code"), parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Attendance view by personal error. " + e.toString());
			throw new GlRuntimeException("Attendance view by personal error.",
					e);
		}
		
		request.setAttribute("month", month);
		request.setAttribute("year", year);
		
		request.setAttribute("basic", basic);
		request.setAttribute("overTimeList", overTimeList);
		request.setAttribute("detailList", detailList);
		request.setAttribute("specialItemList", specialItemList);
		request.setAttribute("dataTable", dataTable) ;

		return "/arViewByPersonal.jsp";

	}
	
	/**
	 * attViewByPersonalExcelImport
	 * 
	 * @param request
	 * @return String
	 */
	private String attViewByPersonalExcelImport(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		List annualHolidayList = null;
		Object monthlyData = null;
		BasicInfo basic = null;
		List detailList = null;
		List overTimeList = null;
		List itemList = null ;
		
		// get search parameter
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String arMonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
		String ar_month_str = arMonth;
		String empid = request.getParameter("empID");
		//String sqlStatement = arServices.getArColumns2();

		try {

			//parameterObject.setString("supervisor", supervisor);
			parameterObject.setString("arMonth", arMonth);
			parameterObject.setString("ar_month_str", ar_month_str);
			parameterObject.setString("empID", empid);
			parameterObject.setString("CPNY_ID", admin.getCpnyId());
			//parameterObject.setString("sqlStatement", sqlStatement);
			parameterObject.setString("key", empid);
			parameterObject.setString("vac_tp", "VacType10");
			parameterObject.setString("vac_id", year);

			// retrieve employee basic information
			basic = (BasicInfo) hrmServices.retrieveBasicInfo(empid);

			if (empid != null && !empid.equals("")) {
				// retrieve annual holidy data
				//annualHolidayList = arServices.retrieveVacationEmpList(parameterObject);

				// retrieve mothly attendance data
				itemList = arServices.retrieveArItem(admin.getCpnyId());
				String sqlStatement = arServices.getDetailSql(itemList) ;
				parameterObject.setString("sqlStatement",sqlStatement) ;
				
				monthlyData = arServices.getArPersonalData(parameterObject);
				
				// retrieve attendance detail data
				detailList = arServices.retrieveAttDetailForView(parameterObject);

				// retrieve over time data
				overTimeList = arServices.retrieveOverTimeByView(parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Attendance view by personal error. " + e.toString());
			throw new GlRuntimeException("Attendance view by personal error.",
					e);
		}
		
		request.setAttribute("month", month);
		request.setAttribute("year", year);
		request.setAttribute("empid", empid);
		
		request.setAttribute("basic", basic);
		request.setAttribute("annualHolidayList", annualHolidayList);
		request.setAttribute("monthlyData", monthlyData);
		request.setAttribute("overTimeList", overTimeList);
		request.setAttribute("detailList", detailList);

		return "/reports/ar_report/arViewByPersonal.jsp";

	}

	/**
	 * over time view by personal
	 * 
	 * @param request
	 * @return String
	 */
	private String overTimeViewByPersonal(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		List monthlyDataList = null;
		BasicInfo basic = null;
		List detailList = null;
		List overTimeList = null;

		// get search parameter
		String supervisor = ((AdminBean) request.getSession().getAttribute(
				"admin")).getAdminID();
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String arMonth = year
				+ ("0" + month).substring(("0" + month).length() - 2,
						("0" + month).length());
		String ar_month_str = arMonth;
		String empid = request.getParameter("empID");
		String sqlStatement = arServices.getArColumns2(((AdminBean) request.getSession().getAttribute("admin")).getCpnyId());

		try {

			//parameterObject.setString("supervisor", supervisor);
			parameterObject.setString("arMonth", arMonth);
			parameterObject.setString("ar_month_str", ar_month_str);
			parameterObject.setString("empID", empid);
			parameterObject.setString("sqlStatement", sqlStatement);

			// retrieve employee basic information
			basic = (BasicInfo) hrmServices.retrieveBasicInfo(empid);

			if (empid != null && !empid.equals("")) {

				// retrieve mothly attendance data
				monthlyDataList = arServices.getArData(parameterObject);

				// retrieve attendance detail data
				detailList = arServices.retrieveAttDetailForView(parameterObject);

				// retrieve over time data
				overTimeList = arServices.retrieveOverTimeByView(parameterObject);

			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Over time view by personal error. " + e.toString());
			throw new GlRuntimeException("Over time view by personal error.", e);
		}

		request.setAttribute("month", month);
		request.setAttribute("year", year);
		request.setAttribute("empid", empid);

		request.setAttribute("basic", basic);
		request.setAttribute("monthlyDataList", monthlyDataList);
		request.setAttribute("overTimeList", overTimeList);
		request.setAttribute("detailList", detailList);

		return "ar/overTimeViewByPersonal.jsp";
	}

	/**
	 * Monthly attendance view by all
	 * 
	 * @param request
	 * @return String
	 */
	private String monthViewByAll(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		List monthlyDataList = null;
		int resultCount = 0;
		String dataTable = "" ;

		// get search parameter
		String supervisor = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession().getAttribute("admin")).getCpnyId();
		String menuCode = request.getParameter("menu_code");
		
		// 取日期参数
		GregorianCalendar currentDay = new GregorianCalendar();
		int m = currentDay.get(Calendar.MONTH) + 1;
		int y = currentDay.get(Calendar.YEAR);

		String month = ("0" + String.valueOf(m)).substring(("0" + String.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
		String year = String.valueOf(y);
		String arMonth = year + month;
		
		String monthEnd = month ;
		String yearEnd = year ;
		String arEndMonth = "" ;
		

		if (request.getParameter("month") != null && request.getParameter("year") != null) {
			try {
				month = request.getParameter("month");
				year = request.getParameter("year");
				arMonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
				
				monthEnd = request.getParameter("monthEnd");
				yearEnd = request.getParameter("yearEnd");
				arEndMonth = yearEnd + ("0" + monthEnd).substring(("0" + monthEnd).length() - 2, ("0" + monthEnd).length());
				
			} catch (Exception e) {e.printStackTrace();}
		}
		
		try {

			parameterObject.setString("supervisor", supervisor);
			parameterObject.setString("arMonth", arMonth);
			parameterObject.setString("arEndMonth", arEndMonth);
			parameterObject.setString("deptID", request.getParameter("deptID"));
			parameterObject.setString("condition", request.getParameter("key"));
			parameterObject.setString("empType", request.getParameter("empType"));
			parameterObject.setString("cpnyId", cpnyId);

			int currentPage = 0;
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			
			// retrieve mothly attendance data
			dataTable = new ViewOptionUtil().makeDataTableForArMonth(menuCode, parameterObject, currentPage, pageSize);

			// 取得数据行数
			resultCount = new ViewOptionUtil().getTableDataCnt(menuCode, parameterObject, currentPage, pageSize)[0];
			
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupsize", pageGroupSize + "");
			


		} catch (Exception e) {
			Logger.getLogger(getClass()).error("Monthly attendance view by all error. " + e.toString());
			e.printStackTrace();
			throw new GlRuntimeException("Monthly attendance view by all error.", e);
		}
		
		request.setAttribute("month", month);
		request.setAttribute("year", year);
		request.setAttribute("monthEnd", monthEnd);
		request.setAttribute("yearEnd", yearEnd);
		request.setAttribute("deptID", request.getParameter("deptID"));
		request.setAttribute("condition", request.getParameter("key"));
		request.setAttribute("empType", request.getParameter("empType"));
		request.setAttribute("dataTable", dataTable);

		return "ar/monthViewByAll.jsp";
	}

	/**
	 * make monthly attendance report
	 * 
	 * @param request
	 * @return String
	 */
	private String monthlyAttReport(HttpServletRequest request) {
		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		SimpleMap parameterObject = new SimpleMap();
		ViewOptionUtil util = new ViewOptionUtil();

		List monthlyDataList = null;

		// get search parameter
		String supervisor = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession().getAttribute("admin")).getCpnyId();
		String menuCode = request.getParameter("menu_code");
		
		String month = request.getParameter("month");
		String year = request.getParameter("year");		
		String arMonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
		
		String monthEnd = request.getParameter("monthEnd");
		String yearEnd = request.getParameter("yearEnd");		
		String arEndMonth = yearEnd + ("0" + monthEnd).substring(("0" + monthEnd).length() - 2, ("0" + monthEnd).length()); ;

		try {

			parameterObject.setString("supervisor", supervisor);
			parameterObject.setString("arMonth", arMonth);
			parameterObject.setString("arEndMonth", arEndMonth);
			parameterObject.setString("deptID", request.getParameter("deptID"));
			parameterObject.setString("condition", request.getParameter("key"));
			parameterObject.setString("empType", request.getParameter("empType"));
			parameterObject.setString("cpnyId", cpnyId);
			parameterObject.setInt("reportType", 1);

			// retrieve mothly attendance report data
			 monthlyDataList = util.getReportData(menuCode, parameterObject);
			
//			 定义列类型
			 SimpleMap columnType = new SimpleMap();
			 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
			
//			 设置报表参数
			ExcelParameterBean paramBean = new ExcelParameterBean();
			paramBean.setFileName("att_month_data.xls");
			paramBean.setSheetname("ATT_MONTH_DATA");
			paramBean.setReportData(monthlyDataList);
			//paramBean.setColumns(columns);
			paramBean.setColumnTypes(columnType);
			paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
			paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
			// 设置页眉
			//paramBean.setHeadContent("LSFC考勤刷卡记录表");
			// 设置内嵌表头
			//月别考勤信息
			MessageSource message = new MessageSource("ar", admin.getLocale(), "UTF-8"); 
			paramBean.setInLineHeadContent(message.getMessage("display.att.view.monthly.monthly_attendance"));
			paramBean.setInLineHeadMergeSize(((SimpleMap)monthlyDataList.get(0)).size());
			// 设置EXCEL表头的显示方向
			//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
			
			// make attendance record report
			ReportUtil.makeReport(request, paramBean); 

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Make Monthly attendance report  error. " + e.toString());
			throw new GlRuntimeException(
					"Make monthly attendance report error.", e);
		}

		return "/inc/commonReport.jsp";
	}

	/**
	 * view attendance status
	 * 
	 * @param request
	 * @return String
	 */
	private String attStatusView(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");

		// 取日期参数
		GregorianCalendar currentDay = new GregorianCalendar();
		int m = currentDay.get(Calendar.MONTH) + 1;
		int y = currentDay.get(Calendar.YEAR);

		String month = ("0" + String.valueOf(m)).substring(("0" + String.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
		String year = String.valueOf(y);

		if (request.getParameter("month") != null && request.getParameter("year") != null) {

			month = request.getParameter("month");
			year = request.getParameter("year");

		}
		String PA_MONTH_STR = year + month;
		parameterObject.setString("PA_MONTH_STR", PA_MONTH_STR);
		parameterObject.setString("CPNY_ID", admin.getCpnyId());

		try {

			List statusList = arServices.retrieveMonthlyStatusList(parameterObject);

			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("statusList", statusList);
			request.setAttribute("menu_code", request.getParameter("menu_code"));
			request.setAttribute("admin", admin.getAdminID());
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("cpnyName", admin.getCpnyName());

		} catch (Exception e) {

			Logger.getLogger(getClass()).error("View attendance status error. " + e.toString());
			throw new GlRuntimeException("View attendance status error.", e);
		}

		if (request.getParameter("menu_code").indexOf("ar") != -1)
			return "/arStatus.jsp";
		else
			return "/paStatus.jsp";
	}

	/**
	 * attendance report
	 * 
	 * @param request
	 * @return String
	 */
	private String attReport(HttpServletRequest request) {
      
		   String url= "/inc/commonReport.jsp";
		SimpleMap parameterObject = new SimpleMap();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		// get search parameter
		String supervisor = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		String language = ((AdminBean) request.getSession().getAttribute("admin")).getLanguagePreference();
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String arMonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
		String reportType = request.getParameter("reportType");
		String deptID = request.getParameter("deptID");
		try {   

			// annual report
			if (reportType.equals("annual")) {  

				// parameterObject.setString("supervisor", supervisor);
				parameterObject.setString("arMonth", arMonth);
				parameterObject.setString("holidyDate", year);
				parameterObject.setString("deptID", deptID);
				parameterObject.setString("EmpDivision", request.getParameter("EmpDivision"));

				// retrieve annual holidy data
				List annualHolidayList = arServices
						.retrieveAnnualHolidyReport(parameterObject);

				// List annualHolidayList = arServices
				// .retrieveAnnualHolidy(parameterObject);

				String fileName = "att_annual_data.xls";

				String sheetName = "ATT_ANNUAL_DATA";

				// 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				// SimpleMap columns = new SimpleMap();
				// columns.setString("工号", "EMPID");
				// columns.setString("姓名", "CHINESENAME");
				// columns.setString("拼音名", "PINYINNAME");
				// columns.setString("部门", "DEPTID");
				// columns.setString("年份", "HOLIDYDATE");
				// columns.setString("本年年假", "YEARDAYS");
				// columns.setString("剩余年假", "LEFTYEARDAYS");  
				// columns.setString("已用年假", "USEDYEARDAYS");

				// make annual report
				ReportUtil.makeReport(request, annualHolidayList, fileName,
						sheetName, 1, 1);
			}
			
			// monthly detail report
			if (reportType.equals("monthlyDetail")) { 

				parameterObject.setString("supervisor", supervisor);
				parameterObject.setString("arMonth", arMonth);
				parameterObject.setString("deptID", deptID);
				parameterObject.setString("EmpDivision", request.getParameter("EmpDivision"));
				

				// retrieve monthly attendance detail data
				List monthlyDetailList = arServices.retrieveMonthlyAttDetailReport(parameterObject);

				String fileName = "att_month_detail.xls";

				String sheetName = "ATT_DETAIL_DATA";
                           //工号,姓名,部门,员工类别,员工类别
				 // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap();
				 columns.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
				 columns.setString(messageSource.getMessage("report.global.title.name"), 
						 messageSource.getMessage("report.global.field.name"));
				 columns.setString(messageSource.getMessage("report.global.title.deptName"), 
						 messageSource.getMessage("report.global.field.deptName"));                
				 columns.setString(messageSource.getMessage("report.mutual.title.staff_class"), 
						 messageSource.getMessage("report.mutual.field.staff_class"));
				 columns.setString(messageSource.getMessage("report.mutual.title.month"), "AR_MONTH_STR");
				 
				 int startDate = Integer.parseInt(arServices.getBeginDate(arMonth).substring(8));
				 int endDate = Integer.parseInt(arServices.getEndDate(arMonth).substring(8));
				 for (int i=startDate; i <= endDate; i++) {
					 columns.setString(i+"", i+"");
				 }
				 
//				 定义列类型
				 SimpleMap columnType = new SimpleMap();
				 columnType.setInt("EMPID", ReportConstant.CELL_TYPE_TEXT);
				 
//				 设置报表参数
				ExcelParameterBean paramBean = new ExcelParameterBean();
				paramBean.setFileName("att_month_detail.xls");
				paramBean.setSheetname("ATT_DETAIL_DATA");
				paramBean.setReportData(monthlyDetailList);
				paramBean.setColumns(columns);
				paramBean.setColumnTypes(columnType);
				paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
				// 添加报表图片
				paramBean.setImageCol(columns.size() - 4);
				paramBean.setImageRow(monthlyDetailList.size()+ 5);
				paramBean.setImageHeight(2);
				paramBean.setImageWidth(4);
				paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
				// 设置页眉
				//paramBean.setHeadContent("LSFC考勤刷卡记录表");
				// 设置内嵌表头
				MessageSource message = new MessageSource("ar", admin.getLocale(), "UTF-8") ;
				//月考勤明细表
				paramBean.setInLineHeadContent(message.getMessage("display.att.view.report.monthly_attendance"));
				paramBean.setInLineHeadMergeSize(columns.size());
				// 设置EXCEL表头的显示方向
				//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
				
				// make attendance record report
				ReportUtil.makeReport(request, paramBean); 

			}
			
//			 monthly ConsumptioDis report
			if (reportType.equals("monthlyConsumptioDis")) { 

				parameterObject.setString("arMonth", arMonth);
				parameterObject.setString("deptID", deptID);
				parameterObject.setString("EmpDivision", request.getParameter("EmpDivision"));
				

				// retrieve monthly attendance detail data
				List datalist = arServices.retrieveMonthlyConsumptioDisReport(parameterObject);

				String fileName = "att_month_ConsumptioDis.xls";
  
				String sheetName = "ATT_ConsumptioDis_DATA";
   //部门,职务,,工号,姓名,应早餐,应中餐,应晚餐,应夜餐,实际早餐,实际中餐,实际晚餐,实际夜餐,早餐差异,中餐差异,晚餐差异,夜餐差异
				 // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap();
				 SimpleMap columnType=new SimpleMap();
				 columnType.set("EMPID",ReportConstant.CELL_TYPE_TEXT);  
				 columns.setString(messageSource.getMessage("report.global.title.deptName")
						 , language.equals("zh")?"DEPTNAME":"DEPT_EN_NAME");
				 columns.setString(messageSource.getMessage("report.common.title.Position")
						 , language.equals("zh")?"POSITONNAME":"POSITIONENNAME");
				 columns.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
				 columns.setString(messageSource.getMessage("report.global.title.name")
						 , language.equals("zh")?"CHINESENAME":"CHINESE_PINYIN");
				 columns.setString(messageSource.getMessage("report.common.title.breakfast") , "BREAKFAST");
				 columns.setString(messageSource.getMessage("report.common.title.lunch"), "LUNCH");
				 columns.setString(messageSource.getMessage("report.common.title.dinner"), "DINNER");           
				 columns.setString(messageSource.getMessage("report.common.title.nightmeal"), "NIGHTMEAL");
				 columns.setString(messageSource.getMessage("report.common.title.actualbreakfast"), "REALBREAKFAST");
				 columns.setString(messageSource.getMessage("report.common.title.actuallunch"), "REALLUNCH");  
				 columns.setString(messageSource.getMessage("report.common.title.actualdinner"), "REALDINNER");
				 columns.setString(messageSource.getMessage("report.common.title.actualnightmeal"), "REALNIGHTMEAL");
				 columns.setString(messageSource.getMessage("report.common.title.variancebreakfast"), "DIFFBREAKFAST");
				 columns.setString(messageSource.getMessage("report.common.title.variancelunch"), "DIFFLUNCH");
				 columns.setString(messageSource.getMessage("report.common.title.variancedinner"), "DIFFDINNER");
				 columns.setString(messageSource.getMessage("report.common.title.variancenightmeal"), "DIFFNIGHTMEAL");  
				 //月别消费差异对比表
				 
//				 设置报表参数
				ExcelParameterBean paramBean = new ExcelParameterBean();
				paramBean.setFileName("att_month_ConsumptioDis.xls");
				paramBean.setSheetname("ATT_ConsumptioDis_DATA");
				paramBean.setReportData(datalist);
				paramBean.setColumns(columns);
				paramBean.setColumnTypes(columnType);
				paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
				// 添加报表图片
				paramBean.setImageCol(columns.size() - 4);
				paramBean.setImageRow(datalist.size()+ 5);
				paramBean.setImageHeight(2);
				paramBean.setImageWidth(4);
				paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
				// 设置页眉
				//paramBean.setHeadContent("LSFC考勤刷卡记录表");
				// 设置内嵌表头
				//月别消费差异对比表
				MessageSource message = new MessageSource("ar", admin.getLocale(), "UTF-8"); 
				paramBean.setInLineHeadContent(message.getMessage("display.att.view.report.meal_comparison"));
				paramBean.setInLineHeadMergeSize(columns.size());
				// 设置EXCEL表头的显示方向
				//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
				
				// make attendance record report
				ReportUtil.makeReport(request, paramBean); 
                                                         
			}
			
			
			if (reportType.equals("monthlyeatryreport")) { 

				parameterObject.setString("arMonth", arMonth);
		
				// retrieve monthly attendance detail data
				List datalist = arServices.retrieveMonthlyeatryReport(parameterObject);

				String fileName = "att_month_eatry.xls";
  
				String sheetName = "ATT_Montheatry_DATA";
   //部门,职务,,工号,姓名,应早餐,应中餐,应晚餐,应夜餐,实际早餐,实际中餐,实际晚餐,实际夜餐,早餐差异,中餐差异,晚餐差异,夜餐差异
				 // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap(); 
				 columns.setString(messageSource.getMessage("report.global.title.deptName")
						 , language.equals("zh")?"DEPT":"DEPT_EN_NAME");
				 columns.setString(messageSource.getMessage("report.common.title.actualbreakfast"), "BREAKFAST");
				 columns.setString(messageSource.getMessage("report.common.title.actuallunch"), "LUNCH");  
				 columns.setString(messageSource.getMessage("report.common.title.actualdinner"), "DINNER");
				 columns.setString(messageSource.getMessage("report.common.title.actualnightmeal"), "NIGHTMEAL");
				 //月别消费差异对比表
				 
//				 设置报表参数
				ExcelParameterBean paramBean = new ExcelParameterBean();
				paramBean.setFileName(fileName);
				paramBean.setSheetname(sheetName);
				paramBean.setReportData(datalist);
				paramBean.setColumns(columns);
				paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
				paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
				// 添加报表图片
				paramBean.setImageCol(columns.size() - 4);
				paramBean.setImageRow(datalist.size()+ 5);
				paramBean.setImageHeight(2);
				paramBean.setImageWidth(4);
				paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
				// 设置页眉
				//paramBean.setHeadContent("LSFC考勤刷卡记录表");
				// 设置内嵌表头
				//月别消费差异对比表
				MessageSource message = new MessageSource("ar", admin.getLocale(), "UTF-8"); 
				paramBean.setInLineHeadContent(message.getMessage("display.att.view.report.meal_tongji"));
				paramBean.setInLineHeadMergeSize(columns.size());
				// 设置EXCEL表头的显示方向
				//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
				
				// make attendance record report
				ReportUtil.makeReport(request, paramBean); 
                                                         
			}
        
        
			
			if (reportType.equals("visitoridcardreport")) { 

				parameterObject.setString("arMonth", arMonth);
				
				List datalist = arServices.retrieveVisitoridCardReport(parameterObject);

				String fileName = "visitoridcardreport.xls";
              
				String sheetName = "visitoridcardreportdata";

				 // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap();
			
				 columns.setString(messageSource.getMessage("report.att.maintenance.title")
						 , language.equals("zh")?"CODE_NAME":"CODE_EN_NAME");
				 columns.setString(messageSource.getMessage("report.att.breakfast.title"), "REALBREAKFAST");
				 columns.setString(messageSource.getMessage("report.att.lunch.title"), "REALLUNCH");          
				 columns.setString(messageSource.getMessage("report.att.dinner.title"), "REALDINNER");
				 columns.setString(messageSource.getMessage("report.att.nightMeal.title"), "REALNIGHTMEAL");
				 columns.setString(messageSource.getMessage("report.att.visit.firsttime"), "BEGINTIME");
				 columns.setString(messageSource.getMessage("report.att.visit.lasttime"), "ENDTIME");
				 columns.setString(messageSource.getMessage("report.att.visit.visitoramont"), "VISITOR_AMONT");
				
				 //访问卡消费报表
//				 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("visitoridcardreport.xls");
					paramBean.setSheetname("visitoridcardreportdata");
					paramBean.setReportData(datalist);
					paramBean.setColumns(columns);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
					// 添加报表图片
					paramBean.setImageCol(columns.size() - 4);
					paramBean.setImageRow(datalist.size()+ 5);
					paramBean.setImageHeight(2);
					paramBean.setImageWidth(4);
					paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
					// 设置页眉
					//paramBean.setHeadContent("LSFC考勤刷卡记录表");
					// 设置内嵌表头
					//访问卡消费报表
					MessageSource message = new MessageSource("ar", admin.getLocale(), "UTF-8"); 
					paramBean.setInLineHeadContent(message.getMessage("display.att.view.report.visit"));
					paramBean.setInLineHeadMergeSize(columns.size());
					// 设置EXCEL表头的显示方向
					//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
					
					// make attendance record report
					ReportUtil.makeReport(request, paramBean); 
                                                                
			}
			
			if (reportType.equals("dailyOtReport")) 
			{ 
				parameterObject.setString("deptID", deptID);
				parameterObject.setString("EmpDivision", request.getParameter("EmpDivision"));
				String date = request.getParameter("dateStarted"); 
				parameterObject.setString("CHECKDATE", date);
				List datalist =arServices.retrieveDailyOtReport(parameterObject);
				request.setAttribute("datalist",datalist);
			   url="reports/ar_report/dailyOtReport.jsp";
			}
			
			if (reportType.equals("overtimedetailreport")) 
			{ 
				parameterObject.setString("arMonth", arMonth);
				parameterObject.setString("deptID", deptID);
				parameterObject.setString("EmpDivision", request.getParameter("EmpDivision"));
				// retrieve monthly attendance detail data
				List datalist = arServices.retrieveOverTimeDailyReport(parameterObject);

				String fileName = "retrieveOverTimeDailyReport.xls";
  
				String sheetName = "ATT_OVERTIMEDAILY_DATA";
                         //"部门",职务,工号,姓名,员工类别
				 // 建立SQL字段和Excel列名的映射(针对重用以前的SQL,SQL结果集必须为SimleMap)
				 SimpleMap columns = new SimpleMap();
				 SimpleMap columnType=new SimpleMap();
				 columnType.set("EMPID",ReportConstant.CELL_TYPE_TEXT);  
				 columns.setString(messageSource.getMessage("report.global.title.deptName"), 
						 language.equals("zh")?"DEPTNAME":"DEPT_EN_NAME");
				 columns.setString(messageSource.getMessage("report.common.title.Position"),
						 language.equals("zh")?"POSITIONNAME":"POSITIONENNAME");   
				 columns.setString(messageSource.getMessage("report.global.title.empID"), "EMPID");
				 columns.setString(messageSource.getMessage("report.global.title.name"), 
						 language.equals("zh")?"CHINESENAME":"CHINESE_PINYIN");
				 columns.setString(messageSource.getMessage("report.mutual.title.staff_class"), 
						 language.equals("zh")?"EMPTYPENAME":"EMPTYPEENNAME");
				    
				 
				 int startDate = Integer.parseInt(arServices.getBeginDate(arMonth).substring(8));
				 int endDate = Integer.parseInt(arServices.getEndDate(arMonth).substring(8));
				 for (int i=startDate; i <= endDate; i++) {
					 columns.setString(i+"", i+"");  
				 }
				 columns.setString(messageSource.getMessage("report.common.title.totaltime"), "TOTAL");
				 columns.setString(messageSource.getMessage("report.common.title.weekday"), "WEEKDAY");    
				 columns.setString(messageSource.getMessage("report.common.title.weekend"), "WEEKEND");           
				 columns.setString(messageSource.getMessage("report.common.title.holiday"), "HOLIDAY"); 
				 
				 
//				 设置报表参数
					ExcelParameterBean paramBean = new ExcelParameterBean();
					paramBean.setFileName("retrieveOverTimeDailyReport.xls");
					paramBean.setSheetname("ATT_OVERTIMEDAILY_DATA");
					paramBean.setReportData(datalist);
					paramBean.setColumns(columns);
					paramBean.setColumnTypes(columnType);
					paramBean.setReportType(ReportConstant.REPORT_TYPE_EXCEL);
					paramBean.setFileType(ReportConstant.REPORT_TYPE_EXCEL);
					// 添加报表图片
					paramBean.setImageCol(columns.size() - 4);
					paramBean.setImageRow(datalist.size()+ 5);
					paramBean.setImageHeight(2);
					paramBean.setImageWidth(4);
					paramBean.setImageFile(new File(request.getRealPath("/") + "images/report_logo.png"));
					// 设置页眉
					//paramBean.setHeadContent("LSFC考勤刷卡记录表");
					// 设置内嵌表头
					//加班时间明细表
					MessageSource message = new MessageSource("ar", admin.getLocale(), "UTF-8"); 
					paramBean.setInLineHeadContent(message.getMessage("display.att.view.report.ot_detail"));
					paramBean.setInLineHeadMergeSize(columns.size());
					// 设置EXCEL表头的显示方向
					//paramBean.setOrientation(ReportConstant.ORIENTATION_HORIZONTAL);
					
					// make attendance record report
					ReportUtil.makeReport(request, paramBean); 
				 
			}
			
			if (reportType.equals("detailStatisticsReport")) 
			{ 
				parameterObject.setString("CPNY_ID", admin.getCpnyId());
				List itemlist = arServices.retrieveArItem(parameterObject);
				parameterObject.setString("deptID", deptID);
				String detail_started = StringUtil.checkNull(request.getParameter("detail_started")) ;
				String detail_end = StringUtil.checkNull(request.getParameter("detail_end")) ;
				parameterObject.setString("EmpDivision", request.getParameter("EmpDivision"));
				String title = "" ;
				//明细统计表
				if(detail_started != null && detail_started.length() > 0)
				{
					parameterObject.setString("detail_started", request.getParameter("detail_started")) ;
					parameterObject.setString("detail_end", request.getParameter("detail_end")) ;
					title = detail_started + " ---- " + detail_end + " " + messageSource.getMessage("report.mutual.title.detailed_statement") ; 
				}
				else{
					title = arMonth.substring(0, 4) + "-" + arMonth.substring(4) + " " + messageSource.getMessage("report.mutual.title.detailed_statement") ; 
					parameterObject.setString("arMonth", arMonth);
				}
				
				request.setAttribute("itemList", itemlist) ;
				request.setAttribute("title", title) ;
				request.setAttribute("parameterObject",parameterObject);
				url="reports/ar_report/detailStatisticsReport.jsp";

			}
			
			if (reportType.equals("dailyDetailStatisticsReport")) 
			{ 
				String dateStarted = StringUtil.checkNull(request.getParameter("dateStarted")) ;
				parameterObject.setString("deptID", deptID);
				parameterObject.setString("dateStarted", dateStarted);
				
				request.setAttribute("parameterObject",parameterObject);
			    url="reports/ar_report/dailyDetailStatisticsReport.jsp";
			}
			
			if (reportType.equals("dailyDetailStatisticsSectionReport")) 
			{ 
				String deptid_section = StringUtil.checkNull(request.getParameter("deptid_section")) ;
				String dateStarted = StringUtil.checkNull(request.getParameter("dateStarted")) ;
				parameterObject.setString("deptID", deptid_section);
				parameterObject.setString("dateStarted", dateStarted);
				
				request.setAttribute("parameterObject",parameterObject);
			    url="reports/ar_report/dailyDetailStatisticsSectionReport.jsp";
			}
			
			if (reportType.equals("dailyDetailOverall")) 
			{ 
				String dateStarted = StringUtil.checkNull(request.getParameter("dateStarted")) ;

				
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");		
				Date d = format1.parse(dateStarted) ;
				
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
				dateStarted = format2.format(d);

				Calendar c = Calendar.getInstance() ;
				c.setTime(d) ;
				
				c.add(Calendar.DATE, -1) ;
				String otEnd = format2.format(c.getTime()) ;
				String otStart = otEnd ;
				
				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
				{
					c.add(Calendar.DATE, -1) ;
					otStart = format2.format(c.getTime()) ;
				}
				
				
				parameterObject.setString("dateStarted", dateStarted) ;
				parameterObject.setString("otStart", otStart) ;
				parameterObject.setString("otEnd", otEnd) ;
				
				request.setAttribute("parameterObject",parameterObject);
			    url="reports/ar_report/dailyDetailOverall.jsp";
			}
			
			if (reportType.equals("dailyDetailStatistics")) 
			{ 
				String dateStarted = StringUtil.checkNull(request.getParameter("dateStarted")) ;

				
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");		
				Date d = format1.parse(dateStarted) ;
				
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
				dateStarted = format2.format(d);

				Calendar c = Calendar.getInstance() ;
				c.setTime(d) ;
				
				c.add(Calendar.DATE, -1) ;
				String otEnd = format2.format(c.getTime()) ;
				String otStart = otEnd ;
				
				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
				{
					c.add(Calendar.DATE, -1) ;
					otStart = format2.format(c.getTime()) ;
				}
				
				
				parameterObject.setString("dateStarted", dateStarted) ;
				parameterObject.setString("otStart", otStart) ;
				parameterObject.setString("otEnd", otEnd) ;
				
				request.setAttribute("parameterObject",parameterObject);
			    url="reports/ar_report/dailyDetailStatistics.jsp";
			}
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Export attendance report Exception. " + e.toString());
			throw new GlRuntimeException(
					"Export attendance report Exception.", e);
		}

		return url;

	}

	private String empCalendar(HttpServletRequest request) {
		try {
			AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
			String empId = StringUtil.checkNull(request.getParameter("empID"),admin.getUsername());
			String personId = StringUtil.checkNull(request.getParameter("person_id"));
			
			if (personId == null || personId.length() == 0){
				SimpleMap parameterObject = new SimpleMap() ;
				parameterObject.setString("empId", empId) ;
				parameterObject.setString("cnpyId", admin.getCpnyId()) ;
				parameterObject.setString("supervisorId", admin.getAdminID()) ;
				
				personId = hrmServices.retrieveHrPersonId(parameterObject) ;
			}
			
			MessageSource ms = new MessageSource("ar",admin.getLocale(),"UTF-8");
			String gong = ms.getMessage("alert.att.maintenance.pattern.w");//工
			String xiu = ms.getMessage("alert.att.maintenance.pattern.r");//休
			String jie = ms.getMessage("alert.att.maintenance.pattern.h");//节
			String gong1 = ms.getMessage("alert.att.maintenance.pattern.c");//公
			String ban = ms.getMessage("alert.att.maintenance.pattern.e");//班

			//"工" 休 节
			String dayTypes[] = { gong, "<font color=\"red\">"+xiu+"</font>","<font color=\"red\">"+jie+"</font>" };
			//"公", "班"
			String calendarTypes[] = { gong1, ban };
			
			GregorianCalendar date = new GregorianCalendar();
			// 将日期设置为1号, 避免当前日期是31号时月份加减造成混乱
			date.set(GregorianCalendar.DAY_OF_MONTH, 1);
			int year = date.get(GregorianCalendar.YEAR);
			int month = date.get(GregorianCalendar.MONTH) + 1;
			if (request.getParameter("year") != null && request.getParameter("month") != null) {
				year = Integer.parseInt(request.getParameter("year"));
				month = Integer.parseInt(request.getParameter("month"));
				date.set(GregorianCalendar.YEAR, year);
				date.set(GregorianCalendar.MONTH, month - 1);
			}

			List arShiftList = arServices.getShiftList();
			List calendarList = arServices.getEmpCalendarList(personId, year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length()), admin.getAdminID(), admin.getCpnyId());
			
			request.setAttribute("arShiftList", arShiftList);
			request.setAttribute("calendarList", calendarList);
			request.setAttribute("employee", hrmServices.retrieveBasicInfo(personId));
			request.setAttribute("dayTypes", dayTypes);
			request.setAttribute("calendarTypes", calendarTypes);
			request.setAttribute("yearMonth", date);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_workcalendar.jsp";
	}

	/**
	 * dynamicGroup
	 * 
	 * @return String
	 */
	private String dynamicGroupAdd(HttpServletRequest request) {
		return "/ar_set_dynamicgroup_a.jsp";
	}

	/**
	 * supervisor
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	public String supervisor(HttpServletRequest request) {
		try {

			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);	
			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = arServices.getAllSupervisorCount(parameterObject);
			List supervisorList = arServices.getAllSupervisorList(parameterObject,currentPage,pageSize);
			request.setAttribute("key",request.getParameter("key"));
			request.setAttribute("personId",request.getParameter("personId"));
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("supervisorList", supervisorList);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_maintenance_supervisor_v.jsp";
	}

	/**
	 * supervisorAdd
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String supervisorAdd(HttpServletRequest request,AdminBean admin) {
		String empID = StringUtil.checkNull(request.getParameter("empID"));
		SimpleMap sm= new SimpleMap();
		sm.set("cpnyId", admin.getCpnyId());
		try {
			// to pass the department tree to jsp page
			request.setAttribute("dept_list", hrmServices.retrieveDeptTree(sm));

			request.setAttribute("supervisor", hrmServices.retrieveBasicInfo(empID));
			
			request.setAttribute("itemGroupList", hrmServices.retrieveItemGroup(sm));
			
			request.setAttribute("itemGroupCut", hrmServices.retrieveItemGroupCut(sm).toString());
			
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_maintenance_supervisor_a.jsp";
	}

	/**
	 * supervisorUpdate
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String supervisorUpdate(HttpServletRequest request,AdminBean admin) {
		String supervisorId = request.getParameter("superVisorID") != null ? request.getParameter("superVisorID") : "";
		
		try {
			SimpleMap sm= new SimpleMap();
			sm.set("cpnyId", admin.getCpnyId());
			request.setAttribute("dept_list", hrmServices.retrieveDeptTree(sm));
			List supervisorInfoList = arServices.getSupervisorInfoList(supervisorId);
			request.setAttribute("supervisorInfoList", supervisorInfoList);
			Supervisor supervisor = arServices.getSupervisorById(supervisorId);
			request.setAttribute("selectSupervisor", supervisor);
			List deptListWithObject = arServices.getDeptListWithObject(supervisorId);
			request.setAttribute("deptListWithObject", deptListWithObject);
			
			request.setAttribute("supervisor_item", arServices.getSupervisorItems(supervisorId));
			request.setAttribute("item_group", hrmServices.retrieveItemGroup(sm));
			request.setAttribute("item_group_cut", hrmServices.retrieveItemGroupCut(sm));
		} catch (Exception ex) {
			ex.printStackTrace() ;
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_maintenance_supervisor_m.jsp";
	}

	/**
	 * supervisorDelete
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String supervisorDelete(HttpServletRequest request) {
		try {
            SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);	
			int pageSize =10;
			int pageGroupSize =10;
			int currentPage = 0;
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
	       //取得数据行数
			int resultCount = arServices.getAllSupervisorCount(parameterObject);
			List supervisorList = arServices.getAllSupervisorList(parameterObject,currentPage,pageSize);			
			request.setAttribute("supervisorList", supervisorList);
			request.setAttribute("key",request.getParameter("key"));
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return "/ar_maintenance_supervisor_d.jsp";
	}

	/**
	 * fill detail list for page table
	 * 
	 * @param detailList
	 */
	private void fillDetailList(List detailList) {

		int flag = 0;
		for (int j = 29; j <= 31; j++) {

			flag = 0;
			Iterator iter = detailList.iterator();

			while (iter.hasNext()) {

				ArDetail detail = (ArDetail) iter.next();
				if (detail.getDate_day().intValue() == j) {
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				continue;

			ArDetail temp = new ArDetail();
			temp.setDate_day(j);
			detailList.add(temp);
		}
	}
	
	/**
	 * download employee shift template
	 * 
	 * @param request
	 * @return
	 */
	private String downloadEmpShiftTemplate(HttpServletRequest request) {
		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		String fileName = "EMP_SHIFT_DATA.xls";

		String sheetName = "SHIFT_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString(messageSource.getMessage("report.global.title.empID"), "") ;
		columns.setString("班次", "") ;
		columns.setString("日期", "") ;

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}
	
	
	/**
	 * download ar ot detail template
	 * 
	 * @param request
	 * @return
	 */
	private String downloadArOtDetailTemplate(HttpServletRequest request) {
		
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		
		String fileName = "AR_OT_DETIAL_DATA.xls";

		String sheetName = "AR_OT_DETIAL_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString("日期", "") ;
		columns.setString(messageSource.getMessage("report.global.title.empID"), "") ;
		columns.setString("班次", "") ;
		columns.setString("项目", "") ;
		columns.setString("开始时间", "") ;
		columns.setString("结束时间", "") ;
		columns.setString("长度", "") ;
		columns.setString("日期类型", "") ;
		columns.setString("考勤月", "") ;
		

		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}
	
	
}
