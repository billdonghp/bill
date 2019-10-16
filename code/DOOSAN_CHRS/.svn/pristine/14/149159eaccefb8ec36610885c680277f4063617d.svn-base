package com.ait.ess.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.bean.EssCardApplyBean;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssMatchBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.bean.PageControl;
import com.ait.ess.dao.AffairLeaveDAO;
import com.ait.ess.dao.EssArDAO;
import com.ait.ess.dao.EssStrategyIntf;
import com.ait.ess.dao.common.EssCommonDAO;
import com.ait.jdbc.core.SQLResult;
import com.ait.util.DataAccessException;

public class EssServices {

	//private HrEntityDAO hrEntityDAO;

	private EssArDAO essArDAO;

	private AffairLeaveDAO affairLeaveDAO;

	private EssCommonDAO essCommonDAO;

	public EssServices() {
		//hrEntityDAO = new HrEntityDAO();
		essArDAO = new EssArDAO();
		affairLeaveDAO = new AffairLeaveDAO();
		essCommonDAO = new EssCommonDAO();
	}
	
	/**
	 * retrieve Leave Affirm Level
	 * 
	 * @param parameterObject
	 * @return int
	 */
	public int retrieveLeaveAffirmLevel(Object parameterObject)
	{
		int result = 0 ;
		try {
			result = essArDAO.retrieveLeaveAffirmLevel(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/**
	 * retrieve Overtime Affirm Level
	 * 
	 * @param parameterObject
	 * @return int
	 */
	public int retrieveOvertimeAffirmLevel(Object parameterObject,Object essOverTime)
	{
		int result = 0 ;
		try {
			result = essArDAO.retrieveOvertimeAffirmLevel(parameterObject,essOverTime) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/**
	 * retrieve apply Leave Length
	 * 
	 * @param parameterObject
	 * @return String
	 */
	public String retrieveApplyLeaveLength(Object parameterObject)
	{
		String result = "0";
		try {
			result = essArDAO.retrieveApplyLeaveLength(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	/**
	 * retrieve applyor dept list
	 * 
	 * @param parameterObject
	 * @return list
	 */
	public List retrieveApplyorDeptList(Object parameterObject)
	{
		List list=null;
		try {
			list = essArDAO.retrieveApplyorDeptList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	/*
	 * new
	 */
	public int retrieveApplyPersonCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveApplyPersonCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public int retrieveApplyOTTopLimitPersonCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveApplyOTTopLimitPersonCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public int retrieveApplyOTPersonCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveApplyOTPersonCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveApplyOTPerson(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrieveApplyOTPerson(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public Object retrieveApplyOTPerson(Object parameterObject)
	{
		Object result = null ;
		try {
			result = essArDAO.retrieveApplyOTPerson(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public String retrieveApplyArMonth(Object parameterObject)
	{
		String result = "" ;
		try {
			result = essArDAO.retrieveApplyArMonth(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveApplyLeavePerson(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrieveApplyLeavePerson(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public Object retrieveApplyLeavePerson(Object parameterObject)
	{
		Object result = null ;
		try {
			result = essArDAO.retrieveApplyLeavePerson(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;	
	}
	
	/*
	 * new
	 */
	public List retrieveApplyOTTopLimitPerson(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrieveApplyOTTopLimitPerson(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public Object retrieveApplyOTTopLimitPerson(Object parameterObject)
	{
		Object result = null ;
		try {
			result = essArDAO.retrieveApplyOTTopLimitPerson(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;	
	}
	
	/*
	 * new
	 */
	public int retrieveApplyAffirmNextFlag(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveApplyAffirmNextFlag(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveOtTopLimitAffirmList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrieveOtTopLimitAffirmList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public int retrieveOtTopLimitAffirmListCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveOtTopLimitAffirmListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveOtAffirmList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrieveOtAffirmList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public List retrieveOtAffirmList(Object parameterObject)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrieveOtAffirmList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public List retrieveAffirmDeptList(Object parameterObject)
	{
		List result = new ArrayList();
		try {
			result = essArDAO.retrieveAffirmDeptList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public int retrieveOtAffirmListCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveOtAffirmListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveLeaveAffirmList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = null;
		try {
			result = essArDAO.retrieveLeaveAffirmList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveArModifyAffirmList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = null;
		try {
			result = essArDAO.retrieveArModifyAffirmList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrieveLeaveAffirmList(Object parameterObject)
	{
		List result = null;
		try {
			result = essArDAO.retrieveLeaveAffirmList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public int retrieveLeaveAffirmListCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveLeaveAffirmListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public int retrieveArModifyAffirmListCnt(Object parameterObject)
	{
		int result = 0;
		try {
			result = essArDAO.retrieveArModifyAffirmListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public List retrievePersonalOtTopLimitInfoList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrievePersonalOtTopLimitInfoList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public int retrievePersonalOtTopLimitInfoListCnt(Object parameterObject)
	{
		int result = 0 ;
		try {
			result = essArDAO.retrievePersonalOtTopLimitInfoListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public List retrievePersonalOtInfoList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrievePersonalOtInfoList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	public List retrievePersonalOtInfoList(Object parameterObject)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrievePersonalOtInfoList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public int retrievePersonalOtInfoListCnt(Object parameterObject)
	{
		int result = 0 ;
		try {
			result = essArDAO.retrievePersonalOtInfoListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public List retrievePersonalLeaveInfoList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrievePersonalLeaveInfoList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public List retrievePersonalLeaveInfoList(Object parameterObject)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrievePersonalLeaveInfoList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public int retrievePersonalLeaveInfoListCnt(Object parameterObject)
	{
		int result = 0 ;
		try {
			result = essArDAO.retrievePersonalLeaveInfoListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	
	/*
	 * new
	 */
	public List retrievePersonalArModifyInfoList(Object parameterObject,int currentPage, int pageSize)
	{
		List result = new ArrayList() ;
		try {
			result = essArDAO.retrievePersonalArModifyInfoList(parameterObject,currentPage,pageSize) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	/*
	 * new
	 */
	public int retrievePersonalArModifyInfoListCnt(Object parameterObject)
	{
		int result = 0 ;
		try {
			result = essArDAO.retrievePersonalArModifyInfoListCnt(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	
	/*
	 * new
	 */
	public Object retrieveInsteadAffirm(Object parameterObject)
	{
		Object result = null ;
		try {
			result = essArDAO.retrieveInsteadAffirm(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	
	/*
	 * new
	 */
	public Object retrieveInsteadAffirmAppoint(Object parameterObject)
	{
		Object result = null ;
		try {
			result = essArDAO.retrieveInsteadAffirmAppoint(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	/*
	 * new
	 */
	public List retrieveApplyOTTopLimitMonthList(Object parameterObject)
	{
		List result = null ;
		try {
			result = essArDAO.retrieveApplyOTTopLimitMonthList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;	
	}
	
	public void doOtTopLimitAffirm(Object parameterObject) {
		try {
			essArDAO.doOtTopLimitAffirm(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}
	
	public int delApply(int applyNo, String applyType) {
		int result = 0;
		try {
			result = essArDAO.delApply(applyNo, applyType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	
	public int delApplyH9Info(int applyNo) {
		int result = 0;
		try {
			result = essArDAO.delApplyH9Info(applyNo);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}

	public HashMap doOtApply(EssOverTimeBean essOverTimeBean) {
		HashMap result = new HashMap();
		try {
			result = essArDAO.doOtApply(essOverTimeBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			result.put("errcode", new Integer(-1)); // 未知锟斤拷锟斤拷,锟斤拷锟斤拷
		}
		return result;
	}
	
	
	public int getCurrentMaxApplyNo() {	
		int seq=0; 
		try {
			seq = essArDAO.getCurrentMaxApplyNo();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return seq;
	}
	
	/**
	 * 批量进行加班上限申请
	 * 
	 * @param list
	 * @return HashMap
	 */
	public HashMap doOtTopLimitApplyByBatch(List parameterObject) throws GlRuntimeException {

		return essArDAO.doOtTopLimitApplyByBatch(parameterObject);
	}

	/**
	 * 批量进行加班申请
	 * 
	 * @param list
	 * @return HashMap
	 */
	public HashMap doOtApplyByBatch(List parameterObject) throws GlRuntimeException {

		return essArDAO.doOtApplyByBatch(parameterObject);
	}

	public HashMap doLeaveApply(EssLeaveBean essLeaveBean) {
		HashMap result = new HashMap();
		try {
			essArDAO.setAdminId(essLeaveBean.getCreatedBy()) ;
			result = essArDAO.doLeaveApply(essLeaveBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			result.put("errcode", new Integer(-1)); // 未知锟斤拷锟斤拷,锟斤拷锟斤拷
		}
		return result;
	}
	
	public void doLeaveApplyId(EssLeaveBean essLeaveBean) {
		
		try {
			essArDAO.doLeaveApplyId(essLeaveBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			//result.put("errcode", new Integer(-1)); // 
		}
		
	}
	
	public void saveLeaveH9Info(EssLeaveBean essLeaveBean) {
		try {
			essArDAO.saveLeaveH9Info(essLeaveBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}

	/**
	 * 批量进行休假申请
	 * 
	 * @param list
	 * @return HashMap
	 */
	public HashMap doLeaveApplyByBatch(List parameterObject) throws GlRuntimeException {

		return essArDAO.doLeaveApplyByBatch(parameterObject);
	}

	public HashMap doCardApply(EssCardApplyBean essCardAppBean) {

		HashMap result = new HashMap();
		try {
			result = essArDAO.doCardApply(essCardAppBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			result.put("errcode", new Integer(-1)); // 未知锟斤拷锟斤拷,锟斤拷锟斤拷
		}
		return result;
	}

	public HashMap doMatchApply(EssMatchBean essMatchBean) {
		HashMap result = new HashMap();
		try {
			result = essArDAO.doMatchApply(essMatchBean);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			result.put("errcode", new Integer(-1)); // 未知锟斤拷锟斤拷,锟斤拷锟斤拷
		}
		return result;
	}

	public List getOtInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getOtInfoList(sDate, eDate, key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getOtInfoList(String sDate, String eDate, String key, String deptID) {
		List list = new ArrayList();
		try {
			list = essArDAO.getOtInfoList(sDate, eDate, key, deptID);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getOtInfoList(String sDate, String eDate, String key,int qryType, String deptID, String otType,String affirmId) {
		List list = new ArrayList();
		try {
			list = essArDAO.getOtInfoList(sDate, eDate, key,qryType, deptID, otType ,affirmId);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getPersonalOtInfoList(String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getPersonalOtInfoList(key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getLeaveInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getLeaveInfoList(sDate, eDate, key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getLeaveInfoList(String sDate, String eDate, String key, String deptID) {
		List list = new ArrayList();
		try {
			list = essArDAO.getLeaveInfoList(sDate, eDate, key, deptID);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getLeaveInfoList(String sDate, String eDate, String key,int qryType, String deptID,int maxLevel, String ltype,String affirmId,String item_no) {
		List list = new ArrayList();
		try {
			list = essArDAO.getLeaveInfoList(sDate, eDate, key,qryType, deptID, maxLevel, ltype,affirmId,item_no);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list; 
	}
	
	public List getPersonalLeaveInfoList(String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getPersonalLeaveInfoList(key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getPersonalLeaveInfoList(String key,String item_no) {
		List list = new ArrayList();
		try {
			list = essArDAO.getPersonalLeaveInfoList(key,item_no);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getEvectionInfoList(String sDate, String eDate, String key, String deptID) {
		List list = new ArrayList();
		try {
			list = essArDAO.getEvectionInfoList(sDate, eDate, key, deptID);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getPersonalEvectionInfoList( String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getPersonalEvectionInfoList( key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getTrainingInfoList(String sDate, String eDate, String key, String deptID) {
		List list = new ArrayList();
		try {
			list = essArDAO.getTrainingInfoList(sDate, eDate, key, deptID);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getPersonalTrainingInfoList(String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getPersonalTrainingInfoList(key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getCardInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getCardInfoList(sDate, eDate, key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getMatchInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		try {
			list = essArDAO.getMatchInfoList(sDate, eDate, key);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getOtAffirmList(String sDate, String eDate, String key, int qryType) {
		List list = new ArrayList();
		try {
			list = essArDAO.getOtAffirmList(sDate, eDate, key, qryType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getOtAffirmList(String sDate, String eDate, String key, int qryType, String deptID, int maxLevel) {
		List list = new ArrayList();
		try {
			list = essArDAO.getOtAffirmList(sDate, eDate, key, qryType, deptID, maxLevel);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public Object getOtAffirmListAll(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtAffirmListAll(parameterObject);
	}

	public List getOtConfirmList(String sDate, String eDate, String key, int qryType) {
		return essArDAO.getOtConfirmList(sDate, eDate, key, qryType);
	}

	public List getOtConfirmList(String sDate, String eDate, String key, int qryType, String deptID) {
		return essArDAO.getOtConfirmList(sDate, eDate, key, qryType, deptID);
	}

	public Object getOtConfirmListFirstDate(Object parameterObject) {
		return essArDAO.getOtConfirmListFirstDate(parameterObject);
	}
	public Object getOtViewListFirstDate(Object parameterObject) {
		return essArDAO.getOtViewListFirstDate(parameterObject);
	}
	
	public Object getConfirmListFirstDate(Object parameterObject) {
		return essArDAO.getConfirmListFirstDate(parameterObject);
	}

	public List getLeaveAffirmList(String sDate, String eDate, String key, int qryType) {
		List list = new ArrayList();
		try {
			list = essArDAO.getLeaveAffirmList(sDate, eDate, key, qryType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getEvectionAffirmList(String sDate, String eDate, String key, int qryType) {
		List list = new ArrayList();
		try {
			list = essArDAO.getEvectionAffirmList(sDate, eDate, key, qryType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getTrainingAffirmList(String sDate, String eDate, String key, int qryType) {
		List list = new ArrayList();
		try {
			list = essArDAO.getTrainingAffirmList(sDate, eDate, key, qryType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getLeaveAffirmList(String sDate, String eDate, String key, int qryType, String deptID, int maxLevel) {
		List list = new ArrayList();
		try {
			list = essArDAO.getLeaveAffirmList(sDate, eDate, key, qryType, deptID, maxLevel);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getEvectionAffirmList(String sDate, String eDate, String key, int qryType, String deptID, int maxLevel) {
		List list = new ArrayList();
		try {
			list = essArDAO.getEvectionAffirmList(sDate, eDate, key, qryType, deptID, maxLevel);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getTrainingAffirmList(String sDate, String eDate, String key, int qryType, String deptID, int maxLevel) {
		List list = new ArrayList();
		try {
			list = essArDAO.getTrainingAffirmList(sDate, eDate, key, qryType, deptID, maxLevel);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public Object getLeaveDateForAffirm(Object parameterObject) {
		Object object = new Object();
		try {
			object = essArDAO.getLeaveDateForAffirm(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return object;
	}

	public Object getEvectionDateForAffirm(Object parameterObject) {
		Object object = new Object();
		try {
			object = essArDAO.getEvectionDateForAffirm(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return object;
	}

	public Object getTrainingDateForAffirm(Object parameterObject) {
		Object object = new Object();
		try {
			object = essArDAO.getTrainingDateForAffirm(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return object;
	}

	public Object getOverTimeDateForAffirm(Object parameterObject) {
		Object object = new Object();
		try {
			object = essArDAO.getOverTimeDateForAffirm(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return object;
	}

	public List getEvectionAffirmList(String sDate, String eDate, String key, int qryType, String deptID) {
		List list = new ArrayList();
		try {
			list = essArDAO.getEvectionAffirmList(sDate, eDate, key, qryType, deptID);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getTrainingAffirmList(String sDate, String eDate, String key, int qryType, String deptID) {
		List list = new ArrayList();
		try {
			list = essArDAO.getTrainingAffirmList(sDate, eDate, key, qryType, deptID);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getCardAffirmList(String sDate, String eDate, String key, int qryType) {

		List list = new ArrayList();
		try {
			list = essArDAO.getCardAffirmList(sDate, eDate, key, qryType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getLeaveConfirmList(String sDate, String eDate, String key, int qryType) {
		return essArDAO.getLeaveConfirmList(sDate, eDate, key, qryType);
	}

	public List getLeaveConfirmList(String sDate, String eDate, String key, int qryType, String deptID) {
		return essArDAO.getLeaveConfirmList(sDate, eDate, key, qryType, deptID);
	}

	public List getEvectionConfirmList(String sDate, String eDate, String key, int qryType, String deptID) {
		return essArDAO.getEvectionConfirmList(sDate, eDate, key, qryType, deptID);
	}

	public List getTrainingConfirmList(String sDate, String eDate, String key, int qryType, String deptID) {
		return essArDAO.getTrainingConfirmList(sDate, eDate, key, qryType, deptID);
	}

	public List getCardConfirmList(String sDate, String eDate, String key, int qryType) {
		return essArDAO.getCardConfirmList(sDate, eDate, key, qryType);
	}

	public List getMatchAffirmList(String sDate, String eDate, String key, int qryType) {
		List list = new ArrayList();
		try {
			list = essArDAO.getMatchAffirmList(sDate, eDate, key, qryType);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}

	public List getMatchConfirmList(String sDate, String eDate, String key, int qryType) {
		return essArDAO.getMatchConfirmList(sDate, eDate, key, qryType);
	}

	public int doAffirm(EssAffirmParam[] params, String applyType, int flag) {
		int result = 0;
		try {
			result = essArDAO.doAffirmList(params, applyType, flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	//考勤修改 决裁
	public int doArModifyAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		try {
			result = essArDAO.doArModifyAffirmList(params,flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}

	public int doUpdateOtApplyAfterConfirm(EssOverTimeBean otBean) {
		return essArDAO.doUpdateOtApplyAfterConfirm(otBean);
	}

	public int doConfirm(int otno, String applyType, int flag, String remark) {
		return essArDAO.doConfirm(otno, applyType, flag, remark);
	}
	
	public int doAttendanceConfirm(int otno, int flag) {
		return essArDAO.doAttendanceConfirm(otno,flag);
	}
	
	public int doArDetailConfirm(int otno, int flag) {
		return essArDAO.doArDetailConfirm(otno,flag ,true);
	}

	public PageControl getPageControl() {
		return essArDAO.getPageControl();
	}

	/**
	 * 取锟斤拷指锟斤拷指锟斤拷员锟斤拷指锟斤拷锟斤拷锟斤拷锟斤拷锟酵的撅拷锟斤拷锟斤拷斜锟?
	 * 
	 * @param empId
	 *            员锟斤拷锟斤拷
	 * @param applyType
	 *            锟斤拷锟斤拷锟斤拷锟斤拷 : "OtApply"/"LeaveApply"/"OtTopLimitApply"
	 */
	public List getAffirmorList(String empId, String applyType, int affirmMaxLevel,boolean isContainsOwner) {
		List list = new ArrayList();
		try {
			list = essArDAO.getAffirmorList2(empId, applyType, affirmMaxLevel,isContainsOwner);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	//获取勤态信息  特殊判断
	public List getAffirmorListDIY(String empId, String applyType, int affirmMaxLevel,boolean isContainsOwner) {
		List list = new ArrayList();
		try {
			list = essArDAO.getAffirmorList(empId, applyType, affirmMaxLevel,isContainsOwner);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	

	public void setPageControl(PageControl pageControl) {
		try {
			essArDAO.setPageControl(pageControl);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}

	public void setAdminId(String adminId) {
		try {
			essArDAO.setAdminId(adminId);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
	}

	public SQLResult getAffairLeave(String adminId, Integer year, String searchcontent) {
		return affairLeaveDAO.getAffairLeave(adminId, year, searchcontent);
	}

	public SQLResult getAffairLeave(String adminId, Integer year) {
		return affairLeaveDAO.getAffairLeave(adminId, year);
	}

	/**
	 * 取得该法人具体的ESS申请的配置
	 * 
	 * @return
	 */
	public EssStrategyIntf getEssStrategyIntf() {

		return essArDAO.getEssStrategyIntf();
	}

	public String getTurnHolidays(String empId, String nameplate) {
		try {
			return essArDAO.getTurnHolidays(empId, nameplate);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
			return "";
		}
	}

	/**
	 * 取得员工的最大决裁级别
	 * 
	 * @param EmpId
	 * @return
	 * @throws DataAccessException
	 */
	public String getLogOnPersonAffirmMaxLevel(String EmpId) throws DataAccessException {

		return essArDAO.getLogOnPersonAffirmMaxLevel(EmpId);
	}

	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyOTDetail(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.retrieveApplyOTDetail(parameterObject);
	}
	
	
	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object getReportType(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.getReportType(parameterObject);
	}
	
	
	public Object getLeaveReportType(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.getLeaveReportType(parameterObject);
	}
	
	
	public List getOverTimeApplerList(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.getOverTimeApplerList(parameterObject);
	}
	
	public List getLeaveApplerList(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.getLeaveApplerList(parameterObject);
	}

	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyOTAffirmor(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.retrieveApplyOTAffirmor(parameterObject);
	}

	/**
	 * retrieve apply overtime detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyAffirmor(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.retrieveApplyAffirmor(parameterObject);
	}

	/**
	 * retrieve apply leave detail
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public Object retrieveApplyLeaveDetail(Object parameterObject) throws GlRuntimeException {

		return essCommonDAO.retrieveApplyLeaveDetail(parameterObject);
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getApplyInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getApplyInfoForEmail(parameterObject);
	}
	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object setToEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.setToEmail(parameterObject);
	}
	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object applyResult(Object parameterObject) throws GlRuntimeException {

		return essArDAO.applyResult(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtTopLimitAffirmInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtTopLimitAffirmInfoForEmail(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtTopLimitApplyInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtTopLimitApplyInfoForEmail(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtAffirmInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtAffirmInfoForEmail(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtApplyInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtApplyInfoForEmail(parameterObject);
	}

	/**
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertLeaveApplyMail(Object parameterObject) throws GlRuntimeException {

		essArDAO.insertLeaveApplyMail(parameterObject);
	}

	/**
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	/**
	 * 
	 */
	public void insertOtApplyMail(Object parameterObject) throws GlRuntimeException {

		essArDAO.insertOtApplyMail(parameterObject);
	}

	/**
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertOtAffirmMail(Object parameterObject) throws GlRuntimeException {

		essArDAO.insertOtAffirmMail(parameterObject);
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getLeaveAffirmInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveAffirmInfoForEmail(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject 
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getLeaveApplyInfoForEmail(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveApplyInfoForEmail(parameterObject);
	}
	
	public Object getLeaveApplyInfo(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveApplyInfo(parameterObject);
	}
	
	public Object getOtApplyInfo(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtApplyInfo(parameterObject);
	}
	
	public Object getLeaveApplyInfo1(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveApplyInfo1(parameterObject);
	}

	public Object getLeaveApplyH9Info(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveApplyH9Info(parameterObject);
	}
	
	/**
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertLeaveAffirmMail(Object parameterObject) throws GlRuntimeException {

		essArDAO.insertLeaveAffirmMail(parameterObject);
	}
	public List getPerson_id(String id)throws GlRuntimeException {
		return essArDAO.getPerson_id(id);
	}
	
	public Object getSaleWastes(Object parameterObject) throws GlRuntimeException {
		
		return essArDAO.getSaleWastes(parameterObject);
	}
	
	public Object getSaleWastesCom(Object parameterObject) throws GlRuntimeException {
		
		return essArDAO.getSaleWastesCom(parameterObject);
	}
	
	public List getAffirmorFromEssAffirm(Object parameterObject)throws GlRuntimeException{
		
		return essArDAO.getAffirmorFromEssAffirm(parameterObject);
	}
	
	public List getApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return essArDAO.getApplyNoForMail(parameterObject);
	}
	
	public List getLeaveAffirmorEssAffirm(Object parameterObject)throws GlRuntimeException{
		
		return essArDAO.getLeaveAffirmorEssAffirm(parameterObject);
	}
	
	public List getLeaveApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return essArDAO.getLeaveApplyNoForMail(parameterObject);
	}
	
    public List getArModifyApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return essArDAO.getArModifyApplyNoForMail(parameterObject);
	}
	
	public List applyLeaveResult(Object parameterObject) throws GlRuntimeException {

		return essArDAO.applyLeaveResult(parameterObject);
	}
	
	public List applyLeaveResult1(Object parameterObject) throws GlRuntimeException {

		return essArDAO.applyLeaveResult1(parameterObject);
	}
	
	public List applyArModifyResult(Object parameterObject) throws GlRuntimeException {

		return essArDAO.applyArModifyResult(parameterObject);
	}
	
	public List getNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getNextAffirmor(parameterObject);
	}
	public List getLeaveNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveNextAffirmor(parameterObject);
	}
	
	public List getArModifyNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getArModifyNextAffirmor(parameterObject);
	}

	public List getOtApplyor(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtApplyor(parameterObject);
	}
	
	public List getOtApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getOtApplyNoCreateBy(parameterObject);
	}
	
	public List getLeaveApplyor(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveApplyor(parameterObject);
	}
	
	public List getLeaveApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {

		return essArDAO.getLeaveApplyNoCreateBy(parameterObject);
	}
	
	public List getAttendanceConfirmList(Object parameterObject) {
		List list = new ArrayList();
		try {
			list = essArDAO.getAttendanceConfirmList(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getArDetailConfirmList(Object parameterObject) {
		List list = new ArrayList();
		try {
			list = essArDAO.getArDetailConfirmList(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public List getDetailConfirmList(Object parameterObject) {
		List list = new ArrayList();
		try {
			list = essArDAO.getDetailConfirmList(parameterObject);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return list;
	}
	
	public void essLeaveDeptNo(int applyNo) {
		 essArDAO.essLeaveDeptNo(applyNo);
	}
	
	
}
