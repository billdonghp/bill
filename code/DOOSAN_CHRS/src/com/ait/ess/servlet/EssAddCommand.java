package com.ait.ess.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.business.ArServices;
import com.ait.ar.dao.AnnualBean;
import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.base.ErrorMessage;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.business.EssServices;
import com.ait.ess.dao.EssArDAO;
import com.ait.hrm.business.HrmServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SQLMapConfigManager;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.util.SysCodeParser;
import com.ait.web.Command;

public class EssAddCommand implements Command {

	private EssServices essServices;

	private HrmServices hrmServices;

	private ArServices arServices;

	//private String adminId;
	
	private String dateFormat = "yyyy-MM-dd HH:mm" ;
	
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//ad登录验证修改
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://portal.doosan.com" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//"http://dghr.corp.doosan.com/dic_login.jsp" ; //"http://172.16.225.240:9080/dic_login.jsp" 
	
	private GregorianCalendar startDate = null ;
	private GregorianCalendar endDate = null ;
	
	//private String cpnyId= null;
	
	//private EssSysparam essSysparam = null;

	private static final Logger logger = Logger.getLogger(SQLMapConfigManager.class);
	
	private String cpnyAllNameForMail="";
	
	private EssViewCommand essViewCommand = new EssViewCommand();

	public EssAddCommand() {
		essServices = new EssServices();
		hrmServices = HrmServices.getInstance();
		arServices = new ArServices();
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = StringUtil.checkNull(request.getParameter("content"));
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug("content : " + content);
		essServices.setAdminId(adminId) ;
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		//EssSysparam essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		if(cpnyId.equals("78000000")){
			cpnyAllNameForMail="斗山工程机械(中国)有限公司";
		}else if(cpnyId.equals("63000000")){
			cpnyAllNameForMail="斗山工程机械（山东）有限公司";
		}else if(cpnyId.equals("61000000")){
			cpnyAllNameForMail="斗山工程机械（苏州）有限公司";
		}else if(cpnyId.equals("59000000")){
			cpnyAllNameForMail="斗山(中国)投资有限公司";				
		}else if(cpnyId.equals("60000000")){
			cpnyAllNameForMail="斗山机床（烟台）有限公司";		
		}else{
			cpnyAllNameForMail="斗山工程机械";
		}
		
