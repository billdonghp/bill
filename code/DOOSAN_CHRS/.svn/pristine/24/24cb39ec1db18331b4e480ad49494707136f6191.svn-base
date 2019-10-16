package com.ait.ar.dao;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.ait.ar.bean.*;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

//根据年月日 显示当天考勤
public class ArRecordsBean {

    // 根据年月日 显示当天考勤
    public ArrayList getRecordsDay(String fArMonth, String tArMonth) {
        ArrayList list = null;
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "AR_MAC_RECORDS.RECORD_NO, "
                + "AR_MAC_RECORDS.EMPID, "
                + "AR_MAC_RECORDS.ACTIVE, "
                + "AR_MAC_RECORDS.INSERT_BY, "
                + "AR_MAC_RECORDS.DOOR_TYPE, "
                + "HR_EMPLOYEE.CHINESENAME, "
                + "TO_CHAR(AR_MAC_RECORDS.R_TIME, 'YYYY-MM-DD HH24:MI:SS') AS R_TIME, "
                + "AR_MAC_RECORDS.DOOR_TYPE, "
                + "HR_EMPLOYEE.DEPTID, "
                + "HR_DEPARTMENT.DEPTNAME "
                + "FROM AR_MAC_RECORDS "
                + "LEFT JOIN HR_EMPLOYEE "
                + "ON AR_MAC_RECORDS.EMPID = HR_EMPLOYEE.EMPID "
                + "LEFT JOIN HR_DEPARTMENT "
                + "ON HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
                + "WHERE HR_EMPLOYEE.CHINESENAME IS NOT NULL "
                + "AND TO_CHAR(R_TIME, 'YYYY-MM-DD') = '"
                + fArMonth
                + "' "
                + "ORDER BY HR_EMPLOYEE.DEPTID DESC, AR_MAC_RECORDS.EMPID, AR_MAC_RECORDS.R_TIME ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
                ArRecords info = new ArRecords();
                info.setDeptId(rs.getString("DEPTID"));
                info.setDeptName(rs.getString("DEPTNAME"));
                info.setRecordNo(rs.getInt("RECORD_NO"));
                info.setEmpid(rs.getString("EMPID"));
                info.setEmpName(rs.getString("CHINESENAME"));
                if (rs.getString("DOOR_TYPE").equalsIgnoreCase("IN")) {
                    info.setEnterTime(rs.getString("R_TIME"));
                    info.setOutTime("");
                } else if (rs.getString("DOOR_TYPE").equalsIgnoreCase("OUT")) {
                    info.setEnterTime("");
                    info.setOutTime(rs.getString("R_TIME"));
                }
                info.setActive(rs.getString("ACTIVE"));
                info.setLock(rs.getString("ACTIVE"));
                info.setLock_js(rs.getString("ACTIVE"));
                info.setInsert_by(rs.getString("INSERT_BY"));
                info.setInsert_by_js(rs.getString("INSERT_BY"));
                info.setState(rs.getString("DOOR_TYPE"));
                info.setState_js(rs.getString("DOOR_TYPE"));
                list.add(info);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }

    // 设定登陆人ID
    public void setLoginID(String loginID) {
    }

