package com.ait.safe.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import com.ait.mail.Mail;
import com.ait.safe.business.SecurityChecksServices;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.FormUtil;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-21
 * 
 */
public class SecurityChecksCommand extends ArAbstractCommand {
	
	private SecurityChecksServices scServices =null;
	
	private Mail mail ;
	
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	public SecurityChecksCommand(){
		scServices = new SecurityChecksServices();
		
		mail = new Mail() ;
	}

	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		String returnPage=null;
		if(!content.equals("") && content.equals("securityChecksView")){
			this.getSecurityChecksList(request, admin);
			returnPage="/safe_securityChecks_view.jsp";
		}else if(!content.equals("") && content.equals("securityChecksForExcel")){
			this.securityChecksForExcel(request, admin);
			returnPage="/reports/safe_report/securityChecks.jsp";
		}else if(!content.equals("") && content.equals("securityChecksForTotalExcel")){
			this.securityChecksForTotalExcel(request, admin);
			returnPage="/reports/safe_report/securityChecksTotalExcel.jsp";
		}else if(!content.equals("") && content.equals("securityChecksaddpage")){
			String documents=FormUtil.getApplyDocumentid("DOCUMENTNO","SE_SECURITYCHECKS_RECORD","EHS");
			this.checkPhoto(request, documents);
			this.update(request, admin);
			this.putToolbarInfo(request);
			request.setAttribute("documentno", documents.toString());
			returnPage="/safe_securityChecks_add.jsp";
		}else if(!content.equals("") && content.equals("addSecurityChecks")){	
			returnPage=this.addSecurityChecks(request,admin);
		//   this.getApplya(request,response);          
		}else if(!content.equals("") && content.equals("ShowDetailsForHIDDENDANGERSCOMMUNICATIONS")){
			returnPage=this.getAnSecurityChecksInformation(request,admin);			
		}else if(!content.equals("") && content.equals("getAnSecurityChecksInformationForUpdate")){
			returnPage=this.getAnSecurityChecksInformationForUpdate(request, admin);
		}else if(!content.equals("") && content.equals("updateSecurityChecks")){
			returnPage=this.updateSecurityChecks(request, admin);
		}else if(!content.equals("") && content.equals("viewCorrectivePlan")){
			returnPage=this.getAnviewCorrectivePlan(request, admin);
		}else if(!content.equals("") && content.equals("viewCorrectivePlan_plan")){
			returnPage=this.getAnviewCorrectivePlan_plan(request, admin);
		}else if(!content.equals("") && content.equals("oingConfirm")){
			returnPage=this.oingConfirm(request, admin);
		}else if(!content.equals("") && content.equals("uploadImp1")){
			this.uploadImp1(request);		
			returnPage="/safe_upload1.jsp?Directive=CloseWindow";
		}else if(!content.equals("") && content.equals("deleteSecurityCheck")){
			returnPage=this.deleteSecurityCheck(request, admin);
		}else if(!content.equals("") && content.equals("viewPhoto")){
			returnPage=this.viewPhoto(request);
		}else if(!content.equals("") && content.equals("deletePhoto")){
			returnPage=this.deletePhoto(request);
		}else if(!content.equals("") && content.equals("view")){
			returnPage=this.view(request,admin);
		}else if(!content.equals("") && content.equals("saveView")){
			this.saveView(request,admin);
			this.putToolbarInfo(request);
			this.getSecurityChecksList(request, admin);
			returnPage="/safe_securityChecks_view.jsp";
		}else if(!content.equals("") && content.equals("tixing")){
			this.tixing(request,admin);
			this.getSecurityChecksList(request, admin);
			returnPage="/safe_securityChecks_view.jsp";
		}else{
			return "error.jsp";
		}
//		
		return returnPage;
	}
	
	/*制作定单号用来插入数据库*/
	private String getApplyWoodProductsDocumentid(String pkcoumeName,String tableName,String flag){
		ResultSet result = null;
		String doucmentno1="";		
		int doucmentno=0;
		Connection conn = ConnBean.getConn();
		Date d = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd");
		PreparedStatement pstmt = null;
		String sql = "select max(substr("+pkcoumeName+",9)) as document_no  from se_securitychecks_record where substr(documentno, 3, 6) = '"+timeFormatter.format(d).substring(2, 8)+"'";
			//String aa="select max(substr("+pkcoumeName+",9)) as document_no  from "+tableName+" where substr("+pkcoumeName+",0,8)="+Integer.parseInt(timeFormatter.format(d));
		Logger.getLogger(getClass()).debug(sql);
		
		try {
		
			pstmt = conn.prepareStatement(sql);
			result=pstmt.executeQuery();
			if(result.next() && (result.getInt("document_no")!=0)){
				if(result.getInt("document_no")<10){
					doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+"0"+(result.getInt("document_no")+1)).toString();
				}else{
					doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+(result.getInt("document_no")+1)).toString();
				}
				
					//Integer.parseInt(timeFormatter.format(d)+(result.getInt("document_no")+1));
			}else{
				
				doucmentno1=(flag+timeFormatter.format(d).substring(2,8)+"01").toString();
			}
			
		
		} catch (Exception e) {
	
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(result, pstmt, conn);
		}
		
		return doucmentno1;
		
	}
