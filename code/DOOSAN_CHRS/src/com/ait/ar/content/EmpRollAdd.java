package com.ait.ar.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.dao.EmpRollBean;
import com.ait.web.Content;

public class EmpRollAdd implements Content{
  public String transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException,
      IOException{
    String str=request.getParameter("str");
    String[] s=str.split("-");
    String empid=request.getParameter("empid");
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    String date=null;
    String leaveType=null;
    String leaveTime=null;
    String lateTime=null;
    String early_time=null;
    EmpRollBean dao=new EmpRollBean();
    for(int i=0;i<s.length;i++){
      date=year+"-"+month+"-"+s[i];
      leaveType=request.getParameter("leave_type"+s[i]);
      leaveTime=request.getParameter("leave_time"+s[i]);
      lateTime=request.getParameter("late_time"+s[i]);
      early_time=request.getParameter("early_time"+s[i]);
      if(leaveType.length()==0){
        leaveType=null;
      }
      if(leaveTime.length()==0){
        leaveTime=null;
      }
      if(lateTime.length()==0){
        lateTime="0";
      }
      if(early_time.length()==0){
        early_time="0";
      }
      if(Integer.parseInt(lateTime)==0){
        lateTime=null;
      }
      if(Integer.parseInt(early_time)==0){
        early_time=null;
      }
      try{


        dao.EmpRollAdd(empid, date, leaveType, leaveTime, lateTime, early_time);
      }catch(Exception ex){

      }
    }
    return "/ar0102_view.jsp";
  }
}
