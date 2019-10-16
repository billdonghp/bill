package com.ait.reports.reportservices;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.dao.AnnualBean;
import com.ait.ar.dao.ArDAOFactory;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.EateryDAO;
import com.ait.reports.reportdao.EatDao;


public class EatReportService {
	EatDao eatDao = null ;
	
	private static final Logger logger = Logger.getLogger(EatReportService.class);

	public EatReportService() {
		eatDao = new EatDao() ; 
	}
	
	public List eatReport(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result=eatDao.eatReport(parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatDao Report list by paging Exception. ", e);
		}
		return result;
	}
	
	public List eateryType() throws GlRuntimeException {
		List result;
		try {
			result=eatDao.eateryType();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatDao eateryType list by paging Exception. ", e);
		}
		return result;
	}
	
	public List empid(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result=eatDao.empid(parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatDao dept list by paging Exception. ", e);
		}
		return result;
	}
}
