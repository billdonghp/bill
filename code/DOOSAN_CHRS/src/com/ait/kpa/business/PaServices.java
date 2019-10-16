package com.ait.kpa.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.dao.bonus.BonusDAO;
import com.ait.kpa.dao.payplan.PayPlanDAO;
import com.ait.kpa.dao.postgrade.PostGradeDAO;
import com.ait.kpa.dao.report.PaReportDAO;
import com.ait.kpa.dao.util.PaUtilDAO;
import com.ait.sqlmap.util.SimpleMap;


public class PaServices {

	private static PaServices paServices;
	
	private PaUtilDAO utilDAO ;
	
	private PaReportDAO paReportDAO ;
	
	private PostGradeDAO postgradedao;
	
	private PayPlanDAO payPlanDAO;
	
	private BonusDAO bonusDAO ;
	
	private PaServices() {
		
		utilDAO = new PaUtilDAO() ;
		
		paReportDAO = new PaReportDAO() ;
		
		postgradedao = new PostGradeDAO() ;
		
		payPlanDAO = new PayPlanDAO();
		
		bonusDAO = new BonusDAO() ;
	}

	/**
	 * 
	 * @return HrmServices
	 */
	public static PaServices getInstance() {

		if (paServices != null)
			return paServices;
		else
			return new PaServices();
	}
	
