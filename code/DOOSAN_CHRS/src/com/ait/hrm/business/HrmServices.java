package com.ait.hrm.business;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import java.sql.*;

import com.ait.ar.dao.ArDAOFactory;
import com.ait.ar.dao.task.AttTaskDAO;
import com.ait.core.db.jdbc.JdbcTemplate;
import com.ait.core.exception.GlRuntimeException;
import com.ait.hrm.dao.common.HrmCommonDAO;
import com.ait.hrm.dao.contract.HrmContractDao;
import com.ait.hrm.dao.empinfo.AccountsInfoDAO;
import com.ait.hrm.dao.empinfo.AppendInfoDAO;
import com.ait.hrm.dao.empinfo.BasicInfoDAO;
import com.ait.hrm.dao.empinfo.EvaluateInfoDAO;
import com.ait.hrm.dao.empinfo.HealthInfoDAO;
import com.ait.hrm.dao.empinfo.OtherInfoDAO;
import com.ait.hrm.dao.empinfo.RelationInfoDAO;
import com.ait.hrm.dao.empinfo.RewardInfoDAO;
import com.ait.hrm.dao.empinfo.TrainingInfoDAO;
import com.ait.hrm.dao.hrcommand.DispatchComDAO;
import com.ait.hrm.dao.hrcommand.EmpHireComDAO;
import com.ait.hrm.dao.hrcommand.ExpInsideComDAO;
import com.ait.hrm.dao.hrcommand.PluralityComDAO;
import com.ait.hrm.dao.hrcommand.PunishComDAO;
import com.ait.hrm.dao.hrcommand.ResignComDAO;
import com.ait.hrm.dao.hrcommand.SuspendComDAO;
import com.ait.hrm.dao.hrcommand.TrainingComDAO;
import com.ait.hrm.dao.report.InfoDAO;
import com.ait.hrm.dao.tipmessagesearch.TipMessageViewDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.SqlUtil;
import com.ait.hrm.bean.Department;
import com.ait.hrm.dao.department.DepartMentChangeDAO;

;

public class HrmServices {

	private static HrmServices hrmServices;

	private DepartMentChangeDAO departmentDAO;

	private HrmCommonDAO hrmCommonDAO;

	private BasicInfoDAO basicInfoDAO;

	private HrmContractDao hrmContractDao;

	private RelationInfoDAO relationInfoDAO;

	private HealthInfoDAO healthInfoDAO;

	private OtherInfoDAO otherInfoDAO;

	private AppendInfoDAO appendInfoDAO;

	private RewardInfoDAO rewardInfoDAO;

	private TrainingInfoDAO trainingInfoDAO;

	private EvaluateInfoDAO evaluateInfoDAO;

	private AccountsInfoDAO accountsInfoDAO;

	private EmpHireComDAO empHireComDAO;

	private ExpInsideComDAO expInsideComDAO;

	private DispatchComDAO dispatchComDAO;

	private PluralityComDAO pluralityComDAO;

	private PunishComDAO punishComDAO;

	private ResignComDAO resignComDAO;

	private SuspendComDAO suspendComDAO;

	private TrainingComDAO trainingComDAO;

	private InfoDAO infoDAO;

	private TipMessageViewDAO tipDAO;

	private HrmServices() {

		tipDAO = new TipMessageViewDAO();

		departmentDAO = new DepartMentChangeDAO();
		
		hrmCommonDAO = new HrmCommonDAO();

		hrmContractDao = new HrmContractDao();

		basicInfoDAO = new BasicInfoDAO();

		relationInfoDAO = new RelationInfoDAO();

		healthInfoDAO = new HealthInfoDAO();

		otherInfoDAO = new OtherInfoDAO();

		appendInfoDAO = new AppendInfoDAO();

		rewardInfoDAO = new RewardInfoDAO();

		trainingInfoDAO = new TrainingInfoDAO();

		evaluateInfoDAO = new EvaluateInfoDAO();

		accountsInfoDAO = new AccountsInfoDAO();

		empHireComDAO = new EmpHireComDAO();

		expInsideComDAO = new ExpInsideComDAO();

		dispatchComDAO = new DispatchComDAO();

		pluralityComDAO = new PluralityComDAO();

		punishComDAO = new PunishComDAO();

		resignComDAO = new ResignComDAO();

		suspendComDAO = new SuspendComDAO();

		trainingComDAO = new TrainingComDAO();

		infoDAO = new InfoDAO();

	}

	/**
	 * 
	 * @return HrmServices
	 */
	public static HrmServices getInstance() {

		if (hrmServices != null)
			return hrmServices;
		else
			return new HrmServices();
	}

	/**
	 * retrieve post list
	 * 
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List retrievePostList() throws GlRuntimeException {

		return hrmCommonDAO.retrievePostList();
	}

	public void changeDepartmentByMonth() throws GlRuntimeException {

		departmentDAO.changeDepartmentByMonth();
	}

	public boolean isExistHistoryData(Object obj) throws GlRuntimeException {

		return departmentDAO.isExistHistoryData(obj);
	}

	public List retrievePostGradeList() throws GlRuntimeException {

		return hrmCommonDAO.retrievePostGradeList();
	}

	public List retrieveGradeLevelList() throws GlRuntimeException {

		return hrmCommonDAO.retrieveGradeLevelList();
	}

	/**
	 * 取得员工简单数据
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */

