package com.ait.ga.dao.certificate;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;

public class CertificateDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	private static final Logger logger = Logger.getLogger(CertificateDAO.class);
	
	public CertificateDAO(){
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * select certificate list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectCertificateList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list ;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.certificate.selectCertificateList",parameterObject,currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select certificate list by paging data Exception.",e);
		}
		return list ;
	}
	
	/**
	 * select certificate list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectCertificateList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.certificate.selectCertificateList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select certificate list data Exception. ",e);
		}
		return list ;
	}
	
	/**
	 * select certificate count
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectCertificateCount(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.certificate.selectCertificateCount", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select certificate count data Exception. ",e);
		}
		return obj;
	}
	
	/**
	 * insert certificate 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertCertificate(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.insert("ga.certificate.insertCertificate", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert certificate data Exception. ",e);
		}
	}
	
	/**
	 * update certificate 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateCertificate(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.certificate.updateCertificate", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update certificate data Excepion.",e);
		}
	}
	
	/**
	 * delete deleteCertificate
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCertificate(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.delete("ga.certificate.deleteCertificate",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete certificate data Exception.",e);
		}
	}
	
	/**
	 * insert certificate novation
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertCertificateNovation(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.insert("ga.certificate.insertCertificateNovation",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert certificate novation data Exception.",e);
		}
	}
	
	/**
	 * insert certificate info 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertCertificateInfo(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.startTransaction();
			this.insertCertificate(parameterObject);
			this.insertCertificateNovation(parameterObject);
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error("insert certificate info error." + e);
			throw new GlRuntimeException("insert certificate info error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * get certificate name 
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getCertificateName(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.certificate.getCertificateName", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get certificate name data Exception.",e);
		}
		return list;
	}
	
	/**
	 * delete certificate histroy
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCertificateHistroy(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.delete("ga.certificate.deleteCertificateHistroy", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete certificate histroy data Exception.",e);
		}
	}
	
	/**
	 * delete certificate all data
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCertificateAllData(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.startTransaction();
			this.deleteCertificate(parameterObject);
			this.deleteCertificateHistroy(parameterObject);
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error("delete certificate all data error." + e);
			throw new GlRuntimeException("delete certificate all data error.", e);
		} finally {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transaction Exception. ", e);
			}
		}
	}
	
	/**
	 * insert certification novation for update
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertCertificateNovationForUpdate(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.insert("ga.certificate.insertCertificateNovationForUpdate", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert certification novation for data update Exception.",e);
		}
	}
	
	/**
	 * get certificate update history
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getCertificateUpdateHistory(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.certificate.getCertificateUpdateHistory", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get certificate update history data Exception. ",e);
		}
		return list;
	}
}
