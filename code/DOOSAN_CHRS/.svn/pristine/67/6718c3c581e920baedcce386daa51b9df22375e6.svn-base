
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsProcess"%>

<%
String evPeriodId="";
String evTypeId="";
String evProcessId="";
String evProcessEDate="";
String evProcessSDate="";
int evProcessOrder=0;
EvsProcess evsProcess;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
evProcessEDate=request.getParameter("evProcessEDate")!=null?request.getParameter("evProcessEDate"):evProcessEDate;
evProcessSDate=request.getParameter("evProcessSDate")!=null?request.getParameter("evProcessSDate"):evProcessSDate;
String RedirectURL="/evs/evs0102.jsp?menu_code=evs0102&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;

try{
	if(request.getParameter("evProcessOrder")!=null){
			evProcessOrder=Integer.parseInt(request.getParameter("evProcessOrder"));
	}

	if(!evPeriodId.equals("")&&!evTypeId.equals("")&&!evProcessId.equals("")&&evProcessOrder!=0){
		evsProcess=new EvsProcess(evPeriodId,evTypeId,evProcessId,"",evProcessOrder,evProcessSDate,evProcessEDate);
		System.out.print("!!!!!!!!!!!!!!!!!!!!!!!!!!");
		evsProcess.addEvsTypeProcess();
	}
}catch(Exception e){ e.printStackTrace(); }
finally{
	response.sendRedirect(RedirectURL);
}
%>
