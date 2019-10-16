package com.ait.safe.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.gm.dao.EateryDAO;
import com.ait.safe.dao.securitychecks.SecurityChecksDAO;

public class RulesDAOFactory {
	
	private static final String Vacation = "Vacation";
	
	private static final String PlanConfirmDAO = "PlanConfirmDAO";
	
	private static final String SecurityChecksDAO="SecurityChecksDAO";
	
	private static final String JobHealthDAO="JobHealthDAO";

	private static RulesDAOFactory instance = new RulesDAOFactory();

	private Map daos;

	public RulesDAOFactory() {
		daos = new HashMap();
		daos.put(PlanConfirmDAO, new RulesDAO());
		daos.put(SecurityChecksDAO, new SecurityChecksDAO());
		daos.put(JobHealthDAO, new JobHealthDAO());
	}

	public RulesDAO getPlanConfirmDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.safe.dao.RulesDAO.PlanConfirmDAO");
		return (RulesDAO) daos.get(PlanConfirmDAO);
	}
	public SecurityChecksDAO getSecurityChecksDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.safe.dao.SecurityChecksDAO");
		return (SecurityChecksDAO) daos.get(SecurityChecksDAO);
	}
	
	public static RulesDAOFactory getInstance() {
		Logger.getLogger(RulesDAOFactory.class)
				.debug("get RulesDAOFactory instancs ");
		return instance;
	}
	
	public JobHealthDAO getJobHealthDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.safe.dao.RulesDAO.JobHealthDAO");
		return (JobHealthDAO) daos.get(JobHealthDAO);
	}
}
