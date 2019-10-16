package com.ait.ar.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;

import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class ArCalc {

    public ArCalc() {
    }

    public String CalcProcess(String armonth, String empid) {
        Connection conn = null;
        try {
            conn = ConnBean.getConn();
        } catch (Exception e1) {
            Logger.getLogger(getClass()).debug(e1.toString());
        }
        CallableStatement cs = null;
        String result = null;
        String sql = "{CALL AR_MONTH_CAL(?,?)}";
        Logger.getLogger(getClass()).debug(sql);
        try {
            cs = conn.prepareCall(sql);
            cs.setString(1, armonth);
            cs.setString(2, empid);
            Logger.getLogger(getClass()).debug(armonth);
            Logger.getLogger(getClass()).debug(empid);
            cs.registerOutParameter(2, Types.VARCHAR);
            if (cs.executeUpdate() == 1)
                result = cs.getString(2);
            else
                result = "[计算失败]<br>未知错误";
        } catch (SQLException e) {
            result = "[计算失败]<br>"
                    + this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):",
                            "<br>");
            Logger.getLogger(getClass()).error(
                    this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", ""));
        } finally {
            SqlUtil.close(cs, conn);
        }
        return result;
    }

    private String Message(String s, String reg, String to) {
        RE re = new RE(reg);
        while (re.match(s)) {
            s = s.replaceAll(re.getParen(0), to);
        }
        return s;
    }

}