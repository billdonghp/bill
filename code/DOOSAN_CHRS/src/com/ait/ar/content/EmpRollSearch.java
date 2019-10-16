package com.ait.ar.content;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.dao.DeptRollBean;
import com.ait.util.StringUtil;
import com.ait.web.Content;

public class EmpRollSearch implements Content{
  public String transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException,
      IOException{

    String deptid=request.getParameter("deptid");
    String year=request.getParameter("year");
    String month=request.getParameter("month");
    String key=request.getParameter("key");
    key=StringUtil.toCN(key);
    ArrayList values=new ArrayList();
    values.add(year+"-"+month);
    values.add(deptid);
    values.add("%"+key+"%");
    values.add("%"+key+"%");
    DeptRollBean dao=new DeptRollBean();
    try {
      ArrayList deptroll_list=dao.getDeptRollEmps(values);
      request.setAttribute("deptroll_list",deptroll_list);
    }
    catch (Exception ex) {
    }
    return "/ar0102_view.jsp";
  }

}
