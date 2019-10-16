package com.ait.ar.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.dao.DeptRollBean;
import com.ait.util.StringUtil;
import com.ait.web.Content;                       

public class DeptRollAdd implements Content{
  public String transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException,
      IOException{
    DeptRollBean dao=new DeptRollBean();
    String str=request.getParameter("addcount");
    String[] s=str.split("-");
    String empid="";
    String starttime="";
    String endtime="";
    String typecode="";
    String hours="";
    String reason="";
    String create=request.getParameter("admin");
    for(int i=0;i<s.length;i++){
      empid=request.getParameter("xz"+s[i]);
      starttime=request.getParameter("startdate"+s[i]);
      endtime=request.getParameter("enddate"+s[i]);
      typecode=request.getParameter("kqType"+s[i]);
      hours=request.getParameter("leavehour"+s[i]);
      reason=StringUtil.checkNull(request.getParameter("shiyou"+s[i]));
      reason=StringUtil.toCN(reason);
      ArrayList values=new ArrayList();
      values.add(empid);
      values.add(starttime);
      values.add(endtime);
      values.add(typecode);
      values.add(create);
      values.add(hours);
      values.add(reason);
      try {
        dao.DeptRollAddLeave(values);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return "/ar0101_view.jsp";
  }
}
