package com.ait.ar.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ait.ar.dao.detaillock.AttDetailLockDAO;
import com.ait.ar.dao.idcard.AttRecordDAO;
import com.ait.ar.dao.implementation.ArCalendarDAOImpl;
import com.ait.ar.dao.implementation.ConditionDAOImpl;
import com.ait.ar.dao.implementation.DynamicGroupDAOImpl;
import com.ait.ar.dao.implementation.GroupConditionDAOImpl;
import com.ait.ar.dao.implementation.ManualRecordDAOImpl;
import com.ait.ar.dao.implementation.SupervisorDAOImpl;
import com.ait.ar.dao.implementation.SupervisorInfoDAOImpl;
import com.ait.ar.dao.otplan.OTPlanDAO;
import com.ait.ar.dao.task.AttTaskDAO;
import com.ait.ar.dao.vacation.VacationBean;
import com.ait.ar.dao.vacation.VacationEmpDAO;

public class ArDAOFactory {
	private static final String SupervisorInfoDAO = "SupervisorInfoDAO";

	private static final String SupervisorDAO = "SupervisorDAO";

	private static final String ArCalendarDAO = "ArCalendarDAO";

	private static final String DynamicGroupDAO = "DynamicGroupDAO";

	private static final String GroupConditionDAO = "GroupConditionDAO";

	private static final String ManualRecordDAO = "ManualRecordDAO";

	private static final String ConditionDAO = "ConditionDAO";

	private static final String EmpCalendarDAO = "EmpCalendarDAO";

	private static final String ArShiftDAO = "ArShiftDAO";

	private static final String ArMonthDAO = "ArMontthDAO";

	private static final String ArDetailDAO = "ArDetailDAO";

	private static final String ArItemDAO = "ArItemDAO";

	private static final String ArStatusDAO = "ArStatusDAO";

	private static final String ArUtilsDAO = "ArUtilsDAO";

	private static final String Annual = "Annual";
	
	private static final String Vacation = "Vacation";
	
	private static final String VacationEmpDAO = "VacationEmpDAO";
	
	private static final String AttRecordDAO = "AttRecordDAO";
	
	private static final String OTPlanDAO = "OTPlanDAO";
	
	private static final String ArCalculateDetailDAO = "ArCalculateDetailDAO";
	
	private static final String AttDetailLockDAO = "AttDetailLockDAO";
	
	private static final String AttTaskDAO = "AttTaskDAO";

	private static ArDAOFactory instance = new ArDAOFactory();
	
	

	private Map daos;

	public ArDAOFactory() {
		daos = new HashMap();
		daos.put(SupervisorInfoDAO, new SupervisorInfoDAOImpl());
		daos.put(SupervisorDAO, new SupervisorDAOImpl());
		daos.put(ArCalendarDAO, new ArCalendarDAOImpl());
		daos.put(DynamicGroupDAO, new DynamicGroupDAOImpl());
		daos.put(GroupConditionDAO, new GroupConditionDAOImpl());
		daos.put(ManualRecordDAO, new ManualRecordDAOImpl());
		daos.put(ConditionDAO, new ConditionDAOImpl());
		daos.put(EmpCalendarDAO, new ArWorkCalendarBean());
		daos.put(ArShiftDAO, new ArShift010Bean());
		daos.put(ArMonthDAO, new ArHistoryMonthBean());
		daos.put(ArDetailDAO, new ArDetailBean());
		daos.put(ArItemDAO, new ArItemBean());
		daos.put(ArStatusDAO, new ArStatusDAO());
		daos.put(ArUtilsDAO, new ArUtils());
		daos.put(Annual, new AnnualBean());
		daos.put(Vacation, new VacationBean());
		daos.put(VacationEmpDAO, new VacationEmpDAO());
		daos.put(AttRecordDAO, new AttRecordDAO());
		daos.put(OTPlanDAO, new OTPlanDAO());
		daos.put(ArCalculateDetailDAO, new ArCalculateDetailDAO());
		daos.put(AttDetailLockDAO, new AttDetailLockDAO());
		daos.put(AttTaskDAO, new AttTaskDAO());
	}

