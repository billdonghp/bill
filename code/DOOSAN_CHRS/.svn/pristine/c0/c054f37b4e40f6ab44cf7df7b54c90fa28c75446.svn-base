package com.ait.ess.base;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import com.ait.i18n.MessageSource;
import com.ait.sy.bean.AdminBean;

public class ErrorMessage {

	/**返回不同操作时各种错误代码对应的错误信息
	 * @param operation 操作类别: otApply, otAffirm, otConfirm,
	 * 							leaveApply, leaveAffirm, leaveConfirm,
	 * 							matchApply, matchAffirm, matchConfirm 
	 * @param errorCode 错误代码
	 * @author Pennix
	 */
	public static String getErrorMessage (String operation, HashMap result,HttpServletRequest request) {
		
		AdminBean admin =(AdminBean) request.getSession().getAttribute("admin");
		MessageSource messageSource = new MessageSource("ess",admin.getLocale(), "UTF-8");
		String errorMsg = messageSource.getMessage("alert.ess.overtime.fail_unknown_error");
		int errorCode = ((Integer) result.get("errcode")).intValue();
		Logger.getLogger(ErrorMessage.class).debug("operation : " + operation);
		Logger.getLogger(ErrorMessage.class).debug("errorCode : " + errorCode);
		
		try {
			if (operation.equalsIgnoreCase("otApply")) {        
				if (errorCode == 0)  
					errorMsg=messageSource.getMessage("alert.ess.overtime.awaiting_approval");
				else if (errorCode == 1)
					errorMsg=messageSource.getMessage("alert.ess.overtime.end_time_format_error");	
				else if (errorCode == 2)
				{
					// 申请失败：员工ID结束时间必须在开始时间之后。
					errorMsg=messageSource.getMessage("alert.ess.overtime.fail_before_start");
				 	errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]") ;   
				}
				else if (errorCode == 3)
				{
//					申请失败：员工ID加班时间与当日班次时间冲突
				   errorMsg=messageSource.getMessage("alert.ess.overtime.fail_ot_shift");
				   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 4)   
				{               
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 加班时间与已有加班申请冲突";
					   errorMsg=messageSource.getMessage("alert.ess.overtime.fail_ot_request");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 5)
				{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 加班时间与已有休假时间冲突";
					   errorMsg=messageSource.getMessage("alert.ess.overtime.fail_ot_dayoff");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 6)
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 加班时间与已有值班时间冲突";
				else if (errorCode == 7)
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 不可以申请多条休息日加班或节假日加班";
				else if (errorCode == 8)
					errorMsg = "申请失败:不可以申请 " + (String) result.get("applydaysafter") + " 天之后的加班";
				else if (errorCode == 9)
				{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 加班类型与班次不符";
					   errorMsg=messageSource.getMessage("alert.ess.overtime.fail_ot_type_shift");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 10)
				{
//					errorMsg = "申请失败:为员工 [" + (String) result.get("erremp") + "] 添加决裁者失败";
					   errorMsg=messageSource.getMessage("alert.ess.overtime.fail_add_approver");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 11)
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] ,月份 [" + (String) result.get("planMonth") + "] ,剩余加班小时不足";
				else if (errorCode == 12)
					errorMsg = "申请失败:紧急加班不可以申请 " + (String) result.get("applydaysbefore") + " 天前之后的加班";
				else if (errorCode == 13)
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 本月加班未满 [" + (String) result.get("maxothour") + "] 小时";
				else if(errorCode == 14)
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 不能在这一天申请节日特勤加班,原因：只有倒班人员在休息日才能申请！";
				else if(errorCode == 15)
				{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 不是倒班的员工!";
					   errorMsg=messageSource.getMessage("alert.ess.overtime.fail_not_employee");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}		
				else if(errorCode == 16)
				{
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] ,申请的加班日期，不符合当天的日期类型!!!";
				}
				else if(errorCode == 17)
				{
				//	errorMsg = "没有为 [" + (String) result.get("erremp") + "] 设置决裁者,请设置决裁者再申请";
					 errorMsg=messageSource.getMessage("alert.ess.approval.no_approver");
					  errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}else if(errorCode == 18){
					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] ,月份 [" + (String) result.get("planMonth") + "] ,没有设置加班上限";
				}else if(errorCode == 19){
					errorMsg =  "申请失败:员工 [" + (String) result.get("erremp") + "] 没有取到决裁者";
				}
				else
					errorMsg = "申请失败:未指定的错误 [" + (String) result.get("errmsg") + "]";
				
			} else if (operation.equalsIgnoreCase("leaveApply")) {                 
				if (errorCode == 0)
					errorMsg=messageSource.getMessage("alert.ess.overtime.awaiting_approval");
				else if (errorCode == 1)
					errorMsg=messageSource.getMessage("alert.ess.overtime.end_time_format_error");	
				else if (errorCode == 2)
				{
					errorMsg=messageSource.getMessage("alert.ess.overtime.fail_before_start");	
					errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 3)
					{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 年假数不足";
					   errorMsg=messageSource.getMessage("alert.ess.dayoff.fail_vacation_days");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
					}
				else if (errorCode == 4)
				{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 休假时间与已有加班申请冲突";
					  errorMsg=messageSource.getMessage("alert.ess.dayoff.fail_dayoff_overtime");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 5)
					{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 休假时间与已有休假时间冲突";
					  errorMsg=messageSource.getMessage("alert.ess.dayoff.fail_between_dayoff");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
					}
				else if (errorCode == 6)
				{
//					errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 休假时间与已有值班时间冲突";
					  errorMsg=messageSource.getMessage("alert.ess.dayoff.fail_dayoff_nightduty");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 10)
				{
//					errorMsg = "申请失败:为员工 [" + (String) result.get("erremp") + "] 添加决裁者失败";
					  errorMsg=messageSource.getMessage("alert.ess.dayoff.fail_add_approver");
					   errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}
				else if (errorCode == 11)
					errorMsg = "申请失败:员工[" + (String) result.get("erremp") + "]历史假期数不足";
				else if (errorCode == 12)
					errorMsg = "申请失败:员工[" + (String) result.get("erremp") + "]特殊事假不得少于五天";
				else if (errorCode == 13)             
					errorMsg = "申请失败:员工[" + (String) result.get("erremp") + "]休假时间为员工休息时间"; 
				else if(errorCode == 14)
				{
				//	 "没有为 [" + (String) result.get("erremp") + "] 设置决裁者,请设置决裁者再申请";
					 errorMsg=messageSource.getMessage("alert.ess.approval.no_approver");
					 errorMsg=errorMsg.replace("ID","[ "+(String)result.get("erremp")+"]");    
				}else if (errorCode == 15){
					errorMsg = "申请失败:员工["+(String) result.get("erremp")+"]有薪病假数不足";
				}else if (errorCode == 16){
					errorMsg = "申请失败:员工["+(String) result.get("erremp")+"]年休假必须按整天申请";
				}else if (errorCode == 17){
					errorMsg = "申请失败:员工["+(String) result.get("erremp")+"]团聚假数不足";
				}else if (errorCode == 18){
					errorMsg = "申请失败:员工["+(String) result.get("erremp")+"]上年剩余年假数不足";
				}else if (errorCode == 21)   
				{               
					   errorMsg = "申请失败:员工 [" + (String) result.get("erremp") + "] 申请的时间段没有已申请的加班";
				}
				else          
					errorMsg = "申请失败:未指定的错误 [" + (String) result.get("errmsg") + "]";
			} else if (operation.equalsIgnoreCase("OtTopLimitApply")) {         
			    if (errorCode == 0)                      
					errorMsg = "申请成功, 请等待决裁者进行决裁";
				else if (errorCode == 1)
					errorMsg = "申请失败: 添加决裁者失败";
				else if (errorCode == 2)
					errorMsg = "申请失败: 没有为 [" + (String) result.get("erremp") + "] 设置决裁者,请设置决裁者再申请";
				else
					errorMsg = "申请失败:未指定的错误 [" + (String) result.get("errmsg") + "]";
			} else
				errorMsg = "申请失败:无效的错误类型 [" + operation + "]";
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(ErrorMessage.class).debug(e.toString());
			errorMsg = "申请失败:未知错误 [" + e.toString() + "]";
		}
		return errorMsg;
	}
}
