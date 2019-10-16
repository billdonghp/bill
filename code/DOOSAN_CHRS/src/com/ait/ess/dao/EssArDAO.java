package com.ait.ess.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmParam;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.bean.EssArShift;
import com.ait.ess.bean.EssCardApplyBean;
import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssMatchBean;
import com.ait.ess.bean.EssOverTimeBean;
import com.ait.ess.bean.PageControl;
import com.ait.ga.bean.GaAffirmList;
import com.ait.hrm.dao.empinfo.AppendInfoDAO;
import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.ParameterType;
import com.ait.jdbc.core.SqlParameter;
import com.ait.mail.Mail;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.DataAccessException;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;
import com.crystaldecisions.Utilities.LengthLimitedDataInputStream;

public class EssArDAO {

	/**
	 * 所有与考勤相关的申请、决裁、确认、查看操作
	 * 
	 * @author Pennix
	 */

	private String adminId = ""; // 当前登录者ID

	private PageControl pageControl = new PageControl();

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	// private EssSysparam essSysparam = null;

	private final String essApplyStrategy = "/configuration/em2/EssApplyStrategy";

	private static ServiceLocator services;

	private static final Logger logger = Logger.getLogger(AppendInfoDAO.class);

	public EssArDAO() {
		// EssSysparam essSysparam = (EssSysparam)
		// SysparamUtils.getSysparam(EssSysparam.class,ApplicationContext.getTheadLocal().getCpnyId());

		commonSQLMapAdapter = new CommonSQLMapAdapter();
		try {
			services = ServiceLocator.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAdminId(String adminId) {
		Logger.getLogger(getClass()).debug("adminId Set to : " + adminId);
		this.adminId = adminId;
	}

	public PageControl getPageControl() {
		return this.pageControl;
	}

	public void setPageControl(PageControl pageControl) {
		this.pageControl = pageControl;
	}

	public ArrayList getEmpShift(String empId, String otDate) {
		AdminBean admin = ApplicationContext.getTheadLocal();
		ArrayList list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT HR_EMPLOYEE.EMPID,HR_EMPLOYEE.CHINESENAME, HR_EMPLOYEE.Chinese_Pinyin, AR_SHIFT010.SHIFT_NO, AR_SHIFT010.SHIFT_NAME,AR_SHIFT010.Shift_En_Name,AR_SHIFT010.SHIFT_KOR_NAME, "
				+ "TO_CHAR(TO_DATE(? || TO_CHAR(AR_SHIFT020.FROM_TIME, ' HH24:MI'), 'YYYY-MM-DD HH24:MI') + AR_SHIFT020.BEGIN_DAY_OFFSET, 'YYYY-MM-DD HH24:MI') FROM_TIME, "
				+ "TO_CHAR(TO_DATE(? || TO_CHAR(AR_SHIFT020.TO_TIME, ' HH24:MI'), 'YYYY-MM-DD HH24:MI') + AR_SHIFT020.END_DAY_OFFSET, 'YYYY-MM-DD HH24:MI') TO_TIME "
				+ "FROM AR_SHIFT020, AR_SHIFT010, HR_EMPLOYEE WHERE AR_SHIFT010.SHIFT_NO = AR_SHIFT020.SHIFT_NO AND AR_SHIFT010.SHIFT_NO = AR_GET_SHIFTNO(?, ?,?) "
				+ "AND HR_EMPLOYEE.PERSON_ID = ? AND HR_EMPLOYEE.CPNY_ID=?  ORDER BY TO_CHAR(TO_DATE('2008-03-10' || TO_CHAR(AR_SHIFT020.FROM_TIME, ' HH24:MI'),'YYYY-MM-DD HH24:MI') + AR_SHIFT020.BEGIN_DAY_OFFSET,'YYYY-MM-DD HH24:MI')";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, otDate);
			pstmt.setString(2, otDate);
			pstmt.setString(3, empId);
			pstmt.setString(4, otDate.replaceAll("-", "/"));
			pstmt.setString(5, admin.getCpnyId());
			pstmt.setString(6, empId);
			pstmt.setString(7, admin.getCpnyId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EssArShift essArShift = new EssArShift();
				essArShift.setEmpId(rs.getString("EMPID"));
				essArShift.setChineseName(rs.getString("CHINESENAME"));
				essArShift.setChineseNamePinyin(rs.getString("Chinese_Pinyin"));
				essArShift.setArDate(otDate);
				essArShift.setShiftNo(rs.getInt("SHIFT_NO"));
				essArShift.setShiftName(rs.getString("SHIFT_NAME"));
				essArShift.setShiftEnName(rs.getString("Shift_En_Name"));
				essArShift.setShiftKorName(rs.getString("SHIFT_KOR_NAME"));
				essArShift.setFromTime(rs.getString("FROM_TIME"));
				essArShift.setToTime(rs.getString("TO_TIME"));
				list.add(essArShift);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得员工班次错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 删除一条ess考勤申请记录
	 * 
	 * @param applyNo
	 *            考勤申请序号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"/"NoCardApply"
	 */
	public int delApply(int applyNo, String applyType) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "";
		if (applyType.equalsIgnoreCase("OtApply"))
			sql = "DELETE FROM ESS_APPLY_OT WHERE APPLY_OT_NO = ?";
		else if (applyType.equalsIgnoreCase("LeaveApply"))
			sql = "DELETE FROM ESS_LEAVE_APPLY_TB WHERE LEAVE_NO = ?";
		else if (applyType.equalsIgnoreCase("OtTopLimitApply"))
			sql = "DELETE FROM ESS_APPLY_OTTOPLIMIT WHERE APPLY_NO = ?";
		else
			return result;
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applyNo);
			result = pstmt.executeUpdate();
			if (result == 1) {
				sql = "DELETE FROM ESS_AFFIRM WHERE APPLY_NO = ? AND APPLY_TYPE = ?";
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, applyNo);
				pstmt.setString(2, applyType);
				pstmt.executeUpdate();
				conn.commit();
			} else
				conn.rollback();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				Logger.getLogger(getClass()).debug(e1.toString());
			}
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 删除一条ess年假申请其他信息
	 * 
	 * @param applyNo
	 *            考勤申请序号
	 */
	public int delApplyH9Info(int applyNo) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ESS_LEAVE_APPLY_H9_INO WHERE LEAVE_NO = ?";
		Logger.getLogger(getClass()).debug(sql + applyNo);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applyNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 取得该法人具体的ESS申请的配置
	 * 
	 * @return
	 */
	public EssStrategyIntf getEssStrategyIntf() {
		String essApplyStrategyClass = "";
		try {
			essApplyStrategyClass = Configuration.getInstance().getString(
					essApplyStrategy, "");
		} catch (Exception e) {
			throw new GlRuntimeException("获取ESS申请校验策略类错误", e);
		}
		if (!essApplyStrategyClass.equals("")) {
			try {
				return (EssStrategyIntf) Thread.currentThread()
						.getContextClassLoader().loadClass(
								essApplyStrategyClass).newInstance();
			} catch (Exception e) {
				throw new GlRuntimeException("注册的ESS申请校验策略类非法"
						+ essApplyStrategyClass, e);
			}
		} else
			return new EssArDAOHelper();
	}

