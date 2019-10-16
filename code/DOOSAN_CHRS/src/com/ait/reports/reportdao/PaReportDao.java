package com.ait.reports.reportdao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class PaReportDao {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ArReportDao.class);

	public PaReportDao() {
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	/**
	 * retrieve pa filiale dept list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaFilialeDeptList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaFilialeDeptList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa filiale dept list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa dept list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDeptList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaFactoryDeptList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa dept list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve pa position wage list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaPositionWageList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaPositionWageList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail Part Factory list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailPartFactoryList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailPartFactoryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve Pa Detail Part Factory list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0204 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0204List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0204List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 78000000 Report0204 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0204 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0204List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0204List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 60000000 Report0204 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0204 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0204List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0204List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 63000000 Report0204 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail All Factory list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailAllFactoryList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailAllFactoryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve Pa Detail All Factory list data Exception. ", e);
		}
		return result;
	}
	/**
	 * retrieve 78000000 Report0212 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0212List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0212List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve780Report0212List  data Exception. ", e);
		}
		return result;
	}	
	/**
	 * retrieve 63000000 Report0212 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0212List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0212List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve780Report0212List data Exception. ", e);
		}
		return result;
	}
	/**
	 * retrieve 60000000 Report0212 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0212List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0212List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve780Report0212List data Exception. ", e);
		}
		return result;
	}
	/**
	 * retrieve 78000000 Report0206 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0206List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0206List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 78000000 Report0206  data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0206 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0206List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0206Lists", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 60000000 Report0206  data Exception. ", e);
		}
		return result;
	}
	
   public List retrieve600Report0206Listss(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0206List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 60000000 Report0206  data Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve 63000000 Report0206 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0206List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0206List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 63000000 Report0206  data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail Factory list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailFactoryList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailFactoryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve Pa Detail Factory list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0202 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0202List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0202List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 78000000 Report0202 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0202 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0202List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0202List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 60000000 Report0202 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0202 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0202List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0202List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve 63000000 Report0202 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve Pa Detail All Filiale list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailAllFilialeList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailAllFilialeList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Detail All Filiale list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0210
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0210List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0210List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0210 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve 63000000 Report0210
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0210List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0210List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0210 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0210
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0210List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0210List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0210 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0226
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0226List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0226List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0226 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0226
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0226List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0226List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0226 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail Filiale list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailFilialeList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailFilialeList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Detail Filiale list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0208
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0208List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0208List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0208 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0208
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0208List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0208List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0208 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0208
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0208List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0208List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0208 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail Replenishment list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailReplenishmentList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailReplenishmentList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Detail Replenishment list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Bonus Detail All Factory list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusDetailAllFactoryList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaBonusDetailAllFactoryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail All Factory list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0207
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0207List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0207List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0207 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0207
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0207List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0207List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0207 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0207
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0207List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0207List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0207 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0212B
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0212BList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0212BList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0212B data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0212B
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0212BList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0212BList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0212B data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0212B
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0212BList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0212BList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0212B data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Bonus Detail Part Factory list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusDetailPartFactoryList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaBonusDetailPartFactoryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail Part Factory list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0205
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0205List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0205List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0205 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0205
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0205List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0205List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0205 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0205
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0205List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0205List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0205 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Bonus Detail Factory list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusDetailFactoryList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaBonusDetailFactoryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail Factory list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0203
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0203List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0203List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0203 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0203
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0203List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0203List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0203 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0203
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0203List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0203List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0203 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Bonus Detail Filiale list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusDetailFilialeList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaBonusDetailFilialeList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail Filiale list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0209
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0209List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0209List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0209 data Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve 63000000 Report0209
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0209List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0209List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0209 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0209
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0209List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0209List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0209 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Bonus Detail All Filiale list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaBonusDetailAllFilialeList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaBonusDetailAllFilialeList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail All Filiale list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0211
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0211List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0211List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0211 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0211
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0211List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0211List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0211 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0211
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0211List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0211List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0211 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0226B
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0226BList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0226BList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0226B data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0226B
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0226BList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0226BList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0226B data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail Factory Average List
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailFactoryAverageList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailFactoryAverageList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail All Filiale list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0211
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0219List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0219List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0211 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0211
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0219List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0219List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0219 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0211
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0219List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0219List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0219 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0221
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0221List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0221List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0221 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0221 Pa
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0221PaList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0221PaList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0221 Pa data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0221 Bonus
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0221BonusList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0221BonusList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0221 Bonus data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0222 Pa
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0222PaList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0222PaList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0222 Pa data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0222 Bonus
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0222BonusList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0222BonusList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0222 Bonus data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 61000000 Report0223
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve610Report0223List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve610Report0223List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 61000000 Report0223 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 61000000 Report0224
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve610Report0224List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve610Report0224List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 61000000 Report0224 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Pa Detail Filiale Average List
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaDetailFilialeAverageList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaDetailFilialeAverageList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Pa Bonus Detail All Filiale list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 78000000 Report0220
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve780Report0220List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve780Report0220List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 78000000 Report0220 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 63000000 Report0220
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve630Report0220List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve630Report0220List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 63000000 Report0220 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve 60000000 Report0220
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieve600Report0220List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieve600Report0220List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve 60000000 Report0220 data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * 工资报表
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaReportList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePaReportList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa report list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * 评价报表
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEvsReportList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveEvsReportList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa report list Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve Report0225 pa
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0225PaList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0225PaList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Report0225 pa data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve Report0225 bonus
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0225BonusList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0225BonusList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve Report0225 bonus data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAchievementList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveAchievementList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	//
	public List retrieveIndividualsWageList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveIndividualsWageList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
//	retrieveInfoOnList
	public List retrieveInfoOnList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoOnList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	//
	public List retrieveAchievementbyEmpId(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveAchievementbyEmpId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	//
	public List retrieveInfoOnListEnd(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoOnListEnd", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePayrollList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrievePayrollList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveKpaAnnualAdjustmentList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveKpaAnnualAdjustmentList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	//
	public List retrieveKpaAmountList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveKpaAmountList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveKpaTaxListAndAchievement(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveKpaTaxListAndAchievement", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveKpaTaxList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveKpaTaxList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	

	public List retrieveKpaTaxYearChangeList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveKpaTaxYearChangeList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	//
	public List retrieveInfoUnderList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoUnderList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	//
	public List retrieveInfoYearSum(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoYearSum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveInfoSecondYearSum(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoSecondYearSum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	public List retrieveInfoUnderSecondList(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoUnderSecondList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	public List retrieveInfoFirstYearSum(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveInfoFirstYearSum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa position wage list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0233 list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0233List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0233List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve report0233 list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0232 list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0232List(SimpleMap parameterObject) throws GlRuntimeException {
		
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0232List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieve report0232 list data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0231 list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0231List(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0231List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0231List data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0228 list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0228List(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0228List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0228List data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0229 list 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0229List(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0229List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0229List data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0229 list sum
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0229ListSum(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0229ListSum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0229ListSum data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0230 list 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0230List(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0230List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0230List data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0234 list 
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0234List(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0234List", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0234List data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * retrieve report0234 list sum
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveReport0234ListSum(SimpleMap parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("report.pa.retrieveReport0234ListSum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveReport0234ListSum data Exception. ", e);
		}
		return result;
	}
}