	public Object getSimpleEmpByEmpId(Object parameterObject) throws GlRuntimeException {
		return hrmCommonDAO.getSimpleEmpByEmpId(parameterObject);
	}

	/**
	 * 取得员工姓名
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getChineseNameByEmpId(Object parameterObject) throws GlRuntimeException {
		return hrmCommonDAO.getChineseNameByEmpId(parameterObject);

	}

	/**
	 * 取得员工拼音
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getChinesePinYinNameByEmpId(Object parameterObject) throws GlRuntimeException {
		return hrmCommonDAO.getChinesePinYinNameByEmpId(parameterObject);

	}

	/**
	 * retrieve expired contract
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public List searchExpiredContract(Object parameterObject) throws GlRuntimeException {
		return hrmContractDao.searchExpiredContract(parameterObject);
	}

	public List searchExpiredContract(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return hrmContractDao.searchExpiredContract(parameterObject, currentPage, pageSize);
	}

	public Object searchExpiredContractCnt(Object parameterObject) throws GlRuntimeException {
		return hrmContractDao.searchExpiredContractCnt(parameterObject);
	}

	/**
	 * inserting multi expired contracts
	 * 
	 * @param List
	 *            parameterList
	 * @throws GlRuntimeException
	 */
	public void addMultiExpiredContract(List parameterList) throws GlRuntimeException {
		hrmContractDao.addMultiExpiredContract(parameterList);
	}

	/**
	 * inserting multi simple contracts
	 * 
	 * @param List
	 *            parameterList
	 * @throws GlRuntimeException
	 */
	public void addMultiSimpleContract(List parameterList) throws GlRuntimeException {
		hrmContractDao.addMultiSimpleContract(parameterList);
	}

	/**
	 * Adding new contract item for someone that has no contract
	 * 
	 * @param List
	 *            parameterList
	 * @return List
	 * @throws GlRuntimeException
	 */
	public void addMultiNewContract(List parameterList) throws GlRuntimeException {
		hrmContractDao.addMultiNewContract(parameterList);
	}

	public String checkNewContractValidity(SimpleMap map) throws GlRuntimeException {
		return hrmContractDao.checkNewContractValidity(map);
	}

	/**
	 * Searching contracts simply
	 * 
	 * @param Object
	 *            parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List searchViewContract(Object parameterObject) throws GlRuntimeException {
		return hrmContractDao.searchViewContract(parameterObject);
	}

	public List searchViewContract(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return hrmContractDao.searchViewContract(parameterObject, currentPage, pageSize);
	}

	public Object searchViewContraCnt(Object parameterObject) throws GlRuntimeException {
		return hrmContractDao.searchViewContractCnt(parameterObject);
	}

	/**
	 * Search employees who have no contracts
	 * 
	 * @return Object object
	 * @throws GlRuntimeException
	 */
	public List searchEmpWithoutContact(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return hrmContractDao.searchEmpWithoutContract(parameterObject, currentPage, pageSize);
	}

	public Object searchEmpWithoutContactCNT(Object parameterObject) throws GlRuntimeException {
		return hrmContractDao.searchEmpWithoutContactCNT(parameterObject);
	}

	/**
	 * Search contract then out a excel file
	 * 
	 * @return List list
	 * @throws GlRuntimeException
	 */
	public List searchContractForOutExcel(Object parameterObject) throws GlRuntimeException {
		return hrmContractDao.searchContractForOutExcel(parameterObject);
	}

	/**
	 * Search basic infromation of employee
	 * 
	 * @param Object
	 *            parameterObject
	 * @return Object object
	 * @throws GlRuntimeException
	 */
	public Object retrieveBasicInfoMap(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveBasicInfoMap(parameterObject);
	}
	
	/**
	 * Search basic infromation of employee
	 * 
	 * @param Object
	 *            parameterObject
	 * @return Object object
	 * @throws GlRuntimeException
	 */
	public Object retrieveBasicInfo(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveBasicInfo(parameterObject);
	}

