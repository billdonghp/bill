<%@page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsOtherItem"%>
<%@ page import="com.ait.evs.EvsOtherItemDetail"%>
<%@ page import="com.ait.evs.EvsOtherColumn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 评价进行 > 其它项目</title>
</head>
<body>
<%

String evPeriodId="";
String evEmpId="";
String evTypeId="";
String evProcessId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evEmpId=request.getParameter("evEmpId")!=null?request.getParameter("evEmpId"):evEmpId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
if(evEmpId.equals("")){
if (admin != null){
		evEmpId = admin.getAdminID();
}}
EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){evPeriodId=evsP.getCurrentEvPeriod();}
if(!evPeriodId.equals("")){
	try{
		EvsEmp evsEmp = new EvsEmp(evEmpId,evPeriodId);
		evsEmp.getEvEmpByEmpIdPeriod();
  		if (evsEmp!=null) {
  			if(evTypeId.equals("")){
  				evTypeId=evsEmp.getEvTypeID();
  			}
  			if(evProcessId.equals("")){
  				evProcessId=evsEmp.getEvCurrentProcessID();
  			}
  		}
	}catch(Exception e){}
}

List lEvsPeriod=null;
List lEvsType=null;

EvsEmp evsEmp=new EvsEmp();
EvsOtherItem evOtherItems=null;
try{
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	evOtherItems=new EvsOtherItem(evPeriodId,evTypeId,evEmpId);
}catch(Exception e){}

%>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evstoolbar_0208.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2" class="title_line_01"></td>
	</tr>
	<tr>
		<td height="2" class="title_line_02"></td>
	</tr>
	<tr>
	<form action="/evs/evs0208.jsp?menu_code=evs0208" method="Post"
		name="evs0208">
	<td height="2" align="right">
		<input type='hidden' name='evTypeId' value='<%=evTypeId%>'>
		<input type='hidden' name='evEmpId' value='<%=evEmpId%>'>
		<input type='hidden' name='evProcessId' value='<%=evProcessId%>'>
		<select name="evPeriodId" onChange="evs0208.submit();">
			<%
                            if(lEvsPeriod!=null){
                            	int lEvsPeriodSize=lEvsPeriod.size();
                            	for(int i=0;i<lEvsPeriodSize;i++){
                            		EvsPeriod evsPeriod_sel=(EvsPeriod)lEvsPeriod.get(i);
                              %>
			<option value="<%=evsPeriod_sel.getEvPeriodID()%>"
				<%if (evPeriodId.equals(evsPeriod_sel.getEvPeriodID())) {%>
				selected="selected" <%}%>><%=evsPeriod_sel.getEvPeriodName()%></option>
			<%}}%>
		</select> </td>
</form>
</tr>
</table>
<form name='evItem'>
<%
if(evOtherItems!=null){
	List lEvOtherItems=null;
	try{
   		lEvOtherItems=evOtherItems.getEvOtherItems(evProcessId);
   	}catch(Exception e){}
   	if(lEvOtherItems!=null){
	   	int evOtherItemSize=lEvOtherItems.size();
	   	for(int i=0;i<evOtherItemSize;i++){
	   		EvsOtherItem evOtherItem=(EvsOtherItem)lEvOtherItems.get(i);
	   		List lEvOtherItemDetail=null;
	   		List lEvOtherColumn=null;
	   		int lEvOtherItemDetailSize=0;
	   		int lEvOtherColumnSize=0;
	   		try{
		   		lEvOtherItemDetail=evOtherItem.getLEvOtherItemDetail();
		   		lEvOtherColumn=evOtherItem.getLItemColumns();
		   		lEvOtherItemDetailSize=lEvOtherItemDetail.size();
		   		lEvOtherColumnSize=lEvOtherColumn.size();
		   }catch(Exception e){}		
	   		
%>
			<table width="98%"  border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<!--项目名-->
			<tr align="center" bgcolor="#F5F5F5">
				<td colspan="<%=lEvOtherColumnSize+2%>">
				<b><%=(evOtherItem.getEvItemName()).replaceAll("<br>","")%></b>
				</td>
			</tr>
			<!--项目行标题-->	
			<tr	align="center" bgcolor="#F5F5F5">
				<td width="50" align="center">序号</td>
				<%if(lEvOtherColumn!=null){
				
					for(int j=0;j<lEvOtherColumnSize;j++){
						EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumn.get(j);
						
				%>	
					<!--项目动态列-->
					<td><%=evOtherColumn.getEvColumnName().replaceAll("<br>","")%></td>	
				<%
					}
				}
				%>
				<!--<td width="50" align="center">比重</td>
			--></tr>
			
			<!--项目内容-->	
			<%if(lEvOtherItemDetail!=null){
				
				for(int k=0;k<lEvOtherItemDetailSize;k++){
					EvsOtherItemDetail evOtherItemDetail=(EvsOtherItemDetail)lEvOtherItemDetail.get(k);
					List  lEvOtherDetailColumn=null;
					try{
						lEvOtherDetailColumn=evOtherItemDetail.getLEvOtherColumn();
					}catch(Exception e){}
					
			%>
			<tr onClick="HighLightTR('#99CCFF','black','<%=evOtherItemDetail.getSeqEvOtherItemDetail()%>','<%=menu_code%>')">	
					<!--序号-->
					<td align="center"><%=k+1%></td>
					<%
					if(lEvOtherDetailColumn!=null){
						int lEvOtherDetailColumnSize=lEvOtherDetailColumn.size();
						for(int m=0;m<lEvOtherDetailColumnSize;m++){
							EvsOtherColumn evOtherDetailColumn=(EvsOtherColumn)lEvOtherDetailColumn.get(m);
							
					%>
							<!--项目列-->
							<td><%=StringUtil.checkNull(evOtherDetailColumn.getEvColumnDetail())%></td>
					<%
						}
					}
					%>
					<!--比重-->
					<!--<td align="center" id="<%//=evOtherItem.getEvItemId()%>" name="<%//=evOtherItem.getEvItemId()%>"><%//=evOtherItemDetail.getEvDetailProp()%></td>
			--></tr>
			<%
				}
			}
			%>
			</table>
<%
		}
	}
}
%>
</form>
</body>
</html>
<script language="JavaScript" type="" src="">
	
    function Add()
    {	
		evPeriodId=document.evs0208.evPeriodId.value;
		evTypeId=document.evs0208.evTypeId.value;
		evEmpId=document.evs0208.evEmpId.value;
		
		location.href='/evs/evs0208_a.jsp?menu_code=evs0208&evTypeId='+evTypeId+'&evPeriodId='+evPeriodId+'&evEmpId='+evEmpId;		

    }
	function Modify2()
	{	
		evPeriodId=document.evs0208.evPeriodId.value;
		evTypeId=document.evs0208.evTypeId.value;
		evEmpId=document.evs0208.evEmpId.value;
		if(evTypeId==''||evPeriodId==''){
			alert("请先选择评价类型与评价期间!");
		}else{
    		if (ID=='')
    		{
            	alert("请在列表中选择要修改的项目");
   	 		}
    		else
    		{	
				location.href='/evs/evs0208_m.jsp?menu_code=evs0208&evTypeId='+evTypeId+'&evPeriodId='+evPeriodId+'&ID='+ID+'&evEmpId='+evEmpId;	
    		}
		}
	}
    </script>