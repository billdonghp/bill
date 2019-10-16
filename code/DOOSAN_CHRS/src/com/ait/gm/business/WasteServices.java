package com.ait.gm.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.EateryDAOFactory;
import com.ait.gm.dao.WasteDAO;
import com.ait.sqlmap.util.SimpleMap;

/**
 * <p>
 * Description: Arservices 是一个代理类。它将数据库操作与
 * 前台操作连接起来。所有数据库的操作方法都可以通过这个代理来执行。
 * 前台操作和servlet操作都直接与services这个代理进行联系。
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 */
public class WasteServices {

	public WasteServices() {
	}

	public List wasteView(Object parameterObject , int currentPage, int pageSize) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.wasteView(parameterObject, currentPage, pageSize);
	}
	public List wasteView(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.wasteView(parameterObject);
	}
	public List wasteType(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.wasteType(parameterObject);
	}
	
	public Object addWaste(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.addWaste(parameterObject);
	}
	
	public List allUpdateWasteInformation(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allUpdateWasteInformation(parameterObject);
	}
	
	public Object updateWaste(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.updateWaste(parameterObject);
	}
	
	public Object allWasteInformationCount(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allWasteInformationCount(parameterObject);
	}
	
	public Object deleteWaste(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.deleteWaste(parameterObject);
	}
	public Object deleteWasteBasicInformation(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.deleteWasteBasicInformation(parameterObject);
	}
	
	public List atpresentUnitPrice(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.atpresentUnitPrice(parameterObject);
	}
	
	public double getUnitPrice(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getUnitPrice(parameterObject);
	}
	
	public SimpleMap getUnits(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getUnits(parameterObject);
	}
	
	public int updateUnitPrice(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.updateUnitPrice(parameterObject);
	}
	
	public Object changeActive(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.changeActive(parameterObject);
	}
	
	public List searchActive(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.searchActive(parameterObject);
	}
	
	public List WASTE_SET_APPLICABLE_DATE(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.WASTE_SET_APPLICABLE_DATE(parameterObject);
	}
	
	public String time(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.time(parameterObject);
	}
	
	public Object allgmmonthClearingSearchCount(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allgmmonthClearingSearchCount(parameterObject);
	}
	
	public List allmonthClearingSearch(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allmonthClearingSearch(parameterObject);
	}
	
	public int alltotal(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.alltotal(parameterObject);
	}
	
	public List getBasicInformation(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getBasicInformation(parameterObject, currentPage, pageSize);
	}
	
	public int getBasicInformationInt(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getBasicInformationInt(parameterObject);
	}
	
	public Object addWasteBasicInformation(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.addWasteBasicInformation(parameterObject);
	}
	
	public List wasteTypeSingle(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.wasteTypeSingle(parameterObject);
	}
	
	public Object updateWasteBasicInformation(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.updateWasteBasicInformation(parameterObject);
	}
	
	public List allCompanyCustomers(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allCompanyCustomers(parameterObject);
	}
	
public List getRevenue_approach(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getRevenue_approach(parameterObject);
	}
	
	public List getNo(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getNo(parameterObject);
	}
	
	public String getEndNo(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getEndNo(parameterObject);
	}
	
	public List allInformation(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allInformation(parameterObject,currentPage,pageSize);
	}
	
	public List allInformation1(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.allInformation1(parameterObject);
	}
	
	public int getSeq(Object parameterObject) throws GlRuntimeException {
		
		WasteDAO wasteDao = (WasteDAO) EateryDAOFactory.getInstance().getWasteDAO();
		
		return wasteDao.getSeq(parameterObject);
	}
}
