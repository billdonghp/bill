package com.ait.ga.cmd.visit;

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
import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.EatingCardServices;
import com.ait.gm.business.EateryServices;
import com.ait.gm.business.WasteServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class GaAffirm extends ArAbstractCommand {
	private WasteServices wasteServices;

	private EateryServices es;
	
	private EatingCardServices eatingCardes;
	
	private SimpleMap parameterObject;
	
	private String url = "http://10.40.128.28:8083/" ;	
//	private String url = "http://portal.doosan.com" ;	
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;	
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private Mail mail = new Mail() ;
	
	private EssSysparam essSysparam = null;

	public GaAffirm() {
		wasteServices = new WasteServices();
		eatingCardes= new EatingCardServices();
		es = new EateryServices();
		parameterObject = new SimpleMap();
	}
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		String content = request.getParameter("content");
	    if (content.equals("cardApplicationAffirm") && content != null) {				
 		return this.getEatingCardesAffirm(request, admin);
		}else if (content.equals("temporarycardApplicationAffirm") && content != null) {				
 		return this.getTempCardesAffirm(request, admin);
		}else if (content.equals("checkApplicationAffirm") && content != null) {//支票领导决裁				
	 		return this.getCheckAffirm(request, admin);
		}else if(content.equals("batchAffirm") && content != null){
			this.batchAffirm(request);			
		    return this.getEatingCardesAffirm(request, admin);		
		}else if(content.equals("batchTempCardAffirm") && content != null){
			this.batchTempCardAffirm(request);			
		    return this.getTempCardesAffirm(request, admin);	
		}else if(content.equals("batchCheckAffirm") && content != null){//领导操作支票信息（通过、否决）
			this.batchCheckAffirm(request);			
		    return this.getCheckAffirm(request, admin);		
		}else if (content.equals("viewCardAffirmInfo") && content != null) {			
			return this.getEatingCardesAffirmInfo(request, admin);		

		} else if (content.equals("viewtemporaryCardAffirmInfo") && content != null) {//临时卡决裁情况			
			return this.getTempCardesAffirmInfo(request, admin);	

		} else if (content.equals("viewCheckAffirmInfo") && content != null) {//支票决裁情况			
			return this.getCheckAffirmInfo(request, admin);		

		} else if(content.equals("viewVoitureAffirm") && content != null){
			return this.viewVoitureAffirm(request,admin);
		} else if(content.equals("viewDriverOtAffirm") && content != null){
			return this.viewDriverOtAffirm(request,admin);
		}else if(content.equals("viewBusArrangeAffirm") && content != null){
			return this.viewBusArrangeAffirm(request,admin);
		}else if(content.equals("visiterApprovalOKorNO") && content != null){
			this.visiterApprovalOKorNO(request, admin);
			return this.viewVoitureAffirm(request,admin);
			
		}else if(content.equals("driverOtApprovalOKorNO") && content != null){
			this.driverOtApprovalOKorNO(request, admin);
			return this.viewDriverOtAffirm(request,admin);
			
		}else if(content.equals("busArrangeApprovalOKorNO") && content != null){
			this.busArrangeApprovalOKorNO(request, admin);
			return this.viewBusArrangeAffirm(request,admin);    
			
		}else if (content.equals("deleteCardApply") && content != null) {
			this.deleteEateryApply(request.getParameter("applyNo"));
			return this.getEatingCardesAffirmInfo(request, admin);
		
		}else if (content.equals("deleteTempCardApply") && content != null) {
			this.deleteTempCardApply(request.getParameter("applyNo"));
			return this.getTempCardesAffirmInfo(request, admin);
		
		}else if (content.equals("deleteCheckApply") && content != null) {
			this.deleteCheckApply(request.getParameter("applyNo"),request.getParameter("checkAccount"),adminid);
			return this.getCheckAffirmInfo(request, admin);
		
		}else if(content.equals("viewAffirmInfo") && content != null){
//			viewAffirmInfo
			return this.viewAffirmInfo(request, admin);
		}else if(content.equals("excelViewAffirmInfo") && content != null){
//			viewAffirmInfo
			return this.excelViewAffirmInfo(request, admin);
		}else if(content.equals("viewAffirmDriverOtInfo") && content != null){
			return this.viewAffirmDriverOtInfo(request, admin);
		}else if(content.equals("viewAffirmBusArrangeInfo") && content != null){
			return this.viewAffirmBusArrangeInfo(request, admin);
		}else if(content.equals("delvoiture") && content != null){
			this.delvoiture(request, admin);
			return this.viewAffirmInfo(request, admin);
		}
		else {
			return "/error.jsp";
		}
	}
	
	//設施維修決裁
	public List getAffirmorFacility(Object applyNo) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GaAffirmList vb = new GaAffirmList();
		String sql="";
		try{
			sql="select * from SE_COMPLETRECTIFICAT_AFFIRM s, hr_employee y where s.affirmor_id = y.person_id and s.apply_no = ?";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);		
//			pstmt.setString(1, applyNo);
			pstmt.setObject(1, applyNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vb = new GaAffirmList();
				vb.setAffirmLevel(rs.getInt("affirm_level"));
				vb.setAffirmorId(rs.getString("affirmor_id"));
				vb.setAffirmorName(rs.getString("chinesename"));
				vb.setApplyNoFa(rs.getString("apply_no"));
				vb.setAffirmorDuty(rs.getString("duty_code"));
//				vb.setAffirmOldLevel(rs.getInt("AFFIRM_LEVEL"));
				result.add(vb);	
			}
		}catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	
	//快件決裁
	public List getAffirmorExperess(Object applyNo) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GaAffirmList vb = new GaAffirmList();
		String sql="";
		try{
			sql="select * from GA_EXPRESS_AFFIRM s, hr_employee y where s.affirmor_id = y.person_id and s.apply_no = ?";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);		
