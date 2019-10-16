package com.ait.kpa.dao.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;

public class PaDsHrDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PaDsHrDAO.class);
	
	private final String dsHrClientName = "dshr";

	public PaDsHrDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter(dsHrClientName);
	}
	
	/**
	 * create DSHR table
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void createDsHrTable(Object parameterObject) throws GlRuntimeException {
		
		try {

			commonSQLMapAdapter.update("kpa.dshr.createDsHrTable",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("create DSHR table Exception. ", e);
		}
		
	}
	
	/**
	 * check DSHR table
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int checkDsHrTable(Object parameterObject) throws GlRuntimeException {

		int result = 0 ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("kpa.dshr.checkDsHrTable",parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("check DSHR table Exception. ", e);
		}
		return result;
	}
	
	/**
	 *  DSHR COLUMN NAMES
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDsHrColumns(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.dshr.retrieveDsHrColumns",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve DSHR COLUMN NAMES Exception. ", e);
		}
		return result;
	}
	
	/**
	 * alter DSHR column
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void alertDsHrColumns(List parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("kpa.dshr.alertDsHrColumns",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve DSHR COLUMN NAMES Exception. ", e);
		}
	}
}

