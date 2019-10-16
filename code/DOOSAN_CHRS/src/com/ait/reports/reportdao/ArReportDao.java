package com.ait.reports.reportdao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;

public class ArReportDao {
	private static ServiceLocator services;
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ArReportDao.class);

	public ArReportDao() {
		 try {
			services = ServiceLocator.getInstance();
			commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
		} catch (ServiceLocatorException ex) {
		}
	}
	
	/**
	 * retrieve daily detail overall
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveDailyDetailOverall(SimpleMap parameterObject) throws GlRuntimeException {
		
		SimpleMap result = new SimpleMap() ;
		try {
			
			Object obj = commonSQLMapAdapter.executeQuery("report.ar.dailyDetailOverall", parameterObject) ;
			if (obj != null)
				result = (SimpleMap)obj ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve daily detail overall data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve daily detail overall content
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDailyDetailOverallContent(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.dailyDetailOverallContent", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve daily detail overall content data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve daily detail statistics
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDailyDetailStatistics(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.dailyDetailStatistics", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve daily detail statistics data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve department by level
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDepartmentByLevel(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveDepartmentByLevel", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve department by level data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve daily detail content
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDailyDetailContent(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveDailyDetailContent", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve daily detail content data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve ar history Columns
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArHistoryColumnsList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveArHistoryColumnsList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve ar history Columns data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve ar history data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArHistoryDataList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveArHistoryDataList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve ar history data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve ar report list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArReportList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveArReportList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve ar report list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0101 dept 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0101Dept(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0101Dept", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0101 dept data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0101 head data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReport0101HeadData(SimpleMap parameterObject) throws GlRuntimeException {
		
		SimpleMap result = new SimpleMap() ;
		try {
			
			Object obj = commonSQLMapAdapter.executeQuery("report.ar.retrieveReport0101HeadData", parameterObject) ;
			if (obj != null)
				result = (SimpleMap)obj ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0101 head data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0101 Center data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0101CenterData(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0101CenterData", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0101 Center data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0101 content data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0101ContentData(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0101ContentData", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0101 content data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0102 Center data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0102CenterData(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0102CenterData", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0102 Center data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0106 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0106Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0106Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0106 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0107 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0107Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0107Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0107 data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveReport0107Data1(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0107Data1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0107 data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveReport0107Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0107Data2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0107 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0107 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport01287Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport01287Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0107 data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveReport01287Data1(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport01287Data1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0107 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0111 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0111Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0111Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0111 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0112 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0112Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0112Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0112 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0105 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0105Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0105Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0105 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0103 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0103Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0103Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0103 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0104 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0104Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0104Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0104 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0104 content data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0104ContentData(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0104ContentData", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0104 content data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0108 ar Date 
	 * 
	 * @param parameterObject
	 * @return SimpleMap
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReport0108ArDate(SimpleMap parameterObject) throws GlRuntimeException {
		
		SimpleMap result = new SimpleMap() ;
		try {
			Object obj = commonSQLMapAdapter.executeQuery("report.ar.retrieveReport0108ArDate", parameterObject); 
			result = obj !=null ? (SimpleMap)obj : result ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0108 date Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0108 Data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0108Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0108Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0108 data Exception. ", e);
		}
		return result;
	}
	
 /* public List retrieveReport0001Data(SimpleMap parameterObject) throws GlRuntimeException {ss
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0001Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0001 data Exception. ", e);
		}
		return result;
	}
  
  public List retrieveReport0001_Data(SimpleMap parameterObject) throws GlRuntimeException {ss
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport00011Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report00011 data Exception. ", e);
		}
		return result;
	}
  
	*/
	/**
	 * retrieve report0108 Data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0108Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0108Data2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0108 data 2 Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0109 Data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0109Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0109Data2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0109 data 2 Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0110 Data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0110Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0110Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0110 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0109 Data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0110Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0110Data2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0110 data 2 Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieveReport0113Data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0113Data(SimpleMap parameterObject)throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0113Data", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0113 data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * retrieve report0114 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0114Data1(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0114Data1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0114 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0115 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0115Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0115Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0115 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0117 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0117Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0117Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0117 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0118 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0118Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0118Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0118 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0119 dept 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0119Dept(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0119Dept", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0119 dept data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0119 ArStaItem 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0119ArStaItem(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0119ArStaItem", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0119 ArItem data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0119 ArEmpList 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReport0119ArEmpList(SimpleMap parameterObject) throws GlRuntimeException {
		
		SimpleMap result = new SimpleMap() ;
		try {

			Object obj = commonSQLMapAdapter.executeQuery("report.ar.retrieveReport0119ArEmpList", parameterObject);
			result = obj !=null ? (SimpleMap)obj : result ;
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0119 ArEmpList data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0120 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0120Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0120Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0120 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0121 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0121Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0121Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0121 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve report0123 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0123Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0123Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0123 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve report0124 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0124Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0124Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0124 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0125 data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0125Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0125Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0125 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve report0126 Data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0126Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0126Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0126 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve report0126 Data 2 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0126Data2(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0126Data2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0126 data 2 Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve retrieve Report Ar Date StartAndEnd
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public SimpleMap retrieveReportArDateStartAndEnd(SimpleMap parameterObject) throws GlRuntimeException {
		
		SimpleMap result = new SimpleMap() ;
		try {
			
			Object obj = commonSQLMapAdapter.executeQuery("report.ar.retrieveReportArDateStartAndEnd", parameterObject) ;
			if (obj != null)
				result = (SimpleMap)obj ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report ar date start and end Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0127 Data 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0127Data(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.ar.retrieveReport0127Data", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve report0127 data Exception. ", e);
		}
		return result;
	}

}
