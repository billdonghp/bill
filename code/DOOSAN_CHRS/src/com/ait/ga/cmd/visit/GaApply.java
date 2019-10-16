package com.ait.ga.cmd.visit;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.EatingCardServices;
import com.ait.ga.business.GaServices;
import com.ait.ga.business.SecurityEnvironmentServices;
import com.ait.gm.business.EateryServices;
import com.ait.gm.business.WasteServices;
import com.ait.gm.business.WoodProductsServices;
import com.ait.i18n.Message;
import com.ait.mail.Mail;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author yangxiaohui (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 *  
 */
public class GaApply extends ArAbstractCommand {
	
	CommonSQLMapAdapter commonSQLMapAdapter = new CommonSQLMapAdapter();
	
	SimpleMap parameterObject = new SimpleMap();
	
	GaServices gaServices = new GaServices();
	
	GaAffirm gaAffirm = new GaAffirm();
	
	EateryServices es = new EateryServices();
	
	EatingCardServices eatingCardServices = new EatingCardServices();
	
	GaAffirmList gaAffirmList = new GaAffirmList();
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	
	private EssSysparam essSysparam = null;
	
	private String cpnyAllNameForMail="";
	
	private  WoodProductsServices wPServices= new WoodProductsServices();
	
	private Mail mail = new Mail() ;
	  
	AdminBean admin = new AdminBean();
	private SecurityEnvironmentServices seServices=new SecurityEnvironmentServices();
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		HttpSession session = request.getSession(false);
		admin = (AdminBean) session.getAttribute("admin");
		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		
		if(content.equals("voitureApply") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("person_id", admin.getAdminID());
			EateryServices es = new EateryServices();
			List listDeptname = es.getDeptname(simpleMap);
			String deptname = "";
			String deptid = "";
			if(!listDeptname.isEmpty()){
				SimpleMap simpleMa1 = new SimpleMap();
				simpleMa1 = (SimpleMap)listDeptname.get(0);
				deptname = simpleMa1.getString("DEPTNAME");
				deptid = simpleMa1.getString("DEPTID");
			}
			String declaration = "";
			
			if("63000000".equals(admin.getCpnyId())){
				//declaration="(车辆使用请提前一天申请；接飞机请填写到达时间、航班号、人员数、并打印接机名单)";
			}
			request.setAttribute("declaration", declaration);
			request.setAttribute("deptname", deptname);
			request.setAttribute("deptid", deptid);
			return "/ga_apply_voiture.jsp";
			
		}else if(content.equals("sealproductionpply") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			List woodProductsList = wPServices.getWoodProductsSet(simpleMap);
			request.setAttribute("woodProductsList", woodProductsList);
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(admin.getAdminID(), "EngravedChapterApply", essSysparam.isContainsOwner()));
			String declaration = "";
			String areaType = request.getParameter("areaType")!=null? request.getParameter("areaType"):"yantai";
			if("63000000".equals(admin.getCpnyId())){
				declaration="(请提前准备刻章样式，刻章后留存样式并在总务课签字进行确认)";
			}
			request.setAttribute("declaration", declaration);
			request.setAttribute("areaType", areaType);
			return "/ga_sealproduction_apply.jsp";
		}else if(content.equals("addSealproductionApply") && content!=null){
			this.addApplyWoodProducts( request);//做添加操作
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			List woodProductsList = wPServices.getWoodProductsSet(simpleMap);
			String areaType = request.getParameter("areaType")!=null? request.getParameter("areaType"):"yantai";
			request.setAttribute("areaType", areaType);
			request.setAttribute("woodProductsList", woodProductsList);	
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(admin.getAdminID(), "EngravedChapterApply", essSysparam.isContainsOwner()));
			return "/ga_sealproduction_apply.jsp";
			
		}else if(content.equals("cardapplication")){//就餐申请
			this.getEatTypeandCanteensType(request);
			return "/ga_card_application.jsp";
		}else if(content.equals("temporarycardapplication")){//进入临时卡申请页面
			this.getTemporarycardView(request);
			return "/ga_temporarycard_application.jsp";
		}else if(content.equals("checkApply")){//进入支票申请页面
			this.getCheckView(request);
			return "/ga_check_application.jsp";
		}else if(content.equals("getCheckAccount")){//得到支票号
			this.getCheckAccount(request);
			return "/ga_check_application.jsp";
		}else if(content.equals("checkApplyAdd")){//支票申请
			return this.checkApplyAdd(request);
		}else if(content.equals("cardApplicationAdd")){
			return this.cardApplicationAdd(request);			
		}else if(content.equals("tempCardApplicationAdd")){//临时卡页面点击申请
			return this.tempCardApplicationAdd(request);			
		}else if(content.equals("addVoitureApply") && content!=null){
			
			return this.addVoitureApply(request,admin);
			
		}else if(content.equals("addDriverOtApply") && content!=null){
			
			return this.addDriverOtApply(request,admin);
			
		}else if(content.equals("addBusArrangeApply") && content!=null){
			
			return this.addBusArrangeApply(request,admin);
			
		}else if(content.equals("wasteUnitPrice")){
			this.wasteUnitPrice(request);
			return "/gm_wasteUnitprice_affirm.jsp";
		}else if(content.equals("woodProductionApplyPage")){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			List woodProductsList = wPServices.getwoodProductionList(simpleMap);
			request.setAttribute("woodProductsList", woodProductsList);
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(admin.getAdminID(), "WoodProductsApply", essSysparam.isContainsOwner()));
			return "/ga_woodproduction_apply.jsp";
		}else if (content.equals("addWoodProduction")){
			this.addWoodProduction(request);
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("cpnyId", admin.getCpnyId());
			List woodProductsList = wPServices.getwoodProductionList(simpleMap);
			request.setAttribute("woodProductsList", woodProductsList);
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(admin.getAdminID(), "WoodProductsApply", essSysparam.isContainsOwner()));
			return "/ga_woodproduction_apply.jsp";
		}
		else if(content.equals("driverOtApplyPage") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			simpleMap.set("person_id", admin.getAdminID());
			//request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("deductList", NumberUtil
					.getDoubleSerialList(24));
			return "/ga_apply_driver_ot.jsp";
			
		}else if(content.equals("busArrangeApplyPage") && content!=null){
			SimpleMap simpleMap = new SimpleMap();
			request.setAttribute("timeList", DateUtil.getTimeList());
			simpleMap.set("person_id", admin.getAdminID());

			return "/ga_apply_bus_arrange.jsp";
			
		}else{
			return "/error.jsp";
		}
		
	}

	/*得到序列号*/
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

	
	/*添加木制品信息*/
	private int addApplyWoodProducts(HttpServletRequest request)throws ServletException, IOException{
		int result = 0;
		int k=0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;		
		PreparedStatement affrimPstmt = null;
		HashMap<String, String> formParamMap = new HashMap<String, String>();   //存放form中的一般属性		
		
		DiskFileUpload fu = new DiskFileUpload();
		int seqId=this.getSequence("GA_PRODUCTS_APPLY_SEQ");
		List fileItems = null; 	      
		Object object=null;		
		try {		
		conn.setAutoCommit(false);		
		 try {
			fileItems = fu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	   Iterator fileItr = fileItems.iterator();	  
		while (fileItr.hasNext()) {   
           FileItem fileItem =null;              
           // 得到当前文件   
           fileItem = (FileItem) fileItr.next();   
           // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)   
            if (fileItem.isFormField()) {  
            if(!fileItem.getFieldName().equals("numberArray")){
               String formname=fileItem.getFieldName();//获取form中的名字   
               String formcontent = fileItem.getString("UTF-8");	
               formParamMap.put(formname, formcontent);
               //System.out.println("formname！"+formname);
               //System.out.println("formcontent！"+formcontent);
            }
           
            }else{
               String fileName="";
        	   String filePath="";
            	if( !fileItem.getName().equals("")){
              	  ServletContext application = request.getSession().getServletContext();
              	  String path = application.getRealPath("/upload/products");
              	  int start = fileItem.getName().lastIndexOf("\\"); 
  			      String name =fileItem.getName().substring(start+1);	
  			      fileName=name.replaceAll("\\s*", "").substring(0,name.replaceAll("\\s*", "").length()-4);
  			      filePath=".."+"/upload/products"+"/"+(seqId+""+k)+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length());
  			      File objectfile= new File(path+"\\"+(seqId+""+k)+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
  			    try {
  					fileItem.write(objectfile);
  				} catch (Exception e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}  				
  				System.out.println("上传文件！");
              	}else{
              		System.out.println("没有上传文件！");
              	}
            	this.insertProductsDetail(formParamMap, seqId, conn, fileName, filePath,formParamMap.get("productsType"),k);
            	++k;
           	}   
           
		}
              
		String sql = " INSERT INTO GA_PRODUCTS_APPLY (APPLY_NO,WP_APPLYERID, APPLY_DATE,HOPE_COMPLETED_DATE,ACTIVE,NOTE,CONFIRM_FLAG,APPLY_TYPE,CPNY_ID)" +
				" values(?,?,SYSDATE,to_date(?,'yyyy-mm-dd'),'0',?,'0',?,?)";       
		
		String affirmSql = "INSERT INTO GA_PRODUCTS_AFFIRM (GA_AFFIRM_NO, APPLY_NO, AFFIRM_FLAG, CREATE_DATE, CREATED_BY, AFFIRM_LEVEL,AFFIRMOR_ID,ACTIVITY, APPLY_TYPE) " + "VALUES " + "(GA_WOODPRODUCTS_AFFIRM_SEQ.NEXTVAL, ?, 0,  SYSDATE, ?,?,?,1, ?)";
		Logger.getLogger(getClass()).debug(sql);
		
		Logger.getLogger(getClass()).debug(affirmSql);		
//		List affirmorList=gaAffirm.getAffirmor(formParamMap.get("APPLYER_ID"),"EngravedChapterApply", essSysparam.isContainsOwner());
		
			pstmt = conn.prepareStatement(sql);//插入申请信息			
			affrimPstmt=conn.prepareStatement(affirmSql);//插入决裁信息
			pstmt.setInt(1, seqId);
			pstmt.setString(2, admin.getAdminID());
			pstmt.setString(3, formParamMap.get("APPLY_DATE"));
			pstmt.setString(4, formParamMap.get("APPLY_PURPOSE"));
			pstmt.setString(5, "Seal");
			pstmt.setString(6, admin.getCpnyId());
			pstmt.executeUpdate();
			
			
//			for(int i=0;i<affirmorList.size();i++){
//				gaAffirmList=(GaAffirmList)affirmorList.get(i);
//				affrimPstmt.setInt(1, seqId);
//				affrimPstmt.setString(2, admin.getAdminID());
//				affrimPstmt.setInt(3, gaAffirmList.getAffirmLevel());
//				affrimPstmt.setString(4, gaAffirmList.getAffirmorId());
//				affrimPstmt.setString(5,"Seal");	
//				affrimPstmt.addBatch();
//			}
						
			String[] affirmorId = (formParamMap.get("affirmorIdArr")).toString().split(",");	
			
			for(int i=0;i<affirmorId.length;i++){
				affrimPstmt.setInt(1, seqId);
				affrimPstmt.setString(2, admin.getAdminID());
				affrimPstmt.setInt(3, i+1);
				affrimPstmt.setString(4, affirmorId[i]);
				affrimPstmt.setString(5, "Seal");
				affrimPstmt.addBatch();
			}
			
			 affrimPstmt.executeBatch();			
			  conn.commit();
				request.setAttribute("errorMsg", "已申请成功！");
//				for(int s=0; s<affirmorList.size();s++){					
//				gaAffirmList=(GaAffirmList)affirmorList.get(s);
//				if(gaAffirmList.getAffirmLevel() == 1){
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
//		    	parameterObject.set("applerId", gaAffirmList.getAffirmorId());	
		    	parameterObject.set("applerId", affirmorId[0]);		    	
			    String toEmail = seServices.getapplyemail(parameterObject);
			    SimpleMap dataMap = new SimpleMap();			    
			    dataMap.set("申请人", admin.getChineseName());
			    dataMap.set("希望完工日期", request.getParameter("APPLY_DATE"));
			    mail.gaSendMail(dataMap, admin.getCpnyId(), toEmail, "刻章申请 请您确认");
//				}
//			  }			
				
		} catch (Exception e) {
			try {
				conn.rollback();				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "申请失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			try {
			pstmt.close();
		
			affrimPstmt.close();
			conn.close();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
		
	}
		
	public String insertProductsDetail(HashMap<String, String> formParamMap,int Applyno,Connection conn,String fileName,String filePath,String applyType,int i){
		PreparedStatement detailPstmt = null;
		String detailSql=" INSERT INTO GA_PRODUCTS_DETAIL(APPLY_NO,WPID,WPNAME,WPNUMBER,WP_SUMPRICE,WP_TYPE,SEAL_TYPE,FILENAME,FILEPATH) VALUES(?,?,?,?,?,?,?,?,?) ";
		Logger.getLogger(getClass()).debug(detailSql);
		try{
		detailPstmt = conn.prepareStatement(detailSql);//插入明细信息
		String numberArray[] =(formParamMap.get("numberArrayArr")).toString().split(",");			
			detailPstmt.setInt(1, Applyno);
			detailPstmt.setInt(2,NumberUtil.parseInt(formParamMap.get("woodProductsid"+numberArray[i])));
			detailPstmt.setString(3, formParamMap.get("woodProductsname"+numberArray[i]));
			detailPstmt.setInt(4,NumberUtil.parseInt(formParamMap.get("num"+numberArray[i])));
			
			if (null==formParamMap.get("sumPrice"+numberArray[i])){
				detailPstmt.setDouble(5,Double.parseDouble("0"));	
			}else{
				detailPstmt.setDouble(5,Double.parseDouble(formParamMap.get("sumPrice"+numberArray[i])));	
			}
			
			detailPstmt.setString(6, "Seal");
			detailPstmt.setString(7, formParamMap.get("sealType"+numberArray[i]));
			detailPstmt.setString(8, fileName);
			detailPstmt.setString(9, filePath);
			detailPstmt.addBatch();
		
		detailPstmt.executeBatch();
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
			try {
				detailPstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   
	   }
		return cpnyAllNameForMail;
		   
	}
		
	/*添加就餐卡申请信息*/
	public String  cardApplicationAdd(HttpServletRequest request){
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();	
		String deptid = ((AdminBean) request.getSession(false).getAttribute("admin")).getDeptID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleMap parmeterObject = new SimpleMap();
		String eatType[] =request.getParameterValues("eatType");
		String eateryName =request.getParameter("eateryName");
		String eatTypeStr="";
		for(int i=0; i<eatType.length;i++){
			eatTypeStr +=eatType[i]+",";
		}
		parmeterObject.set("eatType", eatTypeStr.substring(0, eatTypeStr.length()-1));
		parmeterObject.set("EmployeeType", request.getParameter("EmployeeType"));
		parmeterObject.set("foodType", request.getParameter("foodType"));
		parmeterObject.set("deptid", deptid);
		parmeterObject.set("cpnyId", cpnyId);
		parmeterObject.set("adminId", adminId);
		parmeterObject.set("starttime", request.getParameter("starttime"));
		parmeterObject.set("endtime", request.getParameter("endtime"));
		parmeterObject.set("peoples", request.getParameter("peoples"));
		parmeterObject.set("TJSX", request.getParameter("TJSX"));
		if(request.getParameter("EmployeeType").equals("external")){
			parmeterObject.set("used", request.getParameter("used"));
		}else{
			parmeterObject.set("used", request.getParameter("personId"));
		}
		parmeterObject.set("jobtitle", request.getParameter("jobtitle"));
		parmeterObject.set("company", request.getParameter("company"));
		parmeterObject.set("Remarks", request.getParameter("Remarks"));
		try{			
//			List affirmorList=gaAffirm.getAffirmor(adminId, "CARD_APPLICATION", essSysparam.isContainsOwner());
			
			List affirmorList = new ArrayList();
			String[] affirmorId = request.getParameterValues("affirmorId");
			for(int i=0;i<affirmorId.length;i++){
					GaAffirmList vb = new GaAffirmList();
					vb.setAffirmLevel(i+1);
					vb.setAffirmorId(affirmorId[i]);
					affirmorList.add(vb);
			}
     		boolean flag = eatingCardServices.AddCardApplication(parmeterObject, affirmorList);
     	if(flag == true){
			if(essSysparam.isGaSendMail()){
				SimpleMap dataMap = new SimpleMap();
				dataMap.set("申请人", admin.getChineseName());
				dataMap.set("申请时间", timeFormatter.format(new Date()));
				dataMap.set("使用人", request.getParameter("used"));
				dataMap.set("公司单位", request.getParameter("company"));
				dataMap.set("使用人数", request.getParameter("peoples"));
				dataMap.set("餐饮类别", eateryName.substring(0, eateryName.length()-1));
				dataMap.set("开始日期", request.getParameter("startime"));
				dataMap.set("结束日期", request.getParameter("endtime"));
				gaAffirmList = (GaAffirmList)affirmorList.get(0);
				if(gaAffirmList.getAffirmLevel()==1){
					SimpleMap parameterObject = null;
					parameterObject = ObjectBindUtil.getData(request);
			    	parameterObject.set("applerId", gaAffirmList.getAffirmorId());		    	
				    String toEmail = seServices.getapplyemail(parameterObject);
				    String defaultSysFile = "/system.properties";
				    UserConfiguration userConfig = UserConfiguration.getInstance(defaultSysFile);
				    String emailNameDisd = userConfig.getString("emaile.eat.disd").trim();
				    String emailNameDiy = userConfig.getString("emaile.eat.diy").trim();
				    String emailNameDicc = userConfig.getString("emaile.eat.dicc").trim();//就餐申请后处理发送决裁者邮件，还发送给1464456(jihui.jiang@doosan.com)
				    if(emailNameDisd !=null && !emailNameDisd.isEmpty() && admin.getCpnyId().equals("63000000")){
				    	String emailName1[] = emailNameDisd.split(",");
					    for(int i=0;i<emailName1.length;i++){
					    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
						    	parameterObject.set("applerId", emailName1[i]);	
						    	String toEmail1 = seServices.getapplyemail(parameterObject);
						    	mail.gaSendMail(dataMap, cpnyId, toEmail1, "就餐已经申请");
					    	}
					    }
				    }
				    if(emailNameDiy !=null && !emailNameDiy.isEmpty()  && admin.getCpnyId().equals("60000000")){
				    	String emailName1[] = emailNameDiy.split(",");
					    for(int i=0;i<emailName1.length;i++){
					    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
						    	parameterObject.set("applerId", emailName1[i]);	
						    	String toEmail1 = seServices.getapplyemail(parameterObject);
						    	mail.gaSendMail(dataMap, cpnyId, toEmail1, "就餐已经申请");
					    	}
					    }
				    }
				    if(emailNameDicc !=null && !emailNameDicc.isEmpty()  && admin.getCpnyId().equals("78000000")){
				    	String emailName1[] = emailNameDicc.split(",");
					    for(int i=0;i<emailName1.length;i++){
					    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
						    	parameterObject.set("applerId", emailName1[i]);	
						    	String toEmail1 = seServices.getapplyemail(parameterObject);
						    	mail.gaSendMail(dataMap, cpnyId, toEmail1, "就餐已经申请");
					    	}
					    }
				    }
				    mail.gaSendMail(dataMap, cpnyId, toEmail, "就餐申请，请您决裁");
				}			
			}
			request.setAttribute("errorMsg", "申请成功！");
     	}else {
     		request.setAttribute("errorMag", "申请失败");
     	}
	
		} catch (Exception e) {
			request.setAttribute("errorMsg", "申请失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}	
		request.setAttribute("name", admin.getChineseName());
		request.setAttribute("DeptID", admin.getDepartment());		
		List eatTypeList = es.getEatType(parmeterObject);
		request.setAttribute("eatType", eatTypeList);
		request.setAttribute("affirmList", gaAffirm.getAffirmor1(adminId, "CARD_APPLICATION", essSysparam.isContainsOwner()));
		return "/ga_card_application.jsp";
	}
		
	/*添加支票申请信息*/
	public String  checkApplyAdd(HttpServletRequest request){
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();	
		String deptid = ((AdminBean) request.getSession(false).getAttribute("admin")).getDeptID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleMap parmeterObject = new SimpleMap();
		parmeterObject.set("deptid", deptid);
		parmeterObject.set("cpnyId", cpnyId);
		parmeterObject.set("adminId", adminId);
		parmeterObject.set("createDate", request.getParameter("createDate"));//申请日期
		parmeterObject.set("jine", request.getParameter("JINE"));
		parmeterObject.set("bankNameId", request.getParameter("CHECKBANK_TYPE"));
		parmeterObject.set("account", request.getParameter("ACCOUNT"));
		parmeterObject.set("checkType", request.getParameter("CHECK_TYPE"));
		parmeterObject.set("checkAccount", request.getParameter("CHECKACCOUNT"));
		parmeterObject.set("qiAnAccount", request.getParameter("QIANACCOUNT"));
		parmeterObject.set("pzAccount", request.getParameter("PZACCOUNT"));
		parmeterObject.set("pzDate", request.getParameter("PZDATE"));
		parmeterObject.set("skallName", request.getParameter("SKALLNAME"));
		parmeterObject.set("note", request.getParameter("NOTE"));
		try{			
			
//			List affirmorList=gaAffirm.getAffirmor1(adminId, "checkApply", essSysparam.isContainsOwner());
//     		boolean flag = eatingCardServices.AddCheckApply(parmeterObject, affirmorList);
			String[] affirmorId = request.getParameterValues("affirmorId");
			
			boolean flag = eatingCardServices.AddCheckApply(parmeterObject, affirmorId);
     	if(flag == true){
			if(essSysparam.isGaSendMail()){
				SimpleMap dataMap = new SimpleMap();
				dataMap.set("申请人", admin.getChineseName());
				dataMap.set("申请部门", admin.getDepartment());
				dataMap.set("申请时间", timeFormatter.format(new Date()));
//				gaAffirmList = (GaAffirmList)affirmorList.get(0);
//				if(gaAffirmList.getAffirmLevel()==1){
					SimpleMap parameterObject = null;
					parameterObject = ObjectBindUtil.getData(request);
//			    	parameterObject.set("applerId", gaAffirmList.getAffirmorId());		
			    	parameterObject.set("applerId", affirmorId[0]);	    	
				    String toEmail = seServices.getapplyemail(parameterObject);
				    mail.gaSendMail(dataMap, cpnyId, toEmail, "支票申请，请您决裁");
//				}			
			}
			request.setAttribute("errorMsg", "申请成功！");
     	}else {
     		request.setAttribute("errorMag", "申请失败");
     	}
	
		} catch (Exception e) {
			request.setAttribute("errorMsg", "申请失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}	
		request.setAttribute("name", admin.getChineseName());
		request.setAttribute("DeptID", admin.getDepartment());		
		return "/ga_check_application.jsp";
	}
		
	/*添加临时卡申请信息*/
	public String  tempCardApplicationAdd(HttpServletRequest request){
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();	
		String deptid = ((AdminBean) request.getSession(false).getAttribute("admin")).getDeptID();
		String cpnyId = ((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleMap parmeterObject = new SimpleMap();
		String permissionsType[] =request.getParameterValues("permissionsType");
		String permissionsName =request.getParameter("permissionsName");
		String permissionsTypeStr="";
		int eatYn ;
		String eatA = "9";//中餐
		String eatB = "10";//韩餐
		for(int i=0; i<permissionsType.length;i++){
			permissionsTypeStr +=permissionsType[i]+",";
		}
		eatYn = permissionsTypeStr.indexOf(eatA) + permissionsTypeStr.indexOf(eatB);
		
		parmeterObject.set("permissionsType", permissionsTypeStr.substring(0, permissionsTypeStr.length()-1));
		parmeterObject.set("EmployeeType", request.getParameter("EmployeeType"));
		parmeterObject.set("deptid", deptid);
		parmeterObject.set("cpnyId", cpnyId);
		parmeterObject.set("adminId", adminId);
		parmeterObject.set("starttime", request.getParameter("starttime"));
		parmeterObject.set("endtime", request.getParameter("endtime"));
		parmeterObject.set("username", request.getParameter("username"));
		parmeterObject.set("userbusiness", request.getParameter("userbusiness"));
		try{			
//			List affirmorList=gaAffirm.getAffirmor(adminId, "temporarycardApply", essSysparam.isContainsOwner());
			String[] affirmorId = request.getParameterValues("affirmorId");
			
//     		boolean flag = eatingCardServices.AddTempCardApplication(parmeterObject, affirmorList);
			boolean flag = eatingCardServices.AddTempCardApplication(parmeterObject, affirmorId);
			
     	if(flag == true){
			if(essSysparam.isGaSendMail()){
				SimpleMap dataMap = new SimpleMap();
				dataMap.set("申请人", admin.getChineseName());
				dataMap.set("申请时间", timeFormatter.format(new Date()));
				dataMap.set("使用者姓名", request.getParameter("username"));
				dataMap.set("使用者业务", request.getParameter("userbusiness"));
				dataMap.set("开始日期", request.getParameter("starttime"));
				dataMap.set("结束日期", request.getParameter("endtime"));
//				gaAffirmList = (GaAffirmList)affirmorList.get(0);
//				if(gaAffirmList.getAffirmLevel()==1){
					SimpleMap parameterObject = null;
					parameterObject = ObjectBindUtil.getData(request);
//			    	parameterObject.set("applerId", gaAffirmList.getAffirmorId());	
					parameterObject.set("applerId", affirmorId[0]);
				    String toEmail = seServices.getapplyemail(parameterObject);
				    String defaultSysFile = "/system.properties";
				    UserConfiguration userConfig = UserConfiguration.getInstance(defaultSysFile);
				    String emailNameDicc = userConfig.getString("emaile.tempcard.dicc").trim();//临时卡申请后如果需要就餐（中、韩），还发送给1465030(yilin.kong@doosan.com)
				    if(eatYn > 0 && emailNameDicc !=null && !emailNameDicc.isEmpty()  && admin.getCpnyId().equals("78000000")){
				    	String emailName1[] = emailNameDicc.split(",");
					    for(int i=0;i<emailName1.length;i++){
					    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
						    	parameterObject.set("applerId", emailName1[i]);	
						    	String toEmail1 = seServices.getapplyemail(parameterObject);
						    	mail.gaSendMail(dataMap, cpnyId, toEmail1, "临时卡已经申请，需要就餐");
					    	}
					    }
				    }
				    mail.gaSendMail(dataMap, cpnyId, toEmail, "临时卡已经申请，请您决裁");
//				}			
			}
			request.setAttribute("errorMsg", "申请成功！");
     	}else {
     		request.setAttribute("errorMag", "申请失败");
     	}
	
		} catch (Exception e) {
			request.setAttribute("errorMsg", "申请失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}	
		request.setAttribute("name", admin.getChineseName());
		request.setAttribute("DeptID", admin.getDepartment());		
		List tempCardType = es.getTempCardType(parameterObject);	
		request.setAttribute("tempCardType", tempCardType);
		request.setAttribute("affirmList", gaAffirm.getAffirmor1(adminId, "temporarycardApply", essSysparam.isContainsOwner()));
		return "/ga_temporarycard_application.jsp";
	}
	
	private int addAffirmCard(int seq,String createby,List affirmor,Connection conn){
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ga_card_application_affirm" +
				"(" +
				"GA_AFFIRM_NO," +
				"APPLY_NO," +
				"AFFIRM_FLAG," +
				"CREATE_DATE," +
				"CREATED_BY," +
				"AFFIRM_LEVEL," +
				"AFFIRMOR_ID," +
				"ACTIVITY," +
				"APPLY_TYPE) " 
			+ 
			"VALUES " 
			+ "(GA_CARD_APPLICATION_AFFIRM_SEQ.NEXTVAL, ?, 0, SYSDATE, ?, ?, ?, 1, 'CARD_APPLICATION')";
		Logger.getLogger(getClass()).debug(sql);	
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.setString(2, createby);			
			for(int i=0;i<affirmor.size();i++){
				gaAffirmList=(GaAffirmList)affirmor.get(i);
				pstmt.setInt(3, gaAffirmList.getAffirmLevel());
				pstmt.setString(4, gaAffirmList.getAffirmorId());
				result+=pstmt.executeUpdate();
			}
			if(result==affirmor.size()){
				result = 1;
			}else {
				return -1;
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally{
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}
	
	private int addAffirmCard1(int seq,String createby,List affirmor,Connection conn){
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ga_card_application_affirm" +
				"(" +
				"GA_AFFIRM_NO," +
				"APPLY_NO," +
				"AFFIRM_FLAG," +
				"CREATE_DATE," +
				"CREATED_BY," +
				"AFFIRM_LEVEL," +
				"AFFIRMOR_ID," +
				"ACTIVITY," +
				"APPLY_TYPE) " 
			+ 
			"VALUES " 
			+ "(GA_CARD_APPLICATION_AFFIRM_SEQ.NEXTVAL, ?, 1, SYSDATE, ?, ?, ?, 1, 'CARD_APPLICATION')";
		Logger.getLogger(getClass()).debug(sql);	
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.setString(2, createby);			
			for(int i=0;i<affirmor.size();i++){
				gaAffirmList=(GaAffirmList)affirmor.get(i);
				pstmt.setInt(3, gaAffirmList.getAffirmLevel());
				pstmt.setString(4, gaAffirmList.getAffirmorId());
				result+=pstmt.executeUpdate();
			}
			if(result==affirmor.size()){
				result = 1;
			}else {
				return -1;
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally{
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}
	
	public void wasteUnitPrice(HttpServletRequest request){

		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		
		String chinesename = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		
		request.setAttribute("chinesename", chinesename);
		
		SimpleMap map = null;
		
		String wasteId = request.getParameter("wasteId");
		WasteServices wasteServices = new WasteServices();
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			
//			 retrieve attendance record list
			Object eatLookCount = 0;
			
			List allwasteType = wasteServices.wasteType(parameterObject);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date = format.format(Calendar.getInstance().getTime());
			
			request.setAttribute("date", date);
			
			request.setAttribute("wasteType", allwasteType);
			request.setAttribute("recordCount", NumberUtil
					.parseInt(eatLookCount));
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("rulesView error ", e);
		}
		
	}	

	public void getEatTypeandCanteensType(HttpServletRequest request){

		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		String name = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		String deptname = ((AdminBean) request.getSession(false).getAttribute("admin")).getDepartment();
		SimpleMap map = null;
		EateryServices es = new EateryServices();
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpnyId", cpny_id);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			
			List eatType = es.getEatType(parameterObject);		
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(adminId, "CARD_APPLICATION", essSysparam.isContainsOwner()));
			request.setAttribute("name", name);
			request.setAttribute("DeptID", deptname);
			request.setAttribute("eatType", eatType);
			
			request.setAttribute("startDate", sdf.format(today.getTime()));
			request.setAttribute("endDate", sdf.format(today.getTime()));
			
			String declaration = "";
			if("63000000".equals(cpny_id)){
				declaration="(有外来访客或未带卡员工请在当日10:30前申请午餐及晚餐，韩餐vip餐卡请提前一天申请)";
			}
			request.setAttribute("declaration", declaration);
			
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getEatTypeandCanteensType error ", e);
		}		
	}
	
	public void getTemporarycardView(HttpServletRequest request){

		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute(
				"admin")).getAdminID();
		String cpny_id = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getCpnyId();
		String name = ((AdminBean) request.getSession(false).getAttribute(
		"admin")).getChineseName();
		String deptname = ((AdminBean) request.getSession(false).getAttribute("admin")).getDepartment();
		SimpleMap map = null;
		EateryServices es = new EateryServices();
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("cpnyId", cpny_id);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();
			
			List tempCardType = es.getTempCardType(parameterObject);		
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(adminId, "temporarycardApply", essSysparam.isContainsOwner()));
			request.setAttribute("name", name);
			request.setAttribute("DeptID", deptname);
			request.setAttribute("tempCardType", tempCardType);
			
			request.setAttribute("startDate", sdf.format(today.getTime()));
			request.setAttribute("endDate", sdf.format(today.getTime()));
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getEatTypeandCanteensType error ", e);
		}		
	}
	
	public void getCheckView(HttpServletRequest request){
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String name = ((AdminBean) request.getSession(false).getAttribute("admin")).getChineseName();
		String deptname = ((AdminBean) request.getSession(false).getAttribute("admin")).getDepartment();
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();	
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(adminId, "checkApply", essSysparam.isContainsOwner()));
			request.setAttribute("CHECKBANK_TYPE", "");
			request.setAttribute("ACCOUNT", "");
			request.setAttribute("CHECK_TYPE", "");
			request.setAttribute("name", name);
			request.setAttribute("DeptID", deptname);			
			request.setAttribute("cpnyId", cpnyId);			
			request.setAttribute("startDate", sdf.format(today.getTime()));
			request.setAttribute("endDate", sdf.format(today.getTime()));
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getEatTypeandCanteensType error ", e);
		}		
	}
	
	public void getCheckAccount(HttpServletRequest request){

		SimpleMap parameterObject;
		Object result = null;
		EateryServices es = new EateryServices();
		this.putToolbarInfo(request);
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String name = ((AdminBean) request.getSession(false).getAttribute("admin")).getChineseName();
		String deptname = ((AdminBean) request.getSession(false).getAttribute("admin")).getDepartment();
		String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
		try {
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpnyId", cpnyId);
			if(parameterObject.get("CHECKBANK_TYPE") != null && parameterObject.get("ACCOUNT") != null && parameterObject.get("CHECK_TYPE") != null){
            List list = es.getCheckAccount(parameterObject);

				if(list.size() > 0)
					result = list.get(0);
			}
			if(!result.toString().equals("0")){
				parameterObject.set("adminId", admin.getAdminID());
				parameterObject.set("result", result);
	            es.updateCheckAccount(parameterObject);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();	
			request.setAttribute("result", result.toString());	
			request.setAttribute("affirmList", gaAffirm.getAffirmor1(adminId, "checkApply", essSysparam.isContainsOwner()));
			request.setAttribute("CHECKBANK_TYPE", parameterObject.get("CHECKBANK_TYPE"));
			request.setAttribute("ACCOUNT", parameterObject.get("ACCOUNT"));
			request.setAttribute("CHECK_TYPE", parameterObject.get("CHECK_TYPE"));
			request.setAttribute("name", name);
			request.setAttribute("DeptID", deptname);			
			request.setAttribute("cpnyId", cpnyId);			
			request.setAttribute("startDate", sdf.format(today.getTime()));
			request.setAttribute("endDate", sdf.format(today.getTime()));
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("getEatTypeandCanteensType error ", e);
		}		
	}
	
	public String addVoitureApply(HttpServletRequest request,AdminBean admin){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
		String adminId = admin.getAdminID();
//		GaAffirmList gaAffirmList = new GaAffirmList();
		
//		List affirmor=gaAffirm.getAffirmor(adminId,"VehicleApply",essSysparam.isContainsOwner());		
		
		String sql = "insert into GA_VOITURE_APPLY (VOITURE_APPLYID,APPLYER_ID,APPLYER_DEPTNAME,APPLY_DATE,APPLY_STARTTIME,APPLY_ENDTIME,ACTIVE,APPLY_ENDDATE,APPLY_USERSCOUNT," +
				"DEPARTURES,DESTINATIONS,KILOMETER,LARDER,PURPOSE,DRIVERFLAG,MODIFY_FLAG,CREATE_DATE,TELL_PHONE,LARDER_DEPTNAME,LARDER_POST) values(GA_VOITURE_APPLY_SEQ.Nextval,?,?,to_date(?,'YYYY-MM-DD'),?,?,'0',to_date(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,sysdate,?,?,?)";
		String sql1="insert into GA_VOITURE_AFFIRM(ID,APPLY_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVE) " +
				"values(GA_VOITURE_AFFIRM_SEQ.Nextval,GA_VOITURE_APPLY_SEQ.CURRVAL,'0',?,?,sysdate,?,'1')";
		String sql2 = "insert into GA_VOITURE_APPLY_DISTINCTION (DISTINCTION_ID,APPLY_NO,DISTINCTION,APPLY_DATE,APPLY_STARTTIME,APPLY_ENDDATE,APPLY_ENDTIME,CONTENT,DRIVE_WAY,NOTE,CREATE_DATE) " +
				"values(GA_VOITURE_APPLY_DISTINCT_SEQ.Nextval,GA_VOITURE_APPLY_SEQ.CURRVAL,?,to_date(?,'YYYY-MM-DD'),?,to_date(?,'YYYY-MM-DD'),?,?,?,?,sysdate)";	
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, request.getParameter("APPLYER_DEPTNAME")!=null?request.getParameter("APPLYER_DEPTNAME"):"");
			pstmt.setString(3, request.getParameter("APPLY_DATE")!=null?request.getParameter("APPLY_DATE"):"");
			pstmt.setString(4, request.getParameter("APPLY_STARTTIME")!=null?request.getParameter("APPLY_STARTTIME"):"");
			pstmt.setString(5, request.getParameter("APPLY_ENDTIME")!=null?request.getParameter("APPLY_ENDTIME"):"");
			pstmt.setString(6, request.getParameter("APPLY_ENDDATE")!=null?request.getParameter("APPLY_ENDDATE"):"");
			pstmt.setString(7, request.getParameter("APPLY_USERSCOUNT")!=null?request.getParameter("APPLY_USERSCOUNT"):"");
			pstmt.setString(8, request.getParameter("DEPARTURES")!=null?request.getParameter("DEPARTURES"):"");
			pstmt.setString(9, request.getParameter("DESTINATIONS")!=null?request.getParameter("DESTINATIONS"):"");
			pstmt.setString(10, request.getParameter("KILOMETER")!=null?request.getParameter("KILOMETER"):"");
			pstmt.setString(11, request.getParameter("LARDER")!=null?request.getParameter("LARDER"):"");
			pstmt.setString(12, request.getParameter("PURPOSE")!=null?request.getParameter("PURPOSE"):"");
			pstmt.setString(13, request.getParameter("DRIVERFLAG")!=null?request.getParameter("DRIVERFLAG"):"");
			pstmt.setString(14, "0");
			pstmt.setString(15, request.getParameter("TELL_PHONE")!=null?request.getParameter("TELL_PHONE"):"");
			pstmt.setString(16, request.getParameter("LARDER_DEPTNAME")!=null?request.getParameter("LARDER_DEPTNAME"):"");
			pstmt.setString(17, request.getParameter("LARDER_POST")!=null?request.getParameter("LARDER_POST"):"");
			pstmt.executeUpdate();
			String AffirmorIdFirst = "";
//			for(int i=0;i<affirmor.size();i++){
//				GaAffirmList gaAffirmList1 = new GaAffirmList();
//				gaAffirmList1=(GaAffirmList)affirmor.get(i);
//				pstmt1 = conn.prepareStatement(sql1);
//				pstmt1.setInt(1, gaAffirmList1.getAffirmLevel());
//				pstmt1.setString(2, gaAffirmList1.getAffirmorId());
//				pstmt1.setString(3, adminId);
//				pstmt1.executeUpdate();
//				if(i==0){
//					AffirmorIdFirst = gaAffirmList1.getAffirmorId();
//				}
//			}
			
			int flag = NumberUtil.parseInt(request.getParameter("flag"));
			
			if(flag>=0){
				
				if("".equals(request.getParameter("DISTINCTION0"))||"".equals(request.getParameter("DISTINCTION1"))){
				for(int i=0 ; i<1 ; i++){
					
						pstmt2 = conn.prepareStatement(sql2);
						pstmt2.setString(1,StringUtil.checkNull(request.getParameter("DISTINCTION"+i)));
						pstmt2.setString(2,StringUtil.checkNull(request.getParameter("APPLY_DATE"+i)));
						pstmt2.setString(3,StringUtil.checkNull(request.getParameter("APPLY_STARTTIME"+i)));
						pstmt2.setString(4,StringUtil.checkNull(request.getParameter("APPLY_DATE"+i)));
						pstmt2.setString(5,StringUtil.checkNull(request.getParameter("APPLY_ENDTIME"+i)));
						pstmt2.setString(6,StringUtil.checkNull(request.getParameter("CONTENT"+i)));
						pstmt2.setString(7,StringUtil.checkNull(request.getParameter("DRIVE_WAY"+i)));
						pstmt2.setString(8,StringUtil.checkNull(request.getParameter("memo"+i)));
						pstmt2.executeUpdate();
					}
				
				}else{
					for(int i=0 ; i<(flag+1) ; i++){
						
						pstmt2 = conn.prepareStatement(sql2);
						pstmt2.setString(1,StringUtil.checkNull(request.getParameter("DISTINCTION"+i)));
						pstmt2.setString(2,StringUtil.checkNull(request.getParameter("APPLY_DATE"+i)));
						pstmt2.setString(3,StringUtil.checkNull(request.getParameter("APPLY_STARTTIME"+i)));
						pstmt2.setString(4,StringUtil.checkNull(request.getParameter("APPLY_DATE"+i)));
						pstmt2.setString(5,StringUtil.checkNull(request.getParameter("APPLY_ENDTIME"+i)));
						pstmt2.setString(6,StringUtil.checkNull(request.getParameter("CONTENT"+i)));
						pstmt2.setString(7,StringUtil.checkNull(request.getParameter("DRIVE_WAY"+i)));
						pstmt2.setString(8,StringUtil.checkNull(request.getParameter("memo"+i)));
						pstmt2.executeUpdate();
					}
				}
				
			}
			
			String[] affirmorId = request.getParameterValues("affirmorId");
			
			for(int i=0;i<affirmorId.length;i++){
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, i+1);
				pstmt1.setString(2, affirmorId[i]);
				pstmt1.setString(3, adminId);
				pstmt1.executeUpdate();
			}
			
			AffirmorIdFirst = affirmorId[0];
			conn.commit();
			request.setAttribute("errorMsg", "派车申请成功！");
			String applyDate = request.getParameter("APPLY_DATE")+" "+request.getParameter("APPLY_STARTTIME");
			String used = request.getParameter("APPLY_USERSCOUNT");
			String DEPARTURES = request.getParameter("DEPARTURES");
			String DESTINATIONS = request.getParameter("DESTINATIONS");
			String KILOMETER = request.getParameter("KILOMETER");
			String endTime = request.getParameter("APPLY_ENDDATE")+" "+request.getParameter("APPLY_ENDTIME");
			try {
				if(essSysparam.isGaSendMail()){
					
				this.sendCarApplyMail(AffirmorIdFirst, admin.getChineseName(), applyDate, used, DEPARTURES, DESTINATIONS, KILOMETER,endTime);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errorMsg", "派车申请失败！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			SqlUtil.close(pstmt, conn);
			SqlUtil.close(pstmt1);
		}
		
		request.setAttribute("alert", "  申请成功 !");
		request.setAttribute("url", "/gaControlServlet?menu_code=ga0101&operation=gaApply&content=voitureApply");
		
		return "/inc/alertMessage.jsp";
	}
	
	
	public String addDriverOtApply(HttpServletRequest request,AdminBean admin){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
		String adminId = admin.getAdminID();
//		GaAffirmList gaAffirmList = new GaAffirmList();
//		List affirmor=gaAffirm.getAffirmor(adminId,"VehicleOtApply",essSysparam.isContainsOwner());
		//String sql = "insert into GA_VOITURE_APPLY (VOITURE_APPLYID,APPLYER_ID,APPLYER_DEPTNAME,APPLY_DATE,APPLY_STARTTIME,APPLY_ENDTIME,ACTIVE,APPLY_ENDDATE,APPLY_USERSCOUNT," +
		//		"DEPARTURES,DESTINATIONS,KILOMETER,LARDER,PURPOSE,DRIVERFLAG,MODIFY_FLAG,CREATE_DATE) values(GA_VOITURE_APPLY_SEQ.Nextval,?,?,to_date(?,'YYYY-MM-DD'),?,?,'0',to_date(?,'YYYY-MM-DD'),?,?,?,?,?,?,?,?,sysdate)";
		
		String sql = "insert into GA_DRIVER_OT_APPLY (DRIVER_OT_APPLYID, APPLYER_ID, APPLYER_CHINESENAME,DRIVER_ID,DRIVER_CHINESENAME,APPLY_DATE, APPLY_STARTDATE, APPLY_STARTTIME,"+
				 "APPLY_ENDDATE, APPLY_ENDTIME,REASON, REMARK,ACTIVE,CREATE_DATE,DRIVER_OT_TYPE,OTTYPE,OT_LENGTH)values (GA_DRIVER_OT_APPLY_SEQ.Nextval, ?, ?, ?,get_code_name(?), to_date(?, 'YYYY-MM-DD'),to_date(?, 'YYYY-MM-DD'),?,to_date(?, 'YYYY-MM-DD'),?,?,?,0,sysdate,?,?,?)";
		
		String sql1="insert into GA_DRIVER_OT_AFFIRM(ID,APPLY_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVE) " +
				"values(GA_DRIVER_OT_AFFIRM_SEQ.Nextval,GA_DRIVER_OT_APPLY_SEQ.CURRVAL,'0',?,?,sysdate,?,'1')";
			conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setString(2, request.getParameter("APPLYER_CHINESENAME")!=null?request.getParameter("APPLYER_CHINESENAME"):"");
			pstmt.setString(3, request.getParameter("driverCode")!=null?request.getParameter("driverCode"):"");
			pstmt.setString(4, request.getParameter("driverCode")!=null?request.getParameter("driverCode"):"");
			pstmt.setString(5, request.getParameter("APPLY_DATE")!=null?request.getParameter("APPLY_DATE"):"");
			pstmt.setString(6, request.getParameter("APPLY_STARTDATE")!=null?request.getParameter("APPLY_STARTDATE"):"");
			pstmt.setString(7, request.getParameter("APPLY_STARTTIME")!=null?request.getParameter("APPLY_STARTTIME"):"");
			pstmt.setString(8, request.getParameter("APPLY_ENDDATE")!=null?request.getParameter("APPLY_ENDDATE"):"");
			pstmt.setString(9, request.getParameter("APPLY_ENDTIME")!=null?request.getParameter("APPLY_ENDTIME"):"");
			
			pstmt.setString(10, request.getParameter("REASON")!=null?request.getParameter("REASON"):"");
			pstmt.setString(11, request.getParameter("REMARK")!=null?request.getParameter("REMARK"):"");
			pstmt.setString(12, request.getParameter("driverOtType")!=null?request.getParameter("driverOtType"):"");
			pstmt.setString(13, request.getParameter("OTTypeCode")!=null?request.getParameter("OTTypeCode"):"");
			pstmt.setString(14, request.getParameter("OT_LENGTH")!=null?request.getParameter("OT_LENGTH"):"");
			Logger.getLogger(getClass()).debug(sql);
//			System.out.println(request.getParameter("driverCode")!=null?request.getParameter("driverCode"):"");
//			System.out.println(request.getParameter("APPLY_STARTDATE")!=null?request.getParameter("APPLY_STARTDATE"):"");
//			System.out.println(request.getParameter("APPLY_ENDDATE")!=null?request.getParameter("APPLY_ENDDATE"):"");
			
			Logger.getLogger(getClass()).debug(sql1);
			pstmt.executeUpdate();
			String AffirmorIdFirst = "";
			
//			for(int i=0;i<affirmor.size();i++){
//				GaAffirmList gaAffirmList1 = new GaAffirmList();
//				gaAffirmList1=(GaAffirmList)affirmor.get(i);
//				pstmt1 = conn.prepareStatement(sql1);
//				pstmt1.setInt(1, gaAffirmList1.getAffirmLevel());
//				pstmt1.setString(2, gaAffirmList1.getAffirmorId());
//				pstmt1.setString(3, adminId);
//				pstmt1.executeUpdate();
//				if(i==0){
//					AffirmorIdFirst = gaAffirmList1.getAffirmorId();
//				}
//			}

			String[] affirmorId = request.getParameterValues("affirmorId");
			
			for(int i=0;i<affirmorId.length;i++){
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, i+1);
				pstmt1.setString(2, affirmorId[i]);
				pstmt1.setString(3, adminId);
				pstmt1.executeUpdate();
			}
			
			AffirmorIdFirst = affirmorId[0];
			conn.commit();
			request.setAttribute("errorMsg", "司机加班申请成功！");
//			String applyDate = request.getParameter("APPLY_DATE")+" "+request.getParameter("APPLY_STARTTIME");
//			String used = request.getParameter("APPLY_USERSCOUNT");
//			String DEPARTURES = request.getParameter("DEPARTURES");
//			String DESTINATIONS = request.getParameter("DESTINATIONS");
//			String KILOMETER = request.getParameter("KILOMETER");
//			String endTime = request.getParameter("APPLY_ENDDATE")+" "+request.getParameter("APPLY_ENDTIME");
//			try {
//				if(essSysparam.isGaSendMail()){
//					
//				this.sendCarApplyMail(AffirmorIdFirst, admin.getChineseName(), applyDate, used, DEPARTURES, DESTINATIONS, KILOMETER,endTime);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errorMsg", "司机加班申请失败！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			SqlUtil.close(pstmt, conn);
			SqlUtil.close(pstmt1);
		}
		
		request.setAttribute("alert", "  申请成功 !");
		request.setAttribute("url", "/gaControlServlet?menu_code=ga0101&operation=gaApply&content=driverOtApplyPage");
		
		return "/inc/alertMessage.jsp";
	}
	
	
	public String addBusArrangeApply(HttpServletRequest request,AdminBean admin){
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
		String adminId = admin.getAdminID();
//		GaAffirmList gaAffirmList = new GaAffirmList();
//		List affirmor=gaAffirm.getAffirmor(adminId,"VehicleOtApply",essSysparam.isContainsOwner());
		
		String sql = "insert into GA_BUS_ARRANGE_APPLY (BUS_ARRANGE_APPLYID, APPLYER_ID, APPLYER_CHINESENAME, APPLY_STARTDATE, APPLY_STARTTIME,BUS,SEATNUM,RIDENUM,"+
				 "ARRIVE_DATE, ARRIVE_TIME, REMARK,ACTIVE,CREATE_DATE,BUS_AREA)values (GA_BUS_ARRANGE_APPLY_SEQ.Nextval, ?, ?,to_date(?, 'YYYY-MM-DD'),?,?,?,?,to_date(?, 'YYYY-MM-DD'),?,?,0,sysdate,?)";
		
		String sql1="insert into GA_BUS_ARRANGE_AFFIRM(ID,APPLY_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVE) " +
				"values(GA_BUS_ARRANGE_AFFIRM_SEQ.Nextval,GA_BUS_ARRANGE_APPLY_SEQ.CURRVAL,'0',?,?,sysdate,?,'1')";
			conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		
		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		if(flag>=0){
			for(int j=0 ; j<(flag+1) ; j++){
		
			pstmt.setString(1, adminId);
			pstmt.setString(2, request.getParameter("APPLYER_CHINESENAME")!=null?request.getParameter("APPLYER_CHINESENAME"):"");
			pstmt.setString(3, request.getParameter("APPLY_STARTDATE"+j)!=null?request.getParameter("APPLY_STARTDATE"+j):"");
			pstmt.setString(4, request.getParameter("APPLY_STARTTIME"+j)!=null?request.getParameter("APPLY_STARTTIME"+j):"");
			pstmt.setString(5, request.getParameter("busTypeCode"+j)!=null?request.getParameter("busTypeCode"+j):"");
			pstmt.setString(6, request.getParameter("SEATNUM"+j)!=null?request.getParameter("SEATNUM"+j):"");
			pstmt.setString(7, request.getParameter("RIDENUM"+j)!=null?request.getParameter("RIDENUM"+j):"");
			pstmt.setString(8, request.getParameter("ARRIVE_DATE"+j)!=null?request.getParameter("ARRIVE_DATE"+j):"");
			pstmt.setString(9, request.getParameter("ARRIVE_TIME"+j)!=null?request.getParameter("ARRIVE_TIME"+j):"");
			pstmt.setString(10, request.getParameter("REMARK"+j)!=null?request.getParameter("REMARK"+j):"");
			pstmt.setString(11, request.getParameter("busAreaType"+j)!=null?request.getParameter("busAreaType"+j):"");
			Logger.getLogger(getClass()).debug(sql);
			
			System.out.println(request.getParameter("APPLY_STARTDATE")!=null?request.getParameter("APPLY_STARTDATE"):"");
			System.out.println(request.getParameter("APPLY_ENDDATE")!=null?request.getParameter("APPLY_ENDDATE"):"");
			
			//Logger.getLogger(getClass()).debug(sql1);
			pstmt.executeUpdate();
			String AffirmorIdFirst = "";
//			for(int i=0;i<affirmor.size();i++){
//				GaAffirmList gaAffirmList1 = new GaAffirmList();
//				gaAffirmList1=(GaAffirmList)affirmor.get(i);
//				pstmt1 = conn.prepareStatement(sql1);
//				pstmt1.setInt(1, gaAffirmList1.getAffirmLevel());
//				pstmt1.setString(2, gaAffirmList1.getAffirmorId());
//				pstmt1.setString(3, adminId);
//				pstmt1.executeUpdate();
//				if(i==0){
//					AffirmorIdFirst = gaAffirmList1.getAffirmorId();
//				}
//			}
			
			String[] affirmorId = request.getParameterValues("affirmorId");
			
			for(int i=0;i<affirmorId.length;i++){
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, i+1);
				pstmt1.setString(2, affirmorId[i]);
				pstmt1.setString(3, adminId);
				pstmt1.executeUpdate();
			}
			
			AffirmorIdFirst = affirmorId[0];
			
			
			}	
		}
			
			
			
			conn.commit();
			request.setAttribute("errorMsg", "班车安排申请成功！");
//			String applyDate = request.getParameter("APPLY_DATE")+" "+request.getParameter("APPLY_STARTTIME");
//			String used = request.getParameter("APPLY_USERSCOUNT");
//			String DEPARTURES = request.getParameter("DEPARTURES");
//			String DESTINATIONS = request.getParameter("DESTINATIONS");
//			String KILOMETER = request.getParameter("KILOMETER");
//			String endTime = request.getParameter("APPLY_ENDDATE")+" "+request.getParameter("APPLY_ENDTIME");
//			try {
//				if(essSysparam.isGaSendMail()){
//					
//				this.sendCarApplyMail(AffirmorIdFirst, admin.getChineseName(), applyDate, used, DEPARTURES, DESTINATIONS, KILOMETER,endTime);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("errorMsg", "班车安排申请失败！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			SqlUtil.close(pstmt, conn);
			SqlUtil.close(pstmt1);
		}
		
		request.setAttribute("alert", "  申请成功 !");
		request.setAttribute("url", "/gaControlServlet?menu_code=ga0116&operation=gaApply&content=busArrangeApplyPage");
		
		return "/inc/alertMessage.jsp";
	}
	
	
	private void sendCarApplyMail(String adminid, String applyer,
			String startTime, String usednum, String DEPARTURES, String DESTINATIONS,
			String KILOMETER, String endTime) throws Exception {

		SimpleMap parameterObject = new SimpleMap();

		SimpleMap inputData = new SimpleMap();

		parameterObject.set("applerId", adminid);
		String result = es.getconfirm(parameterObject);

		
		inputData.set("申请人：", applyer);
		inputData.set("用车人数：", usednum);
//		inputData.set(" 出发地：", DEPARTURES);
//		inputData.set(" 目的地：", DESTINATIONS);
//		inputData.set(" 预计行程：", KILOMETER);
//		inputData.set(" 开始日期：", startTime);
//		inputData.set(" 结束日期：", endTime);
		
		String defaultSysFile = "/system.properties";
	    UserConfiguration userConfig = UserConfiguration.getInstance(defaultSysFile);
	    String emailNameDisd = userConfig.getString("emaile.car.disd").trim();
	    String emailNameDiy = userConfig.getString("emaile.car.diy").trim();
	    String emailNameDicc = userConfig.getString("emaile.car.dicc").trim();
	    if(emailNameDisd !=null && !emailNameDisd.isEmpty() && admin.getCpnyId().equals("63000000")){
	    	String emailName1[] = emailNameDisd.split(",");
		    for(int i=0;i<emailName1.length;i++){
		    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
			    	parameterObject.set("applerId", emailName1[i]);	
			    	String toEmail1 = seServices.getapplyemail(parameterObject);
			    	mail.gaSendMail(inputData, admin.getCpnyId(), toEmail1, "派车已经申请");
			    }
			}
	    }
	    if(emailNameDiy !=null && !emailNameDiy.isEmpty()  && admin.getCpnyId().equals("60000000")){
	    	String emailName1[] = emailNameDiy.split(",");
		    for(int i=0;i<emailName1.length;i++){
		    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
			    	parameterObject.set("applerId", emailName1[i]);	
			    	String toEmail1 = seServices.getapplyemail(parameterObject);
			    	mail.gaSendMail(inputData, admin.getCpnyId(), toEmail1, "派车已经申请");
			    }
			}
	    }
	    if(emailNameDicc !=null && !emailNameDicc.isEmpty()  && admin.getCpnyId().equals("78000000")){
	    	String emailName1[] = emailNameDicc.split(",");
		    for(int i=0;i<emailName1.length;i++){
		    	if(emailName1[i] !=null && !emailName1[i].isEmpty()){
			    	parameterObject.set("applerId", emailName1[i]);	
			    	String toEmail1 = seServices.getapplyemail(parameterObject);
			    	mail.gaSendMail(inputData, admin.getCpnyId(), toEmail1, "派车已经申请");
			    }
			}
	    }
	    
