package com.ait.sy.dao;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.HrSysparam;
import com.ait.sysparam.Sysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.sysparam.TaskSysparam;
import com.ait.util.BoolUtil;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class SyParamDAO {

	public void saveParam(HttpServletRequest request, Class cls,String cpnyId){
		Sysparam p = null;
		if (cls == EssSysparam.class)
			p = assembleEssParam(request,cpnyId);
		else if (cls == HrSysparam.class)
			p = assembleHrParam(request,request.getParameter("cpnyId"));
		else if (cls == TaskSysparam.class)
			p = assembleTaskParam(request,request.getParameter("cpnyId"));
		if (p != null){
			Logger.getLogger(getClass()).debug("Now Saving LgcnSysparam : " + cls.getName());
			SysparamUtils.saveSysparam(p,cpnyId);
		}
		
	}
	
	private HrSysparam assembleHrParam(HttpServletRequest request,String cpnyId){
		HrSysparam ret = (HrSysparam)SysparamUtils.getSysparam(HrSysparam.class,cpnyId);
		int probationalMonths = NumberUtil.parseInt(request.getParameter("probationalMonths"), ret.getProbationalMonths());
		ret.setProbationalMonths(probationalMonths);
		return ret;
	}

	/**
	 * 根据传入值组装EssSysparam对象
	 * @param request
	 * @return EssSysparam
	 */
	private EssSysparam assembleEssParam(HttpServletRequest request,String cpnyId){
		EssSysparam essSysparam = (EssSysparam)SysparamUtils.getSysparam(EssSysparam.class,cpnyId);
		essSysparam.setParamName(StringUtil.checkNull(request.getParameter("paramName")));
		essSysparam.setDescription(StringUtil.checkNull(request.getParameter("description")));
		/**
		 * ESS系统是否自动向下一级决裁者发邮件
		 */
		essSysparam.setAutoSendMail(BoolUtil.parseBool(request.getParameter("autoSendMail"), essSysparam.isAutoSendMail()));
		/**
		 * 行政管理是否自动向下一级决裁者发邮件
		 */
		essSysparam.setGaSendMail(BoolUtil.parseBool(request.getParameter("gaSendMail"), essSysparam.isGaSendMail()));
		/**
		 * 取决裁者时，是否包含自己
		 */
		essSysparam.setContainsOwner(BoolUtil.parseBool(request.getParameter("containsOwner"), essSysparam.isContainsOwner()));
		/**
		 * 加班最终决裁后是否自动进行人事确认
		 */
		essSysparam.setOtAutoConfirm(BoolUtil.parseBool(request.getParameter("otAutoConfirm"), essSysparam.isOtAutoConfirm()));
		/**
		 * 休假最终决裁后是否自动进行人事确认
		 */
		essSysparam.setLeaveAutoConfirm(BoolUtil.parseBool(request.getParameter("leaveAutoConfirm"), essSysparam.isLeaveAutoConfirm()));
		/**
		 * 人事是否可以提前进行确认
		 *  */
		essSysparam.setPreConfirm(BoolUtil.parseBool(request.getParameter("preConfirm"), essSysparam.isPreConfirm()));
		/**
		 * 人事是否可以在确认后重新确认
		 */
		essSysparam.setReConfirm(BoolUtil.parseBool(request.getParameter("reConfirm"), essSysparam.isReConfirm()));
		/**
		 * 加班决裁是否可反悔
		 */
		essSysparam.setOtModifiedAfterAffirm(BoolUtil.parseBool(request.getParameter("otModifiedAfterAffirm"), essSysparam.isOtModifiedAfterAffirm()));
		/**
		 *加班修改是否可以通过权限控制
		 */
		essSysparam.setOtUpdateControl(BoolUtil.parseBool(request.getParameter("otUpdateControl"), essSysparam.isOtUpdateControl()));
		/**
		 * 休假决裁是否可反悔
		 */
		essSysparam.setLeaveModifiedAfterAffirm(BoolUtil.parseBool(request.getParameter("leaveModifiedAfterAffirm"), essSysparam.isLeaveModifiedAfterAffirm()));
		/**
		 * 加班申请是否执行始末时间先后检查
		 */
		essSysparam.setCheckOtApplyStartEndTime(BoolUtil.parseBool(request.getParameter("checkOtApplyStartEndTime"), essSysparam.isCheckOtApplyStartEndTime()));
		/**
		 * 加班申请是否执行加班时间与加班时间冲突检查
		 */
		essSysparam.setCheckOtApplyOtConflict(BoolUtil.parseBool(request.getParameter("checkOtApplyOtConflict"), essSysparam.isCheckOtApplyOtConflict()));
		/**
		 * 加班申请是否执行加班时间与班次时间冲突检查
		 */
		essSysparam.setCheckOtApplyShiftConflict(BoolUtil.parseBool(request.getParameter("checkOtApplyShiftConflict"), essSysparam.isCheckOtApplyShiftConflict()));
		/**
		 * 加班申请是否执行加班时间与休假时间冲突检查
		 */
		essSysparam.setCheckOtApplyLeaveConflict(BoolUtil.parseBool(request.getParameter("checkOtApplyLeaveConflict"), essSysparam.isCheckOtApplyLeaveConflict()));
		/**
		 * 加班申请是否执行加班时间与值班时间冲突检查
		 */
		essSysparam.setCheckOtApplyMatchConflict(BoolUtil.parseBool(request.getParameter("checkOtApplyMatchConflict"), essSysparam.isCheckOtApplyMatchConflict()));
		/**
		 * 加班可申请?天前的加班
		 * -1:不检查(可申请任意时间加班)
		 */
		essSysparam.setOtApplyDaysBefore(NumberUtil.parseInt(request.getParameter("otApplyDaysBefore"), essSysparam.getOtApplyDaysBefore()));
		/**
		 * 加班可申请?天后的加班
		 * -1:不检查(可申请任意时间加班)
		 */
		essSysparam.setOtApplyDaysAfter(NumberUtil.parseInt(request.getParameter("otApplyDaysAfter"), essSysparam.getOtApplyDaysAfter()));
		/**
		 * 加班类型判断标志
		 * -1:不判断(取手工提交值);  0:自动判断与手工提交数据比对;  1:取自动判断值;
		 */
		essSysparam.setOtApplyTypeCheckFlag(NumberUtil.parseInt(request.getParameter("otApplyTypeCheckFlag"), essSysparam.getOtApplyTypeCheckFlag()));
    	/**
    	 *加班申请是否执行加班上限检查
    	 */
		essSysparam.setOtApplyMaxHours(BoolUtil.parseBool(request.getParameter("otApplyMaxHours"), essSysparam.getOtApplyMaxHours()));
    	/**
    	 * 休假始末时间前后检查
    	 */
		essSysparam.setCheckLeaveApplyStartEndTime(BoolUtil.parseBool(request.getParameter("checkLeaveApplyStartEndTime"), essSysparam.isCheckLeaveApplyStartEndTime()));
        /**
    	 * 是否执行休假与加班冲突检查
    	 */
    	essSysparam.setCheckLeaveApplyOtConflict(BoolUtil.parseBool(request.getParameter("checkLeaveApplyOtConflict"), essSysparam.isCheckLeaveApplyOtConflict()));
    	
    	/**
    	 * 是否执行休假与休假冲突检查
    	 */
    	essSysparam.setCheckLeaveApplyLeaveConflict(BoolUtil.parseBool(request.getParameter("checkLeaveApplyLeaveConflict"), essSysparam.isCheckLeaveApplyLeaveConflict()));
    	/**
    	 * 是否执行休假与值班冲突检查
    	 */
    	essSysparam.setCheckLeaveApplyMatchConflict(BoolUtil.parseBool(request.getParameter("checkLeaveApplyMatchConflict"), essSysparam.isCheckLeaveApplyMatchConflict()));
    	/**
    	 * 值班始末时间前后检查
    	 */
    	essSysparam.setCheckMatchApplyStartEndTime(BoolUtil.parseBool(request.getParameter("checkMatchApplyStartEndTime"), essSysparam.isCheckMatchApplyStartEndTime()));
    	/**
    	 * 是否执行值班与加班冲突检查
    	 */
    	essSysparam.setCheckMatchApplyOtConflict(BoolUtil.parseBool(request.getParameter("checkMatchApplyOtConflict"), essSysparam.isCheckMatchApplyOtConflict()));
    	/**
    	 * 是否执行值班与休假冲突检查
    	 */
    	essSysparam.setCheckMatchApplyLeaveConflict(BoolUtil.parseBool(request.getParameter("checkMatchApplyLeaveConflict"), essSysparam.isCheckMatchApplyLeaveConflict()));
    	/**
    	 * 是否执行值班与值班冲突检查
    	 */
    	essSysparam.setCheckMatchApplyMatchConflict(BoolUtil.parseBool(request.getParameter("checkMatchApplyMatchConflict"), essSysparam.isCheckMatchApplyMatchConflict()));
		return essSysparam;
	}
	
	private TaskSysparam assembleTaskParam(HttpServletRequest request,String cpnyId) {
		
		TaskSysparam ret = (TaskSysparam) SysparamUtils.getSysparam(TaskSysparam.class,cpnyId);
	
		ret.setMailDelay(NumberUtil.parseInt(request.getParameter("mailDelay"), ret.getMailDelay()));
		ret.setMailPeriod(NumberUtil.parseInt(request.getParameter("mailPeriod"), ret.getMailPeriod()));
		
		ret.setCardDelay(NumberUtil.parseInt(request.getParameter("cardDelay"), ret.getCardDelay()));
		ret.setCardPeriod(NumberUtil.parseInt(request.getParameter("cardPeriod"), ret.getCardPeriod()));
		
		ret.setDeptDelay(NumberUtil.parseInt(request.getParameter("deptDelay"), ret.getDeptDelay()));
		ret.setDeptPeriod(NumberUtil.parseInt(request.getParameter("deptPeriod"), ret.getDeptPeriod()));
		
		ret.setErpDelay(NumberUtil.parseInt(request.getParameter("erpDelay"), ret.getErpDelay()));
		ret.setErpPeriod(NumberUtil.parseInt(request.getParameter("erpPeriod"), ret.getErpPeriod()));
		
		ret.setEateryDelay(NumberUtil.parseInt(request.getParameter("eateryDelay"), ret.getEateryDelay()));
		ret.setEateryPeriod(NumberUtil.parseInt(request.getParameter("eateryPeriod"), ret.getEateryPeriod()));
		
		ret.setAffirmMailDelay(NumberUtil.parseInt(request.getParameter("affirmMailDelay"), ret.getAffirmMailDelay()));
		ret.setAffirmMailPeriod(NumberUtil.parseInt(request.getParameter("affirmMailPeriod"), ret.getAffirmMailPeriod()));
 
		return ret;
	}
	
}
