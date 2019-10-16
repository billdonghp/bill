
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsItem"%>

<%
String evPeriodId="";
String evTypeId="";
String evItemId="";
int evItemOrder=0;

EvsItem evsItem;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;

String RedirectURL="/evs/evs0102.jsp?menu_code=evs0102&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
try{
	if(request.getParameter("evItemOrder")!=null){
			evItemOrder=Integer.parseInt(request.getParameter("evItemOrder"));
	}

	if(!evPeriodId.equals("")&&!evTypeId.equals("")&&!evItemId.equals("")&&evItemOrder!=0){

		evsItem=new EvsItem(evPeriodId,evTypeId,evItemId,"",evItemOrder);
		evsItem.addEvsTypeItem();
	}
}catch(Exception e){}
finally{
	response.sendRedirect(RedirectURL);
}
%>
