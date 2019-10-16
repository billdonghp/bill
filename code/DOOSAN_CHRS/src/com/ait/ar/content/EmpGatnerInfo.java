package com.ait.ar.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.dao.EmpRollBean;
import com.ait.ar.dao.GatnerInfoBean;
import com.ait.web.Content;

public class EmpGatnerInfo implements Content{
  public String transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException,
      IOException {
    EmpRollBean dao=new EmpRollBean();
    GatnerInfoBean daog=new GatnerInfoBean();
    String deptid=request.getParameter("deptid");
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    String date=year +"-"+ dao.getMonth(month);
    String key=request.getParameter("key");
    ArrayList code_id=(ArrayList)request.getAttribute("code_id");
    ArrayList datelist = null;
    ArrayList daycount=new ArrayList();
    try {
      datelist = dao.getDateStarEnd(year + dao.getMonth(month));
      String start=(String)datelist.get(0);
      String end=(String)datelist.get(1);
      int startDay=dao.getDate_day(start);
      int endDay=dao.getDate_day(end);
      for(int i=startDay;i<=endDay;i++){
        daycount.add(month+"/"+i);
      }
      request.setAttribute("daycount",daycount);
      ArrayList fullProe=daog.getGatnerFullInfo(deptid,date,key,year,daycount,code_id);
      request.setAttribute("fullProe",fullProe);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return "/ar0205_excel.jsp";
  }
}
