/*
 * @(#)HrmContractDao.java 1.0 2006-12-25 下午04:57:00
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.contract;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Hue (sunhui@ait.net.cn)
 * @Date 2006-12-25 下午04:57:00
 * @version 1.0
 * 
 */
public class HrmContractDao {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	
	public HrmContractDao() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * Searching contract basic information for parteners
	 * @parameter Object parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	
	public List retrieveContractInfo(Object parameterObject)
		throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
			"hrm.contract.retrieveContractInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
		throw new GlRuntimeException(
			"The information Exception when retrieving contract items. ", e);
		}
		return list;
	}
	
	/**
	 * Searching contract information according to some special condition for "合同查询"
	 * @parameter Object parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List searchViewContract(Object parameterObject)
		throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.contract.searchViewContract",
					parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when Retrieving contract items. ", e);
		}
		return list;
	}
	 
	
	public List searchViewContract(Object parameterObject,int currentPage,int pageSize)
	throws GlRuntimeException{  
	List list;
	try{
		list = commonSQLMapAdapter.executeQueryForMulti("hrm.contract.searchViewContract",
				parameterObject,currentPage,pageSize);  
	}catch(Exception e){
		logger.error(e.toString());
		throw new GlRuntimeException(
		"The information of Exception when Retrieving contract items. ", e);
	}
	return list;
}
	
	public Object searchViewContractCnt(Object parameterObject)
	throws GlRuntimeException{  
		Object obj;
	try{
		obj = commonSQLMapAdapter.executeQuery("hrm.contract.searchViewContractCnt",
				parameterObject);
	}catch(Exception e){
		logger.error(e.toString());
		throw new GlRuntimeException(
		"The information of Exception when Retrieving contract items. ", e);
	}
	return obj;
}
	
	/**
	 * Inserting contract items for "添加合同"
	 * @parameter Object parameterObject
	 * @return Object  object
	 * @throws GlRuntimeException
	 */
	public Object addContract(Object parameterObject)
		throws GlRuntimeException{
		Object object;
		try{
			object = commonSQLMapAdapter.insert("hrm.contract.addContract", 
					parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
		throw new GlRuntimeException(
			"The information of Exception when inserting contract items. ", e);	
		}
		return object;
	}
	
	/**
	 * Inserting new contract items for "添加合同"
	 * @parameter List parameterList
	 * @throws GlRuntimeException
	 */
	public void addMultiNewContract(List parameterList)
		throws GlRuntimeException{
		try{
			commonSQLMapAdapter.insertForMulti("hrm.contract.addContract", 
					parameterList);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when Inserting more than one new contract items. ", e);
		}
	}
	
	
	
	
	/**
	 * Inserting new contract items for "检查新合同合法性"
	 * @parameter List parameterList
	 * @throws GlRuntimeException    
	 */
	public String checkNewContractValidity(SimpleMap map)
		throws GlRuntimeException{  
		String empid=""; 
		try{
			Object obj= commonSQLMapAdapter.executeQuery("hrm.contract.checkNewContractValidity",map);
			int num=((Integer)obj).intValue();
			if(num>0)
			empid=map.getString("empID");
		}catch(Exception e){   
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when Inserting more than one new contract items. ", e);
		}
		return empid;
	}                     
	
	/**
	 * Inserting expired contract items for "到期合同"
	 * @parameter List parameterList
	 * @throws GlRuntimeException
	 */
	public void addMultiExpiredContract(List parameterList)
		throws GlRuntimeException{
		try{
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.insertForMulti("hrm.contract.addContract", 
					parameterList, false);
			commonSQLMapAdapter.updateForMulti("hrm.contract.UpdateContractStatus", 
					parameterList, false);  
			commonSQLMapAdapter.commitTransation();
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when Inserting more than one expired contract items. ", e);
		}
	}   
	
	
	
	
	

	/**
	 * Inserting some simple contract items for "添加合同"
	 * @parameter Object parameterObject
	 * @return List  list
	 * @throws GlRuntimeException
	 */
	public void addMultiSimpleContract(List parameterList)
		throws GlRuntimeException{
		try{
			commonSQLMapAdapter.insertForMulti("hrm.contract.addContract", 
					parameterList);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when inserting more than one simple contract items. ", e);
		}
	}
	
	/**
	 * Searching expired contract items for "到期合同"
	 * @parameter Object parameterObject
	 * @return List  list
	 * @throws GlRuntimeException
	 */
	public List searchExpiredContract(Object parameterObject)
		throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.contract.searchExpiredContract", 
					parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
		throw new GlRuntimeException(
			"The information of Exception when searching expired contract items. ", e);
		}
		return list;
	}
	
	
	public List searchExpiredContract(Object parameterObject,int currentPage,int pageSize) 
	throws GlRuntimeException{
	List list;
	try{
		list = commonSQLMapAdapter.executeQueryForMulti("hrm.contract.searchExpiredContract", 
				parameterObject,currentPage,pageSize);  
	}catch(Exception e){
		logger.error(e.toString());
	throw new GlRuntimeException(
		"The information of Exception when searching expired contract items. ", e);
	}
	return list;
	
}  
	
	public Object searchExpiredContractCnt(Object parameterObject) 
	throws GlRuntimeException{
		Object obj;
	try{
		obj = commonSQLMapAdapter.executeQuery("hrm.contract.searchExpiredContractCnt", 
				parameterObject);
	}catch(Exception e){    
		logger.error(e.toString());
	throw new GlRuntimeException(
		"The information of Exception when searching expired contract items. ", e);
	}
	return obj;
}
	
	/**
	 * Searching employees who have no contracts for "合同添加"
	 * @return Object  object
	 * @throws GlRuntimeException
	 */
	public List searchEmpWithoutContract(Object parameterObject,int currentPage,int pageSize)
		throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.contract.searchEmpWithoutContract", 
					parameterObject,currentPage,pageSize);                
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when searching employees who have no contracts items. ", e);
		}
		return list;
	}
	
	
	public Object searchEmpWithoutContactCNT(Object parameterObject)
	throws GlRuntimeException{
	Object obj;
	try{
		obj = commonSQLMapAdapter.executeQuery("hrm.contract.searchEmpWithoutContactCNT", 
				parameterObject);
	}catch(Exception e){
		logger.error(e.toString());
		throw new GlRuntimeException(
		"The information of Exception when searching employees who have no contracts items. ", e);
	}
	return obj;
}
	
	
	
	
	/**
	 * Searching contract according to some condition then create a excel file for "合同报表"
	 * @parameter Object parameterObject
	 * @return List  list
	 * @throws GlRuntimeException
	 */
	public List searchContractForOutExcel(Object parameterObject)
		throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.contract.searchContractForOutExcel",
					parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException(
			"The information of Exception when searching contract for create a excel file. ", e);
		}
		return list;
	}
}

