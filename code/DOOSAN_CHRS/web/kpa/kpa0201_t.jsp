<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*,java.lang.Integer" errorPage="" %>
<%@ page import="com.ait.kpa.KpaParamItem,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String add = messageSource1.getMessage("alert.pay.add");
	String edit = messageSource1.getMessage("alert.pay.edit");
	String delete = messageSource1.getMessage("alert.pay.delete");
	String initialization = messageSource1.getMessage("alert.pay.initialization");
	Func func = new Func();
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	try{
		if (flag.equals("insert")){
	KpaParamItem paparamitem = new KpaParamItem();
	paparamitem.setParam_id(func.isoStr(request.getParameter("param_id")));
	paparamitem.setParam_name(func.isoStr(request.getParameter("param_name")));
	paparamitem.setParam_en_name(func.isoStr(request.getParameter("param_en_name")));
	paparamitem.setParam_kor_name(func.isoStr(request.getParameter("param_kor_name")));
	paparamitem.setDistinct_field(func.isoStr(request.getParameter("distinct_field")));
	paparamitem.setDistinct_field_2nd(func.isoStr(request.getParameter("distinct_field_2nd")));
	paparamitem.setPa_month_str(func.isoStr(request.getParameter("pa_month_str")));
	paparamitem.setData_type(func.isoStr(request.getParameter("data_type")));
	paparamitem.setDescr(func.isoStr(request.getParameter("descr")));
	paparamitem.setDefault_val(func.isoStr(request.getParameter("default_val")));
	paparamitem.setGenerate_type_code(func.isoStr(request.getParameter("generate_type_code")));
	paparamitem.addPaParamItem();
	  //"添加已完成"
%>
			<script language="javascript">
			alert('<%=add%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&pamonth=<%=request.getParameter("pa_month_str")%>";
			</script>
		<%
			} else if (flag.equals("update")){
			KpaParamItem paparamitem = new KpaParamItem();
			paparamitem.setParam_no(Integer.parseInt(request.getParameter("param_no")));
			paparamitem.setParam_id(func.isoStr(request.getParameter("param_id")));
			paparamitem.setParam_name(func.isoStr(request.getParameter("param_name")));
			paparamitem.setParam_en_name(func.isoStr(request.getParameter("param_en_name")));
			paparamitem.setParam_kor_name(func.isoStr(request.getParameter("param_kor_name")));
			paparamitem.setDistinct_field(func.isoStr(request.getParameter("distinct_field")));
			paparamitem.setDistinct_field_2nd(func.isoStr(request.getParameter("distinct_field_2nd")));
			paparamitem.setPa_month_str(func.isoStr(request.getParameter("pa_month_str")));
			paparamitem.setData_type(func.isoStr(request.getParameter("data_type")));
			paparamitem.setDescr(func.isoStr(request.getParameter("descr")));
			paparamitem.setDefault_val(func.isoStr(request.getParameter("default_val")));
			paparamitem.setGenerate_type_code(func.isoStr(request.getParameter("generate_type_code")));
			paparamitem.modifyPaParamItem();
			  //"修改已完成"
		%>
			<script language="javascript">
			alert('<%=edit%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&pamonth=<%=request.getParameter("pa_month_str")%>";
			</script>
		<%
			} else if (flag.equals("del")) {
			String param_no = request.getParameter("param_no");
			KpaParamItem paparamitem = new KpaParamItem();
			paparamitem.removePaParamItem(param_no);
			  //"删除已完成"
		%>
			<script language="javascript">
			alert('<%=delete%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&pamonth=<%=request.getParameter("pamonth")%>";
			</script>
			
		<%
						} else if (flag.equals("inital")) {
						String pamonth = request.getParameter("pamonth");
						String statTypeCode = request.getParameter("statTypeCode") ;
						KpaParamItem paparamitem = new KpaParamItem();
						paparamitem.initalPaParamItem(pamonth,statTypeCode);
						//"参数初始化完成"
					%>
			<script language="javascript">
			alert('<%=initialization%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&pamonth=<%=request.getParameter("pamonth")%>";
			</script>
		<%}
	}catch (Exception ex) {
		System.out.println(ex);
	}%>