package com.ait.ar.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ait.ar.bean.DeptRoll;

public class GatnerInfoBean {
	private DBConnection db = new DBConnection();

	private ResultSet rs = null;

	public int getArDay(String str) {
		str = str.substring(0, str.indexOf(" "));
		return Integer.parseInt(str.substring(str.lastIndexOf("-") + 1));
	}

	//得到所有应该显示的员工SQL
	public String getEmpView_sql(String deptid, String date, String key) {
		String sql = "select DATE_STARTED,empid,get_emp_name(empid) as chinesename,deptid,get_dept_name(deptid) as deptname from hr_employee where";
		sql += " (date_left is null or to_char(date_left,'yyyy-mm')>'" + date
				+ "')";
		sql += " and deptid in";
		sql += " (select deptid from hr_department START WITH deptid='"
				+ deptid + "' connect by  PRIOR deptid=parent_dept_id)";
		sql += " and (empid like '%" + key
				+ "%' or get_emp_name(empid) like '%" + key + "%')";
		return sql;
	}

	public String getEmpView_sql1(String deptid, String date, String key) {
		String sql = "select empid from hr_employee where";
		sql += " (date_left is null or to_char(date_left,'yyyy-mm')>'" + date
				+ "')";
		sql += " and deptid in";
		sql += " (select deptid from hr_department START WITH deptid='"
				+ deptid + "' connect by  PRIOR deptid=parent_dept_id)";
		sql += " and (empid like '%" + key
				+ "%' or get_emp_name(empid) like '%" + key + "%')";
		return sql;
	}

	//得到所有有请假记录的员工SQL
	public String getEmpLeaveView_sql(String deptid, String date, String key) {
		String sql = "select empid,get_emp_name(empid) as chinesename,leave_type_code,get_code_name(leave_type_code) as leave_type_name,leave_date,leave_time  from ar_emp_leave where empid in";
		sql += " (" + this.getEmpView_sql1(deptid, date, key) + ")";
		sql += " and to_char(leave_date,'yyyy-mm')='" + date + "'";
		sql += " order by empid asc";
		return sql;
	}

	//得到所有迟到记录的员工SQL
	public String getEmpLateView_sql(String deptid, String date, String key) {
		String sql = "select empid,get_emp_name(empid) as chinesename,late_date,late_time  from ar_emp_late where empid in";
		sql += " (" + this.getEmpView_sql1(deptid, date, key) + ")";
		sql += " and to_char(late_date,'yyyy-mm')='" + date + "'";
		sql += " order by empid asc";
		return sql;
	}

	//得到所有早退记录的员工SQL
	public String getEmpLeaveEarlyView_sql(String deptid, String date,
			String key) {
		String sql = "select empid,get_emp_name(empid) as chinesename,early_date,early_time  from ar_emp_leave_early where empid in";
		sql += " (" + this.getEmpView_sql1(deptid, date, key) + ")";
		sql += " and to_char(early_date,'yyyy-mm')='" + date + "'";
		sql += " order by empid asc";
		return sql;
	}

	//得到所有年假记录的员工
	public String getEmpAnnualy_sql(String deptid, String date, String key,
			String year) {
		String sql = "select LEFT_YEAR_DAYS,EMPID,get_emp_name(EMPID) as chinesename from ar_annual_list_v where empid in";
		sql += " (" + this.getEmpView_sql1(deptid, date, key) + ")";
		sql += " and HOLIDAY_YEAR='" + year + "'";
		sql += " order by empid asc";
		return sql;
	}

