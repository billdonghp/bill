package com.ait.ga.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.BLOB;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.SecurityEnvironmentServices;
import com.ait.ga.business.VisiterApplicationsServices;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;
import com.crystaldecisions.reports.queryengine.ca;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-24
 * 
 */
public class FacilitiesMaintenanceACommand implements Command {
	private SecurityEnvironmentServices seServices = null;
	private String url = "http://10.40.128.28:8083/";
	// private String url = "http://portal.doosan.com";
	// private String url = "http://pnbs.corp.doosan.com/dic_login.jsp";
	// private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	// private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	private Mail mail = new Mail();
	private EssSysparam essSysparam = null;

	public FacilitiesMaintenanceACommand() {
		seServices = new SecurityEnvironmentServices();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		String content = request.getParameter("content");
		String returnPage = null;

		if (!content.equals("") && content.equals("correctivePlanApply")) {
			returnPage = this.getIsNotExitsSecurityEnvironmentPlan(request,
					admin);
		} else if (!content.equals("")
				&& content.equals("andSecurityEnvironmentApply")) {
			returnPage = this.andSecurityEnvironmentApply(request, admin);
		} else if (!content.equals("")
				&& content.equals("correctivePlanAffirm")) {
			this.getCorrectivePlanAffirm(request, admin);
			returnPage = "/ga_correctiveplan_affirm.jsp";
		} else if (!content.equals("")
				&& content.equals("correctivePlanAffirmInfo")) {
			this.getCorrectivePlanAffirmInfo(request, admin);
			returnPage = "/ga_correctiveplan_affirmInfo.jsp";
		} else if (!content.equals("") && content.equals("ongingAffirm")) {
			returnPage = this.ongingAffirm(request, admin);
		} else if (!content.equals("") && content.equals("sendEmailFacilities")) {// 按条件发送邮件
			String maType = request.getParameter("applyType");
			if(maType.equals("2")){
				returnPage = this.sendEmailFacilities(request, admin);
			}else{
				returnPage = this.sendEmailFokliftMantain(request, admin,maType);
			}
		} else if (!content.equals("")
				&& content.equals("completedRectificationApply")) {
			returnPage = this.getIsNotExitsSecurityEnvironment(request, admin);
		} else if (content.equals("excelfacilitiesview") && content != null) {// 维修情况
			if(request.getParameter("applyType").equals("2")){
				returnPage = this.ExceFacilitiRectiFication(request, admin);
			}else{
				returnPage = this.ExceForkliRectiFication(request, admin);
			}
		}else if (content.equals("excelvisiterview") && content != null) {// 参观者 EXCEL下载20190426
				returnPage = this.ExceVisiterFication(request, admin);			
		} else if (!content.equals("")
				&& content.equals("addfacilitiesRectificationApply")) {
			returnPage = this.addCompletedRectificationApply(request, admin);
		}else if (!content.equals("")
				&& content.equals("addForkliftApply")) {
			returnPage = this.addForkliftApply(request, admin);
		} else if (!content.equals("")
				&& content.equals("deleteficationaffrim")) { // 删除决裁者
			returnPage = this.deleteFicationAffrim(request, admin);
		} else if (!content.equals("")
				&& content.equals("updateFacilitiesRectification")) {
			String carType = request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			if(carType.equals("2")){
				returnPage = this.updateFacilitiesRectification(request, admin);
			}else{
				returnPage = this.updateForkliRectification(request, admin,carType);
			}
			
		} else if (!content.equals("")
				&& content.equals("updateFacilitiesRectification03")) {
			String carType = request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			if(carType.equals("2")){
				returnPage = this.updateFacilitiesRectification03(request, admin);
			}else{
				returnPage = this.updateForkliRectification03(request, admin,carType);
			}
		} else if (!content.equals("")
				&& content.equals("updateFacilitiesManYiDu")) {
			String applyType = request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			if(applyType.equals("2")){
				returnPage = this.updateFacilitiesManYiDu(request, admin);
			}else{
				returnPage = this.updateForkliManYiDu(request, admin, applyType);
			}
		}else if (!content.equals("")
				&& content.equals("updateVisiterManYiDu")) {		
				returnPage = this.updateVisiterManYiDu(request, admin);			
		} else if (!content.equals("")
				&& content.equals("updateManYiFacilitiesRectification")) {
			returnPage = this
					.updateManYiFacilitiesRectification(request, admin);
		} else if (!content.equals("")
				&& content.equals("facilitiesMaintenanceAAffirm")) {
			String carType = request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			if(carType.equals("2")){
				this.getFampletedRectificationAffirm(request, admin);
			}else{
				this.getForkliftAffirm(request, admin);
			}
			returnPage = "/ga_facilitiesMaintenanceA_affirm.jsp";
		} else if (!content.equals("")
				&& content.equals("maintainChange")) {
			if(request.getParameter("applyType").equals("1")){
				this.getForkliftAffirm(request, admin);
			}else if(request.getParameter("applyType").equals("2")){
				this.getFampletedRectificationAffirm(request, admin);
			}
			returnPage = "/ga_facilitiesMaintenanceA_affirm.jsp";
		} else if (!content.equals("")
				&& content.equals("facilitiesRectificationAffirmInfo")) {
			String maType = request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			if(maType.equals("2")){
				returnPage = this
				.getFacilityRectificationAffirmInfo(request, admin);
			}else{
				returnPage = this
				.getForkliRectificationAffirmInfo(request, admin,maType);
			}
		}else if (!content.equals("")
					&& content.equals("crMaintainType")) {
			String carType = request.getParameter("applyType");
			if(carType.equals("2")){
				returnPage = this
				.getFacilityRectificationAffirmInfo(request, admin);
			}else{
				returnPage = this
				.getForkliRectificationAffirmInfo(request, admin,carType);
			}
		}else if (!content.equals("")
				&& content.equals("facilitiesCrfongingAffirm")) {
			
			if(request.getParameter("applyType").equals("2")){
				returnPage = this.FacilitiesCrfongingAffirm(request, admin);
			}else{
				returnPage = this.ForkliftMaintainCrfongingAffirm(request, admin);
			}
		} else if (!content.equals("")
				&& content.equals("oingcorrectivePlanDelete")) {
			returnPage = this.oingCorrectivePlanDelete(request, admin);
		} else if (content.equals("batchfacilityAffirm") && content != null) {// 领导批量決裁（通过、否决）
			
			if(request.getParameter("applyType").equals("2")){
				this.batchFacilityAffirm(request);
				this.getFampletedRectificationAffirm(request, admin);
			}else{
				this.batchForkliftAffirm(request);
				this.getForkliftAffirm(request, admin);
			}
			returnPage = "/ga_facilitiesMaintenanceA_affirm.jsp";
		} else if (!content.equals("")
				&& content.equals("oingcompletedRectificationDelete")) {
			returnPage = this.oingCompletedRectificationDelete(request, admin);
		} else if (!content.equals("") && content.equals("uploadImp")) {
			this.uploadImp(request);
			returnPage = "/ga_upload.jsp?Directive=CloseWindow";
		} else if (!content.equals("") && content.equals("DelAffirmInfoFa")) {
			String carType = request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			if(carType.equals("2")){
				this.DelAffirmInfo(request, admin);
				returnPage = this
						.getFacilityRectificationAffirmInfo(request, admin);
			}else{
				this.DelForkliAffirmInfo(request, admin);
				returnPage = this
						.getForkliRectificationAffirmInfo(request, admin, carType);
			}
		} else {
			return "error.jsp";
		}
		return returnPage;
	}

	/* 根据登陆者得到没有进行整改计划的信息 */
	public String getIsNotExitsSecurityEnvironmentPlan(
			HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("cpny_id", admin.getCpnyId());

			List IsNotExitsCorrectiveplanList = seServices
					.getIsNotExitsSecurityEnvironmentPlan(parameterObject);// 得到决裁情况信息

			request.setAttribute("IsNotExitsCorrectiveplanList",
					IsNotExitsCorrectiveplanList);
			if (IsNotExitsCorrectiveplanList == null
					|| IsNotExitsCorrectiveplanList.size() == 0) {
				request.setAttribute("errorMsg", "没有您部门相关安全检查记录！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getIsNotExitsSecurityEnvironment happens Exception. ", e);
		}
		return "/ga_correctiveplan_apply.jsp";

	}

	public String getIsNotExitsSecurityEnvironment(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			List IsNotExitsCorrectiveplanList = seServices
					.getIsNotExitsSecurityEnvironment(parameterObject);// 得到决裁情况信息

			request.setAttribute("IsNotExitsCorrectiveplanList",
					IsNotExitsCorrectiveplanList);
			if (IsNotExitsCorrectiveplanList == null
					|| IsNotExitsCorrectiveplanList.size() == 0) {
				request.setAttribute("errorMsg", "没有您部门相关安全检查记录！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getIsNotExitsSecurityEnvironment happens Exception. ", e);
		}
		return "/ga_completedrectification_apply.jsp";

	}

