package com.ait.ga.cmd.visit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.WoodProductsServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class WpAffirm extends ArAbstractCommand {

	private WoodProductsServices wpServices=null;
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private Mail mail = new Mail() ;
	
	private EssSysparam essSysparam = null;
	
	public WpAffirm(){
		wpServices= new WoodProductsServices();		
	}
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		String adminid=admin.getAdminID();
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		if(content.equals("sealproductionAffirm") && content!=null){			
			return this.getWoodProductsAffirm(request, admin);			
		}else if(content.equals("batchAffirm") && content!=null){
			this.batchAffirm(request);
			return this.getWoodProductsAffirm(request, admin);		
		}else if(content.equals("sealproductionAffirmInfo") && content!=null){
			return this.getWoodProductsAffirmInfo(request, admin);			
		}else if(content.equals("deleteApply") && content!=null){
			this.deleteApply(request.getParameter("applyNo"));
			return this.getWoodProductsAffirmInfo(request, admin);			
		}{
			return "error.jsp";
		}
	}   
	
	/*根据登陆者得到决裁的信息*/
	public String getWoodProductsAffirm(HttpServletRequest request,AdminBean admin){
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
		
       //取得数据行数
		int resultCount = wpServices.getWoodProductsAffirmListNumber(parameterObject);
		List WoodProductsAffirmList=wpServices.getWoodProductsAffirmList(parameterObject,currentPage,pageSize);
		for(int i=0;i<WoodProductsAffirmList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)WoodProductsAffirmList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=wpServices.getWoodProductsAffirmorList(parameterObject1);
			List  woodProductsList=wpServices.getWoodProductsList(parameterObject1);
			dataMap.set("affirmorList", affirmorList);
			dataMap.set("woodProductsList", woodProductsList);
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("cpnyId", request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId());
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		request.setAttribute("qryType", qryType);
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
	    request.setAttribute("WoodProductsAffirmList", WoodProductsAffirmList);
	    if(WoodProductsAffirmList==null || WoodProductsAffirmList.size()==0){
	    	   request.setAttribute("errorMsg", "没有您相关的决裁信息！");	
	    }
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("WoodProductsAffirmList happens Exception. ", e);
	}     
		return "/ga_production_affirm.jsp";
	
  }
	public void batchAffirm(HttpServletRequest request) {
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");	
		
		try {		
		String countsArr[]=request.getParameterValues("counts");
		for(int i=0;i<countsArr.length;i++){

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("applyNo", request.getParameter("applyNo_"+countsArr[i]));
		parameterObject.set("chineseName", request.getParameter("chineseName_"+countsArr[i]));
		parameterObject.set("affirmNo", request.getParameter("affirmNo_"+countsArr[i]));
		parameterObject.set("affirmFlag", request.getParameter("affirmFlag"));
		parameterObject.set("maxAffirmFlag", request.getParameter("maxAffirmFlag_"+countsArr[i]));
		parameterObject.set("affirmorIdea", request.getParameter("affirmorIdea_"+countsArr[i]));
		parameterObject.set("hopeDate", request.getParameter("hopeDate_"+countsArr[i]));
		parameterObject.set("adminId", admin.getAdminID());
		parameterObject.set("applyorEmail", request.getParameter("applyorEmail_"+countsArr[i]));
		parameterObject.set("applyDate", request.getParameter("applyDate_"+countsArr[i]));			
		parameterObject.set("upEmail",wpServices.getupaffrimemail(parameterObject));
		
		wpServices.oingAffirm(parameterObject);
		this.oingAffirm(parameterObject,admin);	
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
    	   dataMap.set("主题", "刻章申请");
    	   dataMap.set("申请人", parameterObject.getString("chineseName")); 
    	   dataMap.set("希望完工日期", parameterObject.getString("hopeDate"));    	   
    	   dataMap.set("申请时间", parameterObject.getString("applyDate"));    
    	   
    	   
    	   String defaultSysFile = "/system.properties";
	   	   UserConfiguration userConfig = UserConfiguration.getInstance(defaultSysFile);
	   	   String emailNameDicc = userConfig.getString("emaile.product.dicc").trim();
			
			if(parameterObject.getString("affirmFlag").equals("2")){
				wpServices.updateApplyInfo(parameterObject);//否决后给申请者发邮件
				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("") && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "刻章申请 已被否决");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("1")){
				wpServices.updateApplyInfo(parameterObject);//最大级决裁者通过时，给申请者发邮件				
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "刻章申请 已通过确认");
				}
				
				if(emailNameDicc !=null && !emailNameDicc.isEmpty()  && admin.getCpnyId().equals("78000000")){
			    	String emailName1[] = emailNameDicc.split(",");
				    for(int i=0;i<emailName1.length;i++){
				    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
					    	parameterObject.set("applerId", emailName1[i]);	
					    	String toEmail1 = wpServices.getapplyemail2(parameterObject);
					    	mail.gaSendMail(dataMap, admin.getCpnyId(), toEmail1, "刻章申请 已通过决裁");
					    }
					}
			    }
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("0")) {				
				if(parameterObject.getString("upEmail")!=null && ! parameterObject.getString("upEmail").equals("")  && essSysparam.isGaSendMail()){					
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("upEmail"), "刻章申请 请您确认");
				}
			}			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} 		
		
		
		
	}
	/*根据登陆者得到决裁情况的信息*/
	public String getWoodProductsAffirmInfo(HttpServletRequest request,AdminBean admin){
	try{
		
		SimpleMap parameterObject = null;
		String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
		parameterObject = ObjectBindUtil.getData(request);
		parameterObject.set("applerId", admin.getAdminID());
		parameterObject.set("deptId", admin.getDeptID());	
		parameterObject.set("cpnyId", cpnyId);
		String qryType = request.getParameter("qryType")!=null?request.getParameter("qryType"):"0";
		parameterObject.set("qryType", qryType);
		parameterObject.set("confirmFlag", "0");
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
		parameterObject.set("applerId", "");
		parameterObject.set("ADMIN_ID", admin.getAdminID());
		}
	}
		
       //取得数据行数
		int resultCount = wpServices.getWoodProductsAffirmInfoListNumber(parameterObject);
		List WoodProductsAffirmInfoList=wpServices.getWoodProductsAffirmInfoList(parameterObject,currentPage,pageSize);
		for(int i=0;i<WoodProductsAffirmInfoList.size();i++){
			SimpleMap parameterObject1 =  new SimpleMap();
			dataMap=(SimpleMap)WoodProductsAffirmInfoList.get(i);			
			parameterObject1.set("applyNo", dataMap.get("APPLY_NO"));
			List affirmorList=wpServices.getWoodProductsAffirmorList(parameterObject1);
			List  woodProductsList=wpServices.getWoodProductsList(parameterObject1);
			dataMap.set("affirmorList", affirmorList);
			dataMap.set("woodProductsList", woodProductsList);
		}
	    request.setAttribute("adminid", admin.getAdminID());
		request.setAttribute("cpnyId", cpnyId);
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		request.setAttribute("qryType", qryType);
		request.setAttribute("deptid", request.getParameter("deptid"));
		request.setAttribute("key", request.getParameter("key"));
		request.setAttribute("resultCount", resultCount);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageGroupsize", pageGroupSize);
		request.setAttribute("loginAdmin", admin.getAdminID());
	    request.setAttribute("WoodProductsAffirmInfoList", WoodProductsAffirmInfoList);	   
	    if(WoodProductsAffirmInfoList==null || WoodProductsAffirmInfoList.size()==0){
	    	   request.setAttribute("errorMsg", "没有您相关的决裁信息！");	
	    }
	} catch (Exception e) {

		Logger.getLogger(getClass()).error(e.toString());
		throw new GlRuntimeException("getWoodProductsAffirmInfo happens Exception. ", e);
	}     
		return "/ga_production_affirmInfo.jsp";
	
  }
	  /*得到符合条件的决裁情况的数据个数*/
	  public int getVoitureAffirmInfoNumber(String adminid){
			
		   int s=0;
		
			Connection conn = ConnBean.getConn();
			Statement stmt = null;
			ResultSet rs = null;
			String sql="select max(rownum) as s from (select distinct t.document_no  from ga_woodproducts_apply t    where t.wp_applyerid = '"+adminid+"'   and t.active = 1   order by t.document_no desc) temp";
			Logger.getLogger(getClass()).debug(sql);
			try {
				stmt = conn.createStatement();		
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					s=rs.getInt("s");
				}
				
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();
			} finally {
				SqlUtil.close(rs, stmt, conn);
			}
			  	return s;
		  
	  }
	  
	  /*得到符合条件的决裁的数据个数*/
	  public int getVoitureAffirmNumber(String adminid){
			
		   int s=0;
		
			Connection conn = ConnBean.getConn();
			Statement stmt = null;
			ResultSet rs = null;
			String sql="select count(*) as s  from ga_woodproducts_affirm affirm  where affirm.activity = 1 and affirm.affirmor_id = '"+adminid+"' and affirm.apply_no in( select ap.document_no from ga_woodproducts_apply ap  where ap.active='1')";
			Logger.getLogger(getClass()).debug(sql);
			try {
				stmt = conn.createStatement();		
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					s=rs.getInt("s");
				}
				
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();
			} finally {
				SqlUtil.close(rs, stmt, conn);
			}			
	     	return s;
		  
	  }
	  /*再没有决裁的情况下，删除该信息*/
	  public void deleteApply(String applyno){		
			
			Connection conn = ConnBean.getConn();
			PreparedStatement applyStmt = null;
			PreparedStatement affirmStmt = null;
			PreparedStatement detailStmt = null;		
			int  rs = 0;
			String applySql="delete from ga_products_apply "+			 
			 " where APPLY_NO='"+applyno+"'";
			String affrimSql="delete from ga_products_affirm "+			 
			 " where APPLY_NO='"+applyno+"'";
			String detailSql="delete from ga_products_detail "+			 
			 " where APPLY_NO='"+applyno+"'";
			Logger.getLogger(getClass()).debug(applySql);
			Logger.getLogger(getClass()).debug(affrimSql);
			Logger.getLogger(getClass()).debug(detailSql);
			try {
				conn.setAutoCommit(false);
				applyStmt = conn.prepareStatement(applySql);	
				rs += applyStmt.executeUpdate();
				affirmStmt = conn.prepareStatement(affrimSql);	
				rs += affirmStmt.executeUpdate();
				detailStmt = conn.prepareStatement(detailSql);	
				rs += detailStmt.executeUpdate();
				conn.commit();
				
				
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();
			} finally {
				try{
				applyStmt.close();
				affirmStmt.close();
				detailStmt.close();
				conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			} 	
			
			
			
		}
	   
	/*更改木刻章信息的决裁状态*/
	public void updateWpAffirm(HttpServletRequest request){
		
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		int rs = 0;
		String i =  request.getParameter("i");
		String sql="update ga_woodproducts_affirm set  affirm_Flag="+request.getParameter("flag")+
                  " where  ga_affirm_no="+request.getParameter("affirmno");
		String sql1="update ga_woodproducts_apply set  reply_reason='"+request.getParameter("reply_reason"+i)+
        "' where  document_no='"+request.getParameter("documentno")+"'";
		String sql12="update ga_woodproducts_apply set  active='"+request.getParameter("flag")+
        "' where  document_no='"+request.getParameter("documentno")+"'";
		Logger.getLogger(getClass()).debug(sql);
		Logger.getLogger(getClass()).debug(sql1);
		try {
			SimpleMap parameterObject = null;
			String date1 = "";
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("applyno", request.getParameter("documentno"));
			parameterObject.set("applerId", request.getParameter("applyorid"));

			
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);
			if(rs!=0){
				stmt.executeUpdate(sql1);
			}
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			date1 = format1.format(Calendar.getInstance().getTime());
			
			if(request.getParameter("flag").equals("2")){
				stmt.executeUpdate(sql12);
				String toEmail = wpServices.getapplyemail(parameterObject);
				if(toEmail!=null && ! toEmail.equals("")){
				sendApplyMail("木刻章申请 已被否决",request.getParameter("applyorid"),request.getParameter("applyorname"),toEmail,request.getParameter("overdate"),request.getParameter("documentno"));
				}
			}else if(request.getParameter("flag").equals("1")){
				String MAX_AFFIRM_FLAG =request.getParameter("MAX_AFFIRM_FLAG");
				parameterObject.set("affrimlevel", request.getParameter("affirmlevel"));				
				if(MAX_AFFIRM_FLAG.equals("0")){
					String toEmail = wpServices.getupaffrimemail(parameterObject);
					if(toEmail!=null && ! toEmail.equals("")){
					sendApplyMail("木刻章申请 请您确认",request.getParameter("applyorid"),request.getParameter("applyorname"),toEmail,request.getParameter("overdate"),request.getParameter("documentno"));
					}
				}else{
					stmt.executeUpdate(sql12);
					String toEmail = wpServices.getapplyemail(parameterObject);
					if(toEmail!=null && ! toEmail.equals("")){
					sendApplyMail("木刻章申请 已通过确认",request.getParameter("applyorid"),request.getParameter("applyorname"),toEmail,request.getParameter("overdate"),request.getParameter("documentno"));
					}
				}
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(stmt, conn);
		}
		
	}
	
	private void sendApplyMail(String title,String adminid,String applyer,String toEmail,String overdate,String doucmentno) throws Exception {
		SimpleMap parameterObject = new SimpleMap();
		
		SimpleMap inputData = new SimpleMap();
		
		parameterObject.set("applerId",adminid);
		parameterObject.set("applyer",applyer);
		//List result = seServices.getapplyemail(parameterObject);

		StringBuffer content = new StringBuffer();
					
		content.append(" 申请人：").append(applyer)
			   .append("<br><br>").append(" 主题：").append(title);
			
			content.append("<br><br>").append(" 希望完工日期：").append(overdate);
			content.append("<br><br>").append(" 刻章制作单编号：").append(doucmentno);

			content.append("<br><br>").append(" 请您及时进行确认！");

			   
		content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行确认</a>")
			   .append("<br><br>斗山工程机械(中国)有限公司") ;
		

		// set email title
		inputData.setString("EMAIL_TITLE", applyer + "," + title);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		//for(int i=0;i<result.size();i++){
			inputData.setString("RCVR_EMAIL_ADDR", toEmail);

			mail.sendMail(inputData) ;
		//}
		//essServices.insertOtAffirmMail(inputData);
	}

}
