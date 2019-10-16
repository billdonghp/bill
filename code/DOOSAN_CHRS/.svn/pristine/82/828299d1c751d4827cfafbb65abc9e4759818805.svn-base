package com.ait.safe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.safe.business.JobHealthServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

public class JobHealthCommand implements Command {
	private JobHealthServices jobHealthServices;

	private SimpleMap parameterObject;

	private String content = null;

	public JobHealthCommand() {
		jobHealthServices = new JobHealthServices();
	}
	

	public String execute(HttpServletRequest request,// 捕获调用方法抛出的异常减少调用方法异常处理代码
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		content = request.getParameter("content");// 从request中得到想要查看的内容
		if (content != null) {
			
			if (content.equals("jobHealthSearch")) {
				returnPage = this.jobHealthSearch(request);
			} else if (content.equals("addPositionInformationView")) {
				returnPage = this.addPositionInformationView(request);
			} else if (content.equals("addPositionInformation")) {
				returnPage = this.addPositionInformation(request);
			} else if (content.equals("updatejobHealthView")) {
				returnPage = this.updatejobHealthView(request);
			} else if (content.equals("updatePositionInformation")) {
				returnPage = this.updatePositionInformation(request);
			} else if (content.equals("deletejobHealthView")) {
				returnPage = this.deletejobHealthView(request);
			} else if (content.equals("searchDetail")) {
				returnPage = this.searchDetail(request);
			} else if (content.equals("Jump")) {
				returnPage = this.Jump(request);
			}
			
			
			else if (content.equals("PositionDiseaseDiseaseType")) {
				returnPage = this.PositionDiseaseDiseaseType(request);
			} else if (content.equals("PositionDiseaseDiseaseTypeAddView")) {
				returnPage = this.PositionDiseaseDiseaseTypeAddView(request);
			} else if (content.equals("PositionDiseaseDiseaseTypeAdd")) {
				returnPage = this.PositionDiseaseDiseaseTypeAdd(request);
			} else if (content.equals("PositionDiseaseDiseaseTypeUpdateView")) {
				returnPage = this.PositionDiseaseDiseaseTypeUpdateView(request);
			} else if (content.equals("PositionDiseaseDiseaseTypeUpdate")) {
				returnPage = this.PositionDiseaseDiseaseTypeUpdate(request);
			} else if (content.equals("deletePositionDiseaseDiseaseType")) {
				returnPage = this.deletePositionDiseaseDiseaseType(request);
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

	private String jobHealthSearch(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		String empName = request.getParameter("empName");
		request.setAttribute("empName", empName);
		String empId = request.getParameter("empID");
		request.setAttribute("empId", empId);
		String empIds = request.getParameter("empID");
		String POSITION = request.getParameter("POSITION");
		request.setAttribute("POSITION", POSITION);
		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		String deptId = request.getParameter("deptID");
		request.setAttribute("deptId", deptId);
		String medicalflag = request.getParameter("medicalflag");

		request.setAttribute("medicalflagg", medicalflag);
		
		String flag = StringUtil.checkNull(request.getParameter("flag"),"2");
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
			parameterObject.set("empName", empName);
			parameterObject.set("empId", empId);
			parameterObject.set("empId", empIds);
			parameterObject.set("POSITION", POSITION);
			parameterObject.set("StartDate", StartDate);
			parameterObject.set("EndDate", EndDate);
			parameterObject.set("deptId", deptId);
			parameterObject.set("medicalflag", medicalflag);
			parameterObject.set("cpny_id", cpny_id);
			
			// retrieve attendance record list

			// 得到所有职业健康监护档案
			int jobHealthInformationCount = jobHealthServices
					.JobHealthInformationCount(parameterObject);

			// 得到所有职业健康监护档案信息
			List jobHealthInformation = new ArrayList();
			if(flag.equals("1")){
				jobHealthInformation = jobHealthServices.JobHealthInformationReport(
						parameterObject);
			}else{
				jobHealthInformation = jobHealthServices.JobHealthInformation(
						parameterObject, currentPage, pageSize);
			}
			
			
//			for (int i = 0; i < jobHealthInformation.size(); i++) {
//				map = (SimpleMap) jobHealthInformation.get(i);
//				String employeeId = map.getString("PERSON_ID");				
//				parameterObject.set("employeeId", employeeId);
//				parameterObject.set("JOB_POSITION_ID", map.getString("JOB_POSITION_ID"));
//				parameterObject.set("job_position_name_code", map.getString("JOB_POSITION_NAME_CODE"));
//				parameterObject.set("medicalflag", medicalflag);
//				
//				String medicalFlag = jobHealthServices.getMedicalFlag(parameterObject);
//				map.set("medicalFlag", medicalFlag);
//			}
			request.setAttribute("jobHealthInformation", jobHealthInformation);

			// 得到所有岗位信息
			List POSITION_INFORMATION = jobHealthServices
					.getPositionInformation(parameterObject);
			request.setAttribute("POSITION_INFORMATION", POSITION_INFORMATION);
			List JOB_POSITION_INFORMATION = jobHealthServices
					.getJobPositionInformation(parameterObject);

			request.setAttribute("JOB_POSITION_INFORMATION",
					JOB_POSITION_INFORMATION);

			request.setAttribute("resultCount", NumberUtil
					.parseInt(jobHealthInformationCount));

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		if(flag.equals("1")){
			return "/reports/safe_report/visiterReport.jsp";
		}else if(flag.equals("2")){
			return "/safe_jobHealth_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
		}
		return flag;
	}

	private String addPositionInformationView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		
		SimpleMap map = null;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);

			// 得到所有岗位信息
			List POSITION_INFORMATION = jobHealthServices
					.getPositionInformation(parameterObject);
			request.setAttribute("POSITION_INFORMATION", POSITION_INFORMATION);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_jobHealth_add_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String addPositionInformation(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		SimpleMap map1 = null;

		String empId = request.getParameter("person_id");
		String Position = request.getParameter("Position");
		String JOB_DISEASE_CODE = request.getParameter("JOB_DISEASE_CODE");
		String JOB_DISEASE_TYPE_CODE = request
				.getParameter("JOB_DISEASE_TYPE_CODE");
		String StartDates = request.getParameter("StartDate");
		
		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			// retrieve attendance record list

			int iSize = NumberUtil.parseInt(request.getParameter("i"));

			String medicalDate = "";
			String medicalflag = "";
			String remark = "";
			String medicalstate = "";
			String jobHealth ="";
			String remarkText = "";

			// 得到所有工号
			List allEmpId = jobHealthServices.getAllEmpid(parameterObject);
			for (int j = 0; j < allEmpId.size(); j++) {
				map1 = (SimpleMap) allEmpId.get(j);
				String empID = map1.getString("EMP_ID")!=null?map1.getString("EMP_ID"):"";

				parameterObject.set("empID", empID);
				if (empID.equals(empId.toString())) {
					/*jobHealthServices.deleteMedicalPositionInformation(parameterObject);*/
					request.setAttribute("alert", "不能重复添加同一个员工的健康档案！如需换岗请到修改页面进行操作！");
					request.setAttribute("url","/safeControlServlet?operation=jobHealth&content=jobHealthSearch&menu_code=" + parameterObject.getString("menu_code"));
					
					return "/inc/alertMessage.jsp";
				}
			}
			parameterObject.set("empiD", empId);
			parameterObject.set("Position", Position);
			parameterObject.set("JOB_DISEASE_CODE", JOB_DISEASE_CODE);
			parameterObject.set("JOB_DISEASE_TYPE_CODE",JOB_DISEASE_TYPE_CODE);
			parameterObject.set("StartDates", StartDates);			
			
			int seq = jobHealthServices.getJPSeq(parameterObject);
			parameterObject.set("seq", seq);
			if (iSize >= 0) {
				for (int i = 0; i <= iSize; i++) {
					if(request.getParameter("medicalDate" + i) == null)
						continue;
					medicalDate = request.getParameter("medicalDate" + i);
					medicalflag = request.getParameter("medicalflag" + i);
					remark = request.getParameter("remark" + i);
					medicalstate = request.getParameter("StateMedicalFlag" + i);			
					jobHealth = request.getParameter("jobHealth"+ i);
					remarkText = request.getParameter("remarkText" + i);
					
					if(medicalstate.equals("come")){
						parameterObject.set("medicalstate", "入岗体检");
					}
					if(medicalstate.equals("on")){
						parameterObject.set("medicalstate", "在岗体检");
					}
					if(medicalstate.equals("out")){
						parameterObject.set("medicalstate", "离岗体检");
					}
					
					if (medicalflag.equals("1")) {
						parameterObject.set("medicalflag", "正常");
						parameterObject.set("remarkText", remarkText);
					}
					if (medicalflag.equals("2")) {
						parameterObject.set("medicalflag", "异常");
						parameterObject.set("remark", remark);
					}
					parameterObject.set("medicalDate", medicalDate);
					parameterObject.set("jobHealth", jobHealth);
					parameterObject.set("CHANGE_POSITION_FLAG", "1");
					parameterObject.set("CHANGE_POSITION_ORDER_FLAG", "1");
					jobHealthServices.addMedicalInformation(parameterObject);
				}
				
				jobHealthServices.addPositionInformation(parameterObject);
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
//		添加成功
		request.setAttribute("alert", "添加成功");
		request.setAttribute("url","/safeControlServlet?operation=jobHealth&content=jobHealthSearch&menu_code=" + parameterObject.getString("menu_code"));
		
		return "/inc/alertMessage.jsp";
	}

	private String PositionDiseaseDiseaseType(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap map = null;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", admin.getCpnyId());

			List PositionDiseaseDiseaseType = jobHealthServices
					.getPositionDiseaseDiseaseType(parameterObject);

			request.setAttribute("PositionDiseaseDiseaseType",
					PositionDiseaseDiseaseType);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_PositionDiseaseDiseaseType_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String PositionDiseaseDiseaseTypeAddView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpny_id", cpny_id);

			// 得到所有岗位信息
			List POSITION_INFORMATION = jobHealthServices
					.getjob_poditionInformation(parameterObject);
			// 得到所有职业危害信息
			List DISEASE_INFORMATION = jobHealthServices
					.getDiseaseInformation(parameterObject);
			// 得到所有职业病类型信息
			List DISEASE_TYPE_INFORMATION = jobHealthServices
					.getDiseaseTypeInformation(parameterObject);

			request.setAttribute("POSITION_INFORMATION", POSITION_INFORMATION);
			request.setAttribute("DISEASE_INFORMATION", DISEASE_INFORMATION);
			request.setAttribute("DISEASE_TYPE_INFORMATION",
					DISEASE_TYPE_INFORMATION);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_PositionDiseaseDiseaseType_add_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String PositionDiseaseDiseaseTypeAdd(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		
		SimpleMap map = null;

		String POSITION = request.getParameter("POSITION");
		String DISEASE = request.getParameter("DISEASE");
		String DISEASETYPE = request.getParameter("DISEASETYPE");

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("POSITION", POSITION);
			parameterObject.set("DISEASE", DISEASE);
			parameterObject.set("DISEASETYPE", DISEASETYPE);
			parameterObject.set("cpny_id", admin.getCpnyId());

			// 得到所有岗位
			List getAllPosition = jobHealthServices
					.getPositionDiseaseDiseaseType(parameterObject);
			int PositionDiseaseDiseaseTypeSize = getAllPosition.size();
			for (int i = 0; i < PositionDiseaseDiseaseTypeSize; i++) {
				map = (SimpleMap) getAllPosition.get(i);
				String positionCode = map.getString("POSITIONID");

				if (positionCode.equals(POSITION.toString())) {
					jobHealthServices
							.deletePositionDiseaseDiseaseType(parameterObject);
				}
			}
			jobHealthServices.getPositionDiseaseDiseaseTypeAdd(parameterObject);

			List PositionDiseaseDiseaseType = jobHealthServices
					.getPositionDiseaseDiseaseType(parameterObject);

			request.setAttribute("PositionDiseaseDiseaseType",
					PositionDiseaseDiseaseType);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_PositionDiseaseDiseaseType_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String PositionDiseaseDiseaseTypeUpdateView(
			HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		
		SimpleMap map = null;

		String relations_id = request.getParameter("relations_id");

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("relations_id", relations_id);
			parameterObject.set("cpny_id", cpny_id);

			// 得到选中行的岗位，职业危害，职业病类型 信息
			List getSingleInformation = jobHealthServices
					.getSingleInformation(parameterObject);
			for (int i = 0; i < getSingleInformation.size(); i++) {
				map = (SimpleMap) getSingleInformation.get(i);
				String positionId = map.getString("POSITIONID");
				String position = map.getString("POSITIONNAME");
				String diseaseId = map.getString("DISEASEID");
				String diseaseName = map.getString("DISEASENAME");
				String diseaseTypeId = map.getString("DISEASETYPEID");
				String diseaseTypeName = map.getString("DISEASETYPENAME");
				request.setAttribute("positionId", positionId);
				request.setAttribute("position", position);
				request.setAttribute("diseaseId", diseaseId);
				request.setAttribute("diseaseName", diseaseName);
				request.setAttribute("diseaseTypeId", diseaseTypeId);
				request.setAttribute("diseaseTypeName", diseaseTypeName);
			}

			// 得到所有岗位信息
			List POSITION_INFORMATION = jobHealthServices
					.getjob_poditionInformation(parameterObject);
			// 得到所有职业危害信息
			List DISEASE_INFORMATION = jobHealthServices
					.getDiseaseInformation(parameterObject);
			// 得到所有职业病类型信息
			List DISEASE_TYPE_INFORMATION = jobHealthServices
					.getDiseaseTypeInformation(parameterObject);

			request.setAttribute("POSITION_INFORMATION", POSITION_INFORMATION);
			request.setAttribute("DISEASE_INFORMATION", DISEASE_INFORMATION);
			request.setAttribute("DISEASE_TYPE_INFORMATION",
					DISEASE_TYPE_INFORMATION);
			
			request.setAttribute("relations_id", relations_id);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_PositionDiseaseDiseaseType_update_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String PositionDiseaseDiseaseTypeUpdate(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String POSITION = request.getParameter("POSITION");
		String DISEASE = request.getParameter("DISEASE");
		String DISEASETYPE = request.getParameter("DISEASETYPE");

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("POSITION", POSITION);
			parameterObject.set("DISEASE", DISEASE);
			parameterObject.set("DISEASETYPE", DISEASETYPE);
			parameterObject.set("cpny_id", admin.getCpnyId());

			// 得到所有岗位
			List getAllPosition = jobHealthServices
					.getPositionDiseaseDiseaseType(parameterObject);
			int PositionDiseaseDiseaseTypeSize = getAllPosition.size();
//			for (int i = 0; i < PositionDiseaseDiseaseTypeSize; i++) {
//				map = (SimpleMap) getAllPosition.get(i);
//				String positionCode = map.getString("POSITIONID");
//
//				if (positionCode.equals(POSITION.toString())) {
//					jobHealthServices
//							.deletePositionDiseaseDiseaseType(parameterObject);
//				}
//			}
			jobHealthServices.getPositionDiseaseDiseaseTypeUpdate(parameterObject);

			List PositionDiseaseDiseaseType = jobHealthServices
					.getPositionDiseaseDiseaseType(parameterObject);

			request.setAttribute("PositionDiseaseDiseaseType",
					PositionDiseaseDiseaseType);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_PositionDiseaseDiseaseType_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String deletePositionDiseaseDiseaseType(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		SimpleMap map = null;

		String relations_id = request.getParameter("relations_id");

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("relations_id", relations_id);
			parameterObject.set("cpny_id", cpny_id);

			jobHealthServices
					.deletePositionDiseaseDiseaseTypeguanxi(parameterObject);

			List PositionDiseaseDiseaseType = jobHealthServices
					.getPositionDiseaseDiseaseType(parameterObject);

			request.setAttribute("PositionDiseaseDiseaseType",
					PositionDiseaseDiseaseType);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_PositionDiseaseDiseaseType_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String updatejobHealthView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		SimpleMap map = null;
		SimpleMap sm = null;
		String EMPID = request.getParameter("EMPID");
		request.setAttribute("EMPID", EMPID);
		
		String tempjobId = request.getParameter("tempjobId");
		request.setAttribute("tempjobId", tempjobId);
		
		String huangangFlag = StringUtil.checkNull(request.getParameter("huangangFlag"),"dangqian");
		
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("EMPID", EMPID);
			parameterObject.set("tempjobId", tempjobId);
			parameterObject.set("cpny_id", cpny_id);

			List allPositionInformation = jobHealthServices
					.getAllPositionInformation(parameterObject);
			int allPositionInformationSize = allPositionInformation.size();
			for (int i = 0; i < allPositionInformationSize; i++) {
				map = (SimpleMap) allPositionInformation.get(i);
				String empId = map.getString("PERSON_ID");
				String empName = map.getString("CHINESENAME");
				String deptName = map.getString("DEPTNAME");
				String dateStarted = map.getString("DATE_STARTED");
				String position = map.getString("JOB_POSITION_NAME");
				String positionCode = map.getString("JOB_POSITION_NAME_CODE");
				String disease = map.getString("DISEASE");
				String diseaseCode = map.getString("DISEASE_CODE");
				String diseaseType = map.getString("JOB_DISEASE_TYPE");
				String diseaseTypeCode = map.getString("JOB_DISEASE_TYPE_CODE");
				String DOCUMENTNO= map.getString("DOCUMENTNO");
		
				String startTime = map.getString("START_TIME");
				String birth_ymd = map.getString("BIRTH_YMD");
				parameterObject.set("positionCode", positionCode);
				
                request.setAttribute("DOCUMENTNO", DOCUMENTNO);
				request.setAttribute("empId", empId);
				request.setAttribute("empName", empName);
				request.setAttribute("deptName", deptName);
				request.setAttribute("dateStarted", dateStarted);
				request.setAttribute("position", position);
				request.setAttribute("positionCode", positionCode);
				request.setAttribute("disease", disease);
				request.setAttribute("diseaseCode", diseaseCode);
				request.setAttribute("diseaseType", diseaseType);
				request.setAttribute("diseaseTypeCode", diseaseTypeCode);
				request.setAttribute("startTime", startTime);
				request.setAttribute("birth_ymd", birth_ymd);
			}

			List allMedicalInformation = jobHealthServices
					.getAllMedicalInformation(parameterObject);
			int allMedicalInformationSize = allMedicalInformation.size();
			if(huangangFlag.equals("huangang")){

				request.setAttribute("allMedicalInformation",allMedicalInformation);				
				request.setAttribute("allMedicalInformationSize",1);				
			}else{

			request.setAttribute("allMedicalInformation",allMedicalInformation);
			
			request.setAttribute("allMedicalInformationSize",allMedicalInformationSize);
			}
			// 得到所有岗位信息
			List POSITION_INFORMATION = jobHealthServices
					.getPositionInformation(parameterObject);
			

			request.setAttribute("POSITION_INFORMATION", POSITION_INFORMATION);
			
			request.setAttribute("huangangFlag", huangangFlag);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_update_jobHealth_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String updatePositionInformation(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap map = null;
		SimpleMap map1 = null;
		String changePositionFlag = request.getParameter("changePositionFlag");

		String empID = request.getParameter("EMPID");
		String Position = request.getParameter("Position");
		String JOB_DISEASE_CODE = request.getParameter("JOB_DISEASE_CODE");
		String JOB_DISEASE_TYPE_CODE = request.getParameter("JOB_DISEASE_TYPE_CODE");
		
		String StartDates = request.getParameter("StartDate");
		
		String hgFlagTemp = request.getParameter("hgFlagTemp");

		String tempjobId = request.getParameter("tempjobId");
		String formFor=request.getParameter("fromFor");				
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("seq", tempjobId);			
			parameterObject.set("tempjobId", tempjobId);
	
			if(hgFlagTemp.equals("dangqian")){				
				int iSize = NumberUtil.parseInt(request.getParameter("i"));

				parameterObject.set("empiD", empID);
				parameterObject.set("Position", Position);
				parameterObject.set("JOB_DISEASE_CODE", JOB_DISEASE_CODE);
				parameterObject.set("JOB_DISEASE_TYPE_CODE",JOB_DISEASE_TYPE_CODE);
				parameterObject.set("StartDates", StartDates);
				
				if (iSize >= 0) {
					
					jobHealthServices.updatePositionInformation(parameterObject, iSize);
				}
			}
			
			if(hgFlagTemp.equals("huangang")){

				parameterObject.set("changePositionFlag", changePositionFlag);
				// retrieve attendance record list

				int iSize = NumberUtil.parseInt(request.getParameter("i"));

				String medicalDate = "";
				String medicalflag = "";
				String remark = "";
				String medicalstateflag ="";	
				String jobHealth = "";
				String remarkText = "";
				if (iSize >= 0) {
					
					parameterObject.set("empiD", empID);
					parameterObject.set("Position", Position);
					parameterObject.set("JOB_DISEASE_CODE", JOB_DISEASE_CODE);
					parameterObject.set("JOB_DISEASE_TYPE_CODE",JOB_DISEASE_TYPE_CODE);
					parameterObject.set("StartDates", StartDates);
					
					for (int i = 0; i < iSize; i++) {
						if(request.getParameter("medicalDate" + i) == null)
							continue;
						medicalDate = request.getParameter("medicalDate" + i);
						medicalflag = request.getParameter("medicalflag" + i);
						remark = request.getParameter("remark" + i);
						jobHealth = request.getParameter("jobHealth"+i);
						medicalstateflag = request.getParameter("medicalstateflag" + i);
						remarkText = request.getParameter("remarkText" + i);
						parameterObject.set("medicalDate", medicalDate);
						parameterObject.set("jobHealth", jobHealth);
						if (medicalstateflag.equals("come")) {
							parameterObject.set("medicalstate", "入岗体检");
						}
						if (medicalstateflag.equals("on")) {
							parameterObject.set("medicalstate", "在岗体检");
						}
						if (medicalstateflag.equals("out")) {
							parameterObject.set("medicalstate", "离岗体检");
						}
						
						if (medicalflag.equals("1")) {
							parameterObject.set("medicalflag", "正常");
							parameterObject.set("remark", remarkText);
						}
						if (medicalflag.equals("2")) {
							parameterObject.set("medicalflag", "异常");
							parameterObject.set("remark", remark);
						}
						
						jobHealthServices.addMedicalInformation(parameterObject);
					}
					jobHealthServices.updateJobPosition(parameterObject);
					jobHealthServices.addPosition(parameterObject);
				}
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		request.setAttribute("alert", "修改成功！");
		request.setAttribute("url", "/safeControlServlet?operation=jobHealth&content=jobHealthSearch&menu_code="+parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}

	private String searchDetail(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;

		String empId = request.getParameter("person_id");

		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("EMPID", empId);

			List jobHealthDetail = jobHealthServices
					.getJobHealthDetail(parameterObject);
			int jobHealthDetailSize = jobHealthDetail.size();
			for (int i = 0; i < jobHealthDetailSize; i++) {
				map = (SimpleMap) jobHealthDetail.get(i);
				String EMPID = map.getString("EMPID");
				String CHINESENAME = map.getString("CHINESENAME");
				String DEPTNAME = map.getString("DEPTNAME");				
				String DATE_STARTED = map.getString("DATE_STARTED");
				String START_TIME = map.getString("START_TIME");
				String birth_ymd = map.getString("BIRTH_YMD");
				String documentno =map.getString("DOCUMENTNO");
				String person_id = map.getString("PERSON_ID");
				
				request.setAttribute("PERSON_ID", person_id);
				request.setAttribute("documentno", documentno);
				request.setAttribute("EMPID", EMPID);
				request.setAttribute("CHINESENAME", CHINESENAME);
				request.setAttribute("DEPTNAME", DEPTNAME);			
				request.setAttribute("DATE_STARTED", DATE_STARTED);
				request.setAttribute("START_TIME", START_TIME);
				request.setAttribute("birth_ymd", birth_ymd);
				
			}
			
			List getPositionOrder = jobHealthServices.getPositionOrder(parameterObject);
			request.setAttribute("getPositionOrder", getPositionOrder);
			/*List allMedicalInformation = jobHealthServices
					.getAllMedicalInformation(parameterObject);
			int allMedicalInformationSize = allMedicalInformation.size();

			request.setAttribute("allMedicalInformation",
							allMedicalInformation);
			request.setAttribute("allMedicalInformationSize",
					allMedicalInformationSize);*/

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_searchDetail_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}

	private String deletejobHealthView(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		
		SimpleMap map = null;

		String EMPID = request.getParameter("EMPID");

		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("EMPID", EMPID);

			jobHealthServices.deletejobHealthView(parameterObject);

			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style2.pagesize");
			int pageGroupSize = config.getInt("page.style2.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			String empName = request.getParameter("empName");
			String empId = request.getParameter("empID");
			String POSITION = request.getParameter("POSITION");
			String StartDate = request.getParameter("StartDate");
			String EndDate = request.getParameter("EndDate");
			String deptId = request.getParameter("deptID");

			parameterObject.set("empName", empName);
			parameterObject.set("empId", empId);
			parameterObject.set("POSITION", POSITION);
			parameterObject.set("StartDate", StartDate);
			parameterObject.set("EndDate", EndDate);
			parameterObject.set("deptId", deptId);
			parameterObject.set("cpny_id", cpny_id);
			
			// retrieve attendance record list

			// 得到所有职业健康监护档案
			int jobHealthInformationCount = jobHealthServices
					.JobHealthInformationCount(parameterObject);

			// 得到所有职业健康监护档案信息
			List jobHealthInformation = jobHealthServices.JobHealthInformation(
					parameterObject, currentPage, pageSize);

			// 得到所有岗位信息
			List POSITION_INFORMATION = jobHealthServices
					.getPositionInformation(parameterObject);
			request.setAttribute("POSITION_INFORMATION", POSITION_INFORMATION);
			List JOB_POSITION_INFORMATION = jobHealthServices
					.getJobPositionInformation(parameterObject);

			request.setAttribute("JOB_POSITION_INFORMATION",
					JOB_POSITION_INFORMATION);
			request.setAttribute("jobHealthInformation", jobHealthInformation);
			request.setAttribute("resultCount", NumberUtil
					.parseInt(jobHealthInformationCount));

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("jobHealthSearch error ", e);
		}
		return "/safe_jobHealth_View.jsp?menu_code="
				+ parameterObject.getString("menu_code");
	}
	
	private String Jump(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		SimpleMap map = null;

		String orderFlag = request.getParameter("orderFlag");
		String EMPID = request.getParameter("EMPID");
	//	int Subset;
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("orderFlag", orderFlag);
			parameterObject.set("EMPID", EMPID);
			// retrieve attendance record list
			
			List positionInfo = jobHealthServices.getPositionInfo(parameterObject);
			for(int i=0 ; i<positionInfo.size() ; i++){
				map = (SimpleMap)positionInfo.get(i);
				String JOB_POSITION = map.getString("JOB_POSITION");
				String DISEASE = map.getString("DISEASE");
				String JOB_DISEASE_TYPE = map.getString("JOB_DISEASE_TYPE");
				String DATE_STARTED = map.getString("START_TIME");
				
				request.setAttribute("JOB_POSITION", JOB_POSITION);
				request.setAttribute("DISEASE", DISEASE);
				request.setAttribute("JOB_DISEASE_TYPE", JOB_DISEASE_TYPE);
				request.setAttribute("DATE_STARTED", DATE_STARTED);
			}
			List allMedicalInformation = jobHealthServices.getThisFlagMedicalInformation(parameterObject);
			
			int allMedicalInformationSize = allMedicalInformation.size();
		
			request.setAttribute("allMedicalInformation",allMedicalInformation);
			request.setAttribute("allMedicalInformationSize",allMedicalInformationSize);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Jump error ", e);
		}
			return "/safe_positioninfo_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
}