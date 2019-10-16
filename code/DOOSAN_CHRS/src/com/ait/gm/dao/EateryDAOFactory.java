package com.ait.gm.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.gm.dao.EateryDAO;
import com.ait.gm.dao.sealdao.SealManagementDAO;
import com.ait.gm.dao.woodProductsdao.WoodProductsDAO;

public class EateryDAOFactory {
	
	private static final String Vacation = "Vacation";
	
	private static final String PlanConfirmDAO = "PlanConfirmDAO";
	
	private static final String expressMangerDAO = "expressMangerDAO";
	
	private static final String WasteDAO="WasteDAO";
	
	private static final String SealManagementDAO="SealManagementDAO";
	
	private static final String WoodProductsDAO="WoodProductsDAO";

	private static EateryDAOFactory instance = new EateryDAOFactory();

	private Map daos;

	public EateryDAOFactory() {
		daos = new HashMap();
		daos.put(PlanConfirmDAO, new EateryDAO());
		daos.put(expressMangerDAO,new ExpressMangerDAO());
		daos.put(WasteDAO, new WasteDAO());
		daos.put(SealManagementDAO, new SealManagementDAO());
		daos.put(WoodProductsDAO, new WoodProductsDAO());
	}

	public EateryDAO getPlanConfirmDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.gm.dao.EateryDAO.PlanConfirmDAO");
		return (EateryDAO) daos.get(PlanConfirmDAO);
	}
	
	public ExpressMangerDAO getExpressMangerDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.gm.dao.ExpressMangerDAO.expressMangerDAO");
		return (ExpressMangerDAO) daos.get(expressMangerDAO);
	}

	public static EateryDAOFactory getInstance() {
		Logger.getLogger(EateryDAOFactory.class)
				.debug("get EateryDAOFactory instancs ");
		return instance;
	}
	
	public WasteDAO getWasteDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.gm.dao.WasteDAO.WasteDAO");
		return (WasteDAO) daos.get(WasteDAO);
	}
	public SealManagementDAO getSealManagementDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.gm.dao.sealdao.SealManagementDAO");
		return (SealManagementDAO) daos.get(SealManagementDAO);
	}
	
	public WoodProductsDAO getWoodProductsDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.gm.dao.woodProductsdao.WoodProductsDAO");
		return (WoodProductsDAO) daos.get(WoodProductsDAO);
	}
}
