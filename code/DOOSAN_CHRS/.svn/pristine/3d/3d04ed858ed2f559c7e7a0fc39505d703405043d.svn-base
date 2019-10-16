/**
 * @Copyright 
 * @author qinxd
 * @date 2006-8-25
 */
package com.ait.sy.common;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.ParameterType;
import com.ait.jdbc.core.SQLResult;
import com.ait.jdbc.core.SqlParameter;

/**
 * 
 * @version 
 */
public class PaHelper {
	private static final Logger log = Logger.getLogger(PaHelper.class);
    /**
     * 锁定工资详细信息查看
     */
    public static final int LOCK_FLAG = 1;
    /**
     * 可以查看当前月工资详细信息查看权限
     */
    public static final int UNLOCK_FLAG = 0;
    /**
     * 工资开放
     * @param Pamonth
     * @throws Exception
     */
    public static void openProgress(String Pamonth) throws Exception{
    	execProgressFlag(Pamonth, UNLOCK_FLAG);
    }
    /**
     * 得到 工资详细信息查看是否锁定值
     * @param Pamonth
     * @return
     * @throws Exception
     */
    public static Integer getProcessLockFlag(String Pamonth){
    	try {
        	String sql = "select 1 - nvl(pa_open_flag,0) from pa_progress where PA_MONTH_STR = ?";
        	SqlParameter[] params = new SqlParameter[1];
        	params[0] = new SqlParameter(ParameterType.STRING, Pamonth);
        	Integer ret =  JdbcUtil.executeInteger(sql, params);
        	log.debug("lock flag == " + ret);
        	return ret;
    	} catch (SQLException ex){
    		ex.printStackTrace();
    		throw new GlRuntimeException("取工资详细信息查看是否锁定值触发异常！");
    	}
    }
    /**
     * 设定工资详细信息查看
     * @param Pamonth
     * @param lockFlag
     */
    private static void execProgressFlag(String Pamonth, int flag) throws Exception{
    	try {
    	String sql = "select * from pa_progress where PA_MONTH_STR = ?";
    	SqlParameter[] params = new SqlParameter[1];
    	params[0] = new SqlParameter(ParameterType.STRING, Pamonth);
    	SQLResult rt = JdbcUtil.executeQuery(sql, params);

    	params = new SqlParameter[2];
    	if (rt.size() == 0 ){
    		sql = "insert into pa_progress(PA_MONTH_STR, pa_open_flag) values(?, ?)";
    		params[0] = new SqlParameter(ParameterType.STRING, Pamonth);
    		params[1] = new SqlParameter(1-flag);
    	} else {
    		sql = " update pa_progress set pa_open_flag = ? where PA_MONTH_STR = ?";
    		params[0] = new SqlParameter(1-flag);
    		params[1] = new SqlParameter(ParameterType.STRING, Pamonth);
    	}
    	JdbcUtil.executeUpdate(sql, params);
    	} catch (Exception ex){
    		ex.printStackTrace();
    		throw ex;
    	}
    }

}
