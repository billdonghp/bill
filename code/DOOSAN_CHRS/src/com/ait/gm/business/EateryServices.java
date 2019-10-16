package com.ait.gm.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.gm.dao.EateryDAOFactory;
import com.ait.gm.dao.EateryDAO;
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
public class EateryServices {

	public EateryServices() {
	}

	public List confirmEateryList(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();
		
		return planConfirmDAO.confirmEateryList(parameterObject);
	}
	
	public List confirmDeptList(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();
		
		return planConfirmDAO.confirmDeptList(parameterObject);
	}
	
	public int eateryPeopleNum(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();
		
		return planConfirmDAO.eateryPeopleNum(parameterObject);
	}
	
	public Object confirmEateryCount(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();
		
		return planConfirmDAO.confirmEateryCount(parameterObject);
	}
	
	public void insertStatistic(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.insertStatistic(parameterObject);
	}
	
	public void deleteEaterycount(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.deleteEaterycount(parameterObject);
	}
   public void insertEaterycountTB(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.insertEaterycountTB(parameterObject);
	}
   public void insertEaterycountJC(Object parameterObject) throws GlRuntimeException {
	
	EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

	planConfirmDAO.insertEaterycountJC(parameterObject);
    }
   public String getEaterycountMaxDate(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getEaterycountMaxDate(parameterObject);
	    }
   
	public List getEatType(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getEatType(parameterObject);
	}

	   
	public List getTempCardType(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getTempCardType(parameterObject);
	}

	   
	public List getCheckAccount(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getCheckAccount(parameterObject);
	}

	   
	public int updateCheckAccount(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.updateCheckAccount(parameterObject);
	}
	
	public List getFoodType(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getFoodType(parameterObject);
	}
	
	public String getconfirm(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getconfirm(parameterObject);
	}

	public String getapplyemail(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getapplyemail(parameterObject);
	}
	public String getupaffrimemail(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getupaffrimemail(parameterObject);
	}
	
	public String getupaffrimemail1(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getupaffrimemail1(parameterObject);
	}
	
   public String getupaffrimemaildriverot(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getupaffrimemaildriverot(parameterObject);
	}
   
   public String getupaffrimemailbusarrange(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getupaffrimemailbusarrange(parameterObject);
	}
	
	public void confirmExpressAffirm(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		 planConfirmDAO.confirmExpressAffirm(parameterObject);
	}
	public void confirmExpressApply(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		 planConfirmDAO.confirmExpressApply(parameterObject);
	}
	
	public List getTopAffirmId(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getTopAffirmId(parameterObject);
	}
	
	public List getDept(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getDept(parameterObject);
	}
	
	public List getRseult(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getRseult(parameterObject);
	}
	
	public List getResultTotal(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getResultTotal(parameterObject);
	}
	
	public void voitureApply(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.voitureApply(parameterObject);
	}
	
	public void driverOtApply(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.driverOtApply(parameterObject);
	}
	
	public void busArrangeApply(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.busArrangeApply(parameterObject);
	}
	
	public void updateVoitureAffirm(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.updateVoitureAffirm(parameterObject);
	}
	
    public void updateDriverOtAffirm(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.updateDriverOtAffirm(parameterObject);
	}
    
    public void updateBusArrangeAffirm(Object parameterObject) throws GlRuntimeException {
		
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.updateBusArrangeAffirm(parameterObject);
	}

	public int getVoitureListInt(Object parameterObject) throws GlRuntimeException {
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getVoitureListInt(parameterObject);
		
	}
	
	public int getDriverOtListInt(Object parameterObject) throws GlRuntimeException {
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getDriverOtListInt(parameterObject);
		
	}
	
	public int getBusArrangeListInt(Object parameterObject) throws GlRuntimeException {
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getBusArrangeListInt(parameterObject);
		
	}
	
	public List getVoitureListList(Object parameterObject,int currentPage, int pageSize)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getVoitureListList(parameterObject,currentPage,pageSize);
		
	}
	
	public List getDriverOtListList(Object parameterObject,int currentPage, int pageSize)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getDriverOtListList(parameterObject,currentPage,pageSize);
		
	}
	
	public List getBusArrangeListList(Object parameterObject,int currentPage, int pageSize)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getBusArrangeListList(parameterObject,currentPage,pageSize);
		
	}
	
	public List allvoitureApproval(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.allvoitureApproval(parameterObject);
		
	}
	public List getDistinctionList(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getDistinctionList(parameterObject);
		
	}
	
	public List allDriverOtApproval(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.allDriverOtApproval(parameterObject);
		
	}
	
	public List allBusArrangeApproval(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.allBusArrangeApproval(parameterObject);
		
	}
	
	public List getCar_name(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getCar_name(parameterObject);
		
	}
	
	public void delvoiture(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		planConfirmDAO.delvoiture(parameterObject);
		
	}
	
	public List getVoitureApplyInfo1(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getVoitureApplyInfo1(parameterObject);
		
	}
	
	public List getCarList(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getCarList(parameterObject);
		
	}
	public int getRy(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getRy(parameterObject);
	}
	
	public int getRyInt(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getRyInt(parameterObject);
	}
	
	public List getVoiTureDetail(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getVoiTureDetail(parameterObject);
	}
	
	public List getVoitureApply1(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getVoitureApply1(parameterObject);
		
	}
	
	public List getVoitureApply2(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getVoitureApply2(parameterObject);
		
	}
	
	public List getrivaList(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getrivaList(parameterObject);
		
	}
	
	public List getDeptname(Object parameterObject)throws GlRuntimeException{
		EateryDAO planConfirmDAO = (EateryDAO) EateryDAOFactory.getInstance().getPlanConfirmDAO();

		return planConfirmDAO.getDeptname(parameterObject);
		
	}
}
