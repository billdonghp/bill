package com.ait.ar.dao;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.DeptRoll;
public class DeptRollBean {
  private DBConnection db=new DBConnection();
  private ResultSet rs=null;


  public ArrayList getDeptRollEmps(ArrayList values) throws Exception{
    ArrayList list=new ArrayList();
    String sql=
    	"select " +
	    	"EMPID, " +
	    	"CHINESENAME," +
	    	"deptid, " +
	    	"get_department(deptid) as department " +
    	"from hr_employee " +
    	"where (date_left is null or to_char(date_left,'yyyy-mm') > ?) " +
    	"and deptid in (" +
    		"select deptid " +
    		"from hr_department " +
    		"START WITH deptid= ? " +
    		"connect by PRIOR deptid=parent_dept_id) " +
    		"and (" +
    			"empid like ? " +
    			"or CHINESENAME like ?" +
  			") ";
    Logger.getLogger(getClass()).debug(sql);
    db.getConnectionOracle();
    rs=db.getResultSet(sql,values);
    while(rs.next()){
      DeptRoll info=new DeptRoll();
      info.setEmpid(rs.getString("empid"));
      info.setEmpname(rs.getString("chinesename"));
      info.setDepartment(rs.getString("department"));
      list.add(info);
    }
    db.closeConnection();
    return list;
  }

  public ArrayList getLeaveType() throws Exception {
    ArrayList list=new ArrayList();
    ArrayList code_name=new ArrayList();
    ArrayList code_id=new ArrayList();
    String sql=
						    "SELECT " +
						    	"CODE_ID, " +
						    	"CODE_NAME " +
						  	"FROM SY_CODE " +
						  	"WHERE PARENT_CODE='LEAVETYPECODE' " +
						  	"ORDER BY CODE_ID ";
    Logger.getLogger(getClass()).debug(sql);
    db.getConnectionOracle();
    rs=db.getResultSet(sql);
    while(rs.next()){
      code_id.add(rs.getString("CODE_ID"));
      code_name.add(rs.getString("CODE_NAME"));
    }
    list.add(code_id);
    list.add(code_name);
    db.closeConnection();
    return list;
  }

  public void DeptRollAddLeave(ArrayList values) throws Exception{
    String empid=(String)values.get(0);
    String starttime=(String)values.get(1);
    String endtime=(String)values.get(2);
    String typecode=(String)values.get(3);
    String create=(String)values.get(4);
    String hours=(String)values.get(5);
    String reason=(String)values.get(6);
    String sql="call ar_add_emp_leave_pr('" + empid + "','" + starttime+"','"+endtime+"','"+typecode+"','"+create+"',"+hours+",'"+reason+"')";
    Logger.getLogger(getClass()).debug(sql);
    db.getConnectionOracle();
    db.updatePROC(sql);
    db.closeConnection();
  }

  public String getDeptViewSql_emp(String deptid,String date,String key){
    String sql=
    	"select " +
    		"empid, " +
    		"get_emp_name(empid) as chinesename " +
    	"from ar_emp_leave " +
    	"where empid in(" +
    		"select empid " +
    		"from hr_employee " +
    		"where (date_left is null or to_char(date_left,'yyyy-mm')>'" + date + "') " +
    		"and deptid in (" +
    			"select deptid " +
    			"from hr_department " +
    			"START WITH deptid='" + deptid + "' connect by PRIOR deptid=parent_dept_id" +
    		") " +
    		"and (" +
    			"empid like '%" + key + "%' " +
    			"or get_emp_name(empid) like '%"+key+"%'" +
			")" +
		") " +
		"and to_char(leave_date,'yyyy-mm')='" + date + "' " +
		"group by empid";
    Logger.getLogger(getClass()).debug("return sql string : " + sql);
    return sql;
  }

  public String getDeptViewSql_leaveType(String typeCode,String deptid,String date,String key){
    String sql=
    	"select " +
    		"sum(leave_time), empid " +
    	"from ar_emp_leave " +
    	"where empid in(" +
    		"select empid " +
    		"from hr_employee " +
    		"where (" +
    			"date_left is null " +
    			"or to_char(date_left,'yyyy-mm')>'"+date+"'" +
    		") " +
    		"and deptid in(" +
    			"select deptid " +
    			"from hr_department " +
    			"START WITH deptid='"+deptid+"' " +
    			"connect by PRIOR deptid=parent_dept_id" +
  			") " +
				"and(" +
					"empid like '%"+key+"%' " +
					"or get_emp_name(empid) like '%"+key+"%'" +
				")" +
			")" +
			"and to_char(leave_date,'yyyy-mm')='"+date+"' " +
			"and leave_type_code='"+typeCode+"' " +
			"group by empid " +
			"order by empid asc";
    Logger.getLogger(getClass()).debug("return sql string : " + sql);
    return sql;
  }

