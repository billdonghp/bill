<%@page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsCommonItem"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<title>项目赋予</title>
</head>
<%
String evPeriodId="";
String evTypeId="";
String evDeptId="";
String evItemId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsCommonItem evCommonItems=null;
EvsItem evItem=null;
EvsEmp evEmp=null;
List lEvItem=null;
List lEvEmp=null;
List lEvsDept=null;
int lEvEmpSize=0;
if(!evTypeId.equals("")&&!evPeriodId.equals("")){
	evCommonItems=new EvsCommonItem(evPeriodId,evTypeId);
	evItem=new EvsItem(evPeriodId,evTypeId);
	evEmp=new EvsEmp();
	try{
		lEvsDept=EvsEmp.getEvEmpDeptList();
		lEvItem=evItem.getItemByTypePeriod();
		lEvEmp=evEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId);
	}catch(Exception e){}
}
%>
<body bgcolor="#ffffff">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<form name="evscommon" action="evs0106_t.jsp">
<input type="hidden"
	name="evPeriodId" value="<%=evPeriodId%>"> <input type="hidden"
	name="evTypeId" value="<%=evTypeId%>">
	<input type="hidden"
	name="evItemId" value="<%=evItemId%>">  
<input type="hidden"
	name="flag" value="endow">

<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
				<td colspan="4">
				<select name="evDeptId"
					onChange="location.href='/evs/evscommon.jsp?evPeriodId=<%=evPeriodId%>&evTypeId=<%=evTypeId%>&evItemId=<%=evItemId%>&evDeptId='+this.value;">
					<option value="">评价部门</option>
					<%		if(lEvsDept!=null){
							int lEvsDeptSize=lEvsDept.size();
							Hashtable dept_h=new Hashtable();
	                        for ( int i = 0 ; i < lEvsDeptSize; i++ )
	                        {
	                          dept_h = (Hashtable) lEvsDept.get(i);
                          %>
					<option value="<%=(String)dept_h.get("deptId")%>"
						<%if (((String)dept_h.get("deptId")).equals(evDeptId)){%> selected
						<%}%>><%
                            int level=Integer.parseInt((String)dept_h.get("deptLevel"));
                            String deptname = "";
                            for(int j=0;j<level;j++){
                              deptname +="　";
                            }
                            out.print(deptname+(String)dept_h.get("deptName"));

                            %></option>
					<%}}%>
				</select></td>
			</tr>
			<tr align="right">
				<td colspan="4">
				<div align="justify">全选&nbsp;&nbsp;<input type="checkbox"
					name="checkall" value="all" onClick="selectall()"></div>
				</td>
			</tr>
	<tr id="emplist">
		<td colspan="2">
		<%if(lEvEmp!=null){%>
		<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-103) + ';\">')
//-->
</script>
		<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" >
			
			
			<tr align="center">
				<td width="10%" height="30" bgcolor="#F5F5F5">选择</td>
				<td width="15%" height="30" bgcolor="#F5F5F5">中文姓名</td>
				<td width="15%" bgcolor="#F5F5F5">工号</td>
				<td width="30%" bgcolor="#F5F5F5">部门</td>
			</tr>
			<%
        // show all employees
        lEvEmpSize=lEvEmp.size();
        for(int i=0; i<lEvEmpSize;i++){
          EvsEmp e = (EvsEmp) lEvEmp.get(i);
          %>
			<tr align="center">
				<td><input type="checkbox" name="empid"
					value="<%=e.getEvEmpID()%>"></td>
				<td height="30" class="info_content_01"><%=StringUtil.toUnicode(StringUtil.toISO(StringUtil.checkNull(e.getEvEmpName())),"UTF-8")%></td>
				<td class="info_content_01"><%=e.getEvEmpID()%>&nbsp;</td>
				<td class="info_content_01"><%=e.getEvDeptName()%>&nbsp;</td>

			</tr>
			<%}%>
			
		</table>
		<%}%>
		</div>
		</td>
	</tr>
	<tr align="center">
		<!--<td >
		<input name="type" type="radio" value="all" onclick="radioChange('all')"
			checked="checked">全体赋予 
			<input name="type" type="radio"
			value="employee" onclick="radioChange('employee')">部分赋予
		</td>
		--><td>
		<input type="submit" value="保存" />&nbsp;&nbsp; <input
			type="reset" value="取消" /></td>
	</tr>
</table>
</form>
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">

function selectall()
{
  try{
	  var length=<%=lEvEmpSize%>;
	  document.evscommon.checkall.checked = document.evscommon.checkall.checked|0;

	  if ( length== 0 ){
	    return;
	  }
	  if (length ==1 )
	  {
	    document.evscommon.empid.checked=document.evscommon.checkall.checked ;
	  }
	
	  if (length>1)
	  {
	  	for (var i = 0; i < length; i++)
	    {
	      document.all('empid')[i].checked=document.evscommon.checkall.checked;
	    }
	  }
	}catch(e){alert(e);}
}
function radioChange(va){
	//if(va=="all"){
	//	document.all.emplist.style.visibility='hidden';
	//}else{
	//	document.all.emplist.style.visibility='visible';
	//}
}
</script>
