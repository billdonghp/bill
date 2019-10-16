package com.ait.ga.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.dao.GaDAOFactory;
import com.ait.ga.dao.express.ExpressApplyAndAffirmDAO;
import com.ait.ga.dao.securityenvironment.SecurityEnvironmentDAO;
import com.ait.ga.dao.woodproducts.WoodProductsDAO;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class WoodProductsServices {
	
	WoodProductsDAO wpdao=null;
	
	public WoodProductsServices(){
		wpdao= new WoodProductsDAO();
	}	

  public String getapplyemail(Object parameterObject )throws GlRuntimeException{
	 
		  return  wpdao.getapplyemail(parameterObject);
  }
  public String getapplyemail2(Object parameterObject )throws GlRuntimeException{
		 
	      return  wpdao.getapplyemail2(parameterObject);
  }
  public String getupaffrimemail(Object parameterObject )throws GlRuntimeException{
	 
		  return  wpdao.getupaffrimemail(parameterObject);
  }
  public int getUpAffrimNumber(Object parameterObject)throws GlRuntimeException{
	  
		  return  wpdao.getUpAffrimNumber(parameterObject);
  }
  public void oingAffirm(Object parameterObject)throws GlRuntimeException{	 
		  wpdao.oingAffirm(parameterObject);
  }
  public void updateApplyInfo(Object parameterObject)throws GlRuntimeException{	 
	  wpdao.updateApplyInfo(parameterObject);
  }
  
  public int getWoodProductsAffirmListNumber(Object parameterObject)throws GlRuntimeException{
	  
		  return  wpdao.getWoodProductsAffirmListNumber(parameterObject);
  }
  public List getWoodProductsAffirmList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
	 
	  return  wpdao.getWoodProductsAffirmList(parameterObject,currentPage,pageSize); 
	  
  }
  
  public int getWoodProductsAffirmInfoListNumber(Object parameterObject)throws GlRuntimeException{
	 
		  return  wpdao.getWoodProductsAffirmInfoListNumber(parameterObject);
  }
  public List getWoodProductsAffirmInfoList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
	
	  return  wpdao.getWoodProductsAffirmInfoList(parameterObject,currentPage,pageSize); 
	  
  }
  
  public List getWoodProductsAffirmorList(Object parameterObject)throws GlRuntimeException{
	
	  return  wpdao.getWoodProductsAffirmorList(parameterObject); 
	  
  }
  public List getWoodProductsList(Object parameterObject)throws GlRuntimeException{
	 
	  return  wpdao.getWoodProductsList(parameterObject); 
	  
  }
}
