package com.ait.safe.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.safe.bean.PositionInfo;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class JobHealthDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(JobHealthDAO.class);

	public JobHealthDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List JobHealthInformation(Object parameterObject , int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.JobHealthInformation", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("JobHealthInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List JobHealthInformationReport(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.JobHealthInformationReport", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("JobHealthInformationReport by paging Exception. ", e);
		}
		return result;
	}
	
	public String getMedicalFlag(Object parameterObject) throws GlRuntimeException {

		String result;
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("safe.jobHealth.getMedicalFlag", parameterObject)).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getMedicalFlag by paging Exception. ", e);
		}
		return result;
	}
	
	public int JobHealthInformationCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.jobHealth.JobHealthInformationCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("JobHealthInformationCount by paging Exception. ", e);
		}
		return result;
	}
	
	public List getJobPositionInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getJobPositionInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getJobPositionInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List getPositionInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getPositionInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getPositionInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List getDiseaseInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getDiseaseInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDiseaseInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List getjob_poditionInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getjob_poditionInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDiseaseInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List getDiseaseTypeInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getDiseaseTypeInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDiseaseTypeInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List getPositionDiseaseDiseaseType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getPositionDiseaseDiseaseType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getPositionDiseaseDiseaseType by paging Exception. ", e);
		}
		return result;
	}
	
	public Object getPositionDiseaseDiseaseTypeAdd(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("safe.jobHealth.getPositionDiseaseDiseaseTypeAdd", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getPositionDiseaseDiseaseTypeAdd by paging Exception. ", e);
		}
		return result;
	}
	
	public List empInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.empInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empInformation by paging Exception. ", e);
		}
		return result;
	}
	
	
	public PositionInfo getDisease(Object parameterObject) throws GlRuntimeException {

		PositionInfo result;
		try {
			result = (PositionInfo)commonSQLMapAdapter.executeQuery("safe.jobHealth.getDisease", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public PositionInfo getDiseaseType(Object parameterObject) throws GlRuntimeException {

		PositionInfo result;
		try {
			result = (PositionInfo)commonSQLMapAdapter.executeQuery("safe.jobHealth.getDiseaseType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public int getJPSeq(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.jobHealth.getJPSeq", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getJPSeq by paging Exception. ", e);
		}
		return result;
	}
	
	public Object addPositionInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("safe.jobHealth.addPositionInformation", parameterObject);
			commonSQLMapAdapter.insert("safe.jobHealth.jobPosition", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object addMedicalInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("safe.jobHealth.addMedicalInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object addPosition(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result =commonSQLMapAdapter.insert("safe.jobHealth.jobPosition", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addPosition by paging Exception. ", e);
		}
		return result;
	}
	public Object updateJobPosition(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result =commonSQLMapAdapter.insert("safe.jobHealth.updateJobPosition", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateJobPosition by paging Exception. ", e);
		}
		return result;
	}
	
	public List getAllPositionInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getAllPositionInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllPositionInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public List getAllMedicalInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getAllMedicalInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllMedicalInformation by paging Exception. ", e);
		}
		return result;
	}
	public List getThisFlagMedicalInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getThisFlagMedicalInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getThisFlagMedicalInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updateMedicalInformation(Object parameterObject) throws GlRuntimeException {
		Object result;
		try {
				result = commonSQLMapAdapter.update("safe.jobHealth.updateMedicalInformation", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateMedicalPositionInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updatePositionInformation(Object parameterObject, int iSize) throws GlRuntimeException {
		Object result;
		try {
			String medicalDate = "";
			String medicalflag = "";
			String remark = "";
			String medicalstateflag ="";
			String jobHealth ="";
			String remarkText = "";
			SimpleMap simpleMap1 = new SimpleMap();
			SimpleMap simpleMap = (SimpleMap)parameterObject;
			simpleMap1.set("supervisor", simpleMap.getString("supervisor"));
			simpleMap1.set("seq", simpleMap.getString("seq"));
			simpleMap1.set("tempjobId", simpleMap.getString("tempjobId"));
			simpleMap1.set("empiD", simpleMap.getString("empiD"));
			simpleMap1.set("Position", simpleMap.getString("Position"));
			simpleMap1.set("JOB_DISEASE_CODE", simpleMap.getString("JOB_DISEASE_CODE"));
			simpleMap1.set("JOB_DISEASE_TYPE_CODE", simpleMap.getString("JOB_DISEASE_TYPE_CODE"));
			simpleMap1.set("StartDates", simpleMap.getString("StartDates"));
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.delete("safe.jobHealth.delMedicalInformation", parameterObject);
			
			for (int i = 0; i < iSize; i++) {
				
				if(simpleMap.getString("medicalDate" + i) == null)
					continue;
				medicalDate = simpleMap.getString("medicalDate" + i);
				medicalflag = simpleMap.getString("medicalflag" + i);
				remark = simpleMap.getString("remark" + i);
				medicalstateflag = simpleMap.getString("medicalstateflag" + i);
				jobHealth = simpleMap.getString("jobHealth" + i);
				remarkText = simpleMap.getString("remarkText" + i);
				
				simpleMap1.set("medicalDate", medicalDate);
				if (medicalstateflag.equals("come")) {
					simpleMap1.set("medicalstate", "入岗体检");
				}
				if (medicalstateflag.equals("on")) {
					simpleMap1.set("medicalstate", "在岗体检");
				}
				if (medicalstateflag.equals("out")) {
					simpleMap1.set("medicalstate", "离岗体检");
				}
				
				if (medicalflag.equals("1")) {
					simpleMap1.set("medicalflag", "正常");
					simpleMap1.set("remarkText", remarkText);
				}
				if (medicalflag.equals("2")) {
					simpleMap1.set("medicalflag", "异常");
					simpleMap1.set("remark", remark);
				}
				simpleMap1.set("jobHealth", jobHealth);
				commonSQLMapAdapter.insert("safe.jobHealth.addMedicalInformation", simpleMap1);
			}
				result = commonSQLMapAdapter.update("safe.jobHealth.updateAddPositionInformation", parameterObject);
				commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateMedicalPositionInformation by paging Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return result;
	}
	
	public Object deleteMedicalPositionInformation(Object parameterObject) throws GlRuntimeException {
		Object result;
		try {
			commonSQLMapAdapter.startTransaction();
				result = commonSQLMapAdapter.delete("safe.jobHealth.deletePositionInformation", parameterObject);
				result = commonSQLMapAdapter.delete("safe.jobHealth.deleteMedicalInformation", parameterObject);
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateMedicalPositionInformation by paging Exception. ", e);
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
	
	public List getJobHealthDetail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getJobHealthDetail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getJobHealthDetail by paging Exception. ", e);
		}
		return result;
	}
	
	public Object deletePositionDiseaseDiseaseType(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("safe.jobHealth.deletePositionDiseaseDiseaseType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("deletePositionDiseaseDiseaseType by paging Exception. ", e);
		}
		return result;
	}
	
	public void getPositionDiseaseDiseaseTypeUpdate(Object parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.delete("safe.jobHealth.getPositionDiseaseDiseaseTypeUpdate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getPositionDiseaseDiseaseTypeUpdate by paging Exception. ", e);
		}
	}
	
	public Object deletePositionDiseaseDiseaseTypeguanxi(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("safe.jobHealth.deletePositionDiseaseDiseaseTypeguanxi", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("deletePositionDiseaseDiseaseTypeguanxi by paging Exception. ", e);
		}
		return result;
	}
	
	public List getSingleInformation(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getSingleInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSingleInformation by paging Exception. ", e);
		}
		return result;
	}
	
	public Object deletejobHealthView(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			commonSQLMapAdapter.startTransaction();
			result = commonSQLMapAdapter.delete("safe.jobHealth.deletejobHealthView", parameterObject);
			         commonSQLMapAdapter.delete("safe.jobHealth.deleteMedicalInformation", parameterObject);
			         commonSQLMapAdapter.delete("safe.jobHealth.position", parameterObject);
			commonSQLMapAdapter.commitTransation() ;        
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("deletejobHealthView by paging Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return result;
	}
	
	public List getAllEmpid(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getAllEmpid", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllEmpid by paging Exception. ", e);
		}
		return result;
	}
	
	public List getPositionOrder(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getPositionOrder", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getPositionOrder by paging Exception. ", e);
		}
		return result;
	}
	
	public int getCHANGE_POSITION_ORDER_FLAG(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.jobHealth.getCHANGE_POSITION_ORDER_FLAG", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getCHANGE_POSITION_ORDER_FLAG by paging Exception. ", e);
		}
		return result;
	}
	
	public Object updatePositionCHANGE_POSITION_FLAG(Object parameterObject) throws GlRuntimeException {
		Object result;
		try {
				result = commonSQLMapAdapter.update("safe.jobHealth.updatePositionCHANGE_POSITION_FLAG", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updatePositionCHANGE_POSITION_FLAG by paging Exception. ", e);
		}
		return result;
	}
	
	public List getPositionInfo(Object parameterObject) throws GlRuntimeException {
		List result;
		try {
				result = commonSQLMapAdapter.executeQueryForMulti("safe.jobHealth.getPositionInfo", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getPositionInfo by paging Exception. ", e);
		}
		return result;
	}
}
