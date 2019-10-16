/*
 * @(#)PresentDAO.java 1.0 2009-7-13 下午01:47:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.dao.present;

import java.util.Iterator;
import java.util.List;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-13 下午01:47:53
 * @version 1.0
 * 
 */
public class PresentDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PresentDAO.class);

	public PresentDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * Retrieve present list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present list error." + e);
			throw new GlRuntimeException("Retrieve present list error.", e);
		}

		return result;
	}
	/**
	 * Retrieve present list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List ytglPresentList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.ytglPresentList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present list error." + e);
			throw new GlRuntimeException("Retrieve present list error.", e);
		}

		return result;
	}


	/**
	 * Retrieve present list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveUpCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.check.retrieveUpCheckAccount", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present list error." + e);
			throw new GlRuntimeException("Retrieve present list error.", e);
		}

		return result;
	}
	/**
	 * Retrieve retrieveCheckAccountColumn list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveCheckAccountColumn(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.check.retrieveCheckAccountColumn", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve retrieveCheckAccountColumn list error." + e);
			throw new GlRuntimeException("Retrieve retrieveCheckAccountColumn list error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrievePresentList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve present list by paging error." + e);
			throw new GlRuntimeException("Retrieve present list by paging error.", e);
		}

		return result;
	}
	
	/**
	 * Retrieve present list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List ytglPresentList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.ytglPresentList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve present list by paging error." + e);
			throw new GlRuntimeException("Retrieve present list by paging error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrievePresentListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.present.retrievePresentListCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present list count error." + e);
			throw new GlRuntimeException("Retrieve present list count error.", e);
		}

		return result;
	}
	
	/**
	 * Retrieve present list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object ytglPresentListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.present.ytglPresentListCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present list count error." + e);
			throw new GlRuntimeException("Retrieve present list count error.", e);
		}

		return result;
	}
	/**
	 * Retrieve BankAccount list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveBankAccountList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.check.retrieveBankAccountList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve BankAccount list by paging error." + e);
			throw new GlRuntimeException("Retrieve BankAccount list by paging error.", e);
		}

		return result;
	}

	/**
	 * Retrieve BankAccount list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveCheckAccountList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.check.retrieveCheckAccountList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve BankAccount list by paging error." + e);
			throw new GlRuntimeException("Retrieve BankAccount list by paging error.", e);
		}

		return result;
	}

	/**
	 * Retrieve BankAccount list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveBankAccountListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.check.retrieveBankAccountListCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve BankAccount list count error." + e);
			throw new GlRuntimeException("Retrieve BankAccount list count error.", e);
		}

		return result;
	}

	/**
	 * Retrieve BankAccount list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveCheckAccountListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.check.retrieveCheckAccountListCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve BankAccount list count error." + e);
			throw new GlRuntimeException("Retrieve BankAccount list count error.", e);
		}

		return result;
	}

	/**
	 * Insert present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresent(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresent", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present error." + e);
			throw new GlRuntimeException("Insert present error.", e);
		}
	}
	public void insertPresent1(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresent1", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present error." + e);
			throw new GlRuntimeException("Insert present error.", e);
		}
	}
	public void insertCheckBank(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.check.insertCheckBank", parameterObject);
		} catch (Exception e) {

			logger.error("Insert CheckBank error." + e);
			throw new GlRuntimeException("Insert CheckBank error.", e);
		}
	}
	public void insertCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.check.insertCheckAccount", parameterObject);
		} catch (Exception e) {

			logger.error("Insert CheckBank error." + e);
			throw new GlRuntimeException("Insert CheckAccount error.", e);
		}
	}
	public void updateSaveCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.check.updateSaveCheckAccount", parameterObject);
		} catch (Exception e) {

			logger.error("Insert CheckBank error." + e);
			throw new GlRuntimeException("Insert CheckAccount error.", e);
		}
	}
	public void insertPresent2(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresent2", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present error." + e);
			throw new GlRuntimeException("Insert present error.", e);
		}
	}
	public void insertPresent3(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresent3", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present error." + e);
			throw new GlRuntimeException("Insert present error.", e);
		}
	}

	/**
	 * Update present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresent(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.present.updatePresent", parameterObject);
		} catch (Exception e) {

			logger.error("Update present error." + e);
			throw new GlRuntimeException("Update present error.", e);
		}
	}
	
	/**
	 * Update present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentYtgl(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.present.updatePresentYtgl", parameterObject);
		} catch (Exception e) {

			logger.error("Update present error." + e);
			throw new GlRuntimeException("Update present error.", e);
		}
	}

	/**
	 * Update CheckBank
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateCheckBank(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.check.updateCheckBank", parameterObject);
		} catch (Exception e) {

			logger.error("Update CheckBank error." + e);
			throw new GlRuntimeException("Update CheckBank error.", e);
		}
	}

	/**
	 * Update CheckBank
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.check.deleteCheckAccount", parameterObject);
		} catch (Exception e) {

			logger.error("Update CheckBank error." + e);
			throw new GlRuntimeException("Update CheckBank error.", e);
		}
	}

	/**
	 * udpate present multi
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresent(List parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("ga.present.updatePresent", parameterObject);
		} catch (Exception e) {

			logger.error("Update present by multi error." + e);
			throw new GlRuntimeException("Update present by multi error.", e);
		}
	}

	/**
	 * Delete present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deletePresent(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.deletePresent", parameterObject);
		} catch (Exception e) {

			logger.error("Delete present error." + e);
			throw new GlRuntimeException("Delete present error.", e);
		}
	}

	/**
	 * Retrieve present apply list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentApplyList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentApplyList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present apply list error." + e);
			throw new GlRuntimeException("Retrieve present apply list error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present apply list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrievePresentApplyList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentApplyList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {

			logger.error("Retrieve present apply list by paging error." + e);
			throw new GlRuntimeException("Retrieve present apply list by paging error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present apply count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrievePresentApplyCnt(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.present.retrievePresentApplyCnt", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present apply count error." + e);
			throw new GlRuntimeException("Retrieve present apply count error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present affirm list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentAffirmList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentAffirmList", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present affirm list error." + e);
			throw new GlRuntimeException("Retrieve present affirm list error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present apply detail
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentApplyDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present apply detail error." + e);
			throw new GlRuntimeException("Retrieve present apply detail error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present information
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentInformation(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentInformation", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present information error." + e);
			throw new GlRuntimeException("Retrieve present information error.", e);
		}

		return result;
	}

	/**
	 * Retrieve present apply quentity
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrievePresentApplyQuentity(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.retrievePresentApplyQuentity", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve present apply quentity error." + e);
			throw new GlRuntimeException("Retrieve present apply quentity error.", e);
		}

		return result;
	}
	
	/**
	 * Insert present apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresentApply(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresentApply", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present apply error." + e);
			throw new GlRuntimeException("Insert present apply error.", e);
		}
	}
	
	/**
	 * Insert present apply detial
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresentApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresentApplyDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present apply detial error." + e);
			throw new GlRuntimeException("Insert present apply detail error.", e);
		}
	}
	
	/**
	 * Insert present apply detail multi
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentApplyDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.present.insertPresentApplyDetail", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present apply detial by multi error." + e);
			throw new GlRuntimeException("Insert present apply detail multi error.", e);
		}
	}
	
	public void insertPresentApplyDetail1(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.present.insertPresentApplyDetail1", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present apply detial by multi error." + e);
			throw new GlRuntimeException("Insert present apply detail multi error.", e);
		}
	}
	
	public void insertPresentApplyDetail2(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.present.insertPresentApplyDetail2", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present apply detial by multi error." + e);
			throw new GlRuntimeException("Insert present apply detail multi error.", e);
		}
	}
	
	public void insertPresentApplyDetail3(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.present.insertPresentApplyDetail3", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present apply detial by multi error." + e);
			throw new GlRuntimeException("Insert present apply detail multi error.", e);
		}
	}

	/**
	 * Insert present affirm
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresentAffirm(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresentAffirm", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present affirm error." + e);
			throw new GlRuntimeException("Insert present affirm error.", e);
		}
	}

	/**
	 * Insert present affirm by list
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentAffirm(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.present.insertPresentAffirm", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present affirm by list error." + e);
			throw new GlRuntimeException("Insert present affirm by list error.", e);
		}
	}

	/**
	 * Get current apply No
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object getCurrApplyNo() throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.present.getCurrApplyNo");
		} catch (Exception e) {

			logger.error("Get current apply No error." + e);
			throw new GlRuntimeException("Get current apply No error.", e);
		}

		return result;
	}
	
	/**
	 * apply present
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object applyPresent(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// insert apply data
			this.insertPresentApply(parameterObject);
			
			// insert apply detail data
			List detailList = (List)parameterObject.get("detail");
			Iterator keys = parameterObject.keySet().iterator();
			while(keys.hasNext()){
				String key = (String)keys.next();
				System.out.println("key=============="+key);
				if("PRESENT_ID1_0".equals(key)){	
					this.insertPresentApplyDetail1(detailList, false);	
					}else if("PRESENT_ID2_0".equals(key)){
						this.insertPresentApplyDetail2(detailList, false);
					}else if("PRESENT_ID3_0".equals(key)){
						this.insertPresentApplyDetail3(detailList, false);
					}
				}
			//this.insertPresentApplyDetail(detailList, false);
			
			// insert affirm data
			List affirmList = (List)parameterObject.get("affirm");
			this.insertPresentAffirm(affirmList, false);
			
			// get apply No
			SimpleMap result = (SimpleMap)this.getCurrApplyNo();

			//推送
			DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_PRESENT, result.getString("APPLY_NO"));

			commonSQLMapAdapter.commitTransation();
			
			return result;
			
		} catch (Exception e) {

			logger.error("Apply present error." + e);
			throw new GlRuntimeException("Apply present error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * update present affirm
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentAffirm(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.present.updatePresentAffirm", parameterObject);
		} catch (Exception e) {

			logger.error("Update present affirm error." + e);
			throw new GlRuntimeException("Update present affirm error.", e);
		}
	}
	
	/**
	 * Update present affirm By batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentAffirm(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("ga.present.updatePresentAffirm", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Update present affirm by batch error." + e);
			throw new GlRuntimeException("Update present affirm by batch error.", e);
		}
	}
	
	/**
	 * update present apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentApply(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.present.updatePresentApply", parameterObject);
		} catch (Exception e) {

			logger.error("Update present apply error." + e);
			throw new GlRuntimeException("Update present apply error.", e);
		}
	}

	/**
	 * Update present apply by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentApply(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("ga.present.updatePresentApply", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Update present apply by batch error." + e);
			throw new GlRuntimeException("Update present apply by batch error.", e);
		}
	}
	
	/**
	 * Update present detail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentDetail(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.present.updatePresentDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Update present detail error." + e);
			throw new GlRuntimeException("Update present detail error.", e);
		}
	}
	
	/**
	 * Update present detail by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updatePresentDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.updateForMulti("ga.present.updatePresentDetail", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Update present detail by batch error." + e);
			throw new GlRuntimeException("Update present detail batch error.", e);
		}
	}
	
	/**
	 * Insert present by apply
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentByApply(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresentByApply", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present by apply error." + e);
			throw new GlRuntimeException("Insert present by apply error.", e);
		}
	}
	
	/**
	 * Insert present by apply list
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentByApply(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.present.insertPresentByApply", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present by apply list error." + e);
			throw new GlRuntimeException("Insert present by apply list error.", e);
		}
	}
	
	/**
	 * Insert present by apply 
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentByApply5(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.present.insertPresentByApply", parameterObject);
		} catch (Exception e) {

			logger.error("Insert present by apply list error." + e);
			throw new GlRuntimeException("Insert present by apply list error.", e);
		}
	}
	
	/**
	 * affirm present by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void affirmPresent(List parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// update affirm data
			this.updatePresentAffirm(parameterObject, false);
			
			// update apply data
			this.updatePresentApply(parameterObject, false);
			
			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {

			logger.error("Affirm present by batch error." + e);
			throw new GlRuntimeException("Affirm present error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * affirm present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void affirmPresent(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// update affirm data
			this.updatePresentAffirm(parameterObject);
			
			// update apply data
			this.updatePresentApply(parameterObject);
			
			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {

			logger.error("Affirm present error." + e);
			throw new GlRuntimeException("Affirm present error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * Confirm present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void confirmPresent(SimpleMap parameterObject, boolean opFlag) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// update apply data
			this.updatePresentApply(parameterObject);
			
			// approval operation
			if (opFlag == true) {
				
				// update apply detail data
				List detailList = (List)parameterObject.get("detail");
				this.updatePresentDetail(detailList, false);
				
				// insert present data by apply
				this.insertPresentByApply(detailList, false);
			}
			
			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {

			logger.error("Confirm present error." + e);
			throw new GlRuntimeException("Confirm present error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * Confirm present by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void confirmPresent(List parameterObject, boolean opFlag) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction();
			
			// update apply data
			this.updatePresentApply((List)parameterObject.get(0), false);
			
			// approval operation
			if (opFlag == true) {
				
				// update apply detail data
				this.updatePresentDetail((List)parameterObject.get(1), false);
				
				// insert present data by apply
				//this.insertPresentByApply((List)parameterObject.get(1), false);//批量插入报错，改如下：
				List list5 = (List)parameterObject.get(1);
				SimpleMap map5;
		        for(int i=0; i<list5.size(); i++){
		          map5 = (SimpleMap)list5.get(i);
		          this.insertPresentByApply5(map5);
				}
			}
			
			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {

			logger.error("Confirm present by batch error." + e);
			throw new GlRuntimeException("Confirm present error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * get affirm mail
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getAffirmMail(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.getAffirmMail", parameterObject);
		} catch (Exception e) {

			logger.error("Retrieve affirm mail error." + e);
			throw new GlRuntimeException("Retrieve affirm mail error.", e);
		}

		return result;
	}
	
	/**
	 * Get present quentity statistics
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getPresentQuentStatistics(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.getPresentQuentStatistics", parameterObject);
		} catch (Exception e) {

			logger.error("Get present quentity statistics error." + e);
			throw new GlRuntimeException("Get present quentity statistics error.", e);
		}

		return result;
	}
	
	/**
	 * Get present send statistics
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getPresentSendStatistics(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.getPresentSendStatistics", parameterObject);
		} catch (Exception e) {

			logger.error("Get present send statistics error." + e);
			throw new GlRuntimeException("Get present send statistics error.", e);
		}

		return result;
	}
	
	/**
	 * Get present send detail
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getPresentSendDetail(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.getPresentSendDetail", parameterObject);
		} catch (Exception e) {

			logger.error("Get present send detail error." + e);
			throw new GlRuntimeException("Get present send detail error.", e);
		}

		return result;
	}

	public List getPresentName(Object parameterObject) {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.present.getPresentName", parameterObject);
		} catch (Exception e) {
			logger.error("Get present send detail error." + e);
			throw new GlRuntimeException("Get present name error.", e);
		}
		return result;
	}
}
