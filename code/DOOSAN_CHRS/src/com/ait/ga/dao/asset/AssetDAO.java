package com.ait.ga.dao.asset;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-10
 * @version 1.0
 */
public class AssetDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(AssetDAO.class);

	public AssetDAO() {
		
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	public int  getAssetListNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.asset.selectAssetListNumber", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"selectAssetListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List  getAssetList(Object parameterObject,int  currentPage, int pageSize) throws GlRuntimeException {
		List  temp;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.asset.selectAssetList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"selectAssetList data Exception. ", e);
		}
		return temp;
	}
	
	public List getAssetList(Object parameObject) throws GlRuntimeException{
		List temp;
		try{
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.asset.selectAssetList",parameObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"selectAssetList data Execption. ",e);
		}
		return temp;
	}
	
	public List getAssetNameList(Object parameterObject)throws GlRuntimeException{
		List temp;
		try{
			temp=commonSQLMapAdapter.executeQueryForMulti("ga.asset.selectAssetNameList",parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"selectAssetNameList data Execption",e);
		}
		return temp;
	}

	public void updateAsset(Object parameterObject) throws GlRuntimeException {
		try{
			commonSQLMapAdapter.update("ga.asset.updateAsset", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"updateAsset data Execption. ",e);
		}
	}
	
	public void insertAsset(Object parameterObject) throws GlRuntimeException{
		try{
			commonSQLMapAdapter.insert("ga.asset.insertAsset", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insertAsset data Execption. ",e);
		}
	}
	
	public void deleteAsset(Object parameterObject) throws GlRuntimeException{
		try{
			commonSQLMapAdapter.update("ga.asset.deleteAsset", parameterObject);			
		}catch(Exception e){			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteAsset data Exception. ",e);
		}
	}
	
	public int selectAssetTest(Object parameterObject)throws GlRuntimeException{
		int temp;
		try{
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.asset.selectAssetTest", parameterObject));
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"select asset test data Exception. ",e);
		}
		return temp;
	}

}



