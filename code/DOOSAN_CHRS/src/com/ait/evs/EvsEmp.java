package com.ait.evs;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.hrm.bean.EmployeeBean;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DataAccessException;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class EvsEmp implements Serializable {

    /**
         * 
         */
    private static final long serialVersionUID = 1228675439994337150L;

    private static ServiceLocator services;
    
    private static final Logger logger = Logger.getLogger(EvsEmp.class) ;

    public EvsEmp() {
	try {
	    services = ServiceLocator.getInstance();
	} catch (ServiceLocatorException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public EvsEmp(String evEmpID, String evPeriodId) {

	this.evEmpID = evEmpID;
	this.evPeriodID = evPeriodId;

	try {
	    services = ServiceLocator.getInstance();
	} catch (ServiceLocatorException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getEvPeriodID() {
	return evPeriodID;
    }

    public void setEvPeriodID(String evPeriodID) {
	this.evPeriodID = evPeriodID;
    }

    public String getEvTypeID() {
	return evTypeID;
    }

    public void setEvTypeID(String evTypeID) {
	this.evTypeID = evTypeID;
    }

    public String getEvTypeName() {
	return evTypeName;
    }

    public void setEvTypeName(String evTypeName) {
	this.evTypeName = evTypeName;
    }

    public String getEvEmpID() {
	return evEmpID;
    }

    public void setEvEmpID(String evEmpID) {
	this.evEmpID = evEmpID;
    }

    public String getEvEmpName() {
	return evEmpName;
    }

    public void setEvEmpName(String evEmpName) {
	this.evEmpName = evEmpName;
    }

    public String getEvDeptID() {
	return evDeptID;
    }

    public void setEvDeptID(String evDeptID) {
	this.evDeptID = evDeptID;
    }

    public String getEvDeptName() {
	return evDeptName;
    }

    public void setEvDeptName(String evDeptName) {
	this.evDeptName = evDeptName;
    }

    public String getEvPostGroupID() {
	return evPostGroupID;
    }

    public void setEvPostGroupID(String evPostGroupID) {
	this.evPostGroupID = evPostGroupID;
    }

    public String getEvPostGroupName() {
	return evPostGroupName;
    }

    public void setEvPostGroupName(String evPostGroupName) {
	this.evPostGroupName = evPostGroupName;
    }

    public String getEvCurrentProcessID() {
	return evCurrentProcessID;
    }

    public void setEvCurrentProcessID(String evCurrentProcessID) {
	this.evCurrentProcessID = evCurrentProcessID;
    }

    public String getEvCurrentProcessName() {
	return evCurrentProcessName;
    }

    public void setEvCurrentProcessName(String evCurrentProcessName) {
	this.evCurrentProcessName = evCurrentProcessName;
    }

    public float getEvMark() {
	return evMark;
    }

    public void setEvMark(float evMark) {
	this.evMark = evMark;
    }

    public String getEvGradeID() {
	return evGradeID;
    }

    public void setEvGradeID(String evGradeID) {
	this.evGradeID = evGradeID;
    }

    public String getEvGradeName() {
	return evGradeName;
    }

    public void setEvGradeName(String evGradeName) {
	this.evGradeName = evGradeName;
    }

    public int getActivity() {
	return activity;
    }

    public void setActivity(int activity) {
	this.activity = activity;
    }

    /**
         * @return 返回 evsMaster。
         */
    public List getEvsMaster() {
	return EvsMaster;
    }

    /**
         * @param evsMaster
         *                要设置的 evsMaster。
         */
    public void setEvsMaster(List evsMaster) {
	EvsMaster = evsMaster;
    }

    private final static String GET_EVEMP_TYPELIST = " SELECT *  FROM SY_CODE "
	    + " WHERE PARENT_CODE='EVSTYPE' AND CODE_ID IN (SELECT DISTINCT EV_TYPE_ID FROM EVS_EMP) ";

    /**
         * 评价者中的评价类型列表
         * 
         * @return
         * @throws DataAccessException
         */
    public static List getEvEmpTypeList() throws DataAccessException {
	List lEvTypes = new Vector();
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = ServiceLocator.getInstance().getConnection();
	    pstmt = con.prepareStatement(GET_EVEMP_TYPELIST);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		EvsType evsType = new EvsType();
		evsType.setEvTypeID(rs.getString("code_id"));
		evsType.setEvTypeName(rs.getString("code_name"));
		lEvTypes.add(evsType);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpTypeList statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpTypeList statistics", sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvTypes;
    }

    private final static String GET_EVEMP_PERIODLIST = "SELECT * FROM EVS_PERIOD "
	    + " WHERE EV_PERIOD_ID IN (SELECT DISTINCT EV_PERIOD_ID FROM EVS_EMP) ";

    /**
         * 评价者中的评价期间列表
         * 
         * @return
         * @throws DataAccessException
         */
    public static List getEvEmpPeriodList() throws DataAccessException {
	List lEvPeriods = new Vector();
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = ServiceLocator.getInstance().getConnection();
	    pstmt = con.prepareStatement(GET_EVEMP_PERIODLIST);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		EvsPeriod evsPeriod = new EvsPeriod();
		evsPeriod.setEvPeriodID(rs.getString("ev_period_id"));
		evsPeriod.setEvPeriodName(rs.getString("ev_period_name"));
		evsPeriod.setEvYear(rs.getString("ev_year"));
		lEvPeriods.add(evsPeriod);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpPeriodList statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpPeriodList statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvPeriods;
    }

    private final static String GET_EVEMP_DEPTLIST = 
		" SELECT     deptid, deptname, dept_level	"+
    		"      FROM hr_department			"+
    		"     WHERE deptid IN (SELECT DISTINCT ev_dept_id FROM evs_dept)"+
    		" START WITH parent_dept_id IN (SELECT cpny_id	FROM hr_company)"+
    		" CONNECT BY PRIOR deptid = parent_dept_id		"+
    		" ORDER SIBLINGS BY orderno		";
    
    private final static String GET_EVSEMP_DEPTLIST = 
		" SELECT     deptid, deptname, dept_level	"+
    		"      FROM hr_department			"+
    		"     WHERE deptid IN (SELECT DISTINCT ev_dept_id FROM evs_dept)"+
    		" START WITH parent_dept_id IN (SELECT cpny_id  FROM hr_company t  where  t.cpny_id = '78000000')"+
    		" CONNECT BY PRIOR deptid = parent_dept_id		"+
    		" ORDER SIBLINGS BY orderno		";
    
    private final static String GET_EVEMP_EVSDEPTLIST = 
    	" SELECT T.EV_DEPT_ID, T.EV_DEPT_NAME, T.EV_DEPT_LEVEL, T.EV_PARENT_DEPT_ID,T.EV_DEPT_EN_NAME FROM EVS_DEPT T " +
		//" START WITH T.EV_PARENT_DEPT_ID IN (SELECT C.CPNY_ID FROM HR_COMPANY C where  c.cpny_id = '78000000') AND T.EV_PERIOD_ID = ? " +
		" START WITH T.EV_PARENT_DEPT_ID ='780O0000' " +
		" CONNECT BY PRIOR T.EV_DEPT_ID = T.EV_PARENT_DEPT_ID ";

    private final static String GET_EVEMP_EVSDEPTLISTl = 
    	"select * from (  SELECT T.EV_DEPT_ID, T.EV_DEPT_NAME, T.EV_DEPT_LEVEL, T.EV_PARENT_DEPT_ID,T.EV_DEPT_EN_NAME FROM EVS_DEPT T " +
		//" START WITH T.EV_PARENT_DEPT_ID IN (SELECT C.CPNY_ID FROM HR_COMPANY C where  c.cpny_id = '78000000') AND T.EV_PERIOD_ID = ? " +
		" START WITH T.EV_PARENT_DEPT_ID ='780O0000' " +
		" CONNECT BY PRIOR T.EV_DEPT_ID = T.EV_PARENT_DEPT_ID ) a "
		+" where EV_DEPT_ID in (SELECT DISTINCT   deptid  FROM  HR_DEPARTMENT   "
		+" START WITH deptid = (select deptid from hr_employee where person_id=? )"
		+" CONNECT  BY PRIOR deptid= parent_dept_id )";

    /**
         * 评价者中的部门列表原
         * 
         * @return
         * @throws DataAccessException
         */
    public static List getEvEmpDeptList1(String evPeriodId) throws DataAccessException {
	List lEvDetps = new Vector();
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = ServiceLocator.getInstance().getConnection();
	    
	    if(evPeriodId!=null&&!evPeriodId.equals(""))
	    {
	    	  pstmt = con.prepareStatement(GET_EVEMP_EVSDEPTLISTl);
	  	      pstmt.setString(1, evPeriodId) ;
	    }else	    	
	    	 pstmt = con.prepareStatement(GET_EVEMP_EVSDEPTLIST);
	    
		//pstmt.setString(2, evPeriodId) ;
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		Hashtable h_dept = new Hashtable();
		h_dept.put("deptId", rs.getString("EV_DEPT_ID"));
		h_dept.put("deptName", rs.getString("EV_DEPT_NAME"));
		h_dept.put("deptEnName", StringUtil.checkNull(rs.getString("EV_DEPT_EN_NAME")));
		h_dept.put("deptLevel", rs.getString("EV_DEPT_LEVEL"));
		lEvDetps.add(h_dept);

	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpDeptList statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpDeptList statistics", sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvDetps;
    }
    
    
    /**
	 * 评价者中的部门列表 新
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public static List getEvsEmpDeptList(String adminid) throws DataAccessException {
		List lEvDetps = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = ServiceLocator.getInstance().getConnection();
			Logger.getLogger("EvsEmpjava").debug(GET_EVSEMP_DEPTLIST);
			Logger.getLogger("EvsEmpjava").debug(adminid);
			pstmt = con.prepareStatement(GET_EVSEMP_DEPTLIST);
			pstmt.setString(1, adminid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Hashtable h_dept = new Hashtable();
				h_dept.put("deptId", rs.getString("deptid"));
				int level=Integer.parseInt((String)rs.getString("dept_level"));
                String deptname = "";
	             for(int j=0;j<level;j++){
	               deptname +="　";
	             }
				h_dept.put("deptName", deptname+rs.getString("deptname"));
				h_dept.put("deptLevel", rs.getString("dept_level"));
				lEvDetps.add(h_dept);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvEmpDeptList statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvEmpDeptList statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return lEvDetps;
	}
    
    /**
         * 被评价者列表
         * 
         * @param evDeptId
         * @param evPeriodId
         * @param evTypeId
         * @return
         * @throws DataAccessException
         */
    public List getEvEmpsByDeptPeriodType(String evDeptId, String evPeriodId,
	    String evTypeId) throws DataAccessException {

	List lEvEmps = null;
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";

	String ORDER_SQL = " ORDER BY ev_type_id,ev_dept_id,ev_period_id,ev_emp_id";

	if (!evDeptId.equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}

	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}
	if (!evTypeId.equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	}

	String SELECT_SQL = " SELECT /* INDEX (evs_emp  idx_evs_emp) */ * FROM evs_emp_v1 WHERE ev_emp_id is not null  "
		+ DEPT_SQL + PERIOD_SQL + TYPE_SQL;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {

	    con = services.getConnection();
	    pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

	    rs = pstmt.executeQuery();
	    lEvEmps = new Vector();

	    while (rs.next()) {

		EvsEmp evsEmp = new EvsEmp();
		evsEmp.setEvCurrentProcessID(rs.getString("ev_process_id"));
		evsEmp.setEvCurrentProcessName(rs.getString("ev_process_name"));
		evsEmp.setEvDeptID(rs.getString("ev_dept_id"));
		evsEmp.setEvDeptName(rs.getString("ev_dept_name"));
		evsEmp.setEvEmpID(rs.getString("ev_emp_id"));
		evsEmp.setEvEmpID2(rs.getString("ev_emp_id1"));
		evsEmp.setEvEmpName(rs.getString("ev_emp_name"));
		evsEmp.setEvGradeID(rs.getString("ev_grade_id"));
		evsEmp.setEvMark(rs.getFloat("ev_mark"));
		evsEmp.setEvPeriodID(rs.getString("ev_period_id"));
		evsEmp.setEvTypeName(rs.getString("ev_type_name"));
		evsEmp.setEvTypeID(rs.getString("ev_type_id"));
		evsEmp.setActivity(rs.getInt("activity"));
		evsEmp.setEvGradeName(rs.getString("ev_grade_name"));
		evsEmp.setEvPostGroupID(rs.getString("ev_post_group_id"));
		evsEmp.setEvPostGroupName(rs.getString("ev_post_group_name"));
		evsEmp.setEvsMaster(com.ait.evs.EvsMaster.getEvsMasterByEvsEmp(evsEmp.getEvEmpID(), evsEmp.getEvPeriodID())) ;
		lEvEmps.add(evsEmp);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpsByDeptPeriodType statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpsByDeptPeriodType statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvEmps;
    }

    /**
         * 被评价者列表
         * 
         * @param evDeptId
         * @param evPeriodId
         * @param evTypeId
         * @param evProcessId
         * @return
         * @throws DataAccessException
         */
    public List getEvEmpsByDeptPeriodType(String evDeptId, String evPeriodId,
	    String evTypeId, String evProcessId) throws DataAccessException {

	List lEvEmps = new Vector();
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";
	String PROCESS_SQL = "";
	String ORDER_SQL = " ORDER BY ev_type_id,ev_dept_id,ev_period_id,ev_emp_id";

	if (!evDeptId.equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}

	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}

	if (!evProcessId.equals("")) {
	    PROCESS_SQL = " AND ev_process_id='" + evProcessId + "' ";
	}

	if (!evTypeId.equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	}

	String SELECT_SQL = " SELECT /* INDEX (evs_emp  idx_evs_emp) */ * FROM evs_emp_v1 WHERE ev_emp_id is not null  "
		+ DEPT_SQL + PERIOD_SQL + TYPE_SQL + PROCESS_SQL;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		EvsEmp evsEmp = new EvsEmp();
		evsEmp.setEvCurrentProcessID(rs.getString("ev_process_id"));
		evsEmp.setEvCurrentProcessName(rs.getString("ev_process_name"));
		evsEmp.setEvDeptID(rs.getString("ev_dept_id"));
		evsEmp.setEvDeptName(rs.getString("ev_dept_name"));
		evsEmp.setEvEmpID(rs.getString("ev_emp_id"));
		evsEmp.setEvEmpID2(rs.getString("ev_emp_id1"));
		evsEmp.setEvEmpName(rs.getString("ev_emp_name"));
		evsEmp.setEvGradeID(rs.getString("ev_grade_id"));
		evsEmp.setEvMark(rs.getFloat("ev_mark"));
		evsEmp.setEvPeriodID(rs.getString("ev_period_id"));
		evsEmp.setEvTypeName(rs.getString("ev_type_name"));
		evsEmp.setEvTypeID(rs.getString("ev_type_id"));
		evsEmp.setActivity(rs.getInt("activity"));
		evsEmp.setEvGradeName(rs.getString("ev_grade_name"));
		evsEmp.setEvPostGroupID(rs.getString("ev_post_group_id"));
		evsEmp.setEvPostGroupName(rs.getString("ev_post_group_name"));
		lEvEmps.add(evsEmp);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpsByDeptPeriodType statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpsByDeptPeriodType statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvEmps;
    }
    /**
     * 被评价者列表
     * 
     * @param evDeptId
     * @param evPeriodId
     * @param evTypeId
     * @param evProcessId
     * @return
     * @throws DataAccessException
     */
public List getEvEmpsByDeptPeriodType(String evDeptId, String evPeriodId,
	    String evTypeId, String evProcessId,String evMasterId) throws DataAccessException {

	List lEvEmps = new Vector();
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";
	String PROCESS_SQL = "";
	String MASTER_SQL = "";
	String ORDER_SQL = " ORDER BY ev_type_id,ev_dept_id,ev_period_id,ev_emp_id";

	if (!evDeptId.equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}

	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}

	if (!evProcessId.equals("")) {
	    PROCESS_SQL = " AND ev_process_id='" + evProcessId + "' ";
	}

	if (!evTypeId.equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	}
	
	if (!evMasterId.equals("")) {
	    MASTER_SQL = " AND EXISTS( SELECT * FROM EVS_MASTER " +
	    		"	WHERE EVS_MASTER.EV_PROCESS_ID='"+Constants.EVPROCESS015+"'" +
	    		"	AND EVS_MASTER.EV_EMP_ID=EVS_EMP_V.EMPID " +
	    		"	AND EVS_MASTER.EV_PERIOD_ID=EVS_EMP_V.EV_PERIOD_ID " +
	    		"	AND EVS_MASTER.EV_MASTER='"+evMasterId+"')";
	}
	String SELECT_SQL = " SELECT /* INDEX (evs_emp  idx_evs_emp) */ * FROM evs_emp_v WHERE ev_emp_id is not null  "
		+ DEPT_SQL + PERIOD_SQL + TYPE_SQL + PROCESS_SQL+MASTER_SQL;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    Logger.getLogger(this.getClass()).debug(SELECT_SQL+ ORDER_SQL);
	    pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		EvsEmp evsEmp = new EvsEmp();
		evsEmp.setEvCurrentProcessID(rs.getString("ev_process_id"));
		evsEmp.setEvCurrentProcessName(rs.getString("ev_process_name"));
		evsEmp.setEvDeptID(rs.getString("ev_dept_id"));
		evsEmp.setEvDeptName(rs.getString("ev_dept_name"));
		evsEmp.setEvEmpID(rs.getString("ev_emp_id"));
		evsEmp.setEvEmpName(rs.getString("ev_emp_name"));
		evsEmp.setEvGradeID(rs.getString("ev_grade_id"));
		evsEmp.setEvMark(rs.getFloat("ev_mark"));
		evsEmp.setEvPeriodID(rs.getString("ev_period_id"));
		evsEmp.setEvTypeName(rs.getString("ev_type_name"));
		evsEmp.setEvTypeID(rs.getString("ev_type_id"));
		evsEmp.setActivity(rs.getInt("activity"));
		evsEmp.setEvGradeName(rs.getString("ev_grade_name"));
		evsEmp.setEvPostGroupID(rs.getString("ev_post_group_id"));
		evsEmp.setEvPostGroupName(rs.getString("ev_post_group_name"));
		lEvEmps.add(evsEmp);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpsByDeptPeriodType statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpsByDeptPeriodType statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvEmps;
}
    /**
         * 各流程员工总数
         * 
         * @param evDeptId
         * @param evPeriodId
         * @param evTypeId
         * @param evProcessId
         * @return
         * @throws DataAccessException
         */
    public List getEvEmpCountByDeptPeriodType(String evDeptId,
	    String evPeriodId, String evTypeId, String evProcessId)
	    throws DataAccessException {

	List lEvEmps = new Vector();
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";
	String PROCESS_SQL = "";

	if (!evDeptId.equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}

	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}

	if (!evProcessId.equals("")) {
	    PROCESS_SQL = " AND ev_process_id='" + evProcessId + "' ";
	}

	if (!evTypeId.equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	}
	
	String SELECT_SQL = " SELECT COUNT(ev_emp_id) as empcount,MAX(ev_process_name) ev_process_name FROM evs_emp_v WHERE ev_emp_id is not null  "
		+ DEPT_SQL
		+ PERIOD_SQL
		+ TYPE_SQL
		+ PROCESS_SQL
		+ " GROUP BY ev_process_id ";

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(SELECT_SQL);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		HashMap hEmp = new HashMap();
		hEmp.put("empCount", new Integer(rs.getInt("empcount")));
		hEmp.put("evProcessName", rs.getString("ev_process_name"));
		lEvEmps.add(hEmp);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpCountByDeptPeriodType statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpCountByDeptPeriodType statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvEmps;
    }
    /**
     * 各流程员工总数
     * 
     * @param evDeptId
     * @param evPeriodId
     * @param evTypeId
     * @param evProcessId
     * @return
     * @throws DataAccessException
     */
public List getEvEmpCountByDeptPeriodType(String evDeptId,
	    String evPeriodId, String evTypeId, String evProcessId,String evMasterId)
	    throws DataAccessException {

	List lEvEmps = new Vector();
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";
	String PROCESS_SQL = "";
	String MASTER_SQL="";
	if (!evDeptId.equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}

	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}

	if (!evProcessId.equals("")) {
	    PROCESS_SQL = " AND ev_process_id='" + evProcessId + "' ";
	}

	if (!evTypeId.equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	}
	if (!evMasterId.equals("")) {
	    MASTER_SQL = " AND EXISTS( SELECT * FROM EVS_MASTER " +
	    		"	WHERE EVS_MASTER.EV_PROCESS_ID='"+Constants.EVPROCESS015+"'" +
	    		"	AND EVS_MASTER.EV_EMP_ID=EVS_EMP_V.EMPID " +
	    		"	AND EVS_MASTER.EV_PERIOD_ID=EVS_EMP_V.EV_PERIOD_ID " +
	    		"	AND EVS_MASTER.EV_MASTER='"+evMasterId+"')";
	}
	String SELECT_SQL = " SELECT COUNT(ev_emp_id) as empcount,MAX(ev_process_name) ev_process_name FROM evs_emp_v WHERE ev_emp_id is not null  "
		+ DEPT_SQL
		+ PERIOD_SQL
		+ TYPE_SQL
		+ PROCESS_SQL
		+ MASTER_SQL
		+ " GROUP BY ev_process_id ";

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(SELECT_SQL);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		HashMap hEmp = new HashMap();
		hEmp.put("empCount", new Integer(rs.getInt("empcount")));
		hEmp.put("evProcessName", rs.getString("ev_process_name"));
		lEvEmps.add(hEmp);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpCountByDeptPeriodType statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpCountByDeptPeriodType statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvEmps;
}
    /**
         * 被评价者列表
         * 
         * @param evDeptId
         * @param evPeriodId
         * @param evTypeId
         * @return
         * @throws DataAccessException
         */
    public List getEvEmpsByDeptPeriodTypeProcess(String evDeptId,
	    String evPeriodId, String evTypeId, String evProcessId)
	    throws DataAccessException {

	List lEvEmps = new Vector();
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";
	String PROCESS_SQL = "";
	String ORDER_SQL = " ORDER BY ev_type_id,ev_dept_id,ev_period_id,ev_emp_id";
	if (!evDeptId.equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}

	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}
	if (!evTypeId.equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	}
	if (!evProcessId.equals("")) {
	    PROCESS_SQL = " AND ev_process_id='" + evProcessId + "' ";
	}

	String SELECT_SQL = " SELECT /* INDEX (evs_emp  idx_evs_emp) */ * FROM evs_emp_v WHERE ev_emp_id is not null  "
		+ DEPT_SQL + PERIOD_SQL + TYPE_SQL + PROCESS_SQL;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(SELECT_SQL + ORDER_SQL);

	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		EvsEmp evsEmp = new EvsEmp();
		evsEmp.setEvCurrentProcessID(rs.getString("ev_process_id"));
		evsEmp.setEvCurrentProcessName(rs.getString("ev_process_name"));
		evsEmp.setEvDeptID(rs.getString("ev_dept_id"));
		evsEmp.setEvDeptName(rs.getString("ev_dept_name"));
		evsEmp.setEvEmpID(rs.getString("ev_emp_id"));
		evsEmp.setEvEmpName(rs.getString("ev_emp_name"));
		evsEmp.setEvGradeID(rs.getString("ev_grade_id"));
		evsEmp.setEvMark(rs.getFloat("ev_mark"));
		evsEmp.setEvPeriodID(rs.getString("ev_period_id"));
		evsEmp.setEvTypeName(rs.getString("ev_type_name"));
		evsEmp.setEvTypeID(rs.getString("ev_type_id"));
		evsEmp.setActivity(rs.getInt("activity"));
		evsEmp.setEvGradeName(rs.getString("ev_grade_name"));
		evsEmp.setEvPostGroupID(rs.getString("ev_post_group_id"));
		evsEmp.setEvPostGroupName(rs.getString("ev_post_group_name"));
		lEvEmps.add(evsEmp);
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpsByDeptPeriodTypeProcess statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpsByDeptPeriodTypeProcess statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEvEmps;
    }

    private final static String GET_EVSMASTER_BY_EMPIDPERIOD = " SELECT * FROM evs_emp_v WHERE ev_emp_id=? AND ev_period_id=? ";

    /**
         * 取得被评价者的评价者列表
         * 
         * @throws DataAccessException
         */
    public void getEvEmpMastersByEmpIdPeriod() throws DataAccessException {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(GET_EVSMASTER_BY_EMPIDPERIOD);

	    pstmt.setString(1, this.evEmpID);
	    pstmt.setString(2, this.evPeriodID);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {

		this.setEvCurrentProcessID(rs.getString("ev_process_id"));
		this.setEvCurrentProcessName(rs.getString("ev_process_name"));
		this.setEvDeptID(rs.getString("ev_dept_id"));
		this.setEvDeptName(rs.getString("ev_dept_name"));
		this.setEvEmpID(rs.getString("ev_emp_id"));
		this.setEvEmpName(rs.getString("ev_emp_name"));
		this.setEvGradeID(rs.getString("ev_grade_id"));
		this.setEvMark(rs.getFloat("ev_mark"));
		this.setEvPeriodID(rs.getString("ev_period_id"));
		this.setEvTypeName(rs.getString("ev_type_name"));
		this.setEvTypeID(rs.getString("ev_type_id"));
		this.setActivity(rs.getInt("activity"));

	    }
	    this.setEvsMaster(com.ait.evs.EvsMaster.getEvsMasterByEvsEmp(
		    this.evEmpID, this.evPeriodID));
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpMastersByEmpIdPeriod statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpMastersByEmpIdPeriod statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
    }

    /**
         * 取得被评价者
         * 
         * @throws DataAccessException
         */
    public void getEvEmpByEmpIdPeriod() throws DataAccessException {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(GET_EVSMASTER_BY_EMPIDPERIOD);

	    pstmt.setString(1, this.evEmpID);
	    pstmt.setString(2, this.evPeriodID);
	    rs = pstmt.executeQuery();

	    while (rs.next()) {

		this.setEvCurrentProcessID(rs.getString("ev_process_id"));
		this.setEvCurrentProcessName(rs.getString("ev_process_name"));
		this.setEvDeptID(rs.getString("ev_dept_id"));
		this.setEvDeptName(rs.getString("ev_dept_name"));
		this.setEvEmpID(rs.getString("ev_emp_id"));
		this.setEvEmpName(rs.getString("ev_emp_name"));
		this.setEvGradeID(rs.getString("ev_grade_id"));
		this.setEvGradeName(rs.getString("ev_grade_name"));
		this.setEvMark(rs.getFloat("ev_mark"));
		this.setEvPeriodID(rs.getString("ev_period_id"));
		this.setEvTypeName(rs.getString("ev_type_name"));
		this.setEvTypeID(rs.getString("ev_type_id"));
		this.setActivity(rs.getInt("activity"));
		this.setEvGradeName(rs.getString("ev_grade_name"));
		this.setEvPostGroupID(rs.getString("ev_post_group_id"));
		this.setEvPostGroupName(rs.getString("ev_post_group_name"));

	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpByEmpIdPeriod statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpByEmpIdPeriod statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
    }

    private final static String ADD_EVSEMP = " INSERT INTO evs_emp "
		+ "( ev_period_id,ev_emp_id,ev_emp_name,ev_dept_id,ev_dept_name, "
		+ " ev_post_group_id,ev_post_group_name,ev_type_id,ev_process_id,activity) "
		+ " VALUES(?,?,?,?,?,?,?,?,?,1)";

    /**
         * 
         * @param lEvsEmp
         * @throws DataAccessException
         */
    public void addEvsEmp(List lEvsEmp,String cpnyid) throws DataAccessException {
	Connection con = null;
	PreparedStatement pstmt = null;
	//System.out.println("123123123213213" + cpnyid);
	try {

	    EvsMaster evsMaster = new EvsMaster();
	    if (lEvsEmp != null) {
		int evsEmpSize = lEvsEmp.size();
		for (int i = 0; i < evsEmpSize; i++) {
		    EvsEmp evsEmp = (EvsEmp) lEvsEmp.get(i);
		    pstmt = null;

		    con = services.getConnection();
		    con.setAutoCommit(false);
		    // 先删除被评价者相应信息
		    this.delEvsEmp(evsEmp.getEvEmpID(), evsEmp.getEvPeriodID(),
			    con);
		    pstmt = con.prepareStatement(ADD_EVSEMP);
		    pstmt.setString(1, evsEmp.getEvPeriodID());
		    pstmt.setString(2, evsEmp.getEvEmpID()); 
		    pstmt.setString(3, evsEmp.getEvEmpName());
		    pstmt.setString(4, evsEmp.getEvDeptID());
		    pstmt.setString(5, evsEmp.getEvDeptName());
		    pstmt.setString(6, evsEmp.getEvPostGroupID());
		    pstmt.setString(7, evsEmp.getEvPostGroupName());
		    pstmt.setString(8, evsEmp.getEvTypeID());
		    pstmt.setString(9, evsEmp.getEvCurrentProcessID());
		    pstmt.executeUpdate();
		    evsMaster.addEvsMaster1(evsEmp.getEvsMaster(),cpnyid, con);

		    con.commit();
		    SqlUtil.close(pstmt, con);

		}

	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    sqle.printStackTrace();

	    throw new DataAccessException(
		    "cant execute query for addEvsEmp statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    throw new DataAccessException(
		    "cant get connection for addEvsEmp statistics", sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
	    }
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(pstmt, con);
	}
    }

    private final static String DEL_EVSEMP = " DELETE evs_emp WHERE ev_period_id=? AND ev_emp_id=? ";

    /**
         * 删除被评价者
         * 
         * @param evEmpId
         * @param evPeriodId
         * @throws DataAccessException
         */
    private void delEvsEmp(String evEmpId, String evPeriodId, Connection con)
	    throws DataAccessException {
	PreparedStatement pstmt = null;
	try {
	    pstmt = con.prepareStatement(DEL_EVSEMP);
	    pstmt.setString(1, evPeriodId);
	    pstmt.setString(2, evEmpId);
	    pstmt.executeUpdate();

	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for delEvsEmp statistics", sqle);
	} finally {
	    if (pstmt != null) {
		try {
		    pstmt.close();
		    pstmt = null;
		} catch (SQLException se) {
		}
	    }
	}
    }

    /**
         * 删除被评价者
         * 
         * @param evEmpId
         * @param evPeriodId
         * @throws DataAccessException
         * @throws ServiceLocatorException
         */
    public void delEvsEmp(String evEmpId, String evPeriodId)
	    throws DataAccessException {
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    pstmt = con.prepareStatement(DEL_EVSEMP);
	    pstmt.setString(1, evPeriodId);
	    pstmt.setString(2, evEmpId);
	    pstmt.executeUpdate();
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for delEvsEmp statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    throw new DataAccessException(
		    "cant get connection for delEvsEmp statistics", sle);
	} finally {
	    SqlUtil.close(pstmt, con);

	}

    }

    // 更新员工等级
    private final static String UPDATE_EV_EMP = " UPDATE evs_emp SET ev_grade_id=? "
	    + " WHERE ev_emp_id=? AND ev_period_id=?  ";

    public void updateEvsEmpGrade(List lEvEmp, String evMasterId)
	    throws DataAccessException {
	if (lEvEmp == null) {
	    return;
	}

	int lEvEmpSize = lEvEmp.size();
	for (int i = 0; i < lEvEmpSize; i++) {
	    EvsEmp emp = (EvsEmp) lEvEmp.get(i);

	    Connection con = null;
	    try {
		con = services.getConnection();
		con.setAutoCommit(false);
		this.updateEvsEmpGrade(con, emp, evMasterId);
		con.commit();

	    } catch (SQLException sqle) {
		try {
		    con.rollback();
		} catch (SQLException e) {
		}
		sqle.printStackTrace();
		throw new DataAccessException(
			"cant execute query for updateEvsEmpGrade statistics",
			sqle);
	    } catch (ServiceLocatorException sle) {
		try {
		    con.rollback();
		} catch (SQLException e) {
		}
		throw new DataAccessException(
			"cant get connection for updateEvsEmpGrade statistics",
			sle);
	    } finally {
		SqlUtil.close(con);
	    }
	}

    }

    private void updateEvsEmpGrade(Connection con, EvsEmp evEmp,
	    String evMasterId) throws DataAccessException {
	if (evEmp == null) {
	    return;
	}
	PreparedStatement pstmt = null;
	try {
	    pstmt = con.prepareStatement(UPDATE_EV_EMP);

	    pstmt.setString(1, evEmp.getEvGradeID());
	    pstmt.setString(2, evEmp.getEvEmpID());
	    pstmt.setString(3, evEmp.getEvPeriodID());
	    pstmt.executeUpdate();

	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvsEmpGrade statistics", sqle);
	} finally {
	    SqlUtil.close(pstmt, null);
	}
    }

    public void updateEvsEmpGrade(EvsEmp evEmp, String evMasterId)
	    throws DataAccessException {
	if (evEmp == null) {
	    return;
	}
	Connection con = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    this.updateEvsEmpGrade(con, evEmp, evMasterId);
	    con.commit();

	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvsEmpGrade statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
	    }
	    throw new DataAccessException(
		    "cant get connection for updateEvsEmpGrade statistics", sle);
	} finally {
	    SqlUtil.close(con);
	}
    }

    // //取得员工的可操作状态
    // private final static String GET_OPERATABLE = "";
    //
    // public boolean getOperatable() {
    // return false;
    // }

    /**
         * 更新被评价者流程
         * 
         * @param evProcessId
         * @throws DataAccessException
         */
    public int updateEvsEmpProcess(String evProcessId)
	    throws DataAccessException {
	CallableStatement cstmt = null;
	Connection con = null;
	int i = 0;
	try {
	    con = services.getConnection();
	    cstmt = con.prepareCall("{call EVS_STATUS_P(?,?,?,?)}");
	    Logger.getLogger(this.getClass()).debug("{call EVS_STATUS_P(?,?,?,?)}");
	    
	    cstmt.setString(1, this.evPeriodID);
	    cstmt.setString(2, this.evEmpID);
	    cstmt.setString(3, evProcessId);
	    cstmt.registerOutParameter(4, Types.INTEGER);
	    cstmt.execute();
	    i = cstmt.getInt(4);

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvsEmpProcess statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for updateEvsEmpProcess statistics",
		    sle);
	} finally {
	    if (cstmt != null) {
		try {
		    cstmt.close();
		    cstmt = null;
		} catch (SQLException se) {
		}
	    }
	    SqlUtil.close(con);

	}
	return i;

    }
    
    public int deltetEvsEmpInfo()
	    throws DataAccessException {
	CallableStatement cstmt = null;
	Connection con = null;
	int i = 0;
	try {
	    con = services.getConnection();
	    cstmt = con.prepareCall("{call EVS_SETUPCODE_DEL(?,?,?)}");
	    Logger.getLogger(this.getClass()).debug("{call EVS_SETUPCODE_DEL(?,?,?)}");
	    
	    cstmt.setString(1, this.evPeriodID);
	    cstmt.setString(2, this.evEmpID);
	    cstmt.registerOutParameter(3, Types.INTEGER);
	    cstmt.execute();
	    i = cstmt.getInt(3);
	
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvsEmpProcess statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for updateEvsEmpProcess statistics",
		    sle);
	} finally {
	    if (cstmt != null) {
		try {
		    cstmt.close();
		    cstmt = null;
		} catch (SQLException se) {
		}
	    }
	    SqlUtil.close(con);
	
	}
	return i;
	
	}

    /**
         * 取得相应评价季度相应流程相应评价类型的相应评价部门的员工总数
         * 
         * 
         * @throws DataAccessException
         */
    public int getEvEmpCountByEmpIdPeriod(String evPeriodId, String evDeptId,
	    String evProcessId, String evTypeId) throws DataAccessException {

	int empCount = 0;
	String DEPT_SQL = "";
	String PERIOD_SQL = "";
	// String TYPE_SQL = "";
	String PROCESS_SQL = "";

	if (!evDeptId.equals("")) {
	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}
	if (!evPeriodId.equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + evPeriodId + "' ";
	}
	// if (!evTypeId.equals("")) {
	// TYPE_SQL = " AND ev_type_id='" + evTypeId + "' ";
	// }
	if (!evProcessId.equals("")) {
	    PROCESS_SQL = " AND ev_process_id='" + evProcessId + "' ";
	}

	String select_sql = "SELECT COUNT(*) as emp_count FROM evs_emp WHERE ev_emp_id is not null "
		+ PERIOD_SQL + DEPT_SQL + evTypeId + PROCESS_SQL;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(select_sql);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		empCount = rs.getInt("emp_count");
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpCountByEmpIdPeriod statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpCountByEmpIdPeriod statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return empCount;
    }

    /**
         * 取得被评价者
         * 
         * @throws DataAccessException
         */
    private void getEvEmpByEmpIdPeriod(Connection con)
	    throws DataAccessException {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
	    pstmt = con.prepareStatement(GET_EVSMASTER_BY_EMPIDPERIOD);

	    pstmt.setString(1, this.evEmpID);
	    pstmt.setString(2, this.evPeriodID);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {

		this.setEvCurrentProcessID(rs.getString("ev_process_id"));
		this.setEvCurrentProcessName(rs.getString("ev_process_name"));
		this.setEvDeptID(rs.getString("ev_dept_id"));
		this.setEvDeptName(rs.getString("ev_dept_name"));
		this.setEvEmpID(rs.getString("ev_emp_id"));
		this.setEvEmpName(rs.getString("ev_emp_name"));
		this.setEvGradeID(rs.getString("ev_grade_id"));
		this.setEvMark(rs.getFloat("ev_mark"));
		this.setEvPeriodID(rs.getString("ev_period_id"));
		this.setEvTypeName(rs.getString("ev_type_name"));
		this.setEvTypeID(rs.getString("ev_type_id"));
		this.setActivity(rs.getInt("activity"));
		this.setEvGradeName(rs.getString("ev_grade_name"));
		this.setEvPostGroupID(rs.getString("ev_post_group_id"));
		this.setEvPostGroupName(rs.getString("ev_post_group_name"));
	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpByEmpIdPeriod statistics",
		    sqle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e2) {
		e2.printStackTrace();
	    }
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt);
	}
    }

    /**
         * 更新被评价者的部门及职务评价类型 如更新评价类型则必需更新相应不同流程的评价者
         * 
         * @param evsEmp
         * @throws DataAccessException
         */
    public void updateEvEmp(EvsEmp evEmpNew,String cpnyid) throws DataAccessException {
	Connection con = null;
	//System.out.println("ffffffffffffffffff" + cpnyid);
	if (evEmpNew == null) {
	    return;
	}
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);

	    this.getEvEmpByEmpIdPeriod(con);
	    // 评价类型不变那就更新被评价者基本信息及相应流程的评价
	    // 评价类型变化的话那就得重新设置其每个流程的评价者
	    if (evEmpNew.getEvTypeID().equals(this.getEvTypeID())) {
		this.updateEvEmpNoType(con, evEmpNew,cpnyid);
	    } else {
		this.updateEvEmpType(con, evEmpNew);
	    }

	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvEmp statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    throw new DataAccessException(
		    "cant get connection for updateEvEmp statistics", sle);
	} finally {
	    SqlUtil.close(con);

	}
    }

    // 更新被评价者不更新评价类型
    private final static String UPDATE_EV_EMP_NO_TYPE = " UPDATE evs_emp SET ev_dept_id=?,ev_dept_name=HR_GET_FULL_DEPTNAME(?) "
	    // + "
                // ev_post_id=hr_get_post_code(?),ev_post_name=get_code_name(hr_get_post_code(?))
                // "
	    + " WHERE ev_emp_id=? AND ev_period_id=? ";

    private void updateEvEmpNoType(Connection con, EvsEmp evEmpNew,String cpnyid)
	    throws DataAccessException {
	PreparedStatement pstmt = null;
	try {
	    pstmt = con.prepareStatement(UPDATE_EV_EMP_NO_TYPE);
	    pstmt.setString(1, evEmpNew.getEvDeptID());
	    pstmt.setString(2, evEmpNew.getEvDeptID());
	    // pstmt.setString(3, evEmpNew.getEvEmpID());
	    // pstmt.setString(4, evEmpNew.getEvEmpID());
	    pstmt.setString(3, evEmpNew.getEvEmpID());
	    pstmt.setString(4, evEmpNew.getEvPeriodID());
	    pstmt.executeUpdate();
	    List lEvMaster = evEmpNew.getEvsMaster();

	    if (lEvMaster != null) {

		for (int i = 0, j = lEvMaster.size(); i < j; i++) {
		    EvsMaster evMaster = (EvsMaster) lEvMaster.get(i);

		    evMaster.updateEvsMasterByEvsEmp1(evMaster.getEvMaster(),
			    evEmpNew.getEvEmpID(), evEmpNew.getEvPeriodID(),
			    evMaster.getEvProcessID(),cpnyid, con);

		}
	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvEmpNoType statistics", sqle);
	} finally {
	    SqlUtil.close(null, pstmt);
	}

    }

    // 更新被评价者更新评价类型
    private final static String UPDATE_EV_EMP_TYPE = " UPDATE evs_emp SET ev_dept_id=?,ev_dept_name=HR_GET_FULL_DEPTNAME(?),"
	    // + "
                // ev_post_id=hr_get_post_code(?),ev_post_name=get_code_name(hr_get_post_code(?)),
                // "
	    + " ev_type_id=? " + " WHERE ev_emp_id=? AND ev_period_id=? ";

    private void updateEvEmpType(Connection con, EvsEmp evEmpNew)
	    throws DataAccessException {
	PreparedStatement pstmt = null;
	try {
		System.out.println("sql:      " + UPDATE_EV_EMP_TYPE);
	    pstmt = con.prepareStatement(UPDATE_EV_EMP_TYPE);
	    pstmt.setString(1, evEmpNew.getEvDeptID());
	    pstmt.setString(2, evEmpNew.getEvDeptID());
	    // pstmt.setString(3, evEmpNew.getEvEmpID());
	    // pstmt.setString(4, evEmpNew.getEvEmpID());
	    pstmt.setString(3, evEmpNew.getEvTypeID());
	    pstmt.setString(4, evEmpNew.getEvEmpID());
	    pstmt.setString(5, evEmpNew.getEvPeriodID());
	    pstmt.executeUpdate();
	    List lEvMaster = evEmpNew.getEvsMaster();
	    EvsMaster evMaster = new EvsMaster();
	    evMaster.delEvEmpMaster(con, evEmpNew.getEvEmpID(), evEmpNew
		    .getEvPeriodID());

	    if (lEvMaster == null) {
		con.rollback();
	    } else {

		evMaster.addEvsMaster(lEvMaster, con);

	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for updateEvEmpType statistics", sqle);
	} finally {
	    SqlUtil.close(null, pstmt);
	}

    }

    /**
         * 取得相应评价季度相应流程相应评价类型的相应评价部门的员工总数包括自己
         * 
         * 
         * @throws DataAccessException
         */
    public int getEvEmpCountByEmpIdPeriod(String evPeriodId, String evDeptId,
	    String evProcessId, String evTypeId, String evMaster)
	    throws DataAccessException {

	int empCount = 0;
	String deptSql = "";
	String typeSql = "";
	String processSql = "";
	String periodSql = "";
	if (!evDeptId.trim().equals("")) {
	    deptSql = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}
	
	if (evPeriodId.trim().length() != 0)
	    periodSql = " and EV_PERIOD_ID  = '" + evPeriodId + "'";
	// if (evTypeId.trim().length() != 0)
	// typeSql = " and EV_TYPE_ID = '" + evTypeId + "'";
	if (!"".equals(evTypeId)) {
	    String temp[] = evTypeId.split(",");
	    typeSql = " AND (";
	    for (int i = 0; i < temp.length; i++) {
		typeSql += " EV_TYPE_ID='" + temp[i] + "'";
		if (i == temp.length - 1) {
		    typeSql += " ) ";
		} else {
		    typeSql += " OR ";
		}
	    }
	}

	if (evProcessId.trim().length() != 0)
	    processSql = " and current_process_id = '" + evProcessId + "' " +
	    		" and ev_master_process_id= '" + evProcessId + "' ";

	String sql = "select count(*) as emp_count from EVS_MASTER_EMP_V2 where "
		+ " operatable=1 AND  ev_master = ? "
		+ periodSql
		+ deptSql
		+ typeSql + processSql + "order by OPERATABLE desc";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, evMaster);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		empCount = rs.getInt("emp_count");
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpCountByEmpIdPeriod statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpCountByEmpIdPeriod statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return empCount;
    }
    /**
     * 取得相应评价季度相应流程相应评价类型的相应评价部门的员工总数包括自己
     * 
     * 
     * @throws DataAccessException
     */
public int getEvEmpCountByEmpIdPeriod(String evPeriodId, String evDeptId,
	    String evProcessId, String evTypeId, String evMaster,String positionCode)
	    throws DataAccessException {

	int empCount = 0;
	String deptSql = "";
	String positionSql = "";
	String typeSql = "";
	String processSql = "";
	String periodSql = "";
	if (!evDeptId.trim().equals("")) {
	    deptSql = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + evDeptId + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}
	if (positionCode != null && !positionCode.equals("")) {
	    positionSql = " AND position_code in (" + Constants.mPositionCondition.get(positionCode) + ")";
	}
	if (evPeriodId.trim().length() != 0)
	    periodSql = " and EV_PERIOD_ID  = '" + evPeriodId + "'";
	// if (evTypeId.trim().length() != 0)
	// typeSql = " and EV_TYPE_ID = '" + evTypeId + "'";
	if (!"".equals(evTypeId)) {
	    String temp[] = evTypeId.split(",");
	    typeSql = " AND (";
	    for (int i = 0; i < temp.length; i++) {
		typeSql += " EV_TYPE_ID='" + temp[i] + "'";
		if (i == temp.length - 1) {
		    typeSql += " ) ";
		} else {
		    typeSql += " OR ";
		}
	    }
	}

	if (evProcessId.trim().length() != 0)
	    processSql = " and current_process_id = '" + evProcessId + "' ";

	String sql = "select count(*) as emp_count from EVS_MASTER_EMP_V2 where "
		+ " operatable=1 AND  ev_master = ? "
		+ periodSql
		+ deptSql
		+ positionSql
		+ typeSql + processSql + "order by OPERATABLE desc";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, evMaster);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		empCount = rs.getInt("emp_count");
	    }
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    throw new DataAccessException(
		    "cant execute query for getEvEmpCountByEmpIdPeriod statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    throw new DataAccessException(
		    "cant get connection for getEvEmpCountByEmpIdPeriod statistics",
		    sle);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return empCount;
}

    public boolean equals(Object o) {
	if (o == null || !(o instanceof EvsEmp) || this.evEmpID == null
		|| this.evPeriodID == null) {
	    return false;
	}

	EvsEmp evsEmp = (EvsEmp) o;

	if (evEmpID.equals(evsEmp.evEmpID)
		&& evPeriodID.equals(evsEmp.evPeriodID)) {
	    return true;
	} else {
	    return false;
	}
    }

    // 取得部门的当前季度的评价等级条件为当前等陆的人是否有对这个部门的人有相对化的权限来判断些人是否有权限查看
    public String getDeptGradeId(String evPeriod, String evDeptId,
	    String evMasterId) throws DataAccessException {
	String grade = "";
	String sql = "SELECT evs_get_dept_grade_id (?, ?) AS evdeptgradeid,"
		+ "       get_code_name (evs_get_dept_grade_id (?, ?)"
		+ "                     ) AS evdeptgradename" + "  FROM DUAL ";
	// + " WHERE ? IN ("
	// + " SELECT DISTINCT deptid"
	// + " FROM hr_department"
	// + " START WITH deptid IN ("
	// + " SELECT DISTINCT evs_emp.ev_dept_id"
	// + " FROM evs_master INNER JOIN evs_emp ON evs_master.ev_emp_id ="
	// + " evs_emp.ev_emp_id"
	// + " AND evs_master.ev_period_id ="
	// + " evs_emp.ev_period_id"
	// + " WHERE evs_master.ev_process_id ="
	// + " 'EVPROCESS015'"
	// + " AND evs_master.ev_master = ?)"
	// + " CONNECT BY deptid = PRIOR parent_dept_id AND dept_level >= 1)";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
	    con = services.getConnection();
	    pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, evDeptId);
	    pstmt.setString(2, evPeriod);
	    pstmt.setString(3, evDeptId);
	    pstmt.setString(4, evPeriod);
	    // pstmt.setString(5, evDeptId);
	    // pstmt.setString(6, evMasterId);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		grade = rs.getString("evdeptgradename");
	    }
	} catch (ServiceLocatorException e) {
	    throw new DataAccessException("Unable to retrieveconnection;"
		    + e.getMessage(), e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DataAccessException("Unable to execute query;"
		    + e.getMessage(), e);
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return grade;
    }

    /**
         * 
         * @param map
         * @return
     * @throws DataAccessException 
         */
    public List<EvsEmp> getEmps(SimpleMap map) throws DataAccessException {
	
	List<EvsEmp> lEmps = new ArrayList<EvsEmp>();
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String DEPT_SQL = "";
	String POSITION_SQL="";
	String PERIOD_SQL = "";
	String TYPE_SQL = "";
	String MASTER_SQL = "";
	String PROCESS_SQL = "";
	String PROCESS_MASTER_SQL="";
	String ORDER_SQL = " ORDER BY  ev_order,ev_emp_id";

	if (!StringUtil.checkNull(map.getString("DEPTID")).equals("")) {

	    DEPT_SQL = " AND ev_dept_id  in  (SELECT DISTINCT  "
		    + " deptid  FROM  HR_DEPARTMENT " + " START WITH deptid ='"
		    + map.get("DEPTID")
		    + "' CONNECT  BY PRIOR deptid= parent_dept_id )";
	}	
	
	if (!StringUtil.checkNull(map.getString("POSITION")).equals("")) {
	    POSITION_SQL = " AND position_code in (" + Constants.mPositionCondition.get(map.get("POSITION")) + ")";
	}
	if (!StringUtil.checkNull(map.getString("PERIODID")).equals("")) {
	    PERIOD_SQL = " AND ev_period_id='" + map.get("PERIODID") + "' ";
	}
	
	if (!StringUtil.checkNull(map.getString("TYPEID")).equals("")) {
	    TYPE_SQL = " AND ev_type_id='" + map.get("TYPEID") + "' ";
	}
	
	if (!StringUtil.checkNull(map.getString("PROCESSID")).equals("")) {
	    PROCESS_SQL = " AND current_process_id='" + map.get("PROCESSID")+ "' " +
	    		  " AND ev_master_process_id='" + map.get("PROCESSID")+ "' ";
//	    PROCESS_SQL = 
//		  " AND ev_master_process_id='" + map.get("PROCESSID")+ "' ";
	    
	}
	
	if (!StringUtil.checkNull(map.getString("MASTERID")).equals("")) {
	    MASTER_SQL = " AND ev_master='" + map.get("MASTERID") + "' ";
	}
	
	if(!StringUtil.checkNull(map.getString("PROCESSMASTERID")).equals("")){
	    PROCESS_MASTER_SQL=" AND EXISTS ( SELECT * FROM EVS_MASTER " 
			  +" WHERE EVS_MASTER.EV_EMP_ID=EVS_MASTER_EMP_V2.EV_EMP_ID "
			  +" AND EVS_MASTER.EV_MASTER='"+map.getString("PROCESSMASTERID")+"'" 
	    		  +" AND EVS_MASTER.EV_PERIOD_ID=EVS_MASTER_EMP_V2.EV_PERIOD_ID ) ";		
	}
	
	StringBuffer sql=new StringBuffer();
	sql.append( " SELECT * FROM evs_master_emp_v2 WHERE 1=1  ");
        sql.append(DEPT_SQL);
        sql.append(POSITION_SQL);
        sql.append(PERIOD_SQL);
        sql.append(TYPE_SQL);
        sql.append(PROCESS_SQL);
        sql.append(MASTER_SQL);
        sql.append(PROCESS_MASTER_SQL);
        sql.append(ORDER_SQL);
	
	try{
        	con = services.getConnection();
        	pstmt = con.prepareStatement(sql.toString());
        	//Logger.getLogger(this.getClass()).debug(sql.toString());
        	rs = pstmt.executeQuery();
        	con.setAutoCommit(false);
        	while (rs.next()) {
                	EvsEmp evsEmp = new EvsEmp();
                	evsEmp.setEvCurrentProcessID(rs.getString("current_process_id"));
                	evsEmp.setEvCurrentProcessName(rs.getString("current_process_name"));
                	evsEmp.setEvDeptID(rs.getString("ev_dept_id"));
                	evsEmp.setEvDeptName(rs.getString("ev_dept_name"));
                	evsEmp.setEvEmpID(rs.getString("ev_emp_id"));
                	evsEmp.setEvEmpName(rs.getString("ev_emp_name"));
                	evsEmp.setEvGradeID(StringUtil.checkNull(rs.getString("ev_grade_id")));
                	evsEmp.setEvMark(NumberUtil.parseFloat(StringUtil.checkNull(rs.getString("ev_mark"))));
                	evsEmp.setEvDefaultGrade(StringUtil.checkNull(rs.getString("ev_default_grade_id")));
                	evsEmp.setEvDefautlGradeName(StringUtil.checkNull(rs.getString("ev_default_grade_name")));
                	evsEmp.setEvOrder(NumberUtil.parseInt(StringUtil.checkNull(rs.getString("ev_order")),(int)0));
                	evsEmp.setOperatable(StringUtil.checkNull(rs.getString("operatable")));
                	evsEmp.setEvPeriodID(StringUtil.checkNull(rs.getString("ev_period_id")));;
                	evsEmp.setEvTypeName(rs.getString("ev_type_name"));
                	evsEmp.setEvTypeID(rs.getString("ev_type_id"));
                	evsEmp.setEvGradeName(StringUtil.checkNull(rs.getString("ev_grade_name")));
                	evsEmp.setEvPostGroupID(StringUtil.checkNull(rs.getString("ev_post_group_id")));
                	evsEmp.setEvPostGroupName(StringUtil.checkNull(rs.getString("ev_post_group_name")));
                	//取得当前流程 前的评价者及分数
                	EvsMaster master=new EvsMaster();
                	String evEmpId=rs.getString("ev_emp_id");
                	String evPeriodId=rs.getString("ev_period_id");
                	String evProcessId=rs.getString("current_process_id");
                	evsEmp.setLPreEvsMaster(master.getPreMasterMark(evEmpId, evPeriodId, evProcessId));
                	evsEmp.setNextProcessMaster(master.getNextMasterMark(evEmpId, evPeriodId, evProcessId));
                	//当前流程各项目分数
                	EvsItemProcessMark evsItemProcessMark=new EvsItemProcessMark();
                	evsEmp.setLItemList(evsItemProcessMark.getEvEmpItemProcessMark(evEmpId, evPeriodId, evProcessId));
                	lEmps.add(evsEmp);
                	//Logger.getLogger(this.getClass()).debug(evEmpId);
        	}
        	con.commit();
        	
	} catch (ServiceLocatorException e) {
	    
	    Logger.getLogger(this.getClass()).error(e.getMessage());
	    throw new DataAccessException("Unable to getEmps "+ e.getMessage(), e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DataAccessException("Unable to execute query getEmps"
		    + e.getMessage(), e);
	}catch (Exception e) {
	    e.printStackTrace();
	    throw new DataAccessException("Unable to execute query getEmps"
		    + e.getMessage(), e);
	} finally {
	    SqlUtil.close(rs, pstmt, con);
	}
	return lEmps;
    }

    
    public List getEmployeeList(String deptID, String condition, String evPeriodId)
	throws DataAccessException {
		String statement = " where deptid IN (SELECT deptid "
				+ "                  FROM hr_department where (date_ended > sysdate or date_ended = '' or date_ended is null)"
				+ "                  START WITH deptid = '"
				+ deptID
				+ "'"
				+ "                  CONNECT BY PRIOR deptid = parent_dept_id) "
				;
		
		if (condition != null && condition.length() > 0){
			statement += " AND (EMPID LIKE '" + condition + "%' OR chinesename LIKE '" + condition + "%') " ;
		}
		
		String sql = "select * from hr_employee_t "
				+ statement
				+ " and (Status_code!='EmpStatus3' or Status_code is null) " 
				+ " AND NOT EXISTS (SELECT EV.EV_EMP_ID FROM EVS_EMP EV WHERE EV.EV_EMP_ID = hr_employee_t.EMPID "
				+ " AND EV.EV_PERIOD_ID = '" + evPeriodId + "') order by empid ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		logger.debug(sql) ;
		
		List list = new ArrayList();
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmployeeBean employee = new EmployeeBean();
				employee.setEmpID(rs.getString("EmpID"));
				employee.setPersonID(rs.getString("person_id"));
				employee.setDeptID(rs.getString("DeptID"));
				employee.setDepartment(rs.getString("DEPARTMENT"));
				employee.setChineseName(rs.getString("ChineseName"));
				employee
						.setChinesePinyin(rs.getString("Chinese_PinYin") != null ? rs
								.getString("Chinese_PinYin")
								: "");
				employee
						.setResignTypeCode(rs.getString("RESIGN_TYPE_CODE") != null ? rs
								.getString("RESIGN_TYPE_CODE")
								: "");
				employee
						.setResignReason(rs.getString("RESIGN_REASON") != null ? rs
								.getString("RESIGN_REASON")
								: "");
				employee
						.setResignDescription(rs.getString("RESIGN_DESC") != null ? rs
								.getString("RESIGN_DESC")
								: "");
				employee.setDateLeft(rs.getString("RESIGN_DATE") != null ? rs
						.getString("RESIGN_DATE").substring(0, 10) : "");
				employee.setResignFine(rs.getString("RESIGN_FINE") != null ? rs
						.getString("RESIGN_FINE") : "");
				 employee
						.setPostGroupName(rs.getString("POST_GRADE") != null ? rs
								.getString("POST_GRADE")
								: ""); 
				employee
				.setPostGroupId(rs.getString("POST_GRADE_CODE") != null ? rs
						.getString("POST_GRADE_CODE")
						: "");
				list.add(employee);
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("Unable to retrieveconnection;"
					+ e.getMessage(), e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Unable to execute query;"
					+ e.getMessage(), e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;
		}
    
    
    private String evPeriodID;

    private String evTypeID;

    private String evTypeName;

    private String evEmpID;
    
    private String evEmpID2;

    private String evEmpName;

    private String evDeptID;

    private String evDeptName;

    private String evPostGroupID;

    private String evPostGroupName;

    private String evCurrentProcessID;

    private String evCurrentProcessName;

    private float evMark;

    private String evGradeID;

    private String evGradeName;

    private int activity;

    private int evOrder;

    private String evDefaultGrade;

    private String evDefautlGradeName;

    private List<EvsItemProcessMark> lItemList;

    private List<EvsMaster> lPreEvsMaster;

    private List<EvsMaster> lNextEvsMaster;
    
    private EvsMaster nextProcessMaster;
    
    private List EvsMaster;

    private String operateId;

    private String operatable;
    
    private String SETUPCODENO;  //序列唯一

	private String CRAFT; //工种

	private String LINE;  //Line线

	private String AIRCRAFT;  //机种

	private String PROCESS; //工序
	
	private String JOBCONTENT; //作业内容

	private String SKILLTYPE; //技能类型
	
	private String TYPEOPERATION; //作业类型
	
	private String QUALIFICATION; //职业资格
	
	private String SKILLSCORE; //技能积分
	
	private String PURPOSE; //培训目标
	
	private String EVS_EMPID; //工号

	private String EV_PERIOD_ID; //评价区间ID

	private String EV_TYPE_ID; //被评价者类型

	private String PROFICIENCY; //熟练度分数
	
	private String SHEOPERCYQ; //设备保全

	private String OPERATIONCOM; //作业基准书遵守分数
	
	private String QUALITYOFWORK; //作业品质分数 	

	private String STANDARDACTION;//标准动作分数
	
	private String COMPOSITE; //综合得分
	
	private String SKILLLEVEL; //技能等级
	
	private String ZYZGDJLEVEL;
	
	private String SUMJCCOUNT;
	
	private String SUMSCORE;
	
	public String getSUMSCORE() {
		return SUMSCORE;
	}

	public void setSUMSCORE(String sUMSCORE) {
		SUMSCORE = sUMSCORE;
	}

	public String getSUMJCCOUNT() {
		return SUMJCCOUNT;
	}

	public void setSUMJCCOUNT(String sUMJCCOUNT) {
		SUMJCCOUNT = sUMJCCOUNT;
	}

	private String REMARK; //主要问题点
    
    private String deptke; //技能等级
	
	private String deptzhi;
	
	private String deptzu; //主要问题点

    public String getOperatable() {
	return operatable;
    }

    public void setOperatable(String operatable) {
	this.operatable = operatable;
    }

    public String getSHEOPERCYQ() {
		return SHEOPERCYQ;
	}

	public void setSHEOPERCYQ(String sHEOPERCYQ) {
		SHEOPERCYQ = sHEOPERCYQ;
	}

	public String getOperateId() {
	return operateId;
    }

    public void setOperateId(String operateId) {
	this.operateId = operateId;
    }

    public String getEvDefaultGrade() {
	return evDefaultGrade;
    }

    public void setEvDefaultGrade(String evDefaultGrade) {
	this.evDefaultGrade = evDefaultGrade;
    }

    public String getEvDefautlGradeName() {
	return evDefautlGradeName;
    }

    public void setEvDefautlGradeName(String evDefautlGradeName) {
	this.evDefautlGradeName = evDefautlGradeName;
    }

    public int getEvOrder() {
	return evOrder;
    }

    public void setEvOrder(int evOrder) {
	this.evOrder = evOrder;
    }

    public List<EvsItemProcessMark> getLItemList() {
        return lItemList;
    }

    public void setLItemList(List<EvsItemProcessMark> itemList) {
        lItemList = itemList;
    }

    public List<EvsMaster> getLNextEvsMaster() {
        return lNextEvsMaster;
    }

    public void setLNextEvsMaster(List<EvsMaster> nextEvsMaster) {
        lNextEvsMaster = nextEvsMaster;
    }

    public List<EvsMaster> getLPreEvsMaster() {
        return lPreEvsMaster;
    }

    public void setLPreEvsMaster(List<EvsMaster> preEvsMaster) {
        lPreEvsMaster = preEvsMaster;
    }

    public EvsMaster getNextProcessMaster() {
        return nextProcessMaster;
    }

    public void setNextProcessMaster(EvsMaster nextProcessMaster) {
        this.nextProcessMaster = nextProcessMaster;
    }

	public List<EvsItemProcessMark> getlItemList() {
		return lItemList;
	}

	public void setlItemList(List<EvsItemProcessMark> lItemList) {
		this.lItemList = lItemList;
	}

	public List<EvsMaster> getlPreEvsMaster() {
		return lPreEvsMaster;
	}

	public void setlPreEvsMaster(List<EvsMaster> lPreEvsMaster) {
		this.lPreEvsMaster = lPreEvsMaster;
	}

	public List<EvsMaster> getlNextEvsMaster() {
		return lNextEvsMaster;
	}

	public void setlNextEvsMaster(List<EvsMaster> lNextEvsMaster) {
		this.lNextEvsMaster = lNextEvsMaster;
	}

	public String getSETUPCODENO() {
		return SETUPCODENO;
	}

	public void setSETUPCODENO(String sETUPCODENO) {
		SETUPCODENO = sETUPCODENO;
	}

	public String getCRAFT() {
		return CRAFT;
	}

	public void setCRAFT(String cRAFT) {
		CRAFT = cRAFT;
	}

	public String getLINE() {
		return LINE;
	}

	public void setLINE(String lINE) {
		LINE = lINE;
	}

	public String getAIRCRAFT() {
		return AIRCRAFT;
	}

	public void setAIRCRAFT(String aIRCRAFT) {
		AIRCRAFT = aIRCRAFT;
	}

	public String getPROCESS() {
		return PROCESS;
	}

	public void setPROCESS(String pROCESS) {
		PROCESS = pROCESS;
	}

	public String getJOBCONTENT() {
		return JOBCONTENT;
	}

	public void setJOBCONTENT(String jOBCONTENT) {
		JOBCONTENT = jOBCONTENT;
	}

	public String getSKILLTYPE() {
		return SKILLTYPE;
	}

	public void setSKILLTYPE(String sKILLTYPE) {
		SKILLTYPE = sKILLTYPE;
	}

	public String getTYPEOPERATION() {
		return TYPEOPERATION;
	}

	public void setTYPEOPERATION(String tYPEOPERATION) {
		TYPEOPERATION = tYPEOPERATION;
	}

	public String getEVS_EMPID() {
		return EVS_EMPID;
	}

	public void setEVS_EMPID(String eVSEMPID) {
		EVS_EMPID = eVSEMPID;
	}

	public String getEV_PERIOD_ID() {
		return EV_PERIOD_ID;
	}

	public void setEV_PERIOD_ID(String eVPERIODID) {
		EV_PERIOD_ID = eVPERIODID;
	}

	public String getEV_TYPE_ID() {
		return EV_TYPE_ID;
	}

	public void setEV_TYPE_ID(String eVTYPEID) {
		EV_TYPE_ID = eVTYPEID;
	}

	public String getPROFICIENCY() {
		return PROFICIENCY;
	}

	public void setPROFICIENCY(String pROFICIENCY) {
		PROFICIENCY = pROFICIENCY;
	}

	public String getOPERATIONCOM() {
		return OPERATIONCOM;
	}

	public void setOPERATIONCOM(String oPERATIONCOM) {
		OPERATIONCOM = oPERATIONCOM;
	}

	public String getQUALITYOFWORK() {
		return QUALITYOFWORK;
	}

	public void setQUALITYOFWORK(String qUALITYOFWORK) {
		QUALITYOFWORK = qUALITYOFWORK;
	}

	public String getSTANDARDACTION() {
		return STANDARDACTION;
	}

	public void setSTANDARDACTION(String sTANDARDACTION) {
		STANDARDACTION = sTANDARDACTION;
	}

	public String getCOMPOSITE() {
		return COMPOSITE;
	}

	public void setCOMPOSITE(String cOMPOSITE) {
		COMPOSITE = cOMPOSITE;
	}

	public String getSKILLLEVEL() {
		return SKILLLEVEL;
	}

	public void setSKILLLEVEL(String sKILLLEVEL) {
		SKILLLEVEL = sKILLLEVEL;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getEvEmpID2() {
		return evEmpID2;
	}

	public void setEvEmpID2(String evEmpID2) {
		this.evEmpID2 = evEmpID2;
	}

	public String getQUALIFICATION() {
		return QUALIFICATION;
	}

	public void setQUALIFICATION(String qualification) {
		QUALIFICATION = qualification;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String purpose) {
		PURPOSE = purpose;
	}

	public String getSKILLSCORE() {
		return SKILLSCORE;
	}

	public void setSKILLSCORE(String skillscore) {
		SKILLSCORE = skillscore;
	}

	public String getZYZGDJLEVEL() {
		return ZYZGDJLEVEL;
	}

	public void setZYZGDJLEVEL(String zYZGDJLEVEL) {
		ZYZGDJLEVEL = zYZGDJLEVEL;
	}

	public String getDeptke() {
		return deptke;
	}

	public void setDeptke(String deptke) {
		this.deptke = deptke;
	}

	public String getDeptzhi() {
		return deptzhi;
	}

	public void setDeptzhi(String deptzhi) {
		this.deptzhi = deptzhi;
	}

	public String getDeptzu() {
		return deptzu;
	}

	public void setDeptzu(String deptzu) {
		this.deptzu = deptzu;
	}
    
   
    

}