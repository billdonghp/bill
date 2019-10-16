package com.ait.ar.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.bean.SupervisorInfo;
import com.ait.ar.business.ArServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.bean.BasicInfo;
import com.ait.hrm.business.HrmServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.OperationStatus;
import com.ait.util.StringUtil;
import com.ait.util.NumberUtil;
import com.ait.web.Command;

public class ArModifyCommand implements Command {
	private ArServices arServices;

	private HrmServices hrmServices;

	public ArModifyCommand() {

		arServices = new ArServices();

		hrmServices = HrmServices.getInstance();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String returnPage = null;

		String content = request.getParameter("content") != null ? request
				.getParameter("content") : "";// 从request中得到要添加的内容类型
		if (content.equals("groupCondition")) {
			returnPage = this.modifyGroupCondition(request);
		} else if (content.equals("supervisorInfo")) {
			returnPage = this.modifySupervisorInfo(request);
		} else if (content.equals("supervisedEmp")) {
			returnPage = this.modifySupervisedEmp(request);
		} else if (content.equals("empCalendar")) {
			returnPage = this.modifyEmpCalendar(request);
		} else if (content.equals("detail")) {
			returnPage = this.modifyDetail(request);
		} else if (content.equals("annual")) { // 修改年假
			returnPage = this.modifyAnnual(request);
		} else if (content.equals("dailyStatus")) { // 更新日考勤状态
			returnPage = this.modifyDailyStatus(request);
		} else if (content.equals("monthlyStatus")) { // 更新月考勤状态
			returnPage = this.modifyMonthlyStatus(request);
		} else if (content.equals("payStatus")) { // 更新工资状态
			returnPage = this.modifyPayStatus(request);
		} else {
			returnPage = "/error.jsp";
		}

		Logger.getLogger(getClass()).debug("return Page is :" + returnPage);
		return returnPage;
	}

	/**
	 * modify attendance detail
	 * 
	 * @param request
	 * @return String
	 */
	private String modifyDetail(HttpServletRequest request) {

		String sDate = StringUtil.checkNull(request.getParameter("sDate"));
		String eDate = StringUtil.checkNull(request.getParameter("eDate"));
		String key = (request.getParameter("key"));
		String deptid = (request.getParameter("deptid"));
		String currentPage = StringUtil.checkNull(request.getParameter("currentPage"));
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		
		List errorList = new ArrayList() ;
		try {
			if (sDate.equals("")) {
				sDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
				eDate = sDate;
			}

			errorList = arServices.modDetail(request,admin);
			
			request.setAttribute("errorList", errorList) ;

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("modify attendance detail  Exception. ", e);
		}

		return "arControlServlet?currentPage=" + currentPage
				+ "&operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&sDate="
				+ sDate + "&eDate=" + eDate + "&key=" + key + "&deptid=" + deptid ;
			//+ "&itemNo" + itemNo + "&sLength" + sLength + "&eLength" + eLength ;
	}

	/**
	 * modify annual holidy
	 * 
	 * @param request
	 * @return String
	 */
	private String modifyAnnual(HttpServletRequest request) {

		// 取得当前查询的参数
		String key = (request.getParameter("key"));
		int year = Integer.parseInt(request.getParameter("year"));
		String currentPage = StringUtil.checkNull(request
				.getParameter("currentPage"));
		int currentListCnt = Integer.parseInt(request
				.getParameter("currentListCnt"));

		List annualList = new ArrayList();

		try {

			// bind append form data to list object
			for (int i = 1; i <= currentListCnt; i++) {

				if (request.getParameter("annual_" + i) != null) {

					Annual annual = new Annual();
					ObjectBindUtil.setFormBean(request, annual, "_" + i, null);
					annualList.add(annual);
				}

			}

			arServices.updateAnnualHolidy(annualList, true);

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("modify annual holidy  Exception. ", e);
		}

		request.setAttribute("url", "arControlServlet?currentPage="
				+ currentPage
				+ "&operation=ar_pagecontrol&page=annual_v&menu_code="
				+ request.getParameter("menu_code") + "&key=" + key + "&year="
				+ year);

		return "/inc/alertMessage.jsp";
	}

