package com.ait.reports.reportservices;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.reports.reportdao.ArReportDao;
import com.ait.sqlmap.util.SimpleMap;


public class ArReportService {
	ArReportDao arReportDao = null ;
	
	private static final Logger logger = Logger.getLogger(ArReportService.class);

	public ArReportService() {
		arReportDao = new ArReportDao() ; 
	}
	
	/**
	 * retrieve daily detail overall
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveDailyDetailOverall(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveDailyDetailOverall(parameterObject) ;
	}
	
	/**
	 * retrieve daily detail overall content
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDailyDetailOverallContent(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveDailyDetailOverallContent(parameterObject) ;
	}
	
	/**
	 * retrieve daily detail statistics
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDailyDetailStatistics(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveDailyDetailStatistics(parameterObject) ;
	}
	
	/**
	 * retrieve department by level
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDepartmentByLevel(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveDepartmentByLevel(parameterObject) ;
	}
	
	/**
	 * retrieve daily detail content
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDailyDetailContent(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveDailyDetailContent(parameterObject) ;
	}
	
	/**
	 * retrieve ar history Columns
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArHistoryColumnsList(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveArHistoryColumnsList(parameterObject) ;
	}
	
	/**
	 * retrieve ar history data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArHistoryDataList(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveArHistoryDataList(parameterObject) ;
	}
	
	/**
	 * retrieve ar report list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArReportList(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveArReportList(parameterObject) ;
	}
	
	/**
	 * retrieve report0101 dept 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0101Dept(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0101Dept(parameterObject) ;
	}
	
	/**
	 * retrieve report0101 head data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReport0101HeadData(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0101HeadData(parameterObject) ;
	}
	
	/**
	 * retrieve report0101 center data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0101CenterData(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0101CenterData(parameterObject) ;
	}
	
	/**
	 * retrieve report0101 content date
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0101ContentData(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0101ContentData(parameterObject) ;
	}
	
	/**
	 * retrieve report0102 center data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0102CenterData(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0102CenterData(parameterObject) ;
	}
	
	/**
	 * retrieve report0106 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0106Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0106Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0107 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0107Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0107Data(parameterObject) ;
	}
	
   public List retrieveReport0107Data1(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0107Data1(parameterObject) ;
	}
   
   public List retrieveReport0107Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0107Data2(parameterObject) ;
	}
	
	/**
	 * retrieve report01287 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport01287Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport01287Data(parameterObject) ;
	}
	public List retrieveReport01287Data1(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport01287Data1(parameterObject) ;
	}
	
	/**
	 * retrieve report0111 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0111Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0111Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0112 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0112Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0112Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0105 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0105Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0105Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0103 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0103Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0103Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0104 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0104Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0104Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0104 content date
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0104ContentData(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0104ContentData(parameterObject) ;
	}
	
	/**
	 * retrieve report0108 ar date 
	 * 
	 * @param parameterObject
	 * @return SimpleMap
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReport0108ArDate(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0108ArDate(parameterObject) ;
	}
	
	/**
	 * retrieve report0108 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0108Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0108Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0001 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
/*	public List retrieveReport0001Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0001Data(parameterObject) ;
	}

	public List retrieveReport0001_Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0001_Data(parameterObject) ;
	}
	*/
	/**
	 * retrieve report0108 data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0108Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0108Data2(parameterObject) ;
	}
	
	/**
	 * retrieve report0109 data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0109Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0109Data2(parameterObject) ;
	}
	
	/**
	 * retrieve report0110 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0110Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0110Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0109 data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0110Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0110Data2(parameterObject) ;
	}
	
	/**
	 * retrieve report0113 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0113Data(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0113Data(parameterObject);
	}
	
	/**
	 * retrieve report0114 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0114Data1(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0114Data1(parameterObject);
	}
	
	/**
	 * retrieve report0115 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0115Data(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0115Data(parameterObject);
	}
	
	/**
	 * retrieve report0117 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0117Data(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0117Data(parameterObject);
	}
	
	/**
	 * retrieve report0118 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0118Data(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0118Data(parameterObject);
	}
	
	
	/**
	 * retrieve report0119 dept 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0119Dept(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0119Dept(parameterObject) ;
	}
	
	
	/**
	 * retrieve report0119 ArStaItem 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0119ArStaItem(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0119ArStaItem(parameterObject) ;
	}
	
	/**
	 * retrieve report0119 ArEmpList 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReport0119ArEmpList(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0119ArEmpList(parameterObject) ;
	}
	
	/**
	 * retrieve report0120 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0120Data(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0120Data(parameterObject);
	}
	
	/**
	 * retrieve report0121 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0121Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0121Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0123 data
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0123Data(SimpleMap parameterObject) throws GlRuntimeException{
		
		return arReportDao.retrieveReport0123Data(parameterObject);
	}
	
	/**
	 * retrieve report0124 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0124Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0124Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0125 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0125Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0125Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0126 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0126Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0126Data(parameterObject) ;
	}
	
	/**
	 * retrieve report0126 data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0126Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0126Data2(parameterObject) ;
	}
	
	
	/**
	 * retrieve 
	 * 
	 * @param parameterObject
	 * @return SimpleMap
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReportArDateStartAndEnd(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReportArDateStartAndEnd(parameterObject) ;
	}
	
	/**
	 * retrieve report0127 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0127Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		return arReportDao.retrieveReport0127Data(parameterObject) ;
	}
}
