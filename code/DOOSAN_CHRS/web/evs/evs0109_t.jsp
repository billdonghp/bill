<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsBatch"%>
<%
String evYear="";
String flag="";
String menu_code="";
String evPeriodName="";
String evPeriodShortName="";
int monthCount=0;
String evPeriodIdOld="";
String RedirectURL="";
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
evPeriodName=request.getParameter("evPeriodName")!=null?request.getParameter("evPeriodName"):evPeriodName;
evPeriodShortName=StringUtil.checkNull(request.getParameter("year"))+StringUtil.checkNull(request.getParameter("month"));
evPeriodIdOld=request.getParameter("evPeriodIdOld")!=null?request.getParameter("evPeriodIdOld"):evPeriodIdOld;
evPeriodName=StringUtil.toCN(evPeriodName);
try{
	monthCount=Integer.parseInt(request.getParameter("monthCount"));
}catch(Exception e){
	monthCount=0;
}
evYear=request.getParameter("evYear")!=null?request.getParameter("evYear"):evYear;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evYear="+evYear;
if(flag.equals("batchAll")){
	try{
	    EvsBatch evBatch=new EvsBatch(evPeriodIdOld, evYear, monthCount,evPeriodName,evPeriodShortName);

	    evBatch.batchAddAll();
	    %>
	    <SCRIPT LANGUAGE="JavaScript" type="">
		<!--
		alert('<%=evBatch.getMessage()%>');
		location.href='<%=RedirectURL%>';
		//-->
		</SCRIPT>
	    <%
	}catch(Exception e){ 
		%>
		<SCRIPT LANGUAGE="JavaScript" type="">
		<!--
		alert("操作失败");
		location.href='<%=RedirectURL%>';
		//-->
		</SCRIPT>
		<%
	}

}
%>
