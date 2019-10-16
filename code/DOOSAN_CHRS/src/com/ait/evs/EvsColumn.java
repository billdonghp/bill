package com.ait.evs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class EvsColumn {
    private static ServiceLocator services;

    private String evColumnId;

    private String evColumnName;

    private int evColumnOrder;

    /**
     * @return 返回 evColumnId。
     */
    public String getEvColumnId() {
        return evColumnId;
    }

    /**
     * @param evColumnId 要设置的 evColumnId。
     */
    public void setEvColumnId(String evColumnId) {
        this.evColumnId = evColumnId;
    }

    /**
     * @return 返回 evColumnName。
     */
    public String getEvColumnName() {
        return evColumnName;
    }

    /**
     * @param evColumnName 要设置的 evColumnName。
     */
    public void setEvColumnName(String evColumnName) {
        this.evColumnName = evColumnName;
    }

    /**
     * @return 返回 evColumnOrder。
     */
    public int getEvColumnOrder() {
        return evColumnOrder;
    }

    /**
     * @param evColumnOrder 要设置的 evColumnOrder。
     */
    public void setEvColumnOrder(int evColumnOrder) {
        this.evColumnOrder = evColumnOrder;
    }

    public EvsColumn() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 获得细则列表
     *  
     */
    public Vector getDetailIDByItemID(String periodID, String empID,
            String itemID) throws DataAccessException {
        String sql = "select SEQ_EV_ITEM_DETAIL from EVS_ITEM_DETAIL where EV_PERIOD_ID = ?"
                + " and EV_EMP_ID = ? and EV_ITEM_ID = ? order by seq_ev_item_detail ";
        Vector v = new Vector();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, periodID);
            pstmt.setString(2, empID);
            pstmt.setString(3, itemID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                v.add(new Integer(rs.getInt("SEQ_EV_ITEM_DETAIL")));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getDetailIDByItemID statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getDetailIDByItemID statistics",
                    sle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return v;
    }

    /*
     * 获得上、下办年度评价细则尺度 参数为上半年细则seq
     */
    public Hashtable getYearEvsColumnByDetailID(int itemDetailSeq)
            throws DataAccessException {
        String sql = "select * from EVS_COLUMN_DETAIL_YEAR_V where EV_ITEM_DETAIL_ID1 = ?";
        Hashtable h = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemDetailSeq);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                h = new Hashtable();
                h.put("detailID1", (rs.getString(1) != null) ? rs.getString(1)
                        : "");
                h.put("detailID2", (rs.getString(2) != null) ? rs.getString(2)
                        : "");
                h.put("column001", (rs.getString(3) != null) ? rs.getString(3)
                        : "");
                h.put("column002", (rs.getString(4) != null) ? rs.getString(4)
                        : "");
                h.put("detailProp", (rs.getString(5) != null) ? rs.getString(5)
                        : "");
                h.put("column003", (rs.getString(6) != null) ? rs.getString(6)
                        : "");
                h.put("column004", (rs.getString(7) != null) ? rs.getString(7)
                        : "");
                h.put("column005", (rs.getString(8) != null) ? rs.getString(8)
                        : "");
                h.put("column006", (rs.getString(9) != null) ? rs.getString(9)
                        : "");
                h.put("column007", (rs.getString(10) != null) ? rs
                        .getString(10) : "");
                h.put("column008", (rs.getString(11) != null) ? rs
                        .getString(11) : "");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getYearEvsColumnByDetailID statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getYearEvsColumnByDetailID statistics",
                    sle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return h;
    }

    public Vector getEvsColumnNameByDetailID(int itemDetailSeq)
            throws DataAccessException {
        String sql = "select ev_column_name from EVS_COLUMN_DETAIL_V where SEQ_EV_ITEM_DETAIL = ?";
        Vector v = new Vector();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemDetailSeq);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                v.add(rs.getString("ev_column_name"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvsColumnNameByDetailID statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvsColumnNameByDetailID statistics",
                    sle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return v;
    }

    public Vector getEvsColumnByDetailID(int itemDetailSeq)
            throws DataAccessException {
        String sql = "select ev_detail_prop,ev_column_detail from EVS_COLUMN_DETAIL_V where SEQ_EV_ITEM_DETAIL = ?";
        Vector v = new Vector();
        Connection con = null;
        ResultSet rs = null;
        String prop = "";
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemDetailSeq);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.isFirst())
                    prop = rs.getString("ev_detail_prop");
                v.add((rs.getString("ev_column_detail") != null) ? rs
                        .getString("ev_column_detail") : "");
            }
            v.add(prop);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvsColumnByDetailID statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvsColumnByDetailID statistics",
                    sle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return v;
    }

    public Hashtable getEvsColumnHByDetailID(int itemDetailSeq)
            throws DataAccessException {
        String sql = "select ev_detail_prop,ev_column_detail,ev_column_id from EVS_COLUMN_DETAIL_V where SEQ_EV_ITEM_DETAIL = ?";
        Hashtable h = new Hashtable();
        Connection con = null;
        ResultSet rs = null;
        String prop = "";
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemDetailSeq);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                if (rs.isFirst())
                    prop = rs.getString("ev_detail_prop");
                h.put(rs.getString("ev_column_id"),
                        (rs.getString("ev_column_detail") != null) ? rs
                                .getString("ev_column_detail") : "");
            }
            h.put("detailProp", prop);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvsColumnByDetailID statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvsColumnByDetailID statistics",
                    sle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);

        }
        return h;
    }

    public int modifyColumnByItemDetail(int detailSeq, String column001,
            String column002, String detailProp, String column003,
            String column004, String column005, String column006,
            String column007, String column008) {
        String sql = "{ call EVS_COLUMN_MOD_P(?,?,?,?,?,?,?,?,?,?,?) }";
        int count = 0;
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setInt(1, detailSeq);
            cs.setString(2, StringUtil.toCN(column001));
            cs.setString(3, StringUtil.toCN(column002));
            cs.setString(4, detailProp);
            cs.setString(5, StringUtil.toCN(column003));
            cs.setString(6, StringUtil.toCN(column004));
            cs.setString(7, StringUtil.toCN(column005));
            cs.setString(8, StringUtil.toCN(column006));
            cs.setString(9, StringUtil.toCN(column007));
            cs.setString(10, StringUtil.toCN(column008));
            cs.registerOutParameter(11, Types.INTEGER);
            cs.execute();
            count = cs.getInt(11);
            return count;
        } catch (Exception sqe) {
            System.out
                    .println("EvsColumn.java:modifyColumnByItemDetail:refresh: sqe="
                            + sqe);
            return 0;
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void modifyColumnDetail(int itemDetailSeq, String evColumnID,
            String evColumnDetail) {
        String columnDetail = StringUtil.toCN(evColumnDetail);
        String checkSql = "select count(*) from EVS_COLUMN_DETAIL where SEQ_EV_ITEM_DETAIL = ? and EV_COLUMN_ID=?";
        String updateSql = "update EVS_COLUMN_DETAIL set EV_COLUMN_DETAIL = '"
                + columnDetail + "' where SEQ_EV_ITEM_DETAIL = "
                + itemDetailSeq + " and EV_COLUMN_ID = '" + evColumnID + "'";
        String insertSql = "insert into  EVS_COLUMN_DETAIL (SEQ_EV_ITEM_DETAIL,EV_COLUMN_ID,EV_COLUMN_DETAIL) values ("
                + itemDetailSeq + ",'" + evColumnID + "',?)";
        Connection con = null;
        ResultSet rs = null;
        int state = 0;
        PreparedStatement pstmt = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(checkSql);
            pstmt.setInt(1, itemDetailSeq);
            pstmt.setString(2, evColumnID);
            rs = pstmt.executeQuery();
            if (rs.next())
                state = rs.getInt(1);
            if (state > 0) {
                pstmt = con.prepareStatement(updateSql);
                pstmt.executeUpdate();
            } else {
                pstmt = con.prepareStatement(insertSql);
                pstmt.setString(1, columnDetail);
                pstmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
    }

    //  取得相应项目的评价列
    private final static String GET_ITEM_COLUMNS = 
        " SELECT ev_column_id,get_sys_code(ev_column_id) AS ev_column_name " +
        " FROM evs_column WHERE ev_period_id=? and ev_type_id=? and ev_item_id=? ";

    public List getItemColumns(String evPeriodId, String evTypeId,
            String evItemId) throws DataAccessException {
        Connection con = null;
        List lItemColumns = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            lItemColumns = this.getItemColumns(con, evPeriodId, evTypeId,
                    evItemId);
            con.commit();

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getItemColumns statistics", sqle);
        } catch (ServiceLocatorException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getItemColumns statistics", sqle);
        }finally{
            SqlUtil.close(con);
        }
        return lItemColumns;
    }
    
    public List getItemColumns(Connection con, String evPeriodId,
            String evTypeId, String evItemId) throws DataAccessException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lItemColumns = new Vector();
        try {
            pstmt = con.prepareStatement(GET_ITEM_COLUMNS);
            pstmt.setString(1, evPeriodId);
            pstmt.setString(2, evTypeId);
            pstmt.setString(3, evItemId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsColumn evColumn = new EvsColumn();
                evColumn.setEvColumnId(rs.getString("ev_column_id"));
                evColumn.setEvColumnName(rs.getString("ev_column_name"));
                //evColumn.setEvColumnOrder(rs.getInt("ev_column_order"));
                lItemColumns.add(evColumn);
                
            }
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getItemColumns statistics", sqle);
        } finally {
            SqlUtil.close(rs,pstmt);
        }
        return lItemColumns;
    }
    public boolean equals(Object o){
        if(o==null||!(o instanceof EvsColumn)||this.evColumnId==null){
            return false;
        }
        EvsColumn evo=(EvsColumn)o;
        if(evo.evColumnId!=null){
	        if(evo.evColumnId.equals(evColumnId)){
	            return true;
	        }else{
	            return false;
	        }
        }else{
            return false;
        }
    }
}