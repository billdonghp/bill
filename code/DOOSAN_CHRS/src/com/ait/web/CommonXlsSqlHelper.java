/**
 * @author qinxd
 * @date 2006-10-8
 */
package com.ait.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @version 1.0
 */
public class CommonXlsSqlHelper {
    //职级别
	public static String KEY_RPGDV = "post_grade";	
	private static String SQL_RPGDV = "select * from hr_rep_postgrade_division_v";
   //工龄别 
	public static String KEY_RWYDV = "work_year";	
	private static String SQL_RWYDV = "select * from HR_REP_WORK_YEAR_DIVISION_V";
   //学历别
	public static String KEY_REDUV = "edu";	
	private static String SQL_REDUV = "select * from HR_REP_EDU_DIVISION_V";
	//年龄别
	public static String KEY_RAGEV = "age";	
	private static String SQL_RAGEV = "select * from HR_REP_AGE_DIVISION_V";
	//民族性别
	public static String KEY_RNATIONV = "nation";	
	private static String SQL_RNATIONV = "select * from HR_REP_NATION_DIVISION_V";
	
	
	public static SqlAndTitle getSqlAndTitle(HttpServletRequest request){
		String xlsKey = request.getParameter("xlsKey");
		return getByKey(xlsKey);
	}
	
	private static SqlAndTitle getByKey(String xlsKey){
		//职级别 hr_rep_postgrade_division_v
		if (KEY_RPGDV.equals(xlsKey))
			return new SqlAndTitle(SQL_RPGDV, "职级别现状报表", "postgrade_division");
		//工龄别 HR_REP_WORK_YEAR_DIVISION_V
		if (KEY_RWYDV.equals(xlsKey))
			return new SqlAndTitle(SQL_RWYDV, "工龄别现状报表", "workyear_division");
		//学历别 HR_REP_EDU_DIVISION_V
		if (KEY_REDUV.equals(xlsKey))
			return new SqlAndTitle(SQL_REDUV, "学历别现状报表", "edu_division");
		//年龄别 HR_REP_AGE_DIVISION_V
		if (KEY_RAGEV.equals(xlsKey))
			return new SqlAndTitle(SQL_RAGEV, "年龄别现状报表", "age_division");
		//民族性别 HR_REP_NATION_DIVISION_V
		if (KEY_RNATIONV.equals(xlsKey))
			return new SqlAndTitle(SQL_RNATIONV, "民族性别现状报表", "nation_division");
		return null;
	}

}
