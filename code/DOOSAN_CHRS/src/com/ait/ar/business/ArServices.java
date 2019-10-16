package com.ait.ar.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ait.ar.bean.Annual;
import com.ait.ar.bean.DynamicGroup;
import com.ait.ar.bean.GroupCondition;
import com.ait.ar.bean.Supervisor;
import com.ait.ar.bean.SupervisorInfo;
import com.ait.ar.dao.AnnualBean;
import com.ait.ar.dao.ArCalculateDetailDAO;
import com.ait.ar.dao.ArDAOFactory;
import com.ait.ar.dao.ArDetailBean;
import com.ait.ar.dao.ArHistoryMonthBean;
import com.ait.ar.dao.ArItemBean;
import com.ait.ar.dao.ArShift010Bean;
import com.ait.ar.dao.ArStatusDAO;
import com.ait.ar.dao.ArUtils;
import com.ait.ar.dao.ArWorkCalendarBean;
import com.ait.ar.dao.ConditionDAO;
import com.ait.ar.dao.DynamicGroupDAO;
import com.ait.ar.dao.GroupConditionDAO;
import com.ait.ar.dao.SupervisorDAO;
import com.ait.ar.dao.SupervisorInfoDAO;
import com.ait.ar.dao.detaillock.AttDetailLockDAO;
import com.ait.ar.dao.idcard.AttRecordDAO;
import com.ait.ar.dao.otplan.OTPlanDAO;
import com.ait.ar.dao.task.AttTaskDAO;
import com.ait.ar.dao.vacation.VacationBean;
import com.ait.ar.dao.vacation.VacationEmpDAO;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

/**
 * <p>
 * Title:Arservices
 * </p>
 * 
 * <p>
 * Description: Arservices 是一个代理类。它将考勤模块的数据库操作与
 * 前台操作连接起来。考勤模块的所有数据库的操作方法都可以通过这个代理来 执行。前台操作和servlet操作都直接与Arservices这个代理进行联系。
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 * 
 * @author CHENGWEI
 * @version 2.0
 */
public class ArServices {

	public ArServices() {
	}