//		mail.sendMail(inputData);
		mail.gaSendMail(inputData, admin.getCpnyId(), result, "派车申请 等待决裁");

	}
	private int addWoodProduction(HttpServletRequest request)throws ServletException, IOException{	
		
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;		
		PreparedStatement affrimPstmt = null;
		 PreparedStatement detailPstmt = null;
		int seqId=this.getSequence("GA_PRODUCTS_APPLY_SEQ");	
		try {		
		conn.setAutoCommit(false);	
              
		String sql = " INSERT INTO GA_PRODUCTS_APPLY (APPLY_NO,WP_APPLYERID, APPLY_DATE,HOPE_COMPLETED_DATE,ACTIVE,NOTE,CONFIRM_FLAG,APPLY_TYPE,CPNY_ID)" +
				" values(?,?,SYSDATE,to_date(?,'yyyy-mm-dd'),'0',?,'0',?,?)";       
		
		String affirmSql = "INSERT INTO GA_PRODUCTS_AFFIRM (GA_AFFIRM_NO, APPLY_NO, AFFIRM_FLAG, CREATE_DATE, CREATED_BY, AFFIRM_LEVEL,AFFIRMOR_ID,ACTIVITY, APPLY_TYPE) " + "VALUES " + "(GA_WOODPRODUCTS_AFFIRM_SEQ.NEXTVAL, ?, 0,  SYSDATE, ?,?,?,1, ?)";
		
		String detailSql=" INSERT INTO GA_PRODUCTS_DETAIL(APPLY_NO,WPID,WPNAME,WPNUMBER,WP_L,WP_W,WP_H,WP_SUMPRICE,WP_TYPE,SEAL_TYPE) VALUES(?,?,?,?,?,?,?,?,?,?) ";
		Logger.getLogger(getClass()).debug(detailSql);
		
		Logger.getLogger(getClass()).debug(sql);
		
		Logger.getLogger(getClass()).debug(affirmSql);		
//		List affirmorList=gaAffirm.getAffirmor(request.getParameter("APPLYER_ID"),"WoodProductsApply", essSysparam.isContainsOwner());
		
			pstmt = conn.prepareStatement(sql);//插入申请信息			
			affrimPstmt=conn.prepareStatement(affirmSql);//插入决裁信息
			detailPstmt = conn.prepareStatement(detailSql);//插入明细信息					
			pstmt.setInt(1, seqId);
			pstmt.setString(2, admin.getAdminID());
			pstmt.setString(3, request.getParameter("APPLY_DATE"));
			pstmt.setString(4, request.getParameter("APPLY_PURPOSE"));
			pstmt.setString(5, "WoodProducts");
			pstmt.setString(6, admin.getCpnyId());
			pstmt.executeUpdate();
			
			String[] affirmorId = request.getParameterValues("affirmorId");
			
			
//			for(int i=0;i<affirmorList.size();i++){
//				gaAffirmList=(GaAffirmList)affirmorList.get(i);
//				affrimPstmt.setInt(1, seqId);
//				affrimPstmt.setString(2, admin.getAdminID());
//				affrimPstmt.setInt(3, gaAffirmList.getAffirmLevel());
//				affrimPstmt.setString(4, gaAffirmList.getAffirmorId());
//				affrimPstmt.setString(5,"WoodProducts");	
//				affrimPstmt.addBatch();
//			}
			
			for(int i=0;i<affirmorId.length;i++){
				affrimPstmt.setInt(1, seqId);
				affrimPstmt.setString(2, admin.getAdminID());
				affrimPstmt.setInt(3, i+1);
				affrimPstmt.setString(4, affirmorId[i]);
				affrimPstmt.setString(5,"WoodProducts");	
				affrimPstmt.addBatch();
			}

			affrimPstmt.executeBatch();	
		    String numberArray[] =request.getParameterValues("numberArray");		
				    for(int s=0;s<numberArray.length;s++){
					detailPstmt.setInt(1, seqId);
					detailPstmt.setString(2,request.getParameter("woodProductsid"+numberArray[s]));
					detailPstmt.setString(3, request.getParameter("woodProductsname"+numberArray[s]));
					detailPstmt.setInt(4,NumberUtil.parseInt(request.getParameter("num"+numberArray[s])));
					detailPstmt.setDouble(5,Double.parseDouble(request.getParameter("L"+numberArray[s])));
					detailPstmt.setDouble(6,Double.parseDouble(request.getParameter("W"+numberArray[s])));
					detailPstmt.setDouble(7,Double.parseDouble(request.getParameter("H"+numberArray[s])));
					detailPstmt.setDouble(8,Double.parseDouble(request.getParameter("sumPrice"+numberArray[s])));	
					detailPstmt.setString(9, "WoodProducts");
					detailPstmt.setString(10, "newSeal");
					detailPstmt.addBatch();
				    }				
			detailPstmt.executeBatch();
			conn.commit();
				request.setAttribute("errorMsg", "已申请成功！");
//				for(int s=0; s<affirmorList.size();s++){					
//				gaAffirmList=(GaAffirmList)affirmorList.get(s);
//				if(gaAffirmList.getAffirmLevel() == 1){
				SimpleMap parameterObject = null;
				parameterObject = ObjectBindUtil.getData(request);
//		    	parameterObject.set("applerId", gaAffirmList.getAffirmorId());	
		    	parameterObject.set("applerId", affirmorId[0]);		    	
			    String toEmail = seServices.getapplyemail(parameterObject);
			    SimpleMap dataMap = new SimpleMap();			    
			    dataMap.set("申请人", admin.getChineseName());
			    dataMap.set("希望完工日期", request.getParameter("APPLY_DATE"));
			    mail.gaSendMail(dataMap, admin.getCpnyId(), toEmail, "木制品申请 请您确认");
//				}
//			  }			
				
		} catch (Exception e) {
			try {
				conn.rollback();				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("errorMsg", "申请失败！");
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			try {
			pstmt.close();
		
			affrimPstmt.close();
			conn.close();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
		
	}

}