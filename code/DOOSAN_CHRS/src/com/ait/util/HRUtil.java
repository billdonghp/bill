/**
 * @Copyright AIT
 * @author qinxd
 * @date 2006-8-18
 */
package com.ait.util;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ait.core.config.Configuration;
import com.ait.core.exception.GlRuntimeException;
import com.ait.jdbc.core.JdbcUtil;
import com.ait.jdbc.core.RowResult;
import com.ait.jdbc.core.SQLResult;
import com.ait.utils.IntPair;

/**
 * 需要访问数据库的工具类
 * 
 * @version
 */
public class HRUtil {
	/**
	 * 返回部门最大（最低）的级别
	 * 
	 * @return
	 */
	public static IntPair getMaxAndMinDeptLevel() {
		IntPair ret = new IntPair();
		try {
			String sql = "select max(dept_level) as max_dept_level, min(dept_level) as min_dept_level from hr_department where activity = 1";
			SQLResult rt = JdbcUtil.executeQuery(sql);
			RowResult r = rt.get(0);
			ret.setMax(r.getInt(1));
			ret.setMin(r.getInt(2));
		} catch (SQLException ex) {
           ex.printStackTrace();
           throw new GlRuntimeException("查询部门最大级别触发数据库异常， 请与系统管理员联系！");
		}
		return ret;
	}
	/**
	 * 显示页面中职级（用于页面显示）
	 * @param gradeNo 职别编号
	 * @param gradeName 职级名称
	 * @return
	 */
	public static String  showPostGrade(String postGroupNo, String gradeName){
		if (isYxApp()){
			if (Logger.getLogger(HRUtil.class).isDebugEnabled())
		    	Logger.getLogger(HRUtil.class).debug("***职别编号: " + postGroupNo);
		    if ("Temp".equals(postGroupNo) || "FSE-K".equals(postGroupNo) || "FSE-C".equals(postGroupNo))//不是职级，只是为了找职别用
			     return "";
		}
		return gradeName;
	}
	/**
	 * 得到应用名
	 * @return
	 */
	public static String getApplicationName(){
		try {
			return Configuration.getInstance().getString("/configuration/em2/appName", "");
		} catch (Exception ex){
			ex.printStackTrace();
			Logger.getLogger(HRUtil.class).error("Configuration：" + ex.getMessage());
		}
		return "";		
	}
	/**
	 * 是否为甬兴的应用
	 * @param appName
	 * @return
	 */
	public static boolean isYxApp(String appName){
		if (Logger.getLogger(HRUtil.class).isDebugEnabled())
	    	Logger.getLogger(HRUtil.class).debug("APPLICATION NAME == " + appName);
		return "lgyx".equals(appName);
	}
	/**
	 * 是否为甬兴的应用
	 * @return
	 */
	public static boolean isYxApp(){
		String appName = getApplicationName();
		return isYxApp(appName);
	}

}
