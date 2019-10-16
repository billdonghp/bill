package com.ait.gm.dao.woodProductsdao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.EateryDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-5-20
 * 
 */
public class WoodProductsDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EateryDAO.class);

	public WoodProductsDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List getDocumentListForConfigList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.woodProducts.DocumentListForConfigList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDocumentListForConfigList Exception. ", e);
		}
		return list;
	}
	public List getWoodProductsSet(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.woodProducts.getWoodProductsSet", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDocumentListForConfigList Exception. ", e);
		}
		return list;
	}
	public List getwoodProductionList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.woodProducts.getwoodProductionList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getwoodProductionList Exception. ", e);
		}
		return list;
	}
	
	public Object getWoodProductsObject(String seqId) throws GlRuntimeException {
		Object result = null;
		try {
			result = commonSQLMapAdapter.executeQuery("gm.woodProducts.getWoodProductsObject", seqId);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getWoodProductsObject Exception. ", e);
		}
		return result;
	}
	
	public List ProductsImageView(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.woodProducts.ProductsImageView", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("ProductsImageView Exception. ", e);
		}
		return list;
	}
	

	public Object getDocumentConfigInfo(Object parameterObject) throws GlRuntimeException {
		Object result = null;
		try {
			result = commonSQLMapAdapter.executeQuery("gm.woodProducts.DocumentListForConfigList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get DocumenConfigInfo Exception. ", e);
		}
		return result;
	}

	public int getDocumentListForConfigNumber(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.woodProducts.DocumentListForConfigNumber", parameterObject).toString());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDocumentListForConfigNumber Exception. ", e);
		}
		return temp;
	}

	public void updateGa_woodproducts_apply(List parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.updateForMulti("gm.woodProducts.updateGa_woodproducts_apply", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getWoodProductsList Exception. ", e);
		}		
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
	
	public int  getwoodProductionListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.woodProducts.getwoodProductionListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getWwoodProductionListNumber data Exception. ", e);
		}
		return temp;
	}
	public List  getwoodProductionList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("gm.woodProducts.getwoodProductionList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getwoodProductionList data Exception. ", e);
		}
		return temp;
	}
	public void  addWoodProduction(Object parameterObject) throws GlRuntimeException {
	
		try {
			commonSQLMapAdapter.update("gm.woodProducts.addWoodProduction", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"addWoodProduction data Exception. ", e);
		}
		
	}
	public void  updateWoodProduction(Object parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.update("gm.woodProducts.updateWoodProduction", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"updateWoodProduction data Exception. ", e);
		}
		
	}
	public void  deleteWoodProduction(Object parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.update("gm.woodProducts.deleteWoodProduction", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteWoodProduction data Exception. ", e);
		}
		
	}
	
	
	
}
