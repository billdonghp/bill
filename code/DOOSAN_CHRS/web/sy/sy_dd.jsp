<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.Sydept"%>
<%@ page import="com.ait.hr.bean.Department"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ page import="com.ait.hr.bean.*,com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.SydeptBean">
</jsp:useBean>
<jsp:useBean id="hr" scope="page" class="com.ait.hr.business.HrServices">
</jsp:useBean>

<%
String deptid=request.getParameter("deptid");
ArrayList list=dao.getEmpids(deptid);
Sydept info=null;


HttpSession session1 = request.getSession(false);
AdminBean admin = (AdminBean) session1.getAttribute("admin");
String adminID  = admin.getAdminID();
List dept_list = hr.getGrantDept(adminID);
Department de=null;
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function selectAll()
{

		for(var i=0;i<document.form1.elements.length;i++)
		{
			if(form1.elements[i].type=="checkbox")
			{
				//if(document.form1.Submit.value=="全选"){
				form1.elements[i].checked=true;
				//document.form1.Submit.value="反选";
				//}else{
				//form1.elements[i].checked=false;
				//document.form1.Submit.value="全选";
				//}
			}
		}
}
function Save()
{
	var str="";
	for(var i=0;i<document.form1.elements.length;i++)
	{
		if(document.form1.elements[i].type=="checkbox")
		{
			if(document.form1.elements[i].checked==true)
			{
				str+=document.form1.elements[i].value+"-";
			}
		}
	}
	//alert(str);
	var mubiao=document.form1.mubiao.value;
      document.form1.action="/ar/arControlServlet?content=sysdeptdd&menu_code=<%=request.getParameter("menu_code")%>&str="+str+"&mubiao="+mubiao;
      document.form1.submit();
}
</script>
</head>

<body>

<form name="form1" method="post" action="">
<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<tr align="center">
		  <td width="6%" bgcolor="#F5F5F5">选择</td>
			<td width="21%" height="30" bgcolor="#F5F5F5">姓名</td>
			<td width="19%" bgcolor="#F5F5F5">工号</td>
			<td width="54%" bgcolor="#F5F5F5">部门</td>
		</tr>

                <%for(int i=0;i<list.size();i++){
                  info=(Sydept)list.get(i);
                %>
                <tr align="center">
		  <td ><input type="checkbox" name="<%=i%>" value="<%=info.getEmpid()%>"/></td>
                  <td ><%=info.getEmpname()%></td>
                  <td ><%=info.getEmpid()%></td>
                  <td ><%=info.getDeptname()%></td>
		</tr>
                <%}%>

</table>
<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td><input type="button" name="Submit" value="全选" onClick="selectAll()">
      移动到
      <select name="mubiao">
        <%

					for ( int i = 0 ; i < dept_list.size() ; i++ )
					{
						de = (Department) dept_list.get(i);
						int deptLevel = de.getDeptLevel();
						String deptname= de.getDeptName();
						for(int k=0;k<deptLevel;k++){
							deptname = "&nbsp;&nbsp;" + deptname;
						}
						if(deptLevel ==1){
			%>
        <option>&nbsp;</option>
        <%}%>
        <option value="<%=de.getDeptID()%>" <%if(deptLevel==1){%>style="color:#FF0000"<%}%> ><%=deptname%></option>
        <%		}%>
      </select></td>
    </tr>
  <tr>
    <td align="center"><img src="../images/btn/p_setup.gif" style="cursor:pointer " onClick="Save()"></td>
    </tr>
</table>
</form>
</body>
</html>
