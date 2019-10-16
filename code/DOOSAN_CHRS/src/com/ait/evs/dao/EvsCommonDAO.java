/*
 * @(#)EvsCommonDAO.java 1.0 2007-5-17 下午02:59:58
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-5-17 下午02:59:58
 * @version 1.0
 * 
 */
public class EvsCommonDAO {

	
	private String adminId = ""; // 当前登录者ID
	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	private static ServiceLocator serviceLocator = null;
	
	private static final Logger logger = Logger.getLogger(EvsCommonDAO.class);

	public EvsCommonDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}
	
	public void setAdminId(String adminId) {
		Logger.getLogger(getClass()).debug("adminId Set to : " + adminId);
		this.adminId = adminId;
	}
	/**
	 * 在技能类型查询CODE_ID，CODE_name  工种的   添加页面
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List<SimpleMap> getEvscodeStypeList(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvscodeStypeList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	
	 
	
	
	/**
	 * 在技能类型查询CODE_ID，CODE_name  工种的   添加页面
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvsopacodeList(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvsopacodeList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	/**
	 * 在Line查询CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List<SimpleMap> getEvscodeList3(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvscodeList3", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	
	public List<SimpleMap> getEvscodeList5(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvscodeList5", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	public List<SimpleMap> getEvscodeList6(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvscodeList6", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	/**
	 * 技能类型  查找
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> getStypeEvscodeList(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getStypeEvscodeList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	
	public void deleteLine(String parameterObject)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.delete("evs.common.deleteLine", parameterObject);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void deleteZyzgdj(String parameterObject)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.delete("evs.common.deleteZyzgdj", parameterObject);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void deleteGjjsdj(String parameterObject)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.delete("evs.common.deleteGjjsdj", parameterObject);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void insertLine(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertLine",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void insertLiner(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertLiner",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void deleteSetupcode(String parameterObject)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.delete("evs.common.deleteSetupcode", parameterObject);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	/**
	 * 技能类型  保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	
	public void insertStype(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertStype",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	
	/**
	 * 职业资格等级  保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	
	public void insertZyzgd(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertZyzgd",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void insertGjjsdj(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertGjjsdj",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	/**
	 * 作业类型  保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	
	public void insertTypeopa(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertTypeopa",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	
	/**
	 * 作业类型  更新
	 * 
	 * @param parameterObject
	 * @return List
	 */
	
	public void updateTypeopa(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.updateTypeopa",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	
	
	/**
	 * 在Ircraft查询CODE_ID，CODE_name  工种的  和  LIne 的
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List<SimpleMap> getEvsIrcraftcodeList(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvsIrcraftcodeList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	/**
	 * 添加机种
	 * @param parameterList
	 * @throws GlRuntimeException
	 */
	public void insertIrcraft(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertIrcraft",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	/**
	 * 在工种更新CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object getEvscodeIrcraftList0(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvscodeIrcraftList0", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	/**
	 * 在机种更新CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object getEvscodeIrcraftList(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvscodeIrcraftList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	public List<SimpleMap> getEvscodeIrcraftList2(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvscodeIrcraftList2", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	public List<SimpleMap> getEvscodeIrcraftList3(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvscodeIrcraftList3", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	
	/**
	 * retrieve evaluate result list
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List retrieveEvaluateResultList(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.retrieveEvaluateResultList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"Retrieve evaluate result list Exception. ", e);
		}
		return result;
	}
	
	/**
	 * 在Line更新CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object getEvscodeList4(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvscodeList4", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	/**
	 * 技能等级list
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object getEvsgxjndjList(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvsgxjndjList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	public Object getEvsgxjsdjList(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvsgxjsdjList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	/**
	 * 在评价进行时查询评价人员数据
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvsempcodeList(Object parameterObject)
	throws GlRuntimeException {

		Object result;
	try {
	
		result = commonSQLMapAdapter.executeQuery(
				"evs.common.getEvsempcodeList", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return result;
	}
	/**
	 * 在评价进行时查询工种查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsCraftList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsCraftList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	public List getEvsYearIDList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsYearIDList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsYearIDList() Exception. ", e);
		}
		return list;
	}
	/**
	 * 在评价进行时查询LIne查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsLineList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsLineList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	/**
	 * 在评价进行时查询技能类型查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsSkitypeList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsSkitypeList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	/**
	 * 在评价进行时查询机种查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsAircraftList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsAircraftList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	/**
	 * 在评价进行时查询工序查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsProcesstList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsProcesstList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	/**
	 * 在评价进行时查询作业内容查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsJobcontentList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsJobcontentList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	/**
	 * 在评价进行时查询作业类型查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsTypetionList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsTypetionList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsCraftList() Exception. ", e);
		}
		return list;
	}
	
	/**
	 * 在评价进行时查询技能等级
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsSkilelist() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsSkilelist", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsSkilelist() Exception. ", e);
		}
		return list;
	}
	
	/**
	 * 在评价进行时查询职业资格
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsQualificationList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsQualificationList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsQualificationList() Exception. ", e);
		}
		return list;
	}
	
	/**
	 * 在评价进行时查询培训目标
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsPurposeList() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsPurposeList", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsPurposeList() Exception. ", e);
		}
		return list;
	}
	
	/**
	 * 在导出报表时查询技能类型
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsSkiltypelist() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsSkiltypelist", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsSkiltypelist() Exception. ", e);
		}
		return list;
	}
	
	/**
	 * 在导出报表时查询作业类型
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsTypeoationlist() throws GlRuntimeException {

		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getEvsTypeoationlist", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsSkiltypelist() Exception. ", e);
		}
		return list;
	}
	
	
	
	/**
	 * 添加机种
	 * @param parameterList
	 * @throws GlRuntimeException
	 */
	public void insertJinxing(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.insertForMulti("evs.common.insertJinxing",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	public void updateJinxing(List parameterList)
	throws GlRuntimeException {
			try {
				commonSQLMapAdapter.updateForMulti("evs.common.updateJinxing",
						parameterList);
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException(
						"The information of Exception when Inserting more than one expired contract items. ",
						e);
			}
	}
	/**
	 * 在评价进行时查询评价人员数据
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsCountEmp(Object parameterObject)
	throws GlRuntimeException {

		List list = null;
	try {
	
		list = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getEvsCountEmp", parameterObject);
	
	} catch (Exception e) {
		logger.error(e.toString());
		throw new GlRuntimeException(
				"Retrieve evaluate result list Exception. ", e);
	}
	return list;
	}
	
	public List regularlySentEvMailInfoList() throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.regularlySentEvMailInfoList");
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentEvMailInfoList Exception. ", e);
		}
		return object;
	}
	public List regularlySentEvMydMailInfoList() throws GlRuntimeException {

		List object=null;
		try {
		
			object = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.regularlySentEvMydMailInfoList");
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentEvMydMailInfoList Exception. ", e);
		}
		return object;
	}
	public void insertRegularlySentEvMail(SimpleMap parameterObject) throws GlRuntimeException {

		try {
		   commonSQLMapAdapter.insert(
					"evs.common.insertSentEvMail", parameterObject);
		
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"regularlySentArAbnormalInfoList Exception. ", e);
		}
	}
	public List retrieveZyzgdjModifyAffirmList(Object parameterObject) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.retrieveZyzgdjModifyAffirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List retrieveSetupcodeAffirmList(Object parameterObject) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.retrieveSetupcodeAffirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List retrieveGjjsAffirmList(Object parameterObject) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.retrieveGjjsAffirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List retrieveGxModifyAffirmList(Object parameterObject) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.retrieveGxModifyAffirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	/**
	 * 评价成绩提交决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam
	 *            []
	 * @param AffirmType
	 *            申请类型 无
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doSetupcodeAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		List<EssAffirmParam> paramList = new ArrayList<EssAffirmParam>();
		for (int i = 0; i < params.length; i++) {
			EssAffirmParam p = params[i];

			result = doSetupAffirm(p, flag, paramList);
			
		}
		
		return result;
	}
	/**
	 * 工匠技师提交决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam
	 *            []
	 * @param AffirmType
	 *            申请类型 无
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doGjjsAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		List<EssAffirmParam> paramList = new ArrayList<EssAffirmParam>();
		for (int i = 0; i < params.length; i++) {
			EssAffirmParam p = params[i];

			result = doGjjssAffirm(p, flag, paramList);
			
		}
		
		return result;
	}
	/**
	 * 职业资格等级修改功能决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam
	 *            []
	 * @param AffirmType
	 *            申请类型 无
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doZyezgdjModifyAffirmList(EssAffirmParam[] params, int flag) {
		int result = 0;
		List<EssAffirmParam> paramList = new ArrayList<EssAffirmParam>();
		for (int i = 0; i < params.length; i++) {
			EssAffirmParam p = params[i];

			result = doArModifyAffirm(p, flag, paramList);
			
		}
		
		return result;
	}
	/**
	 * 工序难度系数修改功能决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam
	 *            []
	 * @param AffirmType
	 *            申请类型 无
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doGxModifyAffirmList(EssAffirmParam[] params, int flag) {
		int result = 0;
		List<EssAffirmParam> paramList = new ArrayList<EssAffirmParam>();
		for (int i = 0; i < params.length; i++) {
			EssAffirmParam p = params[i];

			result = doGxModifyAffirm(p, flag, paramList);
			
		}
		
		return result;
	}
	/**
	 * 进行评价成绩提交 决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	private int doGjjssAffirm(EssAffirmParam p, int flag,
			List<EssAffirmParam> paramList) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		int result = 0;
		int affirmNo = p.getAffirmNo();
		int applyNo = p.getApplyNo();
		String codeflag =p.getFromDate();
		adminId = p.getPerson_id();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE EVS_CODE_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ? ,AFFIRM_REMARKS  = ? WHERE ACTIVITY ='1' AND AR_DETAIL_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, p.getRemark());
			pstmt.setInt(4, affirmNo);
			result = pstmt.executeUpdate();

			if (result == 1 && flag == 2) {
				this.doGjjsConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& this.isArModifyLastAffirmor(affirmNo)) {
				this.doGjjsConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& !this.isArModifyLastAffirmor(affirmNo)) {
				this.doGjjsConfirm(applyNo, flag,codeflag, false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}
	/**
	 * 进行工匠技师提交 决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	private int doSetupAffirm(EssAffirmParam p, int flag,
			List<EssAffirmParam> paramList) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		int result = 0;
		int affirmNo = p.getAffirmNo();
		int applyNo = p.getApplyNo();
		String codeflag =p.getFromDate();
		adminId = p.getPerson_id();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE EVS_CODE_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ? ,AFFIRM_REMARKS  = ? WHERE ACTIVITY ='1' AND AR_DETAIL_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, p.getRemark());
			pstmt.setInt(4, affirmNo);
			result = pstmt.executeUpdate();

			if (result == 1 && flag == 2) {
				this.doSetUPConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& this.isArModifyLastAffirmor(affirmNo)) {
				this.doSetUPConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& !this.isArModifyLastAffirmor(affirmNo)) {
				this.doSetUPConfirm(applyNo, flag,codeflag, false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}
	/**
	 * 进行职业资格等级功能 决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	private int doArModifyAffirm(EssAffirmParam p, int flag,
			List<EssAffirmParam> paramList) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		int result = 0;
		int affirmNo = p.getAffirmNo();
		int applyNo = p.getApplyNo();
		String codeflag =p.getFromDate();
		adminId = p.getPerson_id();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE EVS_CODE_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ? ,AFFIRM_REMARKS  = ? WHERE ACTIVITY ='1' AND AR_DETAIL_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, p.getRemark());
			pstmt.setInt(4, affirmNo);
			result = pstmt.executeUpdate();

			if (result == 1 && flag == 2) {
				this.doArDetailConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& this.isArModifyLastAffirmor(affirmNo)) {
				this.doArDetailConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& !this.isArModifyLastAffirmor(affirmNo)) {
				this.doArDetailConfirm(applyNo, flag,codeflag, false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}
	/**
	 * 工序难度系数修改 决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	private int doGxModifyAffirm(EssAffirmParam p, int flag,
			List<EssAffirmParam> paramList) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		int result = 0;
		int affirmNo = p.getAffirmNo();
		int applyNo = p.getApplyNo();
		String codeflag = p.getFromDate();
		adminId = p.getPerson_id();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE EVS_CODE_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ? ,AFFIRM_REMARKS  = ? WHERE ACTIVITY ='1' AND AR_DETAIL_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, p.getRemark());
			pstmt.setInt(4, affirmNo);
			result = pstmt.executeUpdate();

			if (result == 1 && flag == 2) {
				this.doGxDetailConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& this.isArModifyLastAffirmor(affirmNo)) {
				this.doGxDetailConfirm(applyNo, flag,codeflag, true);
			}
			if (result == 1 && flag == 1
					&& !this.isArModifyLastAffirmor(affirmNo)) {
				this.doGxDetailConfirm(applyNo, flag,codeflag, false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}
	/**
	 * 进行DICC评价成绩决裁
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果 1 通过; 2 否决
	 */
	public int doSetUPConfirm(int affirmNo, int flag,String codeflag, boolean LastAffirmor) {
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		sql = "UPDATE evs_setupcode T SET T.STATUS = ? ,FLAG = ? WHERE T.SETUPCODENO = ? ";
		String sql1 = " update evs_setupcode set SUMSCORE = (select  trim(TO_CHAR(sum(to_number(nvl(SKILLSCORE,0))),'90.0'))  from evs_setupcode  WHERE "
			+ 	" EVS_EMPID =  (select EVS_EMPID from evs_setupcode where  SETUPCODENO = ?) "
			+ 	" AND CRAFT =  (select CRAFT from evs_setupcode where  SETUPCODENO = ?) and status = '3' and purpose ='yes' )"
			+ 	" 	where EVS_EMPID = (select EVS_EMPID from evs_setupcode where  SETUPCODENO = ?) "
			+ 	" AND CRAFT =  (select CRAFT from evs_setupcode where  SETUPCODENO = ?) and status = '3' and purpose ='yes'";
		String sql2 = " update evs_setupcode set SUMJCCOUNT = (select count(1)  from evs_setupcode  WHERE "
			+ 	" EVS_EMPID =  (select EVS_EMPID from evs_setupcode where  SETUPCODENO = ?)  "
			+ 	" AND CRAFT =  (select CRAFT from evs_setupcode where  SETUPCODENO = ?) and status = '3' and purpose ='yes')"
			+" 	where EVS_EMPID = (select EVS_EMPID from evs_setupcode where  SETUPCODENO = ?) "
			+ 	" AND CRAFT =  (select CRAFT from evs_setupcode where  SETUPCODENO = ?) and status = '3' and purpose ='yes'";
		String sql3 = " update evs_setupcode set ZYZGDJLEVEL = (select dengji from EVS_GXJNDJ   where code_flag ='CT'   "
			+ 	" and code_id = (select craft from evs_setupcode where  SETUPCODENO = ?) "
			+   " and  djjffrom <= (select SUMSCORE from evs_setupcode where  SETUPCODENO = ?) "
			+   " and djjfto >= (select SUMSCORE from evs_setupcode where  SETUPCODENO = ?) )"
			+" 	where EVS_EMPID = (select EVS_EMPID from evs_setupcode where  SETUPCODENO = ?) "
			+ 	" AND CRAFT =  (select CRAFT from evs_setupcode where  SETUPCODENO = ?) and status = '3' and purpose ='yes'";
		try {
			if (flag == 2) {

				Logger.getLogger(getClass()).debug(sql);
				pst = conn.prepareStatement(sql);
				pst.setInt(1, 1);
				pst.setInt(2, flag);
				pst.setInt(3, affirmNo);
				result1 = pst.executeUpdate();

			}
			if (flag == 1 && LastAffirmor) {

				if(codeflag!=null&&codeflag.equals("delete")){
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();
				}else{
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 3);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();
				}
				
				
				Logger.getLogger(getClass()).debug(sql1);
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, affirmNo);
				pstmt1.setInt(2, affirmNo);

				result1 = pstmt1.executeUpdate();
				
				Logger.getLogger(getClass()).debug(sql2);
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, affirmNo);
				pstmt2.setInt(2, affirmNo);

				result2 = pstmt2.executeUpdate();
				
				Logger.getLogger(getClass()).debug(sql3);
				pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, affirmNo);
				pstmt3.setInt(2, affirmNo);
				pstmt3.setInt(3, affirmNo);
				pstmt3.setInt(4, affirmNo);
				
				result3 = pstmt3.executeUpdate();

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	///工匠技师
	public int doGjjsConfirm(int affirmNo, int flag,String codeflag, boolean LastAffirmor) {
		int result = 0;
	
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		sql = "UPDATE EVS_GXJSDJ T SET T.STATUS = ? ,FLAG = ? WHERE T.CODE_NO = ? ";
	
		try {
			if (flag == 2) {

				Logger.getLogger(getClass()).debug(sql);
				pst = conn.prepareStatement(sql);
				pst.setInt(1, 1);
				pst.setInt(2, flag);
				pst.setInt(3, affirmNo);
				result = pst.executeUpdate();

			}
			if (flag == 1 && LastAffirmor) {

				
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 3);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();
				
								

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	/**
	 * 职业资格等级决裁
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果 1 通过; 2 否决
	 */
	public int doArDetailConfirm(int affirmNo, int flag,String codeflag, boolean LastAffirmor) {
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		String sql1 = "";
		sql = "UPDATE EVS_GXJNDJ T SET T.STATUS = ? ,FLAG = ? WHERE T.CODE_NO = ? ";
		sql1 = "UPDATE EVS_GXJNDJ_BACK T SET T.STATUS = ?,FLAG = ? WHERE T.STATUS ='1' AND T.CODE_NO = ? ";
		String sql2 = "UPDATE EVS_GXJNDJ T SET (T.DJJFFROM, T.DJJFTO,  T.UPDATE_DATE,  T.FLAG) = "
				+ "(SELECT B.DJJFFROM, B.DJJFTO, SYSDATE, 2 FROM EVS_GXJNDJ_BACK B WHERE B.CODE_NO = ? AND B.ACTIVITY = 1 ) WHERE T.CODE_NO = ?  ";

		try {
			if (flag == 2) {

				if(codeflag!=null&&codeflag.equals("delete")){
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);
					result = pstmt.executeUpdate();
				}else{
					Logger.getLogger(getClass()).debug(sql2);
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, affirmNo);
					pstmt2.setInt(2, affirmNo);
					result2 = pstmt2.executeUpdate();
	
					Logger.getLogger(getClass()).debug(sql1);
					pst = conn.prepareStatement(sql1);
					pst.setInt(1, 1);
					pst.setInt(2, flag);
					pst.setInt(3, affirmNo);
					result1 = pst.executeUpdate();
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, affirmNo);
					pstmt.setInt(2, affirmNo);
					result = pstmt.executeUpdate();
				}
			}
			if (flag == 1 && LastAffirmor) {

				
				if(codeflag!=null&&codeflag.equals("delete")){
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();
					
				}else{
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();
					
					Logger.getLogger(getClass()).debug(sql1);
					pst = conn.prepareStatement(sql1);
					pst.setInt(1, 1);
					pst.setInt(2, flag);
					pst.setInt(3, affirmNo);

					result1 = pst.executeUpdate();
				}
				

				

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	/**
	 * 进行DICC
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果 1 通过; 2 否决
	 */
	public int doGxDetailConfirm(int affirmNo, int flag,String codeflag, boolean LastAffirmor) {
		int result2 = 0;
		int result = 0;
		int result1 = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		String sql1 = "";
		sql = "UPDATE EVS_CODE T SET T.STATUS = ? ,FLAG = ? WHERE T.CODE_NO = ? ";
		sql1 = "UPDATE EVS_CODE_BACK T SET T.STATUS = ?,FLAG = ? WHERE T.STATUS ='1' AND T.CODE_NO = ? ";
		String sql2 = "UPDATE EVS_CODE T SET (T.DESCRIPTIO,  T.UPDATE_DATE,  T.FLAG) = "
				+ "(SELECT B.DESCRIPTIO, SYSDATE, 2 FROM AR_DETAIL_BACK B WHERE B.AR_PK_NO = ? AND B.FLAG = 0 ) WHERE T.PK_NO = ? AND T.FLAG = 0 ";

		try {
			if (flag == 2) {

				if(codeflag!=null&&codeflag.equals("delete")){
										
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);
					result = pstmt.executeUpdate();
				}else{
					Logger.getLogger(getClass()).debug(sql2);
					pstmt2 = conn.prepareStatement(sql2);
					pstmt2.setInt(1, affirmNo);
					pstmt2.setInt(2, affirmNo);
					result2 = pstmt2.executeUpdate();

					Logger.getLogger(getClass()).debug(sql1);
					pst = conn.prepareStatement(sql1);
					pst.setInt(1, 1);
					pst.setInt(2, flag);
					pst.setInt(3, affirmNo);
					result1 = pst.executeUpdate();
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);
					result = pstmt.executeUpdate();
				}
				

			}
			if (flag == 1 && LastAffirmor) {

				if(codeflag!=null&&codeflag.equals("delete")){
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();
				}else{
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, flag);
					pstmt.setInt(3, affirmNo);

					result = pstmt.executeUpdate();

					Logger.getLogger(getClass()).debug(sql1);
					pst = conn.prepareStatement(sql1);
					pst.setInt(1, 1);
					pst.setInt(2, flag);
					pst.setInt(3, affirmNo);

					result1 = pst.executeUpdate();
				}
				

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	/**
	 * 判断DICC职业修改决裁是否是最后一级决裁者
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 */
	private boolean isArModifyLastAffirmor(int affirmNo) {
		Logger.getLogger(getClass()).debug(
				"Checking if this is the Ar Modify Last Affirmor........");
		boolean result = false;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// String sql = "SELECT A.APPLY_NO " +
		// "FROM ESS_AFFIRM A, ESS_AFFIRM B " +
		// "WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL " +
		// "AND A.APPLY_NO(+) = B.APPLY_NO " +
		// "AND A.APPLY_TYPE(+) = B.APPLY_TYPE " + "AND B.ESS_AFFIRM_NO = ?";
		String sql = "SELECT A.AR_DETAIL_NO FROM EVS_CODE_AFFIRM A, EVS_CODE_AFFIRM B WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL AND A.AR_DETAIL_NO(+) = B.AR_DETAIL_NO  AND B.AR_DETAIL_AFFIRM_NO = ?";

		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, affirmNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					Logger.getLogger(getClass()).debug("Is Last Affirmor !");
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	public List getZyzgdjModifyNextAffirmor(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getZyzgdjModifyNextAffirmor", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getSetupcodeNextAffirmor(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getSetupcodeNextAffirmor", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getGjjsNextAffirmor(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGjjsNextAffirmor", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getGxModifyNextAffirmor(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGxModifyNextAffirmor", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getArModifyApplyNoForMail(Object parameterObject)
	throws GlRuntimeException {
		List result = null;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getArModifyApplyNoForMail", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getSetupcodeApplyNoForMail(Object parameterObject)
	throws GlRuntimeException {
		List result = null;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getSetupcodeApplyNoForMail", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getGjjsApplyNoForMail(Object parameterObject)
	throws GlRuntimeException {
		List result = null;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGjjsApplyNoForMail", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getGxModifyApplyNoForMail(Object parameterObject)
	throws GlRuntimeException {
		List result = null;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGxModifyApplyNoForMail", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
		}
	public List getLeaveApplyor(Object parameterObject)
		throws GlRuntimeException {
	
		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getLeaveApplyor", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List getSetupApplyor(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getSetupApplyor", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
}
	public List getGjjsApplyor(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.getGjjsApplyor", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
}
	public List getGxApplyor(Object parameterObject)
		throws GlRuntimeException {
	
		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGxApplyor", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List getLeaveApplyNoCreateBy(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getLeaveApplyNoCreateBy", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List getSetupApplyNoCreateBy(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getSetupApplyNoCreateBy", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List getGjjsApplyNoCreateBy(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGjjsApplyNoCreateBy", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List getGxApplyNoCreateBy(Object parameterObject)
	throws GlRuntimeException {

		List result;
		try {
		
			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.getGxApplyNoCreateBy", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object setToEmail(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("evs.common.setToEmail",
					parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}
	public List applyLeaveResult(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.applyLeaveResult", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
	}
	public List applyGxResult(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.applyGxResult", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
	}
	public List applyLeaveResult1(Object parameterObject)
		throws GlRuntimeException {
	
	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.applyLeaveResult1", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
	}
	public List applySetupcodeResult(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.applySetupcodeResult", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
	}
	public List applyArModifyResult(Object parameterObject)
		throws GlRuntimeException {
	
	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.applyArModifyResult", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
	}
	public List applyGxModifyResult(Object parameterObject)
	throws GlRuntimeException {

	List result;
	try {
	
		result = commonSQLMapAdapter.executeQueryForMulti(
				"evs.common.applyGxModifyResult", parameterObject);
	
	} catch (Exception e) {
	
		logger.error(e.toString());
		throw new GlRuntimeException(" Exception. ", e);
	}
	return result;
	}
	public List retrieveJnpjRecordList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"evs.common.retrieveJnpjRecordList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve atendance record list Exception. ", e);
		}
		return result;
	}
	public String getDeptkeByEmpId(String empId) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		serviceLocator = ServiceLocator.getInstance();
		try {
			conn = serviceLocator.getConnection();
			pstmt = conn.prepareStatement("SELECT get_deptke_name(?) AS deptke FROM DUAL ");
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			else
				return "ERROR";
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	public String getDeptzhiByEmpId(String empId) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		serviceLocator = ServiceLocator.getInstance();
		try {
			conn = serviceLocator.getConnection();
			pstmt = conn.prepareStatement("SELECT get_deptzhi_name(?) AS deptke FROM DUAL ");
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			else
				return "ERROR";
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}
	public String getDeptzuByEmpId(String empId) throws ServiceLocatorException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		serviceLocator = ServiceLocator.getInstance();
		try {
			conn = serviceLocator.getConnection();
			pstmt = conn.prepareStatement("SELECT get_deptzu_name(?) AS deptke FROM DUAL ");
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			else
				return "ERROR";
		} catch (ServiceLocatorException e) {
			logger.error(e.toString(), e);
			throw e;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw e;
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	public List retrieveSumCore(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("evs.common.getSumCore", list);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EvsCommanDAO.getEvsPurposeList() Exception. ", e);
		}
		return list;
	}
}