	/**
	 * retrieve paInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaInfoForSendMail(parameterObject);
	}
	
	/**
	 * retrieve paInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDiffInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaDiffInfoForSendMail(parameterObject);
	}
	
	/**
	 * retrieve paBonusInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaBonusInfoForSendMail(parameterObject);
	}
	
	/**
	 * retrieve insert DSHR data
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrieveInsertDsHrDatas(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveInsertDsHrDatas(parameterObject);
	}
	
	/**
	 * retrieve insert data DSHR columns
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrieveInsertDataColumns(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveInsertDataColumns(parameterObject);
	}
	
	/**
	 * retrieve alert DSHR columns
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrieveAlterDsHrColumns(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveAlterDsHrColumns(parameterObject);
	}
	
	/**
	 * retrieve alert DSHR Comments
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrieveAlterDsHrComments(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveAlterDsHrComments(parameterObject);
	}
	
	
	/**
	 * retrieve pa_util pa_item_list of month 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_Item_List_Month() throws GlRuntimeException {

		return utilDAO.retrievePa_Item_List_Month();
	}
	
	/**
	 * retrieve pa_util pa_bonus_item_list of month 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_bonus_Item_List_Month() throws GlRuntimeException {

		return utilDAO.retrievePa_bonus_Item_List_Month();
	}
	
	public List retrieveDiff_Item_List_Month() throws GlRuntimeException {

		return utilDAO.retrieveDiff_Item_List_Month();
	}
	
	/**
	 * retrieve pa_util pa_param_item_list 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_param_item_list(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePa_param_item_list(parameterObject);
	}
	
	/**
	 * retrieve pa_util pa_bonus_param_item_list 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_bonus_param_item_list(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePa_bonus_param_item_list(parameterObject);
	}
	
	public List retrieveDiff_param_item_list(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveDiff_param_item_list(parameterObject);
	}
	
	/**
	 * retrieve pa_util Pa_distinct_list  
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_distinct_list(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePa_distinct_list(parameterObject);
	}
	
	/**
	 * retrieve pa_bonus_util Pa_distinct_list  
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_bonus_distinct_list(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePa_bonus_distinct_list(parameterObject);
	}
	
	/**
	 * retrieve export pa detail month data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetailMonth(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDetailMonth(parameterObject);
	}
	
	/**
	 * retrieve export pa detail month sum data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetailMonth_SUM(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDetailMonth_SUM(parameterObject);
	}
	
	/**
	 * retrieve export pa detail month data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePersonalTaxMonthly(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePersonalTaxMonthly(parameterObject);
	}
	
	
	/**
	 * retrieve export pa complete month data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaCompelte(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaCompelte(parameterObject);
	}
	
	public List retrievePayDetail(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePayDetail(parameterObject);
	}
	
	
	public List retrieveInsDetailMonth(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveInsDetailMonth(parameterObject);
	}
	
	public List retrieveInsDetailMonth_SUM(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveInsDetailMonth_SUM(parameterObject);
	}
	
	
	
	public Object retrieveInsuranceData(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveInsuranceData(parameterObject);
	}
	
	public List retrieveInsuranceData_simple(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveInsuranceData_simple(parameterObject);
	}
	
	public List retrieveInsuranceData_simple_per(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveInsuranceData_simple_per(parameterObject);
	}
	
	public List retrieveInsuranceData_simple_com(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveInsuranceData_simple_com(parameterObject);
	}
	
	/**
	 * retrieve export t_pa_result column list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaResultColumnList(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveTPaResultColumnList(parameterObject);
	}
	
	/**
	 * retrieve export t_pa_result data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaResultDataList(SimpleMap parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveTPaResultDataList(parameterObject);
	}
	
	/**
	 * retrieve export t_pa_bonus_result column list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaBonusResultColumnList(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveTPaBonusResultColumnList(parameterObject);
	}
	
	/**
	 * retrieve export t_pa_bonus_result data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaBonusResultDataList(SimpleMap parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveTPaBonusResultDataList(parameterObject);
	}
	
	/**
	 * retrieve export pay slip data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaySlip(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaySlip(parameterObject);
	}
	
	/**
	 * retrieve export dept pay insurance data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePayInsurance(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePayInsurance(parameterObject);
	}
	
	/**
	 * retrieve work area data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrieveWorkAreaData(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrieveWorkAreaData(parameterObject);
	}
	
	/**
	 * retrieve export pa daily head data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaDaily_head(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDaily_head(parameterObject);
	}
	
	/**
	 * retrieve export pa daily data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaDaily(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDaily(parameterObject);
	}
	
	/**
	 * retrieve pa detail dept list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetail_DeptList(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDetail_DeptList(parameterObject);
	}
	
	/**
	 * retrieve pa detail section dept list 
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetail_DeptList_section(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDetail_DeptList_section(parameterObject);
	}
	
	/**
	 * retrieve pa detail dept list by parent_dept_id 
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaDetail_DeptList_parent_dept_id(Object parameterObject) throws GlRuntimeException {

		return paReportDAO.retrievePaDetail_DeptList_parent_dept_id(parameterObject);
	}
	
	/**
	 * retrieve pa bonus param item list  
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonus_paramList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonus_paramList(parameterObject);
	}
	
	public List retrieveDiff_paramList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiff_paramList(parameterObject);
	}
	
	/**
	 * retrieve pa bonus param   
	 * 
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaBonus_param(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonus_param(parameterObject);
	}
	
	public Object retrieveDiff_param(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiff_param(parameterObject);
	}
	
	/**
	 * validate pa bonus param itemid  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int validatePaBonusParamItemId(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.validatePaBonusParamItemId(parameterObject);
	}
	
	public int validateDiffParamItemId(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.validateDiffParamItemId(parameterObject);
	}
	
	/**
	 * insert pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void insertPaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertPaBonusParam(parameterObject);
	}
	
	public void insertDiffParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertDiffParam(parameterObject);
	}
	
	/**
	 * update pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusParam(parameterObject);
	}
	
	public void updateDiffParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffParam(parameterObject);
	}
	
	public void updateDiffDefaultParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffDefaultParam(parameterObject);
	}
	
	
	
	public void updateDiffSalaryS(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffSalaryS(parameterObject);
	}
	
	public void updateDiffSalaryS1(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffSalaryS1(parameterObject);
	}
	
	public void updateDiffTaxS(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffTaxS(parameterObject);
	}
	
	public void updateDiffTaxS1(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffTaxS1(parameterObject);
	}
	
	public void updateDiffOverseaS(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffOverseaS(parameterObject);
	}
	
	public void updateDiffOverseaS1(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffOverseaS1(parameterObject);
	}
	
	public int findHistoryS(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.findHistoryS(parameterObject);
	}
	
	public int findHistoryS_tax(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.findHistoryS_tax(parameterObject);
	}
	
	public int findHistoryS_oversea(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.findHistoryS_oversea(parameterObject);
	}	
	
	
	public int findHistoryE(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.findHistoryE(parameterObject);
	}
	
	public int findHistoryE_tax(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.findHistoryE_tax(parameterObject);
	}
	
	public int findHistoryE_oversea(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.findHistoryE_oversea(parameterObject);
	}
	
	public void updateDiffSalaryE(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffSalaryE(parameterObject);
	}
	
	public void updateDiffSalaryE1(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffSalaryE1(parameterObject);
	}
	
	public void updateDiffTaxE(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffTaxE(parameterObject);
	}
	
	public void updateDiffTaxE1(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffTaxE1(parameterObject);
	}
	
	public void updateDiffOverseaE(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffOverseaE(parameterObject);
	}
	
	public void updateDiffOverseaE1(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffOverseaE1(parameterObject);
	}
	
	/**
	 * delete pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void deletePaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deletePaBonusParam(parameterObject);
	}
	
	public void deleteDiffParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deleteDiffParam(parameterObject);
	}
	
	/**
	 * retrieve pa bonus param data list  
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusParamDataList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusParamDataList(parameterObject);
	}
	
	public List retrieveDiffParamDataList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiffParamDataList(parameterObject);
	}
	
	/**
	 * retrieve pa bonus param data for insert list  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusParamDataForInsert(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusParamDataForInsert(parameterObject);
	}
	
	/**
	 * insert pa bonus param data   
	 * 
	 * @param list
	 * @throws GlRuntimeException
	 */
	