	public int getCurrentMaxApplyNo() {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		String sql = " select  ess_apply_group_seq.nextval currentMaxApplySeq  from  dual";
		Logger.getLogger(getClass()).debug(sql);
		int num = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rst = pstmt.executeQuery();
			if (rst.next()) {
				num = rst.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rst.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return num;
	}

	/**
	 * 添加一条加班申请信息
	 * 
	 * @param essOverTimeBean
	 * @return 返回错误代码, 0为处理正常
	 */
	public HashMap doOtApply(EssOverTimeBean essOverTimeBean) {

		// 根据申请参数对加班申请进行验证
		HashMap result = getEssStrategyIntf().otApplyChecker(essOverTimeBean);
		if (((Integer) result.get("errcode")).intValue() != 0)
			return result;

		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ESS_APPLY_OT "
				+ "(APPLY_OT_NO, EMPID, APPLY_OT_TYPE_CODE, "
				+ "APPLY_OT_DATE, OT_FROM_TIME, OT_TO_TIME, "
				+ "APPLY_OT_REMARK, APPLY_OT_FLAG, CREATE_DATE, "
				+ "CREATED_BY, ACTIVITY, OT_DEDUCT_TIME, OT_TYPE_REMARK, "
				+ "APPLY_OT_AFFIRM,APPLY_GROUP_NO) " + "VALUES " + "(?, ?, ?, "
				+ "TO_DATE(?, 'YYYY-MM-DD'), ?, ?, " + "?, ?, SYSDATE, "
				+ "?, 0, ?, ?, " + "?,?)";
		Logger.getLogger(getClass()).debug(sql);
		int sequence = this.getSequence("ESS_OTAPPLY_SEQ");
		result.put("sequence", sequence);

		List affirmorList = new ArrayList();
		if (essOverTimeBean.getOtSort().equals("emergency"))
			affirmorList = (ArrayList) this.getAffirmorList(essOverTimeBean
					.getEmpId(), "TOTApply", 99, true);
		else if (essOverTimeBean.getOtSort().equals("overmax"))
			affirmorList = (ArrayList) this.getAffirmorList(essOverTimeBean
					.getEmpId(), "SOTApply", 99, true);
		else
			affirmorList = (ArrayList) this.getAffirmorList(essOverTimeBean
					.getEmpId(), "OtApply", 99, true);
		Logger.getLogger(getClass()).debug(
				"Affirmor Count : " + affirmorList.size());
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setInt(i++, sequence);
			pstmt.setString(i++, essOverTimeBean.getEmpId());
			pstmt.setString(i++, essOverTimeBean.getOtTypeCode());//
			pstmt.setString(i++, essOverTimeBean.getOtDate());
			pstmt.setString(i++, essOverTimeBean.getOtFromTime());
			pstmt.setString(i++, essOverTimeBean.getOtToTime());//
			pstmt.setString(i++, essOverTimeBean.getOtRemark());
			pstmt.setInt(i++, essOverTimeBean.getOtNextDays());//
			pstmt.setString(i++, adminId);
			pstmt.setDouble(i++, essOverTimeBean.getOtDeduct());
			pstmt.setString(i++, essOverTimeBean.getOtSortCode());
			if (essOverTimeBean.isForceType())
				pstmt.setInt(i++, 1);
			else
				pstmt.setInt(i++, 0);
			pstmt.setInt(i++, essOverTimeBean.getApplyGroupSeq());// lsfc要求批量申请,线下批量进行决裁,加如这列作为同期次申请的标示
			if (pstmt.executeUpdate() == 1) {
				Logger
						.getLogger(getClass())
						.debug(
								"Apply Inserted Successfully, processing Affirmor.......");
				if (this.addApplyAffirmor(sequence, "OtApply", affirmorList) == -1) {
					Logger.getLogger(getClass()).debug(
							"Affirmor process failed");
					conn.rollback();
					result.put("errcode", new Integer(10)); // 插入决裁者失败,返回错误代码2
					result.put("erremp", essOverTimeBean.getEmpId());
				} else
					conn.commit();
			} else {
				conn.rollback();
				result.put("errcode", new Integer(-1));
				; // 未知错误,返回
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("添加一条加班申请信息错误", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 批量进行加班申请
	 * 
	 * @param list
	 * @return HashMap
	 */
	public HashMap doOtApplyByBatch(List parameterObject)
			throws GlRuntimeException {

		HashMap result = new HashMap();
		result.put("errcode", new Integer(0));

		try {

			commonSQLMapAdapter.startTransaction();

			Iterator iterator = parameterObject.iterator();

			while (iterator.hasNext()) {

				EssOverTimeBean overTimeBean = (EssOverTimeBean) iterator
						.next();

				// 取出决裁者列表
				List affirmorList = overTimeBean.getAffirmorList();
				Iterator affirmorIter = affirmorList.iterator();
				int applyNo = this.getSequence("ESS_OTAPPLY_SEQ");
				overTimeBean.setOtNo(applyNo);

				// 插入一条加班申请
				commonSQLMapAdapter.insert("ess.apply.insertOvertimeApply",
						overTimeBean);

				// 取得插入的申请数据的申请号
				// String applyNo =
				// commonSQLMapAdapter.executeQuery("ess.apply.getOTApplyNo").toString();

				while (affirmorIter.hasNext()) {

					EssAffirmor affirmor = (EssAffirmor) affirmorIter.next();
					affirmor.setApplyNo(applyNo);
					affirmor.setCreatedBy(overTimeBean.getCreatedBy());
					affirmor.setApplyType("OtApply");
				}

				// 插入决裁者列表
				this.insertAffirmorInfo(affirmorList, false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			result.put("errcode", new Integer(-1));
			result.put("errmsg", e.toString());
			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
			// throw new GlRuntimeException("Apply overtime by batch Exception.
			// ",e);
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

	/**
	 * 批量进行加班上限申请
	 * 
	 * @param list
	 * @return HashMap
	 */
	public HashMap doOtTopLimitApplyByBatch(List parameterObject)
			throws GlRuntimeException {

		HashMap result = new HashMap();
		result.put("errcode", new Integer(0));

		try {

			commonSQLMapAdapter.startTransaction();

			Iterator iterator = parameterObject.iterator();

			while (iterator.hasNext()) {

				EssOverTimeBean overTimeBean = (EssOverTimeBean) iterator
						.next();

				// 取出决裁者列表
				List affirmorList = overTimeBean.getAffirmorList();
				Iterator affirmorIter = affirmorList.iterator();

				// 插入一条加班上限申请
				commonSQLMapAdapter.insert(
						"ess.apply.insertOvertimeTopLimitApply", overTimeBean);

				// 取得插入的申请数据的申请号
				String applyNo = commonSQLMapAdapter.executeQuery(
						"ess.apply.getOTTopLimitApplyNo").toString();

				while (affirmorIter.hasNext()) {
					EssAffirmor affirmor = (EssAffirmor) affirmorIter.next();
					affirmor.setApplyNo(Integer.parseInt(applyNo));
					affirmor.setCreatedBy(overTimeBean.getCreatedBy());
					affirmor.setApplyType("OtTopLimitApply");
				}

				// 插入决裁者列表
				this.insertAffirmorInfo(affirmorList, false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			result.put("errcode", new Integer(-1));
			result.put("errmsg", e.toString());
			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
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

	/**
	 * insert affirmor information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertAffirmorInfo(List parameterObject, boolean isAutoTrans)
			throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ess.apply.insertAffirmorInfo",
					parameterObject, isAutoTrans);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"insert affirmor information Exception. ", e);
		}
	}

	/**
	 * 添加一条休假申请信息
	 * 
	 * @param essLeaveBean
	 * @return 返回错误代码, 0为处理正常
	 */
	public HashMap doLeaveApply(EssLeaveBean essLeaveBean) {

		HashMap result = getEssStrategyIntf().leaveApplyChecker(essLeaveBean);
		if (((Integer) result.get("errcode")).intValue() != 0)
			return result;

		EssArDAOHelper daoHelp = new EssArDAOHelper();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ESS_LEAVE_APPLY_TB "
				+ "(LEAVE_NO, LEAVE_EMPID, LEAVE_TYPE, "
				+ "LEAVE_FROM_TIME, LEAVE_TO_TIME, LEAVE_REASON, "
				+ "CREATE_DATE, CREATED_BY, ACTIVITY,APPLY_GROUP_NO,ApplyLeaveType, FILE_UPLOAD_FLAG,CPNY)"
				+ "VALUES "
				+ "(?, ?, ?, "
				+ "TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ?, "
				+ "SYSDATE, ?, 0, ?, ? , ?,?)";

		Logger.getLogger(getClass()).debug(sql);
		// int sequence = this.getSequence("ESS_OTAPPLY_SEQ");
		// result.put("sequence", sequence);

		List affirmorList = essLeaveBean.getAffirmorList();
		Logger.getLogger(getClass()).debug(
				"Affirmor Count : " + affirmorList.size());
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setInt(i++, essLeaveBean.getLeaveNo());
			pstmt.setString(i++, essLeaveBean.getPerson_id());
			pstmt.setString(i++, essLeaveBean.getLeaveTypeCode());
			pstmt.setString(i++, essLeaveBean.getLeaveFromDate() + " "
					+ essLeaveBean.getLeaveFromTime());
			pstmt.setString(i++, essLeaveBean.getLeaveToDate() + " "
					+ essLeaveBean.getLeaveToTime());
			pstmt.setString(i++, essLeaveBean.getLeaveReason());
			pstmt.setString(i++, essLeaveBean.getCreatedBy());
			pstmt.setInt(i++, essLeaveBean.getApplyGroupSeq());
			pstmt.setString(i++, essLeaveBean.getApplyLeaveType());
			pstmt.setInt(i++, essLeaveBean.getFileUploadFlag());
			pstmt.setString(i++, essLeaveBean.getCpny());
			if (pstmt.executeUpdate() == 1) {
				Logger
						.getLogger(getClass())
						.debug(
								"Apply Inserted Successfully, processing Affirmor.......");
				if (this.addApplyAffirmor(essLeaveBean.getLeaveNo(),
						essLeaveBean.getApllyTypeName(), affirmorList) == -1) {
					Logger.getLogger(getClass()).debug(
							"Affirmor process failed");
					conn.rollback();
					result.put("errcode", new Integer(10)); // 插入决裁者失败,返回错误代码2
					result.put("erremp", essLeaveBean.getEmpId());
				} else {
					conn.commit();
				}

			} else {
				conn.rollback();
				result.put("errcode", new Integer(-1)); // 未知错误,返回
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("添加一条休假申请信息错误", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 添加申请信息编号
	 * 
	 * @param essLeaveBean
	 * @return 返回错误代码, 0为处理正常
	 */
	public void doLeaveApplyId(EssLeaveBean essLeaveBean) {

		HashMap result = getEssStrategyIntf().leaveApplyChecker(essLeaveBean);
		// if (((Integer) result.get("errcode")).intValue() != 0)
		// return result;

		EssArDAOHelper daoHelp = new EssArDAOHelper();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;

		String sql = "UPDATE ESS_LEAVE_APPLY_TB T SET T.DISD_DEPT_REPORT_ID ="
				+ "(SELECT TO_CHAR(SYSDATE, 'YYYY')||NVL((SELECT  MAX(NVL( SUBSTR(T1.DISD_DEPT_REPORT_ID,5,10),0)+1)  FROM ESS_LEAVE_APPLY_TB T1 "
				+ " WHERE SUBSTR(T1.DISD_DEPT_REPORT_ID,1,4) = TO_CHAR(SYSDATE,'YYYY')),1) FROM DUAL) WHERE T.LEAVE_NO = ? ";

		Logger.getLogger(getClass()).debug(sql);
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, essLeaveBean.getLeaveNo());

			if (pstmt.executeUpdate() == 1) {
				conn.commit();
			} else {
				conn.rollback();
				result.put("errcode", new Integer(-1)); // 未知错误,返回
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("添加申请编号信息错误", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		// return result;
	}

	/**
	 * 添加一条年休假申请的其他信息
	 * 
	 * @param essLeaveBean
	 */
	public void saveLeaveH9Info(EssLeaveBean essLeaveBean) {

		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ESS_LEAVE_APPLY_H9_INO "
				+ "(LEAVE_NO, BUSINESS, AGENT_EMPID, AGENT_NAME,AGENT_POSITION, AGENT_OFFICE_PHONE, "
				+ " AGENT_CELLPHONE, OTHERBUSINESS, CONTACT_MODE) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Logger.getLogger(getClass()).debug(sql);

		try {
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setInt(i++, essLeaveBean.getLeaveNo());
			pstmt.setString(i++, essLeaveBean.getBusiness());
			pstmt.setString(i++, essLeaveBean.getAgentEmpId());
			pstmt.setString(i++, essLeaveBean.getAgentName());
			pstmt.setString(i++, essLeaveBean.getAgentPosition());
			pstmt.setString(i++, essLeaveBean.getAgentOfficePhone());
			pstmt.setString(i++, essLeaveBean.getAgentCellphone());
			pstmt.setString(i++, essLeaveBean.getOtherBusiness());
			pstmt.setString(i++, essLeaveBean.getContactMode());
			pstmt.executeUpdate();
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("添加一条年休假申请的其他信息", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	/**
	 * 批量进行休假申请
	 * 
	 * @param list
	 * @return HashMap
	 */
	public HashMap doLeaveApplyByBatch(List parameterObject)
			throws GlRuntimeException {

		HashMap result = new HashMap();
		result.put("errcode", new Integer(0));

		try {
			// 取得插入的申请数据的申请号
			commonSQLMapAdapter.startTransaction();

			Iterator iterator = parameterObject.iterator();

			while (iterator.hasNext()) {
				EssLeaveBean leaveBean = (EssLeaveBean) iterator.next();
				// /int sequence = this.getSequence("ESS_OTAPPLY_SEQ");
				// /leaveBean.setLeaveNo(sequence);

				// 取出决裁者列表
				List affirmorList = leaveBean.getAffirmorList();
				Iterator affirmorIter = affirmorList.iterator();
				int applyNo = this.getSequence("ESS_OTAPPLY_SEQ");
				leaveBean.setLeaveNo(applyNo);

				// 插入一条休假申请
				commonSQLMapAdapter.insert("ess.apply.insertLeaveApply",
						leaveBean);
				List<EssAffirmor> list = new ArrayList<EssAffirmor>();
				// String applyNo =
				// commonSQLMapAdapter.executeQuery("ess.apply.getLeaveApplyNo").toString();
				while (affirmorIter.hasNext()) {

					EssAffirmor affirmor = (EssAffirmor) affirmorIter.next();
					affirmor.setApplyNo(applyNo);
					affirmor.setCreatedBy(leaveBean.getCreatedBy());
					affirmor.setApplyType("LeaveApply");
					list.add(affirmor);
				}

				// 插入决裁者列表
				this.insertAffirmorInfo(affirmorList, false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			result.put("errcode", new Integer(-1));
			result.put("errmsg", e.toString());
			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
			// throw new GlRuntimeException("Apply overtime by batch Exception.
			// ",e);
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

	/**
	 * 添加一条漏卡申请信息
	 * 
	 * @param essCardApplyBean
	 * @return 返回错误代码, 0为处理正常
	 */
	public HashMap doCardApply(EssCardApplyBean essCardApplyBean) {

		HashMap result = new HashMap();
		result.put("errcode", new Integer(0));

		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ESS_CARD_APPLY_TB "
				+ "(APPLY_ID, APPLY_EMP_ID, APPLY_REASON, "
				+ "CARD_NO, R_TIME, DOOR_TYPE, CREATED_BY,"
				+ "CREATE_DATE, UPDATED_BY, UPDATE_DATE,"
				+ " ACTIVITY, REMARK)" + "VALUES " + "(?, ?, ?, NVL(?,00000),"
				+ "TO_DATE(?, 'YYYY-MM-DD HH24:MI'), ?, ?, SYSDATE, ?, "
				+ "SYSDATE, 0, '')";
		Logger.getLogger(getClass()).debug(sql);
		int sequence = this.getSequence("ESS_CARDAPPLY_SEQ");
		List affirmorList = this.getAffirmorList(
				essCardApplyBean.getApplyEmp(), "NoCardApply", 99, true);
		Logger.getLogger(getClass()).debug(
				"Affirmor Count : " + affirmorList.size());
		try {

			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setInt(i++, sequence);
			pstmt.setString(i++, essCardApplyBean.getApplyEmp());
			pstmt.setString(i++, essCardApplyBean.getApplyReason());
			pstmt.setString(i++, essCardApplyBean.getCardNo());
			pstmt.setString(i++, essCardApplyBean.getCardTime());
			pstmt.setString(i++, essCardApplyBean.getDoorType());
			pstmt.setString(i++, essCardApplyBean.getCreateBy());
			pstmt.setString(i++, essCardApplyBean.getUpdateBy());

			if (pstmt.executeUpdate() == 1) {
				Logger
						.getLogger(getClass())
						.debug(
								"Apply Inserted Successfully, processing Affirmor.......");
				if (this
						.addApplyAffirmor(sequence, "NoCardApply", affirmorList) == -1) {
					Logger.getLogger(getClass()).debug(
							"Affirmor process failed");
					conn.rollback();
					result.put("errcode", new Integer(1)); // 插入决裁者失败,返回错误代码1
					result.put("erremp", essCardApplyBean.getApplyEmp());
				} else {
					conn.commit();
				}
			} else {
				conn.rollback();
				result.put("errcode", new Integer(-1)); // 未知错误,返回
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("添加一条漏卡申请信息错误", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 添加一条值班申请信息
	 * 
	 * @param essMatchBean
	 * @return 返回错误代码, 0为处理正常
	 */
	public HashMap doMatchApply(EssMatchBean essMatchBean) {
		HashMap result = getEssStrategyIntf().matchApplyChecker(essMatchBean);
		if (((Integer) result.get("errcode")).intValue() != 0)
			return result;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ESS_MATCH_APPLY_TB "
				+ "(MATCH_NO, MATCH_EMPID, MATCH_TYPE, "
				+ "MATCH_FROM_TIME, MATCH_TO_TIME, CREATE_DATE, "
				+ "CREATED_BY, ACTIVITY)"
				+ "VALUES "
				+ "(?, ?, ?, "
				+ "TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(?, 'YYYY-MM-DD HH24:MI'), SYSDATE, "
				+ "?, 0)";
		Logger.getLogger(getClass()).debug(sql);
		int sequence = this.getSequence("ESS_MATCHAPPLY_SEQ");
		List affirmorList = this.getAffirmorList(essMatchBean.getEmpID(),
				"MatchApply", 99, true);
		Logger.getLogger(getClass()).debug(
				"Affirmor Count : " + affirmorList.size());
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setInt(i++, sequence);
			pstmt.setString(i++, essMatchBean.getEmpID());
			pstmt.setString(i++, essMatchBean.getMatchTypeCode());
			pstmt.setString(i++, essMatchBean.getMatchFromTime());
			pstmt.setString(i++, essMatchBean.getMatchToTime());
			pstmt.setString(i++, adminId);
			if (pstmt.executeUpdate() == 1) {
				Logger
						.getLogger(getClass())
						.debug(
								"Apply Inserted Successfully, processing Affirmor.......");
				if (this.addApplyAffirmor(sequence, "MatchApply", affirmorList) == -1) {
					Logger.getLogger(getClass()).debug(
							"Affirmor process failed");
					conn.rollback();
					result.put("errcode", new Integer(10)); // 插入决裁者失败,返回错误代码2
					result.put("erremp", essMatchBean.getEmpID());
				} else
					conn.commit();
			} else {
				conn.rollback();
				result.put("errcode", new Integer(-1)); // 未知错误,返回
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("添加一条值班申请信息错误", e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 为ESS考勤申请添加决裁者
	 * 
	 * @param applyNo
	 *            申请序号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"
	 * @param affirmorList
	 *            决裁者列表
	 * @return 插入决裁者数目, -1时表示操作失败
	 */
	private int addApplyAffirmor(int applyNo, String applyType,
			List affirmorList) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ESS_AFFIRM "
				+ "(ESS_AFFIRM_NO, APPLY_NO, AFFIRM_FLAG, "
				+ "AFFIRM_LEVEL, AFFIRMOR_ID, CREATE_DATE, "
				+ "CREATED_BY, ACTIVITY, APPLY_TYPE,AFFIRM_LEVEL_ORIGINAL) "
				+ "VALUES " + "(ESS_AFFIRM_SEQ.NEXTVAL, ?, 0, "
				+ "?, ?, SYSDATE, " + "?, 1, ?,?)";
		Logger.getLogger(getClass()).debug(sql);
		EssAffirmor essAffirmor = new EssAffirmor();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applyNo);
			pstmt.setString(4, adminId);
			pstmt.setString(5, applyType);
			String copyAffirm = "";
			int affNo = 1;
			boolean flag = true;
			for (int i = 0; i < affirmorList.size(); i++) {
				essAffirmor = (EssAffirmor) affirmorList.get(i);
				if(!copyAffirm.equals("")){
					if(copyAffirm.equals(essAffirmor.getAffirmorId())){
						flag = false;
					}
				}
				if(affNo == 1){
					copyAffirm = essAffirmor.getAffirmorId();
					affNo++;
				}
				if(flag){
				pstmt.setInt(2, essAffirmor.getAffirmLevel());
				pstmt.setString(3, essAffirmor.getAffirmorId());
				pstmt.setInt(6, essAffirmor.getAffirmLevelOriginal());
				
				Logger.getLogger(getClass()).debug(
						"Affirmor[" + essAffirmor.getAffirmLevel() + "] : "
								+ essAffirmor.getAffirmorId());
				result += pstmt.executeUpdate();
				flag = true;
				}
			}
//			if (result == affirmorList.size())
				conn.commit();
//			else {
//				conn.rollback();
//				return -1;
//			}
		} catch (Exception e) {
			result= -1;
			result = -1;
			try {
				conn.rollback();
			} catch (Exception ee) {
				ee.printStackTrace();
				Logger.getLogger(getClass()).debug(ee.toString());
			}
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 取出当前登录者的加班申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 */
	public List getOtInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO, ESS_APPLY_OT.EMPID, HR_EMPLOYEE.CHINESENAME, "
				+ "HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME OT_FROM_TIME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME OT_TO_TIME, "
				+ "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, ESS_APPLY_OT.REMARK, ESS_APPLY_OT.CREATED_BY, "
				+ "ESS_APPLY_OT.ACTIVITY, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG,ESS_APPLY_OT.APPLY_OT_AFFIRM "
				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.EMPID = HR_EMPLOYEE.EMPID "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_APPLY_OT.EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "ORDER BY APPLY_OT_DATE DESC";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, sDate);
			pstmt.setString(i++, eDate);
			rs = pstmt.executeQuery();
			i = 0;
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));
					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("EMPID")))) {
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essOverTimeBean.setOpFlag(3);
					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的加班申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 * @param deptId
	 *            搜索部门
	 */
	public List getOtInfoList(String sDate, String eDate, String key,
			String deptId) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO,ESS_APPLY_OT.APPLY_GROUP_NO ,ESS_APPLY_OT.EMPID, HR_EMPLOYEE_V.CHINESENAME, "
				+ "HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "hr_employee_v.chinese_pinyin,hr_employee_v.englishposition ,hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME OT_FROM_TIME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME OT_TO_TIME, "
				+ "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, ESS_APPLY_OT.REMARK, ESS_APPLY_OT.CREATED_BY, "
				+ "ESS_APPLY_OT.ACTIVITY, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG,ESS_APPLY_OT.APPLY_OT_AFFIRM ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST "

				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE_V, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_APPLY_OT.EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_APPLY_OT.APPLY_GROUP_NO LIKE ?) ";
		if (!sDate.equals("") && !eDate.equals(""))
			sql += " AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ";
		sql += " AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
				+ ") "
				+ "ORDER BY ESS_APPLY_OT.ACTIVITY asc,APPLY_GROUP_NO,APPLY_OT_DATE DESC";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("deptId : " + deptId);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			if (!sDate.equals("") && !eDate.equals("")) {
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
			}
			pstmt.setString(i++, deptId.equals("") ? "1" : deptId);
			rs = pstmt.executeQuery();
			i = 0;
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setApplyGroupSeq(rs
							.getInt("APPLY_GROUP_NO"));

					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));

					essOverTimeBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essOverTimeBean.setEnPosition(rs
							.getString("englishposition"));
					essOverTimeBean.setEnPost(rs.getString("englishpost"));
					essOverTimeBean.setDeptEnName(rs.getString("dept_en_name"));
					essOverTimeBean.setEnOtTypeName(rs
							.getString("CODE_EN_NAME"));

					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("EMPID")))) {
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essOverTimeBean.setOpFlag(3);
					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean.setPosition(rs.getString("POSITION"));
					essOverTimeBean.setPost(rs.getString("POST"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的加班申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 * @param deptId
	 *            搜索部门
	 */
	public List getOtInfoList(String sDate, String eDate, String key,
			int qryType, String deptId, String otType, String affirmId) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO,ESS_APPLY_OT.APPLY_GROUP_NO ,ESS_APPLY_OT.EMPID, HR_EMPLOYEE_V.CHINESENAME, "
				+ "HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "hr_employee_v.chinese_pinyin,hr_employee_v.englishposition ,hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME OT_FROM_TIME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME OT_TO_TIME, "

				// +
				// "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				// +
				// "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME - decode(apply_ot_type_code,'WorkingOtType01',0,(select get_ot_time_types(ot_to_time,ot_from_time,APPLY_OT_FLAG) from dual)) OT_LENGTH, "

				+ "ESS_APPLY_OT.APPLY_OT_REMARK,SUBSTR(ESS_APPLY_OT.APPLY_OT_REMARK, 0, 10)  INTRO, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, ESS_APPLY_OT.REMARK, ESS_APPLY_OT.CREATED_BY, "
				+ "ESS_APPLY_OT.ACTIVITY, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG,ESS_APPLY_OT.APPLY_OT_AFFIRM ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION,HR_EMPLOYEE_V.POST_GRADE POST_GRADE, HR_EMPLOYEE_V.POST POST "

				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE_V, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.EMPID = HR_EMPLOYEE_V.EMPID ";

		if (qryType == 1) {// 已决裁
			sql += " AND ESS_AFFIRM.affirm_flag = 1 ";
		} else if (qryType == 3) {// 未决裁
			sql += " AND ESS_AFFIRM.affirm_flag = 0 ";
		}

		if (!otType.equals("")) {
			sql += "AND ESS_APPLY_OT.Apply_Ot_Type_Code = '" + otType + "' ";
		}

		sql += "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID ";
		sql += "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) ";

		sql += "AND EXISTS (SELECT S2.ADMINID "
				+ "	  FROM SY_ADMIN S2, SY_ADMIN_DEPTID S3 "
				+ "	 WHERE S2.ADMINID = '"
				+ adminId
				+ "'"
				+ "	   AND HR_EMPLOYEE_V.DEPTID = S3.ADMIN_DEPTID "
				+ "	   AND S2.ADMINNO = S3.ADMIN_NO "
				+ "UNION ALL SELECT ADMINID FROM SY_ADMIN T WHERE T.ADMINID = HR_EMPLOYEE_V.EMPID AND T.ADMINID = '"
				+ adminId + "') ";

		if (!affirmId.equals("")) {
			sql += " AND ESS_APPLY_OT.EMPID = '" + affirmId + "'";
		}

		if (!key.equals("")) {
			sql += "AND (ESS_APPLY_OT.EMPID LIKE '%" + key + "%' "
					+ "OR HR_EMPLOYEE_V.CHINESENAME LIKE '%" + key + "%' "
					+ "OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER('%"
					+ key + "%') " + "OR HR_DEPARTMENT.DEPTNAME LIKE '%" + key
					+ "%' " + "OR ESS_APPLY_OT.APPLY_GROUP_NO LIKE '%" + key
					+ "%') ";
		}

		if (!sDate.equals("") && !eDate.equals(""))
			sql += " AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE('" + sDate
					+ "', 'YYYY-MM-DD') AND TO_DATE('" + eDate
					+ "' || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') ";

		sql += " AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 ";

		if (!deptId.equals("") && !deptId.equals("-1")) {
			sql += "AND EXISTS ( " + "	SELECT     * "
					+ "FROM HR_DEPARTMENT B1 "
					+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
					+ "START WITH B1.DEPTID = '" + deptId + "'  "
					+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		}

		sql += " ORDER BY decode(ESS_APPLY_OT.activity,0,ESS_APPLY_OT.activity) asc,OT_TO_TIME DESC,HR_EMPLOYEE_V.DEPTID,HR_EMPLOYEE_V.EMPID  ";

		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("deptId : " + deptId);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			i = 0;
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setApplyGroupSeq(rs
							.getInt("APPLY_GROUP_NO"));

					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));

					essOverTimeBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essOverTimeBean.setEnPosition(rs
							.getString("englishposition"));
					essOverTimeBean.setEnPost(rs.getString("englishpost"));
					essOverTimeBean.setDeptEnName(rs.getString("dept_en_name"));
					essOverTimeBean.setEnOtTypeName(rs
							.getString("CODE_EN_NAME"));

					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					// essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));

					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("EMPID")))) {
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essOverTimeBean.setOpFlag(3);
					}
					essOverTimeBean.setOtRemark(StringUtil.checkNull(rs
							.getString("APPLY_OT_REMARK")));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean.setPosition(rs.getString("POSITION"));
					essOverTimeBean.setPost(rs.getString("POST"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////
	public List getPersonalOtInfoList(String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO, ESS_APPLY_OT.EMPID, HR_EMPLOYEE_V.CHINESENAME, "
				+ "HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME OT_FROM_TIME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME OT_TO_TIME, "
				+ "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, ESS_APPLY_OT.REMARK, ESS_APPLY_OT.CREATED_BY, "
				+ "ESS_APPLY_OT.ACTIVITY, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG,ESS_APPLY_OT.APPLY_OT_AFFIRM ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST "
				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE_V, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.EMPID = HR_EMPLOYEE_V.EMPID  and ESS_APPLY_OT.ACTIVITY='0' "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_APPLY_OT.EMPID LIKE ? ) "
				+ " AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = 'Z000000'  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
				+ ") "
				+ "ORDER BY ESS_APPLY_OT.ACTIVITY asc,APPLY_OT_DATE DESC";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key + "%");
			rs = pstmt.executeQuery();
			i = 0;
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));
					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("EMPID")))) {
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essOverTimeBean.setOpFlag(3);
					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean.setPosition(rs.getString("POSITION"));
					essOverTimeBean.setPost(rs.getString("POST"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的休假申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 */
	public List getLeaveInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG "
				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "ORDER BY LEAVE_FROM_TIME DESC";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, sDate);
			pstmt.setString(i++, eDate);
			pstmt.setString(i++, sDate);
			pstmt.setString(i++, eDate);
			pstmt.setString(i++, sDate);
			pstmt.setString(i++, eDate);
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的休假申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 */
	public List getLeaveInfoList(String sDate, String eDate, String key,
			String deptID) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO,ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ " hr_employee_v.chinese_pinyin,hr_employee_v.englishposition ,hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'LeaveTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO LIKE ?) ";
		if (!sDate.equals("") || !eDate.equals("")) {
			sql += "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) ";
		}
		sql += "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 " + "AND EXISTS ( "
				+ "	SELECT     * " + "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc,APPLY_GROUP_NO";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			if (!sDate.equals("") || !eDate.equals("")) {
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
			}
			pstmt.setString(i++, deptID.equals("") ? "1" : deptID);
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));

					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getLeaveInfoList(String sDate, String eDate, String key,
			int qryType, String deptID, int maxLevel, String ltype,
			String affirmId, String item_no) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO,ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO, "
				+ " ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME,  "
				+ " hr_employee_v.chinese_pinyin,hr_employee_v.englishposition ,"
				+ " hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				// +
				// "ATT_GET_LEAVE_LENGTH_W(ESS_LEAVE_APPLY_TB.LEAVE_EMPID,TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI'),TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI'),2,decode(leave_type,'LeaveType001',17,'LeaveType002',24,'LeaveType003',16,'LeaveType004',23,'LeaveType005',22,'LeaveType006',18,'LeaveType007',19,'LeaveType008',21,'LeaveType009',20,'LeaveType012',31,'LeaveType010',25,14)) LENGTH,"

				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON,SUBSTR(ESS_LEAVE_APPLY_TB.LEAVE_REASON, 0, 10)  INTRO, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION,HR_EMPLOYEE_V.POST_GRADE POST_GRADE, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'LeaveTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID ";

		if (!affirmId.equals("")) {
			sql += " AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID ='" + affirmId + "'";
		}

		if (qryType == 1) {// 已决裁
			sql += " AND ESS_AFFIRM.affirm_flag = 1 ";
		} else if (qryType == 3) {// 未决裁
			sql += " AND ESS_AFFIRM.affirm_flag = 0 ";
		}

		if (!ltype.equals("")) {
			sql += "AND ESS_LEAVE_APPLY_TB.LEAVE_TYPE = '" + ltype + "' ";
		}

		sql += "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND EXISTS (SELECT S2.ADMINID "
				+ "	  FROM SY_ADMIN S2, SY_ADMIN_DEPTID S3 "
				+ "	 WHERE S2.ADMINID = '"
				+ adminId
				+ "'"
				+ "	   AND HR_EMPLOYEE_V.DEPTID = S3.ADMIN_DEPTID "
				+ "	   AND S2.ADMINNO = S3.ADMIN_NO "
				+ "UNION ALL SELECT ADMINID FROM SY_ADMIN T WHERE T.ADMINID = HR_EMPLOYEE_V.EMPID AND T.ADMINID = '"
				+ adminId + "') ";

		if (!key.equals("")) {
			sql += "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE '%" + key + "%' "
					+ "OR HR_EMPLOYEE_V.CHINESENAME LIKE '%" + key + "%' "
					+ "OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER('%"
					+ key + "%') " + "OR HR_DEPARTMENT.DEPTNAME LIKE '%" + key
					+ "%' " + "OR ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO LIKE '%"
					+ key + "%') ";
		}

		if (!sDate.equals("") || !eDate.equals("")) {
			sql += "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE('"
					+ sDate + "', 'YYYY-MM-DD') AND TO_DATE('" + eDate
					+ "' || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE('"
					+ sDate + "', 'YYYY-MM-DD') AND TO_DATE('" + eDate
					+ "' || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE('"
					+ sDate + "', 'YYYY-MM-DD') "
					+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE('"
					+ eDate + "' || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) ";
		}

		sql += "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 ";

		if (!deptID.equals("") && !deptID.equals("-1")) {
			sql += "AND EXISTS ( " + "	SELECT     * "
					+ "FROM HR_DEPARTMENT B1 "
					+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
					+ "START WITH B1.DEPTID = '" + deptID + "'  "
					+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		}

		sql += "ORDER BY decode(ESS_LEAVE_APPLY_TB.activity,0,ESS_LEAVE_APPLY_TB.activity) asc,ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME DESC,HR_EMPLOYEE_V.DEPTID,HR_EMPLOYEE_V.EMPID  ";

		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);

			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));

					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(StringUtil.checkNull(rs
							.getString("LEAVE_REASON")));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					// essLeaveBean.setLength(rs.getString("LENGTH"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getPersonalLeaveInfoList(String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'LeaveTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID  and ESS_LEAVE_APPLY_TB.activity='0'"
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? ) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = 'Z000000'  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
				+ ") "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getPersonalLeaveInfoList(String key, String item_no) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE,SUBSTR(ESS_LEAVE_APPLY_TB.LEAVE_REASON, 0, 10) INTRO, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// +
				// "ATT_GET_LEAVE_LENGTH_W(ESS_LEAVE_APPLY_TB.LEAVE_EMPID,TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI'),TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI'),2,decode(leave_type,'LeaveType001',17,'LeaveType002',24,'LeaveType003',16,'LeaveType004',23,'LeaveType005',22,'LeaveType006',18,'LeaveType007',19,'LeaveType008',21,'LeaveType009',20,'LeaveType012',31,'LeaveType010',25,14)) LENGTH,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION,HR_EMPLOYEE_V.POST_GRADE POST_GRADE, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'LeaveTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID  and ESS_LEAVE_APPLY_TB.activity='0'"
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND EXISTS (SELECT S2.ADMINID "
				+ "	  FROM SY_ADMIN S2, SY_ADMIN_DEPTID S3 "
				+ "	 WHERE S2.ADMINID = ? "
				+ "	   AND HR_EMPLOYEE_V.DEPTID = S3.ADMIN_DEPTID "
				+ "	   AND S2.ADMINNO = S3.ADMIN_NO "
				+ "UNION ALL SELECT ADMINID FROM SY_ADMIN T WHERE T.ADMINID = HR_EMPLOYEE_V.EMPID AND T.ADMINID = ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? ) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc,ESS_LEAVE_APPLY_TB.CREATE_DATE DESC,HR_EMPLOYEE_V.DEPTID,HR_EMPLOYEE_V.EMPID  ";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(StringUtil.checkNull(rs
							.getString("LEAVE_REASON")));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					// essLeaveBean.setLength(rs.getString("LENGTH"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的出差申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 */
	public List getEvectionInfoList(String sDate, String eDate, String key,
			String deptID) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO,ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "  hr_employee_v.chinese_pinyin,hr_employee_v.englishposition,hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'EvectionTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_LEAVE_APPLY_TB.LEAVE_NO LIKE ?) ";
		if (!sDate.equals("") || !eDate.equals("")) {
			sql += " AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) ";
		}

		sql += " AND ESS_AFFIRM.APPLY_TYPE(+) = 'EvectionApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 " + " AND EXISTS ( "
				+ "	SELECT     * " + "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc,APPLY_GROUP_NO";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			if (!sDate.equals("") || !eDate.equals("")) {
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
			}
			pstmt.setString(i++, deptID.equals("") ? "Z000000" : deptID);
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"EvectionApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getPersonalEvectionInfoList(String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'EvectionTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID and ESS_LEAVE_APPLY_TB.activity='0' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? ) "
				+ " AND ESS_AFFIRM.APPLY_TYPE(+) = 'EvectionApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ " AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = 'Z000000'  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
				+ ") "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"EvectionApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的教育培训申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 加班时间排倒序
	 */
	public List getTrainingInfoList(String sDate, String eDate, String key,
			String deptID) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO,ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO ,ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ " HR_EMPLOYEE_V.CHINESE_PINYIN,HR_EMPLOYEE_V.ENGLISHPOSITION ,HR_EMPLOYEE_V.ENGLISHPOST,HR_EMPLOYEE_V.POST,HR_DEPARTMENT.DEPT_EN_NAME,SY_CODE.CODE_EN_NAME,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'TrainingTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_LEAVE_APPLY_TB.LEAVE_NO  LIKE ?) ";
		if (!sDate.equals("") || !eDate.equals("")) {
			sql += " AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
					+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
					+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) ";
		}
		sql += " AND ESS_AFFIRM.APPLY_TYPE(+) = 'TrainingApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 " + "AND EXISTS ( "
				+ "	SELECT     * " + "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc,APPLY_GROUP_NO ";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			pstmt.setString(i++, key.trim() + "%");
			if (!sDate.equals("") || !eDate.equals("")) {
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
				pstmt.setString(i++, sDate);
				pstmt.setString(i++, eDate);
			}
			pstmt.setString(i++, deptID.equals("") ? "1" : deptID);
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));

					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setPost(rs.getString("POST"));
					essLeaveBean.setChinesePinYin(rs
							.getString("CHINESE_PINYIN"));
					essLeaveBean.setEnPositon(rs.getString("ENGLISHPOSITION"));
					essLeaveBean.setEnPost(rs.getString("ENGLISHPOST"));
					essLeaveBean.setDeptEnName(rs.getString("DEPT_EN_NAME"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"TrainingApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getPersonalTrainingInfoList(String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.CREATED_BY, "
				+ "ESS_LEAVE_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG ,"
				+ "HR_EMPLOYEE_V.POSITION POSITION, HR_EMPLOYEE_V.POST POST  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE = 'TrainingTypeCode'"
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID and ESS_LEAVE_APPLY_TB.activity='0' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( HR_EMPLOYEE_V.DEPTID IN "
				+ "(SELECT ADMIN_DEPTID FROM SY_ADMIN,SY_ADMIN_DEPTID WHERE SY_ADMIN.ADMINID = ? "
				+ " AND SY_ADMIN.ADMINNO = SY_ADMIN_DEPTID.ADMIN_NO) "
				+ "OR "
				+ "HR_EMPLOYEE_V.EMPID = ? "
				+ ") "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? ) "
				+ " AND ESS_AFFIRM.APPLY_TYPE(+) = 'TrainingApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID ='Z000000'  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
				+ ") "
				+ "ORDER BY ESS_LEAVE_APPLY_TB.activity asc";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key.trim() + "%");
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("LEAVE_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essLeaveBean.setOpFlag(3);
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"TrainingApply"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的漏卡申请记录以进行查看
	 * 
	 * @param sDate
	 * @param eDate
	 * @param key
	 * @return
	 */
	public List getCardInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_CARD_APPLY_TB.APPLY_ID, ESS_CARD_APPLY_TB.APPLY_EMP_ID, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME,ESS_CARD_APPLY_TB.DOOR_TYPE, "
				+ "TO_CHAR(ESS_CARD_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_CARD_APPLY_TB.R_TIME, 'YYYY-MM-DD HH24:MI') CARD_TIME, "
				+ "ESS_CARD_APPLY_TB.APPLY_REASON, ESS_CARD_APPLY_TB.ACTIVITY, ESS_CARD_APPLY_TB.CREATED_BY, "
				+ "ESS_CARD_APPLY_TB.REMARK, NVL(ESS_AFFIRM.AFFIRM_FLAG, 0) AFFIRM_FLAG "
				+ "FROM ESS_CARD_APPLY_TB, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_CARD_APPLY_TB.APPLY_EMP_ID = HR_EMPLOYEE.EMPID "
				+ "AND ESS_CARD_APPLY_TB.APPLY_ID = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( "
				+ "HR_EMPLOYEE.DEPTID IN "
				+ "(SELECT SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO WHERE AR_SUPERVISOR_ID = ?) "
				+ "OR "
				+ "HR_EMPLOYEE.EMPID = ? "
				+ ") "
				+ "AND (ESS_CARD_APPLY_TB.APPLY_EMP_ID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_CARD_APPLY_TB.R_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'NoCardApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "ORDER BY ESS_CARD_APPLY_TB.R_TIME DESC";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, sDate);
			pstmt.setString(i++, eDate);
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssCardApplyBean essCardApplyBean = new EssCardApplyBean();
					essCardApplyBean.setApplyID(String.valueOf(rs
							.getInt("APPLY_ID")));
					essCardApplyBean.setApplyEmp(rs.getString("APPLY_EMP_ID"));
					essCardApplyBean
							.setChineseName(rs.getString("CHINESENAME"));
					essCardApplyBean.setDeptName(rs.getString("DEPTNAME"));
					essCardApplyBean.setCreateDate(rs.getString("CREATE_DATE"));
					essCardApplyBean.setCardTime(rs.getString("CARD_TIME"));
					essCardApplyBean.setApplyReason(rs
							.getString("APPLY_REASON"));
					essCardApplyBean.setActivity(rs.getInt("ACTIVITY"));
					essCardApplyBean.setRemark(rs.getString("REMARK"));
					essCardApplyBean.setDoorType(rs.getString("DOOR_TYPE"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("APPLY_EMP_ID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essCardApplyBean.setOpFlag(3);
					essCardApplyBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_ID"),
									"NoCardApply"));
					list.add(essCardApplyBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出漏卡申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者的值班申请记录以进行查看
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 值班时间排倒序
	 */
	public List getMatchInfoList(String sDate, String eDate, String key) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 1;
		String sql = "SELECT ESS_MATCH_APPLY_TB.MATCH_NO, ESS_MATCH_APPLY_TB.MATCH_EMPID, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_MATCH_APPLY_TB.MATCH_TYPE, SY_CODE.CODE_NAME MATCH_TYPE_NAME, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD') MATCH_DATE, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD HH24:MI') MATCH_FROM_TIME, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_TO_TIME, 'YYYY-MM-DD HH24:MI') MATCH_TO_TIME, "
				+ "ESS_MATCH_APPLY_TB.ACTIVITY, ESS_MATCH_APPLY_TB.CREATED_BY, "
				+ "ESS_AFFIRM.AFFIRM_FLAG, ESS_MATCH_APPLY_TB.REMARK "
				+ "FROM ESS_MATCH_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE ESS_MATCH_APPLY_TB.MATCH_TYPE = SY_CODE.CODE_ID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_MATCH_APPLY_TB.MATCH_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND ESS_MATCH_APPLY_TB.MATCH_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND ( "
				+ "HR_EMPLOYEE.DEPTID IN "
				+ "(SELECT SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO WHERE AR_SUPERVISOR_ID = ?) "
				+ "OR "
				+ "HR_EMPLOYEE.EMPID = ? "
				+ ") "
				+ "AND (ESS_MATCH_APPLY_TB.MATCH_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND TO_DATE(TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'MatchApply' "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL(+) = 1 "
				+ "ORDER BY MATCH_FROM_TIME DESC, MATCH_EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, adminId);
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, key + "%");
			pstmt.setString(i++, sDate);
			pstmt.setString(i++, eDate);
			i = 0;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssMatchBean essMatchBean = new EssMatchBean();
					essMatchBean.setMatchNo(rs.getInt("MATCH_NO"));
					essMatchBean.setEmpID(rs.getString("MATCH_EMPID"));
					essMatchBean.setChineseName(rs.getString("CHINESENAME"));
					essMatchBean.setDeptName(rs.getString("DEPTNAME"));
					essMatchBean.setMatchDate(rs.getString("MATCH_DATE"));
					essMatchBean.setMatchTypeCode(rs.getString("MATCH_TYPE"));
					essMatchBean.setMatchTypeName(rs
							.getString("MATCH_TYPE_NAME"));
					essMatchBean.setCreateDate(rs.getString("CREATE_DATE"));
					essMatchBean.setMatchFromTime(rs
							.getString("MATCH_FROM_TIME"));
					essMatchBean.setMatchToTime(rs.getString("MATCH_TO_TIME"));
					essMatchBean.setActivity(rs.getInt("ACTIVITY"));
					if (rs.getInt("AFFIRM_FLAG") == 0
							&& rs.getInt("ACTIVITY") == 0
							&& (adminId.equals(rs.getString("CREATED_BY")) || adminId
									.equals(rs.getString("MATCH_EMPID"))))
						// 决裁者与人事都未操作时, 且申请人或加班人是当前登录者时,可以进行删除
						essMatchBean.setOpFlag(3);
					essMatchBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("MATCH_NO"),
									"MatchApply"));
					essMatchBean.setRemark(rs.getString("REMARK"));
					list.add(essMatchBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有加班记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getOtAffirmList(String sDate, String eDate, String key,
			int qryType) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO, ESS_APPLY_OT.EMPID, HR_EMPLOYEE.CHINESENAME, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "ESS_AFFIRM.AFFIRM_FLAG, HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME OT_FROM_TIME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME OT_TO_TIME, "
				+ "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, ESS_APPLY_OT.ACTIVITY, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, A.OT_TOTAL "
				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST, "

				// QPSS要求在每条加班申请后面显示当月加班总计
				+ "(SELECT AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR,SUM (DECODE(AR_ITEM.ITEM_GROUP_CODE,'OverTimeGroup',AR_DETAIL.QUANTITY,0)) OT_TOTAL "
				+ "	  FROM AR_DETAIL, AR_ITEM "
				+ "	 WHERE AR_DETAIL.AR_ITEM_NO = AR_ITEM.ITEM_NO "
				+ " GROUP BY AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR "
				+ ") A "

				+ "WHERE ESS_APPLY_OT.EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_APPLY_OT.EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'OtApply') = 'OtApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'OtApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "

				// QPSS要求在每条加班申请后面显示当月加班总计
				+ "AND A.AR_MONTH_STR(+) = AR_GET_MONTHSTR (TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE,'YYYY/MM/DD')) "
				+ "AND A.EMPID(+) = ESS_APPLY_OT.EMPID ";

		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag > 0)";

		}
		sql += " ORDER BY DECODE(ACTIVITY, 0, 1, ACTIVITY), LAST_FLAG, AFFIRM_FLAG, APPLY_OT_DATE DESC, DEPTNAME, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));
					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {
						// 上级决裁者通过, 且人事未否决时才能进行操作
						if (rs.getInt("AFFIRM_FLAG") == 0)
							essOverTimeBean.setOpFlag(0);
						else if (essSysparam.isOtModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							essOverTimeBean.setOpFlag(2);
						else if (essSysparam.isOtModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							essOverTimeBean.setOpFlag(1);
					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					essOverTimeBean.setOtTotal(rs.getDouble("OT_TOTAL"));
					// 取得个人加班参考数据
					essOverTimeBean.setOtDetail(this
							.getOverTimeDetail(essOverTimeBean));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有加班记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getOtAffirmList(String sDate, String eDate, String key,
			int qryType, String deptID, int maxLevel) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO, ESS_APPLY_OT.EMPID, HR_EMPLOYEE.CHINESENAME, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "ESS_AFFIRM.AFFIRM_FLAG, HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ " HEV.chinese_pinyin,HEV.koreanname,HEV.englishposition ,HEV.korposition,"
				+ "  HEV.englishpost,  HEV.KORPOST,hr_department.dept_en_name,hr_department.dept_kor_name ,"
				+ " SY_CODE.CODE_EN_NAME,SY_CODE.Code_Kor_Name, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME OT_FROM_TIME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME OT_TO_TIME, "
				+ "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, ESS_APPLY_OT.ACTIVITY, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, A.OT_TOTAL, "
				+ "HEV.POST,HEV.POSITION  "
				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST,	HR_EMPLOYEE_V HEV , "

				// QPSS要求在每条加班申请后面显示当月加班总计
				+ "(SELECT AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR,SUM (DECODE(AR_ITEM.ITEM_GROUP_CODE,'OverTimeGroup',AR_DETAIL.QUANTITY,0)) OT_TOTAL "
				+ "	  FROM AR_DETAIL, AR_ITEM "
				+ "	 WHERE AR_DETAIL.AR_ITEM_NO = AR_ITEM.ITEM_NO "
				+ " GROUP BY AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR "
				+ ") A "

				+ "WHERE ESS_APPLY_OT.EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HEV.EMPID=HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_APPLY_OT.EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'OtApply') = 'OtApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'OtApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
				+ ") "
				// QPSS要求在每条加班申请后面显示当月加班总计
				+ "AND A.AR_MONTH_STR(+) = AR_GET_MONTHSTR (TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE,'YYYY/MM/DD')) "
				+ "AND A.EMPID(+) = ESS_APPLY_OT.EMPID ";

		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += "and ESS_AFFIRM.affirm_flag = 0 and ((ESS_AFFIRM.Apply_No in"
					+ "       (select tt.Apply_No"
					+ "            from ESS_AFFIRM tt"
					+ "           where tt.Apply_No = ESS_AFFIRM.Apply_No"
					+ "             and tt.Affirmor_Id <> ESS_AFFIRM.Affirmor_Id"
					+ "             and tt.Affirm_Flag = 1"
					+ "             and tt.AFFIRM_LEVEL = ESS_AFFIRM.AFFIRM_LEVEL - 1) and"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = "
					+ maxLevel
					+ ") or"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = '1')";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag > 0)";

		}
		sql += " ORDER BY DECODE(ACTIVITY, 0, 1, ACTIVITY), LAST_FLAG, AFFIRM_FLAG, APPLY_OT_DATE DESC, DEPTNAME, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, sDate);
			pstmt.setString(6, eDate);
			pstmt.setString(7, adminId);
			pstmt.setString(8, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));

					essOverTimeBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essOverTimeBean.setKoreanname(rs.getString("koreanname"));
					essOverTimeBean.setDeptEnName(rs.getString("dept_en_name"));
					essOverTimeBean.setDeptKorName(rs
							.getString("dept_kor_name"));
					essOverTimeBean.setEnPosition(rs
							.getString("englishposition"));
					essOverTimeBean.setKorPosition(rs.getString("korposition"));
					essOverTimeBean.setEnPost(rs.getString("englishpost"));
					essOverTimeBean.setKorPost(rs.getString("KORPOST"));
					essOverTimeBean.setEnOtTypeName(rs
							.getString("CODE_EN_NAME"));
					essOverTimeBean.setKorOtTypeName(rs
							.getString("Code_Kor_Name"));

					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					essOverTimeBean.setPost(rs.getString("POST"));
					essOverTimeBean.setPosition(rs.getString("POSITION"));
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {
						// 上级决裁者通过, 且人事未否决时才能进行操作
						if (rs.getInt("AFFIRM_FLAG") == 0)
							essOverTimeBean.setOpFlag(0);
						else if (essSysparam.isOtModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							essOverTimeBean.setOpFlag(2);
						else if (essSysparam.isOtModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							essOverTimeBean.setOpFlag(1);
					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					essOverTimeBean.setOtTotal(rs.getDouble("OT_TOTAL"));
					// 取得个人加班参考数据
					essOverTimeBean.setOtDetail(this
							.getOverTimeDetail(essOverTimeBean));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有未决裁加班记录
	 * 
	 * 
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public Object getOtAffirmListAll(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtAffirmListAll", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 取出加班记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getOtConfirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_APPLY_OT.APPLY_OT_NO, MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "ESS_APPLY_OT.EMPID, HR_EMPLOYEE.CHINESENAME, "
				+ "HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') OT_TO_DATE, "
				+ "ESS_APPLY_OT.OT_FROM_TIME, ESS_APPLY_OT.OT_TO_TIME, "
				+ "( "
				+ "TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') "
				+ "- TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI') "
				+ ")* 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, ESS_APPLY_OT.OT_DEDUCT_TIME, "
				+ "ESS_APPLY_OT.ACTIVITY,ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, A.OT_TOTAL "
				+ "FROM ESS_APPLY_OT, HR_EMPLOYEE, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM, "

				// QPSS要求在每条加班申请后面显示当月加班总计
				+ "(SELECT AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR,SUM (DECODE(AR_ITEM.ITEM_GROUP_CODE,'OverTimeGroup',AR_DETAIL.QUANTITY,0)) OT_TOTAL "
				+ "	  FROM AR_DETAIL, AR_ITEM "
				+ "	 WHERE AR_DETAIL.AR_ITEM_NO = AR_ITEM.ITEM_NO "
				+ " GROUP BY AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR "
				+ ") A "

				+ "WHERE ESS_APPLY_OT.EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_APPLY_OT.EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' "

				// QPSS要求在每条加班申请后面显示当月加班总计
				+ "AND A.AR_MONTH_STR(+) = AR_GET_MONTHSTR (TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE,'YYYY/MM/DD')) "
				+ "AND A.EMPID(+) = ESS_APPLY_OT.EMPID ";

		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag > 0))";

		}
		;
		sql += " GROUP BY APPLY_OT_NO, ESS_APPLY_OT.EMPID, CHINESENAME, DEPTNAME, CODE_NAME, "
				+ "APPLY_OT_DATE, APPLY_OT_FLAG, OT_FROM_TIME, OT_TO_TIME, OT_DEDUCT_TIME, "
				+ "APPLY_OT_REMARK, ESS_APPLY_OT.CREATE_DATE, ESS_APPLY_OT.ACTIVITY,ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, A.OT_TOTAL "
				+ " ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY, APPLY_OT_DATE DESC, DEPTNAME, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));
					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtToDate(rs.getString("OT_TO_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					Logger.getLogger(getClass())
							.debug(
									"============================="
											+ String.valueOf(essSysparam
													.isReConfirm()));
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {
						if (rs.getInt("ACTIVITY") == 0)
							essOverTimeBean.setOpFlag(0);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							essOverTimeBean.setOpFlag(2);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							essOverTimeBean.setOpFlag(1);
					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					essOverTimeBean.setOtTotal(rs.getDouble("OT_TOTAL"));
					// 取得个人加班参考数据
					essOverTimeBean.setOtDetail(this
							.getOverTimeDetail(essOverTimeBean));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出加班记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出加班记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getOtConfirmList(String sDate, String eDate, String key,
			int qryType, String deptID) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;

		String sql = "SELECT HR_EMP_V.person_id , "
				+ "ESS_APPLY_OT.APPLY_OT_NO, "
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, "
				+ "MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMP_V.EMPID, "
				+ "HR_EMP_V.CHINESENAME, "
				+ "HR_EMP_V.chinese_pinyin, "
				+ "HR_DEPARTMENT.DEPTNAME, "
				+ "SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME, "
				+ "SY_CODE.CODE_EN_NAME, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') OT_TO_DATE, "
				+ "ESS_APPLY_OT.OT_FROM_TIME, "
				+ "ESS_APPLY_OT.OT_TO_TIME, "
				// +"( TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') "
				// +"- TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI') "
				// +")* 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, "
				+ "(CASE MAX(ESS_APPLY_OT.APPLY_OT_TYPE_CODE) "
				+ "WHEN 'WorkingOtType01'  THEN "
				+ "(TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') - "
				+ "TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, "
				+ "'YYYY-MM-DD HH24:MI')) * 24 - ESS_APPLY_OT.OT_DEDUCT_TIME "
				+ "ELSE "
				+ "MAX(ESS_APPLY_OT.OT_LENGTH) "
				+ "END) OT_LENGTH,"
				+ "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE - OT_NIGHT_FLAG, 'YYYY-MM-DD') AS otDate, "
				+ "HR_EMP_V.FOURTHDEPTNAME, "
				+ "ESS_APPLY_OT.APPLY_OT_REMARK, "
				+ "ESS_APPLY_OT.APPLY_OT_FLAG, "
				+ "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "ESS_APPLY_OT.OT_DEDUCT_TIME, "
				+ "ESS_APPLY_OT.ACTIVITY,ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, "
				+ "hr_department.dept_en_name, "
				+ "ESS_APPLY_OT.APPLY_GROUP_NO "
				+ "FROM ESS_APPLY_OT, "
				+ "HR_EMP_V, SY_CODE, "
				+ "HR_DEPARTMENT, "
				+ "ESS_AFFIRM "

				+ "WHERE ESS_APPLY_OT.EMPID = HR_EMP_V.person_id "
				+ "AND HR_EMP_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID "
				+ "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (HR_EMP_V.EMPID LIKE ? OR HR_EMP_V.CHINESENAME LIKE ? OR UPPER(HR_EMP_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_APPLY_OT.APPLY_GROUP_NO LIKE ?) "
				+ "AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' " + "AND EXISTS ( "
				+ "	SELECT     * " + "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMP_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";

		// QPSS要求在每条加班申请后面显示当月加班总计
		// +
		// "AND A.AR_MONTH_STR(+) = AR_GET_MONTHSTR (TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE,'YYYY/MM/DD')) "
		// + "AND A.EMPID(+) = ESS_APPLY_OT.EMPID ";

		// String sql =
		// "SELECT HR_EMP_V.person_id,ESS_APPLY_OT.APPLY_OT_NO, MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
		// + "HR_EMP_V.EMPID, HR_EMP_V.CHINESENAME,HR_EMP_V.chinese_pinyin, " +
		// "HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME APPLY_OT_TYPE_NAME,SY_CODE.CODE_EN_NAME, "
		// + "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD') APPLY_OT_DATE, "
		// +
		// "TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') OT_TO_DATE, "
		// + "ESS_APPLY_OT.OT_FROM_TIME, ESS_APPLY_OT.OT_TO_TIME, " + "( " +
		// "TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE + ESS_APPLY_OT.APPLY_OT_FLAG, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') "
		// +
		// "- TO_DATE(TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE, 'YYYY-MM-DD ') || ESS_APPLY_OT.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI') "
		// +
		// ")* 24 - ESS_APPLY_OT.OT_DEDUCT_TIME OT_LENGTH, TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE - OT_NIGHT_FLAG, 'YYYY-MM-DD')     AS otDate, HR_EMP_V.FOURTHDEPTNAME, "
		// + "ESS_APPLY_OT.APPLY_OT_REMARK, ESS_APPLY_OT.APPLY_OT_FLAG, " +
		// "TO_CHAR(ESS_APPLY_OT.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, ESS_APPLY_OT.OT_DEDUCT_TIME, "
		// +
		// "ESS_APPLY_OT.ACTIVITY,ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, A.OT_TOTAL ,"
		// + "hr_department.dept_en_name, ESS_APPLY_OT.APPLY_GROUP_NO " +
		// " FROM ESS_APPLY_OT, HR_EMP_V, SY_CODE, HR_DEPARTMENT, ESS_AFFIRM, "
		//
		// // QPSS要求在每条加班申请后面显示当月加班总计
		// +
		// "(SELECT AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR,SUM (DECODE(AR_ITEM.ITEM_GROUP_CODE,'OverTimeGroup',AR_DETAIL.QUANTITY,0)) OT_TOTAL "
		// + "	  FROM AR_DETAIL, AR_ITEM " +
		// "	 WHERE AR_DETAIL.AR_ITEM_NO = AR_ITEM.ITEM_NO " +
		// " GROUP BY AR_DETAIL.EMPID,AR_DETAIL.AR_MONTH_STR " + ") A "
		//
		// + "WHERE ESS_APPLY_OT.EMPID = HR_EMP_V.person_id " +
		// "AND HR_EMP_V.DEPTID = HR_DEPARTMENT.DEPTID " +
		// "AND ESS_APPLY_OT.APPLY_OT_TYPE_CODE = SY_CODE.CODE_ID " +
		// "AND ESS_APPLY_OT.APPLY_OT_NO = ESS_AFFIRM.APPLY_NO(+) " +
		// "AND (HR_EMP_V.EMPID LIKE ? OR HR_EMP_V.CHINESENAME LIKE ? OR UPPER(HR_EMP_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_APPLY_OT.APPLY_GROUP_NO LIKE ?) "
		// +
		// "AND ESS_APPLY_OT.APPLY_OT_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
		// + "AND ESS_AFFIRM.APPLY_TYPE(+) = 'OtApply' " + "AND EXISTS ( " +
		// "	SELECT     * " + "FROM HR_DEPARTMENT B1 " +
		// "WHERE B1.DEPTID=HR_EMP_V.DEPTID " + "START WITH B1.DEPTID = ?  " +
		// "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID "
		// + ") "
		//
		// // QPSS要求在每条加班申请后面显示当月加班总计
		// +
		// "AND A.AR_MONTH_STR(+) = AR_GET_MONTHSTR (TO_CHAR(ESS_APPLY_OT.APPLY_OT_DATE,'YYYY/MM/DD')) "
		// + "AND A.EMPID(+) = ESS_APPLY_OT.EMPID ";

		// QPSS要求在每条加班申请后面显示当月加班总计
		// if (qryType == 1) {// 全部决裁
		// sql += " and 0 = (select count(*) from ess_affirm where apply_no =
		// ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag =
		// 0) and ESS_APPLY_OT.Activity=0 ";
		// }
		// if (qryType == 2) {// 部分决裁
		// sql += " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag =
		// 0)"
		// + " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_APPLY_OT.APPLY_OT_NO and APPLY_TYPE = 'OtApply' and affirm_flag >
		// 0)";
		// } else if (qryType == 3) {// 未决裁
		// sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select
		// count(*) from ess_affirm where apply_no = ESS_APPLY_OT.APPLY_OT_NO
		// and APPLY_TYPE = 'OtApply' and affirm_flag > 0))";
		//
		// }

		if (qryType == 0) {// 人事未决裁
			sql += " and  ESS_APPLY_OT.Activity=0 ";
		}
		if (qryType == 1) {// 人事已通过
			sql += "and  ESS_APPLY_OT.Activity=1 ";
		} else if (qryType == 2) {// 人事已否决
			sql += " and ESS_APPLY_OT.Activity=2 ";
		}

		sql += "   HAVING    MAX(ESS_AFFIRM.AFFIRM_FLAG)=1 AND  MIN(ESS_AFFIRM.AFFIRM_FLAG)=1  GROUP BY ESS_APPLY_OT.APPLY_GROUP_NO , HR_EMP_V.person_id , APPLY_OT_NO, HR_EMP_V.EMPID,SY_CODE.CODE_EN_NAME, CHINESENAME,HR_EMP_V.chinese_pinyin, hr_department.dept_en_name,HR_DEPARTMENT.DEPTNAME, SY_CODE.CODE_NAME,SY_CODE.CODE_EN_NAME, "
				+ "APPLY_OT_DATE, APPLY_OT_FLAG, OT_FROM_TIME, OT_TO_TIME, OT_DEDUCT_TIME, "
				+ "APPLY_OT_REMARK, ESS_APPLY_OT.CREATE_DATE, ESS_APPLY_OT.ACTIVITY,ESS_APPLY_OT.REMARK,ESS_APPLY_OT.APPLY_OT_AFFIRM, HR_EMP_V.FOURTHDEPTNAME,OT_NIGHT_FLAG "
				+ " ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY,APPLY_GROUP_NO,APPLY_OT_DATE DESC, DEPTNAME, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, key.trim() + "%");
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getDisdpageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getDisdpageSize()) {
					EssOverTimeBean essOverTimeBean = new EssOverTimeBean();
					essOverTimeBean.setOtNo(rs.getInt("APPLY_OT_NO"));
					essOverTimeBean.setEmpId(rs.getString("EMPID"));
					essOverTimeBean.setChineseName(rs.getString("CHINESENAME"));
					essOverTimeBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essOverTimeBean.setEnOtTypeName(rs
							.getString("CODE_EN_NAME"));
					essOverTimeBean.setDeptEnName(rs.getString("dept_en_name"));
					essOverTimeBean.setDeptName(rs.getString("DEPTNAME"));
					essOverTimeBean.setOtTypeName(rs
							.getString("APPLY_OT_TYPE_NAME"));
					essOverTimeBean.setOtDate(rs.getString("APPLY_OT_DATE"));
					essOverTimeBean.setOtToDate(rs.getString("OT_TO_DATE"));
					essOverTimeBean.setOtFromTime(rs.getString("OT_FROM_TIME"));
					essOverTimeBean.setOtToTime(rs.getString("OT_TO_TIME"));
					essOverTimeBean.setOtLength(rs.getDouble("OT_LENGTH"));
					essOverTimeBean.setPerson_id(rs.getString("PERSON_ID"));
					essOverTimeBean.setFourthDeptName(rs
							.getString("fourthDeptName"));
					essOverTimeBean.setOtDate(rs.getString("otDate"));

					Logger.getLogger(getClass())
							.debug(
									"============================="
											+ String.valueOf(essSysparam
													.isReConfirm()));
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {

						if (rs.getInt("ACTIVITY") == 0)
							essOverTimeBean.setOpFlag(0);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							essOverTimeBean.setOpFlag(2);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							essOverTimeBean.setOpFlag(1);

					}
					essOverTimeBean
							.setOtRemark(rs.getString("APPLY_OT_REMARK"));
					essOverTimeBean.setOtNextDays(rs.getInt("APPLY_OT_FLAG"));
					essOverTimeBean.setOtDeduct(rs.getDouble("OT_DEDUCT_TIME"));
					essOverTimeBean.setCreateDate(rs.getString("CREATE_DATE"));
					essOverTimeBean.setActivity(rs.getInt("ACTIVITY"));
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_OT_NO"),
									"OtApply"));
					essOverTimeBean.setRemark(rs.getString("REMARK"));
					essOverTimeBean
							.setForceType(rs.getInt("APPLY_OT_AFFIRM") == 1);
					// essOverTimeBean.setOtTotal(rs.getDouble("OT_TOTAL"));
					essOverTimeBean.setApplyGroupSeq(rs
							.getInt("APPLY_GROUP_NO"));
					// 取得个人加班参考数据
					// essOverTimeBean.setOtDetail(this.getOverTimeDetail(essOverTimeBean));
					essOverTimeBean.setAffirmorList((ArrayList) this
							.getAffirmorList(essOverTimeBean.getOtNo()
									.intValue(), "OtApply"));
					list.add(essOverTimeBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i
					/ this.pageControl.getDisdpageSize()
					+ (i % this.pageControl.getDisdpageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出加班记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public Object getOtConfirmListFirstDate(Object parameterObject)
			throws GlRuntimeException {

		try {

			return commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtConfirmListFirstDate", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("Get OverTime Count Exception. ", e);
		}
	}

	public Object getOtViewListFirstDate(Object parameterObject)
			throws GlRuntimeException {

		try {

			return commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtViewListFirstDate", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("Get OverTime Count Exception. ", e);
		}
	}

	public Object getConfirmListFirstDate(Object parameterObject)
			throws GlRuntimeException {

		try {

			return commonSQLMapAdapter.executeQuery(
					"ess.apply.getConfirmListFirstDate", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("Get Leave Count Exception. ", e);
		}
	}

	/**
	 * get overtime detail
	 * 
	 * @param OTBean
	 * @return List
	 */
	public List getOverTimeDetail(EssOverTimeBean OTBean) {

		List list = new ArrayList();

		// 设置基本参数
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("EMPID", OTBean.getEmpId());
		parameterObject.setString("APPLY_OT_DATE", OTBean.getOtDate());

		// 未进行决裁的加班小时总计(1级决裁，未决裁，下级决裁状态为未决裁或者无下级决裁：0，-1)
		parameterObject.setInt("AFFIRM_LEVEL", 1);
		parameterObject.setInt("AFFIRM_FLAG", 0);
		parameterObject.set("NEXT_AFFIRM_FLAG", new Integer[] { 0, -1 });
		SimpleMap map1 = (SimpleMap) this.getOvertimeDetail(parameterObject);
		if (map1 == null) {

			map1 = new SimpleMap();
			map1.setInt("OT_HOUR", 0);
		}
		map1.setString("title", "未决裁：");
		list.add(map1);

		// 当前级别已决裁但下级未决裁的加班小时总计(当前级决裁，已决裁，下级决裁状态为未决裁)
		parameterObject.setInt("AFFIRM_FLAG", 1);
		parameterObject.set("NEXT_AFFIRM_FLAG", new Integer[] { 0 });

		// 循环决裁者列表
		Iterator affirmorList = OTBean.getAffirmorList().iterator();
		while (affirmorList.hasNext()) {

			EssAffirmor affirmor = (EssAffirmor) affirmorList.next();
			if (affirmor.getAffirmLevel() < OTBean.getAffirmorList().size()) {

				// 设置当前决裁级别参数
				parameterObject.setInt("AFFIRM_LEVEL", affirmor
						.getAffirmLevel());
				SimpleMap map2 = (SimpleMap) this
						.getOvertimeDetail(parameterObject);
				if (map2 == null) {

					map2 = new SimpleMap();
					map2.setInt("OT_HOUR", 0);
				}
				map2.setInt("AFFIRM_LEVEL", affirmor.getAffirmLevel());
				map2.setInt("NEXT_AFFIRM_LEVEL", affirmor.getAffirmLevel() + 1);
				map2.setString("title", map2.getString("AFFIRM_LEVEL") + "级决裁"
						+ map2.getString("NEXT_AFFIRM_LEVEL") + "级未决裁：");
				list.add(map2);
			}
		}

		// 全部决裁完毕但未进行人事确认的加班小时总计(最后一级决裁，已决裁，无下级决裁)
		parameterObject.setInt("AFFIRM_LEVEL", OTBean.getAffirmorList().size());
		parameterObject.setInt("AFFIRM_FLAG", 1);
		parameterObject.set("NEXT_AFFIRM_FLAG", new Integer[] { -1 });
		SimpleMap map3 = (SimpleMap) this.getOvertimeDetail(parameterObject);
		if (map3 == null) {

			map3 = new SimpleMap();
			map3.setInt("OT_HOUR", 0);
		}
		map3.setString("title", "决裁完毕未确认：");
		list.add(map3);

		return list;
	}

	/**
	 * get overtime detail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public Object getOvertimeDetail(Object parameterObject)
			throws GlRuntimeException {

		try {

			return commonSQLMapAdapter.executeQuery(
					"ess.apply.getOvertimeDetail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("Get overtime detail Exception. ", e);
		}
	}

	/**
	 * 取出当前登录者可以进行决裁的所有休假记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getLeaveAffirmList(String sDate, String eDate, String key,
			int qryType, String deptID, int maxLevel) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ " hr_employee_v.chinese_pinyin,hr_employee_v.koreanname,hr_employee_v.englishposition ,hr_employee_v.korposition,"
				+ "  hr_employee_v.englishpost, hr_employee_v.korpost,hr_department.dept_en_name,hr_department.dept_kor_name ,"
				+ "SY_CODE.CODE_EN_NAME,SY_CODE.Code_Kor_Name, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK ,"
				+ " HR_EMPLOYEE_V.POST POST ,HR_EMPLOYEE_V.POSITION POSITION,  "
				+ "ESS_GET_TURN_HOLIDY(SYSDATE,ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveThisMonth, "
				+ "ESS_GET_TURN_HOLIDY(ADD_MONTHS(SYSDATE,1),ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveNextMonth "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='LeaveTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'LeaveApply') = 'LeaveApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag = 0) ";
		}
		if (qryType == 2) {// 部分决裁
			sql += "and ESS_AFFIRM.affirm_flag = 0 and ((ESS_AFFIRM.Apply_No in"
					+ "       (select tt.Apply_No"
					+ "            from ESS_AFFIRM tt"
					+ "           where tt.Apply_No = ESS_AFFIRM.Apply_No"
					+ "             and tt.Affirmor_Id <> ESS_AFFIRM.Affirmor_Id"
					+ "             and tt.Affirm_Flag = 1"
					+ "             and tt.AFFIRM_LEVEL = ESS_AFFIRM.AFFIRM_LEVEL - 1) and"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = "
					+ maxLevel
					+ ") or"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = '1')";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag > 0)";

		}

		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("dept : " + deptID);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, sDate);
			pstmt.setString(6, eDate);
			pstmt.setString(7, sDate);
			pstmt.setString(8, eDate);
			pstmt.setString(9, sDate);
			pstmt.setString(10, eDate);
			pstmt.setString(11, adminId);
			pstmt.setString(12, deptID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setKoreanname(rs.getString("koreanname"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));
					essLeaveBean.setDeptKorName(rs.getString("dept_kor_name"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setKorPositon(rs.getString("korposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setKorPost(rs.getString("korpost"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setLeaveTypeKorName(rs
							.getString("Code_Kor_Name"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					essLeaveBean.setLeaveThisMonth(new Double(rs
							.getString("leaveThisMonth")).toString());
					essLeaveBean.setLeaveNextMonth(new Double(rs
							.getString("leaveNextMonth")).toString());

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));

					// essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "this"));

					// essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "next"));

					list.add(essLeaveBean);
				}
				i++;
			}

			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			// Logger.getLogger(getClass()).debug(e.toString());
			// throw new GlRuntimeException("取出当前登录者可以进行决裁的所有休假记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}

		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有出差记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getEvectionAffirmList(String sDate, String eDate, String key,
			int qryType, String deptID, int maxLevel) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ " hr_employee_v.chinese_pinyin,hr_employee_v.koreanname,hr_employee_v.englishposition ,hr_employee_v.korposition,"
				+ "  hr_employee_v.englishpost, hr_employee_v.korpost,hr_department.dept_en_name,hr_department.dept_kor_name ,"
				+ "SY_CODE.CODE_EN_NAME,SY_CODE.Code_Kor_Name, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK ,"
				+ " HR_EMPLOYEE_V.POST POST ,HR_EMPLOYEE_V.POSITION POSITION,  "
				+ "ESS_GET_TURN_HOLIDY(SYSDATE,ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveThisMonth, "
				+ "ESS_GET_TURN_HOLIDY(ADD_MONTHS(SYSDATE,1),ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveNextMonth "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='EvectionTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'EvectionApply') = 'EvectionApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'EvectionApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += "and ESS_AFFIRM.affirm_flag = 0 and ((ESS_AFFIRM.Apply_No in"
					+ "       (select tt.Apply_No"
					+ "            from ESS_AFFIRM tt"
					+ "           where tt.Apply_No = ESS_AFFIRM.Apply_No"
					+ "             and tt.Affirmor_Id <> ESS_AFFIRM.Affirmor_Id"
					+ "             and tt.Affirm_Flag = 1"
					+ "             and tt.AFFIRM_LEVEL = ESS_AFFIRM.AFFIRM_LEVEL - 1) and"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = "
					+ maxLevel
					+ ") or"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = '1')";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag > 0)";

		}

		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("dept : " + deptID);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, sDate);
			pstmt.setString(6, eDate);
			pstmt.setString(7, sDate);
			pstmt.setString(8, eDate);
			pstmt.setString(9, sDate);
			pstmt.setString(10, eDate);
			pstmt.setString(11, adminId);
			pstmt.setString(12, deptID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setKoreanname(rs.getString("koreanname"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));
					essLeaveBean.setDeptKorName(rs.getString("dept_kor_name"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setKorPositon(rs.getString("korposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setKorPost(rs.getString("korpost"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setLeaveTypeKorName(rs
							.getString("Code_Kor_Name"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					essLeaveBean.setLeaveThisMonth(new Double(rs
							.getString("leaveThisMonth")).toString());
					essLeaveBean.setLeaveNextMonth(new Double(rs
							.getString("leaveNextMonth")).toString());

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"EvectionApply"));

					// essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "this"));

					// essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "next"));

					list.add(essLeaveBean);
				}
				i++;
			}

			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			// Logger.getLogger(getClass()).debug(e.toString());
			// throw new GlRuntimeException("取出当前登录者可以进行决裁的所有出差记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}

		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有培训记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getTrainingAffirmList(String sDate, String eDate, String key,
			int qryType, String deptID, int maxLevel) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ " HR_EMPLOYEE_V.chinese_pinyin,HR_EMPLOYEE_V.koreanname,HR_EMPLOYEE_V.englishposition ,HR_EMPLOYEE_V.korposition,"
				+ "  HR_EMPLOYEE_V.englishpost, HR_EMPLOYEE_V.korpost,HR_DEPARTMENT.dept_en_name,HR_DEPARTMENT.dept_kor_name,"
				+ "SY_CODE.CODE_EN_NAME,SY_CODE.Code_Kor_Name, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK ,"
				+ " HR_EMPLOYEE_V.POST POST ,HR_EMPLOYEE_V.POSITION POSITION,  "
				+ "ESS_GET_TURN_HOLIDY(SYSDATE,ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveThisMonth, "
				+ "ESS_GET_TURN_HOLIDY(ADD_MONTHS(SYSDATE,1),ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveNextMonth "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='TrainingTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'TrainingApply') = 'TrainingApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'TrainingApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += "and ESS_AFFIRM.affirm_flag = 0 and ((ESS_AFFIRM.Apply_No in"
					+ "       (select tt.Apply_No"
					+ "            from ESS_AFFIRM tt"
					+ "           where tt.Apply_No = ESS_AFFIRM.Apply_No"
					+ "             and tt.Affirmor_Id <> ESS_AFFIRM.Affirmor_Id"
					+ "             and tt.Affirm_Flag = 1"
					+ "             and tt.AFFIRM_LEVEL = ESS_AFFIRM.AFFIRM_LEVEL - 1) and"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = "
					+ maxLevel
					+ ") or"
					+ "       ESS_AFFIRM.AFFIRM_LEVEL = '1')";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag > 0)";

		}

		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("dept : " + deptID);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, sDate);
			pstmt.setString(6, eDate);
			pstmt.setString(7, sDate);
			pstmt.setString(8, eDate);
			pstmt.setString(9, sDate);
			pstmt.setString(10, eDate);
			pstmt.setString(11, adminId);
			pstmt.setString(12, deptID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setKoreanname(rs.getString("koreanname"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));
					essLeaveBean.setDeptKorName(rs.getString("dept_kor_name"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setKorPositon(rs.getString("korposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setKorPost(rs.getString("korpost"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setLeaveTypeKorName(rs
							.getString("Code_Kor_Name"));

					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					essLeaveBean.setLeaveThisMonth(new Double(rs
							.getString("leaveThisMonth")).toString());
					essLeaveBean.setLeaveNextMonth(new Double(rs
							.getString("leaveNextMonth")).toString());

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"TrainingApply"));

					list.add(essLeaveBean);
				}
				i++;
			}

			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			// Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
			// throw new GlRuntimeException("取出当前登录者可以进行决裁的所有出差记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}

		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有出差记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getEvectionAffirmList(String sDate, String eDate, String key,
			int qryType, String deptID) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK ,"
				+ " HR_EMPLOYEE_V.POST POST ,HR_EMPLOYEE_V.POSITION POSITION  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='EvectionTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'EvectionApply') = 'EvectionApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'EvectionApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, adminId);
			pstmt.setString(11, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"EvectionApply"));

					essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "this"));

					essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "next"));

					list.add(essLeaveBean);
				}
				i++;
			}

			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出当前登录者可以进行决裁的所有休假记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		//		
		// Iterator it= list.iterator();
		//		
		// while(it.hasNext()){
		//			
		// EssLeaveBean essLeaveBean = (EssLeaveBean)it.next();
		//			
		//			
		// }
		//		
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有教育培训记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getTrainingAffirmList(String sDate, String eDate, String key,
			int qryType, String deptID) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK ,"
				+ " HR_EMPLOYEE_V.POST POST ,HR_EMPLOYEE_V.POSITION POSITION  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='TrainingTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'TrainingApply') = 'TrainingApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'TrainingApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, adminId);
			pstmt.setString(11, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"TrainingApply"));

					essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "this"));

					essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "next"));

					list.add(essLeaveBean);
				}
				i++;
			}

			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出当前登录者可以进行决裁的所有教育培训记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		//		
		// Iterator it= list.iterator();
		//		
		// while(it.hasNext()){
		//			
		// EssLeaveBean essLeaveBean = (EssLeaveBean)it.next();
		//			
		//			
		// }
		//		
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有休假记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getLeaveAffirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK, "
				+ "ESS_GET_TURN_HOLIDY(SYSDATE,ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveThisMonth, "
				+ "ESS_GET_TURN_HOLIDY(ADD_MONTHS(SYSDATE,1),ESS_LEAVE_APPLY_TB.LEAVE_EMPID) AS leaveNextMonth "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE='LeaveTypeCode'"
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'LeaveApply') = 'LeaveApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'LeaveApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));
					essLeaveBean.setLeaveThisMonth(rs
							.getString("leaveThisMonth"));
					essLeaveBean.setLeaveNextMonth(rs
							.getString("leaveNextMonth"));

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));

					// essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "this"));
					// essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "next"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出当前登录者可以进行决裁的所有休假记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有出差记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getEvectionAffirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK "
				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE='EvectionTypeCode'"
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'EvectionApply') = 'EvectionApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'EvectionApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"EvectionApply"));

					essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "this"));
					essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "next"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出当前登录者可以进行决裁的所有出差记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有教育培训记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getTrainingAffirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY, ESS_LEAVE_APPLY_TB.REMARK "
				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND SY_CODE.PARENT_CODE='TrainingTypeCode'"
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'TrainingApply') = 'TrainingApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'TrainingApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, LEAVE_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setRemark(rs.getString("REMARK"));

					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {

						// 决裁状态为"待决裁"
						if (rs.getInt("AFFIRM_FLAG") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 决裁后可进行修改并且状态为"已通过"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 决裁后可进行修改并且状态为"否决"
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"TrainingApply"));

					essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "this"));
					essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "next"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出当前登录者可以进行决裁的所有教育培训记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出休假申请记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getLeaveConfirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK "
				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and affirm_flag > 0))";

		}
		;
		sql += " GROUP BY LEAVE_NO, LEAVE_EMPID, CHINESENAME, DEPTNAME, LEAVE_TYPE, CODE_NAME, "
				+ "ESS_LEAVE_APPLY_TB.CREATE_DATE, LEAVE_FROM_TIME, LEAVE_TO_TIME, LEAVE_REASON, "
				+ "ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK "
				+ "ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY, "
				+ "LEAVE_FROM_TIME DESC, LEAVE_EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));

					// 如果人事可以提前进行确认或者所有决裁全部完毕，可进行人事确认
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {

						// 申请状态为"待决裁"
						if (rs.getInt("ACTIVITY") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 确认后可以重新进行确认并且申请状态为"已通过"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 确认后可以重新进行确认并且申请状态为"已否决"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					essLeaveBean.setRemark(rs.getString("remark"));
					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出休假申请记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getLeaveConfirmList(String sDate, String eDate, String key,
			int qryType, String deptID) {
		/*
		 * try{ if(qryType == 4){ CommonSQLMapAdapter commonSQLMapAdapter = new
		 * CommonSQLMapAdapter("em2"); SimpleMap smap = new SimpleMap();
		 * smap.setString("SDATE",sDate); smap.setString("EDATE",eDate); List
		 * list1 =
		 * commonSQLMapAdapter.executeQueryForMulti("ess.apply.getLeaveConfirmList"
		 * ,smap);
		 * 
		 * return list1; } }catch(Exception e) {
		 * Logger.getLogger(getClass()).debug(e.toString()); throw new
		 * GlRuntimeException("取出休假申请记录以进行人事确认错误", e); }
		 */
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, HR_EMP_V.EMPID LEAVE_EMPID, "
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMP_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "HR_EMP_V.chinese_pinyin,HR_EMP_V.FOURTHDEPTNAME,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD  HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK ,POSTION.CODE_NAME POSTION, ALTYPE.CODE_NAME as APPLYLEAVETYPE,MAX(ESS_LEAVE_APPLY_TB.FILE_UPLOAD_FLAG) AS FILE_UPLOAD_FLAG "
				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE ALTYPE,SY_CODE, HR_EMP_V,SY_CODE POSTION, HR_DEPARTMENT, ESS_AFFIRM ,"
				+ "(SELECT APPLY_NO,MAX(AFFIRM_FLAG) MAXFLAG,MIN(AFFIRM_FLAG) MINFLAG FROM ESS_AFFIRM "
				+ "  WHERE APPLY_TYPE='LeaveApply' GROUP BY APPLY_NO) B "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID  AND HR_EMP_V.POST_GRADE_CODE=POSTION.CODE_ID(+)    AND ESS_LEAVE_APPLY_TB.Applyleavetype = ALTYPE.CODE_ID(+) "
				+ " AND SY_CODE.PARENT_CODE='LeaveTypeCode'"
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMP_V.person_id "
				+ "AND HR_EMP_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (HR_EMP_V.EMPID LIKE ? OR HR_EMP_V.CHINESENAME LIKE ?  OR UPPER(HR_EMP_V.CHINESE_PINYIN)  LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'LeaveApply' "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMP_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		// if (qryType == 1) {// 全部决裁
		// sql += " and 0 = (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and
		// affirm_flag = 0)";
		// }
		// if (qryType == 2) {// 部分决裁
		// sql += " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and
		// affirm_flag = 0)"
		// + " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'LeaveApply' and
		// affirm_flag > 0)";
		// } else if (qryType == 3) {// 未决裁
		// sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select
		// count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO
		// and APPLY_TYPE = 'LeaveApply' and affirm_flag > 0))";
		//
		// } else if (qryType == 4) {//未确定
		// sql += " AND B.APPLY_NO = ESS_AFFIRM.APPLY_NO AND
		// ESS_LEAVE_APPLY_TB.Activity = 0 AND (B.MAXFLAG = 1 AND B.MINFLAG=1)";
		// }
		// ;
		if (qryType == 0) {// 人事未决裁
			sql += " and 0 = ESS_LEAVE_APPLY_TB.ACTIVITY ";
		}
		if (qryType == 1) {// 人事已通过
			sql += " and 1 = ESS_LEAVE_APPLY_TB.ACTIVITY ";
		} else if (qryType == 2) {// 人事已否决
			sql += "and 2 = ESS_LEAVE_APPLY_TB.ACTIVITY ";

		}

		sql += " AND  B.MINFLAG=1  AND  B.MAXFLAG=1  AND ESS_LEAVE_APPLY_TB.LEAVE_NO=B.APPLY_NO  GROUP BY ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO,LEAVE_NO, HR_EMP_V.EMPID, CHINESENAME, HR_DEPARTMENT.DEPTNAME, LEAVE_TYPE, SY_CODE.CODE_NAME, "
				+ "ESS_LEAVE_APPLY_TB.CREATE_DATE, LEAVE_FROM_TIME, LEAVE_TO_TIME, LEAVE_REASON, "
				+ "ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK , "
				+ " HR_EMP_V.chinese_pinyin, POSTION.CODE_NAME ,hr_department.dept_en_name, HR_EMP_V.FOURTHDEPTNAME, SY_CODE.CODE_EN_NAME,ALTYPE.CODE_NAME "
				+ "ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY, APPLY_GROUP_NO,"
				+ "LEAVE_FROM_TIME DESC, LEAVE_EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug("deptID : " + deptID);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, key.trim() + "%");
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, sDate);
			pstmt.setString(11, eDate);
			pstmt.setString(12, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getDisdpageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getDisdpageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));
					essLeaveBean.setPosition(rs.getString("POSTION"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));
					essLeaveBean.setFourthDeptName(rs
							.getString("FOURTHDEPTNAME"));

					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));

					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));
					essLeaveBean.setApplyLeaveType(rs
							.getString("APPLYLEAVETYPE"));
					essLeaveBean.setFileUploadFlag(rs
							.getInt("FILE_UPLOAD_FLAG"));

					// 如果人事可以提前进行确认或者所有决裁全部完毕，可进行人事确认
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {

						// 申请状态为"待决裁"
						if (rs.getInt("ACTIVITY") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 确认后可以重新进行确认并且申请状态为"已通过"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 确认后可以重新进行确认并且申请状态为"已否决"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"LeaveApply"));
					essLeaveBean.setRemark(rs.getString("remark"));

					// essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "this"));
					// essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(essLeaveBean.getEmpId(),
					// "next"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i
					/ this.pageControl.getDisdpageSize()
					+ (i % this.pageControl.getDisdpageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出出差申请记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getEvectionConfirmList(String sDate, String eDate, String key,
			int qryType, String deptID) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO,ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, "
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME, HR_DEPARTMENT.DEPTNAME,"
				+ "hr_employee_v.chinese_pinyin,hr_employee_v.englishposition ,hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD  HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK ,"
				+ "HR_EMPLOYEE_V.POST,HR_EMPLOYEE_V.POSITION  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM ,"
				+ "(SELECT APPLY_NO,MAX(AFFIRM_FLAG) MAXFLAG,MIN(AFFIRM_FLAG) MINFLAG FROM ESS_AFFIRM "
				+ "  WHERE APPLY_TYPE='EvectionApply' GROUP BY APPLY_NO) B "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='EvectionTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ?  OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_LEAVE_APPLY_TB.LEAVE_NO LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'EvectionApply' "
				+ "AND EXISTS ( "
				+ "	SELECT     * "
				+ "FROM HR_DEPARTMENT B1 "
				+ "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
				+ "START WITH B1.DEPTID = ?  "
				+ "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") ";
		// if (qryType == 1) {// 全部决裁
		// sql += " and 0 = (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and
		// affirm_flag = 0)";
		// }
		// if (qryType == 2) {// 部分决裁
		// sql += " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and
		// affirm_flag = 0)"
		// + " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'EvectionApply' and
		// affirm_flag > 0)";
		// } else if (qryType == 3) {// 未决裁
		// sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select
		// count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO
		// and APPLY_TYPE = 'EvectionApply' and affirm_flag > 0))";
		//
		// } else if (qryType == 4) {//未确定
		// sql += " AND B.APPLY_NO = ESS_AFFIRM.APPLY_NO AND
		// ESS_LEAVE_APPLY_TB.Activity = 0 AND (B.MAXFLAG = 1 AND B.MINFLAG=1)";
		// }
		// ;

		if (qryType == 0) {// 未决裁
			sql += " and 0 = ESS_LEAVE_APPLY_TB.ACTIVITY ";
		}
		if (qryType == 1) {// 已确认
			sql += " and 1 = ESS_LEAVE_APPLY_TB.ACTIVITY ";
		} else if (qryType == 2) {// 已否决
			sql += " and 2 = ESS_LEAVE_APPLY_TB.ACTIVITY ";

		}
		sql += " GROUP BY ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO,LEAVE_NO, LEAVE_EMPID, CHINESENAME, HR_DEPARTMENT.DEPTNAME, LEAVE_TYPE, SY_CODE.CODE_NAME, "
				+ "ESS_LEAVE_APPLY_TB.CREATE_DATE, LEAVE_FROM_TIME, LEAVE_TO_TIME, LEAVE_REASON, "
				+ "ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK ,POSITION,POST ,"
				+ " hr_employee_v.chinese_pinyin,hr_employee_v.englishposition ,hr_employee_v.englishpost,hr_department.dept_en_name,SY_CODE.CODE_EN_NAME  "
				+ " ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY, APPLY_GROUP_NO,"
				+ "LEAVE_FROM_TIME DESC, LEAVE_EMPID ";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, key.trim() + "%");
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, sDate);
			pstmt.setString(11, eDate);
			pstmt.setString(12, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));

					essLeaveBean.setChinesePinYin(rs
							.getString("chinese_pinyin"));
					essLeaveBean.setEnPositon(rs.getString("englishposition"));
					essLeaveBean.setEnPost(rs.getString("englishpost"));
					essLeaveBean.setDeptEnName(rs.getString("dept_en_name"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("CODE_EN_NAME"));

					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));

					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));

					// 如果人事可以提前进行确认或者所有决裁全部完毕，可进行人事确认
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {

						// 申请状态为"待决裁"
						if (rs.getInt("ACTIVITY") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 确认后可以重新进行确认并且申请状态为"已通过"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 确认后可以重新进行确认并且申请状态为"已否决"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"EvectionApply"));
					essLeaveBean.setRemark(rs.getString("remark"));

					essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "this"));
					essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "next"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出教育培训申请记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getTrainingConfirmList(String sDate, String eDate, String key,
			int qryType, String deptID) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_LEAVE_APPLY_TB.LEAVE_NO, ESS_LEAVE_APPLY_TB.LEAVE_EMPID, ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO,"
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMPLOYEE_V.CHINESENAME,HR_EMPLOYEE_V.CHINESE_PINYIN CHINESEPINYIN, HR_DEPARTMENT.DEPTNAME,HR_EMPLOYEE_V.DEPT_EN_NAME DEPTENNAME,"
				+ "HR_EMPLOYEE_V.ENGLISHPOST ENGLISHPOST,HR_EMPLOYEE_V.POST POST,HR_EMPLOYEE_V.ENGLISHPOSITION  ENGLISHPOSITION,"
				+ "ESS_LEAVE_APPLY_TB.LEAVE_TYPE, SY_CODE.CODE_NAME LEAVE_TYPE_NAME,SY_CODE.CODE_EN_NAME LEAVE_TYPE_EN_NAME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD  HH24:MI') LEAVE_FROM_TIME, "
				+ "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD HH24:MI') LEAVE_TO_TIME, "
				// update style of display for QPSS
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME, 'YYYY-MM-DD')
				// LEAVE_FROM_TIME, "
				// + "TO_CHAR(ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME, 'YYYY-MM-DD')
				// LEAVE_TO_TIME, "
				+ "ESS_LEAVE_APPLY_TB.LEAVE_REASON, ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK ,"
				+ "HR_EMPLOYEE_V.POST,HR_EMPLOYEE_V.POSITION  "

				+ "FROM ESS_LEAVE_APPLY_TB, SY_CODE, HR_EMPLOYEE_V, HR_DEPARTMENT, ESS_AFFIRM ,"
				+ "(SELECT APPLY_NO,MAX(AFFIRM_FLAG) MAXFLAG,MIN(AFFIRM_FLAG) MINFLAG FROM ESS_AFFIRM "
				+ "  WHERE APPLY_TYPE='TrainingApply' GROUP BY APPLY_NO) B "

				+ "WHERE ESS_LEAVE_APPLY_TB.LEAVE_TYPE = SY_CODE.CODE_ID "
				+ " AND SY_CODE.PARENT_CODE='TrainingTypeCode' "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_EMPID = HR_EMPLOYEE_V.EMPID "
				+ "AND HR_EMPLOYEE_V.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_EMPID LIKE ? OR HR_EMPLOYEE_V.CHINESENAME LIKE ?  OR UPPER(HR_EMPLOYEE_V.CHINESE_PINYIN) LIKE UPPER(?) OR HR_DEPARTMENT.DEPTNAME LIKE ? OR ESS_LEAVE_APPLY_TB.LEAVE_NO LIKE ?) "
				+ "AND (ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "OR ESS_LEAVE_APPLY_TB.LEAVE_FROM_TIME < TO_DATE(?, 'YYYY-MM-DD') "
				+ "AND ESS_LEAVE_APPLY_TB.LEAVE_TO_TIME > TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'TrainingApply' "
		// + "AND EXISTS ( "
		// + " SELECT * "
		// + "FROM HR_DEPARTMENT B1 "
		// + "WHERE B1.DEPTID=HR_EMPLOYEE_V.DEPTID "
		// + "START WITH B1.DEPTID = ? "
		// + "CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID " + ") "
		;
		// if (qryType == 1) {// 全部决裁
		// sql += " and 0 = (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and
		// affirm_flag = 0)";
		// }
		// if (qryType == 2) {// 部分决裁
		// sql += " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and
		// affirm_flag = 0)"
		// + " and 0 < (select count(*) from ess_affirm where apply_no =
		// ESS_LEAVE_APPLY_TB.LEAVE_NO and APPLY_TYPE = 'TrainingApply' and
		// affirm_flag > 0)";
		// } else if (qryType == 3) {// 未决裁
		// sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select
		// count(*) from ess_affirm where apply_no = ESS_LEAVE_APPLY_TB.LEAVE_NO
		// and APPLY_TYPE = 'TrainingApply' and affirm_flag > 0))";
		//
		// } else if (qryType == 4) {//未确定
		// sql += " AND B.APPLY_NO = ESS_AFFIRM.APPLY_NO AND
		// ESS_LEAVE_APPLY_TB.Activity = 0 AND (B.MAXFLAG = 1 AND B.MINFLAG=1)";
		// }
		// ;

		if (qryType == 0) {// 未决裁
			sql += " and 0 = ESS_LEAVE_APPLY_TB.ACTIVITY ";
		}
		if (qryType == 1) {// 已确认
			sql += " and 1 = ESS_LEAVE_APPLY_TB.ACTIVITY ";
		} else if (qryType == 2) {// 已否决
			sql += " and 2 = ESS_LEAVE_APPLY_TB.ACTIVITY ";

		}
		sql += " GROUP BY ESS_LEAVE_APPLY_TB.APPLY_GROUP_NO,LEAVE_NO, LEAVE_EMPID, CHINESENAME,HR_DEPARTMENT.DEPTNAME, LEAVE_TYPE, SY_CODE.CODE_NAME,SY_CODE.CODE_EN_NAME , "
				+ "ESS_LEAVE_APPLY_TB.CREATE_DATE, LEAVE_FROM_TIME, LEAVE_TO_TIME, LEAVE_REASON, "
				+ "ESS_LEAVE_APPLY_TB.ACTIVITY,ESS_LEAVE_APPLY_TB.REMARK ,POSITION,POST,"
				+ "HR_EMPLOYEE_V.CHINESE_PINYIN,HR_EMPLOYEE_V.DEPT_EN_NAME,HR_EMPLOYEE_V.ENGLISHPOST ,HR_EMPLOYEE_V.ENGLISHPOSITION  "
				+ "ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY,APPLY_GROUP_NO,"
				+ "LEAVE_FROM_TIME DESC, LEAVE_EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.trim() + "%");
			pstmt.setString(2, key.trim() + "%");
			pstmt.setString(3, key.trim() + "%");
			pstmt.setString(4, key.trim() + "%");
			pstmt.setString(5, key.trim() + "%");
			pstmt.setString(6, sDate);
			pstmt.setString(7, eDate);
			pstmt.setString(8, sDate);
			pstmt.setString(9, eDate);
			pstmt.setString(10, sDate);
			pstmt.setString(11, eDate);
			// pstmt.setString(10, deptID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssLeaveBean essLeaveBean = new EssLeaveBean();
					essLeaveBean.setLeaveNo(rs.getInt("LEAVE_NO"));
					essLeaveBean.setApplyGroupSeq(rs.getInt("APPLY_GROUP_NO"));
					essLeaveBean.setEmpId(rs.getString("LEAVE_EMPID"));
					essLeaveBean.setChineseName(rs.getString("CHINESENAME"));
					essLeaveBean.setDeptName(rs.getString("DEPTNAME"));
					essLeaveBean.setCreateDate(rs.getString("CREATE_DATE"));
					essLeaveBean.setLeaveTypeCode(rs.getString("LEAVE_TYPE"));
					essLeaveBean.setPosition(rs.getString("POSITION"));
					essLeaveBean.setPost(rs.getString("POST"));
					essLeaveBean
							.setChinesePinYin(rs.getString("CHINESEPINYIN"));
					essLeaveBean.setDeptEnName(rs.getString("DEPTENNAME"));
					essLeaveBean.setEnPositon(rs.getString("ENGLISHPOSITION"));
					essLeaveBean.setEnPost(rs.getString("ENGLISHPOST"));
					essLeaveBean.setLeaveTypeEnName(rs
							.getString("LEAVE_TYPE_EN_NAME"));
					essLeaveBean.setLeaveTypeName(rs
							.getString("LEAVE_TYPE_NAME"));
					essLeaveBean.setLeaveFromTime(rs
							.getString("LEAVE_FROM_TIME"));
					essLeaveBean.setLeaveToTime(rs.getString("LEAVE_TO_TIME"));
					essLeaveBean.setLeaveReason(rs.getString("LEAVE_REASON"));
					essLeaveBean.setActivity(rs.getInt("ACTIVITY"));

					// 如果人事可以提前进行确认或者所有决裁全部完毕，可进行人事确认
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {

						// 申请状态为"待决裁"
						if (rs.getInt("ACTIVITY") == 0)
							// 可进行"通过"和"否决"
							essLeaveBean.setOpFlag(0);

						// 确认后可以重新进行确认并且申请状态为"已通过"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							// 可进行"否决"
							essLeaveBean.setOpFlag(2);

						// 确认后可以重新进行确认并且申请状态为"已否决"
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							// 可进行"通过"
							essLeaveBean.setOpFlag(1);
					}
					essLeaveBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("LEAVE_NO"),
									"TrainingApply"));
					essLeaveBean.setRemark(rs.getString("remark"));

					essLeaveBean.setLeaveThisMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "this"));
					essLeaveBean.setLeaveNextMonth(this.getTurnHolidays(
							essLeaveBean.getEmpId(), "next"));

					list.add(essLeaveBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出休假申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有漏卡申请记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getCardAffirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_CARD_APPLY_TB.APPLY_ID, ESS_CARD_APPLY_TB.APPLY_EMP_ID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_CARD_APPLY_TB.DOOR_TYPE, ESS_CARD_APPLY_TB.CREATED_BY, "
				+ "TO_CHAR(ESS_CARD_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_CARD_APPLY_TB.R_TIME, 'YYYY-MM-DD HH24:MI') CARD_TIME, "
				+ "ESS_CARD_APPLY_TB.APPLY_REASON, ESS_CARD_APPLY_TB.ACTIVITY, ESS_CARD_APPLY_TB.REMARK "
				+ "FROM ESS_CARD_APPLY_TB, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_CARD_APPLY_TB.APPLY_EMP_ID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_CARD_APPLY_TB.APPLY_ID = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_CARD_APPLY_TB.APPLY_EMP_ID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_CARD_APPLY_TB.R_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'NoCardApply') = 'NoCardApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'NoCardApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, CARD_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssCardApplyBean essCardApplyBean = new EssCardApplyBean();
					essCardApplyBean.setApplyID(String.valueOf(rs
							.getInt("APPLY_ID")));
					essCardApplyBean.setApplyEmp(rs.getString("APPLY_EMP_ID"));
					essCardApplyBean
							.setChineseName(rs.getString("CHINESENAME"));
					essCardApplyBean.setDeptName(rs.getString("DEPTNAME"));
					essCardApplyBean.setCreateDate(rs.getString("CREATE_DATE"));
					essCardApplyBean.setCardTime(rs.getString("CARD_TIME"));
					essCardApplyBean.setApplyReason(rs
							.getString("APPLY_REASON"));
					essCardApplyBean.setActivity(rs.getInt("ACTIVITY"));
					essCardApplyBean.setRemark(rs.getString("REMARK"));
					essCardApplyBean.setDoorType(rs.getString("DOOR_TYPE"));
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {
						// 上级决裁者通过, 且人事未否决时才能进行操作
						if (rs.getInt("AFFIRM_FLAG") == 0)
							essCardApplyBean.setOpFlag(0);
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							essCardApplyBean.setOpFlag(2);
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							essCardApplyBean.setOpFlag(1);
					}
					essCardApplyBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_ID"),
									"NoCardApply"));
					list.add(essCardApplyBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出当前登录者可以进行决裁的所有漏卡申请记录异常", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出漏卡申请记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.可否进行确认(最终决裁者的决裁状态)--已通过,未决裁,已否决
	 *            2.人事确认情况--未确认,已通过,已否决
	 */
	public List getCardConfirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_CARD_APPLY_TB.APPLY_ID, ESS_CARD_APPLY_TB.APPLY_EMP_ID, "
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_CARD_APPLY_TB.DOOR_TYPE,"
				+ "TO_CHAR(ESS_CARD_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_CARD_APPLY_TB.R_TIME, 'YYYY-MM-DD HH24:MI') CARD_TIME, "
				+ "ESS_CARD_APPLY_TB.APPLY_REASON, ESS_CARD_APPLY_TB.ACTIVITY, ESS_CARD_APPLY_TB.REMARK "
				+ "FROM ESS_CARD_APPLY_TB, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE ESS_CARD_APPLY_TB.APPLY_EMP_ID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_CARD_APPLY_TB.APPLY_ID = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_CARD_APPLY_TB.APPLY_EMP_ID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND (ESS_CARD_APPLY_TB.R_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'NoCardApply' ";

		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select count(*) from ess_affirm where apply_no = ESS_CARD_APPLY_TB.APPLY_ID and APPLY_TYPE = 'NoCardApply' and affirm_flag > 0))";
		}
		;
		sql += " GROUP BY APPLY_ID, APPLY_EMP_ID, CHINESENAME, DEPTNAME,"
				+ "ESS_CARD_APPLY_TB.CREATE_DATE,  APPLY_REASON, "
				+ "ESS_CARD_APPLY_TB.ACTIVITY, ESS_CARD_APPLY_TB.REMARK, "
				+ "ESS_CARD_APPLY_TB.DOOR_TYPE,ESS_CARD_APPLY_TB.R_TIME "
				+ "ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY, "
				+ "CARD_TIME DESC, APPLY_EMP_ID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssCardApplyBean essCardApplyBean = new EssCardApplyBean();
					essCardApplyBean.setApplyID(String.valueOf(rs
							.getInt("APPLY_ID")));
					essCardApplyBean.setApplyEmp(rs.getString("APPLY_EMP_ID"));
					essCardApplyBean
							.setChineseName(rs.getString("CHINESENAME"));
					essCardApplyBean.setDeptName(rs.getString("DEPTNAME"));
					essCardApplyBean.setCreateDate(rs.getString("CREATE_DATE"));
					essCardApplyBean.setCardTime(rs.getString("CARD_TIME"));
					essCardApplyBean.setApplyReason(rs
							.getString("APPLY_REASON"));
					essCardApplyBean.setActivity(rs.getInt("ACTIVITY"));
					essCardApplyBean.setRemark(rs.getString("REMARK"));
					essCardApplyBean.setDoorType(rs.getString("DOOR_TYPE"));
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {
						if (rs.getInt("ACTIVITY") == 0)
							essCardApplyBean.setOpFlag(0);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							essCardApplyBean.setOpFlag(2);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							essCardApplyBean.setOpFlag(1);
					}
					essCardApplyBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("APPLY_ID"),
									"NoCardApply"));
					essCardApplyBean.setRemark(rs.getString("REMARK"));
					list.add(essCardApplyBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出漏卡申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出当前登录者可以进行决裁的所有值班记录
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字 列表顺序: 1.人事确认情况--未确认,已通过,已否决 2.自己决裁情况--未决裁,已通过,已否决
	 *            3.该否自己决裁--应该,不该
	 */
	public List getMatchAffirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_MATCH_APPLY_TB.MATCH_NO, ESS_MATCH_APPLY_TB.MATCH_EMPID, NVL(DECODE(ESS_LAST.AFFIRM_FLAG, 0, 1.5, ESS_LAST.AFFIRM_FLAG), 1) LAST_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "ESS_MATCH_APPLY_TB.MATCH_TYPE, SY_CODE.CODE_NAME MATCH_TYPE_NAME, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD') MATCH_DATE, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD HH24:MI') MATCH_FROM_TIME, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_TO_TIME, 'YYYY-MM-DD HH24:MI') MATCH_TO_TIME, "
				+ "ESS_MATCH_APPLY_TB.ACTIVITY, ESS_MATCH_APPLY_TB.REMARK "
				+ "FROM ESS_MATCH_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM, ESS_AFFIRM ESS_LAST "
				+ "WHERE ESS_MATCH_APPLY_TB.MATCH_TYPE = SY_CODE.CODE_ID "
				+ "AND ESS_MATCH_APPLY_TB.MATCH_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_MATCH_APPLY_TB.MATCH_NO = ESS_AFFIRM.APPLY_NO "
				+ "AND ESS_AFFIRM.APPLY_NO = ESS_LAST.APPLY_NO(+) "
				+ "AND ESS_AFFIRM.AFFIRM_LEVEL = ESS_LAST.AFFIRM_LEVEL(+) + 1 "
				+ "AND (ESS_MATCH_APPLY_TB.MATCH_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND TO_DATE(TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND NVL(ESS_LAST.APPLY_TYPE, 'MatchApply') = 'MatchApply' "
				+ "AND ESS_AFFIRM.APPLY_TYPE = 'MatchApply' "
				+ "AND ESS_AFFIRM.AFFIRMOR_ID = ? ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag > 0)";

		}
		;
		sql += " ORDER BY ACTIVITY, ESS_AFFIRM.AFFIRM_FLAG, "
				+ "LAST_FLAG, MATCH_FROM_TIME DESC, EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setString(6, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssMatchBean essMatchBean = new EssMatchBean();
					essMatchBean.setMatchNo(rs.getInt("MATCH_NO"));
					essMatchBean.setEmpID(rs.getString("MATCH_EMPID"));
					essMatchBean.setChineseName(rs.getString("CHINESENAME"));
					essMatchBean.setDeptName(rs.getString("DEPTNAME"));
					essMatchBean.setMatchDate(rs.getString("MATCH_DATE"));
					essMatchBean.setMatchTypeCode(rs.getString("MATCH_TYPE"));
					essMatchBean.setMatchTypeName(rs
							.getString("MATCH_TYPE_NAME"));
					essMatchBean.setCreateDate(rs.getString("CREATE_DATE"));
					essMatchBean.setMatchFromTime(rs
							.getString("MATCH_FROM_TIME"));
					essMatchBean.setMatchToTime(rs.getString("MATCH_TO_TIME"));
					essMatchBean.setActivity(rs.getInt("ACTIVITY"));
					essMatchBean.setRemark(rs.getString("REMARK"));
					if (rs.getDouble("LAST_FLAG") == 1
							&& rs.getInt("ACTIVITY") != 2) {
						// 上级决裁者通过, 且人事未否决时才能进行操作
						if (rs.getInt("AFFIRM_FLAG") == 0)
							essMatchBean.setOpFlag(0);
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 1)
							essMatchBean.setOpFlag(2);
						else if (essSysparam.isLeaveModifiedAfterAffirm()
								&& rs.getInt("AFFIRM_FLAG") == 2)
							essMatchBean.setOpFlag(1);
					}
					essMatchBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("MATCH_NO"),
									"MatchApply"));
					list.add(essMatchBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出值班申请记录以进行人事确认
	 * 
	 * @param sDate
	 *            开始日期
	 * @param eDate
	 *            结束日期
	 * @param key
	 *            搜索关键字
	 */
	public List getMatchConfirmList(String sDate, String eDate, String key,
			int qryType) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String sql = "SELECT ESS_MATCH_APPLY_TB.MATCH_NO, ESS_MATCH_APPLY_TB.MATCH_EMPID, "
				+ "MAX(ESS_AFFIRM.AFFIRM_FLAG) MAX_FLAG, MIN(ESS_AFFIRM.AFFIRM_FLAG) MIN_FLAG, "
				+ "HR_EMPLOYEE.CHINESENAME, HR_DEPARTMENT.DEPTNAME, "
				+ "ESS_MATCH_APPLY_TB.MATCH_TYPE, SY_CODE.CODE_NAME MATCH_TYPE_NAME, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD') MATCH_DATE, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD HH24:MI') MATCH_FROM_TIME, "
				+ "TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_TO_TIME, 'YYYY-MM-DD HH24:MI') MATCH_TO_TIME, "
				+ "ESS_MATCH_APPLY_TB.ACTIVITY,ESS_MATCH_APPLY_TB.REMARK "
				+ "FROM ESS_MATCH_APPLY_TB, SY_CODE, HR_EMPLOYEE, HR_DEPARTMENT, ESS_AFFIRM "
				+ "WHERE ESS_MATCH_APPLY_TB.MATCH_TYPE = SY_CODE.CODE_ID "
				+ "AND ESS_MATCH_APPLY_TB.MATCH_EMPID = HR_EMPLOYEE.EMPID "
				+ "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
				+ "AND ESS_MATCH_APPLY_TB.MATCH_NO = ESS_AFFIRM.APPLY_NO(+) "
				+ "AND (ESS_MATCH_APPLY_TB.MATCH_EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ? OR HR_DEPARTMENT.DEPTNAME LIKE ?) "
				+ "AND TO_DATE(TO_CHAR(ESS_MATCH_APPLY_TB.MATCH_FROM_TIME, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(? || ' 23:59:59', 'YYYY-MM-DD HH24:MI:SS') "
				+ "AND ESS_AFFIRM.APPLY_TYPE(+) = 'MatchApply' ";
		if (qryType == 1) {// 全部决裁
			sql += " and 0 = (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag = 0)";
		}
		if (qryType == 2) {// 部分决裁
			sql += " and 0 < (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag = 0)"
					+ " and 0 < (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag > 0)";
		} else if (qryType == 3) {// 未决裁
			sql += " and (not ESS_AFFIRM.APPLY_NO is null and 0 = (select count(*) from ess_affirm where apply_no = ESS_MATCH_APPLY_TB.MATCH_NO and APPLY_TYPE = 'MatchApply' and affirm_flag > 0))";

		}
		;
		sql += " GROUP BY MATCH_NO, MATCH_EMPID, CHINESENAME, DEPTNAME, MATCH_TYPE, CODE_NAME, "
				+ "ESS_MATCH_APPLY_TB.CREATE_DATE, MATCH_FROM_TIME, MATCH_TO_TIME, "
				+ "ESS_MATCH_APPLY_TB.ACTIVITY,ESS_MATCH_APPLY_TB.REMARK "
				+ " ORDER BY DECODE(MAX_FLAG, 0, 1.5, MAX_FLAG), MIN_FLAG DESC, ACTIVITY, MATCH_FROM_TIME DESC, MATCH_EMPID";
		Logger.getLogger(getClass()).debug("sql : " + sql);
		Logger.getLogger(getClass()).debug("key : " + key);
		Logger.getLogger(getClass()).debug("sDate : " + sDate);
		Logger.getLogger(getClass()).debug("eDate : " + eDate);
		Logger.getLogger(getClass()).debug("adminId : " + adminId);
		Logger.getLogger(getClass()).debug(
				"PageSize : " + pageControl.getPageSize());
		Logger.getLogger(getClass()).debug(
				"CurrentPage : " + pageControl.getCurrentPage());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key + "%");
			pstmt.setString(2, key + "%");
			pstmt.setString(3, key + "%");
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (i >= (pageControl.getCurrentPage() - 1)
						* pageControl.getPageSize()
						&& i < pageControl.getCurrentPage()
								* pageControl.getPageSize()) {
					EssMatchBean essMatchBean = new EssMatchBean();
					essMatchBean.setMatchNo(rs.getInt("MATCH_NO"));
					essMatchBean.setEmpID(rs.getString("MATCH_EMPID"));
					essMatchBean.setChineseName(rs.getString("CHINESENAME"));
					essMatchBean.setDeptName(rs.getString("DEPTNAME"));
					essMatchBean.setMatchDate(rs.getString("MATCH_DATE"));
					essMatchBean.setMatchTypeCode(rs.getString("MATCH_TYPE"));
					essMatchBean.setMatchTypeName(rs
							.getString("MATCH_TYPE_NAME"));
					essMatchBean.setCreateDate(rs.getString("CREATE_DATE"));
					essMatchBean.setMatchFromTime(rs
							.getString("MATCH_FROM_TIME"));
					essMatchBean.setMatchToTime(rs.getString("MATCH_TO_TIME"));
					essMatchBean.setActivity(rs.getInt("ACTIVITY"));
					if (essSysparam.isPreConfirm()
							|| rs.getInt("MIN_FLAG") == 1
							&& rs.getInt("MAX_FLAG") == 1) {
						if (rs.getInt("ACTIVITY") == 0)
							essMatchBean.setOpFlag(0);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 1)
							essMatchBean.setOpFlag(2);
						else if (essSysparam.isReConfirm()
								&& rs.getInt("ACTIVITY") == 2)
							essMatchBean.setOpFlag(1);
					}
					essMatchBean.setAffirmorList((ArrayList) this
							.getAffirmorList(rs.getInt("MATCH_NO"),
									"MatchApply"));
					essMatchBean.setRemark(rs.getString("REMARK"));
					list.add(essMatchBean);
				}
				i++;
			}
			Logger.getLogger(getClass()).debug("Total Records Count : " + i);
			this.pageControl.setPageCount(i / this.pageControl.getPageSize()
					+ (i % this.pageControl.getPageSize() == 0 ? 0 : 1));
			Logger.getLogger(getClass()).debug(
					"PageCount : " + this.pageControl.getPageCount());
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取出值班申请记录以进行人事确认错误", e);
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 得到考勤月，开始日期，结束日期
	 * 
	 * @param essOverTimeBean
	 * @return
	 */
	public SimpleMap getArMonthDate(EssOverTimeBean essOverTimeBean) {

		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SimpleMap result = new SimpleMap();

		try {

			// 得到考勤月，开始日期，结束日期
			sql = " SELECT TO_CHAR(ar_get_startdate_empid(AR_MONTH, ?),'YYYY-MM-DD') AS STARTDATE, "
					+ " 	   TO_CHAR(ar_get_enddate_empid(AR_MONTH, ?),'YYYY-MM-DD') AS ENDDATE,AR_MONTH "
					+ " FROM DUAL,(SELECT get_armonth_empid(TO_DATE(?, 'YYYY-MM-DD'), ?) AS AR_MONTH FROM DUAL) T";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, essOverTimeBean.getPerson_id());
			pstmt.setString(3, essOverTimeBean.getOtDate());
			pstmt.setString(4, essOverTimeBean.getPerson_id());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result.setString("arMonth", rs.getString("AR_MONTH"));
				result.setString("arStartDate", rs.getString("STARTDATE"));
				result.setString("arEndDate", rs.getString("ENDDATE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());

		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}

		return result;
	}

	/*
	 * 得到员工当月已经申请的加班小时数
	 * 
	 * String paramType('arMonth','arDate')
	 */
	public double getPersonOtTime(EssOverTimeBean essOverTimeBean,
			SimpleMap arMonthObj, String paramType) {
		double applyOtHours = 0.0; // 已加班小时数,ess和考勤明细,结合的加班小时数
		double otHours = 0.0; // 加班小时数,考勤明细信息
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {

			// 取出加班小时数
			sql = " SELECT SUM(CASE WHEN NVL(AR.QUANTITY,-1) >= 0 THEN  AR.QUANTITY ELSE NVL(APPLY.OT_LENGTH,0) END ) OT_SUMAPPLY_OT_SUM,SUM(NVL(AR.QUANTITY,0)) AS OT_SUM "
					+ "   FROM "
					+ "       (SELECT ESS.APPLY_DATE,ESS.EMPID,SUM(CASE ESS.APPLY_TYPE WHEN 'WorkingOtType01' THEN (ESS.TO_TIME - ESS.FROM_TIME) * 24 ELSE ESS.OT_LENGTH END) AS OT_LENGTH "
					+ "          FROM AR_APPLY_RESULT ESS "
					+ "         WHERE ESS.EMPID = ? AND ESS.APPLY_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') "
					+ "         GROUP BY ESS.APPLY_DATE, ESS.EMPID) APPLY, "
					+ "       (SELECT TO_DATE(AD.AR_DATE_STR,'YYYY-MM-DD') AS AR_DATE_STR,AD.EMPID, SUM(AD.QUANTITY) AS QUANTITY "
					+ "          FROM AR_DETAIL AD "
					+ "         WHERE AD.EMPID = ? AND AD.AR_MONTH_STR=? "
					+ "           AND EXISTS (SELECT * FROM AR_ITEM T WHERE AD.AR_ITEM_NO = T.ITEM_NO AND T.ITEM_GROUP_CODE= ? ) "
					+ "         GROUP BY AD.AR_DATE_STR,AD.EMPID) AR, "
					+ "        (SELECT AC.DDATE FROM AR_CALENDER AC WHERE AC.DDATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') AND AC.CPNY_ID=? ) AR_DATE "
					+ "   WHERE AR_DATE.DDATE = APPLY.APPLY_DATE(+) AND AR_DATE.DDATE = AR.AR_DATE_STR(+) ";

			Logger.getLogger(getClass()).debug(
					"Get Overtime Hours From AR_DETAIL : " + sql);
			Logger.getLogger(getClass()).debug(
					"Get arStartDate : " + arMonthObj.getString("arStartDate"));
			Logger.getLogger(getClass()).debug(
					"Get arEndDate : " + arMonthObj.getString("arEndDate"));
			Logger.getLogger(getClass()).debug(
					"Get arMonth : " + arMonthObj.getString("arMonth"));
			Logger.getLogger(getClass()).debug(
					"Get OtDate : " + essOverTimeBean.getOtDate());
			Logger.getLogger(getClass()).debug(
					"Get empId : " + essOverTimeBean.getPerson_id());
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, arMonthObj.getString("arStartDate"));

			if (paramType.equals("arMonth")) {
				pstmt.setString(3, arMonthObj.getString("arEndDate"));
			} else {
				pstmt.setString(3, essOverTimeBean.getOtDate());
			}

			pstmt.setString(4, essOverTimeBean.getPerson_id());
			pstmt.setString(5, arMonthObj.getString("arMonth"));
			pstmt.setString(6, Configuration.getInstance().getString(
					"/configuration/em2/ar-item-shortname/overtime", ""));
			pstmt.setString(7, arMonthObj.getString("arStartDate"));

			if (paramType.equals("arMonth")) {
				pstmt.setString(8, arMonthObj.getString("arEndDate"));
			} else {
				pstmt.setString(8, essOverTimeBean.getOtDate());
			}

			pstmt.setString(9, ApplicationContext.getTheadLocal().getCpnyId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				applyOtHours += rs.getDouble(1);
				otHours += rs.getDouble(2);

			}

			// 取出ess中，未确认和未被否决的申请加班小时数
			sql = "SELECT NVL(SUM( CASE ESS.APPLY_OT_TYPE_CODE WHEN 'WorkingOtType01' THEN (TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG,'YYYY-MM-DD ') || OT_TO_TIME,'YYYY-MM-DD HH24:MI') - "
					+ " TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME,'YYYY-MM-DD HH24:MI')) * 24 ELSE ESS.OT_LENGTH END - OT_DEDUCT_TIME),0)"
					+ " FROM (SELECT E.APPLY_OT_NO, E.APPLY_OT_DATE,E.APPLY_OT_FLAG,E.OT_TO_TIME,E.OT_FROM_TIME,E.OT_LENGTH,E.APPLY_OT_TYPE_CODE,E.OT_DEDUCT_TIME,E.ACTIVITY FROM ESS_APPLY_OT E WHERE EMPID = ?) ESS "
					+ " WHERE APPLY_OT_DATE BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD') "
					+ " AND ACTIVITY = 0 ";
			Logger.getLogger(getClass()).debug(
					"Get Overtime Hours From UnConfirmed Apply : " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, arMonthObj.getString("arStartDate"));

			if (paramType.equals("arMonth")) {
				pstmt.setString(3, arMonthObj.getString("arEndDate"));
			} else {
				pstmt.setString(3, essOverTimeBean.getOtDate());
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				applyOtHours += rs.getDouble(1);
			}

			Logger.getLogger(getClass()).debug("otHours : " + otHours);

			essOverTimeBean.setAppply_ot(applyOtHours);
			essOverTimeBean.setOtTotal(otHours);

			// Logger.getLogger(getClass()).debug("otHours : " + otHours);

			// otHours -= essOverTimeBean.getOtDeduct();

			Logger.getLogger(getClass()).debug("applyHours : " + applyOtHours);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());

		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return applyOtHours;
	}

	/*
	 * 得到员工当月的加班上限
	 */
	public int getOtMaxHours(EssOverTimeBean essOverTimeBean,
			SimpleMap arMonthObj) {
		int maxHours = 0; // 限制小时数
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {

			// 得到加班上限
			sql = " SELECT OT.EMPID, OT.LIMIT_OT FROM AR_OVERTIME_PLAN OT WHERE OT.EMPID = ? AND (? BETWEEN OT.START_MONTH AND  OT.END_MONTH)";
			Logger.getLogger(getClass()).debug("sql : " + sql);
			Logger.getLogger(getClass()).debug(
					"Get Overtime EmpId : " + essOverTimeBean.getPerson_id());
			Logger.getLogger(getClass()).debug(
					"Get Overtime PlanMonth : "
							+ arMonthObj.getString("arMonth"));

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, essOverTimeBean.getPerson_id());
			pstmt.setString(2, arMonthObj.getString("arMonth"));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxHours = rs.getInt("LIMIT_OT");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return maxHours;
	}

	/**
	 * 进行考勤决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam
	 *            []
	 * @param AffirmType
	 *            申请类型 ot/leave/match/card
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doAffirmList(EssAffirmParam[] params, String ApplyType, int flag) {
		int result = 0;
		List<EssAffirmParam> paramList = new ArrayList<EssAffirmParam>();
		for (int i = 0; i < params.length; i++) {
			EssAffirmParam p = params[i];

			result = doAffirm(p, flag, ApplyType, paramList);
			try {
				if ("ot".equals(ApplyType))
					doUpdateApply_Ot(p);
				else if ("leave".equals(ApplyType))
					doUpdateApply_Leave(p);
			} catch (Exception e) {
				result = 0;
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			}
		}
		if (paramList.size() > 0 && flag == 1) {
			this.sendMailForConfirm(paramList, ApplyType);
		}
		return result;
	}

	/**
	 * 进行考勤修改功能决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam
	 *            []
	 * @param AffirmType
	 *            申请类型 无
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doArModifyAffirmList(EssAffirmParam[] params, int flag) {
		int result = 0;
		List<EssAffirmParam> paramList = new ArrayList<EssAffirmParam>();
		for (int i = 0; i < params.length; i++) {
			EssAffirmParam p = params[i];

			result = doArModifyAffirm(p, flag, paramList);
			// try
			// {
			// doUpdateApply_Ot(p);
			//				
			// }
			// catch(Exception e)
			// {
			// result = 0 ;
			// e.printStackTrace();
			// Logger.getLogger(getClass()).debug(e.toString());
			// }
		}
		// if(paramList.size()>0 && flag==1)
		// {
		// this.sendMailForConfirm(paramList, ApplyType);
		// }
		return result;
	}

	/**
	 * 更新加班申请信息
	 * 
	 * @param param
	 * @param AffirmType
	 * @param flag
	 * @return
	 */
	private void doUpdateApply_Ot(EssAffirmParam param) throws SQLException {
		String sql = "UPDATE ESS_APPLY_OT SET OT_FROM_TIME=?,OT_TO_TIME=? ,APPLY_OT_FLAG=NVL(TO_DATE(?,'YYYY-MM-DD')-APPLY_OT_DATE,0) ,OT_DEDUCT_TIME=?, OT_LENGTH = ?,UPDATE_DATE = SYSDATE, UPDATED_BY = ? WHERE APPLY_OT_NO = ?";
		SqlParameter[] sqlParams = new SqlParameter[7];
		sqlParams[0] = new SqlParameter(ParameterType.STRING, param
				.getFromTime());
		sqlParams[1] = new SqlParameter(ParameterType.STRING, param.getToTime());
		sqlParams[2] = new SqlParameter(ParameterType.STRING, param.getToDate());
		sqlParams[3] = new SqlParameter(ParameterType.STRING, param
				.getDeductTime());
		// sqlParams[4] = new SqlParameter(ParameterType.STRING,
		// param.getRemark());
		sqlParams[4] = new SqlParameter(ParameterType.DOUBLE, param
				.getOtLength());
		sqlParams[5] = new SqlParameter(ParameterType.STRING, adminId);
		sqlParams[6] = new SqlParameter(param.getApplyNo());
		JdbcUtil.executeUpdate(sql, sqlParams);

		// 同时更新中间状态表的申请 －－ hanguoshun
		String ar_app_result_SQL = " UPDATE AR_APPLY_RESULT AAT SET "
				+ " AAT.APPLY_DATE =(SELECT EAO.APPLY_OT_DATE - EAO.OT_NIGHT_FLAG FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = AAT.APPLY_NO AND EAO.EMPID = AAT.EMPID AND EAO.APPLY_OT_TYPE_CODE = AAT.APPLY_TYPE AND AAT.APPLY_NO = ?), "
				+ " AAT.FROM_TIME  =(SELECT TO_DATE(TO_CHAR(EAO.APPLY_OT_DATE,'YYYY-MM-DD ') || EAO.OT_FROM_TIME, 'YYYY-MM-DD HH24:MI') FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = AAT.APPLY_NO AND EAO.EMPID = AAT.EMPID AND EAO.APPLY_OT_TYPE_CODE = AAT.APPLY_TYPE AND AAT.APPLY_NO = ?), "
				+ " AAT.TO_TIME    =(SELECT TO_DATE(TO_CHAR(EAO.APPLY_OT_DATE + EAO.APPLY_OT_FLAG, 'YYYY-MM-DD ') || EAO.OT_TO_TIME, 'YYYY-MM-DD HH24:MI') FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = AAT.APPLY_NO AND EAO.EMPID = AAT.EMPID AND EAO.APPLY_OT_TYPE_CODE = AAT.APPLY_TYPE AND AAT.APPLY_NO = ?), "
				+ " AAT.APPLY_DEDUCT = (SELECT EAO.OT_DEDUCT_TIME FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = AAT.APPLY_NO AND EAO.EMPID = AAT.EMPID AND EAO.APPLY_OT_TYPE_CODE = AAT.APPLY_TYPE AND AAT.APPLY_NO = ?), "
				+ " AAT.OT_LENGTH = (SELECT EAO.OT_LENGTH FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = AAT.APPLY_NO AND EAO.EMPID = AAT.EMPID AND EAO.APPLY_OT_TYPE_CODE = AAT.APPLY_TYPE AND AAT.APPLY_NO = ?) "
				+ " WHERE AAT.APPLY_NO = (SELECT EAO.APPLY_OT_NO FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = ?) AND AAT.APPLY_TYPE = (SELECT EAO.APPLY_OT_TYPE_CODE FROM ESS_APPLY_OT EAO WHERE EAO.APPLY_OT_NO = ?) ";

		SqlParameter[] sqlParam = new SqlParameter[7];
		sqlParam[0] = new SqlParameter(param.getApplyNo());
		sqlParam[1] = new SqlParameter(param.getApplyNo());
		sqlParam[2] = new SqlParameter(param.getApplyNo());
		sqlParam[3] = new SqlParameter(param.getApplyNo());
		sqlParam[4] = new SqlParameter(param.getApplyNo());
		sqlParam[5] = new SqlParameter(param.getApplyNo());
		sqlParam[6] = new SqlParameter(param.getApplyNo());

		JdbcUtil.executeUpdate(ar_app_result_SQL, sqlParam);

	}

	/**
	 * 更新请假申请信息
	 * 
	 * @param affirmNo
	 * @param remark
	 * @param flag
	 * @return
	 */
	private void doUpdateApply_Leave(int applyNo, String remark)
			throws SQLException {
		String sql = "UPDATE ESS_LEAVE_APPLY_TB SET REMARK=?,UPDATE_DATE = SYSDATE, UPDATED_BY = ? WHERE LEAVE_NO = ?";
		SqlParameter[] sqlParams = new SqlParameter[3];
		sqlParams[0] = new SqlParameter(ParameterType.STRING, remark);
		sqlParams[1] = new SqlParameter(ParameterType.STRING, adminId);
		sqlParams[2] = new SqlParameter(applyNo);
		JdbcUtil.executeUpdate(sql, sqlParams);
	}

	/**
	 * 进行考勤决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	private int doAffirm(EssAffirmParam p, int flag, String ApplyType,
			List<EssAffirmParam> paramList) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		int result = 0;
		int affirmNo = p.getAffirmNo();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE ESS_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATED_BY = ? ,AFFIRM_REMARKS  = ? WHERE ESS_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, p.getRemark());
			pstmt.setInt(4, affirmNo);
			result = pstmt.executeUpdate();

			if (result == 1 && flag == 2) {
				this.doConfirm(affirmNo, flag);
			}
			if (result == 1 && flag == 1 && ApplyType.equals("ot")
					&& essSysparam.isOtAutoConfirm()
					&& this.isLastAffirmor(affirmNo)) {
				this.doConfirm(affirmNo, flag);
			}
			if (result == 1 && flag == 1 && ApplyType.equals("leave")
					&& essSysparam.isLeaveAutoConfirm()
					&& this.isLastAffirmor(affirmNo)) {
				this.doConfirm(affirmNo, flag);
			}
			if (result == 1 && flag == 1 && ApplyType.equals("leave")
					&& this.isLastAffirmor(affirmNo)
					&& !essSysparam.isLeaveAutoConfirm()) {

				paramList.add(p);
				// this.sendMailForConfirm(p,"leave");

			}
			if (result == 1 && flag == 1 && ApplyType.equals("ot")
					&& this.isLastAffirmor(affirmNo)
					&& !essSysparam.isOtAutoConfirm()) {

				paramList.add(p);
				// this.sendMailForConfirm(p,"ot");

			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 进行考勤修改功能 决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	private int doArModifyAffirm(EssAffirmParam p, int flag,
			List<EssAffirmParam> paramList) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		int result = 0;
		int affirmNo = p.getAffirmNo();
		int applyNo = p.getApplyNo();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE AR_DETAIL_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ? ,AFFIRM_REMARKS  = ? WHERE AR_DETAIL_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, p.getRemark());
			pstmt.setInt(4, affirmNo);
			result = pstmt.executeUpdate();

			if (result == 1 && flag == 2) {
				this.doArDetailConfirm(applyNo, flag, true);
			}
			if (result == 1 && flag == 1
					&& this.isArModifyLastAffirmor(affirmNo)) {
				this.doArDetailConfirm(applyNo, flag, true);
			}
			if (result == 1 && flag == 1
					&& !this.isArModifyLastAffirmor(affirmNo)) {
				this.doArDetailConfirm(applyNo, flag, false);
			}
			// if(result == 1 && flag==1 && ApplyType.equals("leave") &&
			// essSysparam.isLeaveAutoConfirm() &&
			// this.isLastAffirmor(affirmNo)){
			// this.doConfirm(affirmNo, flag);
			// }
			// if(result == 1 && flag==1 && ApplyType.equals("leave") &&
			// this.isLastAffirmor(affirmNo) &&
			// !essSysparam.isLeaveAutoConfirm()){
			//            	
			// paramList.add(p);
			// //this.sendMailForConfirm(p,"leave");
			//            	
			// }
			// if(result == 1 && flag==1 && ApplyType.equals("ot") &&
			// this.isLastAffirmor(affirmNo) && !essSysparam.isOtAutoConfirm()){
			//            	
			// paramList.add(p);
			// //this.sendMailForConfirm(p,"ot");
			//            	
			// }
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 进行人事确认提醒
	 * 
	 */
	private void sendMailForConfirm(List<EssAffirmParam> paramList,
			String ApplyType) {

		AdminBean admin = ApplicationContext.getTheadLocal();
		// String url = "http://doodream.corp.doosan.com/portal/server.pt" ;
		// String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
		// String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
		// AD登录验证
		// String url = "http://portal.doosan.com" ;
		String url = "http://10.40.128.28:8083/";
		Connection conn = ConnBean.getConn();
		Statement ps = null;
		ResultSet rs = null;

		PreparedStatement pst = null;
		ResultSet prs = null;
		String cpnyAllNameForMail = "";
		String cpnyId = admin.getCpnyId();

		if (cpnyId.equals("78000000")) {
			cpnyAllNameForMail = "斗山工程机械(中国)有限公司";
		} else if (cpnyId.equals("63000000")) {
			cpnyAllNameForMail = "斗山工程机械（山东）有限公司";
		} else if (cpnyId.equals("61000000")) {
			cpnyAllNameForMail = "斗山工程机械（苏州）有限公司";
		} else if (cpnyId.equals("59000000")) {
			cpnyAllNameForMail = "斗山（中国）投资有限公司";
		} else if (cpnyId.equals("60000000")) {
			cpnyAllNameForMail = "斗山机床（烟台）有限公司";
		} else {
			cpnyAllNameForMail = "斗山工程机械";
		}

		String sql1 = "(SELECT A.SCREEN_GRANT_NO FROM SY_LOGIN_SCREEN A WHERE A.SCREEN_GRANT_NAME = '考勤担当'"
				+ " AND   A.CPNY_ID = (SELECT HR.CPNY_ID FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = "
				+ adminId + "))";

		String sql2 = " SELECT SY.ADMINID, HR.EMAIL, HR.EMPID, HR.CHINESENAME"
				+ " FROM SY_ADMIN SY,HR_EMPLOYEE HR"
				+ " WHERE SY.ADMINID=HR.PERSON_ID" + " AND HR.STATUS_CODE <> 'EmpStatus3' AND HR.CPNY_ID='"
				+ admin.getCpnyId() + "'"
				+ " AND  INSTR((',' || screen_grant_no || ','),','||" + sql1
				+ "||',')>1";

		String sql3 = " SELECT B.CHINESENAME CHINESENAME, TO_CHAR(A.CREATE_DATE,'YYYY-MM-DD HH:MI') CREATE_DATE, "
				+ " GET_CODE_NAME(A.APPLY_OT_TYPE_CODE) APPLY_TYPE FROM ESS_APPLY_OT A, HR_EMPLOYEE B "
				+ " WHERE A.EMPID = B.PERSON_ID AND   A.APPLY_OT_NO  = ? ";

		String sql4 = " SELECT B.CHINESENAME CHINESENAME, TO_CHAR(A.CREATE_DATE,'YYYY-MM-DD HH:MI') CREATE_DATE, "
				+ " TO_CHAR(A.LEAVE_FROM_TIME,'YYYY-MM-DD HH:MI') FROM_DATE, TO_CHAR(A.LEAVE_TO_TIME,'YYYY-MM-DD HH:MI') TO_DATE, "
				+ " GET_CODE_NAME(A.LEAVE_TYPE) APPLY_TYPE, A.LEAVE_REASON REASON FROM ESS_LEAVE_APPLY_TB A, HR_EMPLOYEE B "
				+ " WHERE A.LEAVE_EMPID = B.PERSON_ID AND   A.LEAVE_NO  = ? ";

		try {
			ps = conn.createStatement();
			rs = ps.executeQuery(sql2);
			List<SimpleMap> para = new ArrayList<SimpleMap>();
			while (rs.next()) {
				SimpleMap simpleMap = new SimpleMap();
				simpleMap.setString("name", rs.getString("CHINESENAME"));
				simpleMap.setString("mail", rs.getString("EMAIL"));
				para.add(simpleMap);
			}

			List<SimpleMap> mailContent = new ArrayList<SimpleMap>();
			if (ApplyType.equals("ot")) {

				Iterator iter = paramList.iterator();
				for (; iter.hasNext();) {

					EssAffirmParam eap = (EssAffirmParam) iter.next();
					pst = conn.prepareStatement(sql3);
					pst.setInt(1, eap.getApplyNo());
					prs = pst.executeQuery();
					if (prs.next()) {
						SimpleMap simpleMap = new SimpleMap();
						simpleMap.setString("name", prs
								.getString("CHINESENAME"));
						simpleMap.setString("createDate", prs
								.getString("CREATE_DATE"));
						simpleMap.setString("applyType", prs
								.getString("APPLY_TYPE"));
						simpleMap.setString("from", eap.getFromDate() + " "
								+ eap.getFromTime());
						simpleMap.setString("to", eap.getToDate() + " "
								+ eap.getToTime());
						mailContent.add(simpleMap);
					}
				}

			} else if (ApplyType.equals("leave")) {

				Iterator iter = paramList.iterator();
				for (; iter.hasNext();) {

					EssAffirmParam eap = (EssAffirmParam) iter.next();
					pst = conn.prepareStatement(sql4);
					pst.setInt(1, eap.getApplyNo());
					prs = pst.executeQuery();
					if (prs.next()) {
						SimpleMap simpleMap = new SimpleMap();
						simpleMap.setString("name", prs
								.getString("CHINESENAME"));
						simpleMap.setString("createDate", prs
								.getString("CREATE_DATE"));
						simpleMap.setString("applyType", prs
								.getString("APPLY_TYPE"));
						simpleMap.setString("from", prs.getString("FROM_DATE"));
						simpleMap.setString("to", prs.getString("TO_DATE"));
						simpleMap.setString("reason", prs.getString("REASON"));
						mailContent.add(simpleMap);
					}
				}
			}

			Iterator iter = para.iterator();
			for (; iter.hasNext();) {
				SimpleMap inputData = new SimpleMap();
				StringBuffer content = new StringBuffer();
				SimpleMap sm = (SimpleMap) iter.next();
				if (sm.getString("mail") != null
						&& !sm.getString("mail").equals("")) {

					if (ApplyType.equals("ot")) {

						for (Iterator iters = mailContent.iterator(); iters
								.hasNext();) {

							SimpleMap map = (SimpleMap) iters.next();
							content
									.append(" 申请人：")
									.append(map.getString("name"))
									.append("<br><br>")
									.append(" 主题：")
									.append(map.getString("applyType"))
									.append("<br><br>")
									.append(" 申请时间：")
									.append(map.getString("createDate"))
									.append("<br><br>")
									.append(" 开始时间：")
									.append(map.getString("from"))
									.append("<br><br>")
									.append(" 结束时间：")
									.append(map.getString("to"))
									.append("<br><br>")
									.append(
											"-------------------------------------------------------------------------------")
									.append("<br><br>");
						}
						content.append("<br><br>").append("<a href=\"").append(
								url).append("\" target=\"_blank\">点击此处登陆</a>")
								.append("<br><br>" + cpnyAllNameForMail);
						inputData.setString("EMAIL_TITLE", "加班申请确认");

					} else if (ApplyType.equals("leave")) {

						for (Iterator iters = mailContent.iterator(); iters
								.hasNext();) {

							SimpleMap map = (SimpleMap) iters.next();
							content
									.append(" 申请人：")
									.append(map.getString("name"))
									.append("<br><br>")
									.append(" 主题：")
									.append(map.getString("applyType"))
									.append("<br><br>")
									.append(" 申请时间：")
									.append(map.getString("createDate"))
									.append("<br><br>")
									.append(" 开始时间：")
									.append(map.getString("from"))
									.append("<br><br>")
									.append(" 结束时间：")
									.append(map.getString("to"))
									.append("<br><br>")
									.append(" 休假原因：")
									.append(map.getString("reason"))
									.append("<br><br>")
									.append(
											"-------------------------------------------------------------------------------")
									.append("<br><br>");
						}
						content.append("<br><br>").append("<a href=\"").append(
								url).append("\" target=\"_blank\">点击此处登陆</a>")
								.append("<br><br>" + cpnyAllNameForMail);
						inputData.setString("EMAIL_TITLE", "休假申请确认");
					}

					// set email content
					inputData.setString("EMAIL_CONTNT", content.toString());
					// set email type
					inputData.setString("EMAIL_TP", "H");

					inputData
							.setString("RCVR_EMAIL_ADDR", sm.getString("mail"));

					// set biz type is attendance
					inputData.setString("BIZ_TP", "A");
					// set mail status
					inputData.setString("SND_STAT", "N");

					new Mail().sendMail(inputData);
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(prs, pst);
			SqlUtil.close(rs, ps, conn);

		}

	}

	/**
	 * 进行人事确认
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果 1 通过; 2 否决
	 */
	public int doConfirm(int affirmNo, int flag) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT APPLY_NO, APPLY_TYPE FROM ESS_AFFIRM WHERE ESS_AFFIRM_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, affirmNo);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = this.doConfirm(rs.getInt("APPLY_NO"), rs
						.getString("APPLY_TYPE"), flag, "");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	/**
	 * 进行DICC支社考勤人事确认
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果 1 通过; 2 否决
	 */
	public int doAttendanceConfirm(int affirmNo, int flag) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		sql = "update ar_detail t set t.ar_flag = ? where t.pk_no = ? ";
		try {
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setInt(2, affirmNo);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	/**
	 * 进行DICC考勤人事确认
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 * @param flag
	 *            决裁结果 1 通过; 2 否决
	 */
	public int doArDetailConfirm(int affirmNo, int flag, boolean LastAffirmor) {
		int result = 0;
		int result1 = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		String sql1 = "";
		sql = "UPDATE AR_DETAIL T SET T.FLAG = ? WHERE T.PK_NO = ? ";
		sql1 = "UPDATE AR_DETAIL_BACK T SET T.FLAG = ? WHERE T.AR_PK_NO = ? ";
		String sql2 = "UPDATE AR_DETAIL T SET (T.AR_ITEM_NO, T.QUANTITY, T.LOCK_YN, T.MOD_DATE, T.UNIT, T.FLAG) = "
				+ "(SELECT B.AR_ITEM_NO, B.QUANTITY, B.LOCK_YN, SYSDATE, B.UNIT, 2 FROM AR_DETAIL_BACK B WHERE B.AR_PK_NO = ? AND B.FLAG = 0 ) WHERE T.PK_NO = ? AND T.FLAG = 0 ";

		try {
			if (flag == 2) {

				Logger.getLogger(getClass()).debug(sql2);
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, affirmNo);
				pstmt.setInt(2, affirmNo);
				result = pstmt.executeUpdate();

				Logger.getLogger(getClass()).debug(sql1);
				pst = conn.prepareStatement(sql1);
				pst.setInt(1, flag);
				pst.setInt(2, affirmNo);
				result1 = pst.executeUpdate();

			}
			if (flag == 1 && LastAffirmor) {

				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, flag);
				pstmt.setInt(2, affirmNo);

				result = pstmt.executeUpdate();

				Logger.getLogger(getClass()).debug(sql1);
				pst = conn.prepareStatement(sql1);
				pst.setInt(1, flag);
				pst.setInt(2, affirmNo);

				result1 = pst.executeUpdate();

			}
			// if( flag == 1 && !LastAffirmor){
			//				
			// Logger.getLogger(getClass()).debug(sql);
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, flag);
			// pstmt.setInt(2, affirmNo);
			//				
			// result = pstmt.executeUpdate();
			//				
			// Logger.getLogger(getClass()).debug(sql1);
			// pst = conn.prepareStatement(sql1);
			// pst.setInt(1, flag);
			// pst.setInt(2, affirmNo);
			//				
			// result1 = pst.executeUpdate();
			//				
			// }

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	/**
	 * 进行人事确认
	 * 
	 * @param applyNo
	 *            考勤申请序号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply/"NoCardApply""
	 * @param flag
	 *            确认结果 0 未确认; 1 通过; 2 否决
	 */
	public int doConfirm(int applyNo, String applyType, int flag, String remark) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			if (applyType.equalsIgnoreCase("OtApply"))
				sql = "UPDATE ESS_APPLY_OT SET ACTIVITY = ?, UPDATE_DATE = SYSDATE, UPDATED_BY = ?, REMARK=? WHERE APPLY_OT_NO = ? and ACTIVITY<>?";
			else if (applyType.equalsIgnoreCase("LeaveApply"))
				sql = "UPDATE ESS_LEAVE_APPLY_TB SET ACTIVITY = ?, UPDATE_DATE = SYSDATE, UPDATED_BY = ?, REMARK=? WHERE LEAVE_NO = ? and ACTIVITY<>?";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, flag);
			pstmt.setString(2, adminId);
			pstmt.setString(3, remark);
			pstmt.setInt(4, applyNo);
			pstmt.setInt(5, flag);
			result = pstmt.executeUpdate();
			if (result == 1) {
				this.applyToAr(applyNo, applyType, flag);
			}
			// if(result == 1 && flag == 1){
			// this.essLeaveDeptNo(applyNo);//对DISD 按部门别增加勤态确认编码
			// }
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("人事确认错误[APPLY_OT_NO=]" + applyNo, e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 人事确认过程修改加班长度
	 * 
	 * @return
	 */
	public int doUpdateOtApplyAfterConfirm(EssOverTimeBean otBean) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = "UPDATE ESS_APPLY_OT  "
					+ "SET "
					+ "OT_FROM_TIME = ?, "
					+ "OT_TO_TIME = ?, "
					+ "APPLY_OT_FLAG = NVL(TO_DATE(?, 'YYYY-MM-DD') - APPLY_OT_DATE, 0), "
					+ "OT_DEDUCT_TIME = ?, " + "UPDATE_DATE = SYSDATE, "
					+ "UPDATED_BY = ? " + "WHERE APPLY_OT_NO = ? ";
			Logger.getLogger(getClass()).debug(sql);
			Logger.getLogger(getClass()).debug(otBean.getOtFromTime());
			Logger.getLogger(getClass()).debug(otBean.getOtToTime());
			Logger.getLogger(getClass()).debug(otBean.getOtToDate());
			Logger.getLogger(getClass()).debug(otBean.getOtDeduct() + "");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, otBean.getOtFromTime());
			pstmt.setString(2, otBean.getOtToTime());
			pstmt.setString(3, otBean.getOtToDate());
			pstmt.setDouble(4, otBean.getOtDeduct());
			pstmt.setString(5, adminId);
			pstmt.setInt(6, otBean.getOtNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("人事确认中修改加班长度错误[APPLY_OT_NO=]"
					+ otBean.getOtNo(), e);
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * 根据决裁情况更新考勤接口表
	 * 
	 * @param applyNo
	 *            考勤申请序号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"/"NoCardApply"
	 * @param flag
	 *            操作类型 : 0 未确认; 1 添加; 2 删除
	 */
	private int applyToAr(int applyNo, String applyType, int flag) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = null;
		if (applyType.equalsIgnoreCase("OtApply")) {
			sql = "DELETE FROM AR_APPLY_RESULT WHERE APPLY_NO = ? AND APPLY_DATE IS NOT NULL AND APPLY_DEDUCT IS NOT NULL";
		} else if (applyType.equalsIgnoreCase("LeaveApply")) {
			sql = "DELETE FROM AR_APPLY_RESULT WHERE APPLY_NO = ? AND APPLY_DATE IS NULL AND APPLY_DEDUCT IS NULL";
		} else {
			Logger.getLogger(getClass()).debug("applyType Error.......");
			return result;
		}
		Logger.getLogger(getClass()).debug(sql);
		try {
			conn.setAutoCommit(false);// 开启事物时设置sql自动提交为false
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applyNo);
			result = pstmt.executeUpdate();
			if (flag == 1) {
				if (applyType.equalsIgnoreCase("OtApply")) {
					sql = "INSERT INTO AR_APPLY_RESULT (APPLY_NO, EMPID, APPLY_DATE, APPLY_TYPE, FROM_TIME, TO_TIME, APPLY_DEDUCT,OT_LENGTH) SELECT APPLY_OT_NO, EMPID, APPLY_OT_DATE - OT_NIGHT_FLAG, APPLY_OT_TYPE_CODE, TO_DATE(TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD ') || OT_FROM_TIME, 'YYYY-MM-DD HH24:MI'), TO_DATE(TO_CHAR(APPLY_OT_DATE + APPLY_OT_FLAG, 'YYYY-MM-DD ') || OT_TO_TIME, 'YYYY-MM-DD HH24:MI'), OT_DEDUCT_TIME, OT_LENGTH FROM ESS_APPLY_OT WHERE APPLY_OT_NO = ?";
				} else if (applyType.equalsIgnoreCase("LeaveApply")) {
					sql = "INSERT INTO AR_APPLY_RESULT (APPLY_NO, EMPID, APPLY_TYPE, FROM_TIME, TO_TIME,APPLYLEAVETYPE) SELECT LEAVE_NO, LEAVE_EMPID, LEAVE_TYPE,LEAVE_FROM_TIME, LEAVE_TO_TIME,APPLYLEAVETYPE  FROM ESS_LEAVE_APPLY_TB WHERE LEAVE_NO = ?";
				} else {
					Logger.getLogger(getClass())
							.debug("applyType Error.......");
					return result;
				}
				Logger.getLogger(getClass()).debug(sql);
				pstmt = conn.prepareStatement(sql);
				if (applyType.equalsIgnoreCase("LeaveApply")) {

					// QPSS的休假申请SQL需要两个参数
					pstmt.setInt(1, applyNo);
					// pstmt.setInt(2, applyNo);
				} else {

					pstmt.setInt(1, applyNo);
				}

				result = pstmt.executeUpdate();
				conn.commit();// 提交
			}
			if (result == 1) {

				// 接口表有更新, 计算考勤明细
				this.calcArDetail(applyNo, applyType);
			}
		} catch (Exception e) {
			try {
				conn.rollback();// 如果异常则回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
		return result;
	}

	/**
	 * DISD 按部门别添加勤态编号
	 * 
	 * @param applyNo
	 *            考勤申请序号
	 * @param flag
	 *            操作类型 : 0 未确认; 1 添加; 2 删除
	 */
	public void essLeaveDeptNo(int applyNo) {
		int result = 0;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		String sql = null;

		sql = "update ess_leave_apply_tb t set t.disd_dept_report_id ="
				+ "(select to_char(sysdate, 'yyyy')||nvl((select  max(nvl( substr(t1.disd_dept_report_id,5,10),0)+1)  from ess_leave_apply_tb t1 "
				+ " where substr(t1.disd_dept_report_id,1,4) = to_char(sysdate,'yyyy')),1) from dual) where t.leave_no = ? ";

		// sql = "update ess_leave_apply_tb t "
		// +" set t.disd_dept_report_no =  (select case  when h.FOURTHDEPTID = '630G7000' then"
		// +"(select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                        from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                       where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630G7000')"
		//			                                     
		// +"                                      when h.FOURTHDEPTID = '630G6000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630G6000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630H1000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630H1000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630B1000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630B1000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630B4000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630B4000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630E1000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630E1000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630F1000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630F1000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630G1000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630G1000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630G3000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630G3000')"
		//			                                       
		// +"                                    when h.FOURTHDEPTID = '630G4000' then"
		// +"                                    (select nvl(max(t1.disd_dept_report_no), 0) + 1"
		// +"                                            from ess_leave_apply_tb t1,  HR_EMP_V H1"
		// +"                                           where t1.leave_empid = h1.person_id"
		// +"                                         and h1.FOURTHDEPTID = '630G4000')"
		//			                                       
		// +"                                  end as disd_dept_report_no"
		// +"                             from ess_leave_apply_tb t, HR_EMP_V H"
		// +"                            where t.leave_empid = h.person_id"
		// +"                              and h.Cpny_Id = '63000000' and t.leave_no = ?"
		// +"                              and (t.leave_type <> 'H4' OR t.leave_type <> 'H8' OR t.leave_type <> 'H13' OR"
		// +"                                  t.leave_type <> 'H14' OR t.leave_type <> 'H26' OR t.leave_type <> 'H9'))"
		// +"                            where t.leave_no = ? and t.disd_dept_report_no is null";

		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applyNo);
			// pstmt.setInt(2, applyNo);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}

	}

	/**
	 * 调用存储过程, 计算考勤明细
	 * 
	 * @param applyNo
	 *            考勤申请序号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"/"NoCardApply"
	 */
	private void calcArDetail(int applyNo, String applyType) {
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql = "";

		// 取得申请的开始结束时间

		if (applyType.equalsIgnoreCase("OtApply"))
			sql = "SELECT ESS_APPLY_OT.EMPID, "
					+ "TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD') FROM_DATE, "
					+ "TO_CHAR(APPLY_OT_DATE, 'YYYY-MM-DD') TO_DATE,HR_EMPLOYEE.CPNY_ID  "
					+ "FROM ESS_APPLY_OT, HR_EMPLOYEE "
					+ "WHERE APPLY_OT_NO = ? AND  ESS_APPLY_OT.EMPID=HR_EMPLOYEE.PERSON_ID ";
		else if (applyType.equalsIgnoreCase("LeaveApply"))
			sql = "SELECT LEAVE_EMPID EMPID, "
					+ "TO_CHAR(LEAVE_FROM_TIME, 'YYYY-MM-DD') FROM_DATE, "
					+ "TO_CHAR(LEAVE_TO_TIME, 'YYYY-MM-DD') TO_DATE,HR_EMPLOYEE.CPNY_ID "
					+ "FROM ESS_LEAVE_APPLY_TB,HR_EMPLOYEE  "
					+ "WHERE LEAVE_NO = ? AND HR_EMPLOYEE.PERSON_ID=ESS_LEAVE_APPLY_TB.LEAVE_EMPID ";
		else {
			Logger.getLogger(getClass()).debug("applyType Error........");
			return;
		}
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, applyNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String empId = rs.getString("EMPID");
				sql = "{CALL Ar_Detail_Cal(?, ?, ?, ?, ?, ?, ?)}";
				cstmt = conn.prepareCall(sql);
				cstmt.setString(1, rs.getString("FROM_DATE"));
				cstmt.setString(2, rs.getString("TO_DATE"));
				cstmt.setString(3, "EMP");
				cstmt.setString(4, rs.getString("CPNY_ID"));
				cstmt.setString(5, "");
				cstmt.setString(6, "");
				cstmt.setString(7, empId);
				cstmt.execute();// 计算考勤明晰

			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(cstmt);
			SqlUtil.close(rs, pstmt, conn);
		}
	}

	/**
	 * 判断是否是最后一级决裁者
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 */
	private boolean isLastAffirmor(int affirmNo) {
		Logger.getLogger(getClass()).debug(
				"Checking if this is the Last Affirmor........");
		boolean result = false;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT A.APPLY_NO " + "FROM ESS_AFFIRM A, ESS_AFFIRM B "
				+ "WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL "
				+ "AND A.APPLY_NO(+) = B.APPLY_NO "
				+ "AND A.APPLY_TYPE(+) = B.APPLY_TYPE "
				+ "AND B.ESS_AFFIRM_NO = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, affirmNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					Logger.getLogger(getClass()).debug("Is Last Affirmor !");
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	/**
	 * 判断DICC考勤修改功能 是否是最后一级决裁者
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 */
	private boolean isArModifyLastAffirmor(int affirmNo) {
		Logger.getLogger(getClass()).debug(
				"Checking if this is the Ar Modify Last Affirmor........");
		boolean result = false;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// String sql = "SELECT A.APPLY_NO " +
		// "FROM ESS_AFFIRM A, ESS_AFFIRM B " +
		// "WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL " +
		// "AND A.APPLY_NO(+) = B.APPLY_NO " +
		// "AND A.APPLY_TYPE(+) = B.APPLY_TYPE " + "AND B.ESS_AFFIRM_NO = ?";
		String sql = "SELECT A.AR_DETAIL_NO FROM AR_DETAIL_AFFIRM A, AR_DETAIL_AFFIRM B WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL AND A.AR_DETAIL_NO(+) = B.AR_DETAIL_NO  AND B.AR_DETAIL_AFFIRM_NO = ?";

		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, affirmNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1) == null) {
					Logger.getLogger(getClass()).debug("Is Last Affirmor !");
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

	/**
	 * 取出指定考勤申请记录的所有决裁者
	 * 
	 * @param applyNo
	 *            申请序号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"
	 */
	public List getAffirmorList(int applyNo, String applyType) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ESS_AFFIRM.ESS_AFFIRM_NO, ESS_AFFIRM.APPLY_NO, ESS_AFFIRM.AFFIRMOR_ID, TO_CHAR(ESS_AFFIRM.UPDATE_DATE,'YYYY-MM-DD HH24:MI') AS UPDATE_DATE, "
				+ "HR_EMPLOYEE.CHINESENAME,hr_employee.chinese_pinyin, hr_employee.koreanname, "
				+ "ESS_AFFIRM.AFFIRM_LEVEL, ESS_AFFIRM.AFFIRM_FLAG, ESS_AFFIRM.APPLY_TYPE, ESS_AFFIRM.AFFIRM_REMARKS "
				+ "FROM ESS_AFFIRM, HR_EMPLOYEE "
				+ "WHERE ESS_AFFIRM.AFFIRMOR_ID = HR_EMPLOYEE.Person_Id AND ESS_AFFIRM.APPLY_TYPE = ? "
				+ "AND ESS_AFFIRM.APPLY_NO = ? ORDER BY ESS_AFFIRM.AFFIRM_LEVEL";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, applyType);
			pstmt.setInt(2, applyNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor.setAffirmNo(rs.getInt("ESS_AFFIRM_NO"));
				essAffirmor.setApplyNo(rs.getInt("APPLY_NO"));
				essAffirmor.setAffirmorId(rs.getString("AFFIRMOR_ID"));
				essAffirmor.setUpdateDate(rs.getString("UPDATE_DATE"));

				essAffirmor.setAffirmorName(rs.getString("CHINESENAME"));

				essAffirmor.setAffirmorEnName(rs.getString("CHINESE_PINYIN"));
				essAffirmor.setAffirmorKorName(rs.getString("KOREANNAME"));

				essAffirmor.setAffirmLevel(rs.getInt("AFFIRM_LEVEL"));
				essAffirmor.setAffirmFlag(rs.getInt("AFFIRM_FLAG"));
				essAffirmor.setApplyType(rs.getString("APPLY_TYPE"));
				essAffirmor.setRemark(rs.getString("AFFIRM_REMARKS"));
				list.add(essAffirmor);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出指定考勤修改记录的所有决裁者
	 * 
	 */
	public List getArModifyAffirmorList(int applyNo) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// String sql =
		// "SELECT ESS_AFFIRM.ESS_AFFIRM_NO, ESS_AFFIRM.APPLY_NO, ESS_AFFIRM.AFFIRMOR_ID, TO_CHAR(ESS_AFFIRM.UPDATE_DATE,'YYYY-MM-DD HH24:MI') AS UPDATE_DATE, "
		// +
		// "HR_EMPLOYEE.CHINESENAME,hr_employee.chinese_pinyin, hr_employee.koreanname, "
		// +
		// "ESS_AFFIRM.AFFIRM_LEVEL, ESS_AFFIRM.AFFIRM_FLAG, ESS_AFFIRM.APPLY_TYPE, ESS_AFFIRM.AFFIRM_REMARKS "
		// + "FROM ESS_AFFIRM, HR_EMPLOYEE "
		// +
		// "WHERE ESS_AFFIRM.AFFIRMOR_ID = HR_EMPLOYEE.Person_Id AND ESS_AFFIRM.APPLY_TYPE = ? "
		// + "AND ESS_AFFIRM.APPLY_NO = ? ORDER BY ESS_AFFIRM.AFFIRM_LEVEL";
		String sql = "SELECT AR_DETAIL_AFFIRM.AR_DETAIL_AFFIRM_NO,"
				+ "        AR_DETAIL_AFFIRM.AR_DETAIL_NO,"
				+ "	       AR_DETAIL_AFFIRM.AFFIRMOR_ID,"
				+ "	       TO_CHAR(AR_DETAIL_AFFIRM.UPDATE_DATE, 'YYYY-MM-DD HH24:MI') AS UPDATE_DATE,"
				+ "	       HR_EMPLOYEE.CHINESENAME,"
				+ "	       hr_employee.chinese_pinyin,"
				+ "	       hr_employee.koreanname,"
				+ "	       AR_DETAIL_AFFIRM.AFFIRM_LEVEL,"
				+ "	       AR_DETAIL_AFFIRM.AFFIRM_FLAG,"
				+ "	       AR_DETAIL_AFFIRM.AFFIRM_REMARKS"
				+ "		   FROM AR_DETAIL_AFFIRM, HR_EMPLOYEE"
				+ "		   WHERE AR_DETAIL_AFFIRM.AFFIRMOR_ID = HR_EMPLOYEE.Person_Id"
				+ "		   AND AR_DETAIL_AFFIRM.AR_DETAIL_NO = ? "
				+ "		   ORDER BY AR_DETAIL_AFFIRM.AFFIRM_LEVEL ";
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, applyType);
			pstmt.setInt(1, applyNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor.setAffirmNo(rs.getInt("AR_DETAIL_AFFIRM_NO"));
				essAffirmor.setApplyNo(rs.getInt("AR_DETAIL_NO"));
				essAffirmor.setAffirmorId(rs.getString("AFFIRMOR_ID"));
				essAffirmor.setUpdateDate(rs.getString("UPDATE_DATE"));

				essAffirmor.setAffirmorName(rs.getString("CHINESENAME"));

				essAffirmor.setAffirmorEnName(rs.getString("CHINESE_PINYIN"));
				essAffirmor.setAffirmorKorName(rs.getString("KOREANNAME"));

				essAffirmor.setAffirmLevel(rs.getInt("AFFIRM_LEVEL"));
				essAffirmor.setAffirmFlag(rs.getInt("AFFIRM_FLAG"));
				// essAffirmor.setApplyType(rs.getString("APPLY_TYPE"));
				essAffirmor.setRemark(rs.getString("AFFIRM_REMARKS"));
				list.add(essAffirmor);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 取出指定员工指定申请类型的决裁者列表
	 * 
	 * @param empId
	 *            员工号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"
	 */
	public List getAffirmorList(String empId, String applyType,
			int affirmMaxLevel, boolean containsOwner) {
		List list = new ArrayList();
		List endlist = new ArrayList();
		Connection conn = ConnBean.getConn();
		Connection conn2 = ConnBean.getConn();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int affirmLevel = 1;
		int endaffirmLevel = 1;
		int ownerLevel = 0;
		int affirmMinLevel = 0;
		String sql = "";
		try {
			Logger.getLogger(getClass()).debug("applyType= " + applyType);
			// 决裁对象为员工号
			sql = " SELECT SY_AFFIRM_RELATION_TB.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME,HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL "
					+ " FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE, HR_EMPLOYEE HR_AFFIRMOR "
					+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.Person_Id "
					+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL <= "
					+ affirmMaxLevel
					+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL > "
					+ affirmMinLevel
					+ " AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id "
					+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID = ? "
					+ " AND HR_EMPLOYEE.Person_Id = ? "
					+ " ORDER BY AFFIRM_LEVEL";
			Logger.getLogger(getClass()).debug("SQL2: " + sql);
			Logger.getLogger(getClass()).debug("empId= " + empId);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, applyType);
			pstmt.setString(2, empId);
			rs = pstmt.executeQuery();
			if (!rs.next()) {// 决裁对象为部门
				sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID "
						+ " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = ? ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empId);
				rs = pstmt.executeQuery();
				Logger.getLogger(getClass()).debug("SQL3: " + sql);
				Logger.getLogger(getClass()).debug("empId: " + empId);
				while (rs.next()) {
					sql = " SELECT SY_AFFIRM_RELATION_TB.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME,HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME, SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL "
							+ " FROM SY_AFFIRM_RELATION_TB, HR_DEPARTMENT, HR_EMPLOYEE HR_AFFIRMOR "
							+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_DEPARTMENT.DEPTID "
							+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL <= "
							+ affirmMaxLevel
							+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL >  "
							+ affirmMinLevel
							+ " AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id "
							+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID = ? "
							+ " AND HR_DEPARTMENT.DEPTID = ? "
							+ " ORDER BY AFFIRM_LEVEL";
					Logger.getLogger(getClass()).debug("SQL4: " + sql);
					Logger.getLogger(getClass()).debug(
							"DEPTID: " + rs.getString("DEPTID"));
					pstmt2 = conn2.prepareStatement(sql);
					pstmt2.setString(1, applyType);
					pstmt2.setString(2, rs.getString("DEPTID"));
					rs2 = pstmt2.executeQuery();
					while (rs2.next()) {
						list.add(createEssAffirmor(rs2, affirmLevel++));
					}
					if (list.size() > 0) {
						break;
					}
				}
			} else {
				list.add(createEssAffirmor(rs, affirmLevel++));
				while (rs.next()) {
					list.add(createEssAffirmor(rs, affirmLevel++));
				}
			}

			for (int i = 0; i < list.size(); i++) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor = (EssAffirmor) list.get(i);
				if (essAffirmor.getAffirmorId().equals(empId)) {
					ownerLevel = essAffirmor.getAffirmLevel();
				}

			}
			Logger.getLogger(getClass()).debug(
					" personid=== " + empId + "  ownerLevel : " + ownerLevel);
			if (containsOwner && ownerLevel != 0) {
				ownerLevel = ownerLevel - 1;
			}
			Logger.getLogger(getClass()).debug(
					" personid=== " + empId + "  ownerLevel : " + ownerLevel
							+ "+ affirmMaxLevel: " + affirmMaxLevel
							+ " containsOwner: " + containsOwner);
			for (int i = 0; i < list.size(); i++) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor = (EssAffirmor) list.get(i);
				if (essAffirmor.getAffirmLevel() > ownerLevel) {
					EssAffirmor AffirmorObject = new EssAffirmor();
					AffirmorObject.setAffirmorId(essAffirmor.getAffirmorId());
					AffirmorObject.setAffirmorName(essAffirmor
							.getAffirmorName());
					AffirmorObject.setAffirmorEnName(essAffirmor
							.getAffirmorEnName());
					AffirmorObject.setAffirmorKorName(essAffirmor
							.getAffirmorKorName());
					AffirmorObject.setAffirmLevel(endaffirmLevel++);
					AffirmorObject.setAffirmLevelOriginal(essAffirmor
							.getAffirmLevelOriginal());
					endlist.add(AffirmorObject);
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
			Logger.getLogger(getClass()).debug(se.toString());
			return new ArrayList();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs2, pstmt2, conn2);
			SqlUtil.close(rs, pstmt, conn);
		}
		return endlist;
	}

	/**
	 * 取出指定员工指定申请类型的决裁者列表
	 * 
	 * @param empId
	 *            员工号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"
	 */
	public List getAffirmorList1(String empId, String applyType,
			int affirmMaxLevel, boolean containsOwner) {
		List list = new ArrayList();
		List endlist = new ArrayList();
		Connection conn = ConnBean.getConn();
		Connection conn2 = ConnBean.getConn();

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int affirmLevel = 1;
		int endaffirmLevel = 1;
		int ownerLevel = 0;
		int affirmMinLevel = 0;
		String sql = "";
		try {
			Logger.getLogger(getClass()).debug("applyType= " + applyType);
			// 决裁对象为部门
			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID "
					+ " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = ? ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			Logger.getLogger(getClass()).debug("SQL3: " + sql);
			Logger.getLogger(getClass()).debug("empId: " + empId);
			while (rs.next()) {
				sql = " SELECT SAR.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME,HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_DUTY, SAR.AFFIRM_LEVEL "
						+ " FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT, HR_EMPLOYEE HR_AFFIRMOR "
						+ " WHERE SAR.AFFIRM_DEPTID = HR_DEPARTMENT.DEPTID "
						+ " AND SAR.AFFIRM_LEVEL <= "
						+ affirmMaxLevel
						+ " AND SAR.AFFIRM_LEVEL >  "
						+ affirmMinLevel
						+ " AND SAR.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id "
						+ " AND SAR.MODULE ='AR' "
						+ " AND HR_DEPARTMENT.DEPTID = ? "
						+ "AND SAR.AFFIRM_DUTY IS NOT NULL "
						+ " ORDER BY AFFIRM_LEVEL";
				Logger.getLogger(getClass()).debug("SQL4: " + sql);
				Logger.getLogger(getClass()).debug(
						"DEPTID: " + rs.getString("DEPTID"));
				pstmt2 = conn2.prepareStatement(sql);
				pstmt2.setString(1, rs.getString("DEPTID"));
				rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					// DIY去掉2级决裁以上的部长
					if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")
							&& rs.getString("DEPTID").substring(0, 2).equals(
									"60")
							&& Integer.parseInt(rs2.getString("AFFIRM_LEVEL")) > 2) {
						break;
					}

					list.add(createEssAffirmor(rs2, affirmLevel++));

					if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")) {
						break;
					}
				}
				if (list.size() > 0) {
					break;
				}
			}

			for (int i = 0; i < list.size(); i++) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor = (EssAffirmor) list.get(i);
				if (essAffirmor.getAffirmorId().equals(empId)) {
					ownerLevel = essAffirmor.getAffirmLevel();
				}

			}
			Logger.getLogger(getClass()).debug(
					" personid=== " + empId + "  ownerLevel : " + ownerLevel);
			if (containsOwner && ownerLevel != 0) {
				ownerLevel = ownerLevel - 1;
			}
			Logger.getLogger(getClass()).debug(
					" personid=== " + empId + "  ownerLevel : " + ownerLevel
							+ "+ affirmMaxLevel: " + affirmMaxLevel
							+ " containsOwner: " + containsOwner);
			for (int i = 0; i < list.size(); i++) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor = (EssAffirmor) list.get(i);
				if (essAffirmor.getAffirmLevel() > ownerLevel) {
					EssAffirmor AffirmorObject = new EssAffirmor();
					AffirmorObject.setAffirmorId(essAffirmor.getAffirmorId());
					AffirmorObject.setAffirmorName(essAffirmor
							.getAffirmorName());
					AffirmorObject.setAffirmorEnName(essAffirmor
							.getAffirmorEnName());
					AffirmorObject.setAffirmorKorName(essAffirmor
							.getAffirmorKorName());
					AffirmorObject.setAffirmLevel(endaffirmLevel++);
					AffirmorObject.setAffirmLevelOriginal(essAffirmor
							.getAffirmLevelOriginal());
					endlist.add(AffirmorObject);
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
			Logger.getLogger(getClass()).debug(se.toString());
			return new ArrayList();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs2, pstmt2, conn2);
			SqlUtil.close(rs, pstmt, conn);
		}
		return endlist;
	}
	
	
	/**
	 * 取出指定员工指定申请类型的决裁者列表
	 * 
	 * @param empId
	 *            员工号
	 * @param applyType
	 *            申请类型 : "OtApply"/"LeaveApply"/"MatchApply"
	 */
	public List getAffirmorList2(String empId, String applyType,
			int affirmMaxLevel, boolean containsOwner) {
		List list = new ArrayList();
		List endlist = new ArrayList();
		Connection conn = ConnBean.getConn();
		Connection conn2 = ConnBean.getConn();
		Connection conn3 = ConnBean.getConn();
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		int affirmLevel = 1;
		int endaffirmLevel = 1;
		int ownerLevel = 0;
		int affirmMinLevel = 0;
		String sql = "";
		try {
			Logger.getLogger(getClass()).debug("applyType= " + applyType);
			// 决裁对象为部门
			sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID "
					+ " FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = ? ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID";
			
			String sqlFunction = "select y.empid,y.deptid,y.chinesename,y.duty_code from hr_employee y where "
					+ " y.person_id = ? and y.status_code not in 'EmpStatus3' and y.activity = '1' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			rs = pstmt.executeQuery();
			pstmt3 = conn3.prepareStatement(sqlFunction);
			pstmt3.setString(1, empId);
			rs3 = pstmt3.executeQuery();
			Logger.getLogger(getClass()).debug("SQL3: " + sql);
			Logger.getLogger(getClass()).debug("empId: " + empId);
			Logger.getLogger(getClass()).debug("duty_code: " + sqlFunction);
			while (rs.next()) {
				sql = " SELECT NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME,HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_DUTY, SAR.AFFIRM_LEVEL "
						+ " FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT, HR_EMPLOYEE HR_AFFIRMOR, "
						+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = ?)  SAA"
						+ " WHERE SAR.AFFIRM_DEPTID = HR_DEPARTMENT.DEPTID "
						+ " AND SAR.AFFIRM_LEVEL <= "
						+ affirmMaxLevel
						+ " AND SAR.AFFIRM_LEVEL >  "
						+ affirmMinLevel
						+ " AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id "
						+ " AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
						+ " AND HR_DEPARTMENT.DEPTID = ? "
						+ " AND SAR.MODULE ='AR' "
						+ "AND SAR.AFFIRM_DUTY IS NOT NULL "
						+ " ORDER BY AFFIRM_LEVEL";
				Logger.getLogger(getClass()).debug("SQL4: " + sql);
				Logger.getLogger(getClass()).debug(
						"DEPTID: " + rs.getString("DEPTID"));
				pstmt2 = conn2.prepareStatement(sql);
				pstmt2.setString(1, applyType);
				String deptid=rs.getString("DEPTID");
				pstmt2.setString(2, deptid);
//				pstmt2.setString(2, rs.getString("DEPTID"));
				rs2 = pstmt2.executeQuery();
				boolean flag = true;
				
				String affirmorID=null;
				String affrimDuty = null;
				boolean fg = true;
				while (rs2.next()) {
					// DIY去掉2级决裁以上的部长
					if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")
							&& rs.getString("DEPTID").substring(0, 2).equals(
									"60")
							&& Integer.parseInt(rs2.getString("AFFIRM_LEVEL")) > 2) {
						break;
					}
					while(rs3.next()){
						if(rs3.getString("duty_code") != null 
								&& rs2.getString("AFFIRMOR_ID").equals(empId)
								
								&& !empId.equals("4632013")
								&& !empId.equals("8423313")
								&& !rs3.getString("duty_code").equals("C_12005_43")
								){
								flag = false;
								break;
						}
					}
					if(rs2.getString("AFFIRMOR_ID").equals(affirmorID)){
						fg=false;
						
					}else{
						affirmorID=rs2.getString("AFFIRMOR_ID");
						fg=true;
						
					}
				
						
						affrimDuty=rs2.getString("AFFIRM_DUTY");
					
					if(flag&&fg){
						list.add(createEssAffirmor(rs2, affirmLevel++));
						}
						if(fg){
						if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")) {
							break;
						}
				}
						if (affrimDuty.equals("C_12005_93775")) {
							break;
						}
					}
					if (list.size() > 0) {
						break;
					}
			}

