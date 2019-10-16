package com.ait.ga.dao.securityenvironment;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-24
 * 
 */
public class SecurityEnvironmentDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public SecurityEnvironmentDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List getIsNotExitsSecurityEnvironmentPlan(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.IsNotExitsSecurityEnvironmentPlanList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"IsNotExitsSecurityEnvironmentPlanList data Exception. ", e);
		}
		return list;
	}
	
	public List getIsNotExitsSecurityEnvironment(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.IsNotExitsSecurityEnvironmentList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getIsNotExitsSecurityEnvironment data Exception. ", e);
		}
		return list;
	}
	public boolean  andSecurityEnvironmentApply(Object parameterObject,List affirorList) throws GlRuntimeException {
		GaAffirmList gaAffirmList = null;
		SimpleMap dateMap = (SimpleMap) parameterObject;
		boolean temp = false;
		int confirmnumber = 0;
		try {
			//commonSQLMapAdapter.startTransaction();
			//commonSQLMapAdapter.update("ga.securityenvironment.updateSecurityChecksState",parameterObject);
			//confirmnumber += 1;
			commonSQLMapAdapter.insert("ga.securityenvironment.andSecurityEnvironmentApply",parameterObject);
			confirmnumber += 1;
			for (int i = 0; i < affirorList.size(); i++) {
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
				dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
				commonSQLMapAdapter.insert("ga.securityenvironment.andSecurityEnvironmentAffirm",
						dateMap);
				confirmnumber += 1;
			}
			temp = true;
			/*if (confirmnumber == (affirorList.size() + 2)) {
				commonSQLMapAdapter.commitTransation();
				commonSQLMapAdapter.endTransation();
				temp = true;
			}*/

		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert andSecurityEnvironmentApply data Exception. ", e);
		}
		return temp;
	}
	
	public int  getCorrectivePlanAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.correctivePlanAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getCorrectivePlanAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.correctivePlanAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getIfAffirmNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.getIfAffirmNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getIfAffirmNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List  getCorrectivePlanAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.correctivePlanAffirmList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirmList data Exception. ", e);
		}
		return temp;
	}
	public List  getCorrectivePlanAffirmInfoList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.correctivePlanAffirmInfoList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	
	public List  getCorrectivePlanAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.correctivePlanAffirmorList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCorrectivePlanAffirmorList data Exception. ", e);
		}
		return temp;
	}
	public String  getapplyemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.securityenvironment.getapplyemail", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getapplyemail data Exception. ", e);
		}
		return temp;
	}
	public String  getForkliftAffirmEmail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.securityenvironment.getForkliftAffirmEmail", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getForkliftAffirmEmail data Exception. ", e);
		}
		return temp;
	}
	public String  getupaffrimemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.securityenvironment.getupaffrimemail", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getupaffrimemail data Exception. ", e);
		}
		return temp;
	}
	public String  getlevelaffmail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.securityenvironment.getlevelaffemail", parameterObject)).toString();
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getupaffrimemail data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  ongingAffirm(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;		
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.ongingAffirm", parameterObject);
			commonSQLMapAdapter.update("ga.securityenvironment.updateCheckDate", parameterObject);	
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"ongingAffirm data Exception. ", e);
		}
		return temp;
	}
	public boolean  updateComplate(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;		
		try {	
			commonSQLMapAdapter.update("ga.securityenvironment.updateComplate", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"ongingAffirm data Exception. ", e);
		}
		return temp;
	}
	public List  getIsNotOingCompletedRectification(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.IsNotOingCompletedRectificationList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getIsNotOingCompletedRectification data Exception. ", e);
		}
		return temp;
	}
	public int  getCompletedRectificationAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.completedRectificationAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	//facilityRectificationAffirmListNumber
	public int  getFacilityRectificationAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.facilityRectificationAffirmListNumber", parameterObject).toString());
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getForkliftMaintainAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.forkliftmaintainAffirmListNumber", parameterObject).toString());
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getforkliftmaintainAffirmListNumberAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getCompletedRectificationAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.completedRectificationAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	
	
	
	public int  getFacilityRectificationAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.facilityRectificationAffirmInfoListNumber", parameterObject).toString());
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getFacilityRectificationAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getForkliRectificationAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.forkliRectificationAffirmInfoListNumber", parameterObject).toString());
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getforkliRectificationAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getUpAffrimNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.securityenvironment.getUpAffrimNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getUpAffrimNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getCompletedRectificationAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.completedRectificationAffirmList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	//设施维修申请
	public List  getFacilityRectificationAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.facilityRectificationAffirmList", parameterObject, currentPage, pageSize);
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getFacilityRectificationAffirmListNumber data Exception. ", e);
		}
		return temp;
	}	
	public List  getForkliftMaintainAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.forkliftmaintainAffirmList", parameterObject, currentPage, pageSize);
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getForkliftMaintainAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getCompletedRectificationAffirmInfoList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.completedRectificationAffirmInfoList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	//得到维修情况数据
	public List  getFacilityRectificationAffirmInfoList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.facilityRectificationAffirmInfoList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getFacilityRectificationAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getForkliRectificationAffirmInfoList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.forkliRectificationAffirmInfoList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getForkliRectificationAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getExcelFacilityRectificationAffirmInfoList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.excelfacilityRectificationAffirmInfoList", parameterObject);
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getFacilityRectificationAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getExcelForkliRectificationAffirmInfoList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.excelForkliRectificationAffirmInfoList", parameterObject);
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExcelForkliRectificationAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	public List  facilityExcelList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.facilityExcelList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getFacilityRectificationAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getCompletedRectificationAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.completedRectificationAffirmorList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompletedRectificationAffirmorList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getExForkliRectificationAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.forkliRectificationAffirmorList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getForkliRectificationAffirmorList data Exception. ", e);
		}
		return temp;
	}

	
	public List  getForkliftMaintainAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.securityenvironment.forkliftMaintainAffirmorList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getforkliftMaintainAffirmorListAffirmorList data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crfongingAffirm(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingAffirm", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crfongingAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crforkliAffirm(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crforkliAffirm", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crforkliAffirm data Exception. ", e);
		}
		return temp;
	}
	
	//添加设施维修担当
	public boolean  crfongingAffirmWD(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingAffirmWD", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"UPDATE data  Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crForkliAffirmWD(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crforkliAffirmWD", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"UPDATE data  Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crfongingAffirm01(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingAffirm01", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crfongingAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crForkliAffirm(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crForkliAffirm", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crForkliAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crfongingAffirm03(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingAffirm03", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crfongingAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crfongingAffirm04(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingAffirm04", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crfongingAffirm data Exception. ", e);
		}
		return temp;
	}
	public boolean  crfongingAffirm02(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingAffirm02", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crfongingAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  crForkliftMantainAffirm02(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crforkliftmaintainAffirm02", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"crforkliftmaintainAffirm02 data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  oingCrfongingAffirm(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.crfongingFacilityAffirm", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"oingCrfongingAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  oingCrForkliftMaintainAffirm(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.forkliftmaintainAffirm", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"oingCrForkliftMaintainAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  oingCorrectivePlanDelete(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.oingCorrectivePlanDelete", parameterObject);
			commonSQLMapAdapter.update("ga.securityenvironment.updateCorrectivePlanState", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"oingCorrectivePlanDelete data Exception. ", e);
		}
		return temp;
	}
	public boolean  oingCompletedRectificationDelete(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.oingCompletedRectificationDelete", parameterObject);
			commonSQLMapAdapter.update("ga.securityenvironment.updateCompletedRectificationState", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"oingCompletedRectificationDelete data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  confirmCrfongingApply(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.confirmCrfongingApply", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmCrfongingApply data Exception. ", e);
		}
		return temp;
	}
	
	//设施维修决裁
	public boolean  facilityConfirmCrfongingApply(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.facilityconfirmCrfongingApply", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmCrfongingApply data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  forkliConfirmCrfongingApply(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.forkliconfirmCrfongingApply", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"forkliConfirmCrfongingApply data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  confirmCrfongingRecord(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.update("ga.securityenvironment.confirmCrfongingRecord", parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmCrfongingRecord data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  DelAffirmInfo(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.delete("ga.securityenvironment.DelapplayInfo", parameterObject);
			commonSQLMapAdapter.delete("ga.securityenvironment.DelAffirmInfo", parameterObject);
			commonSQLMapAdapter.update("ga.securityenvironment.udateApplayInfo", parameterObject);
			commonSQLMapAdapter.commitTransation();
			temp = true;
		} catch (Exception e) {

			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"DelAffirmInfo data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return temp;
	}
	public boolean  DelAffirmInfoFa(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.delete("ga.securityenvironment.DelapplayInfo", parameterObject);
			commonSQLMapAdapter.delete("ga.securityenvironment.DelAffirmInfo", parameterObject);
			commonSQLMapAdapter.commitTransation();
			temp = true;
		} catch (Exception e) {
			
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"DelAffirmInfo data Exception. ", e);
		} finally {
			
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return temp;
	}
	
	public boolean  DelForkliAffirmInfoFa(Object parameterObject) throws GlRuntimeException {
		boolean  temp=false;
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.delete("ga.securityenvironment.DelForkliAffirmInfo", parameterObject);
			commonSQLMapAdapter.delete("ga.securityenvironment.DelForkliapplayInfo", parameterObject);
			commonSQLMapAdapter.commitTransation();
			temp = true;
		} catch (Exception e) {
			
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"DelFokliAffirmInfo data Exception. ", e);
		} finally {
			
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return temp;
	}
	
	public String  getApplayInfo(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp = (String)commonSQLMapAdapter.executeQuery("ga.securityenvironment.getApplayInfo", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getApplayInfo data Exception. ", e);
		}
		return temp;
	}
	
	public String  getForkliApplayInfo(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp = (String)commonSQLMapAdapter.executeQuery("ga.securityenvironment.getForkliApplayInfo", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getForkliApplayInfo data Exception. ", e);
		}
		return temp;
	}
	
}
