package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ait.api.resultApi.DooPushAPI;
import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDetail;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.ess.business.EssServices;
import com.ait.ga.bean.GaAffirmList;
import com.ait.mail.Mail;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

public class ArDetailBean {

	private String loginID = null;

	private String loginCnpyId = null;
	
	private EssServices essServices;

	//private String url = "http://doopis.corp.doosan.com/dic_login.jsp" ;
//	private String url = "http://pnbs.corp.doosan.com/dic_login.jsp" ;
	//ad登录验证
//	private String url = "http://portal.doosan.com" ;
	private String url = "http://10.40.128.28:8083/" ;
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	public ArDetailBean() {

		commonSQLMapAdapter = new CommonSQLMapAdapter("em2");
	}

	// 设定登陆人ID
	public void setLoginID(String loginID) {
		Logger.getLogger(getClass()).debug("LoginID Set to : " + loginID);
		this.loginID = loginID;
	}

	/**
	 * retrieve attendance detailBack  information
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getArDetailBackList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List list;

		try {
			//list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetailBackList", parameterObject,currentPage, pageSize);
			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetailBackList", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detailBack information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve attendance detailBack  information
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getArDetailBackList(Object parameterObject) throws GlRuntimeException {

		List list;

		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetailBackList", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detailBack information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve attendance detail information
	 * 
	 * @param sDate
	 * @param eDate
	 * @param key
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getArDetail(String sDate, String eDate, String key, String deptid, String itemNo, String sLength, String eLength, String empType, String tableName) throws GlRuntimeException {

		List list;
		SimpleMap parameterObject = new SimpleMap();

		try {

			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("deptid", deptid);
			parameterObject.setString("supervisor", this.loginID);
			parameterObject.setString("itemNo", itemNo);
			parameterObject.setString("sLength", sLength);
			parameterObject.setString("eLength", eLength);
			parameterObject.setString("tableName", tableName);
			parameterObject.setString("cnpyId", this.loginCnpyId);

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetail", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail information Exception. ", e);
		}
		return list;
	}
	
	
	/**
	 * retrieve attendance detail information
	 * 
	 * @param sDate
	 * @param eDate
	 * @param key
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getArDetailByEmail(String sDate, String eDate, String key, String deptid, String itemNo, String sLength, String eLength, String empType, String tableName) throws GlRuntimeException {

		List list;
		SimpleMap parameterObject = new SimpleMap();

		try {

			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("deptid", deptid);
			parameterObject.setString("supervisor", this.loginID);
			parameterObject.setString("itemNo", itemNo);
			parameterObject.setString("sLength", sLength);
			parameterObject.setString("eLength", eLength);
			parameterObject.setString("tableName", tableName);
			parameterObject.setString("cnpyId", "78000000");//DICC支社

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetailByZhishe", parameterObject);
			Iterator iter = list.iterator();
			String	result = "";
			if (list.size()>0) {
			ArDetail are = (ArDetail)list.get(0);		
			SimpleMap parameterObject1 = new SimpleMap();
			parameterObject1.setString("deptId", are.getDeptId());
			parameterObject1.setString("flag", "1");
			result = (String)commonSQLMapAdapter.executeQuery("ar.common.getArEmail", parameterObject1);
			}
		    
			if(!"".equals(result)){
			while (iter.hasNext()) {
				ArDetail no = (ArDetail)iter.next();
				parameterObject.setInt("PKNO", no.getPkNo()); 
				commonSQLMapAdapter.update("ar.common.updateArDetailFlag",parameterObject);
			}
			}
			
//			for(int i = 0 ; i<=list.size() ; i++){
//				SimpleMap no = (SimpleMap)list.get(i);
//				parameterObject.setString("tableName", no.getString("no")); 
//			}

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve attendance detail information by paging
	 * 
	 * @param sDate
	 * @param eDate
	 * @param key
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getArDetail(String sDate, String eDate, String key, String deptid, String itemNo, String sLength, String eLength, int currentPage, int pageSize, String empType, String personId, String tableName) throws GlRuntimeException {

		List list;
		SimpleMap parameterObject = new SimpleMap();

		try {
			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("personId", personId);
			parameterObject.setString("deptid", deptid);
			parameterObject.setString("supervisor", this.loginID);
			parameterObject.setString("cnpyId", this.loginCnpyId);
			parameterObject.setString("itemNo", itemNo);
			parameterObject.setString("sLength", sLength);
			parameterObject.setString("eLength", eLength);
			parameterObject.setString("empType", empType);
			parameterObject.setString("tableName", tableName);
			parameterObject.set("startRownum", currentPage * pageSize);
			parameterObject.set("endRownum", (currentPage + 1) * pageSize);

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetail", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail information Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve attendance detail information for report
	 * 
	 * @param sDate
	 * @param eDate
	 * @param key
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */

