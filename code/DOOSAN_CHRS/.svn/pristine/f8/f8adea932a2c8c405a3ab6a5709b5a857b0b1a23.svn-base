<%@ page language="java" import="java.util.*,com.ait.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<jsp:useBean id="rd" class="com.ait.web.ReportDAO"></jsp:useBean>
<jsp:useBean id="sr" class="com.ait.hrm.entity.EmpContrastBean"></jsp:useBean>
 
    <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_newcontrastreport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
     %>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr align="center" >  <td  rowspan="2" align="center"  colspan="8">     
        <!--组织人员结构-----组织人员现况月对比表-->
        	<ait:message  messageID="report.hrm.employee.month.title.name" module="report"/>
                </td>      </tr>
      </table>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     <%------------------------------ 表头 ----- -----------------------------%>
     	<tr>
     	    <td align="center"    rowspan="2"><!--                部门    -->
        	<ait:message  messageID="report.hrm.organize.structure.dept" module="report"/>
        	</td>
     		<td align="center"    rowspan="2"><!--                科室   --> 
        	<ait:message  messageID="report.hrm.organize.structure.section.office" module="report"/></td>
     		<td align="center"    rowspan="2"><!--                区分    -->
        	<ait:message  messageID="report.hrm.organize.structure.division" module="report"/></td>
     		<td align="center"    rowspan="2" ><!-- 上月末人数  -->
        	<ait:message  messageID="report.hrm.employee.month.last.count" module="report"/></td>
     		<td align="center"    rowspan="2"><!-- 本月录用  -->
        	<ait:message  messageID="report.hrm.employee.month.hire" module="report"/></td>  
     		<td align="center"    rowspan="2"><!-- 本月离职  -->
        	<ait:message  messageID="report.hrm.employee.month.dimission" module="report"/></td>  
     		<td align="center"    rowspan="2"><!-- 本月末人数  -->
        	<ait:message  messageID="report.hrm.employee.month.count" module="report"/></td>  
     		<td align="center"    rowspan="2"><!-- 增减变动  -->
        	<ait:message  messageID="report.hrm.employee.month.change" module="report"/></td>  
   		</tr>
   		  <tr>
   		 </tr>
   <%---------------------------------------------------------------------- --%>
         <tr>
           <td align="center"  colspan="2"><!--                高管 -->
        	<ait:message  messageID="report.hrm.organize.structure.President" module="report"/></td>
           <%
   				sr = rd.getEmpContrast("Z000000_");
   			 %>
            <td align="center" ><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>
            <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
        </tr>
        
   		<tr>
   			<td rowspan="12"  align="center"><!--                管理部 -->
        	<ait:message  messageID="report.hrm.organize.structure.Management.Department" module="report"/></td>
   			<td rowspan="4"  align="center"><!--                人事总务科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Personnel.Section" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("G201000");
   			 %>
        	<td align="center"><!--                总务 -->
        	<ait:message  messageID="report.hrm.organize.structure.General.Affairs" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>		                                    
   			<%
   				sr = rd.getEmpContrast("G202000");
   			 %>
   			<td align="center"><!--                法务 -->
        	<ait:message  messageID="report.hrm.organize.structure.Law.Affairs" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("G203000");
   			 %>
   			<td align="center"><!--                人事 -->
        	<ait:message  messageID="report.hrm.organize.structure.Human.Resource" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
     	<tr>
   			<%
   				sr = rd.getEmpContrast("G200000");
   			 %>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center"><!--                企划科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Planning.Section" module="report"/></td>
   			<td  align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("G400000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		<td rowspan="3"  align="center"><!--                财务科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Fiscal.Section" module="report"/></td>
   		 
   			<%
   				sr = rd.getEmpContrast("G101000");
   			 %>
   			<td align="center"><!--                资金 -->
        	<ait:message  messageID="report.hrm.organize.structure.Financing" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
     	<tr>
   			<%
   				sr = rd.getEmpContrast("G102000");
   			 %>
   			<td align="center"><!--                会计 -->
        	<ait:message  messageID="report.hrm.organize.structure.Accounting" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("G100000");
   			 %>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		
   		<tr>
   		<td rowspan="3"  align="center"><!--                采购科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Purchase.Section" module="report"/></td>
   		 <td align="center"><!--                采购1科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Purchase.Section.one" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("G301000");  
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
     	<tr>
     	<td align="center"><!--                采购2科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Purchase.Section.two" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("G302000");
   			 %>
   		     <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("G300000");
   			 %>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		<td colspan="2"  align="center"><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("G000000");
   			 %>  
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
  

   		<tr>  
   			<td rowspan="18"  align="center"><!--                生产部 -->
        	<ait:message  messageID="report.hrm.organize.structure.Manufacturing.Department" module="report"/></td>
   			<td rowspan="3"  align="center"><!--                生产管理科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Production.Management.Section" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S301000");
   			 %>
   			<td align="center"><!--                生产企划 -->
        	<ait:message  messageID="report.hrm.organize.structure.Production.Planning" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		
   			<%
   				sr = rd.getEmpContrast("S302000");
   			 %>
   			<td align="center"><!--                成本管理 -->
        	<ait:message  messageID="report.hrm.organize.structure.Cost.Management" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("S300000");
   			 %>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
     	<td rowspan="3"  align="center"><!--                生产科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Production.Section" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S201000");
   			 %>
   			<td align="center"><!--                巧克力车间 -->
        	<ait:message  messageID="report.hrm.organize.structure.Chocolate.Workshop" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("S202000");
   			 %>
   			<td align="center"><!--                可可车间 -->
        	<ait:message  messageID="report.hrm.organize.structure.Cocoa.Workshop" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("S200000");
   			 %>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		<td rowspan="4"  align="center"><!--           工务科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Workshop.Affairs.Section" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S401000");
   			 %>
   			<td align="center"><!--      工务企划 -->
        	<ait:message  messageID="report.hrm.organize.structure.Workshop.Affairs.&.Planning" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center"><!-- 动力车间 -->
        	<ait:message  messageID="report.hrm.organize.structure.Power.Engineering.Workshop" module="report"/>
        	</td>
   			<%
   				sr = rd.getEmpContrast("S402000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		
   			<%
   				sr = rd.getEmpContrast("S403000");
   			 %>
   			<td align="center"><!-- 维修 -->
        	<ait:message  messageID="report.hrm.organize.structure.Maintenance" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<%
   				sr = rd.getEmpContrast("S400000");
   			 %>
   			<td align="center"><!-- 科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
       <td rowspan="3"  align="center"><!-- 品质管理科 -->
        	<ait:message  messageID="report.hrm.organize.structure.QM.Section" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S101000");
   			 %>
   			<td align="center"><!-- QC -->
        	<ait:message  messageID="report.hrm.organize.structure.QC" module="report"/></td>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr >
   			<td  align="center"><!-- QA -->
        	<ait:message  messageID="report.hrm.organize.structure.QA" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S102000");
   			 %>
   		  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		
   		<tr >
   			<td  align="center"><!-- 科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S100000");
   			 %>  
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		<td rowspan="4"  align="center"><!-- 仓库物流科 -->
        	<ait:message  messageID="report.hrm.organize.structure.storage.Logistics" module="report"/></td>
   			<td  align="center"><!-- 原辅料仓库 -->
        	<ait:message  messageID="report.hrm.organize.structure.Material.Warehouse" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S501000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center"><!-- 成品仓库 -->
        	<ait:message  messageID="report.hrm.organize.structure.Finished.Products.Warehouse" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S502000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center"><!-- 物流 -->
        	<ait:message  messageID="report.hrm.organize.structure.Logistics" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S503000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center"><!-- 科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("S500000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td colspan="2" align="center"><!-- 部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>  
   			<%
   				sr = rd.getEmpContrast("G000000"); 
   			 %>
   		  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   		  <td rowspan="4"></td>
   		  <td align="center"  rowspan="3"><!-- 营销科 -->
        	<ait:message  messageID="report.hrm.organize.structure.Sales.Section" module="report"/></td>  
   			<td align="center"><!-- 国内 -->
        	<ait:message  messageID="report.hrm.organize.structure.Inport" module="report"/></td>  
   			<%
   				sr = rd.getEmpContrast("Y101000");
   			 %>
   		  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		
   		<tr>
   			<td  align="center"><!-- 国外 -->
        	<ait:message  messageID="report.hrm.organize.structure.Export" module="report"/></td>  
   			<%
   				sr = rd.getEmpContrast("Y102000");
   			 %>
   		  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center"><!-- 科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>  
   			<%
   				sr = rd.getEmpContrast("Y600000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>
   			<td  align="center" colspan="2"><!-- 部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>  
   			<%
   				sr = rd.getEmpContrast("S000000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
   		<tr>                 
   			<td colspan="3" align="center"><!-- 合计 -->
        	<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/></td>
   			<%
   				sr = rd.getEmpContrast("Z000000");
   			 %>
   			  <td align="center"><%=sr.getPrevMonthCount() %></td>
   			<td align="center"><%=sr.getThisMonthIn() %></td>
   			<td align="center"><%=sr.getThisMonthOut() %></td>
   			<td align="center"><%=sr.getThisMonthCount() %></td>
   			<td align="center"><%=sr.getDiff() %></td>
   		</tr>
    </table>
