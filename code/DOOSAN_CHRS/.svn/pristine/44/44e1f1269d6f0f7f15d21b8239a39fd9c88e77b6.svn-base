package com.ait.evs;

import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Hashtable;
import java.util.List;

import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class EvsScore {
    /**
     * Logger for this class
     */
    private static final Logger   logger = Logger.getLogger(EvsScore.class);

    private static ServiceLocator services;

    public EvsScore() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
	    logger.error("EvsScore()", e); 
        } catch (Exception e) {
	    logger.error("EvsScore()", e); 
        }
    }

    public String getEmpOpoerateByMaster(String period, String empID,
            String masterID) throws DataAccessException {
        String sql = "select EV_OPERATE_ID from EVS_OPERATE_V where "
                + "EV_PERIOD_ID = ? AND EV_EMP_ID = ? AND EV_MASTER = ?";
        Connection con = null;
        ResultSet rs = null;
        String operate = "";
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, period);
            pstmt.setString(2, empID);
            pstmt.setString(3, masterID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                operate = (rs.getString("EV_OPERATE_ID") != null) ? rs
                        .getString("EV_OPERATE_ID") : "";
            }
        } catch (SQLException sqle) {
	    logger.error("getEmpOpoerateByMaster(String, String, String)", sqle); 
            throw new DataAccessException(
                    "cant execute query for getEmpOpoerateByMaster statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEmpOpoerateByMaster statistics",
                    sle);
        } catch (Exception e) {
	    logger.error("getEmpOpoerateByMaster(String, String, String)", e); 
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return operate;
    }
    //当前评价流程否超出评价时间范围
    public boolean getIsOverPeriod(String period, String evProcessId,String evTypeId) throws DataAccessException {
        String sql = 
       " SELECT  * FROM EVS_TYPE_PROCESS  " +
       " WHERE ev_period_id=?  AND ev_type_id=?	 AND  ev_process_id=?	"+
       " AND TO_DATE(TO_CHAR(SYSDATE,'yyyymmdd'),'yyyymmdd') BETWEEN EV_PROCESS_SDATE AND EV_PROCESS_EDATE ";
        Connection con = null;
        ResultSet rs = null;
        boolean isOverPeriod = false;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, period);
            pstmt.setString(2, evTypeId);
            pstmt.setString(3, evProcessId);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                isOverPeriod = true;
            }
        } catch (SQLException sqle) {
	    logger.error("getIsOverPeriod(String, String, String)", sqle); 
            throw new DataAccessException(
                    "cant execute query for getIsOverPeriod statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getIsOverPeriod statistics",
                    sle);
        } catch (Exception e) {
	    logger.error("getIsOverPeriod(String, String, String)", e); 
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return isOverPeriod;
    }

    /*
     * 获得默认分值 参数 细则编号
     */
    public Hashtable getDefaultByDetailID(int itemDetailSeq)
            throws DataAccessException {
        String sql = "select * from EVS_DEFAULT_MARK_V where SEQ_EV_ITEM_DETAIL = ?";
        Hashtable<String, String> h = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemDetailSeq);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                h = new Hashtable<String, String>();
                h.put("Marks", (rs.getString("EV_MARKS_VALUE") != null) ? rs
                        .getString("EV_MARKS_VALUE") : "");
                h.put("MarksName", (rs.getString("EV_MARKS_NAME") != null) ? rs
                        .getString("EV_MARKS_NAME") : "");
                h.put("DefaultMark", (rs.getString("EV_MARKS_DEFAULT")!=null)?rs.getString("EV_MARKS_DEFAULT"):"");
                h.put("CurrentMark", (rs.getString("EV_MARK") != null) ? rs
                        .getString("EV_MARK") : "");
                h.put("CurrentIdea", (rs.getString("EV_IDEA") != null) ? rs
                        .getString("EV_IDEA") : "");
            }
        } catch (SQLException sqle) {
	    logger.error("getDefaultByDetailID(int)", sqle); 
            throw new DataAccessException(
                    "cant execute query for getDefaultByDetailID statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getDefaultByDetailID statistics",
                    sle);
        } catch (Exception e) {
	    logger.error("getDefaultByDetailID(int)", e); 
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return h;
    }

    public int saveEvsMark(int detailSeq, String processID, float mark,
            String idea) {
        String sql = "{ call EVS_DETAIL_MARK_P(?,?,?,?,?) }";
        int count = 0;
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, detailSeq);
            cs.setString(2, processID);
            cs.setFloat(3, mark);
            cs.setString(4, StringUtil.toCN(idea));
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            count = cs.getInt(5);
            return count;
        } catch (Exception sqe) {
		logger.error("saveEvsMark() - EvsScore.java:saveEvsMark:refresh: sqe=" + sqe); 
            return 0;
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
		    logger.error("saveEvsMark() - " + e.getMessage()); 
            }
        }
    }

    public int evsSave(String periodID, String empID) {
        String sql = "{ call EVS_SAVE_P(?,?,?) }";
        int count = 0;
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, periodID);
            cs.setString(2, empID);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            count = cs.getInt(3);
            return count;
        } catch (Exception sqe) {
		logger.error("evsSave() - EvsScore.java:evsSave:refresh: sqe=" + sqe); 
            return 0;
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
		    logger.error("evsSave() - " + e.getMessage()); 

            }
        }
    }

    
    public int evsSubmit(String periodID, String empID) {
        String sql = "{ call evs_submit_p_1(?,?,?) }";
        int count = 0;
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, periodID);
            cs.setString(2, empID);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            count = cs.getInt(3);
            return count;
        } catch (Exception sqe) {
		logger.error("evsSubmit() - EvsScore.java:evsSubmit:refresh: sqe=" + sqe); 
            return 0;
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
		    logger.error("evsSubmit() - " + e.getMessage()); 
            }
        }
    }

    public int evsReject(String periodID, String empID) {
        String sql = "{ call EVS_REJECT_P(?,?,?) }";
        int count = 0;
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, periodID);
            cs.setString(2, empID);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            count = cs.getInt(3);
            return count;
        } catch (Exception sqe) {
		logger.error("evsReject() - EvsScore.java:evsReject:refresh: sqe=" + sqe); 
            return 0;
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
		    logger.error("evsReject() - " + e.getMessage()); 
            }
        }
    }
    /**
     * 批量否决
     * @param lEmp
     * @return
     */
    public int evsReject(List lEmp) {
        String sql = "{ call EVS_REJECT_P(?,?,?) }";
        int count = 0;
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall(sql);
            if(null!=lEmp){
                for(int i=0,j=lEmp.size();i<j;i++){
            	    SimpleMap map = (SimpleMap) lEmp.get(i);
                    int k = 0;
                    cs.setString(++k, map.getString("EV_PERIOD_ID"));
                    cs.setString(++k, map.getString("EV_EMP_ID"));
                    cs.registerOutParameter(++k, Types.INTEGER);
                    cs.execute();
                }
            }
            con.commit();
            return count;
        } catch (Exception sqe) {
		logger.error("evsReject() - EvsScore.java:evsReject:refresh: sqe=" + sqe); 
            return 0;
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
		    logger.error("evsReject() - " + e.getMessage()); 
            }
        }
    }
    /**
     * 评价打分 分数保存 员工总分更新
     * @param map
     * @throws DataAccessException
     */
    public void evsSave(List empList) throws DataAccessException {

	Connection con = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    this.evsItemMarksSave(empList, con);
	    this.evsEmpMarkUpdate(empList, con);
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsSave()", e);
	    }
	    logger.error("evsSave()", sqle);
	    throw new DataAccessException("cant execute  evsSave statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsSave()", e1);
	    }
	    logger.error("evsSave()", sle);
	    throw new DataAccessException("cant get connection for evsSave",
		    sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsSave()", e1);
	    }
	    logger.error("evsSave()", e);
	} finally {
	    SqlUtil.close(con);
	}
    }

    /**
     * 员工评价总分数保存
     * @param map
     * @throws DataAccessException
     */
    public void evsEmpMarkUpdate(List empList) throws DataAccessException {

	StringBuffer updateSql = new StringBuffer();
	updateSql.append(" UPDATE EVS_EMP SET   ");
	updateSql.append(" EV_MARK=( ");
	updateSql.append(" 	   SELECT SUM(EV_MARKS) FROM EVS_MASTER_MARK_V ");
	updateSql.append(" 	   	WHERE EV_EMP_ID=? ");
	updateSql.append("		AND EV_PERIOD_ID= ? ");
	updateSql.append("		GROUP BY EV_EMP_ID,EV_PERIOD_ID ");
	updateSql.append(" )");
	updateSql.append(" WHERE EV_EMP_ID=? ");
	updateSql.append(" AND EV_PERIOD_ID= ? ");
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    if (null != empList) {
		if (empList.size() > 0) {

		    pstmt = con.prepareStatement(updateSql.toString());
		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);
			int k = 0;
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		}
	    }
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsEmpMarkUpdate()", e);
	    }
	    logger.error("evsEmpMarkUpdate()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsEmpMarkUpdate statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpMarkUpdate()", e1);
	    }
	    logger.error("evsEmpMarkUpdate", sle);
	    throw new DataAccessException(
		    "cant get connection for evsEmpMarkUpdate", sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpMarkUpdate()", e1);
	    }
	    logger.error("evsEmpMarkUpdate()", e);
	} finally {
	    SqlUtil.close(pstmt, con);
	}
    }

    /**
     * 员工评价总分数保存
     * @param map
     * @throws DataAccessException
     */
    public void evsEmpMarkUpdate(List empList, Connection con)
	    throws DataAccessException {

	StringBuffer updateSql = new StringBuffer();
	updateSql.append(" UPDATE EVS_EMP SET   ");
	updateSql.append(" EV_MARK=( ");
	updateSql.append(" 	   SELECT SUM(EV_MARKS) FROM EVS_MASTER_MARK_V ");
	updateSql.append(" 	   	WHERE EV_EMP_ID=? ");
	updateSql.append("		AND EV_PERIOD_ID= ? ");
	updateSql.append("		GROUP BY EV_EMP_ID,EV_PERIOD_ID ");
	updateSql.append(" )");
	updateSql.append(" WHERE EV_EMP_ID=? ");
	updateSql.append(" AND EV_PERIOD_ID= ? ");
	PreparedStatement pstmt = null;
	try {
	    if (null != empList) {
		if (empList.size() > 0) {

		    pstmt = con.prepareStatement(updateSql.toString());
		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);
			int k = 0;
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		}
	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsEmpMarkUpdate()", e);
	    }
	    logger.error("evsEmpMarkUpdate()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsEmpMarkUpdate statistics", sqle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpMarkUpdate()", e1);
	    }
	    logger.error("evsEmpMarkUpdate()", e);
	} finally {
	    SqlUtil.close(pstmt);
	}
    }
    /**
     * 员工评价流程保存
     * @param map
     * @throws DataAccessException
     */
    public void evsEmpProcessUpdate(List empList) throws DataAccessException {

	StringBuffer updateSql = new StringBuffer();
	updateSql.append(" UPDATE EVS_EMP SET   ");
	updateSql.append(" EV_PROCESS_ID=( ");
	updateSql.append("    SELECT EV_PROCESS_ID ");
	updateSql.append("     FROM EVS_TYPE_PROCESS ");
	updateSql.append("    WHERE EV_PERIOD_ID = ? ");
	updateSql.append("      AND EV_TYPE_ID = ? ");
	updateSql.append("      AND EV_PROCESS_ORDER = ");
	updateSql.append("	       (SELECT EV_PROCESS_ORDER ");
	updateSql.append("		  FROM EVS_TYPE_PROCESS ");
	updateSql.append("		 WHERE EV_PERIOD_ID = ? ");
	updateSql.append("		   AND EV_TYPE_ID = ? ");
	updateSql.append("		   AND EV_PROCESS_ID = ?) ");
	updateSql.append("	     + 1 ");
	updateSql.append(" ) ");
	updateSql.append(" WHERE EV_EMP_ID=? ");
	updateSql.append(" AND EV_PERIOD_ID= ? ");
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    if (null != empList) {
		if (empList.size() > 0) {

		    pstmt = con.prepareStatement(updateSql.toString());
		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);
			int k = 0;

			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_TYPE_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_TYPE_ID"));
			pstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		}
	    }
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsEmpProcessUpdate()", e);
	    }
	    logger.error("evsEmpProcessUpdate()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsEmpProcessUpdate statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpProcessUpdate()", e1);
	    }
	    logger.error("evsEmpProcessUpdate()", sle);
	    throw new DataAccessException(
		    "cant get connection for evsEmpProcessUpdate", sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpProcessUpdate()", e1);
	    }
	    logger.error("evsEmpProcessUpdate()", e);
	} finally {
	    SqlUtil.close(pstmt, con);
	}
    }
    /**
     * 员工评价流程保存
     * @param map
     * @throws DataAccessException
     */
    public void evsEmpProcessUpdate(List empList,Connection con) throws DataAccessException {

	StringBuffer updateSql = new StringBuffer();
	updateSql.append(" UPDATE EVS_EMP SET   ");
	updateSql.append(" EV_PROCESS_ID=( ");
	updateSql.append("    SELECT EV_PROCESS_ID ");
	updateSql.append("     FROM EVS_TYPE_PROCESS ");
	updateSql.append("    WHERE EV_PERIOD_ID = ? ");
	updateSql.append("      AND EV_TYPE_ID = ? ");
	updateSql.append("      AND EV_PROCESS_ORDER = ");
	updateSql.append("	       (SELECT EV_PROCESS_ORDER ");
	updateSql.append("		  FROM EVS_TYPE_PROCESS ");
	updateSql.append("		 WHERE EV_PERIOD_ID = ? ");
	updateSql.append("		   AND EV_TYPE_ID = ? ");
	updateSql.append("		   AND EV_PROCESS_ID = ?) ");
	updateSql.append("	     + 1 ");
	updateSql.append(" ) ");
	updateSql.append(" WHERE EV_EMP_ID=? ");
	updateSql.append(" AND EV_PERIOD_ID= ? ");
	PreparedStatement pstmt = null;
	try {
	    if (null != empList) {
		if (empList.size() > 0) {

		    pstmt = con.prepareStatement(updateSql.toString());
		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);
			int k = 0;

			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_TYPE_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_TYPE_ID"));
			pstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		}
	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsEmpProcessUpdate()", e);
	    }
	    logger.error("evsEmpProcessUpdate()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsEmpProcessUpdate statistics", sqle);
	}catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpProcessUpdate()", e1);
	    }
	    logger.error("evsEmpProcessUpdate()", e);
	} finally {
	    SqlUtil.close(pstmt);
	}
    }
    /**
     * 评价者是否提交标记保存
     * @param map
     * @throws DataAccessException
     */
    public void evsMasterSubmitted(List empList) throws DataAccessException {

	StringBuffer updateSql = new StringBuffer();
	updateSql.append(" UPDATE EVS_MASTER SET   ");
	updateSql.append(" EV_SUBMITTED=1 ");
	updateSql.append(" WHERE EV_EMP_ID=? ");
	updateSql.append(" AND EV_PERIOD_ID= ? ");
	updateSql.append(" AND EV_PROCESS_ID= ? ");
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    if (null != empList) {
		if (empList.size() > 0) {

		    pstmt = con.prepareStatement(updateSql.toString());
		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);
			int k = 0;
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		}
	    }
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsMasterSubmitted() connection rollback", e);
	    }
	    logger.error("evsMasterSubmitted()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsMasterSubmitted statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsMasterSubmitted() connection rollback ", e1);
	    }
	    logger.error("evsMasterSubmitted()", sle);
	    throw new DataAccessException(
		    "cant get connection for evsMasterSubmitted", sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsMasterSubmitted() connection rollback ", e1);
	    }
	    logger.error("evsMasterSubmitted()", e);
	} finally {
	    SqlUtil.close(pstmt, con);
	}
    }
    /**
     * 评价者是否提交标记保存
     * @param map
     * @throws DataAccessException
     */
    public void evsMasterSubmitted(List empList,Connection con) throws DataAccessException {

	StringBuffer updateSql = new StringBuffer();
	updateSql.append(" UPDATE EVS_MASTER SET   ");
	updateSql.append(" EV_SUBMITTED=1 ");
	updateSql.append(" WHERE EV_EMP_ID=? ");
	updateSql.append(" AND EV_PERIOD_ID= ? ");
	updateSql.append(" AND EV_PROCESS_ID= ? ");
	PreparedStatement pstmt = null;
	try {
	    if (null != empList) {
		if (empList.size() > 0) {

		    pstmt = con.prepareStatement(updateSql.toString());
		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);
			int k = 0;			
			pstmt.setString(++k, map.getString("EV_EMP_ID"));
			pstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			pstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		}
	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsEmpProcessUpdate() connection rollback ", e);
	    }
	    logger.error("evsEmpProcessUpdate()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsEmpProcessUpdate statistics", sqle);
	}catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsEmpProcessUpdate() connection rollback ", e1);
	    }
	    logger.error("evsEmpProcessUpdate()", e);
	} finally {
	    SqlUtil.close(pstmt);
	}
    }
    /**
     * 评价流程项目分数保存
     * @param map
     * @throws DataAccessException
     */
    public void evsItemMarksSave(List empList) throws DataAccessException {

	StringBuffer insertSql = new StringBuffer();
	insertSql.append(" Insert into EVS_ITEM_PROCESS_MARK  ");
	insertSql.append(" (EV_PERIOD_ID, EV_EMP_ID, ");
	insertSql.append(" EV_PROCESS_ID, EV_ITEM_ID, EV_MARK ) ");
	insertSql.append("  Values (?,?,?,?,?) ");

	StringBuffer delSql = new StringBuffer();
	delSql.append(" DELETE EVS_ITEM_PROCESS_MARK  ");
	delSql.append(" WHERE EV_PERIOD_ID=?    ");
	delSql.append(" AND EV_EMP_ID=?");
	delSql.append(" AND EV_PROCESS_ID=?");
	delSql.append(" AND EV_ITEM_ID=?");

	Connection con = null;
	PreparedStatement insertPstmt = null;
	PreparedStatement delPstmt = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    if (null != empList) {
		if (empList.size() > 0) {

		    insertPstmt = con.prepareStatement(insertSql.toString());
		    delPstmt = con.prepareStatement(delSql.toString());

		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);

			int k = 0;
			insertPstmt.setString(++k, map
				.getString("EV_PERIOD_ID"));
			insertPstmt.setString(++k, map.getString("EV_EMP_ID"));
			insertPstmt.setString(++k, map
				.getString("EV_PROCESS_ID"));
			insertPstmt.setString(++k, map.getString("EV_ITEM_ID"));
			insertPstmt.setString(++k, map.getString("EV_MARKS"));
			insertPstmt.addBatch();

			k = 0;
			delPstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			delPstmt.setString(++k, map.getString("EV_EMP_ID"));
			delPstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			delPstmt.setString(++k, map.getString("EV_ITEM_ID"));
			delPstmt.addBatch();
		    }

		    delPstmt.executeBatch();
		    insertPstmt.executeBatch();
		}
	    }
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsItemMarksSave()", e);
	    }
	    logger.error("evsItemMarksSave()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsItemMarksSave statistics", sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsItemMarksSave()", e1);
	    }
	    logger.error("evsItemMarksSave()", sle);
	    throw new DataAccessException(
		    "cant get connection for evsItemMarksSave", sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsItemMarksSave()", e1);
	    }
	    logger.error("evsItemMarksSave()", e);
	} finally {
	    SqlUtil.close(insertPstmt);
	    SqlUtil.close(delPstmt, con);
	}
    }

    /**
     * 评价流程项目分数保存
     * @param map
     * @throws DataAccessException
     */
    public void evsItemMarksSave(List empList, Connection con)
	    throws DataAccessException {

	StringBuffer insertSql = new StringBuffer();
	insertSql.append(" Insert into EVS_ITEM_PROCESS_MARK  ");
	insertSql.append(" (EV_PERIOD_ID, EV_EMP_ID, " );
	insertSql.append("  EV_PROCESS_ID, EV_ITEM_ID, EV_MARK ) ");
	insertSql.append("  Values (?,?,?,?,?) ");

	StringBuffer delSql = new StringBuffer();
	delSql.append(" DELETE EVS_ITEM_PROCESS_MARK  ");
	delSql.append(" WHERE EV_PERIOD_ID=?    ");
	delSql.append(" AND EV_EMP_ID=?");
	delSql.append(" AND EV_PROCESS_ID=?");
	delSql.append(" AND EV_ITEM_ID=?");

	PreparedStatement insertPstmt = null;
	PreparedStatement delPstmt = null;
	try {
	    if (null != empList) {
		if (empList.size() > 0) {

		    insertPstmt = con.prepareStatement(insertSql.toString());
		    delPstmt = con.prepareStatement(delSql.toString());

		    for (int i = 0, j = empList.size(); i < j; i++) {

			SimpleMap map = (SimpleMap) empList.get(i);

			int k = 0;
			insertPstmt.setString(++k, map
				.getString("EV_PERIOD_ID"));
			insertPstmt.setString(++k, map.getString("EV_EMP_ID"));
			insertPstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			insertPstmt.setString(++k, map.getString("EV_ITEM_ID"));
			insertPstmt.setString(++k, map.getString("EV_MARKS"));
			insertPstmt.addBatch();

			k = 0;
			delPstmt.setString(++k, map.getString("EV_PERIOD_ID"));
			delPstmt.setString(++k, map.getString("EV_EMP_ID"));
			delPstmt.setString(++k, map.getString("EV_PROCESS_ID"));
			delPstmt.setString(++k, map.getString("EV_ITEM_ID"));
			delPstmt.addBatch();
		    }

		    delPstmt.executeBatch();
		    insertPstmt.executeBatch();
		}
	    }
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evsItemMarksSave()", e);
	    }
	    logger.error("evsItemMarksSave()", sqle);
	    throw new DataAccessException(
		    "cant execute  evsItemMarksSave statistics", sqle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evsItemMarksSave()", e1);
	    }
	    logger.error("evsItemMarksSave()", e);
	} finally {
	    SqlUtil.close(insertPstmt);
	    SqlUtil.close(delPstmt);
	}
    }

    /**
     * 提交评价 评价流程项目分数更新 员工评价流程,总分数更新 更新评价者已提交  
     * @param empList
     * @throws DataAccessException 
     */
    public void evSubmit(List empList) throws DataAccessException {
	Connection con = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    this.evsItemMarksSave(empList, con);
	    this.evsEmpMarkUpdate(empList, con);
	    this.evsEmpProcessUpdate(empList, con);
	    this.evsMasterSubmitted(empList, con);
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evSubmit() ", e);
	    }
	    logger.error("evSubmit()", sqle);
	    throw new DataAccessException("cant execute  evSubmit statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evSubmit()", e1);
	    }
	    logger.error("evSubmit()", sle);
	    throw new DataAccessException("cant get connection for evSubmit",
		    sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evSubmit()", e1);
	    }
	    logger.error("evSubmit()", e);
	} finally {
	    SqlUtil.close(con);
	}
    }
    /**
     * 提交评价 员工评价流程 更新评价者已提交  
     * @param empList
     * @throws DataAccessException 
     */
    public void evSubmit2(List empList) throws DataAccessException {
	Connection con = null;
	try {
	    con = services.getConnection();
	    con.setAutoCommit(false);
	    this.evsEmpProcessUpdate(empList, con);
	    this.evsMasterSubmitted(empList, con);
	    con.commit();
	} catch (SQLException sqle) {
	    try {
		con.rollback();
	    } catch (SQLException e) {
		logger.error("evSubmit2() ", e);
	    }
	    logger.error("evSubmit2()", sqle);
	    throw new DataAccessException("cant execute  evSubmit2 statistics",
		    sqle);
	} catch (ServiceLocatorException sle) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evSubmit2()", e1);
	    }
	    logger.error("evSubmit2()", sle);
	    throw new DataAccessException("cant get connection for evSubmit2",
		    sle);
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		logger.error("evSubmit2()", e1);
	    }
	    logger.error("evSubmit2()", e);
	} finally {
	    SqlUtil.close(con);
	}
    }
}