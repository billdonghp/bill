/*
 * 创建日期 2005-9-9
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
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.SysCodeParser;

/**
 * @author AIT Administrator
 */
public class EvsNorm {

    List lEvDeptGrade;

    Vector vEvEmpGrade;

    int seqEvsNorm;

    String evDeptGrade;

    String evEmpGrade;

    /**
     * @return 返回 evDeptGrade。
     */
    public String getEvDeptGrade() {
        return evDeptGrade;
    }

    /**
     * @param evDeptGrade
     *            要设置的 evDeptGrade。
     */
    public void setEvDeptGrade(String evDeptGrade) {
        this.evDeptGrade = evDeptGrade;
    }

    /**
     * @return 返回 evEmpGrade。
     */
    public String getEvEmpGrade() {
        return evEmpGrade;
    }

    /**
     * @param evEmpGrade
     *            要设置的 evEmpGrade。
     */
    public void setEvEmpGrade(String evEmpGrade) {
        this.evEmpGrade = evEmpGrade;
    }

    /**
     * @return 返回 lEvsDeptGrade。
     */
    public List getLEvDeptGrade() {
        return lEvDeptGrade;
    }

    /**
     * @param evsDeptGrade
     *            要设置的 lEvsDeptGrade。
     */
    public void setLEvDeptGrade(List evDeptGrade) {
        lEvDeptGrade = evDeptGrade;
    }

    /**
     * @return 返回 seqEvsNorm。
     */
    public int getSeqEvsNorm() {
        return seqEvsNorm;
    }

    /**
     * @param seqEvsNorm
     *            要设置的 seqEvsNorm。
     */
    public void setSeqEvsNorm(int seqEvsNorm) {
        this.seqEvsNorm = seqEvsNorm;
    }

    /**
     * @return 返回 vEvEmpGrade。
     */
    public Vector getVEvEmpGrade() {
        return vEvEmpGrade;
    }

    /**
     * @param evEmpGrade
     *            要设置的 vEvEmpGrade。
     */
    public void setVEvEmpGrade(Vector evEmpGrade) {
        vEvEmpGrade = evEmpGrade;
    }
    //根据系统CODE里构造出相应的员工等级列表
    public void setVEvEmpGrade() throws DataAccessException {
        this.vEvEmpGrade = SysCodeParser.getCode("EVEMPGRADE");
    }
    
    private static ServiceLocator services;

