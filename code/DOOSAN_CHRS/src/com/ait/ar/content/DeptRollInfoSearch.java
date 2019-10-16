package com.ait.ar.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.dao.DeptRollBean;
import com.ait.util.StringUtil;
import com.ait.web.Content;
public class DeptRollInfoSearch implements Content{
  public String transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException,
      IOException{
    DeptRollBean dao=new DeptRollBean();
    String deptid=request.getParameter("deptid");
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    String key=request.getParameter("key");
    String date=year+"-"+dao.getMonth(month);
    key=StringUtil.toCN(key);

    try {

      ArrayList list=dao.getLeaveType();
      ArrayList code_id=(ArrayList)list.get(0);
      ArrayList code_name=(ArrayList)list.get(1);
      request.setAttribute("code_name",code_name);
      request.setAttribute("code_id",code_id);

      ArrayList fullList=dao.getDeptView(deptid,date,key,code_id,year);
      request.setAttribute("fullList",fullList);

    }
    catch (Exception ex) {
    }
    return "/ar0201_view.jsp";


  }
}
