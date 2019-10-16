/*
 * @(#)EvsServices.java 1.0 2007-5-17 下午03:07:38
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArDAOFactory;
import com.ait.ar.dao.idcard.AttRecordDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.bean.EssAffirmor;
import com.ait.evs.dao.EvsCommonDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-5-17 下午03:07:38
 * @version 1.0
 * 
 */
public class EvsServices {

	private static EvsServices evsServices;

	private EvsCommonDAO evsCommonDAO;

	private EvsServices() {

		this.evsCommonDAO = new EvsCommonDAO();
		//evsCommonDAO1 = new com.ait.evs.dao.EvsFSCommonDAO();
	}

	/**
	 * 
	 * @return EvsServices
	 */
	public static EvsServices getInstance() {

		if (evsServices != null)
			return evsServices;
		else
			return new EvsServices();
	}
	
	/**
	 * 在Line查询CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List<SimpleMap> getEvscodeList3(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeList3(parameterObject);
	}
	/**
	 * 在Line更新CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvscodeList4(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeList4(parameterObject);
	}
	
	/**
	 * 在Line更新CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvsgxjndjList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvsgxjndjList(parameterObject);
	}
	public Object getEvsgxjsdjList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvsgxjsdjList(parameterObject);
	}
	/**
	 * 在技能类型查询CODE_ID，CODE_name  工种的   添加页面
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List<SimpleMap> getEvscodeStypeList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeStypeList(parameterObject);
	}
	
	/**
	 * 在作业类型更新页面显示CODE_ID，CODE_name  
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvsopacodeList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvsopacodeList(parameterObject);
	}
	 
	
	public List<SimpleMap> getEvscodeList5(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeList5(parameterObject);
	}
	
	public List<SimpleMap> getEvscodeList6(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeList6(parameterObject);
	}
	
	/**
	 * 技能类型  查找
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> getStypeEvscodeList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getStypeEvscodeList(parameterObject);
	}
	
	
	public void deleteLine(String parameterObject) throws GlRuntimeException {
		evsCommonDAO.deleteLine(parameterObject);
	}
	
	public void deleteZyzgdj(String parameterObject) throws GlRuntimeException {
		evsCommonDAO.deleteZyzgdj(parameterObject);
	}
	public void deleteGjjsdj(String parameterObject) throws GlRuntimeException {
		evsCommonDAO.deleteGjjsdj(parameterObject);
	}
	public void insertLine(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertLine(parameterList);
	}
	public void insertLiner(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertLiner(parameterList);
	}
	public void deleteSetupcode(String parameterObject) throws GlRuntimeException {
		evsCommonDAO.deleteSetupcode(parameterObject);
	}
	/**
	 * 技能类型  保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public void insertStype(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertStype(parameterList);
	}
	
	/**
	 * 职业资格等级  保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public void insertZyzgdj(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertZyzgd(parameterList);
	}
	public void insertGjjsdj(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertGjjsdj(parameterList);
	}
	/**
	 * 作业类型  保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public void insertTypeopa(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertTypeopa(parameterList);
	}
	
	
	/**
	 * 作业类型  更新保存
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public void updateTypeopa(List parameterList) throws GlRuntimeException {
		evsCommonDAO.updateTypeopa(parameterList);
	}
	
	/**
	 * 在Ircraft查询CODE_ID，CODE_name  工种的  和  LIne 的
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List<SimpleMap> getEvsIrcraftcodeList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvsIrcraftcodeList(parameterObject);
	}
	
	public void insertIrcraft(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertIrcraft(parameterList);
	}
	
	/**
	 * 在机种更新CODE_ID，CODE_name  工种的
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvscodeIrcraftList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeIrcraftList(parameterObject);
	}
	public Object getEvscodeIrcraftList0(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeIrcraftList0(parameterObject);
	}
	public List<SimpleMap> getEvscodeIrcraftList2(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeIrcraftList2(parameterObject);
	}
	
	public List<SimpleMap> getEvscodeIrcraftList3(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvscodeIrcraftList3(parameterObject);
	}
	
	/**
	 * retrieve evaluate result list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveEvaluateResultList(Object parameterObject)
			throws GlRuntimeException {

		return evsCommonDAO.retrieveEvaluateResultList(parameterObject);
	}

	
	/**
	 * 评价进行时查询评价人员数据
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getEvsempcodeList(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvsempcodeList(parameterObject);
	}
	/**
	 * 在评价进行时查询工种查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsCraftList() throws GlRuntimeException {

		return evsCommonDAO.getEvsCraftList();
	}
	public List getEvsYearIDList() throws GlRuntimeException {

		return evsCommonDAO.getEvsYearIDList();
	}
	/**
	 * 在评价进行时查询LIne查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsLineList() throws GlRuntimeException {

		return evsCommonDAO.getEvsLineList();
	}
	/**
	 * 在评价进行时查询技能类型查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsSkitypeList() throws GlRuntimeException {

		return evsCommonDAO.getEvsSkitypeList();
	}
	/**
	 * 在评价进行时查询机种查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsAircraftList() throws GlRuntimeException {

		return evsCommonDAO.getEvsAircraftList();
	}
	/**
	 * 在评价进行时查询工序查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsProcesstList() throws GlRuntimeException {

		return evsCommonDAO.getEvsProcesstList();
	}
	/**
	 * 在评价进行时查询作业内容查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsJobcontentList() throws GlRuntimeException {

		return evsCommonDAO.getEvsJobcontentList();
	}
	/**
	 * 在评价进行时查询作业类型查询
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsTypetionList() throws GlRuntimeException {

		return evsCommonDAO.getEvsTypetionList();
	}
	
	/**
	 * 在评价进行时查询技能等级
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsSkilelist() throws GlRuntimeException {

		return evsCommonDAO.getEvsSkilelist();
	}
	
	/**
	 * 在评价进行时查询职业资格
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsQualificationList() throws GlRuntimeException {

		return evsCommonDAO.getEvsQualificationList();
	}
	
	/**
	 * 在评价进行时查询培训目标
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsPurposeList() throws GlRuntimeException {

		return evsCommonDAO.getEvsPurposeList();
	}
	
	/**
	 * 在评价进行时查询技能类型
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsTypeoationlist() throws GlRuntimeException {

		return evsCommonDAO.getEvsTypeoationlist();
	}
	
	/**
	 * 在评价进行时查询作业类型
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsSkiltypelist() throws GlRuntimeException {

		return evsCommonDAO.getEvsSkiltypelist();
	}
	
	
	/**
	 * 评价进行时保存数据
	 * @param parameterList
	 * @throws GlRuntimeException
	 */
	public void insertJinxing(List parameterList) throws GlRuntimeException {
		evsCommonDAO.insertJinxing(parameterList);
	}

