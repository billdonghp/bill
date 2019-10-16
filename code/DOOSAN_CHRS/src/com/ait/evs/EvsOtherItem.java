/*
 * 创建日期 2005-8-24
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

/**
 * @author AIT Administrator
 */
public class EvsOtherItem {
    private String evPeriodId;

    private String evTypeId;

    private String evTypeName;

    private String evItemId;

    private String evItemName;
    
    private String evEmpId;
    
    private String evEmpName;
    
    private int evItemOrder;

    private List lItemColumns;

    private List lEvOtherItemDetail;

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
     * @return 返回 lEvOtherItemDetail。
     */
    public List getLEvOtherItemDetail() {
        return lEvOtherItemDetail;
    }

    /**
     * @param EvOtherItemDetail
     *            要设置的 lEvOtherItemDetail。
     */
    public void setLEvOtherItemDetail(List evOtherItemDetail) {
        lEvOtherItemDetail = evOtherItemDetail;
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
    
    
    /**
     * @return 返回 evEmpId。
     */
    public String getEvEmpId() {
        return evEmpId;
    }
    /**
     * @param evEmpId 要设置的 evEmpId。
     */
    public void setEvEmpId(String evEmpId) {
        this.evEmpId = evEmpId;
    }
    /**
     * @return 返回 evEmpName。
     */
    public String getEvEmpName() {
        return evEmpName;
    }
    /**
     * @param evEmpName 要设置的 evEmpName。
     */
    public void setEvEmpName(String evEmpName) {
        this.evEmpName = evEmpName;
    }
    private static ServiceLocator services;

    public EvsOtherItem() {
        lItemColumns = new Vector();
        lEvOtherItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  构造出所有项目
    public EvsOtherItem(String evPeriodId, String evTypeId,String evEmpId) {
        this.evPeriodId = evPeriodId;
        this.evTypeId = evTypeId;
        this.evEmpId=evEmpId;
        lItemColumns = new Vector();
        lEvOtherItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //构造出所有项目
    public EvsOtherItem(String evPeriodId, String evTypeId,String evEmpId, String evItemId) {
        this.evPeriodId = evPeriodId;
        this.evTypeId = evTypeId;
        this.evEmpId=evEmpId;
        this.evItemId = evItemId;
        lItemColumns = new Vector();
        lEvOtherItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 构造出所有项目
    public EvsOtherItem(String evPeriodId, String evItemId, String evItemName,
            String evTypeId, String evTypeName,String evEmpId) {
        this.evPeriodId = evPeriodId;
        this.evTypeId = evTypeId;
        this.evEmpId=evEmpId;
        this.evTypeName = evTypeName;
        this.evItemId = evItemId;
        this.evItemName = evItemName;
        lItemColumns = new Vector();
        lEvOtherItemDetail = new Vector();
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static String GET_EV_OTHER_COLUMNS = "SELECT distinct ev_period_id,ev_type_id,get_sys_code(ev_type_id) AS ev_type_name,"
            + " ev_item_id, get_sys_code(ev_item_id) AS ev_item_name,ev_column_id,"
            + " get_sys_code(ev_column_id) AS ev_column_name "
            + " FROM evs_column WHERE ev_period_id is not null  "
            + " AND ev_period_id=?  AND ev_type_id=? AND ev_item_id=? ";

    public List getEvOtherColumns(String evItemId) throws DataAccessException {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lEvOtherColumns = new Vector();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EV_OTHER_COLUMNS);
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evTypeId);
            pstmt.setString(3, evItemId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsOtherColumn evColumn = new EvsOtherColumn();
                evColumn.setEvColumnId(rs.getString("ev_column_id"));
                evColumn.setEvColumnName(rs.getString("ev_column_name"));
                lEvOtherColumns.add(evColumn);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvOtherColumns statistics", sqle);
        } catch (ServiceLocatorException sle) {

            throw new DataAccessException(
                    "cant get connection for getEvOtherColumns statistics", sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvOtherColumns;

    }

    //取得相应项目的内容
    private final static String GET_EVOTHER_ITEMDETAILS = "SELECT  seq_ev_item_detail,ev_item_id,"
            + " get_sys_code(ev_item_id) AS ev_item_name,ev_emp_id,"
            + " hr_get_name(ev_emp_id) AS ev_emp_name,ev_detail_prop,ev_detail_order "
            + " FROM evs_item_detail "
            + " WHERE ev_period_id=? AND ev_emp_id=? AND ev_item_id=? ";

    private List getEvOtherItemDetails(String evItemId, List evItemColumn)
            throws DataAccessException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lEvOtherItemDetails = new Vector();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EVOTHER_ITEMDETAILS);
            
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evEmpId);
            pstmt.setString(3, evItemId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                EvsOtherItemDetail evOtherItem = new EvsOtherItemDetail();
                evOtherItem.setEvDetailProp(rs.getFloat("ev_detail_prop"));
                evOtherItem.setEvEmpId(rs.getString("ev_emp_id"));
                evOtherItem.setEvEmpName(rs.getString("ev_emp_name"));
                evOtherItem.setEvItemId(rs.getString("ev_item_id"));
                evOtherItem.setEvItemName(rs.getString("ev_item_name"));
                evOtherItem.setSeqEvOtherItemDetail(rs
                        .getInt("seq_ev_item_detail"));
                evOtherItem.setLEvsOtherColumn(getItemDetailColumn(rs
                        .getInt("seq_ev_item_detail"), evItemColumn));
                lEvOtherItemDetails.add(evOtherItem);
                
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvOtherItemDetails statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvOtherItemDetails statistics",
                    sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvOtherItemDetails;
    }

    //取得相应项目内容的列
    private final static String GET_ITEM_DETAIL_COLUMN = " SELECT * FROM evs_column_detail WHERE seq_ev_item_detail=? AND ev_column_id=? ";

    private List getItemDetailColumn(int seqEvOtherItemDetail,
            List lEvOtherItemColumn) throws DataAccessException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List lEvOtherColumnDetail = new Vector();
        try {
            if (lEvOtherItemColumn != null) {
                int lEvOtherItemColumnSize = lEvOtherItemColumn.size();
                for (int i = 0; i < lEvOtherItemColumnSize; i++) {
                    EvsOtherColumn EvOtherColumn = (EvsOtherColumn) lEvOtherItemColumn
                            .get(i);
                    con = services.getConnection();
                    pstmt = con.prepareStatement(GET_ITEM_DETAIL_COLUMN);
                    pstmt.setInt(1, seqEvOtherItemDetail);
                    pstmt.setString(2, EvOtherColumn.getEvColumnId());
                    rs = pstmt.executeQuery();
                    EvsOtherColumn evOther = new EvsOtherColumn();
                    if (rs.next()) {
                        evOther.setEvColumnDetail(rs
                                .getString("ev_column_detail"));
                        evOther.setEvColumnId(rs.getString("ev_column_id"));
                        evOther
                                .setEvColumnName(EvOtherColumn
                                        .getEvColumnName());
                    }
                    lEvOtherColumnDetail.add(evOther);
                    
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
        return lEvOtherColumnDetail;
    }

    //取得所有项目
    private final static String GET_EV_OTHER_ITEM = " SELECT  DISTINCT  ev_period_id,ev_item_id," +
    		" get_sys_code(ev_item_id) AS ev_item_name,ev_emp_id,"
            + " hr_get_name(ev_emp_id) AS ev_emp_name FROM evs_item_detail"
            + " WHERE ev_period_id=? AND ev_emp_id=? "
            + " AND ev_item_id  in (" +
            		" SELECT evs_relation.ev_item_id  FROM evs_relation "+
                    " WHERE  ev_period_id=? AND ev_type_id=?  AND ev_process_id=?  " +
                   	" AND ev_operate_id=? " 
                    + " AND ev_item_id not in "+Constants.EVOTHERITEM+
                    " )";
            //+ " GROUP BY ev_period_id,ev_emp_id,ev_item_id ";

    public List getEvOtherItems(String evProcessId) throws DataAccessException {

        if (this.checkNull()) {
            return null;
        }
        List lEvOtherItem = new Vector();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EV_OTHER_ITEM);
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evEmpId);
            pstmt.setString(3,this.evPeriodId);
            pstmt.setString(4,this.evTypeId);
            pstmt.setString(5,evProcessId);
            pstmt.setString(6,Constants.EVOPERATE001);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                EvsOtherItem evItem = new EvsOtherItem();
                evItem.setEvPeriodId(rs.getString("ev_period_id"));
                evItem.setEvItemId(rs.getString("ev_item_id"));
                evItem.setEvItemName(rs.getString("ev_item_name"));
                evItem.setEvEmpId(rs.getString("ev_emp_id"));
                evItem.setEvEmpName(rs.getString("ev_emp_name"));
                evItem.setLItemColumns(getEvOtherColumns(rs
                        .getString("ev_item_id")));
                
                evItem.setLEvOtherItemDetail(getEvOtherItemDetails(rs
                        .getString("ev_item_id"), evItem.lItemColumns));
                lEvOtherItem.add(evItem);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvOtherItems statistics", sqle);
        } catch (ServiceLocatorException sle) {
            sle.printStackTrace();
            throw new DataAccessException(
                    "cant get connection for getEvOtherItems statistics", sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lEvOtherItem;

    }
    
//  取得操作方式为内容输入的评价项目排除目标输入
    private final static String GET_ITEMLIST_BY_PERIODTYPE = " SELECT evs_relation.ev_item_id,sy_code.code_name AS ev_item_name FROM evs_relation "
            + " INNER JOIN sy_code ON evs_relation.ev_item_id = sy_code.code_id AND SY_CODE.PARENT_CODE = 'EVITEM'"
            + " WHERE  ev_period_id=? AND ev_type_id=?  AND ev_process_id=?  AND ev_operate_id=? " +
           " AND ev_item_id not in "+Constants.EVOTHERITEM;

    public List getItemListByTypePeriod(String evProcessId) throws DataAccessException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        List lItemList = new Vector();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_ITEMLIST_BY_PERIODTYPE);
            pstmt.setString(1, this.evPeriodId);
            pstmt.setString(2, this.evTypeId);
            pstmt.setString(3, evProcessId);
            pstmt.setString(4, Constants.EVOPERATE001);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                HashMap mItem = new HashMap();
                mItem.put("itemId", rs.getString("ev_item_id"));
                mItem.put("itemName", rs.getString("ev_item_name"));
                lItemList.add(mItem);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getItemListByTypePeriod statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getItemListByTypePeriod statistics",
                    sle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return lItemList;
    }
    
    private final static String GET_EVOTHER_ITEMDETAIL_BYSEQ = "SELECT  seq_EV_item_detail,ev_item_id,"
            + " get_sys_code(ev_item_id) AS ev_item_name,ev_emp_id,"
            + " Hr_Get_Name(ev_emp_id) AS ev_emp_name,ev_detail_prop,ev_detail_order "
            + " FROM evs_item_detail "
            + " WHERE seq_ev_item_detail=? ";

    public EvsOtherItemDetail getEvOtherItemDetailBySeq(int seq)
            throws DataAccessException {
        if (this.checkNull()) {
            return null;
        }
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        EvsOtherItemDetail evOtherItem = new EvsOtherItemDetail();
        try {
            con = services.getConnection();
            pstmt = con.prepareStatement(GET_EVOTHER_ITEMDETAIL_BYSEQ);
            pstmt.setInt(1, seq);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                evOtherItem.setEvItemId(rs.getString("ev_item_id"));
                evOtherItem.setEvItemName(rs.getString("ev_item_name"));
                evOtherItem.setEvEmpId(rs.getString("ev_emp_id"));
                evOtherItem.setEvEmpName(rs.getString("ev_emp_name"));
                evOtherItem.setEvDetailProp(rs.getFloat("ev_detail_prop"));
                evOtherItem.setSeqEvOtherItemDetail(rs
                        .getInt("seq_ev_item_detail"));
                evOtherItem.setLEvsOtherColumn(getItemDetailColumn(rs
                        .getInt("seq_ev_item_detail"),
                        getEvOtherColumns(rs.getString("ev_item_id"))));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DataAccessException(
                    "cant execute query for getEvOtherItemDetailBySeq statistics",
                    sqle);
        } catch (ServiceLocatorException sle) {
            throw new DataAccessException(
                    "cant get connection for getEvOtherItemDetailBySeq statistics",
                    sle);
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return evOtherItem;

    }

    private boolean checkNull() {
        if (evPeriodId == null || evPeriodId.equals("") || evTypeId == null
                || evTypeId.equals("")) {
            return true;
        }
        return false;
    }

}