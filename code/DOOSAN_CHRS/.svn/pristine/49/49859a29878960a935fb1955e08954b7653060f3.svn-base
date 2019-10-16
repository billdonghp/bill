package com.ait.ev.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.PresentBean;
import com.ait.hrm.bean.Department;
import com.ait.hrm.business.HrmServices;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.ess.base.ErrorMessage;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.business.EssServices;
import com.ait.ev.bean.EvaluateAffirm;
import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.bean.EvaluateAffirmor;
import com.ait.ev.bean.EvaluateInfo;
import com.ait.ev.bean.EvaluateSetItem;
import com.ait.ev.business.EvaluateApplyServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2011-9-19
 * 
 */
public class EvaluateSetCommand extends ArAbstractCommand {

	private EvaluateApplyServices evServices = null;

	private EssSysparam essSysparam = null;

	private Mail mail;

	private String cpnyId;

	private String url = "http://doopis.corp.doosan.com/dic_login.jsp";
	// private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	private HrmServices services = HrmServices.getInstance();

	public EvaluateSetCommand() {
		evServices = new EvaluateApplyServices();

		mail = new Mail();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("") && content.equals("evaluateAffirmSetView")) {
			this.evaluateAffirmSetView(request, admin);
			returnPage = "/ev_affirm_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateAffirmSetAddView")) {
			this.evaluateAffirmSetAddView(request, admin);
			returnPage = "/ev_affirm_set_add.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateAffirmSetAdd")) {
			this.evaluateAffirmSetAdd(request, admin);
			this.evaluateAffirmSetView(request, admin);
			returnPage = "/ev_affirm_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("AUTOevaluateAffirmSetAdd")) {// 自动生成决裁者
			this.autoEvaluateAffirmSetAdd(request, admin);
			this.evaluateAffirmSetView(request, admin);
			returnPage = "/ev_affirm_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateAffirmSetModifyView")) {
			this.evaluateAffirmSetModifyView(request, admin);
			returnPage = "/ev_affirm_set_modify.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateAffirmSetModify")) {
			this.evaluateAffirmSetModify(request, admin);
			this.evaluateAffirmSetView(request, admin);
			returnPage = "/ev_affirm_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateAffirmSetDelete")) {
			this.evaluateAffirmSetDelete(request, admin);
			this.evaluateAffirmSetView(request, admin);
			returnPage = "/ev_affirm_set_view.jsp";

		} else if (!content.equals("") && content.equals("evaluateItemSetView")) {
			this.evaluateItemSetView(request, admin);
			returnPage = "/ev_item_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateItemSetAddView")) {
			this.evaluateItemSetAddView(request, admin);
			returnPage = "/ev_item_set_add.jsp";
		} else if (!content.equals("") && content.equals("evaluateItemSetAdd")) {
			this.evaluateItemSetAdd(request, admin);
			this.evaluateItemSetView(request, admin);
			returnPage = "/ev_item_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateItemSetModifyView")) {
			this.evaluateItemSetModifyView(request, admin);
			returnPage = "/ev_item_set_modify.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateItemSetModify")) {
			this.evaluateItemSetModify(request, admin);
			this.evaluateItemSetView(request, admin);
			returnPage = "/ev_item_set_view.jsp";
		} else if (!content.equals("")
				&& content.equals("evaluateItemSetDelete")) {
			this.evaluateItemSetDelete(request, admin);
			this.evaluateItemSetView(request, admin);
			returnPage = "/ev_item_set_view.jsp";
		} else if (!content.equals("") && content.equals("evaluateItemSetInit")) {
			this.evaluateItemSetInit(request, admin);
			this.evaluateItemSetView(request, admin);
			returnPage = "/ev_item_set_view.jsp";
		} else {
			return "error.jsp";
		}
		//		
		return returnPage;
	}

	/* 得到决裁者设置信息 */
	public String evaluateAffirmSetView(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			cpnyId = ((AdminBean) request.getSession(false).getAttribute(
					"admin")).getCpnyId();

			List deptList = new ArrayList();

			parameterObject = ObjectBindUtil.getData(request);

			parameterObject.set("dept", request.getParameter("DeptId"));
			parameterObject.set("position", request
					.getParameter("selectPosition"));
			parameterObject.set("cpnyId", cpnyId);

			int pageSize = 20;
			int pageGroupSize = 10;
			int currentPage = 0;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			int resultCount = evServices
					.getEvaluateAffirmSetListCnt(parameterObject);

			List affirmSetList = evServices.getEvaluateAffirmSetList(
					parameterObject, currentPage, pageSize);

			// /EvaluateInfo evaluateInfo = new EvaluateInfo();
			for (int i = 0; i < affirmSetList.size(); i++) {

				EvaluateAffirm ea = (EvaluateAffirm) affirmSetList.get(i);

				ea.setAffirmorList((ArrayList) evServices
						.getAffirmSetAffirmorList(ea.getDeptId(), ea
								.getPosition()));

			}

			// parameterObject.set("cpnyId",admin.getCpnyId());
			// deptList=HrmServices.getInstance().retrieveDeptTree(parameterObject);

			List positionList = evServices
					.getEvaluatePositionSetList(parameterObject);

			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

			request.setAttribute("affirmSetList", affirmSetList);

			request.setAttribute("positionList", positionList);
			// request.setAttribute("deptList", deptList);
			request.setAttribute("DeptId", request.getParameter("DeptId"));
			request.setAttribute("selectPosition", request
					.getParameter("selectPosition"));

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get affirmSetList Exception. ", e);
		}
		return "/ev_affirm_set_view.jsp";

	}

	/* 添加页面 */
	public String evaluateAffirmSetAddView(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);

			List positionList = evServices
					.getEvaluatePositionSetList(parameterObject);

			request.setAttribute("positionList", positionList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"get evaluatePositionSetList Exception. ", e);
		}
		return "/ev_affirm_set_add.jsp";

	}

	/* 添加决裁者设置信息 */
	public String evaluateAffirmSetAdd(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId", admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			String deptid = StringUtil
					.checkNull(request.getParameter("DeptId"));
			String position = StringUtil.checkNull(request
					.getParameter("selectPosition"));

			SimpleMap parameterObject1 = new SimpleMap();
			parameterObject1.setString("dept", deptid);
			parameterObject1.setString("position", position);
			int resultCount = evServices
					.getEvaluateAffirmSetListCnt(parameterObject1);
			if (resultCount == 0) {

				List<EvaluateAffirm> eaList = new ArrayList<EvaluateAffirm>();

				int flag = NumberUtil.parseInt(request.getParameter("flag"));
				if (flag >= 0) {
					String empid = "";
					int level;
					for (int i = 0; i < (flag + 1); i++) {
						EvaluateAffirm ea = new EvaluateAffirm();
						empid = StringUtil.checkNull(request
								.getParameter("empID" + i));// 决裁人工号
						level = NumberUtil.parseInt(request
								.getParameter("level" + i));// 决裁等级

						if (empid.equals("") || empid == null) {

							continue;
						}
						ea.setDeptId(deptid);
						ea.setPosition(position);
						ea.setAffirmorId(empid);
						ea.setLevel(level);

						eaList.add(ea);
					}

				}

				// SimpleMap parameterObject2 = null;
				// parameterObject2.setString("deptid",deptidString);
				// parameterObject2.setString("position",parameterObject.getString("selectPosition"));

				evServices.insertAffirmSetInfo(parameterObject, eaList);
				// List
				// positionList=evServices.getEvaluatePositionSetList(parameterObject);

				// request.setAttribute("positionList", positionList);

				request.setAttribute("errorMsg", "添加成功 !");

			} else {
				request.setAttribute("errorMsg", "添加失败,此信息已存在 !");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", "添加失败,请检查该部门该职位信息是否存在");
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insertAffirmSetInfo Exception. ", e);
		}
		return "/ev_affirm_set_add.jsp";

	}

	/* 自动生成决裁者设置信息 */
	public void autoEvaluateAffirmSetAdd(HttpServletRequest request,
			AdminBean admin) {
		try {
			SimpleMap parameterObject = null;
			Connection conn = ConnBean.getConn();
			// 查询人员
			PreparedStatement ps = null;
			ResultSet rs = null;
			// 判断部门有无员工
			PreparedStatement psdg = null;
			ResultSet rsdg = null;
			//查询上级部门有无人员
			PreparedStatement psdgs = null;
			ResultSet rsdgs = null;
			// 查询裁决人员
			PreparedStatement psAf = null;
			// 查询人员（工人）
			PreparedStatement psg = null;
			ResultSet rsg = null;
			// 添加工人裁决人员（工人）
			PreparedStatement psEvaluation = null;
			// 删除工人裁决人员（工人）
			PreparedStatement deEvaluation = null;
			parameterObject = ObjectBindUtil.getData(request);
			int level = 1;
			String empSql = " select h.empid,h.chinesename,h.person_id,h.deptid,h.duty_code,h.post_code, "
					+ " h.cpny_id,d.parent_dept_id parentDeptID from hr_employee h,hr_department d "
					+ "where h.deptid=d.deptid and  h.deptid = ? and h.post_code is not null and h.status_code not in 'EmpStatus3' and h.activity='1' ";

			String empSqlAff = " select h.empid,h.chinesename,h.person_id,h.deptid,h.duty_code,h.post_code, "
					+ " h.cpny_id,d.parent_dept_id parentDeptID from hr_employee h,hr_department d "
					+ "where h.deptid=d.deptid and  h.deptid = ? and h.duty_code is not null and h.status_code not in 'EmpStatus3' and h.activity='1' ";

			String empSqlAffDept = "select d.deptid,d.parent_dept_id parentDeptID from hr_department d where d.deptid = ? ";
			
			String empSqlNo = " select count(h.person_id) noo from hr_employee h,hr_department d "
					+ "where h.deptid=d.deptid and  h.deptid = ? and h.duty_code is not null and h.status_code not in 'EmpStatus3' and h.activity='1' ";

			String keys = admin.getCpnyId().substring(0, 2);
			String deleteRelation = "delete from EV_AFFIRM_RELATION f where f.affirm_deptid like '"
					+ keys + "%'";

			String affirmRelation = " INSERT INTO EV_AFFIRM_RELATION A (A.AFFIRM_RELATION_NO, "
					+ " A.AFFIRM_DEPTID,A.AFFIRM_POSITION,A.AFFIRMOR_ID,A.AFFIRM_LEVEL, "
					+ " A.UPDATE_DATE, A.UPDATE_BY) VALUES (EV_AFFIRM_RELATION_SEQ.NEXTVAL, "
					+ " ?,?,get_personid(?, ?),?,SYSDATE,?) ";

			// 查询人员
			ps = conn.prepareStatement(empSql);
			psdg = conn.prepareStatement(empSqlNo);
			psdgs = conn.prepareStatement(empSqlAffDept);
			// 裁决者
			psAf = conn.prepareStatement(empSqlAff);
			// 添加工人评价决裁者
			psEvaluation = conn.prepareStatement(affirmRelation);
			// 删除裁决者
			deEvaluation = conn.prepareStatement(deleteRelation);
			deEvaluation.executeQuery();
			conn.commit();
			parameterObject.setString("cpnyId", admin.getCpnyId());
			List deptTreeList = services.retrieveDeptTreeNo(parameterObject);
			Iterator it = deptTreeList.iterator();
			while (it.hasNext()) {
				Department dept = (Department) it.next();
				Logger.getLogger(getClass()).debug(empSql);
				ps.setString(1, dept.getDeptID());
				System.out.println("部门ID: " + dept.getDeptID());
				rs = ps.executeQuery();
				// 重复添加验证
				int grOk = 1;
				int zzOk = 1;
				int ForemanOk = 1;
				boolean mod = true;
				while (rs.next()) {
					if (!(rs.getString("post_code") == null)
							&& rs.getString("post_code").equals(
									"C_12004_1355928")) {// 工人
						level = 1;
						mod = true;
						if (grOk == 1) {
							String deptIdParent = "";
							while (mod) {
								Logger.getLogger(getClass()).debug(empSqlAff);
								psg = psAf;
								if (grOk == 1) {
									psg.setString(1, dept.getDeptID());
									psdg.setString(1, dept.getDeptID());
									psdgs.setString(1, dept.getDeptID());
								} else {
									psg.setString(1, deptIdParent);
									psdg.setString(1, deptIdParent);
									psdgs.setString(1, deptIdParent);
								}
								rsg = psg.executeQuery();
								rsdg = psdg.executeQuery();
								rsdgs = psdgs.executeQuery();
								while (rsdg.next()) {
									if (rsdg.getString("noo").equals("0")) {
										while (rsdgs.next()) {
											deptIdParent = rsdgs
													.getString("parentDeptID");
											grOk++;
											break;
										}
										break;
									}
								}
									while (rsg.next()) {// 添加决裁者（组长）
										Logger.getLogger(getClass()).debug(
												affirmRelation);
										psEvaluation.setString(1, dept
												.getDeptID());
										psEvaluation.setString(2,
												"C_12004_1355928");// 页面裁定
										psEvaluation.setString(3, rsg
												.getString("empid"));
										psEvaluation.setString(4, rsg
												.getString("cpny_id"));
										psEvaluation.setInt(5, level);
										psEvaluation.setString(6, rsg
												.getString("person_id"));
										psEvaluation.executeQuery();
										conn.commit();
										grOk++;
										level++;
										if (rsg.getString("duty_code").equals(
												"C_12005_93775")) {
											mod = false;
										}
										if (rsg.getString("deptid").equals(
												"00000000")
												|| rsg.getString("deptid")
														.equals(admin.getCpnyId())) {
											mod = false;
										}
										deptIdParent = rsg.getString("parentDeptID");
									}
							}
						}
					}else if (!(rs.getString("post_code") == null)
							&& rs.getString("post_code").equals(
							"C_12004_1331763")){                //组长
						level = 1;
						mod = true;
						if (zzOk == 1) {
							String deptIdParentz = "";
							while (mod) {
								Logger.getLogger(getClass()).debug(empSqlAff);
								psg = psAf;
								if (zzOk == 1) {
									psg.setString(1, dept.getParentDeptID());
									psdg.setString(1, dept.getParentDeptID());
									psdgs.setString(1, dept.getParentDeptID());
								} else {
									psg.setString(1, deptIdParentz);
									psdg.setString(1, deptIdParentz);
									psdgs.setString(1, deptIdParentz);
								}
								rsg = psg.executeQuery();
								rsdg = psdg.executeQuery();
								rsdgs = psdgs.executeQuery();
								while (rsdg.next()) {
									if (rsdg.getString("noo").equals("0")) {
										while (rsdgs.next()) {
											deptIdParentz = rsdgs
													.getString("parentDeptID");
											zzOk++;
											break;
										}
										break;
									}
								}
									while (rsg.next()) {// 添加决裁者（组长）
										Logger.getLogger(getClass()).debug(
												affirmRelation);
										psEvaluation.setString(1, dept
												.getDeptID());
										psEvaluation.setString(2,
												"C_12004_1331763");// 页面裁定
										psEvaluation.setString(3, rsg
												.getString("empid"));
										psEvaluation.setString(4, rsg
												.getString("cpny_id"));
										psEvaluation.setInt(5, level);
										psEvaluation.setString(6, rsg
												.getString("person_id"));
										psEvaluation.executeQuery();
										conn.commit();
										zzOk++;
										level++;
										if (rsg.getString("duty_code").equals(
												"C_12005_93775")) {
											mod = false;
										}
										if (rsg.getString("deptid").equals(
												"00000000")
												|| rsg.getString("deptid")
														.equals(admin.getCpnyId())) {
											mod = false;
										}
										deptIdParentz = rsg.getString("parentDeptID");
									}
							}
						}
					
						
					}else if (!(rs.getString("post_code") == null)
							&& rs.getString("post_code").equals(
							"C_12004_1331764")){   //职长
		                //组长
						level = 1;
						mod = true;
						if (ForemanOk == 1) {
							String deptIdParentz = "";
							while (mod) {
								Logger.getLogger(getClass()).debug(empSqlAff);
								psg = psAf;
								if (ForemanOk == 1) {
									psg.setString(1, dept.getParentDeptID());
									psdg.setString(1, dept.getParentDeptID());
									psdgs.setString(1, dept.getParentDeptID());
								} else {
									psg.setString(1, deptIdParentz);
									psdg.setString(1, deptIdParentz);
									psdgs.setString(1, deptIdParentz);
								}
								rsg = psg.executeQuery();
								rsdg = psdg.executeQuery();
								rsdgs = psdgs.executeQuery();
								while (rsdg.next()) {
									if (rsdg.getString("noo").equals("0")) {
										while (rsdgs.next()) {
											deptIdParentz = rsdgs
													.getString("parentDeptID");
											ForemanOk++;
											
											if (deptIdParentz.equals(
											"00000000")
											|| deptIdParentz.equals(admin.getCpnyId())) {
										mod = false;
									}
											
											break;
										}
										break;
									}
								}
									while (rsg.next()) {// 添加决裁者（组长）
										Logger.getLogger(getClass()).debug(
												affirmRelation);
										psEvaluation.setString(1, dept
												.getDeptID());
										psEvaluation.setString(2,
												"C_12004_1331764");// 页面裁定
										psEvaluation.setString(3, rsg
												.getString("empid"));
										psEvaluation.setString(4, rsg
												.getString("cpny_id"));
										psEvaluation.setInt(5, level);
										psEvaluation.setString(6, rsg
												.getString("person_id"));
										psEvaluation.executeQuery();
										conn.commit();
										ForemanOk++;
										level++;
										if (rsg.getString("duty_code").equals(
												"C_12005_93775")) {
											mod = false;
										}
										if (rsg.getString("deptid").equals(
												"00000000")
												|| rsg.getString("deptid")
														.equals(admin.getCpnyId())) {
											mod = false;
										}
										deptIdParentz = rsg.getString("parentDeptID");
									}
							}
						}
					
						
					
					}
				}
			}
			conn.close();
			ps.close();
			request.setAttribute("errorMsg", "自动生成裁决者成功 !");
		} catch (Exception e) {
			request.setAttribute("errorMsg", "生成失败,请检查该部门该职位信息是否存在");
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insertAffirmSetInfo Exception. ", e);
		}
	}

	// //添加工人评价裁决者
	// public void addAffirmSetInfo(String affirmRelation,
	// String DeptID,String empId,
	// String cpnyId,String leve,String personId){
	//		
	// Logger.getLogger(getClass()).debug(affirmRelation);
	// psEvaluation.setString(1, dept.getDeptID());
	// psEvaluation.setString(2, "C_12004_1355928");// 页面裁定
	// psEvaluation.setString(3, rsg.getString("empid"));
	// psEvaluation.setString(4, rsg.getString("cpny_id"));
	// psEvaluation.setInt(5, level);
	// psEvaluation.setString(6, rsg.getString("person_id"));
	// psEvaluation.executeQuery();
	// conn.commit();
	// }

	/* 修改页面 */
	public String evaluateAffirmSetModifyView(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			Object result = null;

			parameterObject = ObjectBindUtil.getData(request);

			// List
			// positionList=evServices.getEvaluatePositionSetList(parameterObject);
			// // retrieve Affirm Set by sequence No
			// List list=evServices.getEvaluateAffirmSetInfo(parameterObject);
			// if(list.size() > 0)
			// result = list.get(0);
			//				
			// request.setAttribute("result", result);
			// request.setAttribute("positionList", positionList);

			parameterObject.set("dept", request.getParameter("DEPTID_SEQ"));
			parameterObject.set("position", request
					.getParameter("POSITION_SEQ"));
			parameterObject.set("cpnyId", admin.getCpnyId());

			List affirmSetList = evServices.getEvaluateAffirmSetList(
					parameterObject, 0, 20);

			for (int i = 0; i < affirmSetList.size(); i++) {

				EvaluateAffirm ea = (EvaluateAffirm) affirmSetList.get(i);

				ea.setAffirmorList((ArrayList) evServices
						.getAffirmSetAffirmorList(ea.getDeptId(), ea
								.getPosition()));

			}

			request.setAttribute("affirmSetList", affirmSetList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"get evaluate affirm set info Exception. ", e);
		}
		return "/ev_affirm_set_modify.jsp";

	}

	/* 修改决裁者设置信息 */
	public String evaluateAffirmSetModify(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId", admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			// /evServices.updateAffirmSetInfo(parameterObject);

			evServices.deleteAffirmSetInfo(parameterObject);

			List<EvaluateAffirm> eaList = new ArrayList<EvaluateAffirm>();
			int flag = NumberUtil.parseInt(request.getParameter("temp1"));
			if (flag >= 0) {
				String empid = "";
				int level;
				String deptid = "";
				String position = "";
				for (int i = 0; i < (flag + 1); i++) {
					EvaluateAffirm ea = new EvaluateAffirm();
					empid = StringUtil.checkNull(request.getParameter("empID"
							+ i));// 决裁人工号
					level = NumberUtil.parseInt(request.getParameter("level"
							+ i));// 决裁等级
					deptid = StringUtil.checkNull(request
							.getParameter("DEPTID_SEQ"));
					position = StringUtil.checkNull(request
							.getParameter("POSITION_SEQ"));
					if (empid.equals("") || empid == null) {

						continue;
					}
					ea.setDeptId(deptid);
					ea.setPosition(position);
					ea.setAffirmorId(empid);
					ea.setLevel(level);

					eaList.add(ea);
				}

			}

			// SimpleMap parameterObject2 = null;
			// parameterObject2.setString("deptid",deptidString);
			// parameterObject2.setString("position",parameterObject.getString("selectPosition"));

			evServices.insertAffirmSetInfo(parameterObject, eaList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateAffirmSetInfo Exception. ", e);
		}
		return "/ev_affirm_set_view.jsp";

	}

	/* 删除决裁者设置信息 */
	public String evaluateAffirmSetDelete(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			// parameterObject.setString("personId",admin.getPersonId());
			// parameterObject.setString("cpnyId", admin.getCpnyId());
			evServices.deleteAffirmSetInfo(parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteAffirmSetInfo Exception. ", e);
		}
		return "/ev_affirm_set_view.jsp";

	}

	/* 得到评价项目设置信息 */
	public String evaluateItemSetView(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			cpnyId = ((AdminBean) request.getSession(false).getAttribute(
					"admin")).getCpnyId();
			// List deptList=new ArrayList();

			parameterObject = ObjectBindUtil.getData(request);
			if (request.getParameter("year") != null
					&& request.getParameter("month") != null) {
				String yearMonth = request.getParameter("year")
						+ request.getParameter("month");
				parameterObject.set("yearMonth", yearMonth);
			} else {

				// 取日期参数
				GregorianCalendar currentDay = new GregorianCalendar();
				int m = currentDay.get(Calendar.MONTH) + 1;
				int y = currentDay.get(Calendar.YEAR);

				String month = ("0" + String.valueOf(m)).substring(
						("0" + String.valueOf(m)).length() - 2, ("0" + String
								.valueOf(m)).length());
				String year = String.valueOf(y);

				parameterObject.set("yearMonth", year + month);

			}
			parameterObject.set("dept", request.getParameter("DeptId"));
			parameterObject.set("position", request
					.getParameter("positionCodeId"));
			parameterObject.set("cpnyId", cpnyId);

			int pageSize = 20;
			int pageGroupSize = 10;
			int currentPage = 0;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			int resultCount = evServices
					.getEvaluateItemSetListCnt(parameterObject);

			List itemSetList = evServices.getEvaluateItemSetList(
					parameterObject, currentPage, pageSize);

			// parameterObject.set("cpnyId",admin.getCpnyId());
			// deptList=HrmServices.getInstance().retrieveDeptTree(parameterObject);

			// /EvaluateInfo evaluateInfo = new EvaluateInfo();
			for (int i = 0; i < itemSetList.size(); i++) {

				EvaluateSetItem ea = (EvaluateSetItem) itemSetList.get(i);

				ea.setItemList((ArrayList) evServices.getItemSetItemList(ea
						.getMonth(), ea.getDeptId(), ea.getPosition()));

			}

			List positionList = evServices
					.getEvaluatePositionSetList(parameterObject);

			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

			request.setAttribute("itemSetList", itemSetList);

			request.setAttribute("positionList", positionList);
			// request.setAttribute("deptList", deptList);
			request.setAttribute("DeptId", request.getParameter("DeptId"));
			request.setAttribute("positionCodeId", request
					.getParameter("positionCodeId"));

			request.setAttribute("year", request.getParameter("year"));
			request.setAttribute("month", request.getParameter("month"));

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get itemSetList Exception. ", e);
		}
		return "/ev_item_set_view.jsp";

	}

	/* 添加页面 */
	public String evaluateItemSetAddView(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);

			List positionList = evServices
					.getEvaluatePositionSetList(parameterObject);

			List itemList = evServices.getEvaluateItemList(parameterObject);

			request.setAttribute("positionList", positionList);
			request.setAttribute("itemList", itemList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"get evaluatePositionSetList Exception. ", e);
		}
		return "/ev_item_set_add.jsp";

	}

	/* 添加项目设置信息 */
	public String evaluateItemSetAdd(HttpServletRequest request, AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId", admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());

			String yearMonth = request.getParameter("year")
					+ request.getParameter("month");
			String deptid = StringUtil
					.checkNull(request.getParameter("DeptId"));
			String position = StringUtil.checkNull(request
					.getParameter("selectPosition"));

			SimpleMap parameterObject1 = new SimpleMap();
			parameterObject1.setString("yearMonth", yearMonth);
			parameterObject1.setString("dept", deptid);
			parameterObject1.setString("position", position);
			parameterObject1.setString("cpnyId", admin.getCpnyId());
			int resultCount = evServices
					.getEvaluateItemSetListCnt(parameterObject1);
			if (resultCount == 0) {

				List<EvaluateSetItem> esList = new ArrayList<EvaluateSetItem>();
				int flag = NumberUtil.parseInt(request.getParameter("flag"));
				if (flag >= 0) {
					String item = "";
					String proportion = "";

					for (int i = 0; i < (flag + 1); i++) {
						EvaluateSetItem es = new EvaluateSetItem();
						item = StringUtil.checkNull(request
								.getParameter("selectItem" + i));
						proportion = StringUtil.checkNull(request
								.getParameter("proportion" + i));

						if (item.equals("") || item == null) {

							continue;
						}
						es.setDeptId(deptid);
						es.setPosition(position);
						es.setMonth(yearMonth);
						es.setItemId(item);
						es.setProportion(proportion);
						esList.add(es);
					}

				}

				evServices.insertItemSetInfo(parameterObject, esList);

				request.setAttribute("errorMsg", "添加成功 !");

			} else {

				request.setAttribute("errorMsg", "添加失败,此信息已存在 !");
			}

			// List
			// positionList=evServices.getEvaluatePositionSetList(parameterObject);

			// request.setAttribute("positionList", positionList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insertAffirmSetInfo Exception. ", e);
		}
		return "/ev_item_set_view.jsp";

	}

	/* 修改页面 */
	public String evaluateItemSetModifyView(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;
			Object result = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("cpnyId", admin.getCpnyId());
			parameterObject.set("dept", request.getParameter("DEPTID_SEQ"));
			parameterObject.set("position", request
					.getParameter("POSITION_SEQ"));
			parameterObject.set("yearMonth", request.getParameter("MONTH_SEQ"));

			List itemSetList = evServices.getEvaluateItemSetList(
					parameterObject, 0, 20);

			for (int i = 0; i < itemSetList.size(); i++) {

				EvaluateSetItem es = (EvaluateSetItem) itemSetList.get(i);

				es.setItemList((ArrayList) evServices.getItemSetItemList(es
						.getMonth(), es.getDeptId(), es.getPosition()));
			}

			request.setAttribute("itemSetList", itemSetList);

			List itemList = evServices.getEvaluateItemList(parameterObject);
			// List
			// positionList=evServices.getEvaluatePositionSetList(parameterObject);
			// // retrieve Item Set by sequence No
			// List list=evServices.getEvaluateItemSetInfo(parameterObject);
			// if(list.size() > 0)
			// result = list.get(0);
			//				
			// request.setAttribute("result", result);
			// request.setAttribute("positionList", positionList);
			request.setAttribute("itemList1", itemList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"get evaluate item set info Exception. ", e);
		}
		return "/ev_item_set_modify.jsp";

	}

	/* 修改评价项目设置信息 */
	public String evaluateItemSetModify(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId", admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			// evServices.updateItemSetInfo(parameterObject);

			evServices.deleteItemSetInfo(parameterObject);

			List<EvaluateSetItem> esList = new ArrayList<EvaluateSetItem>();
			int flag = NumberUtil.parseInt(request.getParameter("temp1"));
			if (flag >= 0) {
				String item = "";
				String proportion = "";
				String yearMonth = StringUtil.checkNull(request
						.getParameter("MONTH_SEQ"));
				String deptid = StringUtil.checkNull(request
						.getParameter("DEPTID_SEQ"));
				String position = StringUtil.checkNull(request
						.getParameter("POSITION_SEQ"));

				for (int i = 0; i < (flag + 1); i++) {
					EvaluateSetItem es = new EvaluateSetItem();
					item = StringUtil.checkNull(request
							.getParameter("selectItem" + i));
					proportion = StringUtil.checkNull(request
							.getParameter("proportion" + i));

					if (item.equals("") || item == null) {

						continue;
					}
					es.setDeptId(deptid);
					es.setPosition(position);
					es.setMonth(yearMonth);
					es.setItemId(item);
					es.setProportion(proportion);
					esList.add(es);
				}

			}

			evServices.insertItemSetInfo(parameterObject, esList);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("updateItemSetInfo Exception. ", e);
		}
		return "/ev_item_set_view.jsp";

	}

	/* 删除评价项目设置信息 */
	public String evaluateItemSetDelete(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			// parameterObject.setString("personId",admin.getPersonId());
			// parameterObject.setString("cpnyId", admin.getCpnyId());
			evServices.deleteItemSetInfo(parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteAffirmSetInfo Exception. ", e);
		}
		return "/ev_item_set_view.jsp";

	}

	/* 初始化评价项目设置信息 */
	public String evaluateItemSetInit(HttpServletRequest request,
			AdminBean admin) {
		try {
			this.putToolbarInfo(request);
			SimpleMap parameterObject = null;
			String cpnyId = admin.getCpnyId();

			parameterObject = ObjectBindUtil.getData(request);
			String yearMonth = request.getParameter("year")
					+ request.getParameter("month");

			evServices.initalEvParamItem(yearMonth, cpnyId);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("initalEvParamItem Exception. ", e);
		}
		return "/ev_item_set_view.jsp";

	}

}
