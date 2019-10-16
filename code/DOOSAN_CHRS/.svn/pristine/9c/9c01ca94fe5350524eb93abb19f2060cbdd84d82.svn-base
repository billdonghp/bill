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
   Evsupcode evsUpcode1  = new  Evsupcode();
   evsUpcode1.DelEvsetupCode(ev_emp_id,ev_period_id,ev_type_id);
   if(null!=number)
   {
         for (int i = 0 ; i < number.length ; ++i)
		{
		    
			String  proficiency2 =  request.getParameter("proficiency1_"+i)==null?"0": request.getParameter("proficiency1_"+i);
			String  sheopercyq2 = request.getParameter("sheopercyq1_"+i)==null?"0":request.getParameter("sheopercyq1_"+i);
			String  operationcom2 = request.getParameter("operationcom1_"+i);
			String  qualityofwork2 = request.getParameter("qualityofwork1_"+i);
			String  standardaction2= request.getParameter("standardaction1_"+i);
			String  composite2 = request.getParameter("composite1_"+i);
			String  remark2 = request.getParameter("remark1_"+i);
			String  craftid2 = request.getParameter("craftid1_"+i);
			String  skilltype2 = request.getParameter("skilltype1_"+i);
			String  lineid2 = request.getParameter("lineid_"+i);
			String  aircraft2 = request.getParameter("aircraft_"+i);
			String  process2 = request.getParameter("process_"+i);
			String  jobcontent2 = request.getParameter("jobcontent_"+i);
			String  typeionid2 = request.getParameter("typeionid1_"+i);
			String  skileid2 = request.getParameter("skileid1_"+i);
			String  qualification2 = request.getParameter("qualification1_"+i);
			String  skillscore2 = request.getParameter("skillscore1_"+i);
			String  purpose2 = request.getParameter("purpose1_"+i);
		 if (flag1.equals("submit")) { 
		    Evsupcode evsUpcode = new Evsupcode(craftid2,lineid2,aircraft2,process2,jobcontent2,skilltype2,typeionid2,qualification2,skillscore2,purpose2,ev_emp_id,
		    ev_period_id,ev_type_id,proficiency2,sheopercyq2,operationcom2,qualityofwork2,standardaction2,composite2,skileid2,remark2);
		    try{
		    	  evsUpcode.addEvsetupCode();
		    }catch(Exception e){}
		  }	
	   }
   }

  String craftid = request.getParameter("craftid");
  String evstypecode = request.getParameter("evstypecode");
  String evslinecode = request.getParameter("evslinecode");
  String evsaircraftcode = request.getParameter("evsaircraftcode");
  String evsprocesscode = request.getParameter("evsprocesscode");
  String evsjcoentcode = request.getParameter("evsjcoentcode");
  String typeionid = request.getParameter("typeionid");
  String qualificationid = request.getParameter("qualificationid");
  String skillscore = request.getParameter("skillscore")==null?"0":request.getParameter("skillscore");
  String purposeid = request.getParameter("purposeid");
  String proficiency = request.getParameter("proficiency")==null?"0":request.getParameter("proficiency");
  String sheopercyq = request.getParameter("sheopercyq")==null?"0":request.getParameter("sheopercyq");
  String operationcom = request.getParameter("operationcom");
  String qualityofwork = request.getParameter("qualityofwork");
  String standardaction = request.getParameter("standardaction");
  String composite = request.getParameter("composite");
  String skileid = request.getParameter("skileid");   
  String REMARK = request.getParameter("REMARK");  
  String redirectPath = "/evs/evs0202.jsp?menu_code=evs0202";
//处理保存分数   项目流程向下移一位
  if (flag1.equals("submit") && null != craftid  ) { 
   
    Evsupcode evsUpcode = new Evsupcode(craftid,evslinecode,evsaircraftcode,evsprocesscode,evsjcoentcode,evstypecode,typeionid,qualificationid,skillscore,purposeid,ev_emp_id,
    ev_period_id,ev_type_id,proficiency,sheopercyq,operationcom,qualityofwork,standardaction,composite,skileid,REMARK);
    try{
    	//evsScore.saveEvsMark(detailSeq,processID,mark,idea);
    	  evsUpcode.addEvsetupCode();
    }catch(Exception e){}
    //evsScore.evsSave(period,empID);
  }
  int state = evsScore.evsSubmit(ev_period_id,ev_emp_id);
  System.out.print(state + "22222222222222222");
  if (flag1.equals("reject")){
      int state1 = evsScore.evsReject(ev_period_id,ev_emp_id);
  }
  if (flag1.equals("submit")) {
%>
<SCRIPT LANGUAGE="JavaScript" type="">
<!--
alert("您已成功操作");
location.href="<%=redirectPath%>";
//-->
</SCRIPT>
<%
  }
%>

