
package com.ait.kpa.dao.bonus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;


public class BonusDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(BonusDAO.class);

	public BonusDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	/**
	 *  Retrieve retrievePaBonus_paramList
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonus_paramList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.RetrievePaBonus_paramList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonus_paramList Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiff_paramList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.diff.retrieveDiff_paramList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiff_paramList Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  Retrieve retrievePaBonus_param
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrievePaBonus_param(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("kpa.bonus.RetrievePaBonus_paramList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonus_paramList Exception. ", e);
		}
		return result;
	}
	
	public Object retrieveDiff_param(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("kpa.diff.retrieveDiff_paramList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiff_param Exception. ", e);
		}
		return result;
	}
	
	/**
	 * validate pa bonus param itemid  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validatePaBonusParamItemId(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.bonus.ValidatePaBonusParamItemId",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate pa bonus param itemid Exception. ", e);
		}
		return result;
	}
	
	public int validateDiffParamItemId(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.validateDiffParamItemId",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate kpa diff param itemid Exception. ", e);
		}
		return result;
	}
	
	/**
	 * insert pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void insertPaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("kpa.bonus.insertPaBonusParam",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus param Exception. ", e);
		}
	}
	
	public void insertDiffParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("kpa.diff.insertDiffParam",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert kpa diff param Exception. ", e);
		}
	}
	
	/**
	 * update pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("kpa.bonus.updatePaBonusParam",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus param Exception. ", e);
		}
	}
	
	public void updateDiffParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("kpa.diff.updateDiffParam",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff param Exception. ", e);
		}
	}
	
	public void updateDiffDefaultParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam11",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam12",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam13",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam14",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam21",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam22",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam23",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam24",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam31",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam32",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam33",parameterObject) ;
			commonSQLMapAdapter.update("kpa.diff.updateDiffDefaultParam34",parameterObject) ;
			commonSQLMapAdapter.commitTransation() ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff param Exception. ", e);
		}
	}
	
	public void updateDiffSalaryS(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffSalaryS",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffSalaryS",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffSalaryS1(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffSalaryS1",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffSalaryS1",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffTaxS(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffTaxS",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffTaxS",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffTaxS1(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffTaxS1",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffTaxS1",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffOverseaS(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffOverseaS",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffOverseaS",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffOverseaS1(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffOverseaS1",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffOverseaS1",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public int findHistoryS(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.findHistoryS",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate findHistoryE Exception. ", e);
		}
		return result;
	}
	
	public int findHistoryS_tax(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.findHistoryS_tax",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate findHistoryE Exception. ", e);
		}
		return result;
	}
	
	public int findHistoryS_oversea(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.findHistoryS_oversea",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate findHistoryE Exception. ", e);
		}
		return result;
	}
	
	
	public int findHistoryE(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.findHistoryE",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate findHistoryE Exception. ", e);
		}
		return result;
	}
	
	public int findHistoryE_tax(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.findHistoryE_tax",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate findHistoryE Exception. ", e);
		}
		return result;
	}
	
	public int findHistoryE_oversea(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.findHistoryE_oversea",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate findHistoryE Exception. ", e);
		}
		return result;
	}
	
	public void updateDiffSalaryE(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffSalaryE",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffSalaryE",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffSalaryE1(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffSalaryE1",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffSalaryE1",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffTaxE(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffTaxE",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffTaxE",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffTaxE1(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffTaxE1",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffTaxE1",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffOverseaE(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffOverseaE",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffOverseaE",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	public void updateDiffOverseaE1(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffOverseaE1",parameterObject) ;

			commonSQLMapAdapter.update("kpa.diff.updateDiffOverseaE1",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;


		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff salary Exception. ", e);
		}
	}
	
	/**
	 * delete pa bonus param  
	 * 
	 * @throws GlRuntimeException
	 */
	public void deletePaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.bonus.deletePaBonusParam",parameterObject) ;
			
			commonSQLMapAdapter.delete("kpa.bonus.deletePaBonusParamDate",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		
		} catch (Exception e) {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception ee) {}
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus param Exception. ", e);
		}
	}
	
	public void deleteDiffParam(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffParam",parameterObject) ;
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffParamDate",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		
		} catch (Exception e) {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception ee) {}
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa diff param Exception. ", e);
		}
	}
	
	/**
	 *  Retrieve retrievePaBonus_paramList
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusParamDataList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.RetrievePaBonusParamDataList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusParamDataList Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiffParamDataList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.diff.retrieveDiffParamDataList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiffParamDataList Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa bonus param data for insert list  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusParamDataForInsert(SimpleMap parameterObject) throws GlRuntimeException {

		List result ;
		try {
			if (parameterObject.getString("actionType") == null || !parameterObject.getString("actionType").equals("search"))
				commonSQLMapAdapter.insert("kpa.bonus.callPrBonusParamList",parameterObject);
			
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.RetrievePaBonusParamDataForInsertList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve RetrievePaBonusParamDataForInsertList Exception. ", e);
		}
		return result;
	}
	
	/**
	 * insert pa bonus param data   
	 * 
	 * @param list
	 * @throws GlRuntimeException
	 */
	public void insertPaBonusParamData(List parameterList) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.insertForMulti("kpa.bonus.insertPrBonusParamData",parameterList);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus param data Exception. ", e);
		}
	}
	
	/**
	 * update pa bonus param data   
	 * 
	 * @param list
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusParamData(List parameterList) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.updateForMulti("kpa.bonus.updatePrBonusParamData",parameterList);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus param data Exception. ", e);
		}
	}
	
	/**
	 * delete pa bonus param data   
	 * 
	 * @param list
	 * @throws GlRuntimeException
	 */
	public void deletePaBonusParamData(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.delete("kpa.bonus.deletePrBonusParamData",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus param data Exception. ", e);
		}
	}
	
	/**
	 *  Retrieve retrievePaBonusItemList
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusItemList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.RetrievePaBonusItemList",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItemList Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiffItemList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.diff.RetrieveDiffItemList",parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItemList Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  Retrieve retrievePaBonusItemList count
 	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrievePaBonusItemListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.bonus.RetrievePaBonusItemListCnt",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItemList Exception. ", e);
		}
		return result;
	}
	
	public int retrieveDiffItemListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.RetrieveDiffItemListCnt",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiffItemListCnt Exception. ", e);
		}
		return result;
	}
	
	/**
	 * validate pa bonus itemid  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validatePaBonusItemId(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.bonus.ValidatePaBonusItemId",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate pa bonus itemid Exception. ", e);
		}
		return result;
	}
	
	public int validateDiffItemId(SimpleMap parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.diff.ValidateDiffItemId",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate DiffItemId itemid Exception. ", e);
		}
		return result;
	}
	
	/**
	 * insert pa bonus item  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void insertPaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("kpa.bonus.insertPaBonusItem",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus item Exception. ", e);
		}
	}
	
	public void insertDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("kpa.diff.insertDiffItem",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertDiffItem item Exception. ", e);
		}
	}
	
	/**
	 *  Retrieve retrievePaBonusItem
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrievePaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("kpa.bonus.RetrievePaBonusItemList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItem Exception. ", e);
		}
		return result;
	}
	
	public Object retrieveDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("kpa.diff.RetrieveDiffItemList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiffItem Exception. ", e);
		}
		return result;
	}
	
	/**
	 * update pa bonus param  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("kpa.bonus.updatePaBonusItem",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus item Exception. ", e);
		}
	}
	
	public void updateDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("kpa.diff.updateDiffItem",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus item Exception. ", e);
		}
	}
	
	/**
	 * delete pa bonus item  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deletePaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.bonus.deletePaBonusFormular",parameterObject) ;
			
			commonSQLMapAdapter.delete("kpa.bonus.deletePaBonusItem",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		
		} catch (Exception e) {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception ee) {}
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus item Exception. ", e);
		}
	}
	
	public void deleteDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffFormular",parameterObject) ;
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffItem",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		
		} catch (Exception e) {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception ee) {}
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus item Exception. ", e);
		}
	}
	
	/**
	 *  Retrieve retrievePaBonusOfFormularItemList
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusItemOfFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.RetrievePaBonusItemList");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItemOfFormularList Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiffItemOfFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.diff.RetrieveDiffItemList");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiffItemOfFormularList Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  Retrieve retrievePaBonusFormularList
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.RetrievePaBonusFormularList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusFormularList Exception. ", e);
		}
		return result;
	}
	
	public List retrieveDiffFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.diff.RetrieveDiffFormularList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiffFormularList Exception. ", e);
		}
		return result;
	}
	/**
	 * insert pa bonus formular  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void insertPaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("kpa.bonus.insertPaBonusFormular",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus formular Exception. ", e);
		}
	}
	
	public void insertDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("kpa.diff.insertDiffFormular",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa diff formular Exception. ", e);
		}
	}
	
	/**
	 *  Retrieve retrievePaBonusFormular
	 *  
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrievePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.bonus.RetrievePaBonusFormularList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusFormular Exception. ", e);
		}
		return result;
	}
	
	public Object retrieveDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.diff.RetrieveDiffFormularList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveDiffFormular Exception. ", e);
		}
		return result;
	}
	
	/**
	 * update pa bonus formular
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("kpa.bonus.updatePaBonusFormular",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus formular Exception. ", e);
		}
	}
	
	public void updateDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("kpa.diff.updateDiffFormular",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff formular Exception. ", e);
		}
	}
	
	/**
	 * delete pa bonus formular 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deletePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.delete("kpa.bonus.deletePaBonusFormular",parameterObject) ;	
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus formular Exception. ", e);
		}
	}
	
	public void deleteDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.delete("kpa.diff.deleteDiffFormular",parameterObject) ;	
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa diff formular Exception. ", e);
		}
	}
	
	/**
	 * retrieve pa bonus item caluOrder  
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrievePaBonusItemCalcuOrder(SimpleMap parameterObject) throws GlRuntimeException {
		
		Object result;
		try {
			
			result = commonSQLMapAdapter.executeQuery("kpa.bonus.RetrievePaBonusItemCaluOrder",parameterObject) ;	
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus item caluOrder Exception. ", e);
		}
		return result ;
	}
	
	public Object retrieveDiffItemCalcuOrder(SimpleMap parameterObject) throws GlRuntimeException {
		
		Object result;
		try {
			
			result = commonSQLMapAdapter.executeQuery("kpa.diff.RetrieveDiffItemCaluOrder",parameterObject) ;	
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve pa diff item caluOrder Exception. ", e);
		}
		return result ;
	}
	
	/**
	 * update pa bonus item caluOrder up 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusItemCalcuOrderUp(SimpleMap parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.update("kpa.bonus.UpdatePaBonusItemCaluOrderUpOne",parameterObject) ;
			
			commonSQLMapAdapter.update("kpa.bonus.UpdatePaBonusItemCaluOrderUpTwo",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			try
			{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception ee){}
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus item caluOrder up Exception. ", e);
		}
	}
	
public void updateDiffItemCalcuOrderUp(SimpleMap parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.update("kpa.diff.UpdateDiffItemCaluOrderUpOne",parameterObject) ;
			
			commonSQLMapAdapter.update("kpa.diff.UpdateDiffItemCaluOrderUpTwo",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			try
			{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception ee){}
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff item caluOrder up Exception. ", e);
		}
	}
	
	/**
	 * update pa bonus item caluOrder down 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusItemCalcuOrderDown(SimpleMap parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.update("kpa.bonus.UpdatePaBonusItemCaluOrderDownOne",parameterObject) ;
			
			commonSQLMapAdapter.update("kpa.bonus.UpdatePaBonusItemCaluOrderDownTwo",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			try
			{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception ee){}
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus item caluOrder down Exception. ", e);
		}
	}
	
	public void updateDiffItemCalcuOrderDown(SimpleMap parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.update("kpa.diff.UpdateDiffItemCaluOrderDownOne",parameterObject) ;
			
			commonSQLMapAdapter.update("kpa.diff.UpdateDiffItemCaluOrderDownTwo",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		} catch (Exception e) {
			try
			{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception ee){}
			logger.error(e.toString());
			throw new GlRuntimeException("update pa diff item caluOrder down Exception. ", e);
		}
	}
	
	/**
	 * call PA_BONUS_CONFIRM   
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void callPaBonusConfirm(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.executeQuery("kpa.bonus.callPaBonusConfirm",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("call PA_BONUS_CONFIRM Exception. ", e);
		}
	}
	
	
	/**
	 * retrieve export pa bonus Param Data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveExportPaBonusParamDataList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.bonus.retrieveExportPaBonusParamDataList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveExportPaBonusParamDataList Exception. ", e);
		}
		return result;
	}
}