  public String getDeptViewSql_late(String deptid,String date,String key){
    String sql=
    	"select " +
    	"sum(late_time),empid " +
    	"from ar_emp_late " +
    	"where empid in(" +
    		"select empid " +
    		"from hr_employee " +
    		"where (" +
    			"date_left is null " +
    			"or to_char(date_left,'yyyy-mm')>'"+date+"'" +
			") " +
			"and deptid in (" +
				"select deptid " +
				"from hr_department " +
				"START WITH deptid='"+deptid+"' " +
				"connect by PRIOR deptid=parent_dept_id" +
			") " +
    		"and (" +
    			"empid like '%"+key+"%' " +
    			"or get_emp_name(empid) like '%"+key+"%'" +
			")" +
		") " +
		"and to_char(late_date,'yyyy-mm')='"+date+"' " +
		"group by empid";
    Logger.getLogger(getClass()).debug("return sql string : " + sql);
    return sql;
  }

  public String getDeptViewSql_leave_Early(String deptid,String date,String key){
    String sql=
    	"select " +
    	"sum(early_time),empid " +
    	"from ar_emp_leave_early " +
    	"where empid in (" +
	    	"select empid " +
	    	"from hr_employee where (" +
		    	"date_left is null " +
		    	"or to_char(date_left,'yyyy-mm')>'"+date+"'" +
    		") " +
    		"and deptid in (" +
    			"select deptid " +
    			"from hr_department " +
    			"START WITH deptid='"+deptid+"' " +
    					"connect by  PRIOR deptid=parent_dept_id" +
    		") " +
    		"and (" +
    			"empid like '%"+key+"%' " +
				"or get_emp_name(empid) like '%"+key+"%'" +
			")" +
		") " +
		"and to_char(early_date,'yyyy-mm')='"+date+"' " +
		"group by empid";
    Logger.getLogger(getClass()).debug("return sql string : " + sql);
    return sql;
  }

  public String getDeptViewSql_leftYear(String deptid,String date,String key,String year){
    String sql=
    	"select " +
    	"LEFT_YEAR_DAYS,EMPID " +
    	"from ar_annual_list_v " +
    	"where empid in (" +
	    	"select empid " +
	    	"from hr_employee " +
	    	"where (" +
		    	"date_left is null " +
		    	"or to_char(date_left,'yyyy-mm')>'"+date+"'" +
	    	") " +
			"and deptid in (" +
    			"select deptid " +
    			"from hr_department " +
    			"START WITH deptid='"+deptid+"' " +
					"connect by  PRIOR deptid=parent_dept_id" +
			") " +
			"and (" +
				"empid like '%"+key+"%' " +
				"or get_emp_name(empid) like '%"+key+"%'" +
			")" +
		") " +
		"and HOLIDAY_YEAR='"+year+"'";
    Logger.getLogger(getClass()).debug("return sql string : " + sql);
    return sql;
  }




  public ArrayList getDeptView(String deptid,String date,String key,ArrayList code_id,String year) throws Exception{
    //先取出人员列表
    String sql = this.getDeptViewSql_emp(deptid,date,key);//构造SQL
    Logger.getLogger(getClass()).debug(sql);

    ArrayList empList=this.getRS_emp(sql);//取出应该显示的人员列表
    ArrayList rsLeave=new ArrayList();
    //循环取出所有LEAVE类型的数据
    for(int i=0;i<code_id.size();i++){
      //得出该类的SQL

      sql=this.getDeptViewSql_leaveType((String)code_id.get(i),deptid,date,key);
      rsLeave.add(this.getRs_Leave(sql));
    }
    //取出迟到
    sql=this.getDeptViewSql_late(deptid,date,key);
    ArrayList lateList=this.getRs_Late(sql);
    //取出早退
    sql=this.getDeptViewSql_leave_Early(deptid,date,key);
    ArrayList earlyList=this.getRs_LeaveEarly(sql);
    //取出年假
    sql=this.getDeptViewSql_leftYear(deptid,date,key,year);
    ArrayList yearList=this.getRs_leftYear(sql);
    //比对以后得出一条记录


    return getFull(empList,rsLeave,lateList,earlyList,yearList);
  }
  public ArrayList getRS_emp(String sql) throws Exception{
  	Logger.getLogger(getClass()).debug(sql);
    ArrayList empList=new ArrayList();

    db.getConnectionOracle();
    rs=db.getResultSet(sql);
    while(rs.next()){
      DeptRoll empInfo=new DeptRoll();
      empInfo.setEmpid(rs.getString("empid"));
      empInfo.setEmpname(rs.getString("chinesename"));
      empList.add(empInfo);
    }
    db.closeConnection();
    return empList;
  }

  public ArrayList getRs_Leave(String sql) throws Exception{
  	Logger.getLogger(getClass()).debug(sql);
    ArrayList leaveList=new ArrayList();
    db.getConnectionOracle();
    rs=db.getResultSet(sql);
    while(rs.next()){
      DeptRoll leaveInfo=new DeptRoll();
      leaveInfo.setLeaveTime(rs.getInt(1));
      leaveInfo.setEmpid(rs.getString("empid"));
      leaveList.add(leaveInfo);
    }
    db.closeConnection();
    return leaveList;
  }

