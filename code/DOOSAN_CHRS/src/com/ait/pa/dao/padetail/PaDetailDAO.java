package com.ait.pa.dao.padetail;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.dao.postgrade.PostGradeDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

public class PaDetailDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(PostGradeDAO.class);
	
	public PaDetailDAO(){
		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	public int getF_viewDetailListNumber(Object parameterObject) throws GlRuntimeException {
		int result=0 ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.viewDetail.getF_viewDetailListNumber", parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getF_viewDetailListNumber data list. ", e);
		}
		return result ;

	}
	public List basicList(Object parameterObject) throws GlRuntimeException {
		List result=null ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.viewDetail.basicList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" basicList data list. ", e);
		}
		return result ;

	}
	public List paTypeList(Object parameterObject) throws GlRuntimeException {
		List result=null ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.viewDetail.paTypeList", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" paTypeList data list. ", e);
		}
		return result ;

	}
	
	
	public List basicListForDept(Object parameterObject) throws GlRuntimeException {
		List result=null ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.viewDetail.basicListForDep", parameterObject) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" basicList data list. ", e);
		}
		return result ;

	}
	
	public List getF_viewDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List result=null ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.viewDetail.getF_viewDetailList", parameterObject,currentPage, pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getF_viewDetailList data list. ", e);
		}
		return result ;

	}
	
	public int getB_viewDetailListNumber(Object parameterObject) throws GlRuntimeException {
		int result=0 ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.viewDetail.getB_viewDetailListNumber", parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getB_viewDetailListNumber data list. ", e);
		}
		return result ;

	}
	
	public List getB_viewDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List result=null ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.viewDetail.getB_viewDetailList", parameterObject,currentPage, pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getB_viewDetailList data list. ", e);
		}
		return result ;

	}
	public int getPaBonusDetailListNumber(Object parameterObject) throws GlRuntimeException {
		int result=0 ;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pa.viewDetail.getPaBonusDetailListNumber", parameterObject)) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getPaBonusDetailListNumber data list. ", e);
		}
		return result ;

	}
	
	public List getPaBonusDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List result=null ;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("pa.viewDetail.getPaBonusDetailList", parameterObject,currentPage, pageSize) ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" getPaBonusDetailList data list. ", e);
		}
		return result ;

	}
	
}
