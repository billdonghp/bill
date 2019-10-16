/*
 * 创建日期 2005-8-24
 * 
 * Company: AIT
 * 
 * @author QING
 * @version 1.0
 */
package com.ait.evs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

/**
 * @author AIT Administrator
 */
public class EvsCommonItem {
    private String evPeriodId;

    private String evTypeId;

    private String evTypeName;

    private String evItemId;

    private String evItemName;

    private int evItemOrder;

    private List lItemColumns;

    private List lEvCommonItemDetail;

    /**
     * @return 返回 evItemId。
     */
    public String getEvItemId() {
        return evItemId;
    }

    /**
     * @param evItemId
     *            要设置的 evItemId。
     */
    public void setEvItemId(String evItemId) {
        this.evItemId = evItemId;
    }

    /**
     * @return 返回 evItemName。
     */
    public String getEvItemName() {
        return evItemName;
    }

    /**
     * @param evItemName
     *            要设置的 evItemName。
     */
    public void setEvItemName(String evItemName) {
        this.evItemName = evItemName;
    }

    /**
     * @return 返回 evItemOrder。
     */
    public int getEvItemOrder() {
        return evItemOrder;
    }

    /**
     * @param evItemOrder
     *            要设置的 evItemOrder。
     */
    public void setEvItemOrder(int evItemOrder) {
        this.evItemOrder = evItemOrder;
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
     * @return 返回 evTypeName。
     */
    public String getEvTypeName() {
        return evTypeName;
    }

    /**
     * @param evTypeName
     *            要设置的 evTypeName。
     */
    public void setEvTypeName(String evTypeName) {
        this.evTypeName = evTypeName;
    }

    /**
     * @return 返回 lEvCommonItemDetail。
     */
    public List getLEvCommonItemDetail() {
        return lEvCommonItemDetail;
    }

    /**
     * @param evCommonItemDetail
     *            要设置的 lEvCommonItemDetail。
     */
    public void setLEvCommonItemDetail(List evCommonItemDetail) {
        lEvCommonItemDetail = evCommonItemDetail;
    }

    /**
     * @return 返回 lItemColumns。
     */
    public List getLItemColumns() {
        return lItemColumns;
    }

    /**
     * @param itemColumns
     *            要设置的 lItemColumns。
     */
    public void setLItemColumns(List itemColumns) {
        lItemColumns = itemColumns;
    }

    private static ServiceLocator services;

    public EvsCommonItem() {
        lItemColumns = new Vector();
        lEvCommonItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  构造出所有项目
    public EvsCommonItem(String evPeriodId, String evTypeId) {
        this.evPeriodId = evPeriodId;
        this.evTypeId = evTypeId;
        lItemColumns = new Vector();
        lEvCommonItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //构造出所有项目
    public EvsCommonItem(String evPeriodId, String evTypeId, String evItemId) {
        this.evPeriodId = evPeriodId;
        this.evTypeId = evTypeId;
        this.evItemId = evItemId;
        lItemColumns = new Vector();
        lEvCommonItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 构造出所有项目
    public EvsCommonItem(String evPeriodId, String evItemId, String evItemName,
            String evTypeId, String evTypeName) {
        this.evPeriodId = evPeriodId;
        this.evTypeId = evTypeId;
        this.evTypeName = evTypeName;
        this.evItemId = evItemId;
        this.evItemName = evItemName;
        lItemColumns = new Vector();
        lEvCommonItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static String GET_EV_COMMON_COLUMNS = "SELECT distinct ev_period_id,ev_type_id,get_sys_code(ev_type_id) AS ev_type_name,"
            + " ev_item_id, get_sys_code(ev_item_id) AS ev_item_name,ev_column_id,"
            + " get_sys_code(ev_column_id) AS ev_column_name "
            + " FROM evs_column WHERE ev_period_id is not null  "
            + " AND ev_period_id=?  AND ev_type_id=? AND ev_item_id=? ";

    public List getEvCommonColumns(String evItemId) throws DataAccessException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lEvCommonColumns = new Vector();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EV_COMMON_COLUMNS);
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evTypeId);
            pstmt.setString(3, evItemId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsCommonColumn evColumn = new EvsCommonColumn();
                evColumn.setEvColumnId(rs.getString("ev_column_id"));
                evColumn.setEvColumnName(rs.getString("ev_column_name"));
                lEvCommonColumns.add(evColumn);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvCommonColumns statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {

            throw new DataAccessException(
                    "cant get connection for getEvCommonColumns statistics",
                    sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvCommonColumns;

    }

    //取得相应项目的内容
    private final static String GET_EVCOMMON_ITEMDETAILS = "SELECT  seq_ev_common_item_detail,ev_item_id,"
            + " get_sys_code(ev_item_id) AS ev_item_name,ev_type_id,"
            + " get_sys_code(ev_type_id) AS ev_type_name,ev_detail_prop,ev_detail_order "
            + " FROM evs_common_item_detail "
            + " WHERE ev_period_id=? AND ev_type_id=? AND ev_item_id=? ";

    private List getEvCommonItemDetails(String evItemId, List evItemColumn)
            throws DataAccessException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lEvCommonItemDetails = new Vector();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EVCOMMON_ITEMDETAILS);
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evTypeId);
            pstmt.setString(3, evItemId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsCommonItemDetail evCommonItem = new EvsCommonItemDetail();
                evCommonItem.setEvDetailProp(rs.getFloat("ev_detail_prop"));
                evCommonItem.setSeqEvCommonItemDetail(rs
                        .getInt("seq_ev_common_item_detail"));
                evCommonItem.setLEvsCommonColumn(getItemDetailColumn(rs
                        .getInt("seq_ev_common_item_detail"), evItemColumn));
                lEvCommonItemDetails.add(evCommonItem);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvCommonItemDetails statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvCommonItemDetails statistics",
                    sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvCommonItemDetails;
    }

    //取得相应项目内容的列
    private final static String GET_ITEM_DETAIL_COLUMN = " SELECT * FROM evs_common_column_detail WHERE seq_ev_common_item_detail=? AND ev_column_id=? ";

    private List getItemDetailColumn(int seqEvCommonItemDetail,
            List lEvCommonItemColumn) throws DataAccessException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lEvCommonColumnDetail = new Vector();
        try {
            if (lEvCommonItemColumn != null) {
                int lEvCommonItemColumnSize = lEvCommonItemColumn.size();
                for (int i = 0; i < lEvCommonItemColumnSize; i++) {
                    EvsCommonColumn evCommonColumn = (EvsCommonColumn) lEvCommonItemColumn
                            .get(i);
                    con = services.getConnection();
                    pstmt = con.prepareStatement(GET_ITEM_DETAIL_COLUMN);
                    pstmt.setInt(1, seqEvCommonItemDetail);
                    pstmt.setString(2, evCommonColumn.getEvColumnId());
                    rs = pstmt.executeQuery();
                    EvsCommonColumn evCommon = new EvsCommonColumn();
                    if (rs.next()) {
                        evCommon.setEvColumnDetail(rs
                                .getString("ev_column_detail"));
                        evCommon.setEvColumnId(rs.getString("ev_column_id"));
                        evCommon.setEvColumnName(evCommonColumn
                                .getEvColumnName());
                    }
                    lEvCommonColumnDetail.add(evCommon);
                    SqlUtil.close(rs, pstmt, con);
                }
            }
        } catch (SQLException sqle) {

            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getItemDetailColumn statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {

            throw new DataAccessException(
                    "cant get connection for getItemDetailColumn statistics",
                    sle);
        }
        return lEvCommonColumnDetail;
    }

    //取得所有项目
    private final static String GET_EV_COMMON_ITEM = " SELECT  DISTINCT  ev_period_id,ev_item_id,get_sys_code(ev_item_id) AS ev_item_name,ev_type_id,"
            + " get_sys_code(ev_type_id) AS ev_type_name FROM evs_common_item_detail"
            + " WHERE ev_period_id=? AND ev_type_id=? "
            + " GROUP BY ev_period_id,ev_item_id,ev_type_id ";

    public List getEvCommonItems() throws DataAccessException {

        if (this.checkNull()) {
            return null;
        }
        List lEvCommonItem = new Vector();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EV_COMMON_ITEM);
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evTypeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsCommonItem evItem = new EvsCommonItem();
                evItem.setEvPeriodId(rs.getString("ev_period_id"));
                evItem.setEvItemId(rs.getString("ev_item_id"));
                evItem.setEvItemName(rs.getString("ev_item_name"));
                evItem.setEvTypeId(rs.getString("ev_type_id"));
                evItem.setEvTypeName(rs.getString("ev_type_name"));
                evItem.setLItemColumns(getEvCommonColumns(rs
                        .getString("ev_item_id")));
                evItem.setLEvCommonItemDetail(getEvCommonItemDetails(rs
                        .getString("ev_item_id"), evItem.lItemColumns));
                lEvCommonItem.add(evItem);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvCommonItems statistics", sqle);
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
            throw new DataAccessException(
                    "cant get connection for getEvCommonItems statistics", sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvCommonItem;

    }

    private final static String GET_EVCOMMON_ITEMDETAIL_BYSEQ = "SELECT  seq_ev_common_item_detail,ev_item_id,"
            + " get_sys_code(ev_item_id) AS ev_item_name,ev_type_id,"
            + " get_sys_code(ev_type_id) AS ev_type_name,ev_detail_prop,ev_detail_order "
            + " FROM evs_common_item_detail "
            + " WHERE seq_ev_common_item_detail=? ";

    public EvsCommonItemDetail getEvCommonItemDetailBySeq(int seq)
            throws DataAccessException {
        if (this.checkNull()) {
            return null;
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        EvsCommonItemDetail evCommonItem = new EvsCommonItemDetail();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EVCOMMON_ITEMDETAIL_BYSEQ);
            pstmt.setInt(1, seq);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                evCommonItem.setEvItemId(rs.getString("ev_item_id"));
                evCommonItem.setEvItemName(rs.getString("ev_item_name"));
                evCommonItem.setEvTypeId(rs.getString("ev_type_id"));
                evCommonItem.setEvTypeName(rs.getString("ev_type_name"));
                evCommonItem.setEvDetailProp(rs.getFloat("ev_detail_prop"));
                evCommonItem.setSeqEvCommonItemDetail(rs
                        .getInt("seq_ev_common_item_detail"));
                evCommonItem.setLEvsCommonColumn(getItemDetailColumn(rs
                        .getInt("seq_ev_common_item_detail"),
                        getEvCommonColumns(rs.getString("ev_item_id"))));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvCommonItemDetailBySeq statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvCommonItemDetailBySeq statistics",
                    sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return evCommonItem;

    }

    //部分员工赋予
    public int evCommonEndow(List lEvsEmp,String evItemId) throws DataAccessException {

        //CREATE OR REPLACE PROCEDURE Evs_Endow_Common_Item_P (
        //PERIOD_I VARCHAR2,
        //FLAG_I VARCHAR2,
        //EMP_ID_I VARCHAR2,
        //EV_ITEM_ID_I VARCHAR2,
        //STATUS_O OUT NUMBER
        //)
        if (lEvsEmp == null) {
            return 0;
        }
        int sum = 0;
        String sql = "{ call Evs_Endow_Common_Item_p(?,?,?,?,?,?) }";
        Connection con = null;
        CallableStatement cs = null;
        try {
            int lEvsEmpSize = lEvsEmp.size();
            int ok = -1;
            for (int i = 0; i < lEvsEmpSize; i++) {
                EvsEmp evEmp = (EvsEmp) lEvsEmp.get(i);
                cs = null;
                con = services.getConnection();
                con.setAutoCommit(false);
                cs = con.prepareCall(sql);
                cs.setString(1, StringUtil.checkNull(evEmp.getEvPeriodID()));
                cs.setString(2, "E");
                cs.setString(3, StringUtil.checkNull(evEmp.getEvEmpID()));
                cs.setString(4, StringUtil.checkNull(evItemId));
                cs.setString(5, "");
                cs.registerOutParameter(6, Types.INTEGER);
               
                cs.execute();
               
                con.commit();
                ok = cs.getInt(6);
                if (ok == 1) {
                    sum++;
                }
                cs.close();
                SqlUtil.close(con);
                
            }

        }  catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvCommonItemDetailBySeq statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for getEvCommonItemDetailBySeq statistics",
                    sle);
        }finally {
            try {
                cs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
        return sum;
    }

    //全部员工赋于
    public int evCommonEndow(String evPeriodId,String evTypeId) throws DataAccessException {
//        CREATE OR REPLACE PROCEDURE Evs_Endow_Common_Item_P (
//                period_i          VARCHAR2,
//                flag_i            VARCHAR2,
//                emp_id_i          VARCHAR2,
//                item_id_i         VARCHAR2,
//                type_id_i         VARCHAR2,
//                status_o    OUT   NUMBER
//             )
        int ok = -1;
        String sql = "{ call Evs_Endow_Common_Item_p(?,?,?,?,?,?) }";
        Connection con = null;
        CallableStatement cs = null;
        try {
            
            con = services.getConnection();
            con.setAutoCommit(false);
            cs = con.prepareCall(sql);
            cs.setString(1, evPeriodId);
            cs.setString(2, "A");
            cs.setString(3, "");
            cs.setString(4, "");
            cs.setString(5, evTypeId);
            cs.registerOutParameter(6, Types.INTEGER);

            cs.execute();
            con.commit();
            ok = cs.getInt(6);
            
        } catch (SQLException sqle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvCommonItemDetailBySeq statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            try {
                con.rollback();
            } catch (SQLException e) {
            }
            throw new DataAccessException(
                    "cant get connection for getEvCommonItemDetailBySeq statistics",
                    sle);
        } finally {
            try {
                cs.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            SqlUtil.close(con);
        }
        return ok;

    }

    private boolean checkNull() {
        if (evPeriodId == null || evPeriodId.equals("") || evTypeId == null
                || evTypeId.equals("")) {
            return true;
        }
        return false;
    }

}