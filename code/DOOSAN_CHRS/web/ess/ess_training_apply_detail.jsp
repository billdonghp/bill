<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请</title>
</head>
<body>
   <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=training.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
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
	    	 <ait:message messageID="display.ess.attendance_request.training_request_report" module="ess"></ait:message>
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
   <tr align="center"> 
    <td	width="100%"><table width="100%" border="1"
			align="center" cellpadding="0" cellspacing="0"
			style="padding: 2px 2px 2px 2px;border-collapse:collapse">
        <tr>
        
          <td  height="50" align="center" ><div  height="50"><strong>
         <ait:message messageID="display.ess.confirmation.personal_info.request_no" module="ess"></ait:message>
        <!--  申请号--> </strong></div></td>
          <td   colspan="2" align="center"><c:out value='${applyLeaveDetail.LEAVE_NO}'/></td>
          
          <td   height="50" colspan="2" align="center"><div align="center"><strong>
            <ait:message messageID="display.mutual.name"  module="ess"></ait:message>
         <!--  姓名 --></strong></div></td>
       <td   colspan="4" align="center">
         <ait:content enContent='${applyLeaveDetail.CHINESE_PINYIN}'  zhContent='${applyLeaveDetail.CHINESENAME}'></ait:content>  
          </td>  
         
        </tr>              
        <tr>
          <td  height="50" align="center" ><div align="center"><strong><!-- 部门 -->
               <ait:message messageID="display.mutual.department"  ></ait:message>
          </strong></div></td>            
         <td colspan="2"  height="50"  align="center">
          <ait:content enContent='${applyLeaveDetail.ENDEPT}'  zhContent='${applyLeaveDetail.DEPTNAME}'></ait:content>
          </td>
           <td   height="50" colspan="2" align="center"><div align="center"><strong><!--  岗位职务 -->
       <ait:message messageID="display.mutual.post" ></ait:message>   
         </strong></div></td>
        <td colspan="4"  align="center">
           <ait:content enContent=' ${applyLeaveDetail.POSTENNAME}'  zhContent='${applyLeaveDetail.POST}'></ait:content>    
         </td>
        </tr>
        <tr>
        <td   height="50" align="center" ><div align="center" ><strong><!-- 申请时间  -->
        <ait:message messageID="display.mutual.request_date"  module="ess"></ait:message>        
         </strong></div></td>
         <td   colspan="2"  align="center">${applyLeaveDetail.CREATE_DATE}</td>
        <td  colspan="2"  align="center"><div align="center"><strong><!--培训类型  -->
        <ait:message messageID="display.ess.attendance_request.training_request.training_type"  module="ess"></ait:message>          
          </strong></div></td>
           <td  colspan="4"  align="center">
      <ait:content enContent='${applyLeaveDetail.LEAVE_TYPE_EN_NAME}'  zhContent='${applyLeaveDetail.LEAVE_TYPE_NAME}'></ait:content>      
       </td>
        </tr>
        <tr>
         <td height="50" align="center"><div align="center"><div align="center"><strong><!-- 培训时段 -->
       <ait:message messageID="display.ess.review.training.period" module="ess" ></ait:message>        
          </strong></div></td>
        <td colspan="8"  nowrap>${applyLeaveDetail.LEAVE_FROM_TIME}~~${applyLeaveDetail.LEAVE_TO_TIME}</td>
        </tr>
		<tr>
          <td  height="50" rowspan="9" align="center"><div align="center"><strong><!-- 培训原因 -->
      <ait:message messageID="display.ess.approval.training.reason"  module="ess"></ait:message>         
          </strong></div></td>
          <td colspan="8" rowspan="9"  align="center">${applyLeaveDetail.LEAVE_REASON}</td>                        
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
          <td   height="50" align="center"><div  height="50"><div align="center"><strong><!-- 备注 -->
         <ait:message messageID="display.mutual.notes" module="ess"></ait:message>      
          </strong></div></td>
         <td colspan="8" align="center">${applyLeaveDetail.REMARK}</td>
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