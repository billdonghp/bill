package com.ait.ga.dao.visit;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

public class ListRecordDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public ListRecordDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List empAllList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visit.empAllList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("emp record list by paging Exception. ", e);
		}
		return result;
	}
	
	public List visitRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.visit.visitRecordList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit record list by paging Exception. ", e);
		}
		return result;
	}

	/**
	 * visit record list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List visitRecordList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.visit.visitRecordList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit record list Exception. ", e);
		}
		return result;
	}

	/**
	 * visit record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object visitRecordListCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.visit.visitRecordListCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit record list count Exception. ", e);
		}
		return result;
	}
	
	public void visitRecordAdd(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.visit.visitRecordAdd", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Insert visit record data Exception. ", e);
		}
	}

	public void visitRecordUpdate(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("ga.visit.visitRecordUpdate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Update visit record data Exception. ", e);
		}
	}
	
	public Object cardApplicationCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ga.visit.cardApplicationCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("cardApplicationCount count Exception. ", e);
		}
		return result;
	}
	
	public List cardApplication(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.visit.cardApplication", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("cardApplication Exception. ", e);
		}
		return result;
	}
	
	public List getAllCardStatus(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.visit.getAllCardStatus", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllCardStatus Exception. ", e);
		}
		return result;
	}
	
	public List getCardEndDate() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.visit.getCardEndDate");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getCardEndDate Exception. ", e);
		}
		return result;
	}
	
	public void updateCardStatus(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("ga.visit.updateCardStatus", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateCardStatus Exception. ", e);
		}
	}
	
	public List getFirstAffirmEmail(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result=commonSQLMapAdapter.executeQueryForMulti("ga.visit.getFirstAffirmEmail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getFirstAffirmEmail Exception. ", e);
		}
		return result ; 
	}
	
	public List getFirstAffirmEmail1(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result=commonSQLMapAdapter.executeQueryForMulti("ga.visit.getFirstAffirmEmail1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getFirstAffirmEmail1 Exception. ", e);
		}
		return result ; 
	}
	
	public void AddMaintenance(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("ga.visit.AddMaintenance", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("AddMaintenance Exception. ", e);
		}
	}
	
	public List troubleManger(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List result;
		try {

			result=commonSQLMapAdapter.executeQueryForMulti("ga.visit.troubleManger",  parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("troubleManger Exception. ", e);
		}
		return result ; 
	}
	public int troubleMangerInt(Object parameterObject) throws GlRuntimeException {
		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.visit.troubleMangerInt", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("troubleMangerInt Exception. ", e);
		}
		return result ; 
	}
	
	public void UpdatevoitureManger(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("ga.visit.UpdatevoitureManger", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("UpdateMaintenance Exception. ", e);
		}
	}
	
	public List UpdateViewVoitureManger(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result=commonSQLMapAdapter.executeQueryForMulti("ga.visit.UpdateViewVoitureManger",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("UpdateViewVoitureManger Exception. ", e);
		}
		return result ; 
	}
}
