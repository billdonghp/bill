
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>

<%
String flag="";
String menu_code="";
String ev_period_id="";
String RedirectURL="";
String evPeriodId="";
String evTypeId="";
String evDeptId="";
String evProcessId="";
String evEmpId="";
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evDeptId="+evDeptId+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;

if(flag.equals("update")){
    evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
    evEmpId=request.getParameter("evEmpId")!=null?request.getParameter("evEmpId"):evEmpId;
    int ok=0;
	try{
		
		if(!evProcessId.equals("")&&!evPeriodId.equals("")&&!evEmpId.equals("")){
			EvsEmp evsEmp=new EvsEmp(evEmpId,evPeriodId);
			ok=evsEmp.updateEvsEmpProcess(evProcessId);
		}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
%>
