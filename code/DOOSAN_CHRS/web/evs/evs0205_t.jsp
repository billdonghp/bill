<%@page contentType="text/html; charset=UTF-8" errorPage=""%>
<%@page
	import="java.util.*,
                com.ait.evs.EvsScore,
                com.ait.evs.EvsMaster,
                com.ait.evs.EvsColumn,
                com.ait.util.StringUtil,
                com.ait.evs.EvsItemDetail"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%
  String empID = request.getParameter("EmpID");
  String period = request.getParameter("Period");
  String type = request.getParameter("type");
  String action = request.getParameter("Action");
  String itemCode = request.getParameter("itemCode");
  String processID = request.getParameter("Process");
  int seqDetail = 0;
  if (request.getParameter("SeqDetail")!=null && request.getParameter("SeqDetail").trim().length()!=0)
    seqDetail = new Integer(request.getParameter("SeqDetail")).intValue();
  String redirectPath = "/evsControlServlet?operation=retrieveEvaluate&EmpID=" + empID + "&Period=" + period + "&menu_code=evs0205";
//处理保存分数
  if (action.equals("save")) {
    EvsScore evsScore = new EvsScore();
    int count =0;
    try{
     	count=new Integer(request.getParameter("count")).intValue();
     }catch(Exception e){}
    for (int index = 1;index<count;index++) {
		int detailSeq =-1;
        float mark = 0f;
        try{
        detailSeq=new Integer(request.getParameter("detail_"+index)).intValue();
        mark=new Float(request.getParameter("mark_"+index)).floatValue();
        }catch(Exception e){
        }

        String idea = (request.getParameter("idea_"+index)!=null) ? request.getParameter("idea_"+index) : "";
        evsScore.saveEvsMark(detailSeq,processID,mark,idea);
    }
   //加减分操作
    float append=0;
    String appendReason="";
    if(request.getParameter("append")!=null){
    	try{
    		append=Float.parseFloat(request.getParameter("append"));
    	}catch(Exception e){
    		append=0;
    	}
    }
    appendReason=StringUtil.toCN(StringUtil.checkNull(request.getParameter("appendReason")));
    EvsMaster evMaster=new EvsMaster();
    try{
    	evMaster.addAppendByMaster(period,empID,processID,appendReason,append);
    }catch(Exception e){}
    evsScore.evsSave(period,empID);
  }
//处理提交

  if (action.equals("submit")){
  
     EvsScore evsScore = new EvsScore();
     
    int count =0;
    try{
     	count=new Integer(request.getParameter("count")).intValue();
     }catch(Exception e){e.printStackTrace();}
    for (int index = 1;index<count;index++) {
		int detailSeq =-1;
        float mark = 0f;
        try{
        detailSeq=new Integer(request.getParameter("detail_"+index)).intValue();
        mark=new Float(request.getParameter("mark_"+index)).floatValue();
        }catch(Exception e){
        	e.printStackTrace();
        }
        String idea = (request.getParameter("idea_"+index)!=null) ? request.getParameter("idea_"+index) : "";
        evsScore.saveEvsMark(detailSeq,processID,mark,idea);
    }
   //加减分操作
    float append=0;
    String appendReason="";
    if(request.getParameter("append")!=null){
    	try{
    		append=Float.parseFloat(request.getParameter("append"));
    	}catch(Exception e){
    		append=0;
    	}
    }
    appendReason=StringUtil.toCN(StringUtil.checkNull(request.getParameter("appendReason")));
    EvsMaster evMaster=new EvsMaster();
    try{
    	evMaster.addAppendByMaster(period,empID,processID,appendReason,append);
    }catch(Exception e){e.printStackTrace();}
    
      
      int state = evsScore.evsSubmit(period,empID);
  }
  if (action.equals("reject")){
      EvsScore evsScore = new EvsScore();
      int state = evsScore.evsReject(period,empID);
  }
  if (action.equals("save") || action.equals("submit") || action.equals("reject")) {
%>
<SCRIPT LANGUAGE="JavaScript" type="">
<!--
alert("您已成功操作");
location.href="<%=redirectPath%>";
//-->
</SCRIPT>
<%
  }
  if (action != null && action.equals("modify")) {
    float prop = 0;
    String column001 = request.getParameter("column001");
    String column002 = request.getParameter("column002");
    String column003 = request.getParameter("column003");
    String column004 = request.getParameter("column004");
    String column005 = request.getParameter("column005");
    String column006 = request.getParameter("column006");
    String column016 = request.getParameter("column016");
    String detailProp = request.getParameter("DetailProp");
    if (seqDetail!=0) {
      if (detailProp!=null)
        prop = new Float(detailProp).floatValue();
      EvsColumn evsColumn = new EvsColumn();
      EvsItemDetail itemDetail = new EvsItemDetail();
 	  if (type.equals("EVTYPE003") || type.equals("EVTYPE004")) {
    	  
   		  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN001",column001);
   		  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN016",column016);
      } else {
    	  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN002",column002);
    	  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN006",column006);
   		  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN003",column003);
   		  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN004",column004);
 		  evsColumn.modifyColumnDetail(seqDetail,"EVCOLUMN005",column005);
      }
      itemDetail.modiyProp(seqDetail,prop);
    }%>
<script language="JavaScript">
<!--
 	
	window.opener.opener.location.reload(true);
	window.opener.close();
	window.close();
//-->
</script>
<%
  //response.sendRedirect(redirectPath);
  //return;
  }
  if (action != null && action.equals("add")) {
	
    float prop = 0;
    int seq = 0;
    String column001 = request.getParameter("column001");
    String column002 = request.getParameter("column002");
    String column003 = request.getParameter("column003");
    String column004 = request.getParameter("column004");
    String column005 = request.getParameter("column005");
    String column006 = request.getParameter("column006");
    String column016 = request.getParameter("column016");
    String detailProp = request.getParameter("DetailProp");
    EvsColumn evsColumn = new EvsColumn();
    if (detailProp!=null)
      prop = new Float(detailProp).floatValue();
    EvsItemDetail itemDetail = new EvsItemDetail();
    itemDetail.setEvEmpID(empID);
    itemDetail.setEvItemID(itemCode);
    itemDetail.setPeriodID(period);
    itemDetail.setEvDetailProp(prop);
    seq = itemDetail.createItemDetial(0);
    if (seq!=0) {
    	
      if (type.equals("EVTYPE003") || type.equals("EVTYPE004")) {
    	  
   		  evsColumn.modifyColumnDetail(seq,"EVCOLUMN001",column001);
   		  evsColumn.modifyColumnDetail(seq,"EVCOLUMN016",column016);
      } else {
    	  
    	  evsColumn.modifyColumnDetail(seq,"EVCOLUMN002",column002);
    	  evsColumn.modifyColumnDetail(seq,"EVCOLUMN006",column006);
   		  evsColumn.modifyColumnDetail(seq,"EVCOLUMN003",column003);
   		  evsColumn.modifyColumnDetail(seq,"EVCOLUMN004",column004);
 		  evsColumn.modifyColumnDetail(seq,"EVCOLUMN005",column005);
      }
  }%>
<script language="JavaScript">
<!--
	window.opener.opener.location.reload(true);
	window.opener.close();
	window.close();
//-->
</script>
<%
	//response.sendRedirect(redirectPath);
	//return;
  }
  if (action != null && action.equals("delete")) {
    if (seqDetail!=0) {
        EvsItemDetail evsItemDetail = new EvsItemDetail();
        int a = evsItemDetail.deleteItemDetial(seqDetail);
    }
    response.sendRedirect(redirectPath);
    return;
  }
%>

