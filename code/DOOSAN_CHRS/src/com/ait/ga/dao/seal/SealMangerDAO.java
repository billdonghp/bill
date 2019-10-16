package com.ait.ga.dao.seal;

import java.util.List;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-27
 * 
 */
public class SealMangerDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public SealMangerDAO() {
		
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	public boolean  addSealApply(Object parameterObject,List affirorList) throws GlRuntimeException {
		GaAffirmList gaAffirmList = null;
		SimpleMap dateMap = (SimpleMap) parameterObject;
		boolean temp = false;
		int confirmnumber = 0;
		
		String personId=(String) ((SimpleMap) parameterObject).get("personId");
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.insert("ga.sealManger.addSealApply",
					parameterObject);
			confirmnumber += 1;
			for (int i = 0; i < affirorList.size(); i++) {
				int affirmFlag=0;
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				if(personId.equals(gaAffirmList.getAffirmorId())){
					affirmFlag=1;
				}
				dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
				dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
				dateMap.set("affirmFlag", affirmFlag);
				
				commonSQLMapAdapter.insert("ga.sealManger.addSealAffirm",
						dateMap);
				confirmnumber += 1;
			}
			if (confirmnumber == (affirorList.size() + 1)) {
				commonSQLMapAdapter.commitTransation();
				temp = true;
			}

			//อฦหอ
			DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_SEAL, dateMap.getString("documentno"));

		} catch (Exception e) {
			
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert addSealApply data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return temp;
	}
	
	 public List  getSealAffirmAndInformationList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.sealAffirmAndInformationList", parameterObject, currentPage, pageSize);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getSealAffirmAndInformationList data Exception. ", e);
			}
			return temp;
	  }
	 public List  getSealAffirmAndInformationAffirmorList(Object parameterObject)throws GlRuntimeException{
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.sealAffirmAndInformationAffirmorList", parameterObject);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getSealAffirmAndInformationAffirmorList data Exception. ", e);
			}
			return temp;
	  }
	 public int  getSealAffirmAndInformationListNumber(Object parameterObject)throws GlRuntimeException{
			int  temp=0;
			try {
				temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.sealManger.sealAffirmAndInformationListNumber", parameterObject).toString());

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getSealAffirmAndInformationListNumber data Exception. ", e);
			}
			return temp;
	  }
	 public boolean  ongingAffirm(Object parameterObject)throws GlRuntimeException{
			boolean  temp = false;
			try {
				commonSQLMapAdapter.update("ga.sealManger.ongingAffirm", parameterObject);
                temp = true;
			} catch (Exception e) {
				temp = false;
				logger.error(e.toString());
				throw new GlRuntimeException(
						"ongingAffirm data Exception. ", e);
			}
			return temp;
	  }
	 public boolean  ongingDelete(Object parameterObject)throws GlRuntimeException{
			boolean  temp = false;
			try {
				commonSQLMapAdapter.update("ga.sealManger.ongingDelete", parameterObject);
             temp = true;
			} catch (Exception e) {
				temp = false;
				logger.error(e.toString());
				throw new GlRuntimeException(
						"ongingDelete data Exception. ", e);
			}
			return temp;
	  }
	 
	 public boolean  ongingRead(Object parameterObject)throws GlRuntimeException{
			boolean  temp = false;
			try {
				commonSQLMapAdapter.update("ga.sealManger.ongingRead", parameterObject);
          temp = true;
			} catch (Exception e) {
				temp = false;
				logger.error(e.toString());
				throw new GlRuntimeException(
						"ongingRead data Exception. ", e);
			}
			return temp;
	  }
	 
	 public boolean  updateAffirmFlag(Object parameterObject)throws GlRuntimeException{
			boolean  temp = false;
			try {
				commonSQLMapAdapter.update("ga.sealManger.updateAffirmFlag", parameterObject);
          temp = true;
			} catch (Exception e) {
				temp = false;
				logger.error(e.toString());
				throw new GlRuntimeException(
						"updateAffirmFlag data Exception. ", e);
			}
			return temp;
	  }
	 
	 public boolean  confirmSealApply(Object parameterObject)throws GlRuntimeException{
			boolean  temp = false;
			try {
				commonSQLMapAdapter.update("ga.sealManger.confirmSealApply", parameterObject);
       temp = true;
			} catch (Exception e) {
				temp = false;
				logger.error(e.toString());
				throw new GlRuntimeException(
						"confirmSealApply data Exception. ", e);
			}
			return temp;
	  }
	 
	 public boolean  confirmSealApplyForDISD(Object parameterObject)throws GlRuntimeException{
			boolean  temp = false;
			try {
				commonSQLMapAdapter.update("ga.sealManger.confirmSealApplyForDISD", parameterObject);
    temp = true;
			} catch (Exception e) {
				temp = false;
				logger.error(e.toString());
				throw new GlRuntimeException(
						"confirmSealApplyForDISD data Exception. ", e);
			}
			return temp;
	  }
	 
	 public List  allsealAffrimInfoList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException{
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.allsealAffrimInfoList", parameterObject, currentPage, pageSize);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"allsealAffrimInfoList data Exception. ", e);
			}
			return temp;
	  }
	  
		 public int  allsealAffrimInfoInt(Object parameterObject)throws GlRuntimeException{
			int  temp=0;
			try {
				temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.sealManger.allsealAffrimInfoInt", parameterObject).toString());

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"allsealAffrimInfoInt data Exception. ", e);
			}
			return temp;
	  }
	 
	 public List  allSealApproval(Object parameterObject)throws GlRuntimeException{
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.allSealApproval", parameterObject);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"allSealApproval data Exception. ", e);
			}
			return temp;
	  }
	 
	 public List  getExcelSealInfoList(Object parameterObject) throws GlRuntimeException {
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.excelSealInfoList", parameterObject);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getExcelSealInfoList data Exception. ", e);
			}
			return temp;
		}
	 
	 public List  getSealAffirmorList(Object parameterObject) throws GlRuntimeException {
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.SealAffirmorList", parameterObject);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getSealAffirmorList data Exception. ", e);
			}
			return temp;
		}
	 
	 public void  deleteApply(Object parameterObject)throws GlRuntimeException{
			try {
				commonSQLMapAdapter.delete("ga.sealManger.deleteAffim", parameterObject);
				commonSQLMapAdapter.delete("ga.sealManger.deleteApply", parameterObject);
			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"deleteApply data Exception. ", e);
			}
	  }
	 
	 public String  getconfirm(Object parameterObject)throws GlRuntimeException{
		 String temp = "";
			try {
				temp = (String)commonSQLMapAdapter.executeQuery("ga.sealManger.getconfirm", parameterObject);
			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getconfirm data Exception. ", e);
			}
			return temp;
	  }
	 
	 public String  getsealName(Object parameterObject)throws GlRuntimeException{
		 String temp = "";
			try {
				temp = (String)commonSQLMapAdapter.executeQuery("ga.sealManger.getsealName", parameterObject);
			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getsealName data Exception. ", e);
			}
			return temp;
	  }
	 
	 public String  getAffirmorIdFirst(Object parameterObject)throws GlRuntimeException{
		 String temp = "";
			try {
				temp = (String)commonSQLMapAdapter.executeQuery("ga.sealManger.getAffirmorIdFirst", parameterObject);
			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getsealName data Exception. ", e);
			}
			return temp;
	  }
	 
	 public String  getApplerId(Object parameterObject)throws GlRuntimeException{
		 String temp = "";
			try {
				temp = (String)commonSQLMapAdapter.executeQuery("ga.sealManger.getApplerId", parameterObject);
			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"getApplerId data Exception. ", e);
			}
			return temp;
	  }
	 
	 public List  allsealAffrimInfoList1(Object parameterObject)throws GlRuntimeException{
			List  temp=null;
			try {
				temp=commonSQLMapAdapter.executeQueryForMulti("ga.sealManger.allsealAffrimInfoList1", parameterObject);

			} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"allsealAffrimInfoList1 data Exception. ", e);
			}
			return temp;
	  }
	 
	 public int  checkReadCount(Object parameterObject)throws GlRuntimeException{
		 int temp = 0;
			try {
				temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
						"ga.sealManger.checkReadCount",
						parameterObject).toString());
				} catch (Exception e) {
				
				logger.error(e.toString());
				throw new GlRuntimeException(
						"checkReadCount data Exception. ", e);
			}
			return temp;
	  }
	 
}