	/**
	 * retrieve department tree
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDeptTree(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveDeptTree(parameterObject);
	}
	
	public List retrieveDeptTreeNo(Object parameterObject) throws GlRuntimeException {
		
		return hrmCommonDAO.retrieveDeptTreeNo(parameterObject);
	}
	
	/**
	 * retrieve department tree
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDeptTree2(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveDeptTree2(parameterObject);
	}
	

	public List retrieveDeptTreeNew(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveDeptTreeNew(parameterObject);
	}

	/**
	 * retrieve employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEmpList(parameterObject);
	}
	
	/**
	 * retrieve retrieveEmpListNotCpnyId list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListNotCpnyId(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEmpListNotCpnyId(parameterObject);
	}
	
	/**
	 * retrieve typeIdGz list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveaffirmtypeIdGzList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveaffirmtypeGzList(parameterObject);
	}
	
	/**
	 * retrieve employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpMapList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEmpMapList(parameterObject);
	}
	
	/**
	 * retrieveSupervisorEmpList
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveSupervisorEmpList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveSupervisorEmpList(parameterObject);
	}
	
	/**
	 * retrieve pa employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrievePaEmpList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrievePaEmpList(parameterObject);
	}

	/**
	 * retrieve employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListIncumbency(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEmpListIncumbency(parameterObject, currentPage, pageSize);
	}

	public String getLastTransNO(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.getLastTransNO(parameterObject);
	}

	public List retrieveEmpListIncumbency(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEmpListIncumbency(parameterObject);
	}

	public Object retrieveEmpListIncumbencyCNT(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEmpListIncumbencyCNT(parameterObject);
	}

	/**
	 * retrieve education information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEducationInfo(Object parameterObject) throws GlRuntimeException {

		return basicInfoDAO.retrieveEducationInfo(parameterObject);
	}

	/**
	 * retrieve personal information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrievePersonalInfo(Object parameterObject) throws GlRuntimeException {

		return basicInfoDAO.retrievePersonalInfo(parameterObject);
	}

	/**
	 * retrieve language information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveLanguageInfo(Object parameterObject) throws GlRuntimeException {

		return basicInfoDAO.retrieveLanguageInfo(parameterObject);
	}

	/**
	 * retrieve military service information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveMilitaryServiceInfo(Object parameterObject) throws GlRuntimeException {

		return relationInfoDAO.retrieveMilitaryServiceInfo(parameterObject);
	}

	/**
	 * retrieve IT level information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveITLevelInfo(Object parameterObject) throws GlRuntimeException {

		return basicInfoDAO.retrieveITLevelInfo(parameterObject);
	}

	/**
	 * retrieve contract information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveContractInfo(Object parameterObject) throws GlRuntimeException {

		return basicInfoDAO.retrieveContractInfo(parameterObject);
	}

	/**
	 * insert education information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEducationInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.insertEducationInfo(parameterObject, isAutoTrans);
	}

	/**
	 * insert language information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertLanguageInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.insertLanguageInfo(parameterObject, isAutoTrans);
	}

	/**
	 * insert military service information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertMilitaryServiceInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		relationInfoDAO.insertMilitaryServiceInfo(parameterObject, isAutoTrans);
	}

	/**
	 * insert IT level information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertITLevelInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.insertITLevelInfo(parameterObject, isAutoTrans);
	}

	/**
	 * insert basic information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertBasicInfo(Map map) throws GlRuntimeException {

		basicInfoDAO.insertBasicInfo(map);
	}

	/**
	 * update education information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEducationInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.updateEducationInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update personal information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePersonalBasicInfo(Object parameterObject) throws GlRuntimeException {

		basicInfoDAO.updatePersonalBasicInfo(parameterObject);
	}

	/**
	 * update language information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateLanguageInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.updateLanguageInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update military service information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateMilitaryServiceInfo(Object parameterObject) throws GlRuntimeException {

		relationInfoDAO.updateMilitaryServiceInfo(parameterObject);
	}

	/**
	 * update costcenter information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateCostcenterInfo(Object parameterObject) throws GlRuntimeException {

		basicInfoDAO.updateCostcenterInfo(parameterObject);
	}

	/**
	 * update IT level information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateITLevelInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.updateITLevelInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update basic information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateBasicInfo(Map map) throws GlRuntimeException {

		basicInfoDAO.updateBasicInfo(map);
	}

	/**
	 * delete education information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEducationInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.deleteEducationInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete language information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteLanguageInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.deleteLanguageInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete military service information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteMilitaryServiceInfo(Object parameterObject) throws GlRuntimeException {

		relationInfoDAO.deleteMilitaryServiceInfo(parameterObject);
	}

	/**
	 * delete IT level information.
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteITLevelInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		basicInfoDAO.deleteITLevelInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete basic information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteBasicInfo(Map map) throws GlRuntimeException {

		basicInfoDAO.deleteBasicInfo(map);
	}

	/**
	 * retrieve family information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveFamilyInfo(Object parameterObject) throws GlRuntimeException {

		return relationInfoDAO.retrieveFamilyInfo(parameterObject);
	}

	/**
	 * insert family information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertFamilyInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		relationInfoDAO.insertFamilyInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update family information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateFamilyInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		relationInfoDAO.updateFamilyInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update personal relation information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updatePersonalRelationInfo(Object parameterObject) throws GlRuntimeException {

		relationInfoDAO.updatePersonalRelationInfo(parameterObject);
	}

	/**
	 * delete family information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteFamilyInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		relationInfoDAO.deleteFamilyInfo(parameterObject, isAutoTrans);
	}

	/**
	 * insert relation information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertRelationInfo(Map map) throws GlRuntimeException {

		relationInfoDAO.insertRelationInfo(map);
	}

	/**
	 * update relation information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateRelationInfo(Map map) throws GlRuntimeException {

		relationInfoDAO.updateRelationInfo(map);
	}

	/**
	 * delete relation information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteRelationInfo(Map map) throws GlRuntimeException {

		relationInfoDAO.deleteRelationInfo(map);
	}

	/**
	 * retrieve health information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveHealthInfo(Object parameterObject) throws GlRuntimeException {

		return healthInfoDAO.retrieveHealthInfo(parameterObject);
	}

	/**
	 * insert health information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertHealthInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		healthInfoDAO.insertHealthInfo(parameterObject, isAutoTrans);
	}

	public void insertEvaluate(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		evaluateInfoDAO.insertEvaluate(parameterObject, isAutoTrans);
	}

	public void updateEvaluateById(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		evaluateInfoDAO.updateEvaluateById(parameterObject, isAutoTrans);
	}

	public void deleteEvaluateById(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		evaluateInfoDAO.deleteEvaluateById(parameterObject, isAutoTrans);
	}

	/**
	 * update health information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateHealthInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		healthInfoDAO.updateHealthInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete health information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteHealthInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		healthInfoDAO.deleteHealthInfo(parameterObject, isAutoTrans);
	}

	/**
	 * retrieve experience information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveExperienceInfo(Object parameterObject) throws GlRuntimeException {

		return otherInfoDAO.retrieveExperienceInfo(parameterObject);
	}

	/**
	 * insert experience information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertExperienceInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		otherInfoDAO.insertExperienceInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update experience information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateExperienceInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		otherInfoDAO.updateExperienceInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete experience information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteExperienceInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		otherInfoDAO.deleteExperienceInfo(parameterObject, isAutoTrans);
	}

	/**
	 * retrieve qualification information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveQualificationInfo(Object parameterObject) throws GlRuntimeException {

		return otherInfoDAO.retrieveQualificationInfo(parameterObject);
	}

	/**
	 * insert qualification information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertQualificationInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		otherInfoDAO.insertQualificationInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update qualification information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateQualificationInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		otherInfoDAO.updateQualificationInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete qualification information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteQualificationInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		otherInfoDAO.deleteQualificationInfo(parameterObject, isAutoTrans);
	}

	/**
	 * insert Other information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertOtherInfo(Map map) throws GlRuntimeException {

		otherInfoDAO.insertOtherInfo(map);
	}

	/**
	 * update Other information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateOtherInfo(Map map) throws GlRuntimeException {

		otherInfoDAO.updateOtherInfo(map);
	}

	/**
	 * delete Other information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteOtherInfo(Map map) throws GlRuntimeException {

		otherInfoDAO.deleteOtherInfo(map);
	}

	/**
	 * retrieve additional information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAdditionalInfo(Object parameterObject) throws GlRuntimeException {

		return appendInfoDAO.retrieveAdditionalInfo(parameterObject);
	}

	/**
	 * insert additional information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertAdditionalInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		appendInfoDAO.insertAdditionalInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update additional information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateAdditionalInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		appendInfoDAO.updateAdditionalInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete additional information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteAdditionalInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		appendInfoDAO.deleteAdditionalInfo(parameterObject, isAutoTrans);
	}

	/**
	 * retrieve reward information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveRewardInfo(Object parameterObject) throws GlRuntimeException {

		return rewardInfoDAO.retrieveRewardInfo(parameterObject);
	}

	/**
	 * insert reward information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertRewardInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		rewardInfoDAO.insertRewardInfo(parameterObject, isAutoTrans);
	}

	/**
	 * update reward information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateRewardInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		rewardInfoDAO.updateRewardInfo(parameterObject, isAutoTrans);
	}

	/**
	 * delete reward information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteRewardInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		rewardInfoDAO.deleteRewardInfo(parameterObject, isAutoTrans);
	}

	/**
	 * 分页取奖励信息
	 * 
	 * @param paramObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List getRewardInfo(Object paramObject, int currentPage, int pageSize) {

		return rewardInfoDAO.getRewardInfo(paramObject, currentPage, pageSize);

	}

	/**
	 * 取奖励信息记录数
	 * @param paramObject
	 * @return
	 */
	public Object getRewardCnt(Object paramObject) {
		return rewardInfoDAO.getRewardCnt(paramObject);
	}

