/*
 * @(#)TipMessageService.java 1.0 2007-8-29 下午04:30:54
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.tipmessage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ait.ess.dao.EssDAO;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.hrm.tipmessagedao.TipMessageDao;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

public class TipMessageService {
	
	public AdminBean admin;

	private UserConfiguration userConfig;
	
	private static final String defaultSysFile = "/system.properties";
	
	public TipMessageService() {
	}

	public TipMessageService(AdminBean adimin) {
		this.admin=adimin;
		userConfig = UserConfiguration.getInstance(defaultSysFile);              
	}
	
	public void setTipMessageInSession(HttpServletRequest request) throws Exception
	{
		TipMessageDao tipmessagedao = new TipMessageDao();
		HttpSession session=request.getSession(false);
//----------------------------------------待决裁信息-----------------------------------------------------------------//
		String[] sgNo = admin.getScreenGrantNo().split(",");
		boolean b = false;
		for (int i = 0; i < sgNo.length; i++) {                 
			if (sgNo[i].trim().equals(userConfig.getString("common.role.affirmor").trim()))   
				b = true;
		}  
		if (b) {
			String MaxLevel = tipmessagedao.getLogOnPersonAffirmMaxLevel("hrm.tip.getLogOnTipMessage", admin.getAdminID());
			SimpleMap smap = new SimpleMap();
			smap.put("adminid", admin.getAdminID());
			smap.put("maxLevel", MaxLevel);
			int countOT = tipmessagedao.getCount("hrm.tip.getCountOt", smap);
			int countLeave = tipmessagedao.getCount("hrm.tip.getCountLeave", smap);
			int countEvection = tipmessagedao.getCount("hrm.tip.getCountEvection", smap);
			int countTraining = tipmessagedao.getCount("hrm.tip.getCountTraining", smap);
			session.setAttribute("affirmInfo",true);
			session.setAttribute("affirmCountOT", countOT);
			session.setAttribute("affirmCountLeave", countLeave);
			session.setAttribute("affirmCountEvection", countEvection);
			session.setAttribute("affirmCountTraining", countTraining);

		}                   
//-------------------------------------------人事待确认信息----------------------------------------------------------//	
	   b=false;
		for (int i = 0; i < sgNo.length; i++) {  
				if (sgNo[i].trim().equals(userConfig.getString("common.role.hrAdmin").trim())
						|| sgNo[i].trim().equals(userConfig.getString("common.role.seniorLeader").trim()))   
					b = true;
			}
		   if(b)
		   {
			    String adminid = admin.getAdminID();  
			    HrmCommonDAO had = new HrmCommonDAO();
			    SimpleMap simpleMap = new SimpleMap();
			    simpleMap.putInt("ACTIVITY", 0);           
			    int infoCount=0;  
//				int otCount = had.getOtConfirmCnt();
//				int leaveCount = had.getConfirmCnt("LeaveApply");
//				int evectionCount = had.getConfirmCnt("EvectionApply");
//				int trainingCount = had.getConfirmCnt("TrainingApply");
				EssDAO ed = new EssDAO();
				infoCount = (Integer) ed.getEssPersonalInfoListCnt(simpleMap);
//				session.setAttribute("confirmOtCount",otCount);
//				session.setAttribute("confirmLeaveCount",leaveCount);
//				session.setAttribute("confirmEvectionCount",evectionCount);
//				session.setAttribute("confirmTrainingCount",trainingCount);
				session.setAttribute("confirmInfoCount",infoCount);
/*			 			
//------------------------------------------将过期试用转正人员列表-----------------------------------------------------//
			    List probationList=null;
			    probationList = (ArrayList)tipmessagedao.getExpiredProbation("hrm.tip.getExpiredProbation");
			    session.setAttribute("probationList",probationList);
//-------------------------------------------健康信息提示------------------------------------------------------------//			      
			    List healthlist=tipmessagedao.getHealthOverTimeInfoList("hrm.tip.getHealthOverTimeInfoList");
			    session.setAttribute("healthlist",healthlist);
//---------------------------------------------- 将到期合同--------------------------------------------------------- //			    
			    List contractList=null;
				contractList = tipmessagedao.getExpiredContract("hrm.tip.getExpiredContract");
				session.setAttribute("contractList",contractList);  
*/				
				session.setAttribute("confirmInfo",true);//设置人事确认标志，在info_main_index.jsp中用来判断显示提示与否
			    
		   }
/*
//-----------------------------------------------三月内人事令--------------------------------------------------------//
			  
			HrmCommonDAO hrmCommonDAO = new HrmCommonDAO();  
			Map map = hrmCommonDAO.getOrderMap();
			List UpgradeList = (List) map.get("UpgradeList");
			session.setAttribute("UpgradeList",UpgradeList);     
*/
//-----------------------------------------------个人申请项提示------------------------------------------------------//
			AdminBean adminbean=(AdminBean) request.getSession().getAttribute("admin");
			String id=adminbean.getAdminID();
			int personalInfoApplyCount=tipmessagedao.getPersonalInfoApplyCount(id);      
			int personalApplyOtCount=tipmessagedao.getPersonalApplyOtCount(id);
			int personalApplyLeavelCount=tipmessagedao.getPersonalApplyLeavelCount(id);  
			int personalApplyErvectionCount=tipmessagedao.getPersonalApplyErvectionCount(id);
			int personalApllyTrainningCount=tipmessagedao.getPersonalApplyTrainningCount(id);
			session.setAttribute("personalInfoApplyCount",personalInfoApplyCount);
			session.setAttribute("personalApplyOtCount",personalApplyOtCount);
			session.setAttribute("personalApplyLeavelCount",personalApplyLeavelCount);
			session.setAttribute("personalApplyErvectionCount",personalApplyErvectionCount);
			session.setAttribute("personalApllyTrainningCount",personalApllyTrainningCount);
			session.setAttribute("applyInfo",true);
					
    }//方法结束
}