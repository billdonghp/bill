package com.ait.evs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class EvsOtherColumn {

    private static ServiceLocator services;

    private String evColumnId;

    private String evColumnName;

    private String evColumnDetail;

    private int evColumnOrder;

    private int seqEvOtherItemDetail;

    /**
     * @return 返回 evColumnId。
     */
    public String getEvColumnId() {
        return evColumnId;
    }

    /**
     * @param evColumnId
     *            要设置的 evColumnId。
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
     * @param evColumnName
     *            要设置的 evColumnName。
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
     * @param evColumnOrder
     *            要设置的 evColumnOrder。
     */
    public void setEvColumnOrder(int evColumnOrder) {
        this.evColumnOrder = evColumnOrder;
    }

    /**
     * @return 返回 seqEvOtherItemDetail。
     */
    public int getseqEvOtherItemDetail() {
        return seqEvOtherItemDetail;
    }

    /**
     * @param seqEvOtherItemDetail
     *            要设置的 seqEvOtherItemDetail。
     */
    public void setseqEvOtherItemDetail(int seqEvOtherItemDetail) {
        this.seqEvOtherItemDetail = seqEvOtherItemDetail;
    }

    /**
     * @return 返回 evColumnDetail。
     */
    public String getEvColumnDetail() {
        return evColumnDetail;
    }

    /**
     * @param evColumnDetail
     *            要设置的 evColumnDetail。
     */
    public void setEvColumnDetail(String evColumnDetail) {
        this.evColumnDetail = evColumnDetail;
    }

    public EvsOtherColumn() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //add and  update
    public EvsOtherColumn(String evColumnId, String evColumnDetail) {
        this.evColumnDetail = evColumnDetail;
        this.evColumnId = evColumnId;
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // del
    public EvsOtherColumn(int seqEvOtherItemDetail) {
        this.seqEvOtherItemDetail = seqEvOtherItemDetail;
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //增加项目列
    private final static String ADD_EV_OTHER_COLUMN = "INSERT INTO evs_column_detail(seq_ev_item_detail,ev_column_detail,ev_column_id)"
            + " VALUES(?,?,?)";

    public void addEvOtherColumn() throws DataAccessException {
        Connection con = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            addEvOtherColumn(con);
            con.commit();

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for addEvOtherColumn statistics", sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for addEvOtherColumn statistics", sle);
        } finally {
            SqlUtil.close(con);
        }
    }

    public void addEvOtherColumn(Connection con) throws DataAccessException {

        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(ADD_EV_OTHER_COLUMN);
            pstmt.setInt(1, this.seqEvOtherItemDetail);
            pstmt.setString(2, this.evColumnDetail);
            pstmt.setString(3, this.evColumnId);
            pstmt.execute();

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for addEvOtherColumn statistics", sqle);
        } finally {
            SqlUtil.close(pstmt, null);
        }

    }

    //  修改项目列
    private final static String UPDATE_EV_OTHER_COLUMN = "UPDATE evs_column_detail"
            + " SET ev_column_detail=? WHERE seq_ev_item_detail=? AND ev_column_id=? ";

    public void updateEvOtherColumn() throws DataAccessException {
        Connection con = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            updateEvOtherColumn(con);
            con.commit();

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for updateEvOtherColumn statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for updateEvOtherColumn statistics",
                    sle);
        } finally {
            SqlUtil.close(con);
        }
    }

    public void updateEvOtherColumn(Connection con) throws DataAccessException {

        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(UPDATE_EV_OTHER_COLUMN);
            pstmt.setString(1, this.evColumnDetail);
            pstmt.setInt(2, this.seqEvOtherItemDetail);
            pstmt.setString(3, this.evColumnId);
            pstmt.execute();
            
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for updateEvOtherColumn statistics",
                    sqle);
        } finally {
            SqlUtil.close(pstmt, null);
        }

    }

    //  删除项目列
    private final static String DEL_EV_OTHER_COLUMN = "DELETE evs_column_detail"
            + " WHERE seq_ev_item_detail=? ";

    public void delEvOtherColumn() throws DataAccessException {
        Connection con = null;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            delEvOtherColumn(con);
            con.commit();

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for delEvOtherColumn statistics", sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for delEvOtherColumn statistics", sle);
        } finally {
            SqlUtil.close(con);
        }
    }

    public void delEvOtherColumn(Connection con) throws DataAccessException {

        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(DEL_EV_OTHER_COLUMN);
            pstmt.setInt(1, this.seqEvOtherItemDetail);
            pstmt.execute();

        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for delEvOtherColumn statistics", sqle);
        } finally {
            SqlUtil.close(pstmt, null);
        }

    }
}