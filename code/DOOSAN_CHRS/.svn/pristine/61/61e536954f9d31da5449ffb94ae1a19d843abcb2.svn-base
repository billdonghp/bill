package com.ait.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.business.ArServices;
import com.ait.ess.business.EssServices;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.hrm.tipmessagedao.TipMessageDao;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.sy.service.SysService;
import com.ait.util.DateUtil;
import com.ait.util.GetLoginCodeType;

public class MainCommand implements Command {

	private UserConfiguration userConfig;

	private static final String defaultSysFile = "/system.properties";

	public MainCommand() {
	}

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleMap sm = new SimpleMap();

		try {
			userConfig = UserConfiguration.getInstance(defaultSysFile);
			AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
			TipMessageDao tipmessagedao = new TipMessageDao();
			EssServices essServices = new EssServices();
			String[] sgNo = admin.getScreenGrantNo().split(",");
			String grant = userConfig.getString("grant.module."+admin.getCpnyId()).trim();
			
			// ----------------------------------------考勤待决裁信息-----------------------------------------------------------------//
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.hraffirmor").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1
						|| ("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 )
					b = true;
			}
			if (b) {
				String MaxLevel = tipmessagedao.getLogOnPersonAffirmMaxLevel("hrm.tip.getLogOnTipMessage", admin.getAdminID());
				String MaxLevelAr = tipmessagedao.getLogOnPersonAffirmMaxLevel("hrm.tip.getLogOnTipMessageAr", admin.getAdminID());

				SimpleMap smap = new SimpleMap();
				smap.setString("adminid", admin.getAdminID());
				smap.setString("cpnyId", admin.getCpnyId());
				smap.setString("maxLevel", MaxLevel);
				smap.setString("MaxLevelAr", MaxLevelAr);
				int countOT = tipmessagedao.getCount("hrm.tip.getCountOt", smap);
				int countLeave = tipmessagedao.getCount("hrm.tip.getCountLeave", smap);
				int armodifyaffirmNo = tipmessagedao.getCount("hrm.tip.getArmodifyaffirmNo", smap);//getArmodifyaffirmNo
				request.setAttribute("affirmFlag", true);
				request.setAttribute("attAffirmFlag", true);
				request.setAttribute("affirmCountOT", countOT);
				request.setAttribute("affirmCountLeave", countLeave);
				request.setAttribute("armodifyaffirmNo", armodifyaffirmNo);

			}
			
			// ----------------------------------------总务待决裁信息-----------------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.gaaffirmor").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1
					 || ("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap smap = new SimpleMap();
				smap.put("adminid", admin.getAdminID());
				smap.set("applerId", admin.getAdminID());
				smap.set("qryType", "0");
				int countVisiter = tipmessagedao.getCount("hrm.tip.getVisiterAffirmCnt", smap);
				int countEatery = tipmessagedao.getCount("hrm.tip.getEateryAffirmCnt", smap);
				int countPresent = tipmessagedao.getCount("hrm.tip.getPresentAffirmCnt", smap);
				int affirmCountExpenses = tipmessagedao.getCount("hrm.tip.expressAffirmListNumber", smap);
				int affirmCountSeal = tipmessagedao.getCount("hrm.tip.sealAffirmAndInformationListNumber", smap);
				int affirmCountVisa = tipmessagedao.getCount("hrm.tip.visaAffirmAndInformationListNumber", smap);
				int affirmCountCheck = tipmessagedao.getCount("hrm.tip.getCheckAffirmCnt", smap);
				int voitureInt = tipmessagedao.getCount("hrm.tip.getvoitureInt",smap);
				int affirmCountProducts = tipmessagedao.getCount("hrm.tip.getProductsAffirmCnt", smap);
				int affirmCountSecurity = tipmessagedao.getCount("hrm.tip.getSecurityAffirmCnt", smap);
				int affirmCountFacility = tipmessagedao.getCount("hrm.tip.getFacilityAffirmCnt", smap);
				int affirmCountForkli = tipmessagedao.getCount("hrm.tip.getForkliAffirmCnt", smap);
			
				
				request.setAttribute("affirmFlag", true);
				request.setAttribute("gaAffirmFlag", true);
				request.setAttribute("affirmCountVisiter", countVisiter);
				request.setAttribute("affirmCountEatery", countEatery);
				request.setAttribute("voitureInt", voitureInt);
				request.setAttribute("affirmCountPresent", countPresent);
				request.setAttribute("affirmCountExpenses", affirmCountExpenses);
				request.setAttribute("affirmCountSeal", affirmCountSeal);
				request.setAttribute("affirmCountVisa", affirmCountVisa);
				request.setAttribute("affirmCountCheck", affirmCountCheck);
				request.setAttribute("affirmCountProducts", affirmCountProducts);
				request.setAttribute("affirmCountSecurity", affirmCountSecurity);
				request.setAttribute("affirmCountFacility", affirmCountFacility);
				request.setAttribute("affirmCountForkli", affirmCountForkli);
				

			}

			// ----------------------------------------技能评价待决裁信息-----------------------------------------------------------------//
			 b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if ((",82,").indexOf(","+sgNo[i].trim()+",") > -1
						|| ("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 )
					b = true;
			}
			if (b) {
				SimpleMap smap = new SimpleMap();
				smap.put("adminid", admin.getAdminID());
				smap.set("applerId", admin.getAdminID());
				smap.set("qryType", "0");
				int affirmCountGxxg = tipmessagedao.getCount("hrm.tip.getGxxgAffirmCnt", smap);
				int affirmCountGxjndj = tipmessagedao.getCount("hrm.tip.getGxjndjAffirmCnt", smap);
				int affirmCountZyzgdj = tipmessagedao.getCount("hrm.tip.getZyzgdjAffirmCnt", smap);
				int affirmCountPjcj = tipmessagedao.getCount("hrm.tip.getPjcjAffirmCnt", smap);
				int affirmCountGjjs = tipmessagedao.getCount("hrm.tip.getGjjsdjAffirmCnt", smap);
				
				request.setAttribute("affirmjnpjFlag", true);
				request.setAttribute("affirmCountGxxg", affirmCountGxxg);
				request.setAttribute("affirmCountGxjndj", affirmCountGxjndj);
				request.setAttribute("affirmCountZyzgdj", affirmCountZyzgdj);
				request.setAttribute("affirmCountPjcj", affirmCountPjcj);
				request.setAttribute("affirmCountGjjs", affirmCountGjjs);

			}
			// -------------------------------------------人事待确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1
				     || ("," + userConfig.getString("common.role.attAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				
				SimpleMap smap = new SimpleMap();
				smap.setString("Type", "LeaveApply");
				smap.setString("cpnyId", admin.getCpnyId());
			    HrmCommonDAO had = new HrmCommonDAO();      
				int otCount = had.getOtConfirmCnt(smap);
				int leaveCount = had.getConfirmCnt(smap);
				
				request.setAttribute("confirmOtCount", otCount);
				request.setAttribute("confirmLeaveCount", leaveCount);
				request.setAttribute("attConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置人事确认标志，在main.jsp中用来判断显示提示与否

			}
			
			// -------------------------------------------参观者担当待确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("visiter") > -1
					|| ("," + userConfig.getString("common.role.visiterAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				
				int countVisiter = tipmessagedao.getCount("hrm.tip.getVisiterConfirmCnt", simpleMap);
				int countVisa = tipmessagedao.getCount("hrm.tip.getVisiterConfirmCnt", simpleMap);
				
				request.setAttribute("confirmVisiterCnt", countVisiter);
				request.setAttribute("countVisaCnt", countVisa);
				request.setAttribute("visiterConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
			// -------------------------------------------会议室担当待确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("meeting") > -1
					|| ("," + userConfig.getString("common.role.meetingAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				
				int countMeetingroom = tipmessagedao.getCount("hrm.tip.getMeetingroomConfirmCnt",simpleMap);
				
				request.setAttribute("confirmMeetingCnt", countMeetingroom);
				request.setAttribute("meetingConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			// -------------------------------------------快件担当待确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("expenses") > -1
					|| ("," + userConfig.getString("common.role.expensesAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminid", admin.getAdminID());
				simpleMap.set("qryType", "0");
				int countExpenses = tipmessagedao.getCount("gm.express.expressConfirmInfoListNumber",simpleMap);
				
				request.setAttribute("confirmExpensesCnt", countExpenses);
				request.setAttribute("ExpensesConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
			// -------------------------------------------公章担当待确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("seal") > -1
					|| ("," + userConfig.getString("common.role.sealAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("applerId", admin.getAdminID());
				simpleMap.set("qryType", "0");
				int countExpenses = tipmessagedao.getCount("gm.sealmanagement.allWaitConfirmListNumber",simpleMap);
				
				request.setAttribute("confirmSealCnt", countExpenses);
				request.setAttribute("SealConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			// -------------------------------------------派车确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("voiture") > -1
						|| ("," + userConfig.getString("common.role.voitureAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("applerId", admin.getAdminID());
				simpleMap.set("qryType", "0");
				int confirmvoitureCnt = tipmessagedao.getCount("hrm.tip.confirmvoitureCnt",simpleMap);
				
				request.setAttribute("confirmvoitureCnt", confirmvoitureCnt);
				request.setAttribute("voitureConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
        // -------------------------------------------就餐确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("eatery") > -1
						|| ("," + userConfig.getString("common.role.eateryAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				int countEateryConfirmCnt = tipmessagedao.getCount("hrm.tip.countEateryConfirmCnt", simpleMap);
				
				request.setAttribute("countEateryConfirmCnt", countEateryConfirmCnt);
				request.setAttribute("eateryConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}

	        // -------------------------------------------礼品确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("present") > -1
						|| ("," + userConfig.getString("common.role.presentAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				int presentConfirmCnt = tipmessagedao.getCount("hrm.tip.getPresentConfirmCnt", simpleMap);
				
				request.setAttribute("presentConfirmCnt", presentConfirmCnt);
				request.setAttribute("presentConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
			 // -------------------------------------------洗衣确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("wash") > -1
						|| ("," + userConfig.getString("common.role.washAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				int washConfirmCnt = tipmessagedao.getCount("hrm.tip.getWashConfirmCnt", simpleMap);
				
				request.setAttribute("washConfirmCnt", washConfirmCnt);
				request.setAttribute("washConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			// -------------------------------------------安全确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("security") > -1
						|| ("," + userConfig.getString("common.role.securityAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				int SecurityConfirmCnt = tipmessagedao.getCount("hrm.tip.getSecurityConfirmCnt", simpleMap);
				
				request.setAttribute("SecurityConfirmCnt", SecurityConfirmCnt);
				request.setAttribute("SecurityConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
			 // -------------------------------------------制品确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("product") > -1
						|| ("," + userConfig.getString("common.role.productsAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				String codeStr="";
				List codeType=admin.getCodeType();		
				List result= GetLoginCodeType.getLoginCodeType(codeType,"ga0403");		
				for(int i=0;i<result.size();i++){
					codeStr +="'"+(String)result.get(i)+"',";
				}
				if(!codeStr.equals("")){
				codeStr=codeStr.substring(0,codeStr.length()-1);
				}
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				simpleMap.set("codeType", codeStr);	
				int productsConfirmCnt = tipmessagedao.getCount("hrm.tip.getProductsConfirmCnt", simpleMap);
				
				request.setAttribute("productsConfirmCnt", productsConfirmCnt);
				request.setAttribute("productsConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
			//	-------------------------------------------节日礼品确认信息----------------------------------------------------------//
			b = false;
			for (int i = 0; i < sgNo.length; i++) {
				if (("," + userConfig.getString("common.role.administrator").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 
						&& grant.indexOf("holidy") > -1
						|| ("," + userConfig.getString("common.role.holidayPresentAdmin").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1)
					b = true;
			}
			if (b) {
				SimpleMap simpleMap=new SimpleMap();
				simpleMap.set("adminId", admin.getAdminID());
				simpleMap.set("cpnyId", admin.getCpnyId());
				int festivalPresentConfirmCnt = tipmessagedao.getCount("hrm.tip.getFestivalPresentConfirmCnt", simpleMap);
				
				request.setAttribute("festivalPresentConfirmCnt", festivalPresentConfirmCnt);
				request.setAttribute("festivalPresentConfirmFlag", true);
				request.setAttribute("confirmFlag", true);// 设置总务标志，在main.jsp中用来判断显示提示与否

			}
			
			// -----------------------------------------------个人申请项提示------------------------------------------------------// 
			String today = DateUtil.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd") ;
			String sDate = today;
			String eDate = today;	
			String starteDate = today;
			String endDate = today;
			sm.set("sDate", sDate);
			sm.set("eDate", eDate);
			sm.set("supervisor", admin.getAdminID());
			sm.set("adminId", admin.getAdminID());	
			SimpleMap parameterObjectDate = new SimpleMap();
			parameterObjectDate.set("cpnyId", admin.getCpnyId());			
			SimpleMap smap = new SimpleMap();				
				smap = (SimpleMap) essServices.getOtViewListFirstDate(parameterObjectDate);
				if (smap.getString("MINDATE") == null) {
					ArServices arServices = new ArServices();
					starteDate = arServices.getStartDateStr();
					endDate = arServices.getEndDateStr();
				} else {
					starteDate = smap.getString("MINDATE");
					endDate = smap.getString("MAXDATE");
				}
			sm.set("starteDate", starteDate);
			sm.set("endDate", endDate);
			int applyOtCount=tipmessagedao.getPersonalApplyOtCount(sm);
			int applyLeavelCount=tipmessagedao.getPersonalApplyLeavelCount(admin.getAdminID());
			int applyVisiterCount=tipmessagedao.getCount("hrm.tip.getVisiterApplyCnt", sm);
			int applyMeetingCount=tipmessagedao.getCount("hrm.tip.getMeetingroomApplyCnt", sm);
			int applyEateryCount=tipmessagedao.getCount("hrm.tip.getEateryApplyCnt", sm);
			int applyPresentCount=tipmessagedao.getCount("hrm.tip.getPresentApplyCnt", sm);
			int applyWashCount=tipmessagedao.getCount("hrm.tip.getWashApplyCnt", sm);
			int applyProductsCount=tipmessagedao.getCount("hrm.tip.getProductsApplyCnt", sm);
			int applyvoitureCount=tipmessagedao.getCount("hrm.tip.getVoitureListList", sm);
			int applyExpensesCount=tipmessagedao.getCount("hrm.tip.expressAffirmInfoListNumber", sm);
			int applySealCount=tipmessagedao.getCount("hrm.tip.allsealAffrimInfoInt", sm);
			int applyVisaCount=tipmessagedao.getCount("hrm.tip.allvisaAffrimInfoInt", sm);
			int applySecurityCount=tipmessagedao.getCount("hrm.tip.getSecurityApplyCnt", sm);
			int applyFestivalPresentCount=tipmessagedao.getCount("hrm.tip.getFestivalPresentApplyCnt", sm);
			int applyModifyCount=tipmessagedao.getCount("hrm.tip.getApplyLeavelModifyCount", sm);
			
			request.setAttribute("personalApplyOtCount", applyOtCount);
			request.setAttribute("applyvoitureCount", applyvoitureCount);
			request.setAttribute("personalApplyLeavelCount", applyLeavelCount);
			request.setAttribute("personalApplyLeavelModifyCount", applyModifyCount);
			request.setAttribute("applyVisiterCount", applyVisiterCount);
			request.setAttribute("applyMeetingCount", applyMeetingCount);
			request.setAttribute("applyEateryCount", applyEateryCount);
			request.setAttribute("applyPresentCount", applyPresentCount);
			request.setAttribute("applyWashCount", applyWashCount);
			request.setAttribute("applyExpensesCount", applyExpensesCount);
			request.setAttribute("applySealCount", applySealCount);
			request.setAttribute("applyVisaCount", applyVisaCount);
			request.setAttribute("applyProductsCount", applyProductsCount);
			request.setAttribute("applySecurityCount", applySecurityCount);
			request.setAttribute("applyFestivalPresentCount", applyFestivalPresentCount);
			
			// ---------------------------  读取配置文件，设置首页总务相关提醒的详细权限 ------------------------------------- //
			if(grant.indexOf("voiture") > -1)
				request.setAttribute("voitureFlag", true);
			if(grant.indexOf("visiter") > -1)
				request.setAttribute("visiterFlag", true);
			if(grant.indexOf("meeting") > -1)
				request.setAttribute("meetingFlag", true);
			if(grant.indexOf("eatery") > -1)
				request.setAttribute("eateryFlag", true);
			if(grant.indexOf("present") > -1)
				request.setAttribute("presentFlag", true);
			if(grant.indexOf("wash") > -1)
				request.setAttribute("washFlag", true);
			if(grant.indexOf("expenses") > -1)
				request.setAttribute("expensesFlag", true);
			if(grant.indexOf("seal") > -1)
				request.setAttribute("sealFlag", true);
			if(grant.indexOf("check") > -1)
				request.setAttribute("checkFlag", true);
			if(grant.indexOf("product") > -1)
				request.setAttribute("productFlag", true);
			if(grant.indexOf("security") > -1)
				request.setAttribute("securityFlag", true);
			if(grant.indexOf("holidy") > -1)
				request.setAttribute("festivalFlag", true);
			if(grant.indexOf("jnpj") > -1)
				request.setAttribute("jnpjFlag", true);
			
			
			// ------------取得可选择的法人列表-------------//
			SysService service = SysService.getInstance();
			SimpleMap parameterObject = new SimpleMap();
			
			//原数据
//			parameterObject.setString("adminid", admin.getUsername());
			
			//添加Ad 验证
			
			parameterObject.setString("adminid", admin.getAd_User_Id());
			
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			List empDiff = service.getEmpDiff(parameterObject);
			request.setAttribute("empDiff", empDiff);
			request.setAttribute("diffCnt", empDiff.size());
			request.setAttribute("cpnyId", admin.getCpnyId());
			
			return "/main.jsp";
//			return "/hint.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "/loginfailed.jsp";
		}
	}
}