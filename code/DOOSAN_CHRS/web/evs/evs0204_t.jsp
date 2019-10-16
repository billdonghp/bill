
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsDept"%>
<%
String flag="";
String menu_code="";
String evPeriodId="";
String RedirectURL="";
EvsDept evsDept;
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evPeriodId="+evPeriodId;

if(flag.equals("update")){
	evsDept=new EvsDept();
	String [] check;
	check=request.getParameterValues("check");
    try{
    	if(check!=null){
    		int checkLen=check.length;
    		
    		for(int i=0;i<checkLen;i++){
    			String evDeptId=request.getParameter("evDeptId"+check[i])!=null?request.getParameter("evDeptId"+check[i]):"";
    			String evDeptGradeId=request.getParameter("evDeptGradeId"+check[i])!=null?request.getParameter("evDeptGradeId"+check[i]):"";
    			
    			if(!evDeptGradeId.equals("")&&!evDeptId.equals("")&&!evPeriodId.equals("")){
    				evsDept.updateDeptGradeId(evDeptGradeId,evDeptId,evPeriodId);
    			}
    			
    		}
    	}
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
%>