	/**
	 * retrieve training inside information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveTrainingInside(Object parameterObject) throws GlRuntimeException {

		return trainingInfoDAO.retrieveTrainingInside(parameterObject);
	}

	/**
	 * insert training inside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertTrainingInside(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		trainingInfoDAO.insertTrainingInside(parameterObject, isAutoTrans);
	}

	/**
	 * update training inside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateTrainingInside(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		trainingInfoDAO.updateTrainingInside(parameterObject, isAutoTrans);
	}

	/**
	 * delete training inside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteTrainingInside(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		trainingInfoDAO.deleteTrainingInside(parameterObject, isAutoTrans);
	}

	/**
	 * retrieve training outside information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveTrainingOutside(Object parameterObject) throws GlRuntimeException {

		return trainingInfoDAO.retrieveTrainingOutside(parameterObject);
	}

	/**
	 * insert training outside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertTrainingOutside(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		trainingInfoDAO.insertTrainingOutside(parameterObject, isAutoTrans);
	}

	/**
	 * update training outside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateTrainingOutside(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		trainingInfoDAO.updateTrainingOutside(parameterObject, isAutoTrans);
	}

	/**
	 * delete training outside information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteTrainingOutside(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		trainingInfoDAO.deleteTrainingOutside(parameterObject, isAutoTrans);
	}

	/**
	 * insert training information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void insertTrainingInfo(Map map) throws GlRuntimeException {

		trainingInfoDAO.insertTrainingInfo(map);
	}

	/**
	 * update training information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void updateTrainingInfo(Map map) throws GlRuntimeException {

		trainingInfoDAO.updateTrainingInfo(map);
	}

	/**
	 * delete training information
	 * 
	 * @param map
	 * @throws GlRuntimeException
	 */
	public void deleteTrainingInfo(Map map) throws GlRuntimeException {

		trainingInfoDAO.deleteTrainingInfo(map);
	}

