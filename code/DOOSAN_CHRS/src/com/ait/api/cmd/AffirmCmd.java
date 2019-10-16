package com.ait.api.cmd;

import com.ait.api.model.R;
import com.ait.api.resultApi.DooPushAPI;
import com.ait.api.resultApi.KdGoldAPI;
import com.ait.api.service.ApiService;
import com.ait.api.service.impl.ApiServiceImpl;
import com.ait.api.util.AES;
import com.ait.ar.business.ArServices;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.business.EssServices;
import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.business.EvaluateApplyServices;
import com.ait.evs.business.EvsServices;
import com.ait.ga.business.*;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.web.ApplicationContext;
import com.ait.web.Command;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AffirmCmd implements Command {

	private SysService sysService;
	private EssServices essServices;
	private ExpressApplyAndAffirmServices eaaServices;
	private VisiterApplicationsServices visiterApplicationsServices;
	private EvsServices evsservices;

	private EvaluateApplyServices evServices ;
	private SealMangerSerivces smServices;
	private VisaMangerSerivces visaMangerSerivces;
	private ApiService apiService;
	private GaServices services;
	private EatingCardServices eatingCardes;
	private Mail mail;

	private String url = "http://10.40.128.28:8083/" ;

	public AffirmCmd() {
		essServices = new EssServices();
		sysService = SysService.getInstance();
		apiService = new ApiServiceImpl();
		services = new GaServices();
		eaaServices = new ExpressApplyAndAffirmServices();
		smServices = new SealMangerSerivces();
		visaMangerSerivces = new VisaMangerSerivces();
		eatingCardes = new EatingCardServices();
		visiterApplicationsServices = new VisiterApplicationsServices();
		evServices = new EvaluateApplyServices();
		evsservices = EvsServices.getInstance();
		mail = new Mail();
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paramData = this.getData(request);
		String content = StringUtil.checkNull(paramData.get("content"));
		try {
			//决裁
			if ("otAffirm".equals(content)) {
				this.otAffirm(paramData,request,response);
			} else if ("leaveAffirm".equals(content)){
				this.leaveAffirm(paramData,request,response);
			} else if ("arModifyAffirm".equals(content)){//考勤修改人事确认
				this.arModifyAffirm(paramData,request,response);
			} else if ("presentAffirm".equals(content)) {
				this.presentAffirm(paramData,request,response);
				//快件审批
			} else if ("expressAffirm".equals(content)) {
				this.expressAffirm(paramData, request, response);
				//公章审批
			} else if ("sealAffirm".equals(content)) {
				this.sealAffirm(paramData, request, response);
				//临时卡审批
			} else if ("tempCardAffirm".equals(content)) {
				this.tempCardAffirm(paramData, request, response);
				//参观者审批
			} else if ("visiterAffirm".equals(content)) {
				this.visiterAffirm(paramData, request, response);
				//签证审批
			} else if ("visaAffirm".equals(content)) {
				this.visaAffirm(paramData, request, response);
				//工人评价
			} else if ("evsAffirm".equals(content)) {
				this.doEvaluateAffrim(paramData, request, response);
				//评价成绩审批
			} else if ("evsResultAffirm".equals(content)) {
				this.doEvsResultAffrim(paramData, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append(R.toJson(R.error("审批失败")));
		}
		return null;
	}

	/**
	 * ESS/考勤决裁---加班决裁
	 */
	private void otAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List otList = this.apiService.getInfoList(paramData, "getOtInfo");
		if (otList != null && otList.size() > 0) {
			for (int j = 0; j < otList.size(); j++) {
				Map infoMap = (Map) otList.get(j);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(),infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				int flag = NumberUtil.parseInt(StringUtil.checkNull(paramData.get("flag")));
				String adminId = StringUtil.checkNull(admin.getAdminID());
				essServices.setAdminId(adminId);

				//封装参数
				EssAffirmParam[] params = new EssAffirmParam[1];
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("ESS_AFFIRM_NO"))));
				v.setApplyNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("APPLY_NO"))));
				v.setFromDate(StringUtil.checkNull(infoMap.get("OT_FROM_DATE")));
				v.setFromTime(StringUtil.checkNull(infoMap.get("OT_FROM_TIME")));
				v.setToDate(StringUtil.checkNull(infoMap.get("OT_TO_DATE")));
				v.setToTime(StringUtil.checkNull(infoMap.get("OT_TO_TIME")));
				v.setOtLength(NumberUtil.parseDouble(StringUtil.checkNull(infoMap.get("OT_LENGTH"))));
				v.setDeductTime(StringUtil.checkNull(infoMap.get("OT_DEDUCT_TIME" ),"0"));
				v.setRemark(StringUtil.toCN(StringUtil.checkNull(paramData.get("affirmRemark"))));
				params[0] = v;

				//审批处理
				int result = essServices.doAffirm(params, "ot", flag);

				//发送邮件
				if(result > 0 && getSysParam(admin.getCpnyId()).isAutoSendMail()){
					if(flag == 1){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						for(int i=0;i<params.length;i++){
							EssAffirmParam param = (EssAffirmParam)params[i];
							buffer.append("'"+param.getApplyNo()+"',");
						}
						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
						simpleMap.setString("applyType", "OtApply");
						simpleMap.setString("curAffirmor", adminId);
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
									DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_OT,map2.getString("APPLY_NO"),map.getString("AFFIRMOR_ID"));
								}
								this.sendOtMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin.getChineseName(),admin.getCpnyId());
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
								this.sendOtMail(applyNo, map.getString("CREATED_BY"), flag,admin.getChineseName(),admin.getCpnyId());
							}
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}

	//发送加班审批邮件
	public void sendOtMail(int[]Applyno,String setTo,int flag,String adminName, String cpnyId){
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
				.append("<br><br>"+ this.getEmailCpny(cpnyId));
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	//休假审批
	private void leaveAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List leaveList = this.apiService.getInfoList(paramData, "getLeaveInfo");
		if (leaveList != null && leaveList.size() > 0) {
			for (int j = 0; j < leaveList.size(); j++) {
				Map infoMap = (Map) leaveList.get(j);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(),infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				int flag = NumberUtil.parseInt(paramData.get("flag"));
				String adminId = admin.getAdminID();
				essServices.setAdminId(adminId);

				//封装参数
				EssAffirmParam[] params = new EssAffirmParam[1];
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_NO"))));
				v.setApplyNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("APPLY_NO"))));
				v.setFromDate(StringUtil.checkNull(infoMap.get("FROM_DATE")));
				v.setFromTime(StringUtil.checkNull(infoMap.get("FROM_TIME")));
				v.setToDate(StringUtil.checkNull(infoMap.get("TO_DATE")));
				v.setToTime(StringUtil.checkNull(infoMap.get("TO_TIME")));
				v.setRemark(StringUtil.toCN(StringUtil.checkNull(paramData.get("affirmRemark"))));
				params[0] = v ;

				System.out.println(v);
				//flag 1,通过,2,否决
				int result = essServices.doAffirm(params, "leave", flag);
				if(result > 0 && getSysParam(admin.getCpnyId()).isAutoSendMail())
				{
					if(flag == 1){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						for(int i=0;i<params.length;i++){
							EssAffirmParam param = params[i];
							buffer.append("'"+param.getApplyNo()+"',");
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
									DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_LEAVE,map2.getString("APPLY_NO"),map.getString("AFFIRMOR_ID"));
								}
								this.sendLeaveMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin);//加了adminid参数调用不同的方法，增加决裁意见
							}
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
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
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
				.append("<br><br>"+ this.getEmailCpny(admin.getCpnyId())) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	//考勤是修改审批
	private void arModifyAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List arList = this.apiService.getInfoList(paramData, "getArInfo");
		if (arList != null && arList.size() > 0) {
			for (int j = 0; j < arList.size(); j++) {
				Map infoMap = (Map) arList.get(j);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(),infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);
				//flag 1,通过,2,否决
				int flag = NumberUtil.parseInt(paramData.get("flag"));
				String adminId = admin.getAdminID();
				essServices.setAdminId(adminId);

				//封装参数
				EssAffirmParam[] params = new EssAffirmParam[1];
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("ESS_AFFIRM_NO"))));
				v.setApplyNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("APPLY_NO"))));
				v.setRemark(StringUtil.toCN(StringUtil.checkNull(paramData.get("affirmRemark"))));
				params[0] = v ;
				//flag 1,通过,2,否决
				int result = essServices.doArModifyAffirm(params,flag);
				if(result > 0 && this.getSysParam(admin.getCpnyId()).isAutoSendMail())
				{
					if(flag == 1){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						for(int i=0;i<params.length;i++){
							EssAffirmParam param = params[i];
							buffer.append("'"+param.getApplyNo()+"',");
						}
						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
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
								this.sendArModifyMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin);//加了adminid参数调用不同的方法，增加决裁意见
							}
						}else{//如果是最后一个决裁者，决裁后添加DISD部门编号
							for(int i=0;i<params.length;i++){
								EssAffirmParam param = (EssAffirmParam)params[i];
								essServices.essLeaveDeptNo(param.getApplyNo());
							}
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
								this.sendLeaveMail(applyNo, map.getString("CREATED_BY"), flag,admin.getCpnyId());
							}
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}

	public void sendArModifyMail(int[]Applyno,String setTo,int flag,AdminBean admin)throws Exception{//比上面的方法多一个决裁意见
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		parameterObject.setString("adminId", admin.getAdminID());
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
				.append("<br>"+this.getEmailCpny(admin.getCpnyId())) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	public void sendLeaveMail(int[]Applyno,String setTo,int flag,String cpnyId)throws Exception{
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
				.append("<br><br>"+this.getEmailCpny(cpnyId)) ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	//礼品
	public void presentAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List presentList = this.apiService.getInfoList(paramData, "getPresentInfo");
		if (presentList != null && presentList.size() > 0) {
			for (int j = 0; j < presentList.size(); j++) {
				Map infoMap = (Map) presentList.get(j);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(),infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);
				//flag 1,通过,2,否决
				String flag = StringUtil.checkNull(paramData.get("flag"));

				//封装参数
				List<SimpleMap> affirmList = new ArrayList();
				SimpleMap data = new SimpleMap("REQUEST_DATA");
				data.setString("adminId", admin.getAdminID());
				data.setString("affirmOP", "Y");
				data.setString("SEQ_NO", StringUtil.checkNull(infoMap.get("APPLY_NO")));
				data.setString("affirmNo", StringUtil.checkNull(infoMap.get("AFFIRM_NO")));
				data.setString("AFFIRM_LEVEL", StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL")));
				data.setString("AFFIRM_OPITION", StringUtil.checkNull(paramData.get("affirmRemark")));
				data.setString("AFFIRM_FLAG", flag);
				affirmList.add(data);
				//审批处理
				services.affirmPresent(affirmList);

				// 发送邮件
				MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
				String msg = messageSource.getMessage("alert.mutual.affirm_successfully");

				if (getSysParam(admin.getCpnyId()).isGaSendMail()) {
					Mail mailUtil = new Mail();
					for(SimpleMap map : affirmList) {
						String mailTitle = "礼品申请";
						data.setString("applyNO", StringUtil.checkNull(infoMap.get("APPLY_NO")));

						// 否决，发送邮件给申请者
						if(data.getInt("AFFIRM_FLAG") == 2) {
							data.setInt("applyLevel", 0);
							mailTitle = "礼品申请被否决";

						} else // 通过，发送给下级决裁者
							data.setInt("applyLevel", Integer.parseInt(map.getString("AFFIRM_LEVEL"))+1);

						List affirmMailList = (List)services.getAffirmMail(data);
						if (affirmMailList != null && affirmMailList.size() > 0 ) {
							SimpleMap affirmObj = (SimpleMap)affirmMailList.get(0);
							//send mail
							SimpleMap inputData = new SimpleMap();
							inputData.setString("主题", "礼品申请");
							inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
							inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
							inputData.setString("申请内容", affirmObj.getString("PRESENT_OBJECT") + "  " + StringUtil.checkNull(affirmObj.getString("REASON"),""));
							mailUtil.gaSendMail(inputData, admin.getCpnyId(), affirmObj.getString("EMAIL"), mailTitle);

							//推送
							DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_PRESENT,data.getString("APPLY_NO"),map.getString("PERSON_ID"));
						}else{//礼品最后一级决裁完毕发送邮件通知XX人
							if("78000000".equals(admin.getCpnyId())){
								data.setInt("applyLevel", Integer.parseInt(map.getString("AFFIRM_LEVEL")));
								List affirmMailLists = (List)services.getAffirmMail(data);
								SimpleMap affirmObj = (SimpleMap)affirmMailLists.get(0);
								SimpleMap inputData = new SimpleMap();
								inputData.setString("主题", "礼品申请  决裁通过");
								inputData.setString("申请人", affirmObj.getString("APPLY_NAME"));
								inputData.setString("申请时间", affirmObj.getString("APPLY_DATE"));
								inputData.setString("申请内容", affirmObj.getString("PRESENT_OBJECT") + "  " + StringUtil.checkNull(affirmObj.getString("REASON"),""));
								mailUtil.gaSendMail(inputData, admin.getCpnyId(), "xueyan.ma@doosan.com", mailTitle);//马雪雁  2623519  xueyan.ma@doosan.com
//						mailUtil.gaSendMail(inputData, admin.getCpnyId(), "lingyan.du@doosan.com", mailTitle);//杜玲艳  ic0732695  lingyan.du@doosan.com
							}
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}

	/* 快件决裁 */
	public void expressAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List expressList = this.apiService.getInfoList(paramData, "getExpressInfo");
		if (expressList != null && expressList.size() > 0) {
			for (int j = 0; j < expressList.size(); j++) {
				Map infoMap = (Map) expressList.get(j);

				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(),infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				String flag = StringUtil.checkNull(paramData.get("flag"));

				//封装参数
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.setString("affirmorid", admin.getAdminID());
				parameterObject.setString("flag", flag);
				parameterObject.setString("affirmno", StringUtil.checkNull(infoMap.get("APPLY_NO")));
				parameterObject.setString("affirmorIdea", StringUtil.checkNull(paramData.get("affirmRemark")));

				//审批处理
				eaaServices.confirmExpressAffirm(parameterObject);

				parameterObject.setString("applerId", StringUtil.checkNull(infoMap.get("APPLYOR_ID")));

//				parameterObject = ObjectBindUtil.getData(request);

				//否决
				if ("2".equals(flag)) {
					eaaServices.confirmExpressApply(parameterObject);
					String toEmail = eaaServices.getapplyemail(parameterObject);
					if (!toEmail.equals("") && toEmail != null && this.getSysParam(admin.getCpnyId()).isGaSendMail()) {
						sendExpressMail(
								"快件申请 已被否决",
								StringUtil.checkNull(infoMap.get("APPLYOR_ID")),
								StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
								toEmail,
								StringUtil.checkNull(infoMap.get("CITYSENDTO")),
								StringUtil.checkNull(infoMap.get("POSTDESCRIPTION")),
								admin.getCpnyId());
					}
					//通过
				} else if ("1".equals(flag)) {
					String MAX_AFFIRM_FLAG = StringUtil.checkNull(infoMap.get("MAX_AFFIRM_FLAG"));
					parameterObject.set("affrimlevel", StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL")));
					if (MAX_AFFIRM_FLAG.equals("0")) {
						String toEmail = eaaServices
								.getupaffrimemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& this.getSysParam(admin.getCpnyId()).isGaSendMail()) {
							sendExpressMail("快件申请 请您确认",
									StringUtil.checkNull(infoMap.get("APPLYOR_ID")),
									StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
									toEmail,
									StringUtil.checkNull(infoMap.get("CITYSENDTO")),
									StringUtil.checkNull(infoMap.get("POSTDESCRIPTION")),
									admin.getCpnyId());
						}
						//推送
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_EXPRESS,StringUtil.checkNull(infoMap.get("APPLY_NO")),Integer.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL"))) + 1);
					} else {
						eaaServices.confirmExpressApply(parameterObject);

						//审批完成，获取快递单
						KdGoldAPI.orderOnlineByJson(StringUtil.checkNull(infoMap.get("APPLY_NO")));
						//获取快递单结束

						String toEmail = eaaServices
								.getapplyemail(parameterObject);
						if (!toEmail.equals("") && toEmail != null
								&& this.getSysParam(admin.getCpnyId()).isGaSendMail()) {
							sendExpressMail("快件申请 已通过确认",
									StringUtil.checkNull(infoMap.get("APPLYOR_ID")),
									StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
									toEmail,
									StringUtil.checkNull(infoMap.get("CITYSENDTO")),
									StringUtil.checkNull(infoMap.get("POSTDESCRIPTION")),
									admin.getCpnyId());
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
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

		 //推送
	}

	//公章审批
	public void sealAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List sealList = this.apiService.getInfoList(paramData, "getSealInfo");
		if (sealList != null && sealList.size() > 0) {
			for (int m = 0; m < sealList.size(); m++) {
				Map infoMap = (Map) sealList.get(m);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(), infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				String flag = StringUtil.checkNull(paramData.get("flag"));

				//封装参数
				SimpleMap parameterObject = new SimpleMap();

				String defaultSysFile = "/system.properties";
				UserConfiguration userConfig = UserConfiguration.getInstance(defaultSysFile);
				String emailNameDicc = userConfig.getString("emaile.seal.dicc").trim();
				parameterObject.set("flag", flag);
				parameterObject.set("adminId", admin.getAdminID());
				parameterObject.set("affirmNo", StringUtil.checkNull(infoMap.get("AFFIRM_NO")));
				parameterObject.set("applerId", StringUtil.checkNull(infoMap.get("APPLYOR_ID")));
				parameterObject.set("affirmorIdea", StringUtil.checkNull(paramData.get("affirmRemark")));
				parameterObject.set("applyno", StringUtil.checkNull(infoMap.get("APPLY_NO")));
				smServices.updateAffirmFlag(parameterObject);
				if (flag.equals("2")) {
					smServices.confirmSealApply(parameterObject);

					this.sendCarApplyMail("公章申请 已经否决",
							StringUtil.checkNull(infoMap.get("APPLYOR_ID")),
							StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
							admin.getCpnyId(),
							StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
							StringUtil.checkNull(infoMap.get("USEDATE")),
							StringUtil.checkNull(infoMap.get("USEINFORMATION")),
							StringUtil.checkNull(infoMap.get("USESHARES")),
							StringUtil.checkNull(infoMap.get("SEALTYPE")),
							StringUtil.checkNull(paramData.get("affirmRemark")));
				} else if (flag.equals("1")) {
					// 公章最后一级决裁完毕发送邮件通知XX人（MAX_AFFIRM_FLAG 0 不是最后一级决裁者；1
					// 是最后一级决裁）
					String MAX_AFFIRM_FLAG = StringUtil.checkNull(infoMap.get("MAX_AFFIRM_FLAG"));
					parameterObject.set("affrimlevel", StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL")));
					if (MAX_AFFIRM_FLAG.equals("0")) {
						String applerId1 = smServices.getApplerId(parameterObject);
						String deptName = StringUtil.checkNull(infoMap.get("APPLYDEPTNAME"));

						this.sendCarApplyMail("公章申请 等待决裁",
								applerId1,
								StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
								admin.getCpnyId(),
								StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
								StringUtil.checkNull(infoMap.get("USEDATE")),
								StringUtil.checkNull(infoMap.get("USEINFORMATION")),
								StringUtil.checkNull(infoMap.get("USESHARES")),
								StringUtil.checkNull(infoMap.get("SEALTYPE")),
								StringUtil.checkNull(paramData.get("affirmRemark")));
						if("7509910".equals(applerId1) && !"总务/宣传课".equals(deptName)){
							this.sendCarApplyMail("申请部门领导已裁决完毕，请及时到管理部门盖章处理",
									StringUtil.checkNull(infoMap.get("APPLYOR_ID")),
									StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
									admin.getCpnyId(),
									StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
									StringUtil.checkNull(infoMap.get("USEDATE")),
									StringUtil.checkNull(infoMap.get("USEINFORMATION")),
									StringUtil.checkNull(infoMap.get("USESHARES")),
									StringUtil.checkNull(infoMap.get("SEALTYPE")),
									StringUtil.checkNull(paramData.get("affirmRemark")));
						}

						//推送
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_SEAL,StringUtil.checkNull(infoMap.get("APPLY_NO")),Integer.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL"))) + 1);
					} else {
						if ("63000000".equals(admin.getCpnyId())) {
							smServices.confirmSealApplyForDISD(parameterObject);
						} else {
							smServices.confirmSealApply(parameterObject);
							// 这五个章领导决策通过时都会邮件提醒suyue.li@doosan.com李素月
							/*if (sealTypeNumber >= 106
									&& sealTypeNumber <= 110) {
								// this.sendCarApplyMail2("公章申请 已经通过","suyue.li@doosan.com",
								// request.getParameter("adminDept_"+applyno[i]),
								// admin.getCpnyId(),
								// request.getParameter("CHINESENAME_"+applyno[i]),
								// request.getParameter("useDate_"+applyno[i]),
								// request.getParameter("useInformation_"+applyno[i]),
								// request.getParameter("useShares_"+applyno[i]),
								// request.getParameter("sealType_"+applyno[i]));
							}*/
							this.sendCarApplyMail("您的申请已经全部决裁完毕",
									StringUtil.checkNull(infoMap.get("APPLYOR_ID")),
									StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
									admin.getCpnyId(),
									StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
									StringUtil.checkNull(infoMap.get("USEDATE")),
									StringUtil.checkNull(infoMap.get("USEINFORMATION")),
									StringUtil.checkNull(infoMap.get("USESHARES")),
									StringUtil.checkNull(infoMap.get("SEALTYPE")),
									StringUtil.checkNull(paramData.get("affirmRemark")));

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
							for (int j = 0; j < emailName1.length; j++) {
								if (emailName1[j] != null && !emailName1[j].isEmpty()) {
									this.sendCarApplyMail(
													"公章申请 已通过决裁",
													emailName1[j],
											StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
											admin.getCpnyId(),
											StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
											StringUtil.checkNull(infoMap.get("USEDATE")),
											StringUtil.checkNull(infoMap.get("USEINFORMATION")),
											StringUtil.checkNull(infoMap.get("USESHARES")),
											StringUtil.checkNull(infoMap.get("SEALTYPE")),
											StringUtil.checkNull(paramData.get("affirmRemark")));
								}
							}
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
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
		if (result != null && !result.equals("") && this.getSysParam(cpny_id).isGaSendMail()) {
			mail.gaSendMail(inputData, cpny_id, result, title);
		}
	}

	/*临时卡决裁验证*/
	public void tempCardAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {

		List expressList = this.apiService.getInfoList(paramData, "getTempCardInfo");
		if (expressList != null && expressList.size() > 0) {
			for (int j = 0; j < expressList.size(); j++) {
				Map infoMap = (Map) expressList.get(j);

				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(), infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				String flag = StringUtil.checkNull(paramData.get("flag"));

				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("applyNo", StringUtil.checkNull(infoMap.get("APPLY_NO")));
				parameterObject.set("affirmNo", StringUtil.checkNull(infoMap.get("AFFIRM_NO")));
				parameterObject.set("affirmFlag", flag);
				parameterObject.set("maxAffirmFlag", StringUtil.checkNull(infoMap.get("MAX_AFFIRM_FLAG")));
				parameterObject.set("applyer", StringUtil.checkNull(infoMap.get("APPLYOR_ID")));
				parameterObject.set("adminId", admin.getAdminID());
				parameterObject.set("applyorEmail", StringUtil.checkNull(infoMap.get("APPLYOR_EMAIL")));
				parameterObject.set("applyDate", StringUtil.checkNull(infoMap.get("APPLY_DATE")));
				parameterObject.set("affirmorIdea", StringUtil.checkNull(paramData.get("affirmRemark")));
				parameterObject.set("upEmail",eatingCardes.getuptcaraffrimemail(parameterObject));
				parameterObject.set("AFFIRM_LEVEL", StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL")));

				eatingCardes.oingTempAffirm(parameterObject);
				this.oingTempAffirm(parameterObject,admin);
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}

	private void oingTempAffirm(SimpleMap parameterObject,AdminBean admin){

		try {
			SimpleMap dataMap = new SimpleMap();
			dataMap.set("主题", "临时卡申请");
			dataMap.set("申请人", parameterObject.getString("applyer"));
			dataMap.set("申请时间", parameterObject.getString("applyDate"));

			if(parameterObject.getString("affirmFlag").equals("2")){
				eatingCardes.updateTempCardApplyInfo(parameterObject);//否决后给申请者发邮件

				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("") && this.getSysParam(admin.getCpnyId()).isGaSendMail()){
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "临时卡申请 已被否决");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("1")){
				eatingCardes.updateTempCardApplyInfo(parameterObject);//最大级决裁者通过时，给申请者发邮件
				if(parameterObject.getString("applyorEmail")!=null && ! parameterObject.getString("applyorEmail").equals("")  && this.getSysParam(admin.getCpnyId()).isGaSendMail()){
					mail.gaSendMail(dataMap, admin.getCpnyId(), parameterObject.getString("applyorEmail"), "临时卡申请 已通过确认");
				}
			}
			if(parameterObject.getString("affirmFlag").equals("1") && parameterObject.getString("maxAffirmFlag").equals("0")) {
				if(parameterObject.getString("upEmail")!=null && ! parameterObject.getString("upEmail").equals("")  && this.getSysParam(admin.getCpnyId()).isGaSendMail()){
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

	/*参观者*/
	private void visiterAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(Calendar.getInstance().getTime());
		List visiterList = this.apiService.getInfoList(paramData, "getVisiterInfo");
		if (visiterList != null && visiterList.size() > 0) {
			for (int m = 0; m < visiterList.size(); m++) {
				Map infoMap = (Map) visiterList.get(m);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(), infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				String flag = StringUtil.checkNull(paramData.get("flag"));

				//封装参数
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("flag", flag);
				parameterObject.set("adminId", admin.getAdminID());


				String affirmNo=StringUtil.checkNull(infoMap.get("AFFIRM_NO"));
				String applerId=StringUtil.checkNull(infoMap.get("APPLYOR_ID"));
				String applerName=StringUtil.checkNull(infoMap.get("APPLYOR_NAME"));
				String affirmorIdea = StringUtil.checkNull(paramData.get("affirmRemark"));
				String visitCount = StringUtil.checkNull(infoMap.get("VISIT_COUNT"));
				String applyNo = StringUtil.checkNull(infoMap.get("APPLY_NO"));

				parameterObject.set("affirmNo", affirmNo);
				parameterObject.set("applerId",applerId);
				parameterObject.set("affirmorIdea",affirmorIdea);
				parameterObject.set("applyno", applyNo);
				visiterApplicationsServices.updateAffirmFlag(parameterObject);
				if(flag.equals("2")){
					visiterApplicationsServices.confirmVisiterApply(parameterObject);
					parameterObject.set("adminId1", StringUtil.checkNull(infoMap.get("applerId1")));
					String toEmail = visiterApplicationsServices.getapplyemail(parameterObject);
					if(!toEmail.equals("") && toEmail!=null && this.getSysParam(admin.getCpnyId()).isGaSendMail()){
						this.sendVIsiterAffirmMail(
								"参观者申请已被否决",
								applerName,
								date,
								StringUtil.checkNull(infoMap.get("VISITER_DATE")),
								StringUtil.checkNull(infoMap.get("VISITER_COME_TIME")),
								StringUtil.checkNull(infoMap.get("VISITER_END_TIME")),
								visitCount,
								toEmail,
								admin.getCpnyId()
						);
					}
				}else if(flag.equals("1")){
					String MAX_AFFIRM_FLAG = StringUtil.checkNull(infoMap.get("MAX_AFFIRM_FLAG"));
					parameterObject.set("affrimlevel", StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL")));
					if(MAX_AFFIRM_FLAG.equals("0")){
						String toEmail = visiterApplicationsServices.getupaffrimemail(parameterObject);
						if(!toEmail.equals("") && toEmail!=null && this.getSysParam(admin.getCpnyId()).isGaSendMail()){
							this.sendVIsiterAffirmMail(
									"参观者申请 请进行确认",
									applerName,
									date,
									StringUtil.checkNull(infoMap.get("VISITER_DATE")),
									StringUtil.checkNull(infoMap.get("VISITER_COME_TIME")),
									StringUtil.checkNull(infoMap.get("VISITER_END_TIME")),
									visitCount,
									toEmail,
									admin.getCpnyId()
							);
						}
						//推送
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_VISITER, applyNo,Integer.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL"))) + 1);
					}else{
						visiterApplicationsServices.confirmVisiterApply(parameterObject);
						parameterObject.set("adminId1", StringUtil.checkNull(infoMap.get("applerId1")));
						String toEmail = visiterApplicationsServices.getapplyemail(parameterObject);
						if(!toEmail.equals("") && toEmail!=null && this.getSysParam(admin.getCpnyId()).isGaSendMail()){
							this.sendVIsiterAffirmMail(
									"参观者申请 已经通过决裁",
									applerName,
									date,
									StringUtil.checkNull(infoMap.get("VISITER_DATE")),
									StringUtil.checkNull(infoMap.get("VISITER_COME_TIME")),
									StringUtil.checkNull(infoMap.get("VISITER_END_TIME")),
									visitCount,
									toEmail,
									admin.getCpnyId()
							);
							this.sendVIsiterAffirmMail(//参观者申请决裁完毕后除了发邮件给申请者还发送给褚衍桥\孔一琳\郑秀荣、刘孟才
									"参观者申请信息",
									applerName,
									date,
									StringUtil.checkNull(infoMap.get("VISITER_DATE")),
									StringUtil.checkNull(infoMap.get("VISITER_COME_TIME")),
									StringUtil.checkNull(infoMap.get("VISITER_END_TIME")),
									visitCount,
									"qianchao.chen@doosan.com",//"li.sun@doosan.com",//yanqiao.chu@doosan.com/
									admin.getCpnyId()
							);
//						this.sendVIsiterAffirmMail(
//								"参观者申请信息",
//								applerName,
//								date,
//								request.getParameter("VISITER_DATE_"),
//								request.getParameter("VISITER_COME_TIME_"),
//								request.getParameter("VISITER_END_TIME_"),
//								request.getParameter("VISITER_PEOPLE_NUM_"),
//								"yilin.kong@doosan.com",//yilin.kong@doosan.com
//								admin.getCpnyId()
//								);
//						this.sendVIsiterAffirmMail(
//								"参观者申请信息",
//								applerName,
//								date,
//								request.getParameter("VISITER_DATE_"),
//								request.getParameter("VISITER_COME_TIME_"),
//								request.getParameter("VISITER_END_TIME_"),
//								request.getParameter("VISITER_PEOPLE_NUM_"),
//								"xiurong.zheng@doosan.com",//xiurong.zheng@doosan.com/
//								admin.getCpnyId()
//								);
//						this.sendVIsiterAffirmMail(
//								"参观者申请信息",
//								applerName,
//								date,
//								request.getParameter("VISITER_DATE_"),
//								request.getParameter("VISITER_COME_TIME_"),
//								request.getParameter("VISITER_END_TIME_"),
//								request.getParameter("VISITER_PEOPLE_NUM_"),
//								"mengcai.liu@doosan.com",//mengcai.liu@doosan.com
//								admin.getCpnyId()
//								);
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}
	private void sendVIsiterAffirmMail(String title,String applerId,String applyDate,String visiterComeDate,String visiterComeTime,String visiterEndTime,
			String visitCount,String email,String cpny_id) throws Exception {

		SimpleMap inputData = new SimpleMap();

		inputData.set("申请人：", applerId);
		inputData.set("来访日期：", visiterComeDate);
		inputData.set("到达时间：", visiterComeTime);
		inputData.set("离开时间：", visiterEndTime);
		inputData.set("来访人数：", visitCount);

		mail.gaSendMail(inputData,cpny_id,email,title) ;

	}

	//签证审批
	public void visaAffirm(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List visaList = this.apiService.getInfoList(paramData, "getVisaInfo");
		if (visaList != null && visaList.size() > 0) {
			for (int m = 0; m < visaList.size(); m++) {
				Map infoMap = (Map) visaList.get(m);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(), infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				String flag = StringUtil.checkNull(paramData.get("flag"));

				//封装参数
				SimpleMap parameterObject = new SimpleMap();
				parameterObject.set("flag", flag);
				parameterObject.set("adminId", admin.getAdminID());

				String defaultSysFile = "/system.properties";
				UserConfiguration userConfig = UserConfiguration.getInstance(defaultSysFile);
				String emailNameDicc = userConfig.getString("emaile.seal.dicc").trim();

				// 签证编号全部为SealType_Code+数字，截取后面数字：
				String affirmNo = StringUtil.checkNull(infoMap.get("AFFIRM_NO"));
				String applerId = StringUtil.checkNull(infoMap.get("APPLYOR_ID"));
				String affirmorIdea = StringUtil.checkNull(paramData.get("affirmRemark"));
				String applyNo = StringUtil.checkNull(infoMap.get("APPLY_NO"));

				// String
				// xiezhuPerMail=request.getParameter("xiezhuPer_"+applyno[i]);
				parameterObject.set("affirmNo", affirmNo);
				parameterObject.set("applerId", applerId);
				parameterObject.set("affirmorIdea", affirmorIdea);
				parameterObject.set("applyno", applyNo);
				visaMangerSerivces.updateAffirmFlag(parameterObject);
				if (flag.equals("2")) {
					visaMangerSerivces.confirmSealApply(parameterObject);

					this.sendVisaApplyMail("签证申请 已经否决", applerId,
							StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
							admin.getCpnyId(),
							StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
							StringUtil.checkNull(infoMap.get("USEDATE")),
							StringUtil.checkNull(infoMap.get("USEINFORMATION")),
							StringUtil.checkNull(infoMap.get("USESHARES")),
							StringUtil.checkNull(infoMap.get("SEALTYPE")),
							StringUtil.checkNull(paramData.get("affirmRemark")));

				} else if (flag.equals("1")) {
					// 签证最后一级决裁完毕发送邮件通知XX人（MAX_AFFIRM_FLAG 0 不是最后一级决裁者；1
					// 是最后一级决裁）
					String MAX_AFFIRM_FLAG = StringUtil.checkNull(infoMap.get("MAX_AFFIRM_FLAG"));
//					String MAX_AFFIRM_LEVEL = StringUtil.checkNull(infoMap.get("MAX_AFFIRM_LEVEL"));
					parameterObject.set("affrimlevel", StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL")));
					if (MAX_AFFIRM_FLAG.equals("0")) {
						String applerId1 = visaMangerSerivces.getApplerId(parameterObject);

						this.sendVisaApplyMail("签证申请 等待决裁", applerId1,
								StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
								admin.getCpnyId(),
								StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
								StringUtil.checkNull(infoMap.get("USEDATE")),
								StringUtil.checkNull(infoMap.get("USEINFORMATION")),
								StringUtil.checkNull(infoMap.get("USESHARES")),
								StringUtil.checkNull(infoMap.get("SEALTYPE")),
								StringUtil.checkNull(paramData.get("affirmRemark")));

						//推送
						DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_VISA, applyNo,Integer.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_LEVEL"))) + 1);
							/*if("7509910".equals(applerId1) && !"总务/宣传课".equals(deptName)){
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
							}*/

					} else {
							/*if ("63000000".equals(admin.getCpnyId())) {
								visaMangerSerivces.confirmSealApplyForDISD(parameterObject);
							} else {*/
						visaMangerSerivces.confirmSealApply(parameterObject);

						this.sendVisaApplyMail("您的申请已经全部决裁完毕", applerId,
								StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
								admin.getCpnyId(),
								StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
								StringUtil.checkNull(infoMap.get("USEDATE")),
								StringUtil.checkNull(infoMap.get("USEINFORMATION")),
								StringUtil.checkNull(infoMap.get("USESHARES")),
								StringUtil.checkNull(infoMap.get("SEALTYPE")),
								StringUtil.checkNull(paramData.get("affirmRemark")));

						//}
						// 添加给协助者发邮件功能：
						// if(xiezhuPerMail !=null ){
						// this.sendCarApplyMail1("签证申请 已通过决裁！请协助",xiezhuPerMail,
						// request.getParameter("adminDept_"+applyno[i]),
						// admin.getCpnyId(),
						// request.getParameter("CHINESENAME_"+applyno[i]),
						// request.getParameter("useDate_"+applyno[i]),
						// request.getParameter("useInformation_"+applyno[i]),
						// request.getParameter("useShares_"+applyno[i]),
						// request.getParameter("sealType_"+applyno[i]));

						// }

						if (emailNameDicc != null&& !emailNameDicc.isEmpty()&& admin.getCpnyId().equals("78000000")) {// 签证申请
							// 通过后发邮件通知孔一琳（已取消）
							String emailName1[] = emailNameDicc.split(",");
							for (int j = 0; j < emailName1.length; j++) {
								if (emailName1[j] != null	&& !emailName1[j].isEmpty()) {
									this.sendVisaApplyMail(	"签证申请 已通过决裁",	emailName1[j],
											StringUtil.checkNull(infoMap.get("APPLYDEPTNAME")),
											admin.getCpnyId(),
											StringUtil.checkNull(infoMap.get("APPLYOR_NAME")),
											StringUtil.checkNull(infoMap.get("USEDATE")),
											StringUtil.checkNull(infoMap.get("USEINFORMATION")),
											StringUtil.checkNull(infoMap.get("USESHARES")),
											StringUtil.checkNull(infoMap.get("SEALTYPE")),
											StringUtil.checkNull(paramData.get("affirmRemark")));
								}
							}
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}

	private void sendVisaApplyMail(String title, String adminid,
								  String adminDept, String cpny_id, String applyer, String useDate,
								  String useInformation, String useShares, String sealType,String affirmorIdea)
			throws Exception {
		SimpleMap inputData = new SimpleMap();

		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("applerId", adminid);
		String result = smServices.getconfirm(parameterObject);
		parameterObject.set("sealType", sealType);
		///String sealName = smServices.getsealName(parameterObject);

		inputData.set("申请人 ", applyer);
		inputData.set("部门 ", adminDept);
		inputData.set("日期 ", useDate);
		inputData.set("签证类型 ", sealType);


		inputData.set("出差内容 ", useInformation);
		inputData.set("意见 ", affirmorIdea);

		mail.sendMail(inputData);
		if (result != null && !result.equals("") && this.getSysParam(cpny_id).isGaSendMail()) {
			mail.gaSendMail(inputData, cpny_id, result, title);
		}

	}

	//评价成绩审批
	public void doEvaluateAffrim(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception{

		List evList = this.apiService.getInfoList(paramData, "getEvaluateInfo");
		if (evList != null && evList.size() > 0) {
			for (int m = 0; m < evList.size(); m++) {
				Map infoMap = (Map) evList.get(m);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(), infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				int flag = NumberUtil.parseInt(paramData.get("flag"));

				List<EvaluateAffirmParam> pList = new ArrayList<EvaluateAffirmParam>() ;

				EvaluateAffirmParam v = new EvaluateAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_NO"))));
				v.setApplyNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("APPLY_NO"))));
				v.setAdminId(admin.getAdminID());
				v.setRemark(StringUtil.toCN(StringUtil.checkNull(paramData.get("affirmRemark"))));
				v.setTotal(NumberUtil.parseDouble(infoMap.get("TOTAL"),0));
				v.setItem1(NumberUtil.parseDouble(infoMap.get("ITEM1"),0));
				v.setItem2(NumberUtil.parseDouble(infoMap.get("ITEM2"),0));
				v.setItem3(NumberUtil.parseDouble(infoMap.get("ITEM3"),0));
				v.setItem4(NumberUtil.parseDouble(infoMap.get("ITEM4"),0));
				v.setItem5(NumberUtil.parseDouble(infoMap.get("ITEM5"),0));
				v.setItem6(NumberUtil.parseDouble(infoMap.get("ITEM6"),0));
				v.setItem7(NumberUtil.parseDouble(infoMap.get("ITEM7"),0));
				v.setItem8(NumberUtil.parseDouble(infoMap.get("ITEM8"),0));
				v.setItem9(NumberUtil.parseDouble(infoMap.get("ITEM9"),0));
				v.setItem10(NumberUtil.parseDouble(infoMap.get("ITEM10"),0));
				v.setItem11(NumberUtil.parseDouble(infoMap.get("ITEM11"),0));
				v.setItem12(NumberUtil.parseDouble(infoMap.get("ITEM12"),0));
				v.setItem13(NumberUtil.parseDouble(infoMap.get("ITEM13"),0));
				v.setItem14(NumberUtil.parseDouble(infoMap.get("ITEM14"),0));
				v.setItem15(NumberUtil.parseDouble(infoMap.get("ITEM15"),0));
				v.setItem16(NumberUtil.parseDouble(infoMap.get("ITEM16"),0));
				v.setItem17(NumberUtil.parseDouble(infoMap.get("ITEM17"),0));
				v.setItem18(NumberUtil.parseDouble(infoMap.get("ITEM18"),0));
				v.setItem19(NumberUtil.parseDouble(infoMap.get("ITEM19"),0));
				v.setItem20(NumberUtil.parseDouble(infoMap.get("ITEM20"),0));
				v.setItem21(NumberUtil.parseDouble(infoMap.get("ITEM21"),0));
				v.setItem22(NumberUtil.parseDouble(infoMap.get("ITEM22"),0));
				v.setItem23(NumberUtil.parseDouble(infoMap.get("ITEM23"),0));
				v.setItem24(NumberUtil.parseDouble(infoMap.get("ITEM24"),0));
				v.setItem25(NumberUtil.parseDouble(infoMap.get("ITEM25"),0));
				v.setItem26(NumberUtil.parseDouble(infoMap.get("ITEM26"),0));
				v.setItem27(NumberUtil.parseDouble(infoMap.get("ITEM27"),0));
				v.setItem28(NumberUtil.parseDouble(infoMap.get("ITEM28"),0));
				v.setItem29(NumberUtil.parseDouble(infoMap.get("ITEM29"),0));
				v.setItem30(NumberUtil.parseDouble(infoMap.get("ITEM30"),0));
				v.setItem31(NumberUtil.parseDouble(infoMap.get("ITEM31"),0));
				v.setItem32(NumberUtil.parseDouble(infoMap.get("ITEM32"),0));
				v.setItem33(NumberUtil.parseDouble(infoMap.get("ITEM33"),0));
				v.setItem0(NumberUtil.parseDouble(infoMap.get("ITEM0"),0));

				pList.add(v) ;

				//flag 1,通过,2,否决
				int result = evServices.doEvaluateAffirm(pList,flag);

				Logger.getLogger(getClass()).debug("********" + result + "******");
				if(result > 0 && getSysParam(admin.getCpnyId()).isAutoSendMail())
				{
					if(flag == 1){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);

						Iterator iterator3 = pList.iterator();
						for(int i= 0;iterator3.hasNext();i++){

							EvaluateAffirmParam param = (EvaluateAffirmParam)iterator3.next();
							buffer.append("'"+param.getApplyNo()+"',");
						}

						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
						simpleMap.setString("curAffirmor", admin.getAdminID());
						List list1 = evServices.getEvaluateNextAffirmor(simpleMap);

						if(list1.size()>0){
							Iterator iterator = list1.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("affirmLevel", map.getString("AFFIRM_LEVEL"));
								simpleMap2.setString("affirmorId", map.getString("AFFIRMOR_ID"));
								simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
								List list2 = evServices.getEvaluateApplyNoForMail(simpleMap2);

								int applyNo[] = new int[list2.size()];
								Iterator iterator2 = list2.iterator();
								for(int i= 0;iterator2.hasNext();i++){
									SimpleMap map2 = (SimpleMap)iterator2.next();
									applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								}
								this.sendEvaluateMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin.getAdminID());//加了adminid参数调用不同的方法，增加决裁意见
							}
						}
					}

					else if(flag == 2){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);

						Iterator iterator4 = pList.iterator();
						for(int i= 0;iterator4.hasNext();i++){

							EvaluateAffirmParam param = (EvaluateAffirmParam)iterator4.next();
							buffer.append("'"+param.getApplyNo()+"',");
						}

						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
						List list1 = evServices.getEvaluateApplyor(simpleMap);

						if(list1.size()>0){
							Iterator iterator = list1.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("createBy", map.getString("CREATED_BY"));
								simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
								List list2 = evServices.getEvaluateApplyNoCreateBy(simpleMap2);

								int applyNo[] = new int[list2.size()];
								Iterator iterator2 = list2.iterator();
								for(int i= 0;iterator2.hasNext();i++){
									SimpleMap map2 = (SimpleMap)iterator2.next();
									applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								}
								this.sendEvaluateMail(applyNo, map.getString("CREATED_BY"), flag,admin.getAdminID());
							}
						}
					}
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}


	public void sendEvaluateMail(int[]Applyno,String setTo,int flag,String adminId)throws Exception{//比上面的方法多一个决裁意见
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		parameterObject.setString("adminId", adminId);
		SimpleMap result = (SimpleMap) evServices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) evServices.applyEvaluateResult(parameterObject);
		SimpleMap inputData = new SimpleMap();
		String emailTitle="";
		StringBuffer content = new StringBuffer();
		if(flag==1){
			emailTitle="评价申请";
		}else if(flag==2){
			emailTitle="评价申请,被否决";
		}else{
			emailTitle="评价申请";
		}
		String emailAddress=result.getString("EMAIL");
		for(int i=0;i<applyResult.size();i++){
			SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
			content.append("姓名:").append(simpleMap.getString("CHINESENAME"));
			content.append("<br>").append("评价总分:").append(StringUtil.checkNull(simpleMap.getString("TOTAL")));
			content.append("<br>").append("说明事项:").append(StringUtil.checkNull(simpleMap.getString("REMARK")));
			//content.append("<br>").append("决裁意见:").append(StringUtil.checkNull(simpleMap.getString("AFFIRM_REMARKS")));

			content.append("<br>");
			content.append("--------------------------------------------------------------------------");
			content.append("<br>");
		}
		content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>");

		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	//评价成绩审批
	public void doEvsResultAffrim(Map paramData,HttpServletRequest request, HttpServletResponse response) throws Exception{

		List evsList = this.apiService.getInfoList(paramData, "getEvsResultInfo");
		if (evsList != null && evsList.size() > 0) {
			for (int m = 0; m < evsList.size(); m++) {
				Map infoMap = (Map) evsList.get(m);
				//获取人员信息存入session
				AdminBean admin = sysService.searchEmp(infoMap.get("EMPID").toString(), infoMap.get("CPNY_ID").toString());
				request.getSession().setAttribute("admin", admin);
				//	设置静态线程变量
				ApplicationContext.setAdminBean(request, response);

				//flag 1,通过,2,否决
				int flag = NumberUtil.parseInt(paramData.get("flag"));

				EssAffirmParam[] params = new EssAffirmParam[1];
				EssAffirmParam v = new EssAffirmParam();
				v.setAffirmNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("AFFIRM_NO"))));
				v.setApplyNo(NumberUtil.parseInt(StringUtil.checkNull(infoMap.get("APPLY_NO"))));
				v.setFromDate(StringUtil.checkNull(infoMap.get("CODE_FLAG")));
				//v.setFromTime(request.getParameter("fromTime" + str_affirmNos[i]));
				//v.setToDate(request.getParameter("toDate" + str_affirmNos[i]));
				//v.setToTime(request.getParameter("toTime" + str_affirmNos[i]));
				v.setPerson_id( admin.getAdminID());
				v.setRemark(StringUtil.toCN(StringUtil.checkNull(paramData.get("affirmRemark"))));
				params[0] = v ;

				///决裁操作
				int result = evsservices.doSetupcodeAffirm(params,flag);

				///发邮件
				if(result > 0 )
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
						List list1 = evsservices.getSetupcodeNextAffirmor(simpleMap);

						if(list1.size()>0){
							Iterator iterator = list1.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("affirmLevel", map.getString("AFFIRM_LEVEL"));
								simpleMap2.setString("affirmorId", map.getString("AFFIRMOR_ID"));
								simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
								List list2 = evsservices.getSetupcodeApplyNoForMail(simpleMap2);

								int applyNo[] = new int[list2.size()];
								Iterator iterator2 = list2.iterator();
								for(int i= 0;iterator2.hasNext();i++){
									SimpleMap map2 = (SimpleMap)iterator2.next();
									applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								}

								//隐藏  修改
								this.sendEvsResultMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin.getAdminID());//加了adminid参数调用不同的方法，增加决裁意见
							}
							//  开始
						}else{//如果是最后一个决裁者，决裁后添加DISD部门编号
							//for(int i=0;i<params.length;i++){
							//	EssAffirmParam param = (EssAffirmParam)params[i];
							//evsservices.essLeaveDeptNo(param.getApplyNo());
							//}
							//结束
						}
					}else if(flag == 2){
						SimpleMap simpleMap = new SimpleMap();
						StringBuffer buffer = new StringBuffer(100);
						for(int i=0;i<params.length;i++){
							EssAffirmParam param = (EssAffirmParam)params[i];
							buffer.append("'"+param.getApplyNo()+"',");
						}
						simpleMap.setString("applyStr", buffer.substring(0,buffer.length()-1));
						List list1 = evsservices.getSetupApplyor(simpleMap);

						if(list1.size()>0){
							Iterator iterator = list1.iterator();
							SimpleMap simpleMap2 = new SimpleMap();
							for(;iterator.hasNext();){
								SimpleMap map = (SimpleMap)iterator.next();
								simpleMap2.setString("createBy", map.getString("CREATED_BY"));
								simpleMap2.setString("applyStr", buffer.substring(0,buffer.length()-1));
								List list2 = evsservices.getSetupApplyNoCreateBy(simpleMap2);

								int applyNo[] = new int[list2.size()];
								Iterator iterator2 = list2.iterator();
								for(int i= 0;iterator2.hasNext();i++){
									SimpleMap map2 = (SimpleMap)iterator2.next();
									applyNo[i] = Integer.parseInt(map2.getString("APPLY_NO"));
								}
								this.sendEvsResultMail(applyNo, map.getString("CREATED_BY"), flag,admin.getAdminID());//加了adminid参数调用不同的方法，增加决裁意见
							}
						}
					}
					//结束
				}
			}
		}
		response.getWriter().append(R.toJson(R.ok()));
	}

	public void sendEvsResultMail(int[]Applyno,String setTo,int flag,String adminId)throws Exception{//比上面的方法多一个决裁意见
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		parameterObject.setString("adminId", adminId);
		SimpleMap result = (SimpleMap) evsservices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) evsservices.applyArModifyResult(parameterObject);
		SimpleMap inputData = new SimpleMap();
		String emailTitle="";
		StringBuffer content = new StringBuffer();
		if(flag==1){
			emailTitle="评价成绩提交决裁申请";
		}else if(flag==2){
			emailTitle="评价成绩提交决裁申请,被否决";
		}else{
			emailTitle="评价成绩提交决裁申请";
		}
		String emailAddress=result.getString("EMAIL");
		for(int i=0;i<applyResult.size();i++){
			SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
			content.append("姓名:").append(simpleMap.getString("CHINESENAME"));
			content.append("<br>").append("评价日期:").append(StringUtil.checkNull(simpleMap.getString("APPLY_DATE")));
			content.append("<br>").append("工序:").append(StringUtil.checkNull(simpleMap.getString("JOBCONTENT")));
			content.append("<br>").append("工序等级:").append(StringUtil.checkNull(simpleMap.getString("SKILLLEVEL")));
			content.append("<br>").append("单项得分:").append(StringUtil.checkNull(simpleMap.getString("COMPOSITE")));

			content.append("<br>").append("决裁意见:").append(StringUtil.checkNull(simpleMap.getString("AFFIRM_REMARKS")));

			content.append("<br>");
			content.append("--------------------------------------------------------------------------");
			content.append("<br>");
		}
		content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>")
				.append("<br>"+"斗山工程机械(中国)有限公司") ;
		inputData.setString("EMAIL_TITLE", emailTitle);

		// set email content
		inputData.setString("EMAIL_CONTNT", content.toString());

		inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

		new Mail().sendMail(inputData) ;
	}

	protected Map getData(HttpServletRequest request) {
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			Map map = new Gson().fromJson(reader, SimpleMap.class);
			map.put("empid", AES.Decrypt(StringUtil.checkNull(map.get("username"))));
			map.put("username", AES.Decrypt(StringUtil.checkNull(map.get("username"))));
			reader.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 获取邮件法人
	 *
	 * @Author weizhengchen
	 * @Email 1377252306@qq.com
	 * @Date 2019/7/29 13:31
	 * @Param []
	 * @Return java.lang.String
	 */
	private String getEmailCpny(String cpnyId){
		if("78000000".equals(cpnyId)){
			return "斗山工程机械(中国)有限公司";
		}else if("63000000".equals(cpnyId)){
			return "斗山工程机械（山东）有限公司";
		}else if("61000000".equals(cpnyId)){
			return "斗山工程机械（苏州）有限公司";
		}else if("59000000".equals(cpnyId)){
			return "斗山(中国)投资有限公司";
		}else if("60000000".equals(cpnyId)){
			return "斗山机床（烟台）有限公司";
		}else{
			return "斗山工程机械";
		}
	}

	/**
	 * 获取邮件法人
	 *
	 * @Author weizhengchen
	 * @Email 1377252306@qq.com
	 * @Date 2019/7/29 13:31
	 * @Param []
	 * @Return java.lang.String
	 */
	private EssSysparam getSysParam(String cpnyId){
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		return essSysparam;
	}
}
