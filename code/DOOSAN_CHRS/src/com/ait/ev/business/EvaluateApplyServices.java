package com.ait.ev.business;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.dao.EvDAOFactory;
import com.ait.ev.dao.EvaluateApplyDAO;
import com.ait.ga.dao.GaDAOFactory;
import com.ait.ga.dao.festivalpresent.FestivalPresentDAO;
import com.ait.util.SqlUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2011-9-19
 * 
 */
public class EvaluateApplyServices {
	
public List getEvaluateApplyList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
		  
		  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
		  return EvaluateApplyDAO.getEvaluateApplyList(parameterObject,currentPage,pageSize);
}

public int getEvaluateApplyListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateApplyListCnt(parameterObject);
}
	
public List getEvaluateApplyDeptList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateApplyDeptList(parameterObject);
}

public List getEvaluateAffirmDeptList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmDeptList(parameterObject);
}

public List getEvaluateAffirmSetList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmSetList(parameterObject,currentPage,pageSize);
}

public int getEvaluateAffirmSetListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateAffirmSetListCnt(parameterObject);
}

public List getEvaluateItemSetList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateItemSetList(parameterObject,currentPage,pageSize);
}

public int getEvaluateItemSetListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateItemSetListCnt(parameterObject);
}

public List getEvaluatePositionSetList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluatePositionSetList(parameterObject);
}

public List getEvaluateQueryPositionList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryPositionList(parameterObject);
}

public List getEvaluateItemList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateItemList(parameterObject);
}




public List getEvaluateApplyPositionList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateApplyPositionList(parameterObject);
}

public List getEvaluateAffirmPositionList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmPositionList(parameterObject);
}

public List getEvaluateApplyItem(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateApplyItem(parameterObject);
}


public List getEvaluateAffirmItem(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateAffirmItem(parameterObject);
}


public String getEvaluateApplyItemId(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateApplyItemId(parameterObject);
}

public List getEvaluateAffirmorList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmorList(parameterObject);
}

public List getEvaluateAffirmorAllList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmorAllList(parameterObject);
}


public List getEvaluateItem(String yearMonth, String deptId, String position,String admin,int level) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateItem(yearMonth, deptId, position,admin,level);
}

public List doEvaluateApplyByBatch(List parameterObject) throws GlRuntimeException {
	EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	return EvaluateApplyDAO.doEvaluateApplyByBatch(parameterObject);
}

public List getEvaluateAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmList(parameterObject,currentPage,pageSize);
}

public List getEvaluateQueryDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryDetailList(parameterObject,currentPage,pageSize);
}

public List getEvaluateQueryResultList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryResultList(parameterObject,currentPage,pageSize);
}

public List getEvaluateQueryNoApplyList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryNoApplyList(parameterObject,currentPage,pageSize);
}

public List getEvaluateQueryDetailExcelList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryDetailExcelList(parameterObject);
}

public List getEvaluateQueryResultExcelList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryResultExcelList(parameterObject);
}

public List getEvaluateQueryNoApplyExcelList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryNoApplyExcelList(parameterObject);
}

public List getEvaluateQueryResultExcelSumList(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateQueryResultExcelSumList(parameterObject);
}

public int getEvaluateAffirmListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateAffirmListCnt(parameterObject);
}

public int getEvaluateQueryDetailListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateQueryDetailListCnt(parameterObject);
}

public int getEvaluateQueryResultListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateQueryResultListCnt(parameterObject);
}

public int getEvaluateQueryNoApplyListCnt(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvaluateQueryNoApplyListCnt(parameterObject);
}

public List getAffirmorList(int applyNo) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getAffirmorList(applyNo);
}

public List getAffirmSetAffirmorList(String deptId,String position) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getAffirmSetAffirmorList(deptId,position);
}

public List getItemSetItemList(String month,String deptId,String position) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getItemSetItemList(month,deptId,position);
}

//决裁
public int doEvaluateAffirm(List parameterObject, int flag) {
	int result = 0;
	try {
		EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
		result = EvaluateApplyDAO.doEvaluateAffirmList(parameterObject,flag);
	} catch (Exception e) {
		e.printStackTrace();
		Logger.getLogger(getClass()).debug(e.toString());
	}
	return result;
}

