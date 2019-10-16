package com.ait.ar.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.bean.DynamicGroup;
import com.ait.ar.bean.Supervisor;
import com.ait.ar.bean.SupervisorInfo;
import com.ait.ar.business.ArServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.OperationStatus;
import com.ait.util.StringUtil;
import com.ait.web.Command;

/**
 * <p>
 * Title: Ar add command
 * </p>
 * 
 * <p>
 * Description: 添加各种维护信息
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class ArAddCommand implements Command {
	private String adminId;

	private String content; // 要添加的内容类型

	private ArServices arServices;

	public ArAddCommand() {
		arServices = new ArServices();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String transmitPage = null;
		if (session == null) {
			transmitPage = "/expired.jsp";
		} else {
			AdminBean admin = (AdminBean) session.getAttribute("admin");
			if (admin == null) {
				transmitPage = "/expired.jsp";
			} else {
				adminId = admin.getAdminID();
				content = request.getParameter("content") != null ? request
						.getParameter("content") : "";// 从request中得到要添加的内容类型
				if (content.equals("dynamicGroup")) {
					this.addDynamicGroup(request);
					transmitPage = "/ar_set_dynamicgroup.jsp";
				} else if (content.equals("groupCondition")) {
					this.addGroupCondition(request);
					transmitPage = "/ar_set_dynamicgroup.jsp";
				} else if (content.equals("supervisor")) {
					transmitPage = this.addSupervisor(request, response);
				} else if (content.equals("supervisedEmp")) {
					transmitPage = this.addSupervisedEmp(request, response);
				} else if (content.equals("detail")) {
					transmitPage = this.addDetail(request);
				} else if (content.equals("annual")) { // 添加年假
					transmitPage = this.addAnnual(request);
				} else {
					transmitPage = "/error.jsp"; 
				}
			}
		}
		Logger.getLogger(getClass()).debug("transmitPage :" + transmitPage);
		return transmitPage;
	}

	/**
	 * add attendance detail
	 * 
	 * @param request
	 * @return String
	 */
	private String addDetail(HttpServletRequest request) {
		
		String sDate = StringUtil.checkNull(request.getParameter("sDate"));
		String eDate = StringUtil.checkNull(request.getParameter("eDate"));
		String key = StringUtil.toCN((request.getParameter("key")));
		String deptid = StringUtil.toCN((request.getParameter("deptid")));
		String currentPage = StringUtil.checkNull(request.getParameter("currentPage"));
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		
		List errorList = new ArrayList() ;
		try {
			errorList = arServices.addDetail(request,admin);
			
			if (sDate.equals("")) {
				sDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
				eDate = sDate;
			}
			
			request.setAttribute("errorList", errorList) ;
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("insert attendance detail  Exception. ", e);
		}
		return "arControlServlet?currentPage="
				+ currentPage
				+ "&operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&sDate="
				+ sDate + "&eDate=" + eDate + "&key=" + key + "&deptid=" + deptid ;
				//+ "&itemNo" + itemNo + "&sLength" + sLength + "&eLength" + eLength ;
	}

	/**
	 * add annual holidy
	 * 
	 * @param request
	 * @return String
	 */
	private String addAnnual(HttpServletRequest request) {

		String key = (request.getParameter("empID"));
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		int holidyDate = Integer.parseInt(request.getParameter("holidyDate"));

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("supervisor", admin.getAdminID());
		parameterObject.setString("holidyDate", holidyDate + "");
		parameterObject.setString("condition", key);

		Annual annual = new Annual();

		try {

			// 绑定表单数据到bean对象
			ObjectBindUtil.setFormBean(request, annual);
			annual.setCreatedBy(admin.getAdminID());


			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			String message = messageSource.getMessage("alert.mutual.save_successfully");
						
			// 取得数据行数
			int resultCount = arServices
					.retrieveAnnualHolidyCnt(parameterObject);

			// 验证是否存在该人年假数据,以及是否有对此人操作的考勤权限
			if (resultCount == 0) {

				// 插入年假数据
				arServices.insertAnnualHolidy(annual);
				request.setAttribute("alert", message);
			} else {
				 messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");//ar
				 message = messageSource.getMessage("alert.att.maintenance.pattern.vacation_exis");
				request.setAttribute("alert", message);
			}

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("insert annual holidy  Exception. ", e);
		}
		request.setAttribute("url",
				"arControlServlet?operation=ar_pagecontrol&page=annual_v&menu_code="
						+ request.getParameter("menu_code"));

		return "/inc/alertMessage.jsp";
	}

	/**
	 * addDynamicGroup
	 * 
	 * @param request
	 *            HttpServletRequest
	 */
	private void addDynamicGroup(HttpServletRequest request) {
		OperationStatus operationStatus = new OperationStatus();
		String description = StringUtil.checkNull(request
				.getParameter("description"));
		String groupName = StringUtil.checkNull(request
				.getParameter("groupName"));
		String groupEnName = StringUtil.checkNull(request
				.getParameter("groupEnName"));
		String groupKoName = StringUtil.checkNull(request
				.getParameter("groupKoName"));
		String groupProperty = StringUtil.checkNull(request
				.getParameter("groupProperty"));
		String sysGroupType = StringUtil.checkNull(request
				.getParameter("sysGroupType"));
		String groupQualification = StringUtil.checkNull(request
				.getParameter("groupQualification"));
		AdminBean admin = (AdminBean) request.getSession()
		.getAttribute("admin");
		MessageSource messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
		String message1 = messageSource.getMessage("alert.att.name_empty_valid");
		if (groupName.equals("")) {
			operationStatus.setSuccess(false);
			operationStatus.setMessage(message1);
		} else {
			DynamicGroup dynamicGroup = new DynamicGroup();
			dynamicGroup.setDescription(description);
			dynamicGroup.setGroupName(groupName);
			dynamicGroup.setGroupEnName(groupEnName);
			dynamicGroup.setGroupKoName(groupKoName);
			dynamicGroup.setCreatedBy(adminId);
			dynamicGroup.setGroupProperty(groupProperty);
			dynamicGroup.setSysGroupType(sysGroupType);                         
			dynamicGroup.setGroupQualification(groupQualification);
			dynamicGroup.setCpny_id(admin.getCpnyId());

			String message = "";
			try {
				int status = arServices.addDynamicGroup(dynamicGroup, message1);     
				switch (status) {
				case 1: {
					message = messageSource.getMessage("alert.att.add_group");
					operationStatus.setSuccess(true);
					break;
				}
				case 2: {
					message =messageSource.getMessage("alert.att.add_failed");
					operationStatus.setSuccess(false);
					break;
				}
				case 3: {
					message = messageSource.getMessage("alert.att.group_exist_fail");
					operationStatus.setSuccess(false);
					break;
				}
				default: {
					message =messageSource.getMessage("alert.att.fail_to_add");
					operationStatus.setSuccess(false);
					break;
				}
				}
				List dynamicGroupList = arServices.getGroupList(admin.getCpnyId());
				request.setAttribute("dynamicGroupList", dynamicGroupList);
				List conditionList = arServices.getConditionList();
				request.setAttribute("conditionList", conditionList);
				operationStatus.setMessage(message);
				request.setAttribute("operationStatus", operationStatus);// 传给界面，界面接受后，判断执行情况
			} catch (Exception ex) {
				Logger.getLogger(getClass()).error(ex.toString());
			}
		}
	}

	// 向动态组中添加人员
	private void addGroupCondition(HttpServletRequest request) {
		OperationStatus operationStatus = new OperationStatus();
		String groupNo = request.getParameter("groupNo");
		String[] condition_no = request.getParameterValues("condition_no");
		String[] relation = request.getParameterValues("relation");
		String[] field1 = request.getParameterValues("field1");
		String[] field2 = request.getParameterValues("field2");
		AdminBean admin = (AdminBean) request.getSession()
		.getAttribute("admin");
		try {
			arServices.addGroupCondition(groupNo, condition_no, relation,
					field1, field2);
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
	}

	/**
	 * addSupervisedEmp
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return String
	 */
	private String addSupervisedEmp(HttpServletRequest request,
			HttpServletResponse response) {

		String supervisorId = StringUtil.checkNull(request
				.getParameter("supervisorID"), "");
		Supervisor supervisor = new Supervisor();
		supervisor.setSupervisorId(supervisorId);
		supervisor.setCreatedBy(adminId);
		try {
			arServices.addSupervisor(supervisor);
			String size = StringUtil
					.checkNull(request.getParameter("size"), "");
			size = size.equals("") ? "0" : size;
			for (int i = 0; i < Integer.parseInt(size); i++) {
				String empid = StringUtil.checkNull(request
						.getParameter("empid" + i));
				if (!empid.equals("")) {
					SupervisorInfo supervisorInfo = new SupervisorInfo();
					supervisorInfo.setSupervisorID(supervisorId);
					supervisorInfo.setSupervisedDeptID(empid);
					supervisorInfo.setCreatedBy(adminId);
					arServices.addSupervisorInfo(supervisorInfo);
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		ArPageControlCommand arpage= new ArPageControlCommand();
		return arpage.supervisor(request);
	}

	/**
	 * supervisor
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	private String addSupervisor(HttpServletRequest request,HttpServletResponse response) {
		
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		String supervisorId = StringUtil.checkNull(request.getParameter("supervisorID"), "");
		Supervisor supervisor = new Supervisor();
		supervisor.setSupervisorId(supervisorId);
		supervisor.setCreatedBy(adminId);
		
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
			supervisor.setItemName(itemstr.toString());
			arServices.addSupervisor(supervisor);
			
			String size = StringUtil.checkNull(request.getParameter("size"), "");
			size = size.equals("") ? "0" : size;
			for (int i = 0; i < Integer.parseInt(size); i++) {
				String deptId = StringUtil.checkNull(request.getParameter("dept_id" + i), "");
				if (!deptId.equals("")) {
					SupervisorInfo supervisorInfo = new SupervisorInfo();
					supervisorInfo.setSupervisorID(supervisorId);
					supervisorInfo.setSupervisedDeptID(deptId);
					supervisorInfo.setCreatedBy(adminId);
					arServices.addSupervisorInfo(supervisorInfo);
				}
			}
			
			//arServices.editSupervisorScreenGrantNo(supervisorId) ;
						
			List supervisorList = arServices.getAllSupervisor(adminBean.getCpnyId());
			request.setAttribute("supervisorList", supervisorList);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		ArPageControlCommand arpage= new ArPageControlCommand();
		return arpage.supervisor(request);		
	}

}
