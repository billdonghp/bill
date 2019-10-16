package com.ait.ga.dao.visiter;

import java.util.List;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.AccommodationBean;
import com.ait.ga.bean.EatryBean;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.bean.PresentBean;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-24
 * 
 */
public class VisiterDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(ListRecordDAO.class);

	public VisiterDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	
	public Object visiterApplySave(Object parameterObject,String[] affirmor, List gigtList,List abList,List ebList) throws GlRuntimeException {
		Object result;
		GaAffirmList gaAffirmList = new GaAffirmList();
		SimpleMap paratmerList =new SimpleMap();
		paratmerList=(SimpleMap)parameterObject;
		String personId = (String) paratmerList.get("supervisor");
		try {
			commonSQLMapAdapter.startTransaction();
			result = commonSQLMapAdapter.insert("ga.visiter.visiterApplySave", parameterObject);
			for(int i=0;i<gigtList.size();i++){
				 PresentBean presentBean = new PresentBean();
				 presentBean = (PresentBean)gigtList.get(i);
				 SimpleMap parameterObject1 = new SimpleMap();
				 parameterObject1.put("vist_name", presentBean.getVist_name());
				 parameterObject1.put("corpopation", presentBean.getCorpopation());
				 parameterObject1.put("jobs", presentBean.getJobs());
				 parameterObject1.put("differ", presentBean.getDiffer());
				 parameterObject1.put("telephone", presentBean.getTelephone());
				 parameterObject1.put("present", presentBean.getPresent());
				 parameterObject1.put("present_num", presentBean.getPresent_num());
				 parameterObject1.put("memo", presentBean.getMemo());
				 parameterObject1.put("ga_vister_apply_id", paratmerList.get("applyId"));
				 parameterObject1.put("person_id", paratmerList.get("supervisor"));
				 parameterObject1.put("present_appply", presentBean.getPresent_apply());
				 commonSQLMapAdapter.insert("ga.visiter.visiterManagementSave", parameterObject1);
			}
			
			for(int i=0;i<abList.size();i++){
				 AccommodationBean accommodationBean = new AccommodationBean();
				 accommodationBean = (AccommodationBean)abList.get(i);
				 SimpleMap parameterObject2 = new SimpleMap();
				 parameterObject2.put("accommodation_name", accommodationBean.getAccommodation_name());
				 parameterObject2.put("accommodation_standard", accommodationBean.getAccommodation_standard());
				 parameterObject2.put("accommodation_in_date", accommodationBean.getAccommodation_in_date());
				 parameterObject2.put("accommodation_in_time", accommodationBean.getAccommodation_in_time());
				 parameterObject2.put("accommodation_out_date", accommodationBean.getAccommodation_out_date());
				 parameterObject2.put("accommodation_out_time", accommodationBean.getAccommodation_out_time());
				 parameterObject2.put("accommodation_memo", accommodationBean.getAccommodation_memo());
				 parameterObject2.put("ga_vister_apply_id", paratmerList.get("applyId"));
				 parameterObject2.put("person_id", paratmerList.get("supervisor"));
				 commonSQLMapAdapter.insert("ga.visiter.visiterAccommodationSave", parameterObject2);
			}
			
			for(int i=0;i<ebList.size();i++){
				 EatryBean eatryBean = new EatryBean();
				 eatryBean = (EatryBean)ebList.get(i);
				 SimpleMap parameterObject3 = new SimpleMap();
				 parameterObject3.put("visiter_eatry_distinction", eatryBean.getVisiter_eatry_distinction());
				 parameterObject3.put("visiter_eatry_name", eatryBean.getVisiter_eatry_name());
				 parameterObject3.put("visiter_eatry_date", eatryBean.getVisiter_eatry_date());
				 parameterObject3.put("visiter_eatry_time", eatryBean.getVisiter_eatry_time());
				 parameterObject3.put("visiter_eatry_type", eatryBean.getVisiter_eatry_type());
				 parameterObject3.put("visiter_eatry_number", eatryBean.getVisiter_eatry_number());
				 parameterObject3.put("visiter_eatry_memo", eatryBean.getVisiter_eatry_memo());
				 parameterObject3.put("ga_vister_apply_id", paratmerList.get("applyId"));
				 parameterObject3.put("person_id", paratmerList.get("supervisor"));
				 commonSQLMapAdapter.insert("ga.visiter.visiterEatrySave", parameterObject3);
			}
			

//			for(int i=0;i<affirmor.size();i++){
//				gaAffirmList=(GaAffirmList)affirmor.get(i);
//				paratmerList.set("AFFIRMOR_NAME", gaAffirmList.getAffirmorName());
//				paratmerList.set("AFFIRM_LEVEL", gaAffirmList.getAffirmLevel());
//				paratmerList.set("AFFIRMOR_ID", gaAffirmList.getAffirmorId());
//				commonSQLMapAdapter.insert("ga.visiter.visiterAffirmSave",paratmerList);
//			}
			for(int i=0;i<affirmor.length;i++){
				paratmerList.set("AFFIRM_LEVEL", i+1);
				paratmerList.set("AFFIRMOR_ID", affirmor[i]);
				int affirmFlag=0;
				if(personId.equals(affirmor[i])){
					affirmFlag=1;
				}
				paratmerList.set("affirmFlag", affirmFlag);
				commonSQLMapAdapter.insert("ga.visiter.visiterAffirmSave",paratmerList);
			}
			
			commonSQLMapAdapter.commitTransation();

			//推送
			DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_VISITER, StringUtil.checkNull(paratmerList.get("applyId")));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterApplySave list by paging Exception. ", e);
		}finally {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return result;
	}
	
	
	
	public Object accessApplySave(Object parameterObject, List gigtList) throws GlRuntimeException {
		Object result;
		GaAffirmList gaAffirmList = new GaAffirmList();
		SimpleMap paratmerList =new SimpleMap();
		paratmerList=(SimpleMap)parameterObject;
		try {
			commonSQLMapAdapter.startTransaction();
			
			result = commonSQLMapAdapter.insert("ga.visiter.accessApplySave", parameterObject);
			for(int i=0;i<gigtList.size();i++){
				 PresentBean presentBean = new PresentBean();
				 presentBean = (PresentBean)gigtList.get(i);
				 SimpleMap parameterObject1 = new SimpleMap();
				 parameterObject1.put("vist_name", presentBean.getVist_name());
				 parameterObject1.put("corpopation", presentBean.getCorpopation());
				 parameterObject1.put("jobs", presentBean.getJobs());
				 parameterObject1.put("differ", presentBean.getDiffer());
				 parameterObject1.put("telephone", presentBean.getTelephone());
				 parameterObject1.put("present", presentBean.getPresent());
				 parameterObject1.put("present_num", presentBean.getPresent_num());
				 parameterObject1.put("memo", presentBean.getMemo());
				 parameterObject1.put("ga_vister_apply_id", paratmerList.get("applyId"));
				 parameterObject1.put("person_id", paratmerList.get("supervisor"));
				 parameterObject1.put("present_appply",presentBean.getPresent_apply());
				 commonSQLMapAdapter.insert("ga.visiter.accessManagementSave", parameterObject1);
			}
			

//			for(int i=0;i<affirmor.size();i++){
//				gaAffirmList=(GaAffirmList)affirmor.get(i);
//				paratmerList.set("AFFIRMOR_NAME", gaAffirmList.getAffirmorName());
//				paratmerList.set("AFFIRM_LEVEL", gaAffirmList.getAffirmLevel());
//				paratmerList.set("AFFIRMOR_ID", gaAffirmList.getAffirmorId());
//				commonSQLMapAdapter.insert("ga.visiter.visiterAffirmSave",paratmerList);
//			}
			
			
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("accessApplySave list by paging Exception. ", e);
		}finally {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return result;
	}
	
	public List apiLanguage(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.apiLanguage", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" apiLanguage list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allvisiterApplyInformation(Object parameterObject , int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allvisiterApplyInformation", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allvisiterApplyInformation list by paging Exception. ", e);
		}
		return result;
	}
	
	public int allvisiterApplyInformationCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.visiter.allvisiterApplyInformationCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allvisiterApplyInformationCount list by paging Exception. ", e);
		}
		return result;
	}
	public int allvisiterAffrimInfoCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.visiter.allvisiterAffrimInfoCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allvisiterAffrimInfoCount list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allvisiterAffrimInfoList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allvisiterAffrimInfoList", parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allvisiterAffrimInfoList list by paging Exception. ", e);
		}
		return result;
	}
	public List allVisiterApproval(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allVisiterApproval", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allVisiterApproval list by paging Exception. ", e);
		}
		return result;
	}
	
	public String allVisiterApproval1(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.visiter.allVisiterApproval1", parameterObject),"0").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allVisiterApproval1 list by paging Exception. ", e);
		}
		return result;
	}
	
	public String allVisiterApproval2(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ga.visiter.allVisiterApproval2", parameterObject),"1").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allVisiterApproval2 list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getAffirmor(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.getAffirmor", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAffirmor list by paging Exception. ", e);
		}
		return result;
	}
	
	public List affirmByInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.affirmByInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("affirmByInformation list by paging Exception. ", e);
		}
		return result;
	}

	public List affirmByInformation1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.affirmByInformation1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("affirmByInformation1 list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updateAffirmFlag(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.update("ga.visiter.updateAffirmFlag", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateAffirmFlag list by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updateApproval(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("ga.visiter.deleteAffirm", parameterObject);
			commonSQLMapAdapter.delete("ga.visiter.deletePresent",parameterObject);
			commonSQLMapAdapter.delete("ga.visiter.deleteAccommodation",parameterObject);
			commonSQLMapAdapter.delete("ga.visiter.deleteEatry",parameterObject);
			result = commonSQLMapAdapter.delete("ga.visiter.deleteApply", parameterObject);
		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException("updateApproval list by paging Exception. ", e);
		}
		return result;
	}
	
	public int getSeq(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ga.visiter.getSeq", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSeq list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getAllVisiterCountry(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.getAllVisiterCountry", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllVisiterCountry list by paging Exception. ", e);
		}
		return result;
	}
	
	public List visiterType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visiterType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterType list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getDept(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.getDept", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDept list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allLanguage(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allLanguage", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allLanguage list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allDevice(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allDevice", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allDevice list by paging Exception. ", e);
		}
		return result;
	}
	
	public List file(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allFile", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allDevice list by paging Exception. ", e);
		}
		return result;
	}
	
	public List visit_item(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visit_item", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allDevice list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allVisiterDistiniction(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.allVisiterDistiniction", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allVisiterDistiniction list by paging Exception. ", e);
		}
		return result;
	}
	public List visiterViewDetail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visiterViewDetail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterViewDetail list by paging Exception. ", e);
		}
		return result;
	}
	public List visiterViewDetail1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visiterViewDetail1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterViewDetail list by paging Exception. ", e);
		}
		return result;
	}
	
	public List visiterViewDetail2(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visiterViewDetail2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterViewDetai2 list by paging Exception. ", e);
		}
		return result;
	}
	
	public List visiterViewDetail3(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visiterViewDetail3", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterViewDetai3 list by paging Exception. ", e);
		}
		return result;
	}
	
	public List visiterViewDetailReport(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.visiterViewDetailReport", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("visiterViewDetailReport list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getconfirm(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.getconfirm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getconfirm list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getSingleInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.getSingleInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSingleInfo list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getTopAffirmId(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ga.visiter.getTopAffirmId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSingleInfo list by paging Exception. ", e);
		}
		return result;
	}
	public String  getapplyemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull((commonSQLMapAdapter.executeQuery("ga.visiter.getapplyemail", parameterObject))).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getapplyemail data Exception. ", e);
		}
		return temp;
	}
	public void  confirmVisiterApply(Object parameterObject) throws GlRuntimeException {
	
		try {
			commonSQLMapAdapter.update("ga.visiter.confirmVisiterApply", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"confirmVisiterApply data Exception. ", e);
		}
		
	}
	public String   getupaffrimemail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull((commonSQLMapAdapter.executeQuery("ga.visiter.getupaffrimemail", parameterObject))).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getupaffrimemail data Exception. ", e);
		}
		return temp;
	}
	
	public String   getAccessApplyId(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull((commonSQLMapAdapter.executeQuery("ga.visiter.getAccessApplyId", parameterObject))).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getAccessApplyId data Exception. ", e);
		}
		return temp;
	}
}