//			for (int i = 0; i < list.size(); i++) {
//				EssAffirmor essAffirmor = new EssAffirmor();
//				essAffirmor = (EssAffirmor) list.get(i);
//				if (essAffirmor.getAffirmorId().equals(empId)) {
//					ownerLevel = essAffirmor.getAffirmLevel();
//				}
//
//			}
			Logger.getLogger(getClass()).debug(
					" personid=== " + empId + "  ownerLevel : " + ownerLevel);
//			if (containsOwner && ownerLevel != 0) {
//				ownerLevel = ownerLevel - 1;
//			}
			Logger.getLogger(getClass()).debug(
					" personid=== " + empId + "  ownerLevel : " + ownerLevel
							+ "+ affirmMaxLevel: " + affirmMaxLevel
							+ " containsOwner: " + containsOwner);
			for (int i = 0; i < list.size(); i++) {
				EssAffirmor essAffirmor = new EssAffirmor();
				essAffirmor = (EssAffirmor) list.get(i);
//				if (essAffirmor.getAffirmLevel() > ownerLevel) {
					EssAffirmor AffirmorObject = new EssAffirmor();
					AffirmorObject.setAffirmorId(essAffirmor.getAffirmorId());
					AffirmorObject.setAffirmorName(essAffirmor
							.getAffirmorName());
					AffirmorObject.setAffirmorEnName(essAffirmor
							.getAffirmorEnName());
					AffirmorObject.setAffirmorKorName(essAffirmor
							.getAffirmorKorName());
					AffirmorObject.setAffirmLevel(endaffirmLevel++);
					AffirmorObject.setAffirmLevelOriginal(essAffirmor
							.getAffirmLevelOriginal());
					endlist.add(AffirmorObject);
//				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
			Logger.getLogger(getClass()).debug(se.toString());
			return new ArrayList();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs3, pstmt3, conn3);
			SqlUtil.close(rs2, pstmt2, conn2);
			SqlUtil.close(rs, pstmt, conn);
		}
		return endlist;
	}

	private EssAffirmor createEssAffirmor(ResultSet rs, int affirmLevel)
			throws SQLException {
		EssAffirmor essAffirmor = new EssAffirmor();
		essAffirmor.setAffirmorId(rs.getString("AFFIRMOR_ID"));
		essAffirmor.setAffirmorName(rs.getString("AFFIRMOR_NAME"));
		essAffirmor.setAffirmorEnName(rs.getString("Chinese_Pinyin"));
		essAffirmor.setAffirmorKorName(rs.getString("KOREANNAME"));
		essAffirmor.setAffirmLevel(affirmLevel);
		essAffirmor.setAffirmLevelOriginal(rs.getInt("AFFIRM_LEVEL"));
		Logger.getLogger(getClass()).debug(
				"Affirmor [" + essAffirmor.getAffirmLevel() + "] : "
						+ essAffirmor.getAffirmorId());
		return essAffirmor;
	}

	/**
	 * 取得序号值
	 * 
	 * @param seqName
	 *            序号名称
	 * @return
	 */
	private int getSequence(String seqName) {
		int result = 0;
		String sql = seqName + ".NEXTVAL";
		SimpleMap map = new SimpleMap();
		try {
			map.set("sql", sql);
			Object obj = commonSQLMapAdapter.executeQuery(
					"ess.apply.getSequence", map);
			result = Integer.parseInt(obj.toString());

		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得序列号失败", e);
		}
		return result;
	}

	private void doUpdateApply_Leave(EssAffirmParam param) throws SQLException {
		String sql = "UPDATE ESS_LEAVE_APPLY_TB SET "
				+ "LEAVE_FROM_TIME=to_date(?,'yyyy-mm-dd hh24:mi:ss')"
				+ " ,LEAVE_TO_TIME=to_date(?,'yyyy-mm-dd hh24:mi:ss')"
				+ " ,UPDATE_DATE = SYSDATE, UPDATED_BY = ? WHERE LEAVE_NO = ?";
		SqlParameter[] sqlParams = new SqlParameter[4];
		sqlParams[0] = new SqlParameter(ParameterType.STRING, param
				.getFromDate()
				+ " " + param.getFromTime());
		sqlParams[1] = new SqlParameter(ParameterType.STRING, param.getToDate()
				+ " " + param.getToTime());

		// sqlParams[2] = new SqlParameter(ParameterType.STRING,
		// param.getRemark());
		sqlParams[2] = new SqlParameter(ParameterType.STRING, adminId);
		sqlParams[3] = new SqlParameter(param.getApplyNo());
		JdbcUtil.executeUpdate(sql, sqlParams);
	}

	public String getTurnHolidays(String empId, String nameplate) {
		String result = null;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		if (nameplate != null && nameplate.equals("this")) {
			sql = "SELECT ESS_GET_TURN_HOLIDY(SYSDATE,?) AS A FROM DUAL ";
			Logger.getLogger(getClass()).debug(sql);
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empId);
				rs = pstmt.executeQuery();
				if (rs.next())
					result = rs.getString("A");
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				e.printStackTrace();
				throw new GlRuntimeException("取本月倒休天数失败", e);
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
		} else if (nameplate != null && nameplate.equals("next")) {
			sql = "SELECT ESS_GET_TURN_HOLIDY(ADD_MONTHS(SYSDATE,1),?) AS A FROM DUAL ";
			Logger.getLogger(getClass()).debug(sql);
			try {
				SqlParameter[] sqlParams = new SqlParameter[1];
				sqlParams[0] = new SqlParameter(ParameterType.STRING, empId);

				result = JdbcUtil.executeString(sql, sqlParams);
			} catch (Exception e) {
				Logger.getLogger(getClass()).debug(e.toString());
				throw new GlRuntimeException("取下月倒休天数失败", e);
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
		}
		return result;
	}

	public String getLogOnPersonAffirmMaxLevel(String EmpId)
			throws DataAccessException {
		String str = "";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select max(t.affirm_level) maxlevel from ess_affirm t where t.affirmor_id = ?";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = ConnBean.getConn();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, EmpId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).error(e.toString());

		} finally {
			SqlUtil.close(rs, stmt, con);
		}
		if (str == null)
			return "0";
		return str;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getApplyInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getApplyInfoForEmail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object setToEmail(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("ess.apply.setToEmail",
					parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object applyResult(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.applyResult", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtTopLimitAffirmInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtTopLimitAffirmInfoForEmail",
					parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtTopLimitApplyInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter
					.executeQuery("ess.apply.getOtTopLimitApplyInfoForEmail",
							parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtAffirmInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtAffirmInfoForEmail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtApplyInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtApplyInfoForEmail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getOtApplyInfoForEmail Exception. ",
					e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getLeaveApplyInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getLeaveApplyInfoForEmail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"getLeaveApplyInfoForEmail Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getLeaveApplyInfo(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getLeaveApplyInfo", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getLeaveApplyInfo Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getOtApplyInfo(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getOtApplyInfo", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getOtApplyInfo Exception. ", e);
		}
		return result;
	}

	public Object getLeaveApplyInfo1(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ga.visiter.getVisiterApplyInfo", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getVisiterApplyInfo Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getLeaveApplyH9Info(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getLeaveApplyH9Info", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getLeaveApplyH9Info Exception. ", e);
		}
		return result;
	}

	public Object getSaleWastes(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ga.visiter.getSaleWastes", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSaleWastes Exception. ", e);
		}
		return result;
	}

	public Object getSaleWastesCom(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"report.gm.eat.getSaleWastesCom", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSaleWastesCom Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object insertLeaveApplyMail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert(
					"ess.apply.insertLeaveApplyMail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object insertOtApplyMail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("ess.apply.insertOtApplyMail",
					parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object insertOtAffirmMail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("ess.apply.insertOtAffirmMail",
					parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object getLeaveAffirmInfoForEmail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getLeaveAffirmInfoForEmail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/**
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object insertLeaveAffirmMail(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert(
					"ess.apply.insertLeaveAffirmMail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public Object getLeaveDateForAffirm(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getLeaveDateForAffirm", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public Object getEvectionDateForAffirm(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getEvectionDateForAffirm", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public Object getTrainingDateForAffirm(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getTrainingDateForAffirm", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public Object getOverTimeDateForAffirm(Object parameterObject)
			throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.getOverTimeDateForAffirm", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveLeaveAffirmLevel(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			List essLeaveParamList = new ArrayList();
			List deptList = new ArrayList();
			SimpleMap param = new SimpleMap();
			SimpleMap requestParam = (SimpleMap) parameterObject;

			// ess勤态个人别勤态参数
			essLeaveParamList = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveLeaveEmpIdParam", requestParam);

			deptList = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyorDeptList", parameterObject);
			// ess勤态部门别勤态参数
			if (essLeaveParamList.size() == 0) {
				for (int j = 0; j < deptList.size(); j++) {
					SimpleMap simpleMap = (SimpleMap) deptList.get(j);
					requestParam.set("DEPTID", simpleMap.getString("DEPTID"));
					essLeaveParamList = commonSQLMapAdapter
							.executeQueryForMulti(
									"ess.apply.retrieveLeaveParam",
									requestParam);
					if (essLeaveParamList.size() != 0) {
						break;
					}

				}
			}
			if (essLeaveParamList.size() == 0) {
				essLeaveParamList = commonSQLMapAdapter.executeQueryForMulti(
						"ess.apply.retrieveLeaveParamDeptIsNull",
						parameterObject);
			}
			String sql = "SELECT CASE ";
			int essLeaveParamSize = essLeaveParamList.size();

			if (essLeaveParamSize == 0) {
				result = 99;
			} else if (essLeaveParamSize == 1) {
				param = (SimpleMap) essLeaveParamList.get(0);
				if (param.getInt("REFERENCN_FLAG") == 0) {
					result = param.getInt("AFFIRM_LEVEL");
				}
			} else {

				for (int i = 0; i < essLeaveParamSize; ++i) {
					param = (SimpleMap) essLeaveParamList.get(i);

					if (param.getInt("REFERENCN_FROM_FLAG") == 1
							&& param.getInt("REFERENCN_TO_FLAG") == 1) {
						sql = sql + " WHEN "
								+ requestParam.getString("LEAVELENGTH")
								+ param.getString("REFERENCN_FROM_RELATION")
								+ param.getString("REFERENCN_FROM_OFFSET")
								+ " AND "
								+ requestParam.getString("LEAVELENGTH")
								+ param.getString("REFERENCN_TO_RELATION")
								+ param.getString("REFERENCN_TO_OFFSET")
								+ " THEN " + param.getString("AFFIRM_LEVEL");
					} else if (param.getInt("REFERENCN_FROM_FLAG") == 1
							&& param.getInt("REFERENCN_TO_FLAG") == 0) {
						sql = sql + " WHEN "
								+ requestParam.getString("LEAVELENGTH")
								+ param.getString("REFERENCN_FROM_RELATION")
								+ param.getString("REFERENCN_FROM_OFFSET")
								+ " THEN " + param.getString("AFFIRM_LEVEL");
					} else if (param.getInt("REFERENCN_FROM_FLAG") == 0
							&& param.getInt("REFERENCN_TO_FLAG") == 1) {
						sql = sql + " WHEN "
								+ requestParam.getString("LEAVELENGTH")
								+ param.getString("REFERENCN_TO_RELATION")
								+ param.getString("REFERENCN_TO_OFFSET")
								+ " THEN " + param.getString("AFFIRM_LEVEL");
					}
				}

				sql = sql + " ELSE 0 END AS AFFIRMLEVEL FROM DUAL ";
			}

			if (result == 0) {
				logger.debug("SQL= " + sql);
				conn = services.getConnection();

				state = conn.prepareStatement(sql);

				rs = state.executeQuery();

				if (rs.next()) {
					result = rs.getInt("AFFIRMLEVEL");
				} else {
					result = 0;
				}
			}

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		} finally {
			SqlUtil.close(rs, state, conn);
		}

		return result;
	}

	/*
	 * new
	 */

	public int retrieveOvertimeAffirmLevel(Object parameterObject,
			Object essOverTimeObject) throws GlRuntimeException {

		int result = 0;
		double otHours = 0;
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {

			EssOverTimeBean essOverTime = (EssOverTimeBean) essOverTimeObject;// 该断取出刚刚申请的加班长度
			GregorianCalendar startTime = DateUtil
					.ParseGregorianCalendar(essOverTime.getOtDate() + " "
							+ essOverTime.getOtFromTime());
			GregorianCalendar endTime = DateUtil
					.ParseGregorianCalendar(essOverTime.getOtDate() + " "
							+ essOverTime.getOtToTime());

			if (essOverTime.getOtTypeCode().equals("WorkingOtType01")) {
				otHours = DateUtil.DateDiff(startTime, endTime, "HOUR");
			} else {
				otHours = essOverTime.getOtLength();
			}
			// System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+otHours);

			List essLeaveParamList = new ArrayList();
			List deptList = new ArrayList();
			SimpleMap param = new SimpleMap();
			SimpleMap requestParam = (SimpleMap) parameterObject;
			requestParam.setDouble("OVERTIMETOTAL", requestParam
					.getDouble("OVERTIMETOTAL")
					+ otHours);

			deptList = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyorDeptList", parameterObject);
			for (int j = 0; j < deptList.size(); j++) {
				SimpleMap simpleMap = (SimpleMap) deptList.get(j);
				requestParam.set("DEPTID", simpleMap.getString("DEPTID"));
				essLeaveParamList = commonSQLMapAdapter.executeQueryForMulti(
						"ess.apply.retrieveOvertimeParam", requestParam);
				if (essLeaveParamList.size() != 0) {
					break;
				}

			}
			if (essLeaveParamList.size() == 0) {
				essLeaveParamList = commonSQLMapAdapter.executeQueryForMulti(
						"ess.apply.retrieveOvertimeParamDeptIsNull",
						parameterObject);
			}
			String sql1 = "SELECT CASE  WHEN 0>0 THEN 0";
			String sql2 = "SELECT CASE  WHEN 0>0 THEN 0";
			String sql = "";
			int essLeaveParamSize = essLeaveParamList.size();

			if (essLeaveParamSize == 0) {
				result = 99;
			}
			// else if (essLeaveParamSize == 1)
			// {
			// param = (SimpleMap)essLeaveParamList.get(0) ;
			// if (param.getInt("REFERENCN_FLAG") == 0 )
			// {
			// result = param.getInt("AFFIRM_LEVEL") ;
			// }
			// }
			else {

				for (int i = 0; i < essLeaveParamSize; ++i) {
					param = (SimpleMap) essLeaveParamList.get(i);

					if (param.getString("CONDITION_TYPE") == "OvertimeTotal"
							|| "OvertimeTotal".equals(param
									.getString("CONDITION_TYPE"))) {

						if (param.getInt("REFERENCN_FROM_FLAG") == 1
								&& param.getInt("REFERENCN_TO_FLAG") == 1) {
							sql1 = sql1
									+ " WHEN "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param
											.getString("REFERENCN_FROM_RELATION")
									+ param.getString("REFERENCN_FROM_OFFSET")
									+ " AND "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param.getString("REFERENCN_TO_RELATION")
									+ param.getString("REFERENCN_TO_OFFSET")
									+ " THEN "
									+ param.getString("AFFIRM_LEVEL");
						} else if (param.getInt("REFERENCN_FROM_FLAG") == 1
								&& param.getInt("REFERENCN_TO_FLAG") == 0) {
							sql1 = sql1
									+ " WHEN "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param
											.getString("REFERENCN_FROM_RELATION")
									+ param.getString("REFERENCN_FROM_OFFSET")
									+ " THEN "
									+ param.getString("AFFIRM_LEVEL");
						} else if (param.getInt("REFERENCN_FROM_FLAG") == 0
								&& param.getInt("REFERENCN_TO_FLAG") == 1) {
							sql1 = sql1 + " WHEN "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param.getString("REFERENCN_TO_RELATION")
									+ param.getString("REFERENCN_TO_OFFSET")
									+ " THEN "
									+ param.getString("AFFIRM_LEVEL");
						}

					} else if ((param.getString("CONDITION_TYPE") == "OvertimeType" || "OvertimeType"
							.equals(param.getString("CONDITION_TYPE")))
							&& (requestParam.getString("OVERTIME_TYPE") == param
									.getString("OVERTIME_TYPE"))
							|| (requestParam.getString("OVERTIME_TYPE")
									.equals(param.getString("OVERTIME_TYPE")))) {

						if (param.getInt("REFERENCN_FROM_FLAG") == 1
								&& param.getInt("REFERENCN_TO_FLAG") == 1) {
							sql2 = sql2
									+ " WHEN "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param
											.getString("REFERENCN_FROM_RELATION")
									+ param.getString("REFERENCN_FROM_OFFSET")
									+ " AND "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param.getString("REFERENCN_TO_RELATION")
									+ param.getString("REFERENCN_TO_OFFSET")
									+ " THEN "
									+ param.getString("AFFIRM_LEVEL");
						} else if (param.getInt("REFERENCN_FROM_FLAG") == 1
								&& param.getInt("REFERENCN_TO_FLAG") == 0) {
							sql2 = sql2
									+ " WHEN "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param
											.getString("REFERENCN_FROM_RELATION")
									+ param.getString("REFERENCN_FROM_OFFSET")
									+ " THEN "
									+ param.getString("AFFIRM_LEVEL");
						} else if (param.getInt("REFERENCN_FROM_FLAG") == 0
								&& param.getInt("REFERENCN_TO_FLAG") == 1) {
							sql2 = sql2 + " WHEN "
									+ requestParam.getString("OVERTIMETOTAL")
									+ param.getString("REFERENCN_TO_RELATION")
									+ param.getString("REFERENCN_TO_OFFSET")
									+ " THEN "
									+ param.getString("AFFIRM_LEVEL");
						}

					}

				}

				sql1 = sql1 + " ELSE 0 END AS AFFIRMLEVEL FROM DUAL ";
				sql2 = sql2 + " ELSE 0 END AS AFFIRMLEVEL FROM DUAL ";
				sql = "select  decode(greatest((" + sql1 + "),(" + sql2
						+ ")),0,99,greatest((" + sql1 + "),(" + sql2
						+ "))) AS AFFIRMLEVEL1 from dual";
			}

			if (result == 0) {
				logger.debug("SQL= " + sql);
				conn = services.getConnection();

				state = conn.prepareStatement(sql);

				rs = state.executeQuery();

				if (rs.next()) {
					result = rs.getInt("AFFIRMLEVEL1");
				} else {
					result = 0;
				}
			}

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		} finally {
			SqlUtil.close(rs, state, conn);
		}

		return result;
	}

	/*
	 * new
	 */

	public String retrieveApplyLeaveLength(Object parameterObject)
			throws GlRuntimeException {

		String result = "0";
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyLeaveLength", parameterObject)
					.toString();

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List retrieveApplyorDeptList(Object parameterObject)
			throws GlRuntimeException {

		List list = null;
		try {

			list = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyorDeptList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return list;
	}

	/*
	 * new
	 */

	public int retrieveApplyPersonCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyOTPersonCnt", parameterObject)
					.toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveApplyOTTopLimitPersonCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyOTTopLimitPersonCnt",
					parameterObject));

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveApplyOTPersonCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyOTPersonCnt", parameterObject)
					.toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveApplyOTPerson(Object parameterObject, int currentPage,
			int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyOTPerson", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public Object retrieveApplyOTPerson(Object parameterObject)
			throws GlRuntimeException {

		Object result = null;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyOTPerson", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public String retrieveApplyArMonth(Object parameterObject)
			throws GlRuntimeException {

		String result = "";
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyArMonth", parameterObject), "");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveApplyLeavePerson(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyLeavePerson", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public Object retrieveApplyLeavePerson(Object parameterObject)
			throws GlRuntimeException {

		Object result = null;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyLeavePerson", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveApplyOTTopLimitPerson(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyOTTopLimitPerson", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public Object retrieveApplyOTTopLimitPerson(Object parameterObject)
			throws GlRuntimeException {

		Object result = null;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyOTTopLimitPerson", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveApplyOTTopLimitMonthList(Object parameterObject)
			throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveApplyOTTopLimitMonthList",
					parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	/*
	 * new
	 */

	public int retrieveApplyAffirmNextFlag(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveApplyAffirmNextFlag", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrieveOtAffirmList(Object parameterObject, int currentPage,
			int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveOtAffirmList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrieveOtAffirmList(Object parameterObject)
			throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveOtAffirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveOtTopLimitAffirmListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveOtTopLimitAffirmListCnt",
					parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrieveOtTopLimitAffirmList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveOtTopLimitAffirmList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveOtAffirmListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveOtAffirmListCnt", parameterObject)
					.toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveAffirmDeptList(Object parameterObject)
			throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveAffirmDeptList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveLeaveAffirmListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveLeaveAffirmListCnt", parameterObject)
					.toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public int retrieveArModifyAffirmListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveArModifyAffirmListCnt", parameterObject)
					.toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveLeaveAffirmList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveLeaveAffirmList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveArModifyAffirmList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveArModifyAffirmList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public List retrieveLeaveAffirmList(Object parameterObject)
			throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrieveLeaveAffirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrievePersonalOtInfoList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrievePersonalOtInfoList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List retrievePersonalOtInfoList(Object parameterObject)
			throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrievePersonalOtInfoList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public int retrievePersonalOtInfoListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrievePersonalOtInfoListCnt", parameterObject)
					.toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrievePersonalOtTopLimitInfoList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrievePersonalOtTopLimitInfoList",
					parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public int retrievePersonalOtTopLimitInfoListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrievePersonalOtTopLimitInfoListCnt",
					parameterObject));

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrievePersonalLeaveInfoList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrievePersonalLeaveInfoList", parameterObject,
					currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrievePersonalLeaveInfoList(Object parameterObject)
			throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrievePersonalLeaveInfoList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public int retrievePersonalLeaveInfoListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrievePersonalLeaveInfoListCnt",
					parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public List retrievePersonalArModifyInfoList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {

		List result = new ArrayList();
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.retrievePersonalArModifyInfoList",
					parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public int retrievePersonalArModifyInfoListCnt(Object parameterObject)
			throws GlRuntimeException {

		int result = 0;
		try {

			result = Integer.parseInt(commonSQLMapAdapter.executeQuery(
					"ess.apply.retrievePersonalArModifyInfoListCnt",
					parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */
	public void doOtTopLimitAffirm(Object parameterObject) throws SQLException {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(
				EssSysparam.class, ApplicationContext.getTheadLocal()
						.getCpnyId());
		try {
			SimpleMap para = (SimpleMap) parameterObject;

			int flag = para.getInt("affirmFlag");

			commonSQLMapAdapter.startTransaction();

			// 更新 ESS_AFFIRM
			commonSQLMapAdapter.update("ess.apply.updateAffirmorInfo", para);

			// 更新 ESS_APPLY_OTTOPLIMIT
			commonSQLMapAdapter.update("ess.apply.updateApplyOtTopLimitInfo",
					para);

			if (essSysparam.isOtAutoConfirm()
					&& isLastAffirmor(para.getInt("affirmNo"))) {
				// 决裁者进行否决操作时, 清除人事确认结果
				if (flag == 2)
					para.setInt("activity", 0);
				else
					para.setInt("activity", 1);
				// 更新 ESS_APPLY_OTTOPLIMIT
				commonSQLMapAdapter.update(
						"ess.apply.updateApplyOtTopLimitInfo", para);

				// 更新 ESS_OVERTIME_PLAN
				commonSQLMapAdapter.update("ess.apply.updatePersonalOTPlan",
						para);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		} finally {
			commonSQLMapAdapter.endTransation();
		}
	}

	public Object retrieveInsteadAffirmAppoint(Object parameterObject)
			throws GlRuntimeException {

		Object result = null;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveInsteadAffirm2", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	/*
	 * new
	 */

	public Object retrieveInsteadAffirm(Object parameterObject)
			throws GlRuntimeException {

		Object result = null;
		try {

			result = commonSQLMapAdapter.executeQuery(
					"ess.apply.retrieveInsteadAffirm1", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getPerson_id(String id) throws GlRuntimeException {
		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getEmployeeInfo", id);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getAffirmorFromEssAffirm(Object parameterObject)
			throws GlRuntimeException {
		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getAffirmorFromEssAffirm", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getApplyNoForMail(Object parameterObject)
			throws GlRuntimeException {
		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getApplyNoForMail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getLeaveAffirmorEssAffirm(Object parameterObject)
			throws GlRuntimeException {
		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getLeaveAffirmorEssAffirm", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getLeaveApplyNoForMail(Object parameterObject)
			throws GlRuntimeException {
		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getLeaveApplyNoForMail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getArModifyApplyNoForMail(Object parameterObject)
			throws GlRuntimeException {
		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getArModifyApplyNoForMail", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List applyLeaveResult(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.applyLeaveResult", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List applyLeaveResult1(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.applyLeaveResult1", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List applyArModifyResult(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.applyArModifyResult", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getNextAffirmor(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getNextAffirmor", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getLeaveNextAffirmor(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getLeaveNextAffirmor", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getArModifyNextAffirmor(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getArModifyNextAffirmor", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getOtApplyor(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getOtApplyor", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getOtApplyNoCreateBy(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getOtApplyNoCreateBy", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getLeaveApplyor(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getLeaveApplyor", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getLeaveApplyNoCreateBy(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getLeaveApplyNoCreateBy", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getAttendanceConfirmList(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getAttendanceConfirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getArDetailConfirmList(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getArDetailConfirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

	public List getDetailConfirmList(Object parameterObject)
			throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti(
					"ess.apply.getDetailConfirmList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(" Exception. ", e);
		}
		return result;
	}

}