public List getEvaluateNextAffirmor(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateNextAffirmor(parameterObject);
}

public List getEvaluateDeptAndPosition(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateDeptAndPosition(parameterObject);
}

public List getEvaluateApplyNoForMail(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateApplyNoForMail(parameterObject);
}


public List getEvaluateApplyor(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateApplyor(parameterObject);
}

public List getEvaluateApplyNoCreateBy(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateApplyNoCreateBy(parameterObject);
}

/**
 * 
 * @param parameterObject
 * @return
 * @throws GlRuntimeException
 */
public Object setToEmail(Object parameterObject) throws GlRuntimeException {
	
	 EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	 return EvaluateApplyDAO.setToEmail(parameterObject);

}

public List applyEvaluateResult(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.applyEvaluateResult(parameterObject);
}

public List applyEvaluateResultNoAffirmor(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.applyEvaluateResultNoAffirmor(parameterObject);
}

public List getEvaluateDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateDetailList(parameterObject,currentPage,pageSize);
}

public int getEvaluateDetailListCnt(Object parameterObject) throws GlRuntimeException{

      EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
      return evaluateApplyDAO.getEvaluateDetailListCnt(parameterObject);
}

//决裁
public int updateEvaluateDetail(List parameterObject) {
	int result = 0;
	try {
		EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
		result = EvaluateApplyDAO.updateEvaluateDetail(parameterObject);
	} catch (Exception e) {
		e.printStackTrace();
		Logger.getLogger(getClass()).debug(e.toString());
	}
	return result;
}


public int updateEvaluateDetailFlag(List parameterObject) {
	int result = 0;
	try {
		EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
		result = EvaluateApplyDAO.updateEvaluateDetailFlag(parameterObject);
	} catch (Exception e) {
		e.printStackTrace();
		Logger.getLogger(getClass()).debug(e.toString());
	}
	return result;
}

//按部门月份生成名次
public void updateEvaluateDetailSortGongren(Object parameterObject) throws GlRuntimeException{

    EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.updateEvaluateDetailSortGongren(parameterObject);
}

//按部门月份生成名次
public void updateEvaluateDetailSortNoGongren(Object parameterObject) throws GlRuntimeException{

    EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.updateEvaluateDetailSortNoGongren(parameterObject);
}

//把总分修改为零的信息进行名次处理
public void updateEvaluateDetailSortZero(Object parameterObject) throws GlRuntimeException{

    EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.updateEvaluateDetailSortZero(parameterObject);
}

public List getEvAffirmorEvaluateAffirm(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.getEvAffirmorEvaluateAffirm(parameterObject);
}


public void insertAffirmSetInfo(Object parameterObject,List eaList)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.insertAffirmSetInfo(parameterObject,eaList);
}

public void insertItemSetInfo(Object parameterObject,List esList)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.insertItemSetInfo(parameterObject,esList);
}


public List getEvaluateAffirmSetInfo(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateAffirmSetInfo(parameterObject);
}

public List getEvaluateItemSetInfo(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO EvaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return EvaluateApplyDAO.getEvaluateItemSetInfo(parameterObject);
}

public void updateAffirmSetInfo(Object parameterObject)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.updateAffirmSetInfo(parameterObject);
}

public void updateItemSetInfo(Object parameterObject)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.updateItemSetInfo(parameterObject);
}

public void deleteAffirmSetInfo(Object parameterObject)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.deleteAffirmSetInfo(parameterObject);
}

public void deleteItemSetInfo(Object parameterObject)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.deleteItemSetInfo(parameterObject);
}

public void initalEvParamItem(String evmonth,String cpnyId)throws GlRuntimeException{
	
	EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
    evaluateApplyDAO.initalEvParamItem(evmonth,cpnyId);
}

public String retrieveEvAffirmAdmin(Object parameterObject) throws GlRuntimeException{
	  
	  EvaluateApplyDAO evaluateApplyDAO =(EvaluateApplyDAO)EvDAOFactory.getInstance().getEvaluateApplyDAO();
	  return evaluateApplyDAO.retrieveEvAffirmAdmin(parameterObject);
}



}
