<%@ page contentType="text/html; charset=UTF-8"  import="java.util.*,com.ait.sqlmap.util.*,com.ait.util.StringUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加班日报表</title>
</head>                              
<body>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=dailyOtReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");   
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
		
     %>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr align="center" height="60">
    <td colspan="2">             
	    <table width="100%">  
	    	<tr>
	    		<td align="center" colspan="5"><b><font size="+2"><!--  日别加班统计表--><ait:message messageID="report.title.name" module="report"></ait:message>
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
         <td width="16%"  align="center"><!-- 姓名 --> <ait:message messageID="report.global.title.name" module="report"></ait:message></td>
         <td width="16%"  align="center"><!-- 工号 --><ait:message messageID="report.global.title.empID" module="report"></ait:message></td>
         <td width="16%"  align="center"><!-- 部门 --><ait:message messageID="report.global.title.deptName" module="report"></ait:message> </td>
         <td width="16%"  align="center"><!-- 员工类别 --><ait:message messageID="report.mutual.title.staff_class" module="report"></ait:message> </td>
         <td width="16%"  align="center"><!-- 加班时间 --> <ait:message messageID="report.global.title.overtime" module="report"></ait:message>
         (<ait:message messageID="report.global.title.hour" module="report"></ait:message>)
         </td>
        </tr>                    
        <%               
        List datalist= (List)request.getAttribute("datalist");
        int num=0;
        if(datalist.size()!=0)
        {
        num=new Integer(((SimpleMap)datalist.get(0)).getString("RECODEMOUNT")).intValue();
        }
        int maxsize=datalist.size();
        for(int i=0;i<datalist.size();i++)
        {	   
        	SimpleMap map=(SimpleMap)datalist.get(i);
            if(i+1==num)
            {
             if(maxsize!=num)       
        	num=new Integer(((SimpleMap)datalist.get(num)).getString("RECODEMOUNT")).intValue()+num;
      %>         
       <tr>
        <td width="16%" align="center"><ait:content enContent="<%=StringUtil.checkNull(map.getString("CHINESE_PINYIN"))%>" 
        zhContent="<%=StringUtil.checkNull(map.getString("CHINESENAME"))%>">
        </ait:content> </td>  
         <td width="16%"  align="center"><%=map.getString("EMPID")%> &nbsp;</td>
          <td width="16%"  align="center">
          <ait:content enContent="<%=StringUtil.checkNull(map.getString("DEPT_EN_NAME"))%>" 
        zhContent="<%=StringUtil.checkNull(map.getString("DEPTNAME"))%>"></ait:content></td>
           <td width="16%"  align="center">
            <ait:content enContent="<%=StringUtil.checkNull(map.getString("CODE_EN_NAME"))%>" 
        zhContent="<%=StringUtil.checkNull(map.getString("CODE_NAME"))%>"></ait:content></td>
            <td width="16%"  align="center"><%=map.getString("QUANTITY")%> </td>
        </tr>
        <tr>
        <td width="16%"  align="center" colspan="2"><ait:message messageID="report.global.title.total" module="report"></ait:message><!-- 小计 --></td>
         <td width="16%"  align="center" colspan="2"><%=map.getString("RECODEMOUNT")%><!-- 人 -->
         <ait:message messageID="report.global.title.person" module="report"></ait:message>
         </td>
          <td width="16%"  align="center" ><%=map.getString("DEPTTOTALOTMOUNT")%> </td>
        </tr>
      <% 
         }else              
         {
        	%>
        	 <tr>
       <td width="16%" align="center"><ait:content enContent="<%=StringUtil.checkNull(map.getString("CHINESE_PINYIN"))%>" 
        zhContent="<%=StringUtil.checkNull(map.getString("CHINESENAME"))%>">
        </ait:content> </td>  
         <td width="16%"  align="center"><%=map.getString("EMPID")%> &nbsp;</td>
          <td width="16%"  align="center">
          <ait:content enContent="<%=StringUtil.checkNull(map.getString("DEPT_EN_NAME"))%>" 
        zhContent="<%=StringUtil.checkNull(map.getString("DEPTNAME"))%>"></ait:content></td>
           <td width="16%"  align="center">
            <ait:content enContent="<%=StringUtil.checkNull(map.getString("CODE_EN_NAME"))%>" 
        zhContent="<%=StringUtil.checkNull(map.getString("CODE_NAME"))%>"></ait:content></td>
            <td width="16%"  align="center"><%=map.getString("QUANTITY")%> </td>
        </tr> 
        	<%   
         }
        }  
        %>                
      </table>  
	  </td>
  </tr>
  <tr>
  	<td>
  		<table>
  			<tr>
  			</tr>
  			<tr>
  				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td align="right"><img src=<%=url%>></td>
  			</tr>
  		</table>
  	</td>
  </tr>
</table>
</body>
</html>