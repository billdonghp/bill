<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.utils.*,com.ait.util.*,java.lang.Integer"%>
<%@ page import="com.ait.pa.PaProgress"%>
<%	Func func = new Func();
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	String strPage = request.getParameter("strPage");
	String bigpage = request.getParameter("bigpage");
	try{
		if (flag.equals("update")){
			PaProgress progress = new PaProgress();
			String no = request.getParameter("no");
			String column = request.getParameter("column");
			String cflag = request.getParameter("cflag");
			String user = request.getParameter("user");
			progress.Update(no,column,cflag,user);
			%>
			<script lang="javascript">
			alert("修改已完成");
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
	}catch (Exception ex) {
	System.out.println(ex);
	}
%>