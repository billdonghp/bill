/*
 * 创建日期 2005-7-14
 * 
 * Company: AIT
 * 
 * @author QING
 * @version 1.0
 */
package com.ait.evs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

/**
 * @author AIT Administrator
 */
public class EvsDeptDefaultGrade {

	private int empCount;

	private String evDeptId;
	
	private String evPositionId;

	private String evDeptGradeId;

	private String evPeriodId;

	private String evProcessId;

	private String evTypeId;

	private List evsEmpGrade_v;

	private List evsGradeRate_v;

	private List residual_v;

	private String evMaster;

	/**
	 * @return 返回 empCount。
	 */
	public int getEmpCount() {

		return empCount;
	}

	/**
	 * @param empCount
	 *            要设置的 empCount。
	 */
	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}

	/**
	 * @return 返回 evDeptGradeId。
	 */
	public String getEvDeptGradeId() {
		return evDeptGradeId;
	}

	/**
	 * @param evDeptGradeId
	 *            要设置的 evDeptGradeId。
	 */
	public void setEvDeptGradeId(String evDeptGradeId) {
		this.evDeptGradeId = evDeptGradeId;
	}

	/**
	 * @return 返回 evDeptId。
	 */
	public String getEvDeptId() {
		return evDeptId;
	}

	/**
	 * @param evDeptId
	 *            要设置的 evDeptId。
	 */
	public void setEvDeptId(String evDeptId) {
		this.evDeptId = evDeptId;
	}

	/**
	 * @return 返回 evMaster。
	 */
	public String getEvMaster() {
		return evMaster;
	}

	/**
	 * @param evMaster
	 *            要设置的 evMaster。
	 */
	public void setEvMaster(String evMaster) {
		this.evMaster = evMaster;
	}

	/**
	 * @return 返回 evPeriodId。
	 */
	public String getEvPeriodId() {
		return evPeriodId;
	}

	/**
	 * @param evPeriodId
	 *            要设置的 evPeriodId。
	 */
	public void setEvPeriodId(String evPeriodId) {
		this.evPeriodId = evPeriodId;
	}

	/**
	 * @return 返回 evProcessId。
	 */
	public String getEvProcessId() {
		return evProcessId;
	}

	/**
	 * @param evProcessId
	 *            要设置的 evProcessId。
	 */
	public void setEvProcessId(String evProcessId) {
		this.evProcessId = evProcessId;
	}

	/**
	 * @return 返回 evsEmpGrade_v。
	 */
	public List getEvsEmpGrade_v() {
		return evsEmpGrade_v;
	}

	/**
	 * @param evsEmpGrade_v
	 *            要设置的 evsEmpGrade_v。
	 */
	public void setEvsEmpGrade_v(List evsEmpGrade_v) {
		this.evsEmpGrade_v = evsEmpGrade_v;
	}

	/**
	 * @return 返回 evsGradeRate_v。
	 */
	public List getEvsGradeRate_v() {
		return evsGradeRate_v;
	}

	/**
	 * @param evsGradeRate_v
	 *            要设置的 evsGradeRate_v。
	 */
	public void setEvsGradeRate_v(List evsGradeRate_v) {
		this.evsGradeRate_v = evsGradeRate_v;
	}

	/**
	 * @return 返回 evTypeId。
	 */
	public String getEvTypeId() {
		return evTypeId;
	}

	/**
	 * @param evTypeId
	 *            要设置的 evTypeId。
	 */
	public void setEvTypeId(String evTypeId) {
		this.evTypeId = evTypeId;
	}

	/**
	 * @return 返回 residual_v。
	 */
	public List getResidual_v() {
		return residual_v;
	}

	/**
	 * @param residual_v
	 *            要设置的 residual_v。
	 */
	public void setResidual_v(List residual_v) {
		this.residual_v = residual_v;
	}

	public EvsDeptDefaultGrade(String evDeptId, String evPeriodId,
			String evProcessId, String evTypeId, String evMaster) {
		this.evDeptId = evDeptId;
		this.evPeriodId = evPeriodId;
		this.evProcessId = evProcessId;
		this.evTypeId = evTypeId;
		this.evMaster = evMaster;
		evsEmpGrade_v = new Vector();
		evsGradeRate_v = new Vector();
		residual_v = new Vector();
		this.setGradeEmpSum();

	}
	public EvsDeptDefaultGrade(SimpleMap map) {
	    
	    this.evDeptId=StringUtil.checkNull(map.getString("DEPTID"));
	    this.evPeriodId=StringUtil.checkNull(map.getString("PERIODID"));
	    this.evTypeId=StringUtil.checkNull(map.getString("TYPEID"));
	    this.evProcessId=StringUtil.checkNull(map.getString("PROCESSID"));
	    this.evMaster=StringUtil.checkNull(map.getString("MASTERID"));
	    this.evPositionId=StringUtil.checkNull(map.getString("POSITION"));
	    evsEmpGrade_v = new Vector();
	    evsGradeRate_v = new Vector();
	    residual_v = new Vector();
	    this.setGradeEmpSum();
	    this.evDeptGradeId="EVDEPTGRADE001";
	}
	
	

	// 取得相应部门相应评价季度的员工总数
	private void setEmpCount() throws DataAccessException {
		EvsEmp evsEmp = new EvsEmp();
		this.empCount = evsEmp.getEvEmpCountByEmpIdPeriod(this.evPeriodId,
				this.evDeptId, this.evProcessId, this.evTypeId, this.evMaster,this.evPositionId);

	}

	// 取得部门的等级
	private void setDeptGradeId() throws DataAccessException {
		EvsDept evsDept = new EvsDept();
		this.evDeptGradeId = evsDept.getDeptGradeIdByDeptPeriod(
				this.evPeriodId, this.evDeptId);

	}

	//
	private void setEvGradeList() throws DataAccessException {

		this.setDeptGradeId();
		EvsDeptRadio evsDeptRadio = new EvsDeptRadio();
		// team内一次相对化
		if ((Constants.EVPROCESS008).equals(this.evProcessId)) {
			evsEmpGrade_v = Constants.lEvsEmpGrade;
		} else {
			evsEmpGrade_v = evsDeptRadio.getEvsDeptRadioByDeptGrade(
					this.evDeptId, this.evPeriodId, this.evDeptGradeId);
		}
	}

	// 计算相应等级员工数
	private void setGradeEmpSum() {
		try {
			this.setEmpCount();
			this.setEvGradeList();
			if (evsEmpGrade_v != null) {
				int evsEmpGradeSize = evsEmpGrade_v.size();
				String evEmpGrade = "";
				int empCountFact = 0;// 等级分配后实际员工总数
				int empDif = 0; // 员工分配等级过后剩余员工人数
				for (int i = 0; i < evsEmpGradeSize; i++) {

					EvsDeptRadio evsGradeRadio = (EvsDeptRadio) evsEmpGrade_v
							.get(i);
					evEmpGrade = evsGradeRadio.getEvEmpGradeId();

					if (evEmpGrade != null && !evEmpGrade.equals("")) {
						float rate = 0;
						int empGradeCount = 0;
						float residual = 0;
						rate = evsGradeRadio.getEvGradeProp();
						empGradeCount = (int) Math.floor(this.empCount * rate);
						residual = (float) (empCount * rate - Math
								.floor(this.empCount * rate));
						EvsGradeRate evsGradeRate = new EvsGradeRate(
								evEmpGrade, empGradeCount, residual, rate);
						evsGradeRate_v.add(evsGradeRate);
						residual_v.add(new Float(residual));
						empCountFact = empCountFact
								+ (int) Math.floor(this.empCount * rate);
					}
				}
				empDif = this.empCount - empCountFact;
				// 如果员工总数与计算出来不同那应填补相应等级的员工数
				if (empDif != 0) {
					this.compareResidual(empDif);
				}
			}
		} catch (Exception e) {
			System.out.println("EvDeptDefaultGrade.setGradeEmpSum.error :"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	// 对余数进行排序并更新相应评价等级员工数
	private void compareResidual(int count) {
		// 排序
		Comparator cmp = Collections.reverseOrder();
		Collections.sort(residual_v, cmp);
		// 减去无用比率余数

		while (residual_v.size() > count) {
			residual_v.remove(residual_v.size() - 1);
		}

		for (int i = 0; i < evsGradeRate_v.size(); i++) {
			EvsGradeRate evR = new EvsGradeRate();
			evR = (EvsGradeRate) evsGradeRate_v.get(i);
			for (int j = 0; j < residual_v.size(); j++) {
				float f = 0;
				if (residual_v.get(j) != null) {
					f = ((Float) residual_v.get(j)).floatValue();
					if (f == evR.getResidual()) {
						evR.setEmpGradeCount(evR.getEmpGradeCount() + 1);
						residual_v.remove(j);
					}
				}
			}
		}
	}

	// 部门相对化
	public void deptDefaultGrade() throws DataAccessException {

		this.setGradeEmpSum();

		if (this.evProcessId.equals(Constants.EVPROCESS015)||this.evProcessId.equals(Constants.EVPROCESS008)||this.evProcessId.equals(Constants.EVPROCESS009)) {
			// 采用评价基准进行评价
			EvsNorm evNorm = new EvsNorm();
			EvsDeptGrade evd = evNorm.getEvDeptGradeById(this.evDeptGradeId,
					this.empCount);
			this.evsGradeRate_v = evd.getLEvsGradeRate();
		}
		String DEPT_SQL = "";
		String PERIOD_SQL = "";
		String TYPE_SQL = "";
		String PROCESS_SQL = "";

		String DEPT_SQL1 = "";
		String PERIOD_SQL1 = "";
		String TYPE_SQL1 = "";
		String PROCESS_SQL1 = "";

		String MASTER_SQL1 = "";
		if (!this.evDeptId.equals("")) {
			DEPT_SQL = " AND EXISTS  (SELECT DISTINCT  "
					+ " HR_DEPARTMENT.deptid  FROM  HR_DEPARTMENT "
					+ " WHERE  emp.ev_dept_id=HR_DEPARTMENT.deptid"
					+ " START WITH HR_DEPARTMENT.deptid ='"
					+ evDeptId
					+ "' "
					+ " CONNECT  BY PRIOR HR_DEPARTMENT.deptid= HR_DEPARTMENT.parent_dept_id )";

			DEPT_SQL1 = " AND EXISTS  (SELECT DISTINCT  "
					+ " HR_DEPARTMENT.deptid  FROM  HR_DEPARTMENT "
					+ " WHERE  master.ev_dept_id=HR_DEPARTMENT.deptid "
					+ " START WITH HR_DEPARTMENT.deptid ='"
					+ evDeptId
					+ "' "
					+ " CONNECT  BY PRIOR HR_DEPARTMENT.deptid= HR_DEPARTMENT.parent_dept_id )";

		}
		// if (!this.evPeriodId.equals("")) {
		PERIOD_SQL = " AND emp.ev_period_id='" + evPeriodId + "' ";
		PERIOD_SQL1 = " AND master.ev_period_id='" + evPeriodId + "' ";
		// }
//		if (!this.evTypeId.equals("")) {
//			TYPE_SQL = " AND emp.ev_type_id='" + evTypeId + "' ";
//			TYPE_SQL1 = " AND master.ev_type_id='" + evTypeId + "' ";
//		}
		if (!"".equals(evTypeId)) {
			String temp[]= evTypeId.split(",");
			TYPE_SQL = " AND (";
			for(int i=0;i<temp.length;i++){
				TYPE_SQL += " emp.ev_type_id='" + temp[i] + "'";
				if(i == temp.length -1){
					TYPE_SQL += " ) ";
				} else {
					TYPE_SQL += " OR ";
				}
			}
		
			String temp1[]= evTypeId.split(",");
			TYPE_SQL1 = " AND (";
			for(int i=0;i<temp1.length;i++){
				TYPE_SQL1 += " master.ev_type_id='" + temp1[i] + "'";
				if(i == temp1.length -1){
					TYPE_SQL1 += " ) ";
				} else {
					TYPE_SQL1 += " OR ";
				}
			}
		}
		if (!this.evProcessId.equals("")) {
			PROCESS_SQL = " AND emp.ev_process_id='" + evProcessId + "' ";
			PROCESS_SQL1 = " AND master.current_process_id='" + evProcessId
					+ "' ";
		}
		// if (!this.evMaster.equals("")) {
		MASTER_SQL1 = " AND master.ev_master='" + evMaster + "' ";
		// }
		Connection con = null;
		PreparedStatement pstmt = null;

		String setFinalIsNull = " UPDATE evs_emp emp set "
				+ " emp.ev_grade_id=NULL  WHERE emp.ev_emp_id is not null "
				+ "  AND   EXISTS            				"
				+ "    (                              			"
				+ "       SELECT  *  FROM EVS_MASTER_EMP_V2 master 	"
				+ "			WHERE master.ev_emp_id IS NOT NULL  "
				+ "			AND master.ev_emp_id=emp.ev_emp_id  "
				+ "			AND master.operatable=1 " 
				+ MASTER_SQL1 + DEPT_SQL1
				+ PERIOD_SQL1 + TYPE_SQL1 + PROCESS_SQL1
				+ "     )     " 
				+ DEPT_SQL 
				+ PERIOD_SQL
				+ TYPE_SQL 
				+ PROCESS_SQL;

		String defaultEmpGrade = "  UPDATE EVS_EMP emp SET emp.ev_grade_id=?    "
				+ "  WHERE  emp.ev_emp_id is not null 	"
				+ " AND  EXISTS     			"
				+ "    (				"
				+ "    	SELECT  EV_EMP_ID		"
				+ " 		FROM (			"
				+ "    			SELECT EV_EMP_ID	"
				+ "   				FROM (		"
				+ "    				SELECT master.ev_emp_id, master.ev_mark, HR_EMPLOYEE.DATE_STARTED "
				+ "    					FROM evs_master_emp_v2 master			"
				+ "    					INNER JOIN HR_EMPLOYEE				"
				+ "    					ON master.EV_EMP_ID = HR_EMPLOYEE.EMPID		"
				+ "    					WHERE master.EV_EMP_ID IS NOT NULL		"
				+ "    					AND master.OPERATABLE = 1		"
				+ "    					AND master.EV_GRADE_ID IS NULL		"
				+ MASTER_SQL1
				+ DEPT_SQL1
				+ PERIOD_SQL1
				+ TYPE_SQL1
				+ PROCESS_SQL1
				+ "    					ORDER BY NVL(master.EV_MARK,0) DESC, HR_EMPLOYEE.DATE_STARTED	"
				+ "   							) 	"
				+ "     				WHERE ROWNUM<= ?	"
				+ "    				) M				"
				+ "   	WHERE emp.EV_EMP_ID=M.EV_EMP_ID				"
				+ "   )								"
				+ "AND emp.ev_grade_id IS NULL 					"
				+ DEPT_SQL
				+ PERIOD_SQL + TYPE_SQL + PROCESS_SQL;

		try {
			con = ServiceLocator.getInstance().getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(setFinalIsNull);
			pstmt.execute();

			// 对相应部门进行等级默认
			for (int i = 0; i < evsGradeRate_v.size(); i++) {
				try {
					pstmt = null;
					EvsGradeRate evsGradeRate = (EvsGradeRate) evsGradeRate_v
							.get(i);
					int empCount = 0;
					String evGrade = "";
					empCount = evsGradeRate.getEmpGradeCount();
					evGrade = evsGradeRate.getEvGrade();

					pstmt = con.prepareStatement(defaultEmpGrade);

					pstmt.setString(1, evGrade);
					pstmt.setInt(2, empCount);
					pstmt.execute();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			con.commit();
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException s) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for deptDefaultGrade statistics", sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException s) {
			}

			throw new DataAccessException(
					"cant get connection for deptDefaultGrade statistics", sle);
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException s) {
			}
		} finally {
			SqlUtil.close(pstmt, con);

		}
	}
	
//	 部门相对化
	public void deptDefaultGrade(SimpleMap map) throws DataAccessException {
	    
//	    this.evDeptGradeId="EVDEPTGRADE001";
//	    this.evDeptId=StringUtil.checkNull(map.getString("DEPTID"));
//	    this.evPeriodId=StringUtil.checkNull(map.getString("PERIODID"));
//	    this.evTypeId=StringUtil.checkNull(map.getString("TYPEID"));
//	    this.evProcessId=StringUtil.checkNull(map.getString("PROCESSID"));
//	    this.evMaster=StringUtil.checkNull(map.getString("MASTERID"));
//	    this.setEmpCount();
	    
        	// 采用评价基准进行评价
        	EvsNorm evNorm = new EvsNorm();
        	EvsDeptGrade evd = evNorm.getEvDeptGradeById(this.evDeptGradeId,this.empCount);
        	Logger.getLogger(this.getClass()).debug(this.evDeptGradeId+"	"+this.empCount);
        	
        	this.evsGradeRate_v = evd.getLEvsGradeRate();

		String DEPT_SQL = "";
		String PERIOD_SQL = "";
		String TYPE_SQL = "";
		String PROCESS_SQL = "";
		String POSITION_SQL="";
		
		String DEPT_SQL1 = "";
		String PERIOD_SQL1 = "";
		String TYPE_SQL1 = "";
		String PROCESS_SQL1 = "";
		String POSITION_SQL1="";

		String MASTER_SQL1 = "";
		if (!this.evDeptId.equals("")) {
			DEPT_SQL = " AND EXISTS  (SELECT DISTINCT  "
					+ " HR_DEPARTMENT.deptid  FROM  HR_DEPARTMENT "
					+ " WHERE  emp.ev_dept_id=HR_DEPARTMENT.deptid"
					+ " START WITH HR_DEPARTMENT.deptid ='"
					+ evDeptId
					+ "' "
					+ " CONNECT  BY PRIOR HR_DEPARTMENT.deptid= HR_DEPARTMENT.parent_dept_id )";

			DEPT_SQL1 = " AND EXISTS  (SELECT DISTINCT  "
					+ " HR_DEPARTMENT.deptid  FROM  HR_DEPARTMENT "
					+ " WHERE  master.ev_dept_id=HR_DEPARTMENT.deptid "
					+ " START WITH HR_DEPARTMENT.deptid ='"
					+ evDeptId
					+ "' "
					+ " CONNECT  BY PRIOR HR_DEPARTMENT.deptid= HR_DEPARTMENT.parent_dept_id )";

		}
		if (!this.evPositionId.equals("")) {
		    POSITION_SQL = " AND emp.ev_emp_id in (" +
		    		"	select empid from hr_employee where " +
		    		"	position_code in (" + Constants.mPositionCondition.get(map.get("POSITION")) + "))";
		    POSITION_SQL1 = " AND master.position_code in (" + Constants.mPositionCondition.get(map.get("POSITION")) + ")";

		}
		// if (!this.evPeriodId.equals("")) {
		PERIOD_SQL = " AND emp.ev_period_id='" + evPeriodId + "' ";
		PERIOD_SQL1 = " AND master.ev_period_id='" + evPeriodId + "' ";
		// }
//		if (!this.evTypeId.equals("")) {
//			TYPE_SQL = " AND emp.ev_type_id='" + evTypeId + "' ";
//			TYPE_SQL1 = " AND master.ev_type_id='" + evTypeId + "' ";
//		}
		if (!"".equals(evTypeId)) {
			String temp[]= evTypeId.split(",");
			TYPE_SQL = " AND (";
			for(int i=0;i<temp.length;i++){
				TYPE_SQL += " emp.ev_type_id='" + temp[i] + "'";
				if(i == temp.length -1){
					TYPE_SQL += " ) ";
				} else {
					TYPE_SQL += " OR ";
				}
			}
		
			String temp1[]= evTypeId.split(",");
			TYPE_SQL1 = " AND (";
			for(int i=0;i<temp1.length;i++){
				TYPE_SQL1 += " master.ev_type_id='" + temp1[i] + "'";
				if(i == temp1.length -1){
					TYPE_SQL1 += " ) ";
				} else {
					TYPE_SQL1 += " OR ";
				}
			}
		}
		if (!this.evProcessId.equals("")) {
			PROCESS_SQL = " AND emp.ev_process_id='" + evProcessId + "' ";
			PROCESS_SQL1 = " AND master.current_process_id='" 
			    		+ evProcessId+ "' " 
			    		+ " AND master.ev_master_process_id='" 
			    		+ evProcessId+ "' " ;
		}
		// if (!this.evMaster.equals("")) {
		MASTER_SQL1 = " AND master.ev_master='" + evMaster + "' ";
		// }
		Connection con = null;
		PreparedStatement pstmt = null;

		String setFinalIsNull = " UPDATE evs_emp emp set 	"
				+ " emp.ev_grade_id=NULL  ,		"
				+ " emp.ev_default_grade_id=NULL , 	" 
				+ " emp.ev_order=NULL  			" 
				+ " WHERE emp.ev_emp_id is not null 	"
				+ "  AND   EXISTS            		"
				+ "    (                              	"
				+ "       SELECT  *	 		"
				+ "		FROM EVS_MASTER_EMP_V2 master 		"
				+ "		WHERE master.ev_emp_id IS NOT NULL  	"
				+ "		AND master.ev_emp_id=emp.ev_emp_id  	"
				+ "		AND master.operatable=1 " 
				+ MASTER_SQL1 + DEPT_SQL1+POSITION_SQL1
				+ PERIOD_SQL1 + TYPE_SQL1 + PROCESS_SQL1
				+ "     )       " 
				+ DEPT_SQL + PERIOD_SQL
				+ TYPE_SQL + PROCESS_SQL+POSITION_SQL;
		
		String updateEmpOrder="  UPDATE EVS_EMP emp SET 		" 
	    		+ " emp.ev_order=(SELECT in_order		"
			+ "   			FROM (				"
			+ "    			SELECT master.ev_emp_id, master.ev_mark,"
			+ "			rank() over(partition by master.ev_master order by master.ev_mark desc) as in_order "
			+ "    				FROM evs_master_emp_v2 master				"
			+ "    				WHERE master.EV_EMP_ID IS NOT NULL			"
			+ "    				AND master.OPERATABLE = 1				"
			+ MASTER_SQL1
			+ DEPT_SQL1
			+ POSITION_SQL1
			+ PERIOD_SQL1
			+ TYPE_SQL1
			+ PROCESS_SQL1
			+ "    				) M	"
			+ "   	WHERE emp.EV_EMP_ID=M.EV_EMP_ID							"
			+ "   )	"
			+ " WHERE  emp.ev_emp_id is not null 	"
			+ DEPT_SQL
			+ PERIOD_SQL + TYPE_SQL + PROCESS_SQL+POSITION_SQL;

//		String defaultEmpGrade = "  UPDATE EVS_EMP emp SET 	" 
//		    		+ " emp.ev_grade_id=?,    		" 
//		    		+ " emp.ev_default_grade_id=? 		" 
//				+ "  WHERE  emp.ev_emp_id is not null 	"
//				+ " AND  EXISTS           	 	"
//				+ "    (				"
//				+ "    	SELECT  EV_EMP_ID,ROWNUM	"
//				+ " 		FROM (	"
//				+ "    		SELECT EV_EMP_ID,ROWNUM	"
//				+ "   			FROM (		"
//				+ "    			SELECT master.ev_emp_id, master.ev_mark "
//				+ "    				FROM evs_master_emp_v2 master		"
//				+ "    				LEFT JOIN evs_master_mark_v master2	"
//				+ "    				ON master2.ev_emp_id =MASTER.ev_emp_id	"
//				+ "    				AND master2.ev_period_id =MASTER.ev_period_id	"
//				+ "    				WHERE master.EV_EMP_ID IS NOT NULL	"
//				+ "    				AND master.OPERATABLE = 1		"
//				+ "    				AND master2.ev_process_id = 'EVPROCESS007'	"
//				+ "    				AND master.EV_GRADE_ID IS NULL		"
//				+ MASTER_SQL1
//				+ DEPT_SQL1
//				+ POSITION_SQL1
//				+ PERIOD_SQL1
//				+ TYPE_SQL1
//				+ PROCESS_SQL1
//				+ "    			ORDER BY NVL(master.EV_MARK,0) DESC ," 
//				+ "			master2.ev_marks DESC," 
//				+ "			master.ev_emp_id "
//				+ "   				) 		 "
//				+ "     			WHERE ROWNUM<= ? "
//				+ "    		) M				 "
//				+ "   	WHERE emp.EV_EMP_ID=M.EV_EMP_ID	"
//				+ "   )					"
//				+ "AND emp.ev_grade_id IS NULL 		"
//				+ DEPT_SQL
//				+ PERIOD_SQL + TYPE_SQL + PROCESS_SQL+POSITION_SQL;
		String getEmpList=
		    "SELECT EV_EMP_ID,ROWNUM	"
		+ "   			FROM (		"
		+ "    			SELECT master.ev_emp_id, master.ev_mark "
		+ "    				FROM evs_master_emp_v2 master		"
		+ "    				LEFT JOIN evs_master_mark_v master2	"
		+ "    				ON master2.ev_emp_id =MASTER.ev_emp_id	"
		+ "    				AND master2.ev_period_id =MASTER.ev_period_id	"
		+ "    				WHERE master.EV_EMP_ID IS NOT NULL	"
		+ "    				AND master.OPERATABLE = 1		"
		+ "    				AND master2.ev_process_id = 'EVPROCESS007'	"
		+ "    				AND master.EV_GRADE_ID IS NULL		"
		+ MASTER_SQL1
		+ DEPT_SQL1
		+ POSITION_SQL1
		+ PERIOD_SQL1
		+ TYPE_SQL1
		+ PROCESS_SQL1
		+ "    			ORDER BY NVL(master.EV_MARK,0) DESC ," 
		+ "			master2.ev_marks DESC," 
		+ "			master.ev_emp_id "
		+ "   				) 		 "
		+ "     			WHERE ROWNUM<= ? ";
		
		String updateEmpGrade="  UPDATE EVS_EMP emp SET 	" 
	    		+ " emp.ev_grade_id=?,    		" 
	    		+ " emp.ev_default_grade_id=? 		" 
			+ "  WHERE  emp.ev_emp_id=?		"
			+ "AND emp.ev_grade_id IS NULL 		"
			+ DEPT_SQL
			+ PERIOD_SQL + TYPE_SQL + PROCESS_SQL+POSITION_SQL;
		try {
			con = ServiceLocator.getInstance().getConnection();
			con.setAutoCommit(false);

			Logger.getLogger(this.getClass()).debug(setFinalIsNull);
			Logger.getLogger(this.getClass()).debug(updateEmpOrder);
//			Logger.getLogger(this.getClass()).debug(defaultEmpGrade);
			
			pstmt = con.prepareStatement(setFinalIsNull);
			pstmt.execute();
			pstmt = null;
			pstmt = con.prepareStatement(updateEmpOrder);
			
			pstmt.execute();
			// 对相应部门进行等级默认
			for (int i = 0; i < evsGradeRate_v.size(); i++) {
				try {
					pstmt = null;
					
					EvsGradeRate evsGradeRate = (EvsGradeRate) evsGradeRate_v.get(i);
					int empCount = 0;
					String evGrade = "";
					empCount = evsGradeRate.getEmpGradeCount();
					evGrade = evsGradeRate.getEvGrade();
					Logger.getLogger(this.getClass()).debug("empCountGrade:"+empCount+"-"+evGrade);
					
//					pstmt = con.prepareStatement(defaultEmpGrade);
//					pstmt.setString(1, evGrade);
//					pstmt.setString(2, evGrade);
//					pstmt.setInt(3, empCount);
//					pstmt.execute();
					ResultSet rs=null;
					pstmt = con.prepareStatement(getEmpList);
					pstmt.setInt(1, empCount);
					rs=pstmt.executeQuery();
					
					pstmt=null;
					while(rs.next()){
					    String empid=rs.getString(1);
					    pstmt=con.prepareStatement(updateEmpGrade);
					    int k=0;
					    pstmt.setString(++k, evGrade);
					    pstmt.setString(++k, evGrade);
					    pstmt.setString(++k, empid);
					    pstmt.executeUpdate();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			con.commit();
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException s) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for deptDefaultGrade statistics", sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException s) {
			}

			throw new DataAccessException(
					"cant get connection for deptDefaultGrade statistics", sle);
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException s) {
			}
		} finally {
			SqlUtil.close(pstmt, con);

		}
	}
	// 相对化提交
	public void defaultGradeSubmit() throws DataAccessException {
		try {
			List evEmp_List = new Vector();
			EvsMaster evsMaster = new EvsMaster();
			EvsScore evsScore = new EvsScore();
			evEmp_List = evsMaster.getEvEmpsByMasterPeriod(this.evPeriodId,
					this.evMaster, this.evDeptId, this.evTypeId,
					this.evProcessId, 1);

			for (int i = 0; i < evEmp_List.size(); i++) {
				EvsEmp evsEmp = (EvsEmp) evEmp_List.get(i);
				if (evsEmp != null) {
					evsScore.evsSubmit(evsEmp.getEvPeriodID(), evsEmp
							.getEvEmpID());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsDeptGradeState getDeptGradeState() {
		String DEPT_SQL = "";
		String PERIOD_SQL = "";
		String TYPE_SQL = "";
		String PROCESS_SQL = "";

		String MASTER_SQL1 = "";

		if (!this.evDeptId.equals("")) {
			DEPT_SQL = " AND EXISTS  (SELECT DISTINCT  "
					+ " HR_DEPARTMENT.deptid  FROM  HR_DEPARTMENT "
					+ " WHERE  master.ev_dept_id=HR_DEPARTMENT.deptid"
					+ " START WITH HR_DEPARTMENT.deptid ='"
					+ evDeptId
					+ "' "
					+ " CONNECT  BY PRIOR HR_DEPARTMENT.deptid= HR_DEPARTMENT.parent_dept_id )";

		}
		// if (!this.evPeriodId.equals("")) {
		PERIOD_SQL = " AND master.ev_period_id='" + evPeriodId + "' ";

		// }
//		if (!this.evTypeId.equals("")) {
//			TYPE_SQL = " AND master.ev_type_id='" + evTypeId + "' ";
//
//		}
		if (!"".equals(evTypeId)) {
			String temp[]= evTypeId.split(",");
			TYPE_SQL = " AND (";
			for(int i=0;i<temp.length;i++){
				TYPE_SQL += " master.ev_type_id='" + temp[i] + "'";
				if(i == temp.length -1){
					TYPE_SQL += " ) ";
				} else {
					TYPE_SQL += " OR ";
				}
			}
		}
		if (!this.evProcessId.equals("")) {
			PROCESS_SQL = " AND master.current_process_id='" + evProcessId
					+ "' ";

		}
		// if (!this.evMaster.equals("")) {
		MASTER_SQL1 = " AND master.ev_master='" + evMaster + "' ";
		// }
		String getEmpCount = " SELECT  count(master.ev_emp_id) as ev_emp_count	FROM EVS_MASTER_EMP_V2 master "
				+ " WHERE master.ev_emp_id IS NOT NULL  	"
				+ "	AND master.ev_grade_id=?  				"
				+ MASTER_SQL1
				+ DEPT_SQL
				+ PERIOD_SQL 
				+ TYPE_SQL 
				+ PROCESS_SQL;

		EvsDeptGradeState state = new EvsDeptGradeState();
		try {
			this.setEmpCount();
			this.setDeptGradeId();
			// 采用评价基准进行评价
			EvsNorm evNorm = new EvsNorm();
			evNorm.setVEvEmpGrade();
			state.setLEvEmpGrade(evNorm.getVEvEmpGrade());
			EvsDeptGrade evd = evNorm.getEvDeptGradeById(this.evDeptGradeId,
					this.empCount);
			state.setLEvNormGrade(evd.getLEvsGradeRate());
			List levEmpFaceGrade = new Vector();
			try {

				if (evNorm.vEvEmpGrade != null) {
					int vEvEmpGradeSize = evNorm.vEvEmpGrade.size();

					for (int i = 0; i < vEvEmpGradeSize; i++) {
						EvsGradeRate evGradeRate = new EvsGradeRate();
						HashMap h = (HashMap) evNorm.vEvEmpGrade.get(i);
						String evGrade = h.get("code").toString();
						String evGradeName = h.get("name").toString();
						int evEmpCount = -1;
						Connection con = null;
						con = ServiceLocator.getInstance().getConnection();
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						pstmt = con.prepareStatement(getEmpCount);
						pstmt.setString(1, h.get("code").toString());
						rs = pstmt.executeQuery();
						if (rs.next()) {
							evEmpCount = rs.getInt("ev_emp_count");
						}
						evGradeRate.setEmpGradeCount(evEmpCount);
						evGradeRate.setEvGradeName(evGradeName);
						evGradeRate.setEvGrade(evGrade);
						levEmpFaceGrade.add(evGradeRate);
						SqlUtil.close(rs, pstmt, con);
					}
				}
			} catch (SQLException sqle) {

				sqle.printStackTrace();
				throw new DataAccessException(
						"cant execute query for getEvsNorm statistics", sqle);
			}
			state.setLEvFactGrade(levEmpFaceGrade);
		} catch (Exception e) {
		}
		return state;
	}
}