  public ArrayList getRs_Late(String sql) throws Exception{
  	Logger.getLogger(getClass()).debug(sql);
    ArrayList lateList=new ArrayList();
    db.getConnectionOracle();
    rs=db.getResultSet(sql);
    while(rs.next()){
      DeptRoll lateInfo=new DeptRoll();
      lateInfo.setLateTime(rs.getInt(1));
      lateInfo.setEmpid(rs.getString("empid"));
      lateList.add(lateInfo);
    }
    db.closeConnection();
    return lateList;
  }

  public ArrayList getRs_LeaveEarly(String sql) throws Exception{
  	Logger.getLogger(getClass()).debug(sql);
    ArrayList earlyList=new ArrayList();
    db.getConnectionOracle();
    rs=db.getResultSet(sql);
    while(rs.next()){
      DeptRoll earlyInfo=new DeptRoll();
      earlyInfo.setLeaveEarlyTime(rs.getInt(1));
      earlyInfo.setEmpid(rs.getString("empid"));
      earlyList.add(earlyInfo);
    }
    db.closeConnection();
    return earlyList;
  }

  public ArrayList getRs_leftYear(String sql) throws Exception{
  	Logger.getLogger(getClass()).debug(sql);
    ArrayList yearList=new ArrayList();
    db.getConnectionOracle();
    rs=db.getResultSet(sql);
    while(rs.next()){
      DeptRoll yearInfo=new DeptRoll();
      yearInfo.setLeft_year_days(rs.getInt(1));
      yearInfo.setEmpid(rs.getString("empid"));
      yearList.add(yearInfo);
    }
    db.closeConnection();
    return yearList;
  }



  public ArrayList getFull(ArrayList empList,ArrayList rsLeave,ArrayList lateList,ArrayList earlyList,ArrayList yearList){
    DeptRoll empInfo=null;
    DeptRoll leaveInfo=null;
    DeptRoll lateInfo=null;
    DeptRoll earlyInfo=null;
    DeptRoll yearInfo=null;
    ArrayList fullList=new ArrayList();
    ArrayList tempLeave=new ArrayList();

    for(int i=0;i<empList.size();i++){//查找每一个人的记录
      empInfo=(DeptRoll)empList.get(i);//取到一个人的基本信息
      ArrayList infoList=new ArrayList();
        //根据他的基本信息 与其他类型信息比对
        infoList.add(empInfo.getEmpid());//设置EMP
        infoList.add(empInfo.getEmpname());//设置EMPID
        for(int j=0;j<rsLeave.size();j++){//获取每个类型
          tempLeave=(ArrayList)rsLeave.get(j);//得到一个类型 记录集合
          infoList.add("0");
          for(int h=0;h<tempLeave.size();h++){
            leaveInfo=(DeptRoll)tempLeave.get(h);//得到一个类型的基本信息

            if(leaveInfo.getEmpid().equals(empInfo.getEmpid())){//如果是同个人 肯定只进一次
              infoList.remove(infoList.size()-1);//删除前面预先添加项目

              infoList.add(Integer.toString(leaveInfo.getLeaveTime()));
            }
          }
        }
        infoList.add("0");

        for(int l=0;l<lateList.size();l++){
          lateInfo=(DeptRoll)lateList.get(l);//取到迟到的一条记录

          if(lateInfo.getEmpid().equals(empInfo.getEmpid())){//有迟到记录
            infoList.remove(infoList.size()-1);//删除前面预先添加项目

            infoList.add(Integer.toString(lateInfo.getLateTime()));
          }
        }
        infoList.add("0");

        for(int e=0;e<earlyList.size();e++){
          earlyInfo=(DeptRoll)earlyList.get(e);//取到早退的一条记录

          if(earlyInfo.getEmpid().equals(empInfo.getEmpid())){//有早退记录
            infoList.remove(infoList.size()-1);//删除前面预先添加项目

            infoList.add(Integer.toString(earlyInfo.getLeaveEarlyTime()));
          }
        }
        infoList.add("0");

        for(int y=0;y<yearList.size();y++){
          yearInfo=(DeptRoll)yearList.get(y);//取到一条年假记录

          if(yearInfo.getEmpid().equals(empInfo.getEmpid())){//有年假记录
            infoList.remove(infoList.size()-1);//删除前面预先添加项目

            infoList.add(Integer.toString(yearInfo.getLeft_year_days()));
          }
        }
        fullList.add(infoList);
    }

    return fullList;
  }

  public String getMonth(String month){
    String rm=null;
    int m=Integer.parseInt(month);
    if(m<10&&month.length()==1){
      rm="0"+month;
    }else{
      rm=month;
    }
    return rm;
  }
}
