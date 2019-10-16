<%@page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.Constants"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsOtherItem"%>
<%@ page import="com.ait.evs.EvsOtherColumn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<title>evcommon</title>
</head>
<%
String evPeriodId="";
String evTypeId="";
String evItemId="";
String evEmpId="";
String evProcessId="";
String evDeptId="";
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;

evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evEmpId=request.getParameter("evEmpId")!=null?request.getParameter("evEmpId"):evEmpId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;



int lEvOtherColumnsSize=0;
if(evEmpId.equals("")){
	if (admin != null){
			evEmpId = admin.getAdminID();
	}
}


if(!evPeriodId.equals("")){
	try{
		EvsEmp evsEmp = new EvsEmp(evEmpId,evPeriodId);
		evsEmp.getEvEmpByEmpIdPeriod();
  		if (evsEmp!=null) {
  			if(evTypeId.equals("")){
  				evTypeId=evsEmp.getEvTypeID();
  			}	
  			evProcessId=evsEmp.getEvCurrentProcessID();
  			evDeptId=evsEmp.getEvDeptID();
  		}
	}catch(Exception e){}
}
EvsOtherItem evOtherItems=null;
EvsItem evItem=null;
List lEvItem=null;
List lEvOtherColumns=null;

List lEvEmps=null;
EvsMaster evMaster=new EvsMaster();
if(!evTypeId.equals("")&&!evPeriodId.equals("")&&!evEmpId.equals("")){
	evOtherItems=new EvsOtherItem(evPeriodId,evTypeId,evEmpId);
	try{
		lEvItem=evOtherItems.getItemListByTypePeriod(evProcessId);
		lEvOtherColumns=evOtherItems.getEvOtherColumns(evItemId);
		
		lEvOtherColumnsSize=lEvOtherColumns.size();
		lEvEmps=evMaster.getEvEmpsByMasterPeriod(evPeriodId,evEmpId,evDeptId,"");
	}catch(Exception e){}
}
%>
<body bgcolor="#ffffff">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<form name="evs0208" action="evs0208_t.jsp" >
<input type="hidden"
	name="evPeriodId" value="<%=evPeriodId%>"> 
	<input type="hidden"
	name="evTypeId" value="<%=evTypeId%>">
		<input type="hidden"
	name="evEmpId" value="<%=evEmpId%>"> 
	<input type="hidden" name="flag"
	value="add" >
<table width="98%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	
	<tr>
	<td colspan="<%=lEvOtherColumnsSize+2%>">
		<select name="evItemId" onchange="location.href='/evs/evs0208_a.jsp?menu_code=evs0208&evPeriodId=<%=evPeriodId%>&evTypeId=<%=evTypeId%>&evItemId='+this.value">
			<option value=''>评价项目</option>
		      <%
      if(lEvItem!=null){
        int lEvItemSize=lEvItem.size();
      	for(int i=0;i<lEvItemSize;i++){
      		HashMap hItem=(HashMap)lEvItem.get(i);

      %>
      	<option value=<%=hItem.get("itemId").toString()%> <%if(hItem.get("itemId").toString().equals(evItemId)){out.println(" selected ");}%>><%=hItem.get("itemName")%></option>
      <%}}%>
		</select>
		</td>
	</tr>
	
	<%
	if(evItemId.equals(Constants.EVITEM005)){
		
		out.println("<tr align='center' bgcolor='#F5F5F5'><td>选择</td>");
		for(int i=0;i<lEvOtherColumnsSize;i++){
			EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumns.get(i);
		%>
			
			<td><%=evOtherColumn.getEvColumnName()%></td>	
		<%
		}
		out.println("</tr>");
		if(lEvEmps!=null){
			int lEvEmpSize=lEvEmps.size();
			for(int i=0;i<lEvEmpSize;i++){
				EvsEmp emp=(EvsEmp)lEvEmps.get(i);
					out.println("<tr align='center'><td><input type='checkbox' name='check' value="+i+" checked></td>");
				
					for(int j=0;j<lEvOtherColumnsSize;j++){
						EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumns.get(j);
					%>
						<td>
						<%
						if(evOtherColumn.getEvColumnId().equals(Constants.EVCOLUMN011)){
							out.println("<input type='text' value='"+emp.getEvEmpID()+"' name="+evOtherColumn.getEvColumnId()+"_detail_"+i+" readonly size='10'/>");	
						}else if(evOtherColumn.getEvColumnId().equals(Constants.EVCOLUMN012)){
							out.println("<input type='text' value='"+emp.getEvEmpName()+"' name="+evOtherColumn.getEvColumnId()+"_detail_"+i+" readonly size='10'/>");	
						}else{
							out.println("<input type='text' value='' name="+evOtherColumn.getEvColumnId()+"_detail_"+i+" />");	
						}%>
						</td>	
					<%
					}
				out.println("</tr>");
			}
		}
		
	}else{
	%>
	<tr align="center" bgcolor="#F5F5F5">
		<td >项目列</td>
		<td>项目列内容</td>
	</tr>
	<!--<tr align="center">
		<td >比重</td>
		<td><input type="text" value="" name="evItemDetailProp"/></td>
	</tr>
	-->
	<%
	if(lEvOtherColumns!=null){
		
		for(int i=0;i<lEvOtherColumnsSize;i++){
			EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumns.get(i);
	%>
			<tr align="center">
			<td><%=evOtherColumn.getEvColumnName()%></td>	
			<td>
			<textarea cols="80" rows="3" name="<%=evOtherColumn.getEvColumnId()+"_detail"%>"></textarea>
			<!--<input type="text" value="" name="<%//=evOtherColumn.getEvColumnId()+"_detail"%>" />
			--></td>
	</tr>
	<%
		}
	}
	}
	%>
	<tr align="center">
		<td colspan="<%=lEvOtherColumnsSize+2%>">
		<input type="submit" value="保存" />&nbsp;&nbsp; 
		<input type="reset" value="取消" />&nbsp;&nbsp; 
		<input type="button" value="返回" onclick="history.back();"/>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">

function checkNum(){
	var prop=document.evs0208.evItemDetailProp.value;
	if(isNaN(prop)){
		alert("项目比重只能为数字！");
		document.evs0208.evItemDetailProp.value="";
		document.evs0208.evItemDetailProp.focus();
		return false;
	}
	return true;
}
</script>
