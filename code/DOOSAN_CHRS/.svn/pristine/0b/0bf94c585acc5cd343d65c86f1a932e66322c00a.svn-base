package com.ait.ar.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class DBConnection {
    private Connection conn = null; // 数据库连接对象

    private Statement stmt = null; // SQL语句对象

    private PreparedStatement ps = null; // 带参数的SQL语句对象

    private CallableStatement cs = null; // 存储过程语句对象

    private ResultSet rs = null; // 结果集

    public Connection getConnectionOracle() {
        Logger.getLogger(getClass()).debug("call connBean.getConn() .....");
        conn = ConnBean.getConn();
        return conn;
    }

    // 关闭数据库连接
    public void closeConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlex) {
                Logger.getLogger(getClass()).error(sqlex.toString());
            } catch (Exception ex) {
                Logger.getLogger(getClass()).error(ex.toString());
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlex) {
                Logger.getLogger(getClass()).error(sqlex.toString());
            } catch (Exception ex) {
                Logger.getLogger(getClass()).error(ex.toString());
            }
        }

        if (ps != null ) {
            try {
                ps.close();
            } catch (SQLException sqlex) {
                Logger.getLogger(getClass()).error(sqlex.toString());
            } catch (Exception ex) {
                Logger.getLogger(getClass()).error(ex.toString());
            }
        }

        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException sqlex) {
                Logger.getLogger(getClass()).error(sqlex.toString());
            } catch (Exception ex) {
                Logger.getLogger(getClass()).error(ex.toString());
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlex) {
                Logger.getLogger(getClass()).error("close connection : " + sqlex.toString());
            } catch (Exception ex) {
                Logger.getLogger(getClass()).error(ex.toString());
            }
        }
        Logger.getLogger(getClass()).debug("method close() callded.");
    }

    // 测试连接是否关闭
    public void closeConnection(String plag) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        }
    }

    // 获得结果集，带参数SQL语句
    // 方法重载
    public ResultSet getResultSet(String sql, ArrayList array) {
        Logger.getLogger(getClass()).debug(sql);
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < array.size(); i++) {
                Logger.getLogger(getClass()).debug(
                        "argument : " + (String) array.get(i));
                if (array.get(i) instanceof String) {
                    ps.setString(i + 1, (String) array.get(i));
                } else if (array.get(i) instanceof Integer) {
                    ps.setInt(i + 1, ((Integer) array.get(i)).intValue());
                } else if (array.get(i) instanceof Double) {
                    ps.setDouble(i + 1, ((Double) array.get(i)).doubleValue());
                } else if (array.get(i) instanceof Float) {
                    ps.setFloat(i + 1, ((Float) array.get(i)).floatValue());
                } else if (array.get(i) instanceof Date) {
                    ps.setDate(i + 1, (Date) array.get(i));
                } else if (array.get(i) instanceof Boolean) {
                    ps.setBoolean(i + 1, ((Boolean) array.get(i))
                            .booleanValue());
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        return rs;
    }

    // 取得结果集，调用存储过程
    public ResultSet getResultSetPROC(String proc) {
        Logger.getLogger(getClass()).debug(proc);
        ResultSet rs = null;
        try {
            CallableStatement cs = conn.prepareCall(proc);
            rs = cs.executeQuery();
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        return rs;
    }

    // 取得结果集，调用存储过程,带参数，方法重载
    public ResultSet getResultSetPROC(String proc, ArrayList array)
            throws Exception {
        CallableStatement cs = conn.prepareCall(proc);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) instanceof String)
                cs.setString(i + 1, (String) array.get(i));
            else if (array.get(i) instanceof Integer)
                cs.setInt(i + 1, ((Integer) array.get(i)).intValue());
            else if (array.get(i) instanceof Double)
                cs.setDouble(i + 1, ((Double) array.get(i)).doubleValue());
            else if (array.get(i) instanceof Float)
                cs.setFloat(i + 1, ((Float) array.get(i)).floatValue());
            else if (array.get(i) instanceof Boolean)
                cs.setBoolean(i + 1, ((Boolean) array.get(i)).booleanValue());
        }
        ResultSet rs = cs.executeQuery();

        return rs;
    }

    // 执行插入、更新、删除，不带参数的SQL语句，返回影响行数
    public int update(String sql) {
        int rows = 0;
        try {
            Statement stmt = conn.createStatement();
            rows = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        closeConnection();
        Logger.getLogger(getClass()).debug("affect row : " + rows);
        return rows;
    }

    // 执行插入、更新、删除，调用存储过程，返回影响行数
    public int updatePROC(String proc) {
        int rows = 0;
        try {
            if (conn == null)
                conn = getConnectionOracle();
            CallableStatement cs = conn.prepareCall(proc);
            rows = cs.executeUpdate();
            cs.close();
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        closeConnection();
        return rows;
    }

    // 执行插入、更新、删除，调用存储过程,带参数，返回影响行数
    // 方法重载
    public int updatePROC(String proc, ArrayList array) {
        int rows = 0;
        try {
            CallableStatement cs = conn.prepareCall(proc);
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) instanceof String)
                    cs.setString(i + 1, (String) array.get(i));
                else if (array.get(i) instanceof Integer)
                    cs.setInt(i + 1, ((Integer) array.get(i)).intValue());
                else if (array.get(i) instanceof Double)
                    cs.setDouble(i + 1, ((Double) array.get(i)).doubleValue());
                else if (array.get(i) instanceof Float)
                    cs.setFloat(i + 1, ((Float) array.get(i)).floatValue());
                else if (array.get(i) instanceof Boolean)
                    cs.setBoolean(i + 1, ((Boolean) array.get(i))
                            .booleanValue());
            }
            rows = cs.executeUpdate();
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        closeConnection();
        return rows;
    }

    /*
     * public String getColsName(String arMonth, String type) {
     * 
     * String colsName = null; String sql = "CALL AR_HTML_SQL_HISTORY(?,?,?)";
     * Logger.getLogger(getClass()).debug(sql); this.getConnectionOracle(); try {
     * CallableStatement cs = conn.prepareCall(sql); cs.setString(1, arMonth);
     * cs.setString(2, type); cs.registerOutParameter(3, Types.VARCHAR);
     * cs.execute(); colsName = cs.getString(3); cs.close(); } catch
     * (SQLException sqlex) {
     * Logger.getLogger(getClass()).error(sqlex.toString()); }
     * closeConnection(); return colsName; }
     */
    public String getArPopedom(String empId) {
        String sql1 = "SELECT GROUP_NO " + "FROM AR_DYNAMIC_GROUP "
                + "WHERE GROUP_TYPE ='TIMECARD1' ";
        String sql2 = null;
        String sql3 = null;
        String sql = "SELECT person_id " + "FROM HR_EMPLOYEE_V "
                + "WHERE UPPER(STATUS_CODE) <> 'STATUS03' " + "AND person_id IN(" + "("
                + "SELECT person_id " + "FROM HR_EMPLOYEE " + "WHERE person_id IN ("
                + "SELECT SUPERVISED_DEPTID " + "FROM AR_SUPERVISOR_INFO "
                + "WHERE AR_SUPERVISOR_ID = '" + empId + "'" + ")" + ")UNION("
                + "SELECT person_id " + "FROM HR_EMPLOYEE " + "WHERE person_id='"
                + empId + "'" + "" + ")UNION(" + "(" + "SELECT person_id "
                + "FROM HR_EMPLOYEE " + "WHERE DEPTID IN(" + "SELECT DEPTID "
                + "FROM HR_DEPARTMENT " + "START WITH DEPTID IN("
                + "SELECT SUPERVISED_DEPTID " + "FROM AR_SUPERVISOR_INFO "
                + "WHERE AR_SUPERVISOR_ID = '" + empId + "'" + ")"
                + "CONNECT BY PRIOR DEPTID = PARENT_DEPT_ID" + ")" + ")MINUS("
                + "SELECT person_id " + "FROM HR_EMPLOYEE " + "WHERE person_id IN("
                + "SELECT SUPERVISED_DEPTID " + "FROM AR_SUPERVISOR_INFO "
                + "WHERE AR_SUPERVISOR_ID <> '" + empId + "'" + ")" + ")" + ")"
                + ") ";
        Logger.getLogger(getClass()).debug(sql1);
        try {
            Connection con = getConnectionOracle();
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql1);
            // 临时变量用于内嵌套查询
            int groupId = -1;
            Statement state1 = null;
            ResultSet rs1 = null;
            while (rs.next()) {
                try {
                    groupId = rs.getInt(1);
                    sql2 = /* 通过动态组ID得到查询该组人员的SQL语句 */
                    "SELECT AR_GET_DYNAMICGROUP_NUMBERS(" + groupId
                            + ") AS GROUP_NUMBER FROM DUAL";
                    Logger.getLogger(getClass()).debug(sql2);
                    state1 = con.createStatement();
                    rs1 = state1.executeQuery(sql2);
                    if (rs.next()) {
                        sql3 = rs.getString("GROUP_NUMBER");
                        sql = sql + "AND empid NOT IN(" + sql3 + ") ";
                    }
                    SqlUtil.close(rs1, state1);
                } catch (SQLException sqlex) {
                    Logger.getLogger(getClass()).error(sqlex.toString());
                }
            }
            SqlUtil.close(rs, state, con);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        Logger.getLogger(getClass()).debug("return SQL : " + sql);
        return sql;
    }

    public ArrayList getDateFromTo(String arMonth) {
        ArrayList list = null;
        String sql = "CALL GET_MONTH_FROM_TO(?,?,?)";
        Logger.getLogger(getClass()).debug(sql);
        Connection conn = null;
        CallableStatement cs = null;
        try {
            if (conn == null)
                conn = getConnectionOracle();
            cs = conn.prepareCall(sql);
            cs.setString(1, arMonth);
            cs.registerOutParameter(2, Types.DATE);
            cs.registerOutParameter(3, Types.DATE);
            cs.execute();
            list = new ArrayList();
            list
                    .add(cs.getString(2).substring(0,
                            cs.getString(2).indexOf(" ")));
            list
                    .add(cs.getString(3).substring(0,
                            cs.getString(3).indexOf(" ")));
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(cs, conn);
        }
        return list;
    }

    // 调用完成后必须调用close方法！
    public ResultSet getResultSet(String sql) {
        Logger.getLogger(getClass()).debug(sql);
        try {
            if (conn == null) //判断conn是否已有连接 Pennix
                conn = getConnectionOracle();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        }
        Logger.getLogger(getClass()).debug(
                "return result! mast call method:close()!!!");
        return rs;
    }
}
