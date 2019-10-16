package com.ait.ar.dao;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.EmpRoll;
import com.ait.util.StringUtil;
public class EmpRollBean {
  private DBConnection db=new DBConnection();
  private ResultSet rs=null;

  public ArrayList getDateStarEnd(String arMonth) throws Exception{
    return db.getDateFromTo(arMonth);
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

  /**
   * getEmpRollList
   *
   * @return ArrayList
   */
  public ArrayList getEmpRollList(ArrayList values) throws Exception {
    ArrayList list=new ArrayList();
    String sql=
				    	"select * " +
				    	"from ar_full_v " +
				    	"where empid=? " +
				    	"and leave_date between to_date(?,'yyyy-mm-dd') " +
				    	"and to_date(?,'yyyy-mm-dd') " +
				    	"order by leave_date asc ";
    Logger.getLogger(getClass()).debug(sql);
    db.getConnectionOracle();
    rs=db.getResultSet(sql,values);
    while(rs.next()){
      EmpRoll info=new EmpRoll();
      info.setEmpid(rs.getString("empid"));
      info.setAr_date(checkTime(rs.getString("LEAVE_DATE")));
      info.setLeave_type_code(rs.getString("leave_type_code"));
      info.setLeave_time(rs.getString("leave_time"));
      info.setLATE_TIME(rs.getString("LATE_TIME"));
      info.setEARLY_TIME(rs.getString("EARLY_TIME"));
      info.setTypeName(rs.getString("typename"));
      info.setLEAVE_REASON(StringUtil.checkNull(rs.getString("LEAVE_REASON")));
      list.add(info);
    }
    db.closeConnection();
    return list;
  }

  public String checkTime (String str){

    return str.substring(0,str.indexOf(" "));
  }
  public int getDate_day(String str){
    return Integer.parseInt(str.substring(str.lastIndexOf("-")+1));
  }

  public void EmpRollAdd(String empid,String date,String leaveType,String leaveTime,String lateTime,String earlyTime)throws Exception{
    String sql="call ar_update_leave('"+empid+"',to_date('"+date+"','yyyy-mm-dd'),'"+leaveType+"',"+leaveTime+","+lateTime+","+earlyTime+")";
    Logger.getLogger(getClass()).debug(sql);
    if(leaveType==null){
      sql="call ar_update_leave('"+empid+"',to_date('"+date+"','yyyy-mm-dd'),"+leaveType+","+leaveTime+","+lateTime+","+earlyTime+")";
    }
    Logger.getLogger(getClass()).debug(sql);
    db.getConnectionOracle();
    db.update(sql);
    db.closeConnection();
  }
}