	/**
	 * modify daily status
	 * 
	 * @param request
	 * @return String
	 */
	private String modifyDailyStatus(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String PA_MONTH_STR = year + month;
		String statTypeCode = request.getParameter("STAT_TYPE_CODE") ;	
		int ATT_MO_FLAG = Integer.parseInt(request.getParameter("status")) == 0 ? 1 : 0;
		String cpnyId =request.getParameter("cpnyId");

		parameterObject.setString("PA_MONTH_STR", PA_MONTH_STR);
		parameterObject.setString("STAT_TYPE_CODE", statTypeCode);
		parameterObject.setInt("ATT_MO_FLAG", ATT_MO_FLAG);
		parameterObject.setString("cpnyId", cpnyId);

		try {

			arServices.updateDailyAttStatus(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("modify daily status Exception. ", e);
		}

		request.setAttribute("url",
				"arControlServlet?operation=ar_pagecontrol&page=attStatus_v&menu_code="
						+ request.getParameter("menu_code") + "&month=" + month
						+ "&year=" + year);

		return "/inc/alertMessage.jsp";
	}

	/**
	 * modify monthly status
	 * 
	 * @param request
	 * @return String
	 */
	private String modifyMonthlyStatus(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String PA_MONTH_STR = year + month;
		String statTypeCode = request.getParameter("STAT_TYPE_CODE") ;		
		int ATT_MO_LOCK_FLAG = Integer.parseInt(request.getParameter("status")) == 0 ? 1 : 0;
		String cpnyId =request.getParameter("cpnyId");

		parameterObject.setString("PA_MONTH_STR", PA_MONTH_STR);
		parameterObject.setString("STAT_TYPE_CODE", statTypeCode);
		parameterObject.setInt("ATT_MO_LOCK_FLAG", ATT_MO_LOCK_FLAG);
		parameterObject.setInt("ATT_MO_FLAG", 1);
		parameterObject.set("cpnyId", cpnyId);

		try {

			if (ATT_MO_LOCK_FLAG == 0) {

				// 如果月考勤解锁，日考勤状态不变
				arServices.updateMonthlyAttStatus(parameterObject);
			} else {

				// 如果月考勤锁定，日考勤强行进行锁定
				arServices.updateAttStatus(parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("modify monthly status Exception. ", e);
		}

		request.setAttribute("url",
				"arControlServlet?operation=ar_pagecontrol&page=attStatus_v&menu_code="
						+ request.getParameter("menu_code") + "&month=" + month
						+ "&year=" + year);

		return "/inc/alertMessage.jsp";
	}

	/**
	 * modify pay status
	 * 
	 * @param request
	 * @return String
	 */
	private String modifyPayStatus(HttpServletRequest request) {

		SimpleMap parameterObject = new SimpleMap();

		String statTypeCode = request.getParameter("STAT_TYPE_CODE") ;		
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String PA_MONTH_STR = year + month;
		String cpnyId=request.getParameter("cpnyId");
		int PA_LOCK_FLAG = NumberUtil.parseInt(request.getParameter("status")) == 0 ? 1 : 0;

		parameterObject.setString("PA_MONTH_STR", PA_MONTH_STR);
		parameterObject.setString("STAT_TYPE_CODE", statTypeCode);
		parameterObject.setInt("PA_LOCK_FLAG", PA_LOCK_FLAG);
		parameterObject.setInt("ATT_MO_LOCK_FLAG", 1);
		parameterObject.setInt("ATT_MO_FLAG", 1);
		parameterObject.set("cpnyId", cpnyId);

		try {

			if (PA_LOCK_FLAG == 0) {
				// 如果工资解锁，月考勤、日考勤状态不变
				arServices.updateMonthlyPayStatus(parameterObject);
			} else {
				// 如果工资锁定，月考勤、日考勤强行进行锁定
				arServices.updatePayStatus(parameterObject);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("modify pay status Exception. ", e);
		}

		request.setAttribute("url", "arControlServlet?operation=ar_pagecontrol&page=attStatus_v&menu_code=" + request.getParameter("menu_code") + "&month=" + month + "&year=" + year);

		return "/inc/alertMessage.jsp";
	}

	private String modifyEmpCalendar(HttpServletRequest request) {
		try {
			arServices.modEmpCalendar(request);
			
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		return "arControlServlet?operation=ar_pagecontrol&page=ec_v&menu_code=ar0101";
	}

	private String modifyGroupCondition(HttpServletRequest request) {

		OperationStatus operationStatus = new OperationStatus();
		String groupNo = request.getParameter("groupNo");
		String condition_no = request.getParameter("condition_no");
		String field1 = request.getParameter("field1");
		String field2 = request.getParameter("field2");
		AdminBean admin = (AdminBean) request.getSession()
		.getAttribute("admin");
		try {
			arServices.modGroupCondition(condition_no, field1, field2);
			groupNo = groupNo.equals("") ? "-1" : groupNo;
			List groupConditionList = arServices.getConditionList(Integer
					.parseInt(groupNo));
			request.setAttribute("groupConditionList", groupConditionList);
			List dynamicGroupList = arServices.getGroupList(admin.getCpnyId());
			request.setAttribute("dynamicGroupList", dynamicGroupList);
			List conditionList = arServices.getConditionList();
			request.setAttribute("conditionList", conditionList);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		request.setAttribute("operationStatus", operationStatus);// 传给界面，界面接受后，判断执行情况
		return "/ar_set_dynamicgroup.jsp";
	}

	/**
	 * modifySupervisorInfo
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String modifySupervisorInfo(HttpServletRequest request) {
		String supervisorId = StringUtil.checkNull(request
				.getParameter("superVisorID"));
		String size = StringUtil.checkNull(request.getParameter("size"), "");
		size = size.equals("") ? "0" : size;
		String delObjectId[] = request.getParameterValues("delDeptid");
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		try {
			int itemSize = Integer.parseInt(request.getParameter("itemSize"));
			StringBuffer itemstr = new StringBuffer();
			for(int i=0;i<itemSize;i++){
				String itemId = request.getParameter("itemCheck"+i);
				if(itemId != null){
					itemstr.append(itemId);
					itemstr.append(",");
				}
			}
			arServices.upDateItemId(supervisorId, itemstr.toString());
			
			for (int i = 0; i < Integer.parseInt(size); i++) {
				String deptId = StringUtil.checkNull(request
						.getParameter("dept_id" + i), "");
				if (!deptId.equals("")) {
					SupervisorInfo supervisorInfo = new SupervisorInfo();
					supervisorInfo.setSupervisorID(supervisorId);
					supervisorInfo.setSupervisedDeptID(deptId);
					supervisorInfo.setCreatedBy(admin.getAdminID());
					arServices.addSupervisorInfo(supervisorInfo);
				}
			}
			if (delObjectId != null) {
				for (int i = 0; i < delObjectId.length; i++) {
					SupervisorInfo supervisorInfo = new SupervisorInfo();
					supervisorInfo.setSupervisorID(supervisorId);
					supervisorInfo.setSupervisedDeptID(delObjectId[i]);
					arServices.deleteSupervisorInfo(supervisorInfo);
				}
			}
			
			arServices.editSupervisorScreenGrantNo(supervisorId) ;
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		ArPageControlCommand arpage= new ArPageControlCommand();
		return arpage.supervisor(request);
	}

	/**
	 * modifySupervisorInfo
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String modifySupervisedEmp(HttpServletRequest request) {
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		String supervisorId = StringUtil.checkNull(request
				.getParameter("supervisorID"));
		String size = StringUtil.checkNull(request.getParameter("size"), "");
		size = size.equals("") ? "0" : size;
		String delObjectId[] = request.getParameterValues("delEmpId");
		try {
			for (int i = 0; i < Integer.parseInt(size); i++) {
				String empid = StringUtil.checkNull(request
						.getParameter("empid" + i));
				if (!empid.equals("")) {
					SupervisorInfo supervisorInfo = new SupervisorInfo();
					supervisorInfo.setSupervisorID(supervisorId);
					supervisorInfo.setSupervisedDeptID(empid);
					supervisorInfo.setCreatedBy(admin.getAdminID());
					arServices.addSupervisorInfo(supervisorInfo);
				}
			}
			if (delObjectId != null) {
				for (int i = 0; i < delObjectId.length; i++) {
					SupervisorInfo supervisorInfo = new SupervisorInfo();
					supervisorInfo.setSupervisorID(supervisorId);
					supervisorInfo.setSupervisedDeptID(delObjectId[i]);
					arServices.deleteSupervisorInfo(supervisorInfo);
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		ArPageControlCommand arpage= new ArPageControlCommand();
		return arpage.supervisor(request);
	}

}
