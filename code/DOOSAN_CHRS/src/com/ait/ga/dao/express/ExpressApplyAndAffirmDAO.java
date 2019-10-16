package com.ait.ga.dao.express;

import java.util.List;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */

public class ExpressApplyAndAffirmDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public ExpressApplyAndAffirmDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	public List getId(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.visiterApplyId", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getId data Exception. ", e);
		}
		return list;
	}

	public String conferenceroomApplyId(Object parameterObject) throws GlRuntimeException {
		String  result=null;
		try {
			result=commonSQLMapAdapter.executeQuery("ga.conferenceroom.conferenceroomApplyIdApplyId", parameterObject).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getId data Exception. ", e);
		}
		return result;
	}
	
	
	public void insertExpressApplyDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ga.express.insertExpressApplyDetail", parameterObject, isAutoTrans);
		} catch (Exception e) {

			logger.error("Insert present apply detial by multi error." + e);
			throw new GlRuntimeException("Insert present apply detail multi error.", e);
		}
	}
	
//	/**
//	 * apply present
//	 * 
//	 * @param parameterObject
//	 * @return
//	 * @throws GlRuntimeException
//	 */
//	public Object addExpressApplyMany1(SimpleMap parameterObject) throws GlRuntimeException {
//		GaAffirmList gaAffirmList = null;
//		SimpleMap dateMap = (SimpleMap) parameterObject;
//		try {
//			
//			commonSQLMapAdapter.startTransaction();
//			
//			// insert apply data
//			//this.insertPresentApply(parameterObject);
//			
//			// insert apply detail data
//			List detailList = (List)parameterObject.get("detail");
//			this.insertExpressApplyDetail(detailList, false);
//			
//			// insert affirm data
//			List affirorList = (List)parameterObject.get("affirm");
//			
//			for (int i = 0; i < affirorList.size(); i++) {
//				gaAffirmList = (GaAffirmList) affirorList.get(i);
//				dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
//				dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
//				commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
//						dateMap);
//				
//			}
//			this.insertPresentAffirm(affirmList, false);
//			
//			// get apply No
//			Object result = this.getCurrApplyNo();
//			
//			commonSQLMapAdapter.commitTransation();
//			
//			return result;
//			
//		} catch (Exception e) {
//
//			logger.error("Apply present error." + e);
//			throw new GlRuntimeException("Apply present error.", e);
//		} finally {
//
//			try {
//				commonSQLMapAdapter.endTransation();
//			} catch (Exception e) {
//				logger.error("End transation. " + e.toString());
//				throw new GlRuntimeException("End transation Exception. ", e);
//			}
//		}
//	}
	
	public boolean addExpressApplyMany(Object parameterObject, List affirorList)
			throws GlRuntimeException {
		GaAffirmList gaAffirmList = null;
		SimpleMap dateMap = (SimpleMap) parameterObject;
		boolean temp = false;
		int confirmnumber = 0;
		int affirmNumber=1;
		try {
			commonSQLMapAdapter.startTransaction();
			//commonSQLMapAdapter.insert("ga.express.addExpressApply",
			//		parameterObject);	
			List detailList = (List)((SimpleMap) parameterObject).get("detail");
			this.insertExpressApplyDetail(detailList, false);
			
			for (int j = 0; j < detailList.size() ; j++){
				SimpleMap s = (SimpleMap)detailList.get(j);
				
				if("61000000".equals(s.getString("cpnyId"))){//DISC 快件 韩国 
					for (int i = 0; i < affirorList.size(); i++) {
						gaAffirmList = (GaAffirmList) affirorList.get(i);
						dateMap.setInt("applyno", s.getInt("applyno"));
						dateMap.set("affirmFlag", gaAffirmList.getAffirmFlag());
						dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
						dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
						// 大于等于7级的，并且 不等于韩国的 就不取7，8等级的决裁者
						//System.out.println("+++++++++++++++++++++"+s.getString("cityarrive"));
//						if(gaAffirmList.getAffirmOldLevel()>=7 ){
//							if((!"韩国(文件)".equals(s.getString("cityarrive"))) && (!"韩国(物品)".equals(s.getString("cityarrive")))){
//								continue;
//							}	
//						}
						commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
								dateMap);
						//System.out.println("+++++++++++++++++++++++"+gaAffirmList.getAffirmOldLevel()+s.getString("cityarrive"));
					}
				}else{
					
					for (int i = 0; i < affirorList.size(); i++) {
						gaAffirmList = (GaAffirmList) affirorList.get(i);
						dateMap.setInt("applyno", s.getInt("applyno"));
						dateMap.set("affirmFlag", gaAffirmList.getAffirmFlag());
						dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
						dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
						commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
								dateMap);
					}
					
				}
				
				
			}
			//推送
			if (detailList != null && detailList.size() > 0) {
				SimpleMap s = (SimpleMap)detailList.get(0);
				DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_EXPRESS, s.getInt("applyno") + "");
			}
			
			
				commonSQLMapAdapter.commitTransation();
				temp = true;
			
		
		} catch (Exception e) {
		
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert Express Apply data Exception. ", e);
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


	
	public boolean addExpressApply(Object parameterObject, List affirorList)
			throws GlRuntimeException {
		GaAffirmList gaAffirmList = null;
		SimpleMap dateMap = (SimpleMap) parameterObject;
		boolean temp = false;
		int confirmnumber = 0;
		int affirmNumber=1;
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.insert("ga.express.addExpressApply",
					parameterObject);	
			
			for (int i = 0; i < affirorList.size(); i++) {
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
				dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
				commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
						dateMap);
				
			}
			
			/*
			if(Double.parseDouble(dateMap.get("costs").toString())<500){//当费用小于500时，按照配置的决裁权限进行插入
			for (int i = 0; i < affirorList.size(); i++) {
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
				dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
				commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
						dateMap);
				
			}
			}else if(Double.parseDouble(dateMap.get("costs").toString())>=500 && Double.parseDouble(dateMap.get("costs").toString())<1000){//当费用大于500，小于1000时，插入已经配置的决裁权限，以及增加管理部课长进决裁
				for (int i = 0; i < affirorList.size(); i++) {
					gaAffirmList = (GaAffirmList) affirorList.get(i);
					dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
					dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
					commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
							dateMap);
					affirmNumber=affirmNumber+1;
					
				}
				dateMap.set("AffirmorLevel",affirmNumber);
				dateMap.set("AffirmorId", "ic9530632");
				commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
						dateMap);
			}else{//当费用大于1000时，插入已经配置的决裁以及管理部部长进决裁表
				for (int i = 0; i < affirorList.size(); i++) {
					gaAffirmList = (GaAffirmList) affirorList.get(i);
					dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
					dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
					commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
							dateMap);
					affirmNumber=affirmNumber+1;
					
				}
				dateMap.set("AffirmorLevel",affirmNumber);
				dateMap.set("AffirmorId", "ic9530945");
				commonSQLMapAdapter.insert("ga.express.addExpressAffirm",
						dateMap);
			}
			*/
				commonSQLMapAdapter.commitTransation();
				temp = true;
			

		} catch (Exception e) {

			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert Express Apply data Exception. ", e);
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

	public int getExpressAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.express.expressAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	public List getExpressAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.express.expressAffirmList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirmList data Exception. ", e);
		}
		return list;
	}
	public List getExpressAffirmorList(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.express.expressAffirmorList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirmorList data Exception. ", e);
		}
		return list;
	}
	public boolean  confirmExpressAffirm(Object parameterObject) throws GlRuntimeException {
		boolean temp=false;
		try {
			commonSQLMapAdapter.update("ga.express.confirmAffirm", parameterObject);
             temp=true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmExpressAffirm data Exception. ", e);
		}
		return temp;
	}
	public boolean  confirmExpressApply(Object parameterObject) throws GlRuntimeException {
		boolean temp=false;
		try {
			commonSQLMapAdapter.update("ga.express.confirmExpressApply", parameterObject);
             temp=true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmExpressApply data Exception. ", e);
		}
		return temp;
	}
	
	public int getExpressAffirmInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.express.expressAffirmInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirmInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	public List getExpressAffirmInfoList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.express.expressAffirmInfoList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirmInfoList data Exception. ", e);
		}
		return list;
	}
	public List getExcelExpressAffirmInfoList(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.express.ExcelExpressAffirmInfoList", parameterObject);
			
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpressAffirmInfoList data Exception. ", e);
		}
		return list;
	}
	public boolean  deleteApply(Object parameterObject) throws GlRuntimeException {
		boolean temp=false;
		try {
			commonSQLMapAdapter.update("ga.express.deleteApply", parameterObject);
			commonSQLMapAdapter.update("ga.express.deleteAffrim", parameterObject);
             temp=true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteApply data Exception. ", e);
		}
		return temp;
	}
	
	public List roomnameList(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.roomnameList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"roomnameList data Exception. ", e);
		}
		return list;
	}
	
	public String roomnameList1(Object parameterObject) throws GlRuntimeException {
		String  list="";
		try {
			list=(String)commonSQLMapAdapter.executeQuery("ga.conferenceroom.roomname", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"roomnameList data Exception. ", e);
		}
		return list;
	}
	
	public String roomConfirmorid(Object parameterObject) throws GlRuntimeException {
		String  list="";
		try {
			list=(String)commonSQLMapAdapter.executeQuery("ga.conferenceroom.roomConfirmorid", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"roomConfirmorid data Exception. ", e);
		}
		return list;
	}
	
	public List meetroomList(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.meetroomList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"roomnameList data Exception. ", e);
		}
		return list;
	}
	
	public Object equipList(Object parameterObject) throws GlRuntimeException {
		Object  list=null;
		try {
			list=commonSQLMapAdapter.executeQuery("ga.conferenceroom.equipList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"equipList data Exception. ", e);
		}
		return list;
	}
	/**
	 * 添加会议室申请
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object meetList(Object parameterObject) throws GlRuntimeException {
		Object  list=null;
		try {
			list=commonSQLMapAdapter.executeQuery("ga.conferenceroom.meetList", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"equipList data Exception. ", e);
		}
		return list;
	}
	
	/*增加会议室的申请信息和决裁信息*/
	public boolean addconferenceRoomApply(Object parameterObject)
	throws GlRuntimeException {
		//GaAffirmList gaAffirmList = null;
		SimpleMap dateMap = (SimpleMap) parameterObject;
		boolean temp = false;
		int confirmnumber = 0;
		try {
			//commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.insert("ga.conferenceroom.addconferenceRoomApply",parameterObject);
			commonSQLMapAdapter.endTransation();
			temp = true;
			//confirmnumber += 1;
			/*for (int i = 0; i < affirorList.size(); i++) {
				gaAffirmList = (GaAffirmList) affirorList.get(i);
				dateMap.set("AffirmorLevel", gaAffirmList.getAffirmLevel());
				dateMap.set("AffirmorId", gaAffirmList.getAffirmorId());
				commonSQLMapAdapter.insert("ga.conferenceroom.addconferenceRoomAffirm",
						dateMap);
				confirmnumber += 1;
			}
			if (confirmnumber == (affirorList.size() + 1)) {
				commonSQLMapAdapter.commitTransation();
				commonSQLMapAdapter.endTransation();
				temp = true;
			}*/
		
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert conferenceRoomApply data Exception. ", e);
		}
		return temp;
	}
	
	public int getconferenceRoomAffirmListNumber(Object parameterObject) throws GlRuntimeException {
		int temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.conferenceroom.conferenceRoomAffirmListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getconferenceRoomAffirmListNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List getconferenceRoomAffirmList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.conferenceRoomAffirmList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getconferenceRoomAffirmList data Exception. ", e);
		}
		return list;
	}
	public boolean  confirmConferenceRoomAffirm(Object parameterObject) throws GlRuntimeException {
		boolean temp=false;
		try {
			commonSQLMapAdapter.update("ga.conferenceroom.confirmConferenceRoomAffirm", parameterObject);
             temp=true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmConferenceRoomAffirm data Exception. ", e);
		}
		return temp;
	}
	
	public int getConfirmConferenceRoomInfoListNumber(Object parameterObject) throws GlRuntimeException {
		int temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.conferenceroom.ConfirmConferenceRoomInfoListNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"ConfirmConferenceRoomInfoListNumber data Exception. ", e);
		}
		return temp;
	}
	public List getConfirmConferenceRoomInfoList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.ConfirmConferenceRoomInfoList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getConfirmConferenceRoomInfoList data Exception. ", e);
		}
		return list;
	}
	public boolean  deleteConfirmConferenceRoomApply(Object parameterObject) throws GlRuntimeException {
		boolean temp=false;
		try {
			commonSQLMapAdapter.update("ga.conferenceroom.deleteConfirmConferenceRoomApply", parameterObject);
             temp=true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteConfirmConferenceRoomApply data Exception. ", e);
		}
		return temp;
	}
	
	public List getAllUsingConfirmConferenceRoom(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.allUsingConfirmConferenceRoom",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getAllUsingConfirmConferenceRoom data Exception. ", e);
		}
		return list;
	}
	
	public List getconfirm(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.getconfirm1",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getconfirm data Exception. ", e);
		}
		return list;
	}
	
	public List selectConfirmoridStr(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("ga.conferenceroom.selectConfirmoridStr",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"selectConfirmoridStr data Exception. ", e);
		}
		return list;
	}
	
	public String  getapplyemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull((commonSQLMapAdapter.executeQuery("ga.express.getapplyemail", parameterObject))).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getapplyemail data Exception. ", e);
		}
		return temp;
	}
	
	public String  getupaffrimemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.express.getupaffrimemail", parameterObject)).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getupaffrimemail data Exception. ", e);
		}
		return temp;
	}
	public int  getUpAffrimNumber(Object parameterObject) throws GlRuntimeException {
		int  temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.express.getUpAffrimNumber", parameterObject).toString());

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getUpAffrimNumber data Exception. ", e);
		}
		return temp;
	}
	
	public List getsendToExpressApplyPage(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.getExpress1",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpress data Exception. ", e);
		}
		return list;
	}
	
	public List getsendToExpressApplyPage1(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.getExpress2",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpress data Exception. ", e);
		}
		return list;
	}
	
	public List getsendToExpressApplyPage2(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.getExpress",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getExpress data Exception. ", e);
		}
		return list;
	}
	
	public List getCompany(Object parameterObject) throws GlRuntimeException {
		List  list=null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.getCompany",parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getCompany data Exception. ", e);
		}
		return list;
	}
	
}