	/**
	 * getDynamicGroupDAO
	 * 
	 * @return ArCalendarDAO
	 */
	public ArCalendarDAO getArCalendarDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.ArCalendarDAOImpl.ArCalendarDAOImpl");
		return (ArCalendarDAO) daos.get(ArCalendarDAO);
	}

	/**
	 * getDynamicGroupDAO
	 * 
	 * @return DynamicGroupDAO
	 */
	public DynamicGroupDAO getDynamicGroupDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.DynamicGroupDAOImpl.DynamicGroupDAOImpl");
		return (DynamicGroupDAO) daos.get(DynamicGroupDAO);
	}

	/**
	 * getManualRecordDAO
	 * 
	 * @return ManualRecordDAO
	 */
	public GroupConditionDAO getGroupConditionDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.GroupConditionDAOImpl.GroupConditionDAOImpl");
		return (GroupConditionDAO) daos.get(GroupConditionDAO);
	}

	/**
	 * getManualRecordDAO
	 * 
	 * @return ManualRecordDAO
	 */
	public ManualRecordDAO getManualRecordDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.ManualRecordDAOImpl.ManualRecordDAOImpl");
		return (ManualRecordDAO) daos.get(ManualRecordDAO);
	}

	/**
	 * getSupervisorInfoDAO
	 * 
	 * @return SupervisorInfoDAO
	 */
	public SupervisorInfoDAO getSupervisorInfoDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.SupervisorInfoDAOImpl.SupervisorInfoDAOImpl");
		return (SupervisorInfoDAO) daos.get(SupervisorInfoDAO);
	}

	/**
	 * getSupervisorDAO
	 * 
	 * @return SupervisorDAO
	 */
	public SupervisorDAO getSupervisorDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.SupervisorDAOImpl.SupervisorDAOImpl");
		return (SupervisorDAO) daos.get(SupervisorDAO);
	}

	/**
	 * getConditionDAO
	 * 
	 * @return SupervisorDAO
	 */
	public ConditionDAO getConditionDAO() {
		Logger
				.getLogger(getClass())
				.debug(
						"get object : com.ait.ar.dao.implementation.ConditionDAOImpl.ConditionDAOImpl");
		return (ConditionDAO) daos.get(ConditionDAO);
	}

	public ArWorkCalendarBean getEmpCalendarDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArWorkCalendarBean");
		return (ArWorkCalendarBean) daos.get(EmpCalendarDAO);
	}

	public ArHistoryMonthBean getMonthDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArHistoryMonthBean");
		return (ArHistoryMonthBean) daos.get(ArMonthDAO);
	}

	public ArDetailBean getDetailDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArDetailBean");
		return (ArDetailBean) daos.get(ArDetailDAO);
	}

	public ArShift010Bean getShiftDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArShift010Bean");
		return (ArShift010Bean) daos.get(ArShiftDAO);
	}

	public ArItemBean getItemDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArItemBean");
		return (ArItemBean) daos.get(ArItemDAO);
	}

	public ArStatusDAO getStatusDAO() {

		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArStatusDAO");
		return (ArStatusDAO) daos.get(ArStatusDAO);
	}

	public AnnualBean getAnnualDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.AnnualBean");
		return (AnnualBean) daos.get(Annual);
	}
	
	public VacationBean getVacationDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.vacation.VacationBean");
		return (VacationBean) daos.get(Vacation);
	}
	
	public VacationEmpDAO getVacationEmpDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.vacation.VacationEmpDAO");
		return (VacationEmpDAO) daos.get(VacationEmpDAO);
	}

	public ArUtils getArUtilsDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArUtils");
		return (ArUtils) daos.get(ArUtilsDAO);
	}
	
	public AttRecordDAO getAttRecordDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.idcard.AttRecordDAO");
		return (AttRecordDAO) daos.get(AttRecordDAO);
	}
	
	public ArCalculateDetailDAO getArCalculateDetailDAO() {
		Logger.getLogger(getClass()).debug(
				"get object : com.ait.ar.dao.ArCalculateDetailDAO");
		return (ArCalculateDetailDAO) daos.get(ArCalculateDetailDAO);
	}

	public static ArDAOFactory getInstance() {
		Logger.getLogger(ArDAOFactory.class)
				.debug("get ArDAOFactory instancs ");
		return instance;
	}
	
	public OTPlanDAO getOTPlanDAO() {
		Logger.getLogger(getClass()).debug("get object : com.ait.ar.dao.otplan.OTPlanDAO");
		return (OTPlanDAO) daos.get(OTPlanDAO);
	}
	
	public AttDetailLockDAO getAttDetailLockDAO() {
		Logger.getLogger(getClass()).debug("get object : com.ait.ar.dao.detaillock.AttDetailLockDAO");
		return (AttDetailLockDAO) daos.get(AttDetailLockDAO);
	}
	
	public AttTaskDAO getAttTaskDAO() {
		Logger.getLogger(getClass()).debug("get object : com.ait.ar.dao.task.AttTaskDAO");
		return (AttTaskDAO) daos.get(AttTaskDAO);
	}
}
