/*
 * @(#)PaDailyItemDAO.java 1.0 2007-10-8 下午09:23:51
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.kpa.dao.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2007-10-8 下午09:23:51
 * @version 1.0
 * 
 */
public class PaReportDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	
	private static ServiceLocator services;

	private static final Logger logger = Logger.getLogger(PaReportDAO.class);

	public PaReportDAO() {
		try {
			services = ServiceLocator.getInstance();
			commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
		} catch (ServiceLocatorException ex) {
		}

		//commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	/**
	 * retrieve pa detail month data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetailMonth(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.RetrievePaDetailMonth", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve pa detail month sum data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetailMonth_SUM(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.RetrievePaDetailMonth_SUM", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export pa detail month sum data list. ", e);
		}
		return result ;
	}
	
	
	/**
	 * retrieve personal tax month data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePersonalTaxMonthly(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrievePersonalTaxMonthly", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month data list. ", e);
		}
		return result ;
	}
	
	
	/**
	 * retrieve pa comepete month data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaCompelte(Object parameterObject) throws GlRuntimeException {
		Object obj ;
		try {  

			obj = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaCompelte", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month data list. ", e);
		}
		return obj ;
	}
	
	public List retrievePayDetail(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrievePayDetail", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month data list. ", e);
		}
		return result ;
	}
	
	public List retrieveInsDetailMonth(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrieveInsDetailMonth", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month data list. ", e);
		}
		return result ;
	}
	
	public List retrieveInsDetailMonth_SUM(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrieveInsDetailMonth_SUM", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month sum data list. ", e);
		}
		return result ;
	}
	
	
	
	public Object retrieveInsuranceData(Object parameterObject) throws GlRuntimeException {
		Object obj ;
		try {

			obj = commonSQLMapAdapter.executeQuery("kpa.report.RetrieveInsuranceData", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail month data list. ", e);
		}
		return obj ;
	}
	
	public List retrieveInsuranceData_simple(Object parameterObject) throws GlRuntimeException {
		List obj ;
		try {

			obj = commonSQLMapAdapter.executeQueryForMulti("kpa.report.RetrieveInsuranceData_simple", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa insurance month simple data list. ", e);
		}
		return obj ;
	}
	
	public List retrieveInsuranceData_simple_per(Object parameterObject) throws GlRuntimeException {
		List obj ;
		try {

			obj = commonSQLMapAdapter.executeQueryForMulti("kpa.report.RetrieveInsuranceData_simple_per", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa insurance month simple data per list. ", e);
		}
		return obj ;
	}
	
	public List retrieveInsuranceData_simple_com(Object parameterObject) throws GlRuntimeException {
		List obj ;
		try {

			obj = commonSQLMapAdapter.executeQueryForMulti("kpa.report.RetrieveInsuranceData_simple_com", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa insurance month simple data com list. ", e);
		}
		return obj ;
	}
	
	/**
	 * retrieve t_pa_result column list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaResultColumnList(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrieveTPaResultColumnList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve t_pa_result column list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve t_pa_bonus_result column list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaBonusResultColumnList(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrieveTPaBonusResultColumnList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve t_pa_bonus_result column list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve pay slip data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaySlip(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.RetrievePaySlip", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pay slip data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export dept pay insurance data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePayInsurance(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrievePayInsurance", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve dept pay insurance data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export t_pa_result data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaResultDataList(SimpleMap parameterObject) throws GlRuntimeException {
		List result = new ArrayList() ;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		// SELECT $sqlColumn2$ FROM T_PA_RESULT
		String sql = " SELECT " + parameterObject.getString("sqlColumn2") + " FROM T_KPA_RESULT " ;
		
		logger.debug("sql = " + sql) ;
		
		try {
			
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List dateList = null ;
			
			while (rs.next()) {
				
				dateList = new ArrayList() ;
				// 从2开始，因为有个无用数据
				for (int i = 2 ; i <= parameterObject.getInt("columnSize") ; ++ i )
				{
					dateList.add(rs.getString(i)) ;
				}
				result.add(dateList) ;			
			}

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export t_pa_result data list. ", e);
		}
		finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return result ;
	}
	
	/**
	 * retrieve export t_pa_bonus_result data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
/*	public List retrieveTPaBonusResultDataList(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrieveTPaBonusResultDataList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export t_pa_bonus_result data list. ", e);
		}
		return result ;
	}*/
	
	
	public List retrieveTPaBonusResultDataList(SimpleMap parameterObject) throws GlRuntimeException {
		List result = new ArrayList() ;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		// SELECT $sqlColumn2$ FROM T_PA_RESULT
		String sql = " SELECT " + parameterObject.getString("sqlColumn2") + " FROM t_kpa_bonus_result " ;
		
		logger.debug("sql = " + sql) ;
		
		try {
			
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List dateList = null ;
			
			while (rs.next()) {
				
				dateList = new ArrayList() ;
				// 从2开始，因为有个无用数据
				for (int i = 2 ; i <= parameterObject.getInt("columnSize") ; ++ i )
				{
					dateList.add(rs.getString(i)) ;
				}
				result.add(dateList) ;			
			}

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export t_pa_result data list. ", e);
		}
		finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return result ;
	}
	
	/**
	 * retrieve export peopleware data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaPeopleware(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaPeopleware", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export peopleware data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export peopleware end data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaPeopleware_end(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaPeopleware_end", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export peopleware end data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export peopleware direct data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaPeopleware_direct(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaPeopleware_direct", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export peopleware direct data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export peopleware indirect data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaPeopleware_indirect(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaPeopleware_indirect", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export peopleware indirect data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve work area data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrieveWorkAreaData(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrieveWorkAreaData", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve work area data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export pa daily head data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaDaily_head(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaDaily_head", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export peopleware head data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve export pa daily data list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaDaily(Object parameterObject) throws GlRuntimeException {
		Object result ;
		try {

			result = commonSQLMapAdapter.executeQuery("kpa.report.retrievePaDaily", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve export peopleware data list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve pa detail dept list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetail_DeptList(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrievePaDetail_DeptList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail dept list. ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve pa detail section dept list 
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetail_DeptList_section(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrievePaDetail_DeptList_section", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail section dept list . ", e);
		}
		return result ;
	}
	
	/**
	 * retrieve pa detail dept list by parent_dept_id 
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetail_DeptList_parent_dept_id(Object parameterObject) throws GlRuntimeException {
		List result ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("kpa.report.retrievePaDetail_DeptList_parent_dept_id", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" retrieve pa detail dept list by parent_dept_id . ", e);
		}
		return result ;
	}
}
