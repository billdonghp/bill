<%@page contentType="text/html; charset=UTF-8" errorPage=""%>
<%@page
	import="java.util.*,
                com.ait.evs.EvsScore,
                com.ait.evs.Evsupcode,
                com.ait.evs.EvsMaster,
                com.ait.evs.EvsColumn,
                com.ait.util.StringUtil,
                com.ait.evs.EvsItemDetail"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%
    String ev_emp_id = request.getParameter("ev_emp_id");
	String ev_period_id = request.getParameter("ev_period_id");
    String ev_type_id = request.getParameter("ev_type_id");
   String flag1 = request.getParameter("flag1");   
   String[] number = request.getParameterValues("number");
   EvsScore evsScore = new EvsScore();
   String redirectPath = "/evs/evs0202.jsp?menu_code=evs0202";
   if(null!=number)
   {
         for (int i = 0 ; i < number.length ; ++i)
		{
		    
        	 String  affirmNo = request.getParameter("affirmNo_"+i);
        	 String  setupcodeNo = request.getParameter("setupcodeNo_"+i);
        	 System.out.print(setupcodeNo);
        	 if(affirmNo!=null&&affirmNo.equals("1")){
        		 try{
      		    	Evsupcode evsUpcode = new Evsupcode();
      		    	evsUpcode.setSETUPCODENO(setupcodeNo);
      		    	 evsUpcode.updateEvsSetupcodeById();
      		    }catch(Exception e){
      		    	
      		    }
        	 }
        	 	  
		  
	   }
   }
  if (flag1.equals("submit")) {
%>
<SCRIPT LANGUAGE="JavaScript" type="">
alert("您已成功操作");
location.href="<%=redirectPath%>";
</SCRIPT>
<%
  }
%>

