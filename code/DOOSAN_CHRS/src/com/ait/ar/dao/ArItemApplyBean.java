package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItemApply;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArItemApplyBean {

    /**
     * addArItemApply
     * 
     * @param values
     *            ArrayList
     */
	AdminBean admin=ApplicationContext.getTheadLocal();
    public int addArItemApply(ArrayList values) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String sql = "INSERT INTO AR_ITEM_APPLY(" + "ITEM_NO, " + "GROUP_NO, "
                + "FLAG, " + "APPLY_TYPE, " + "ACTIVITY, " + "VALUESS, "
                + "CARD_OR_APP" + ")VALUES(" + (String) values.get(0) + ", "
                + "'" + (String) values.get(1) + "', " + "'"
                + (String) values.get(2) + "', " + "'" + (String) values.get(3)
                + "', " + "'" + (String) values.get(4) + "', " + "'"
                + (String) values.get(5) + "', " + "'" + (String) values.get(6)
                + "'" + ")";
        Logger.getLogger(getClass()).debug("return sql : " + sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    /**
     * addArItemApply overload
     * 
     * @param values
     *            ArrayList
     * @param transation
     *            String
     * @return String
     */
    public String addArItemApply(ArrayList values, String transation) {
        int item_no = Integer.parseInt(values.get(0).toString());
        String group_No = values.get(1).toString();
        String flag = values.get(2).toString();
        String apply_type = values.get(3).toString();
        int activity = Integer.parseInt(values.get(4).toString());
        int valuess = Integer.parseInt(values.get(5).toString());
        int card_or_app = Integer.parseInt(values.get(6).toString());
        String sql = "INSERT INTO AR_ITEM_APPLY(" + "ITEM_NO, " + "GROUP_NO, "
                + "FLAG, " + "APPLY_TYPE, " + "ACTIVITY, " + "VALUESS, "
                + "CARD_OR_APP" + ")VALUES(" + item_no + ", " + "'" + group_No
                + "', " + "'" + flag + "', " + "'" + apply_type + "', "
                + +activity + ", " + +valuess + ", " + +card_or_app + ")";
        Logger.getLogger(getClass()).debug("return sql : " + sql);
        return sql;
    }

    /**
     * getApply
     * 
     * @return ArrayList
     */
    
    public ArrayList getApply() {
        ArrayList list = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT CODE_ID, CODE_NAME,CODE_EN_NAME,CODE_KOR_NAME FROM SY_CODE "
                + "WHERE PARENT_CODE = 'OTTypeCode' "
                + "OR PARENT_CODE = 'LeaveTypeCode' "
                //+ "OR PARENT_CODE = 'MatchTypeCode' "
                //+ "OR PARENT_CODE = 'EvectionTypeCode' "
                //+ "OR PARENT_CODE = 'TrainingTypeCode' "
                + "AND ACTIVITY = 1 " 
                +"  AND (CPNY_ID='"+admin.getCpnyId()+"' OR CPNY_ID IS NULL) "
                + "ORDER BY PARENT_CODE, CODE_ID DESC ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
                ArItemApply info = new ArItemApply();
                info.setCode_id(rs.getString("CODE_ID"));
                info.setCode_name(rs.getString("CODE_NAME"));
                info.setCode_en_name(rs.getString("CODE_EN_NAME"));
                info.setCode_kor_name(rs.getString("CODE_KOR_NAME"));
                list.add(info);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }
}
