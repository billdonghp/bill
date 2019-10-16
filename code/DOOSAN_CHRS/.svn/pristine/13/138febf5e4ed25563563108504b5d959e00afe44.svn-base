package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArShift020;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArShift020Bean {
    private DBConnection db = new DBConnection();

    private ResultSet rs = null;
    
    AdminBean admin=ApplicationContext.getTheadLocal();
    // 得到shift020
    public ArrayList getShift020() {
        ArrayList shift020s = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT " + "A.PK_NO, " + "A.SHIFT_NO, "
                + "B.SHIFT_NAME, " + "B.SHIFT_EN_NAME, " + "B.SHIFT_KOR_NAME, " 
                + "B.DATATYPE, " + "A.AR_ITEM_NO, " + "C.ITEM_NAME, " + "B.SHIFT_ID, "
                + "C.ITEM_EN_NAME, " + "C.ITEM_KOR_NAME, " + "A.BEGIN_DAY_OFFSET, "
                + "D.CODE_NAME BEGIN_DAY_OFFSET_NAME, "
                + "D.CODE_EN_NAME BEGIN_DAY_OFFSET_EN_NAME, "
                + "D.CODE_KOR_NAME BEGIN_DAY_OFFSET_KOR_NAME, " 
                + "A.FROM_TIME, " + "A.END_DAY_OFFSET, " 
                + "E.CODE_NAME END_DAY_OFFSET_NAME, "
                + "E.CODE_EN_NAME END_DAY_OFFSET_EN_NAME, "
                + "E.CODE_KOR_NAME END_DAY_OFFSET_KOR_NAME, " 
                + "A.TO_TIME, "
                + "ROW_NUMBER () OVER (PARTITION BY A.SHIFT_NO ORDER BY A.SHIFT_NO, A.BEGIN_DAY_OFFSET, A.FROM_TIME) AS ROW_NUM, "
                + " B.CPNY_ID,  CPNY.CODE_NAME CPNYNAME "
                + "FROM AR_SHIFT020 A "
                + "LEFT JOIN AR_SHIFT010 B " + "ON A.SHIFT_NO = B.SHIFT_NO "
                + "LEFT JOIN AR_ITEM C " + "ON A.AR_ITEM_NO = C.ITEM_NO "
                + "LEFT JOIN SY_CODE D ON TO_CHAR(A.BEGIN_DAY_OFFSET) = D.CODE_ID "
                + "LEFT JOIN SY_CODE E ON TO_CHAR(A.END_DAY_OFFSET) = E.CODE_ID " 
                + "LEFT JOIN SY_CODE CPNY ON B.CPNY_ID=CPNY.CODE_ID  " 
                + " WHERE (B.CPNY_ID='"+admin.getCpnyId()+"' OR B.CPNY_ID IS NULL) ";
                //+ "ORDER BY A.SHIFT_NO, A.BEGIN_DAY_OFFSET, A.FROM_TIME ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            shift020s = new ArrayList();
            while (rs.next()) {
                ArShift020 ar = new ArShift020();
                ar.setPk_no(rs.getInt("PK_NO"));
                ar.setShift_no(rs.getInt("SHIFT_NO"));
                ar.setShiftId(rs.getString("SHIFT_ID"));
                ar.setShiftName(rs.getString("SHIFT_NAME"));
                ar.setShiftEnName(rs.getString("SHIFT_EN_NAME"));
                ar.setShiftKoName(rs.getString("SHIFT_KOR_NAME"));
                ar.setItem_no(rs.getInt("AR_ITEM_NO"));
                ar.setItemName(rs.getString("ITEM_NAME"));
                ar.setItemEnName(rs.getString("ITEM_EN_NAME"));
                ar.setItemKoName(rs.getString("ITEM_KOR_NAME"));
                ar.setFromDay(rs.getInt("begin_day_offset"));
                ar.setToDay(rs.getInt("end_day_offset"));
                ar.setFromDayName(rs.getString("BEGIN_DAY_OFFSET_NAME"));
                ar.setFromDayEnName(rs.getString("BEGIN_DAY_OFFSET_EN_NAME"));
                ar.setFromDayKoName(rs.getString("BEGIN_DAY_OFFSET_KOR_NAME"));
                ar.setToDayName(rs.getString("END_DAY_OFFSET_NAME"));
                ar.setToDayEnName(rs.getString("END_DAY_OFFSET_EN_NAME"));
                ar.setToDayKoName(rs.getString("END_DAY_OFFSET_KOR_NAME"));
                ar.setFromTime(getTime(rs.getString("from_time")));
                ar.setToTime(getTime(rs.getString("to_time")));
                ar.setDataType(rs.getInt("DATATYPE"));
                ar.setRowNum(rs.getInt("ROW_NUM"));
                ar.setCpnyId(StringUtil.checkNull(rs.getString("CPNY_ID")));
                ar.setCpnyName(StringUtil.checkNull(rs.getString("CPNYNAME"),"公共"));
                
                shift020s.add(ar);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return shift020s;
    }

    // 根据shift_no 得到shift020
    public ArrayList getShift020(int shiftNo) throws Exception {
        ArrayList shift020s = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT " + "A.PK_NO, " + "A.SHIFT_NO, "
		        + "B.SHIFT_NAME, " + "B.SHIFT_EN_NAME, " + "B.SHIFT_KOR_NAME, " 
		        + "B.DATATYPE, " + "A.AR_ITEM_NO, " + "C.ITEM_NAME, " + "B.SHIFT_ID, "
		        + "C.ITEM_EN_NAME, " + "C.ITEM_KOR_NAME, " + "A.BEGIN_DAY_OFFSET, "
		        + "DECODE(A.BEGIN_DAY_OFFSET,0,'当日',1,'次日',2,'三日') AS BEGIN_DAY_OFFSET_NAME, "
		        + "A.FROM_TIME," + " A.END_DAY_OFFSET, " 
		        + "DECODE(A.END_DAY_OFFSET,0,'当日',1,'次日',2,'三日') AS END_DAY_OFFSET_NAME, "
		        + "A.TO_TIME " + "FROM AR_SHIFT020 A "
		        + "LEFT JOIN AR_SHIFT010 B " + "ON A.SHIFT_NO = B.SHIFT_NO "
		        + "LEFT JOIN AR_ITEM C " + "ON A.AR_ITEM_NO = C.ITEM_NO "
                + "WHERE A.SHIFT_NO='" + shiftNo + "' "
                + "ORDER BY A.SHIFT_NO, A.BEGIN_DAY_OFFSET, A.FROM_TIME ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            shift020s = new ArrayList();
            while (rs.next()) {
                ArShift020 ar = new ArShift020();
                ar.setPk_no(rs.getInt("PK_NO"));
                ar.setShift_no(rs.getInt("SHIFT_NO"));
                ar.setShiftId(rs.getString("SHIFT_ID"));
                ar.setShiftName(rs.getString("SHIFT_NAME"));
                ar.setShiftEnName(rs.getString("SHIFT_EN_NAME"));
                ar.setShiftKoName(rs.getString("SHIFT_KOR_NAME"));
                ar.setItem_no(rs.getInt("AR_ITEM_NO"));
                ar.setItemName(rs.getString("ITEM_NAME"));
                ar.setItemEnName(rs.getString("ITEM_EN_NAME"));
                ar.setItemKoName(rs.getString("ITEM_KOR_NAME"));
                ar.setFromDay(rs.getInt("begin_day_offset"));
                ar.setToDay(rs.getInt("end_day_offset"));
                ar.setFromDayName(rs.getString("BEGIN_DAY_OFFSET_NAME"));
                ar.setToDayName(rs.getString("END_DAY_OFFSET_NAME"));
                ar.setFromTime(getTime(rs.getString("from_time")));
                ar.setToTime(getTime(rs.getString("to_time")));
                ar.setDataType(rs.getInt("DATATYPE"));
                shift020s.add(ar);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return shift020s;
    }

    // 截取时间 不要年份
    public String getTime(String time) {
        Logger.getLogger(getClass()).debug("time : " + time);
        if (time != null) {
            try {
                time = time.substring(time.indexOf(" ") + 1, time
                        .lastIndexOf("."));
            } catch (Exception e) {
                Logger.getLogger(getClass()).debug(e.toString());
            }
        }
        return time;
    }

    // 得到日期
    public String getstr(int begin) {
        String str = null;
        if (begin == 0)
            str = "当日";
        if (begin == 1)
            str = "次日";
        if (begin == 2)
            str = "三日";
        return str;
    }

    // 增加shift010
    public void addShift010(ArrayList values) {
        String sql = "INSERT INTO AR_SHIFT010 (SHIFT_NO, SHIFT_NAME, SHIFT_EN_NAME, SHIFT_KOR_NAME, DATATYPE, SHIFT_ID,CPNY_ID) "
        	    + "VALUES('" + values.get(0) + "','" + values.get(1) + "','" + values.get(2) + "','"
                + values.get(3) + "','" + values.get(4) + "','" + values.get(5) + "','"+values.get(6)+"' ) ";
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql); //已关闭连接
        //db.closeConnection();
    }

    // 增加shift020
    public void addShift020(ArrayList values) throws Exception {
        int shiftNo = ((Integer) values.get(0)).intValue();
        int itemNo = ((Integer) values.get(1)).intValue();
        int star_day = ((Integer) values.get(2)).intValue();
        String from_time = (String) values.get(3);
        int end_day = ((Integer) values.get(4)).intValue();
        String to_time = (String) values.get(5);
        String sql = "insert into ar_shift020 values(ar_shift020_seq.nextval,"
                + shiftNo + "," + itemNo + "," + star_day + "," + from_time
                + "," + end_day + "," + to_time + ")";
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql);//已关闭连接
        //db.closeConnection();
    }

    // 删除班次shift010
    public void delShift010(int shiftNo) throws Exception {
        String sql = "delete from ar_shift010 where shift_no=" + shiftNo;
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql);//已关闭连接
        //db.closeConnection();
    }

    // 删除班次shift020
    public void delShift020(int shiftNo) throws Exception {
        String sql = "delete from ar_shift020 where shift_No=" + shiftNo;
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql);//已关闭连接
        //db.closeConnection();
    }

    // 修改班次名称
    public void editShift(int shiftNo, String shiftId, String shiftName, String shiftEnName, String shiftKoName, int dataType)
            throws Exception {
        String sql = "update ar_shift010 set " 
        		+ "shift_id='" + shiftId + "', " 
        	    + "shift_name='" + shiftName + "', " 
        	    + "shift_en_name='" + shiftEnName + "', " 
        	    + "shift_kor_name='" + shiftKoName + "', " 
        	    + "dataType=" + dataType + " " + "where shift_no="
                + shiftNo;
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql);//已关闭连接
        //db.closeConnection();
    }

    // 修改班次
    public void editShifts(ArrayList values, int pk_No) throws Exception {
        int itemNo = ((Integer) values.get(0)).intValue();
        int star_day = ((Integer) values.get(1)).intValue();
        String from_time = (String) values.get(2);
        int end_day = ((Integer) values.get(3)).intValue();
        String to_time = (String) values.get(4);
        String sql = "update ar_shift020 set " + "ar_item_no=" + itemNo + ", "
                + "begin_day_offset=" + star_day + ", " + "from_time="
                + from_time + ", " + "end_day_offset=" + end_day + ", "
                + "to_time=" + to_time + " " + "where pk_no=" + pk_No;
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql);//已关闭连接
        //db.closeConnection();
    }

    // 得到自动增长值
    public int nextvalue() {
        int nextvalue = 0;
        String sql = "select ar_shift010_seq.nextval from dual";
        Logger.getLogger(getClass()).debug(sql);
        try {
            db.getConnectionOracle();
            rs = db.getResultSet(sql);
            while (rs.next()) {
                nextvalue = rs.getInt("nextval");
            }
            db.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
            return 0;
        }
        return nextvalue;
    }

    // 删除 shift020
    public void delArShift020(int pkno) throws Exception {
        String sql = "delete from ar_shift020 where pk_no=" + pkno;
        Logger.getLogger(getClass()).debug(sql);
        db.getConnectionOracle();
        db.update(sql);//已关闭连接
        //db.closeConnection();
    }

}
