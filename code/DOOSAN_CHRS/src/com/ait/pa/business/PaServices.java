package com.ait.pa.business;

import java.util.ArrayList;
import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.dao.bonus.BonusDAO;
import com.ait.pa.dao.padetail.PaDetailDAO;
import com.ait.pa.dao.paramItem.PaParamItemDAO;
import com.ait.pa.dao.payplan.PayPlanDAO;
import com.ait.pa.dao.postgrade.PostGradeDAO;
import com.ait.pa.dao.report.PaReportDAO;
import com.ait.pa.dao.util.PaUtilDAO;
import com.ait.pa.dao.vouchertype.VoucherTypeDAO;
import com.ait.sqlmap.util.SimpleMap;


public class PaServices {

	private static PaServices paServices;
	
	private PaUtilDAO utilDAO ;
	
	private PaReportDAO paReportDAO ;
	
	private PostGradeDAO postgradedao;
	
	private VoucherTypeDAO vouchertypedao;
	
	private PayPlanDAO payPlanDAO;
	
	private BonusDAO bonusDAO ;
	
	private PaDetailDAO paDetailDAO;
	
	private PaParamItemDAO paParamItemDAO ;
	
	private PaServices() {
		
		utilDAO = new PaUtilDAO() ;
		
		paReportDAO = new PaReportDAO() ;
		
		postgradedao = new PostGradeDAO() ;
		
		vouchertypedao = new VoucherTypeDAO() ;
		
		payPlanDAO = new PayPlanDAO();
		
		bonusDAO = new BonusDAO() ;
		
		paDetailDAO = new PaDetailDAO();
		
		paParamItemDAO = new PaParamItemDAO() ;
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
	 * retrieve update pa tables 
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	
	public List retrieveUpdatePaTableColumns(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrieveUpdatePaTableColumns(parameterObject);
	}
	
	/**
	 * update pa bonus history 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusHistory(Object parameterObject) throws GlRuntimeException {

		utilDAO.updatePaBonusHistory(parameterObject);
	}
	
	/**
	 * update pa bonus history proc
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void callPaBonusHistoryProc(Object parameterObject) throws GlRuntimeException {

		utilDAO.callPaBonusHistoryProc(parameterObject);
	}
	
	/**
	 * update pa history factory
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaHistoryData(Object parameterObject) throws GlRuntimeException {

		utilDAO.updatePaHistoryData(parameterObject);
	}
	
	/**
	 * update pa history proc
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void callPaHistoryProc(Object parameterObject) throws GlRuntimeException {

		utilDAO.callPaHistoryProc(parameterObject);
	}
	
	/**
	 * retrieve pa mail information count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int retrievePaMailCount(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaMailCount(parameterObject);
	}
	public int retrievePaOtherMailCount(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaOtherMailCount(parameterObject);
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
	public List retrievePaOtherSendMail(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaOtherSendMail(parameterObject);
	}
	
	/**
	 * retrieve paInfo send mail information
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaInfoForSendMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return utilDAO.retrievePaInfoForSendMail(parameterObject,currentPage,pageSize);
	}
	
	public List retrievePaInfoForSendOtherMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return utilDAO.retrievePaInfoForSendOtherMail(parameterObject,currentPage,pageSize);
	}
	
	/**
	 * retrieve paReplenishmentInfo send mail information
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaReplenishmentInfoForSendMail(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePaReplenishmentInfoForSendMail(parameterObject);
	}
	
	/**
	 * retrieve paReplenishmentInfo send mail information
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaReplenishmentInfoForSendMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return utilDAO.retrievePaReplenishmentInfoForSendMail(parameterObject,currentPage,pageSize);
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
	 * retrieve paBonusInfo send mail information
	 * 
	 * @param parameterObject,currentPage,pageSize
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePaBonusInfoForSendMail(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		return utilDAO.retrievePaBonusInfoForSendMail(parameterObject,currentPage,pageSize);
	}
	
	/**
	 * update pa email send ACTIVITY
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public void updatePaEmail(Object parameterObject) throws GlRuntimeException {

		utilDAO.updatePaEmail(parameterObject);
	}
	public void updatePaOtherEmail(Object parameterObject) throws GlRuntimeException {

		utilDAO.updatePaOtherEmail(parameterObject);
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
	
	public List retrievePa_Item_List_Month(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePa_Item_List_Month(parameterObject);
	}
	
	/**
	 * retrieve pa_util pa_bonus_item_list of month 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public List retrievePa_bonus_Item_List_Month(Object parameterObject) throws GlRuntimeException {

		return utilDAO.retrievePa_bonus_Item_List_Month(parameterObject);
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
	 * retrieve export t_pa_result data list
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveTPaResultExceptionDataList(SimpleMap parameterObject) throws GlRuntimeException {
		
		return paReportDAO.retrieveTPaResultExceptionDataList(parameterObject);
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
	
	/**
	 * retrieve pa bonus param   
	 * 
	 * @return Object
	 * @throws GlRuntimeException
	 */
	
	public Object retrievePaBonus_param(SimpleMap parameterObject) throws GlRuntimeException {

		return bonusDAO.retrievePaBonus_param(parameterObject);
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
	
	/**
	 * insert pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void insertPaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.insertPaBonusParam(parameterObject);
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
	
	/**
	 * delete pa bonus param  
	 * 
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public void deletePaBonusParam(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.deletePaBonusParam(parameterObject);
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
	
	/**
	 * update pa bonus formular  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusFormular(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusFormular(parameterObject);
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
	
	/**
	 * update pa bonus item caluOrder up 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	
	public void updatePaBonusItemCalcuOrderUp(SimpleMap parameterObject) throws GlRuntimeException {

		 bonusDAO.updatePaBonusItemCalcuOrderUp(parameterObject);
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
	 * get PA_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int paCheckNormalCalc(SimpleMap parameterObject) throws GlRuntimeException {

		return utilDAO.paCheckNormalCalc(parameterObject);
	}
	
	/**
	 * get PA_REPLENISHMENT_HISTORY count  
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int paCheckSpecialCalc(SimpleMap parameterObject) throws GlRuntimeException {

		return utilDAO.paCheckSpecialCalc(parameterObject);
	}
	/**
	 * get arStatusFlag   
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	
	public int arStatusFlag(SimpleMap parameterObject) throws GlRuntimeException {

		return utilDAO.arStatusFlag(parameterObject);
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
	
	public List getDeptPostGradeList(SimpleMap parameterObject) {
		List list = postgradedao.getDeptPostGrade(parameterObject);
		return list;
	}
	
	public List getPostGradeYear(SimpleMap parameterObject) {
		List list = postgradedao.getPostGradeYear(parameterObject);
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
	
	public void updateDeptPostGrade(List updateList) {
		postgradedao.UpdateDeptPostGrade(updateList);
	}

	public void deletePostGrade(SimpleMap parameterObject) {
		postgradedao.deletePostGrade(parameterObject);
	}
	
	
	/*搜索工资计划*/
	public List payPlanSerche(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.payPlanSerche(parameterObject);

	}
	public List ImportExcelProcess(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.ImportExcelProcess(parameterObject);

	}
	public List ImportExcelResult(Object parameterObject) throws GlRuntimeException {
		return payPlanDAO.ImportExcelResult(parameterObject);

	}
	/*把临时表的数据插入到正式表*/
	public void payPlanSave(List parameterObject) throws GlRuntimeException {
		 payPlanDAO.payPlanSave(parameterObject);

	}
	
	
	/*size PayCalculationSignsForSearch  */
	public int PayCalculationSignsForSearchNumber(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayCalculationSignsForSearchNumber(parameterObject);

	}
	
	/*size PayCalculationSignsForSearch  */
	public int PayCalculationSignsForSearchNumberDici(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayCalculationSignsForSearchNumberDici(parameterObject);

	}
	
	/*list PayCalculationSignsForSearch  */
	public List PayCalculationSignsForSearch(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.PayCalculationSignsForSearch(parameterObject,currentPage,pageSize);

	}
	
	/*list PayCalculationSignsForSearch  */
	public List PayCalculationSignsForSearchDici(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.PayCalculationSignsForSearchDici(parameterObject,currentPage,pageSize);

	}
	
	/*void MESFlagForUpdate  */
	public void MESFlagForUpdate(Object parameterObject) throws GlRuntimeException {
		 utilDAO.MESFlagForUpdate(parameterObject);

	}
	/*size MESFlagForSearchNumber  */
	public int MESFlagForSearchNumber(Object parameterObject) throws GlRuntimeException {
		return utilDAO.MESFlagForSearchNumber(parameterObject);

	}
	
	/*list MESFlagForSearch  */
	public List MESFlagForSearch(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.MESFlagForSearch(parameterObject,currentPage,pageSize);

	}
	
	/*size PayBankCodeForSearchNumber pa */
	public int PayBankCodeForSearchNumberPa(Object parameterObject) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchNumberPa(parameterObject);

	}
	
	/*list PayBankCodeForSearch pa */
	public List PayBankCodeForSearchPa(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return utilDAO.PayBankCodeForSearchPa(parameterObject,currentPage,pageSize);

	}
	/*list SearchCardno pa */
	public List SearchCardno(Object parameterObject) throws GlRuntimeException {
		return utilDAO.SearchCardno(parameterObject);

	}
	/*void SaveCardno pa */
	public void SaveCardno(Object parameterObject) throws GlRuntimeException {
		utilDAO.SaveCardno(parameterObject);

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
	/*list getF_viewDetailListNumberSearch  */
	public int getF_viewDetailListNumber(Object parameterObject) throws GlRuntimeException {
		return paDetailDAO.getF_viewDetailListNumber(parameterObject);

	}
	public List basicList(Object parameterObject) throws GlRuntimeException {
		return paDetailDAO.basicList(parameterObject);

	}
	public List paTypeList(Object parameterObject) throws GlRuntimeException {
		return paDetailDAO.paTypeList(parameterObject);

	}
	
	/*list getF_viewDetailListSearch  */
	public List getF_viewDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return paDetailDAO.getF_viewDetailList(parameterObject,currentPage,pageSize);

	}
	/*list getB_viewDetailListNumberSearch  */
	public int getB_viewDetailListNumber(Object parameterObject) throws GlRuntimeException {
		return paDetailDAO.getB_viewDetailListNumber(parameterObject);

	}
	
	/*list getB_viewDetailListSearch  */
	public List getB_viewDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return paDetailDAO.getB_viewDetailList(parameterObject,currentPage,pageSize);

	}
	/*list basicList  */
	public List basicListForDept(Object parameterObject) throws GlRuntimeException {
		return paDetailDAO.basicListForDept(parameterObject);

	}
	/*list getPaBonusDetailListNumber  */
	public int getPaBonusDetailListNumber(Object parameterObject) throws GlRuntimeException {
		return paDetailDAO.getPaBonusDetailListNumber(parameterObject);

	}
	
