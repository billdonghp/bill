/**
 * 
 */
package com.ait.ga.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.api.resultApi.DooPushAPI;
import com.ait.api.resultApi.KdGoldAPI;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.ExpressApplyAndAffirmServices;
import com.ait.ga.business.SecurityEnvironmentServices;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class ExpressApplyAndAffirmCommand implements Command {
	private ExpressApplyAndAffirmServices eaaServices = null;
	private SecurityEnvironmentServices seServices = null;
	private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt";
	private Mail mail = new Mail();
	private EssSysparam essSysparam = null;

	public ExpressApplyAndAffirmCommand() {
		eaaServices = new ExpressApplyAndAffirmServices();
		seServices = new SecurityEnvironmentServices();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		if (!content.equals("") && content.equals("sendToExpressApplyPage")) {
			this.sendToExpressApplyPage(request, admin);
			returnPage = "/ga_express_apply.jsp";
		} else if (!content.equals("") && content.equals("andExpressApply")) {
			returnPage = this.addExpressApplyMany(request, admin);
		} else if (!content.equals("") && content.equals("expressAffirm")) {
			returnPage = this.getExpressAffirm(request, admin);
		} else if (!content.equals("") && content.equals("ongingAffirm")) {
			returnPage = this.confirmExpressAffirm(request, admin);
		} else if (!content.equals("") && content.equals("expressAffirmInfo")) {
			returnPage = this.getExpressAffirmInfo(request, admin);
		}else if (!content.equals("") && content.equals("excelExpressAffirmInfo")){
			returnPage = this.getExcelExpressAffirmInfo(request, admin);
		}else if (!content.equals("") && content.equals("deleteExpressAffrimInfo")) {
			returnPage = this.deleteExpressAffrimInfo(request, admin);
		} else if (!content.equals("") && content.equals("deleteApply")) {
			returnPage = this.deleteApply(request, admin);
			returnPage = this.getExpressAffirmInfo(request, admin);
		} else {
			return "error.jsp";
		}
		return returnPage;
	}
	

	
	/* 增加快件申请信息,同时添加决裁信息 */
	public String addExpressApplyMany(HttpServletRequest request, AdminBean admin) {
		GaAffirm gaAffirm = new GaAffirm();
//		List affirorList = gaAffirm.getAffirmor(admin.getAdminID(),
//				"ExpressApply",essSysparam.isContainsOwner());
		
		boolean temp = false;
		SimpleMap parameterObject = null;
		List detailList = new ArrayList();
		
		List affirorList = new ArrayList();
		String[] affirmorId = request.getParameterValues("affirmorId");
		String[] affirmorDuty = request.getParameterValues("affirmorDuty");
		String isOver_kg = StringUtil.checkNull(request.getParameter("isOver_kg"),"0");
		String personId=admin.getPersonId();
		int jNo = 1;
		int level = 1;
		for(int i=0;i<affirmorId.length;i++){
			if (isOver_kg.equals("0")){
				if (!affirmorDuty[i].equals("C_12005_43")){
					GaAffirmList vb = new GaAffirmList();
					int affirmFlag=0;
					if(personId.equals(affirmorId[i])){
						affirmFlag=1;
					}
					vb.setAffirmFlag(affirmFlag);
					vb.setAffirmLevel(level);
					level++;
					vb.setAffirmorId(affirmorId[i]);
					affirorList.add(vb);
				}
			}else{
				GaAffirmList vb = new GaAffirmList();
				int affirmFlag=0;
				if(personId.equals(affirmorId[i])){
					affirmFlag=1;
				}
				vb.setAffirmFlag(affirmFlag);
				vb.setAffirmLevel(i+1);
				vb.setAffirmorId(affirmorId[i]);
				affirorList.add(vb);
			}
		}
		
		try {

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			
			String SENDPERSON1 = request.getParameter("SENDPERSON1");
//			double costs = NumberUtil.parseDouble(request.getParameter("costs"));
			String mobileS = request.getParameter("mobileS");
			String citySentS = request.getParameter("citySentS");
			String citySent = request.getParameter("citySent");
			String citySentQ = request.getParameter("citySentQ");
			String citySentD = request.getParameter("citySentD");
			String postCodeS = request.getParameter("postCodeS");
			//System.out.print(SENDPERSON1+costs+citySent);
			
			// bind apply detail parameter
			int size = parameterObject.getInt("maxRowNum");
			SimpleMap map;
			for (int i=0; i<=size; i++) {
				
				map = ObjectBindUtil.getData(request, "_"+i);
				if (map.keySet().size() > 0) {
					int Sequence = this.getSequence("GA_EXPRESS_APPLY_SEQ");
					parameterObject.set("applyno", Sequence);
					map.setString("SENDPERSON1", SENDPERSON1);
					map.setString("mobileS", mobileS);
					map.setString("postCodeS", postCodeS);
					map.setString("citySentS", citySentS);
					map.setString("citySent", citySent);
					map.setString("citySentQ", citySentQ);
					map.setString("citySentD", citySentD);
					map.setString("applerId", admin.getAdminID());
					map.setString("deptId", admin.getDeptID());
					map.setInt("applyno", Sequence);
					map.setString("adminId", admin.getAdminID());
					map.setString("cpnyId", admin.getCpnyId());
					map.setString("isOver_kg", isOver_kg);
					detailList.add(map);
				}
			}
			
			// apply present
			parameterObject.set("affirm", affirorList);
			parameterObject.set("detail", detailList);
			
			//temp = eaaServices.addExpressApply(parameterObject, affirorList);
			
			temp = eaaServices.addExpressApplyMany(parameterObject, affirorList);

			if (temp) {
				request.setAttribute("errorMsg", "已申请成功！");
				parameterObject.set("applerId", request
						.getParameter("firstaffrim"));

//				GaAffirmList gaAffirmList = new GaAffirmList();
//				gaAffirmList = (GaAffirmList)affirorList.get(0);
//				String affirorID = gaAffirmList.getAffirmorId();
				
				String affirorID = affirmorId[0];
				parameterObject.set("applerId", affirorID);
				String toEmail = eaaServices.getapplyemail(parameterObject);
				
//				if (!toEmail.equals("") && toEmail != null
//						&& essSysparam.isGaSendMail()) {
//					
//					sendApplyPlanMail("快件申请 请您确认", affirorID, admin
//							.getChineseName(), toEmail, request
//							.getParameter("cityarrive"), request
//							.getParameter("postAddress"), request
//							.getParameter("postDescription"), admin.getCpnyId());
//				}
				
				SimpleMap dateMap = (SimpleMap) parameterObject;
				if (!toEmail.equals("") && toEmail != null
						&& essSysparam.isGaSendMail()) {
					
					for (int j = 0; j < detailList.size() ; j++){
						SimpleMap s = (SimpleMap)detailList.get(j);
						
						sendApplyPlanMail("快件申请 请您确认", affirorID, admin
								.getChineseName(), toEmail, s.getString("cityarrive"), 
								 s.getString("postDescription"), 
								admin.getCpnyId());

					}	
					
				}


			} else {
				request.setAttribute("errorMsg", "添加快件申请失败！");
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addExpressApply happens Exception. ",
					e);
		}

		return "/ga_express_apply.jsp";

	}
	
	/* 增加快件申请信息,同时添加决裁信息 */
	public String addExpressApply(HttpServletRequest request, AdminBean admin) {
		GaAffirm gaAffirm = new GaAffirm();
		List affirorList = gaAffirm.getAffirmor(admin.getAdminID(),
				"ExpressApply",essSysparam.isContainsOwner());
		int Sequence = this.getSequence("GA_EXPRESS_APPLY_SEQ");
		boolean temp = false;
		SimpleMap parameterObject = null;
		try {

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("applyno", Sequence);
			temp = eaaServices.addExpressApply(parameterObject, affirorList);

			if (temp) {
				request.setAttribute("errorMsg", "已申请成功！");
				parameterObject.set("applerId", request
						.getParameter("firstaffrim"));

				GaAffirmList gaAffirmList = new GaAffirmList();
				gaAffirmList = (GaAffirmList)affirorList.get(0);
				String affirorID = gaAffirmList.getAffirmorId();
				parameterObject.set("affirorID", affirorID);
				String toEmail = eaaServices.getapplyemail(parameterObject);
				
				if (!toEmail.equals("") && toEmail != null
						&& essSysparam.isGaSendMail()) {
					
					sendApplyPlanMail("快件申请 请您确认", affirorID, admin
							.getChineseName(), toEmail, request
							.getParameter("cityarrive"), request
							.getParameter("postDescription"), admin.getCpnyId());
				}

			} else {
				request.setAttribute("errorMsg", "添加快件申请失败！");
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addExpressApply happens Exception. ",
					e);
		}

		return "/ga_express_apply.jsp";

	}

	private void sendApplyPlanMail(String title, String adminid,
			String applyer, String toEmail, String citySent,
			 String postDescription, String cpny_id)
			throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人：", applyer);
		inputData.set("寄送城市：", citySent);
		//inputData.set("收件单位：", postAddress);
		inputData.set("邮件内容：", postDescription);
		
		mail.gaSendMail(inputData, cpny_id, toEmail, "快件申请,等待决裁");
		// }
		// essServices.insertOtAffirmMail(inputData);
	}

	/* 根据登陆者得到决裁的信息 */
	public String getExpressAffirm(HttpServletRequest request, AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
					
			String companyId = request.getParameter("companyId") != null ? request
							.getParameter("companyId") : admin.getCpnyId();
			String cpnyId = request.getParameter("company") != null ? request.getParameter("company") : admin.getCpnyId();

			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);
			List companyList = eaaServices.getCompany(parameterObject);

			// 取得数据行数
			int resultCount = eaaServices
					.getExpressAffirmListNumber(parameterObject);
			List ExpressAffirmList = eaaServices.getExpressAffirmList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < ExpressAffirmList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) ExpressAffirmList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLY_NO"));
				List affirmorList = eaaServices
						.getExpressAffirmorList(parameterObject1);
				dataMap.set("affirmorList", affirmorList);
			}
			request.setAttribute("companyList", companyList);
			request.setAttribute("companyId", companyId);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("cpnyId", cpnyId);
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("expressAffirmList", ExpressAffirmList);

			request.setAttribute("adminId", admin.getAdminID());
			if (ExpressAffirmList == null || ExpressAffirmList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirm happens Exception. ", e);
		}
		return "/ga_express_affirm.jsp";

	}

	/* 执行决裁 */
	public String confirmExpressAffirm(HttpServletRequest request,
			AdminBean admin) {

		try {
			String affirmno[] = request.getParameterValues("affirmno");

			SimpleMap parameterObject = null;
			String date1 = "";
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("affirmorid", admin.getAdminID());
			for (int i = 0; i < affirmno.length; ++i) {
				parameterObject.setString("affirmno", affirmno[i]);
				parameterObject.set("affirmorIdea", request
						.getParameter("affirmorIdea_" + affirmno[i]));
				eaaServices.confirmExpressAffirm(parameterObject);
				parameterObject.set("applerId", request
						.getParameter("applyorid_" + affirmno[i]));

				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				date1 = format1.format(Calendar.getInstance().getTime());

				if (request.getParameter("flag").equals("2")) {
					eaaServices.confirmExpressApply(parameterObject);
					String toEmail = eaaServices.getapplyemail(parameterObject);
					if (!toEmail.equals("") && toEmail != null
							&& essSysparam.isGaSendMail()) {
						sendExpressMail(
								"快件申请 已被否决",
								request
										.getParameter("applyorid_"
												+ affirmno[i]),
								request.getParameter("applyorname_"
										+ affirmno[i]),
								toEmail,
								request.getParameter("citySent_" + affirmno[i]),
								request
										.getParameter("postDescription_"
												+ affirmno[i]), admin
										.getCpnyId());
					}
				} else if (request.getParameter("flag").equals("1")) {
					String MAX_AFFIRM_FLAG = request
							.getParameter("MAX_AFFIRM_FLAG_" + affirmno[i]);
					parameterObject.set("affrimlevel", request
							.getParameter("affirmlevel_" + affirmno[i]));
					if (MAX_AFFIRM_FLAG.equals("0")) {
						String toEmail = eaaServices
								.getupaffrimemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendExpressMail("快件申请 请您确认", request
									.getParameter("applyorid_" + affirmno[i]),
									request.getParameter("applyorname_"
											+ affirmno[i]), toEmail, request
											.getParameter("citySent_"
													+ affirmno[i]), request
											.getParameter("postDescription_"
													+ affirmno[i]), admin
											.getCpnyId());
						}
						//推送
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_EXPRESS,affirmno[i],Integer.parseInt(StringUtil.checkNull(parameterObject.get("affrimlevel"))) + 1);
					} else {
						eaaServices.confirmExpressApply(parameterObject);

						//审批完成，获取快递单
						KdGoldAPI.orderOnlineByJson(StringUtil.checkNull(affirmno[i]));
						//获取快递单结束

						String toEmail = eaaServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& essSysparam.isGaSendMail()) {
							sendExpressMail("快件申请 已通过确认", request
									.getParameter("applyorid_" + affirmno[i]),
									request.getParameter("applyorname_"
											+ affirmno[i]), toEmail, request
											.getParameter("citySent_"
													+ affirmno[i]), request
											.getParameter("postDescription_"
													+ affirmno[i]), admin
											.getCpnyId());
						}
					}
				}
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addExpressApply happens Exception. ",
					e);
		}
		this.getExpressAffirm(request, admin);
		return "/ga_express_affirm.jsp";

	}

	private void sendExpressMail(String title, String adminid, String applyer,
			String toEmail, String citySent,
			String postDescription, String cpny_id) throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人：", applyer);
		inputData.set("寄送城市：", citySent);
		//inputData.set("收件单位：", postAddress);
		inputData.set("邮件内容：", postDescription);
		
		mail.gaSendMail(inputData, cpny_id, toEmail, title);
		// mail.sendMail(inputData) ;
		// }
		// essServices.insertOtAffirmMail(inputData);
	}

	/* 根据登陆者得到决裁情况的信息 */
	public String getExpressAffirmInfo(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId") != null ? request
							.getParameter("cpnyId") : admin.getCpnyId();	
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);

			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("applerId", "");
			parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}
			
			
			// 取得数据行数
 			int resultCount = eaaServices
					.getExpressAffirmInfoListNumber(parameterObject);
			List ExpressAffirmList = eaaServices.getExpressAffirmInfoList(
					parameterObject, currentPage, pageSize);// 得到决裁情况信息
			for (int i = 0; i < ExpressAffirmList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) ExpressAffirmList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLY_NO"));
				List affirmorList = eaaServices
						.getExpressAffirmorList(parameterObject1);
				boolean isfalg = true;
				for (int z = 0; z < affirmorList.size(); z++) {
					SimpleMap map1 = null;
					map1 = (SimpleMap) affirmorList.get(z);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					if (isfalg && affirmFlag.equals("0")) {
						dataMap.set("isfalg", "0");
					}
					isfalg = false;
				}
				dataMap.set("affirmorList", affirmorList);
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
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("expressAffirmList", ExpressAffirmList);
			request.setAttribute("adminID", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			if (ExpressAffirmList == null || ExpressAffirmList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirm happens Exception. ", e);
		}
		return "/ga_express_affirmInfo.jsp";

	}
	/* 导出快件申请报表 */
	public String getExcelExpressAffirmInfo(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = new SimpleMap();
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId") != null ? request
							.getParameter("cpnyId") : admin.getCpnyId();	
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);

			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("applerId", "");
			parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}
			
			
			// 取得数据行数
 			int resultCount = eaaServices
					.getExpressAffirmInfoListNumber(parameterObject);
			List ExpressAffirmList = eaaServices.getExcelExpressAffirmInfoList(parameterObject);// 得到决裁情况信息
			for (int i = 0; i < ExpressAffirmList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) ExpressAffirmList.get(i);
				parameterObject1.set("applyno", dataMap.get("APPLY_NO"));
				List affirmorList = eaaServices
						.getExpressAffirmorList(parameterObject1);
				boolean isfalg = true;
				for (int z = 0; z < affirmorList.size(); z++) {
					SimpleMap map1 = null;
					map1 = (SimpleMap) affirmorList.get(z);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					if (isfalg && affirmFlag.equals("0")) {
						dataMap.set("isfalg", "0");
					}
					isfalg = false;
				}
				dataMap.set("affirmorList", affirmorList);
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
			request.setAttribute("loginAdmin", admin.getAdminID());
			request.setAttribute("expressAffirmList", ExpressAffirmList);
			request.setAttribute("adminID", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			if (ExpressAffirmList == null || ExpressAffirmList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirm happens Exception. ", e);
		}
		return "/ga_express_affirmInfo_Excel.jsp";

	}

	/* 执行删除 */
	public String deleteApply(HttpServletRequest request, AdminBean admin) {

		try {

			SimpleMap parameterObject = null;
			parameterObject = ObjectBindUtil.getData(request);
			eaaServices.deleteApply(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteApply happens Exception. ", e);
		}
		this.getExpressAffirm(request, admin);
		return "/ga_express_affirmInfo.jsp";

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

	public void sendToExpressApplyPage(HttpServletRequest request,
			AdminBean admin) {
		
		
		
		try {
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("cpny_id", admin.getCpnyId());
			List list1 = eaaServices.getsendToExpressApplyPage(parameterObject);
			List list2 = eaaServices.getsendToExpressApplyPage1(parameterObject);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			String declaration = "";
			if("63000000".equals(admin.getCpnyId())){
				declaration="(申请后请促进领导决裁；有物品寄送(文件发票等纸质材料除外)时请开搬出证并到警卫室进行封装)";
			}
			request.setAttribute("declaration", declaration);
			
///			int Sequence = this.getSequence("GA_EXPRESS_APPLY_SEQ");
			
			
//			
//			SimpleMap parameterObject = null;
//
//			parameterObject = ObjectBindUtil.getData(request);
//			parameterObject.set("applerId", admin.getAdminID());
//			parameterObject.set("deptId", admin.getDeptID());
			
			// 添加 開始
			Connection conn = ConnBean.getConn();
			int Sequence = this.getSequence("GA_EXPRESS_APPLY_SEQ");
			int result = 0;
			PreparedStatement ps = null;
			GaAffirmList gaAffirmList = null;
			GaAffirm gaAffirm = new GaAffirm();
			List affirorList = gaAffirm.getAffirmor(admin.getAdminID(),
					"ExpressApply", essSysparam.isContainsOwner());

			for (int i = 0; i < affirorList.size(); i++) {
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				String sql1 = "     insert into GA_EXPRESS_AFFIRM(GA_AFFIRM_NO, APPLY_NO,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATED_BY,ACTIVITY,APPLY_TYPE ) values ( "
						+ "  GA_EXPRESS_AFFIRM_SEQ.NEXTVAL, ?,?,?,SYSDATE,?,'1', 'ExpressApply' )  ";
				Logger.getLogger(getClass()).debug(sql1);
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, Sequence);
				ps.setInt(2, gaAffirmList.getAffirmLevel());
				ps.setString(3, gaAffirmList.getAffirmorId());
				ps.setString(4, admin.getAdminID());
				result += ps.executeUpdate();
				ps.close();
			}
			request.setAttribute("applyNo", Sequence);
			// 添加 結束
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getIsNotExitsSecurityEnvironment happens Exception. ", e);
		}
		
	}
	
	/*
	 * 删除决裁者信息
	 */
	public String deleteExpressAffrimInfo(HttpServletRequest request,
			AdminBean admin) {
		PreparedStatement ps = null;
		Connection conn = ConnBean.getConn();
		GaAffirmList vb = new GaAffirmList();
		GaAffirm gaAffirm = new GaAffirm();
		try {

			String sql2 = " update GA_EXPRESS_AFFIRM f set f.affirm_level = ? where f.apply_no = ? and f.affirm_level = ?";
			String sql1 = " delete from GA_EXPRESS_AFFIRM f where f.apply_no = ? and f.affirm_level = ? and f.affirmor_id = ?";
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
			
			
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.set("cpny_id", admin.getCpnyId());
			List list1 = eaaServices.getsendToExpressApplyPage(parameterObject);
			List list2 = eaaServices.getsendToExpressApplyPage1(parameterObject);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			String declaration = "";
			if("63000000".equals(admin.getCpnyId())){
				declaration="(申请后请促进领导决裁；有物品寄送(文件发票等纸质材料除外)时请开搬出证并到警卫室进行封装)";
			}
			request.setAttribute("declaration", declaration);
			
			request.setAttribute("documentnoBian", request
					.getParameter("document_number"));
			request.setAttribute("applyNoFaht", request
					.getParameter("ApplyNoFa"));
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"deleteExpressAffrim happens Exception. ", e);
		} finally {
			SqlUtil.close(ps, conn);
		}

		return "/ga_express_apply.jsp";

	}

}
