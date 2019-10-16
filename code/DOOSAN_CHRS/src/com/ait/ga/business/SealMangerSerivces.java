package com.ait.ga.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.dao.GaDAOFactory;
import com.ait.ga.dao.express.ExpressApplyAndAffirmDAO;
import com.ait.ga.dao.seal.SealMangerDAO;
import com.ait.ga.dao.securityenvironment.SecurityEnvironmentDAO;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-27
 * 
 */
public class SealMangerSerivces {
	
	 public boolean  addSealApply(Object parameterObject,List affirorList)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.addSealApply(parameterObject,affirorList);
	  }
	 public List  getSealAffirmAndInformationList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getSealAffirmAndInformationList(parameterObject,currentPage,pageSize);
	  }
	 public List  getSealAffirmAndInformationAffirmorList(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getSealAffirmAndInformationAffirmorList(parameterObject);
	  }
	 public int  getSealAffirmAndInformationListNumber(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getSealAffirmAndInformationListNumber(parameterObject);
	  }
	 public boolean  ongingAffirm(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.ongingAffirm(parameterObject);
	  }
	 public boolean  ongingDelete(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.ongingDelete(parameterObject);
	  }
	 
	 public boolean  ongingRead(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.ongingRead(parameterObject);
	 }
	 
	 public boolean  updateAffirmFlag(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.updateAffirmFlag(parameterObject);
	  }
	 
	 public boolean  confirmSealApply(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.confirmSealApply(parameterObject);
	  }
	 
	 public boolean  confirmSealApplyForDISD(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.confirmSealApplyForDISD(parameterObject);
	  }
	 
	 public List  allsealAffrimInfoList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.allsealAffrimInfoList(parameterObject,currentPage,pageSize);
	  }
	 
	 public int  allsealAffrimInfoInt(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.allsealAffrimInfoInt(parameterObject);
	  }
	 
	 public List  allSealApproval(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.allSealApproval(parameterObject);
	  }
	 
	 public List getExcelSealInfoList(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		 return  smDao.getExcelSealInfoList(parameterObject);
	 }
	 
	 public List getSealAffirmorList(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getSealAffirmorList(parameterObject);
	 }
	 
	 public void  deleteApply(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  smDao.deleteApply(parameterObject);
	  }
	 public String  getconfirm(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getconfirm(parameterObject);
	  }
	 
	 public String  getsealName(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getsealName(parameterObject);
	  }
	 public String  getAffirmorIdFirst(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getAffirmorIdFirst(parameterObject);
	  }
	 
	 public String  getApplerId(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.getApplerId(parameterObject);
	  }
	 
	 public List  allsealAffrimInfoList1(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.allsealAffrimInfoList1(parameterObject);
	  }
	 
	 public int  checkReadCount(Object parameterObject)throws GlRuntimeException{
		 SealMangerDAO smDao =(SealMangerDAO)GaDAOFactory.getInstance().getSealMangerDAO();
		  return  smDao.checkReadCount(parameterObject);
	 }
	 
}
