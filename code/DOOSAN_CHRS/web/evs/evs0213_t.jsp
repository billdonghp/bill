<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsScore"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsDeptDefaultGrade"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil"%>
<%@ page import="java.util.*"%>

<%
EvsScore evsScore=new EvsScore();
String forwardAddress="/evs/evs0213.jsp?menu_code=evs0213";

String flag=StringUtil.checkNull(request.getParameter("flag"));

SimpleMap parameterMap=new SimpleMap();
parameterMap = ObjectBindUtil.getData(request);
SimpleMap conditionMap=new SimpleMap();
Set s=parameterMap.keySet();
Iterator iter=s.iterator();

while(iter.hasNext()){
	String key = (String)iter.next();
	if(key.indexOf("_CON")>=1){
		forwardAddress=forwardAddress+"&"+key.replaceAll("_CON","")+"="+parameterMap.get(key);
		conditionMap.setString(key.replaceAll("_CON",""),StringUtil.checkNull(parameterMap.get(key)));
	}
}

if(flag.equals("return")){
    
	try{
				
	    	String[] check;
	    	check = request.getParameterValues("selectedTag");
	    	List<SimpleMap> lEmp = new ArrayList<SimpleMap>();
	    	
	    	if (null != check) {
				for (int m = 0, n = check.length; m < n; m++) {
			    	SimpleMap bean = new SimpleMap();
			    	bean=ObjectBindUtil.getData(request,"_" + check[m]);
			    	lEmp.add(bean);
				}
	    	}
			evsScore.evsReject(lEmp);
		    	
			
	}catch(Exception e){}
	finally{
		
		response.sendRedirect(forwardAddress);
	}
}
if(flag.equals("submit")){
   
	try{
		
			String[] check;
	    	check = request.getParameterValues("selectedTag");
	    	List<SimpleMap> lEmp = new ArrayList<SimpleMap>();
	    	
	    	if (null != check) {
				for (int m = 0, n = check.length; m < n; m++) {
			    	SimpleMap bean = new SimpleMap();
			    	bean=ObjectBindUtil.getData(request,"_" + check[m]);
			    	lEmp.add(bean);
				}
	    	}
			evsScore.evSubmit2(lEmp);
		
	}catch(Exception e){}
	finally{
		response.sendRedirect(forwardAddress);
	}
}
%>