	public List getArDetailForExecl(String sDate, String eDate, String key) throws GlRuntimeException {

		List list;
		SimpleMap parameterObject = new SimpleMap();

		try {

			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("supervisor", this.loginID);

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetail", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail information Exception. ", e);
		}
		return list;
	}

	public int getArDetailCnt(String sDate, String eDate, String key, String deptid, String itemNo, String sLength, String eLength, String empType, String personId, String tableName) throws GlRuntimeException {

		SimpleMap parameterObject = new SimpleMap();
		int result;
		try {

			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("personId", personId);
			parameterObject.setString("deptid", deptid);
			parameterObject.setString("supervisor", this.loginID);
			parameterObject.setString("cnpyId", this.loginCnpyId);
			parameterObject.setString("itemNo", itemNo);
			parameterObject.setString("sLength", sLength);
			parameterObject.setString("eLength", eLength);
			parameterObject.setString("empType", empType);
			parameterObject.setString("tableName", tableName);

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ar.common.RetrieveAttDetailCnt", parameterObject).toString());

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail count information Exception. ", e);
		}
		return result;
	}

	public int getDetailBackCnt(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ar.common.RetrieveAttDetailBackCnt", parameterObject).toString());

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail count information Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve attendance detail for attendance view
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveAttDetailForView(Object parameterObject) throws GlRuntimeException {

		List list;

		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveAttDetailForView", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance detail for attendance view Exception. ", e);
		}
		return list;
	}

	/**
	 * Retrieve special item for attendance view
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveSpecialItemForView(Object parameterObject) throws GlRuntimeException {

		List list;

		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveSpecialItemForView", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve special item for attendance view Exception. ", e);
		}
		return list;
	}

	/**
	 * retrieve attendance start time and end time
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object retrieveAttStartEndTime(Object parameterObject) throws GlRuntimeException {

		Object object;

		try {

			object = commonSQLMapAdapter.executeQuery("ar.common.retrieveAttStartEndTime", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve attendance start time and end time Exception. ", e);
		}
		return object;
	}

	/**
	 * retrieve over time for attendance view
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveOverTimeByView(Object parameterObject) throws GlRuntimeException {

		List list;

		try {

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveOverTimeByView", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve over time for attendance view Exception. ", e);
		}
		return list;
	}

	public List<String> addArDetail(HttpServletRequest request, AdminBean admin) {

		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class, admin.getCpnyId());

		List<String> errorList = new ArrayList<String>();
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rsCheck = null;
		ResultSet rsCheck1 = null;

		PreparedStatement pst1 = null;
		ResultSet rs1 = null;

		//String post_grade_code = "" ;
		String item_group_code = "";
		String item_group_name = "";
		float limitOt = 0;
		float quantity = 0;
		boolean flag = false;

		String[] details = request.getParameterValues("details");

		String sqlCheck = "SELECT T.ITEM_GROUP_CODE , GET_CODE_NAME(T.ITEM_GROUP_CODE) ITEM_GROUP_NAME FROM AR_ITEM T WHERE T.ITEM_NO = ?";

		String sqlCheck1 = "SELECT OT.EMPID,OT.PLAN_MONTH,OT.LIMIT_OT FROM AR_OVERTIME_PLAN OT WHERE OT.EMPID = ? AND ? BETWEEN OT.START_MONTH AND OT.END_MONTH ";

		String sqlCheck2 = "SELECT AD.EMPID,AD.AR_MONTH_STR, NVL(SUM(AD.QUANTITY),0) AS QUANTITY " + "  FROM AR_DETAIL AD , AR_ITEM AI " + " WHERE AI.ITEM_NO = AD.AR_ITEM_NO AND AD.EMPID = ? " + "   AND AI.ITEM_GROUP_CODE = 'OverTimeGroup' AND AD.AR_MONTH_STR = ? "
				+ " GROUP BY AD.EMPID,AD.AR_MONTH_STR";

		String sqlSelect = "SELECT AR_DETAIL.EMPID, HR.CHINESENAME, AR_GET_SHIFTNO(AR_DETAIL.EMPID, AR_DATE_STR,HR.CPNY_ID) SHIFT_NO, " + "AR_DATE_STR, AR_MONTH_STR, DATE_TYPE, HR.POST_GRADE_CODE "
				+ "FROM AR_DETAIL,HR_EMPLOYEE HR WHERE AR_DETAIL.EMPID = HR.PERSON_ID AND PK_NO = ?";

		String sqlInsertBack = "INSERT INTO AR_DETAIL_BACK " + "(PK_NO, EMPID, AR_ITEM_NO, FROM_TIME, " + "TO_TIME, SHIFT_NO, AR_DATE_STR, AR_MONTH_STR, QUANTITY, " + "LOCK_YN, DATE_TYPE, UNIT,CREATE_DATE,CREATED_BY,OPERATION,AR_PK_NO) "
				+ "SELECT AR_DETAIL_BACK_SEQ.NEXTVAL,EMPID, AR_ITEM_NO, FROM_TIME," + "TO_TIME, SHIFT_NO, AR_DATE_STR,AR_MONTH_STR, QUANTITY, " + "LOCK_YN, DATE_TYPE, UNIT,SYSDATE,?,'CREATE',PK_NO " + "FROM AR_DETAIL WHERE PK_NO = ?  ";

		String sqlDelete = "DELETE FROM AR_DETAIL WHERE EMPID = ? AND AR_DATE_STR = ? AND AR_ITEM_NO = ? ";

		String sqlInsert = "INSERT INTO AR_DETAIL (PK_NO, EMPID, AR_ITEM_NO, FROM_TIME, TO_TIME, SHIFT_NO, AR_DATE_STR, AR_MONTH_STR, QUANTITY, LOCK_YN, DATE_TYPE, UNIT) (SELECT AR_DETAIL_SEQ.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY/MM/DD'), "
				+ "TO_DATE(?, 'YYYY/MM/DD'), ?, ?, ?, ?, ?, ?, A.UNIT FROM AR_ITEM_PARAM A, HR_EMPLOYEE HR WHERE A.AR_ITEM_NO = ? AND AR_GROUP_NO = AR_GET_GROUPNO_FOR_ITEM(?, ?, HR.CPNY_ID, HR.EMP_DIFF_CODE, HR.WAGES_TYPE_CODE) AND A.SHIFT_NO = AR_GET_SHIFTNO_FOR_ITEM(?, ?, HR.CPNY_ID, HR.EMP_DIFF_CODE, HR.WAGES_TYPE_CODE) "
				+ "AND A.WAGES_TYPE_CODE = HR.WAGES_TYPE_CODE AND A.STAT_TYPE_CODE = HR.EMP_DIFF_CODE AND A.CPNY_ID = ? AND HR.PERSON_ID = ?) ";

		//考勤员验证SQL
		String getSupervisorItemId_sql = "select a.item_group_id from ar_supervisor a where a.supervisor_id = ?";

		Logger.getLogger(getClass()).debug(sqlCheck);
		Logger.getLogger(getClass()).debug(sqlCheck1);
		Logger.getLogger(getClass()).debug(sqlCheck2);

		Logger.getLogger(getClass()).debug(sqlSelect);
		Logger.getLogger(getClass()).debug(sqlInsertBack);
		Logger.getLogger(getClass()).debug(sqlDelete);
		Logger.getLogger(getClass()).debug(sqlInsert);

		try {
			conn.setAutoCommit(false);
			if (details != null) {
				for (int i = 0; i < details.length; i++) {
					ps = conn.prepareStatement(sqlSelect);
					ps.setInt(1, Integer.parseInt(details[i]));
					rs = ps.executeQuery();
					if (rs.next()) {

						//						根据考勤员权限进行验证
						pst1 = conn.prepareStatement(getSupervisorItemId_sql);
						pst1.setString(1, admin.getAdminID());
						rs1 = pst1.executeQuery();

						String itemStr[] = null;

						if (rs1.next()) {
							String itemId = rs1.getString(1);
							if (itemId != null) {
								itemStr = itemId.split(",");
							}
						}

						pst1 = conn.prepareStatement(sqlCheck);
						pst1.setInt(1, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						rs1 = pst1.executeQuery();
						if (rs1.next()) {
							item_group_code = rs1.getString(1);
							item_group_name = rs1.getString(2);
						}

						for (String string : itemStr) {

							if (item_group_code == string.trim() || item_group_code.equals(string.trim())) {
								flag = true;
							}
						}

						if (flag == false) {
							errorList.add("当前考勤员 [" + admin.getChineseName() + "],没有添加 [" + item_group_name + "] 的权限");
							continue;
						}

						//	根据加班上限进行验证
						if (essSysparam.getOtApplyMaxHours() && item_group_code.equals("OverTimeGroup")) {

							ps = conn.prepareStatement(sqlCheck1);
							ps.setString(1, rs.getString("EMPID"));
							ps.setString(2, rs.getString("AR_MONTH_STR"));
							rsCheck = ps.executeQuery();
							if (rsCheck.next()) {
								ps = conn.prepareStatement(sqlCheck2);
								ps.setString(1, rs.getString("EMPID"));
								ps.setString(2, rs.getString("AR_MONTH_STR"));
								rsCheck1 = ps.executeQuery();

								limitOt = rsCheck.getInt("LIMIT_OT");
								float paraQuantity = NumberUtil.parseFloat(request.getParameter("quantity_" + details[i]));
								if (rsCheck1.next()) {

									quantity = rsCheck1.getFloat("QUANTITY");
								}
								
								Logger.getLogger(getClass()).debug("limitOt : " + limitOt);
								Logger.getLogger(getClass()).debug("quantity : " + quantity);
								Logger.getLogger(getClass()).debug("paraQuantity : " + paraQuantity);
								Logger.getLogger(getClass()).debug("limitOt - quantity - paraQuantity : " + (limitOt - quantity - paraQuantity));
								
								if (limitOt - quantity - paraQuantity < 0) {
									errorList.add("员工 [" + rs.getString("CHINESENAME") + "],剩余加班小时数不足,添加失败!!!");
									continue;
								}
							} else {
								errorList.add("员工 [" + rs.getString("CHINESENAME") + "],没有加班限制,不能增加加班!!!");
								continue;
							}
						}
						ps = conn.prepareStatement(sqlInsertBack);
						ps.setString(1, admin.getAdminID());
						ps.setInt(2, NumberUtil.parseInt(details[i]));
						ps.executeUpdate();

						ps = conn.prepareStatement(sqlDelete);
						ps.setString(1, rs.getString("EMPID"));
						ps.setString(2, rs.getString("AR_DATE_STR"));
						ps.setInt(3, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						ps.executeUpdate();

						ps = conn.prepareStatement(sqlInsert);
						ps.setString(1, rs.getString("EMPID"));
						ps.setInt(2, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						ps.setString(3, rs.getString("AR_DATE_STR"));
						ps.setString(4, rs.getString("AR_DATE_STR"));
						ps.setInt(5, rs.getInt("SHIFT_NO"));
						ps.setString(6, rs.getString("AR_DATE_STR"));
						ps.setString(7, rs.getString("AR_MONTH_STR"));
						ps.setDouble(8, NumberUtil.parseDouble(request.getParameter("quantity_" + details[i])));
						ps.setString(9, StringUtil.checkNull(request.getParameter("lock_" + details[i]), "Y"));
						ps.setInt(10, rs.getInt("DATE_TYPE"));
						ps.setInt(11, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						ps.setString(12, rs.getString("EMPID"));
						ps.setInt(13, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						ps.setInt(14, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						ps.setInt(15, rs.getInt("SHIFT_NO"));
						ps.setString(16, admin.getCpnyId());
						ps.setString(17, rs.getString("EMPID"));
						ps.executeUpdate();

					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs1, pst1);
			SqlUtil.close(rsCheck1, ps);
			SqlUtil.close(rsCheck, ps);
			SqlUtil.close(rs, ps, conn);
		}

		return errorList;
	}

	public List modArDetail(HttpServletRequest request, AdminBean admin) {
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class, admin.getCpnyId());

		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		PreparedStatement psCheck = null;
		PreparedStatement insertPs = null;
		PreparedStatement updatePs = null;
		PreparedStatement psAffirmEmail = null;
		PreparedStatement insertAffirmPs = null;
		
		ResultSet rs = null;
		ResultSet rsCheck = null;
		ResultSet rsCheck1 = null;
		ResultSet rsAffirmEmail = null;
		
		

		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		
		String item_group_code = "";
		String item_group_name = "";
		
		float limitOt = 0;
		float quantity = 0;
		boolean flag = false;
		
		String affirmEmail ="";

		SimpleMap inputData = new SimpleMap();
		
		StringBuffer content = new StringBuffer();
		String emailAddress = "";
		
		List errorList = new ArrayList();

		String[] details = request.getParameterValues("details");

		String sqlSelect = " SELECT AR_DETAIL.EMPID, HR.CHINESENAME,HR.EMPID AS HR_EMPID, HR.EMP_DIFF_CODE,HR.POST_GRADE_CODE, AR_DATE_STR, AR_MONTH_STR, DATE_TYPE, HR.POST_GRADE_CODE,AR_DETAIL.REMARK  " + " FROM AR_DETAIL,HR_EMPLOYEE HR WHERE AR_DETAIL.EMPID = HR.PERSON_ID AND PK_NO = ? ";

		String sqlCheck = "SELECT T.ITEM_GROUP_CODE , GET_CODE_NAME(T.ITEM_GROUP_CODE) ITEM_GROUP_NAME FROM AR_ITEM T WHERE T.ITEM_NO = ?";

		String sqlCheck1 = "SELECT OT.EMPID,OT.PLAN_MONTH,OT.LIMIT_OT FROM AR_OVERTIME_PLAN OT WHERE OT.EMPID = ? AND ? BETWEEN OT.START_MONTH AND OT.END_MONTH ";

		String sqlCheck2 = "SELECT AD.EMPID,AD.AR_MONTH_STR, SUM(AD.QUANTITY) AS QUANTITY FROM AR_DETAIL AD , AR_ITEM AI WHERE AI.ITEM_NO = AD.AR_ITEM_NO AND AD.EMPID = ? " + "   AND AI.ITEM_GROUP_CODE = 'OverTimeGroup' AND AD.AR_MONTH_STR = ? "
				+ "   AND AD.PK_NO <> ? GROUP BY AD.EMPID,AD.AR_MONTH_STR";

		String sqlInsertBack = "INSERT INTO AR_DETAIL_BACK " + "(PK_NO, EMPID, AR_ITEM_NO, FROM_TIME, TO_TIME, SHIFT_NO, AR_DATE_STR, AR_MONTH_STR, QUANTITY, LOCK_YN, DATE_TYPE, UNIT,CREATE_DATE,CREATED_BY,OPERATION,AR_PK_NO,FLAG) "
				+ "SELECT AR_DETAIL_BACK_SEQ.NEXTVAL,EMPID, AR_ITEM_NO, FROM_TIME, TO_TIME, SHIFT_NO, AR_DATE_STR,AR_MONTH_STR, QUANTITY, LOCK_YN, DATE_TYPE, UNIT,SYSDATE,?,'UPDATE',PK_NO,0 FROM AR_DETAIL WHERE PK_NO = ? ";

		String sql = "UPDATE AR_DETAIL SET (AR_ITEM_NO, QUANTITY, LOCK_YN, REMARK, MOD_DATE, UNIT, FLAG) =(SELECT ?, ?, ?, ?,SYSDATE, A.UNIT, ?  FROM AR_ITEM_PARAM A, HR_EMPLOYEE HR " 
                    + " WHERE A.AR_ITEM_NO = ? AND AR_GROUP_NO = AR_GET_GROUPNO_FOR_ITEM(HR.PERSON_ID, ?, HR.CPNY_ID, HR.EMP_DIFF_CODE, HR.WAGES_TYPE_CODE) "
                    + " AND A.SHIFT_NO = AR_GET_SHIFTNO_FOR_ITEM(?, AR_DETAIL.SHIFT_NO, HR.CPNY_ID, HR.EMP_DIFF_CODE, HR.WAGES_TYPE_CODE) AND A.WAGES_TYPE_CODE = HR.WAGES_TYPE_CODE "
                    + " AND A.STAT_TYPE_CODE = HR.EMP_DIFF_CODE AND A.CPNY_ID = ? AND HR.PERSON_ID = AR_DETAIL.EMPID) WHERE PK_NO = ?";

		String sqlDeptEmpid =   "select h1.person_id AS EMPID,h1.email AS EMAIL from"
						        +"(select CASE WHEN emp_diff_code ='C_12067_1330306' and (post_grade_code = 'C_12004_1330054' or post_grade_code = 'C_12004_1324167') "
						        +"THEN hr_get_level_deptid(h.deptid,5) ELSE hr_get_level_deptid(h.deptid,4) END as deptid, "
						        +"CASE WHEN emp_diff_code ='C_12067_1330306' and (post_grade_code = 'C_12004_1330054' or post_grade_code = 'C_12004_1324167') THEN 5 ELSE 4 END as deptlevel "
						        +"from hr_employee h where h.person_id = (select t.empid from ar_detail t where t.pk_no = ? )) d,ar_detail_back_email e,hr_employee h1 where "
						        +"e.empid = h1.empid and h1.cpny_id = '78000000' and e.deptid = d.deptid and e.dept_level = d.deptlevel ";
		
		
		String sqlInsertAffirm="INSERT INTO AR_DETAIL_AFFIRM(AR_DETAIL_AFFIRM_NO,AR_DETAIL_NO,AFFIRM_FLAG,AFFIRM_LEVEL,AFFIRMOR_ID,CREATE_DATE,CREATE_BY,ACTIVITY) " +
                                "VALUES(AR_DETAIL_AFFIRM_SEQ.NEXTVAL,?,'0',?,?,SYSDATE,?,'1')";

		String sqlAffirmEmail = "SELECT T.EMAIL FROM HR_EMPLOYEE T WHERE T.PERSON_ID = ? AND T.CPNY_ID in ('78000000','59000000','63000000')";

//		String sqlDeptId = "SELECT D.DEPTID,D.DEPT_LEVEL FROM HR_DEPARTMENT D,(SELECT HD.DEPTID,HD.DEPTNAME,LTRIM(SYS_CONNECT_BY_PATH(HD.DEPTID, ','), ',') AS DEPT_ID"
//				           +"FROM HR_DEPARTMENT HD WHERE HD.DEPTID = (SELECT DEPTID FROM HR_EMPLOYEE HR WHERE HR.Person_Id = (select t.empid from ar_detail t where t.pk_no = '17372506'))"
//				           +"START WITH HD.DEPT_LEVEL = (SELECT MIN(DEPT_LEVEL) FROM HR_DEPARTMENT) CONNECT BY PRIOR HD.DEPTID = HD.PARENT_DEPT_ID) A WHERE INSTR(A.DEPT_ID, D.DEPTID) > 0"
//				           +"AND D.DEPT_LEVEL IN ('4','5','6') ORDER BY D.DEPT_LEVEL DESC";
//		
///		String sqlConfirmEmail = "select h.person_id,h.email from ar_detail_back_email t ,hr_employee h where t.empid = h.empid and h.cpny_id = '78000000' and t.deptid = ? and t.dept_level = ?";

		//	考勤员验证SQL
		String getSupervisorItemId_sql = " select a.item_group_id from ar_supervisor a where a.supervisor_id = ? ";

		Logger.getLogger(getClass()).debug(sqlSelect);
		Logger.getLogger(getClass()).debug(sqlCheck);
		Logger.getLogger(getClass()).debug(sqlCheck1);
		Logger.getLogger(getClass()).debug(sqlCheck2);
		Logger.getLogger(getClass()).debug(sqlInsertBack);
		Logger.getLogger(getClass()).debug(sql);
		Logger.getLogger(getClass()).debug(sqlInsertAffirm);

		try {
			conn.setAutoCommit(false);
			if (details != null) {
				ps = conn.prepareStatement(sql);
				insertPs = conn.prepareStatement(sqlInsertBack);				
				for (int i = 0; i < details.length; i++) {
					//DICC考勤明细维护时除了休息日加班和节假日加班都不可维护为8小时以上
					if (admin.getCpnyId().equals("78000000") && !request.getParameter("item_" + details[i]).equals("31") && !request.getParameter("item_" + details[i]).equals("32") && NumberUtil.parseFloat(request.getParameter("quantity_" + details[i])) > 8 ) {
						errorList.add("考勤长度不可大于8小时!");
						continue;
					}
					//DICC考勤明细维护时休息日加班和节假日加班都不可维护为16小时以上
					if (admin.getCpnyId().equals("78000000") && (request.getParameter("item_" + details[i]).equals("31") || request.getParameter("item_" + details[i]).equals("32")) && NumberUtil.parseFloat(request.getParameter("quantity_" + details[i])) > 16 ) {
						errorList.add("加班考勤长度不可大于16小时!");
						continue;
					}
					
					psCheck = conn.prepareStatement(sqlSelect);
					psCheck.setInt(1, NumberUtil.parseInt(details[i]));
					rs = psCheck.executeQuery();
					Logger.getLogger(getClass()).debug(sqlSelect);
					if (rs.next()) {

						//根据考勤员权限进行验证
						pst1 = conn.prepareStatement(getSupervisorItemId_sql);
						pst1.setString(1, admin.getAdminID());
						rs2 = pst1.executeQuery();

						String itemStr[] = null;

						if (rs2.next()) {
							String itemId = rs2.getString(1);
							if (itemId != null) {
								itemStr = itemId.split(",");
							}
						}

						pst1 = conn.prepareStatement(sqlCheck);
						pst1.setInt(1, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
						rs1 = pst1.executeQuery();
						if (rs1.next()) {
							item_group_code = rs1.getString(1);
							item_group_name = rs1.getString(2);
						}

						if (itemStr.length > 0) {
							for (String string : itemStr) {
								if (item_group_code == string.trim() || item_group_code.equals(string.trim())) {
									flag = true;
								}
							}
						}

						if (flag == false) {
							errorList.add("当前考勤员 [" + admin.getChineseName() + "],没有修改 [" + item_group_name + "] 的权限");
							continue;
						}

						// 根据加班上限进行验证
						if (essSysparam.getOtApplyMaxHours() && item_group_code.equals("OverTimeGroup")) {

							psCheck = conn.prepareStatement(sqlCheck1);
							psCheck.setString(1, rs.getString("EMPID"));
							psCheck.setString(2, rs.getString("AR_MONTH_STR"));
							rsCheck = psCheck.executeQuery();
							if (rsCheck.next()) {
								limitOt = rsCheck.getInt("LIMIT_OT");

								psCheck = conn.prepareStatement(sqlCheck2);
								psCheck.setString(1, rs.getString("EMPID"));
								psCheck.setString(2, rs.getString("AR_MONTH_STR"));
								psCheck.setInt(3, NumberUtil.parseInt(details[i]));
								rsCheck1 = psCheck.executeQuery();

								float paraQuantity = NumberUtil.parseFloat(request.getParameter("quantity_" + details[i]));
								;
								if (rsCheck1.next()) {
									quantity = rsCheck1.getFloat("QUANTITY");
								}

								if (limitOt - quantity - paraQuantity < 0) {
									errorList.add("员工 [" + rs.getString("CHINESENAME") + "] ,剩余加班小时数不足,修改失败!!!");
									continue;
								}
							} else {
								errorList.add("员工 [" + rs.getString("CHINESENAME") + "],没有加班限制,不能修改加班!!!");
								continue;
							}
						}
					}
									
					
//					String deptEmpid = "";
//					
//					int updateFlag =-1;
//					if (rsSelectDeptEmpid.next()) {
//						deptEmpid = rsSelectDeptEmpid.getString("EMPID");
//						confirmEmail = rsSelectDeptEmpid.getString("EMAIL");
//						updateFlag = 0; 
//					}
					
					
					ArrayList affirmList = new ArrayList() ;
					affirmList = (ArrayList) this.getAffirmorArModify1(rs.getString("EMPID"), "ArModifyApply", essSysparam.isContainsOwner()) ;
						
					
					insertPs.setString(1, admin.getAdminID());
					///insertPs.setString(2, deptEmpid);
					insertPs.setInt(2, NumberUtil.parseInt(details[i]));
					
					
					insertPs.addBatch();
					
					//取出决裁者
					String AffirmorIdFirst ="" ;
					for(int j=0;j<affirmList.size();j++){
						EssAffirmor arAffirmList = new EssAffirmor();
						arAffirmList=(EssAffirmor)affirmList.get(j);
						insertAffirmPs = conn.prepareStatement(sqlInsertAffirm);
						insertAffirmPs.setInt(1, NumberUtil.parseInt(details[i]));
						insertAffirmPs.setInt(2, arAffirmList.getAffirmLevel());
						insertAffirmPs.setString(3, arAffirmList.getAffirmorId());
						insertAffirmPs.setString(4, admin.getAdminID());
						insertAffirmPs.executeUpdate();
						if(j==0){
							AffirmorIdFirst = arAffirmList.getAffirmorId();
						}
					}
					
					psAffirmEmail = conn.prepareStatement(sqlAffirmEmail);//取出第一级决裁者的邮箱地址
					psAffirmEmail.setString(1, AffirmorIdFirst);
					rsAffirmEmail = psAffirmEmail.executeQuery();
					
					int updateFlag =-1;
					if (rsAffirmEmail.next()) {
						affirmEmail = rsAffirmEmail.getString("EMAIL");
						updateFlag = 0; 
					}
					
					
					

					ps.setInt(1, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
					ps.setDouble(2, NumberUtil.parseDouble(request.getParameter("quantity_" + details[i])));
					ps.setString(3, StringUtil.checkNull(request.getParameter("lock_" + details[i]), "Y"));
					
					ps.setString(4, StringUtil.checkNull(request.getParameter("remark_" + details[i])));
					
				
					ps.setInt(5, updateFlag);
					
					ps.setInt(6, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
					ps.setInt(7, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
					ps.setInt(8, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
					ps.setString(9, admin.getCpnyId());
					ps.setInt(10, NumberUtil.parseInt(details[i]));
					

					Logger.getLogger(getClass()).debug("Update PK_NO : " + details[i]);

					ps.addBatch();
					
					//修改  勤态修改发送邮件
//					if("78000000".equals(admin.getCpnyId()) && affirmEmail !=""){	元
						if(affirmEmail !=""){	
						
						
							//SimpleMap simpleMap=(SimpleMap)applyResult.get(i);
						    content.append("<br>").append("姓名:").append(rs.getString("CHINESENAME"));
							content.append("<br>").append("考勤日期:").append(StringUtil.checkNull(rs.getString("AR_DATE_STR")));
							//content.append("<br>").append("考勤班次:").append(StringUtil.checkNull(simpleMap.getString("SHIFT_NAME")));
							//content.append("<br>").append("考勤项目:").append(StringUtil.checkNull(simpleMap.getString("ITEM_NAME")));
//							content.append("<br>").append("修改原因:").append(StringUtil.checkNull(rs.getString("REMARK")));
							content.append("<br>").append("修改原因:").append(StringUtil.checkNull(request.getParameter("remark_" + details[i])));
							content.append("<br>");
							content.append("----------------------------");
							
					}
					//推送
					DooPushAPI.insertNotice(DooPushAPI.APPLY_TYPE_AR,details[i]);
				}//循环结束
				Logger.getLogger(getClass()).debug("Lines to Update : " + details.length);

				insertPs.executeBatch();
				Logger.getLogger(getClass()).debug(sqlInsertBack);
				ps.executeBatch();
				
			   
			}
			conn.commit();
			
			if(affirmEmail !="")
			{
				emailAddress=affirmEmail;
				content.append("<br>");
				content.append("系统决裁页面位置:ESS系统 > 考勤决裁 > 考勤修改决裁");
				content.append("<br>");
				content.append("--------------------------------------------------------------------------");
				content.append("<br>");
		
			    content.append("<br>").append("<a href=" + url + " target=\"_blank\">点击此处登陆e-HR决裁</a>")
			    .append("<br>"+"斗山工程机械(中国)有限公司") ;
			    String emailTitle="考勤明细修改申请";	
			    inputData.setString("EMAIL_TITLE", emailTitle);

			    // set email content
			    inputData.setString("EMAIL_CONTNT", content.toString());

			    inputData.setString("RCVR_EMAIL_ADDR", emailAddress);

			    new Mail().sendMail(inputData) ;	
			}
				
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs1, pst1);
			SqlUtil.close(insertPs);
			SqlUtil.close(rsCheck, psCheck);
			SqlUtil.close(rsCheck1, psCheck);
			SqlUtil.close(rs, ps, conn);
			SqlUtil.close(insertAffirmPs);
			SqlUtil.close(rsAffirmEmail, psAffirmEmail);
		}

		return errorList;
	}

	
	public List delArDetail(HttpServletRequest request, AdminBean admin) {
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		PreparedStatement updatePs = null;
		PreparedStatement insertPs = null;
		List errorList = new ArrayList();

		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		//String post_grade_code = "" ;
		String item_group_code = "";
		String item_group_name = "";
		boolean flag = false;

		String[] details = request.getParameterValues("details");
		String sql = "DELETE FROM AR_DETAIL WHERE PK_NO = ?";
		String sqlInsertBack = "INSERT INTO AR_DETAIL_BACK " + "(PK_NO, EMPID, AR_ITEM_NO, FROM_TIME, " + "TO_TIME, SHIFT_NO, AR_DATE_STR, AR_MONTH_STR, QUANTITY, " + "LOCK_YN, DATE_TYPE, UNIT,CREATE_DATE,CREATED_BY,OPERATION) "
				+ "SELECT AR_DETAIL_BACK_SEQ.NEXTVAL,EMPID, AR_ITEM_NO, FROM_TIME," + "TO_TIME, SHIFT_NO, AR_DATE_STR,AR_MONTH_STR, QUANTITY, " + "LOCK_YN, DATE_TYPE, UNIT,SYSDATE,?,'DELETE' " + "FROM AR_DETAIL WHERE PK_NO = ? ";

		//String updateSql = "UPDATE AR_DETAIL SET LOCK_YN = 'Y' WHERE EXISTS (SELECT T.EMPID FROM AR_DETAIL T WHERE T.PK_NO = ? " + "AND T.EMPID = AR_DETAIL.EMPID AND T.AR_DATE_STR = AR_DETAIL.AR_DATE_STR)";
		
		String updateSql = "UPDATE AR_DETAIL SET LOCK_YN = 'Y' WHERE AR_DETAIL.PK_NO = ?";

		//		考勤员验证SQL
		String getSupervisorItemId_sql = "select a.item_group_id from ar_supervisor a where a.supervisor_id = ?";
		String sqlCheck = "SELECT T.ITEM_GROUP_CODE , GET_CODE_NAME(T.ITEM_GROUP_CODE) ITEM_GROUP_NAME FROM AR_ITEM T WHERE T.ITEM_NO = ?";

		Logger.getLogger(getClass()).debug(sql);
		Logger.getLogger(getClass()).debug(sqlInsertBack);
		Logger.getLogger(getClass()).debug(updateSql);
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			insertPs = conn.prepareStatement(sqlInsertBack);
			updatePs = conn.prepareStatement(updateSql);
			for (int i = 0; i < details.length; i++) {

				//				根据考勤员权限进行验证
				pst1 = conn.prepareStatement(getSupervisorItemId_sql);
				pst1.setString(1, admin.getAdminID());
				rs1 = pst1.executeQuery();

				String itemStr[] = null;

				if (rs1.next()) {
					String itemId = rs1.getString(1);
					if (itemId != null) {
						itemStr = itemId.split(",");
					}
				}

				pst1 = conn.prepareStatement(sqlCheck);
				pst1.setInt(1, NumberUtil.parseInt(request.getParameter("item_" + details[i])));
				rs1 = pst1.executeQuery();
				if (rs1.next()) {
					item_group_code = rs1.getString(1);
					item_group_name = rs1.getString(2);
				}

				for (String string : itemStr) {

					if (item_group_code == string.trim() || item_group_code.equals(string.trim())) {
						flag = true;
					}
				}

				if (flag == false) {
					errorList.add("当前考勤员 [" + admin.getChineseName() + "],没有删除 [" + item_group_name + "] 的权限");
					continue;
				}

				insertPs.setString(1, admin.getAdminID());
				insertPs.setInt(2, Integer.parseInt(details[i]));
				insertPs.addBatch();

				ps.setInt(1, Integer.parseInt(details[i]));
				ps.addBatch();

				updatePs.setInt(1, Integer.parseInt(details[i]));
				updatePs.addBatch();
			}
			Logger.getLogger(getClass()).debug("Lines to Delete : " + details.length);

			if (flag == true) {
				insertPs.executeBatch();
				updatePs.executeBatch();
				ps.executeBatch();
			}

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ee) {
			}
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs1, pst1);
			SqlUtil.close(insertPs);
			SqlUtil.close(ps, conn);
		}
		return errorList;
	}

	private String getCNunit(String unit) {
		unit = StringUtil.checkNull(unit, "HOUR");
		String cn = null;
		if (unit.equals("DAY"))
			cn = "天";
		if (unit.equals("HOUR"))
			cn = "小时";
		if (unit.equals("MINUTE"))
			cn = "分钟";
		return cn;
	}

	/**
	 * retrieve ar item detail
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArItem(Object param) throws GlRuntimeException {

		List result;

		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveArItem", param);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve ar item detail Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve ar detail sqlcontent
	 * 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public String getDetailSql(List itemList) throws GlRuntimeException {

		String sql = "";
		try {
			for (int i = 0; i < itemList.size(); i++) {
				SimpleMap map = (SimpleMap) itemList.get(i);
				sql += ",SUM(CASE AR_ITEM_NO WHEN " + map.getString("ITEM_NO") + " THEN QUANTITY ELSE 0 END) AS \"" + map.getString("ITEM_ID") + "\"";
			}

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			e.printStackTrace();
		}
		return sql;
	}

	/**
	 * retrieve ar detailStatistics data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArDetailStatisticsData(Object parameterObject) throws GlRuntimeException {

		List result;

		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveArDetailStatisticsData", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve ar detailStatistics data Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve ar detailStatistics data end
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List retrieveArDetailStatisticsData_end(Object parameterObject) throws GlRuntimeException {

		List result;

		try {
			result = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveArDetailStatisticsData_end", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve ar detailStatistics data Exception. ", e);
		}
		return result;
	}

	public List retrieveDept() throws GlRuntimeException {

		List result;

		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ar.common.retrieveDept");

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve dept by deptid_section Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve ar dailydetailStatistics head data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public Object retrieveArDailyDetailStatisticsData_head(Object parameterObject) throws GlRuntimeException {

		Object result;

		try {
			result = commonSQLMapAdapter.executeQuery("ar.common.retrieveArDailyDetailStatisticsData_head", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve ar dailydetailStatistics head data Exception. ", e);
		}
		return result;
	}

	/**
	 * retrieve ar dailydetailStatistics data
	 * 
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public Object retrieveArDailyDetailStatisticsData(Object parameterObject) throws GlRuntimeException {

		Object result;

		try {
			result = commonSQLMapAdapter.executeQuery("ar.common.retrieveArDailyDetailStatisticsData", parameterObject);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve ar dailydetailStatistics data Exception. ", e);
		}
		return result;
	}

	public void setLoginCnpyId(String loginCnpyId) {
		this.loginCnpyId = loginCnpyId;
	}
	
	/**
	 * retrieve person detail information by paging
	 * 
	 * @param sDate
	 * @param eDate
	 * @param key
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getArDetailPerson(String sDate, String eDate, String key, String deptid, String itemNo, String sLength, String eLength, int currentPage, int pageSize, String empType, String personId, String tableName) throws GlRuntimeException {

		List list;
		SimpleMap parameterObject = new SimpleMap();

		try {
			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("personId", personId);
			parameterObject.setString("deptid", deptid);
			parameterObject.setString("supervisor", this.loginID);
			parameterObject.setString("cnpyId", this.loginCnpyId);
			parameterObject.setString("itemNo", itemNo);
			parameterObject.setString("sLength", sLength);
			parameterObject.setString("eLength", eLength);
			parameterObject.setString("empType", empType);
			parameterObject.setString("tableName", tableName);
			parameterObject.set("startRownum", currentPage * pageSize);
			parameterObject.set("endRownum", (currentPage + 1) * pageSize);

			list = commonSQLMapAdapter.executeQueryForMulti("ar.common.RetrieveAttDetailPerson", parameterObject);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve person detail information Exception. ", e);
		}
		return list;
	}

	
	public int getArDetailPersonCnt(String sDate, String eDate, String key, String deptid, String itemNo, String sLength, String eLength, String empType, String personId, String tableName) throws GlRuntimeException {

		SimpleMap parameterObject = new SimpleMap();
		int result;
		try {

			parameterObject.setString("sDate", sDate.replaceAll("-", "/"));
			parameterObject.setString("eDate", eDate.replaceAll("-", "/"));
			parameterObject.setString("condition", key);
			parameterObject.setString("personId", personId);
			parameterObject.setString("deptid", deptid);
			parameterObject.setString("supervisor", this.loginID);
			parameterObject.setString("cnpyId", this.loginCnpyId);
			parameterObject.setString("itemNo", itemNo);
			parameterObject.setString("sLength", sLength);
			parameterObject.setString("eLength", eLength);
			parameterObject.setString("empType", empType);
			parameterObject.setString("tableName", tableName);

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ar.common.RetrieveAttDetailPersonCnt", parameterObject).toString());

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Retrieve person detail count information Exception. ", e);
		}
		return result;
	}
	
	public List getAffirmorArModify(String empId, String applyType,boolean containsOwner) {
		//System.out.println("++++++++++++++++++++++++++++++_______________)))))))))))))))))))))))))))))))");
		
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql="";
		int ownerLevel=0;
		int affirmMinLevel=0;
		try {
		//取出决裁对象为员工号时，自己的决裁级别
		sql= " select NVL(MAX(SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL), 0) ownerLevel "+
		            " from SY_AFFIRM_RELATION_TB, HR_EMPLOYEE HR_AFFIRMOR "+
		            " where SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 "+
		            " AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_AFFIRMOR.PERSON_ID "+
		            " AND HR_AFFIRMOR.PERSON_ID=SY_AFFIRM_RELATION_TB.AFFIRMOR_ID "+
		            " AND HR_AFFIRMOR.Person_Id =? "+
		            " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID =? ";
		Logger.getLogger(getClass()).debug(sql);
		pstmt = conn.prepareStatement(sql);		
		pstmt.setString(1, empId);
		pstmt.setString(2, applyType);
		rs = pstmt.executeQuery();
		if(rs.next() && NumberUtil.parseInt(rs.getString("OWNERLEVEL"))!=0){
			ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
		}else{//取出决裁对象为部门时，自己的决裁级别
			sql= " select NVL(MAX(SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL), 0) ownerLevel "+
	          " from SY_AFFIRM_RELATION_TB, HR_EMPLOYEE HR_AFFIRMOR "+
	          " where SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 "+
	          " AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_AFFIRMOR.DEPTID "+
	          " AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
	          " AND HR_AFFIRMOR.Person_Id =? "+
	          " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID =? ";
			Logger.getLogger(getClass()).debug(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empId);
			pstmt.setString(2, applyType);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
			}
		}
		if(containsOwner && ownerLevel!=0){
			affirmMinLevel=ownerLevel-1;
		}else{
			affirmMinLevel=ownerLevel;
		}
		 sql =  " SELECT t.*,rownum num from (select SY_AFFIRM_RELATION_TB.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, HR_AFFIRMOR.Chinese_Pinyin, HR_AFFIRMOR.KOREANNAME, SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL"
				+ " FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE, HR_EMPLOYEE HR_AFFIRMOR"
				+ " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.Person_Id"
				+ " AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id"
				+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL > "+affirmMinLevel
				+ " AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID='"				
				+ applyType
				+ "'"
				+ " AND HR_EMPLOYEE.Person_Id ='"
				+ empId + "'" + " ORDER BY AFFIRM_LEVEL) t";
		Logger.getLogger(getClass()).debug(sql);
	
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		if(!rs.next()){
			sql = " SELECT DEPTID FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ";  //取得人员所属部门，不取它的上级部门
			//sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
            //" FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			Logger.getLogger(getClass()).debug(sql);
			while (rs.next())
			{
				sql = "  SELECT t.*,rownum num from(select SY_AFFIRM_RELATION_TB.AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME,HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME, " 
					+ "  SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL " 
					+ "  FROM SY_AFFIRM_RELATION_TB, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR " 
					+ "  WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HD.DEPTID " 
					+ "  AND SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = HR_AFFIRMOR.Person_Id " 
					+ "  AND SY_AFFIRM_RELATION_TB.AFFIRM_LEVEL > "+affirmMinLevel
					+ "  AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID ='" +applyType +"'  AND HD.DEPTID ='"
					+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
				pstmt = conn.prepareStatement(sql);
				rs2 = pstmt.executeQuery(sql);
				Logger.getLogger(getClass()).debug(sql);
				while (rs2.next()) {
					EssAffirmor vb = new EssAffirmor();
					vb.setAffirmLevel(rs2.getInt("num"));
					vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
					vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
					result.add(vb);
				}
				
				if (result.size() > 0)
				{
					break ;
				}	
			}
		}else{

			EssAffirmor vb = new EssAffirmor();
			vb.setAffirmLevel(rs.getInt("num"));
			vb.setAffirmorId(rs.getString("affirmor_id"));
			vb.setAffirmorName(rs.getString("AFFIRMOR_NAME"));
			result.add(vb);
			while (rs.next()) {
				vb = new EssAffirmor();
				vb.setAffirmLevel(rs.getInt("num"));
				vb.setAffirmorId(rs.getString("affirmor_id"));
				vb.setAffirmorName(rs.getString("AFFIRMOR_NAME"));
				result.add(vb);	
			}
		}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}	

public List getAffirmorArModify1(String empId, String applyType,boolean containsOwner) {
	//System.out.println("++++++++++++++++++++++++++++++_______________)))))))))))))))))))))))))))))))");
	
	List result = new ArrayList();
	Connection conn = ConnBean.getConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	String sql="";
	int ownerLevel=0;
	int affirmMinLevel=0;
	try {
	//取出决裁对象为部门时，自己的决裁级别
		sql= " select NVL(MAX(SAR.AFFIRM_LEVEL), 0) ownerLevel "+
          " from SY_AFFIRM_RELATION_TB_AUTO SAR, HR_EMPLOYEE HR_AFFIRMOR "+
          " where SAR.AFFIRM_DEPTID = HR_AFFIRMOR.DEPTID "+
          " AND SAR.AFFIRMOR_ID=HR_AFFIRMOR.PERSON_ID"+
          " AND SAR.AFFIRM_DUTY IS NOT NULL "+	
          " AND SAR.MODULE = 'AR' "+
          " AND HR_AFFIRMOR.Person_Id =? ";
		Logger.getLogger(getClass()).debug(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empId);
		rs = pstmt.executeQuery();
		if(rs.next()){
			ownerLevel=NumberUtil.parseInt(rs.getString("OWNERLEVEL"));
		}
	if(containsOwner && ownerLevel!=0){
		affirmMinLevel=ownerLevel-1;
	}else{
		affirmMinLevel=ownerLevel;
	}

		sql = " SELECT DEPTID FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ";  //取得人员所属部门，不取它的上级部门
		//sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = (SELECT DEPTID " + 
        //" FROM HR_EMPLOYEE HR WHERE HR.PERSON_ID = '" + empId + "' ) CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);
		Logger.getLogger(getClass()).debug(sql);
		while (rs.next())
		{
			sql = "  SELECT t.*,rownum num from(select NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) AFFIRMOR_ID, HR_AFFIRMOR.CHINESENAME AFFIRMOR_NAME, " 
				+ "  HR_AFFIRMOR.Chinese_Pinyin,HR_AFFIRMOR.KOREANNAME,SAR.AFFIRM_LEVEL,SAR.AFFIRM_DUTY " 
				+ "  FROM SY_AFFIRM_RELATION_TB_AUTO SAR, HR_DEPARTMENT HD, HR_EMPLOYEE HR_AFFIRMOR, " 
				+ " (SELECT * FROM SY_AFFIRM_RELATION_TB_ACT WHERE AFFIRM_TYPE_ID = '"+applyType+"') SAA"
				+ "  WHERE SAR.AFFIRM_DEPTID = HD.DEPTID " 
				+ "  AND NVL(SAA.AFFIRMOR_ACT_ID, SAR.AFFIRMOR_ID) = HR_AFFIRMOR.Person_Id " 
				+ "  AND SAR.AFFIRMOR_ID = SAA.AFFIRMOR_ID(+) "
				+ "  AND SAR.MODULE ='AR'"
				+ "  AND SAR.AFFIRM_LEVEL > "+affirmMinLevel
				+ " AND SAR.AFFIRM_DUTY IS NOT NULL "
				+ "  AND HD.DEPTID ='"
				+ rs.getString("DEPTID") +"' ORDER BY AFFIRM_LEVEL) t";
			pstmt = conn.prepareStatement(sql);
			rs2 = pstmt.executeQuery(sql);
			System.out.println("applyType"+applyType+"11"+ rs.getString("DEPTID")+"22"+affirmMinLevel);
			Logger.getLogger(getClass()).debug(sql);
			while (rs2.next()) {
				EssAffirmor vb = new EssAffirmor();
				vb.setAffirmLevel(rs2.getInt("num"));
				vb.setAffirmorId(rs2.getString("AFFIRMOR_ID"));
				vb.setAffirmorName(rs2.getString("AFFIRMOR_NAME"));
				result.add(vb);
				
				if (rs2.getString("AFFIRM_DUTY").equals("C_12005_93775")){
					break ;
				}
			}
			
			if (result.size() > 0)
			{
				break ;
			}	
		}
	} catch (Exception e) {
		Logger.getLogger(getClass()).debug(e.toString());
		e.printStackTrace();
	} finally {
		SqlUtil.close(rs, pstmt, conn);
	}
	return result;
}	
}
