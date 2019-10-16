package com.ait.ar.content;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.bean.EmpRoll;
import com.ait.ar.dao.EmpRollBean;
import com.ait.web.Content;

public class EmpRollUpdateView implements Content{
  public String transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException,
      IOException{
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    String empid=request.getParameter("empid");

    EmpRollBean dao=new EmpRollBean();
    try {
      ArrayList datelist = dao.getDateStarEnd(year + dao.getMonth(month));
      String start=(String)datelist.get(0);
      String end=(String)datelist.get(1);
      ArrayList values=new ArrayList();
      values.add(empid);
      values.add(start);
      values.add(end);

      ArrayList emprollList=dao.getEmpRollList(values);

      EmpRoll info=null;

      int startDay=dao.getDate_day(start);
      int endDay=dao.getDate_day(end);


      ArrayList returnList=new ArrayList();

      for(int i=startDay;i<=endDay;i++){
        EmpRoll returnInfo=new EmpRoll();
        for(int j=0;j<emprollList.size();j++){
          info=(EmpRoll)emprollList.get(j);
          int tempDay=dao.getDate_day(info.getAr_date());
          if(i==tempDay){
            returnInfo.setAr_date(info.getAr_date());
            returnInfo.setEmpid(info.getEmpid());
            returnInfo.setLeave_type_code(info.getLeave_type_code());
            returnInfo.setLeave_time(info.getLeave_time());
            returnInfo.setLATE_TIME(info.getLATE_TIME());
            returnInfo.setEARLY_TIME(info.getEARLY_TIME());
            returnInfo.setTypeName(info.getTypeName());
          }
        }
        returnList.add(returnInfo);
      }



     // request.setAttribute("startDay",Integer.toString(startDay));
     // request.setAttribute("endDay",Integer.toString(endDay));


      request.setAttribute("year",year);
      request.setAttribute("month",dao.getMonth(month));
      request.setAttribute("emprollList",returnList);
      request.setAttribute("empid",empid);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return "/ar0102_m.jsp";
  }
}
