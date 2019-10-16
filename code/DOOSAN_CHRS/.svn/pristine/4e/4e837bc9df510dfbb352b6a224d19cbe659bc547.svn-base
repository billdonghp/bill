package com.ait.pu.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-20
 * 
 */
public class VisiterManagementDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private Logger logger = null;

	public VisiterManagementDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List SearchAllVisiterManagementInformation(Object parameterObject ,int currentPage , int pageSize) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.SearchAllVisiterManagementInformation", parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"SearchAllVisiterManagementInformation data Exception. ", e);
		}
		return result;
	}
	
	public List allVisiterManagementInformationApply(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allVisiterManagementInformationApply", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterManagementInformationApply data Exception. ", e);
		}
		return result;
	}
	
	public int SearchAllVisiterManagementInformationCount(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.SearchAllVisiterManagementInformationCount",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"SearchAllVisiterManagementInformationCount data Exception. ", e);
		}
		return result;
	}
	
	public List allVisiterManagementModifyView(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allVisiterManagementModifyView", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterManagementModifyView data Exception. ", e);
		}
		return result;
	}
	
	public List visiterType(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.visiterType", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterType data Exception. ", e);
		}
		return result;
	}
	
	public List visiterCountry(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.visiterCountry", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterCountry data Exception. ", e);
		}
		return result;
	}
	
	public List allapiLanguage(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allapiLanguage", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allapiLanguage data Exception. ", e);
		}
		return result;
	}
	
	public Object updateVisiterManagementModify(Object parameterObject) throws GlRuntimeException {
		Object result;
		try {
			commonSQLMapAdapter.startTransaction();
			result = NumberUtil.parseInt(commonSQLMapAdapter.update("pu.visiterManagement.updateVisiterManagementModify", parameterObject));
			commonSQLMapAdapter.update("pu.visiterManagement.updateVisiterPresentDelete", parameterObject);
			SimpleMap simpleMap = new SimpleMap();
			simpleMap = (SimpleMap)parameterObject;
			int num = simpleMap.getInt("temp1");
			for(int i=0;i<num;i++){
				SimpleMap simpleMap1 = new SimpleMap();
				simpleMap1.put("id", simpleMap.getString("id"+i));
				simpleMap1.put("visiterName", simpleMap.getString("visiterName"+i));//姓名
				simpleMap1.put("visiterDuty", simpleMap.getString("visiterDuty"+i));//职务
				simpleMap1.put("Distinction", simpleMap.getString("Distinction"+i));//区分
				simpleMap1.put("visiterCompany", simpleMap.getString("visiterCompany"+i));//工作单位
				simpleMap1.put("ContactTel", simpleMap.getString("ContactTel"+i));
				simpleMap1.put("GiftYesOrNo", simpleMap.getString("visiterGiftYesOrNo"+i));
				simpleMap1.put("GiftName", simpleMap.getString("GiftName"+i));
				simpleMap1.put("GiftNumber", simpleMap.getString("GiftNumber"+i));
				simpleMap1.put("memo", simpleMap.getString("memo"+i));
				simpleMap1.put("ApplyId", simpleMap.getString("ApplyId"));
				result = NumberUtil.parseInt(commonSQLMapAdapter.insert("pu.visiterManagement.updateVisiterPresentModify", simpleMap1));
			}
			
			commonSQLMapAdapter.update("pu.visiterManagement.updateVisiterAccommodationDelete", parameterObject);
			SimpleMap simpleMap2 = new SimpleMap();
			simpleMap2 = (SimpleMap)parameterObject;
			int num1 = simpleMap2.getInt("temp2");
			for(int i=0;i<num1;i++){
				SimpleMap simpleMap3 = new SimpleMap();
				simpleMap3.put("id", simpleMap.getString("id"+i));
				simpleMap3.put("accommodationName", simpleMap2.getString("accommodationName"+i));//姓名
				simpleMap3.put("accommodationStandard", simpleMap2.getString("accommodationStandard"+i));
				simpleMap3.put("accommodationInDate", simpleMap2.getString("accommodationInDate"+i));
				simpleMap3.put("accommodationInTime", StringUtil.checkNull(simpleMap2.getString("accommodationInHour"+i))+":"+StringUtil.checkNull(simpleMap2.getString("accommodationInMin"+i)));
				simpleMap3.put("accommodationOutTime", StringUtil.checkNull(simpleMap2.getString("accommodationOutHour"+i))+":"+StringUtil.checkNull(simpleMap2.getString("accommodationOutMin"+i)));
				simpleMap3.put("accommodationOutDate", simpleMap2.getString("accommodationOutDate"+i));
				simpleMap3.put("accommodationMemo", simpleMap2.getString("accommodationMemo"+i));
				simpleMap3.put("ApplyId", simpleMap2.getString("ApplyId"));
				result = NumberUtil.parseInt(commonSQLMapAdapter.insert("pu.visiterManagement.updateVisiterAccommodationModify", simpleMap3));
			}
			
			commonSQLMapAdapter.update("pu.visiterManagement.updateVisiterEatryDelete", parameterObject);
			SimpleMap simpleMap4 = new SimpleMap();
			simpleMap4 = (SimpleMap)parameterObject;
			int num2 = simpleMap4.getInt("temp3");
			for(int i=0;i<num2;i++){
				SimpleMap simpleMap5 = new SimpleMap();
				simpleMap5.put("id", simpleMap.getString("id"+i));
				simpleMap5.put("visiterEatryDistinction", simpleMap4.getString("visiterEatryDistinction"+i));//姓名
				simpleMap5.put("visiterEatryName", simpleMap4.getString("visiterEatryName"+i));
				simpleMap5.put("visiterEatryDate", simpleMap4.getString("visiterEatryDate"+i));
				simpleMap5.put("visiterEatryTime", StringUtil.checkNull(simpleMap4.getString("visiterEatryHour"+i))+":"+StringUtil.checkNull(simpleMap4.getString("visiterEatryMin"+i)));
				simpleMap5.put("visiterEatryType", simpleMap4.getString("visiterEatryType"+i));
				simpleMap5.put("visiterEatryNumber", simpleMap4.getString("visiterEatryNumber"+i));
				simpleMap5.put("visiterEatryMemo", simpleMap4.getString("visiterEatryMemo"+i));
				simpleMap5.put("ApplyId", simpleMap4.getString("ApplyId"));
				result = NumberUtil.parseInt(commonSQLMapAdapter.insert("pu.visiterManagement.updateVisiterEatryModify", simpleMap5));
			}
			
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateVisiterAccommodationModify data Exception. ", e);
		}
		finally {
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return result;
	}
	
	public int visiterChinese(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.visiterChinese",parameterObject));
		} catch (Exception e) {
			e.printStackTrace() ;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterChinese data Exception. ", e);
		}
		return result;
	}
	
	public int visiterKorea(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.visiterKorea",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterKorea data Exception. ", e);
		}
		return result;
	}
	
	public int visiterAnnather(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.visiterAnnather",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterAnnather data Exception. ", e);
		}
		return result;
	}
	
	public int visiterChineseNum(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.visiterChineseNum",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterChineseNum data Exception. ", e);
		}
		return result;
	}
	
	public int visiterKoreaNum(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.visiterKoreaNum",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterKoreaNum data Exception. ", e);
		}
		return result;
	}
	
	public int visiterAnnatherNum(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.visiterAnnatherNum",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"visiterAnnatherNum data Exception. ", e);
		}
		return result;
	}
	
	public List allVisiterType(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allVisiterType",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterType data Exception. ", e);
		}
		return result;
	}
	
	public List allVisiterTypeNum(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allVisiterTypeNum",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterTypeNum data Exception. ", e);
		}
		return result;
	}
	
	public List allVisiterTypeVisiterNum(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allVisiterTypeVisiterNum",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterTypeVisiterNum data Exception. ", e);
		}
		return result;
	}
	
	public int allVisiterTypeNumSum(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.allVisiterTypeNumSum",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterTypeNumSum data Exception. ", e);
		}
		return result;
	}
	
	public int allVisiterTypeVisiterNumSum(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.visiterManagement.allVisiterTypeVisiterNumSum",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allVisiterTypeVisiterNumSum data Exception. ", e);
		}
		return result;
	}
	
	public List allDistiniction() throws GlRuntimeException {
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.visiterManagement.allDistiniction");
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"allDistiniction data Exception. ", e);
		}
		return result;
	}
	
	public Object UpdateModifyFlag(Object parameterObject) throws GlRuntimeException {
		Object result;
		try {
			result = commonSQLMapAdapter.update("pu.visiterManagement.UpdateModifyFlag",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"UpdateModifyFlag data Exception. ", e);
		}
		return result;
	}
	public void deleteVisiterManagement(Object parameterObject) throws GlRuntimeException {
		
		try {
			commonSQLMapAdapter.update("pu.visiterManagement.deleteVisiterManagement",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteVisiterManagement data Exception. ", e);
		}
		
	}
	
	public String  getVisiterApplyEmail(Object parameterObject) throws GlRuntimeException {
		String  temp="";
		try {
			temp=StringUtil.checkNull((commonSQLMapAdapter.executeQuery("pu.visiterManagement.getVisiterApplyEmail", parameterObject))).toString();

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getVisiterApplyEmail data Exception. ", e);
		}
		return temp;
	}
}