
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.ait.util.DataAccessException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsBatch"%>
<%@ page import="com.ait.sy.bean.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"      scope="session" />
<%

admin = (AdminBean)session.getAttribute("admin");
String ev_year="";
String flag="";
String menu_code="";
String ev_period_name="";
String ev_period_id=""; 
String empid = admin.getAdminID();
String [] ev_type_id;
String RedirectURL="";
EvsPeriod evsPeriod;
List lEvType;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
ev_period_id=request.getParameter("ev_period_id")!=null?request.getParameter("ev_period_id"):ev_period_id;
ev_year=request.getParameter("ev_year")!=null?request.getParameter("ev_year"):ev_year; 
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&ev_year="+ev_year;
//添加评价期间
if(flag.equals("add")){
    
	ev_period_name=request.getParameter("ev_period_name")!=null?request.getParameter("ev_period_name"):ev_period_name;
	ev_period_name=StringUtil.toCN(ev_period_name);
	ev_type_id=request.getParameterValues("ev_type_id");
	lEvType=new Vector();
	try{
	if(ev_type_id!=null){
	    EvsType evsType;
	    int len=ev_type_id.length;
		for(int i=0;i<len;i++){
		   evsType=new EvsType();
		   evsType.setEvTypeID(ev_type_id[i]);
		   lEvType.add(evsType);
		}
	}
	evsPeriod=new EvsPeriod(ev_year,ev_period_id,ev_period_name,lEvType,empid);
	evsPeriod.addPeriod();
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
//更新评价期间
if(flag.equals("update")){
    ev_period_id=request.getParameter("ev_period_id")!=null?request.getParameter("ev_period_id"):ev_period_id;
	ev_period_name=request.getParameter("ev_period_name")!=null?request.getParameter("ev_period_name"):ev_period_name;
	ev_period_name=StringUtil.toCN(ev_period_name);
	ev_type_id=request.getParameterValues("ev_type_id");
	lEvType=new Vector();
	try{
	if(ev_type_id!=null){
	    EvsType evsType;
	    int len=ev_type_id.length;
		for(int i=0;i<len;i++){
		   evsType=new EvsType();
		   evsType.setEvTypeID(ev_type_id[i]);
		   lEvType.add(evsType);
		}
	}
	evsPeriod=new EvsPeriod(ev_year,ev_period_id,ev_period_name,lEvType,empid); 
	if(!ev_period_id.trim().equals("")){
		evsPeriod.updateEvsPeriodById();
	}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
if(flag.equals("del")){
	EvsBatch evBatch=null;
    ev_period_id=request.getParameter("ID")!=null?request.getParameter("ID"):ev_period_id;
    try{
	    lEvType=new Vector();
		evsPeriod=new EvsPeriod(ev_year,ev_period_id,ev_period_name,lEvType);
		if(!ev_period_id.trim().equals("")){
		   evBatch=new EvsBatch(ev_period_id);
			
		   evBatch.batchDelAll();
			//evsPeriod.delEvPeriod();
		}
	}catch(DataAccessException e){
		%>
		<SCRIPT LANGUAGE="JavaScript" type="">
		<!--
		alert("<%=e.getMessage()%>");
		location.href='<%=RedirectURL%>';
		//-->
		</SCRIPT>
		<%
		
	}
	catch(Exception e){
		%>
		<SCRIPT LANGUAGE="JavaScript" type="">
		<!--
		alert("删除失败！");
		location.href='<%=RedirectURL%>';
		//-->
		</SCRIPT>
		<%
		
	}
	%>
		<SCRIPT LANGUAGE="JavaScript" type="">
		<!--
		alert("操作成功");
		location.href='<%=RedirectURL%>';
		//-->
		</SCRIPT>
		<%
	
}
%>
