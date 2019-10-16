<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage=""%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<html>
<head>
<%
AdminBean admin = new AdminBean();
if (session.getAttribute("admin") == null) {
	response.sendRedirect("/expired.jsp");
}
admin = (AdminBean) session.getAttribute("admin");
String rodeLevel = "";
String pd = "";
rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";
MenuBiz menubiz = new MenuBiz();
MenuEnt menuent = new MenuEnt();
Vector vlist2 = new Vector();
vlist2 = menubiz.MenuList(rodeLevel);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../js/slide_menu.js"></script>
</head>
<body>