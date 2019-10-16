<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.utils.*,com.ait.util.*,java.lang.Integer"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="com.ait.pa.Paformular,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String add = messageSource1.getMessage("alert.pay.add");
	String edit = messageSource1.getMessage("alert.pay.edit");
	String delete = messageSource1.getMessage("alert.pay.delete");
	Func func = new Func();
	String flag = request.getParameter("flag");
	int pa_item_no = new Integer(request.getParameter("pa_item_no")).intValue();
	try{
		if (flag.equals("modify")){
			int formular_no = new Integer(request.getParameter("formular_no")).intValue();
			Paformular paformular = new Paformular();
			  paformular.setpa_item_no(pa_item_no);
			  paformular.setCondition_cn(func.isoStr(request.getParameter("condition_cn")));
			  paformular.setFormular_cn(func.isoStr(request.getParameter("formular_cn")));
			  paformular.setformular_no(formular_no);
			  paformular.Update();
			  //"修改已完成"
			%>
			<script lang="javascript">
			alert('<%=edit%>');
			location.href="formular_data.jsp?pa_item_no=<%=pa_item_no%>";
			</script>
			<%
		}
		if (flag.equals("insert")){
			Paformular paformular = new Paformular();
			  paformular.setpa_item_no(pa_item_no);
			  paformular.setCondition_cn(func.isoStr(request.getParameter("condition_cn")));
			  paformular.setFormular_cn(func.isoStr(request.getParameter("formular_cn")));
			  paformular.Insert();
			  //"添加已完成"
			%>
			<script lang="javascript">
			alert('<%=add%>');
			location.href="formular_data.jsp?pa_item_no=<%=pa_item_no%>";
			</script>
			<%
		}

		if (flag.equals("del")) {
			int formular_no = new Integer(request.getParameter("formular_no")).intValue();
			Paformular paformular = new Paformular();
			paformular.Delete(formular_no);
			  //"删除已完成"
			%>
			<script lang="javascript">
			alert('<%=delete%>');
			location.href="formular_data.jsp?pa_item_no=<%=pa_item_no%>";
			</script>
			<%
		}
	}catch (Exception ex) {
	System.out.println(ex);
	}
%>