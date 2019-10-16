<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,
                com.ait.evs.*"%>
<%
  String action = request.getParameter("Action");
  String empID = request.getParameter("EmpID");
  String itemCode = request.getParameter("itemCode");
  String year = request.getParameter("year");
  String period1 = "";
  String period2 = "";
  String redirectPath = "evs0201.jsp?EmpID=" + empID + "&menu_code=evs0201";
  int seqDetail = 0;
  if (request.getParameter("SeqDetail")!=null && request.getParameter("SeqDetail").trim().length()!=0)
    seqDetail = new Integer(request.getParameter("SeqDetail")).intValue();
  EvsPeriod evsPeriod = new EvsPeriod();

  if (year!=null && !year.equals("null") && year.trim().length()!=0) {
      Hashtable period_h = evsPeriod.getEvPeriodByEvYear(year);
      period1 = period_h.get("Period1").toString();
      period2 = period_h.get("Period2").toString();
  }

  if (action != null && action.equals("submit")){
      EvsScore evsScore = new EvsScore();
      evsScore.evsSubmit(period1,empID);
      evsScore.evsSubmit(period2,empID);
    response.sendRedirect(redirectPath);
    return;
  }
  
  if (action.equals("reject")){
      EvsScore evsScore = new EvsScore();
      evsScore.evsReject(period1,empID);
      evsScore.evsReject(period2,empID);
      response.sendRedirect(redirectPath);
    return;
  }
  
  if (action != null && action.equals("delete")) {
    if (seqDetail!=0) {
        EvsItemDetail evsItemDetail = new EvsItemDetail();
        evsItemDetail.deleteYearItemDetial(seqDetail);
    }
    response.sendRedirect(redirectPath);
    return;
  }
  if (action != null && action.equals("modify")) {
    String column001 = request.getParameter("column001");
    String column002 = request.getParameter("column002");
    String column003 = request.getParameter("column003");
    String column004 = request.getParameter("column004");
    String column005 = request.getParameter("column005");
    String column006 = request.getParameter("column006");
    String column007 = request.getParameter("column007");
    String column008 = request.getParameter("column008");
    String detailProp = request.getParameter("DetailProp");
    if (seqDetail!=0) {

        EvsColumn evsColumn = new EvsColumn();
        int returnCount = evsColumn.modifyColumnByItemDetail(seqDetail,column001,column002
        ,detailProp,column003,column004,column005,column006,column007,column008);
    }%>
<script language="JavaScript">
<!--
	window.opener.opener.location.href=opener.opener.location.href;	
	window.opener.close();
	window.close();
//-->
</script>
  <%
  }
  if (action != null && action.equals("add")) {
    float prop = 0;
    int seq = 0;
    int order = 0;
    String column001 = request.getParameter("column001");
    String column002 = request.getParameter("column002");
    String column003 = request.getParameter("column003");
    String column004 = request.getParameter("column004");
    String column005 = request.getParameter("column005");
    String column006 = request.getParameter("column006");
    String column007 = request.getParameter("column007");
    String column008 = request.getParameter("column008");
    String detailProp = request.getParameter("DetailProp");
    EvsColumn evsColumn = new EvsColumn();
    if (detailProp!=null && detailProp.trim().length()!=0)
      prop = new Float(detailProp).floatValue();
    EvsItemDetail itemDetail = new EvsItemDetail();
    itemDetail.setEvEmpID(empID);
    itemDetail.setEvItemID(itemCode);
    itemDetail.setPeriodID(period1);
    itemDetail.setEvDetailProp(prop);
    seq = itemDetail.createItemDetial(0);
    if (seq!=0) {
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN001",column001);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN002",column002);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN003",column003);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN006",column006);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN007",column007);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN008",column008);
      
    }
    itemDetail.setPeriodID(period2);
    seq = itemDetail.createItemDetial(seq);
    if (seq!=0) {
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN001",column001);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN002",column002);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN004",column004);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN005",column005);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN006",column006);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN007",column007);
      evsColumn.modifyColumnDetail(seq,"EVCOLUMN008",column008);
    }
    %>
<script language="JavaScript">
<!--
	window.opener.opener.location.href=opener.opener.location.href;
	window.close();
//-->
</script>
  <%}
  //response.sendRedirect(redirectPath);
%>
