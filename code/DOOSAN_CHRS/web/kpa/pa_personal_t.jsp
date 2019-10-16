<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.utils.*,com.ait.util.*,java.lang.Integer"%>
<%@ page import="com.ait.pa.PaParam,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String edit = messageSource1.getMessage("alert.pay.edit");
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	String pamonth = request.getParameter("pamonth");
	String deptid = request.getParameter("deptid");
	try{
		if (flag.equals("modify")){
			String [] param_data_no = request.getParameterValues("param_data_no");
			String [] param_no = request.getParameterValues("param_no");
			String [] return_value = request.getParameterValues("return_value");
			String empID = request.getParameter("empID");
			String chineseName = StringUtil.toCN(request.getParameter("chineseName"));
			String pinyinName = request.getParameter("pinyinName");
			String korName = request.getParameter("korName");
			PaParam paParam = new PaParam();
			paParam.modifyPaParam(param_data_no,param_no,return_value,empID,chineseName,pinyinName,korName);
			//"修改已完成"
			%>
			<script lang="javascript">
			alert('<%=edit%>');
			//location.href="pa_personal_v.jsp?menu_code=<%=menu_code%>&pamonth=<%=pamonth%>&empID=<%=empID%>&deptid=<%=deptid%>";
			//location.href="/hrmControlServlet?operation=destinationCmd&menu_code=<%=menu_code%>&destination=/pa_personal&pamonth=<%=pamonth%>&empID=<%=empID%>&deptid=<%=deptid%>";
			location.href="pa0201.jsp?menu_code=<%=menu_code%>&pamonth=<%=pamonth%>&empID=<%=empID%>&deptid=<%=deptid%>";
			</script>
			<%
		}
	}catch (Exception ex) {
	System.out.println(ex);
	}
%>