package com.ait.reports.reportservices;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.reports.reportdao.EvsReportDao;
import com.ait.reports.reportdao.PaReportDao;
import com.ait.sqlmap.util.SimpleMap;


public class EvsReportService {
	EvsReportDao evsReportDao = null ;
	
	private static final Logger logger = Logger.getLogger(EvsReportService.class);

	public EvsReportService() {
		evsReportDao = new EvsReportDao() ; 
	}
	
	/**
	 * retrieve 78000000 Report0212
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0535List(SimpleMap parameterObject) throws GlRuntimeException {
		
		return evsReportDao.retrieve780Report0535List(parameterObject) ;
	}
	/**
	 * retrieve 78000000 Report0212
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0536List(SimpleMap parameterObject) throws GlRuntimeException {
		
		return evsReportDao.retrieve780Report0536List(parameterObject) ;
	}
	
	/**
	 * retrieve 78000000 Report0212
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0537List(SimpleMap parameterObject) throws GlRuntimeException {
		
		return evsReportDao.retrieve780Report0537List(parameterObject) ;
	}
	
	/**
	 * retrieve 78000000 Report0212
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0538List(SimpleMap parameterObject) throws GlRuntimeException {
		
		return evsReportDao.retrieve780Report0538List(parameterObject) ;
	}
	
	/**
	 * retrieve 78000000 Report0212
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0539List(SimpleMap parameterObject) throws GlRuntimeException {
		
		return evsReportDao.retrieve780Report0539List(parameterObject) ;
	}
	
	
	
}
