package com.ait.ga.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ait.api.resultApi.DooPushAPI;
import com.ait.util.StringUtil;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.SealMangerSerivces;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.gm.business.EateryServices;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.utils.MenuBiz;
import com.ait.utils.ToolMenu;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-27
 * 
 */
public class SealMangerCommand implements Command {

	private SealMangerSerivces smServices = null;
	private Mail mail = null;
	private EssSysparam essSysparam = null;

	public SealMangerCommand() {
		smServices = new SealMangerSerivces();
		mail = new Mail();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		String content = request.getParameter("content");
		String returnPage = null;
		if (!content.equals("") && content.equals("sealApplyPage")) {
			String documentno = FormUtil.getApplyDocumentid("DOCUMENTNO",
					"GA_SEAL_APPLY", "GZ");
			request.setAttribute("documentno", documentno);
			String declaration = "";
			if ("63000000".equals(admin.getCpnyId())) {
				declaration = "(申请后请邀请领导决裁，在8:00~9:00，13:00~14:00进行盖章，谢谢)";
			}
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("person_id", admin.getAdminID());
			EateryServices es = new EateryServices();
			List listDeptname = es.getDeptname(simpleMap);
			String deptname = "";
			String deptid = "";
			String isLend = request.getParameter("isLend") != null ? request.getParameter("isLend") : "0";
			if (!listDeptname.isEmpty()) {
				SimpleMap simpleMa1 = new SimpleMap();
				simpleMa1 = (SimpleMap) listDeptname.get(0);
				deptname = simpleMa1.getString("DEPTNAME");
				deptid = simpleMa1.getString("DEPTID");
			}
			String sealType=request.getParameter("sealType");
			String seDa= "0";
			if(sealType != null && isLend.equals("1") && (sealType.equals("SealType_Code05")||
					sealType.equals("SealType_Code06")||sealType.equals("SealType_Code09")||
					sealType.equals("SealType_Code10")||sealType.equals("SealType_Code13")||
					sealType.equals("SealType_Code14")||sealType.equals("SealType_Code17")||
					sealType.equals("SealType_Code18")||sealType.equals("SealType_Code93")||
					sealType.equals("SealType_Code94")||sealType.equals("SealType_Code106")||
					sealType.equals("SealType_Code108")||sealType.equals("SealType_Code110")||
					sealType.equals("SealType_Code03")||sealType.equals("SealType_Code132")||
					sealType.equals("SealType_Code135")||sealType.equals("SealType_Code28")||
					sealType.equals("SealType_Code29")||sealType.equals("SealType_Code30")||
					sealType.equals("SealType_Code32")||sealType.equals("SealType_Code33")||
					sealType.equals("SealType_Code107")||sealType.equals("SealType_Code109")||
					sealType.equals("SealType_Code105")||sealType.equals("SealType_Code104")||
					sealType.equals("SealType_Code112")||sealType.equals("SealType_Code131")||
					sealType.equals("SealType_Code113")||sealType.equals("SealType_Code111")||
					sealType.equals("SealType_Code114"))){
				seDa = "1";
				request.setAttribute("seViewDate", seDa);
			}else{
				request.setAttribute("seViewDate", seDa);
			}
			
			GaAffirm ga = new GaAffirm();
			EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
					EssSysparam.class, admin.getCpnyId());

			List list=ga.getAffirmor4(admin.getAdminID(), "SealApply", sealType, isLend, essSysparam.isContainsOwner());
			String companyId = request.getParameter("companyId") != null ? request.getParameter("companyId") : admin.getCpnyId();
			request.setAttribute("sealType", sealType);
			request.setAttribute("deptname", deptname);
			request.setAttribute("deptid", deptid);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("companyId", companyId);
			request.setAttribute("declaration", declaration);
			request.setAttribute("isLend", isLend);
			request.setAttribute("list", list);
			
			returnPage = "/ga_seal_apply.jsp";
		} else if (!content.equals("") && content.equals("addSealApply")) {
			returnPage = this.addSealApply(request, admin);
		} else if (!content.equals("") && content.equals("sealAffirm")) {
			this.getSealAffirmAndInformation(request, admin);
			returnPage = "/ga_seal_affirm.jsp";
		} else if (!content.equals("") && content.equals("sealAffirmInfo")) {
			this.visiterApprovalInformation(request, admin);
			returnPage = "/ga_seal_affirmInfo.jsp";
		}else if (content.equals("excelSealInfo") && content != null){
			returnPage = this.ExcelSealInfo(request, admin);
		}else if (!content.equals("") && content.equals("ongingAffirm")) {
			returnPage = this.ongingAffirm(request, admin);
		} else if (!content.equals("") && content.equals("ongingDelete")) {
			returnPage = this.ongingDelete(request, admin);
		} else if (!content.equals("") && content.equals("ongingRead")) {
			returnPage = this.ongingRead(request, admin);
		} else if (!content.equals("") && content.equals("sealApprovalOKorNO")) {
			returnPage = this.sealApprovalOKorNO(request, admin);
		} else if (!content.equals("") && content.equals("deleteApply")) {
			this.deleteApply(request, admin);
			this.visiterApprovalInformation(request, admin);
			returnPage = "/ga_seal_affirmInfo.jsp";
		} else {
			return "error.jsp";
		}
		return returnPage;
	}

	/* 增加公章申请，同时加入公章决裁信息 */
	public String addSealApply(HttpServletRequest request, AdminBean admin) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		GaAffirm gaAffirm = new GaAffirm();
		GaAffirmList gaAffirmList = new GaAffirmList();
