<%@ page language="java" import="java.util.*,com.ait.util.*,com.ait.hrm.bean.Department" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<jsp:useBean id="rd" class="com.ait.web.ReportDAO"></jsp:useBean>
<jsp:useBean id="sr" class="com.ait.hrm.entity.DimissionReport"></jsp:useBean>
 
    <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_dimission.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
     %>
      <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr align="center" >  <td  rowspan="2" align="center"  colspan="8">    
       <!-- 员工离职率统计表 -->
        	<ait:message  messageID="report.hrm.employee.dimission.title.name" module="report"/>
        
              </td>      </tr>
      </table>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     <%------------------------------ 表头 ----- -----------------------------%>
     	<tr>
     	    <td align="center"    rowspan="3"><!--                部门    -->
        	<ait:message  messageID="report.hrm.organize.structure.dept" module="report"/>
        	</td>
     		<td align="center"    rowspan="3"><!--                科室   --> 
        	<ait:message  messageID="report.hrm.organize.structure.section.office" module="report"/></td>  
     		<td align="center"    rowspan="3"><!--                区分    -->
        	<ait:message  messageID="report.hrm.organize.structure.division" module="report"/></td>
     		<td align="center"    rowspan="2"  colspan="4" ><%=from %><!--至-->
        	<ait:message  messageID="report.hrm.employee.dimission.to" module="report"/><%=to %></td>
     		<td align="center"    rowspan="3"><!--离职率-->
        	<ait:message  messageID="report.hrm.employee.dimission.scale" module="report"/></td>    
   		</tr>
   		  <tr> 
   		 </tr>
   		<tr>
	   		<td align="center" ><!--期初人数-->
        	<ait:message  messageID="report.hrm.employee.dimission.start.count" module="report"/></td>
	   		<td align="center" ><!--期间入社人数-->
        	<ait:message  messageID="report.hrm.employee.dimission.join.count" module="report"/></td>          
	   		<td align="center" ><!--期间离职人数-->
        	<ait:message  messageID="report.hrm.employee.dimission.dimission.count" module="report"/></td>
	   		<td align="center"><!--期末人数-->
        	<ait:message  messageID="report.hrm.employee.dimission.end.count" module="report"/></td>       
   		</tr>
   <%---------------------------------------------------------------------- --%>
         <tr>
           <td align="center"  colspan="2"><!--                高管 -->
        	<ait:message  messageID="report.hrm.organize.structure.President" module="report"/></td>
           <%
   				sr = rd.getDimissionInfo("Z000000_",from,to);
   			 %>
            <td align="center" ><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>
            <td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
        </tr>
   		  <%
        	List oneList = rd.getDeptIdListByParent_dept_id("Z000000") ;//根据顶级部门ID，取出一级部门．
        	
        	for (int i = 0 ; i < oneList.size() ; i ++)        
        	{
        		Department oneDept = (Department)oneList.get(i) ;
        		List twoList = rd.getDeptIdListByParent_dept_id(oneDept.getDeptID()) ;
        		
        		for (int j = 0 ; j < twoList.size() ; j ++)
        		{
        			Department twoDept = (Department)twoList.get(j) ;
        			List threeList = rd.getDeptIdListByParent_dept_id(twoDept.getDeptID()) ;//根据一级部门ID，取出二级部门
        			
					for (int k = 0 ; k < threeList.size() ; k ++)
					{
						Department threeDept = (Department)threeList.get(k) ;    
						sr = rd.getDimissionInfo(threeDept.getDeptID(),from,to);  
						if( j == 0 && k == 0)
						{
			%>
		<tr>
   			<td rowspan="<%= rd.getDeptRowspan(oneDept.getDeptID()) %>"  align="center"><!--管理部 -->
   		<ait:content enContent="<%= StringUtil.checkNull(oneDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(oneDept.getDeptName()) %>" ></ait:content>			
   			</td>		
				<td rowspan="<%= rd.getDeptRowspan(twoDept.getDeptID()) %>"  align="center">
			<ait:content enContent="<%= StringUtil.checkNull(twoDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(twoDept.getDeptName()) %>" ></ait:content>	
				</td>
				<td align="center">                             
			<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>			
				</td>
   			  <td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
	   	</tr>			
			<%				
						}else if ( j != 0 && k == 0 )
						{
			%>
		<tr>
				<td rowspan="<%= rd.getDeptRowspan(twoDept.getDeptID()) %>"  align="center">
		<ait:content enContent="<%= StringUtil.checkNull(twoDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(twoDept.getDeptName()) %>" ></ait:content>		
				</td>
				<td align="center">
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>				
				</td>
   			  <td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
	   	</tr>
			<%				
						}else{
			%>
		<tr>
				<td align="center">
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>		
				</td>
   			 <td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
	   	</tr>					
			<%							
						}												
					}
					if (threeList.isEmpty()){
			%>
		<tr>
				<td  align="center">
				<ait:content enContent="<%= StringUtil.checkNull(twoDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(twoDept.getDeptName()) %>" ></ait:content>
				</td>	
			<%				
					}else{
			%>
		<tr>	
			<%			
					}					
					sr = rd.getDimissionInfo(twoDept.getDeptID(),from,to);
			%>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   		    <td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
   		</tr>	
			<%
        		}
        		sr = rd.getDimissionInfo(oneDept.getDeptID(),from,to);            
        	%>	
        <tr>
   		<td colspan="2"  align="center"><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>  
   			<td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
   		</tr>			
        <%		
        	}
        %>
   		<tr>
   			<td colspan="3" align="center"><!-- 合计 -->
        	<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/></td>
   			<%
   				sr = rd.getDimissionInfo("Z000000",from,to);  
   			 %>
   			<td align="center"><%=sr.getA() %></td>
   			<td align="center"><%=sr.getB() %></td>
   			<td align="center"><%=sr.getC() %></td>
   			<td align="center"><%=sr.getD() %></td>
   			<td align="center"><%=sr.getDimission() %>%</td>
   		</tr>
   		
   		 <tr  height="50"> 
   		 <td colspan="8">
   		 <table> 
   		 <tr>
   		 <td></td>
   		 <td></td>
   		 <td></td>
   		 <td></td>
         <td align="right" colspan="3"><img src=<%=url%>></td> 
         </tr>
         </table>
         </td>  
         </tr> 

    </table>
