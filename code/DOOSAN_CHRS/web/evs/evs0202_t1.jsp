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
  String craftid = request.getParameter("craftid");
  String evstypecode = request.getParameter("evstypecode");
  String evslinecode = request.getParameter("evslinecode");
  String evsaircraftcode = request.getParameter("evsaircraftcode");
  String evsprocesscode = request.getParameter("evsprocesscode");
  String evsjcoentcode = request.getParameter("evsjcoentcode");
  String typeionid = request.getParameter("typeionid");
  String proficiency = request.getParameter("proficiency");
  String operationcom = request.getParameter("operationcom");
  String qualityofwork = request.getParameter("qualityofwork");
  String standardaction = request.getParameter("standardaction");
  String composite = request.getParameter("composite");
  String skileid = request.getParameter("skileid");   
  String REMARK = request.getParameter("REMARK"); 
  String flag1 = request.getParameter("flag1");    
  String redirectPath = "/evs/evs0202.jsp?menu_code=evs0202";
//处理保存分数   项目流程向下移一位
  if (flag1.equals("submit")) { 
    EvsScore evsScore = new EvsScore();
    Evsupcode evsUpcode = new Evsupcode(craftid,evslinecode,evsaircraftcode,evsprocesscode,evsjcoentcode,evstypecode,typeionid,ev_emp_id,
    ev_period_id,ev_type_id,proficiency,operationcom,qualityofwork,standardaction,composite,skileid,REMARK);
    try{
    	//evsScore.saveEvsMark(detailSeq,processID,mark,idea);
    	  evsUpcode.addEvsetupCode();
    }catch(Exception e){}
    //evsScore.evsSave(period,empID);
    int state = evsScore.evsSubmit(ev_period_id,ev_emp_id);
  }
  if (flag1.equals("reject")){
      EvsScore evsScore = new EvsScore();
      int state = evsScore.evsReject(ev_period_id,ev_emp_id);
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

