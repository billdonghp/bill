<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.ait.utils.*"%>

    <SCRIPT language=JavaScript>
var ID;
ID='';
var MENU_CODE;
MENU_CODE='';
</SCRIPT>
<SCRIPT language=JavaScript 
      src="../js/table150.js"></SCRIPT>
<%
String menu_code = "";
menu_code = request.getParameter("menu_code");
Func func= new Func();
AdminBean admin = new AdminBean();
%>