	public void insertPaBonusParamData(List parameterList) throws GlRuntimeException {

		bonusDAO.insertPaBonusParamData(parameterList);
	}
	
	/**
	 * update pa bonus param data   
	 * 
	 * @param list
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusParamData(List parameterList) throws GlRuntimeException {

		bonusDAO.updatePaBonusParamData(parameterList);
	}
	
	/**
	 * delete pa bonus param data
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void deletePaBonusParamData(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deletePaBonusParamData(parameterObject);
	}
	
	/**
	 * retrieve pa bonus item list  
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusItemList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusItemList(parameterObject,currentPage,pageSize);
	}
	
	public List retrieveDiffItemList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return bonusDAO.retrieveDiffItemList(parameterObject,currentPage,pageSize);
	}
	
	/**
	 * retrieve pa bonus item list count 
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int retrievePaBonusItemListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusItemListCnt(parameterObject);
	}
	
	public int retrieveDiffItemListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiffItemListCnt(parameterObject);
	}
	
	/**
	 * validate pa bonus itemid  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int validatePaBonusItemId(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.validatePaBonusItemId(parameterObject);
	}
	
	public int validateDiffItemId(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.validateDiffItemId(parameterObject);
	}
	
	/**
	 * insert pa bonus param  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void insertPaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertPaBonusItem(parameterObject);
	}
	
	public void insertDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertDiffItem(parameterObject);
	}
	
	/**
	 * retrieve pa bonus item   
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusItem(parameterObject);
	}
	
	public Object retrieveDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiffItem(parameterObject);
	}
	
	/**
	 * update pa bonus item  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusItem(parameterObject);
	}
	
	public void updateDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffItem(parameterObject);
	}
	
	/**
	 * delete pa bonus item  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void deletePaBonusItem(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deletePaBonusItem(parameterObject);
	}
	
	public void deleteDiffItem(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deleteDiffItem(parameterObject);
	}
	/**
	 * retrieve pa bonus item of formula list  
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusItemOfFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusItemOfFormularList(parameterObject);
	}
	
	public List retrieveDiffItemOfFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiffItemOfFormularList(parameterObject);
	}
	/**
	 * retrieve pa bonus formular list  
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusFormularList(parameterObject);
	}
	
	public List retrieveDiffFormularList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiffFormularList(parameterObject);
	}
	/**
	 * insert pa bonus formular  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void insertPaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertPaBonusFormular(parameterObject);
	}
	
	public void insertDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertDiffFormular(parameterObject);
	}
	
	/**
	 * retrieve pa bonus formular  
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonusFormular(parameterObject);
	}
	
	
	public Object retrieveDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveDiffFormular(parameterObject);
	}
	/**
	 * update pa bonus formular  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusFormular(parameterObject);
	}
	
	public void updateDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffFormular(parameterObject);
	}
	/**
	 * delete pa bonus item  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void deletePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deletePaBonusFormular(parameterObject);
	}
	
	public void deleteDiffFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deleteDiffFormular(parameterObject);
	}
	/**
	 * retrieve pa bonus item caluOrder  
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaBonusItemCalcuOrder(SimpleMap parameterObject) throws GlRuntimeException {

		 return bonusDAO.retrievePaBonusItemCalcuOrder(parameterObject);
	}
	
	public Object retrieveDiffItemCalcuOrder(SimpleMap parameterObject) throws GlRuntimeException {

		 return bonusDAO.retrieveDiffItemCalcuOrder(parameterObject);
	}
	/**
	 * update pa bonus item caluOrder up 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusItemCalcuOrderUp(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusItemCalcuOrderUp(parameterObject);
	}
	
	public void updateDiffItemCalcuOrderUp(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffItemCalcuOrderUp(parameterObject);
	}
	
	/**
	 * retrieve pa bonus item caluOrder down
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusItemCalcuOrderDown(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusItemCalcuOrderDown(parameterObject);
	}
	
	public void updateDiffItemCalcuOrderDown(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updateDiffItemCalcuOrderDown(parameterObject);
	}
	/**
	 * call PA_BONUS_CONFIRM   
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void callPaBonusConfirm(SimpleMap parameterObject) throws GlRuntimeException {

		bonusDAO.callPaBonusConfirm(parameterObject);
	}
	
	/**
	 * get PA_BONUS_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int paBonusCheckCalc(SimpleMap parameterObject) throws GlRuntimeException {

		return utilDAO.paBonusCheckCalc(parameterObject);
	}
	
	/**
	 * retrieve Param Data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveParamDataList(SimpleMap parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveParamDataList(parameterObject);
	}
	
	/**
	 * get PA_REPLENISHMENT_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int paCheckCalc(SimpleMap parameterObject) throws GlRuntimeException {

		return utilDAO.paCheckCalc(parameterObject);
	}
	
	/**
	 * retrieve export pa bonus Param Data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveExportPaBonusParamDataList(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrieveExportPaBonusParamDataList(parameterObject);
	}
	
	
	public List getPostGradeList(SimpleMap parameterObject) {
		List list = postgradedao.getPostGrade(parameterObject);
		return list;
	}
	
	public List getPostGradeYear() {
		List list = postgradedao.getPostGradeYear();
		return list;
	}

	public Object getPostGradeById(SimpleMap parameterObject) {
		Object obj = postgradedao.getPostGradeById(parameterObject);
		return obj;
	}
	
	public List getPostGradeTypeList() {
		return  postgradedao.getPostGradeTypeList();
		
	}

	public void insertPostGrade(SimpleMap map) {
		postgradedao.InsertPostGrade(map);
	}
	
	public int validateInsertPostGrade(SimpleMap map) {
		return postgradedao.validateInsertPostGrade(map);
	}

	public void updatePostGrade(SimpleMap map) {
		postgradedao.UpdatePostGrade(map);
	}

	public void deletePostGrade(String groupid) {
		postgradedao.deletePostGrade(groupid);
	}
	
	
	/*搜索工资计划*/
	public List payPlan01Serche(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.payPlan01Serche(parameterObject);

	}
	/*get year pay plan*/
	public List getYearPayPlan(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.getYearPayPlan(parameterObject);

	}
	/*get seq_id pay plan*/
	public String getSeqChar(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.getSeqChar(parameterObject);

	}
	/*get year plan for insert */
	public List getSavePayPlanProdures(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.getSavePayPlanProdures(parameterObject);

	}
	
