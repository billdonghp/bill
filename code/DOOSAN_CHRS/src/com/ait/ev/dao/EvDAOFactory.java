package com.ait.ev.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.gm.dao.EateryDAO;
import com.ait.safe.dao.securitychecks.SecurityChecksDAO;

public class EvDAOFactory {
	
	//private static final String Vacation = "Vacation";
	
	//private static final String PlanConfirmDAO = "PlanConfirmDAO";
	
	private static final String EvaluateApplyDAO="EvaluateApplyDAO";
	
	//private static final String JobHealthDAO="JobHealthDAO";

	private static EvDAOFactory instance = new EvDAOFactory();

	private Map daos;

	public EvDAOFactory() {
		daos = new HashMap();
		//daos.put(PlanConfirmDAO, new RulesDAO());
		daos.put(EvaluateApplyDAO, new EvaluateApplyDAO());
		//.put(JobHealthDAO, new JobHealthDAO());
	}

//	public RulesDAO getPlanConfirmDAO() {
//		Logger.getLogger(getClass()).debug(
//				"get object : com.ait.safe.dao.RulesDAO.PlanConfirmDAO");
//		return (RulesDAO) daos.get(PlanConfirmDAO);
//	}
	public EvaluateApplyDAO getEvaluateApplyDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ev.dao.EvaluateApplyDAO");
		return (EvaluateApplyDAO) daos.get(EvaluateApplyDAO);
	}
	
	public static EvDAOFactory getInstance() {
		Logger.getLogger(EvDAOFactory.class)
				.debug("get RulesDAOFactory instancs ");
		return instance;
	}
	
//	public JobHealthDAO getJobHealthDAO() {
//		Logger.getLogger(getClass()).debug(
//				"get object : com.ait.safe.dao.RulesDAO.JobHealthDAO");
//		return (JobHealthDAO) daos.get(JobHealthDAO);
//	}
}
