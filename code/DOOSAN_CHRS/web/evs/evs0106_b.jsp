<%@ page contentType="text/html; charset=UTF-8" errorPage="../error/ev_error.jsp"%>
<%@ page import="com.ait.ev.processing.*,java.util.*,com.ait.util.*" %>
<%
String itemcode = request.getParameter("Ev_Item_Code");
String seasonid = request.getParameter("season");
String asstype = request.getParameter("asstyp");
String deptid = "0";
String empid = "0";

if ("department".equals(asstype)){
  deptid = request.getParameter("dept");
}

if ("employee".equals(asstype)){
  empid = request.getParameter("emp");
}

if ("all".equals(asstype)){
  deptid = "0";
  empid = "0";
}

EvItems.assignCommonItem(itemcode, seasonid, deptid, empid);
response.sendRedirect("ev0105.jsp?menu_code=ev0105");
%>
