
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsDept"%>

<%
String ev_period_id="";
String flag="";
String menu_code="";
String ev_dept_id="";
String RedirectURL="";
EvsDept evsDept;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
ev_period_id=request.getParameter("ev_period_id")!=null?request.getParameter("ev_period_id"):ev_period_id;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&ev_period_id="+ev_period_id;

if(flag.equals("update")){
    evsDept=new EvsDept();
	try{
	int activity=-1;
   if(request.getParameter("activity")!=null){
	activity=Integer.parseInt(request.getParameter("activity"));
	}
	ev_dept_id=request.getParameter("ev_dept_id")!=null?request.getParameter("ev_dept_id"):ev_dept_id;
	if(activity!=-1&&ev_dept_id!=null&&ev_period_id!=null&&!ev_period_id.trim().equals("")&&!ev_dept_id.trim().equals("")){
	  evsDept.updateDeptActivity(ev_dept_id,ev_period_id,activity);
	}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
if(flag.equals("add")){
    evsDept=new EvsDept();
    String evPeriodId="";
    int ok=0;
	try{
		ok=evsDept.addEvsDept(ev_period_id);
	}catch(Exception e){}
	finally{
		//response.sendRedirect(RedirectURL);
	}
	if(ok==1){
		%>
	<SCRIPT LANGUAGE="JavaScript" type="">
	<!--
	alert("您已成功操作");
	location.href="<%=RedirectURL%>";
	//-->
	</SCRIPT>
		
		<%
	}else{
		response.sendRedirect(RedirectURL);
	}
}
%>
