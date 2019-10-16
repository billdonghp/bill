<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page import="com.ait.hrm.business.*"%>
<%@ page import="com.ait.hrm.bean.BasicInfo"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%
String deptid="";
deptid=(request.getParameter("deptid")!=null)?request.getParameter("deptid"):"";

List empList=new Vector();
try{
	//if(!"".equals(deptid)){
		SimpleMap map=new SimpleMap();
		map.set("DEPTID",deptid);
		map.set("ADMINID",admin.getAdminID());
		map.set("cpnyId",admin.getCpnyId());
		empList = HrmServices.getInstance().retrieveEmpList(map);
	//}
}catch(Exception e){}
%>
<form name="evsMasterListForm" method="POST">
 <table width="100%" >
   <tr>
	<td width="60%" >
		<select name="evsMasterList" size="<%=empList.size()+2%>" style="width:110pt">
			<option value="">选择评价者</option>
			<option value="self">被评价者本人</option>
			<%
			for(int n=0;n<empList.size();n++){
			  BasicInfo h=(BasicInfo)empList.get(n);
			  
			  %>
			<option value="<%=h.getPersonId()%>" ><%=h.getEmpID()%>--<%=h.getChineseName()%></option>
			<%
			}
			%>
		</select>
	</td>
   	</tr>
</table>
</form>


