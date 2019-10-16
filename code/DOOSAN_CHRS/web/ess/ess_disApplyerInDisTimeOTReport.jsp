<%@ page contentType="text/html; charset=UTF-8" import="com.ait.sqlmap.util.SimpleMap"%>
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
		response.setHeader("Content-Disposition", "attachment; filename=overtimeapllybatch.xls");
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
	    		<td  height="30" colspan="9" align="center" rowspan="2"><b><font size="+3">
	       <ait:message messageID="display.ess.attendance_request.bulk_overtime.bulk_overtime_request_report" module="ess"></ait:message>
	    	 </td>
	    	</tr>
	    	<tr></tr>
	      <tr>     
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="630" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>   
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		    
	     </tr>
	 <tr>  </tr>
     <tr></tr>
	    </table>    	
    </td>
  </tr>
  
  <tr>
    <td><table width="100%" border="1"
			align="center" cellpadding="0" cellspacing="0"
			style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
       <tr>      
        <td  height="50" align="center" colspan="2"><div  height="50"><strong>
         <ait:message messageID="display.ess.confirmation.personal_info.request_no" module="ess"></ait:message>
        <!--  申请号--> </strong></div></td>
          <td   colspan="2" align="center"><c:out value='${APPLY_GROUP_NO}'/></td>
          <td   height="50" align="center"  colspan="2"> <div  height="50"><strong>
         <ait:message messageID="display.mutual.request_date" module="ess"></ait:message>
         <!-- 申请时间 --> </strong></div></td>
          <td   colspan="3"  align="center"><c:out value='${CREATE_DATE}'/></td> 
        </tr> 
         <tr>   
         <td   height="50" align="center"  colspan="1"> <div  height="50"><strong>
              部门
          </strong></div></td>   
        <td  height="50" align="center" colspan="1"><div  height="50"><strong>
               姓名
        </strong></div></td>                        
          <td  height="50" align="center" colspan="1"><div  height="50"><strong>
             加班类型
        </strong></div></td>   
         <td  height="50" align="center" colspan="2"><div  height="50"><strong>
              时间段
        </strong></div></td>   
          <td   height="50" align="center"  colspan="4"> <div  height="50"><strong>
             工作内容
          </strong></div></td>
        </tr>   
        <c:forEach items="${applyOTDetail}" var="temp">
           <tr>
            <td   colspan="1" align="center" height="40"><c:out value='${temp.PARENTDEPTNAME}'/></td>
            <td   colspan="1" align="center" height="40"><c:out value='${temp.CHINESENAME}'/></td>
             <td   colspan="1" align="center" height="40"><c:out value='${temp.APPLY_OT_TYPE}'/></td>
             <td   colspan="2" align="center" height="40">
               <c:out value='${temp.BEGINTIME}'/><br>
               <c:out value='${temp.ENDTIME}'/>   
              </td>
            <td   colspan="4" align="center" height="40"><c:out value='${temp.CONTENT}'/></td>
           </tr>
        </c:forEach>                 
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
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>
	    		 <td   height="30" rowspan="3">&nbsp;</td>   
	    		 <td   height="30" rowspan="3">&nbsp;</td>
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