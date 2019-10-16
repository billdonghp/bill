<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.utils.*,com.ait.util.*"%>
<%@ page import="com.ait.kpa.Kpaitem,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%@ include file="../inc/taglibs.jsp"%>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String stredit = messageSource1.getMessage("alert.pay.sequence_edited");
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	try{
		if (flag.equals("up")||flag.equals("down")){
			String item_no = request.getParameter("item_no");
			Kpaitem paitem = new Kpaitem();
			paitem.ChangOrder(flag,item_no);
			//计算顺序已修改
			%>
			<script lang="javascript">
			
			alert('<%=stredit%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>";
			</script>
			<%
		}
	}catch (Exception ex) {
	System.out.println(ex);
	}
%>