	//获取应该显示员工结果集
	public ArrayList getRsEmp(String sql) throws Exception {
		ArrayList empList = new ArrayList();
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			DeptRoll empInfo = new DeptRoll();
			empInfo.setEmpid(rs.getString("empid"));
			empInfo.setEmpname(rs.getString("chinesename"));
			empInfo.setDeptid(rs.getString("deptid"));
			empInfo.setDepartment(rs.getString("deptname"));
			empInfo.setDATE_STARTED(checkTime(rs.getString("DATE_STARTED")));
			empList.add(empInfo);
		}
		db.closeConnection();
		return empList;
	}

	//获取有请假记录员工结果集
	public ArrayList getRsLeave(String sql) throws Exception {
		ArrayList leaveList = new ArrayList();
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			DeptRoll leaveInfo = new DeptRoll();
			leaveInfo.setEmpid(rs.getString("empid"));
			leaveInfo.setEmpname(rs.getString("chinesename"));
			leaveInfo.setLeavetypecode(rs.getString("leave_type_code"));
			leaveInfo.setLeavetypename(rs.getString("leave_type_name"));
			leaveInfo.setArDay(getArDay(rs.getString("leave_date")));
			leaveInfo.setLeaveTime(rs.getInt("leave_time"));
			leaveList.add(leaveInfo);
		}
		db.closeConnection();
		return leaveList;
	}

	//获取所有迟到记录员工结果集
	public ArrayList getRsLate(String sql) throws Exception {
		ArrayList lateList = new ArrayList();
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			DeptRoll lateInfo = new DeptRoll();
			lateInfo.setEmpid(rs.getString("empid"));
			lateInfo.setEmpname(rs.getString("chinesename"));
			lateInfo.setArDay(getArDay(rs.getString("late_date")));
			lateInfo.setLateTime(rs.getInt("late_time"));
			lateList.add(lateInfo);
		}
		db.closeConnection();
		return lateList;
	}

	//获取所有早退记录员工结果集
	public ArrayList getRsLeaveEarly(String sql) throws Exception {
		ArrayList earlyList = new ArrayList();
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			DeptRoll earlyInfo = new DeptRoll();
			earlyInfo.setEmpid(rs.getString("empid"));
			earlyInfo.setEmpname(rs.getString("chinesename"));
			earlyInfo.setArDay(getArDay(rs.getString("early_date")));
			earlyInfo.setLeaveEarlyTime(rs.getInt("early_time"));
			earlyList.add(earlyInfo);
		}
		db.closeConnection();
		return earlyList;
	}

	//获取所有记录员工年假结果集
	public ArrayList getRsAnnualy(String sql) throws Exception {
		ArrayList annualyList = new ArrayList();
		db.getConnectionOracle();
		rs = db.getResultSet(sql);
		while (rs.next()) {
			DeptRoll annulyInfo = new DeptRoll();
			annulyInfo.setEmpid(rs.getString("empid"));
			annulyInfo.setEmpname(rs.getString("chinesename"));
			annulyInfo.setLeft_year_days(rs.getInt("LEFT_YEAR_DAYS"));
			annualyList.add(annulyInfo);
		}
		db.closeConnection();
		return annualyList;
	}

	public ArrayList getGatnerFullInfo(String deptid, String date, String key,
			String year, ArrayList dayCount, ArrayList code_id)
			throws Exception {
		DeptRollBean daod = new DeptRollBean();
		//获取员工列表SQL
		String sql = this.getEmpView_sql(deptid, date, key);
		//获取员工列表结果集合
		ArrayList empList = this.getRsEmp(sql);
		//获取请假记录列表SQL
		sql = this.getEmpLeaveView_sql(deptid, date, key);

		//获取请假记录列表集合
		ArrayList leaveList = this.getRsLeave(sql);
		//    //获取迟到记录列表SQL
		//    sql=this.getEmpLateView_sql(deptid,date,key);
		//    //获取迟到记录列表SQL
		//    ArrayList lateList=this.getRsLate(sql);
		//    //获取早退记录列表SQL
		//    sql=this.getEmpLeaveEarlyView_sql(deptid,date,key);
		//    //获取早退记录列表集合
		//    ArrayList earlyList=this.getRsLeaveEarly(sql);
		//    //获取年假记录列表SQL
		//    sql=this.getEmpAnnualy_sql(deptid,date,key,year);
		//    //获取年假记录列表集合
		//    ArrayList annulyList=this.getRsAnnualy(sql);
		//////////////////////////////////////////
		ArrayList rsLeave = new ArrayList();
		//循环取出所有LEAVE类型的数据
		for (int i = 0; i < code_id.size(); i++) {
			//得出该类的SQL

			sql = daod.getDeptViewSql_leaveType((String) code_id.get(i),
					deptid, date, key);
			rsLeave.add(daod.getRs_Leave(sql));
		}
		//取出迟到
		sql = daod.getDeptViewSql_late(deptid, date, key);
		ArrayList lateList_d = daod.getRs_Late(sql);
		//取出早退
		sql = daod.getDeptViewSql_leave_Early(deptid, date, key);
		ArrayList earlyList_d = daod.getRs_LeaveEarly(sql);
		//取出年假
		sql = daod.getDeptViewSql_leftYear(deptid, date, key, year);
		ArrayList yearList_d = daod.getRs_leftYear(sql);

		DeptRoll empInfo = null;
		DeptRoll leaveInfo = null;
		DeptRoll lateInfo = null;
		DeptRoll earlyInfo = null;
		DeptRoll annulyInfo = null;
		ArrayList full = new ArrayList();
		ArrayList tempLeave = null;
		for (int e = 0; e < empList.size(); e++) {//循环每个人
			empInfo = (DeptRoll) empList.get(e);
			ArrayList info = new ArrayList();
			info.add(Integer.toString(e));
			info.add(empInfo.getEmpid());
			info.add(empInfo.getEmpname());
			info.add(empInfo.getDATE_STARTED());
			info.add(empInfo.getDepartment());
			//插入天数详细记录
			for (int d = 1; d <= dayCount.size(); d++) {//循环每一天 的记录 没有为空
				//先加上一个空记录
				info.add("&nbsp;");
				for (int le = 0; le < leaveList.size(); le++) {//循环所有请假信息
					leaveInfo = (DeptRoll) leaveList.get(le);

					if (empInfo.getEmpid().equals(leaveInfo.getEmpid())) {//找到有同个人的记录

						if (d == leaveInfo.getArDay()) {//并且是同一天
							info.remove(info.size() - 1);
							info.add(getView(leaveInfo.getLeavetypecode())
									+ " " + leaveInfo.getLeaveTime());
						}
					}
				}
			}
			//添加请假记录 月汇总
			for (int j = 0; j < rsLeave.size(); j++) {//获取每个类型
				tempLeave = (ArrayList) rsLeave.get(j);//得到一个类型 记录集合
				info.add("0");
				for (int h = 0; h < tempLeave.size(); h++) {
					leaveInfo = (DeptRoll) tempLeave.get(h);//得到一个类型的基本信息
					if (leaveInfo.getEmpid().equals(empInfo.getEmpid())) {//如果是同个人 肯定只进一次
						info.remove(info.size() - 1);//删除前面预先添加项目
						info.add(Integer.toString(leaveInfo.getLeaveTime()));
					}
				}
			}
			//添加迟到记录月汇总
			info.add("0");

			for (int l = 0; l < lateList_d.size(); l++) {
				lateInfo = (DeptRoll) lateList_d.get(l);//取到迟到的一条记录

				if (lateInfo.getEmpid().equals(empInfo.getEmpid())) {//有迟到记录
					info.remove(info.size() - 1);//删除前面预先添加项目

					info.add(Integer.toString(lateInfo.getLateTime()));
				}
			}
			info.add("0");

			for (int ea = 0; ea < earlyList_d.size(); ea++) {
				earlyInfo = (DeptRoll) earlyList_d.get(ea);//取到早退的一条记录

				if (earlyInfo.getEmpid().equals(empInfo.getEmpid())) {//有早退记录
					info.remove(info.size() - 1);//删除前面预先添加项目

					info.add(Integer.toString(earlyInfo.getLeaveEarlyTime()));
				}
			}
			info.add("0");

			for (int y = 0; y < yearList_d.size(); y++) {
				annulyInfo = (DeptRoll) yearList_d.get(y);//取到一条年假记录

				if (annulyInfo.getEmpid().equals(empInfo.getEmpid())) {//有年假记录
					info.remove(info.size() - 1);//删除前面预先添加项目

					info.add(Integer.toString(annulyInfo.getLeft_year_days()));
				}
			}

			full.add(info);
		}
		return full;
	}

	public String getView(String str) {
		return str.substring(0, 1);
	}

	public String checkTime(String str) {
		return str.substring(0, str.indexOf(" "));
	}

}
