package com.ait.ar.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDynamicGroup;
import com.ait.ar.bean.ArSchedule;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArScheduleBean {
    private DBConnection db = new DBConnection();

    private ResultSet rs = null;

    // getDayArrayList
    public ArrayList getDayArrayList(String from_date, String to_date, int count) {
        ArrayList d_count = new ArrayList();
        String[] ymd = from_date.split("-");
        int from_y = Integer.parseInt(ymd[0].toString());
        int from_m = Integer.parseInt(ymd[1].toString());
        int from_d = Integer.parseInt(ymd[2].toString());
        ymd = to_date.split("-");
        // int to_y = Integer.parseInt(ymd[0].toString());
        // int to_m = Integer.parseInt(ymd[1].toString());
        // int to_d = Integer.parseInt(ymd[2].toString());

        for (int i = 0; i < count; i++) {
            d_count.add(from_y + "-" + from_m + "-" + from_d);
            if (from_m == 2) { // 2月
                if (from_y % 4 == 0 && from_y % 100 != 0 || from_y % 400 == 0) {
                    if (from_d >= 29) {
                        from_m += 1; // 月份加1
                        from_d = 1; // 天数归1
                    } else {
                        from_d += 1;
                    }
                } else {
                    if (from_d >= 28) {
                        from_m += 1; // 月份加1
                        from_d = 1; // 天数归1
                    } else {
                        from_d += 1;
                    }
                }
            }

            if (from_m == 1 || from_m == 3 || from_m == 5 || from_m == 7
                    || from_m == 8 || from_m == 10 || from_m == 12) { // 31天
                if (from_d >= 31) {
                    from_m += 1; // 月份加1
                    from_d = 1; // 天数归1
                } else {
                    from_d += 1;
                }
            }
            if (from_m == 4 || from_m == 6 || from_m == 9 || from_m == 11) { // 30天
                if (from_d >= 30) {
                    from_m += 1; // 月份加1
                    from_d = 1; // 天数归1
                } else {
                    from_d += 1;
                }
            }
            if (from_m > 12) {
                from_y += 1;
                from_m = 1;
            }
        }
        return d_count;
    }

    // AddShift
    public void addShift(int count, int length, String empID,
            String[] shift_nos, ArrayList d_count) throws Exception {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        // ArrayList values = new ArrayList();
        int temp = 0; // 需要重复循环几次
        int residual = 0; // 余数
        int d = 0;
        int shiftNo = 0;
        if (count % length != 0) {
            temp = count / length + 1; // 3
            residual = count % length; // 得到余数
        } else {
            temp = count / length;
        }

        conn = ConnBean.getConn();
        for (int i = 0; i < temp; i++) { // 3
            for (int j = 0; j < length; j++) { // 3
                if (i == temp - 1 && residual > 0) { // 最后一次并且有余数
                    for (int k = 0; k < residual; k++) {
                        shiftNo = Integer.parseInt(shift_nos[k].toString());
                        String sql = "select * " + "from AR_SCHEDULE "
                                + "where empid ='" + empID + "' "
                                + "and ar_date_str = '"
                                + (String) d_count.get(d) + "' ";
                        Logger.getLogger(getClass()).debug(sql);
                        state = conn.createStatement();
                        rs = state.executeQuery(sql);

                        Statement stateSub = conn.createStatement();
                        if (rs.next()) {
                            if (rs.getString("lock_yn").equals("N")) {
                                sql = "delete AR_SCHEDULE " + "where empid ='"
                                        + empID + "' " + "and ar_date_str = '"
                                        + (String) d_count.get(d) + "' ";
                                Logger.getLogger(getClass()).debug(sql);
                                stateSub.addBatch(sql);
                                sql = "call pr_MakeSchedule(" + "'" + empID
                                        + "', " + "to_date('"
                                        + (String) d_count.get(d)
                                        + "','yyyy-mm-dd'), " + "'" + shiftNo
                                        + "')";
                                Logger.getLogger(getClass()).debug(sql);
                                stateSub.addBatch(sql);
                            }
                        } else {
                            sql = "call pr_MakeSchedule(" + "'" + empID + "', "
                                    + "to_date('" + (String) d_count.get(d)
                                    + "','yyyy-mm-dd'), " + "'" + shiftNo
                                    + "') ";
                            Logger.getLogger(getClass()).debug(sql);
                            stateSub.addBatch(sql);
                        }
                        stateSub.executeBatch();
                        SqlUtil.close(stateSub);
                        SqlUtil.close(rs, state);
                        d += 1;
                    }// end for
                } else {
                    shiftNo = Integer.parseInt(shift_nos[j].toString());
                    String sql = "select * from "
                            + "AR_SCHEDULE where empid ='" + empID + "' "
                            + "and ar_date_str = '" + (String) d_count.get(d)
                            + "' ";
                    Logger.getLogger(getClass()).debug(sql);
                    state = conn.createStatement();
                    rs = state.executeQuery(sql);

                    Statement stateSub = conn.createStatement();
                    if (rs.next()) {
                        if (rs.getString("lock_yn").equals("N")) {
                            sql = "delete AR_SCHEDULE " + "where empid ='"
                                    + empID + "' " + "and ar_date_str = '"
                                    + (String) d_count.get(d) + "' ";
                            Logger.getLogger(getClass()).debug(sql);
                            stateSub.addBatch(sql);

                            sql = "call pr_MakeSchedule(" + "'" + empID + "', "
                                    + "to_date('" + (String) d_count.get(d)
                                    + "','yyyy-mm-dd'), " + "'" + shiftNo
                                    + "')";
                            Logger.getLogger(getClass()).debug(sql);
                            stateSub.addBatch(sql);
                        }
                    } else {
                        sql = "call pr_MakeSchedule('" + empID + "',to_date('"
                                + (String) d_count.get(d) + "','yyyy-mm-dd'),"
                                + shiftNo + ")";
                        Logger.getLogger(getClass()).debug(sql);
                        stateSub.addBatch(sql);
                    }
                    stateSub.executeBatch();
                    d += 1;
                    SqlUtil.close(stateSub);
                    SqlUtil.close(rs, state);
                }
            }
        }
        SqlUtil.close(conn);
    }

    public void addShiftGroup(String groupID, String fromDate, String toDate,
            String shiftNOs) {
        fromDate = "to_date('" + fromDate + "','yyyy-mm-dd')";
        toDate = "to_date('" + toDate + "','yyyy-mm-dd')";
        Connection conn = null;
        Statement state = null;
        String sql = "call ar_shift_item ('" + groupID + "'," + fromDate + ","
                + toDate + ",'" + shiftNOs + "')";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            state.execute(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
    }

    // 根据动态组添加排班信息
    public void addShiftByGroup(String groupId, String startDate,
            String endDate, String[] shiftNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        StringBuffer empIdEnumeration = null;
        String sql = "SELECT field_name, relation, value1 "
                + "FROM ar_group_conditions " + "WHERE group_no = '" + groupId
                + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            sql = "select empid " + "from　ar_emp_info_v " + "where ";
            while (rs != null && rs.next()) {
                sql += rs.getString("field_name") + " "
                        + rs.getString("relation") + " "
                        + rs.getString("value1") + " or ";
            }
            sql += "false ";
            SqlUtil.close(rs, state);
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            empIdEnumeration = new StringBuffer();
            while (rs != null && rs.next()) {
                empIdEnumeration.append(rs.getInt("empid") + "', '");
            }
            empIdEnumeration.append(" ");
            // String empIdEnumerationStr = "in('" +
            // empIdEnumeration.toString().replaceAll(", ' ", ")");
            SqlUtil.close(rs, state);

            // 还没写完，明天继续
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
    }

    public ArrayList disPlaySchedule(String empID) throws Exception {
        if (empID == null)
            return null;
        String from_time = null;
        String to_time = null;
        ArrayList schedule = new ArrayList();
        String sql = "select " + "AR_ITEM_NO, shift_no, "
                + "from_time, to_time " + "from ar_schedule " + "where empID='"
                + empID + "'";
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        rs = db.getResultSet(sql);
        while (rs.next()) {
            ArSchedule s = new ArSchedule();
            s.setEmpID(empID);
            s.setShift_Item(this.getItemName(rs.getInt("AR_ITEM_NO")));
            s.setShiftName(this.getShiftName(rs.getInt("shift_no")));
            from_time = rs.getString("from_time");
            from_time = from_time.substring(0, from_time.lastIndexOf("."));
            s.setFrom_time(from_time);
            to_time = rs.getString("to_time");
            to_time = to_time.substring(0, to_time.lastIndexOf("."));
            s.setTo_time(to_time);
            schedule.add(s);
        }
        db.closeConnection();
        return schedule;
    }

    // 根据ShiftNo 得到ShiftName
    public String getShiftName(int shiftNo) throws Exception {
        String shiftName = null;
        String sql = "select * from ar_shift010 where shift_no=" + shiftNo;
        Logger.getLogger(getClass()).debug(sql);
        ResultSet rs1 = db.getResultSet(sql);
        if (rs1.next()) {
            shiftName = rs1.getString("shift_Name");
        }
        db.closeConnection();
        return shiftName;
    }

    // 得到arItemName 根据Item_no
    public String getItemName(int itemNo) throws Exception {
        String itemName = null;
        String sql = "select item_name from ar_item where item_no=" + itemNo;
        Logger.getLogger(getClass()).debug(sql);
        ResultSet rs1 = db.getResultSet(sql);
        if (rs1.next()) {
            itemName = rs1.getString("item_name");
        }
        db.closeConnection();
        return itemName;
    }

    // 按组号添加班次
    public void addGroupShift(ArrayList values) throws Exception {
        Connection conn = null;
        Statement state = null;
        String type = (String) values.get(0);
        String shiftID = (String) values.get(1);
        String fromDate = (String) values.get(2);
        String toDate = (String) values.get(3);
        String shifts = (String) values.get(4);
        String adminId = (String) values.get(5);
        String sql = "call ar_make_schedule(" + "'" + type + "', " + "'"
                + shiftID + "', " + fromDate + ", " + toDate + ", '" + shifts + "', '" + adminId
                + "')";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            state.execute(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
    }

    // 按部门号添加班次
    public int addDeptShift(ArrayList values) throws Exception {
        int affRow = 0;
        Connection conn = null;
        CallableStatement state = null;
        String detpID = (String) values.get(0);
        String fromDate = (String) values.get(1);
        String toDate = (String) values.get(2);
        String shifts = (String) values.get(3);
        String sql = "call ar_deptid_shift('" + detpID + "'," + fromDate + ","
                + toDate + ",'" + shifts + "')";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.prepareCall(sql) ;
            state.execute();
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    // 得到动态组号
    public ArrayList getGroupNo(String cpny_id) throws Exception {
        ArrayList item = new ArrayList();
        String sql = "SELECT GROUP_NO, GROUP_NAME,GROUP_EN_NAME,GROUP_KOR_NAME FROM AR_DYNAMIC_GROUP where cpny_id='"+ cpny_id +"' ORDER BY GROUP_NAME";
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        rs = db.getResultSet(sql);
        while (rs.next()) {
            ArDynamicGroup ar = new ArDynamicGroup();
            ar.setGroupNo(rs.getInt("GROUP_NO"));
            ar.setGroupName(rs.getString("GROUP_NAME"));
            ar.setGroupEnName(rs.getString("GROUP_EN_NAME"));
            ar.setGroupKorName(rs.getString("GROUP_KOR_NAME"));
            item.add(ar);
        }
        db.closeConnection();
        return item;
    }
}