	/**
	 * 评价进行时修改保存数据
	 * @param parameterList
	 * @throws GlRuntimeException
	 */
	public void updateJinxing(List parameterList) throws GlRuntimeException {
		evsCommonDAO.updateJinxing(parameterList);
	}
	public List retrieveZyzgdjModifyAffirmList(Object parameterObject)
	{
		List result = null;
		try {
			result = evsCommonDAO.retrieveZyzgdjModifyAffirmList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	public List retrieveSetupcodeAffirmList(Object parameterObject)
	{
		List result = null;
		try {
			result = evsCommonDAO.retrieveSetupcodeAffirmList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	public List retrieveGjjsAffirmList(Object parameterObject)
	{
		List result = null;
		try {
			result = evsCommonDAO.retrieveGjjsAffirmList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	public List retrieveGxModifyAffirmList(Object parameterObject)
	{
		List result = null;
		try {
			result = evsCommonDAO.retrieveGxModifyAffirmList(parameterObject) ;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
		
	}
	/**
	 * 在评价进行时查询已存在的人员
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getEvsCountEmp(Object parameterObject)
	throws GlRuntimeException {
	
	return evsCommonDAO.getEvsCountEmp(parameterObject);
	}
	
	/**
	 * 取出指定职业资格修改记录的所有决裁者
	 * 
	 */
	public List getZyzgdjModifyAffirmorList(int applyNo) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT EVS_CODE_AFFIRM.AR_DETAIL_AFFIRM_NO,"
				+ "        EVS_CODE_AFFIRM.AR_DETAIL_NO,"
				+ "	       EVS_CODE_AFFIRM.AFFIRMOR_ID,"
				+ "	       TO_CHAR(EVS_CODE_AFFIRM.UPDATE_DATE, 'YYYY-MM-DD HH24:MI') AS UPDATE_DATE,"
				+ "	       HR_EMPLOYEE.CHINESENAME,"
				+ "	       hr_employee.chinese_pinyin,"
				+ "	       hr_employee.koreanname,"
				+ "	       EVS_CODE_AFFIRM.AFFIRM_LEVEL,"
				+ "	       EVS_CODE_AFFIRM.AFFIRM_FLAG,"
				+ "	       EVS_CODE_AFFIRM.AFFIRM_REMARKS"
				+ "		   FROM EVS_CODE_AFFIRM, HR_EMPLOYEE"
				+ "		   WHERE EVS_CODE_AFFIRM.ACTIVITY ='1' AND EVS_CODE_AFFIRM.AFFIRMOR_ID = HR_EMPLOYEE.Person_Id"
				+ "		   AND EVS_CODE_AFFIRM.AR_DETAIL_NO = ? "
				+ "		   ORDER BY EVS_CODE_AFFIRM.AFFIRM_LEVEL ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, applyType);
			pstmt.setInt(1, applyNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor.setAffirmNo(rs.getInt("AR_DETAIL_AFFIRM_NO"));
				essAffirmor.setApplyNo(rs.getInt("AR_DETAIL_NO"));
				essAffirmor.setAffirmorId(rs.getString("AFFIRMOR_ID"));
				essAffirmor.setUpdateDate(rs.getString("UPDATE_DATE"));

				essAffirmor.setAffirmorName(rs.getString("CHINESENAME"));

				essAffirmor.setAffirmorEnName(rs.getString("CHINESE_PINYIN"));
				essAffirmor.setAffirmorKorName(rs.getString("KOREANNAME"));

				essAffirmor.setAffirmLevel(rs.getInt("AFFIRM_LEVEL"));
				essAffirmor.setAffirmFlag(rs.getInt("AFFIRM_FLAG"));
				// essAffirmor.setApplyType(rs.getString("APPLY_TYPE"));
				essAffirmor.setRemark(rs.getString("AFFIRM_REMARKS"));
				list.add(essAffirmor);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	//评价成绩 决裁
	public int doSetupcodeAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		try {
			result = evsCommonDAO.doSetupcodeAffirm(params,flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	//工匠技师 决裁
	public int doGjjsAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		try {
			result = evsCommonDAO.doGjjsAffirm(params,flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	//职业资格等级修改 决裁
	public int doZyezgdjModifyAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		try {
			result = evsCommonDAO.doZyezgdjModifyAffirmList(params,flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	//工序难度系数修改 决裁
	public int doGxModifyAffirm(EssAffirmParam[] params, int flag) {
		int result = 0;
		try {
			result = evsCommonDAO.doGxModifyAffirmList(params,flag);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return result;
	}
	public List getSetupcodeNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getSetupcodeNextAffirmor(parameterObject);
	}
	public List getGjjsNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getGjjsNextAffirmor(parameterObject);
	}
	public List getZyzgdjModifyNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getZyzgdjModifyNextAffirmor(parameterObject);
	}
	public List getGxModifyNextAffirmor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getGxModifyNextAffirmor(parameterObject);
	}
	public List getSetupcodeApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return evsCommonDAO.getSetupcodeApplyNoForMail(parameterObject);
	}
	public List getGjjsApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return evsCommonDAO.getGjjsApplyNoForMail(parameterObject);
	}
	public List getArModifyApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return evsCommonDAO.getArModifyApplyNoForMail(parameterObject);
	}
	public List getGxModifyApplyNoForMail(Object parameterObject)throws GlRuntimeException{
		
		return evsCommonDAO.getGxModifyApplyNoForMail(parameterObject);
	}
	public List getLeaveApplyor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getLeaveApplyor(parameterObject);
	}
	public List getSetupApplyor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getSetupApplyor(parameterObject);
	}
	public List getGjjsApplyor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getGjjsApplyor(parameterObject);
	}
	public List getGxApplyor(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getGxApplyor(parameterObject);
	}
	public List getLeaveApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getLeaveApplyNoCreateBy(parameterObject);
	}
	public List getSetupApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getSetupApplyNoCreateBy(parameterObject);
	}
	public List getGjjsApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getGjjsApplyNoCreateBy(parameterObject);
	}
	public List getGxApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.getGxApplyNoCreateBy(parameterObject);
	}
	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object setToEmail(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.setToEmail(parameterObject);
	}
	
	public List applyLeaveResult(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.applyLeaveResult(parameterObject);
	}
	public List applyGxResult(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.applyGxResult(parameterObject);
	}
	public List applyLeaveResult1(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.applyLeaveResult1(parameterObject);
	}
	public List applySetupcodeResult(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.applySetupcodeResult(parameterObject);
	}
	public List applyArModifyResult(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.applyArModifyResult(parameterObject);
	}
	public List applyGxModifyResult(Object parameterObject) throws GlRuntimeException {

		return evsCommonDAO.applyGxModifyResult(parameterObject);
	}
	public List retrieveJnpjRecordList(Object parameterObject) throws GlRuntimeException {
		
		return evsCommonDAO.retrieveJnpjRecordList(parameterObject);
	}
	/**
	 * 累计积分 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getSumCore(Object parameterObject) throws GlRuntimeException{
		return evsCommonDAO.retrieveSumCore(parameterObject);
	}
}