	/**
	 * retrieve accounts information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveAccountsInfo(Object parameterObject) throws GlRuntimeException {

		return accountsInfoDAO.retrieveAccountsInfo(parameterObject);
	}

	/**
	 * insert accounts information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAccountsInfo(Object parameterObject) throws GlRuntimeException {

		accountsInfoDAO.insertAccountsInfo(parameterObject);
	}

	/**
	 * update accounts information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAccountsInfo(Object parameterObject) throws GlRuntimeException {

		accountsInfoDAO.updateAccountsInfo(parameterObject);
	}

	/**
	 * delete accounts information
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteAccountsInfo(Object parameterObject) throws GlRuntimeException {

		accountsInfoDAO.deleteAccountsInfo(parameterObject);
	}

	/**
	 * retrieve retrieveEvaluateType1 inside information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEvaluate1(Object parameterObject) throws GlRuntimeException {

		return evaluateInfoDAO.retrieveEvaluate1(parameterObject);
	}

	/**
	 * retrieve retrieveEvaluateType2 inside information
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEvaluate2(Object parameterObject) throws GlRuntimeException {

		return evaluateInfoDAO.retrieveEvaluate2(parameterObject);
	}

	public List retrieveEvaluate(Object parameterObject) throws GlRuntimeException {

		return evaluateInfoDAO.retrieveEvaluate(parameterObject);
	}

	/*
	 * ******************发令***********************
	 */

	/**
	 * 发令添加员工 基本信息 个人信息 职务信息 合同信息 登陆信息
	 * 
	 * @param emp
	 * @param personal
	 * @param contract
	 * @throws GlRuntimeException
	 */
	public void insertEmployee(Object emp, Object personal, Object contract) throws GlRuntimeException {
		empHireComDAO.addEmployee(emp, personal, contract);
	}

	/**
	 * 发令添加员工 基本信息 个人信息 职务信息 登陆信息
	 * 
	 * @param emp
	 * @param personal
	 * @throws GlRuntimeException
	 */
	public void insertEmployee(Object emp, Object personal) throws GlRuntimeException {
		empHireComDAO.addEmployee(emp, personal);
	}

	/**
	 * 添加合同信息
	 * 
	 * @param contract
	 * @throws GlRuntimeException
	 */
	public void insertContract(Object contract) throws GlRuntimeException {
		empHireComDAO.addContract(contract);
	}

	/**
	 * 批量添加调动信息
	 * 
	 * @param lExpInside
	 */
	public void insertExpInside(List lExpInside) throws GlRuntimeException {
		expInsideComDAO.insertExpInside(lExpInside);
	}

	public void insertExpInsideAndSaveOldEmpProperty(List lExpInside) throws GlRuntimeException {
		expInsideComDAO.insertExpInsideAndSaveOldEmpProperty(lExpInside);
	}

	/**
	 * 添加调动信息
	 * 
	 * @param lExpInside
	 */
	public void insertExpInside(Object expInside) throws GlRuntimeException {
		expInsideComDAO.insertExpInside(expInside);
	}

	/**
	 * 取得调动列表
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List getExpInside(Object parameterObject) throws GlRuntimeException {
		return expInsideComDAO.getExpInside(parameterObject);
	}

	/**
	 * 批量执行发令
	 * 
	 * @param expInside
	 */
	public void executeExpInside(List lExpInside) throws GlRuntimeException {
		expInsideComDAO.executeExpInside(lExpInside);
	}

	/**
	 * 批量执行发令
	 * 
	 * @param expInside
	 */
	public void batchExeExpInside(Object paramObject) throws GlRuntimeException {
		expInsideComDAO.executeExpInside(this.getExpInside(paramObject));
	}

	public void updateEmpStatus() throws GlRuntimeException {
		expInsideComDAO.updateEmpStatus();
	}

	/**
	 * 分页取得调动列表
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List getExpInside(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		return expInsideComDAO.getExpInside(parameterObject, currentPage, pageSize);
	}

	public List searchEmpHistoryById(Object parameterObject) throws GlRuntimeException {
		return expInsideComDAO.searchEmpHistoryById(parameterObject);

	}

	/**
	 * 分页取得调动列表
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object getExpInsideCnt(Object parameterObject) throws GlRuntimeException {
		return expInsideComDAO.getExpInsideCnt(parameterObject);
	}

	public Object getHealthTipMessageCNT(Object parameterObject) throws GlRuntimeException {
		return tipDAO.getHealthTipMessageCNT(parameterObject);
	}

	public Object getExpiredProbationCNT(Object parameterObject) throws GlRuntimeException {
		return tipDAO.getExpiredProbationCNT(parameterObject);
	}

	public Object getUpgradeInfoFieldCNT(Object parameterObject) throws GlRuntimeException {
		return tipDAO.getUpgradeInfoFieldCNT(parameterObject);
	}

	/**
	 * 删除发令信息
	 * 
	 * @param expInside
	 */
	public void delExpInside(Object expInside) throws GlRuntimeException {
		expInsideComDAO.delExpInside(expInside);
	}

