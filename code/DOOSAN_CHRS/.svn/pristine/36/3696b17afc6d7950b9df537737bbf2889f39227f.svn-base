/*
 * @(#)PaUtilDAO.java 1.0 2007-11-10 下午08:30:28
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.dao.util;

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
	 * retrieve paInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrievePaInfoForSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paInfo send mail information Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve paDiffInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List retrievePaDiffInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrievePaDiffInfoForSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paDiffInfo send mail information Exception. ", e);
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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrievePaBonusInfoForSendMail",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve paBonusInfo send mail information Exception. ", e);
		}
		return result;
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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrieveInsertDsHrDatas",parameterObject);

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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrieveInsertDataColumns",parameterObject);

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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrieveAlterDsHrColumns",parameterObject);

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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrieveAlterDsHrComments",parameterObject);

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
	public List retrievePa_Item_List_Month() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrievePa_Item_List_Month");

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
	public List retrievePa_bonus_Item_List_Month() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrievePa_bonus_Item_List_Month");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_util pa_bonus_item_list of month Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiff_Item_List_Month() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrieveDiff_Item_List_Month");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_util retrieveDiff_Item_List_Month of month Exception. ", e);
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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrievePa_param_item_list",parameterObject);

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

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrievePa_bonus_param_item_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa_bonus_param_item_list pa_param_item_list Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiff_param_item_list(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrieveDiff_param_item_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiff_param_item_list  Exception. ", e);
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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrievePa_distinct_list",parameterObject);

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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.RetrievePa_bonus_distinct_list",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("pa_util retrievePa_bonus_distinct_list Exception. ", e);
		}
		return result;
	}	
	
	/**
	 * get PA_REPLENISHMENT_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int paCheckCalc(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.util.paCheckCalc",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get PA_REPLENISHMENT_HISTORY count Exception. ", e);
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
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.util.paBonusCheckCalc",parameterObject)) ;

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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.retrieveParamDataList",parameterObject) ;

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
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.util.PayCalculationSignsForSearchNumber",parameterObject).toString());

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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.PayCalculationSignsForSearch",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForSearch Exception. ", e);
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
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.util.PayBankCodeForSearchNumberPa",parameterObject).toString());

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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.PayBankCodeForSearchPa",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForSearchPa Exception. ", e);
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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.PayBankCodeForSearchPa",parameterObject);

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
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.util.PayBankCodeForSearchNumberBonus",parameterObject).toString());

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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.PayBankCodeForSearchBonus",parameterObject,currentPage,pageSize);

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
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.util.PayBankCodeForSearchBonus",parameterObject);

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
			 commonSQLMapAdapter.update("kpa.util.PayCalculationSignsForUpdate",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayCalculationSignsForUpdate Exception. ", e);
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
			 commonSQLMapAdapter.update("kpa.util.PayBankCodeForUpdate",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("PayBankCodeForUpdate Exception. ", e);
		}
		
	}
}

