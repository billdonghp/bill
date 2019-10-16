package com.ait.kpa.dao.payplan;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.dao.postgrade.PostGradeDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.StringUtil;

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
	public List payPlan01Serche(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.payPlan01Serche", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" payPlan01Serche data list. ", e);
		}
		return result ;

	}
	/*get year pay plan*/
	public List getYearPayPlan(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.getYearPayPlan", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getYearPayPlan data list. ", e);
		}
		return result ;

	}
	/*get seq_id pay plan*/
	public String getSeqChar(Object parameterObject) throws GlRuntimeException {
		String result ;
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("pa.payplan.getSeqChar", parameterObject),"z");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getSeqChar data list. ", e);
		}
		return result ;
	}
	
	/*get year for insert*/
	public List getSavePayPlanProdures(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.savePayPlanProdures", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getSavePayPlanProdures data list. ", e);
		}
		return result ;

	}
	
	public void savePayPlanIntoInterfaceTable(Object parameterObject) throws GlRuntimeException {
	
		try {

			commonSQLMapAdapter.insert("pa.payplan.savePayPlanIntoInterfaceTable", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" savePayPlanIntoInterfaceTable data list. ", e);
		}
	

	}
	
	/*get AveragePayPlan Search*/
	public List getAveragePayPlanSearch(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.averagePayPlanSearch", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getAveragePayPlanSearch data list. ", e);
		}
		return result ;

	}
	
	public void saveAveragePayPlan(Object parameterObject) throws GlRuntimeException {
		
		try {

			commonSQLMapAdapter.update("pa.payplan.saveAveragePayPlan", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" saveAveragePayPlan data list. ", e);
		}
	

	}
	
	/*get AveragePayPlan Search for month*/
	public List getAveragePayPlanMonthSearch(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.payplan.averagePayPlanMonthSearch", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getAveragePayPlanMonthSearch data list. ", e);
		}
		return result ;

	}
	/*saveAveragePayPlanMonth*/
	public void saveAveragePayPlanMonth(Object parameterObject) throws GlRuntimeException {
		
		try {

			commonSQLMapAdapter.update("pa.payplan.saveAveragePayPlanMonth", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" saveAveragePayPlanMonth data list. ", e);
		}
	

	}
	
}