    public EvsNorm() {
        this.lEvDeptGrade = new Vector();
        
        try {
            this.setVEvEmpGrade();
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (DataAccessException e1) {
            e1.printStackTrace();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getEvsNorm(String evDeptGradeId) throws DataAccessException {
        String evDeptGrade_sql = "";

        // if (!evDeptGradeId.trim().equals("")) {
        evDeptGrade_sql = " AND ev_dept_grade='" + evDeptGradeId + "'";
        // }

        String select_sql = " SELECT  DISTINCT  EVS_NORM.ev_dept_grade, GET_SYS_CODE(EVS_NORM.ev_dept_grade) "
                + " AS ev_dept_grade_name, EVS_NORM.ev_emp_sum FROM EVS_NORM WHERE ev_dept_grade is not null  "
                + evDeptGrade_sql;

        String order_sql = " ORDER BY EVS_NORM.ev_emp_sum ";
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;
        List lEvDeptGrade = new Vector();
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(select_sql + order_sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsDeptGrade evDeptGrade = new EvsDeptGrade();
                evDeptGrade.setEvDeptGradeId(rs.getString("ev_dept_grade"));
                evDeptGrade.setEvDeptGradeName(rs
                        .getString("ev_dept_grade_name"));
                evDeptGrade.setEvEmpSum(rs.getInt("ev_emp_sum"));
                evDeptGrade.setLEvsGradeRate(this.getEvEmpGradeList(con, rs
                        .getString("ev_dept_grade"), rs.getInt("ev_emp_sum")));
                lEvDeptGrade.add(evDeptGrade);
            }
            con.commit();
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvsNorm statistics", sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for getEvsNorm statistics", sle);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvDeptGrade;
    }

    private final static String GET_EVEMP_GRADE_LIST = " SELECT ev_emp_count FROM evs_norm "
            + " WHERE ev_dept_grade=? "
            + " AND  ev_emp_sum=? AND  ev_emp_grade=? ";

    //
    private List getEvEmpGradeList(Connection con, String evDeptGrade,
            int evEmpSum) throws DataAccessException {

        List levEmpGrade = new Vector();
        try {
            this.setVEvEmpGrade();

            if (this.vEvEmpGrade != null) {
                int vEvEmpGradeSize = vEvEmpGrade.size();
                for (int i = 0; i < vEvEmpGradeSize; i++) {
                    EvsGradeRate evGradeRate = new EvsGradeRate();
                    HashMap h = (HashMap) vEvEmpGrade.get(i);

                    String evGrade = h.get("code").toString();
                    String evGradeName=h.get("name").toString();
                    int evEmpCount = -1;

                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    pstmt = con.prepareStatement(GET_EVEMP_GRADE_LIST);
                    pstmt.setString(1, evDeptGrade);
                    pstmt.setInt(2, evEmpSum);
                    pstmt.setString(3, h.get("code").toString());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        evEmpCount = rs.getInt("ev_emp_count");
                    }
                    evGradeRate.setEmpGradeCount(evEmpCount);
                    evGradeRate.setEvGradeName(evGradeName);
                    evGradeRate.setEvGrade(evGrade);
                    levEmpGrade.add(evGradeRate);
                    SqlUtil.close(rs, pstmt);
                }
            }
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvsNorm statistics", sqle);
        }
        return levEmpGrade;
    }
    
    public EvsDeptGrade getEvDeptGradeById(String evDeptGradeId, int evEmpSum)
            throws DataAccessException {
        EvsDeptGrade evDeptGrade = new EvsDeptGrade();
        Connection con = null;
        try {
            this.setVEvEmpGrade();
            con = services.getConnection();
            con.setAutoCommit(false);
            evDeptGrade.setLEvsGradeRate(this.getEvEmpGradeList(con, evDeptGradeId, evEmpSum));
            con.commit();
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for getEvDeptGradeById statistics", sle);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            SqlUtil.close(con);
        }
        return evDeptGrade;
    }

    private final static String ADD_EV_NORM = "INSERT INTO evs_norm(seq_evs_norm,ev_dept_grade,ev_emp_sum,ev_emp_grade,ev_emp_count)"
            + " VALUES (seq_evs_norm.nextval,?,?,?,?)";

    public void addEvNorm(List lEvDeptGrade) throws DataAccessException {
        if (lEvDeptGrade == null) {
            return;
        }
        int lEvDeptGradeSize = lEvDeptGrade.size();

        for (int i = 0; i < lEvDeptGradeSize; i++) {
            EvsDeptGrade evd = (EvsDeptGrade) lEvDeptGrade.get(i);
            Connection con = null;
            try {
                con = services.getConnection();
                con.setAutoCommit(false);
                this.addEvNorm(con, evd);
                con.commit();
            } catch (ServiceLocatorException sle) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                }
                throw new DataAccessException(
                        "cant get connection for addEvNorm statistics", sle);
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                }
                e.printStackTrace();
            } finally {
                SqlUtil.close(con);
            }
        }

    }
    private final static  String EVS_NORM_IS_SAVE=" SELECT * FROM evs_norm WHERE ev_dept_grade=? " +
    		" AND ev_emp_sum=? AND ev_emp_grade=? ";
    private final static String UPDATE_EVS_NORM=" UPDATE evs_norm SET ev_emp_count=? " +
    		" WHERE ev_dept_grade=? AND ev_emp_sum=? AND ev_emp_grade=? ";
    private void addEvNorm(Connection con, EvsDeptGrade evDeptGrade)
            throws DataAccessException {

        String evDeptGradeId = evDeptGrade.getEvDeptGradeId();
        int evEmpSum = evDeptGrade.getEvEmpSum();
        List lEvGradeRate = evDeptGrade.getLEvsGradeRate();
        if (lEvGradeRate != null) {
            int lEvGradeRateSize = lEvGradeRate.size();
            for (int i = 0; i < lEvGradeRateSize; i++) {
                EvsGradeRate evr = (EvsGradeRate) lEvGradeRate.get(i);
                PreparedStatement pstmt = null;
                ResultSet rs=null;
                try {
                    
                    pstmt=con.prepareStatement(EVS_NORM_IS_SAVE);
                    pstmt.setString(1, evDeptGradeId);
                    pstmt.setInt(2, evEmpSum);
                    pstmt.setString(3, evr.getEvGrade());
                    rs=pstmt.executeQuery();
                    if(rs.next()){
                        pstmt=null;
	                    pstmt = con.prepareStatement(UPDATE_EVS_NORM);
	                    pstmt.setInt(1, evr.getEmpGradeCount());
	                    pstmt.setString(2, evDeptGradeId);
	                    pstmt.setInt(3, evEmpSum);
	                    pstmt.setString(4, evr.getEvGrade());
	                    pstmt.executeUpdate();
                    }else{
                        pstmt=null;
	                    pstmt = con.prepareStatement(ADD_EV_NORM);
	                    pstmt.setString(1, evDeptGradeId);
	                    pstmt.setInt(2, evEmpSum);
	                    pstmt.setString(3, evr.getEvGrade());
	                    pstmt.setInt(4, evr.getEmpGradeCount());
	                    pstmt.executeUpdate();
                    }
                } catch (SQLException sqle) {
                    try {
                        con.rollback();
                    } catch (SQLException e) {
                    }
                    sqle.printStackTrace();
                    throw new DataAccessException(
                            "cant execute query for getEvsNorm statistics",
                            sqle);
                }
            }
        }
    }

    public void addEvNorm(EvsDeptGrade evDeptGrade) throws DataAccessException {
        Connection con = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            this.addEvNorm(con, evDeptGrade);
            con.commit();
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for addEvNorm statistics", sle);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
            }
            e.printStackTrace();
        } finally {
            SqlUtil.close(con);
        }
    }
    
    private final static String DEL_EV_NORM="DELETE evs_norm WHERE ev_dept_grade=? AND ev_emp_sum=? ";
    public void delEvNorm(String evDeptGrade,int evEmpSum) throws DataAccessException{
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DEL_EV_NORM);
            pstmt.setString(1, evDeptGrade);
            pstmt.setInt(2, evEmpSum);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for delEvNorm statistics", sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for delEvNorm statistics", sle);
        } finally {
            SqlUtil.close(pstmt, con);

        }

    }
}