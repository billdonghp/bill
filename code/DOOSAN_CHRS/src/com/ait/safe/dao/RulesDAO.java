package com.ait.safe.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

public class RulesDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(RulesDAO.class);

	public RulesDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List DisplayInformation(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.DisplayInformation", parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("DisplayInformation by paging Exception. ", e);
		}
		return result;
	}
	public List DisplayInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.DisplayInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("DisplayInformation by paging Exception. ", e);
		}
		return result;
	}
	public int DisplayInformationNumber(Object parameterObject) throws GlRuntimeException {

		int result=0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.rules.DisplayInformationNumber", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("DisplayInformationNumber by paging Exception. ", e);
		}
		return result;
	}
	
	public List menuInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.menuInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("menuInformation by paging Exception. ", e);
		}
		return result;
	}
	public List menuInformation_1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.menuInformation_1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("menuInformation by paging Exception. ", e);
		}
		return result;
	}

	public List AllMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.AllMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("AllMenu by paging Exception. ", e);
		}
		return result;
	}
	
	public Object AddFile(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("safe.rules.AddFile", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("AddFile by paging Exception. ", e);
		}
		return result;
	}
	
	public List AllUpdateInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.AllUpdateInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("AllUpdateInformation by paging Exception. ", e);
		}
		return result;
	}
	public List SingleMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.SingleMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("SingleMenu by paging Exception. ", e);
		}
		return result;
	}
	
	public Object UpdateSingleFileInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			
			result = commonSQLMapAdapter.update("safe.rules.UpdateSingleFileInformation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("UpdateSingleFileInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object UpdateSingleFileInformation1(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			
			result = commonSQLMapAdapter.update("safe.rules.UpdateSingleFileInformation1", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("UpdateSingleFileInformation1 by paging Exception. ", e);
		}
		return result;
	}
	
	public String getFileloadpath(Object parameterObject) throws GlRuntimeException {

		String result;
		try {
			result = commonSQLMapAdapter.executeQuery("safe.rules.getFileloadpath", parameterObject).toString();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getFileloadpath by paging Exception. ", e);
		}
		return result;
	}
	
	public Object DeleteSingleFileInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("safe.rules.DeleteFileInformation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("DeleteSingleFileInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List AllMenuInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.AllMenuInformation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("AllMenuInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object addmenu(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			
			result = commonSQLMapAdapter.insert("safe.rules.addmenu", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addmenu by paging Exception. ", e);
		}
		return result;
	}
	
	public Object topMenu(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			
			result = commonSQLMapAdapter.executeQuery("safe.rules.topMenu", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("topMenu by paging Exception. ", e);
		}
		return result;
	}
	
	public List getFileInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.getFileInfo", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getFileInfo by paging Exception. ", e);
		}
		return result;
	}
	
	public List menuName(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.menuName", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("menuName by paging Exception. ", e);
		}
		return result;
	}
	
	public List singleMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.singleMenu", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("singleMenu by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updatemenu(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			
			result = commonSQLMapAdapter.update("safe.rules.updatemenu", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updatemenu by paging Exception. ", e);
		}
		return result;
	}
	
	public Object DeleteSingleMenuInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("safe.rules.DeleteSingleMenuInformation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("DeleteSingleMenuInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List parentCode(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.parentCode", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("parentCode by paging Exception. ", e);
		}
		return result;
	}
	
	public List SuperTopMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.SuperTopMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("SuperTopMenu by paging Exception. ", e);
		}
		return result;
	}
	
	public List nextMenu(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.nextMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("nextMenu by paging Exception. ", e);
		}
		return result;
	}
	
	public int yesornoSubset(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.rules.yesornoSubset", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("yesornoSubset by paging Exception. ", e);
		}
		return result;
	}
	
	public List fileview(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.fileview", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("fileview by paging Exception. ", e);
		}
		return result;
	}
	
	public List getSystemName(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.rules.getSystemName", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSystemName by paging Exception. ", e);
		}
		return result;
	}
	
	public String getMyhrefaddress(Object parameterObject) throws GlRuntimeException {

		String result;
		try {
			result = commonSQLMapAdapter.executeQuery("safe.rules.getMyhrefaddress", parameterObject).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getMyhrefaddress by paging Exception. ", e);
		}
		return result;
	}
	
	public int getSeq(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.rules.getSeq", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getMyhrefaddress by paging Exception. ", e);
		}
		return result;
	}
}
