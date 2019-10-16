package com.ait.ga.dao.woodproducts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.dao.GaDAOFactory;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-6-12
 * 
 */
public class WoodProductsDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public WoodProductsDAO() {
		
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	public String  getapplyemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.woodproducts.getapplyemail", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getapplyemail data Exception. ", e);
		}
		return temp;
	}
	
	public String  getapplyemail2(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.woodproducts.getapplyemail2", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getapplyemail2 data Exception. ", e);
		}
		return temp;
	}
	
	public String  getupaffrimemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.woodproducts.getupaffrimemail", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getupaffrimemail data Exception. ", e);
		}
		return temp;
	}
	public int  getUpAffrimNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.woodproducts.getUpAffrimNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getUpAffrimNumber data Exception. ", e);
		}
		return temp;
	}
	public void  oingAffirm(Object parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.update("ga.woodproducts.oingAffirm", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"oingAffirm data Exception. ", e);
		}
		
	}
	public void  updateApplyInfo(Object parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.update("ga.woodproducts.updateApplyInfo", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"updateApplyInfo data Exception. ", e);
		}
		
	}
	
	public int  getWoodProductsAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.woodproducts.woodProductsAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWoodProductsAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getWoodProductsAffirmList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.woodproducts.woodProductsAffirmList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWoodProductsAffirmList data Exception. ", e);
		}
		return temp;
	}
	public int  getWoodProductsAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.woodproducts.woodProductsAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWoodProductsAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getWoodProductsAffirmInfoList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.woodproducts.woodProductsAffirmInfoList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWoodProductsAffirmInfoList data Exception. ", e);
		}
		return temp;
	}
	public List  getWoodProductsAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.woodproducts.woodProductsAffirmorList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWoodProductsAffirmorList data Exception. ", e);
		}
		return temp;
	}
	public List  getWoodProductsList(Object parameterObject) throws GlRuntimeException {
		List  temp=null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.woodproducts.woodProductsList",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWoodProductsList data Exception. ", e);
		}
		return temp;
	}
	
}
