package com.ait.evs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.evs.EvsCraft;
import com.ait.evs.EvsGxjndj;
import com.ait.evs.business.EvsServices;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class EvsSetupcodeaffirmCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsSetupcodeaffirmCmd.class);
				private String url = "http://10.40.128.28:8083/" ;
				EvsServices evsservices = EvsServices.getInstance();
@Override
public String execute(HttpServletRequest request,HttpServletResponse response) 
   throws ServletException, IOException {
	MessageSource messageSource ;
	
			
			
			try { 
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
				String[] str_affirmNos = request.getParameterValues("affirmNo");
				if (str_affirmNos == null)
					str_affirmNos = new String[0];
				
				int flag = NumberUtil.parseInt(request.getParameter("flag"));
				int size = str_affirmNos.length ;
				EssAffirmParam[] params = new EssAffirmParam[str_affirmNos.length];
				for (int i = 0; i < size; i++) {
					EssAffirmParam v = new EssAffirmParam();
					v.setAffirmNo(NumberUtil.parseInt(str_affirmNos[i]));
					int applyNo = NumberUtil.parseInt(request.getParameter("applyNo" + str_affirmNos[i]));
					
					v.setApplyNo(applyNo);
					v.setFromDate(request.getParameter("codeflag" + str_affirmNos[i]));
					//v.setFromTime(request.getParameter("fromTime" + str_affirmNos[i]));
					//v.setToDate(request.getParameter("toDate" + str_affirmNos[i]));
					//v.setToTime(request.getParameter("toTime" + str_affirmNos[i]));
					v.setPerson_id( admin.getAdminID());
					v.setRemark(StringUtil.toCN(request.getParameter("remark" + str_affirmNos[i])));
					params[i] = v ;
				}
				
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
								this.sendArModifyMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin.getAdminID());//加了adminid参数调用不同的方法，增加决裁意见
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
								
								this.sendArModifyMail(applyNo, map.getString("AFFIRMOR_ID"), flag,admin.getAdminID());//加了adminid参数调用不同的方法，增加决裁意见

							}
						}
					}
					
					//结束
				}								
				
			} catch (Exception e) {
				logger.error(e.toString());
				request.setAttribute("update", 2);
				throw new GlRuntimeException(
						"The information Exception when running the IsertExpiredContract. ",
						e);
			}			
			request.setAttribute("update", 1);
			return "/evs0307_modify_affirm.jsp";
		}
	public void sendLeaveMail(int[]Applyno,String setTo,int flag)throws Exception{
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("setTo", setTo);
		SimpleMap result = (SimpleMap) evsservices.setToEmail(parameterObject);
		String applyStr="";
		for(int i=0;i<Applyno.length;i++){
			applyStr +=("'"+Applyno[i]+"',");
		}
		parameterObject.setString("applyNoStr", applyStr.substring(0,applyStr.length()-1));
		List applyResult = (List) evsservices.applyLeaveResult(parameterObject);
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
			content.append("申请人:").append(simpleMap.getString("CHINESENAME"))
			       .append("<br><br>").append("主题:").append(simpleMap.getString("APPLY_TYPE")).append("<br><br>")
			       .append("申请时间:").append(simpleMap.getString("APPLY_DATE"));
			content.append("<br>").append("工序:").append(StringUtil.checkNull(simpleMap.getString("JOBCONTENT")));
			content.append("<br>").append("工序等级:").append(StringUtil.checkNull(simpleMap.getString("SKILLLEVEL")));
			content.append("<br>").append("单项得分:").append(StringUtil.checkNull(simpleMap.getString("COMPOSITE")));
			
			
			content.append("<br><br>");
			content.append("--------------------------------------------------------------------------");
			content.append("<br><br>");
		}
		content.append("<br><br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆系统决裁</a>")
		   .append("<br><br>"+"斗山工程机械(中国)有限公司") ;
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
}
