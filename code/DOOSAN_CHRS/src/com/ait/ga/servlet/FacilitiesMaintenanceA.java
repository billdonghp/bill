package com.ait.ga.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-24
 * 
 */
public class FacilitiesMaintenanceA implements Command {
	private SecurityEnvironmentServices seServices = null;
	private String url = "http://10.40.128.28:8083/";
	// private String url = "http://portal.doosan.com" ;
	// private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	// private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	// private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	private Mail mail = new Mail();
	private EssSysparam essSysparam = null;

	public FacilitiesMaintenanceA() {
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
		} else if (!content.equals("")
				&& content.equals("facilitiesMaintenance")) {
			String documents = FormUtil.getApplyDocumentidFa("DOCUMENTNO",
					"SE_SECURITYCHECKS_RECORD", "JZ");
			String isType=request.getParameter("maintainType")!=null?request.getParameter("maintainType"):"2";
			request.setAttribute("maintainType", isType);
			request.setAttribute("documentnoBian", documents.toString());
			returnPage = this.getIsNotExitsSecurityEnvironment(request, admin);
		}else if (!content.equals("")
				&& content.equals("maintainChange")) {
			String isType=request.getParameter("applyType")!=null?request.getParameter("applyType"):"2";
			String maintainTypeNo = "1";
			String documents = "1";
			if(isType.equals("2")){
			//设施
			 documents = FormUtil.getApplyDocumentidFa("DOCUMENTNO",
					"SE_SECURITYCHECKS_RECORD", "JZ");
			}else{
			//叉车申请
			 maintainTypeNo = FormUtil.getApplyDocumentid("FORKLIFTMAINTAINNO",
					"SE_FORKLIFTMAINTAIN_APPLY", "FR");
			}
			request.setAttribute("documentnoBian", documents.toString());
			request.setAttribute("frNo", maintainTypeNo.toString());
			request.setAttribute("maintainType", isType);
			returnPage = this.getIsNotExitsSecurityEnvironment(request, admin);
		} else if (!content.equals("")
				&& content.equals("addCompletedRectificationApply")) {
			returnPage = this.addCompletedRectificationApply(request, admin);
		} else if (!content.equals("")
				&& content.equals("completedRectificationAffirm")) {
			this.getCompletedRectificationAffirm(request, admin);
			returnPage = "/ga_completedrectification_affirm.jsp";
		} else if (!content.equals("")
				&& content.equals("completedRectificationAffirmInfo")) {
			returnPage = this.getCompletedRectificationAffirmInfo(request,
					admin);
		} else if (!content.equals("") && content.equals("crfongingAffirm")) {
			returnPage = this.crfongingAffirm(request, admin);
		} else if (!content.equals("")
				&& content.equals("oingcorrectivePlanDelete")) {
			returnPage = this.oingCorrectivePlanDelete(request, admin);
		} else if (!content.equals("")
				&& content.equals("oingcompletedRectificationDelete")) {
			returnPage = this.oingCompletedRectificationDelete(request, admin);
		} else if (!content.equals("") && content.equals("uploadImp")) {
			this.uploadImp(request);
			returnPage = "/ga_upload.jsp?Directive=CloseWindow";
		} else if (!content.equals("") && content.equals("DelAffirmInfo")) {
			this.DelAffirmInfo(request, admin);
			returnPage = this.getCompletedRectificationAffirmInfo(request,
					admin);
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

	// 设施维修申请
	public String getIsNotExitsSecurityEnvironment(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			PreparedStatement ps = null;
			GaAffirm gaAffirm = new GaAffirm();
			List affirorList = gaAffirm.getAffirmor1(admin.getAdminID(),
					"SecurityComplateApply", essSysparam.isContainsOwner());
			request.setAttribute("affirmList", affirorList);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getIsNotExitsSecurityEnvironment happens Exception. ", e);
		}
		return "/ga_facilitiesMaintenance_apply.jsp";

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

	/* 增加整改完成的申请信息 */
	public String addCompletedRectificationApply(HttpServletRequest request,
			AdminBean admin) {
		boolean temp = false;
		GaAffirmList gaAffirmList = null;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		List affirorList = gaAffirm.getAffirmor(admin.getAdminID(),
				"SecurityComplateApply", essSysparam.isContainsOwner());
		int Sequence = this.getSequence("SE_COMPLETRECTIFICAT_APPLY_SEQ");
		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		int result = 0;// 提交标识
		int isExists = 0;// 操作标识
		int isExistsSequence = 0;// 已存在的sequece

		String date1 = "";
		String AffirmID = "";

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
			/*
			 * if(isExists==1){ String sqlStr1=
			 * " update  SE_COMPLETRECTIFICAT_APPLY set DEPTID=?,APPLYORID=?,CRFNOTE=?,CREATEDATE=SYSDATE,OVERDATE=to_date(?,'yyyy-mm-dd'),COMPLETIONDATE=to_date(?,'yyyy-mm-dd')  where APPLYNO=?"
			 * ; Logger.getLogger(getClass()).debug(sqlStr1); ps =
			 * conn.prepareStatement( sqlStr1); ps.setString(1,
			 * admin.getDeptID()); ps.setString(2, admin.getAdminID());
			 * ps.setString(3, request.getParameter("CompletedRectification"));
			 * ps.setString(4, request.getParameter("overDate"));
			 * ps.setString(5,
			 * request.getParameter("RECTIFICATIONCOMPLETIONDATE"));
			 * ps.setInt(6, isExistsSequence); result +=ps.executeUpdate();
			 * ps.close(); for (int i = 0; i < affirorList.size(); i++) {
			 * gaAffirmList = (GaAffirmList) affirorList.get(i); String sql1=
			 * "     insert into SE_COMPLETRECTIFICAT_AFFIRM(GA_AFFIRM_NO, APPLY_NO,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATED_BY,ACTIVITY,APPLY_TYPE ) values ( "
			 * +
			 * "  SE_COMPLETRECTIFICAT_SEQ.NEXTVAL, ?,?,?,SYSDATE,?,'1', 'SecurityEnvironmentApply' )  "
			 * ; Logger.getLogger(getClass()).debug(sql1); ps =
			 * conn.prepareStatement( sql1); ps.setInt(1, isExistsSequence);
			 * ps.setInt(2, gaAffirmList.getAffirmLevel()); ps.setString(3,
			 * gaAffirmList.getAffirmorId()); ps.setString(4,
			 * admin.getAdminID()); result +=ps.executeUpdate(); ps.close();
			 * if(i==0){ AffirmID = gaAffirmList.getAffirmorId(); } }
			 * 
			 * }else{
			 */
			String sqlStr = " insert into SE_COMPLETRECTIFICAT_APPLY(APPLYNO ,SECURITYCHECKSNO,DEPTID,APPLYORID,CRFNOTE,CRFHOTOS,CREATEDATE ,OVERDATE,COMPLETIONDATE) values( "
					+ " ?,?,?,?,?,?,SYSDATE,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd')) ";
			Logger.getLogger(getClass()).debug(sqlStr);
			ps = conn.prepareStatement(sqlStr);
			ps.setInt(1, Sequence);
			ps.setString(2, request.getParameter("document_number"));
			ps.setString(3, admin.getDeptID());
			ps.setString(4, request.getParameter("SENDPERSON"));
			ps.setString(5, request.getParameter("CompletedRectification"));
			ps.setBlob(6, BLOB.empty_lob());
			ps.setString(7, request.getParameter("overDate"));
			ps
					.setString(8, request
							.getParameter("RECTIFICATIONCOMPLETIONDATE"));
			result += ps.executeUpdate();
			ps.close();
			for (int i = 0; i < affirorList.size(); i++) {
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				String sql1 = "     insert into SE_COMPLETRECTIFICAT_AFFIRM(GA_AFFIRM_NO, APPLY_NO,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATED_BY,ACTIVITY,APPLY_TYPE ) values ( "
						+ "  SE_COMPLETRECTIFICAT_SEQ.NEXTVAL, ?,?,?,SYSDATE,?,'1', 'SecurityEnvironmentApply' )  ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, Sequence);
				ps.setInt(2, gaAffirmList.getAffirmLevel());
				ps.setString(3, gaAffirmList.getAffirmorId());
				ps.setString(4, admin.getAdminID());
				result += ps.executeUpdate();
				ps.close();
				if (i == 0) {
					AffirmID = gaAffirmList.getAffirmorId();
				}
			}
			/* } */

			String sql2 = "  update se_securitychecks_record t set t.iscompletedrectification='1' where t.documentno=?";
			Logger.getLogger(getClass()).debug(sql2);
			ps = conn.prepareStatement(sql2);
			ps.setString(1, request.getParameter("document_number"));
			result += ps.executeUpdate();
			ps.close();
			if (result == (2 + affirorList.size())) {
				conn.commit();
				request.setAttribute("errorMsg", "申请成功！");
				parameterObject.set("applerId", request
						.getParameter("firstaffrim"));
				String toEmail = seServices.getapplyemail(parameterObject);
				if (!toEmail.equals("") && toEmail != null
						&& essSysparam.isGaSendMail()) {
					sendApplyComplateMail(
							"隐患整改反馈 请您确认",
							admin.getAdminID(),
							admin.getChineseName(),
							toEmail,
							request.getParameter("RECTIFICATIONCOMPLETIONDATE"),
							request.getParameter("overDate"), request
									.getParameter("CompletedRectification"),
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

		return "/ga_completedrectification_apply.jsp";

	}

	/* 得到整完成决裁的信息 */
	public void getCompletedRectificationAffirm(HttpServletRequest request,
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
			parameterObject.set("qryType", "");
			parameterObject.set("cpny_id", admin.getCpnyId());
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			// 取得数据行数
			int resultCount = seServices
					.getCompletedRectificationAffirmListNumber(parameterObject);
			List applyList = seServices.getCompletedRectificationAffirmList(
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
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirm happens Exception. ", e);
		}

	}

	/* 得到整完成决裁情况的信息 */
	public String getCompletedRectificationAffirmInfo(
			HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			String adminId = admin.getAdminID();

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

			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid");
			String key = request.getParameter("key");
			parameterObject.set("startDate", startDate);
			parameterObject.set("endDate", endDate);
			parameterObject.set("qryType", qryType);
			parameterObject.set("deptid", deptid);
			parameterObject.set("key", key);

			// 取得数据行数
			int resultCount = seServices
					.getCompletedRectificationAffirmInfoListNumber(parameterObject);
			List applyList = seServices
					.getCompletedRectificationAffirmInfoList(parameterObject,
							currentPage, pageSize);
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
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirm happens Exception. ", e);
		}

		return "/ga_completedrectification_affirmInfo.jsp";
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
	public String crfongingAffirm(HttpServletRequest request, AdminBean admin) {
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
				for (int i = 0; i < applyno.length; i++) {
					String affirmNo = request.getParameter("affirmNo_"
							+ applyno[i]);
					String applerId = request.getParameter("applerId_"
							+ applyno[i]);
					String documentNo = request.getParameter("documentno_"
							+ applyno[i]);

					String affirmorIdea = request.getParameter("affirmorIdea_"
							+ applyno[i]) != null ? request
							.getParameter("affirmorIdea_" + applyno[i]) : "";
					parameterObject.set("affirmNo", affirmNo);
					parameterObject.set("applerId", applerId);
					parameterObject.set("affirmorIdea", affirmorIdea);
					parameterObject.set("applyno", applyno[i]);
					parameterObject.set("documentNo", documentNo);
					// seServices.updateComplate(parameterObject);
					temp = seServices.crfongingAffirm(parameterObject);
					if (request.getParameter("flag").equals("2")) {
						// seServices.confirmCrfongingApply(parameterObject);
						seServices.confirmCrfongingRecord(parameterObject);// 记录表中变否决
						String toEmail = seServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendComplateApplyMail("隐患整改反馈 已被否决", request
									.getParameter("applyorname_" + applyno[i]),
									toEmail, request.getParameter("overdate_"
											+ applyno[i]), request
											.getParameter("useInformation_"
													+ applyno[i]), admin
											.getCpnyId());
						}
					} else if (request.getParameter("flag").equals("1")) {
						String MAX_AFFIRM_FLAG = request
								.getParameter("MAX_AFFIRM_FLAG_" + applyno[i]);
						parameterObject.set("affrimlevel", request
								.getParameter("AFFIRM_LEVEL_" + applyno[i]));
						if (MAX_AFFIRM_FLAG.equals("0")) {
							String toEmail = seServices
									.getupaffrimemail(parameterObject);
							if (!toEmail.equals("") && toEmail != null
									&& essSysparam.isGaSendMail()) {
								sendComplateApplyMail("隐患整改反馈 请您确认", request
										.getParameter("applyorname_"
												+ applyno[i]), toEmail,
										request.getParameter("overdate_"
												+ applyno[i]), request
												.getParameter("useInformation_"
														+ applyno[i]), admin
												.getCpnyId());
							}
						} else {
							seServices.confirmCrfongingApply(parameterObject);
							parameterObject.set("flag", "3");
							seServices.confirmCrfongingRecord(parameterObject);// 记录边中变否决
							String toEmail = seServices
									.getapplyemail(parameterObject);
							if (!toEmail.equals("") && toEmail != null
									&& essSysparam.isGaSendMail()) {
								sendComplateApplyMail("隐患整改反馈 已通过确认", request
										.getParameter("applyorname_"
												+ applyno[i]), toEmail,
										request.getParameter("overdate_"
												+ applyno[i]), request
												.getParameter("useInformation_"
														+ applyno[i]), admin
												.getCpnyId());
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
		this.getCompletedRectificationAffirm(request, admin);
		return "/ga_completedrectification_affirm.jsp";

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
			temp = seServices.DelAffirmInfo(parameterObject);
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

}
