
<%@ page contentType="text/html; charset=UTF-8" import="com.ait.sqlmap.util.SimpleMap,java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>休职信息</title>
</head>                         
<body>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=suspend.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
		  Calendar c = Calendar.getInstance();
		    String strdate=c.get(1) + "年"+(c.get(2) + 1) + "月"+c.get(5) + "日"; 
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
	                  人事发令通报书
	    	 </td>
	    	</tr>
	    	<tr></tr>
	      <tr>     
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
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
    <td><table width="100%">  
       <tr>
        <td  height="50" align="left" colspan="8"><div  height="50"><strong>
                人事令号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${NUMBER}'/>
         </strong></div></td>
        </tr> 
           <tr>   
             <td  height="50" align="left" colspan="8"><div><strong>
                  职员休职人事发令如下：
              </strong>
              </div>
              </td>      
               </tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
         </table>
         </td>
         </tr> 
    <tr>
    <td><table width="100%" border="1"
			align="center" cellpadding="0" cellspacing="0"
			style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
           <tr>
       <td align="center" height="40" >姓名</td>
       <td align="center" height="40" >所属部门</td>
       <td align="center" height="40" >岗位职务</td>
        <td align="center" height="40" >职位</td>
       <td align="center" height="40" >开始日期</td>
       <td align="center" height="40">结束日期</td>
        <td align="center" height="40" colspan="3">休职事由</td>
      </tr>
      
     
             <c:forEach items="${list}" var="temp">   
              <tr>
            <td align="center" height="40">${temp.CHINESENAME }</td>   
             <td align="center" height="40">${temp.DEPTNAME }</td> 
             <td align="center" height="40">${temp.POST }</td>
             <td align="center" height="40">${temp.POSITION }</td>
             <td align="center" height="40">${temp.START_DATE }</td>   
             <td align="center" height="40">${temp.END_DATE }</td>
             <td align="center" height="40" colspan="3">${temp.SUSPEND_REASON }</td>
             </tr> 
             </c:forEach>
        </table>
        </td>
        </tr>
         <tr>
        <td>
        <table width="100%" >  
		    <tr></tr>
	        <tr></tr>
	        <tr></tr>
			</table>
			</td>
		</tr>
        <tr>
        <td><table width="100%" border="1"
			align="center" cellpadding="0" cellspacing="0"
			style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
      
        <tr>
          <td   height="50" rowspan="4" align="center"><div  height="50"><strong>决裁</strong></div></td>
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
         <tr>
         <td  height="50" align="center"  height="40"><div><strong>合意</strong></div></td>
        <td colspan="8" height="40" ></td>
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
         <td align="right" colspan="4">
         <img src=<%=url%>>&nbsp;&nbsp;</td> 
        </tr>   
         </table>    	
    </td>
  </tr> 
  <tr  height="50">  
    <td colspan="8">             
	    <table width="100%">
        <tr>
         <td  align="right" colspan="9"><font size="3"><strong><%=strdate %></strong></font></td>  
        </tr>    
	    </table>    	
    </td>
  </tr> 
</table>
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
	    		<td  height="30" colspan="8" align="center" rowspan="2"><b><font size="+3">
	                  人事发令通报书
	    	 </td>
	    	</tr>
	    	<tr></tr>
	      <tr>     
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
	    		 <td   height="60" rowspan="3">&nbsp;</td>
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
    <td><table width="100%">  
       <tr>
        <td  height="50" align="left" colspan="9"><div  height="50"><strong>
                人事令号:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${NUMBER}'/>
         </strong></div></td>
        </tr> 
           <tr>   
             <td  height="50" align="left" colspan="8"><div><strong>
                  职员休职人事发令如下：
              </strong>
              </div>
              </td>      
               </tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
         </table>
         </td>
         </tr> 
    <tr>
    <td><table width="100%" border="1"
			align="center" cellpadding="0" cellspacing="0"
			style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
        <tr>
       <td align="center" height="40" >姓名</td>
       <td align="center" height="40" >所属部门</td>
       <td align="center" height="40" >岗位职务</td>
        <td align="center" height="40" >职位</td>
       <td align="center" height="40" >开始日期</td>
       <td align="center" height="40">结束日期</td>
        <td align="center" height="40" colspan="3">休职事由</td>
      </tr>
      
     
             <c:forEach items="${list}" var="temp">   
              <tr>
            <td align="center" height="40">${temp.CHINESENAME }</td>   
             <td align="center" height="40">${temp.DEPTNAME }</td> 
             <td align="center" height="40">${temp.POST }</td>
             <td align="center" height="40">${temp.POSITION }</td>
             <td align="center" height="40">${temp.START_DATE }</td>   
             <td align="center" height="40">${temp.END_DATE }</td>
             <td align="center" height="40" colspan="3">${temp.SUSPEND_REASON }</td>
             </tr> 
             </c:forEach>
      
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
           <td align="center" rowspan="3" colspan="4">
            <img src=<%=url%>>&nbsp;&nbsp;</td> 
           <td  rowspan="6">&nbsp;</td>
	       <td  rowspan="6">&nbsp;</td>  
	
        </tr> 
        <tr></tr>   
        <tr></tr>                          
        <tr></tr> 
          <tr>
            <td  colspan="9" align="center" ><font size="3"><strong>总经理：</strong></font></td>  
           </tr> 
               <tr></tr> 
                <tr></tr> 
                 <tr></tr> 
                <tr>
            <td  colspan="9" align="center" ><font size="3"><strong><%=strdate %></strong></font></td>  
           </tr> 
             
	    </table>    	
    </td>
  </tr> 
</table>
</body>
</html>  

