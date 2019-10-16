<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.lang.Integer" errorPage="" %>
<%@ page import="com.ait.kpa.PaParam,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String add = messageSource1.getMessage("alert.pay.add");
	String edit = messageSource1.getMessage("alert.pay.edit");
	String delete = messageSource1.getMessage("alert.pay.delete");
	String flag = request.getParameter("flag");
	try{
		if (flag.equals("insert")){
			String [] field1 = request.getParameterValues("field1");
			String [] fielden1 = request.getParameterValues("field1_en");
			String [] fieldkor1 = request.getParameterValues("field1_kor");
			String [] field2 = request.getParameterValues("field2");
			String [] fielden2 = request.getParameterValues("field2_en");
			String [] fieldkor2 = request.getParameterValues("field2_kor");
			String [] return_value = request.getParameterValues("return_value");
			String [] startMonth = request.getParameterValues("startMonth");
			String [] endMonth = request.getParameterValues("endMonth");
			String [] sdEdValue = request.getParameterValues("sdEdValue");
			int param_no = Integer.parseInt(request.getParameter("param_no"));
			PaParam paParam = new PaParam();
			paParam.addPaParam(param_no,field1,fielden1,fieldkor1,field2,fielden2,fieldkor2,return_value,startMonth,endMonth,sdEdValue);%>
			<script language="javascript">
			alert('<%=add%>');
			location.href="param_data.jsp?param_no=<%=param_no%>";
			</script>
		<%} else if (flag.equals("modify")){
			String [] param_data_no = request.getParameterValues("param_data_no");
			String [] return_value = request.getParameterValues("return_value");
			String [] startMonth = request.getParameterValues("startMonth");
			String [] endMonth = request.getParameterValues("endMonth");
			String [] sdEdValue = request.getParameterValues("sdEdValue");
			int param_no = Integer.parseInt(request.getParameter("param_no"));
			PaParam paParam = new PaParam();
			paParam.modifyPaParam(param_data_no,return_value,startMonth,endMonth,sdEdValue);%>
			<script language="javascript">
			alert('<%=edit%>');
			location.href="param_data.jsp?param_no=<%=param_no%>";
			</script>
		<%} else if (flag.equals("del")) {
			int param_no = Integer.parseInt(request.getParameter("param_no"));
			int param_data_no = Integer.parseInt(request.getParameter("param_data_no"));
			PaParam paParam = new PaParam();
			paParam.removePaParam(param_data_no);%>
			<script language="javascript">
			alert('<%=delete%>');
			location.href="param_data.jsp?param_no=<%=param_no%>";
			</script>
		<%} else if (flag.equals("delall")) {
			String param_no = request.getParameter("param_no");
			PaParam paParam = new PaParam();
			paParam.removePaParam(param_no);%>
			<script language="javascript">
			alert('<%=delete%>');
			location.href="param_data.jsp?param_no=<%=param_no%>";
			</script>
		<%}
	}catch (Exception ex) {
		System.out.println(ex);
	}
%>