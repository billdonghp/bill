package com.ait.ar.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItem;
import com.ait.ar.bean.ArStaItem;
import com.ait.pa.bean.PaDistinctList;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArStaItemBean {
	AdminBean admin=ApplicationContext.getTheadLocal();
    public ArrayList getPA_DISTINCT_LIST() {
        ArrayList list = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT_FIELD,FIELD_NAME,FIELD_EN_NAME,FIELD_KOR_NAME " + "FROM PA_DISTINCT_LIST "
                + "WHERE TABLE_NAME = 'PA_HR_V' AND ACTIVITY=1";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
            	PaDistinctList pdl = new PaDistinctList();
            	pdl.setDistinctField(rs.getString("DISTINCT_FIELD"));
            	pdl.setFieldName(rs.getString("FIELD_NAME"));
            	pdl.setFieldEnName(rs.getString("FIELD_EN_NAME"));
            	pdl.setFieldKorName(rs.getString("FIELD_KOR_NAME"));
                list.add(pdl);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }

    // 得到列表
    public ArrayList getStaItemList() {
        ArrayList list = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT " + "ITEM_NO, " + "ITEM_NAME, " 
        		+ "ITEM_EN_NAME, " + "ITEM_KOR_NAME, " + "UNIT, " 
        		+ "B.CODE_NAME UNIT_NAME, B.CODE_EN_NAME UNIT_EN_NAME, B.CODE_KOR_NAME UNIT_KOR_NAME, "
                + "STA_ITEM_ID, " + "MIN_UNIT, " + "CAL_ORDER, " + "FROM_DATE, " + "TO_DATE,A.CPNY_ID,CPNY.CODE_NAME CPNYNAME "
                + "FROM AR_STA_ITEM A,SY_CODE B ,SY_CODE CPNY  WHERE A.UNIT = B.CODE_ID  AND A.CPNY_ID=CPNY.CODE_ID(+)  AND (A.CPNY_ID='" +admin.getCpnyId()+"' OR A.CPNY_ID IS NULL) ORDER BY CAL_ORDER ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
                ArStaItem ar = new ArStaItem();
                ar.setItemNo(rs.getInt("ITEM_NO"));
                ar.setStaItemId(rs.getString("STA_ITEM_ID"));
                ar.setItemName(rs.getString("ITEM_NAME"));
                ar.setItemEnName(rs.getString("ITEM_EN_NAME"));
                ar.setItemKoName(rs.getString("ITEM_KOR_NAME"));
                ar.setUnit(rs.getString("UNIT"));
                ar.setUnitName(rs.getString("UNIT_NAME"));
                ar.setUnitEnName(rs.getString("UNIT_EN_NAME"));
                ar.setUnitKoName(rs.getString("UNIT_KOR_NAME"));
                ar.setMinUnit(rs.getFloat("MIN_UNIT"));
                ar.setCalOrder(rs.getInt("CAL_ORDER"));
                ar.setFromDate(this.getISnull(rs.getString("FROM_DATE")));
                ar.setToDate(this.getISnull(rs.getString("TO_DATE")));
                ar.setCpnyId(StringUtil.checkNull(rs.getString("CPNY_ID")));
                ar.setCpnyName(StringUtil.checkNull(rs.getString("CPNYNAME"),"公共"));
                list.add(ar);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }

    // 根据编号得到列表
    public ArrayList getStaItemList(int itemNo) {
        ArrayList list = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT " + "ITEM_NO, " + "ITEM_NAME, "
        		+ "ITEM_EN_NAME, " + "ITEM_KOR_NAME, " + "UNIT, "
        		+ "B.CODE_NAME UNIT_NAME, B.CODE_EN_NAME UNIT_EN_NAME, B.CODE_KOR_NAME UNIT_KOR_NAME, "
                + "STA_ITEM_ID, " + "MIN_UNIT, " + "CAL_ORDER, " + "FROM_DATE, " + "TO_DATE "
                + "FROM AR_STA_ITEM A,SY_CODE B WHERE A.UNIT = B.CODE_ID AND ITEM_NO='" + itemNo + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
                ArStaItem ar = new ArStaItem();
                ar.setItemNo(rs.getInt("ITEM_NO"));
                ar.setStaItemId(rs.getString("STA_ITEM_ID"));
                ar.setItemName(rs.getString("ITEM_NAME"));
                ar.setOldItemName(ar.getItemName()) ;
                ar.setItemEnName(rs.getString("ITEM_EN_NAME"));
                ar.setItemKoName(rs.getString("ITEM_KOR_NAME"));
                ar.setUnit(rs.getString("UNIT"));
                ar.setUnitName(rs.getString("UNIT_NAME"));
                ar.setUnitEnName(rs.getString("UNIT_EN_NAME"));
                ar.setUnitKoName(rs.getString("UNIT_KOR_NAME"));
                ar.setMinUnit(rs.getFloat("MIN_UNIT"));
                ar.setCalOrder(rs.getInt("CAL_ORDER"));
                ar.setFromDate(this.getISnull(rs.getString("FROM_DATE")));
                ar.setToDate(this.getISnull(rs.getString("TO_DATE"))) ;
                list.add(ar);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }

    public String getMonth(int month) {
        String temp = Integer.toString(month);
        if (month < 10 && temp.length() < 2)
            temp = "0" + temp;
        return temp;
    }

    public String getDay(int day) {
        String temp = Integer.toString(day);
        if (day < 10 && temp.length() < 2)
            temp = "0" + temp;
        return temp;
    }

    public String getISnull(String str) {
        if (str == null)
            return "&nbsp;";
        else
            return str;
    }

    // 排序 编号 原记录 现在记录
    public int OrderStaItem(int itemNo, int order, int calOrder) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            String sql = "UPDATE AR_STA_ITEM SET " + "CAL_ORDER=" + order + " "
                    + "where cal_order=" + calOrder;// 先把需要修改的改为原来的
            state.addBatch(sql);

            Logger.getLogger(getClass()).debug(sql);
            sql = "UPDATE AR_STA_ITEM SET " + "CAL_ORDER=" + calOrder + " "
                    + "where item_No=" + itemNo;// 再把现在的改好
            Logger.getLogger(getClass()).debug(sql);
            state.addBatch(sql);

            int[] affRowArray = state.executeBatch();
            for (int i = 0; i < affRowArray.length; i++) {
                affRow = affRowArray[i];
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }
    
    /**
     * 验证考勤项目名称
     * 
     * @param values
     *  ArStaItem
     */
    public int validateItemNameInsert(ArStaItem arStaItem) {
    	int count = 0 ;
    	Connection conn = null; // 数据库连接对象
        PreparedStatement ps = null; // 带参数的SQL语句对象
        ResultSet rs = null; // 结果集
    	
        String sql = "SELECT COUNT(STA_ITEM_ID) FROM AR_STA_ITEM WHERE (INSTR(AR_STA_ITEM.ITEM_NAME,?) = 1 OR INSTR(?,AR_STA_ITEM.ITEM_NAME) = 1) ";
        Logger.getLogger(getClass()).debug(sql);
        Logger.getLogger(getClass()).debug(arStaItem.getItemName());
        try {
        	conn = ConnBean.getConn();
            ps = conn.prepareStatement(sql) ;
            ps.setString(1, arStaItem.getItemName()) ;
            ps.setString(2, arStaItem.getItemName()) ;           
            rs = ps.executeQuery() ;
            if(rs.next())
            {
            	count = rs.getInt(1) ;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(rs, ps, conn) ;
        }
        return count ;
    }

    // 增加
    public int AddStaItem(ArrayList values) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;

        String sql = "INSERT INTO AR_STA_ITEM (ITEM_NO,ITEM_NAME,ITEM_EN_NAME,ITEM_KOR_NAME,UNIT,MIN_UNIT,CAL_ORDER,FROM_DATE,TO_DATE,DATATYPE,STA_ITEM_ID,CPNY_ID)" 
        	    + " SELECT MAX(ITEM_NO) + 1,"
                + "'" + (String) values.get(1) + "', " 
                + "'" + (String) values.get(2) + "', " 
                + "'" + (String) values.get(3) + "', " 
                + "'" + (String) values.get(4) + "', " 
                + "'" + ((Float) values.get(5)).floatValue() + "', " 
                + " MAX(CAL_ORDER) + 1, " 
                + "'" + (String) values.get(6) + "', " 
                + "'" + (String) values.get(7)+ "', " 
                + "'NUMBER(14,4)', " 
                + "'" + (String) values.get(8) + "',"
                + "'" + (String) values.get(9) + "' "
                + " FROM AR_STA_ITEM";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }
    
    /**
     * 验证考勤项目名称
     * 
     * @param values
     *  ArStaItem
     */
    public int validateItemNameUpdate(ArStaItem arStaItem) {
    	int count = 0 ;
    	Connection conn = null; // 数据库连接对象
        PreparedStatement ps = null; // 带参数的SQL语句对象
        ResultSet rs = null; // 结果集
    	
        String sql = "SELECT COUNT(STA_ITEM_ID) FROM AR_STA_ITEM WHERE (INSTR(AR_STA_ITEM.ITEM_NAME,?) = 1 OR INSTR(?,AR_STA_ITEM.ITEM_NAME) = 1) AND ITEM_NO != ?";
        Logger.getLogger(getClass()).debug(sql);
        Logger.getLogger(getClass()).debug(arStaItem.getItemName());
        try {
        	conn = ConnBean.getConn();
            ps = conn.prepareStatement(sql) ;
            ps.setString(1, arStaItem.getItemName()) ;
            ps.setString(2, arStaItem.getItemName()) ;
            ps.setInt(3, arStaItem.getItemNo()) ;
            rs = ps.executeQuery() ;
            if(rs.next())
            {
            	count = rs.getInt(1) ;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(rs, ps, conn) ;
        }
        return count ;
    }

    // 修改
    public int EditStaItem(ArrayList values, int itemNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String itemName = (String) values.get(0);
        String itemEnName = (String) values.get(1);
        String itemKoName = (String) values.get(2);
        String unit = (String) values.get(3);
        float minUnit = ((Float) values.get(4)).floatValue();
        String fDate = (String) values.get(5);
        String tDate = (String) values.get(6);
        String staItemId = (String) values.get(7);
        String sql = "UPDATE AR_STA_ITEM SET " + "ITEM_NAME='" + itemName
        		+ "', ITEM_EN_NAME='" + itemEnName + "', ITEM_KOR_NAME='" + itemKoName
        		+ "', STA_ITEM_ID ='" + staItemId 
                + "', " + "UNIT='" + unit + "', " + "MIN_UNIT=" + minUnit
                + ", " + "FROM_DATE='" + fDate + "', " + "TO_DATE='" + tDate
                + "', " + "DATATYPE='NUMBER(14,4)' " + "WHERE ITEM_NO="
                + itemNo;
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    // 删除
    public int DelStaItem(int itemNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String sql = "delete from ar_sta_item " + "where item_no='" + itemNo
                + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    // 编辑列名称
    public int EditColumnName(String oldName, String newName, String type) {
        int affRow = 0;
        Connection conn = null;
        CallableStatement cState = null;
        ArrayList list = new ArrayList();
        list.add(oldName);
        list.add(newName);
        list.add(type);
        String sql = "call add_columns_AR_HISTORY_MONTH(" + "'" + oldName
                + "', " + "'" + newName + "', " + "'" + type + "') ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            cState = conn.prepareCall(sql);
            affRow = cState.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
        	SqlUtil.close(cState, conn);
        }
        if (affRow != 0) {
            Logger.getLogger(getClass()).debug(
                    "affect row :" + affRow + " execute success");
        } else {
            Logger.getLogger(getClass()).warn(
                    "affect row :" + affRow + " execute fail!");
        }
        return affRow;
    }
}
