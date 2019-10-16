<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.utils.*,com.ait.util.*,java.lang.Integer"%>
<%@ page import="com.ait.pa.PaReport"%>
<%	
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	String adminid = request.getParameter("adminid");
	try{
		if (flag.equals("update")){
			PaReport paReport = new PaReport();
		    String result = paReport.PaConfirm(adminid);
			%>
			<script lang="javascript">
			alert("<%=result%>");
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>";
			</script>
			<%
		}
	}catch (Exception ex) {
		ex.printStackTrace();
	}
%>