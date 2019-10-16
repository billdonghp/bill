package com.ait.pa.dao.payplan;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.dao.postgrade.PostGradeDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-01
 * 
 */
public class PayPlanDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PostGradeDAO.class);
	
	public PayPlanDAO(){
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	
	/*get month pay plan*/
	public List payPlanSerche(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.payPlanSerche", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" payPlanSerche data list. ", e);
		}
		return result ;

	}
	public List ImportExcelProcess(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.ImportExcelProcess", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" ImportExcelProcess data list. ", e);
		}
		return result ;

	}
	public List ImportExcelResult(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.ImportExcelResult", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" ImportExcelResult data list. ", e);
		}
		return result ;

	}
	
	
	/*insert into  Py_Pay_Plan_Detail table  from temp table t_Py_Pay_Plan_Detail*/
	public void payPlanSave(List parameterList) throws GlRuntimeException {
	
		try {			
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.startBatch();

			Object parameterObject;
			for (int i = 0; i < parameterList.size(); i++) {
				parameterObject = parameterList.get(i);
				commonSQLMapAdapter.update("pa.payplan.payPlanSave", parameterObject) ;
				commonSQLMapAdapter.delete("pa.payplan.payPlandelete", parameterObject) ;
			}

			commonSQLMapAdapter.executeBatch();
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" payPlanSave data list. ", e);
			
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		

	}
	
	public List paHousingFundList(Object parameterObject , int currentPage , int pageSize) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.paHousingFundList", parameterObject,currentPage, pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" paHousingFundList data list. ", e);
		}
		return result ;

	}
	
	public List paHousingFundListExcel(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.paHousingFundList",parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" paHousingFundList data list. ", e);
		}
		return result ;

	}
	
	public int housingCount(Object parameterObject) throws GlRuntimeException {
		int result ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.payplan.paHousingFCount", parameterObject).toString()) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" paHousingFCount data count. ", e);
		}
		return result ;

	}
	
	public void inertPaHouing(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.insert("pa.payplan.inertPaHouing", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" pa.payplan.inertPaHouing. ", e);
		}

	}
	
	public void delPaHouing(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.delete("pa.payplan.delPaHouing", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" pa.payplan.delPaHouing. ", e);
		}

	}
	
	public void updatePaHouing(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("pa.payplan.updatePaHouing", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" pa.payplan.inertPaHouing. ", e);
		}

	}
	
	public List aHouingList(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.aHouingList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" aHouingList data list. ", e);
		}
		return result ;

	}
	
}
