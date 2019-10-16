package com.ait.gm.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.EateryDAOFactory;
import com.ait.gm.dao.sealdao.SealManagementDAO;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-28
 * 
 */
public class SealManagementServices {
	
	 public List  getAllWaitConfirmList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
		 SealManagementDAO smDao =(SealManagementDAO)EateryDAOFactory.getInstance().getSealManagementDAO();
		  return  smDao.getAllWaitConfirmList(parameterObject,currentPage,pageSize);
	  }
	 public int  getAllWaitConfirmListNumber(Object parameterObject)throws GlRuntimeException{
		 SealManagementDAO smDao =(SealManagementDAO)EateryDAOFactory.getInstance().getSealManagementDAO();
		  return  smDao.getAllWaitConfirmListNumber(parameterObject);
	  }
	 public boolean  oingConfirm(Object parameterObject)throws GlRuntimeException{
		 SealManagementDAO smDao =(SealManagementDAO)EateryDAOFactory.getInstance().getSealManagementDAO();
		  return  smDao.oingConfirm(parameterObject);
	  }
	  
	 public List  getAllWaitConfirmList(Object parameterObject)throws GlRuntimeException{
		 SealManagementDAO smDao =(SealManagementDAO)EateryDAOFactory.getInstance().getSealManagementDAO();
		  return  smDao.getAllWaitConfirmList(parameterObject);
	  }

	 
}
