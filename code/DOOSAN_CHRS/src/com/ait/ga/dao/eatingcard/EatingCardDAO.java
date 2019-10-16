package com.ait.ga.dao.eatingcard;

import java.util.List;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.cmd.visit.GaApply;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-7-17
 * 
 */
public class EatingCardDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public EatingCardDAO() {
		
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}	
	public int  getEatingCardAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.eatingCardAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public int  getTempCardAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.tempCardAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public int  getCheckAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.check.checkAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getEatingCardAffirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.eatingCardAffirmList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmList data Exception. ", e);
		}
		return temp;
	}
	public List  getTempCardAffirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.tempCardAffirmList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmList data Exception. ", e);
		}
		return temp;
	}
	public List  getCheckAffirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.checkAffirmList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmList data Exception. ", e);
		}
		return temp;
	}
	public int  getEatingCardAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.eatingCardAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	public int  getTempCardAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.tempCardAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	public int  getCheckAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.check.checkAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getEatingCardAffirmInfoList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.eatingCardAffirmInfoList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"eatingCardAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	public List  getTempCardAffirmInfoList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.tempCardAffirmInfoList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"eatingCardAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	public List  getCheckAffirmInfoList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.checkAffirmInfoList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"eatingCardAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	public List  getEateryTypeName(Object parameterObject) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getEateryTypeName",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	public List  getTempCardTypeName(Object parameterObject) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getTempCardTypeName",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	
	
	public List  getEatingCardAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.eatingCardAffirmorList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmorList data Exception. ", e);
		}
		return temp;
	}
	public List  getTempCardAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.tempCardAffirmorList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmorList data Exception. ", e);
		}
		return temp;
	}
	public List  getCheckAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.checkAffirmorList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmorList data Exception. ", e);
		}
		return temp;
	}
	public int  getEateryConfirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.getEateryConfirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public int  getTempCardConfirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.getTempCardConfirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public int  getCheckConfirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.check.getCheckConfirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getEateryConfirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getEateryConfirmList",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public List  getTempCardConfirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getTempCardConfirmList",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public List  getCheckConfirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.getCheckConfirmList",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public List  tempCardExcelList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getTempCardConfirmList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public List  checkExcelList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.getCheckConfirmList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public List  checkAccountExcelList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.retrieveCheckAccountList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public List  checkAccountBalanceList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.check.checkAccountBalanceList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEateryConfirmListList data Exception. ", e);
		}
		return temp;
	}
	public int  getvoitureInt(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.getvoitureInt", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getdriverOtInt(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.getdriverOtInt", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getdriverOtNumber data Exception. ", e);
		}
		return temp;
	}
	
	public int  getBusArrangeInt(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.eatingCard.getBusArrangeInt", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getBusArrangeInt data Exception. ", e);
		}
		return temp;
	}
	
	public List  getvoitureList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getvoitureList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List  getVoitureDistinctionList(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getVoitureDistinctionList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List  getdriverOtList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getdriverOtList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getdriverOtList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getdriverOtListAll(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getdriverOtListAll", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getdriverOtList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getbusArrangeListAll(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getbusArrangeListAll", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getbusArrangeListAll data Exception. ", e);
		}
		return temp;
	}
	
	public List  getBusArrangeList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getBusArrangeList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getBusArrangeList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getBusArrangeListAll(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getBusArrangeListAll", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getBusArrangeListAll data Exception. ", e);
		}
		return temp;
	}
	
	
	public List  getVoitureAffiirmList(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getVoitureAffiirmList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List  getDriverOtAffiirmList(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getDriverOtAffiirmList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getDriverOtAffiirmList data Exception. ", e);
		}
		return temp;
	}
	
	public List  getBusArrangeAffiirmList(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getBusArrangeAffiirmList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getBusArrangeAffiirmList data Exception. ", e);
		}
		return temp;
	}
	
	public boolean  AddCardApplication(SimpleMap parameterObject,List affirmorList) throws GlRuntimeException {
		GaApply gaApply = new GaApply();
		boolean flag = true;
		try {
			commonSQLMapAdapter.startTransaction();
			int applyNo=gaApply.getSequence("ga_card_application_seq");
			parameterObject.set("applyNo", applyNo);
			commonSQLMapAdapter.insert("ga.eatingCard.addCardApplication", parameterObject);
			for(int i=0;i<affirmorList.size();i++){
				GaAffirmList gaAffirmList=(GaAffirmList)affirmorList.get(i);				
				parameterObject.set("affirmorId", gaAffirmList.getAffirmorId());
				parameterObject.set("affirmorLevel", gaAffirmList.getAffirmLevel());
				parameterObject.set("applyNo",applyNo);
				
				commonSQLMapAdapter.insert("ga.eatingCard.addAffirmListForCardApply", parameterObject);	
			}
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			flag = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return flag;
	}
	
	public boolean  AddCheckApply(SimpleMap parameterObject,String[] affirmorList) throws GlRuntimeException {
		GaApply gaApply = new GaApply();
		boolean flag = true;
		try {
			commonSQLMapAdapter.startTransaction();
			int applyNo=gaApply.getSequence("GA_CHECK_APPLICATION_SEQ");
			parameterObject.set("applyNo", applyNo);
			commonSQLMapAdapter.insert("ga.check.addcheckApplication", parameterObject);
			for(int i=0;i<affirmorList.length;i++){
//				GaAffirmList gaAffirmList=(GaAffirmList)affirmorList.get(i);				
				parameterObject.set("affirmorId", affirmorList[i]);
				parameterObject.set("affirmorLevel", i+1);
				parameterObject.set("applyNo",applyNo);
				
				commonSQLMapAdapter.insert("ga.check.addAffirmListForCheckApply", parameterObject);	
			}
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			flag = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return flag;
	}
	
	public boolean  AddTempCardApplication(SimpleMap parameterObject,String[] affirmorList) throws GlRuntimeException {
		GaApply gaApply = new GaApply();
		boolean flag = true;
		try {
			commonSQLMapAdapter.startTransaction();
			int applyNo=gaApply.getSequence("GA_TCAR_APPLICATION_SEQ");
			parameterObject.set("applyNo", applyNo);
			commonSQLMapAdapter.insert("ga.eatingCard.addTempCardApplication", parameterObject);
			for(int i=0;i<affirmorList.length;i++){
//				GaAffirmList gaAffirmList=(GaAffirmList)affirmorList.get(i);				
				parameterObject.set("affirmorId", affirmorList[i]);
				parameterObject.set("affirmorLevel", i+1);
				parameterObject.set("applyNo",applyNo);
				
				commonSQLMapAdapter.insert("ga.eatingCard.addAffirmListForTempCardApply", parameterObject);	
			}
			commonSQLMapAdapter.commitTransation();

			//推送
			DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_TEMP_CARD, applyNo + "");

		} catch (Exception e) {
			flag = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getEatingCardAffirmListNumber data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return flag;
	}

	public void  oingAffirm(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.eatingCard.oingAffirm", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  oingTempAffirm(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.eatingCard.oingTempAffirm", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  oingCheckAffirm(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.check.oingCheckAffirm", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  oingConfirm(List parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.updateForMulti("ga.eatingCard.oingConfirm", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  oingTempCardConfirm(List parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.updateForMulti("ga.eatingCard.oingTempCardConfirm", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  oingCheckConfirm(List parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.updateForMulti("ga.check.oingCheckConfirm", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  oingUpdateCheckAccount(List parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.updateForMulti("ga.check.oingUpdateCheckAccount", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	
	public void  cardProvideAdd(Object parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.insert("ga.eatingCard.cardProvideAdd", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm oingAffirm data Exception. ", e);
		}
		
	}
	public void  cardProvideUpdate(Object parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.eatingCard.cardProvideUpdate", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"cardProvideUpdate cardProvideUpdate data Exception. ", e);
		}
		
	}
	public void  tempCardSave(Object parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.eatingCard.tempCardSave", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"cardProvideUpdate cardProvideUpdate data Exception. ", e);
		}
		
	}
	public void  cardProvideDelete(Object parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.delete("ga.eatingCard.cardProvideDelete", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"cardProvideDelete  data Exception. ", e);
		}
		
	}
	
	public List  getCardType(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.getCardType", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCardType data Exception. ", e);
		}
		return temp;
	}
	public List  cardProvidList(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.cardProvidList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"cardProvidList data Exception. ", e);
		}
		return temp;
	}

	public List  tempCardProvidList(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.tempCardProvidList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"cardProvidList data Exception. ", e);
		}
		return temp;
	}
	
	public String  getupaffrimemail(SimpleMap parameterObject) throws GlRuntimeException {		
		String result="";
		try {
			
			result=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.eatingCard.getupaffrimemail", parameterObject));
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm getupaffrimemail data Exception. ", e);
		}
		return result;
	}
	public String  getuptcaraffrimemail(SimpleMap parameterObject) throws GlRuntimeException {		
		String result="";
		try {
			
			result=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.eatingCard.getuptcaraffrimemail", parameterObject));
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm getupaffrimemail data Exception. ", e);
		}
		return result;
	}
	public String  getupcheckaffrimemail(SimpleMap parameterObject) throws GlRuntimeException {		
		String result="";
		try {
			
			result=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.check.getupcheckaffrimemail", parameterObject));
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm getupaffrimemail data Exception. ", e);
		}
		return result;
	}
	public void  updateApplyInfo(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.eatingCard.updateApplyInfo", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm updateApplyInfo data Exception. ", e);
		}
		
	}
	public void  updateTempCardApplyInfo(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.eatingCard.updateTempCardApplyInfo", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm updateApplyInfo data Exception. ", e);
		}
		
	}
	public void  updateCheckApplyInfo(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.check.updateCheckApplyInfo", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm updateApplyInfo data Exception. ", e);
		}
		
	}
	public void  updateCheckInfo(SimpleMap parameterObject) throws GlRuntimeException {		
		try {
			
			commonSQLMapAdapter.update("ga.check.updateCheckInfo", parameterObject);
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"EatingCardAffirm updateApplyInfo data Exception. ", e);
		}
		
	}
	
	
	
}