	/**
	 * 删除发令信息
	 * 
	 * @param expInside
	 */
	public void delExpInside(List lExpInside) throws GlRuntimeException {
		expInsideComDAO.delExpInside(lExpInside);
	}

	/**
	 * 执行发令
	 * 
	 * @param expInside
	 */
	public void executeExpInside(Object expInside) throws GlRuntimeException {
		expInsideComDAO.executeExpInside(expInside);
	}

	/**
	 * 添加培训信息
	 * 
	 * @param lStudying
	 */
	public void insertStudying(List lStudying) throws GlRuntimeException {
		trainingComDAO.insertStudying(lStudying);

	}

	/**
	 * 删除培训信息
	 * 
	 * @param lStudying
	 */
	public void delStudying(List lStudying) throws GlRuntimeException {
		trainingComDAO.delStudying(lStudying);
	}

	/**
	 * 取得培训信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getStudying(Object paramObject) throws GlRuntimeException {
		return trainingComDAO.getStudying(paramObject);
	}

	/**
	 * 分页取得培训信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getStudying(Object paramObject, int currentPage, int pageSize) throws GlRuntimeException {
		return trainingComDAO.getStudying(paramObject, currentPage, pageSize);
	}

	/**
	 * 取得培训信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public Object getStudyingCnt(Object paramObject) throws GlRuntimeException {
		return trainingComDAO.getStudyingCnt(paramObject);
	}

	/**
	 * 批量添加休职信息
	 * 
	 * @param lSuspend
	 */
	public void insertSuspension(List lSuspend) throws GlRuntimeException {
		suspendComDAO.insertSuspension(lSuspend);
	}

	/**
	 * 复职
	 * 
	 * @param lSuspend
	 */
	public void updateSuspension(List lSuspend) throws GlRuntimeException {
		suspendComDAO.updateSuspension(lSuspend);
	}

	/**
	 * 复职同时修改员工状态
	 * 
	 * @param lSuspend
	 */
	public void updateSuspensionCascade(List lSuspend) throws GlRuntimeException {
		suspendComDAO.updateSuspensionCascade(lSuspend);
	}

	/**
	 * 批量删除休职信息
	 * 
	 * @param lSuspend
	 */
	public void delSuspension(List lSuspend) throws GlRuntimeException {
		suspendComDAO.delSuspension(lSuspend);
	}

	/**
	 * 删除休职信息
	 * 
	 * @param suspend
	 */
	public void delSuspension(Object suspend) throws GlRuntimeException {
		suspendComDAO.delSuspension(suspend);
	}

	/**
	 * 取得休职信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getSuspension(Object paramObject) throws GlRuntimeException {
		return suspendComDAO.getSuspension(paramObject);
	}

	/**
	 * 分页取得休职信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getSuspension(Object paramObject, int currentPage, int pageSize) throws GlRuntimeException {
		return suspendComDAO.getSuspension(paramObject, currentPage, pageSize);
	}

	/**
	 * 取得休职信息记录数
	 * 
	 * @param paramObject
	 * @return
	 */
	public Object getSuspensionCnt(Object paramObject) throws GlRuntimeException {
		return suspendComDAO.getSuspensionCnt(paramObject);
	}

	/**
	 * 添加离职发令
	 * 
	 * @param lResignation
	 */
	public void insertResignation(List lResignation) throws GlRuntimeException {
		resignComDAO.insertResignation(lResignation);
	}

	/**
	 * 删除离职信息
	 * 
	 * @param lResignation
	 */
	public void delResignation(List lResignation) throws GlRuntimeException {
		resignComDAO.delResignation(lResignation);

	}

	/**
	 * 删除离职信息
	 * 
	 * @param lResignation
	 */
	public void delResignation(Object resignation) throws GlRuntimeException {
		resignComDAO.delResignation(resignation);

	}

	/**
	 * 批量执行退社发令
	 * 
	 * @param lResignation
	 */
	public void batchExeResignation(Object paramObject) throws GlRuntimeException {
		resignComDAO.executeResignation(this.getResignation(paramObject));
	}

	public void batchExeModifyEmpStatus() throws GlRuntimeException {
		suspendComDAO.batchExeModifyEmpStatus(suspendComDAO.getSuspensionForUpdateEmployeeStatus());
	}

	/**
	 * 批量执行退社发令
	 * 
	 * @param lResignation
	 */
	public void executeResignation(List lResignation) throws GlRuntimeException {
		resignComDAO.executeResignation(lResignation);
	}

	/**
	 * 执行退社发令
	 * 
	 * @param lResignation
	 */
	public void executeResignation(Object resignation) throws GlRuntimeException {
		resignComDAO.executeResignation(resignation);
	}

