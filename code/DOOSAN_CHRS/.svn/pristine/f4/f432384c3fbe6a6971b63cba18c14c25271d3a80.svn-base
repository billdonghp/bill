package com.ait.safe.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.safe.bean.PositionInfo;
import com.ait.safe.dao.JobHealthDAO;
import com.ait.safe.dao.RulesDAOFactory;
import com.ait.safe.dao.RulesDAO;
import com.ait.core.exception.GlRuntimeException;

/**
 * <p>
 * Description: Arservices 是一个代理类。它将数据库操作与
 * 前台操作连接起来。所有数据库的操作方法都可以通过这个代理来执行。前台操作和servlet操作都直接与services这个代理进行联系。
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 */
public class JobHealthServices {

	public JobHealthServices() {
	}

	public List JobHealthInformation(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.JobHealthInformation(parameterObject, currentPage, pageSize);
	}
	
	public List JobHealthInformationReport(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.JobHealthInformationReport(parameterObject);
	}
	
	public String getMedicalFlag(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getMedicalFlag(parameterObject);
	}
	
	public int JobHealthInformationCount(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.JobHealthInformationCount(parameterObject);
	}
	
	public List getJobPositionInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getJobPositionInformation(parameterObject);
	}
	
	public List getPositionInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getPositionInformation(parameterObject);
	}
	
	public List getDiseaseInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getDiseaseInformation(parameterObject);
	}
	
	public List getjob_poditionInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getjob_poditionInformation(parameterObject);
	}
	
	public List getDiseaseTypeInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getDiseaseTypeInformation(parameterObject);
	}
	
	public Object getPositionDiseaseDiseaseTypeAdd(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getPositionDiseaseDiseaseTypeAdd(parameterObject);
	}
	
	public List getPositionDiseaseDiseaseType(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getPositionDiseaseDiseaseType(parameterObject);
	}
	
	public List empInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.empInformation(parameterObject);
	}
	
	public PositionInfo getDisease(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getDisease(parameterObject);
	}
	
	public PositionInfo getDiseaseType(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getDiseaseType(parameterObject);
	}
	
	public int getJPSeq(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getJPSeq(parameterObject);
	}
	
	public Object addPositionInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.addPositionInformation(parameterObject);
	}
	
	public Object addMedicalInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.addMedicalInformation(parameterObject);
	}
	
	public Object updateJobPosition(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.updateJobPosition(parameterObject);
	}
	public Object addPosition(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.addPosition(parameterObject);
	}
	
	public List getAllPositionInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getAllPositionInformation(parameterObject);
	}
	
	public List getAllMedicalInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getAllMedicalInformation(parameterObject);
	}
	public List getThisFlagMedicalInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getThisFlagMedicalInformation(parameterObject);
	}
	
	public Object updatePositionInformation(Object parameterObject, int iSize) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.updatePositionInformation(parameterObject,iSize);
	}
	
	public Object updateMedicalInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.updateMedicalInformation(parameterObject);
	}
	
	public Object deleteMedicalPositionInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.deleteMedicalPositionInformation(parameterObject);
	}
	
	public List getJobHealthDetail(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getJobHealthDetail(parameterObject);
	}
	
	public Object deletePositionDiseaseDiseaseType(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.deletePositionDiseaseDiseaseType(parameterObject);
	}
	
	public void getPositionDiseaseDiseaseTypeUpdate(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		jhd.getPositionDiseaseDiseaseTypeUpdate(parameterObject);
	}
	
	public Object deletePositionDiseaseDiseaseTypeguanxi(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.deletePositionDiseaseDiseaseTypeguanxi(parameterObject);
	}
	public List getSingleInformation(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getSingleInformation(parameterObject);
	}
	
	public Object deletejobHealthView(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.deletejobHealthView(parameterObject);
	}
	
	public List getAllEmpid(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getAllEmpid(parameterObject);
	}
	
	public List getPositionOrder(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getPositionOrder(parameterObject);
	}
	
	public int getCHANGE_POSITION_ORDER_FLAG(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getCHANGE_POSITION_ORDER_FLAG(parameterObject);
	}
	
	public Object updatePositionCHANGE_POSITION_FLAG(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.updatePositionCHANGE_POSITION_FLAG(parameterObject);
	}
	
	public List getPositionInfo(Object parameterObject) throws GlRuntimeException {
		
		JobHealthDAO jhd = (JobHealthDAO) RulesDAOFactory.getInstance().getJobHealthDAO();
		
		return jhd.getPositionInfo(parameterObject);
	}
}
