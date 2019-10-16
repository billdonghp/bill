package com.ait.gm.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class WasteDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(WasteDAO.class);

	public WasteDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List wasteView(Object parameterObject , int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.wasteView", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" wasteView list by paging Exception. ", e);
		}
		return result;
	}
	public List wasteView(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.wasteView", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" wasteView list by paging Exception. ", e);
		}
		return result;
	}

	public List wasteType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.wasteType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" wasteType list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object addWaste(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("gm.waste.addWaste", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" addWaste list by paging Exception. ", e);
		}
		return result;
	}
	
	
	public List allUpdateWasteInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.allUpdateWasteInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allUpdateWasteInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updateWaste(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.update("gm.waste.updateWaste", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" updateWaste list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object allWasteInformationCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("gm.waste.allWasteInformationCount", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allWasteInformationCount list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object deleteWaste(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("gm.waste.deleteWaste", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" deleteWaste list by paging Exception. ", e);
		}
		return result;
	}
	public Object deleteWasteBasicInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("gm.waste.deleteWasteBasicInformation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" deleteWasteBasicInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public List atpresentUnitPrice(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.atpresentUnitPrice", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" atpresentUnitPrice list by paging Exception. ", e);
		}
		return result;
	}

	public double getUnitPrice(Object parameterObject) throws GlRuntimeException {

		double result;
		try {
			result = NumberUtil.parseDouble(commonSQLMapAdapter.executeQuery("gm.waste.getUnitPrice", parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getUnitPrice list by paging Exception. ", e);
		}
		return result;
	}
	
	public SimpleMap getUnits(Object parameterObject) throws GlRuntimeException {

		SimpleMap result;
		try {
			result = (SimpleMap)commonSQLMapAdapter.executeQuery("gm.waste.getUnits", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getUnits list by paging Exception. ", e);
		}
		return result;
	}
	
	public int updateUnitPrice(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = commonSQLMapAdapter.update("gm.waste.updateUnitPrice", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" atpresentUnitPrice list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object changeActive(Object parameterObject) throws GlRuntimeException {
		
		Object result;
		
		try {
			result = commonSQLMapAdapter.update("gm.waste.changeActive", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("changeActive list by paging Exception. ", e);
		}
		return result;
	}
	
	public List searchActive(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.searchActive", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" searchActive list by paging Exception. ", e);
		}
		return result;
	}
	
	public List WASTE_SET_APPLICABLE_DATE(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.WASTE_SET_APPLICABLE_DATE", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" WASTE_SET_APPLICABLE_DATE list by paging Exception. ", e);
		}
		return result;
	}
	
	public String time(Object parameterObject) throws GlRuntimeException {

		String result;
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.waste.time", parameterObject),"2005-01-01").toString();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" time list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object allgmmonthClearingSearchCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("gm.waste.allgmmonthClearingSearchCount", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allgmmonthClearingSearchCount list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allmonthClearingSearch(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.allmonthClearingSearch", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allmonthClearingSearch list by paging Exception. ", e);
		}
		return result;
	}
	
	public int alltotal(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.waste.alltotal", parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" alltotal list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getBasicInformation(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.getBasicInformation", parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getBasicInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public int getBasicInformationInt(Object parameterObject) throws GlRuntimeException {

		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.waste.getBasicInformationInt", parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getBasicInformationInt list by paging Exception. ", e);
		}
		return result;
	}
	

	public Object addWasteBasicInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("gm.waste.addWasteBasicInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" addWasteBasicInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public List wasteTypeSingle(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.wasteTypeSingle", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" wasteTypeSingle list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updateWasteBasicInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.update("gm.waste.updateWasteBasicInformation", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" addWasteBasicInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allCompanyCustomers(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.allCompanyCustomers", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allCompanyCustomers list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getRevenue_approach(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.getRevenue_approach", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getRevenue_approach list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getNo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.getNo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getNo list by paging Exception. ", e);
		}
		return result;
	}
	
	public String getEndNo(Object parameterObject) throws GlRuntimeException {

		String result;
		try {
			result = commonSQLMapAdapter.executeQuery("gm.waste.getEndNo", parameterObject).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getEndNo list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allInformation(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.allInformation", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allInformation1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.waste.allInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" allInformation1 list by paging Exception. ", e);
		}
		return result;
	}
	
	public int getSeq(Object parameterObject) throws GlRuntimeException {

		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.waste.getSeq", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getSeq list by paging Exception. ", e);
		}
		return result;
	}
}
