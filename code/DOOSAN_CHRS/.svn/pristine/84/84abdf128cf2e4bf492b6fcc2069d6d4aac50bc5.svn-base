<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加班申请</title>
</head>                              
<body>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=overtimeapply.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/main/logo.jpg";
     %>
<table width="98%" border="0"  height="50" cellpadding="0" cellspacing="0">
         <tr height="30">
	    <td   height="30">&nbsp;</td>
	     <td   height="30">&nbsp;</td>
	    <td   height="30">&nbsp;</td>
	    <td   height="30">&nbsp;</td>
	    <td   height="30">&nbsp;</td>
	   </tr>
  <tr  height="50">
    <td colspan="9">             
	    <table width="100%">
	    	<tr>
	    		<td  height="30" colspan="9" align="center"><b><font size="+3">
	    	 <ait:message messageID="display.ess.attendance_request.ot_request.overtime_work_request_report" module="ess"></ait:message>
	    	 </td>
	    	</tr>
	      <tr>     
	    		 <td   height="30">&nbsp;</td>
	    		  <td   height="30">&nbsp;</td>
	    		   <td   height="30">&nbsp;</td>
	    		    <td   height="30">&nbsp;</td>
	    		     <td   height="30">&nbsp;</td>
	    		     <td   height="30">&nbsp;</td>
	    		     <td   height="30">&nbsp;</td>
	    		      <td   height="30">&nbsp;</td>
	    		    
	     </tr>
	    </table>    	
    </td>
  </tr>
  <tr>
    <table width="100%" border="1"
			 height="50" cellpadding="0" cellspacing="0">  
        <tr>
         <td  height="50" align="center" ><div  height="50"><strong>
         <ait:message messageID="display.ess.confirmation.personal_info.request_no" module="ess"></ait:message>
        <!--  申请号--> </strong></div></td>
          <td   colspan="2" align="center"><c:out value='${applyOTDetail.APPLY_GROUP_NO}'/></td>
          <td   height="50" colspan="2" align="center"><div  height="50" align="center"><strong>
          <!-- 姓名  -->           
          <ait:message messageID="display.mutual.name"  module="ess"></ait:message>
         </strong></div></td>
          <td colspan="4"  height="50"  align="center">
          <ait:content enContent='${applyOTDetail.ENAPPLYER}' zhContent='${applyOTDetail.APPLYER}'></ait:content>
          </td>
        </tr>                            
        <tr><!-- 部门 -->
          <td   height="50" align="center" ><div  height="50" colspan="1"><strong><ait:message messageID="display.mutual.department"/></strong></div></td>
          <td nowrap  colspan="2"  align="center"><ait:content enContent='${applyOTDetail.PARENTDEPTNAME}' zhContent='${applyOTDetail.PARENTDEPTNAME}'/></td>
          <!-- 岗位职务 -->
          <td   height="50" colspan="2" align="center"><div  height="50" ><strong><ait:message messageID="display.mutual.post" /></strong></div></td>
          <td colspan="4"  align="center"><ait:content enContent='${applyOTDetail.ENGLISHPOST}' zhContent='${applyOTDetail.POST}'/></td>
        </tr>
        <tr>
          <td   height="50" align="center" > <div  height="50"><strong>
         <ait:message messageID="display.mutual.request_date" module="ess"></ait:message>
         <!-- 申请时间 --> </strong></div></td>
          <td   colspan="2"  align="center"><c:out value='${applyOTDetail.CREATE_DATE}'/></td>
          <td   height="50" align="center" colspan="2"><div  height="50"><strong>
          <ait:message messageID="display.ess.attendance_request.ot_request.overtime_type" module="ess"></ait:message>   
         <!--  加班类型--></strong></div></td>
          <td  colspan="4"  align="center">
        <ait:content enContent='${applyOTDetail.APPLY_OT_EN_TYPE}' zhContent='${applyOTDetail.APPLY_OT_TYPE}'></ait:content>
        </td>    
        </tr>                   
        <tr>              
        
          <td   height="50" align="center" colspan="1"><div  height="50"><strong>
             <ait:message messageID="display.ess.review.overtime.period" module="ess"></ait:message>
         <!-- 加班时段 --> </strong></div></td>
          <td colspan="8"  align="center"><c:out value='${applyOTDetail.TIME_AREA}'/></td>     
        </tr>
        <tr>  
          <td   height="50" rowspan="9" align="center"><div  height="50"><strong>
          <ait:message messageID="display.mutual.work_narrative" module="ess"></ait:message>   
         <!--工作内容  --></strong></div></td>
          <td colspan="8" rowspan="9"  align="center"><c:out value='${applyOTDetail.APPLY_OT_REMARK}'/></td>
        </tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
         <tr></tr>
        <tr></tr>
		<tr>
          <td   height="50" align="center"><div  height="50"><strong>
             <ait:message messageID="display.mutual.notes" module="ess"></ait:message>
         <!-- 备注 --> </strong></div></td>
          <td colspan="8" ><c:out value='${applyOTDetail.REMARK}'/></td>
        </tr>
        <tr>
          <td   height="50" rowspan="4" align="center"><div  height="50"><strong>所属部门</strong></div></td>
           <td   height="50" align="center" colspan="2"><div ><strong>起案
         </strong></div></td>
           <td   height="50"  align="center" colspan="2"><div ><strong>调整 
           </strong></div></td>     
           <td   height="50" align="center" colspan="2"><div ><strong>确认
           </strong></div></td>
           <td   height="50" align="center" colspan="2"><div ><strong>决裁
        </strong></div></td>  
        </tr>
         <tr>
           <td   height="50" colspan="2" rowspan="3"></td>
           <td   height="50" colspan="2" rowspan="3"></td>
           <td   height="50" colspan="2" rowspan="3"></td>
           <td   height="50" colspan="2" rowspan="3"></td>    
        </tr> 
         <tr>
        </tr>  
         <tr>
        </tr>           
      </table>  
	  </td>
  </tr>
   <tr  height="50">
    <td colspan="8">             
	    <table width="100%">
	      <tr>     
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	    		 <td   height="630" rowspan="6">&nbsp;</td>
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	    		 <td   height="60" rowspan="6">&nbsp;</td>   
	    		 <td   height="60" rowspan="6">&nbsp;</td>
	     </tr>       
	     <tr></tr>
	     <tr></tr>    
	    </table>    	
    </td>
  </tr> 
    <tr  height="50">  
    <td colspan="8">             
	    <table width="100%">
	      <tr>
	       <td  rowspan="6">&nbsp;</td>
	       <td  rowspan="6">&nbsp;</td> 
	       <td  rowspan="6">&nbsp;</td>
	       <td  rowspan="6">&nbsp;</td>  
	       <td  rowspan="6">&nbsp;</td>  
         <td align="right" rowspan="3" colspan="4">
         <img src=<%=url%>>&nbsp;&nbsp;</td> 
        </tr> 
        <tr></tr>   
        <tr></tr>                        
        <tr></tr>     
	    </table>    	
    </td>
  </tr> 
</table>
</body>
</html>