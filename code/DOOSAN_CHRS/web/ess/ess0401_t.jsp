<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,com.ait.ess.dao.*" errorPage="" %>
<% 
   Tolerence tolerence = new Tolerence();
   String menu_code = request.getParameter("menu_code")!=null? request.getParameter("menu_code"):"";
   String empID = request.getParameter("empID")!=null? request.getParameter("empID"):"";
   String[] affirmlist = request.getParameterValues("affirmlist");
   String StartDate = request.getParameter("StartDate")!=null? request.getParameter("StartDate"):"";
   String EndDate = request.getParameter("EndDate")!=null? request.getParameter("EndDate"):"";
   String tolerenceTarget = request.getParameter("tolerenceTarget")!=null? request.getParameter("tolerenceTarget"):"";
  
  
  
   String[] tolerencTime = request.getParameterValues("tolerencTime"); //出差内容
   String[] tolerencTransfer = request.getParameterValues("tolerencTransfer");
   String[] tolerencPlace = request.getParameterValues("tolerencPlace");
   String[] tolerencCompany = request.getParameterValues("tolerencCompany");
   String[] tolerencContent = request.getParameterValues("tolerencContent");
   
   
   String[] tolerencFeeTime = request.getParameterValues("tolerencFeeTime");//出差费项目
   String[] ticketFee = request.getParameterValues("ticketFee");
   String[] houseFee = request.getParameterValues("houseFee");
   String[] workFee = request.getParameterValues("workFee");
   String[] otherFee = request.getParameterValues("otherFee");
   
   String[] ticketCount = request.getParameterValues("ticketCount");
   String[] houseCount = request.getParameterValues("houseCount");
   String[] workCount = request.getParameterValues("workCount");
   String[] otherCount = request.getParameterValues("otherCount");
   
   String[] ticketFeeD = request.getParameterValues("ticketFee");
   String[] houseFeeD = request.getParameterValues("houseFee");
   String[] workFeeD = request.getParameterValues("workFee");
   String[] otherFeeD = request.getParameterValues("otherFee");
   
   String[] ticketCountD = request.getParameterValues("ticketCountD");
   String[] houseCountD = request.getParameterValues("houseCountD");
   String[] workCountD = request.getParameterValues("workCountD");
   String[] otherCountD = request.getParameterValues("otherCountD");
   
   EssDAO essdao  = new EssDAO();
   String TolerenceSeq = essdao.getTolerenceApplySeq();
   tolerence.setTolerenceNo(TolerenceSeq);
   tolerence.setTolerenceFromTime(StartDate);
   tolerence.setTolerenceToTime(EndDate);
   tolerence.setTolerenceTarget(tolerenceTarget);
   essdao.addTolerenceApply(tolerence,empID);
   
   if(affirmlist!=null){
   essdao.addEssApplyAffirmor(TolerenceSeq,affirmlist,"TolerenceApply");
   }
   
   if(tolerencTime!=null){
   essdao.addTolerenceContent(TolerenceSeq,tolerencTime,tolerencTransfer,tolerencPlace,tolerencCompany,tolerencContent);
   }
   if(tolerencFeeTime!=null){
   essdao.addTolerenceFeeContent(TolerenceSeq,tolerencFeeTime,ticketFee,houseFee,workFee,otherFee,ticketCount,houseCount,workCount,otherCount,ticketFeeD,houseFeeD,workFeeD,otherFeeD,ticketCountD,houseCountD,workCountD,otherCountD);
   }
   response.sendRedirect("/ess/ess0401.jsp?menu_code="+menu_code);
%>