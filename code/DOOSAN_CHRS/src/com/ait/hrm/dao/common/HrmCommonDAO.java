/*
 * @(#)HrmCommonDAO.java 1.0 2006-12-20 上午10:30:56
 *  
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.dao.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-20 上午10:30:56
 * @version 1.0
 * 
 */
public class HrmCommonDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(HrmCommonDAO.class);

	public HrmCommonDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	/**
	 * retrieve employee basic information
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveBasicInfo(Object parameterObject) throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQuery("hrm.common.retrieveBasicInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee basic information Exception. ", e);
		}
		return object;
	}
	
	public Object retrieveBasicInfoMap(Object parameterObject) throws GlRuntimeException {

		Object object;
		try {

			object = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveBasicInfoMap", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee basic information Exception. ", e);
		}
		return object;
	}

	/**
	 * retrieve department tree
	 * 
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List retrieveDeptTree(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveDeptTree", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve deptartment tree Exception. ", e);
		}
		return list;
	}
	public List retrieveDeptTreeNo(Object parameterObject) throws GlRuntimeException {
		
		List list;
		try {
			
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveDeptTreeNo", parameterObject);
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve deptartment tree Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve department tree
	 * 
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List retrieveDeptTree2(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveDeptTree2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve deptartment tree Exception. ", e);
		}
		return list;
	}
	
	
	
	public List retrieveDeptTreeNew(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveDeptTreeNew", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve deptartment tree Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListIncumbency(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveEmpListIncumbency", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve incumbency employee list Exception. ", e);
		}
		return list;
	}

	public String getLastTransNO(Object parameterObject) throws GlRuntimeException {
		String str;
		try {

			str = commonSQLMapAdapter.executeQuery("hrm.common.getLastTransNO", parameterObject).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve incumbency employee list Exception. ", e);
		}
		return str;
	}

	public List retrieveEmpListIncumbency(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveEmpListIncumbency", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve incumbency employee list Exception. ", e);
		}
		return list;
	}

	public List retrieveArEmpList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveArEmpList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve incumbency employee list Exception. ", e);
		}
		return list;
	}

	public Object retrieveEmpListIncumbencyCNT(Object parameterObject) throws GlRuntimeException {
		Object obj;
		try {

			obj = commonSQLMapAdapter.executeQuery("hrm.common.retrieveEmpListIncumbencyCNT", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve incumbency employee list Exception. ", e);
		}
		return obj;
	}

	public List retrieveEmpList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveEmpList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee list Exception. ", e);
		}
		return list;
	}
	
	public List retrieveEmpListNotCpnyId(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveEmpNotCpnyIdList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve retrieveEmpNotCpnyIdList list Exception. ", e);
		}
		return list;
	}
	
	public List retrieveaffirmtypeGzList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveGzList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee list Exception. ", e);
		}
		return list;
	}
	
	public List retrieveEmpMapList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveEmpMapList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee list Exception. ", e);
		}
		return list;
	}
	
	public List retrieveSupervisorEmpList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveSupervisorEmpList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve employee list Exception. ", e);
		}
		return list;
	}
	
	
	public List retrievePaEmpList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrievePaEmpList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve pa employee list Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve post list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePostList() throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrievePostList");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("HrmCommanDAO.retrievePostList() Exception. ", e);
		}
		return list;
	}

	public List retrievePostGradeList() throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrievePostGradeList");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("HrmCommanDAO.retrievePostList() Exception. ", e);
		}
		return list;
	}

	public List retrieveGradeLevelList() throws GlRuntimeException {

		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveGradeLevelList");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("HrmCommanDAO.retrievePostList() Exception. ", e);
		}
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getChinesePinYinNameByEmpId(Object parameterObject) throws GlRuntimeException {

		Object name;
		try {
			name = commonSQLMapAdapter.executeQuery("hrm.common.getChinesePinYinNameByEmpId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("HrmCommanDAO.getChineseNameByEmpId() Exception. ", e);
		}
		return name;
	}

	/**
	 * 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getSimpleEmpByEmpId(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.executeQuery("hrm.common.getSimpleEmpByEmpId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("HrmCommanDAO.getChineseNameByEmpId() Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getChineseNameByEmpId(Object parameterObject) throws GlRuntimeException {

		Object name;
		try {
			name = commonSQLMapAdapter.executeQuery("hrm.common.getChineseNameByEmpId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("HrmCommanDAO.getChineseNameByEmpId() Exception. ", e);
		}
		return name;
	}

	public Map getOrderMap() throws GlRuntimeException {
		Map map = new HashMap();
		List UpgradeList;
		//		List SuspendList;
		//		List PluralityList;
		//		List DispatchList;
		//		List ResignList;
		//		List TrainingList;
		//		List PunishMentList;
		//		List HortationList;
		try {
			UpgradeList = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveUpgradeList");
			//			SuspendList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveSuspendList");
			//			PluralityList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrievePluralityList");
			//			DispatchList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveDispatchList");
			//			ResignList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveResignList");
			//			TrainingList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveTrainingList");
			//			PunishMentList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrievePunishMentList");
			//			HortationList=commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveHortationList");

			map.put("UpgradeList", UpgradeList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	public String getDeptid(String adminid) throws GlRuntimeException {
		String deptid = "";
		try {
			deptid = (String) commonSQLMapAdapter.executeQuery("hrm.common.getDeptid", adminid);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Get Deptid Exception. ", e);
		}
		return deptid;
	}

	public int getOtConfirmCnt(SimpleMap map) {
		int count = 0;
		try {
			count = (Integer) commonSQLMapAdapter.executeQuery("hrm.common.getOtConfirmCnt", map);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Get otConfirm Count Exception. ", e);
		}
		return count;
	}

	public int getConfirmCnt(SimpleMap map) {
		int count = 0;
		try {
			count = (Integer) commonSQLMapAdapter.executeQuery("hrm.common.getConfirmCnt", map);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Get otConfirm Count Exception. ", e);
		}
		return count;
	}
	
	
	/**
	 * NEW retrieve SyAffirm employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveSyAffirmList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveSyAffirmList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve SyAffirm employee list Exception. ", e);
		}
		return list;
	}
	
	/**
	 * NEW retrieve Instead Affirm list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveInsteadAffirmList(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveInsteadAffirmList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve Instead Affirm list Exception. ", e);
		}
		return list;
	}
	
	/**
	 * retrieveHrPersonId by cnpyId,empId
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public String retrieveHrPersonId(SimpleMap parameterObject) throws GlRuntimeException {

		String result;
		try {
			Object obj = commonSQLMapAdapter.executeQuery("hrm.common.retrieveHrPersonId", parameterObject);
			result = obj != null ? obj.toString() : "" ;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("RetrieveHrPersonId by cnpyId,empId Exception. ", e);
		}
		return result;
	}
	
	
	/**
	 * retrieve Item Group
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveItemGroup(Object parameterObject) throws GlRuntimeException {

		List list;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti("hrm.common.retrieveItemGroup", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve item group Exception. ", e);
		}
		return list;
	}
	
	/**
	 * retrieve Item Group Cut
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveItemGroupCut(Object parameterObject) throws GlRuntimeException {

		Object obj;
		try {

			obj = commonSQLMapAdapter.executeQuery("hrm.common.retrieveItemGroupCut", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve item group cut Exception. ", e);
		}
		return obj;
	}
	
	
	/**
	 * retrieve Ev Affirm Admin
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public String retrieveEvAffirmAdmin(Object parameterObject) throws GlRuntimeException {

		String empid = "";
		try {

			empid = (String)commonSQLMapAdapter.executeQuery("ev.evaluateapply.retrieveEvAffirmAdmin", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Retrieve ev affirm admin Exception. ", e);
		}
		return empid;
	}
	
	
}
