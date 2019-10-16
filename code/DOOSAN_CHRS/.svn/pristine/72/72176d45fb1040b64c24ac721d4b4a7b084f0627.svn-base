
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="com.ait.util.DataAccessException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsCraft"%> 
<%
 
String menu_code="";
String flag = "";
String evscodename="";
String evscodeid="";
String evscodeenname = ""; 
String cpnyid  = "";
String RedirectURL="";
EvsCraft evsCraft;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
evscodeid=request.getParameter("evscodeid")!=null?request.getParameter("evscodeid"):evscodeid;
cpnyid=request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):cpnyid; 
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evscodeid="+evscodeid;
//添加工种信息
if(flag.equals("add")){
    
	evscodename=request.getParameter("evscodename")!=null?request.getParameter("evscodename"):evscodename; 
cpnyid=request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):cpnyid; 
	try{
	evsCraft=new EvsCraft(evscodeid,evscodename,evscodeenname,cpnyid);
	if(!evscodename.trim().equals("")){
		evsCraft.addEvsCraft();
	}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}


//更新工种信息
 

//删除工种信息
%>