    // 搜索 刷卡
    public ArrayList getRecordsSearch(String startDateStr, String endDateStr,
            String empId, String state, String loginUserId) {
        // 根据用户的要求筛选记录
        ArrayList list = null;
        String sql = "SELECT "
                + "AR_MAC_RECORDS.RECORD_NO AS RECORD_NO, "
                + "AR_MAC_RECORDS.EMPID AS EMPID, "
                + "AR_MAC_RECORDS.ACTIVE AS ACTIVE, "
                + "AR_MAC_RECORDS.INSERT_BY AS INSERT_BY, "
                + "NVL(to_char(AR_MAC_RECORDS.D_TIME,'YYYY-MM-DD HH24:MI:SS'),' ') AS D_TIME, "
                + "NVL(AR_MAC_RECORDS.REMARK,' ') AS REMARK, "
                + "AR_MAC_RECORDS.DOOR_TYPE AS DOOR_TYPE, "
                + "HR_EMPLOYEE.CHINESENAME AS CHINESENAME, "
                + "TO_CHAR(AR_MAC_RECORDS.R_TIME, 'YYYY-MM-DD HH24:MI:SS') AS R_TIME, "
                + "AR_MAC_RECORDS.DOOR_TYPE AS DOOR_TYPE, "
                + "HR_EMPLOYEE.DEPTID AS DEPTID, "
                + "HR_DEPARTMENT.DEPTNAME AS DEPTNAME "
                + "FROM AR_MAC_RECORDS, HR_EMPLOYEE, HR_DEPARTMENT, AR_SUPERVISOR_INFO "
                + "WHERE AR_MAC_RECORDS.EMPID = HR_EMPLOYEE.EMPID(+) "
                + "AND HR_DEPARTMENT.DEPTID(+) = HR_EMPLOYEE.DEPTID "
                + "AND AR_SUPERVISOR_INFO.SUPERVISED_DEPTID = HR_EMPLOYEE.DEPTID "
                + "AND AR_SUPERVISOR_INFO.AR_SUPERVISOR_ID = ? "
                + "AND AR_MAC_RECORDS.R_TIME BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') + 1 "
                + "AND AR_MAC_RECORDS.DOOR_TYPE LIKE ? "
                + "AND (HR_EMPLOYEE.EMPID LIKE ? OR HR_EMPLOYEE.CHINESENAME LIKE ?) "
                + "ORDER BY HR_EMPLOYEE.DEPTID DESC, AR_MAC_RECORDS.EMPID, AR_MAC_RECORDS.R_TIME ";
        Logger.getLogger(getClass()).debug(sql);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnBean.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, loginUserId);
            Logger.getLogger(getClass()).debug(loginUserId);
            ps.setString(2, startDateStr);
            Logger.getLogger(getClass()).debug(startDateStr);
            ps.setString(3, endDateStr);
            Logger.getLogger(getClass()).debug(endDateStr);
            ps.setString(4, state + "%");
            Logger.getLogger(getClass()).debug(state + "%");
            ps.setString(5, empId + "%");
            Logger.getLogger(getClass()).debug(empId + "%");
            ps.setString(6, "%" + empId + "%");
            Logger.getLogger(getClass()).debug("%" + empId + "%");
            rs = ps.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                ArRecords info = new ArRecords();
                info.setDeptName(rs.getString("DEPTNAME"));
                info.setState(rs.getString("DOOR_TYPE"));
                info.setEmpName(rs.getString("CHINESENAME"));
                info.setActive(rs.getString("ACTIVE"));
                info.setD_time(rs.getString("D_TIME"));
                info.setRemark(rs.getString("REMARK"));
                info.setRecordNo(rs.getInt("RECORD_NO"));
                info.setEmpid(rs.getString("EMPID"));
                if (info.getState().equalsIgnoreCase("IN")) {
                    info.setEnterTime(rs.getString("R_TIME"));
                    info.setOutTime("");
                } else if (info.getState().equalsIgnoreCase("OUT")) {
                    info.setEnterTime("");
                    info.setOutTime(rs.getString("R_TIME"));
                }
                info.setLock(rs.getString("ACTIVE"));
                info.setLock_js(rs.getString("ACTIVE"));
                info.setInsert_by(rs.getString("INSERT_BY"));
                info.setInsert_by_js(rs.getString("INSERT_BY"));
                list.add(info);
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, ps, conn);
        }
        return list;
    }

    // 根据编号得到刷卡
    public ArRecords getRecords(String empId, // 修改哪个员工的补录的记录
            String rTime // 哪个时间的
    ) {
        
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        ArRecords info = null;

        String sql = "SELECT "
                + "TO_CHAR(AR_MAC_RECORDS.R_TIME, 'YYYY-MM-DD HH24:MI:SS') AS R_TIME, "
                + "AR_MAC_RECORDS.DOOR_TYPE AS DOOR_TYPE, "
                + "AR_MAC_RECORDS.ACTIVE AS ACTIVE, "
                + "AR_MAC_RECORDS.OPERATOR_ID AS OPERATOR_ID, "
                + "AR_MAC_RECORDS.INSERT_TIME AS INSERT_TIME,"
                + "AR_MAC_RECORDS.DOOR_TYPE AS DOOR_TYPE, "
                + "HR_EMPLOYEE.CHINESENAME AS CHINESENAME, "
                + "HR_DEPARTMENT.DEPTNAME AS DEPTNAME "
                + "FROM AR_MAC_RECORDS , HR_EMPLOYEE, HR_DEPARTMENT "
                + "WHERE AR_MAC_RECORDS.EMPID = HR_EMPLOYEE.EMPID "
                + "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
                + "AND AR_MAC_RECORDS.EMPID = '" + empId + "'\n"
                + "AND AR_MAC_RECORDS.R_TIME = TO_DATE('" + rTime
                + "', 'YYYY-MM-DD HH24:MI:SS') "
                + "AND AR_MAC_RECORDS.INSERT_BY = 'H' ";
        Logger.getLogger(getClass()).debug(sql);

        // String sql = "select * from AR_RECORDS where
        // to_date(ar_date_str,'yyyy-mm-dd') =
        // to_date('"+ar_date_str+"','yyyy-mm-dd')";
        conn = ConnBean.getConn();
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                info = new ArRecords();
                info.setEmpid(empId);
                info.setEmpName(rs.getString("CHINESENAME"));// 用于显示的员工名称
                info.setDeptName(rs.getString("DEPTNAME"));// 用于显示的部门名称
                info.setState(rs.getString("DOOR_TYPE"));// 进门还是出门
                Logger.getLogger(getClass()).debug(info.getState());
                info.setActive(rs.getString("ACTIVE"));// 标志此条考勤数据在考勤汇总的时候是否可用
                Logger.getLogger(getClass()).debug(info.getActive());
                if (info.getState().equalsIgnoreCase("IN")) {
                    info.setEnterTime(rs.getString("R_TIME"));// 如果是进门就跟进门时间
                    info.setOutTime("");// 出门时间置空
                } else if (info.getState().equalsIgnoreCase("OUT")) {
                    info.setEnterTime("");
                    info.setOutTime(rs.getString("R_TIME"));
                }
                info.setOperatorId(rs.getString("OPERATOR_ID"));
                info.setInsertTime(rs.getDate("INSERT_TIME"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            e.printStackTrace();
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return info;
    }

    /**
     * getItemInfo
     * 
     * @param itemNo
     *            int
     * @return ArItem
     */
    public ArItem getItemInfo(int itemNo) {
        return null;
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

    public String getTime(String time) {
        time = time.substring(0, time.lastIndexOf("."));
        return time;
    }

    // 增加一条考勤数据，作为没有刷卡的补录
    public static synchronized int addRescords(String empId, // 工号
            String rTime, // 补录打卡时间按照 YYYY-MM-DD HH24:MI:SS格式
            String state, // 进门或出门 IN 进门卡 OUT 出门卡 仅限这两种
            String operatorId// 录入者ID(工号)
    ) {
        int affRow = 0;
        Connection conn = null;
        Statement stat = null;
        String sql = "INSERT INTO AR_MAC_RECORDS(" + "RECORD_NO, "
                + "CARD_NO, " + "R_TIME, " + "DOOR_TYPE, " + "ACTIVE, "
                + "INSERT_BY, " + "EMPID, " + "OPERATOR_ID, " + "INSERT_TIME"
                + ")VALUES(" + "0, " + // 记录号为0
                "'0', " + // 没有卡号
                "TO_DATE('" + rTime + "', 'YYYY-MM-DD HH24:MI:SS'), " + // 写入补录打卡时间
                "'" + state + "', " + // 进门卡还是出门卡
                "'Y', " + // 计算汇总时此条数据有效
                "'H', " + // 手工录入
                "'" + empId + "', " + "'" + operatorId + "', " + // 记录哪个人录入的
                "SYSDATE" + // 录入时间
                ") ";
        Logger.getLogger(ArRecordsBean.class).debug(sql);
        try {
            conn = ConnBean.getConn();
            stat = conn.createStatement();
            affRow = stat.executeUpdate(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(ArRecordsBean.class).error(sqlex.toString());
        } finally {
            SqlUtil.close(stat, conn);
        }
        return affRow;
    }

    // 修改
    public int editRescords(List values) {
        Connection conn = null;
        Statement stat = null;
        int affRow = 0;

        String sql = "UPDATE AR_MAC_RECORDS SET " + "R_TIME = TO_DATE('"
                + (String) values.get(0) + "', 'YYYY-MM-DD HH24:MI:SS'), "
                + "DOOR_TYPE = '" + (String) values.get(1) + "', "
                + "ACTIVE = '" + (String) values.get(2) + "', " + "EMPID = '"
                + (String) values.get(3) + "' " + "WHERE EMPID = '"
                + (String) values.get(4) + "' " + "AND R_TIME = TO_DATE('"
                + (String) values.get(5) + "', 'YYYY-MM-DD HH24:MI:SS') ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            stat = conn.createStatement();
            affRow = stat.executeUpdate(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(stat, conn);
        }
        return affRow;
    }
    
//  修改
    //update table1 set rtime='' where (empid='' and rtime='' and type='') or (empid='' and rtime='' and type='')
//    String[] values = 90612;;白振军;;进门卡;;2006-06-02 06:29:47
//    					91325;;米钢;;进门卡;;2006-06-02 04:54:50
//    					91325;;米钢;;进门卡;;2006-06-02 04:54:50
    public int editAllRescords(String[] empIds,String eDate,String remark) {
        Connection conn = null;
        Statement stat = null;
        String tmp = null;
        int affRow = 0;
        
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE AR_MAC_RECORDS SET ");
        sb.append(" R_TIME = TO_DATE('");
        sb.append(eDate);
        sb.append("', 'YYYY-MM-DD HH24:MI:SS'), ");
        sb.append(" D_TIME = R_TIME, INSERT_BY = 'H', ");
        sb.append(" REMARK = '");
        sb.append(remark);
        sb.append("' WHERE ");
        for(int i = 0; i < empIds.length; i++) {
        	tmp = empIds[i];
        	if(tmp == null || "".equals(tmp.trim()))
        		continue;
        	
        	String[] empInfo = tmp.split(";;");
        	sb.append(" ( EMPID = '")
        	  .append(empInfo[0])
        	  .append("' AND DOOR_TYPE = '")
        	  .append(empInfo[2])
        	  .append("' AND TO_CHAR(R_TIME,'YYYY-MM-DD HH24:MI:SS') = '")
        	  .append(empInfo[3])
        	  .append("' ) ");
        	
        	if(i == empIds.length - 1)
        		break;
        	
        	sb.append(" OR ");
        }
        
        Logger.getLogger(getClass()).debug(sb.toString());
        try {
            conn = ConnBean.getConn();
            stat = conn.createStatement();
            affRow = stat.executeUpdate(sb.toString());
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(stat, conn);
        }
        return affRow;
    }

    // 删除
    public int delRescords(int No) throws Exception {
        Connection conn = null;
        Statement stat = null;
        int affRow = 0;
        String sql = "DELETE " + "FROM AR_RECORDS " + "WHERE RECORD_NO = '"
                + No + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            stat = conn.createStatement();
            affRow = stat.executeUpdate(sql);
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(stat, conn);
        }
        return affRow;
    }

    // add by lvhongbin at 2006-01-01
    // 向考勤数据中添加关于员工的信息
    public ArRecords fillDynamicInfo(ArRecords ar, Connection conn) {
        String sql = "select i.chinesename, " + "i.deptid, " + "i.dept_name, "
                + "d.group_no, " + "d.group_name "
                + "from ar_group_conditions a " + "inner join(" + "select "
                + "hr_employee.empid, " + "hr_employee.chinesename, " + // --中文名
                "hr_employee.deptid, " + // --部门ID
                "(" + "select max(hr_department.deptname) "
                + "from hr_department "
                + "where hr_department.deptid = hr_employee.deptid"
                + ") as dept_name " + // --部门名称
                "from hr_employee " + "where empid = '" + ar.getEmpid() + "' " + // --根据工号获取其在哪个动态组
                ")i " + "on a.value1 like '%'||i.chinesename||'%' "
                + "or a.value1 like '%'||i.deptid||'%' "
                + "or a.value1 like '%'||i.chinesename||'%' "
                + "or a.value1 like '%'||i.empid||'%' "
                + "left join ar_dynamic_group d "
                + "on d.group_no = a.group_no ";
        Logger.getLogger(getClass()).debug(sql);
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if (rs != null && rs.next()) {
                ar.setEmpName(rs.getString("chinesename"));
                ar.setDeptId(rs.getString("deptid"));
                ar.setDeptName(rs.getString("dept_name"));
                ar.setDynamicGroupId(rs.getString("group_no"));
                ar.setDynamicGroupName(rs.getString("group_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(rs, state);
        }
        return ar;
    }
    
//  根据编号得到刷卡
    public ArrayList getAllRecords(String[] empIds, // 修改一批员工的打卡记录
            String sTime, String eTime,				// 哪个时间段
            String doorType
    ) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        String sql = " SELECT DISTINCT "
        	+ "        AR_MAC_RECORDS.EMPID AS EMPID, "
        	+ "        AR_MAC_RECORDS.DOOR_TYPE AS DOOR_TYPE, "
        	+ "        HR_EMPLOYEE.CHINESENAME AS CHINESENAME, "
        	+ "        HR_EMPLOYEE.CHINESE_PINYIN AS CHINESE_PINYIN, "
        	+ "        TO_CHAR(AR_MAC_RECORDS.R_TIME, 'YYYY-MM-DD HH24:MI:SS') AS R_TIME, "
        	+ "        HR_DEPARTMENT.DEPTNAME AS DEPTNAME "
        	+ "   FROM AR_MAC_RECORDS, HR_EMPLOYEE, HR_DEPARTMENT, AR_SUPERVISOR_INFO "
        	+ "  WHERE AR_MAC_RECORDS.EMPID = HR_EMPLOYEE.EMPID(+) "
        	+ "    AND HR_DEPARTMENT.DEPTID(+) = HR_EMPLOYEE.DEPTID "
        	+ "    AND AR_SUPERVISOR_INFO.SUPERVISED_DEPTID = HR_EMPLOYEE.DEPTID "
        	//+ "    AND AR_SUPERVISOR_INFO.AR_SUPERVISOR_ID = '99999' "
        	+ "    AND AR_MAC_RECORDS.R_TIME BETWEEN TO_DATE('"+ sTime +"', 'YYYY-MM-DD HH24:MI:SS') AND "
        	+ "        TO_DATE('"+ eTime +"', 'YYYY-MM-DD HH24:MI:SS') "
        	+ "    AND AR_MAC_RECORDS.DOOR_TYPE = '"+doorType+"' ";
        	if(empIds != null) {
	        	sql = sql + "    AND (  HR_EMPLOYEE.EMPID = '"+empIds[0]+"' ";
	        	for(int i = 1,num = empIds.length; i < num; i++){
	        		sql +=  "     OR HR_EMPLOYEE.EMPID = '"+empIds[i]+"' ";
	        	}
	        	sql += "  )  ";
        	}
        	sql = sql + "    ORDER BY AR_MAC_RECORDS.EMPID,R_TIME ";
        Logger.getLogger(getClass()).debug(sql);

        // String sql = "select * from AR_RECORDS where
        // to_date(ar_date_str,'yyyy-mm-dd') =
        // to_date('"+ar_date_str+"','yyyy-mm-dd')";
        conn = ConnBean.getConn();
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
            	ArRecords info = new ArRecords();
                info.setEmpid(rs.getString("EMPID"));
                info.setEmpName(rs.getString("CHINESENAME"));// 用于显示的员工名称
                info.setDeptName(rs.getString("DEPTNAME"));// 用于显示的部门名称
                info.setState(rs.getString("DOOR_TYPE"));// 进门还是出门
                info.setChinese_pinyin(rs.getString("chinese_pinyin"));// 员工名称拼音
                info.setEnterTime(rs.getString("R_TIME"));// 打卡时间
                if (info.getState().equalsIgnoreCase("IN")) {
                	info.setState_name("进门卡");
                } else if (info.getState().equalsIgnoreCase("OUT")) {
                	info.setState_name("出门卡");
                }
                list.add(info);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return list;
    }
}
