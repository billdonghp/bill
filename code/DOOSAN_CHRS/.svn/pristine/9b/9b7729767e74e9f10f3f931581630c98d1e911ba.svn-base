<%@ page contentType="text/html; charset=UTF-8"  import="java.util.*,com.ait.sqlmap.util.*,com.ait.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.ait.ar.bean.ArDetail"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤明细维护报表</title>
</head>                              
<body>
 <%
 		response.reset();
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=arDeailReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
     %>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr align="center" height="60">
    <td colspan="2">             
	    <table width="100%">
	    	<tr>
	    		<td align="center" colspan="11"><b><font size="+2"><!-- 考勤明细维护报表 -->
	    		<ait:message messageID="report.reportname" module="report"></ait:message>
	    		<%-- 
	    	<ait:message messageID="display.ess.attendance_request.ot_request.overtime_work_request" module="ess"></ait:message>
	    	 --%>
	    		</font></b></td>
	    	</tr>
	    </table>    	
    </td>
  </tr>
  
  <tr>
    <td><table width="100%" border="2"   
			align="center" cellpadding="0" cellspacing="0"
			style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
		<tr>
		 <td align="center"><!--部门  --> <ait:message messageID="report.global.title.deptName" module="report"></ait:message></td>
		 <td align="center"><!-- 职务 --><ait:message messageID="report.common.title.Position" module="report"></ait:message></td>	
		 <td align="center"><!-- 日期 --><ait:message messageID="report.mutual.date" module="report"></ait:message> </td>
         <td align="center"><!-- 工号 --><ait:message messageID="report.global.title.empID" module="report"></ait:message></td>
         <td align="center"><!--  姓名--><ait:message messageID="report.global.title.name" module="report"></ait:message> </td>
         <td align="center"><!--  班次--><ait:message messageID="report.mutual.shift" module="report"></ait:message> </td>
         <td align="center"><!-- 项目 --> <ait:message messageID="report.att.maintenance.maintenance.item" module="report"></ait:message></td>
         <td align="center"><!-- 开始时间 --> <ait:message messageID="report.mutual.start_date_2" module="report"></ait:message></td>
         <td align="center"><!-- 结束时间 --><ait:message messageID="report.mutual.end_date" module="report"></ait:message> </td> 
         <td align="center"><!-- 长度 --><ait:message messageID="report.mutual.length" module="report"></ait:message> </td>
         <td align="center">单位 </td>
        </tr>      
      <%
      List datalist= (List)request.getAttribute("datalist");
      ArDetail  detail=new ArDetail() ;
      for(int i=0;i<datalist.size();i++)
      {
    	  detail = (ArDetail) datalist.get(i);
    	  %>
    	 <tr>
    	  <td align="center"><ait:content enContent='<%=StringUtil.checkNull(detail.getEnDept())%>' 
    	  zhContent='<%=StringUtil.checkNull(detail.getDeptName())%>' 
    	  koContent='<%=StringUtil.checkNull(detail.getKorDept())%>'></ait:content></td>
    	  <td align="center"><ait:content enContent='<%=StringUtil.checkNull(detail.getEnPositionName())%>' 
    	  zhContent='<%=StringUtil.checkNull(detail.getPositionName())%>' 
    	  koContent='<%=StringUtil.checkNull(detail.getKorPositionName())%>'></ait:content></td>
    	  <td align="center"><%=detail.getAr_date_str() %></td>  
    	  <td align="center"><%=detail.getEmpID()%>&nbsp;</td>
    	  <td align="center"><ait:content enContent='<%=StringUtil.checkNull(detail.getEmpPinyin())%>' 
    	  zhContent='<%=StringUtil.checkNull(detail.getEmpName())%>' 
    	  koContent='<%=StringUtil.checkNull(detail.getKorEmpName())%>'></ait:content></td>
    	  <td align="center">
    	  <ait:content enContent='<%=StringUtil.checkNull(detail.getEnShiftName())%>' 
    	  zhContent='<%=StringUtil.checkNull(detail.getShiftName())%>' 
    	  koContent='<%=StringUtil.checkNull(detail.getKorShiftName())%>'></ait:content></td>  
    	  <td align="center">
    	  <ait:content enContent='<%=StringUtil.checkNull(detail.getEnItemName())%>' 
    	  zhContent='<%=StringUtil.checkNull(detail.getItemName())%>' 
    	  koContent='<%=StringUtil.checkNull(detail.getKorItemName())%>'></ait:content></td>
    	   <td align="center"><%=StringUtil.checkNull(detail.getFromTime(),"未刷卡")%></td>
    	    <td align="center"><%=StringUtil.checkNull(detail.getToTime(),"未刷卡")%></td>        
    	  <td align="center"><%=detail.getQuantity()%></td>
    	  <td align="center"><%=detail.getUnitName() %></td>                 
    	 </tr> 
    	                                   
    	  <%	    
      }
      %>
      </table>  
	  </td>
  </tr>
</table>
</body>
</html>