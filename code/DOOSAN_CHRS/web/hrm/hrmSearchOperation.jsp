<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,java.util.*,
			com.ait.hrm.bean.*,
			com.ait.hrm.dao.common.*,
			com.ait.util.StringUtil"  errorPage=""%>
			<jsp:useBean id="type_list" class="java.util.ArrayList" />
<jsp:useBean id="table_list" class="java.util.ArrayList" />
<jsp:useBean id="field_list" class="java.util.ArrayList" />  
<%
String str="";
String typeId = StringUtil.checkNull(request.getParameter("typeId"));
String tableId = StringUtil.checkNull(request.getParameter("tableId"));
String[] fieldId = request.getParameterValues("fieldId");
String[] fieldName = request.getParameterValues("fieldName");


InfoTypeDAO typeDAO = new InfoTypeDAO();
type_list = (ArrayList)typeDAO.getAllInfoType();
if (!typeId.equals("")) {
	InfoTableDAO tableDAO = new InfoTableDAO();
	table_list = (ArrayList)tableDAO.getTableListByType(typeId);
}
if (!tableId.equals("")) {              
	InfoFieldDAO fieldDAO = new InfoFieldDAO();
	field_list = (ArrayList)fieldDAO.getFieldListByTable(tableId);  
}
%>  