/*增加安全检查记录*/
	public String  addSecurityChecks(HttpServletRequest request,AdminBean admin){
		this.putToolbarInfo(request);
		int result = 0;	//做为整体提交的表示符号
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String dd = request.getParameter("deptname");
		String[] ss = dd.split(",");
		String DOCUMENTNO = FormUtil.getApplyDocumentid("DOCUMENTNO","SE_SECURITYCHECKS_RECORD","EHS");
		try {
			 for(int i=0;i<ss.length;i++){
				 String sql = " insert into SE_SECURITYCHECKS_RECORD(DOCUMENTNO,SECURITYCHECKSOR_ID,SECURITYCHECKSDATE,DEPTID,LOCATION,HIDDENDANGERSCOMMUNICATIONS,RECTIFICATIONREQUIREMENTS,RECTIFICATION_DATE,MAILEMPIDLIST ,ISRECTIFICATION,APPLORID ,APPLYDATE,BRIEF, ZHENGGAINUM, cpny_id,HIDDENDANGERSTYPE) " +
			 		" values(?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,to_date(?,'yyyy-mm-dd'),?,'0',?,SYSDATE,?,?,?,?)";
			 
					Logger.getLogger(getClass()).debug(sql);
					ps = conn.prepareStatement(sql);
					
					if(i==0){
						ps.setString(1, request.getParameter("No_number"));
					}else{
						ResultSet result1 = null;
						ps.setString(1, DOCUMENTNO);
//						FILE_OR_PHOTO_PATH
						PreparedStatement pstmt = null;
						String fileStr = "select * from FILE_OR_PHOTO_PATH t where t.documentno=?";
						pstmt = conn.prepareStatement(fileStr);
						pstmt.setString(1, request.getParameter("No_number"));
						result1 = pstmt.executeQuery();
						//多个部门时，给每个部门分发图片
						while(result1.next()){
							String updateStr = "insert into FILE_OR_PHOTO_PATH (DOCUMENTNO,CHINESENAME,PATHADDRESS,FILENAME) values(?,?,?,?)";
							PreparedStatement pstmt1 = null;
							pstmt1 = conn.prepareStatement(updateStr);
							pstmt1.setString(1, DOCUMENTNO);
							pstmt1.setString(2, result1.getString("CHINESENAME"));
							pstmt1.setString(3, result1.getString("PATHADDRESS"));
							pstmt1.setString(4, result1.getString("FILENAME"));
							pstmt1.executeUpdate();
						}
					}
					
					ps.setString(2, admin.getAdminID());
					ps.setString(3, request.getParameter("check_date"));
					ps.setString(4, ss[i]);
					ps.setString(5, request.getParameter("Location"));
					ps.setString(6, request.getParameter("HiddenDangersCommunications1"));
					ps.setString(7, request.getParameter("Rectification_requirements1"));
					ps.setString(8, request.getParameter("Rectification_date"));
					ps.setString(9, request.getParameter("person_id").substring(0,request.getParameter("person_id").length()-1));
					ps.setString(10, admin.getAdminID());		
					ps.setString(11, request.getParameter("brief"));
					ps.setString(12, request.getParameter("0"));
					ps.setString(13, admin.getCpnyId());
					ps.setString(14, request.getParameter("HiddenDangersType1"));
			        ps.executeUpdate();
			        
			 }
			 String EmailEmpidList=request.getParameter("person_id");
		  	  String[]EmpidArr=EmailEmpidList.split(",");
		  	  for(int s=0; s<EmpidArr.length; s++){
		  		  String toEmail=this.getEmail(EmpidArr[s].trim());
		  		  this.sendSecurityChecksMail(toEmail,request.getParameter("check_date"),request.getParameter("Rectification_date"),admin.getChineseName(),DOCUMENTNO,admin.getCpnyId());
		  	  }
	 
		} catch (Exception e) {
			request.setAttribute("errorMsg", "添加检查记录失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}finally {
			SqlUtil.close(ps, conn);
		}
		this.getSecurityChecksList(request, admin);
		request.setAttribute("errorMsg", "添加检查记录成功！");
		return "/safe_securityChecks_view.jsp?menu_code="+ request.getParameter("menu_code");
	}
	
	
	
	/*修改安全检查记录，同时插入BLOB对象*/
	public String  updateSecurityChecks(HttpServletRequest request,AdminBean admin){
		int result = 0;	
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		try {	
	        String sql = " update SE_SECURITYCHECKS_RECORD  set SECURITYCHECKSOR_ID=?, " +
	      		" SECURITYCHECKSDATE=to_date(?,'yyyy-mm-dd'),DEPTID=?,LOCATION=?,HIDDENDANGERSCOMMUNICATIONS=?," +
	      		" RECTIFICATIONREQUIREMENTS=?,RECTIFICATION_DATE=to_date(?,'yyyy-mm-dd'),MAILEMPIDLIST=?,brief=?,mail_num=?,HIDDENDANGERSTYPE=? where  DOCUMENTNO=? " ;
			Logger.getLogger(getClass()).debug(sql);
			ps = conn.prepareStatement(sql);			
			ps.setString(1, admin.getAdminID());
			ps.setString(2, request.getParameter("check_date"));
			ps.setString(3, request.getParameter("deptid"));
			ps.setString(4,request.getParameter("Location"));
			ps.setString(5, request.getParameter("HiddenDangersCommunications1"));
			ps.setString(6, request.getParameter("Rectification_requirements1"));
			ps.setString(7, request.getParameter("Rectification_date"));
			ps.setString(8, request.getParameter("person_id").substring(0,request.getParameter("person_id").length()-1));			
			ps.setString(9, request.getParameter("brief"));
			ps.setString(10, "0");
			ps.setString(11, request.getParameter("HiddenDangersType1"));
			ps.setString(12, request.getParameter("No_number"));
			
	        ps.executeUpdate();
	        //ps.close(); 
	        //conn.close();
		} catch (Exception e) {
			request.setAttribute("errorMsg", "修改检查记录失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}finally {
			SqlUtil.close(ps, conn);
		}		
			String EmailEmpidList=request.getParameter("person_id");
			String[]EmpidArr=EmailEmpidList.split(",");
			for(int i=0; i<EmpidArr.length; i++){
				  String toEmail=this.getEmail(EmpidArr[i].trim());
				  this.sendSecurityChecksMail(toEmail,request.getParameter("check_date"),request.getParameter("Rectification_date"),admin.getChineseName(),request.getParameter("No_number"),admin.getCpnyId());
			  }
			request.setAttribute("errorMsg", "修改检查记录成功！");
		
		this.getSecurityChecksList(request, admin);
		return "/safe_securityChecks_view.jsp";
	}	
	/*得到所有的安全检查记录*/
	 public String getSecurityChecksList(HttpServletRequest request,AdminBean admin){	  
		   try{
			   this.putToolbarInfo(request);
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				parameterObject.set("cpnyId", admin.getCpnyId());	
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
				
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				String status_code = "";
				String falg = request.getParameter("falg");
				if(falg != null && falg.equals("1")){
					
				}else{
					status_code = request.getParameter("status_code")!=null?request.getParameter("status_code"):"2";
					parameterObject.set("status_code", status_code);
				}
				
		       //取得数据行数
				int resultCount = scServices.getSecurityChecksListNumber(parameterObject);
				List securityChecksList=scServices.getSecurityChecksList(parameterObject,currentPage,pageSize);
				
				request.setAttribute("serchDept", request.getParameter("serchDept"));
				request.setAttribute("documentno", request.getParameter("documentno"));
				request.setAttribute("EndDate", request.getParameter("EndDate"));
				request.setAttribute("StartDate", request.getParameter("StartDate"));
				request.setAttribute("HiddenDangersCommunications", request.getParameter("HiddenDangersCommunications"));
				request.setAttribute("HiddenDangersType", request.getParameter("HiddenDangersType"));
				request.setAttribute("Rectification_requirements", request.getParameter("Rectification_requirements"));
				request.setAttribute("status_code", status_code);
				
				request.setAttribute("securityChecksList",securityChecksList);
				request.setAttribute("resultCount", resultCount);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("pageGroupsize", pageGroupSize);
				if(securityChecksList==null ||securityChecksList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  getConferenceRoomConfirmList Exception. ", e);
			}		 
		 return "/safe_securityChecks_view.jsp";
		   
	   }
	 
	 public String securityChecksForExcel(HttpServletRequest request,AdminBean admin){	  
		   try{
				
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				parameterObject.set("cpnyId", admin.getCpnyId());	
//				int pageSize =10;
//				int pageGroupSize =10;
//				int currentPage = 0;
//			
//				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
//					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		       //取得数据行数
				//int resultCount = scServices.getSecurityChecksListNumber(parameterObject);
				List securityChecksList=scServices.getSecurityChecksList(parameterObject);
				
				request.setAttribute("serchDept", request.getParameter("serchDept"));
				request.setAttribute("documentno", request.getParameter("documentno"));
				request.setAttribute("EndDate", request.getParameter("EndDate"));
				request.setAttribute("StartDate", request.getParameter("StartDate"));
				request.setAttribute("HiddenDangersCommunications", request.getParameter("HiddenDangersCommunications"));
				request.setAttribute("HiddenDangersType", request.getParameter("HiddenDangersType"));
				request.setAttribute("Rectification_requirements", request.getParameter("Rectification_requirements"));
				request.setAttribute("status_code", request.getParameter("status_code"));
				
				request.setAttribute("securityChecksList",securityChecksList);
//				request.setAttribute("resultCount", resultCount);
//				request.setAttribute("currentPage", currentPage);
//				request.setAttribute("pageSize", pageSize);
//				request.setAttribute("pageGroupsize", pageGroupSize);
				if(securityChecksList==null ||securityChecksList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  securityChecksForExcel Exception. ", e);
			}		 
			return "/reports/safe_report/securityChecks.jsp";
		   
	   }	
	 
	 public String securityChecksForTotalExcel(HttpServletRequest request,AdminBean admin){	  
		   try{
				
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				parameterObject.set("CPNY_ID", admin.getCpnyId());	

				List securityChecksTotalExcelList=scServices.getSecurityChecksTotalExcelList(parameterObject);
				
//				request.setAttribute("serchDept", request.getParameter("serchDept"));
//				request.setAttribute("documentno", request.getParameter("documentno"));
//				request.setAttribute("EndDate", request.getParameter("safeEndDate"));
//				request.setAttribute("StartDate", request.getParameter("safeStartDate"));
//				request.setAttribute("HiddenDangersCommunications", request.getParameter("HiddenDangersCommunications"));
//				request.setAttribute("Rectification_requirements", request.getParameter("Rectification_requirements"));
//				request.setAttribute("status_code", request.getParameter("status_code"));
				
				request.setAttribute("securityChecksTotalExcelList",securityChecksTotalExcelList);

				if(securityChecksTotalExcelList==null ||securityChecksTotalExcelList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  securityChecksForExcel Exception. ", e);
			}		 
			return "/reports/safe_report/securityChecksTotalExcel.jsp";
		   
	   }	
	 
	 /*得到一个安全记录的详情查看*/
	 public String getAnSecurityChecksInformation(HttpServletRequest request,AdminBean admin){	  
		   try{
				
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());			
				List securityChecksList=scServices.getAnSecurityChecksInformation(parameterObject);
				request.setAttribute("securityChecksList",securityChecksList);
			
				if(securityChecksList==null ||securityChecksList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}/*else{
					List securityList=scServices.getAnSecurityInformation(parameterObject);
					request.setAttribute("securityList",securityList);
				}*/
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get pu  getConferenceRoomConfirmList Exception. ", e);
			}		 
		 return "/safe_securityChecks_showDials.jsp";
		   
	   }
	 /*得到该人的Email*/
	 public String getEmail(String empid){
		 String email="";
		   try{
				
				SimpleMap parameterObject = new SimpleMap();				
				parameterObject.set("empid", empid);					
				email=scServices.getEmail(parameterObject);				
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get se  getEmail Exception. ", e);
				
			}	
		   return email;
	   }
	 
	 /*得到一个安全记录的详情查看*/
	 public String getAnSecurityChecksInformationForUpdate(HttpServletRequest request,AdminBean admin){	  
		   try{
			   this.putToolbarInfo(request);
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				int pageSize =10;
				int pageGroupSize =10;
				int currentPage = 0;
			
				if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
		   
				
				List securityChecksList=scServices.getAnSecurityChecksInformation(parameterObject);
				SimpleMap sm= new SimpleMap();
				String emplist="";
				for(int i=0;i<securityChecksList.size();i++){
					sm=(SimpleMap)securityChecksList.get(i);
					emplist=sm.get("MAILEMPIDLIST").toString();
				}
				request.setAttribute("selectEmpidLStr",emplist);
				String emplistarrry[]=emplist.split(",");
				String selectEmpidLStr="";
				for(int s=0;s<emplistarrry.length;s++){
					selectEmpidLStr +="'"+emplistarrry[s]+"',";
				}
				parameterObject.set("selectEmpidLStr", selectEmpidLStr.substring(0,selectEmpidLStr.length()-1));
				List selectEmpidList=scServices.selectEmpidLStr(parameterObject);
				request.setAttribute("selectEmpidList",selectEmpidList);
				request.setAttribute("securityChecksList",securityChecksList);
			
				if(securityChecksList==null ||securityChecksList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get safe  getAnSecurityChecksInformationForUpdate Exception. ", e);
			}		 
		 return "/safe_securityChecks_update.jsp";
		   
	   }
	 /*得到一个整改计划申请的详情查看*/
	 public String getAnviewCorrectivePlan(HttpServletRequest request,AdminBean admin){	  
		   try{
				
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
                List AnviewCorrectivePlanList=scServices.getAnviewCorrectivePlan(parameterObject);
				request.setAttribute("AnviewCorrectivePlanList",AnviewCorrectivePlanList);
			
				if(AnviewCorrectivePlanList==null ||AnviewCorrectivePlanList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get safe  AnviewCorrectivePlanList Exception. ", e);
			}		 
		 return "/safe_correctivePlan_showDials.jsp";
		   
	   }
	 
	 public String getAnviewCorrectivePlan_plan(HttpServletRequest request,AdminBean admin){	  
		   try{
				
				SimpleMap parameterObject = null;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
              List AnviewCorrectivePlanList=scServices.getAnviewCorrectivePlan_plan(parameterObject);
				request.setAttribute("AnviewCorrectivePlanList",AnviewCorrectivePlanList);
			
				if(AnviewCorrectivePlanList==null ||AnviewCorrectivePlanList.size()==0){
				 request.setAttribute("errorMsg", "暂无相关信息！");
				}
			} catch (Exception e) {

				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("get safe  AnviewCorrectivePlanList Exception. ", e);
			}		 
		 return "/safe_correctivePlan_plan_showDials.jsp";
		   
	   }
	 
	 /*对已经整改完成的安全检查记录进行确认*/
	 public String oingConfirm(HttpServletRequest request,AdminBean admin){	 
		 boolean  temp  =  false ; 
		   try{
				
				SimpleMap parameterObject = null;
				//SimpleMap securityChecks = new SimpleMap() ;
				
				parameterObject = ObjectBindUtil.getData(request);
				parameterObject.set("applerId", admin.getAdminID());
				parameterObject.set("deptId", admin.getDeptID());	
				parameterObject.set("documentno", parameterObject.getString("securityChecksNo"));
				String affirmorIdea = (String)parameterObject.get("affirmorIdea_"+parameterObject.getString("securityChecksNo"));
				parameterObject.set("affirmorIdea",affirmorIdea);
				parameterObject.get("affirmorIdea_"+parameterObject.getString("securityChecksNo"));
				// securityChecks = (SimpleMap)scServices.getSecurityChecks(parameterObject);
				parameterObject.set("falg", request.getParameter("falg"));
				String DOCUMENTNO = FormUtil.getApplyDocumentid("DOCUMENTNO","SE_SECURITYCHECKS_RECORD","EHS");
				List list=scServices.oingConfirm(parameterObject,DOCUMENTNO,request.getParameter("falg"));
				if(request.getParameter("falg").equals("2")){
					SimpleMap simpleMap = new SimpleMap();
					simpleMap = (SimpleMap)list.get(0);
					String EmailEmpidList = simpleMap.getString("MAILEMPIDLIST");
					String[]EmpidArr=EmailEmpidList.split(",");
				  	  for(int s=0; s<EmpidArr.length; s++){
				  		  String toEmail=this.getEmail(EmpidArr[s]);
				  		  this.sendSecurityChecksMail(toEmail,simpleMap.getString("check_date"),simpleMap.getString("Rectification_date"),admin.getChineseName(),DOCUMENTNO,admin.getCpnyId());
				  	  }
				}
				temp = true;
                //sendSecurityChecksConfirmMail(securityChecks) ;
			
			} catch (Exception e) {
                   temp = false ;
				Logger.getLogger(getClass()).error(e.toString());
				throw new GlRuntimeException("oingConfirm Exception. ", e);
			}	
			if(!temp){
				 request.setAttribute("errorMsg", "确认失败！");
			}
		 return this.getSecurityChecksList(request, admin);
		   
	   }
	 
	
	 // 安全检查确认,发邮件
		private void sendSecurityChecksConfirmMail(SimpleMap securityChecks) {

			try
			{
				SimpleMap inputData = new SimpleMap();
				
				StringBuffer content = new StringBuffer();
		
				content.append(" 申请人：").append(securityChecks.getString("")).append("<br><br>").append(" 主题：").append("安全检查记录");
		
				content.append("<br><br>").append(" 计划完成日期：").append(securityChecks.getString(""));
		
				content.append("<br><br>").append(" 整改计划：").append(securityChecks.getString(""));
		
				content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行确认</a>").append("<br><br>斗山工程机械(中国)有限公司");
		
				// set email title
				inputData.setString("EMAIL_TITLE", securityChecks.getString("") + "," + "安全检查记录");
		
				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
		
				// for(int i=0;i<result.size();i++){
				inputData.setString("RCVR_EMAIL_ADDR", securityChecks.getString(""));
		
				mail.sendMail(inputData);
				// }
				// essServices.insertOtAffirmMail(inputData);
			}
			catch(Exception e)
			{
				Logger.getLogger(getClass()).error(e.toString());
				e.printStackTrace() ;
			}
			
		}
		 // 成功添加安全检查记录给通知人,发邮件
		private void sendSecurityChecksMail(String toEmail,String checkDate,String requestOverDate,String checkEmpName,String checkDocumentno,String cpny_id) {

			try
			{
				SimpleMap inputData = new SimpleMap();
				
				inputData.set("检查人：", checkEmpName);
				inputData.set("检查日期：", checkDate);
				inputData.set("要求完成日期：", requestOverDate);
				inputData.set("检查编号：", checkDocumentno);
				
				mail.gaSendMail(inputData, cpny_id, toEmail, "安全检查记录");
			}
			catch(Exception e)
			{
				Logger.getLogger(getClass()).error(e.toString());
				e.printStackTrace() ;
			}
			
		}
		
		 /*删除已经定义的安全检查记录*/
	public String deleteSecurityCheck(HttpServletRequest request,AdminBean admin){	 
			 boolean  temp  =  false ; 
			   try{
					
					SimpleMap parameterObject = null;
					//SimpleMap securityChecks = new SimpleMap() ;
					
					parameterObject = ObjectBindUtil.getData(request);
					parameterObject.set("applerId", admin.getAdminID());
					parameterObject.set("deptId", admin.getDeptID());	
					parameterObject.set("documentno", parameterObject.getString("securityChecksNo"));
					
					 //securityChecks = (SimpleMap)scServices.getSecurityChecks(parameterObject);
					
	                temp=scServices.deleteSecurityCheck(parameterObject);
					
	               // sendSecurityChecksConfirmMail(securityChecks) ;
				
				} catch (Exception e) {
					request.setAttribute("errorMsg", "删除失败！");
					Logger.getLogger(getClass()).error(e.toString());
					throw new GlRuntimeException("oingConfirm Exception. ", e);
				}
				request.setAttribute("errorMsg", "删除成功！");
			 return this.getSecurityChecksList(request, admin);
			   
	}
	 /*上传图片*/
	 public void uploadImp1(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql="insert into FILE_OR_PHOTO_PATH(DOCUMENTNO,CHINESENAME,PATHADDRESS,filename) values (?,?,?,?)";
		DiskFileUpload fu = new DiskFileUpload();
		if(request.getParameter("jpgUpload")!=null && request.getParameter("jpgUpload")!=""){
		String documentno=	request.getParameter("documentno");		 
			 List fileItems = null;
			    try {
					fileItems = fu.parseRequest(request);				
				Iterator iter = null;
				if(fileItems!=null){
					iter = fileItems.iterator();
					 while (iter.hasNext()) {						
						    FileItem item = (FileItem)iter.next();
						    if (!item.isFormField() && !item.getName().equals("")){
						    	ServletContext application = request.getSession().getServletContext();
						    	String filepath = "/upload/safe"; 						    	
						    	String path = application.getRealPath(filepath);
						    	File file = null;						    	
						    	int start = item.getName().lastIndexOf("\\"); 
						    	String name =item.getName().substring(start+1);						    	
						    	file = new File(path); 
						    	/**如果文件已经存在，则删除原有文件，上传新文件覆盖原有文件。*/ 
						    	/*if(file.exists()){ 
						    	file.delete(); 
						    	}else{ 
						    	file = new File("/upload/safe/",documentno+item.getName()); 
						    	*//**如果文件已经存在，则删除原有文件，上传新文件覆盖原有文件。*//* 						    	
						    	} */
						    	int seqid=this.getSequence("FILE_OR_PHOTO_PATH_SEQ");
						    	File objectfile= new File(path+"\\"+documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	if(!file.exists()){
						    		file.mkdir();
					       		}
						    	ps = conn.prepareStatement(sql);
						    	ps.setString(1,documentno);
						    	ps.setString(2,name.replaceAll("\\s*", ""));
						    	ps.setString(3,"../upload/safe/"+documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	ps.setString(4,documentno+seqid+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	ps.executeUpdate();						    
								item.write(objectfile);
								
						    }
					 }
				}
			    } catch (Exception e) {				
					e.printStackTrace();
				}finally {
					SqlUtil.close(ps, conn);
				}
			request.setAttribute("errorMsg", "修改检查记录失败！");
		
	 }
		
	}
	 
	 /*查看 图片*/
	 public String  viewPhoto(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "select * from FILE_OR_PHOTO_PATH t where t.documentno=?";
		List result = new ArrayList();
		ResultSet rs = null;
		SimpleMap sm = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, request.getParameter("documentno"));
			rs = ps.executeQuery();
			while (rs.next()) {
				sm = new SimpleMap();
				sm.set("viewfilename",rs.getString("chinesename"));
				sm.set("filename", rs.getString("filename"));
				sm.set("fileaddress",rs.getString("pathaddress"));
				sm.set("documentno",rs.getString("documentno"));
				result.add(sm);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(rs,ps,conn);
		}
		request.setAttribute("resultsize", result.size());
		request.setAttribute("result", result);
		 String flag=(String) request.getParameter("flag");
		if(flag!=null && flag.equals("view")){
		return "/safe_view_photo_onlyview.jsp"; 
		 }		 	
		return "/safe_view_photo.jsp";
	}
	 
	 /*查看 图片*/
	 public void  checkPhoto(HttpServletRequest request,String documentno){	
		 Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "delete  from FILE_OR_PHOTO_PATH t where t.documentno=? ";
		File myDir = null;
		File[] contents = null;
		Iterator currentFileView = null;
		File currentFile = null;
		ServletContext application = request.getSession().getServletContext();
		String filepath = "/upload/safe";
		try {
		String path = application.getRealPath(filepath);
		Vector vectorList = new Vector();
		myDir = new File(path);
		contents = myDir.listFiles();
		if(contents != null){
			for (int i = 0; i < contents.length; i++) {
				vectorList.add(contents[i]);
			}
			currentFileView = vectorList.iterator();
			while (currentFileView.hasNext()) {
				currentFile = (File) currentFileView.next();
				if (currentFile.getName().startsWith(documentno)) {// 如果文件名是以这个编号开始的，那么就删除它，为了防止重复
					File f = new File(path + "\\" + currentFile.getName());
					f.delete();
				}
			}
			    ps = conn.prepareStatement(sql);
				ps.setString(1,documentno);			
				ps.executeUpdate();
		}
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(ps, conn);
		}

	}
	 /* 删除 图片 */
	 public String  deletePhoto(HttpServletRequest request){
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "delete  from FILE_OR_PHOTO_PATH t where t.documentno=? and t.filename=?";
		File myDir = null;
		String fillname = request.getParameter("filename");
		try {
		ServletContext application = request.getSession().getServletContext();
		String filepath = "/upload/safe";
		String path = application.getRealPath(filepath);
		myDir = new File(path + "\\" + fillname);
		myDir.delete();
		ps = conn.prepareStatement(sql);
		ps.setString(1, request.getParameter("documentno"));
		ps.setString(2, request.getParameter("filename"));		
		ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(ps, conn);
		}
		this.viewPhoto(request);
		return "/safe_view_photo.jsp";
	}
	 /*得到序列号*/
	private int getSequence(String seqName) {
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
	 public String  view(HttpServletRequest request,AdminBean admin){
			String flag = request.getParameter("flag");
			String DOCUMENTNO = request.getParameter("DOCUMENTNO");
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.put("DOCUMENTNO", DOCUMENTNO);
			if(flag!=null&flag.equals("1")){
				List securityChecksList=scServices.getQueryView(parameterObject);
				SimpleMap simpleMap = new SimpleMap();
				simpleMap = (SimpleMap)securityChecksList.get(0);
				request.setAttribute("ZHENGGAIPLANTEXT", simpleMap.get("ZHENGGAIPLANTEXT"));
				request.setAttribute("DOCUMENTNO", simpleMap.get("DOCUMENTNO"));
				request.setAttribute("PLANCOMPLETIONDATE", simpleMap.get("PLANCOMPLETIONDATE"));
				return "/safe_security_view.jsp";
			}else if(flag!=null&flag.equals("2")){
				List securityChecksList=scServices.getQueryView(parameterObject);
				SimpleMap simpleMap = new SimpleMap();
				simpleMap = (SimpleMap)securityChecksList.get(0);
				request.setAttribute("ZHENGGAIPLANTEXT", simpleMap.get("ZHENGGAIPLANTEXT"));
				request.setAttribute("DOCUMENTNO", simpleMap.get("DOCUMENTNO"));
				request.setAttribute("PLANCOMPLETIONDATE", simpleMap.get("PLANCOMPLETIONDATE"));
				return "/safe_security_view1.jsp";
			}
			else{
				request.setAttribute("DOCUMENTNO", DOCUMENTNO);
				return "/safe_security_view.jsp";
			}
			
		}
	 
	 public String  saveView(HttpServletRequest request,AdminBean admin){
			SimpleMap parameterObject = null;
			
			parameterObject = ObjectBindUtil.getData(request);
			scServices.updateView(parameterObject);
			return "/safe_security_view.jsp";
			
		}
	 public String  tixing(HttpServletRequest request,AdminBean admin){
		 Connection conn =null;
		 Connection conn1 = null;
		 PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			ResultSet rs = null;
			SimpleMap parameterObject = null;
			try {
			parameterObject = ObjectBindUtil.getData(request);
//			String securityChecksNo = 
			conn = ConnBean.getConn();
			conn1 = ConnBean.getConn();
			String sql = "select MAILEMPIDLIST,mail_num from se_securitychecks_record t where t.DOCUMENTNO=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, parameterObject.getString("securityChecksNo"));
			rs = ps.executeQuery();
			String MAILEMPIDLIST = "";
			while(rs.next()){
				MAILEMPIDLIST = rs.getString("MAILEMPIDLIST");
			}
				String[] MAILEMPIDLIST1 = MAILEMPIDLIST.split(",");
				for(int i=0; i<MAILEMPIDLIST1.length; i++){
					  String toEmail=this.getEmail(MAILEMPIDLIST1[i].trim());
					  this.sendSecurityChecksMail(toEmail,request.getParameter("check_date"),request.getParameter("Rectification_date"),admin.getChineseName(),parameterObject.getString("securityChecksNo"),admin.getCpnyId());
					  String upSql = "update se_securitychecks_record t set t.mail_num = ? WHERE T.DOCUMENTNO = ?";
					  ps1 = conn1.prepareStatement(upSql);
					  int mail = parameterObject.getInt("mail_num");
					  ps1.setInt(1, mail+1);
					  ps1.setString(2, parameterObject.getString("securityChecksNo"));
					  ps1.executeUpdate();
				}
				request.setAttribute("errorMsg", "邮件发送成功！");
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "邮件发送失败！");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				SqlUtil.close(rs, ps, conn);
				SqlUtil.close(ps1, conn1);
			}
//			String EmailEmpidList=request.getParameter("person_id");
//			String[]EmpidArr=EmailEmpidList.split(",");
//			for(int i=0; i<EmpidArr.length; i++){
//				  String toEmail=this.getEmail(EmpidArr[i].trim());
//				  this.sendSecurityChecksMail(toEmail,request.getParameter("check_date"),request.getParameter("Rectification_date"),admin.getChineseName(),request.getParameter("No_number"),admin.getCpnyId());
//			  }
			return "/safe_security_view.jsp";
			
		}
	 
	 public void  update(HttpServletRequest request,AdminBean admin){
		 Connection conn = ConnBean.getConn();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = " select * from se_securitychecks_record t where t.cpny_id=? and t.cpny_id=<>'63000000' order by t.documentno desc";
		 try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getCpnyId());
			rs = ps.executeQuery();
			if(rs.next()){
				String documentno = rs.getString("MAILEMPIDLIST");
				
				request.setAttribute("selectEmpidLStr",documentno);
				String emplistarrry[]=documentno.split(",");
				String selectEmpidLStr="";
				for(int s=0;s<emplistarrry.length;s++){
					selectEmpidLStr +="'"+emplistarrry[s].trim()+"',";
				}
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("selectEmpidLStr", selectEmpidLStr.substring(0,selectEmpidLStr.length()-1));
				List selectEmpidList=scServices.selectEmpidLStr(parameterObject);
				request.setAttribute("selectEmpidList",selectEmpidList);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlUtil.close(rs,ps, conn);
		}
		 
	 }
}
