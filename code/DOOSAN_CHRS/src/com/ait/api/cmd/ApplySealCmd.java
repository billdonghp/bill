package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.SealMangerSerivces;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.gm.business.EateryServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ait.web.Command;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ApplySealCmd extends AbstractCmd implements Command{

	private SealMangerSerivces smServices = null;
	private Mail mail = null;
	private EssSysparam essSysparam = null;

	public ApplySealCmd() {
		smServices = new SealMangerSerivces();
		mail = new Mail();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map map = getData(request);
			String content = StringUtil.checkNull(map.get("content"));
			//公章申请页面所需信息
			if ("sealApply".equals(content)) {
					this.sealApply(map,response);
				//公章编号
			}else if("getDocumentNo".equals(content)){
				this.getDocumentNo(map,response);
				//公章审批线
			} else if("getAffirmor".equals(content)) {
				this.getAffirmor(map,response);
			}
		} catch (Exception e) {
			response.getWriter().append(R.toJson(R.error()));
		}
		return null;
	}

	/* 增加公章申请，同时加入公章决裁信息 */
	public void sealApply(Map map, HttpServletResponse response) throws Exception {

		System.out.println(map.get("cpnyId")+ "********************");
		System.out.println(map.get("empid")+ "********************");

		AdminBean admin = SysService.getInstance().searchEmp(map.get("empid").toString(),map.get("cpnyId").toString());
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, admin.getCpnyId());
		System.out.println(admin.toString()+ "********************");
		System.out.println(essSysparam+ "********************");
		GaAffirm gaAffirm = new GaAffirm();
		GaAffirmList gaAffirmList = new GaAffirmList();

		boolean temp = false;
		String AffirmorIdFirst = "";
		SimpleMap parameterObject = null;
		String documentno1 = FormUtil.getApplyDocumentid("DOCUMENTNO",
				"GA_SEAL_APPLY", "GZ");

		map.put("documentno", documentno1);
		parameterObject = (SimpleMap)map;

		Object object = null;
		try {

			String sealType = StringUtil.checkNull(map.get("sealType"));
			String isLend = StringUtil.checkNull(map.get("isLend"));

			List affirorList = new ArrayList();
			String[] affirmorId = StringUtil.checkNull(map.get("affirmorIdArr")).split(",");
			String[] affirmorDuty = StringUtil.checkNull(map.get("affirmorDutyArr")).split(",");
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

			temp = smServices.addSealApply(parameterObject, affirorList);
			AffirmorIdFirst = smServices.getAffirmorIdFirst(parameterObject);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("addSealApply happens Exception. ", e);
		}
		if (temp) {
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
					e.printStackTrace();
				}
			}
			response.getWriter().append(R.toJson(R.ok()));
		} else {
			response.getWriter().append(R.toJson(R.error("申请失败")));
		}
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
		System.out.println(result+ "********************");
		System.out.println(essSysparam+ "********************");
		if (result != null && !result.equals("") && essSysparam.isGaSendMail()) {
			mail.gaSendMail(inputData, cpny_id, result, title);
		}
	}

	public void getDocumentNo(Map map, HttpServletResponse response) throws IOException {
		Map resultMap = new HashMap();
		String documentno = FormUtil.getApplyDocumentid("DOCUMENTNO",
				"GA_SEAL_APPLY", "GZ");
		resultMap.put("documentno", documentno);
		response.getWriter().append(R.toJson(R.ok(resultMap)));
	}

	public void getAffirmor(Map map, HttpServletResponse response) throws IOException {
		Map resultMap = new HashMap();
		String applyType = StringUtil.checkNull(map.get("applyType"));
		if("SealApply".equals(applyType)){

			String cpnyId = StringUtil.checkNull(map.get("cpnyId"));
			String adminId = StringUtil.checkNull(map.get("adminId"));
			String sealType = StringUtil.checkNull(map.get("sealType"));
			String isLend = StringUtil.checkNull(map.get("isLend"));
			//获取编号
			/*String documentno = FormUtil.getApplyDocumentid("DOCUMENTNO", "GA_SEAL_APPLY", "GZ");
			resultMap.put("documentno", documentno);*/
			//获取审批线
			GaAffirm ga = new GaAffirm();
			EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam( EssSysparam.class, cpnyId);

			List list=ga.getAffirmor4(adminId, applyType, sealType, isLend, essSysparam.isContainsOwner());
			resultMap.put("affirmorList", list);
		}
		response.getWriter().append(R.toJson(R.ok(resultMap)));
	}

}