	/**
	 * addDynamicGroup <br>
	 * 添加一个动态组，同时此动态组的组成条件也被添加到数据库中。
	 * 
	 * @param group
	 *            DynamicGroup
	 * @param message
	 *            String
	 * @throws ArServicesException
	 * @return int
	 */
	public int addDynamicGroup(DynamicGroup group, String message) {
		int returnValue = -1;
		DynamicGroupDAO dynamicGroupDAO = (DynamicGroupDAO) ArDAOFactory.getInstance().getDynamicGroupDAO();
		try {
			returnValue = dynamicGroupDAO.addDynamicGroup(group, message);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return returnValue;
	}

	/**
	 * deleteGroup <br>
	 * 根据输入的组号，删除一个动态组，在删除组本身之前，要删除所有关联于此组的条件。
	 * 
	 * @param groupNo
	 *            int
	 * @return int
	 * @throws ArServicesException
	 */
	public int deleteGroup(int groupNo) {
		int returnValue = -1;
		DynamicGroupDAO dynamicGroupDAO = (DynamicGroupDAO) ArDAOFactory.getInstance().getDynamicGroupDAO();
		try {
			returnValue = dynamicGroupDAO.deleteGroup(groupNo);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return returnValue;
	}

	/**
	 * getGroupMembers <br>
	 * 根据输入的组号，得出所有符合本组条件的员工，将员工的工号和姓名存储到list中，供其它操作使用。
	 * 
	 * @param groupNo
	 *            int
	 * @return List @
	 */
	public List getGroupMembers(int groupNo,String cpny_id) {
		List list = null;
		DynamicGroupDAO dynamicGroupDAO = (DynamicGroupDAO) ArDAOFactory.getInstance().getDynamicGroupDAO();
		try {
			list = dynamicGroupDAO.getGroupMembers(groupNo,cpny_id);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return list;
	}
	
	public List kretrieveMonthlyStatusList(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		return arStatusDAO.kretrieveMonthlyStatusList(parameterObject);
	}

	/**
	 * getGroupList <br>
	 * 得出所有已经创建的组，并且按照组的拼音顺序排序。
	 * 
	 * @return List @
	 */
	public List getGroupList(String cpny_id) {
		DynamicGroupDAO dynamicGroupDAO = (DynamicGroupDAO) ArDAOFactory.getInstance().getDynamicGroupDAO();
		List returnGroupList = null;
		try {
			returnGroupList = dynamicGroupDAO.getGroupList(cpny_id);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return returnGroupList;
	}

	public Annual getAnnual(String empId, String dateStr) {
		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();
		return annualDAO.empAnnual(empId, dateStr);
	}

	public Annual getAnnual(String empId) {
		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();
		return annualDAO.empAnnual(empId);
	}

	/**
	 * getGroupByNo
	 * 
	 * @param groupNo
	 *            int
	 * @return DynamicGroup
	 */
	public DynamicGroup getGroupByNo(int groupNo) {
		DynamicGroup dynamicGroup = null;
		DynamicGroupDAO dynamicGroupDAO = (DynamicGroupDAO) ArDAOFactory.getInstance().getDynamicGroupDAO();
		try {
			dynamicGroup = dynamicGroupDAO.getGroupByNo(groupNo);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return dynamicGroup;
	}

	/**
	 * getAllSupervisor
	 * 
	 * @return List @
	 */
	public List getAllSupervisor(String cpny_id) {
		SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		List allSupervisorList = null;
		try {
			allSupervisorList = supervisorDAO.getAllSupervisor(cpny_id);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return allSupervisorList;
	}
	/**
	 * getAllSupervisorCount
	 * 
	 * @return int @
	 */
	public int getAllSupervisorCount(Object parameterObject) {
		SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		int  getAllSupervisorCount = 0;
		try {
			getAllSupervisorCount = supervisorDAO.getAllSupervisorCount(parameterObject);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return getAllSupervisorCount;
	}
	/**
	 * getAllSupervisorList
	 * 
	 * @return List @
	 */
	public List getAllSupervisorList(Object parameterObject,int currentPage,int pageSize) {
		SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		List allSupervisorList = null;
		try {
			allSupervisorList = supervisorDAO.getAllSupervisorList(parameterObject,currentPage,pageSize);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return allSupervisorList;
	}
	
	/**
	 * deleteSupervisor
	 * 
	 * @param supervisorID
	 * String @
	 */
	public void deleteSupervisor(String supervisorID) {
		SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		try {
			supervisorDAO.deleteSupervisor(supervisorID);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}

	/**
	 * addSupervisor
	 * 
	 * @param supervisor
	 * Supervisor @
	 */
	public void addSupervisor(Supervisor supervisor) {
		SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		try {
			supervisorDAO.addSupervisor(supervisor);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}

	/**
	 * addSupervisorInfo
	 * 
	 * @param supervisorInfo
	 * Supervisor @
	 */
	public void addSupervisorInfo(SupervisorInfo supervisorInfo) {
		SupervisorInfoDAO supervisorInfoDAO = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		try {
			supervisorInfoDAO.addSupervisorInfo(supervisorInfo);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}
	
  /**
   * editSupervisorScreenGrantNo
   *
   * @param supervisorInfo Supervisor
   */
	public void editSupervisorScreenGrantNo(String supervisorId) {
		SupervisorInfoDAO supervisorInfoDAO = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		try {
			supervisorInfoDAO.editSupervisorScreenGrantNo(supervisorId);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}
	
	  /**
		 * deleteSupervisorScreenGrantNo
		 * 
		 * @param supervisorInfo
		 *            Supervisor
		 */
	public void deleteSupervisorScreenGrantNo(String supervisorId) {
		SupervisorInfoDAO supervisorInfoDAO = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		try {
			supervisorInfoDAO.deleteSupervisorScreenGrantNo(supervisorId);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}

	/**
	 * getSupervisedDeptList
	 * 
	 * @param supervisorID
	 *            String
	 * @return List @
	 */
	public List getDeptListWithObject(String supervisorID) {
		List list = null;
		SupervisorInfoDAO supervisorInfoDAO = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		try {
			list = supervisorInfoDAO.getDeptListWithObject(supervisorID);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return list;
	}

	/**
	 * getSupervisedDeptList
	 * 
	 * @param supervisorID
	 *            String
	 * @return List @
	 */
	public List getSupervisorInfoList(String supervisorID) {
		List list = null;
		SupervisorInfoDAO supervisorInfoDAO = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		try {
			list = supervisorInfoDAO.getSupervisorInfoList(supervisorID);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return list;
	}

	/**
	 * getSupervisorById <br>
	 * 根据输入的考勤员工号，来返回考勤员
	 * 
	 * @param supervisorId
	 *            String
	 * @return Supervisor
	 */
	public Supervisor getSupervisorById(String supervisorId) {
		Supervisor supervisor = null;
		SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		try {
			supervisor = supervisorDAO.getSupervisorById(supervisorId);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return supervisor;
	}

	/**
	 * deleteSupervisorInfo <br>
	 * 考勤员直接管理的对象是员工或者部门，我们可以根据输入的考勤关系信息来删除被考勤对象
	 * 
	 * @param empID
	 *            String
	 * @return int @
	 */
	public int deleteSupervisorInfo(SupervisorInfo supervisorInfo) {
		int returnValue = -1;
		SupervisorInfoDAO supervisorInfoDAO = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		try {
			returnValue = supervisorInfoDAO.deleteSupervisorInfo(supervisorInfo);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return returnValue;
	}

	/**
	 * addGroupCondition <br>
	 * 为动态组添加条件，所有条件之间的关系是 intersection 关系。
	 * 
	 * @param condition
	 * GroupCondition @
	 * @return int
	 */
	public int addGroupCondition(GroupCondition condition) {
		GroupConditionDAO groupConditionDAO = (GroupConditionDAO) ArDAOFactory.getInstance().getGroupConditionDAO();
		try {
			groupConditionDAO.addGroupCondition(condition);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return 0;
	}

	/**
	 * addGroupCondition <br>
	 * 为动态组批量添加条件，所有条件之间的关系是 intersection 关系。
	 * 
	 * @param groupNo
	 *            String
	 * @param condition
	 *            String[]
	 * @param relation
	 *            String[]
	 * @param field1
	 *            String[]
	 * @param field2
	 * String[] @
	 * @return int
	 */
	public int addGroupCondition(String groupNo, String[] condition, String[] relation, String[] field1, String[] field2) {
		GroupConditionDAO groupConditionDAO = (GroupConditionDAO) ArDAOFactory.getInstance().getGroupConditionDAO();
		try {
			groupConditionDAO.addGroupCondition(groupNo, condition, relation, field1, field2);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return 0;
	}

	/**
	 * getConditionList <br>
	 * 根据输入的组编号，来导出所有关联于此组的条件。
	 * 
	 * @param groupNo
	 *            int
	 * @return List @
	 */
	public List getConditionList(int groupNo) {
		GroupConditionDAO groupConditionDAO = (GroupConditionDAO) ArDAOFactory.getInstance().getGroupConditionDAO();
		List conditionList = null;
		try {
			conditionList = groupConditionDAO.getConditionList(groupNo);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return conditionList;
	}

	/**
	 * getConditionList <br>
	 * 列出所有系统提供的的条件。
	 * 
	 * @return List @
	 */
	public List getConditionList() {
		List list = null;
		ConditionDAO conditionDAO = (ConditionDAO) ArDAOFactory.getInstance().getConditionDAO();
		try {
			list = conditionDAO.getAllConditions();
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return list;
	}

	/**
	 * deleteGroupCondition <br>
	 * 根据条件号，来删除某个关联动态组的条件,其中 conditionNo 是条件的编号。
	 * 
	 * @param conditionNo
	 * String @
	 */
	public void deleteGroupCondition(int conditionNo) {
		GroupConditionDAO groupConditionDAO = (GroupConditionDAO) ArDAOFactory.getInstance().getGroupConditionDAO();
		try {
			groupConditionDAO.deleteGroupCondition(conditionNo);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}

	public int modGroupCondition(String condition_no, String field1, String field2) {
		GroupConditionDAO groupConditionDAO = (GroupConditionDAO) ArDAOFactory.getInstance().getGroupConditionDAO();
		try {
			groupConditionDAO.modGroupCondition(condition_no, field1, field2);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return 0;
	}

	public List getShiftList() {
		List list = null;
		ArShift010Bean arShiftDAO = (ArShift010Bean) ArDAOFactory.getInstance().getShiftDAO();
		try {
			list = arShiftDAO.getShift010();
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		}
		return list;
	}

	public List getEmpCalendarList(String empId, String arMonth, String adminId, String cpny_id) {
		List list = null;
		ArWorkCalendarBean workCalendarDAO = (ArWorkCalendarBean) ArDAOFactory.getInstance().getEmpCalendarDAO();
		workCalendarDAO.setLoginID(adminId);
		try {
			list = workCalendarDAO.getEmpCalendar(empId, arMonth, cpny_id);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		return list;
	}

	public void delEmpCalendar(HttpServletRequest request) {
		ArWorkCalendarBean workCalendarDAO = (ArWorkCalendarBean) ArDAOFactory.getInstance().getEmpCalendarDAO();
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		workCalendarDAO.setLoginID(admin.getAdminID());
		try {
			workCalendarDAO.delEmpCalendar(request);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}

	public void modEmpCalendar(HttpServletRequest request) {
		ArWorkCalendarBean workCalendarDAO = (ArWorkCalendarBean) ArDAOFactory.getInstance().getEmpCalendarDAO();
		AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
		workCalendarDAO.setLoginID(admin.getAdminID());
		try {
			workCalendarDAO.modEmpCalendar(request);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
	}

	public List getItemList() {
		List list = null;
		ArItemBean arItemDAO = (ArItemBean) ArDAOFactory.getInstance().getItemDAO();
		try {
			list = arItemDAO.getItemList();
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
		}
		return list;
	}
	
	public List getDetailBackList(Object parameterObject,int currentPage, int pageSize) {
		List list = null;
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		try {
			list = detailDAO.getArDetailBackList(parameterObject,currentPage,pageSize);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		return list;
	}
	
	public List getDetailBackList(Object parameterObject) {
		List list = null;
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		try {
			list = detailDAO.getArDetailBackList(parameterObject);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		return list;
	}

	public List getDetailList(String sDate, String eDate, String key, AdminBean admin,String deptid, String itemNo, String sLength, String eLength, int currentPage, int pageSize, String empType, String personId, String tableName) {

		List list = null;
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		detailDAO.setLoginID(admin.getAdminID());
		detailDAO.setLoginCnpyId(admin.getCpnyId());

		list = detailDAO.getArDetail(sDate, eDate, key,deptid,itemNo,sLength,eLength, currentPage, pageSize, empType, personId,tableName);

		return list;
	}
	
	
	public List getArDetailForExecl(String sDate, String eDate, String key, String adminID,String deptid, String itemNo, String sLength, String eLength, String empType, String tableName) {

		List list = null;
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		detailDAO.setLoginID(adminID);
		list = detailDAO.getArDetail(sDate, eDate, key,deptid, itemNo, sLength, eLength, empType, tableName);

		return list;
	}
	
	public List getArDetailByEmail(String sDate, String eDate, String key, String adminID,String deptid, String itemNo, String sLength, String eLength, String empType, String tableName) {

		List list = null;
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		detailDAO.setLoginID(adminID);
		list = detailDAO.getArDetailByEmail(sDate, eDate, key,deptid, itemNo, sLength, eLength, empType, tableName);

		return list;
	}

	public int getDetailListCnt(String sDate, String eDate, String key, AdminBean admin,String deptid, String itemNo, String sLength, String eLength, String empType, String personId, String tableName) {

		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		detailDAO.setLoginID(admin.getAdminID());
		detailDAO.setLoginCnpyId(admin.getCpnyId());

		return detailDAO.getArDetailCnt(sDate, eDate, key, deptid, itemNo, sLength, eLength, empType, personId, tableName);
	}
	
	public int getDetailBackCnt(Object parameterObject) {

		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.getDetailBackCnt(parameterObject);
	}

	public List modDetail(HttpServletRequest request,AdminBean admin) {
		List errorList = new ArrayList() ;
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		try {
			errorList = detailDAO.modArDetail(request,admin);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		
		return errorList ;
	}

	public List delDetail(HttpServletRequest request,AdminBean admin) {
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		List list = null;
		try {
			list = detailDAO.delArDetail(request,admin);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		return list;
	}

	public List addDetail(HttpServletRequest request,AdminBean admin) {
		List errorList = new ArrayList() ;
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		try {
			errorList = detailDAO.addArDetail(request,admin);
		} catch (Exception ex) {
			Logger.getLogger(getClass()).debug(ex.toString());
			ex.printStackTrace();
		}
		
		return errorList ;
	}

	public String getStartDateStr() {
		String string = new String();
		ArUtils arUtilsDAO = (ArUtils) ArDAOFactory.getInstance().getArUtilsDAO();
		try {
			string = arUtilsDAO.getStartDateStr();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return string;
	}

	public String getEndDateStr() {
		String string = new String();
		ArUtils arUtilsDAO = (ArUtils) ArDAOFactory.getInstance().getArUtilsDAO();
		try {
			string = arUtilsDAO.getEndDateStr();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		}
		return string;
	}
	
	/**
	 * Retrieve VacationEmpREST_VAC_CNT
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public List retrieveVacationEmpREST_VAC_CNT(Object parameterObject) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		return vacationEmpDAO.retrieveVacationEmpREST_VAC_CNT(parameterObject);
	}
	
	/**
	 * init vacationEmp 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void initVacationEmp() throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		vacationEmpDAO.initVacationEmp();
	}
	
	/**
	 * retrieve vacation list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveVacationEmpCnt(Object parameterObject) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		return vacationEmpDAO.retrieveVacationEmpCnt(parameterObject);
	}

	/**
	 * retrieve vacationEmp list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveVacationEmpList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		return vacationEmpDAO.retrieveVacationEmpList(parameterObject, currentPage, pageSize);
	}
	
	
	
	/**
	 * retrieve vacationEmp list by paging
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveVacationEmpList(Object parameterObject) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		return vacationEmpDAO.retrieveVacationEmpList(parameterObject);
	}
	
	/**
	 * retrieve vacation list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveVacationCnt(Object parameterObject) throws GlRuntimeException {

		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO();

		return vacationDAO.retrieveVacationCnt(parameterObject);
	}
	
	/**
	 * retrieve vacation list by paging
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List retrieveVacationList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO();

		return vacationDAO.retrieveVacationList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve vacation strt_month list by paging
	 * 
	 * @return Object
	 */
	public List retrieveVacationStrt_monthList() throws GlRuntimeException {

		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO();

		return vacationDAO.retrieveVacationStrt_monthList();
	}
	
	/**
	 * retrieve annual holidy list by paging
	 * 
	 * @param parameterObject
	 * @return Object
	 */
	public List retrieveAnnualHolidyList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		return annualDAO.retrieveAnnualHolidyList(parameterObject, currentPage, pageSize);
	}

	/**
	 * retrieve annual holidy list
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveAnnualHolidy(Object parameterObject) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		return annualDAO.retrieveAnnualHolidy(parameterObject);
	}

	/**
	 * retrieve annual holidy report data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAnnualHolidyReport(Object parameterObject) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		return annualDAO.retrieveAnnualHolidyReport(parameterObject);
	}

	/**
	 * retrieve annual holidy list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveAnnualHolidyCnt(Object parameterObject) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		return annualDAO.retrieveAnnualHolidyCnt(parameterObject);
	}

	/**
	 * insert annual holidy
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAnnualHolidy(Object parameterObject) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.insertAnnualHolidy(parameterObject);
	}

	/**
	 * insert annual holidy
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertAnnualHolidy(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.insertAnnualHolidy(parameterObject, isAutoTrans);
	}

	/**
	 * update annual holidy
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAnnualHolidy(Object parameterObject) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.updateAnnualHolidy(parameterObject);

	}

	/**
	 * update annual holidy
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateAnnualHolidy(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.updateAnnualHolidy(parameterObject, isAutoTrans);
	}

	/**
	 * delete imported annual temp Data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteAnnualImportTempData() throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.deleteAnnualImportTempData();
	}

	/**
	 * insert annual holidy by import data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAnnualHolidyByImport() throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.insertAnnualHolidyByImport();
	}

	/**
	 * update annual holidy by import data
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateAnnualHolidyByImport() throws GlRuntimeException {

		AnnualBean annualDAO = (AnnualBean) ArDAOFactory.getInstance().getAnnualDAO();

		annualDAO.updateAnnualHolidyByImport();
	}

	/**
	 * get monthly attendance columns 2
	 * 
	 * @return String
	 * @throws GlRuntimeException
	 */
	public String getArColumns2(String cpnyId) throws GlRuntimeException {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArColumns2(cpnyId);

	}

	/**
	 * get monthly attendance columns
	 * 
	 * @return SimpleMap
	 */
	public List<SimpleMap> getArColumns(String cpnyId) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArColumns(cpnyId);
	}

	/**
	 * retrieve monthly attendance information
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List getArData(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArData(parameterObject);
	}
	
	/**
	 * retrieve ar Personal information
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public Object getArPersonalData(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArPersonalData(parameterObject);
	}

	/**
	 * retrieve monthly attendance information for paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 */
	public List getArData(Object parameterObject, int currentPage, int pageSize) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArData(parameterObject, currentPage, pageSize);
	}

	/**
	 * retrieve monthly attendance count
	 * 
	 * @param parameterObject
	 * @return int
	 */
	public int getArDataCnt(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArDataCnt(parameterObject);
	}

	/**
	 * retrieve attendance detail for attendance view
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAttDetailForView(Object parameterObject) throws GlRuntimeException {

		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveAttDetailForView(parameterObject);
	}
	
	/**
	 * Retrieve special item for attendance view
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveSpecialItemForViewForView(Object parameterObject) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveSpecialItemForView(parameterObject);
	}

	/**
	 * retrieve attendance start time and end time
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveAttStartEndTime(Object parameterObject) throws GlRuntimeException {

		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveAttStartEndTime(parameterObject);
	}

	/**
	 * retrieve over time for attendance view
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveOverTimeByView(Object parameterObject) throws GlRuntimeException {

		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveOverTimeByView(parameterObject);
	}

	/**
	 * retrieve monthly attendance report
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveMonthlyAttReport(Object parameterObject) throws GlRuntimeException {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveMonthlyAttReport(parameterObject);
	}

	/**
	 * retrieve monthly attendance detail report
	 * 
	 * @param parameterObject
	 * @return List
	 */
	public List retrieveMonthlyAttDetailReport(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveMonthlyAttDetailReport(parameterObject);
	}
	
	
	
	public List retrieveMonthlyConsumptioDisReport(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveMonthlyConsumptioDisReport(parameterObject);
	}
	
	public List retrieveMonthlyeatryReport(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveMonthlyeatryReport(parameterObject);
	}
	
	
	public List retrieveVisitoridCardReport(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveVisitoridCardReport(parameterObject);
	}
	public List retrieveDailyOtReport(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveDailyOtReport(parameterObject);
	}
	
	
	public List retrieveOverTimeDailyReport(Object parameterObject) {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.retrieveOverTimeDailyReport(parameterObject);
	}
	
	/**
	 * get begin date by attendance month
	 * 
	 * @param month
	 * @return
	 */
	public String getBeginDate(String month) {
		
		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();
		
		return arMonthDAO.getBeginDate(month);
	}

	/**
	 * get end date by attendance month
	 * 
	 * @param month
	 * @return
	 */
	public String getEndDate(String month) {
		
		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();
		
		return arMonthDAO.getEndDate(month);
	}
	
	/**
	 * retrieve monthly status list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveMonthlyStatusList(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		return arStatusDAO.retrieveMonthlyStatusList(parameterObject);
	}

	/**
	 * retrieve monthly status list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveMonthlyStatusList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		return arStatusDAO.retrieveMonthlyStatusList(parameterObject, currentPage, pageSize);
	}

	/**
	 * retrieve monthly status list count
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int retrieveMonthlyStatusCnt(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		return arStatusDAO.retrieveMonthlyStatusCnt(parameterObject);
	}

	/**
	 * insert monthly satatus
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertMonthlyStatus(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		arStatusDAO.insertMonthlyStatus(parameterObject);
	}

	/**
	 * update daily attendance status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateDailyAttStatus(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		arStatusDAO.updateDailyAttStatus(parameterObject);
	}

	/**
	 * update monthly attendance status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateMonthlyAttStatus(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		arStatusDAO.updateMonthlyAttStatus(parameterObject);
	}

	/**
	 * update monthly pay status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateMonthlyPayStatus(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		arStatusDAO.updateMonthlyPayStatus(parameterObject);
	}

	/**
	 * update daily status and monthly status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAttStatus(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		arStatusDAO.updateAttStatus(parameterObject);
	}

	/**
	 * update daily status,monthly status and pay status
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePayStatus(Object parameterObject) throws GlRuntimeException {

		ArStatusDAO arStatusDAO = (ArStatusDAO) ArDAOFactory.getInstance().getStatusDAO();

		arStatusDAO.updatePayStatus(parameterObject);
	}

	/**
	 * retrieve attendance record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAttRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		return attRecordDAO.retrieveAttRecordList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve attendance record list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveAttRecordList(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		return attRecordDAO.retrieveAttRecordList(parameterObject);
	}

	/**
	 * retrieve attendance record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveAttRecordListCount(Object parameterObject) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		return attRecordDAO.retrieveAttRecordListCount(parameterObject);
	}

	/**
	 * insert attendance record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAttRecord(Object parameterObject) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		attRecordDAO.insertAttRecord(parameterObject);
	}
	
	/**
	 * insert attendance records data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAttRecords(List parameterObject) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		attRecordDAO.insertAttRecords(parameterObject);
	}

	/**
	 * retrieve data for update attendance record 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public void retrieveDataForUpdateAttRecord(Object parameterObject) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		attRecordDAO.retrieveDataForUpdateAttRecord(parameterObject);
	}

	/**
	 * update attendance record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAttRecord(Object parameterObject) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		attRecordDAO.updateAttRecord(parameterObject);
	}

	/**
	 * delete attendance record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteAttRecord(Object parameterObject) throws GlRuntimeException {

		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		attRecordDAO.deleteAttRecord(parameterObject);
	}
	
	/**
	 * delete attendance record data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteAttRecord(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.deleteAttRecord(parameterObject, isAutoTrans);
	}
	
	/**
	 * validate R_TIEM
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateAttRecordDate(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.validateAttRecordDate(parameterObject);
	}
	
	/**
	 * validate validate empid and tot_vac_cnt
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateVacationEmpByUpdate(Object parameterObject) throws GlRuntimeException {
		
		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();
		
		return vacationEmpDAO.validateVacationEmpByUpdate(parameterObject);
	}
	
	/**
	 * validate empid and vac_tp and strt_date and end date
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateVacationEmp(Object parameterObject) throws GlRuntimeException {
		
		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();
		
		return vacationEmpDAO.validateVacationEmp(parameterObject);
	}
	
	/**
	 * insert vacationEmp data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertVacationEmp(Object parameterObject) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		vacationEmpDAO.insertVacationEmp(parameterObject);
	}
	
	/**
	 * retrievevacationEmp data for update vacationEmp relation
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateVacationEmp(Object parameterObject) throws GlRuntimeException {
		
		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();
		
		return vacationEmpDAO.retrieveDataForUpdateVacationEmp(parameterObject);
	}
	
	/**
	 * update vacationEmp relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateVacationEmp(Object parameterObject) throws GlRuntimeException {
		
		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();
		
		vacationEmpDAO.updateVacationEmp(parameterObject);
	}
	
	/**
	 * delete vacation data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteVacationEmp(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();
		
		vacationEmpDAO.deleteVacationEmp(parameterObject, isAutoTrans);
	}
	
	/**
	 * insert vacation data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertVacation(Object parameterObject) throws GlRuntimeException {

		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO();

		vacationDAO.insertVacation(parameterObject);
	}
	
	/**
	 * retrievevacation data for update vacation relation
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateVacation(Object parameterObject) throws GlRuntimeException {
		
		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO();
		
		return vacationDAO.retrieveDataForUpdateVacation(parameterObject);
	}
	
	/**
	 * delete vacation data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteVacation(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO() ;
		
		vacationDAO.deleteVacation(parameterObject, isAutoTrans);
	}
	
	/**
	 * update vacation relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateVacation(Object parameterObject) throws GlRuntimeException {
		
		VacationBean vacationDAO = (VacationBean) ArDAOFactory.getInstance().getVacationDAO() ;
		
		vacationDAO.updateVacation(parameterObject);
	}
	
	/**
	 * retrieve employee list by card
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListByCard(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveEmpListByCard(parameterObject);
	}
	
	/**
	 * retrieve visit card list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVisitCardList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveVisitCardList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve visit card list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVisitCardList(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveVisitCardList(parameterObject);
	}
	
	/**
	 * retrieve visit card list count
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveVisitCardListCnt(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveVisitCardListCnt(parameterObject);
	}
	
	/**
	 * retrieve visit card no list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveVisitCardNoList(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveVisitCardNoList(parameterObject);
	}
	
	/**
	 * add visit card relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertVisitCard(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.insertVisitCard(parameterObject);
	}
	
	/**
	 * retrieve visit card data for update visit card relation
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateVisitCard(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveDataForUpdateVisitCard(parameterObject);
	}
	
	/**
	 * update visit card relation
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateVisitCard(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.updateVisitCard(parameterObject);
	}

	/**
	 * validate start date or end date
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateVisitCardDate(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.validateVisitCardDate(parameterObject);
	}
	
	/**
	 * retrieve eatery record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEateryRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveEateryRecordList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve eatery record list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEateryRecordList(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveEateryRecordList(parameterObject);
	}
	
	/**
	 * retrieve eatery record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveEateryRecordListCount(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveEateryRecordListCount(parameterObject);
	}
	
	/**
	 * insert eatery record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertEateryRecord(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.insertEateryRecord(parameterObject);
	}
	
	/**
	 * retrieve data for update eatery record 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateEateryRecord(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveDataForUpdateEateryRecord(parameterObject);
	}
	
	/**
	 * update eatery record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateEateryRecord(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.updateEateryRecord(parameterObject);
	}
	
	/**
	 * delete eatery record data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteEateryRecord(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.deleteEateryRecord(parameterObject);
	}
	
	/**
	 * delete eatery record data by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void deleteEateryRecord(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		attRecordDAO.deleteEateryRecord(parameterObject, isAutoTrans);
	}
	
	/**
	 * retrieve employee list by eatery
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListByEatery(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.retrieveEmpListByEatery(parameterObject); 
	}
	
	/**
	 * validate eatery record date
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateEateryRecordDate(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();
		
		return attRecordDAO.validateEateryRecordDate(parameterObject);
	}
	
	/**
	 * calculate attendance detail data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public Object calculateAttDetail(List parameterObject)
			throws GlRuntimeException {
		
		ArCalculateDetailDAO calDetailDAO = (ArCalculateDetailDAO)ArDAOFactory.getInstance().getArCalculateDetailDAO();
		
		return calDetailDAO.calculateAttDetail(parameterObject);
	}
	
	/**
	 * retrieve date list
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveDateListForCalc(Object parameterObject)
			throws GlRuntimeException {
		
		ArCalculateDetailDAO calDetailDAO = (ArCalculateDetailDAO)ArDAOFactory.getInstance().getArCalculateDetailDAO();
		
		return calDetailDAO.retrieveDateListForCalc(parameterObject);
	}
	
	/**
	 * retrieve employee list by supervisor
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveEmpListBySupervisor(Object parameterObject)
			throws GlRuntimeException {
		
		ArCalculateDetailDAO calDetailDAO = (ArCalculateDetailDAO)ArDAOFactory.getInstance().getArCalculateDetailDAO();
		
		return calDetailDAO.retrieveEmpListBySupervisor(parameterObject);
	}
	
	/**
	 * retrieve ar item detail
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArItem(Object param) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveArItem(param);
	}
	
	/**
	 * retrieve ar detail sqlcontent
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public String getDetailSql(List itemList) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.getDetailSql(itemList);
	}
	
	/**
	 * retrieve ar detailStatistics data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArDetailStatisticsData(Object parameterObject) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveArDetailStatisticsData(parameterObject);
	}
	
	/**
	 * retrieve ar detailStatistics data end
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArDetailStatisticsData_end(Object parameterObject) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveArDetailStatisticsData_end(parameterObject);
	}
	
	
	public List retrieveDept() throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveDept();
	}
	
	/**
	 * retrieve ar dailydetailStatistics head data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public Object retrieveArDailyDetailStatisticsData_head(Object parameterObject) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveArDailyDetailStatisticsData_head(parameterObject);
	}
	
	/**
	 * retrieve ar dailydetailStatistics data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public Object retrieveArDailyDetailStatisticsData(Object parameterObject) throws GlRuntimeException {
		
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();

		return detailDAO.retrieveArDailyDetailStatisticsData(parameterObject);
	}
	
	/**
	 * init initOTPlan 
	 * 
	 * @throws GlRuntimeException
	 */
	public void initOTPlan(Object parameterObject) throws GlRuntimeException {

		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();

		otPlanDAO.initOTPlan(parameterObject);
	}
	
	/**
	 * NEW validate init arMonth
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public int validateInitOTPlan(Object parameterObject) throws GlRuntimeException {
		
		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();
		
		return otPlanDAO.validateInitOTPlan(parameterObject);
	}
	
	/**
	 * NEW retrieve overtime plan record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveOTPlanRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();

		return otPlanDAO.retrieveOTPlanRecordList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * NEW retrieve overtime plan record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveOTPlanRecordListCount(Object parameterObject) throws GlRuntimeException {

		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();

		return otPlanDAO.retrieveOTPlanRecordListCount(parameterObject);
	}
	
	/**
	 * NEW validate empid and plan_month
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateOTPlan(Object parameterObject) throws GlRuntimeException {
		
		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();
		
		return otPlanDAO.validateOTPlan(parameterObject);
	}
	
	/**
	 * NEW insert AR_OVERTIME_PLAN data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertOTPlan(Object parameterObject) throws GlRuntimeException {

		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();

		otPlanDAO.insertOTPlan(parameterObject);
	}
	
	/**
	 * NEW retrieveOTPlan data for update 
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object retrieveDataForUpdateOTPlan(Object parameterObject) throws GlRuntimeException {
		
		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();
		
		return otPlanDAO.retrieveDataForUpdateOTPlan(parameterObject);
	}
	
	/**
	 * update AR_OVERTIME_PLAN
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateOTPlan(Object parameterObject) throws GlRuntimeException {
		
		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();
		
		otPlanDAO.updateOTPlan(parameterObject);
	}
	
	/**
	 * delete OTPlan
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteOTPlan(Object parameterObject)throws GlRuntimeException{
		
		OTPlanDAO otPlanDAO = (OTPlanDAO) ArDAOFactory.getInstance().getOTPlanDAO();
		otPlanDAO.deleteOTPlan(parameterObject);
	}
	
	/**
	 * Retrieve detail lock list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveDetailLockList(Object parameterObject) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		return detailLockDAO.retrieveDetailLockList(parameterObject);
	}
	
	/**
	 * Retrieve detail Supervisor lock list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveDetailSupervisorLockList(Object parameterObject) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		return detailLockDAO.retrieveDetailSupervisorLockList(parameterObject);
	}
	
	/**
	 * Retrieve detail Supervisor lock list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveDetailSupervisorLockList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		return detailLockDAO.retrieveDetailSupervisorLockList(parameterObject,currentPage, pageSize);
	}
	
	/**
	 * Retrieve detail Supervisor lock count
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public int retrieveDetailSupervisorLockCnt(Object parameterObject) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		return detailLockDAO.retrieveDetailSupervisorLockCnt(parameterObject);
	}
	
	/**
	 * Update detail supervisor lock data
	 * 
	 * @param paramList
	 * @throws GlRuntimeException
	 */
	public void updateDetailSupervisorLock(List paramList) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		detailLockDAO.updateDetailSupervisorLock(paramList);
	}
	
	/**
	 * Delete detail lock data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteDetailLock(Object parameterObject) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		detailLockDAO.deleteDetailLock(parameterObject);
	}
	
	/**
	 * Update detail lock data
	 * 
	 * @param paramList
	 * @throws GlRuntimeException
	 */
	public void updateDetailLock(List paramList) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		detailLockDAO.updateDetailLock(paramList);
	}
	
	/**
	 * validate detail lock data
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object validateDetailLockData(Object parameterObject) throws GlRuntimeException {
		
		AttDetailLockDAO detailLockDAO = (AttDetailLockDAO) ArDAOFactory.getInstance().getAttDetailLockDAO();
		
		return detailLockDAO.validateDetailLockData(parameterObject);
	}
	
	/**
	 * sparate daily attendance
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void sparateDailyAttenance(Object parameterObject) throws GlRuntimeException {

		AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

		dao.sparateDailyAttenance(parameterObject);
	}
	
	/**
	 * sparate card record
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void sparateCardRecord(Object parameterObject) throws GlRuntimeException {

		AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

		dao.sparateCardRecord(parameterObject);
	}
	
	
	/**
	 * sparate eatery card record
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void sparateEateryCardRecord(Object parameterObject) throws GlRuntimeException {

		AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

		dao.sparateEateryCardRecord(parameterObject);
	}
	
	/**
	 * delete detail operation
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public void deleteDetailOperation(Object parameterObject) throws GlRuntimeException {

		AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

		dao.deleteDetailOperation(parameterObject);
	}
	
	public List getSupervisorItems(String supervisorID) throws GlRuntimeException {
		
		SupervisorInfoDAO dao = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();

		return dao.getSupervisorItems(supervisorID);
	}
	
	public void upDateItemId(String supervisorID, String itemId)throws GlRuntimeException{
		
		SupervisorInfoDAO dao = (SupervisorInfoDAO) ArDAOFactory.getInstance().getSupervisorInfoDAO();
		
		dao.upDateItemId(supervisorID, itemId);
	}
	
	/**
	 * check attendance record 
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public int checkAttRecord(Object parameterObject) throws GlRuntimeException {
		
		AttRecordDAO attRecordDAO = (AttRecordDAO) ArDAOFactory.getInstance().getAttRecordDAO();

		return attRecordDAO.checkAttRecord(parameterObject);
	}
	
	/**
	 * get ar dicc email 
	 * 
	 * @return String
	 * @throws GlRuntimeException
	 */
	public String getArEmail(Object parameterObject) throws GlRuntimeException {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArEmail(parameterObject);

	}
	
	
	/**
	 * get ar dicc email 
	 * 
	 * @return String
	 * @throws GlRuntimeException
	 */
	public String getArDetailEmail(Object parameterObject) throws GlRuntimeException {

		ArHistoryMonthBean arMonthDAO = (ArHistoryMonthBean) ArDAOFactory.getInstance().getMonthDAO();

		return arMonthDAO.getArDetailEmail(parameterObject);

	}
	
	/**
	 * retrieve vacationEmp list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveArFactroyTotalList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		return vacationEmpDAO.retrieveArFactroyTotalList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * retrieve vacationEmp list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	
	public List retrieveArFactroyTotalListSum(Object parameterObject) throws GlRuntimeException {

		VacationEmpDAO vacationEmpDAO = (VacationEmpDAO) ArDAOFactory.getInstance().getVacationEmpDAO();

		return vacationEmpDAO.retrieveArFactroyTotalListSum(parameterObject);
	}
	
	
	public List getArDetailPersonList(String sDate, String eDate, String key, AdminBean admin,String deptid, String itemNo, String sLength, String eLength, int currentPage, int pageSize, String empType, String personId, String tableName) {

		List list = null;
		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		detailDAO.setLoginID(admin.getAdminID());
		detailDAO.setLoginCnpyId(admin.getCpnyId());

		list = detailDAO.getArDetailPerson(sDate, eDate, key,deptid,itemNo,sLength,eLength, currentPage, pageSize, empType, personId,tableName);

		return list;
	}
	
	public int getDetailPersonListCnt(String sDate, String eDate, String key, AdminBean admin,String deptid, String itemNo, String sLength, String eLength, String empType, String personId, String tableName) {

		ArDetailBean detailDAO = (ArDetailBean) ArDAOFactory.getInstance().getDetailDAO();
		detailDAO.setLoginID(admin.getAdminID());
		detailDAO.setLoginCnpyId(admin.getCpnyId());

		return detailDAO.getArDetailPersonCnt(sDate, eDate, key, deptid, itemNo, sLength, eLength, empType, personId, tableName);
	}
	
	
	

		/**
		 * delete ar ot detail operation
		 * 
		 * @param parameterObject
		 * @return int
		 * @throws GlRuntimeException
		 */
		public void deleteArOtDetail() throws GlRuntimeException {

			AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

			dao.deleteArOtDetail();
		}
		
		/**
		 * delete T ar ot detail operation
		 * 
		 * @param parameterObject
		 * @return int
		 * @throws GlRuntimeException
		 */
		public void deleteTArOtDetail() throws GlRuntimeException {

			AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

			dao.deleteTArOtDetail();
		}
		
		/**
		 * insert ar ot detail operation
		 * 
		 * @param parameterObject
		 * @return int
		 * @throws GlRuntimeException
		 */
		public void insertArOtDetail() throws GlRuntimeException {

			AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

			dao.insertArOtDetail();
		}
		
		
		/**
		 * sparate emp attendance
		 * 
		 * @param parameterObject
		 * @return int
		 * @throws GlRuntimeException
		 */
		public void sparateEmpAttenance(Object parameterObject) throws GlRuntimeException {

			AttTaskDAO dao = (AttTaskDAO) ArDAOFactory.getInstance().getAttTaskDAO();

			dao.sparateEmpAttenance(parameterObject);
		}

}
