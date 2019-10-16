<%@ page pageEncoding="UTF-8"  import="java.util.*,com.ait.util.StringUtil,com.ait.ga.bean.*"%>
<%@ include file="/inc/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="wpConfirm" scope="page" class="com.ait.gm.cmd.WpConfirm"/>
<jsp:useBean id="wpb" class="com.ait.ga.bean.WoodProductsBean"/>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=WoodProducts.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
 %>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="font-size:9pt;FONT-FAMILY:宋体;font-weight:900">
<tr align="center"><td align="center" colspan="8" rowspan="2"><font style="font-size:14pt">制品清单</font></td></tr>
</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="FONT-FAMILY:宋体;font-size:9pt;">
		   <tr align="center" bgcolor="#F5F5F5" style="font-size:14pt">  
			  		  	
		    </tr>
		    <c:forEach items="${confirmList}" var="test" varStatus="i">
		    <tr align="center">	
		    <td  align="center">职号</td>	   
		    <td  align="center">${test.EMPID}</td>
		    </tr>	
		    <tr>
		     <td  align="center">	姓名</td>	    
		    <td  align="center">${test.CHINESENAME}</td>	
		    </tr>	
		    <Tr>    
		    <td align="center">部门</td>	
		    <td  align="center">${test.DEPTNAME}</td>	
		    </tr>	
		    <tr>
		    <td  align="center">完成日期</td>	    
		    <td  align="center">${test.HOPE_COMPLETED_DATE}</td>
		    </tr>
		    <tr>
		     <td  align="center">	用途</td>		    
		    <td  align="center">${test.NOTE}</td>	
		    </tr>
		    <tr>
		    <td  align="center">制品</td>
		    <td nowrap="nowrap" align="left">
			    <table style="FONT-FAMILY:宋体;font-size:9pt;">
				    <c:forEach items="${test.woodProductsList}"  var="wpMap" varStatus="j"> 
				    <tr>
				    <td nowrap="nowrap">${j.index+1}</td>
				    <td nowrap="nowrap">${wpMap.WPNAME}</td>
				    <td nowrap="nowrap">${wpMap.WPNUMBER}个</td>
				    <td nowrap="nowrap">长:${wpMap.WP_L}厘米</td>
				    <td nowrap="nowrap">宽:${wpMap.WP_W}厘米</td>
				    <td nowrap="nowrap">高:${wpMap.WP_H}厘米</td>				    
				    <td nowrap="nowrap">价格:${wpMap.WP_SUMPRICE}元</td>		    
				    </tr>
				    </c:forEach>
			    </table>
		    </td>
		    </tr>		   
		    </c:forEach>		   
		 </table>

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="font-size:9pt;FONT-FAMILY:宋体">
<tr><td rowspan="4">管理部签字:</td><td  colspan="7" rowspan="4">&nbsp;</td></tr>
</table>
