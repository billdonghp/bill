package com.ait.ga.dao.food;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class FoodDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	private static final Logger logger = Logger.getLogger(FoodDAO.class);

	public FoodDAO(){
		commonSQLMapAdapter  = new CommonSQLMapAdapter();
	}
	
	/**
	 * select food product by paging 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodProduct(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list ;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodProduct",parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select Food Product by paging date Exception.",e);
		}
		return list;
	}
	/**
	 * select food product
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List selectFoodProduct(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodProduct", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select Food Product date Exception.",e);
		}
		return list;
	}
	
	/**
	 * select food product cut
	 * @param parameterObject
	 * @return object 
	 * @throws GlRuntimeException
	 */
	public Object selectFoodProductCut(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.food.selectFoodProductCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food product cut date Exception.",e);
		}
		return obj;
	}
	
	/**
	 * insert food product
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFoodProduct(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.insert("ga.food.insertFoodProduct", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert food product data Exception.",e);
		}
	}
	
	/**
	 * update food product
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFoodProduct(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.food.updateFoodProduct", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update food product date Exception.",e);
		}
	}
	
	/**
	 * delete food product
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFoodProduct(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.delete("ga.food.deleteFoodProduct", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete food product date Exception.",e);
		}
	}
	
	/**
	 * select food scheme
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List selectFoodScheme(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodScheme",parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food scheme by paging data Exception.",e);
		}
		return list;
	}
	
	/**
	 * select food scheme
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodScheme(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodScheme", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food shceme data Exception.",e);
		}
		return list;
	}
	
	/**
	 * select food scheme cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFoodSchemeCut(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.food.selectFoodSchemeCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food scheme cut data Exception.",e);
		}
		return obj;
	}
	
	/**
	 * insert food scheme
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFoodScheme(Object parameterObject)throws GlRuntimeException{
		try {
			SimpleMap simpleMap = new SimpleMap();
			commonSQLMapAdapter.startTransaction();
			simpleMap.set("schemeName", ((SimpleMap)parameterObject).getString("schemeName"));
			simpleMap.set("remark", ((SimpleMap)parameterObject).getString("remark"));
			simpleMap.set("adminId", ((SimpleMap)parameterObject).getString("adminId"));
			simpleMap.set("cpnyId", ((SimpleMap)parameterObject).getString("cpnyId"));
			for (int i = 0; i <= Integer.parseInt(((SimpleMap)parameterObject).getString("maxRowNum")); i++) {
				
				simpleMap.setString("foodNo", ((SimpleMap)parameterObject).getString("presentNo"+i));
				simpleMap.setString("quentity", ((SimpleMap)parameterObject).getString("quentity"+i));
				commonSQLMapAdapter.insert("ga.food.insertFoodScheme", simpleMap);
			}	
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert food scheme data Exception. ",e);
		}finally{
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * update food scheme
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFoodScheme(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.food.updateFoodScheme", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update food scheme data Exception.",e);
		}
	}
	
	/**
	 * delete food scheme
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFoodScheme(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.delete("ga.food.deleteFoodScheme", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete food scheme data Exception.",e);
		}
	}
	
	/**
	 * select food detail
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodDetail(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodDetail", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food detail data Exception.",e);
		}
		return list;
	}
	
	/**
	 * get food product list 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFoodProductList(Object parameterObject)throws GlRuntimeException{
		List list ;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.getFoodProductList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get food product list data Exception.",e);
		}
		return list;
	}
	
	/**
	 * insert data for apply food
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertDataForApplyFood(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.insert("ga.food.insertDataForApplyFood", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert data for apply food data Exception.",e);
		}
	}
	
	/**
	 * select food apply
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List selectFoodApply(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodApply", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food apply data Exception.",e);
		}
		return list;
	}
	
	/**
	 * select food apply cut
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object selectFoodApplyCut(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.food.selectFoodApplyCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food apply cut data Exception.",e);
		}
		return obj;
	}
	
	/**
	 * select food dept report
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodDeptReport(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodDeptReport", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food dept report data Exception.",e);
		}
		return list;
	}
	
	/**
	 * select food detail report
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodDetailReport(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodDetailReport", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select food detail report data Exception.",e);
		}
		return list;
	}
	
	/**
	 * get food scheme list report
	 * @param paramteterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List selectSchemeReport(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectSchemeReport", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get food scheme list date Exception.",e);
		}
		return list;
	}
	
	/**
	 * select data for food report
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodReport(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.selectFoodReport", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select data for foot report data Exception.",e);
		}
		return list;
	}
	
	/**
	 * get food scheme list 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFoodSchemeList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.food.getFoodSchemeList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get food scheme List data Exception.",e);
		}
		return list;
	}
	
	/**
	 * get food apply quentity
	 * @param paramterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object getFoodApplyQuentity(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.food.getFoodApplyQuentity", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get food apply quentity data Exception.",e);
		}
		return obj;
	}
	
	/**
	 * get food apply price 
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object getFoodApplyPrice(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.food.getFoodApplyPrice", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get food apply price data Exception.",e);
		}
		return obj;
	}
}
