/*
 * 创建日期 2006-3-4
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.ait.ar.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;

/**
 * @author Administrator
 * 
 */
public class ArDayCalcuJobBean {
    private String procName = "ar_detail_calall();";

    //取得任务的ID
    public String getDayCalcuJobID() {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT JOB FROM user_jobs where rownum=1 and what='" + procName + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                return StringUtil.checkNull(rs.getString("job"),"无");
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return new String("");
    }

    //取得任务的上次执行时间
    public String getJobLastTime() {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT last_date FROM user_jobs where rownum=1 and what='" + procName + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                return StringUtil.checkNull(rs.getString("last_date"),"无");
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return new String("");
    }

    //取得任务的下次执行时间
    public String getJobNextTime() {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT next_date FROM user_jobs where rownum=1 and what='" + procName + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()){
                return StringUtil.checkNull(rs.getString("next_date"),"无");
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return new String("");
    }

    //删除定时任务
    public void delDayCalcuJob() {
        Connection conn = null;
        CallableStatement state = null;
        ResultSet rs = null;
        String sql = "{call ar_job_del}";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state =conn.prepareCall(sql);
            state.execute();
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }


    }

    //添加JOB
    public void addDayCalcuJob(String exeTime) {
        Connection conn = null;
        CallableStatement state = null;
        ResultSet rs = null;
        String sql = "{call ar_job_add(?)}";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state =conn.prepareCall(sql);
            state.setString(1,exeTime);
            state.execute();
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }

    }

    public void updateDayCalcuJob(String exeTime) {
        Connection conn = null;
        CallableStatement state = null;
        ResultSet rs = null;
        String sql = "{call ar_job_update(?)}";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state =conn.prepareCall(sql);
            state.setString(1,exeTime);
            state.execute();
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
    }
}
