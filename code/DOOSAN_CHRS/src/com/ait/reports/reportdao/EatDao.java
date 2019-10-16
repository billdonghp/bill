package com.ait.reports.reportdao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class EatDao {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EatDao.class);

	public EatDao() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	public List eatReport(Object parameterObject) throws GlRuntimeException {
	
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("report.gm.eat.eatReport", parameterObject);
	
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatReport by paging Exception. ", e);
		}
		return result;
	}
	
	public List eateryType() throws GlRuntimeException {
		
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("report.gm.eat.eateryType");
	
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eateryType by paging Exception. ", e);
		}
		return result;
	}
	
	public List empid(Object parameterObject) throws GlRuntimeException {
		
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("report.gm.eat.empid", parameterObject);
	
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eateryType by paging Exception. ", e);
		}
		return result;
	}
}
