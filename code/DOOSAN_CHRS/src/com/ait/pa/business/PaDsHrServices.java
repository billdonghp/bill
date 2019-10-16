package com.ait.pa.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.dao.bonus.BonusDAO;
import com.ait.pa.dao.payplan.PayPlanDAO;
import com.ait.pa.dao.postgrade.PostGradeDAO;
import com.ait.pa.dao.report.PaReportDAO;
import com.ait.pa.dao.util.PaDsHrDAO;
import com.ait.pa.dao.util.PaUtilDAO;
import com.ait.sqlmap.util.SimpleMap;


public class PaDsHrServices {

	private static PaDsHrServices paServices;
	
	private PaDsHrDAO paDsHrDAO ;
	
	private PaDsHrServices() {
		
		paDsHrDAO = new PaDsHrDAO() ;
	}

	/**
	 * 
	 * @return HrmServices
	 */
	public static PaDsHrServices getInstance() {

		if (paServices != null)
			return paServices;
		else
			return new PaDsHrServices();
	}
	
	/**
	 *  check DSHR table
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int checkDsHrTable(Object parameterObject) throws GlRuntimeException {

		return paDsHrDAO.checkDsHrTable(parameterObject);
	}
	
	/**
	 *  create DSHR table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void createDsHrTable(Object parameterObject) throws GlRuntimeException {

		paDsHrDAO.createDsHrTable(parameterObject);
	}
	
	/**
	 *  DSHR COLUMN NAMES
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveDsHrColumns(Object parameterObject) throws GlRuntimeException {

		return paDsHrDAO.retrieveDsHrColumns(parameterObject);
	}
	
	/**
	 * alter DSHR column
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public void alterDsHrColumns(List parameterObject) throws GlRuntimeException {

		paDsHrDAO.alertDsHrColumns(parameterObject);
	}
}