	/*list getPaBonusDetailList  */
	public List getPaBonusDetailList(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {
		return paDetailDAO.getPaBonusDetailList(parameterObject,currentPage,pageSize);

	}
	
	/*update PayCalculationSignsForUpdate  */
	public void PayCalculationSignsForUpdate(Object parameterObject) throws GlRuntimeException {
		utilDAO.PayCalculationSignsForUpdate(parameterObject);

	}
	
	/*update PayCalculationSignsForUpdate  */
	public void PayCalculationSignsForUpdateDici(Object parameterObject) throws GlRuntimeException {
		utilDAO.PayCalculationSignsForUpdateDici(parameterObject);

	}
	
	/*update PayCalculationSignsForUpdate  */
	public void PayBankCodeForUpdate(Object parameterObject) throws GlRuntimeException {
		utilDAO.PayBankCodeForUpdate(parameterObject);

	}
	/*save PayProbationSave  */
	public void PayProbationSave(Object parameterObject) throws GlRuntimeException {
		utilDAO.PayProbationSave(parameterObject);

	}
	/*select probationSearch  */
	public List probationSearch(Object parameterObject) throws GlRuntimeException {
		return utilDAO.probationSearch(parameterObject);

	}
	/*delete probationDelete  */
	public void probationDelete(Object parameterObject) throws GlRuntimeException {
		utilDAO.probationDelete(parameterObject);

	}
	/*update updateARHistoryData  */
	public void updateARHistoryData(Object parameterObject) throws GlRuntimeException {
		utilDAO.updateARHistoryData(parameterObject);

	}
	
	/**
	 * retrieve pa param item list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		return this.paParamItemDAO.retrievePaParamList(parameterObject,currentPage,pageSize) ;
	}
	
	/**
	 * retrieve pa param item list count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaParamListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		return this.paParamItemDAO.retrievePaParamListCnt(parameterObject) ;
	}
	
	/**
	 * retrieve pa param item
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamList(SimpleMap parameterObject) throws GlRuntimeException {

		return this.paParamItemDAO.retrievePaParamList(parameterObject) ;
	}
	
	/**
	 * retrieve pa param item configure list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamConfigureList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		return this.paParamItemDAO.retrievePaParamConfigureList(parameterObject,currentPage,pageSize) ;
	}
	
	/**
	 * retrieve pa param item list configure count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaParamConfigureListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		return this.paParamItemDAO.retrievePaParamConfigureListCnt(parameterObject) ;
	}
	
	/**
	 * retrieve pa param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaParamConfigureList(SimpleMap parameterObject) throws GlRuntimeException {

		return this.paParamItemDAO.retrievePaParamConfigureList(parameterObject) ;
	}
	
	/**
	 * delete pa param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void deletePaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		this.paParamItemDAO.deletePaParamConfigure(parameterObject) ;
	}
	
	/**
	 * validate update pa param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateUpdatePaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		return this.paParamItemDAO.validateUpdatePaParamConfigure(parameterObject) ;
	}
	
	
	/**
	 * update pa param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void updatePaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		this.paParamItemDAO.updatePaParamConfigure(parameterObject) ;
	}
	
	/**
	 * validate add pa param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateAddPaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		return this.paParamItemDAO.validateAddPaParamConfigure(parameterObject) ;
	}
	
	/**
	 * add pa param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void insertPaParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		this.paParamItemDAO.insertPaParamConfigure(parameterObject) ;
	}
	
	/**
	 * retrieve pa bonus param item list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		return this.bonusDAO.retrievePaBonusParamList(parameterObject,currentPage,pageSize) ;
	}
	
	/**
	 * retrieve pa Bonus param item list count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaBonusParamListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		return this.bonusDAO.retrievePaBonusParamListCnt(parameterObject) ;
	}
	
	/**
	 * retrieve pa bonus param item
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamList(SimpleMap parameterObject) throws GlRuntimeException {

		return this.bonusDAO.retrievePaBonusParamList(parameterObject) ;
	}
	
	/**
	 * retrieve pa bonus param item configure list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamConfigureList(SimpleMap parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		return this.bonusDAO.retrievePaBonusParamConfigureList(parameterObject,currentPage,pageSize) ;
	}
	
	/**
	 * retrieve pa bonus param item list configure count
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int retrievePaBonusParamConfigureListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		return this.bonusDAO.retrievePaBonusParamConfigureListCnt(parameterObject) ;
	}
	
	/**
	 * retrieve pa bonus param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List<SimpleMap> retrievePaBonusParamConfigureList(SimpleMap parameterObject) throws GlRuntimeException {

		return this.bonusDAO.retrievePaBonusParamConfigureList(parameterObject) ;
	}
	
	/**
	 * delete pa bonus param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void deletePaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		this.bonusDAO.deletePaBonusParamConfigure(parameterObject) ;
	}
	
	/**
	 * validate update pa bonus param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateUpdatePaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		return this.bonusDAO.validateUpdatePaBonusParamConfigure(parameterObject) ;
	}
	
	
	/**
	 * update pa bonus param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void updatePaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		this.bonusDAO.updatePaBonusParamConfigure(parameterObject) ;
	}
	
	/**
	 * validate add pa bonus param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public int validateAddPaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		return this.bonusDAO.validateAddPaBonusParamConfigure(parameterObject) ;
	}
	
	/**
	 * add pa bonus param item configure
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public void insertPaBonusParamConfigure(SimpleMap parameterObject) throws GlRuntimeException {

		this.bonusDAO.insertPaBonusParamConfigure(parameterObject) ;
	}
	
	/**
	 * pa bonus param Initial  
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void paBonusParamInitial(SimpleMap parameterObject) throws GlRuntimeException {

		this.bonusDAO.paBonusParamInitial(parameterObject) ;
	}
	
	public List paHousingFundList(SimpleMap parameterObject , int currentPage , int pageSize) throws GlRuntimeException {

		return this.payPlanDAO.paHousingFundList(parameterObject,currentPage,pageSize);
	}
	
	public List paHousingFundListExcel(SimpleMap parameterObject) throws GlRuntimeException {

		return this.payPlanDAO.paHousingFundListExcel(parameterObject);
	}
	
	public int housingCount(SimpleMap parameterObject) throws GlRuntimeException {

		return this.payPlanDAO.housingCount(parameterObject);
	}
	
	public void inertPaHouing(SimpleMap parameterObject) throws GlRuntimeException {

		this.payPlanDAO.inertPaHouing(parameterObject);
	}
	
	public void delPaHouing(SimpleMap parameterObject) throws GlRuntimeException {

		this.payPlanDAO.delPaHouing(parameterObject);
	}
	
	public void updatePaHouing(SimpleMap parameterObject) throws GlRuntimeException {

		this.payPlanDAO.updatePaHouing(parameterObject);
	}
	
	public List aHouingList(SimpleMap parameterObjec) throws GlRuntimeException {

		return this.payPlanDAO.aHouingList(parameterObjec);
	}
	public void deletePostGrade(String groupid) {
		postgradedao.deletePostGrade(groupid);
	}
	public List getPostGradeYear() {
		List list = postgradedao.getPostGradeYear();
		return list;
	}
	
	public List getDeptVoucherList(SimpleMap parameterObject) {
		List list = vouchertypedao.getDeptVoucherList(parameterObject);
		return list;
	}
	
	public List getDeptVoucherTypeList(SimpleMap parameterObject) {
		List list = vouchertypedao.getDeptVoucherTypeList(parameterObject);
		return list;
	}
	
	public void updateDeptVoucherType(List updateList) {
		vouchertypedao.updateDeptVoucherType(updateList);
	}
	

}