	/**
	 * 取退社信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getResignation(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getResignation(paramObject);
	}

	/**
	 * 分页取退社信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getResignation(Object paramObject, int currentPage, int pageSize) throws GlRuntimeException {
		return resignComDAO.getResignation(paramObject, currentPage, pageSize);
	}

	public List getResignInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getResignInfo(paramObject);
	}

	public List getRewardInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getRewardInfo(paramObject);
	}

	public List getExperienceInsideInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getExperienceInsideInfo(paramObject);
	}

	public List getSuspensionInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getSuspensionInfo(paramObject);
	}

	public List getPluralityInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getPluralityInfo(paramObject);
	}

	public List getDispatchInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getDispatchInfo(paramObject);
	}

	public List getSdutyingInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getSdutyingInfo(paramObject);
	}

	public List getPulishInfo(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getPulishInfo(paramObject);
	}

	/**
	 * 取退社信息记录数
	 * 
	 * @param paramObject
	 * @return
	 */
	public Object getResignationCnt(Object paramObject) throws GlRuntimeException {
		return resignComDAO.getResignationCnt(paramObject);
	}

	/**
	 * 添加惩戒信息
	 * 
	 * @param lPunishMent
	 */
	public void insertPunishMent(List lPunishMent) throws GlRuntimeException {
		punishComDAO.insertPunishMent(lPunishMent);
	}

	/**
	 * 删除惩戒信息
	 * 
	 * @param lPunishMent
	 */
	public void delPunishMent(List lPunishMent) throws GlRuntimeException {
		punishComDAO.delPunishMent(lPunishMent);
	}

	/**
	 * 删除惩戒信息
	 * 
	 * @param punishMent
	 */
	public void delPunishMent(Object punishMent) throws GlRuntimeException {
		punishComDAO.delPunishMent(punishMent);
	}

	/**
	 * 取惩戒信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getPunishMent(Object paramObject) throws GlRuntimeException {
		return punishComDAO.getPunishMent(paramObject);
	}

	/**
	 * 分页取惩戒信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getPunishMent(Object paramObject, int currentPage, int pageSize) throws GlRuntimeException {
		return punishComDAO.getPunishMent(paramObject, currentPage, pageSize);
	}

	/**
	 * 取惩戒信息记录数
	 * 
	 * @param paramObject
	 * @return
	 */
	public Object getPunishMentCnt(Object paramObject) throws GlRuntimeException {
		return punishComDAO.getPunishMentCnt(paramObject);
	}

	/**
	 * 添加兼职信息
	 * 
	 * @param lPlurality
	 */
	public void insertPlurality(List lPlurality) throws GlRuntimeException {
		pluralityComDAO.insertPlurality(lPlurality);
	}

	/**
	 * 复职
	 * 
	 * @param lPlurality
	 */
	public void updatePlurality(List lPlurality) throws GlRuntimeException {
		pluralityComDAO.updatePlurality(lPlurality);
	}

	/**
	 * 删除休职
	 * 
	 * @param lPlurality
	 */
	public void delPlurality(List lPlurality) throws GlRuntimeException {
		pluralityComDAO.delPlurality(lPlurality);
	}

	/**
	 * 删除休职信息
	 * 
	 * @param plurality
	 */
	public void delPlurality(Object plurality) throws GlRuntimeException {
		pluralityComDAO.delPlurality(plurality);
	}

	/**
	 * 取休职信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getPlurality(Object paramObject) throws GlRuntimeException {
		return pluralityComDAO.getPlurality(paramObject);
	}

	/**
	 * 分页取兼职信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getPlurality(Object paramObject, int currentPage, int pageSize) throws GlRuntimeException {
		return pluralityComDAO.getPlurality(paramObject, currentPage, pageSize);
	}

	/**
	 * 取休职信息记录数
	 * 
	 * @param paramObject
	 * @return
	 */
	public Object getPluralityCnt(Object paramObject) throws GlRuntimeException {
		return pluralityComDAO.getPluralityCnt(paramObject);
	}

	/**
	 * 添加派遣信息
	 * 
	 * @param lDispatch
	 */
	public void insertDispatch(List lDispatch) throws GlRuntimeException {
		dispatchComDAO.insertDispatch(lDispatch);
	}

	/**
	 * 派遣期满本职
	 * 
	 * @param lDispatch
	 */
	public void updateDispatch(List lDispatch) throws GlRuntimeException {
		dispatchComDAO.updateDispatch(lDispatch);
	}

	/**
	 * 删除派遣信息
	 * 
	 * @param lDispatch
	 */
	public void delDispatch(List lDispatch) throws GlRuntimeException {
		dispatchComDAO.delDispatch(lDispatch);
	}

	/**
	 * 删除派遣信息
	 * 
	 * @param dispatch
	 */
	public void delDispatch(Object dispatch) throws GlRuntimeException {
		dispatchComDAO.delDispatch(dispatch);
	}

	/**
	 * 取得派遣信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getDispatch(Object paramObject) throws GlRuntimeException {
		return dispatchComDAO.getDispatch(paramObject);
	}

	/**
	 * 分页取得派遣信息
	 * 
	 * @param paramObject
	 * @return
	 */
	public List getDispatch(Object paramObject, int currentPage, int pageSize) throws GlRuntimeException {
		return dispatchComDAO.getDispatch(paramObject, currentPage, pageSize);
	}

	/**
	 * 取得派遣信息记录数
	 * 
	 * @param paramObject
	 * @return
	 */
	public Object getDispatchCnt(Object paramObject) throws GlRuntimeException {
		return dispatchComDAO.getDispatchCnt(paramObject);
	}

