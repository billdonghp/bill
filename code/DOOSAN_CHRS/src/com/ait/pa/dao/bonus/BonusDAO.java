
package com.ait.pa.dao.bonus;

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

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.RetrievePaBonus_paramList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonus_paramList Exception. ", e);
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
			result = commonSQLMapAdapter.executeQuery("pa.bonus.RetrievePaBonus_paramList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonus_paramList Exception. ", e);
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

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.bonus.ValidatePaBonusParamItemId",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate pa bonus param itemid Exception. ", e);
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

			commonSQLMapAdapter.insert("pa.bonus.insertPaBonusParam",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus param Exception. ", e);
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

			commonSQLMapAdapter.update("pa.bonus.updatePaBonusParam",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus param Exception. ", e);
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
			
			commonSQLMapAdapter.delete("pa.bonus.deletePaBonusParam",parameterObject) ;
			
			commonSQLMapAdapter.delete("pa.bonus.deletePaBonusParamDate",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;
		
		} catch (Exception e) {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception ee) {}
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus param Exception. ", e);
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

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.RetrievePaBonusParamDataList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusParamDataList Exception. ", e);
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
			if (parameterObject.getString("actionType") != null && !parameterObject.getString("actionType").equals("search"))
				commonSQLMapAdapter.insert("pa.bonus.callPrBonusParamList",parameterObject);
			
			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.RetrievePaBonusParamDataForInsertList",parameterObject);

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
			commonSQLMapAdapter.insertForMulti("pa.bonus.insertPrBonusParamData",parameterList);

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
			commonSQLMapAdapter.updateForMulti("pa.bonus.updatePrBonusParamData",parameterList);

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
			commonSQLMapAdapter.delete("pa.bonus.deletePrBonusParamData",parameterObject);

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

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.RetrievePaBonusItemList",parameterObject,currentPage,pageSize);

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

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.bonus.RetrievePaBonusItemListCnt",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItemList Exception. ", e);
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

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.bonus.ValidatePaBonusItemId",parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate pa bonus itemid Exception. ", e);
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

			commonSQLMapAdapter.insert("pa.bonus.insertPaBonusItem",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus item Exception. ", e);
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
			result = commonSQLMapAdapter.executeQuery("pa.bonus.RetrievePaBonusItemList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItem Exception. ", e);
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

			commonSQLMapAdapter.update("pa.bonus.updatePaBonusItem",parameterObject) ;

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
			
			commonSQLMapAdapter.delete("pa.bonus.deletePaBonusFormular",parameterObject) ;
			
			commonSQLMapAdapter.delete("pa.bonus.deletePaBonusItem",parameterObject) ;
			
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

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.RetrievePaBonusItemList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusItemOfFormularList Exception. ", e);
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

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.RetrievePaBonusFormularList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusFormularList Exception. ", e);
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

			commonSQLMapAdapter.insert("pa.bonus.insertPaBonusFormular",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert pa bonus formular Exception. ", e);
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

			result = commonSQLMapAdapter.executeQuery("pa.bonus.RetrievePaBonusFormularList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrievePaBonusFormular Exception. ", e);
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

			commonSQLMapAdapter.update("pa.bonus.updatePaBonusFormular",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update pa bonus formular Exception. ", e);
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
			
			commonSQLMapAdapter.delete("pa.bonus.deletePaBonusFormular",parameterObject) ;	
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete pa bonus formular Exception. ", e);
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
			
			result = commonSQLMapAdapter.executeQuery("pa.bonus.RetrievePaBonusItemCaluOrder",parameterObject) ;	
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus item caluOrder Exception. ", e);
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
			
			List updateList = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.UpdatePaBonusItemCaluOrderUpData",parameterObject) ;
			
			if (updateList.size() > 0)
			{
				
				parameterObject = (SimpleMap)updateList.get(0) ;
				
				parameterObject.setInt("calcu_order", parameterObject.getInt("CALCU_ORDER_OLD")) ;
				parameterObject.setInt("item_no", parameterObject.getInt("ITEM_NO")) ;
				commonSQLMapAdapter.update("pa.bonus.UpdatePaBonusItemCaluOrder",parameterObject) ;
				
				parameterObject.setInt("calcu_order", parameterObject.getInt("CALCU_ORDER")) ;
				parameterObject.setInt("item_no", parameterObject.getInt("ITEM_NO_OLD")) ;
				commonSQLMapAdapter.update("pa.bonus.UpdatePaBonusItemCaluOrder",parameterObject) ;
			}
			
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
	
	/**
	 * update pa bonus item caluOrder down 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusItemCalcuOrderDown(SimpleMap parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.startTransaction() ;
			
			List updateList = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.UpdatePaBonusItemCaluOrderDownData",parameterObject) ;
			
			if (updateList.size() > 0)
			{
				parameterObject = (SimpleMap)updateList.get(0) ;
				
				parameterObject.setInt("calcu_order", parameterObject.getInt("CALCU_ORDER_OLD")) ;
				parameterObject.setInt("item_no", parameterObject.getInt("ITEM_NO")) ;
				commonSQLMapAdapter.update("pa.bonus.UpdatePaBonusItemCaluOrder",parameterObject) ;
				
				parameterObject.setInt("calcu_order", parameterObject.getInt("CALCU_ORDER")) ;
				parameterObject.setInt("item_no", parameterObject.getInt("ITEM_NO_OLD")) ;
				commonSQLMapAdapter.update("pa.bonus.UpdatePaBonusItemCaluOrder",parameterObject) ;
			}
			
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
	
	/**
	 * call PA_BONUS_CONFIRM   
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void callPaBonusConfirm(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.executeQuery("pa.bonus.callPaBonusConfirm",parameterObject);

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

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.retrieveExportPaBonusParamDataList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveExportPaBonusParamDataList Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa bonus param item list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamList(SimpleMap parameterObject) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.retrievePaBonusParamList",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa bonus Param List Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa bonus param item list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.retrievePaBonusParamList",parameterObject,currentPage,pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa bonus Param List Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa bonus param item list count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaBonusParamListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.bonus.retrievePaBonusParamListCnt",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa bonus Param List count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa bonus param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamConfigureList(SimpleMap parameterObject) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.retrievePaBonusParamConfigureList",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa bonus Param configure item Configure Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa bonus param item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamConfigureList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List<SimpleMap> result = new ArrayList<SimpleMap>() ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.bonus.retrievePaBonusParamConfigureList",parameterObject,currentPage,pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa bonus Param configure item Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve pa bonus param item list count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaBonusParamConfigureListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.bonus.retrievePaBonusParamConfigureListCnt",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa bonus Param configure List count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * delete pa bonus param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void deletePaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		try {
			
			commonSQLMapAdapter.startTransaction() ;
			
			commonSQLMapAdapter.delete("pa.param.deletePaBonusParamData",parameterObject) ;
			
			commonSQLMapAdapter.delete("pa.bonus.deletePaBonusParamConfigure",parameterObject) ;
			
			commonSQLMapAdapter.commitTransation() ;

		} catch (Exception e) {
			try{
				commonSQLMapAdapter.endTransation() ;
			}catch(Exception ee){	}
			logger.error(e.toString());
			throw new GlRuntimeException("delete Pa bonus Param configure item Configure Exception. ", e);
		}
	}
	
	/**
	 * validate update pa bonus param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateUpdatePaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.bonus.validateUpdatePaBonusParamConfigure",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" validate update Pa bonus Param configure item Configure Exception. ", e);
		}
		
		return result ;
	}
	
	/**
	 * update pa bonus param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("pa.bonus.updatePaBonusParamConfigure",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update Pa bonus Param configure item Configure Exception. ", e);
		}
	}
	
	/**
	 * validate add pa bonus param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateAddPaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {
		
		int result = 0 ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("pa.bonus.validateAddPaBonusParamConfigure",parameterObject) ;
			result = NumberUtil.parseInt(obj.toString()) ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" validate add Pa bonus Param configure item Configure Exception. ", e);
		}
		
		return result ;
	}
	
	/**
	 * add pa bonus param Configure item 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void insertPaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("pa.bonus.insertPaBonusParamConfigure",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("add Pa bonus Param configure item Configure Exception. ", e);
		}
	}
	
	/**
	 * pa bonus param Initial  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void paBonusParamInitial(SimpleMap parameterObject) throws GlRuntimeException {

		try {
				commonSQLMapAdapter.insert("pa.bonus.callPrBonusParamInitial",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("pa bonus param Initial  Exception. ", e);
		}
	}
}	


