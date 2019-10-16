package com.ait.ga.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import sun.util.logging.resources.logging;

import com.ait.ar.dao.vacation.VacationBean;
import com.ait.ar.dao.vacation.VacationEmpDAO;
import com.ait.ga.dao.asset.AssetDAO;
import com.ait.ga.dao.certificate.CertificateDAO;
import com.ait.ga.dao.eatingcard.EatingCardDAO;
import com.ait.ga.dao.express.ExpressApplyAndAffirmDAO;
import com.ait.ga.dao.festivalpresent.FestivalPresentDAO;
import com.ait.ga.dao.food.FoodDAO;
import com.ait.ga.dao.inspection.InspectionDAO;
import com.ait.ga.dao.present.PresentDAO;
import com.ait.ga.dao.seal.SealMangerDAO;
import com.ait.ga.dao.securityenvironment.SecurityEnvironmentDAO;
import com.ait.ga.dao.smock.SmockDAO;
import com.ait.ga.dao.smock.SmockProvideDAO;
import com.ait.ga.dao.smock.SmockRelationDAO;
import com.ait.ga.dao.visa.VisaMangerDAO;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.ga.dao.visiter.VisiterDAO;
import com.ait.ga.dao.washhouse.WashhouseDAO;
import com.ait.ga.dao.woodproducts.WoodProductsDAO;

public class GaDAOFactory {

	private static final String Vacation = "Vacation";

	private static final String VacationEmpDAO = "VacationEmpDAO";

	private static final String ListRecordDAO = "ListRecordDAO";

	private static final String ExpressApplyAndAffirmDAO = "ExpressApplyAndAffirmDAO";
	
	private static final String SecurityEnvironmentDAO="SecurityEnvironmentDAO";

	private static final String VisiterDAO="VisiterDAO";
	
	private static final String SealMangerDAO = "SealMangerDAO";
	
	private static final String VisaMangerDAO = "VisaMangerDAO";
	
	private static final String WoodProductsDAO = "WoodProductsDAO";
	
	private static final String EatingCardDAO ="EatingCardDAO";
	
	private static final String AssetDAO = "AssetDAO";
	
	private static final String PresentDAO = "PresentDAO";
	
	private static final String InspectionDAO = "InspectionDAO";
	
	private static final String SmockDAO = "SmockDAO";
	
	private static final String WashhouseDAO = "WashhouseDAO";
	
	private static final String SmockRelationDAO = "SmockRelationDAO";
	
	private static final String SmockProvideDAO = "SmockProvideDAO";
	
	private static final String FestivalPresentDAO = "FestivalPresentDAO";
	
	private static final String CertificateDAO = "CertificateDAO";
	
	private static final String FoodDAO = "FoodDAO";
	
	
	private static GaDAOFactory instance = new GaDAOFactory();

	private Map daos;

	public GaDAOFactory() {
		daos = new HashMap();
		daos.put(Vacation, new VacationBean());
		daos.put(VacationEmpDAO, new VacationEmpDAO());
		daos.put(ListRecordDAO, new ListRecordDAO());
		daos.put(ExpressApplyAndAffirmDAO, new ExpressApplyAndAffirmDAO());
		daos.put(SecurityEnvironmentDAO, new SecurityEnvironmentDAO());
		daos.put(VisiterDAO, new VisiterDAO());
		daos.put(SealMangerDAO, new SealMangerDAO());
		daos.put(VisaMangerDAO, new VisaMangerDAO());
		daos.put(WoodProductsDAO, new WoodProductsDAO());
		daos.put(EatingCardDAO, new EatingCardDAO());
		daos.put(PresentDAO, new PresentDAO());
		daos.put("AssetDAO", new AssetDAO());
		daos.put(InspectionDAO, new InspectionDAO());
		daos.put("SmockDAO", new SmockDAO());
		daos.put("WashhouseDAO", new WashhouseDAO());
		daos.put("SmockRelationDAO", new SmockRelationDAO());
		daos.put("SmockProvideDAO", new SmockProvideDAO());
		daos.put("FestivalPresentDAO", new FestivalPresentDAO());
		daos.put("CertificateDAO", new CertificateDAO());
		daos.put("FoodDAO", new FoodDAO());
	}

	public VacationBean getVacationDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.vacation.VacationBean");
		return (VacationBean) daos.get(Vacation);
	}

	public ListRecordDAO getListRecordDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.visit.ListRecordDAO");
		return (ListRecordDAO) daos.get(ListRecordDAO);
	}
	public ExpressApplyAndAffirmDAO getExpressApplyAndAffirmDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.express.ExpressApplyAndAffirmDAO");
		return (ExpressApplyAndAffirmDAO) daos.get(ExpressApplyAndAffirmDAO);
	}
	public SecurityEnvironmentDAO getSecurityEnvironmentDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.SecurityEnvironmentDAO");
		return (SecurityEnvironmentDAO) daos.get(SecurityEnvironmentDAO);
	}
	public SealMangerDAO getSealMangerDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.SealMangerDAO");
		return (SealMangerDAO) daos.get(SealMangerDAO);
	}
	public VisaMangerDAO getVisaMangerDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.VisaMangerDAO");
		return (VisaMangerDAO) daos.get(VisaMangerDAO);
	}
	public VisiterDAO getVisiterDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.getVisiterDAO");
		return (VisiterDAO) daos.get(VisiterDAO);
	}
	public WoodProductsDAO getWoodProductsDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.WoodProductsDAO");
		return (WoodProductsDAO) daos.get(WoodProductsDAO);
	}
	public EatingCardDAO getEatingCardDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.EatingCardDAO");
		return (EatingCardDAO) daos.get(EatingCardDAO);
	}
	
	public PresentDAO getPresentDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.present.PresentDAO");
		return (PresentDAO) daos.get(PresentDAO);
	}
	
	public static GaDAOFactory getInstance() {
		Logger.getLogger(GaDAOFactory.class)
				.debug("get GaDAOFactory instancs ");
		return instance;
	}
	
	public AssetDAO getAssetDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.AssetDAO");
		return (AssetDAO) daos.get(AssetDAO);
	}
	
	public InspectionDAO getInspectionDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.InspectionDAO");
		return (InspectionDAO) daos.get(InspectionDAO);
	}
	
	public SmockDAO getSmockDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.SmockDAO");
		return (SmockDAO)daos.get("SmockDAO");
	}
	
	public WashhouseDAO getWashhouseDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.WashhouseDAO");
		return (WashhouseDAO)daos.get("WashhouseDAO");
	}
	
	public SmockRelationDAO getSmockRelationDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.SmockRelationDAO");
		return (SmockRelationDAO)daos.get("SmockRelationDAO");
	}
	
	public SmockProvideDAO getSmockProvideDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.SmockProvideDAO");
		return (SmockProvideDAO)daos.get("SmockProvideDAO");
	}
	
	public FestivalPresentDAO getFestivalPresentDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.FestivalPresentDAO");
		return (FestivalPresentDAO)daos.get("FestivalPresentDAO");
	}
	
	public CertificateDAO getCertificateDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.CertificateDAO");
		return (CertificateDAO)daos.get("CertificateDAO");
	}
	
	public FoodDAO getFoodDAO(){
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ga.dao.FoodDAO");
		return (FoodDAO)daos.get("FoodDAO");
	}
}