	/* 对指定的检查编号指定整改计划 */
	public String andSecurityEnvironmentApply(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		List affirorList = gaAffirm.getAffirmor(admin.getAdminID(),
				"SecurityEnvironmentApply", essSysparam.isContainsOwner());
		int Sequence = this.getSequence("SE_CORRECTIVEPLAN_APPLY_SEQ");
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("applyno", Sequence);
			temp = seServices.andSecurityEnvironmentApply(parameterObject,
					affirorList);// 得到决裁情况信息

			if (temp) {
				request.setAttribute("errorMsg", "申请成功！");
				parameterObject.set("applerId", request
						.getParameter("firstaffrim"));
				String toEmail = seServices.getapplyemail(parameterObject);
				if (!toEmail.equals("") && toEmail != null) {
					sendApplyPlanMail("整改计划通报 请您确认", admin.getAdminID(), admin
							.getChineseName(), toEmail, request
							.getParameter("RECTIFICATIONCOMPLETIONDATE"),
							request.getParameter("PLANCOMPLETIONDATE"), request
									.getParameter("Correctiveplan"));
				}
			} else {
				request.setAttribute("errorMsg", "申请信息失败！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"andSecurityEnvironmentApply happens Exception. ", e);
		}
		return "/ga_correctiveplan_apply.jsp";

	}

	/* 得到整改计划申请决裁的信息 */
	public void getCorrectivePlanAffirm(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = seServices
					.getCorrectivePlanAffirmListNumber(parameterObject);
			List applyList = seServices.getCorrectivePlanAffirmList(
					parameterObject, currentPage, pageSize);
			// int ifaffirm = seServices.getIfAffirmNumber(parameterObject);

			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				List affirmorList = seServices
						.getCorrectivePlanAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
			}
			request
					.setAttribute("startDate", request
							.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));
			request.setAttribute("qryType", request.getParameter("qryType"));
			request.setAttribute("deptid", request.getParameter("deptid"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirm happens Exception. ", e);
		}

	}

	/* 得到整改计划决裁情况的信息 */
	public void getCorrectivePlanAffirmInfo(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = seServices
					.getCorrectivePlanAffirmInfoListNumber(parameterObject);
			List applyList = seServices.getCorrectivePlanAffirmInfoList(
					parameterObject, currentPage, pageSize);
			// int ifaffirm = seServices.getIfAffirmNumber(parameterObject);

			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				List affirmorList = seServices
						.getCorrectivePlanAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
			}
			request
					.setAttribute("startDate", request
							.getParameter("startDate"));
			request.setAttribute("endDate", request.getParameter("endDate"));
			request.setAttribute("qryType", request.getParameter("qryType"));
			request.setAttribute("deptid", request.getParameter("deptid"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirm happens Exception. ", e);
		}

	}