//			pstmt.setString(1, applyNo);
			pstmt.setObject(1, applyNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vb = new GaAffirmList();
				vb.setAffirmLevel(rs.getInt("affirm_level"));
				vb.setAffirmorId(rs.getString("affirmor_id"));
				vb.setAffirmorName(rs.getString("chinesename"));
				vb.setApplyNoFa(rs.getString("apply_no"));
				vb.setAffirmorDuty(rs.getString("duty_code"));
//				vb.setAffirmOldLevel(rs.getInt("AFFIRM_LEVEL"));
				result.add(vb);	
			}
		}catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	
	/* 得到决裁者信息 */

	public List getAffirmor(String empId, String applyType,boolean containsOwner) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		try {
		//取出决裁对象为员工号时，自己的决裁级别
		sql= " select NVL(MAX(SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL), 0) ownerLevel "+
		            " from SY_AFFIRM_RELATION_TB, HR_EMPLOYEE HR_AFFIRMOR "+
		            " where SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 "+
		            " AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_AFFIRMOR.PERSON_ID "+
		            " AND HR_AFFIRMOR.PERSON_ID=SY_AFFIRM_RELATION_TB.AFFIRMOR_ID "+
		            " AND HR_AFFIRMOR.Person_Id =? "+
		            " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID =? ";
		Logger.getLogger(getClass()).debug(sql);
		pstmt = conn.prepareStatement(sql);		
		pstmt.setString(1, empId);
		pstmt.setString(2, applyType);
		rs = pstmt.executeQuery();
		if(rs.next() && NumberUtil.parseInt(rs.getString("OWNERLEVEL"))!=0){
			ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
		}else{//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 "+
	          " AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_AFFIRMOR.DEPTID "+
	          " AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND HR_AFFIRMOR.Person_Id =? "+
	          " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			pstmt.setString(2, applyType);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}
		}
		if(containsOwner && ownerLevel!=0){
			affirmMinLevel=ownerLevel-1;
		}else{
			affirmMinLevel=ownerLevel;
		}
		 sql =  " SELECT t.*,rownum num from (select SY_AFFIRM_RELATION_TB.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, HR_AFFIRMOR.Chinese_Pinyin, HR_AFFIRMOR.KOREANNAME, SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL"
				+ " FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE, HR_EMPLOYEE HR_AFFIRMOR"
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.Person_Id"
				+ " AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id"
				+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL > "+affirmMinLevel
				+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID='"				
				+ applyType
				+ "'"
				+ " AND HR_EMPLOYEE.Person_Id ='"
				+ empId + "'" + " ORDER BY AFFIRM_LEVEL) t";
		Logger.getLogger(getClass()).debug(sql);
	
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		if(!rs.next()){
			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
            " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select SY_AFFIRM_RELATION_TB.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME,HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME, " 
					+ "  SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL " 
					+ "  FROM SY_AFFIRM_RELATION_TB, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR " 
					+ "  WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HD.DEPTID " 
					+ "  AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id " 
					+ "  AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL > "+affirmMinLevel
					+ "  AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID ='" +applyType +"'  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);
				while (rs2.next()) {
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					vb.setAffirmOldLevel(rs2.getInt("AFFIRM_LEVEL"));
					result.add(vb);
				}
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		}else{

			GaAffirmList vb = new GaAffirmList();
			vb.setAffirmLevel(rs.getInt("num"));
			vb.setAffirmorId(rs.getString("affirmor_id"));
			vb.setAffirmorName(rs.getString("AFFIRMOR_NAME"));
			vb.setAffirmOldLevel(rs.getInt("AFFIRM_LEVEL"));
			result.add(vb);
			while (rs.next()) {
				vb = new GaAffirmList();
				vb.setAffirmLevel(rs.getInt("num"));
				vb.setAffirmorId(rs.getString("affirmor_id"));
				vb.setAffirmorName(rs.getString("AFFIRMOR_NAME"));
				vb.setAffirmOldLevel(rs.getInt("AFFIRM_LEVEL"));
				result.add(vb);	
			}
		}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}	
	
	/* 得到决裁者信息1 */

	public List getAffirmor1(String empId, String applyType,boolean containsOwner) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		int autoMaxLevel=0;
		try {
			//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SAR.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB_AUTO SAR, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SAR.AFFIRM_DEPTID = HR_AFFIRMOR.DEPTID "+
	          " AND SAR.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND SAR.AFFIRM_DUTY IS NOT NULL "+
	          " AND SAR.MODULE ='GA' "+
	          " AND HR_AFFIRMOR.Person_Id =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}
		if(containsOwner && ownerLevel!=0){
			affirmMinLevel=ownerLevel-1;
		}else{
			affirmMinLevel=ownerLevel;
		}

			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
            " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,SAR.AFFIRM_DUTY " 
					+ "  FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR, " 
					+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
					+ "  WHERE SAR.AFFIRM_DEPTID = HD.DEPTID " 
					+ "  AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
					+ "  AND SAR.AFFIRM_DUTY IS NOT NULL "
					+ "  AND SAR.MODULE ='GA'"
					+ "  AND SAR.AFFIRM_LEVEL > "+affirmMinLevel
					+ "  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);

				while (rs2.next()) {
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					vb.setAffirmOldLevel(rs2.getInt("AFFIRM_LEVEL"));
					vb.setAffirmorDuty(rs2.getString("AFFIRM_DUTY"));
					result.add(vb);
					
					autoMaxLevel=rs2.getInt("AFFIRM_LEVEL");
					//PresentApply礼品申请\ExpressApply快件申请\SealApply公章申请，要总监裁决
					if (applyType.equals("ExpressApply")){
						//课长长裁决
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_17")){
							break ;
						}
					} 
					else if (applyType.equals("PresentApply") || applyType.equals("SealApply")){
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_43")){
							break ;
						}
					}else{
						//部长裁决
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
							break ;
						}
					}

				}
				if (!applyType.equals("SealApply")){
//				if (!applyType.equals("SealApply")&& !applyType.equals("ExpressApply")){//20190719取消快件申请杜玲艳裁决

					sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
						+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,HR_AFFIRMOR.DUTY_CODE" 
						+ "  FROM SY_AFFIRM_RELATION_TB_MGR SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR, " 
						+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
						+ "  WHERE SAR.COMPANY_ID = HD.CPNY_ID " 
						+ "  AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
						+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
						+ "  AND SAR.AFFIRM_TYPE_ID ='" +applyType +"' AND HD.DEPTID ='"
						+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
					pstmt = conn.prepareStatement(sql);
					rs3 = pstmt.executeQuery(sql);
					Logger.getLogger(getClass()).debug(sql);
	
					String AffirmorId="";
					while (rs3.next()) {
	
						Boolean bAffirmor = true;
						
						for (int k=0;k<result.size();k++ ){
							GaAffirmList vc = (GaAffirmList)result.get(k);
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
							vb.setAffirmorDuty(rs3.getString("DUTY_CODE"));
							result.add(vb);
						}
						
					}
				
				}			
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
			SqlUtil.close(rs2, pstmt, conn);
			SqlUtil.close(rs3, pstmt, conn);
		}
		return result;
	}	

	/* 得到决裁者信息2 */

	public List getAffirmor2(String empId, String cpnyId, String applyType,boolean containsOwner) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		int autoMaxLevel=0;
		try {
			//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SAR.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB_AUTO SAR, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SAR.AFFIRM_DEPTID = HR_AFFIRMOR.DEPTID "+
	          " AND SAR.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND SAR.AFFIRM_DUTY IS NOT NULL "+
	          " AND SAR.MODULE ='GA' "+
	          " AND HR_AFFIRMOR.Person_Id =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}
		if(containsOwner && ownerLevel!=0){
			affirmMinLevel=ownerLevel-1;
		}else{
			affirmMinLevel=ownerLevel;
		}

			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
            " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,SAR.AFFIRM_DUTY " 
					+ "  FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR, " 
					+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
					+ "  WHERE SAR.AFFIRM_DEPTID = HD.DEPTID " 
					+ "  AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
					+ "  AND SAR.MODULE ='GA'"
					+ "  AND SAR.AFFIRM_LEVEL > "+affirmMinLevel
					+ " AND SAR.AFFIRM_DUTY IS NOT NULL "
					+ "  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);
				String affirmorID=null;
				String affrimDuty = null;
				boolean fg = true;
				boolean fg2 = true;
				while (rs2.next()) {
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					vb.setAffirmOldLevel(rs2.getInt("AFFIRM_LEVEL"));
					vb.setAffirmorDuty(rs2.getString("AFFIRM_DUTY"));
					if(rs2.getString("AFFIRMOR_ID").equals(affirmorID)){
						fg=false;
						
					}else{
						affirmorID=rs2.getString("AFFIRMOR_ID");
						fg=true;
						
					}
					if(rs2.getString("AFFIRMOR_ID").equals("1463896")){
						fg2=false;
					}else{
						fg2=true;
					}
						
					affrimDuty=rs2.getString("AFFIRM_DUTY");
					if(fg&&fg2){
					//System.out.println("AFFIRMOR_ID:"+rs2.getString("AFFIRMOR_ID")+"AFFIRMOR_NAME"+rs2.getString("AFFIRMOR_NAME"));
					result.add(vb);
					}
					autoMaxLevel=rs2.getInt("AFFIRM_LEVEL");
					//PresentApply礼品申请\ExpressApply快件申请\SealApply公章申请，要总监裁决
					if (applyType.equals("PresentApply") || applyType.equals("ExpressApply") || applyType.equals("SealApply")){
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_43")){
							break ;
						}
					}else{
						//部长裁决
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
							break ;
						}
					}

				}
				if (!applyType.equals("SealApply")){

					sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
						+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,HR_AFFIRMOR.DUTY_CODE" 
						+ "  FROM SY_AFFIRM_RELATION_TB_MGR SAR, HR_EMPLOYEE HR_AFFIRMOR, " 
						+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
						+ "  WHERE SAR.COMPANY_ID = '" +cpnyId+ "' AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
						+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
						+ "  AND SAR.AFFIRM_TYPE_ID ='" +applyType +"' ORDER BY AFFIRM_LEVEL) t";
					pstmt = conn.prepareStatement(sql);
					rs3 = pstmt.executeQuery(sql);
					Logger.getLogger(getClass()).debug(sql);
	
					String AffirmorId="";
					while (rs3.next()) {
	
						Boolean bAffirmor = true;
						
						for (int k=0;k<result.size();k++ ){
							GaAffirmList vc = (GaAffirmList)result.get(k);
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
							vb.setAffirmorDuty(rs3.getString("DUTY_CODE"));
							result.add(vb);
						}
						
					}
				
				}			
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
			SqlUtil.close(rs2, pstmt, conn);
			SqlUtil.close(rs3, pstmt, conn);
		}
		return result;
	}	
	
	/* 得到决裁者信息3 目前没用由getAffirmor4替换*/

	public List getAffirmor3(String empId, String sealType, String isLend, boolean containsOwner) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		int autoMaxLevel=0;
		try {
			//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SAR.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB_AUTO SAR, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SAR.AFFIRM_DEPTID = HR_AFFIRMOR.DEPTID "+
	          " AND SAR.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND SAR.AFFIRM_DUTY IS NOT NULL "+
	          " AND SAR.MODULE ='GA' "+
	          " AND HR_AFFIRMOR.Person_Id =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}
		if(containsOwner && ownerLevel!=0){
			affirmMinLevel=ownerLevel-1;
		}else{
			affirmMinLevel=ownerLevel;
		}

			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
            " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select SAR.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,SAR.AFFIRM_DUTY " 
					+ "  FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR " 
					+ "  WHERE SAR.AFFIRM_DEPTID = HD.DEPTID " 
					+ "  AND SAR.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.MODULE ='GA'"
					+ "  AND SAR.AFFIRM_LEVEL > "+affirmMinLevel
					+ " AND SAR.AFFIRM_DUTY IS NOT NULL "
					+ "  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);

				while (rs2.next()) {
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					vb.setAffirmOldLevel(rs2.getInt("AFFIRM_LEVEL"));
					vb.setAffirmorDuty(rs2.getString("AFFIRM_DUTY"));
					result.add(vb);
					
					autoMaxLevel=rs2.getInt("AFFIRM_LEVEL");
					if (isLend.equals("1")){
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_43")){
							break ;
						}
					}else{
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
							break ;
						}
					}

				}
				
				sql = "  SELECT t.*,rownum num from(select SAR.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,HR_AFFIRMOR.DUTY_CODE" 
					+ "  FROM SY_AFFIRM_RELATION_TB_MGR SAR, HR_EMPLOYEE HR_AFFIRMOR " 
					+ "  WHERE SAR.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.AFFIRM_TYPE_ID ='SealApply' AND SAR.AFFIRM_OBJECT ='"
					+ sealType +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs3 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);

				String AffirmorId="";
				while (rs3.next()) {

					Boolean bAffirmor = true;
					
					for (int k=0;k<result.size();k++ ){
						GaAffirmList vc = (GaAffirmList)result.get(k);
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
						result.add(vb);
					}
					
				}
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
			SqlUtil.close(rs2, pstmt, conn);
			SqlUtil.close(rs3, pstmt, conn);
		}
		return result;
	}	
	
	
	/* 得到决裁者信息4 */

	public List getAffirmor4(String empId, String applyType, String sealType, String isLend, boolean containsOwner) {
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		int autoMaxLevel=0;
		try {
			//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SAR.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB_AUTO SAR, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SAR.AFFIRM_DEPTID = HR_AFFIRMOR.DEPTID "+
	          " AND SAR.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND SAR.AFFIRM_DUTY IS NOT NULL "+
	          " AND SAR.MODULE ='GA' "+
	          " AND HR_AFFIRMOR.Person_Id =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}
		if(containsOwner && ownerLevel!=0){
			affirmMinLevel=ownerLevel-1;
		}else{
			affirmMinLevel=ownerLevel;
		}

			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
            " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,SAR.AFFIRM_DUTY " 
					+ "  FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR, " 
					+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
					+ "  WHERE SAR.AFFIRM_DEPTID = HD.DEPTID " 
					+ "  AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
					+ "  AND SAR.MODULE ='GA'"
					+ "  AND SAR.AFFIRM_LEVEL > "+affirmMinLevel
					+ " AND SAR.AFFIRM_DUTY IS NOT NULL "
					+ "  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);

				while (rs2.next()) {
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					vb.setAffirmOldLevel(rs2.getInt("AFFIRM_LEVEL"));
					vb.setAffirmorDuty(rs2.getString("AFFIRM_DUTY"));
					result.add(vb);
					
					autoMaxLevel=rs2.getInt("AFFIRM_LEVEL");
					if (isLend.equals("1")){
						if(sealType.equals("SealType_Code17")||sealType.equals("SealType_Code18")
								||sealType.equals("SealType_Code19")||sealType.equals("SealType_Code20")
								||sealType.equals("SealType_Code05")||sealType.equals("SealType_Code06")){
							if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
								break ;
							}
						}else if (rs2.getString("AFFIRM_DUTY").equals("C_12005_43")){
							break ;
						}
					}else{
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
							break ;
						}
					}

				}
				
				sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
					+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,HR_AFFIRMOR.DUTY_CODE" 
					+ "  FROM SY_AFFIRM_RELATION_TB_MGR SAR, HR_EMPLOYEE HR_AFFIRMOR, " 
					+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
					+ "  WHERE NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
					+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
					+ "  AND SAR.AFFIRM_TYPE_ID ='SealApply' AND SAR.AFFIRM_OBJECT ='"
					+ sealType +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs3 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);

				String AffirmorId="";
				while (rs3.next()) {

					Boolean bAffirmor = true;
					
					for (int k=0;k<result.size();k++ ){
						GaAffirmList vc = (GaAffirmList)result.get(k);
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
						result.add(vb);
					}
					
				}
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
			SqlUtil.close(rs2, pstmt, conn);
			SqlUtil.close(rs3, pstmt, conn);
		}
		return result;
	}		
	/* 获取Person_id */
	public String getPersonId(String empid, String cpnyId) {

		String personId = "";

		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select get_personid('" + empid + "','"+cpnyId+"') as person_id from dual ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				personId = rs.getString("person_id");// 代表可以进行操作
			}

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		
		return personId;

	}
	
	
	
	
	
	/* 根据登陆者的ID判断是否可以进行删除操作 */
	public int getDeleteOperationInfo(String adminid, String applyid,
			String tablename) {

		int s = 0;// 3没有任何意义，只是为了初始化

		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select affirm.affirm_flag " + " from  " + tablename
				+ " where   affirm.apply_no = '" + applyid + "'   "
				+ " and affirm.created_by='" + adminid + "'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				s += rs.getInt("affirm_flag");// 代表可以进行操作
			}

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		if (s >= 1) {
			s = 1;
		}
		return s;

	}

	/* 执行决裁 */
	public void getOperationAffirm(String affirmorid, String applyno,
			String result) {

		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
		String sql = "update ga_affirm " + " set AFFIRM_FLAG='" + result + "'"
				+ " where  AFFIRMOR_ID = '" + affirmorid + "' "
				+ " and APPLY_NO='" + applyno + "'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeUpdate();

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}

	}

	public void batchAffirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		
		try {		
		String countsArr[]=request.getParameterValues("counts");
		for(int i=0;i<countsArr.length;i++){
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
		parameterObject.set("affirmNo", request.getParameter("affirmNo_"+countsArr[i]));
		parameterObject.set("affirmFlag", request.getParameter("affirmFlag"));
		parameterObject.set("maxAffirmFlag", request.getParameter("maxAffirmFlag_"+countsArr[i]));
		parameterObject.set("affirmorIdea", request.getParameter("affirmorIdea_"+countsArr[i]));
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("applyorEmail", request.getParameter("applyorEmail_"+countsArr[i]));
		parameterObject.set("applyDate", request.getParameter("applyDate_"+countsArr[i]));	
		parameterObject.set("affirmorIdea",request.getParameter("affirmorIdea_"+countsArr[i]));
		parameterObject.set("applyUser",request.getParameter("applyUser_"+countsArr[i]));
		parameterObject.set("upEmail",eatingCardes.getupaffrimemail(parameterObject));
		
		eatingCardes.oingAffirm(parameterObject);
		this.oingAffirm(parameterObject,admin);	
		}			
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
	}

	public void batchTempCardAffirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		
		try {		
		String countsArr[]=request.getParameterValues("counts");
		for(int i=0;i<countsArr.length;i++){
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
		parameterObject.set("affirmNo", request.getParameter("affirmNo_"+countsArr[i]));
		parameterObject.set("affirmFlag", request.getParameter("affirmFlag"));
		parameterObject.set("maxAffirmFlag", request.getParameter("maxAffirmFlag_"+countsArr[i]));
		parameterObject.set("applyer", request.getParameter("applyer_"+countsArr[i]));
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("applyorEmail", request.getParameter("applyorEmail_"+countsArr[i]));
		parameterObject.set("applyDate", request.getParameter("applyDate_"+countsArr[i]));	
		parameterObject.set("affirmorIdea",request.getParameter("affirmorIdea_"+countsArr[i]));
		parameterObject.set("upEmail",eatingCardes.getuptcaraffrimemail(parameterObject));
			parameterObject.set("AFFIRM_LEVEL", request.getParameter("AFFIRM_LEVEL_"+countsArr[i]));

			eatingCardes.oingTempAffirm(parameterObject);
		this.oingTempAffirm(parameterObject,admin);	
		}			
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
	}

	public void batchCheckAffirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		
		try {		
		String countsArr[]=request.getParameterValues("counts");
		for(int i=0;i<countsArr.length;i++){
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
		parameterObject.set("affirmNo", request.getParameter("affirmNo_"+countsArr[i]));
		parameterObject.set("affirmFlag", request.getParameter("affirmFlag"));
		parameterObject.set("maxAffirmFlag", request.getParameter("maxAffirmFlag_"+countsArr[i]));
		parameterObject.set("applyer", request.getParameter("applyer_"+countsArr[i]));
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("applyorEmail", request.getParameter("applyorEmail_"+countsArr[i]));
		parameterObject.set("applyDate", request.getParameter("applyDate_"+countsArr[i]));	
		parameterObject.set("affirmorIdea",request.getParameter("affirmorIdea_"+countsArr[i]));
		parameterObject.set("checkAccount",request.getParameter("checkAccount_"+countsArr[i]));
		parameterObject.set("upEmail",eatingCardes.getupcheckaffrimemail(parameterObject));
		
		eatingCardes.oingCheckAffirm(parameterObject);
		this.oingCheckAffirm(parameterObject,admin);	
		}			
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
	}
	/*决裁验证*/
	private void oingAffirm(SimpleMap parameterObject,AdminBean admin){
		
       try {	
    	 SimpleMap dataMap = new SimpleMap();
    	   dataMap.set("主题", "就餐申请");
    	   dataMap.set("申请人", parameterObject.getString("applyUser"));    	   
    	   dataMap.set("申请时间", parameterObject.getString("applyDate"));    	
			
			if(parameterObject.getString("affirmFlag").equals("2")){
				eatingCardes.updateApplyInfo(parameterObject);//否决后给申请者发邮件
				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("") && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "就餐卡申请 已被否决");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("1")){
				eatingCardes.updateApplyInfo(parameterObject);//最大级决裁者通过时，给申请者发邮件				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "就餐卡申请 已通过确认");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("0")) {				
				if(parameterObject.getString("upEmail")!=null && ! parameterObject.getString("upEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("upEmail"), "就餐卡申请 请您确认");
				}
			}			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
		
		
		
	}
	/*决裁验证*/
	private void oingTempAffirm(SimpleMap parameterObject,AdminBean admin){
		
       try {	
    	 SimpleMap dataMap = new SimpleMap();
    	   dataMap.set("主题", "临时卡申请");
    	   dataMap.set("申请人", parameterObject.getString("applyer"));    	   
    	   dataMap.set("申请时间", parameterObject.getString("applyDate"));    	
			
			if(parameterObject.getString("affirmFlag").equals("2")){
				eatingCardes.updateTempCardApplyInfo(parameterObject);//否决后给申请者发邮件
				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("") && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "临时卡申请 已被否决");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("1")){
				eatingCardes.updateTempCardApplyInfo(parameterObject);//最大级决裁者通过时，给申请者发邮件				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "临时卡申请 已通过确认");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("0")) {				
				if(parameterObject.getString("upEmail")!=null && ! parameterObject.getString("upEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("upEmail"), "临时卡申请 请您确认");
				}
				//推送
				DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_TEMP_CARD,parameterObject.getString("applyNo"),Integer.parseInt(parameterObject.getString("AFFIRM_LEVEL")) + 1);
			}			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 	
	}
	
	/*决裁验证*/
	private void oingCheckAffirm(SimpleMap parameterObject,AdminBean admin){
		
       try {	
    	 SimpleMap dataMap = new SimpleMap();
    	   dataMap.set("主题", "支票申请");
    	   dataMap.set("申请人", parameterObject.getString("applyer"));    	   
    	   dataMap.set("申请时间", parameterObject.getString("applyDate"));    	
			
			if(parameterObject.getString("affirmFlag").equals("2")){
				eatingCardes.updateCheckApplyInfo(parameterObject);//否决后给申请者发邮件
			    eatingCardes.updateCheckInfo(parameterObject);//否决后更新支票号状态为未使用
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("") && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "支票申请 已被否决");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("1")){
				eatingCardes.updateCheckApplyInfo(parameterObject);//最大级决裁者通过时，给申请者发邮件				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "支票申请 已通过确认");
					mail.gaSendMail(dataMap, admin.getCpnyId(), "binglin.cong@doosan.com", "支票申请已通过，请确认");//支票申请通过邮件提醒支票担当丛炳林
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("0")) {				
				if(parameterObject.getString("upEmail")!=null && ! parameterObject.getString("upEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("upEmail"), "支票申请 请您确认");
				}
			}			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 	
	}
	private void sendAffirmMail(
			String title,
            String applyer, 
            String applyDate,
            String mailAddress
            ) throws Exception {
			
			SimpleMap parameterObject = new SimpleMap();
			
			SimpleMap inputData = new SimpleMap();
			
			StringBuffer content = new StringBuffer();
					
			content.append(" 申请人：").append(applyer)
			.append("<br><br>").append(" 主题：").append("发放就餐卡申请")
		   .append("<br><br>").append(" 申请时间：").append(applyDate) ;				
            content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行决裁</a>");
	
			content.append("<br><br>斗山工程机械(中国)有限公司") ;
				inputData.setString("EMAIL_TITLE", title);
	
			
			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());			
			inputData.setString("RCVR_EMAIL_ADDR", mailAddress);			
				mail.sendMail(inputData) ;			
	}
	/*根据登陆者得到决裁的信息*/
	public String getEatingCardesAffirm(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;
		
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());
		
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		parameterObject.set("qryType", qryType);
		parameterObject.set("cpnyId", cpnyId);
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);
		
       //取得数据行数
		int resultCount = eatingCardes.getEatingCardAffirmListNumber(parameterObject);
		List eatingCardAffirmList=eatingCardes.getEatingCardAffirmList(parameterObject,currentPage,pageSize);
		for(int i=0;i<eatingCardAffirmList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)eatingCardAffirmList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=eatingCardes.getEatingCardAffirmorList(parameterObject1);			
			dataMap.set("affirmorList", affirmorList);			
			dataMap.set("EateryTypeName",this.getEateryTypeName(dataMap.getString("EATERY_TYPE"), admin));
			
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("qryType", request.getParameter("qryType"));
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
	    request.setAttribute("eatingCardAffirmList", eatingCardAffirmList);
	    request.setAttribute("qryType", qryType);	    
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("eatingCardAffirmList happens Exception. ", e);
	}     
		return "/ga_card_application_affirm.jsp?menu_code=ga0202";
	
  }
	/*根据登陆者得到决裁的信息*/
	public String getTempCardesAffirm(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;
		
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		parameterObject.set("qryType", qryType);
		parameterObject.set("cpnyId", cpnyId);
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);
		
       //取得数据行数
		int resultCount = eatingCardes.getTempCardAffirmListNumber(parameterObject);
		List tempCardAffirmList=eatingCardes.getTempCardAffirmList(parameterObject,currentPage,pageSize);
		for(int i=0;i<tempCardAffirmList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)tempCardAffirmList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=eatingCardes.getTempCardAffirmorList(parameterObject1);			
			dataMap.set("affirmorList", affirmorList);			
			dataMap.set("permissionsTypeName",this.getPermissionsTypeName(dataMap.getString("PERMISSIONS_TYPE"), admin));
			
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("qryType", request.getParameter("qryType"));
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
	    request.setAttribute("tempCardAffirmList", tempCardAffirmList);
	    request.setAttribute("qryType", qryType);	    
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("eatingCardAffirmList happens Exception. ", e);
	}     
		return "/ga_tempcard_application_affirm.jsp?menu_code=ga0213";
	
  }
	/*根据登陆者得到待决裁的支票信息*/
	public String getCheckAffirm(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;
		
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		parameterObject.set("cpnyId", cpnyId);
		parameterObject.set("qryType", qryType);
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
//		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
//		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);

	       //取得数据行数			
			int resultCount = eatingCardes.getCheckAffirmListNumber(parameterObject);
			List checkAffirmInfoList=eatingCardes.getCheckAffirmList(parameterObject,currentPage,pageSize);
			for(int i=0;i<checkAffirmInfoList.size();i++){
				SimpleMap parameterObject1 =  new SimpleMap();
				dataMap=(SimpleMap)checkAffirmInfoList.get(i);			
				parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
				List affirmorList=eatingCardes.getCheckAffirmorList(parameterObject1);			
				dataMap.set("affirmorList", affirmorList);		
			}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("qryType", request.getParameter("qryType"));
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
	    request.setAttribute("checkAffirmInfoList", checkAffirmInfoList);
	    request.setAttribute("qryType", qryType);	    
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("eatingCardAffirmList happens Exception. ", e);
	}     
		return "/ga_check_application_affirm.jsp?menu_code=ga0215";
	
  }
	/*根据登陆者得到决裁情况的信息*/
	public String getEatingCardesAffirmInfo(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;

		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());	
		parameterObject.set("cpnyId", cpnyId);	
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		parameterObject.set("qryType", qryType);	
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		UserConfiguration userConfig;
		userConfig = UserConfiguration.getInstance("/system.properties");
		String[] sgNo = admin.getScreenGrantNo().split(",");
		boolean b = false;
		for (int i = 0; i < sgNo.length; i++) {
		if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
			b = true;
		parameterObject.set("adminId", "");
		parameterObject.set("ADMIN_ID", admin.getAdminID());
		}
	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);
		
       //取得数据行数
		int resultCount = eatingCardes.getEatingCardAffirmInfoListNumber(parameterObject);
		List eatingCardAffirmInfoList=eatingCardes.getEatingCardAffirmInfoList(parameterObject,currentPage,pageSize);
		for(int i=0;i<eatingCardAffirmInfoList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)eatingCardAffirmInfoList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=eatingCardes.getEatingCardAffirmorList(parameterObject1);		
			dataMap.set("EateryTypeName",this.getEateryTypeName(dataMap.getString("EATERY_TYPE"), admin));
			dataMap.set("affirmorList", affirmorList);			
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("qryType", request.getParameter("qryType"));
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
		request.setAttribute("qryType", qryType);
	    request.setAttribute("eatingCardAffirmInfoList", eatingCardAffirmInfoList);	  
		request.setAttribute("cpnyId", cpnyId);
	    
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("eatingCardAffirmInfoList happens Exception. ", e);
	}     
		return "/ga_card_affirm_info.jsp?menu_code=ga0302";
	
  }	
	/*根据登陆者得到决裁情况的信息*/
	public String getTempCardesAffirmInfo(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());	
		parameterObject.set("cpnyId", cpnyId);	
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		parameterObject.set("qryType", qryType);	
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		UserConfiguration userConfig;
		userConfig = UserConfiguration.getInstance("/system.properties");
		String[] sgNo = admin.getScreenGrantNo().split(",");
		boolean b = false;
		for (int i = 0; i < sgNo.length; i++) {
		if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
			b = true;
		parameterObject.set("adminId", "");
		parameterObject.set("ADMIN_ID", admin.getAdminID());
		}
	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);
		
     //取得数据行数
		int resultCount = eatingCardes.getTempCardAffirmInfoListNumber(parameterObject);
		List tempCardAffirmInfoList=eatingCardes.getTempCardAffirmInfoList(parameterObject,currentPage,pageSize);
		for(int i=0;i<tempCardAffirmInfoList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)tempCardAffirmInfoList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=eatingCardes.getTempCardAffirmorList(parameterObject1);		
			dataMap.set("PERMISSIONSTYPENAME",this.getPermissionsTypeName(dataMap.getString("PERMISSIONS_TYPE"), admin));
			dataMap.set("affirmorList", affirmorList);			
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("qryType", request.getParameter("qryType"));
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
		request.setAttribute("qryType", qryType);
	    request.setAttribute("tempCardAffirmInfoList", tempCardAffirmInfoList);	  
		request.setAttribute("cpnyId", cpnyId);
	    
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("eatingCardAffirmInfoList happens Exception. ", e);
	}     
		return "/ga_temp_card_affirm_info.jsp?menu_code=ga0316";
	
}	
	/*根据登陆者得到决裁情况的信息*/
	public String getCheckAffirmInfo(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());	
		parameterObject.set("cpnyId", cpnyId);	
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		parameterObject.set("qryType", qryType);	
		int pageSize =10;
		int pageGroupSize =10;
		int currentPage = 0;
		SimpleMap dataMap = new SimpleMap();
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		UserConfiguration userConfig;
		userConfig = UserConfiguration.getInstance("/system.properties");
		String[] sgNo = admin.getScreenGrantNo().split(",");
		boolean b = false;
		for (int i = 0; i < sgNo.length; i++) {
		if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
			b = true;
		parameterObject.set("adminId", "");
		parameterObject.set("ADMIN_ID", admin.getAdminID());
		}
	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):sdf.format(today.getTime());
		String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):sdf.format(today.getTime());
		parameterObject.set("startDate", startDate);
		parameterObject.set("endDate", endDate);
		
     //取得数据行数
		int resultCount = eatingCardes.getCheckAffirmInfoListNumber(parameterObject);
		List checkAffirmInfoList=eatingCardes.getCheckAffirmInfoList(parameterObject,currentPage,pageSize);
		for(int i=0;i<checkAffirmInfoList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)checkAffirmInfoList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=eatingCardes.getCheckAffirmorList(parameterObject1);	
			dataMap.set("affirmorList", affirmorList);			
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("cpnyId", cpnyId);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("qryType", request.getParameter("qryType"));
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
		request.setAttribute("qryType", qryType);
	    request.setAttribute("checkAffirmInfoList", checkAffirmInfoList);	  
	    
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("eatingCardAffirmInfoList happens Exception. ", e);
	}     
		return "/ga_check_affirm_info.jsp?menu_code=ga0317";
	
}	
	/* 再没有决裁的情况下，删除该信息 */
	public void deleteEateryApply(String APPLYNO){

		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
	
		String sql1 = "delete from ga_card_application  where APPLY_NO=" + APPLYNO + "";	
		String sql2 = "delete from ga_card_application_affirm  where APPLY_NO=" + APPLYNO + "";		
		Logger.getLogger(getClass()).debug(sql1);
		try {
			conn.setAutoCommit(true);		   
			stmt = conn.prepareStatement(sql1);
			rs += stmt.executeUpdate();
			stmt = conn.prepareStatement(sql2);
			rs += stmt.executeUpdate();
			if(rs==0){
				conn.rollback();
			}
			
			conn.commit();
			
		} catch (Exception e) {
			
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}
	
	/* 再没有决裁的情况下，删除该信息 */
	public void deleteTempCardApply(String APPLYNO){

		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
	
		String sql1 = "delete from GA_TEMPCARD_APPLICATION  where APPLY_NO=" + APPLYNO + "";	
		String sql2 = "delete from GA_TEMPCARD_APPLICATION_AFFIRM  where APPLY_NO=" + APPLYNO + "";		
		Logger.getLogger(getClass()).debug(sql1);
		try {
			conn.setAutoCommit(true);		   
			stmt = conn.prepareStatement(sql1);
			rs += stmt.executeUpdate();
			stmt = conn.prepareStatement(sql2);
			rs += stmt.executeUpdate();
			if(rs==0){
				conn.rollback();
			}
			
			conn.commit();
			
		} catch (Exception e) {
			
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}
	
	/* 再没有决裁的情况下，删除该信息;并更新支票号为未使用 */
	public void deleteCheckApply(String APPLYNO,String checkAccount,String adminId){

		Connection conn = ConnBean.getConn();
		PreparedStatement stmt = null;
		int rs = 0;
	
		String sql1 = "delete from GA_CHECK_APPLICATION  where APPLY_NO=" + APPLYNO + "";	
		String sql2 = "delete from GA_CHECK_APPLY_AFFIRM  where APPLY_NO=" + APPLYNO + "";	
		String sql3 = "UPDATE GA_CHECK_ACCOUNT G SET G.ACTIVITY = 0,G.UPDATE_DATE = SYSDATE,G.UPDATED_BY = " + adminId + " WHERE G.CHECK_ACCOUNT = " + checkAccount + "";		
		Logger.getLogger(getClass()).debug(sql1);	
		Logger.getLogger(getClass()).debug(sql2);	
		Logger.getLogger(getClass()).debug(sql3);
		try {
			conn.setAutoCommit(true);		   
			stmt = conn.prepareStatement(sql1);
			rs += stmt.executeUpdate();
			stmt = conn.prepareStatement(sql2);
			rs += stmt.executeUpdate();
			stmt = conn.prepareStatement(sql3);
			rs += stmt.executeUpdate();
			if(rs==0){
				conn.rollback();
			}
			
			conn.commit();
			
		} catch (Exception e) {
			
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
	}
	
//	 单价修改
	private void updateUnitPrice(HttpServletRequest request) {
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		SimpleMap map = null;
		
		String unitPrice = request.getParameter("unitPrice");
		String wasteId = request.getParameter("wasteId");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("unitPrice", unitPrice);
			parameterObject.set("wasteId", wasteId);
			
			// retrieve attendance record list
			Object eatLookCount = 0;
			
			/*List activeDatelist = wasteServices.searchOKDATE(parameterObject);*/
			/*for(int i=0 ; i<activeDatelist.size() ; i++){
				map = (SimpleMap) activeDatelist.get(i);
				String activeDate = map.getString("WASTE_UNITPRICEOKDATE");
				if(activeDate.equals(date.toString())){
					wasteServices.updateUnitPrice(parameterObject);
				}
			}*/
			request.setAttribute("recordCount", NumberUtil
					.parseInt(eatLookCount));

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
	}
	public String viewVoitureAffirm(HttpServletRequest request,AdminBean admin) {
		try {
		request.setAttribute("adminId", admin.getAdminID());
		parameterObject = ObjectBindUtil.getData(request);
		/* paging logic */
		UserConfiguration config = UserConfiguration
				.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			SimpleMap map = null;
			SimpleMap map1 = null;
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			parameterObject.put("qryType", qryType);
			parameterObject.put("deptid", deptid);
			parameterObject.put("endDate",endDate );
			parameterObject.put("startDate", startDate);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			parameterObject.put("supervisor", admin.getAdminID());
			int voitureInt = eatingCardes.getvoitureInt(parameterObject);
			List voitureList = eatingCardes.getvoitureList(parameterObject,currentPage, pageSize);
			for(int i=0;i<voitureList.size();i++){
				map = (SimpleMap) voitureList.get(i);
				String applyId = map.getString("VOITURE_APPLYID");
				parameterObject.set("APPLY_NO", applyId);
				map.set("applyId", applyId);
				List voitureDetailList = eatingCardes.getVoitureDistinctionList(parameterObject);
				List voitureAffiirmList = eatingCardes.getVoitureAffiirmList(parameterObject);
				map.set("voitureAffiirmList", voitureAffiirmList);
				map.set("voitureDetailList", voitureDetailList);
				
				String carStr = "";
				String VOITURE_BRAND = "";
				String VOITURE_NUMBER = "";
				parameterObject.set("applyId", applyId);
				List car_name = es.getCar_name(parameterObject);
				for(int z=0;z<car_name.size();z++){
					SimpleMap map2 = new SimpleMap();
					map2 = (SimpleMap)car_name.get(z);
					if(map2.getString("DRIVER")!=null){
						carStr +=map2.getString("DRIVER")+",";
					}
					VOITURE_BRAND += map2.getString("VOITURE_BRAND")+",";
					VOITURE_NUMBER += map2.getString("VOITURE_NUMBER")+",";
				}
				if(carStr != ""&& carStr != null){
					map.set("carStr", carStr.substring(0,carStr.length()-1));
				}
				if(VOITURE_BRAND != ""&& VOITURE_BRAND != null){
					map.set("VOITURE_BRAND", VOITURE_BRAND.substring(0,VOITURE_BRAND.length()-1));
				}
				if(VOITURE_NUMBER != ""&& VOITURE_NUMBER != null){
					map.set("VOITURE_NUMBER", VOITURE_NUMBER.substring(0,VOITURE_NUMBER.length()-1));
				}
			}
			request.setAttribute("voitureList", voitureList);
			request.setAttribute("voitureInt", voitureInt);
			
			request.setAttribute("qryType", qryType);
			request.setAttribute("cpnyId", cpnyId);
			request.setAttribute("deptid", deptid);
			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("key", key);
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}
		return "/ga_affirm_voiture.jsp";
	}
	
	public String viewDriverOtAffirm(HttpServletRequest request,AdminBean admin) {
		try {
		request.setAttribute("adminId", admin.getAdminID());
		parameterObject = ObjectBindUtil.getData(request);
		/* paging logic */
		UserConfiguration config = UserConfiguration
				.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			SimpleMap map = null;
			SimpleMap map1 = null;
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		    //String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			parameterObject.put("qryType", qryType);
			//parameterObject.put("deptid", deptid);
			parameterObject.put("endDate",endDate );
			parameterObject.put("startDate", startDate);
			parameterObject.put("key", key);
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			parameterObject.put("supervisor", admin.getAdminID());
			
			
			
//			if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
//				List driverOtList = eatingCardes.getdriverOtListAll(parameterObject);
//				request.setAttribute("driverOtList", driverOtList);
//				//return "/ga_affirm_bus_arrange_excel.jsp";
//			}else{
				int driverOtInt = eatingCardes.getdriverOtInt(parameterObject);
				List driverOtList = eatingCardes.getdriverOtList(parameterObject,currentPage, pageSize);
				for(int i=0;i<driverOtList.size();i++){
					map = (SimpleMap) driverOtList.get(i);
					String applyId = map.getString("DRIVER_OT_APPLYID");
					parameterObject.set("APPLY_NO", applyId);
					//map.set("applyId", applyId);
					List driverOtAffiirmList = eatingCardes.getDriverOtAffiirmList(parameterObject);
					map.set("driverOtAffiirmList", driverOtAffiirmList);

				}
				request.setAttribute("driverOtList", driverOtList);
				request.setAttribute("driverOtInt", driverOtInt);
//			}
			
			request.setAttribute("qryType", qryType);
			//request.setAttribute("deptid", deptid);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("key", key);
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("driverOtApproval error ", e);
		}
//		if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
//			return "/ga_affirm_driver_ot_excel.jsp";
//		}else{
			return "/ga_affirm_driver_ot.jsp";
//		}
//		return "/ga_affirm_driver_ot.jsp";
	}
	
	public String viewBusArrangeAffirm(HttpServletRequest request,AdminBean admin) {
		try {
	    
		request.setAttribute("adminId", admin.getAdminID());
		parameterObject = ObjectBindUtil.getData(request);
		/* paging logic */
		UserConfiguration config = UserConfiguration
				.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			SimpleMap map = null;
			SimpleMap map1 = null;
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		    //String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			parameterObject.put("qryType", qryType);
			//parameterObject.put("deptid", deptid);
			parameterObject.put("endDate",endDate );
			parameterObject.put("startDate", startDate);
			parameterObject.put("key", key);
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
			parameterObject.put("supervisor", admin.getAdminID());
			
//			if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
//				List busArrangeList = eatingCardes.getBusArrangeListAll(parameterObject);
//				request.setAttribute("busArrangeList", busArrangeList);
//				//return "/ga_affirm_bus_arrange_excel.jsp";
//			}else{
				int busArrangeInt = eatingCardes.getBusArrangeInt(parameterObject);
				List busArrangeList = eatingCardes.getBusArrangeList(parameterObject,currentPage, pageSize);
				for(int i=0;i<busArrangeList.size();i++){
					map = (SimpleMap) busArrangeList.get(i);
					String applyId = map.getString("BUS_ARRANGE_APPLYID");
					parameterObject.set("APPLY_NO", applyId);
					//map.set("applyId", applyId);
					List busArrangeAffiirmList = eatingCardes.getBusArrangeAffiirmList(parameterObject);
					map.set("busArrangeAffiirmList", busArrangeAffiirmList);
					

				}
				request.setAttribute("busArrangeList", busArrangeList);
				request.setAttribute("busArrangeInt", busArrangeInt);
//			}
			
			
		    
			
			request.setAttribute("qryType", qryType);
			//request.setAttribute("deptid", deptid);
			request.setAttribute("endDate", endDate);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("startDate", startDate);
			request.setAttribute("key", key);
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("visiterApproval error ", e);
		}
//		if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
//			return "/ga_affirm_bus_arrange_excel.jsp";
//		}else{
			return "/ga_affirm_bus_arrange.jsp";
//		}
		
	}
	
	public String visiterApprovalOKorNO(HttpServletRequest request,AdminBean admin) {
		this.putToolbarInfo(request);
		parameterObject = ObjectBindUtil.getData(request);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		parameterObject = ObjectBindUtil.getData(request);
		try {
			String applyno[]=request.getParameterValues("applyno");
			String flag=request.getParameter("flag");
			parameterObject.set("flag", flag);
			for(int i=0;i<applyno.length;i++){
				String affirmNo=request.getParameter("affirmNo_"+applyno[i]);			
				String applerId=request.getParameter("applerId_"+applyno[i]);
				String appler_Name = request.getParameter("appler_Name_"+applyno[i]);
				String affirmorIdea = request.getParameter("affirmorIdea_"+applyno[i])!=null?request.getParameter("affirmorIdea_"+applyno[i]):"";
				parameterObject.set("affirmNo", affirmNo);		
				parameterObject.set("applerId",applerId);
				parameterObject.set("affirmorIdea", affirmorIdea);
				parameterObject.set("applyno", applyno[i]);
				es.updateVoitureAffirm(parameterObject);
				if(request.getParameter("flag").equals("2")){
					es.voitureApply(parameterObject);
					String toEmail = es.getapplyemail(parameterObject);
					if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){				
					this.sendVIsiterAffirmMail(
							"车辆申请已被否决",
							appler_Name,						 
							request.getParameter("VISITER_DATE_"+applyno[i]), 
							request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
							request.getParameter("VISITER_END_TIME_"+applyno[i]),
							request.getParameter("VISITER_PEOPLE_NUM_"+applyno[i]), 
							toEmail,
							admin.getCpnyId(),
							request.getParameter("APPLY_ENDDATE_"+applyno[i])
							);
					}
				}else if(request.getParameter("flag").equals("1")){	
					String MAX_AFFIRM_FLAG=request.getParameter("MAX_AFFIRM_FLAG_"+applyno[i]);
					parameterObject.set("affrimlevel", request.getParameter("AFFIRM_LEVEL_"+applyno[i]));
					if(MAX_AFFIRM_FLAG.equals("0")){
						String toEmail = es.getupaffrimemail1(parameterObject);
						if(!toEmail.equals("") && toEmail!=null&& essSysparam.isGaSendMail()){
							this.sendVIsiterAffirmMail(
									"车辆申请 请进行确认",
									appler_Name,						 
									request.getParameter("VISITER_DATE_"+applyno[i]), 
									request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
									request.getParameter("VISITER_END_TIME_"+applyno[i]),
									request.getParameter("VISITER_PEOPLE_NUM_"+applyno[i]), 
									toEmail,
									admin.getCpnyId(),
									request.getParameter("APPLY_ENDDATE_"+applyno[i])
									);
						}
					}else{
						es.voitureApply(parameterObject);
						String toEmail = es.getapplyemail(parameterObject);
						if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){
							this.sendVIsiterAffirmMail(
									"车辆申请 已经通过确认",
									appler_Name,						 
									request.getParameter("VISITER_DATE_"+applyno[i]), 
									request.getParameter("VISITER_COME_TIME_"+applyno[i]), 
									request.getParameter("VISITER_END_TIME_"+applyno[i]),
									request.getParameter("VISITER_PEOPLE_NUM_"+applyno[i]), 
									toEmail,
									admin.getCpnyId(),
									request.getParameter("APPLY_ENDDATE_"+applyno[i])
									);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/ga_affirm_voiture.jsp";
	}
	
	public String driverOtApprovalOKorNO(HttpServletRequest request,AdminBean admin) {
		this.putToolbarInfo(request);
		parameterObject = ObjectBindUtil.getData(request);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		parameterObject = ObjectBindUtil.getData(request);
		try {
			String applyno[]=request.getParameterValues("applyno");
			String flag=request.getParameter("flag");
			parameterObject.set("flag", flag);
			for(int i=0;i<applyno.length;i++){
				String affirmNo=request.getParameter("affirmNo_"+applyno[i]);			
				String applerId=request.getParameter("applerId_"+applyno[i]);
				String appler_Name = request.getParameter("appler_Name_"+applyno[i]);
				String affirmorIdea = request.getParameter("affirmorIdea_"+applyno[i])!=null?request.getParameter("affirmorIdea_"+applyno[i]):"";
				parameterObject.set("affirmNo", affirmNo);		
				parameterObject.set("applerId",applerId);
				parameterObject.set("affirmorIdea", affirmorIdea);
				parameterObject.set("applyno", applyno[i]);
				es.updateDriverOtAffirm(parameterObject);
				if(request.getParameter("flag").equals("2")){
					es.driverOtApply(parameterObject);
					String toEmail = es.getapplyemail(parameterObject);
					
					if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){				
					this.sendDriverOtAffirmMail(
							"司机加班申请 已被否决",
							appler_Name,						 
							request.getParameter("DRIVER_OT_APPLY_STARTDATE_"+applyno[i]), 
							request.getParameter("DRIVER_OT_APPLY_STARTTIME_"+applyno[i]), 
							request.getParameter("DRIVER_OT_APPLY_ENDDATE_"+applyno[i]),
							request.getParameter("DRIVER_OT_APPLY_ENDTIME_"+applyno[i]), 
							toEmail,
							admin.getCpnyId()
							);
					}
				}else if(request.getParameter("flag").equals("1")){	
					String MAX_AFFIRM_FLAG=request.getParameter("MAX_AFFIRM_FLAG_"+applyno[i]);
					parameterObject.set("affrimlevel", request.getParameter("AFFIRM_LEVEL_"+applyno[i]));
					if(MAX_AFFIRM_FLAG.equals("0")){
						String toEmail = es.getupaffrimemaildriverot(parameterObject);
						System.out.print(essSysparam.isGaSendMail()+toEmail);
						if(!toEmail.equals("") && toEmail!=null&& essSysparam.isGaSendMail()){
							this.sendDriverOtAffirmMail(
									"司机加班申请 已被通过",
									appler_Name,						 
									request.getParameter("DRIVER_OT_APPLY_STARTDATE_"+applyno[i]), 
									request.getParameter("DRIVER_OT_APPLY_STARTTIME_"+applyno[i]), 
									request.getParameter("DRIVER_OT_APPLY_ENDDATE_"+applyno[i]),
									request.getParameter("DRIVER_OT_APPLY_ENDTIME_"+applyno[i]), 
									toEmail,
									admin.getCpnyId()
									);
						}
					}else{
						es.driverOtApply(parameterObject);
						String toEmail = es.getapplyemail(parameterObject);
						if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){
							this.sendDriverOtAffirmMail(
									"司机加班申请 已经通过确认",
									appler_Name,						 
									request.getParameter("DRIVER_OT_APPLY_STARTDATE_"+applyno[i]), 
									request.getParameter("DRIVER_OT_APPLY_STARTTIME_"+applyno[i]), 
									request.getParameter("DRIVER_OT_APPLY_ENDDATE_"+applyno[i]),
									request.getParameter("DRIVER_OT_APPLY_ENDTIME_"+applyno[i]), 
									toEmail,
									admin.getCpnyId()
									);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/ga_affirm_driver_ot.jsp";
	}
	
	public String busArrangeApprovalOKorNO(HttpServletRequest request,AdminBean admin) {
		this.putToolbarInfo(request);
		parameterObject = ObjectBindUtil.getData(request);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		parameterObject = ObjectBindUtil.getData(request);
		try {
			String applyno[]=request.getParameterValues("applyno");
			String flag=request.getParameter("flag");
			parameterObject.set("flag", flag);
			for(int i=0;i<applyno.length;i++){
				String affirmNo=request.getParameter("affirmNo_"+applyno[i]);			
				String applerId=request.getParameter("applerId_"+applyno[i]);
				String appler_Name = request.getParameter("appler_Name_"+applyno[i]);
				String affirmorIdea = request.getParameter("affirmorIdea_"+applyno[i])!=null?request.getParameter("affirmorIdea_"+applyno[i]):"";
				parameterObject.set("affirmNo", affirmNo);		
				parameterObject.set("applerId",applerId);
				parameterObject.set("affirmorIdea", affirmorIdea);
				parameterObject.set("applyno", applyno[i]);
				es.updateBusArrangeAffirm(parameterObject);
				if(request.getParameter("flag").equals("2")){
					es.busArrangeApply(parameterObject);
					String toEmail = es.getapplyemail(parameterObject);
					
					if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){				
					this.sendBusArrangeAffirmMail(
							"班车安排申请 已被否决",
							appler_Name,						 
							request.getParameter("BUS_ARRANGE_APPLY_STARTDATE_"+applyno[i]), 
							request.getParameter("BUS_ARRANGE_APPLY_STARTTIME_"+applyno[i]), 
							request.getParameter("BUS_ARRANGE_ARRIVE_DATE_"+applyno[i]),
							request.getParameter("BUS_ARRANGE_ARRIVE_TIME_"+applyno[i]), 
							toEmail,
							admin.getCpnyId()
							);
					}
				}else if(request.getParameter("flag").equals("1")){	
					String MAX_AFFIRM_FLAG=request.getParameter("MAX_AFFIRM_FLAG_"+applyno[i]);
					parameterObject.set("affrimlevel", request.getParameter("AFFIRM_LEVEL_"+applyno[i]));
					if(MAX_AFFIRM_FLAG.equals("0")){
						String toEmail = es.getupaffrimemaildriverot(parameterObject);
						System.out.print(essSysparam.isGaSendMail()+toEmail);
						if(!toEmail.equals("") && toEmail!=null&& essSysparam.isGaSendMail()){
							this.sendBusArrangeAffirmMail(
									"班车安排申请 已被通过",
									appler_Name,						 
									request.getParameter("BUS_ARRANGE_APPLY_STARTDATE_"+applyno[i]), 
									request.getParameter("BUS_ARRANGE_APPLY_STARTTIME_"+applyno[i]), 
									request.getParameter("BUS_ARRANGE_ARRIVE_DATE_"+applyno[i]),
									request.getParameter("BUS_ARRANGE_ARRIVE_TIME_"+applyno[i]), 
									toEmail,
									admin.getCpnyId()
									);
						}
					}else{
						es.busArrangeApply(parameterObject);
						String toEmail = es.getapplyemail(parameterObject);
						if(!toEmail.equals("") && toEmail!=null && essSysparam.isGaSendMail()){
							this.sendBusArrangeAffirmMail(
									"班车安排申请 已经通过确认",
									appler_Name,						 
									request.getParameter("BUS_ARRANGE_APPLY_STARTDATE_"+applyno[i]), 
									request.getParameter("BUS_ARRANGE_APPLY_STARTTIME_"+applyno[i]), 
									request.getParameter("BUS_ARRANGE_ARRIVE_DATE_"+applyno[i]),
									request.getParameter("BUS_ARRANGE_ARRIVE_TIME_"+applyno[i]), 
									toEmail,
									admin.getCpnyId()
									);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/ga_affirm_bus_arrange.jsp";
	}
	
	private void sendDriverOtAffirmMail(
			String title,
			String applerId,
            String driverOtStartDate,
			String driverOtStartTime,
			String driverOtEndDate,
			String driverOtEndTime,
			String email,
			String cpny_id
			) throws Exception {

			SimpleMap inputData = new SimpleMap();
			
			inputData.set("申请人(司机)：", applerId);
			inputData.set("加班开始日期：", driverOtStartDate);
			inputData.set("加班开始时间：", driverOtStartTime);
			inputData.set("加班结束日期：", driverOtEndDate);
			inputData.set("加班结束时间：", driverOtEndTime);
			
			mail.gaSendMail(inputData, cpny_id, email, title);

	}
	
	private void sendBusArrangeAffirmMail(
			String title,
			String applerId,
            String driverOtStartDate,
			String driverOtStartTime,
			String driverOtEndDate,
			String driverOtEndTime,
			String email,
			String cpny_id
			) throws Exception {

			SimpleMap inputData = new SimpleMap();
			
			inputData.set("代请人：", applerId);
			inputData.set("开始日期：", driverOtStartDate);
			inputData.set("开始时间：", driverOtStartTime);
			inputData.set("到达日期：", driverOtEndDate);
			inputData.set("到达时间：", driverOtEndTime);
			
			mail.gaSendMail(inputData, cpny_id, email, title);

	}
	
	private void sendVIsiterAffirmMail(
			String title,
			String applerId,
            String visiterComeDate,
			String visiterComeTime,
			String visiterEndTime,
			String peopleNum,
			String email,
			String cpny_id,
			String APPLY_ENDDATE) throws Exception {

			SimpleMap inputData = new SimpleMap();
			
			inputData.set("申请人：", applerId);
//			inputData.set("开始日期：", visiterComeDate);
//			inputData.set("开始时间：", visiterComeTime);
//			inputData.set("结束日期：", APPLY_ENDDATE);
//			inputData.set("结束时间：", visiterEndTime);
			inputData.set("用车人数：", peopleNum);
			
			mail.gaSendMail(inputData, cpny_id, email, title);

	}
	public String viewAffirmInfo(HttpServletRequest request,AdminBean admin) {		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		request.setAttribute("adminId", adminId);
		SimpleMap map = null;
		SimpleMap map1 = null;
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
			parameterObject.set("ADMIN_ID", adminId);
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"3";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			parameterObject.put("qryType", qryType);
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);
			
			parameterObject.set("qryType",qryType);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("ADMIN_ID", "");
			parameterObject.set("applerId", admin.getAdminID());
			
			}
		}
			
			int voitureListInt = es.getVoitureListInt(parameterObject);
			List voitureListList = es.getVoitureListList(parameterObject, currentPage, pageSize);
			
			for(int i=0 ; i<voitureListList.size() ; i++){
				map = (SimpleMap) voitureListList.get(i);
				String applyId = map.getString("VOITURE_APPLYID");
				request.setAttribute("applyId", applyId);
				parameterObject.set("applyId", applyId);
				List allvoitureListList = es.allvoitureApproval(parameterObject);
				List voitureDetailList = es.getDistinctionList(parameterObject);
				List car_name = es.getCar_name(parameterObject);
				map.set("voitureDetailList", voitureDetailList);
				String carStr = "";
				String VOITURE_BRAND = "";
				String VOITURE_NUMBER = "";
				String DRIVER_PHONE = "";
				List carList = new ArrayList();
				for(int z=0;z<car_name.size();z++){
					SimpleMap map2 = new SimpleMap();
					map2 = (SimpleMap)car_name.get(z);
					if(map2.getString("DRIVER")!=null){
						carStr +=map2.getString("DRIVER")+",";
					}
					VOITURE_BRAND += map2.getString("VOITURE_BRAND")+",";
					VOITURE_NUMBER += map2.getString("VOITURE_NUMBER")+",";
					DRIVER_PHONE += map2.getString("DRIVER_PHONE")+",";
				}
				if(carStr != ""&& carStr != null){
					map.set("carStr", carStr.substring(0,carStr.length()-1));
				}
				if(VOITURE_BRAND != ""&& VOITURE_BRAND != null){
					map.set("VOITURE_BRAND", VOITURE_BRAND.substring(0,VOITURE_BRAND.length()-1));
				}
				if(VOITURE_NUMBER != ""&& VOITURE_NUMBER != null){
					map.set("VOITURE_NUMBER", VOITURE_NUMBER.substring(0,VOITURE_NUMBER.length()-1));
				}
				if(DRIVER_PHONE != ""&& DRIVER_PHONE != null){
					map.set("DRIVER_PHONE", DRIVER_PHONE.substring(0,DRIVER_PHONE.length()-1));
				}
				for(int j=0 ; j<allvoitureListList.size() ; j++){
					map1 = (SimpleMap) allvoitureListList.get(j);
					
					String AFFIRMORIDEA = map1.getString("AFFIRMORIDEA");
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					if(affirmFlag.equals("0")){
						map.set("AFFIRM_FLAG", "0");
						map.set("allvoitureListList", allvoitureListList);
						break;
					}else{
						map.set("AFFIRM_FLAG", "1");
						map.set("allvoitureListList", allvoitureListList);
						break;
					}
				}
				
			}
			
			request.setAttribute("qryType", qryType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("adminID", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			
			request.setAttribute("voitureListList", voitureListList);
			request.setAttribute("voitureInt", NumberUtil
					.parseInt(voitureListInt));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("voitureApproval error ", e);
			}
			return "/ga_affirm_info.jsp?menu_code="
					+ parameterObject.getString("menu_code");
		
	}
	public String excelViewAffirmInfo(HttpServletRequest request,AdminBean admin) {		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getAdminID();
		request.setAttribute("adminId", adminId);
		SimpleMap map = null;
		SimpleMap map1 = null;
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
			parameterObject.set("ADMIN_ID", adminId);
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"3";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			parameterObject.put("qryType", qryType);
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("cpnyId", cpnyId);
			
			parameterObject.set("qryType",qryType);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
					b = true;
					parameterObject.set("ADMIN_ID", "");
					parameterObject.set("applerId", admin.getAdminID());
					
				}
			}
			
			int voitureListInt = es.getVoitureListInt(parameterObject);
			List voitureListList = es.getVoitureListList(parameterObject, currentPage, pageSize);
			
			for(int i=0 ; i<voitureListList.size() ; i++){
				map = (SimpleMap) voitureListList.get(i);
				String applyId = map.getString("VOITURE_APPLYID");
				request.setAttribute("applyId", applyId);
				parameterObject.set("applyId", applyId);
				List allvoitureListList = es.allvoitureApproval(parameterObject);
				List voitureDetailList = es.getDistinctionList(parameterObject);
				List car_name = es.getCar_name(parameterObject);
				map.set("voitureDetailList", voitureDetailList);
				String carStr = "";
				String VOITURE_BRAND = "";
				String VOITURE_NUMBER = "";
				String DRIVER_PHONE = "";
				List carList = new ArrayList();
				for(int z=0;z<car_name.size();z++){
					SimpleMap map2 = new SimpleMap();
					map2 = (SimpleMap)car_name.get(z);
					if(map2.getString("DRIVER")!=null){
						carStr +=map2.getString("DRIVER")+",";
					}
					VOITURE_BRAND += map2.getString("VOITURE_BRAND")+",";
					VOITURE_NUMBER += map2.getString("VOITURE_NUMBER")+",";
					DRIVER_PHONE += map2.getString("DRIVER_PHONE")+",";
				}
				if(carStr != ""&& carStr != null){
					map.set("carStr", carStr.substring(0,carStr.length()-1));
				}
				if(VOITURE_BRAND != ""&& VOITURE_BRAND != null){
					map.set("VOITURE_BRAND", VOITURE_BRAND.substring(0,VOITURE_BRAND.length()-1));
				}
				if(VOITURE_NUMBER != ""&& VOITURE_NUMBER != null){
					map.set("VOITURE_NUMBER", VOITURE_NUMBER.substring(0,VOITURE_NUMBER.length()-1));
				}
				if(DRIVER_PHONE != ""&& DRIVER_PHONE != null){
					map.set("DRIVER_PHONE", DRIVER_PHONE.substring(0,DRIVER_PHONE.length()-1));
				}
				for(int j=0 ; j<allvoitureListList.size() ; j++){
					map1 = (SimpleMap) allvoitureListList.get(j);
					
					String AFFIRMORIDEA = map1.getString("AFFIRMORIDEA");
					map.set("AFFIRMORIDEA", AFFIRMORIDEA);
					String affirmFlag = map1.getString("AFFIRM_FLAG");
					String affirmor_id = map1.getString("AFFIRMOR_ID");
					if(affirmFlag.equals("0")){
						map.set("AFFIRM_FLAG", "0");
						map.set("allvoitureListList", allvoitureListList);
						break;
					}else{
						map.set("AFFIRM_FLAG", "1");
						map.set("allvoitureListList", allvoitureListList);
						break;
					}
				}
				
			}
			
			request.setAttribute("qryType", qryType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("adminID", admin.getAdminID());
			request.setAttribute("cpnyId", cpnyId);
			
			request.setAttribute("voitureListList", voitureListList);
			request.setAttribute("voitureInt", NumberUtil
					.parseInt(voitureListInt));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("voitureApproval error ", e);
		}
		return "/ga_affirm_info_excel.jsp?menu_code="
		+ parameterObject.getString("menu_code");
		
	}
	
	public String viewAffirmDriverOtInfo(HttpServletRequest request,AdminBean admin) {		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		request.setAttribute("adminId", adminId);
		SimpleMap map = null;
		SimpleMap map1 = null;
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
			parameterObject.set("ADMIN_ID", adminId);
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			//String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			String driverOtType = request.getParameter("driverOtType")!=null?request.getParameter("driverOtType"):"";
			String OTTypeCode = request.getParameter("OTTypeCode")!=null?request.getParameter("OTTypeCode"):"";
			parameterObject.put("qryType", qryType);
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			//parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("driverOtType", driverOtType);
			parameterObject.put("OTTypeCode", OTTypeCode);
			
			parameterObject.set("qryType",qryType);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("ADMIN_ID", "");
			parameterObject.set("applerId", admin.getAdminID());
			}
		}
			if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
				List driverOtList = eatingCardes.getdriverOtListAll(parameterObject);
				request.setAttribute("driverOtList", driverOtList);
				//return "/ga_affirm_bus_arrange_excel.jsp";
			}else{
				int driverOtListInt = es.getDriverOtListInt(parameterObject);
				List driverOtListList = es.getDriverOtListList(parameterObject, currentPage, pageSize);
				
				request.setAttribute("driverOtListList", driverOtListList);
				request.setAttribute("driverOtListInt", NumberUtil
						.parseInt(driverOtListInt));
				System.out.println(driverOtListInt);
				
				for(int i=0 ; i<driverOtListList.size() ; i++){
					map = (SimpleMap) driverOtListList.get(i);
					String applyId = map.getString("DRIVER_OT_APPLYID");
					request.setAttribute("applyId", applyId);
					parameterObject.set("applyId", applyId);
					List allDriverOtListList = es.allDriverOtApproval(parameterObject);
					map.set("allDriverOtListList", allDriverOtListList);
					
					
				}
			}
			
			
			request.setAttribute("qryType", qryType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			//request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("driverOtType", driverOtType);
			request.setAttribute("OTTypeCode", OTTypeCode);
			request.setAttribute("adminID", admin.getAdminID());
			
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("driverOtList error ", e);
			}
			
			if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
				return "/ga_affirm_driver_ot_excel.jsp";
			}else{
				return "/ga_affirm_driver_ot_info.jsp?menu_code="
				+ parameterObject.getString("menu_code");
			}
			
		
	}
	
	public String viewAffirmBusArrangeInfo(HttpServletRequest request,AdminBean admin) {		
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		request.setAttribute("adminId", adminId);
		SimpleMap map = null;
		SimpleMap map1 = null;
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
			parameterObject.set("ADMIN_ID", adminId);
			String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
			String arriveType = request.getParameter("arriveType")!=null?request.getParameter("arriveType"):"";
			String startDate = request.getParameter("startDate")!=null?request.getParameter("startDate"):"";
			String endDate = request.getParameter("endDate")!=null?request.getParameter("endDate"):"";
			//String deptid = request.getParameter("deptid")!=null?request.getParameter("deptid"):"";
			String key = request.getParameter("key")!=null?request.getParameter("key"):"";
			String busAreaType = request.getParameter("busAreaType")!=null?request.getParameter("busAreaType"):"";
			String busTypeCode = request.getParameter("busTypeCode")!=null?request.getParameter("busTypeCode"):"";
			parameterObject.put("qryType", qryType);
			parameterObject.put("startDate", startDate);
			parameterObject.put("endDate", endDate);
			//parameterObject.put("deptid", deptid);
			parameterObject.put("key", key);
			parameterObject.put("busAreaType", busAreaType);
			parameterObject.put("busTypeCode", busTypeCode);
			parameterObject.set("arriveType",arriveType);
			parameterObject.set("qryType",qryType);
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("ADMIN_ID", "");
			parameterObject.set("applerId", admin.getAdminID());
			}
		}
			if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
				List busArrangeList = eatingCardes.getbusArrangeListAll(parameterObject);
				request.setAttribute("busArrangeList", busArrangeList);
				
			}else{
				int busArrangeListInt = es.getBusArrangeListInt(parameterObject);
				List busArrangeListList = es.getBusArrangeListList(parameterObject, currentPage, pageSize);
				
				request.setAttribute("busArrangeListList", busArrangeListList);
				request.setAttribute("busArrangeListInt", NumberUtil
						.parseInt(busArrangeListInt));
				
				for(int i=0 ; i<busArrangeListList.size() ; i++){
					map = (SimpleMap) busArrangeListList.get(i);
					String applyId = map.getString("BUS_ARRANGE_APPLYID");
					request.setAttribute("applyId", applyId);
					parameterObject.set("applyId", applyId);
					List allBusArrangeListList = es.allBusArrangeApproval(parameterObject);
					map.set("allBusArrangeListList", allBusArrangeListList);
					
					
				}
			}
			
			
			request.setAttribute("qryType", qryType);
			request.setAttribute("arriveType", arriveType);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			//request.setAttribute("deptid", deptid);
			request.setAttribute("key", key);
			request.setAttribute("busAreaType", busAreaType);
			request.setAttribute("busTypeCode", busTypeCode);
			request.setAttribute("adminID", admin.getAdminID());
			
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("adminId", adminId);
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("driverOtList error ", e);
			}
			
			if("excel".equals(request.getParameter("excelFlag"))||"excel"==request.getParameter("excelFlag")){
				return "/ga_affirm_bus_arrange_excel.jsp";
			}else{
				return "/ga_affirm_bus_arrange_info.jsp?menu_code="
				+ parameterObject.getString("menu_code");
			}
			
		
	}
	
	public String delvoiture(HttpServletRequest request,AdminBean admin) {
		parameterObject = ObjectBindUtil.getData(request);
		try{
			parameterObject.put("applyNo", request.getParameter("ga_voiture_apply_id"));
			es.delvoiture(parameterObject);
			request.setAttribute("errorMsg", "派车申请删除成功！");
	} catch (Exception e) {
		request.setAttribute("errorMsg", "派车申请删除失败！");
		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("voitureApproval error ", e);
	}
		return "/ga_affirm_info.jsp?menu_code="
		+ parameterObject.getString("menu_code");
	}
	public String getEateryTypeName(String eateryType,AdminBean admin) {
		/*String eateryTypeStr[]=eateryType.split(",");
		String InPameter="";
		for(int i =0;i<eateryTypeStr.length;i++){
			InPameter =InPameter+"'"+eateryTypeStr+"',";
			
		}*/
		String returnStr="";
		SimpleMap  sm = new SimpleMap();
		sm.set("eateryType", eateryType);
		sm.set("cpnyId", admin.getCpnyId());
		try{
			List  result = eatingCardes.getEateryTypeName(sm);
			for(int i=0;i<result.size();i++){
				SimpleMap simpleMap =(SimpleMap)result.get(i);
				returnStr +=simpleMap.getString("GM_TYPE")+"，";
				
			}
	 if(returnStr.equals("")){
		 returnStr="无，";
	 }
			
	} catch (Exception e) {
		
		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getEateryTypeName error ", e);
	}
		return returnStr.substring(0,returnStr.length()-1);
	}
	public String getPermissionsTypeName(String PERMISSIONS_TYPE,AdminBean admin) {
		/*String eateryTypeStr[]=eateryType.split(",");
		String InPameter="";
		for(int i =0;i<eateryTypeStr.length;i++){
			InPameter =InPameter+"'"+eateryTypeStr+"',";
			
		}*/
		String returnStr="";
		SimpleMap  sm = new SimpleMap();
		sm.set("PERMISSIONS_TYPE", PERMISSIONS_TYPE);
		sm.set("cpnyId", admin.getCpnyId());
		try{
			List  result = eatingCardes.getTempCardTypeName(sm);
			for(int i=0;i<result.size();i++){
				SimpleMap simpleMap =(SimpleMap)result.get(i);
				returnStr +=simpleMap.getString("GM_TYPE")+"，";
				
			}
	 if(returnStr.equals("")){
		 returnStr="无，";
	 }
			
	} catch (Exception e) {
		
		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getEateryTypeName error ", e);
	}
		return returnStr.substring(0,returnStr.length()-1);
	}
}
