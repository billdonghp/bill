/*
 * @(#)PaUtilDAO.java 1.0 2007-11-10 下午08:30:28
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.pa.dao.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-10-8 下午09:23:51
 * @version 1.0
 * 
 */

public class PaUtilDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PaUtilDAO.class);

	public PaUtilDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * retrieve update pa tables 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List retrieveUpdatePaTableColumns(Object parameterObject) throws GlRuntimeException {

		List result = new ArrayList() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrieveUpdatePaTableColumns",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve update pa Exception. ", e);
		}
		return result;
	}
	
	/**
	 * update pa bonus history 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusHistory(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.util.updatePaBonusHistory",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus history Exception. ", e);
		}
	}
	
	/**
	 * update pa bonus history proc 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void callPaBonusHistoryProc(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.util.callPaBonusHistoryProc",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("call pa bonus history proc Exception. ", e);
		}
	}
	
	/**
	 * update pa history data 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePaHistoryData(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.util.updatePaHistoryData",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa history data Exception. ", e);
		}
	}
	
	/**
	 * update pa history proc 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void callPaHistoryProc(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.util.callPaHistoryProc",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("call pa history proc Exception. ", e);
		}
	}
	
	
	/**
	 * retrieve pa mail count
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrievePaMailCount(Object parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.retrievePaMailCount",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve pa mail count  Exception. ", e);
		}
		return result;
	}
	public int retrievePaOtherMailCount(Object parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.retrievePaOtherMailCount",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve pa mail count  Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaInfoForSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paInfo send mail information Exception. ", e);
		}
		return result;
	}
	public List retrievePaOtherSendMail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaOtherSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paInfo send mail information
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaInfoForSendMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaInfoForSendMail",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paInfo send mail information Exception. ", e);
		}
		return result;
	}
	public List retrievePaInfoForSendOtherMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaInfoForSendOtherMail",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paBonusInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaBonusInfoForSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paBonusInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paBonusInfo send mail information
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusInfoForSendMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaBonusInfoForSendMail",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paBonusInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paReplenishmentInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaReplenishmentInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaReplenishmentInfoForSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paBonusInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paReplenishmentInfo send mail information
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaReplenishmentInfoForSendMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrievePaReplenishmentInfoForSendMail",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paBonusInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * update pa email send ACTIVITY
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public void updatePaEmail(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.util.updatePaEmail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa email send ACTIVITY Exception. ", e);
		}
	}
	public void updatePaOtherEmail(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.util.updatePaOtherEmail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa OtherEmail send ACTIVITY Exception. ", e);
		}
	}
	
	/**
	 * retrieve insert DSHR data
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrieveInsertDsHrDatas(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrieveInsertDsHrDatas",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve insert DSHR data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve insert data DSHR columns
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrieveInsertDataColumns(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrieveInsertDataColumns",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve insert data DSHR columns Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve alert DSHR columns
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrieveAlterDsHrColumns(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrieveAlterDsHrColumns",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve alter DSHR columns Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve alert DSHR Comments
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrieveAlterDsHrComments(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrieveAlterDsHrComments",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve alter DSHR Comments Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  pa_util pa_item_list of month
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePa_Item_List_Month(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.RetrievePa_Item_List_Month",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_util pa_item_list of month Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  pa_util pa_bonus_item_list of month
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePa_bonus_Item_List_Month(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.RetrievePa_bonus_Item_List_Month",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_util pa_bonus_item_list of month Exception. ", e);
		}
		return result;
	}
		
	/**
	 *  pa_util pa_param_item_list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePa_param_item_list(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.RetrievePa_param_item_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_param_item_list pa_param_item_list Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  pa_util pa_bonus_param_item_list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePa_bonus_param_item_list(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.RetrievePa_bonus_param_item_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_bonus_param_item_list pa_param_item_list Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 *  pa_util Pa_distinct_list 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePa_distinct_list(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.RetrievePa_distinct_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("pa_util Pa_distinct_list Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  pa_util Pa_bonus_distinct_list 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePa_bonus_distinct_list(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.RetrievePa_bonus_distinct_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("pa_util retrievePa_bonus_distinct_list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * get PA_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int paCheckNormalCalc(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.paCheckNormalCalc",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get PA_HISTORY count Exception. ", e);
		}
		
		return result ;
	}
	
	
	/**
	 * get PA_REPLENISHMENT_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int paCheckSpecialCalc(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.paCheckSpecialCalc",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get PA_REPLENISHMENT_HISTORY count Exception. ", e);
		}
		
		return result ;
	}
	/**
	 * get arStatusFlag
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int arStatusFlag(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.arStatusFlag",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get arStatusFlag Exception. ", e);
		}
		
		return result ;
	}
	
	/**
	 * get PA_BONUS_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int paBonusCheckCalc(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.paBonusCheckCalc",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get PA_BONUS_HISTORY count Exception. ", e);
		}
		
		return result ;
	}
	
	/**
	 * retrieve Param Data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveParamDataList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result = new ArrayList() ;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.retrieveParamDataList",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Param Data Exception. ", e);
		}
		
		return result ;
	}
	
	
	/**
	 *  PayCalculationSignsForSearchNumber
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public int PayCalculationSignsForSearchNumber(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.PayCalculationSignsForSearchNumber",parameterObject).toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForSearchNumber Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  PayCalculationSignsForSearchNumber
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public int PayCalculationSignsForSearchNumberDici(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.PayCalculationSignsForSearchNumberDici",parameterObject).toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForSearchNumber Exception. ", e);
		}
		return result;
	}
	/**
	 *  Pay_Calculation_Signs
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List PayCalculationSignsForSearch(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.PayCalculationSignsForSearch",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForSearch Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  Pay_Calculation_Signs
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List PayCalculationSignsForSearchDici(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.PayCalculationSignsForSearchDici",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForSearchDici Exception. ", e);
		}
		return result;
	}
	/**
	 *  MESFlagForSearchNumber
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public int MESFlagForSearchNumber(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.MESFlagForSearchNumber",parameterObject).toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("MESFlagForSearchNumber Exception. ", e);
		}
		return result;
	}
	/**
	 *  Pay_Calculation_Signs
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List MESFlagForSearch(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.MESFlagForSearch",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("MESFlagForSearch Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  PayBankCodeForSearchNumberPa
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public int PayBankCodeForSearchNumberPa(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
//			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.PayBankCodeForSearchNumberPa",parameterObject).toString());
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.PayBankCodeForSearchNumberPa1",parameterObject).toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchNumberPa Exception. ", e);
		}
		return result;
	}
	/**
	 *  PayBankCodeForSearchPa
	 * 
	 * @param parameterObject ,currentPage, pageSize
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List PayBankCodeForSearchPa(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.PayBankCodeForSearchPa1",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchPa Exception. ", e);
		}
		return result;
	}
	/**
	 *  SearchCardno
	 * 
	 * @param parameterObject 
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List SearchCardno(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.SearchCardno",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("SearchCardno Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  PayBankCodeForSearchPa
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List PayBankCodeForSearchPa(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.PayBankCodeForSearchPa",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchPa Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  PayBankCodeForSearchNumberBonus
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public int PayBankCodeForSearchNumberBonus(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.util.PayBankCodeForSearchNumberBonus",parameterObject).toString());

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchNumberBonus Exception. ", e);
		}
		return result;
	}
	/**
	 *  PayBankCodeForSearchBonus
	 * 
	 * @param parameterObject, currentPage, pageSize
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List PayBankCodeForSearchBonus(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.PayBankCodeForSearchBonus",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchBonus Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  PayBankCodeForSearchBonus
	 * 
	 * @param parameterObject
	 * @return employee_List
	 * @throws GlRuntimeException
	 */
	public List PayBankCodeForSearchBonus(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pa.util.PayBankCodeForSearchBonus",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchBonus Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  PayCalculationSignsForUpdate
	 * 
	 * @param parameterObject
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void PayCalculationSignsForUpdate(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.PayCalculationSignsForUpdate",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForUpdate Exception. ", e);
		}
		
	}
	
	/**
	 *  PayCalculationSignsForUpdate
	 * 
	 * @param parameterObject
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void PayCalculationSignsForUpdateDici(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.PayCalculationSignsForUpdateDici",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForUpdate Exception. ", e);
		}
		
	}
	/**
	 *  SaveCardno
	 * 
	 * @param parameterObject
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void SaveCardno(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.SaveCardno",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("SaveCardno Exception. ", e);
		}
		
	}
	/**
	 *  MESFlagForUpdate
	 * 
	 * @param parameterObject
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void MESFlagForUpdate(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.MESFlagForUpdate",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("MESFlagForUpdate Exception. ", e);
		}
		
	}
	
	
	/**
	 *  PayBankCodeForUpdate
	 * 
	 * @param parameterObject
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void PayBankCodeForUpdate(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.PayBankCodeForUpdate",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForUpdate Exception. ", e);
		}
		
	}
	/**
	 *  PayProbationSave
	 * 
	 * @param parameterObject
	 * @return void
	 * @throws GlRuntimeException
	 */
	public void PayProbationSave(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.PayProbationSave",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayProbationSave Exception. ", e);
		}
		
	}
	/**
	 *  probationSearch
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List probationSearch(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result= commonSQLMapAdapter.executeQueryForMulti("pa.util.probationSearch",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("probationSearch Exception. ", e);
		}
		return result;
		
	}
   public void probationDelete(Object parameterObject) throws GlRuntimeException {

		
		try {
			 commonSQLMapAdapter.update("pa.util.probationDelete",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("probationDelete Exception. ", e);
		}
		
	}
   public void updateARHistoryData(Object parameterObject) throws GlRuntimeException {
	   SimpleMap parameterObject1= new SimpleMap();
		
		try {
			SimpleMap sm=null;
			List result= commonSQLMapAdapter.executeQueryForMulti("pa.util.arHistoryInfo",parameterObject);
			for(int i=0;i<result.size();i++){
				sm=(SimpleMap)result.get(i);
				if((NumberUtil.parseInt(sm.get("FORMAL_PERIOD"))+NumberUtil.parseInt(sm.get("INSUFFICIENT_ATTENDANCE")))!=NumberUtil.parseInt(sm.get("SCHEDULED_WORK_DAYS"))){
				parameterObject1.set("AR_MONTH",sm.get("AR_MONTH"));
				parameterObject1.set("EMPID",sm.get("EMPID"));
				commonSQLMapAdapter.startTransaction();
				 commonSQLMapAdapter.update("pa.util.formalPeriod",parameterObject1);
				 commonSQLMapAdapter.update("pa.util.probationPeriod",parameterObject1);
				 commonSQLMapAdapter.commitTransation();
				}
				
			}		

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateARHistoryData Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		
	}
   
}