	/* 进行决裁 */
	public String ongingAffirm(HttpServletRequest request, AdminBean admin) {
		boolean temp = false;
		try {

			SimpleMap parameterObject = null;
			String date1 = "";

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applyno", request.getParameter("applyno"));
			parameterObject.set("applerId", request.getParameter("applyorid"));

			temp = seServices.ongingAffirm(parameterObject);
			String affno = "0";
			if (request.getParameter("flag").equals("1")) {
				affno = "1";
			}
			String documentno = request.getParameter("documentno");
			parameterObject.set("documentno", documentno);
			parameterObject.set("affno", affno);
			seServices.updateComplate(parameterObject);

			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			date1 = format1.format(Calendar.getInstance().getTime());

			if (request.getParameter("flag").equals("2")) {
				String toEmail = seServices.getapplyemail(parameterObject);
				if (!toEmail.equals("") && toEmail != null) {
					sendApplyMail("整改计划通报 已被否决", request
							.getParameter("applyorid"), request
							.getParameter("applyorname"), toEmail, request
							.getParameter("APPLY_DATE0"), request
							.getParameter("useInformation"));
				}
			} else if (request.getParameter("flag").equals("1")) {

				parameterObject.set("affrimlevel", request
						.getParameter("affrimlevel"));
				int resultCount = seServices.getUpAffrimNumber(parameterObject);
				if (resultCount != 0) {
					String toEmail = seServices
							.getupaffrimemail(parameterObject);
					if (!toEmail.equals("") && toEmail != null) {
						sendPassMail("整改计划通报 请您确认", request
								.getParameter("applyorid"), request
								.getParameter("applyorname"), toEmail, request
								.getParameter("APPLY_DATE0"), request
								.getParameter("useInformation"));
					}
				} else {
					String toEmail = seServices.getapplyemail(parameterObject);
					if (!toEmail.equals("") && toEmail != null) {
						sendApplyMail("整改计划通报 已通过确认", request
								.getParameter("applyorid"), request
								.getParameter("applyorname"), toEmail, request
								.getParameter("APPLY_DATE0"), request
								.getParameter("useInformation"));
					}
				}
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getCorrectivePlanAffirm(request, admin);
		return "/ga_correctiveplan_affirm.jsp";

	}

	// 批量決裁 通過 或 否決
	public void batchFacilityAffirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");

		try {
			String countsArr[] = request.getParameterValues("counts");

			for (int i = 0; i < countsArr.length; i++) {
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("applyNo", request.getParameter("applyNo_"
						+ countsArr[i]));// 申请号
				parameterObject.set("affirmFlag", request
						.getParameter("affirmFlag"));// 决裁号
				parameterObject.set("applerId", request.getParameter("applyer_"
						+ countsArr[i]));
				parameterObject.set("adminId", admin.getAdminID());
				parameterObject.set("crfno_", request.getParameter("crfno_"
						+ countsArr[i]));
				parameterObject.set("affrimlevel", request
						.getParameter("AFFIRM_LEVEL001_" + countsArr[i]));
				parameterObject.set("securitychecksno", request
						.getParameter("securitychecksno_" + countsArr[i]));
				parameterObject.set("applerIdName_", request
						.getParameter("applerIdName003_" + countsArr[i]));
				parameterObject.set("completiondate_", request
						.getParameter("completiondate_" + countsArr[i]));
				seServices.oingFacilityAffirm(parameterObject);
				if(!request.getParameter("affirmlevelMax_" + countsArr[i]).equals("0")){
					
					seServices.crfongingAffirm02(parameterObject);
					}
				if (request.getParameter("affirmFlag").equals("2")) {
					String toEmail = seServices.getapplyemail(parameterObject);
					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendFacilitiesApplyMail02("设施维修申请 已被否决",
								parameterObject.getString("applerIdName_"),
								toEmail, parameterObject.getString("crfno_"),
								parameterObject.getString("completiondate_"),
								admin.getCpnyId());
					}

				} else if (request.getParameter("affirmFlag").equals("1")) {
					String MAX_AFFIRM_FLAG = request
							.getParameter("maxAffirmFlag001_" + countsArr[i]);

					if (MAX_AFFIRM_FLAG.equals("0")) {
						parameterObject.set("applyNoId", request
								.getParameter("applyNo_" + countsArr[i]));
						//seServices.crfongingAffirm03(parameterObject);
						String toEmail = seServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendFacilitiesApplyMail02("设施维修申请 请您确认",
									parameterObject.getString("applerIdName_"),
									toEmail, parameterObject
											.getString("crfno_"),
									parameterObject
											.getString("completiondate_"),
									admin.getCpnyId());
						}
					} else {
						//seServices.crfongingAffirm04(parameterObject);
						String toEmail = seServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendFacilitiesApplyMail02("设施维修申请 已通过确认",
									parameterObject.getString("applerIdName_"),
									toEmail, parameterObject
											.getString("crfno_"),
									parameterObject
											.getString("completiondate_"),
									admin.getCpnyId());
							
							sendFacilitiesApplyMail02("设施维修申请 已通过确认，请您进行维修 谢谢！",
									parameterObject.getString("applerIdName_"),
									"tao.zhao@doosan.com", parameterObject
											.getString("crfno_"),
									parameterObject
											.getString("completiondate_"),
									admin.getCpnyId());
							
						}
					}
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
	}

	
	public void batchForkliftAffirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean) request.getSession()
				.getAttribute("admin");
		try {
			String countsArr[] = request.getParameterValues("counts");

			for (int i = 0; i < countsArr.length; i++) {
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("applyorname_", request.getParameter("applyorname_"+countsArr[i]));
				parameterObject.set("faultDate_fa_", request.getParameter("faultDate_fa_"+countsArr[i]));
				parameterObject.set("detailSa_fa_", request.getParameter("detailSa_fa_"+countsArr[i]));
				parameterObject.set("applerId", request.getParameter("APPLYORID_FA_" + countsArr[i]));//申请者
				parameterObject.set("applyNo", request.getParameter("applyNo_"
						+ countsArr[i]));// 申请号
				parameterObject.set("affrimlevel", request.getParameter("AFFIRM_LEVEL_" + countsArr[i]));//申请者
				parameterObject.set("affirmFlag", request
						.getParameter("affirmFlag"));// 决裁号
				seServices.oingForkliftMaintainAffirm(parameterObject);
				if(!request.getParameter("affirmlevelMax_" + countsArr[i]).equals("0")){
				seServices.crForkliftMantainAffirm02(parameterObject);
				}
				if (request.getParameter("affirmFlag").equals("2")) {
					String toEmail = seServices.getapplyemail(parameterObject);
					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendFacilitiesApplyMail02("叉车维修申请 已被否决",
								parameterObject.getString("applyorname_"),
								toEmail, parameterObject.getString("faultDate_fa_"),
								parameterObject.getString("detailSa_fa_"),
								admin.getCpnyId());
					}

				} else if (request.getParameter("affirmFlag").equals("1")) {
					String MAX_AFFIRM_FLAG = request
							.getParameter("affirmlevelMax_" + countsArr[i]);

					if (MAX_AFFIRM_FLAG.equals("0")) {
						parameterObject.set("affirmno", request.getParameter("applyNo_"
								+ countsArr[i]));
						parameterObject.set("affrimlevel", request.getParameter("affirmId_Level_"
								+ countsArr[i]));
						String toEmail = seServices
								.getForkliftAffirmEmail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendFacilitiesApplyMail02("叉车维修申请 请您确认",
									parameterObject.getString("applyorname_"),
									toEmail, parameterObject
											.getString("faultDate_fa_"),
									parameterObject
											.getString("detailSa_fa_"),
									admin.getCpnyId());
						}
					} else {
						String toEmail = seServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendFacilitiesApplyMail02("叉车维修申请 已通过确认",
									parameterObject.getString("applyorname_"),
									toEmail, parameterObject
											.getString("faultDate_fa_"),
									parameterObject
											.getString("detailSa_fa_"),
									admin.getCpnyId());
							
							sendFacilitiesApplyMail02("叉车维修申请 已通过确认,请您进行维修，谢谢！",
									parameterObject.getString("applyorname_"),
									"tao.zhao@doosan.com", parameterObject
											.getString("faultDate_fa_"),
									parameterObject
											.getString("detailSa_fa_"),
									admin.getCpnyId());
							
						}
					}
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
	}
	
	
	private void sendApplyPlanMail(String title, String adminid,
			String applyer, String toEmail, String rectificationdate,
			String plandate, String plan) throws Exception {

		SimpleMap parameterObject = new SimpleMap();

		SimpleMap inputData = new SimpleMap();

		parameterObject.set("applerId", adminid);
		parameterObject.set("applyer", applyer);
		// List result = seServices.getapplyemail(parameterObject);

		StringBuffer content = new StringBuffer();

		content.append(" 申请人：").append(applyer).append("<br><br>").append(
				" 主题：").append(title);

		content.append("<br><br>").append(" 要求整改日期：").append(rectificationdate);
		content.append("<br><br>").append(" 计划完成日期：").append(plandate);

		content.append("<br><br>").append(" 整改计划：").append(plan);

		content.append("<br><br>").append(
				"<a href=" + url + " target=\"_blank\">点击此处进行确认</a>").append(
				"<br><br>斗山工程机械(中国)有限公司");

		// set email title
		inputData.setString("EMAIL_TITLE", applyer + "," + title);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		// for(int i=0;i<result.size();i++){
		inputData.setString("RCVR_EMAIL_ADDR", toEmail);

		mail.sendMail(inputData);
		// }
		// essServices.insertOtAffirmMail(inputData);
	}

	private void sendApplyMail(String title, String adminid, String applyer,
			String toEmail, String plandate, String plan) throws Exception {

		SimpleMap parameterObject = new SimpleMap();

		SimpleMap inputData = new SimpleMap();

		parameterObject.set("applerId", adminid);
		parameterObject.set("applyer", applyer);
		// List result = seServices.getapplyemail(parameterObject);

		StringBuffer content = new StringBuffer();

		content.append(" 申请人：").append(applyer).append("<br><br>").append(
				" 主题：").append(title);

		content.append("<br><br>").append(" 计划完成日期：").append(plandate);

		content.append("<br><br>").append(" 整改计划：").append(plan);

		content.append("<br><br>").append(
				"<a href=" + url + " target=\"_blank\">点击此处进行确认</a>").append(
				"<br><br>斗山工程机械(中国)有限公司");

		// set email title
		inputData.setString("EMAIL_TITLE", applyer + "," + title);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		// for(int i=0;i<result.size();i++){
		inputData.setString("RCVR_EMAIL_ADDR", toEmail);

		mail.sendMail(inputData);
		// }
		// essServices.insertOtAffirmMail(inputData);
	}

	private void sendPassMail(String title, String adminid, String applyer,
			String toEmail, String plandate, String plan) throws Exception {

		SimpleMap parameterObject = new SimpleMap();

		SimpleMap inputData = new SimpleMap();

		parameterObject.set("applerId", adminid);
		parameterObject.set("applyer", applyer);
		// List result = seServices.getapplyemail(parameterObject);

		StringBuffer content = new StringBuffer();

		content.append(" 申请人：").append(applyer).append("<br><br>").append(
				" 主题：").append(title);

		content.append("<br><br>").append(" 计划完成日期：").append(plandate);

		content.append("<br><br>").append(" 整改计划：").append(plan);

		content.append("<br><br>").append(
				"<a href=" + url + " target=\"_blank\">点击此处进行确认</a>").append(
				"<br><br>斗山工程机械(中国)有限公司");

		// set email title
		inputData.setString("EMAIL_TITLE", applyer + "," + title);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		// for(int i=0;i<result.size();i++){
		inputData.setString("RCVR_EMAIL_ADDR", toEmail);

		mail.sendMail(inputData);
		// }
		// essServices.insertOtAffirmMail(inputData);
	}

	/*
	 * private void sendComplatePassMail(String title,String adminid,String
	 * applyer,String toEmail,String plandate,String plan) throws Exception {
	 * 
	 * SimpleMap parameterObject = new SimpleMap();
	 * 
	 * SimpleMap inputData = new SimpleMap();
	 * 
	 * parameterObject.set("applerId",adminid);
	 * parameterObject.set("applyer",applyer); //List result =
	 * seServices.getapplyemail(parameterObject);
	 * 
	 * StringBuffer content = new StringBuffer();
	 * 
	 * content.append(" 申请人：").append(applyer)
	 * .append("<br><br>").append(" 主题：").append(title);
	 * 
	 * content.append("<br><br>").append(" 计划完成日期：").append(plandate);
	 * 
	 * content.append("<br><br>").append(" 整改计划：").append(plan) ;
	 * 
	 * 
	 * content.append("<br><br>").append("<a href=" + url +
	 * " target=\"_blank\">点击此处进行确认</a>") .append("<br><br>斗山工程机械(中国)有限公司") ;
	 * 
	 * 
	 * // set email title inputData.setString("EMAIL_TITLE", applyer + "," +
	 * title);
	 * 
	 * // set email content inputData.setString("EMAIL_CONTNT",
	 * content.toString());
	 * 
	 * //for(int i=0;i<result.size();i++){
	 * inputData.setString("RCVR_EMAIL_ADDR", toEmail);
	 * 
	 * mail.sendMail(inputData) ; //}
	 * //essServices.insertOtAffirmMail(inputData);sendComplatePassMail }
	 */

	/* 根据登陆者得到没有进行整改完成的信息 */
	public String getIsNotOingCompletedRectification(
			HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			List IsNotOingCompletedRectificationList = seServices
					.getIsNotOingCompletedRectification(parameterObject);// 得到决裁情况信息

			request.setAttribute("IsNotOingCompletedRectificationList",
					IsNotOingCompletedRectificationList);
			if (IsNotOingCompletedRectificationList == null
					|| IsNotOingCompletedRectificationList.size() == 0) {
				request.setAttribute("errorMsg", "没有进行整改计划申请，不能进行整改完成申请！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getIsNotOingCompletedRectification happens Exception. ", e);
		}
		return "/ga_completedrectification_apply.jsp";

	}

	/*
	 * 设施维修申请信息
	 */
	public String addCompletedRectificationApply(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
//		List affirorList = gaAffirm.getAffirmor1(admin.getAdminID(),
//				"SecurityComplateApply", essSysparam.isContainsOwner());
		int Sequence = this.getSequence("SE_COMPLETRECTIFICAT_APPLY_SEQ");
		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		int isExists = 0;// 操作标识
		int isExistsSequence = 0;// 已存在的sequece
		
		List affirorList = new ArrayList();
		String[] affirmorId = request.getParameterValues("affirmorId");
		
		for(int i=0;i<affirmorId.length;i++){
				GaAffirmList vb = new GaAffirmList();
				vb.setAffirmLevel(i+1);
				vb.setAffirmorId(affirmorId[i]);
				affirorList.add(vb);
		}
		try {
				String sql = "select APPLYNO from SE_COMPLETRECTIFICAT_APPLY where SECURITYCHECKSNO=?";
				ps = conn.prepareStatement(sql);
				Logger.getLogger(getClass()).debug(sql);
				ps.setString(1, request.getParameter("document_number"));
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					isExistsSequence = rs.getInt("APPLYNO");
					isExists = 1;
				}

				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sqlStr = " insert into SE_COMPLETRECTIFICAT_APPLY(APPLYNO ,SECURITYCHECKSNO,DEPTID,APPLYORID,CRFNOTE,COMPLETIONDATE,CLASSIFICATION,PLACE,DETAILSA,ISCONFIRM) values( "
						+ " ?,?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?) ";
				Logger.getLogger(getClass()).debug(sqlStr);
				ps = conn.prepareStatement(sqlStr);
				ps.setInt(1, Sequence);
//				ps.setString(1, request.getParameter("ApplyNoFaApp"));
				// String a=request.getParameter("ApplyNoFa");
				ps.setString(2, request.getParameter("document_number"));
				ps.setString(3, admin.getDeptID());
				ps.setString(4, request.getParameter("SENDPERSON"));
				ps.setString(5, request.getParameter("CompletedRectification"));// 要求维修内容
				// ps.setBlob(6, BLOB.empty_lob());
				ps.setString(6, request.getParameter("overDate")); // 故障时间
				// ps.setString(8,
				// request.getParameter("RECTIFICATIONCOMPLETIONDATE"));
				ps.setString(7, request.getParameter("empType")); // 分类
				ps.setString(8, request.getParameter("location")); // 位置
				ps.setString(9, request
						.getParameter("CompletedRectificationdetails")); // 故障详情
				ps.setString(10, "2");
				result += ps.executeUpdate();
				ps.close();


				 for (int i = 0; i < affirorList.size(); i++) {
					 gaAffirmList = (GaAffirmList) affirorList.get(i);
					 String sql1 =
					 "     insert into SE_COMPLETRECTIFICAT_AFFIRM(GA_AFFIRM_NO, APPLY_NO,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATED_BY,ACTIVITY,APPLY_TYPE ) values ( "
					 +
					 "  SE_COMPLETRECTIFICAT_SEQ.NEXTVAL, ?,?,?,SYSDATE,?,'1', 'SecurityEnvironmentApply' )  ";
					 Logger.getLogger(getClass()).debug(sql1);
					 ps = conn.prepareStatement(sql1);
									
					 ps.setInt(1, Sequence);
					 ps.setInt(2, gaAffirmList.getAffirmLevel());
					 ps.setString(3, gaAffirmList.getAffirmorId());
					 ps.setString(4, admin.getAdminID());
									
					 result += ps.executeUpdate();
					 ps.close();

				 }
					if (result >= 2) {
					conn.commit();
					request.setAttribute("errorMsg", "申请成功！");
					// 发送申请email
					parameterObject.set("applerId", affirmorId[0]);
					String toEmail = seServices.getapplyemail(parameterObject);

					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendFacilitiesApplyMail01("维修设施申请 请您确认", request
								.getParameter("ChineseName001"), toEmail,
								request.getParameter("overDate"),
								request.getParameter("CompletedRectification"),
								admin.getCpnyId());
					}
				} else {
					request.setAttribute("errorMsg", "申请信息失败！");
					conn.rollback();
				}
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"addCompletedRectificationApply happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}

		// return "/ga_completedrectification_apply.jsp";
		List affirorList1 = gaAffirm.getAffirmor1(admin.getAdminID(),
				"SecurityComplateApply", essSysparam.isContainsOwner());

		request.setAttribute("affirmList", affirorList1);
		return "/ga_facilitiesMaintenance_apply.jsp";

	}

	/*
	 * 叉车维修申请信息
	 */
	public String addForkliftApply(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		PreparedStatement ps = null;
		int Sequence = this.getSequence("SE_FORKLIFT_APPLY_SEQ");
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		List affirorList = new ArrayList();
		String[] affirmorId = request.getParameterValues("affirmorId");
		
		for(int i=0;i<affirmorId.length;i++){
				GaAffirmList vb = new GaAffirmList();
				vb.setAffirmLevel(i+1);
				vb.setAffirmorId(affirmorId[i]);
				affirorList.add(vb);
		}
		try {
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sqlStr = " insert into SE_FORKLIFTMAINTAIN_APPLY(APPLYNO ,FORKLIFTMAINTAINNO,"
					+"APPLYORID,DEPTID,CARTYPE,CARNUM,FAILURETYPE,MILEAGE,FAULTDATE,DETAILSA,DIVISIVE,CARBELONG) values( "
					+ " ?,?,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?) ";
				Logger.getLogger(getClass()).debug(sqlStr);
				ps = conn.prepareStatement(sqlStr);
				ps.setInt(1, Sequence);
				ps.setString(2, request.getParameter("document_number"));
				ps.setString(3, admin.getAdminID());
				ps.setString(4, admin.getDeptID());
				ps.setString(5, request.getParameter("carType_01"));
				if(request.getParameter("carType_01").equals("ForkliftNo")){
					ps.setString(6, request.getParameter("ForkliftNo_0")); //车号   待定
				}else if(request.getParameter("carType_01").equals("TCarNo")){
					ps.setString(6, request.getParameter("TCarNo_0")); //车号   待定
				}else if(request.getParameter("carType_01").equals("SweepCarNo")){
					ps.setString(6, request.getParameter("SweepCarNo_0")); //车号   待定
				}
				ps.setString(7, request.getParameter("faultType")); 
				ps.setString(8, request.getParameter("mileage"));
				ps.setString(9, request.getParameter("FAULTDATE")); //故障时间
				ps.setString(10, request.getParameter("CompletedRectificationdetails"));
				ps.setString(11, request.getParameter("applyType"));//区分叉车 设施申请
				ps.setString(12, request.getParameter("carBelong"));//区分叉车 设施申请
				result += ps.executeUpdate();
				ps.close();

				 for (int i = 0; i < affirorList.size(); i++) {
					 gaAffirmList = (GaAffirmList) affirorList.get(i);
					 String sql1 =
					 "     insert into SE_FORKLIFTMAINTAIN_AFFIRM(GA_AFFIRM_NO, APPLY_NO,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATED_BY,ACTIVITY,APPLY_TYPE ) values ( "
					 +
					 "  SE_FORKLIFT_APPLY_SEQ.NEXTVAL, ?,?,?,SYSDATE,?,'1', 'CarApply' )  ";
					 Logger.getLogger(getClass()).debug(sql1);
					 ps = conn.prepareStatement(sql1);
									
					 ps.setInt(1, Sequence);
					 ps.setInt(2, gaAffirmList.getAffirmLevel());
					 ps.setString(3, gaAffirmList.getAffirmorId());
					 ps.setString(4, admin.getAdminID());
									
					 result += ps.executeUpdate();
					 ps.close();

				 }
					if (result >= 2) {
					conn.commit();
					request.setAttribute("errorMsg", "申请成功！");
					// 发送申请email
					parameterObject.set("applerId", affirmorId[0]);
					String toEmail = seServices.getapplyemail(parameterObject);

					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendFacilitiesApplyMail01("维修设施申请 请您确认", admin.getChineseName(), toEmail,
								request.getParameter("FAULTDATE"),
								request.getParameter("CompletedRectificationdetails"),
								admin.getCpnyId());
					}
				} else {
					request.setAttribute("errorMsg", "申请信息失败！");
					conn.rollback();
				}
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"addCompletedRectificationApply happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}

		List affirorList1 = gaAffirm.getAffirmor1(admin.getAdminID(),
				"SecurityComplateApply", essSysparam.isContainsOwner());
		request.setAttribute("affirmList", affirorList1);
		request.setAttribute("maintainType", request.getParameter("applyType"));
		return "/ga_facilitiesMaintenance_apply.jsp";
	}

	
	/*
	 * 删除决裁者信息
	 */
	public String deleteFicationAffrim(HttpServletRequest request,
			AdminBean admin) {
		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		GaAffirmList vb = new GaAffirmList();
		GaAffirm gaAffirm = new GaAffirm();
		try {

			String sql2 = " update SE_COMPLETRECTIFICAT_AFFIRM f set f.affirm_level = ? where f.apply_no = ? and f.affirm_level = ?";
			String sql1 = " delete from SE_COMPLETRECTIFICAT_AFFIRM f where f.apply_no = ? and f.affirm_level = ? and f.affirmor_id = ?";
			Logger.getLogger(getClass()).debug(sql1);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql1);
			ps.setString(1, request.getParameter("ApplyNoFa"));
			ps.setString(2, request.getParameter("AffirmLevel"));
			ps.setString(3, request.getParameter("AffirmorId"));
			ps.executeUpdate();
			conn.commit();

			List affirorList = gaAffirm.getAffirmorFacility(request
					.getParameter("ApplyNoFa"));

			if (affirorList.size() <= 2) {
				for (int i = 0; i < affirorList.size(); ++i) {
					vb = (GaAffirmList) affirorList.get(i);
					// 不能让第一级减除
					if (vb.getAffirmLevel() > 1) {
						vb.setAffirmLevel(vb.getAffirmLevel() - 1);
						int a = vb.getAffirmLevel() + 1;
						Logger.getLogger(getClass()).debug(sql2);
						conn.setAutoCommit(false);
						ps = conn.prepareStatement(sql2);
						ps.setInt(1, vb.getAffirmLevel());
						ps.setString(2, request.getParameter("ApplyNoFa"));
						ps.setInt(3, vb.getAffirmLevel() + 1);
						ps.executeUpdate();
						conn.commit();
					}
				}
			} else if (affirorList.size() > 2) {
				for (int i = 0; i < affirorList.size(); ++i) {
					vb = (GaAffirmList) affirorList.get(i);
					// 不能让第一级减除
					if (vb.getAffirmLevel() > 2) {
						vb.setAffirmLevel(vb.getAffirmLevel() - 1);
						Logger.getLogger(getClass()).debug(sql2);
						conn.setAutoCommit(false);
						ps = conn.prepareStatement(sql2);
						ps.setInt(1, vb.getAffirmLevel());
						ps.setString(2, request.getParameter("ApplyNoFa"));
						ps.setInt(3, vb.getAffirmLevel() + 1);
						ps.executeUpdate();
						conn.commit();
					}
				}
			}

			ps.close();
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			request.setAttribute("documentnoBian", request
					.getParameter("document_number"));
			request.setAttribute("applyNoFaht", request
					.getParameter("ApplyNoFa"));
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"deleteFicationAffrim happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}

		return "/ga_facilitiesMaintenance_apply.jsp";

	}

	// 维修情况更新 整改
	public String updateFacilitiesRectification(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");
		
		try {
			for (int i = 0; i < countsArr.length; i++) {

				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update SE_COMPLETRECTIFICAT_APPLY t set t.zhengaijiegu=? ,t.MANY=? where t.applyno=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request
						.getParameter("ZHENGAIJIEGU_" + countsArr[i]);
				String bb = request.getParameter("MANY_" + countsArr[i]);
				String cc = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, aa);
				ps.setString(2, bb);
				ps.setString(3, cc);// 申请号
				result += ps.executeUpdate();
				ps.close();
				
				// 完成标示
				String sql2 = " update SE_COMPLETRECTIFICAT_APPLY t set t.isconfirm = 1 where t.applyno= ? ";
				Logger.getLogger(getClass()).debug(sql2);
				ps = conn.prepareStatement(sql2);

				String APPLYNO_ = request.getParameter("APPLYNO_"
						+ countsArr[i]);
				ps.setString(1, APPLYNO_);

				result += ps.executeUpdate();
				ps.close();

				if (result == 2) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");

					parameterObject.set("applerId", request
							.getParameter("applerId_" + countsArr[i]));
					String toEmail = seServices.getapplyemail(parameterObject);

					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendFacilitiesApplyMail01(
								"您好：你申请的设施维修已整改完成 请您对此次整改结果进行满意度评价！",
								request.getParameter("chinesname_"
										+ countsArr[i]),
								toEmail,
								request.getParameter("COMPLETIONDATE_"
										+ countsArr[i]),
								request.getParameter("crfnote_" + countsArr[i]),
								admin.getCpnyId());
					}

				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getFacilityRectificationAffirmInfo(request, admin);
		return "/ga_facilitirectification_affirmInfo.jsp";

	}

	public String updateForkliRectification(HttpServletRequest request,
			AdminBean admin,String carType) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");

		try {
			for (int i = 0; i < countsArr.length; i++) {

				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update se_forkliftmaintain_apply t set t.workh=?, t.zhengaijiegu=?,t.crfnote=?,t.cou=?,t.completiondate=to_date(?,'yyyy-mm-dd'),t.MANY=? where t.applyno=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request
						.getParameter("ZHENGAIJIEGU_" + countsArr[i]);
				String bb = request.getParameter("MANY_" + countsArr[i]);
				String cc = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, request.getParameter("WORK_H_" + countsArr[i]));
				ps.setString(2, aa);
				ps.setString(3, request.getParameter("CRFNOTE_" + countsArr[i]));
				ps.setString(4, request.getParameter("COU_" + countsArr[i]));
				ps.setString(5, request.getParameter("COMPLETIONDATE_"+countsArr[i]));
				ps.setString(6, bb);
				ps.setString(7, cc);// 申请号
				result += ps.executeUpdate();
				ps.close();

				// 完成标示
				String sql2 = " update se_forkliftmaintain_apply t set t.isconfirm = 1 where t.applyno= ? ";
				Logger.getLogger(getClass()).debug(sql2);
				ps = conn.prepareStatement(sql2);

				String APPLYNO_ = request.getParameter("APPLYNO_"
						+ countsArr[i]);
				ps.setString(1, APPLYNO_);

				result += ps.executeUpdate();
				ps.close();

				if (result == 2) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");

					parameterObject.set("applerId", request
							.getParameter("applerId_" + countsArr[i]));
					String toEmail = seServices.getapplyemail(parameterObject);

					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendFacilitiesApplyMail01(
								"您好：你申请的设施维修已整改完成 请您对此次整改结果进行满意度评价！",
								request.getParameter("chinesname_"
										+ countsArr[i]),
								toEmail,
								request.getParameter("FAULTDATE_"
										+ countsArr[i]),
								request.getParameter("crfnote_" + countsArr[i]),
								admin.getCpnyId());
					}

				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getForkliRectificationAffirmInfo(request, admin, carType);
		return "/ga_facilitirectification_affirmInfo.jsp";

	}
	
	// 进度保存
	public String updateFacilitiesRectification03(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");

		try {
			for (int i = 0; i < countsArr.length; i++) {

				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update SE_COMPLETRECTIFICAT_APPLY t set t.zhengaijiegu=? where t.applyno=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request
						.getParameter("ZHENGAIJIEGU_" + countsArr[i]);
				String bb = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, aa);
				ps.setString(2, bb);// 申请号
				result += ps.executeUpdate();
				ps.close();

				if (result == 1) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");
				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getFacilityRectificationAffirmInfo(request, admin);
		return "/ga_facilitirectification_affirmInfo.jsp";

	}
	
	public String updateForkliRectification03(HttpServletRequest request,
			AdminBean admin,String carType) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");

		try {
			for (int i = 0; i < countsArr.length; i++) {

				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update se_forkliftmaintain_apply t set t.zhengaijiegu=? where t.applyno=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request
						.getParameter("ZHENGAIJIEGU_" + countsArr[i]);
				String bb = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, aa);
				ps.setString(2, bb);// 申请号
				result += ps.executeUpdate();
				ps.close();

				if (result == 1) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");
				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveForkliRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getForkliRectificationAffirmInfo(request, admin, carType);
		return "/ga_facilitirectification_affirmInfo.jsp";

	}
	

	// 维修情况更新 满意度
	public String updateFacilitiesManYiDu(HttpServletRequest request,
			AdminBean admin) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");

		try {
			for (int i = 0; i < countsArr.length; i++) {
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update SE_COMPLETRECTIFICAT_APPLY t set t.many=? where t.applyno=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request.getParameter("MANY_" + countsArr[i]);
				String bb = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, aa);
				ps.setString(2, bb);// 申请号
				result += ps.executeUpdate();
				ps.close();

				if (result == 1) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");
				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getFacilityRectificationAffirmInfo(request, admin);
		return "/ga_facilitirectification_affirmInfo.jsp";

	}
	//20190426EXCEL导出
	// 参观者申请更新 满意度
	public String updateVisiterManYiDu(HttpServletRequest request,
			AdminBean admin) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");

		try {
			for (int i = 0; i < countsArr.length; i++) {
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update ga_visiter_apply t set t.many=? where t.GA_VISITER_APPLY_ID=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request.getParameter("MANY_" + countsArr[i]);
				String bb = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, aa);
				ps.setString(2, bb);// 申请号
				result += ps.executeUpdate();
				ps.close();

				if (result == 1) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");
				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getFacilityRectificationAffirmInfo(request, admin);
		return "/ga_visiter_approval_information.jsp";

	}
	public String updateForkliManYiDu(HttpServletRequest request,
			AdminBean admin,String carType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		String countsArr[] = request.getParameterValues("counts");

		try {
			for (int i = 0; i < countsArr.length; i++) {
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
				conn.setAutoCommit(false);
				String sql1 = " update se_forkliftmaintain_apply t set t.many=? where t.applyno=? ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);

				String aa = request.getParameter("MANY_" + countsArr[i]);
				String bb = request.getParameter("APPLYNO_" + countsArr[i]);
				ps.setString(1, aa);
				ps.setString(2, bb);// 申请号
				result += ps.executeUpdate();
				ps.close();

				if (result == 1) {
					conn.commit();
					request.setAttribute("errorMsg", "保存成功！");
				} else {
					request.setAttribute("errorMsg", "保存失败！");
					conn.rollback();
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}
		this.getForkliRectificationAffirmInfo(request, admin, carType);
		return "/ga_facilitirectification_affirmInfo.jsp";

	}
	
	public String updateManYiFacilitiesRectification(
			HttpServletRequest request, AdminBean admin) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());

		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识

		try {
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			conn.setAutoCommit(false);
			String sql1 = " update SE_COMPLETRECTIFICAT_APPLY t set t.many=? where t.applyno=? ";
			Logger.getLogger(getClass()).debug(sql1);
			ps = conn.prepareStatement(sql1);
			ps.setString(1, request.getParameter("MANY"));
			ps.setString(2, request.getParameter("APPLYNO"));// 申请号
			result += ps.executeUpdate();
			ps.close();

			if (result == 1) {
				conn.commit();
				request.setAttribute("errorMsg", "保存成功！");
			} else {
				request.setAttribute("errorMsg", "保存失败！");
				conn.rollback();
			}

		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"saveFacilitiesRectification happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}

		return "/ga_facilitiesMaintenance_apply.jsp";

	}

	/* 得到整完成决裁的信息 */
	public void getFampletedRectificationAffirm(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			parameterObject.set("qryType", "");
			parameterObject.set("cpny_id", cpnyId);
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			// 故障时间
			String completiondate = request.getParameter("createDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("startDate", startDate);
			// 故障时间
			parameterObject.set("completiondate", completiondate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);
			// 取得数据行数
			int resultCount = seServices
					.getFacilityRectificationAffirmListNumber(parameterObject);
			List applyList = seServices.getFacilityRectificationAffirmList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				// AFFIRMORIDEA
				List affirmorList = seServices
						.getCompletedRectificationAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
				for (int y = 0; y < affirmorList.size(); y++) {
					SimpleMap simpleMap = new SimpleMap();
					simpleMap = (SimpleMap) affirmorList.get(y);
					String AFFIRMORIDEA = simpleMap.getString("AFFIRMORIDEA");
					String empid = simpleMap.getString("AFFIRMOR_ID");
					if (empid.equals(admin.getAdminID().toString())) {
						dataMap.set("AFFIRMORIDEA", AFFIRMORIDEA);
					}
				}

			}
			request.setAttribute("maintainType", request.getParameter("applyType")!=null ? request.getParameter("applyType"):"2");
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirm happens Exception. ", e);
		}

	}
	
	public void getForkliftAffirm(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			parameterObject.set("qryType", "");
			parameterObject.set("cpny_id", cpnyId);
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			// 故障时间
			String completiondate = request.getParameter("createDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("startDate", startDate);
			// 故障时间
			parameterObject.set("completiondate", completiondate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);
			// 取得数据行数
			int resultCount = seServices
					.getForkliftMaintainAffirmListNumber(parameterObject);
			List applyList = seServices.getForkliftMaintainAffirmList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				// AFFIRMORIDEA
				List affirmorList = seServices
						.getForkliftMaintainAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
				for (int y = 0; y < affirmorList.size(); y++) {
					SimpleMap simpleMap = new SimpleMap();
					simpleMap = (SimpleMap) affirmorList.get(y);
					String AFFIRMORIDEA = simpleMap.getString("AFFIRMORIDEA");
					String empid = simpleMap.getString("AFFIRMOR_ID");
					if (empid.equals(admin.getAdminID().toString())) {
						dataMap.set("AFFIRMORIDEA", AFFIRMORIDEA);
					}
				}
			}
			request.setAttribute("maintainType", request.getParameter("applyType")!=null ? request.getParameter("applyType"):"2");
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getForkliftMaintainAffirmList happens Exception. ", e);
		}

	}

	/* 得到整完成决裁情况的信息 */
	public String getFacilityRectificationAffirmInfo(
			HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			String adminId = admin.getAdminID();
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			// parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("cpnyId", cpnyId);
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			String completiondate = request.getParameter("createDate");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			// 整改完成判断
			String qryTypeOk = request.getParameter("qryTypeOk") != null ? request
					.getParameter("qryTypeOk")
					: "0";

			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("completiondate", completiondate);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("qryTypeOk", qryTypeOk);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			// 权限
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if ((","
						+ userConfig.getString("common.role.affirmInfo").trim() + ",")
						.indexOf("," + sgNo[i].trim() + ",") > -1) {
					b = true;
					parameterObject.set("applerId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}

			// 取得数据行数
			int resultCount = seServices
					.getFacilityRectificationAffirmInfoListNumber(parameterObject);
			List applyList = seServices.getFacilityRectificationAffirmInfoList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				String applyPersonId = dataMap.getString("PERSON_ID");
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				List affirmorList = seServices
						.getCompletedRectificationAffirmorList(parameterObject1);
				boolean isfalg = true;
				for (int j = 0; j < affirmorList.size(); j++) {
					SimpleMap map1 = (SimpleMap) affirmorList.get(j);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					if (affirmFlag.equals("0") && applyPersonId.equals(adminId)) {
						dataMap.set("AFFIRM_FLAG", "0");
						dataMap.set("affirmorList", affirmorList);
						if (isfalg) {
							dataMap.set("isfalg", "0");
						}
						isfalg = false;
						break;
					} else {
						isfalg = false;
						dataMap.set("AFFIRM_FLAG", "1");
						dataMap.set("isfalg", "1");
						dataMap.set("affirmorList", affirmorList);
					}
				}
			}
			request.setAttribute("maintainType", request.getParameter("applyType")!=null ? request.getParameter("applyType"):"2");
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("qryTypeOk", qryTypeOk);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirm happens Exception. ", e);
		}

		return "/ga_facilitirectification_affirmInfo.jsp";
	}

	
	public String getForkliRectificationAffirmInfo(
			HttpServletRequest request, AdminBean admin,String carType) {
		try {

			SimpleMap parameterObject = null;
			String adminId = admin.getAdminID();
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("cpnyId", cpnyId);
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			String completiondate = request.getParameter("createDate");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			// 整改完成判断
			String qryTypeOk = request.getParameter("qryTypeOk") != null ? request
					.getParameter("qryTypeOk")
					: "0";

			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("completiondate", completiondate);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("qryTypeOk", qryTypeOk);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			// 权限
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if ((","
						+ userConfig.getString("common.role.affirmInfo").trim() + ",")
						.indexOf("," + sgNo[i].trim() + ",") > -1) {
					b = true;
					parameterObject.set("applerId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}

			// 取得数据行数
			int resultCount = seServices
					.getForkliRectificationAffirmInfoListNumber(parameterObject);
			List applyList = seServices.getForkliRectificationAffirmInfoList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				String applyPersonId = dataMap.getString("PERSON_ID");
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				List affirmorList = seServices
						.getExForkliRectificationAffirmorList(parameterObject1);
				boolean isfalg = true;
				for (int j = 0; j < affirmorList.size(); j++) {
					SimpleMap map1 = (SimpleMap) affirmorList.get(j);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					if (affirmFlag.equals("0") && applyPersonId.equals(adminId)) {
						dataMap.set("AFFIRM_FLAG", "0");
						dataMap.set("affirmorList", affirmorList);
						if (isfalg) {
							dataMap.set("isfalg", "0");
						}
						isfalg = false;
						break;
					} else {
						isfalg = false;
						dataMap.set("AFFIRM_FLAG", "1");
						dataMap.set("isfalg", "1");
						dataMap.set("affirmorList", affirmorList);
					}
				}
			}
			request.setAttribute("maintainType", carType);
			request.setAttribute("applyType", request.getParameter("applyType")!=null ? request.getParameter("applyType"):"2");
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("qryTypeOk", qryTypeOk);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirm happens Exception. ", e);
		}

		return "/ga_facilitirectification_affirmInfo.jsp";
	}
	
	public String ExceFacilitiRectiFication(HttpServletRequest request,
			AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			// parameterObject.set("deptId", admin.getDeptID());
			SimpleMap dataMap = null;
			String completiondate = request.getParameter("createDate");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("completiondate", completiondate);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			// 权限
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if ((","
						+ userConfig.getString("common.role.affirmInfo").trim() + ",")
						.indexOf("," + sgNo[i].trim() + ",") > -1) {
					b = true;
					parameterObject.set("applerId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}
			List applyList = seServices
					.getExcelFacilityRectificationAffirmInfoList(parameterObject);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				String applyPersonId = dataMap.getString("PERSON_ID");
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				List affirmorList = seServices
						.getCompletedRectificationAffirmorList(parameterObject1);
				boolean isfalg = true;
				for (int j = 0; j < affirmorList.size(); j++) {
					SimpleMap map1 = (SimpleMap) affirmorList.get(j);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					if (affirmFlag.equals("0") && applyPersonId.equals(adminId)) {
						dataMap.set("AFFIRM_FLAG", "0");
						dataMap.set("affirmorList", affirmorList);
						if (isfalg) {
							dataMap.set("isfalg", "0");
						}
						isfalg = false;
						break;
					} else {
						isfalg = false;
						dataMap.set("AFFIRM_FLAG", "1");
						dataMap.set("isfalg", "1");
						dataMap.set("affirmorList", affirmorList);
					}
				}
			}

			request.setAttribute("checkList", applyList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		// this.getAllWaitConfirmList(request, admin);
		return "/ga_Faciliti_Exce.jsp";

	}

	
	public String ExceForkliRectiFication(HttpServletRequest request,
			AdminBean admin) {
		this.putToolbarInfo(request);
		SimpleMap parameterObject = null;
		parameterObject = ObjectBindUtil.getData(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			SimpleMap dataMap = null;
			String completiondate = request.getParameter("createDate");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("completiondate", completiondate);
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			// 权限
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if ((","
						+ userConfig.getString("common.role.affirmInfo").trim() + ",")
						.indexOf("," + sgNo[i].trim() + ",") > -1) {
					b = true;
					parameterObject.set("applerId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}
			List applyList = seServices
					.getExcelForkliRectificationAffirmInfoList(parameterObject);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				String applyPersonId = dataMap.getString("PERSON_ID");
				parameterObject1.set("applyno", dataMap.get("APPLYNO"));
				List affirmorList = seServices
						.getExForkliRectificationAffirmorList(parameterObject1);
				boolean isfalg = true;
				for (int j = 0; j < affirmorList.size(); j++) {
					SimpleMap map1 = (SimpleMap) affirmorList.get(j);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					if (affirmFlag.equals("0") && applyPersonId.equals(adminId)) {
						dataMap.set("AFFIRM_FLAG", "0");
						dataMap.set("affirmorList", affirmorList);
						if (isfalg) {
							dataMap.set("isfalg", "0");
						}
						isfalg = false;
						break;
					} else {
						isfalg = false;
						dataMap.set("AFFIRM_FLAG", "1");
						dataMap.set("isfalg", "1");
						dataMap.set("affirmorList", affirmorList);
					}
				}
			}

			request.setAttribute("checkList", applyList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",
					e);
		}
		return "/ga_Forkli_Exce.jsp";
	}
	///20190426 参观者 EXCEL下载
	public String ExceVisiterFication(HttpServletRequest request,AdminBean admin) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpnyIdd = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : cpnyIdd;
		//AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		request.setAttribute("adminId", adminId);
		SimpleMap map = null;
		SimpleMap map1 = null;
	    SimpleMap parameterObject = null;
	    VisiterApplicationsServices visiterApplicationsServices =new VisiterApplicationsServices();
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
			parameterObject.set("cpnyId", cpnyId);
			String qryType = (String) (parameterObject.get("qryType")!=null?parameterObject.get("qryType"):"0");
			parameterObject.set("qryType",qryType);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("supervisor", "");			
			
			parameterObject.set("ADMIN_ID", admin.getAdminID());
			}
			
		}
			//参观者担当可以查看所有
			for (int i = 0; i < sgNo.length; i++) {
				if ((",11,").indexOf(","+sgNo[i].trim()+",") > -1 ){
					b = true;
				parameterObject.set("supervisor", "");			
				
				parameterObject.set("ADMIN_ID", "");
				}
				
			}
			int allvisiterApplyInformationCount = visiterApplicationsServices.allvisiterAffrimInfoCount(parameterObject);
			
			List allvisiterApplyInformation = visiterApplicationsServices.allvisiterAffrimInfoList(parameterObject, currentPage, 10000);
			
			int allvisiterApplyInformationSize = allvisiterApplyInformation.size();
			for(int i=0 ; i<allvisiterApplyInformationSize ; i++){
				map = (SimpleMap) allvisiterApplyInformation.get(i);
				String applyId = map.getString("GA_VISITER_APPLY_ID");
				request.setAttribute("applyId", applyId);
				parameterObject.set("applyId", applyId);
				List allVisiterApproval = visiterApplicationsServices.allVisiterApproval(parameterObject);
				//boolean isfalg = true;
				for(int j=0 ; j<allVisiterApproval.size() ; j++){
					map1 = (SimpleMap) allVisiterApproval.get(j);
					
					String applyNo= map1.getString("APPLY_NO");
					String AFFIRMORIDEA= map1.getString("AFFIRMORIDEA");
					map.set("applyNo", applyNo);
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					//String affirmor_id = map1.getString("AFFIRMOR_ID");
					
					if(affirmFlag!=null&&affirmFlag.equals("0")){
						map.set("AFFIRM_FLAG", "0");
						map.set("allVisiterApproval", allVisiterApproval);
//						if(isfalg){
//							map.set("isfalg", "0");
//						}
//						isfalg = false;

					}else{
//						isfalg = false;
						map.set("AFFIRM_FLAG", "1");
//						map.set("isfalg", "1");
						map.set("allVisiterApproval", allVisiterApproval);
						break;
					}
				}
				
			}
		

			request.setAttribute("checkList", allvisiterApplyInformation);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(" oingConfirm  happends Exception. ",e);
		}
		return "/ga_Visiter_Exce.jsp";
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

	private void sendApplyComplateMail(String title, String adminid,
			String applyer, String toEmail, String rectificationdate,
			String overdate, String over, String cpny_id) throws Exception {

		SimpleMap parameterObject = new SimpleMap();

		SimpleMap inputData = new SimpleMap();

		parameterObject.set("applerId", adminid);
		parameterObject.set("applyer", applyer);
		// List result = seServices.getapplyemail(parameterObject);

		StringBuffer content = new StringBuffer();

		inputData.set("申请人", applyer);
		inputData.set("要求整改日期", rectificationdate);
		inputData.set("实际完成日期", overdate);
		inputData.set("整改情况及结果", over);

		mail.gaSendMail(inputData, cpny_id, toEmail, "安全隐患反馈申请，请你确认");
	}

	/* 进行决裁 */
	public String FacilitiesCrfongingAffirm(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);

			String adminId = admin.getAdminID();
			String adminName = admin.getChineseName();
			request.setAttribute("adminName", adminName);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(Calendar.getInstance().getTime());
			parameterObject = ObjectBindUtil.getData(request);
			try {
				String applyNoId = request.getParameter("applyno");
				String applyno[] = request.getParameterValues("applyno");
				String flag = request.getParameter("flag");
				parameterObject.set("flag", flag);
				parameterObject.set("adminId", adminId);
				for (int i = 0; i < applyno.length; i++) {
					String affirmNo = request.getParameter("affirmNo_"
							+ applyno[i]);
					String affirmNoName = request.getParameter("applerIdName_"
							+ applyno[i]);
					String applerId = request.getParameter("applerId_"
							+ applyno[i]);
					String documentNo = request.getParameter("documentno_"
							+ applyno[i]);
					String documentnoCrfno = request
							.getParameter("documentnoCrfno_" + applyno[i]);
					String fcompletionDate = request
							.getParameter("fcompletionDate_" + applyno[i]);
					parameterObject.set("affirmNo", affirmNo);
					parameterObject.set("applerId", applerId);
					parameterObject.set("affirmNoName", affirmNoName);
					parameterObject.set("documentnoCrfno", documentnoCrfno);
					parameterObject.set("fcompletionDate", fcompletionDate);
					parameterObject.set("applyNoId", applyNoId);
					// parameterObject.set("affirmorIdea",affirmorIdea);
					parameterObject.set("applyno", applyno[i]);
					parameterObject.set("documentNo", documentNo);
					// seServices.updateComplate(parameterObject);
					temp = seServices.crfongingAffirm(parameterObject);
					temp = seServices.crfongingAffirm01(parameterObject);
					if (request.getParameter("flag").equals("2")) {
						seServices
								.facilityConfirmCrfongingApply(parameterObject);
						// seServices.confirmCrfongingRecord(parameterObject);//记录表中变否决
						String toEmail = seServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendFacilitiesApplyMail02("设施维修申请 已被否决",
									affirmNoName, toEmail, documentnoCrfno,
									fcompletionDate, admin.getCpnyId());
						}
					} else if (request.getParameter("flag").equals("1")) {
						String MAX_AFFIRM_FLAG = request
								.getParameter("MAX_AFFIRM_FLAG_" + applyno[i]);
						parameterObject.set("affrimlevel", request
								.getParameter("AFFIRM_LEVEL_" + applyno[i]));
						if (MAX_AFFIRM_FLAG.equals("0")) {
							temp = seServices
									.crfongingAffirm03(parameterObject);
							String toEmail = seServices
									.getlevelaffmail(parameterObject);
							if (!toEmail.equals("") && toEmail != null
									&& essSysparam.isGaSendMail()) {
								sendFacilitiesApplyMail02("设施维修申请 请您确认",
										affirmNoName, toEmail, documentnoCrfno,
										fcompletionDate, admin.getCpnyId());
							}
						} else {
							// seServices
							// .facilityConfirmCrfongingApply(parameterObject);
							// parameterObject.set("flag", "3");
							// seServices.confirmCrfongingRecord(parameterObject);//记录边中变否决
							temp = seServices
							.crfongingAffirm04(parameterObject);
							String toEmail = seServices
									.getapplyemail(parameterObject);
							if (!toEmail.equals("") && toEmail != null
									&& essSysparam.isGaSendMail()) {
								sendFacilitiesApplyMail02("设施维修申请 已通过确认",
										affirmNoName, toEmail, documentnoCrfno,
										fcompletionDate, admin.getCpnyId());
								
								sendFacilitiesApplyMail02("设施维修申请 已通过确认，请您进行维修 谢谢！",
										affirmNoName, "tao.zhao@doosan.com", documentnoCrfno,
										fcompletionDate, admin.getCpnyId());
							}
						}
					}
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("visiterApproval error ", e);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getFampletedRectificationAffirm(request, admin);
		return "/ga_facilitiesMaintenanceA_affirm.jsp";

	}

	public String ForkliftMaintainCrfongingAffirm(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		try {
			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			String adminId = admin.getAdminID();
			String adminName = admin.getChineseName();
			request.setAttribute("adminName", adminName);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(Calendar.getInstance().getTime());
			parameterObject = ObjectBindUtil.getData(request);
			try {
				String applyno[] = request.getParameterValues("applyno");
				String flag = request.getParameter("flag");
				parameterObject.set("flag", flag);
				parameterObject.set("adminId", adminId);
				parameterObject.set("applyorname_0", request.getParameter("applyorname_0"));
				parameterObject.set("faultDate_fa_0", request.getParameter("faultDate_fa_0"));
				parameterObject.set("detailSa_fa_0", request.getParameter("detailSa_fa_0"));
				for (int i = 0; i < applyno.length; i++) {
					String affirmNoGa = request.getParameter("affirmNo_"
							+ applyno[i]);
					parameterObject.set("affirmNoGa", affirmNoGa);
					parameterObject.set("affirmNo", request.getParameter("applyno"));
					temp = seServices.crforkliAffirm(parameterObject);
					if(!request.getParameter("maxLevelFlag").equals("0")){
						temp = seServices.crForkliAffirm(parameterObject);
					}
					if (request.getParameter("flag").equals("2")) {
						seServices
								.forkliConfirmCrfongingApply(parameterObject);
						String toEmail = seServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendFacilitiesApplyMail02("叉车维修申请 已被否决",
									parameterObject.getString("applyorname_0"), toEmail, parameterObject.getString("detailSa_fa_0"),
									parameterObject.getString("faultDate_fa_0"), admin.getCpnyId());
						}
					} else if (request.getParameter("flag").equals("1")) {
						String MAX_AFFIRM_FLAG = request
								.getParameter("maxLevelFlag");
						parameterObject.set("affrimlevel", request
								.getParameter("AFFIRM_LEVEL_0"));
						if (MAX_AFFIRM_FLAG.equals("0")) {
							String toEmail = seServices
									.getlevelaffmail(parameterObject);
							if (!toEmail.equals("") && toEmail != null
									&& essSysparam.isGaSendMail()) {
								sendFacilitiesApplyMail02("叉车维修申请 请您确认",
										parameterObject.getString("applyorname_0"), toEmail, parameterObject.getString("detailSa_fa_0"),
										parameterObject.getString("faultDate_fa_0"), admin.getCpnyId());
							}
						} else {
							String toEmail = seServices
									.getapplyemail(parameterObject);
							if (!toEmail.equals("") && toEmail != null
									&& essSysparam.isGaSendMail()) {
								sendFacilitiesApplyMail02("叉车维修申请 已通过确认",
										parameterObject.getString("applyorname_0"), toEmail, parameterObject.getString("detailSa_fa_0"),
										parameterObject.getString("faultDate_fa_0"), admin.getCpnyId());
								
								sendFacilitiesApplyMail02("叉车维修申请 已通过确认，请您进行维修 谢谢！",
										parameterObject.getString("applyorname_0"), "tao.zhao@doosan.com", parameterObject.getString("detailSa_fa_0"),
										parameterObject.getString("faultDate_fa_0"), admin.getCpnyId());
							}
						}
					}
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("visiterApproval error ", e);
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getForkliftAffirm(request, admin);
		return "/ga_facilitiesMaintenanceA_affirm.jsp";

	}
	
	// 决裁者发送邮件 按条件发送
	public String sendEmailFacilities(HttpServletRequest request,
			AdminBean admin) {

		admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject = null;
		boolean temp = false;
		try {
			parameterObject = ObjectBindUtil.getData(request);
			SimpleMap inputData = new SimpleMap();
			String countsArr[] = request.getParameterValues("counts");

			for (int i = 0; i < countsArr.length; i++) {
				inputData.set("applyNo", request.getParameter("applyNo_"
						+ countsArr[i]));
				inputData.set("applyorid", request.getParameter("applyer_"
						+ countsArr[i]));
				inputData.set("crfno_", request.getParameter("crfno_"
						+ countsArr[i]));
				inputData.set("chinesname_", request.getParameter("chinesname_"
						+ countsArr[i]));
				inputData.set("completiondate_", request
						.getParameter("completiondate_" + countsArr[i]));
				inputData.set("adminId", admin.getAdminID());
				inputData.set("affirmorIdea", request
						.getParameter("affirmorIdea_" + countsArr[i]));

				String chinesName = inputData.getString("chinesname_");
				String crfno = inputData.getString("crfno_");
				String completiondate = inputData.getString("completiondate_");
				String applerId = request.getParameter("personId");
				String chinesenamewd = request.getParameter("chinesenamewd");
				String applyNo = request
						.getParameter("applyNo_" + countsArr[i]);
				parameterObject.set("applyNo", applyNo);
				parameterObject.set("chinesenamewd", chinesenamewd);
				parameterObject.set("applerId", applerId);
				// 添加设施维修 决裁担当信息
				temp = seServices.crfongingAffirmWD(parameterObject);
				String toEmail = seServices.getapplyemail(parameterObject);
				// 發送郵件
				sendFacilitiesApplyMail01("设施维修申请决裁完成,请进行修改", chinesName,
						toEmail, completiondate, crfno, admin.getCpnyId());
				// request.setAttribute("errorMsg", "发送成功！");

			}
		} catch (Exception e) {
			// request.setAttribute("errorMsg", "发送失败！");

			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
		this.getFampletedRectificationAffirm(request, admin);
		return "/ga_facilitiesMaintenanceA_affirm.jsp";
	}

	public String sendEmailFokliftMantain(HttpServletRequest request,
			AdminBean admin,String maType) {

		admin = (AdminBean) request.getSession().getAttribute("admin");
		SimpleMap parameterObject = null;
		boolean temp = false;
		try {
			parameterObject = ObjectBindUtil.getData(request);
			SimpleMap inputData = new SimpleMap();
			String countsArr[] = request.getParameterValues("counts");

			for (int i = 0; i < countsArr.length; i++) {
				inputData.set("applyNo", request.getParameter("applyNo_"
						+ countsArr[i]));
				inputData.set("applyorid", request.getParameter("applyer_"
						+ countsArr[i]));
				inputData.set("crfno_", request.getParameter("detailSa_fa_"
						+ countsArr[i]));
				inputData.set("chinesname_", request.getParameter("chinesname_"
						+ countsArr[i]));//维修内容
				inputData.set("faultDate_fa_", request
						.getParameter("faultDate_fa_" + countsArr[i]));
				inputData.set("adminId", admin.getAdminID());
				inputData.set("affirmorIdea", request
						.getParameter("affirmorIdea_" + countsArr[i]));

				String chinesName = inputData.getString("chinesname_");
				String crfno = inputData.getString("crfno_");
				String completiondate = inputData.getString("faultDate_fa_");
				String applerId = request.getParameter("personId");
				String chinesenamewd = request.getParameter("chinesenamewd");
				String applyNo = request
						.getParameter("applyNo_" + countsArr[i]);
				parameterObject.set("applyNo", applyNo);//申请编号
				parameterObject.set("chinesenamewd", chinesenamewd);//维修担当   
				parameterObject.set("applerId", applerId);
				// 添加设施维修 决裁担当信息
				temp = seServices.crForkliAffirmWD(parameterObject);
				String toEmail = seServices.getapplyemail(parameterObject);
				// 發送郵件
				sendFacilitiesApplyMail01("叉车维修申请决裁完成,请进行修改", chinesName,
						toEmail, completiondate, crfno, admin.getCpnyId());
				request.setAttribute("errorMsg", "发送成功！");

			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
		if(maType.equals("2")){
			this.getFampletedRectificationAffirm(request, admin);
		}else{
			this.getForkliftAffirm(request, admin);
		}
		return "/ga_facilitiesMaintenanceA_affirm.jsp";
	}
	
	private void sendComplateApplyMail(String title, String applyer,
			String toEmail, String overdate, String over, String cpny_id)
			throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人：", applyer);
		inputData.set("实际完成日期：", overdate);
		inputData.set("整改情况及结果：", over);

		mail.gaSendMail(inputData, cpny_id, toEmail, title);

	}

	private void sendFacilitiesApplyMail(String title, String applyer,
			String toEmail, String over, String cpny_id) throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人：", applyer);
		// inputData.set("实际完成日期：", overdate);
		inputData.set("整改情况及结果：", over);

		mail.gaSendMail(inputData, cpny_id, toEmail, title);

	}

	private void sendFacilitiesApplyMail02(String title, String applyer,
			String toEmail, String over, String overdate, String cpny_id)
			throws Exception {

		SimpleMap inputData = new SimpleMap();

		// inputData.set("您申请的维修设施","已通过决裁");
		inputData.set("申请人", applyer);
		inputData.set("故障时间", overdate);
		inputData.set("要求维修内容", over);

		mail.gaSendMail(inputData, cpny_id, toEmail, title);

	}

	private void sendFacilitiesApplyMail01(String title, String applyer,
			String toEmail, String overdate, String over, String cpny_id)
			throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人", applyer);
		inputData.set("故障时间", overdate);
		inputData.set("要求维修内容", over);

		mail.gaSendMail(inputData, cpny_id, toEmail, title);

	}

	/* 判断是否可以进行删除该条信息 */
	public int isCanDelete(String applyTable, String affirmTable,
			String applyno, String admin) {
		int result = 6;
		int temp = 0;
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " select affirm.affirm_flag from " + applyTable + " t, "
				+ affirmTable + " affirm where affirm.apply_no= t.applyno "
				+ " and t.applyno='" + applyno + "' and t.applyorid='" + admin
				+ "'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				temp += rs.getInt("affirm_flag");
				result += 1;
			}

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("是否可以删除！", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		if (result == 6) {
			temp = 1;
		}
		return temp;
	}

	/* 进行删除整改计划操作 */
	public String oingCorrectivePlanDelete(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;

		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			temp = seServices.oingCorrectivePlanDelete(parameterObject);

		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"oingCorrectivePlanDelete happens Exception. ", e);
		}
		this.getCorrectivePlanAffirm(request, admin);
		if (!temp) {
			request.setAttribute("errorMsg", "操作失败！");
		}
		return "/ga_correctiveplan_affirmInfo.jsp";

	}

	/* 进行删除整改完成操作 */
	public String oingCompletedRectificationDelete(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;

		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			temp = seServices.oingCompletedRectificationDelete(parameterObject);

		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"oingCompletedRectificationDelete happens Exception. ", e);
		}
		this.getCorrectivePlanAffirm(request, admin);
		if (!temp) {
			request.setAttribute("errorMsg", "操作失败！");
		}
		return "/ga_completedrectification_affirmInfo.jsp";

	}

	/* 上传图片 */
	public void uploadImp(HttpServletRequest request) {
		int result = 0; // 做为整体提交的表示符号
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		DiskFileUpload fu = new DiskFileUpload();
		if (request.getParameter("jpgUpload") != null
				&& request.getParameter("jpgUpload") != "") {
			int Sequence = this.getSequence("SE_COMPLETRECTIFICAT_APPLY_SEQ");
			try {
				conn.setAutoCommit(false);
				ps = conn
						.prepareStatement(" insert into SE_COMPLETRECTIFICAT_APPLY(APPLYNO,SECURITYCHECKSNO,CRFHOTOS) values(?,?,?)");
				ps.setInt(1, Sequence);
				ps.setString(2, request.getParameter("documentno"));
				ps.setBlob(3, BLOB.empty_lob());
				result += ps.executeUpdate();
				ps = conn
						.prepareStatement(" select CRFHOTOS from SE_COMPLETRECTIFICAT_APPLY where SECURITYCHECKSNO='"
								+ request.getParameter("documentno")
								+ "' for update ");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result += 1;
				}
				BLOB resClob = (BLOB) rs.getBlob(1);
				InputStream instrem = null; // 将二进制数据写入Blob
				List fileItems = null;
				try {
					fileItems = fu.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				Iterator iter = null;
				if (fileItems != null) {
					iter = fileItems.iterator();
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();
						if (!item.isFormField()) {
							instrem = item.getInputStream();
						}
					}
				}
				BufferedInputStream in = new BufferedInputStream(instrem);
				OutputStream outStream = resClob.getBinaryOutputStream();
				byte[] buf = new byte[10240 * 1024];
				int len = 0;
				while ((len = in.read(buf)) != -1) {
					outStream.write(buf, 0, len);
				}
				in.close();
				outStream.close();
				ps = conn
						.prepareStatement("update SE_COMPLETRECTIFICAT_APPLY set CRFHOTOS=? where SECURITYCHECKSNO='"
								+ request.getParameter("documentno") + "' ");
				ps.setBlob(1, resClob);
				result += ps.executeUpdate();
				if (result == 3) {
					conn.commit();
				} else {
					conn.rollback();
				}

			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				request.setAttribute("errorMsg", "修改检查记录失败！");
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	/* 得到序列号 */
	public int getSequence(String seqName) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT " + seqName + ".NEXTVAL FROM DUAL";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				result = rs.getInt(1);
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得序列号失败", e);
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
	}

	public void DelAffirmInfo(HttpServletRequest request, AdminBean admin) {
		String SECURITYCHECKSNO = request.getParameter("SECURITYCHECKSNO");
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("SECURITYCHECKSNO", SECURITYCHECKSNO);
		String APPLYNO = seServices.getApplayInfo(parameterObject);
		parameterObject.set("APPLYNO", APPLYNO);
		boolean temp = false;
		try {
			temp = seServices.DelAffirmInfoFa(parameterObject);
			request.setAttribute("errorMsg", "删除成功！");
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"oingCompletedRectificationDelete happens Exception. ", e);
		}
		if (!temp) {
			request.setAttribute("errorMsg", "操作失败！");
		}
	}
	
	public void DelForkliAffirmInfo(HttpServletRequest request, AdminBean admin) {
		String FORKLIFTMAINTAINNO = request.getParameter("FORKLIFTMAINTAINNO");
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("FORKLIFTMAINTAINNO", FORKLIFTMAINTAINNO);
		String APPLYNO = seServices.getForkliApplayInfo(parameterObject);
		parameterObject.set("APPLYNO", APPLYNO);
		boolean temp = false;
		try {
			temp = seServices.DelForkliAffirmInfoFa(parameterObject);
			request.setAttribute("errorMsg", "删除成功！");
		} catch (Exception e) {
			temp = false;
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"oingForkliRectificationDelete happens Exception. ", e);
		}
		if (!temp) {
			request.setAttribute("errorMsg", "操作失败！");
		}
	}

}
