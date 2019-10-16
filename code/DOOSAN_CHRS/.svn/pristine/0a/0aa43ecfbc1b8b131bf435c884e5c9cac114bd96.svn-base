package com.ait.ar.dao.otplan;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

public class OTPlanDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(OTPlanDAO.class);

	public OTPlanDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * NEW init initOTPlan 
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public void initOTPlan(Object parameterObject) throws GlRuntimeException {

		try {
			 commonSQLMapAdapter.insert("ar.overtimeplan.initOTPlan",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("init initOTPlan  Exception. ", e);
		}
	}
	
	/**
	 * NEW validate init arMonth
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int validateInitOTPlan(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ar.overtimeplan.validateInitOTPlan", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate init arMonth Exception. ", e);
		}
		return result;
	}
	
	/**
	 * NEW retrieve overtime plan record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveOTPlanRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.overtimeplan.retrieveOTPlanRecordList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve overtime plan record list by paging Exception. ", e);
		}
		return result;
	}
	
	/**
	 * NEW retrieve overtime plan record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveOTPlanRecordListCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.overtimeplan.retrieveOTPlanRecordListCnt", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve overtime plan record list count Exception. ", e);
		}
		return result;
	}
	
	/**
	 * NEW validate empid and plan_month
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object validateOTPlan(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.overtimeplan.validateOTPlan", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validate empid and plan_month Exception. ", e);
		}
		return result;
	}
	
	/**
	 * NEW insert AR_OVERTIME_PLAN data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public Object insertOTPlan(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("ar.overtimeplan.insertOTPlan", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert AR_OVERTIME_PLAN data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * NEW retrieveOTPlan data for update 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateOTPlan(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ar.overtimeplan.retrieveDataForUpdateOTPlan", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrieveOTPlan data for update  Exception. ", e);
		}
		return result;
	}
	
	/**
	 * NEW update AR_OVERTIME_PLAN data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public Object updateOTPlan(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.update("ar.overtimeplan.updateOTPlan", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update AR_OVERTIME_PLAN data Exception. ", e);
		}
		return result;
	}
	
	/**
	 * delete OTPlan
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteOTPlan(Object parameterObject)throws GlRuntimeException{
		
		try{
			commonSQLMapAdapter.delete("ar.overtimeplan.deleteOTPlan",parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("delete AR_OVERTIME_PLAN data Exception. ",e);
		}
	}
	
}













