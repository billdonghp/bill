<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.util.*,java.lang.Integer"%>
<%@ page import="com.ait.pa.PaExp,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String add = messageSource1.getMessage("alert.pay.add");
	String edit = messageSource1.getMessage("alert.pay.edit");
	String delete = messageSource1.getMessage("alert.pay.delete");
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	String strPage = request.getParameter("strPage");
	String bigpage = request.getParameter("bigpage");
	try{
		if (flag.equals("insert")){
			PaExp paExp = new PaExp();
			paExp.setExpense_type(Integer.parseInt(request.getParameter("expense_type")));
			paExp.setExpense_formular(StringUtil.toCN(request.getParameter("expense_formular")));
			paExp.setTag(Integer.parseInt(request.getParameter("tag")));
			paExp.setDebitcredit(StringUtil.toCN(request.getParameter("debitcredit")));
			paExp.setDescr(StringUtil.toCN(request.getParameter("descr")));
			paExp.Insert();
			  //"添加已完成"

			%>
			<script lang="javascript">
			alert('<%=add%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
		if (flag.equals("update")){
			PaExp paExp = new PaExp();
			paExp.setExpense_type(Integer.parseInt(request.getParameter("expense_type")));
			paExp.setExpense_formular(StringUtil.toCN(request.getParameter("expense_formular")));
			paExp.setTag(Integer.parseInt(request.getParameter("tag")));
			paExp.setDebitcredit(StringUtil.toCN(request.getParameter("debitcredit")));
			paExp.setDescr(StringUtil.toCN(request.getParameter("descr")));
			paExp.setPa_expense_seq(Integer.parseInt(request.getParameter("no")));
			paExp.Update();//"修改已完成"
			%>
			<script lang="javascript">
			alert('<%=edit%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
		if (flag.equals("del")) {
			PaExp paExp = new PaExp();
			paExp.Delete(request.getParameter("no")); //"删除已完成"
			%>
			<script lang="javascript">
			alert('<%=delete%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
	}catch (Exception ex) {
	out.println(ex);
	}
%>