//		List affirorList = gaAffirm.getAffirmor(admin.getAdminID(),
//				"SealApply", essSysparam.isContainsOwner());
		
		boolean temp = false;
		String AffirmorIdFirst = "";
		SimpleMap parameterObject = null;
		String documentno1 = FormUtil.getApplyDocumentid("DOCUMENTNO",
				"GA_SEAL_APPLY", "GZ");
		parameterObject = ObjectBindUtil.getData(request);

		HashMap<String, String> formParamMap = new HashMap<String, String>(); // 存放form中的一般属性
		DiskFileUpload fu = new DiskFileUpload();
		// int seqId=this.getSequence("GA_PRODUCTS_APPLY_SEQ");
		List fileItems = null;
		Object object = null;
		try {

			try {
				fileItems = fu.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator fileItr = fileItems.iterator();
			while (fileItr.hasNext()) {
				FileItem fileItem = null;
				// 得到当前文件
				fileItem = (FileItem) fileItr.next();
				// 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
				if (fileItem.isFormField()) {
					if (!fileItem.getFieldName().equals("numberArray")) {
						String formname = fileItem.getFieldName();// 获取form中的名字
						String formcontent = fileItem.getString("UTF-8");
						formParamMap.put(formname, formcontent);
						// System.out.println("formname！"+formname);
						// System.out.println("formcontent！"+formcontent);
					}

				} else {
					String fileName = "";
					String filePath = "";
					if (!fileItem.getName().equals("")) {
						ServletContext application = request.getSession()
								.getServletContext();
						String path = application.getRealPath("/upload/seal");
						int start = fileItem.getName().lastIndexOf("\\");
						String name = fileItem.getName().substring(start + 1);
						String fName = fileItem.getName().substring(
								fileItem.getName().length() - 4,
								fileItem.getName().length());
						File objectfile;
						if (fName.equals("pptx") || fName.equals("PPTX")
								|| fName.equals("DOCX") || fName.equals("docx")) {
							fileName = name.replaceAll("\\s*", "").substring(0,
									name.replaceAll("\\s*", "").length() - 5);
							filePath = ".."
									+ "/upload/seal"
									+ "/"
									+ (documentno1)
									+ name.replaceAll("\\s*", "").substring(
											name.replaceAll("\\s*", "")
													.length() - 5,
											name.replaceAll("\\s*", "")
													.length());
							objectfile = new File(path
									+ "\\"
									+ (documentno1)
									+ name.replaceAll("\\s*", "").substring(
											name.replaceAll("\\s*", "")
													.length() - 5,
											name.replaceAll("\\s*", "")
													.length()));
						} else {
							fileName = name.replaceAll("\\s*", "").substring(0,
									name.replaceAll("\\s*", "").length() - 4);
							filePath = ".."
									+ "/upload/seal"
									+ "/"
									+ (documentno1)
									+ name.replaceAll("\\s*", "").substring(
											name.replaceAll("\\s*", "")
													.length() - 4,
											name.replaceAll("\\s*", "")
													.length());
							objectfile = new File(path
									+ "\\"
									+ (documentno1)
									+ name.replaceAll("\\s*", "").substring(
											name.replaceAll("\\s*", "")
													.length() - 4,
											name.replaceAll("\\s*", "")
													.length()));
						}
						try {
							fileItem.write(objectfile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("上传文件！");
					} else {
						System.out.println("没有上传文件！");
					}
					// /this.insertProductsDetail(formParamMap, seqId, conn,
					// fileName, filePath,formParamMap.get("productsType"),k);
					// ++k;

					parameterObject.set("fileName", fileName);
					parameterObject.set("filePath", filePath);

				}

			}

			// --------------------------------------------------------------------------
			String deptId = formParamMap.get("deptId") != null ? formParamMap
					.get("deptId") : "";
			String sealType = formParamMap.get("sealType");
			String isLend = formParamMap.get("isLend");
			
			List affirorList = new ArrayList();
			String[] affirmorId = (formParamMap.get("affirmorIdArr")).toString().split(",");
			String[] affirmorDuty = (formParamMap.get("affirmorDutyArr")).toString().split(",");
			int Level=1;
			for(int i=0;i<affirmorId.length;i++){				
				if (isLend.equals("0")){
					if (!affirmorDuty[i].equals("C_12005_43")){
						GaAffirmList vb = new GaAffirmList();
						vb.setAffirmLevel(Level);
						Level++;
						vb.setAffirmorId(affirmorId[i]);
						affirorList.add(vb);
					}
				}else{
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(i+1);
					vb.setAffirmorId(affirmorId[i]);
					affirorList.add(vb);
				}
			}	
			
			int autoMaxLevel=affirorList.size();
			
			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;
			ResultSet rs3 = null;
			String sql="";
			
			sql = "  SELECT t.*,rownum num from(select SAR.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
				+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,HR_AFFIRMOR.DUTY_CODE" 
				+ "  FROM SY_AFFIRM_RELATION_TB_MGR SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR " 
				+ "  WHERE SAR.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id " 
				+ "  AND SAR.AFFIRM_TYPE_ID ='SealApply' AND SAR.AFFIRM_OBJECT ='"
				+ sealType +"' ORDER BY AFFIRM_LEVEL) t";
			pstmt = conn.prepareStatement(sql);
			rs3 = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);

			String AffirmorId="";
			while (rs3.next()) {

				Boolean bAffirmor = true;
				
				for (int k=0;k<affirorList.size();k++ ){
					GaAffirmList vc = (GaAffirmList)affirorList.get(k);
					AffirmorId = vc.getAffirmorId();
					if(rs3.getString("AFFIRMOR_ID").equals(AffirmorId) ){
						autoMaxLevel=autoMaxLevel-1;
						bAffirmor = false;
						break;
					}
				}
				
				if (bAffirmor){
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(rs3.getInt("num")+autoMaxLevel);
					vb.setAffirmorId(rs3.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs3.getString("AFFIRMOR_NAME"));
					vb.setAffirmOldLevel(rs3.getInt("AFFIRM_LEVEL"));
					vb.setAffirmorDuty("C_12005_93775");
					affirorList.add(vb);
				}
				
			}
			
			int sealTypeNumber = 0;
			// 公章编号全部为SealType_Code+数字，截取后面数字：
			sealTypeNumber = Integer.parseInt(sealType.substring(13, sealType
					.length()));
			int j = 0;
			int k = 0;
			String empId = "";
			String personId2 = "";
			// 判断 是否在选择公章
			if (sealTypeNumber == 00) {
				request.setAttribute("errorMsg", "请您选择公章类型之后再进行申请!");
				String documentno = FormUtil.getApplyDocumentid("DOCUMENTNO",
						"GA_SEAL_APPLY", "GZ");
				request.setAttribute("documentno", documentno);
				String declaration = "";
				if ("63000000".equals(admin.getCpnyId())) {
					declaration = "(申请后请邀请领导决裁，在8:00~9:00，13:00~14:00进行盖章，谢谢)";
				}
				SimpleMap simpleMap = new SimpleMap();
				simpleMap.set("person_id", admin.getAdminID());
				EateryServices es = new EateryServices();
				List listDeptname = es.getDeptname(simpleMap);
				String deptname = "";
				String deptid = "";
				if (!listDeptname.isEmpty()) {
					SimpleMap simpleMa1 = new SimpleMap();
					simpleMa1 = (SimpleMap) listDeptname.get(0);
					deptname = simpleMa1.getString("DEPTNAME");
					deptid = simpleMa1.getString("DEPTID");
				}
				request.setAttribute("deptname", deptname);
				request.setAttribute("deptid", deptid);
				request.setAttribute("cpnyId", admin.getCpnyId());
				request.setAttribute("declaration", declaration);
				return "/ga_seal_apply.jsp";
			}
			String personId=admin.getPersonId();
			String affirmorIdea="";
			parameterObject.set("useDate", formParamMap.get("useDate"));
			parameterObject.set("sealType", formParamMap.get("sealType"));
			parameterObject.set("selectDeptid", formParamMap
					.get("selectDeptid"));
			parameterObject.set("useInformation", formParamMap
					.get("useInformation"));
			parameterObject.set("useShares", formParamMap.get("useShares"));
			parameterObject.set("note", formParamMap.get("note"));
			parameterObject.set("draftNo", formParamMap.get("draftNo"));
			parameterObject.set("returnDate", formParamMap.get("returnDate"));
			parameterObject.set("isLend", isLend);
			// parameterObject.set("xiezhuPer",
			// formParamMap.get("xiezhuPer"));//公章申请新增协助者邮箱

			parameterObject.set("documentno", documentno1);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			parameterObject.set("personId", personId);
			parameterObject.set("affirmorIdea", affirmorIdea);

			temp = smServices.addSealApply(parameterObject, affirorList);
			AffirmorIdFirst = smServices.getAffirmorIdFirst(parameterObject);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addSealApply happens Exception. ", e);
		}
		if (temp) {
			request.setAttribute("errorMsg", "已申请成功！");
			if (essSysparam.isGaSendMail()) {
				try {
					this.sendCarApplyMail("公章申请 等待决裁", AffirmorIdFirst, admin
							.getDepartment(), admin.getCpnyId(), admin
							.getChineseName(), parameterObject
							.getString("useDate"), parameterObject
							.getString("useInformation"), parameterObject
							.getString("useShares"), parameterObject
							.getString("sealType"), parameterObject
							.getString("affirmorIdea"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			request.setAttribute("errorMsg", "申请失败！");
		}
		String documentno = FormUtil.getApplyDocumentid("DOCUMENTNO",
				"GA_SEAL_APPLY", "GZ");
		request.setAttribute("documentno", documentno);
		
		String seDa= "0";
			request.setAttribute("seViewDate", seDa);
		
		return "/ga_seal_apply.jsp";

	}

	/* 得到公章决裁和决裁情况的信息 */
	public void getSealAffirmAndInformation(HttpServletRequest request,
			AdminBean admin) {
		try {
			String adminId = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getAdminID();
			String adminName = ((AdminBean) request.getSession(false)
					.getAttribute("admin")).getChineseName();
			SimpleMap parameterObject = null;
			SimpleMap map = null;
			SimpleMap map1 = null;
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());

			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
			String cpnyId = request.getParameter("cpnyId");
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("endDate", endDate);
			parameterObject.put("startDate", startDate);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);

			int pageSize = 10;
			int pageGroupSize = 10;
			int currentPage = 0;
			SimpleMap dataMap = null;
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// 取得数据行数
			int resultCount = smServices
					.getSealAffirmAndInformationListNumber(parameterObject);
			List applyList = smServices.getSealAffirmAndInformationList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < applyList.size(); i++) {
				map = (SimpleMap) applyList.get(i);
				parameterObject.set("applyno", map.get("DOCUMENTNO"));
				map.set("applyId", map.get("DOCUMENTNO"));
				List affirmorList = smServices
						.getSealAffirmAndInformationAffirmorList(parameterObject);
				String affirmNo = "";
				String empid = "";
				String tempapplyId = "";
				for (int j = 0; j < affirmorList.size(); j++) {
					map1 = (SimpleMap) affirmorList.get(j);
					affirmNo = map1.getString("GA_AFFIRM_NO");
					String AFFIRMORIDEA = map1.getString("AFFIRMORIDEA");
					empid = map1.getString("AFFIRMOR_ID");
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					if (empid.equals(adminId.toString())) {
						map.set("affirmNo", affirmNo);
					}
					tempapplyId = map1.getString("APPLY_NO");
				}
				request.setAttribute("empid", empid);
				request.setAttribute("tempapplyId", tempapplyId);
				map.set("affirmorList", affirmorList);
			}
			request.setAttribute("adminId", adminId);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("applyList", applyList);
			request.setAttribute("loginAdmin", admin.getAdminID());

			request.setAttribute("qryType", qryType);
			request.setAttribute("deptid", deptid);
			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("key", key);
			request.setAttribute("cpnyId", cpnyId);
			if (applyList == null || applyList.size() == 0) {
				request.setAttribute("errorMsg", "没有您相关的决裁信息！");
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"getSealAffirmAndInformation happens Exception. ", e);
		}

	}

	/* 进行决裁 */
	public String ongingAffirm(HttpServletRequest request, AdminBean admin) {
		boolean temp = false;
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			temp = smServices.ongingAffirm(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getSealAffirmAndInformation(request, admin);
		return "/ga_seal_affirm.jsp";

	}

	/* 进行删除 */
	public String ongingDelete(HttpServletRequest request, AdminBean admin) {
		boolean temp = false;
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			temp = smServices.ongingDelete(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getSealAffirmAndInformation(request, admin);
		return "/ga_seal_affirmInfo.jsp";

	}

	/* 进行标示是否阅读 */
	public String ongingRead(HttpServletRequest request, AdminBean admin) {
		boolean temp = false;
		try {

			SimpleMap parameterObject = null;

			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applerId", admin.getAdminID());
			parameterObject.set("deptId", admin.getDeptID());
			temp = smServices.ongingRead(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getSealAffirmAndInformation(request, admin);
		return "/ga_seal_affirm.jsp";

	}

	public String sealApprovalOKorNO(HttpServletRequest request, AdminBean admin) {
		SimpleMap parameterObject = null;
		try {
			parameterObject = ObjectBindUtil.getData(request);
			String applyno[] = request.getParameterValues("applyno");

			for (int i = 0; i < applyno.length; i++)// 重复赋值为空，避免决裁两次，发送两次一样邮件
			{
				String temp = applyno[i];
				for (int j = i + 1; j < applyno.length; j++) {
					if (temp.equals(applyno[j]))
						applyno[j] = "";
				}
			}

			String flag = request.getParameter("flag");
			parameterObject.set("flag", flag);
			parameterObject.set("adminId", admin.getAdminID());

			String defaultSysFile = "/system.properties";
			UserConfiguration userConfig = UserConfiguration
					.getInstance(defaultSysFile);
			String emailNameDicc = userConfig.getString("emaile.seal.dicc")
					.trim();

			for (int i = 0; i < applyno.length; i++) {

				if (!"".equals(applyno[i])) { // 申请编号不能为空

					int sealTypeNumber = 0;
					// 公章编号全部为SealType_Code+数字，截取后面数字：
					String sealTypeCode = request.getParameter("sealTypeCode_"
							+ applyno[i]);
					sealTypeNumber = Integer.parseInt(sealTypeCode.substring(
							13, sealTypeCode.length()));
					String affirmNo = request.getParameter("affirmNo_"
							+ applyno[i]);
					String applerId = request.getParameter("applerId_"
							+ applyno[i]);
					String affirmorIdea = request.getParameter("affirmorIdea_"
							+ applyno[i]) != null ? request
							.getParameter("affirmorIdea_" + applyno[i]) : "";
					// String
					// xiezhuPerMail=request.getParameter("xiezhuPer_"+applyno[i]);
					parameterObject.set("affirmNo", affirmNo);
					parameterObject.set("applerId", applerId);
					parameterObject.set("affirmorIdea", affirmorIdea);
					parameterObject.set("applyno", applyno[i]);
					smServices.updateAffirmFlag(parameterObject);
					if (request.getParameter("flag").equals("2")) {
						smServices.confirmSealApply(parameterObject);

						this
								.sendCarApplyMail("公章申请 已经否决", applerId,
										request.getParameter("adminDept_"
												+ applyno[i]), admin
												.getCpnyId(), request
												.getParameter("CHINESENAME_"
														+ applyno[i]), request
												.getParameter("useDate_"
														+ applyno[i]), request
												.getParameter("useInformation_"
														+ applyno[i]), request
												.getParameter("useShares_"
														+ applyno[i]), request
												.getParameter("sealType_"
														+ applyno[i]),request
												.getParameter("affirmorIdea_"
														+ applyno[i]));
					} else if (request.getParameter("flag").equals("1")) {
						// 公章最后一级决裁完毕发送邮件通知XX人（MAX_AFFIRM_FLAG 0 不是最后一级决裁者；1
						// 是最后一级决裁）
						String MAX_AFFIRM_FLAG = request
								.getParameter("MAX_AFFIRM_FLAG_" + applyno[i]);
						String MAX_AFFIRM_LEVEL = request
						.getParameter("AFFIRM_LEVEL_" + applyno[i]);
						
						parameterObject.set("affrimlevel", request
								.getParameter("AFFIRM_LEVEL_" + applyno[i]));
						if (MAX_AFFIRM_FLAG.equals("0")) {
							String applerId1 = smServices
									.getApplerId(parameterObject);
							String deptName = request.getParameter("adminDept_"
									+ applyno[i]);

							this.sendCarApplyMail("公章申请 等待决裁", applerId1,
									request.getParameter("adminDept_"
											+ applyno[i]), admin.getCpnyId(),
									request.getParameter("CHINESENAME_"
											+ applyno[i]), request
											.getParameter("useDate_"
													+ applyno[i]), request
											.getParameter("useInformation_"
													+ applyno[i]), request
											.getParameter("useShares_"
													+ applyno[i]), request
											.getParameter("sealType_"
													+ applyno[i]),request
											.getParameter("affirmorIdea_"
													+ applyno[i]));
							if("7509910".equals(applerId1) && !"总务/宣传课".equals(deptName)){
								this.sendCarApplyMail("申请部门领导已裁决完毕，请及时到管理部门盖章处理", applerId,
										request.getParameter("adminDept_"
												+ applyno[i]), admin
												.getCpnyId(), request
												.getParameter("CHINESENAME_"
														+ applyno[i]), request
												.getParameter("useDate_"
														+ applyno[i]), request
												.getParameter("useInformation_"
														+ applyno[i]), request
												.getParameter("useShares_"
														+ applyno[i]), request
												.getParameter("sealType_"
														+ applyno[i]),request
												.getParameter("affirmorIdea_"
														+ applyno[i]));
							}

							//推送
							DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_SEAL, StringUtil.checkNull(applyno[i]),Integer.parseInt(StringUtil.checkNull(parameterObject.get("affrimlevel"))) + 1);
						} else {
							if ("63000000".equals(admin.getCpnyId())) {
								smServices
										.confirmSealApplyForDISD(parameterObject);
							} else {
								smServices.confirmSealApply(parameterObject);
								// 这五个章领导决策通过时都会邮件提醒suyue.li@doosan.com李素月
								if (sealTypeNumber >= 106
										&& sealTypeNumber <= 110) {
									// this.sendCarApplyMail2("公章申请 已经通过","suyue.li@doosan.com",
									// request.getParameter("adminDept_"+applyno[i]),
									// admin.getCpnyId(),
									// request.getParameter("CHINESENAME_"+applyno[i]),
									// request.getParameter("useDate_"+applyno[i]),
									// request.getParameter("useInformation_"+applyno[i]),
									// request.getParameter("useShares_"+applyno[i]),
									// request.getParameter("sealType_"+applyno[i]));
								}
								this.sendCarApplyMail("您的申请已经全部决裁完毕", applerId,
										request.getParameter("adminDept_"
												+ applyno[i]), admin
												.getCpnyId(), request
												.getParameter("CHINESENAME_"
														+ applyno[i]), request
												.getParameter("useDate_"
														+ applyno[i]), request
												.getParameter("useInformation_"
														+ applyno[i]), request
												.getParameter("useShares_"
														+ applyno[i]), request
												.getParameter("sealType_"
														+ applyno[i]),request
												.getParameter("affirmorIdea_"
														+ applyno[i]));

							}
							// 添加给协助者发邮件功能：
							// if(xiezhuPerMail !=null ){
							// this.sendCarApplyMail1("公章申请 已通过决裁！请协助",xiezhuPerMail,
							// request.getParameter("adminDept_"+applyno[i]),
							// admin.getCpnyId(),
							// request.getParameter("CHINESENAME_"+applyno[i]),
							// request.getParameter("useDate_"+applyno[i]),
							// request.getParameter("useInformation_"+applyno[i]),
							// request.getParameter("useShares_"+applyno[i]),
							// request.getParameter("sealType_"+applyno[i]));

							// }

							if (emailNameDicc != null
									&& !emailNameDicc.isEmpty()
									&& admin.getCpnyId().equals("78000000")) {// 公章申请
								// 通过后发邮件通知孔一琳（已取消）
								String emailName1[] = emailNameDicc.split(",");
								for (int j = 0; i < emailName1.length; i++) {
									if (emailName1[j] != null
											&& !emailName1[j].isEmpty()) {
										this
												.sendCarApplyMail(
														"公章申请 已通过决裁",
														emailName1[j],
														request
																.getParameter("adminDept_"
																		+ applyno[i]),
														admin.getCpnyId(),
														request
																.getParameter("CHINESENAME_"
																		+ applyno[i]),
														request
																.getParameter("useDate_"
																		+ applyno[i]),
														request
																.getParameter("useInformation_"
																		+ applyno[i]),
														request
																.getParameter("useShares_"
																		+ applyno[i]),
														request
																.getParameter("sealType_"
																		+ applyno[i]),request
																.getParameter("affirmorIdea_"
																		+ applyno[i]));
									}
								}
							}

						}
					}
				}

			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		this.getSealAffirmAndInformation(request, admin);
		return "/ga_seal_affirm.jsp";

	}

	private void sendCarApplyMail(String title, String adminid,
			String adminDept, String cpny_id, String applyer, String useDate,
			String useInformation, String useShares, String sealType,String affirmorIdea)
			throws Exception {
		SimpleMap inputData = new SimpleMap();

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("applerId", adminid);
		String result = smServices.getconfirm(parameterObject);
		parameterObject.set("sealType", sealType);
		String sealName = smServices.getsealName(parameterObject);

		inputData.set("申请人 ", applyer);
		inputData.set("部门 ", adminDept);
		inputData.set("日期 ", useDate);
		if (sealName == null || sealName.equals("")) {
			inputData.set("使用章 ", sealType);
		} else {
			inputData.set("使用章 ", sealName);
		}

		inputData.set("使用内容 ", useInformation);
		inputData.set("份数 ", useShares);
		inputData.set("意见 ", affirmorIdea);

		// mail.sendMail(inputData);
		if (result != null && !result.equals("") && essSysparam.isGaSendMail()) {
			mail.gaSendMail(inputData, cpny_id, result, title);
		}

	}

	private void sendCarApplyMail1(String title, String email,
			String adminDept, String cpny_id, String applyer, String useDate,
			String useInformation, String useShares, String sealType)
			throws Exception {
		SimpleMap inputData = new SimpleMap();

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("sealType", sealType);
		String sealName = smServices.getsealName(parameterObject);

		inputData.set("申请人：", applyer);
		inputData.set("部门：", adminDept);
		inputData.set("日期：", useDate);
		if (sealName == null || sealName.equals("")) {
			inputData.set("使用章：", sealType);
		} else {
			inputData.set("使用章：", sealName);
		}

		inputData.set("使用内容：", useInformation);
		inputData.set("份数：", useShares);

		// mail.sendMail(inputData);
		mail.gaSendMail(inputData, cpny_id, email, title);

	}

	private void sendCarApplyMail2(String title, String email,
			String adminDept, String cpny_id, String applyer, String useDate,
			String useInformation, String useShares, String sealType)
			throws Exception {
		SimpleMap inputData = new SimpleMap();

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("sealType", sealType);
		String sealName = smServices.getsealName(parameterObject);

		inputData.set("申请人：", applyer);
		inputData.set("部门：", adminDept);
		inputData.set("日期：", useDate);
		if (sealName == null || sealName.equals("")) {
			inputData.set("使用章：", sealType);
		} else {
			inputData.set("使用章：", sealName);
		}

		inputData.set("使用内容：", useInformation);
		inputData.set("份数：", useShares);

		// mail.sendMail(inputData);
		if (email != null && !email.equals("") && essSysparam.isGaSendMail()) {
			mail.gaSendMail(inputData, cpny_id, email, title);
		}

	}

	public String visiterApprovalInformation(HttpServletRequest request,
			AdminBean admin) {
		SimpleMap parameterObject = null;
		try {
			String adminId = admin.getAdminID();
			parameterObject = ObjectBindUtil.getData(request);
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

			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String qryType2 = request.getParameter("qryType2") != null ? request
							.getParameter("qryType2") : "";
			String qryType3 = request.getParameter("qryType3") != null ? request
									.getParameter("qryType3") : "";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
			String key2 = request.getParameter("key2") != null ? request
							.getParameter("key2") : "";
			String key3 = request.getParameter("key3") != null ? request
									.getParameter("key3") : "";
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			
			String codeName = request.getParameter("codeName")!=null?request.getParameter("codeName"):"";
			
			
			
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("qryType2", qryType2);
			parameterObject.put("qryType3", qryType3);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("key2", key2);
			parameterObject.put("key3", key3);
			parameterObject.put("cpnyId", cpnyId);
			parameterObject.put("codeName", codeName);


			parameterObject.set("adminId", admin.getAdminID());

			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if ((","
						+ userConfig.getString("common.role.affirmInfo").trim() + ",")
						.indexOf("," + sgNo[i].trim() + ",") > -1) {
					b = true;
					parameterObject.set("adminId", "");
					parameterObject.set("ADMIN_ID", admin.getAdminID());
				}
			}

			int allsealAffrimInfoInt = smServices
					.allsealAffrimInfoInt(parameterObject);
			List allsealAffrimInfoList = smServices.allsealAffrimInfoList(
					parameterObject, currentPage, pageSize);
			for (int i = 0; i < allsealAffrimInfoList.size(); i++) {
				SimpleMap map = null;
				map = (SimpleMap) allsealAffrimInfoList.get(i);
				String applyId = map.getString("DOCUMENTNO");
				String applyEmpId = map.getString("APPLYORID");
				request.setAttribute("applyId", applyId);
				parameterObject.set("applyId", applyId);
				List allSealApproval = smServices
						.allSealApproval(parameterObject);
				boolean isfalg = true;
				for (int j = 0; j < allSealApproval.size(); j++) {
					SimpleMap map1 = null;
					map1 = (SimpleMap) allSealApproval.get(j);
					String applyNo = map1.getString("APPLY_NO");
					String AFFIRMORIDEA = map1.getString("AFFIRMORIDEA");
					map.set("applyNo", applyNo);
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);

					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					map.set("allSealApproval", allSealApproval);
					if (affirmFlag.equals("0") && applyEmpId.equals(adminId)) {
						map.set("AFFIRM_FLAG", "0");
						if (isfalg) {
							map.set("isfalg", "0");
						}
						isfalg = false;
						break;
					} else {
						isfalg = false;
						map.set("AFFIRM_FLAG", "1");
						map.set("isfalg", "1");
					}
				}
			}
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("qryType", qryType);
			request.setAttribute("qryType2", qryType2);
			request.setAttribute("qryType3", qryType3);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("key2", key2);
			request.setAttribute("key3", key3);
			request.setAttribute("adminID", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			request.setAttribute("codeName", codeName);
			
			
			request.setAttribute("resultCount", allsealAffrimInfoInt);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

			request
					.setAttribute("allsealAffrimInfoList",
							allsealAffrimInfoList);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("ongingAffirm happens Exception. ", e);
		}
		return "/ga_seal_affirmInfo.jsp";

	}
	public String ExcelSealInfo(HttpServletRequest request,
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
			String startDate = request.getParameter("startDate") != null ? request
					.getParameter("startDate")
					: "";
			String endDate = request.getParameter("endDate") != null ? request
					.getParameter("endDate") : "";
			String qryType = request.getParameter("qryType") != null ? request
					.getParameter("qryType") : "0";
			String qryType2 = request.getParameter("qryType2") != null ? request
							.getParameter("qryType2") : "";
			String qryType3 = request.getParameter("qryType3") != null ? request
									.getParameter("qryType3") : "";
			String deptid = request.getParameter("deptid") != null ? request
					.getParameter("deptid") : "";
			String key = request.getParameter("key") != null ? request
					.getParameter("key") : "";
			String key2 = request.getParameter("key2") != null ? request
							.getParameter("key2") : "";
			String key3 = request.getParameter("key3") != null ? request
									.getParameter("key3") : "";
			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("qryType", qryType);
			parameterObject.put("qryType2", qryType2);
			parameterObject.put("qryType3", qryType3);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("key2", key2);
			parameterObject.put("key3", key3);
			parameterObject.put("cpnyId", cpnyId);

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
			List applyList = smServices
					.getExcelSealInfoList(parameterObject);
			for (int i = 0; i < applyList.size(); i++) {
				SimpleMap parameterObject1 = new SimpleMap();
				dataMap = (SimpleMap) applyList.get(i);
				String applyPersonId = dataMap.getString("PERSON_ID");
				parameterObject1.set("applyNo", dataMap.get("DOCUMENTNO"));
				List affirmorList = smServices
						.getSealAffirmorList(parameterObject1);
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
		return "/ga_Seal_Excel.jsp";

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
	public void deleteApply(HttpServletRequest request, AdminBean admin) {
		SimpleMap parameterObject = null;
		try {
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.put("applyno", request.getParameter("applyno"));
			smServices.deleteApply(parameterObject);

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("deleteApply happens Exception. ", e);
		}
	}

}
