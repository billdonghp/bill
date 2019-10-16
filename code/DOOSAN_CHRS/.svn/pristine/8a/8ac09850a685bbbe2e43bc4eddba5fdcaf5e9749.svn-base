package com.ait.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: AIT
 * </p>
 * 
 * @author AIT
 * @version 1.0
 */

public class SqlUtil {
    protected SqlUtil() {
    }

    /**
     * sql
     * 
     * @param str
     * @return
     */
    public static String checkSqlParam(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

    public static void close(Connection conn) {
        close(null, null, null, conn);
    }

    public static void close(ResultSet rs, Statement stmt) {
        close(rs, stmt, null, null);
    }
    
    public static void close(Statement stmt) {
        close(null, stmt, null, null);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        close(rs, stmt, null, conn);
    }

    public static void close(ResultSet rs, PreparedStatement pstmt) {
        close(rs, null, pstmt, null);
    }

    public static void close(ResultSet rs, PreparedStatement pstmt,
            Connection conn) {
        close(rs, null, pstmt, conn);
    }

    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, null, conn);
    }

    public static void close(PreparedStatement pstmt, Connection conn) {
        close(null, null, pstmt, conn);
    }

    public static void close(Statement stmt, PreparedStatement pstmt,
            Connection conn) {
        close(null, stmt, pstmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt,
            PreparedStatement pstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException se) {
                Logger.getLogger(SqlUtil.class).error(se.toString());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException se) {
                Logger.getLogger(SqlUtil.class).error(se.toString());
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            } catch (SQLException se) {
                Logger.getLogger(SqlUtil.class).error(se.toString());
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException se) {
                Logger.getLogger(SqlUtil.class).error(se.toString());
            }
        }
        
        //Logger.getLogger(" SqlUtil: ").debug("method close() callded.");
    }
}