		if (content.equals("otapply"))
			return this.overtimeApply(request, response);
		else if (content.equals("otapply_batch"))
			return this.overtimeApplyBatch(request, response);
		else if (content.equals("leaveapply"))
			return this.leaveApply(request, response);
		else if (content.equals("leaveapply_batch"))
			return this.leaveApplyBatch(request, response);
		else if (content.equals("ottoplimitapply_batch"))
			return this.overtimeTopLimitApplyBatch(request, response);
		else if (content.equals("upLoadFile"))
			return this.upLoadFile(request, response);
		else return "/error.jsp";
	}
	/**
	 * 
	 * @param Applyno[]
	 * @param Affirmor
	 * @param flag
	 * @throws Exception
	 */

	public void sendOtMail(int[]Applyno,String setTo,int flag,String adminName)throws Exception{
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);//员工的PersonId
		SimpleMap result = (SimpleMap) essServices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) essServices.applyResult(parameterObject);
		SimpleMap inputData = new SimpleMap();
		String emailTitle="";	
		StringBuffer content = new StringBuffer();
		if(flag==1){
			emailTitle="加班申请";
		}else if(flag==2){
			emailTitle="加班申请,被否决";
		}else{
			emailTitle="加班申请";
		}
		String emailAddress=result.getString("EMAIL");
		
		for(int i=0;i<applyResult.size();i++){
			SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
			content.append("发件人:").append(adminName).append("<br><br>")
			       .append("申请人:").append(simpleMap.getString("CHINESENAME")).append("<br><br>")
			       .append("所属:").append(simpleMap.getString("DEPTNAME")).append("<br><br>")  
			       .append("主题:").append(simpleMap.getString("APPLYTYPE")).append("<br><br>")
			       .append("申请时间:").append(simpleMap.getString("APPLYTIME"));
			if(simpleMap.getString("APPLYTYPECODE").equals("WorkingOtType01")){
				content.append("<br><br>").append("开始时间:").append(simpleMap.getString("APPLYFROMTIME"))
				       .append("<br><br>").append("结束时间:").append(simpleMap.getString("APPLYTOTIME"));				
			}else{
				content.append("<br><br>").append("加班日期:").append(simpleMap.getString("APPLY_OT_DATE"))
			           .append("<br><br>").append("加班长度:").append(simpleMap.getString("OT_LENGTH"));				
			}	
			content.append("<br><br>");
			content.append("--------------------------------------------------------------------------");
			content.append("<br><br>");
		}
		content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>")
		   .append("<br><br>"+cpnyAllNameForMail) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;	
	}
	
	public void sendLeaveMail(int[]Applyno,String setTo,int flag,String adminName)throws Exception{
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		SimpleMap result = (SimpleMap) essServices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) essServices.applyLeaveResult(parameterObject);
		SimpleMap inputData = new SimpleMap();
		String emailTitle="";	
		StringBuffer content = new StringBuffer();
		if(flag==1){
			emailTitle="勤态申请";
		}else if(flag==2){
			emailTitle="勤态申请,被否决";
		}else{
			emailTitle="勤态申请";
		}
		String emailAddress=result.getString("EMAIL");
		for(int i=0;i<applyResult.size();i++){
			SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
			content.append("发件人:").append(adminName).append("<br><br>")
			       .append("申请人:").append(simpleMap.getString("CHINESENAME")).append("<br><br>")  
			       .append("所属:").append(simpleMap.getString("DEPTNAME")).append("<br><br>")
			       .append("主题:").append(simpleMap.getString("APPLY_TYPE")).append("<br><br>")
			       .append("申请时间:").append(simpleMap.getString("APPLY_DATE"));
			content.append("<br><br>").append("开始时间:").append(simpleMap.getString("FROM_DATE"))
				   .append("<br><br>").append("结束时间:").append(simpleMap.getString("TO_DATE"));
			content.append("<br><br>").append("勤态原因:").append(simpleMap.getString("LEAVE_REASON"));
			
			content.append("<br><br>");
			content.append("--------------------------------------------------------------------------");
			content.append("<br><br>");
		}
		content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>")
		   .append("<br><br>"+cpnyAllNameForMail) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;	
	}
	/**
	 * 
	 * @param sequence
	 * @param ApplyType
	 * @param menu_code
	 * @throws Exception
	 */
	private void sendOtApplyMail(EssOverTimeBean essOverTime, String ApplyType,String menu_code) throws Exception {

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("APPLY_EMPID", essOverTime.getPerson_id());
		parameterObject.setString("APPLY_TYPE_CONTENT", essOverTime.getOtTypeCode());
		parameterObject.setString("APPLY_TYPE", ApplyType);
		parameterObject.setInt("APPLY_NO", essOverTime.getOtNo());
		
		SimpleMap result = (SimpleMap) essServices.getApplyInfoForEmail(parameterObject);

		if (result != null) {
			SimpleMap inputData = new SimpleMap();

			StringBuffer content = new StringBuffer();
			
			startDate = DateUtil.ParseGregorianCalendar(essOverTime.getOtDate() + " " + essOverTime.getOtFromTime()) ;
			endDate = DateUtil.ParseGregorianCalendar(essOverTime.getOtDate() + " " + essOverTime.getOtToTime()) ;
			endDate.add(GregorianCalendar.DAY_OF_MONTH, essOverTime.getOtNextDays());
			
			content.append(" 申请人：").append(result.getString("OT_CHINESENAME"))
				   .append("<br><br>").append(" 主题：").append(result.getString("APPLY_TYPE_CONTENT"))
				   .append("<br><br>").append(" 申请时间：").append(result.getString("APPLY_TIME")) ;
			
			if (essOverTime.getOtLength() > 0)
			{
				content.append("<br><br>").append(" 加班日期：").append(essOverTime.getOtDate())
				       .append("<br><br>").append(" 加班长度：").append(essOverTime.getOtLength()) ;
			}
			else
			{
				content.append("<br><br>").append(" 开始时间：").append(DateUtil.formatDate(startDate.getTime(), this.dateFormat))
				   	   .append("<br><br>").append(" 结束时间：").append(DateUtil.formatDate(endDate.getTime(), this.dateFormat)) ;
			}
				   
			content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行决裁</a>")
				   .append("<br><br>"+cpnyAllNameForMail) ;
			

			// set email title
			inputData.setString("EMAIL_TITLE", result.getString("OT_CHINESENAME") + "," + result.getString("APPLY_TYPE_CONTENT") + " 加班申请");

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", result.getString("AFFIRM_EMAIL"));

			new Mail().sendMail(inputData) ;
			
			//essServices.insertOtAffirmMail(inputData);
		}
	}
	
	/**
	 * 
	 * @param sequence
	 * @param ApplyType
	 * @param Content
	 * @param menu_code
	 * @throws Exception
	 */
	private void sendLeaveApplyMail(EssLeaveBean leaveBean, String ApplyType, String menu_code,String adminName) throws Exception {

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("APPLY_EMPID", leaveBean.getPerson_id());
		parameterObject.setString("APPLY_TYPE_CONTENT", leaveBean.getLeaveTypeCode());
		parameterObject.setString("APPLY_TYPE", ApplyType);
		parameterObject.setInt("APPLY_NO", leaveBean.getLeaveNo());
		
		SimpleMap result = (SimpleMap) essServices.getApplyInfoForEmail(parameterObject);

		if (result != null) {
			SimpleMap inputData = new SimpleMap();

			StringBuffer content = new StringBuffer();
			
			startDate = DateUtil.ParseGregorianCalendar(leaveBean.getLeaveFromDate()+" "+leaveBean.getLeaveFromTime());
			endDate = DateUtil.ParseGregorianCalendar(leaveBean.getLeaveToDate()+" "+leaveBean.getLeaveToTime());
			content.append("发件人:").append(adminName)
			       .append("<br><br>").append(" 申请人：").append(result.getString("OT_CHINESENAME"))
			       .append("<br><br>").append("所属:").append(result.getString("DEPTNAME"))
				   .append("<br><br>").append(" 主题：").append(result.getString("APPLY_TYPE_CONTENT"))
				   .append("<br><br>").append(" 申请时间：").append(result.getString("APPLY_TIME"))
				   .append("<br><br>").append(" 开始时间：").append(DateUtil.formatDate(startDate.getTime(), this.dateFormat))
				   .append("<br><br>").append(" 结束时间：").append(DateUtil.formatDate(endDate.getTime(), this.dateFormat))
				   .append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行决裁</a>")
				   .append("<br><br>"+cpnyAllNameForMail) ;
			// set email title
			inputData.setString("EMAIL_TITLE", result.getString("OT_CHINESENAME") + "," + result.getString("APPLY_TYPE_CONTENT") + " 勤态申请") ;

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", result.getString("AFFIRM_EMAIL"));

			new Mail().sendMail(inputData) ;
			
			//essServices.insertOtAffirmMail(inputData);
		}
	}
	/**
	 * 
	 * @param sequence
	 * @param ApplyType
	 * @param Content
	 * @param menu_code
	 * @throws Exception
	 */
	private void sendLeaveApplyMailMap(SimpleMap leaveBean, String ApplyType, String menu_code) throws Exception {

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("APPLY_EMPID", leaveBean.getString("empId"));
		parameterObject.setString("APPLY_TYPE_CONTENT", leaveBean.getString("leaveTypeCode"));
		parameterObject.setString("APPLY_TYPE", ApplyType);

		SimpleMap result = (SimpleMap) essServices.getApplyInfoForEmail(parameterObject);

		if (result != null) {
			SimpleMap inputData = new SimpleMap();

			StringBuffer content = new StringBuffer();
			
			startDate = DateUtil.ParseGregorianCalendar(leaveBean.getString("leaveFromTime"));
			endDate = DateUtil.ParseGregorianCalendar(leaveBean.getString("leaveToDate"));
			
			content.append(" 申请人：").append(result.getString("OT_CHINESENAME"))
				   .append("<br><br>").append(" 主题：").append(result.getString("APPLY_TYPE_CONTENT"))
				   .append("<br><br>").append(" 申请时间：").append(result.getString("APPLY_TIME"))
				   .append("<br><br>").append(" 开始时间：").append(DateUtil.formatDate(startDate.getTime(), this.dateFormat))
				   .append("<br><br>").append(" 结束时间：").append(DateUtil.formatDate(endDate.getTime(), this.dateFormat))
				   .append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行决裁</a>")
				   .append("<br><br>"+cpnyAllNameForMail) ;
			// set email title
			inputData.setString("EMAIL_TITLE", result.getString("OT_CHINESENAME") + "," + result.getString("APPLY_OT_TYPE") + " 勤态申请") ;

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", result.getString("AFFIRM_EMAIL"));

			new Mail().sendMail(inputData) ;
			
			//essServices.insertOtAffirmMail(inputData);
		}
	}
	
	
	/**
	 * 
	 * @param sequence
	 * @param ApplyType
	 * @param Content
	 * @param menu_code
	 * @throws Exception
	 */
	private void sendOvertimeTopLimitApplyMail(EssOverTimeBean essOverTime, String ApplyType, String menu_code) throws Exception {

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("APPLY_EMPID", essOverTime.getEmpId());
		parameterObject.setString("APPLY_TYPE_CONTENT", ApplyType);
		parameterObject.setString("APPLY_TYPE", ApplyType);
		
		SimpleMap result = (SimpleMap) essServices.getApplyInfoForEmail(parameterObject);

		if (result != null) {
			SimpleMap inputData = new SimpleMap();

			StringBuffer content = new StringBuffer();

			content.append(" 申请人：").append(result.getString("OT_CHINESENAME"))
				   .append("<br><br>").append(" 主题：").append("加班上限申请")
				   .append("<br><br>").append(" 申请时间：").append(result.getString("APPLY_TIME"))
				   .append("<br><br>").append(" 申请加班上限月份：").append(essOverTime.getOtPlanMonth())
				   .append("<br><br>").append(" 申请长度：").append(essOverTime.getOtLength())
				   .append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行决裁</a>")
				   .append("<br><br>斗山工程机械(中国)有限公司") ;
			
			// set email title
			inputData.setString("EMAIL_TITLE", result.getString("OT_CHINESENAME") + ",加班上限申请");

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", result.getString("AFFIRM_EMAIL"));

			new Mail().sendMail(inputData) ;
			
			//essServices.insertOtAffirmMail(inputData);
		}
	}
	
	/**
	 * add overtime apply
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String overtimeApply(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
			String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			EssSysparam essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
			
			String empId = StringUtil.checkNull(request.getParameter("empIds"));
			if (empId.equals(""))
				empId = StringUtil.checkNull(request.getParameter("empId"), adminId);
			String[] empIds = empId.split(",");
			String temp = "";
			String content = "otaffirm";
			String menu_code = "ess0801";
			HashMap hashMap = new HashMap();
			int errorCode = 0;
			EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
			int applyGroup = essServices.getCurrentMaxApplyNo();
			for (int i = 0; i < empIds.length; i++) {
				empId = empIds[i];
				boolean flag = false;// 标示该员工有无决裁人
				essOverTimeBean.setEmpId(empId);
				essOverTimeBean.setChineseName((String) hrmServices.getChineseNameByEmpId(essOverTimeBean.getEmpId()));
				essOverTimeBean.setCreateDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime()));
				essOverTimeBean.setOtDate(StringUtil.checkNull(request.getParameter("otDate"), essOverTimeBean.getCreateDate()));
				essOverTimeBean.setOtFromTime(StringUtil.checkNull(request.getParameter("fromTime")));
				essOverTimeBean.setOtToTime(StringUtil.checkNull(request.getParameter("toTime")));
				essOverTimeBean.setOtNextDays(NumberUtil.parseInt(request.getParameter("otNextDays"), 0));
				essOverTimeBean.setOtDeduct(NumberUtil.parseDouble(request.getParameter("otDeduct"), 0));
				essOverTimeBean.setOtTypeCode(StringUtil.checkNull(request.getParameter("otTypeCode")));
				essOverTimeBean.setOtSort(StringUtil.checkNull(request.getParameter("otsort"), "normal"));
				essOverTimeBean.setOtSortCode(StringUtil.checkNull(request.getParameter("otSortCode")));
				if(!cpnyId.equals("60000000")){
				if (StringUtil.checkNull(request.getParameter("forceType")).equals("true"))
					essOverTimeBean.setForceType(true);
				essOverTimeBean.setOtRemark(StringUtil.toCN(request.getParameter("otRemark")));
				essOverTimeBean.setApplyGroupSeq(applyGroup);
				if (essOverTimeBean.getOtSort().equals("emergency"))
					essOverTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorList(essOverTimeBean.getEmpId(), "TOTApply", 99,essSysparam.isContainsOwner()));
				else if (essOverTimeBean.getOtSort().equals("overmax"))
					essOverTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorList(essOverTimeBean.getEmpId(), "SOTApply", 99,essSysparam.isContainsOwner()));
				else
					essOverTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorList(essOverTimeBean.getEmpId(), "OtApply", 99,essSysparam.isContainsOwner()));

				essServices.setAdminId(adminId);
				}else{
					if (StringUtil.checkNull(request.getParameter("forceType")).equals("true"))
						essOverTimeBean.setForceType(true);
					essOverTimeBean.setOtRemark(StringUtil.toCN(request.getParameter("otRemark")));
					essOverTimeBean.setApplyGroupSeq(applyGroup);
					if (essOverTimeBean.getOtSort().equals("emergency"))
						essOverTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorListDIY(essOverTimeBean.getEmpId(), "TOTApply", 99,essSysparam.isContainsOwner()));
					else if (essOverTimeBean.getOtSort().equals("overmax"))
						essOverTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorListDIY(essOverTimeBean.getEmpId(), "SOTApply", 99,essSysparam.isContainsOwner()));
					else
						essOverTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorListDIY(essOverTimeBean.getEmpId(), "OtApply", 99,essSysparam.isContainsOwner()));

					essServices.setAdminId(adminId);
				}
				
				if (essOverTimeBean.getAffirmorList().size() > 0)
					hashMap = essServices.doOtApply(essOverTimeBean);
				else {
					temp = temp + essOverTimeBean.getEmpId() + ",";
					flag = true;
				}
				if (hashMap.get("sequence") != null) {
					//this.sendApplyMail(Integer.parseInt(hashMap.get("sequence").toString()), "OtApply", content, menu_code);
				}
				errorCode = ((hashMap.get("errcode") != null ? ((Integer) hashMap.get("errcode")).intValue() : 0));
				if (errorCode != 0 || flag == true)
					continue;
			}
			if (temp.length() >= 6)// lsfc工号为六位，大于六位表示存在员工没有设置决裁人
			{
				hashMap.put("errcode", new Integer(17));
				hashMap.put("erremp", temp);
			}
			request.setAttribute("errorMsg", ErrorMessage.getErrorMessage("otApply", hashMap, request));
			request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("nextDaysList", NumberUtil.getIntSerialList(2));
			request.setAttribute("deductList", NumberUtil.getDoubleSerialList(10));
			if (essOverTimeBean.getOtSort().equals("emergency"))
				request.setAttribute("applyTypeVector", SysCodeParser.getCode(Configuration.getInstance().getString("/configuration/em2/syscode-parent/otapply-emergency", ""), 1));
			else if (essOverTimeBean.getOtSort().equals("overmax"))
				request.setAttribute("applyTypeVector", SysCodeParser.getCode(Configuration.getInstance().getString("/configuration/em2/syscode-parent/otapply-overmax", ""), 1));
			else
				request.setAttribute("applyTypeVector", SysCodeParser.getCode(Configuration.getInstance().getString("/configuration/em2/syscode-parent/otapply", ""), 1));
			String otApplySortPCode = Configuration.getInstance().getString("/configuration/em2/syscode-parent/otsort", "");
			request.setAttribute("applySortVector", SysCodeParser.getCode(otApplySortPCode, 1));
			request.setAttribute("essOverTimeBean", essOverTimeBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("Overtime Apply Exception.", e);
		}
		return "/ess_overtime_apply.jsp";
	}

	/**
	 * 批量加班申请
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String overtimeApplyBatch(HttpServletRequest request, HttpServletResponse response) {
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String adminName = ((AdminBean) request.getSession(false).getAttribute("admin")).getChineseName();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		EssSysparam essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		
		
		EssServices services = new EssServices();
		EssArDAO essArDAO = new EssArDAO();
		HashMap result = null;
		List<String> errorMsgList = new ArrayList<String>();
		SimpleMap parameterObject;
		List<EssOverTimeBean> applyList = new ArrayList<EssOverTimeBean>() ; // 申请人数据
		List<EssOverTimeBean> insertApplyList = new ArrayList<EssOverTimeBean>() ; // 插入申请人数据
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		Calendar date = Calendar.getInstance();
		
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style3.pagesize");
			int pageGroupSize = config.getInt("page.style3.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
			// bind apply form data to list object
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request) ; // 得到所有页面上的参数
			// 回填数据
			int applySize = parameterObject.getInt("applySize") ; //参数集合大小
			// 取得数据行数
			int resultCount = 0 ;
			EssOverTimeBean essOverTime = new EssOverTimeBean() ;
			SimpleMap map = new SimpleMap() ;
			map.setString("supervisor", adminId) ;	    // 登陆者权限
			map.setString("deptID", parameterObject.getString("deptID")) ;
			map.setString("key", parameterObject.getString("key")) ;
			map.setString("applyDate", dateFormat.format(date.getTime()));
			map.setString("cpnyId", cpnyId);
			map.setString("ar_month", essServices.retrieveApplyArMonth(map));
			resultCount = services.retrieveApplyPersonCnt(map) ;
			for(int i = 1 ; i <= applySize ; i ++)		
			{
				map.clear() ;
				map.setString("empId", parameterObject.getString("person_id_" + i)) ;
				map.setString("applyDate", parameterObject.getString("otDate_" + i)) ;//班次日期
				map.setString("cpnyId", cpnyId);
				map.setString("ar_month", essServices.retrieveApplyArMonth(map) ) ;
				
				essOverTime = (EssOverTimeBean)services.retrieveApplyOTPerson(map) ;
				List affirmorList = essServices.getAffirmorList(essOverTime.getPerson_id(), "OtApply", 99,essSysparam.isContainsOwner());
				EssAffirmor essAffirmor = new EssAffirmor();
				String AffirmData="";
				for(int j=0;j<affirmorList.size();j++){
					essAffirmor=(EssAffirmor)affirmorList.get(j);
					AffirmData+=essAffirmor.getAffirmLevel()+essAffirmor.getAffirmorName()+"<br>";
				}
				if(AffirmData.equals("")){
					essOverTime.setAffirmData(null);	
				}else{
					essOverTime.setAffirmData(AffirmData.substring(0,AffirmData.length()-4));
				}				
				essOverTime.setPerson_id(parameterObject.getString("person_id_" + i));
				essOverTime.setCk(parameterObject.getString("ck_" + i)) ;
				essOverTime.setOtNightFlag(parameterObject.getInt("otNightFlag_" + i)) ;
				essOverTime.setOtFromTime(parameterObject.getString("otFromTime_" + i)) ;
				essOverTime.setOtToTime(parameterObject.getString("otToTime_" + i)) ;
				essOverTime.setOtTypeCode(parameterObject.getString("otTypeCode_" + i)) ;
				
				if (essOverTime.getOtTypeCode().equals("WorkingOtType02")){
					essOverTime.setOtNextDays(0) ;
				}else{
					essOverTime.setOtNextDays(parameterObject.getInt("otNextDays_" + i)) ;
				}
				
//				essOverTime.setOtDeduct(parameterObject.getDouble("otDeduct_" + i)) ;
				essOverTime.setOtLength(parameterObject.getDouble("otLength_" + i)) ;
				essOverTime.setForceType(parameterObject.getString("forceType_" + i) != null?true:false) ;
				essOverTime.setOtRemark(parameterObject.getString("otRemark_" + i)) ;
				essOverTime.setCreatedBy(adminId) ;
				essOverTime.setApplyLimtidtime(parameterObject.getString("applyLimtidtime_"+i));
				
				SimpleMap arMonthObj = essArDAO.getArMonthDate(essOverTime);
				essArDAO.getPersonOtTime(essOverTime, arMonthObj, "arMonth") ;
//				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + essOverTime.getAppply_ot()) ;
				
				applyList.add(essOverTime) ;
			}
			
			Iterator iterator = applyList.iterator();

			// 根据申请参数对加班申请进行验证
			while (iterator.hasNext()) {

				EssOverTimeBean overTimeBean = (EssOverTimeBean) iterator.next();
				
				// 验证申请(只取得第一个不符和条件的信息)
				if (overTimeBean.getCk() != null && overTimeBean.getCk().length() > 0) {
					if(overTimeBean.getAffirmData() == null || overTimeBean.getAffirmData().length() == 0)
					{
						result = new HashMap() ;
						result.put("errcode", new Integer(17));
						result.put("erremp", overTimeBean.getChineseName());
					}
					else
					{
						result = essServices.getEssStrategyIntf().otApplyChecker(overTimeBean);//
					}
					
					//20190611 周末可多次申请加班限制取消
					if (((Integer) result.get("errcode")).intValue() != 0&&((Integer) result.get("errcode")).intValue() != 7)						
					//if (((Integer) result.get("errcode")).intValue() != 0)
					{
						errorMsgList.add(ErrorMessage.getErrorMessage("otApply", result, request)) ;
					}
					else{
						int affirmMaxLevel = 0 ;
						
						parameterObject.setString("OVERTIME_TYPE", essOverTime.getOtTypeCode());
						parameterObject.setString("EMPID", essOverTime.getPerson_id());
						parameterObject.setDouble("OVERTIMETOTAL", essOverTime.getAppply_ot());
						parameterObject.setString("CPNY_ID", cpnyId);
						//affirmMaxLevel =  essServices.retrieveOvertimeAffirmLevel(parameterObject,essOverTime) ;
						//System.out.println(" &&&&&&&&&&&&&&&&&&%%%%%%%%%%%%%%%%%%%%%" + affirmMaxLevel);
						
						
						
						
						
						
						overTimeBean.setApplyGroupSeq(essServices.getCurrentMaxApplyNo());
						// 添加决裁者列表
						//overTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorList(overTimeBean.getPerson_id(), "OtApply", affirmMaxLevel,essSysparam.isContainsOwner()));
						
						if(cpnyId.equals("60000000")){
							overTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorListDIY(overTimeBean.getPerson_id(), "OtApply", 99,essSysparam.isContainsOwner()));

						}else{
							overTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorList(overTimeBean.getPerson_id(), "OtApply", 99,essSysparam.isContainsOwner()));

						}
						
						
						overTimeBean.setAffirmorCount(overTimeBean.getAffirmorList().size());
//						if(overTimeBean.getAffirmorCount()==0){//决裁者人数为零时
//							
//							HashMap result19 = new HashMap();
//							result19.put("errcode", new Integer(19));
//							result19.put("erremp", overTimeBean.getChineseName());
//							errorMsgList.add(ErrorMessage.getErrorMessage("otApply", result19, request)) ;
//						}
						insertApplyList.add(overTimeBean) ;
					}				
				}
			}
			
//			 插入加班申请数据
			if (errorMsgList.isEmpty()) {
				result = essServices.doOtApplyByBatch(insertApplyList);

				errorMsgList.add(ErrorMessage.getErrorMessage("otApply", result, request)) ;

				//推送
				if (((Integer) result.get("errcode")).intValue() == 0) {
					for (EssOverTimeBean essOverTimeBean : insertApplyList) {
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_OT, essOverTimeBean.getAppply_ot() + "");
					}
				}
				//发送邮件
				if(essSysparam.isAutoSendMail() && ((Integer) result.get("errcode")).intValue() == 0)
				{				
					Iterator iter = insertApplyList.iterator();
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(1000);
					
					for(;iter.hasNext();){
						EssOverTimeBean essOverTimeBean = (EssOverTimeBean)iter.next();
						buffer.append("'"+essOverTimeBean.getOtNo()+"'");
						if(iter.hasNext()){								
							buffer.append(" , ");
						}
					}
					simpleMap.set("applyStr",buffer.toString());
					simpleMap.set("applyLevel",1);
					simpleMap.setString("applyType", "OtApply");
					List affirmorlist = essServices.getAffirmorFromEssAffirm(simpleMap);
					
					Iterator affiter = affirmorlist.iterator();
					for(;affiter.hasNext();){
						SimpleMap sm = (SimpleMap)affiter.next();
						
						simpleMap.clear();
						simpleMap.set("affirmLevel", 1);
						simpleMap.setString("affirmorId", sm.getString("AFFIRMOR_ID"));
						simpleMap.setString("applyStr", buffer.toString());
						List applyNoList = essServices.getApplyNoForMail(simpleMap);
						
						Iterator it = applyNoList.iterator();
						int applyNo[] = new int[applyNoList.size()] ;
						for (int i=0; it.hasNext();i++) {
							SimpleMap map1 = (SimpleMap) it.next();
							applyNo[i] = Integer.parseInt(map1.getString("APPLY_NO"));
						}
						this.sendOtMail(applyNo, sm.getString("AFFIRMOR_ID"), 3,adminName);
					}
				}
			}

			request.setAttribute("deductList", NumberUtil.getDoubleSerialList(24));
			request.setAttribute("errorMsgList", errorMsgList);
			request.setAttribute("applyList", applyList);
			request.setAttribute("adminId", adminId);
			request.setAttribute("c", parameterObject.getString("c")) ;
			request.setAttribute("deptID", parameterObject.getString("deptID")) ;
			request.setAttribute("key", parameterObject.getString("key")) ;
			request.setAttribute("sysDateTime", dateFormat1.format(date.getTime()));
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception e) {

			logger.error("Employee apply overtime by batch Exception. " + e);
			throw new GlRuntimeException("Employee apply overtime by batch Exception. ", e);
		}

		return "/ess_overtime_apply_batch.jsp?menu_code=" + parameterObject.getString("menu_code");
	}

	/**
	 * add leave apply
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String leaveApply(HttpServletRequest request, HttpServletResponse response) {
		try {
			String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
			String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
			String adminName=((AdminBean) request.getSession(false).getAttribute("admin")).getChineseName();
			EssSysparam essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
			EssAffirmor essAffirmor = new EssAffirmor();
			String cpny = request.getParameter("cpnyId");
			String leaveLength = "0" ;
			int affirmMaxLevel = 0 ;
			ArrayList affirmList = new ArrayList() ;
			HashMap result = null;
			EssLeaveBean leaveBean = new EssLeaveBean() ;
			List<String> errorMsgList = new ArrayList<String>();
			SimpleMap parameterObject;
			AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");

			// bind apply form data to list object
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request) ; // 得到所有页面上的参数
			
			SimpleMap map = new SimpleMap() ;
			map.setString("supervisor", adminId) ;	    // 登陆者权限
			map.setString("empId", parameterObject.getString("person_id")) ;
				
			leaveBean = (EssLeaveBean)essServices.retrieveApplyLeavePerson(map) ;
			leaveBean.setLeaveNo(new Integer(request.getParameter("ess_seq_no")));
			leaveBean.setLeaveFromDate(parameterObject.getString("leaveFromDate")) ;
			leaveBean.setLeaveFromTime(parameterObject.getString("leaveFromTime")) ;
							
			leaveBean.setLeaveToDate(parameterObject.getString("leaveToDate")) ;
			leaveBean.setLeaveToTime(parameterObject.getString("leaveToTime")) ;					
			leaveBean.setCpny(parameterObject.getString("cpnyId")) ;					
			
			leaveBean.setLeaveTypeCode(parameterObject.getString("leaveTypeCode")) ;
			leaveBean.setLeaveReason(parameterObject.getString("leaveReason")) ;
			leaveBean.setFileUploadFlag(parameterObject.getInt("fileUploadFlag")) ;

			leaveBean.setAffirmorCount(parameterObject.getInt("affirmLevel")) ;
			
			leaveBean.setBusiness(parameterObject.getString("H9business")) ;
			leaveBean.setAgentEmpId(parameterObject.getString("H9empId")) ;
			leaveBean.setAgentName(parameterObject.getString("H9name")) ;
			leaveBean.setAgentPosition(parameterObject.getString("H9position")) ;
			leaveBean.setAgentOfficePhone(parameterObject.getString("H9officePhone")) ;
			leaveBean.setAgentCellphone(parameterObject.getString("H9cellphone")) ;
			leaveBean.setOtherBusiness(parameterObject.getString("H9otherBusiness")) ;
			leaveBean.setContactMode(parameterObject.getString("H9contactMode")) ;
			
			leaveBean.setApllyTypeName("LeaveApply") ;
			leaveBean.setCreatedBy(adminId) ;
			
			leaveBean.setPerson_id(parameterObject.getString("person_id"));
				
			leaveBean.setApplyLeaveType(parameterObject.getString("ApplyLeaveType"));
			SimpleMap sm = new SimpleMap();
			sm.setString("EMPID", request.getParameter("person_id")) ;
			sm.setString("STARTDATE", request.getParameter("leaveFromDate") + " " + request.getParameter("leaveFromTime")) ;
			sm.setString("ENDDATE", request.getParameter("leaveToDate") + " " + request.getParameter("leaveToTime")) ;
			sm.setString("LEAVETYPE", request.getParameter("leaveTypeCode"));
			sm.setString("CPNY_ID", cpnyId);
			sm.setString("cpny", cpny);
			leaveLength = essServices.retrieveApplyLeaveLength(sm) ;
			
			
			
			sm.setString("LEAVELENGTH", leaveLength);
			
			affirmMaxLevel =  essServices.retrieveLeaveAffirmLevel(sm) ;
			if("60000000".equals(cpnyId)){
				affirmList = (ArrayList) essServices.getAffirmorListDIY(sm.getString("EMPID"), "LeaveApply", affirmMaxLevel,essSysparam.isContainsOwner()) ;
			}else{
				affirmList = (ArrayList) essServices.getAffirmorList(sm.getString("EMPID"), "LeaveApply", affirmMaxLevel,essSysparam.isContainsOwner()) ;
			}
			String leaveTypeCode = parameterObject.getString("leaveTypeCode");
			for(int i = 0 ; i < affirmList.size() ; ++i)//DIY 出差B1培训B3决裁者排除4388429
			{
				essAffirmor = (EssAffirmor)affirmList.get(i) ;
				if((("B1".equals(leaveTypeCode)||"B3".equals(leaveTypeCode))&&essAffirmor.getAffirmorId().equals("4388429"))){
					affirmList.remove(i);
					
				}
	
			}
			
			if(leaveBean.getAffirmorCount() == 0)
			{
				result = new HashMap() ;
				result.put("errcode", new Integer(14));
				result.put("erremp", leaveBean.getChineseName());
			}
//			else if((IsInt(leaveLength) == false )&&("63000000".equals(cpnyId)||"60000000".equals(cpnyId))&&"H9".equals(request.getParameter("leaveTypeCode"))){//针对DISD\DIY年休假必须按整天申请
//			else if((IsInt(leaveLength) == false )&&"H9".equals(request.getParameter("leaveTypeCode"))){
//				result = new HashMap() ;
//				result.put("errcode", new Integer(16));
//				result.put("erremp", leaveBean.getChineseName());
//			}
			else
			{
				result = essServices.getEssStrategyIntf().leaveApplyChecker(leaveBean);
			}
			
			if (((Integer) result.get("errcode")).intValue() != 0)
			{
				errorMsgList.add(ErrorMessage.getErrorMessage("LeaveApply", result, request)) ;
			}
			else{
//						 设置完整的休假开始结束时间
				leaveBean.setApplyGroupSeq(essServices.getCurrentMaxApplyNo());
				// 添加决裁者列表
				leaveBean.setAffirmorList(affirmList);
			}				

			// 插入休假申请数据
			if (errorMsgList.isEmpty()) {
				result = essServices.doLeaveApply(leaveBean);
				
				errorMsgList.add(ErrorMessage.getErrorMessage("LeaveApply", result, request)) ;
				
				if (admin.getCpnyId().equals("63000000")){ //DISD 申请部分勤态增加申请书编号
				     essServices.doLeaveApplyId(leaveBean);
					
					//errorMsgList.add(ErrorMessage.getErrorMessage("LeaveApply", result, request)) ;
				}
				
				if(essSysparam.isAutoSendMail() && ((Integer) result.get("errcode")).intValue() == 0)
				{
					// 工人和临时工，没有不发邮件																
					//if (leaveBean.getPostGradeCode() != null && !leaveBean.getPostGradeCode().equals("C_12004_1330054") && !leaveBean.getPostGradeCode().equals("C_12004_1324167"))
					//{
						this.sendLeaveApplyMail(leaveBean ,"LeaveApply", parameterObject.getString("menu_code"),adminName) ;
					//}
				}
				//推送
				if (((Integer) result.get("errcode")).intValue() == 0) {
					DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_LEAVE,leaveBean.getLeaveNo() + "");
				}
			}
			
			// DISD年假申请书的其他信息
			if (admin.getCpnyId().equals("63000000")){
				essServices.saveLeaveH9Info(leaveBean) ;
			}
			
			AnnualBean annualBean= new AnnualBean();
			Annual annual=  (Annual)annualBean.empAnnualLeft(leaveBean.getPerson_id(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            double allTotVaction=annual.getAnnualHours();
            double lastTotVaction=annual.getAnnualHoursLeft();
			request.setAttribute("errorMsgList", errorMsgList);
			request.setAttribute("adminId", adminId);
			request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("essLeaveBean", leaveBean);
			request.setAttribute("allTotVaction", allTotVaction);
			request.setAttribute("lastTotVaction", lastTotVaction);
			
//			有薪病假
			SimpleMap simpleMap = (SimpleMap)annualBean.retriveSickLeave(StringUtil.checkNull(request.getParameter("person_id"),admin.getAdminID()));
			request.setAttribute("totalSickleave", simpleMap.getDouble("totalSickLeaveHours"));
			request.setAttribute("sickLeaveLeft", simpleMap.getDouble("sickLeaveLeft"));
			if(simpleMap.getString("cpnyId")==admin.getCpnyId()||simpleMap.getString("cpnyId").equals(admin.getCpnyId())){
				request.setAttribute("flag", "true");
			}else{
				request.setAttribute("flag", "false");
			}
			
//			团聚假
			SimpleMap simpleMap1 = (SimpleMap)annualBean.retriveReuniteLeave(StringUtil.checkNull(request.getParameter("person_id"),admin.getAdminID()));
			request.setAttribute("totalReuniteleave", simpleMap1.getDouble("totalReuniteLeaveHours"));
			request.setAttribute("reuniteLeaveLeft", simpleMap1.getDouble("reuniteLeaveLeft"));
			if(simpleMap1.getString("cpnyId")==admin.getCpnyId()||simpleMap1.getString("cpnyId").equals(admin.getCpnyId())){
				request.setAttribute("flagReunite", "true");
			}else{
				request.setAttribute("flagReunite", "false");
			}
			
//			上年剩余年假
			SimpleMap simpleMap2 = (SimpleMap)annualBean.lastAnnualVacation(StringUtil.checkNull(request.getParameter("person_id"),admin.getAdminID()));
			//request.setAttribute("totalLastAnnualVacation", simpleMap2.getDouble("totalLastAnnualVacation"));
			request.setAttribute("lastAnnualVacationLeft", simpleMap2.getDouble("lastAnnualVacationLeft"));

		} catch (Exception e) {
			e.printStackTrace();
			// Logger.getLogger(getClass()).debug(e.toString());
			// throw new GlRuntimeException("Leave Apply Exception.", e);
		}
		return "/ess_leave_apply.jsp";
	}

	private String leaveApplyBatch(HttpServletRequest request, HttpServletResponse response) {
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		String adminName=((AdminBean) request.getSession(false).getAttribute("admin")).getChineseName();
		EssSysparam essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		
		HashMap result = null;
		List<String> errorMsgList = new ArrayList<String>();
		SimpleMap parameterObject= new SimpleMap();
		List<EssLeaveBean> applyList = new ArrayList<EssLeaveBean>() ; // 申请人数据
		List<EssLeaveBean> insertApplyList = new ArrayList<EssLeaveBean>() ; // 插入申请人数据
		AdminBean admin=(AdminBean)request.getSession(false).getAttribute("admin");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
			// bind apply form data to list object
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request) ; // 得到所有页面上的参数

			// 回填数据
			int applySize = parameterObject.getInt("applySize") ; //参数集合大小
			// 取得数据行数
			int resultCount = 0 ;
			EssLeaveBean leaveBean = new EssLeaveBean() ;
			
			SimpleMap map = new SimpleMap() ;
			map.setString("supervisor", adminId) ;	    // 登陆者权限
			map.setString("deptID", parameterObject.getString("deptID")) ;
			map.setString("key", parameterObject.getString("key")) ;
			map.set("apply_date", dateFormat.format(date.getTime()));
			resultCount = essServices.retrieveApplyPersonCnt(map) ;
			
			for(int i = 1 ; i <= applySize ; i ++)		
			{
				map.setString("empId", parameterObject.getString("person_id_" + i)) ;	
				leaveBean = (EssLeaveBean)essServices.retrieveApplyLeavePerson(map) ;
				
				leaveBean.setCk(StringUtil.checkNull(parameterObject.getString("ck_" + i))) ;
				leaveBean.setLeaveFromDate(parameterObject.getString("leaveFromDate_" + i)) ;
				leaveBean.setLeaveFromTime(parameterObject.getString("leaveFromTime_" + i)) ;
								
				leaveBean.setLeaveToDate(parameterObject.getString("leaveToDate_" + i)) ;
				leaveBean.setLeaveToTime(parameterObject.getString("leaveToTime_" + i)) ;					
				leaveBean.setCpny(parameterObject.getString("cpnyId_" + i)) ;					
				
				leaveBean.setLeaveTypeCode(parameterObject.getString("leaveTypeCode_" + i)) ;
				leaveBean.setLeaveReason(parameterObject.getString("leaveReason_" + i)) ;
				leaveBean.setApplyLeaveType(parameterObject.getString("ApplyLeaveType_" + i));
				leaveBean.setCreatedBy(adminId) ;
				AnnualBean annualBean= new AnnualBean();
				SimpleMap vactionList= new SimpleMap();
				Annual annual=  (Annual)annualBean.empAnnualLeft(leaveBean.getPerson_id(),new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	            double allTotVaction=annual.getAnnualHours();
	            double lastTotVaction=annual.getAnnualHoursLeft();
	            vactionList.set("allTotVaction", StringUtil.checkNull(allTotVaction+"天"));
	            vactionList.set("lastTotVaction", StringUtil.checkNull(lastTotVaction+"天"));
	            List list = new ArrayList();
	            list.add(vactionList);
	            leaveBean.setVactionList(list);		
				
				applyList.add(leaveBean) ;
			}
			
			Iterator iterator = applyList.iterator();

//			 根据申请参数对加班申请进行验证
			while (iterator.hasNext()) {

				EssLeaveBean essLeave = (EssLeaveBean) iterator.next();

//				 验证申请(只取得第一个不符和条件的信息)
				if (essLeave.getCk() != null && essLeave.getCk().length() > 0) {
					int affirmMaxLevel = 0 ;				
					String leaveLength = "0" ;
					EssServices essServices = new EssServices() ;					
					List affirmList = new ArrayList() ;
			    	
					// create parameter object
					SimpleMap parameterObject1 = new SimpleMap();
					parameterObject1.setString("EMPID", essLeave.getPerson_id()) ;
					parameterObject1.setString("STARTDATE", essLeave.getLeaveFromDate() + " " + essLeave.getLeaveFromTime()) ;
					parameterObject1.setString("ENDDATE", essLeave.getLeaveToDate() + " " +essLeave.getLeaveToTime()) ;
					parameterObject1.setString("LEAVETYPE", essLeave.getLeaveTypeCode());
					parameterObject1.set("CPNY_ID",admin.getCpnyId());
					leaveLength = essServices.retrieveApplyLeaveLength(parameterObject1) ;
					parameterObject1.setString("LEAVELENGTH", leaveLength);
					
					affirmMaxLevel =  essServices.retrieveLeaveAffirmLevel(parameterObject1) ;					
					affirmList = essServices.getAffirmorList(parameterObject1.getString("EMPID"), "LeaveApply", affirmMaxLevel,essSysparam.isContainsOwner()) ;
					if(affirmList == null || affirmList.size() == 0)
					{
						result = new HashMap() ;
						result.put("errcode", new Integer(14));
						result.put("erremp", essLeave.getChineseName());
					}
					else
					{
						result = essServices.getEssStrategyIntf().leaveApplyChecker(essLeave);
					}
					
					if (((Integer) result.get("errcode")).intValue() != 0)
					{
						errorMsgList.add(ErrorMessage.getErrorMessage("LeaveApply", result, request)) ;
					}
					else{
//						 设置完整的休假开始结束时间
						essLeave.setApplyGroupSeq(essServices.getCurrentMaxApplyNo());
						// 添加决裁者列表
						essLeave.setAffirmorList((ArrayList)affirmList);
						essLeave.setAffirmorCount(affirmList.size());
						insertApplyList.add(essLeave) ;
					}				
				}
			}

			// 插入休假申请数据
			if (errorMsgList.isEmpty()) {
				result = essServices.doLeaveApplyByBatch(insertApplyList);
				errorMsgList.add(ErrorMessage.getErrorMessage("LeaveApply", result, request)) ;

				//推送
				if (((Integer) result.get("errcode")).intValue() == 0) {
					for (EssLeaveBean essLeaveBean: insertApplyList) {
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_LEAVE,essLeaveBean.getLeaveNo() + "");
					}
				}

				if(essSysparam.isAutoSendMail() && ((Integer) result.get("errcode")).intValue() == 0)
				{
					
					Iterator iter = insertApplyList.iterator();
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					
					for(;iter.hasNext();){
						EssLeaveBean essLeaveBean = (EssLeaveBean)iter.next();
						buffer.append("'"+essLeaveBean.getLeaveNo()+"'");
						if(iter.hasNext()){								
							buffer.append(" , ");
						}
					}
					simpleMap.set("applyStr",buffer.toString());
					simpleMap.set("applyLevel",1);
					simpleMap.setString("applyType", "LeaveApply");
					List affirmorlist = essServices.getLeaveAffirmorEssAffirm(simpleMap);
					
					Iterator affiter = affirmorlist.iterator();
					for(;affiter.hasNext();){
						SimpleMap sm = (SimpleMap)affiter.next();
						simpleMap.clear();
						simpleMap.set("affirmLevel", 1);
						simpleMap.setString("affirmorId", sm.getString("AFFIRMOR_ID"));
						simpleMap.setString("applyStr", buffer.toString());
						List applyNoList = essServices.getLeaveApplyNoForMail(simpleMap);
						
						Iterator it = applyNoList.iterator();
						int applyNo[] = new int[applyNoList.size()] ;
						for (int i=0; it.hasNext();i++) {
							SimpleMap map1 = (SimpleMap) it.next();
							applyNo[i] = Integer.parseInt(map1.getString("APPLY_NO"));
						}
						this.sendLeaveMail(applyNo, sm.getString("AFFIRMOR_ID"), 3,adminName);
					}
				}
			}

			request.setAttribute("timeList", DateUtil.getTimeList());
			request.setAttribute("errorMsgList", errorMsgList);
			request.setAttribute("applyList", applyList);
			request.setAttribute("adminId", adminId);
			request.setAttribute("c", parameterObject.getString("c")) ;
			request.setAttribute("deptID", parameterObject.getString("deptID")) ;
			request.setAttribute("key", parameterObject.getString("key")) ;
			
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
	
		} catch (Exception e) {

			logger.error("Employee apply leave by batch Exception. " + e);
			throw new GlRuntimeException("Employee apply leave by batch Exception. ", e);
		}

		return "/ess_leave_apply_batch.jsp?menu_code=" + parameterObject.getString("menu_code") ;
	}
	
	/**
	 * add overtiem toplimit apply by batch
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String overtimeTopLimitApplyBatch(HttpServletRequest request, HttpServletResponse response) {
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		EssSysparam essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		
		HashMap result = null;
		List<String> errorMsgList = new ArrayList<String>();
		SimpleMap parameterObject;
		List<EssOverTimeBean> applyList = new ArrayList<EssOverTimeBean>() ; // 申请人数据
		List<EssOverTimeBean> insertApplyList = new ArrayList<EssOverTimeBean>() ; // 插入申请人数据
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
			// bind apply form data to list object
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request) ; // 得到所有页面上的参数
			
			// 回填数据
			int applySize = parameterObject.getInt("applySize") ; //参数集合大小
			// 取得数据行数
			int resultCount = 0 ;
			EssOverTimeBean essOverTime = new EssOverTimeBean() ;
			SimpleMap map = new SimpleMap() ;
			map.setString("supervisor", adminId) ;	    // 登陆者权限
			resultCount = essServices.retrieveApplyPersonCnt(map) ;
			for(int i = 1 ; i <= applySize ; i ++)		
			{
				map.setString("empId", parameterObject.getString("empId_" + i)) ;
				map.setString("otPlanMonth", parameterObject.getString("otPlanMonth_" + i)) ;
				
				essOverTime = (EssOverTimeBean)essServices.retrieveApplyOTTopLimitPerson(map) ;
				
				essOverTime.setCk(parameterObject.getString("ck_" + i)) ;
				if(parameterObject.getString("otLength_" + i) != null && parameterObject.getString("otLength_" + i).length() > 0)
					essOverTime.setOtLength(parameterObject.getDouble("otLength_" + i)) ;
				essOverTime.setOtRemark(parameterObject.getString("otRemark_" + i)) ;
				essOverTime.setCreatedBy(adminId) ;
				
				applyList.add(essOverTime) ;
			}
			
			Iterator iterator = applyList.iterator();

			// 根据申请参数对加班申请进行验证
			while (iterator.hasNext()) {

				EssOverTimeBean overTimeBean = (EssOverTimeBean) iterator.next();
				// 验证申请(只取得第一个不符和条件的信息)
				if (overTimeBean.getCk() != null && overTimeBean.getCk().length() > 0) {
					if(overTimeBean.getAffirmData() == null || overTimeBean.getAffirmData().length() == 0)
					{
						result = new HashMap() ;
						result.put("errcode", new Integer(2));
						result.put("erremp", overTimeBean.getChineseName());
					}
					else
					{
						result = new HashMap() ;
						result.put("errcode", new Integer(0));
					}
					
					if (((Integer) result.get("errcode")).intValue() != 0)
					{
						errorMsgList.add(ErrorMessage.getErrorMessage("OtTopLimitApply", result, request));
					}
					else
					{
						overTimeBean.setApplyGroupSeq(essServices.getCurrentMaxApplyNo());
						// 添加决裁者列表
						overTimeBean.setAffirmorList((ArrayList) essServices.getAffirmorList(overTimeBean.getEmpId(), "OtTopLimitApply", 99,essSysparam.isContainsOwner()));
						overTimeBean.setAffirmorCount(overTimeBean.getAffirmorList().size());
						insertApplyList.add(overTimeBean) ;
					}				
				}
			}

			// 插入加班申请数据
			if (errorMsgList.isEmpty()) {
				result = essServices.doOtTopLimitApplyByBatch(insertApplyList) ;
				errorMsgList.add(ErrorMessage.getErrorMessage("OtTopLimitApply", result, request));
				
				if(essSysparam.isAutoSendMail() && ((Integer) result.get("errcode")).intValue() == 0 )
				{
					int size = insertApplyList.size() ;
					for(int i = 0 ; i < size ; i ++)
					{
						essOverTime = (EssOverTimeBean)insertApplyList.get(i) ;
						this.sendOvertimeTopLimitApplyMail(essOverTime ,"OtTopLimitApply", parameterObject.getString("menu_code")) ;
					}
				}
			}
			List planMonthList = essServices.retrieveApplyOTTopLimitMonthList(parameterObject);
			
			request.setAttribute("planMonth", parameterObject.getString("planMonth"));
			request.setAttribute("planMonthList", planMonthList);
			request.setAttribute("errorMsgList", errorMsgList);
			request.setAttribute("applyList", applyList);
			request.setAttribute("adminId", adminId);
			
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);

		} catch (Exception e) {

			logger.error("Employee apply overtime toplimit by batch Exception. " + e);
			throw new GlRuntimeException("Employee apply overtime toplimit by batch Exception. ", e);
		}

		return "/ess_overtime_toplimit_apply_batch.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	public boolean IsInt(String str) {
		try {
			int i = Integer.parseInt(str);
			System.out.println("你输入的整数是 " + i);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("你输入的不是整数…… ");
			return false;
		}
	} 
	
// 上传文件
	public String upLoadFile(HttpServletRequest request, HttpServletResponse response) {

		String errorMsg="";
		List fileItems = null;
		DiskFileUpload dfu = new DiskFileUpload();
			
			String fileName = request.getParameter("fileName");
			
			try {
				fileItems = dfu.parseRequest(request);				
				Iterator iter = null;
				
				if(fileItems!=null){
					iter = fileItems.iterator();
					 while (iter.hasNext()) {						
						    FileItem item = (FileItem)iter.next();
						    
						    if (!item.isFormField() && !item.getName().equals("")){
						    	
						    	ServletContext application = request.getSession().getServletContext();
						    	String filepath = "/upload/LeaveApplyFile"; 						    	
						    	String path = application.getRealPath(filepath);
						    	File file = null;						    	
						    	int start = item.getName().lastIndexOf("\\"); 
						    	String name =item.getName().substring(start+1);
						    	file = new File(path); 
						    	
						    	if(!file.exists()){
						    		file.mkdir();
					       		}
						    	
						 
						    	File objectfile= new File(path+"\\"+fileName+name.replaceAll("\\s*", "").substring(name.replaceAll("\\s*", "").length()-4, name.replaceAll("\\s*", "").length()));
						    	
						    	
								item.write(objectfile);
								
						    }
					 }
					 errorMsg="上传成功!";
				}
			  } catch (Exception e) {				
				  e.printStackTrace();
			  }
			 
			  
		return "ess/upLoadLeavaApplyFile.jsp?menu_code="+ request.getParameter("menu_code")+"&Directive=Directive";
	}
}