	/*
	 * ******************发令**********************
	 */

	/**
	 * 取得搜索信息类型
	 * 
	 * @return
	 */
	public List getInfoType() throws GlRuntimeException {
		return infoDAO.getInfoType();
	}

	/**
	 * 取得搜索信息类型表
	 * 
	 * @return
	 */
	public List getInfoTable(Object param) throws GlRuntimeException {
		return infoDAO.getInfoTable(param);
	}

	/**
	 * 取得搜索信息类型表列
	 * 
	 * @return
	 */
	public List getInfoField(Object param) throws GlRuntimeException {
		return infoDAO.getInfoField(param);
	}

	public List getFourthDept() throws GlRuntimeException {
		return tipDAO.getFourthDept();
	}

	public List getHealthTipMessageByConditon(SimpleMap map, int currentPage, int pageSize) throws GlRuntimeException {
		return tipDAO.getHealthTipMessageByConditon(map, currentPage, pageSize);
	}

	public List getHealthTipMessageByConditon(SimpleMap map) throws GlRuntimeException {
		return tipDAO.getHealthTipMessageByConditon(map);
	}

	public List getExpiredProbationByCondition(SimpleMap map) throws GlRuntimeException {
		return tipDAO.getExpiredProbationByCondition(map);
	}

	public List getExpiredProbationByCondition(SimpleMap map, int currentPage, int pageSize) throws GlRuntimeException {
		return tipDAO.getExpiredProbationByCondition(map, currentPage, pageSize);
	}

	public List getUpgradeInfoFieldByCondition(SimpleMap map) throws GlRuntimeException {
		return tipDAO.getUpgradeInfoFieldByCondition(map);
	}

	public List getUpgradeInfoFieldByCondition(SimpleMap map, int currentPage, int pageSize) throws GlRuntimeException {
		return tipDAO.getUpgradeInfoFieldByCondition(map, currentPage, pageSize);
	}

	//	 创建部门树
	public static String createDeptTree(List deptList, String selectedDeptId, String separator) {
		StringBuffer optionHtmlBuf = new StringBuffer();
		optionHtmlBuf.append("<option value=\"\" selected>选择全部</option>");
		Iterator it = deptList.iterator();
		while (it.hasNext()) {
			Department department = (Department) it.next();
			int deptLevel = department.getDeptLevel();
			String deptname = department.getDeptName();
			for (int k = 0; k < deptLevel; k++) {
				deptname = separator + deptname;
			}
			optionHtmlBuf.append("<option value=\"" + department.getDeptID() + "\"");
			if (selectedDeptId != null && (department.getDeptID()).equals(selectedDeptId)) {
				optionHtmlBuf.append(" selected");

			}
			if (deptLevel == 1)
				optionHtmlBuf.append(" style=\"color:#FF0000\"");
			optionHtmlBuf.append(">" + deptname + "</option>");
		}
		return optionHtmlBuf.toString();
	}
	
	/**
	 * NEW retrieve SyAffirm employee list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveSyAffirmList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveSyAffirmList(parameterObject);
	}
	
	/**
	 * NEW retrieve Instead Affirm list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveInsteadAffirmList(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveInsteadAffirmList(parameterObject);
	}
	
	/**
	 * retrieveHrPersonId by cnpyId,empId
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public String retrieveHrPersonId(SimpleMap parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveHrPersonId(parameterObject);
	}
	
	/**
	 * retrieve Item Group
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveItemGroup(Object parameterObject) throws GlRuntimeException{
		
		return hrmCommonDAO.retrieveItemGroup(parameterObject);
	}
	
	/**
	 * retrieve Item Group cut
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveItemGroupCut(Object parameterObject) throws GlRuntimeException{
		
		return hrmCommonDAO.retrieveItemGroupCut(parameterObject);
	}
	
	public String retrieveEvAffirmAdmin(Object parameterObject) throws GlRuntimeException {

		return hrmCommonDAO.retrieveEvAffirmAdmin(parameterObject);
	}
	
	/**
	 * 查询出现在部门
	 * 
	 * @param List
	 *            parameterList
	 * @throws GlRuntimeException
	 */
	
	
	public List getAllDept(String cpnyid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		// 统一从HR_DEPT_TREE_V视图中查询部门数据
		String SQL = " select hdtv.deptid,hdtv.deptname,hdtv.dept_level from HR_DEPT_TREE_V hdtv " +
				     " where hdtv.CPNY_ID = '" + cpnyid + "' ORDER BY hdtv.DEPTID";
		try {
			conn = JdbcTemplate.getTemplate().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				list.add(createDepartment(rs));
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).info("can not get all department \n" + ex.toString());
			throw new RuntimeException("can not get all department", ex);
		} finally {
			SqlUtil.close(rs, stmt);
		}
		return list;
	}
	private Department createDepartment(ResultSet rs) {
		Department department = new Department();
		try {
			department.setDeptID(rs.getString("DEPTID"));
			department.setDeptName(rs.getString("DEPTNAME"));
			department.setDeptLevel(rs.getInt("DEPT_LEVEL"));
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
			throw new RuntimeException("根据ResultSet创建Department,检测get字段是否正确", ex);
		}
		return department;
	}
}