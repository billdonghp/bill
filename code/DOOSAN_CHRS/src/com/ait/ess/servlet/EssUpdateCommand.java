package com.ait.ess.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetail;
import com.ait.ar.business.ArServices;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.business.EssServices;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.dao.AffirmDAO;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class EssUpdateCommand implements Command {

	private EssServices essServices;

	private ArServices arServices;
	
	private EssSysparam essSysparam = null;
	
	//private String cpnyId=null;
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
	//private String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
	//"http://dghr.corp.doosan.com/dic_login.jsp" ; //"http://172.16.225.240:9080/dic_login.jsp" 
	
	private String cpnyAllNameForMail="";
	
	//private AdminBean admin ;

	public EssUpdateCommand() {
		essServices = new EssServices();
		arServices = new ArServices();
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = StringUtil.checkNull(request.getParameter("content"));
		Logger.getLogger(getClass()).debug("content : " + content);
		
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
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
		//决裁
		if (content.equals("otaffirm"))
			return this.otAffirm(request);
		else if (content.equals("leaveaffirm"))
			return this.leaveAffirm(request);
		else if (content.equals("ottoplimitaffirm"))
			return this.otTopLimitAffirm(request);
		else if (content.equals("armodifyaffirm"))//考勤修改人事确认
			return this.arModifyAffirm(request);
		
		// 人事确认
		else if (content.equals("otconfirm"))
			return this.otConfirm(request, response);
		else if (content.equals("leaveconfirm"))
			return this.leaveConfirm(request, response);
		else if (content.equals("attendanceconfirm"))//支社考勤人事确认
			return this.attendanceConfirm(request, response);
		else if (content.equals("ardetailconfirm"))//考勤修改人事确认
			return this.arDetailConfirm(request, response);
		else if (content.equals("insteadAffirm"))
			return this.insteadAffirm(request);
		else if (content.equals("insteadAffirmAppoint"))
			return this.insteadAffirmAppoint(request);
		return "/error.jsp";
	}
	/**
	 * ESS/考勤决裁---加班决裁
	 * @param request
	 * @return
	 */
	private String otAffirm(HttpServletRequest request) {
		try {
			AdminBean admin = (AdminBean) request.getSession().getAttribute("admin") ;
			
			String content = "otaffirm";
			String menu_code = "ess0801";
			
			int flag = NumberUtil.parseInt(request.getParameter("flag"));
			String adminId = admin.getAdminID();
			essServices.setAdminId(adminId);
			String[] str_affirmNos = request.getParameterValues("affirmNo");
			if (str_affirmNos == null)
				str_affirmNos = new String[0];
			EssAffirmParam[] params = new EssAffirmParam[str_affirmNos.length];
			int size = str_affirmNos.length ;
			
			for (int i = 0; i < size ; i++) {
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(str_affirmNos[i]));
				int applyNo = NumberUtil.parseInt(request.getParameter("applyNo" + str_affirmNos[i]));
				v.setApplyNo(applyNo);
				v.setFromDate(request.getParameter("fromDate" + str_affirmNos[i]));
				v.setFromTime(request.getParameter("fromTime" + str_affirmNos[i]));
				v.setToDate(request.getParameter("toDate" + str_affirmNos[i]));
				v.setToTime(request.getParameter("toTime" + str_affirmNos[i]));
				v.setOtLength(NumberUtil.parseDouble(request.getParameter("otLength" + str_affirmNos[i])));
				v.setDeductTime(StringUtil.checkNull(request.getParameter("deductTime" + str_affirmNos[i]),"0"));
				v.setRemark(StringUtil.toCN(request.getParameter("remark" + str_affirmNos[i])));
				params[i] = v;
				
			}
			int result = essServices.doAffirm(params, "ot", flag);
			
			//flag 1,通过,2,否决
			if(result > 0 && essSysparam.isAutoSendMail()){
				
				if(flag == 1){
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					for(int i=0;i<params.length;i++){
						EssAffirmParam param = (EssAffirmParam)params[i];
						buffer.append("'"+param.getApplyNo()+"',");
					}
					simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
					simpleMap.setString("applyType", "OtApply");
					simpleMap.setString("curAffirmor", admin.getAdminID());
					List list = essServices.getNextAffirmor(simpleMap);
					
					if(list.size()>0){
						Iterator iterator = list.iterator();
						
						for(;iterator.hasNext();){
							SimpleMap map = (SimpleMap)iterator.next();
							
							SimpleMap simpleMap2 = new SimpleMap();
							simpleMap2.setString("affirmLevel", map.getString("AFFIRM_LEVEL"));
							simpleMap2.setString("affirmorId", map.getString("AFFIRMOR_ID"));
							simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
							List list2 = essServices.getApplyNoForMail(simpleMap2);
							
							int applyNo[] = new int[list2.size()];
							Iterator iterator2 = list2.iterator();
							for(int i= 0;iterator2.hasNext();i++){
								SimpleMap map2 = (SimpleMap)iterator2.next();
								applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								//推送
								DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_OT, map2.getString("APPLY_NO"), map.getString("AFFIRMOR_ID"));
							}
							this.sendOtMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin.getChineseName());
						}
					}
				}else if(flag ==2){
					
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					for(int i=0;i<params.length;i++){
						EssAffirmParam param = (EssAffirmParam)params[i];
						buffer.append("'"+param.getApplyNo()+"',");
					}
					simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
					List list = essServices.getOtApplyor(simpleMap);
					
					if(list.size()>0){
						Iterator iterator = list.iterator();
						
						for(;iterator.hasNext();){
							SimpleMap map = (SimpleMap)iterator.next();
							
							SimpleMap simpleMap2 = new SimpleMap();
							simpleMap2.setString("createBy", map.getString("CREATED_BY"));
							simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
							List list2 = essServices.getOtApplyNoCreateBy(simpleMap2);
							
							int applyNo[] = new int[list2.size()];
							Iterator iterator2 = list2.iterator();
							for(int i= 0;iterator2.hasNext();i++){
								SimpleMap map2 = (SimpleMap)iterator2.next();
								applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
							}
							this.sendOtMail(applyNo, map.getString("CREATED_BY"), flag,admin.getChineseName());
						}
					}
					
					
					
				}
			}
			
			return "essControlServlet?operation=view&content=otaffirm&menu_code=ess0801";
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	public void sendOtMail(int[]Applyno,String setTo,int flag,String adminName)throws Exception{
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
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
	
	public void sendLeaveMail(int[]Applyno,String setTo,int flag)throws Exception{
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
			content.append("申请人:").append(simpleMap.getString("CHINESENAME"))
			       .append("<br><br>").append("主题:").append(simpleMap.getString("APPLY_TYPE")).append("<br><br>")
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
	
	
	public void sendLeaveMail(int[]Applyno,String setTo,int flag,AdminBean admin)throws Exception{//比上面的方法多一个决裁意见
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		parameterObject.setString("adminId", admin.getAdminID());
		SimpleMap result = (SimpleMap) essServices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) essServices.applyLeaveResult1(parameterObject);
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
			
			content.append("发件人:").append(admin.getChineseName()).append("<br><br>")
			       .append("申请人:").append(simpleMap.getString("CHINESENAME")).append("<br><br>")  
			       .append("所属:").append(simpleMap.getString("DEPTNAME")).append("<br><br>")  
			       .append("主题:").append(simpleMap.getString("APPLY_TYPE")).append("<br><br>")
			       .append("申请时间:").append(simpleMap.getString("APPLY_DATE"));
			content.append("<br><br>").append("开始时间:").append(simpleMap.getString("FROM_DATE"))
				   .append("<br><br>").append("结束时间:").append(simpleMap.getString("TO_DATE"));
			content.append("<br><br>").append("勤态原因:").append(simpleMap.getString("LEAVE_REASON"));
			content.append("<br><br>").append("决裁意见:").append(StringUtil.checkNull(simpleMap.getString("AFFIRM_REMARKS")));
			
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
	
	
	
	public void sendArModifyMail(int[]Applyno,String setTo,int flag,String adminId)throws Exception{//比上面的方法多一个决裁意见
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		parameterObject.setString("adminId", adminId);
		SimpleMap result = (SimpleMap) essServices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) essServices.applyArModifyResult(parameterObject);
		SimpleMap inputData = new SimpleMap();
		String emailTitle="";	
		StringBuffer content = new StringBuffer();
		if(flag==1){
			emailTitle="考勤明细修改申请";
		}else if(flag==2){
			emailTitle="考勤明细修改申请,被否决";
		}else{
			emailTitle="考勤明细修改申请";
		}
		String emailAddress=result.getString("EMAIL");
		for(int i=0;i<applyResult.size();i++){
			SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
			content.append("姓名:").append(simpleMap.getString("CHINESENAME"));
			content.append("<br>").append("考勤日期:").append(StringUtil.checkNull(simpleMap.getString("AR_DATE_STR")));
			content.append("<br>").append("考勤班次:").append(StringUtil.checkNull(simpleMap.getString("SHIFT_NAME")));
			content.append("<br>").append("考勤项目:").append(StringUtil.checkNull(simpleMap.getString("ITEM_NAME")));
			content.append("<br>").append("修改原因:").append(StringUtil.checkNull(simpleMap.getString("REASON")));
			content.append("<br>").append("决裁意见:").append(StringUtil.checkNull(simpleMap.getString("AFFIRM_REMARKS")));
			
			content.append("<br>");
			content.append("--------------------------------------------------------------------------");
			content.append("<br>");
		}
		content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>")
		   .append("<br>"+cpnyAllNameForMail) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	
	private String otConfirm(HttpServletRequest request, HttpServletResponse response) {
		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		essServices.setAdminId(adminId);
		int result = 0;
		String[] otNos = request.getParameterValues("hidden_otno");
		String[] fromTimes = request.getParameterValues("select_fromTime");
		String[] toTimes = request.getParameterValues("select_toTime");
		String[] otToDates = request.getParameterValues("text_otToDate");
		String[] otDeducts = request.getParameterValues("select_otDeduct");
//		String[] otRemarks = request.getParameterValues("text_remark");
		EssOverTimeBean otBean = null;
		boolean batchTag = request.getParameter("batchTag") != null && request.getParameter("batchTag").equals("true");
		if (batchTag) {
			String[] selectTags1 = request.getParameterValues("ck_selectedTag");
			if (selectTags1.length > 0) {
				int[] selectTags = new int[selectTags1.length];
				for (int i = 0; i < selectTags1.length; i++) {
					selectTags[i] = Integer.parseInt(selectTags1[i]);
				}
				Arrays.sort(selectTags);
				if (flag == 1)
					otBean = new EssOverTimeBean();
				for (int i = 0; i < otNos.length; i++) {
					if (Arrays.binarySearch(selectTags, Integer.parseInt(otNos[i])) >= 0) {
						if (flag == 1) {
							otBean.setOtNo(Integer.parseInt(otNos[i]));
							otBean.setOtFromTime(fromTimes[i]);
							otBean.setOtToTime(toTimes[i]);
							otBean.setOtToDate(otToDates[i]);
							otBean.setOtDeduct(Double.parseDouble(otDeducts[i]));
							otBean.setRemark(request.getParameter("remark"+otNos[i]));
						}
						if (essServices.doConfirm(Integer.parseInt(otNos[i]), "OtApply", flag, StringUtil.toCN(request.getParameter("remark"+otNos[i]))) > 0) {
							if (flag == 1)
								essServices.doUpdateOtApplyAfterConfirm(otBean);
							result++;
						}
					}
				}
			}
		} else {
			int otno = NumberUtil.parseInt(request.getParameter("otno"));
			int p = -1;
			for (int i = 0; i < otNos.length; i++)
				if (Integer.parseInt(otNos[i]) == otno) {
					p = i;
					break;
				}
			if (flag == 1) {
				otBean = new EssOverTimeBean();
				otBean.setOtNo(otno);
				otBean.setOtFromTime(fromTimes[p]);
				otBean.setOtToTime(toTimes[p]);
				otBean.setOtToDate(otToDates[p]);
				otBean.setOtDeduct(Double.parseDouble(otDeducts[p]));
				otBean.setRemark(request.getParameter("remark"+otNos[p]));
			}
			if (essServices.doConfirm(otno, "OtApply", flag, StringUtil.toCN(request.getParameter("remark"+otNos[p]))) > 0) {
				if (flag == 1)
					essServices.doUpdateOtApplyAfterConfirm(otBean);
				result++;
			}
		}
		request.setAttribute("result", new Integer(result));
		return new EssViewCommand().viewOvertimeConfirm(request, response);
	}

	private String leaveAffirm(HttpServletRequest request) {
		try {
			AdminBean admin = (AdminBean) request.getSession().getAttribute("admin") ;
			
			String menuCode = "ess0802";

			int flag = NumberUtil.parseInt(request.getParameter("flag"));
			String adminId = admin.getAdminID();
			essServices.setAdminId(adminId);
			String[] str_affirmNos = request.getParameterValues("affirmNo");
			if (str_affirmNos == null)
				str_affirmNos = new String[0];
			EssAffirmParam[] params = new EssAffirmParam[str_affirmNos.length];
			int size = str_affirmNos.length ;
			
			for (int i = 0; i < size; i++) {
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(str_affirmNos[i]));
				int applyNo = NumberUtil.parseInt(request.getParameter("applyNo" + str_affirmNos[i]));
				v.setApplyNo(applyNo);
				v.setFromDate(request.getParameter("fromDate" + str_affirmNos[i]));
				v.setFromTime(request.getParameter("fromTime" + str_affirmNos[i]));
				v.setToDate(request.getParameter("toDate" + str_affirmNos[i]));
				v.setToTime(request.getParameter("toTime" + str_affirmNos[i]));
				v.setRemark(StringUtil.toCN(request.getParameter("remark" + str_affirmNos[i])));
				params[i] = v ;
			}
			//flag 1,通过,2,否决
			int result = essServices.doAffirm(params, "leave", flag);
			
			if(result > 0 && essSysparam.isAutoSendMail())
			{
				if(flag == 1){
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					for(int i=0;i<params.length;i++){
						EssAffirmParam param = (EssAffirmParam)params[i];
						buffer.append("'"+param.getApplyNo()+"',");

//						if("63000000".equals(admin.getCpnyId())){//添加DISD部门编号
//							simpleMap.setString("applyStr", "'"+param.getApplyNo()+"'");//
//							simpleMap.setString("applyType", "LeaveApply");//
//							simpleMap.setString("curAffirmor", admin.getAdminID());//
//							List listNextAffirmor = essServices.getLeaveNextAffirmor(simpleMap);//判断后面是否有决裁者
//							if(listNextAffirmor.size()==0){//
//								essServices.essLeaveDeptNo(param.getApplyNo());//添加DISD部门编号
//							}///
//							
//						}//
						
					}
					simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
					simpleMap.setString("applyType", "LeaveApply");
					simpleMap.setString("curAffirmor", admin.getAdminID());
					List list1 = essServices.getLeaveNextAffirmor(simpleMap);
					
					if(list1.size()>0){
						Iterator iterator = list1.iterator();
						SimpleMap simpleMap2 = new SimpleMap();
						for(;iterator.hasNext();){
							SimpleMap map = (SimpleMap)iterator.next();
							simpleMap2.setString("affirmLevel", map.getString("AFFIRM_LEVEL"));
							simpleMap2.setString("affirmorId", map.getString("AFFIRMOR_ID"));
							simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
							List list2 = essServices.getLeaveApplyNoForMail(simpleMap2);
							
							int applyNo[] = new int[list2.size()];
							Iterator iterator2 = list2.iterator();
							for(int i= 0;iterator2.hasNext();i++){
								SimpleMap map2 = (SimpleMap)iterator2.next();
								applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								//推送
								DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_LEAVE, map2.getString("APPLY_NO"), map.getString("AFFIRMOR_ID"));
							}
							this.sendLeaveMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin);//加了adminid参数调用不同的方法，增加决裁意见
						}
					//}else{//如果是最后一个决裁者，决裁后添加DISD部门编号
					//	for(int i=0;i<params.length;i++){
					//		EssAffirmParam param = (EssAffirmParam)params[i];
					//		essServices.essLeaveDeptNo(param.getApplyNo());
					//	}
						
					}
				}else if(flag == 2){
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					for(int i=0;i<params.length;i++){
						EssAffirmParam param = (EssAffirmParam)params[i];
						buffer.append("'"+param.getApplyNo()+"',");
					}
					simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
					List list1 = essServices.getLeaveApplyor(simpleMap);
					
					if(list1.size()>0){
						Iterator iterator = list1.iterator();
						SimpleMap simpleMap2 = new SimpleMap();
						for(;iterator.hasNext();){
							SimpleMap map = (SimpleMap)iterator.next();
							simpleMap2.setString("createBy", map.getString("CREATED_BY"));
							simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
							List list2 = essServices.getLeaveApplyNoCreateBy(simpleMap2);
							
							int applyNo[] = new int[list2.size()];
							Iterator iterator2 = list2.iterator();
							for(int i= 0;iterator2.hasNext();i++){
								SimpleMap map2 = (SimpleMap)iterator2.next();
								applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
							}
							this.sendLeaveMail(applyNo, map.getString("CREATED_BY"), flag,admin);
						}
					}
				}
			}
			
			return "essControlServlet?operation=view&content=leaveaffirm&menu_code=ess0802";
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
			return "/error.jsp";
		}
	}
	
	private String arModifyAffirm(HttpServletRequest request) {
		try {
			AdminBean admin = (AdminBean) request.getSession().getAttribute("admin") ;
			
			String menuCode = "ess0809";

			int flag = NumberUtil.parseInt(request.getParameter("flag"));
			String adminId = admin.getAdminID();
			essServices.setAdminId(adminId);
			String[] str_affirmNos = request.getParameterValues("affirmNo");
			if (str_affirmNos == null)
				str_affirmNos = new String[0];
			EssAffirmParam[] params = new EssAffirmParam[str_affirmNos.length];
			int size = str_affirmNos.length ;
			
			for (int i = 0; i < size; i++) {
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(str_affirmNos[i]));
				int applyNo = NumberUtil.parseInt(request.getParameter("applyNo" + str_affirmNos[i]));
				v.setApplyNo(applyNo);
				//v.setFromDate(request.getParameter("fromDate" + str_affirmNos[i]));
				//v.setFromTime(request.getParameter("fromTime" + str_affirmNos[i]));
				//v.setToDate(request.getParameter("toDate" + str_affirmNos[i]));
				//v.setToTime(request.getParameter("toTime" + str_affirmNos[i]));
				v.setRemark(StringUtil.toCN(request.getParameter("remark" + str_affirmNos[i])));
				params[i] = v ;
			}
			//flag 1,通过,2,否决
			
			int result = essServices.doArModifyAffirm(params,flag);
			
			if(result > 0 && essSysparam.isAutoSendMail())
			{
				if(flag == 1){
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					for(int i=0;i<params.length;i++){
						EssAffirmParam param = (EssAffirmParam)params[i];
						buffer.append("'"+param.getApplyNo()+"',");
						
					}
					simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
					//simpleMap.setString("applyType", "LeaveApply");
					simpleMap.setString("curAffirmor", admin.getAdminID());
					List list1 = essServices.getArModifyNextAffirmor(simpleMap);
					
					if(list1.size()>0){
						Iterator iterator = list1.iterator();
						SimpleMap simpleMap2 = new SimpleMap();
						for(;iterator.hasNext();){
							SimpleMap map = (SimpleMap)iterator.next();
							simpleMap2.setString("affirmLevel", map.getString("AFFIRM_LEVEL"));
							simpleMap2.setString("affirmorId", map.getString("AFFIRMOR_ID"));
							simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
							List list2 = essServices.getArModifyApplyNoForMail(simpleMap2);
							
							int applyNo[] = new int[list2.size()];
							Iterator iterator2 = list2.iterator();
							for(int i= 0;iterator2.hasNext();i++){
								SimpleMap map2 = (SimpleMap)iterator2.next();
								applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));

								//推送
								DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_AR,map2.getString("APPLY_NO"),map.getString("AFFIRMOR_ID"));
							}
							
							//隐藏  修改
							this.sendArModifyMail(applyNo, map.getString("AFFIRMOR_ID"), flag,adminId);//加了adminid参数调用不同的方法，增加决裁意见
						}
						//  开始
					}else{//如果是最后一个决裁者，决裁后添加DISD部门编号
						for(int i=0;i<params.length;i++){
							EssAffirmParam param = (EssAffirmParam)params[i];
							essServices.essLeaveDeptNo(param.getApplyNo());
						}
						//结束
					}
				}
				//开始
				else if(flag == 2){
					SimpleMap simpleMap = new SimpleMap();
					StringBuffer buffer = new StringBuffer(100);
					for(int i=0;i<params.length;i++){
						EssAffirmParam param = (EssAffirmParam)params[i];
						buffer.append("'"+param.getApplyNo()+"',");
					}
					simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
					List list1 = essServices.getLeaveApplyor(simpleMap);
					
					if(list1.size()>0){
						Iterator iterator = list1.iterator();
						SimpleMap simpleMap2 = new SimpleMap();
						for(;iterator.hasNext();){
							SimpleMap map = (SimpleMap)iterator.next();
							simpleMap2.setString("createBy", map.getString("CREATED_BY"));
							simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
							List list2 = essServices.getLeaveApplyNoCreateBy(simpleMap2);
							
							int applyNo[] = new int[list2.size()];
							Iterator iterator2 = list2.iterator();
							for(int i= 0;iterator2.hasNext();i++){
								SimpleMap map2 = (SimpleMap)iterator2.next();
								applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
							}
//							this.sendLeaveMail(applyNo, map.getString("CREATED_BY"), flag,adminId);
							this.sendLeaveMail(applyNo, map.getString("CREATED_BY"), flag);
						}
					}
				}
				
				//结束
			}
			
			return "essControlServlet?operation=view&content=armodifyaffirm&menu_code=ess0809";
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
			return "/error.jsp";
		}
	}

	private void SendLeaveAffirmMail(int leaveNo, String applyType, int flag, String menu_code, AdminBean admin) throws Exception {
		//flag 1,通过,2,否决
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setInt("LEAVE_NO", leaveNo);
		parameterObject.setString("APPLY_TYPE", applyType);
		
		SimpleMap inputData = new SimpleMap();
		StringBuffer content = new StringBuffer();
		
		SimpleMap apply  = (SimpleMap) essServices.getLeaveApplyInfoForEmail(parameterObject);
		SimpleMap affirm = (SimpleMap) essServices.getLeaveAffirmInfoForEmail(parameterObject);
		
		if (apply.getString("POST_GRADE_CODE") == null || apply.getString("POST_GRADE_CODE").equals("C_12004_1330054") || apply.getString("POST_GRADE_CODE").equals("C_12004_1324167"))
		{
			return ;
		}
		
		if(flag == 1)
		{
			if (affirm != null) {
				
				content.append(" 申请人：").append(affirm.getString("LEAVE_CHINESENAME"))
					   .append("<br><br>").append(" 主题：").append(affirm.getString("SUBAPPLYNAME"))
					   .append("<br><br>").append(" 申请时间：").append(affirm.getString("APPLY_TIME"))
					   .append("<br><br>").append(" 开始时间：").append(affirm.getString("START_TIME"))
					   .append("<br><br>").append(" 结束时间：").append(affirm.getString("END_TIME"))
					   .append("<br><br>").append("<a href=\"").append(url).append("\" target=\"_blank\">点击此处登陆</a>");

				inputData.setString("EMAIL_TITLE", affirm.getString("LEAVE_CHINESENAME") + "," + affirm.getString("SUBAPPLYNAME") + " 勤态申请") ;

				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
				// set email type
				inputData.setString("EMAIL_TP", "H");

				inputData.setString("RCVR_EMAIL_ADDR", affirm.getString("AFFIRM_EMAIL"));

				// set biz type is attendance
				inputData.setString("BIZ_TP", "A");
				// set mail status
				inputData.setString("SND_STAT", "N");

				
			}
			else
			{
				content.append(" 申请人：").append(apply.getString("LEAVE_CHINESENAME"))
				       .append("<br><br>").append(" 主题：").append(apply.getString("SUBAPPLYNAME"))
					   .append("<br><br>").append(" 申请时间：").append(apply.getString("APPLY_TIME"))
					   .append("<br><br>").append(" 开始时间：").append(apply.getString("START_TIME"))
					   .append("<br><br>").append(" 结束时间：").append(apply.getString("END_TIME"))
					   .append("<br><br>").append("<a href=\"").append(url).append("\" target=\"_blank\">申请已通过,点击此处登陆</a>");

				inputData.setString("EMAIL_TITLE", apply.getString("LEAVE_CHINESENAME") + "," + apply.getString("SUBAPPLYNAME") + " 勤态申请,已通过") ;

				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
				// set email type
				inputData.setString("EMAIL_TP", "H");
	
				inputData.setString("RCVR_EMAIL_ADDR", apply.getString("APPLY_EMAIL"));
	
				// set biz type is attendance
				inputData.setString("BIZ_TP", "A");
				// set mail status
				inputData.setString("SND_STAT", "N");
			}
			
			new Mail().sendMail(inputData) ;
			
		}
		else
		{
			content.append(" 申请人：").append(apply.getString("LEAVE_CHINESENAME"))
			   	   .append("<br><br>").append(" 主题：").append(apply.getString("SUBAPPLYNAME"))
			   	   .append("<br><br>").append(" 申请时间：").append(apply.getString("APPLY_TIME"))
			   	   .append("<br><br>").append(" 开始时间：").append(apply.getString("START_TIME"))
			   	   .append("<br><br>").append(" 结束时间：").append(apply.getString("END_TIME"))
			   	   .append("<br><br>").append("<a href=\"").append(url).append("\" target=\"_blank\">申请被否决,点击此处登陆</a>");

			inputData.setString("EMAIL_TITLE", apply.getString("LEAVE_CHINESENAME") + "," + apply.getString("SUBAPPLYNAME") + " 勤态申请,被否决") ;

			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());
			// set email type
			inputData.setString("EMAIL_TP", "H");

			inputData.setString("RCVR_EMAIL_ADDR", apply.getString("APPLY_EMAIL"));

			// set biz type is attendance
			inputData.setString("BIZ_TP", "A");
			// set mail status
			inputData.setString("SND_STAT", "N");
			
			new Mail().sendMail(inputData) ;
		}
		//essServices.insertLeaveAffirmMail(inputData);
	}

	private String leaveConfirm(HttpServletRequest request, HttpServletResponse response) {

		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		essServices.setAdminId(adminId);
		int result = 0;
		String[] otNos = request.getParameterValues("hidden_otno");
		//String[] otRemarks = request.getParameterValues("remark");

		boolean batchTag = request.getParameter("batchTag") != null && request.getParameter("batchTag").equals("true");
		if (batchTag) {
			String[] selectTags1 = request.getParameterValues("ck_selectedTag");
			if (selectTags1.length > 0) {
				int[] selectTags = new int[selectTags1.length];
				for (int i = 0; i < selectTags1.length; i++) {
					selectTags[i] = Integer.parseInt(selectTags1[i]);
				}
				Arrays.sort(selectTags);
				for (int i = 0; i < otNos.length; i++) {
					if (Arrays.binarySearch(selectTags, Integer.parseInt(otNos[i])) >= 0) {
						result += essServices.doConfirm(Integer.parseInt(otNos[i]), "LeaveApply", flag, StringUtil.toCN(request.getParameter("remark"+otNos[i])));
					}
				}
			}
		} else {
			int leaveno = NumberUtil.parseInt(request.getParameter("leaveno"));
			int p = -1;
			for (int i = 0; i < otNos.length; i++)
				if (Integer.parseInt(otNos[i]) == leaveno) {
					p = i;
					break;
				}
			result += essServices.doConfirm(leaveno, "LeaveApply", flag,  StringUtil.toCN(request.getParameter("remark"+otNos[p])));
		}
		request.setAttribute("result", new Integer(result));
		if (request.getParameter("content").equals("leaveconfirm")) {
			return new EssViewCommand().viewLeaveConfirm(request, response);
		} else if (request.getParameter("content").equals("evectionconfirm")) {
			return new EssViewCommand().viewOvertimeConfirm(request, response);
		} else if (request.getParameter("content").equals("trainingconfirm")) {
			return new EssViewCommand().viewOvertimeConfirm(request, response);
		} else
			return "/error.jsp";
	}
	
	private String attendanceConfirm(HttpServletRequest request, HttpServletResponse response) {
		String menu_code = "ess0915";
		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession().getAttribute("admin")).getCpnyId();
		String deptId = ((AdminBean) request.getSession().getAttribute("admin")).getDeptID();
		essServices.setAdminId(adminId);
		int result = 0;
		String[] otNos = request.getParameterValues("hidden_otno");
		
		//String[] arDateStrNos = request.getParameterValues("ar_date_str_no");
		//String[] empIdNos = request.getParameterValues("emp_id_no");
		//String[] empNameNos = request.getParameterValues("emp_name_no");
		//String[] itemNameNos = request.getParameterValues("item_name_no");
		
		//String[] otRemarks = request.getParameterValues("remark");
		// send mail
		Mail mailUtil = null;
		mailUtil = new Mail();
		SimpleMap inputData = new SimpleMap();
		inputData.setString("主题", "考勤明细确认情况");

		// get first affimor mail
		SimpleMap parameterObject = new SimpleMap();
		
		
		

		boolean batchTag = request.getParameter("batchTag") != null && request.getParameter("batchTag").equals("true");
		if (batchTag) {
			String[] selectTags1 = request.getParameterValues("details");
			if (selectTags1.length > 0) {
				int[] selectTags = new int[selectTags1.length];
				for (int i = 0; i < selectTags1.length; i++) {
					selectTags[i] = Integer.parseInt(selectTags1[i]);
				}
				Arrays.sort(selectTags);
				for (int i = 0; i < otNos.length; i++) {
					if (Arrays.binarySearch(selectTags, Integer.parseInt(otNos[i])) >= 0) {
						result += essServices.doAttendanceConfirm(Integer.parseInt(otNos[i]), flag);
						String flagName = "";
						if(flag == 1){
							 flagName = "通过";
						}else if(flag == 2){
							 flagName = "否决";
						}
						inputData.setString(request.getParameter("emp_id_no"+otNos[i]), request.getParameter("emp_name_no"+otNos[i]) + "  " +request.getParameter("ar_date_str_no"+otNos[i]) + "  " + request.getParameter("item_name_no"+otNos[i])+ "  " + flagName + "  " + request.getParameter("remark"+otNos[i]) );
						parameterObject.setString("deptId", request.getParameter("deptid"+otNos[i]));
					}
				}
				
				
			}
		} else {
			int itemno = NumberUtil.parseInt(request.getParameter("itemno"));
			int p = -1;
			for (int i = 0; i < otNos.length; i++)
				if (Integer.parseInt(otNos[i]) == itemno) {
					p = i;
					break;
				}
			result += essServices.doAttendanceConfirm(itemno,flag);
			String flagName = "";
			if(flag == 1){
				 flagName = "通过";
			}else if(flag == 2){
				 flagName = "否决";
			}
			
			inputData.setString(request.getParameter("emp_id_no"+itemno), request.getParameter("emp_name_no"+itemno) + "  " +request.getParameter("ar_date_str_no"+itemno) + "  " + request.getParameter("item_name_no"+itemno)+ "  " + flagName  + "  " +request.getParameter("remark"+itemno)); 
			parameterObject.setString("deptId", request.getParameter("deptid"+itemno));
		}
		
		try {
			parameterObject.setString("flag", "2");
			String email = arServices.getArEmail(parameterObject);
			mailUtil.arSendMail(inputData, cpnyId, email, "考勤明细决裁情况");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.setAttribute("result", new Integer(result));
		request.setAttribute("menu_code", menu_code);
		if (request.getParameter("content").equals("leaveconfirm")) {
			return new EssViewCommand().viewLeaveConfirm(request, response);
		} else if (request.getParameter("content").equals("evectionconfirm")) {
			return new EssViewCommand().viewOvertimeConfirm(request, response);
		} else if (request.getParameter("content").equals("trainingconfirm")) {
			return new EssViewCommand().viewOvertimeConfirm(request, response);
		} else if (request.getParameter("content").equals("attendanceconfirm")){
			return new EssViewCommand().viewAttendanceConfirm(request, response);
		}else
			return "/error.jsp";
	}
	
	
	private String arDetailConfirm(HttpServletRequest request, HttpServletResponse response) {
		String menu_code = "ess0916";
		int flag = NumberUtil.parseInt(request.getParameter("flag"));
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		String cpnyId = ((AdminBean) request.getSession().getAttribute("admin")).getCpnyId();
		String deptId = ((AdminBean) request.getSession().getAttribute("admin")).getDeptID();
		essServices.setAdminId(adminId);
		int result = 0;
		String[] otNos = request.getParameterValues("hidden_otno");
		
		//String[] arDateStrNos = request.getParameterValues("ar_date_str_no");
		//String[] empIdNos = request.getParameterValues("emp_id_no");
		//String[] empNameNos = request.getParameterValues("emp_name_no");
		//String[] itemNameNos = request.getParameterValues("item_name_no");
		
		//String[] otRemarks = request.getParameterValues("remark");
		// send mail
		Mail mailUtil = null;
		mailUtil = new Mail();
		SimpleMap inputData = new SimpleMap();
		inputData.setString("主题", "考勤明细确认情况");

		// get first affimor mail
		SimpleMap parameterObject = new SimpleMap();
		
		
		

		boolean batchTag = request.getParameter("batchTag") != null && request.getParameter("batchTag").equals("true");
		if (batchTag) {
			String[] selectTags1 = request.getParameterValues("details");
			if (selectTags1.length > 0) {
				int[] selectTags = new int[selectTags1.length];
				for (int i = 0; i < selectTags1.length; i++) {
					selectTags[i] = Integer.parseInt(selectTags1[i]);
				}
				Arrays.sort(selectTags);
				for (int i = 0; i < otNos.length; i++) {
					if (Arrays.binarySearch(selectTags, Integer.parseInt(otNos[i])) >= 0) {
						result += essServices.doArDetailConfirm(Integer.parseInt(otNos[i]), flag);
						String flagName = "";
						if(flag == 1){
							 flagName = "通过";
						}else if(flag == 2){
							 flagName = "否决";
						}
						inputData.setString(request.getParameter("emp_id_no"+otNos[i]), request.getParameter("emp_name_no"+otNos[i]) + "  " +request.getParameter("ar_date_str_no"+otNos[i]) + "  " + request.getParameter("item_name_no"+otNos[i])+ "  " + flagName + "  " + request.getParameter("remark"+otNos[i]) );
						parameterObject.setInt("pkNo", Integer.parseInt(otNos[i]));
					}
				}
				
				
			}
		} else {
			int itemno = NumberUtil.parseInt(request.getParameter("itemno"));
			int p = -1;
			for (int i = 0; i < otNos.length; i++)
				if (Integer.parseInt(otNos[i]) == itemno) {
					p = i;
					break;
				}
			result += essServices.doArDetailConfirm(itemno,flag);
			String flagName = "";
			if(flag == 1){
				 flagName = "通过";
			}else if(flag == 2){
				 flagName = "否决";
			}
			
			inputData.setString(request.getParameter("emp_id_no"+itemno), request.getParameter("emp_name_no"+itemno) + "  " +request.getParameter("ar_date_str_no"+itemno) + "  " + request.getParameter("item_name_no"+itemno)+ "  " + flagName  + "  " +request.getParameter("remark"+itemno)); 
			parameterObject.setInt("pkNo", itemno);
		}
		
		try {
			parameterObject.setString("flag", "2");
			String email = arServices.getArEmail(parameterObject);
//			mailUtil.arSendMail(inputData, cpnyId, email, "考勤明细修改决裁情况");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.setAttribute("result", new Integer(result));
		request.setAttribute("menu_code", menu_code);
		if (request.getParameter("content").equals("leaveconfirm")) {
			return new EssViewCommand().viewLeaveConfirm(request, response);
		} else if (request.getParameter("content").equals("evectionconfirm")) {
			return new EssViewCommand().viewOvertimeConfirm(request, response);
		} else if (request.getParameter("content").equals("trainingconfirm")) {
			return new EssViewCommand().viewOvertimeConfirm(request, response);
		} else if (request.getParameter("content").equals("attendanceconfirm")){
			return new EssViewCommand().viewAttendanceConfirm(request, response);
		} else if (request.getParameter("content").equals("ardetailconfirm")){
			return new EssViewCommand().viewArDetailConfirm(request, response);	
		}else
			return "/error.jsp";
	}
	
	private String otTopLimitAffirm(HttpServletRequest request) {
		
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin") ;
		
		String adminId = admin.getAdminID() ;
		SimpleMap parameterObject ;
		String content = "ottoplimitaffirm";
		String menu_code = "ess0807";
		
		try {
			int flag = NumberUtil.parseInt(request.getParameter("flag"));
			String[] str_affirmNos = request.getParameterValues("affirmNo");
			if (str_affirmNos == null)
				str_affirmNos = new String[0];
			List<SimpleMap> paramsList = new ArrayList<SimpleMap>(str_affirmNos.length);

			for (int i = 0; i < str_affirmNos.length; i++) {
				parameterObject = new SimpleMap() ;
				
				parameterObject.setString("affirmNo", str_affirmNos[i]) ;
				parameterObject.setString("adminId", adminId) ;
				parameterObject.setString("applyNo", request.getParameter("applyNo" + str_affirmNos[i])) ;
				parameterObject.setString("otLength", request.getParameter("otLength" + str_affirmNos[i])) ;
				parameterObject.setString("remark", StringUtil.checkNull(request.getParameter("remark" + str_affirmNos[i]))) ;
				parameterObject.setString("applyType", "OtTopLimitApply") ;
				parameterObject.setInt("affirmFlag", flag) ;
				parameterObject.setString("topLimit", request.getParameter("topLimit" + str_affirmNos[i])) ;
			
				paramsList.add(parameterObject) ;
			}
			
			// flag == 1,通过 2,否决
			for(SimpleMap para : paramsList)
			{
				essServices.doOtTopLimitAffirm(para);
				if(flag == 1 && essSysparam.isAutoSendMail())
				{
					// 发送邮件
					this.sendOtTopLimitAffirmMail(para.getInt("applyNo"), para.getString("applyType"), flag, menu_code, admin) ;
				}
			}
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
		
		return "essControlServlet?operation=view&content=ottoplimitaffirm&menu_code=ess0807";
	}
	
	/**
	 * 
	 * @param sequence
	 * @param ApplyType
	 * @param Content
	 * @param menu_code
	 * @throws Exception
	 */
	private void sendOtTopLimitAffirmMail(int sequence, String ApplyType, int flag, String menu_code, AdminBean admin) throws Exception {
		// flag == 1,通过 2,否决
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setInt("OTTOPLIMIT_NO", sequence);
		parameterObject.setString("APPLY_TYPE", ApplyType);

		SimpleMap inputData = new SimpleMap();
		StringBuffer content = new StringBuffer();
		
		SimpleMap apply  = (SimpleMap) essServices.getOtTopLimitApplyInfoForEmail(parameterObject);
		SimpleMap affirm = (SimpleMap) essServices.getOtTopLimitAffirmInfoForEmail(parameterObject);
		
		if(flag == 1)
		{
			if (affirm != null) {

				content.append(" 申请人：").append(affirm.getString("OT_CHINESENAME"))
					   .append("<br><br>").append(" 主题：").append("加班上限申请")
					   .append("<br><br>").append(" 申请时间：").append(affirm.getString("APPLY_TIME"))
					   .append("<br><br>").append(" 申请月份：").append(affirm.getString("AR_MONTH"))
					   .append("<br><br>").append(" 申请长度：").append(affirm.getString("APPLY_LENGTN"))
					   .append("<br><br>").append("<a href=\"").append(url).append("\" target=\"_blank\">点击此处登陆</a>");
	
				// set email title
				inputData.setString("EMAIL_TITLE", affirm.getString("OT_CHINESENAME") + "," + "加班上限申请");
	
				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
	
				inputData.setString("RCVR_EMAIL_ADDR", affirm.getString("AFFIRM_EMAIL"));	
			}
			else
			{
				content.append(" 申请人：").append(apply.getString("OT_CHINESENAME"))
				       .append("<br><br>").append(" 主题：").append("加班上限申请")
					   .append("<br><br>").append(" 申请时间：").append(apply.getString("APPLY_TIME"))
					   .append("<br><br>").append(" 申请月份：").append(apply.getString("AR_MONTH"))
					   .append("<br><br>").append(" 申请长度：").append(apply.getString("APPLY_LENGTN"))
					   .append("<br><br>").append("<a href=\"").append(url).append("\" target=\"_blank\">申请已通过,点击此处进登陆系统</a>");

				// set email title
				inputData.setString("EMAIL_TITLE", apply.getString("OT_CHINESENAME") + "," + "加班上限申请,已通过");
	
				// set email content
				inputData.setString("EMAIL_CONTNT", content.toString());
	
				inputData.setString("RCVR_EMAIL_ADDR", apply.getString("APPLY_EMAIL"));	
			}
			new Mail().sendMail(inputData) ;
		}	
		else
		{
			content.append(" 否决人：").append(admin.getChineseName())
				   .append(" 申请人：").append(apply.getString("OT_CHINESENAME"))
		       	   .append("<br><br>").append(" 主题：").append("加班上限申请")
				   .append("<br><br>").append(" 申请时间：").append(apply.getString("APPLY_TIME"))
				   .append("<br><br>").append(" 申请月份：").append(apply.getString("AR_MONTH"))
				   .append("<br><br>").append(" 申请长度：").append(apply.getString("APPLY_LENGTN"))
				   .append("<br><br>").append("<a href=\"").append(url).append("\" target=\"_blank\">申请被否决,点击此处登陆</a>");

			// set email title
			inputData.setString("EMAIL_TITLE", apply.getString("OT_CHINESENAME") + "," + "加班上限申请,被否决");
	
			// set email content
			inputData.setString("EMAIL_CONTNT", content.toString());
	
			inputData.setString("RCVR_EMAIL_ADDR", apply.getString("APPLY_EMAIL"));	
			
			new Mail().sendMail(inputData) ;	
		}
		
		//essServices.insertOtAffirmMail(inputData);
	}
	
	
	private String insteadAffirm(HttpServletRequest request) {
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		AffirmDAO affirmDAO = new AffirmDAO() ;
		SimpleMap parameterObject ;
		try {
			String person_id = StringUtil.checkNull(request.getParameter("person_id")) ;
			String newAffirmId = StringUtil.checkNull(request.getParameter("newAffirmId")) ;
			String type = StringUtil.checkNull(request.getParameter("type")) ;
			String module = StringUtil.checkNull(request.getParameter("module")) ;
			
			if (type.equals("instead"))
			{
				
				boolean flag = true ;
				
				if(!affirmDAO.validateUdpateAffirm_ESS(adminId,person_id))
				{
					request.setAttribute("error", "errorType1") ;
					flag = false ;
				}
				
				if(flag && !affirmDAO.validateUdpateAffirm_ESS2(adminId,person_id))
				{
					request.setAttribute("error", "errorType2") ;
					flag = false ;
				}
				
				if (flag)
				{
					request.setAttribute("error", "errorType0") ;
					affirmDAO.udpateAffirm_ESS(adminId,person_id,adminId,module);
					String adminChinesename = "";
					String cpnyName = "";
					String personIdChinesename = "";
					String email = "";
					ResultSet rs = affirmDAO.selectChinesename(adminId);
					if(rs.next()){
					adminChinesename = rs.getString("CHINESENAME");
					cpnyName = rs.getString("CPNY_NAME");
					}
					ResultSet rs1 = affirmDAO.selectChinesename(person_id);
					if(rs1.next()){
					personIdChinesename = rs1.getString("CHINESENAME");
					email = rs1.getString("EMAIL");
					}
					//发邮件通知代决裁者
					this.sendInsteadAffirmMail(adminChinesename, personIdChinesename, email, cpnyName);
				}
			}
			else if (type.equals("recover"))
			{
				request.setAttribute("error", "errorType") ;
				affirmDAO.recoverAffirm_ESS(adminId,module) ;
			}
			
			request.setAttribute("newAffirmId", newAffirmId) ;
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
		
		return "essControlServlet?operation=view&content=insteadAffirm&menu_code=ess0808";
	}
	
	//委任
	private String insteadAffirmAppoint(HttpServletRequest request) {
		String adminId = ((AdminBean) request.getSession().getAttribute("admin")).getAdminID();
		AffirmDAO affirmDAO = new AffirmDAO() ;
		SimpleMap parameterObject ;
		try {
			String person_id = StringUtil.checkNull(request.getParameter("person_id")) ;
			String newAffirmId = StringUtil.checkNull(request.getParameter("newAffirmId")) ;
			String type = StringUtil.checkNull(request.getParameter("type")) ;
			String appointType = StringUtil.checkNull(request.getParameter("appoint")) ;
			String module="";
			
			if(appointType.equals("LeaveApply") || appointType.equals("OtApply") || appointType.equals("ArModifyApply")){
				module = "AR";	
			}else{
				module = "GA";
			}
			
			if (type.equals("insteadApp"))
			{
				
				boolean flag = true ;
				
				if(!affirmDAO.validateUdpateAffirm_ESS(adminId,person_id))
				{
					request.setAttribute("error", "errorType1") ;
					flag = false ;
				}
				
				if(flag && !affirmDAO.validateUdpateAffirm_ESS2_Appoint(adminId,person_id,appointType))
				{
					request.setAttribute("error", "errorType2") ;
					flag = false ;
				}
				
				if (flag)
				{
					request.setAttribute("error", "errorType0") ;
					affirmDAO.udpateAffirm_ESS_Appoint(adminId,person_id,adminId,module,appointType);
					String adminChinesename = "";
					String cpnyName = "";
					String personIdChinesename = "";
					String email = "";
					ResultSet rs = affirmDAO.selectChinesename(adminId);
					if(rs.next()){
					adminChinesename = rs.getString("CHINESENAME");
					cpnyName = rs.getString("CPNY_NAME");
					}
					ResultSet rs1 = affirmDAO.selectChinesename(person_id);
					if(rs1.next()){
					personIdChinesename = rs1.getString("CHINESENAME");
					email = rs1.getString("EMAIL");
					}
					//发邮件通知代决裁者
					this.sendInsteadAffirmMail(adminChinesename, personIdChinesename, email, cpnyName);
				}
			}
			else if (type.equals("recover"))
			{
				request.setAttribute("error", "errorType") ;
				affirmDAO.recoverAffirm_ESS_Appoint(adminId,appointType) ;
			}
			
			request.setAttribute("newAffirmId", newAffirmId) ;
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		}
		
		return "essControlServlet?operation=view&content=insteadAffirmAppoint&menu_code=ess0810";
	}
	
	private void sendInsteadAffirmMail(String adminId, String personId, String email, String cpnyName) throws Exception {

			SimpleMap inputData = new SimpleMap();

			StringBuffer content = new StringBuffer();
			
			content.append("发件人:").append(adminId)
				   .append("<br><br>").append(" 主题：").append("因无法及时决裁,特设置ESS决裁代理,邀请您协助ESS决裁。")
				   .append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处进行代理决裁</a>")
				   .append("<br><br>"+cpnyAllNameForMail) ;
			// set email title
			inputData.setString("EMAIL_TITLE", adminId + "邀请您协助ESS决裁。") ;

			inputData.setString("EMAIL_CONTNT", content.toString());

			inputData.setString("RCVR_EMAIL_ADDR", email);

			new Mail().sendMail(inputData) ;
		}
}
