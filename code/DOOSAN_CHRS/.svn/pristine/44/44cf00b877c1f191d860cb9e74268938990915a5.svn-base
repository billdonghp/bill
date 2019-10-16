
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsColumn"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%
EvsItem evsItem=null;
String evTypeId="";
String evPeriodId="";
String evItemId="";
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;

String flag="";
String menu_code="";
String RedirectURL="";
flag=request.getParameter("flag")!=null?request.getParameter("flag"):flag;
menu_code=request.getParameter("menu_code")!=null?request.getParameter("menu_code"):menu_code;
RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;

//添加评价项目列
if(flag.equals("add")){
	try{
    	
    	String [] aEvColumnId;
    	if(!evPeriodId.equals("")&&!evTypeId.equals("")&&!evItemId.equals("")){
    	
    		List lEvColumn=null;
    		aEvColumnId=request.getParameterValues("evColumnId");
    		
    		if(aEvColumnId!=null){
    			
    			lEvColumn=new Vector();
				for(int i=0;i<aEvColumnId.length;i++){
					EvsColumn evsColumn=new EvsColumn();
					evsColumn.setEvColumnId(aEvColumnId[i]);
					lEvColumn.add(evsColumn);
				}
			}
			evsItem=new EvsItem(evPeriodId,evTypeId,evItemId,lEvColumn);
			evsItem.updateItemColumn();
		}	
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
//更新评价项目列
if(flag.equals("update")){
    try{
    	String [] aEvColumnId;
    	if(!evPeriodId.equals("")&&!evTypeId.equals("")&&!evItemId.equals("")){
    	
    		List lEvColumn=null;
    		aEvColumnId=request.getParameterValues("evColumnId");
    		if(aEvColumnId!=null){
    			lEvColumn=new Vector();
				for(int i=0;i<aEvColumnId.length;i++){
					EvsColumn evsColumn=new EvsColumn();
					evsColumn.setEvColumnId(aEvColumnId[i]);
					lEvColumn.add(evsColumn);
				}
			}
			evsItem=new EvsItem(evPeriodId,evTypeId,evItemId,lEvColumn);
			evsItem.updateItemColumn();
		}		
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
//删除评价项目列
if(flag.equals("del")){
    String evPeriodId_DEL = (request.getParameter("ID") != null) ? request.getParameter("ID").trim(): "";
	String evTypeId_DEL=(request.getParameter("ID2") != null) ? request.getParameter("ID2").trim(): "";
	String evItemId_DEL=(request.getParameter("ID3") != null) ? request.getParameter("ID3").trim(): "";
	try{
		evsItem=new EvsItem(evPeriodId_DEL,evTypeId_DEL,evItemId_DEL,"", 0);
		evsItem.delItemColumn();
		RedirectURL=menu_code+".jsp?menu_code="+menu_code+"&evTypeId="+evTypeId_DEL+"&evPeriodId="+evPeriodId_DEL;
	}catch(Exception e){}
	finally{
		response.sendRedirect(RedirectURL);
	}
}
%>