	/*insert year plan */
	public void savePayPlanIntoInterfaceTable(Object parameterObject) throws GlRuntimeException {
		payPlanDAO.savePayPlanIntoInterfaceTable(parameterObject);

	}
	
	/*get AveragePayPlan Search*/
	public List getAveragePayPlanSearch(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.getAveragePayPlanSearch(parameterObject);

	}
	
	/*insert AveragePayPlan  */
	public void saveAveragePayPlan(Object parameterObject) throws GlRuntimeException {
		payPlanDAO.saveAveragePayPlan(parameterObject);

	}
	
	/*get year plan for month */
	public List getAveragePayPlanMonthSearch(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.getAveragePayPlanMonthSearch(parameterObject);

	}
	
	/*insert saveAveragePayPlanMonth  */
	public void saveAveragePayPlanMonth(Object parameterObject) throws GlRuntimeException {
		payPlanDAO.saveAveragePayPlanMonth(parameterObject);

	}
	/*size PayCalculationSignsForSearch  */
	public int PayCalculationSignsForSearchNumber(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayCalculationSignsForSearchNumber(parameterObject);

	}
	
	/*list PayCalculationSignsForSearch  */
	public List PayCalculationSignsForSearch(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.PayCalculationSignsForSearch(parameterObject,currentPage,pageSize);

	}
	
	/*size PayBankCodeForSearchNumber pa */
	public int PayBankCodeForSearchNumberPa(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchNumberPa(parameterObject);

	}
	
	/*list PayBankCodeForSearch pa */
	public List PayBankCodeForSearchPa(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchPa(parameterObject,currentPage,pageSize);

	}
	
	/*list PayBankCodeForSearch pa */
	public List PayBankCodeForSearchPa(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchPa(parameterObject);

	}
	
	/*size PayBankCodeForSearchNumber bonus */
	public int PayBankCodeForSearchNumberBonus(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchNumberBonus(parameterObject);

	}
	
	/*list PayBankCodeForSearch  */
	public List PayBankCodeForSearchBonus(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchBonus(parameterObject,currentPage,pageSize);

	}
	
	/*list PayBankCodeForSearch  */
	public List PayBankCodeForSearchBonus(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchBonus(parameterObject);

	}
	
	/*update PayCalculationSignsForUpdate  */
	public void PayCalculationSignsForUpdate(Object parameterObject) throws GlRuntimeException {
		utilDAO.PayCalculationSignsForUpdate(parameterObject);

	}
	/*update PayCalculationSignsForUpdate  */
	public void PayBankCodeForUpdate(Object parameterObject) throws GlRuntimeException {
		utilDAO.PayBankCodeForUpdate(parameterObject);

	}
	
}