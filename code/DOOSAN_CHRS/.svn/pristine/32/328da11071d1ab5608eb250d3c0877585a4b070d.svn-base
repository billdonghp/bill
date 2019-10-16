package com.ait.gm.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.cmd.WoodWorkingManager;
import com.ait.gm.cmd.WpConfirm;
import com.ait.gm.dao.EateryDAOFactory;
import com.ait.gm.dao.sealdao.SealManagementDAO;
import com.ait.gm.dao.woodProductsdao.WoodProductsDAO;
import com.ait.sy.bean.AdminBean;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class WoodProductsServices {
	private WpConfirm wpConfirm = null;
	private WoodWorkingManager woodManager = null;
    private WoodProductsDAO  wpdao=null;
	public WoodProductsServices() {
		wpConfirm = new WpConfirm();
		woodManager = new WoodWorkingManager();
		wpdao = new WoodProductsDAO();
	}

	public String getNotConfirmDocumentNO() {
		return wpConfirm.getNotConfirmDocumentNO();

	}
	public String isCanConfirm(String documentno) {
		return wpConfirm.isCanConfirm(documentno);

	}

	public List getWoodProductsSet(Object parmeterObject) {
		// TODO Auto-generated method stub
		return wpdao.getWoodProductsSet(parmeterObject);
	}
	public List getwoodProductionList(Object parmeterObject) {
		// TODO Auto-generated method stub
		return wpdao.getwoodProductionList(parmeterObject);
	}
	
	public List woodProductsAllList(Object parmeterObject) {
		// TODO Auto-generated method stub
		return wpdao.getWoodProductsSet(parmeterObject);
	}
	public Object getWoodProductsObject(String seqId){
		return wpdao.getWoodProductsObject(seqId);
	}
	public List ProductsImageView(Object parmeterObject) {
		// TODO Auto-generated method stub
		return wpdao.ProductsImageView(parmeterObject);
	}
	
	public void addWoodProductsSet(HttpServletRequest request, AdminBean admin) {
		// TODO Auto-generated method stub
		woodManager.addWoodProductsSet(request, admin);
	}

	public void deleteWoodProductsSet(HttpServletRequest request) {
		// TODO Auto-generated method stub
		woodManager.deleteWoodProductsSet(request);
	}

	public void updateWoodProductsSet(HttpServletRequest request, AdminBean admin) {
		// TODO Auto-generated method stub
		woodManager.updateWoodProductsSet(request, admin);
	}
	
	public List getDocumentListForConfigList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		return wpdao.getDocumentListForConfigList(parameterObject, currentPage, pageSize);
	}
	
	public Object getDocumentConfigInfo(Object parameterObject) throws GlRuntimeException {
	
		
		return wpdao.getDocumentConfigInfo(parameterObject);
	}
	
	public int  getDocumentListForConfigNumber(Object parameterObject)throws GlRuntimeException{
		
		  return  wpdao.getDocumentListForConfigNumber(parameterObject);
	 }
	 
	 public void  updateGa_woodproducts_apply(List parameterObject)throws GlRuntimeException{
			
			 wpdao.updateGa_woodproducts_apply(parameterObject);
	}
	 
	 public int getWoodProductsAffirmInfoListNumber(Object parameterObject)throws GlRuntimeException{
			 
			  return  wpdao.getWoodProductsAffirmInfoListNumber(parameterObject);
	 }
	 public List getWoodProductsAffirmInfoList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
		
		  return  wpdao.getWoodProductsAffirmInfoList(parameterObject,currentPage,pageSize); 
		  
	 }
	public List  getWoodProductsAffirmorList(Object parameterObject)throws GlRuntimeException{
		
		  return  wpdao.getWoodProductsAffirmorList(parameterObject);
	 }
	public List  getWoodProductsList(Object parameterObject)throws GlRuntimeException{
		
		  return  wpdao.getWoodProductsList(parameterObject);
	 }
	public int getwoodProductionListNumber(Object parameterObject)throws GlRuntimeException{
		 
		  return  wpdao.getwoodProductionListNumber(parameterObject);
}
	 
	public List getwoodProductionList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
		
		  return  wpdao.getwoodProductionList(parameterObject,currentPage,pageSize); 
		  
	 }
	public void addWoodProduction(Object parameterObject)throws GlRuntimeException{
		
		    wpdao.addWoodProduction(parameterObject); 
		  
	 }
	public void updateWoodProduction(Object parameterObject)throws GlRuntimeException{
		
	    wpdao.updateWoodProduction(parameterObject); 
	  
   }
	public void deleteWoodProduction(Object parameterObject)throws GlRuntimeException{
		
	    wpdao.deleteWoodProduction(parameterObject); 
	  
   }
	
	
	
}
