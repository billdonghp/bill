<%@ page language="java" import="java.util.*,com.ait.util.*,com.ait.hrm.entity.EmpDistribution,com.ait.hrm.bean.Department" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>  
<jsp:useBean id="rd" class="com.ait.web.ReportDAO"></jsp:useBean>
    <%     
		
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_distributionreport.xls");  
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");    
		
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
		
		String beginYear=request.getParameter("beginYear");  
		String endYear=request.getParameter("endYear");
		String beginMonth=request.getParameter("beginMonth");
		String endMonth=request.getParameter("endMonth");
		String bym=beginYear+beginMonth;
		String eym=endYear+endMonth;
		Vector vmonth=rd.getMonths(bym,eym);
		String monthstr[]=new String[vmonth.size()];
		for (int x=0;x<vmonth.size();x++)
			monthstr[x]=(String)vmonth.get(x);                   
		                             
		Vector datalist=new Vector();
		EmpDistribution sr=new EmpDistribution();
     %>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr align="center" >  <td  rowspan="2" align="center"  colspan="<%=(vmonth.size())*(3)+6 %>">  <%=beginYear %>.<%=beginMonth %>--<%=endYear %>.<%=endMonth %><!-- 人员配置及分布情况表  -->
          <ait:message  messageID="report.hrm.organize.structure.report1" module="report"/>   </td>      </tr>
      </table>
     <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     	
     <%------------------------------ 表头 ----- -----------------------------%>
                                                     
     	<tr>
     	    <td align="center"    rowspan="3"><!-- 部门 -->
     	    <ait:message  messageID="report.hrm.organize.structure.dept" module="report"/>
     	    </td>
     		<td align="center"    rowspan="3"><!-- 科室 -->
     		<ait:message  messageID="report.hrm.organize.structure.section.office" module="report"/>
     		</td>
     		<td align="center"    rowspan="3"><!-- 区分 -->
     		<ait:message  messageID="report.hrm.organize.structure.division" module="report"/>
     		</td>                              
     		<% 
     		for(int i=1;i<=vmonth.size();i++) 
     		{	%>
	   		<td align="center"  rowspan="2"  colspan="3"><%=(String)vmonth.get(i-1) %>  
	   		<%-- 
	   		<%=((String)vmonth.get(i-1)).substring(0,4) %>.<%=((String)vmonth.get(i-1)).trim().substring(4,6)%>--%>
	   		</td>     
	   		<% }%>
     	     <td align="center"    rowspan="2"  colspan="3"><!-- 期间内增减对比 -->
     	     <ait:message  messageID="report.hrm.organize.structure.VarianceinPeriod" module="report"/>
     	     </td>        
   		 </tr>
   		  <tr>     
   		<tr>                            
   		<%       
   		for(int i=0;i<monthstr.length+1;i++)           
     		{	%>            
	   		<td align="center" ><!-- 男 --><ait:message  messageID="report.hrm.sex.distribute.male" module="report"/></td>                 
	   		<td align="center"><!--女  --><ait:message  messageID="report.hrm.sex.distribute.female" module="report"/></td>
	   		<td align="center" ><!-- 小计 --><ait:message  messageID="report.hrm.sex.distribute.small" module="report"/></td>   
	   		<% }%>                     
   		</tr>             
   <%---------------------------------------------------------------------- --%> 
     
     <tr>
           <td align="center"  colspan="2"><!-- 高管 -->
           <ait:message  messageID="report.hrm.organize.structure.President" module="report"/>
           </td>
           <%
           datalist= rd.getEmpDistributionInfo("Z000000_",monthstr);
   			%>
           <td align="center" ><!-- 部小计 -->
        <ait:message  messageID="report.hrm.organize.structure.office" module="report"/>   
           </td>
           <%
         for(int c=0;c<datalist.size();c++)
         {  
        	 sr=(EmpDistribution)datalist.get(c);                
   			%>
           <td align="center"><%=sr.getMalecount() %></td>
   			<td align="center"><%=sr.getFemalecount() %></td>
   			<td align="center"><%=sr.getCount() %></td>
   		 <%} %>
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
						datalist = rd.getEmpDistributionInfo(threeDept.getDeptID(),monthstr);
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
   			    <%
		         for(int c=0;c<datalist.size();c++)
		         {  
		        	 sr=(EmpDistribution)datalist.get(c);      
		   			%>
		           <td align="center"><%=sr.getMalecount() %></td>
		   			<td align="center"><%=sr.getFemalecount() %></td>
		   			<td align="center"><%=sr.getCount() %></td>
		   		 <%} %>
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
   			     <%
		         for(int c=0;c<datalist.size();c++)
		         {  
		        	 sr=(EmpDistribution)datalist.get(c);      
		   			%>
		           <td align="center"><%=sr.getMalecount() %></td>
		   			<td align="center"><%=sr.getFemalecount() %></td>
		   			<td align="center"><%=sr.getCount() %></td>
		   		 <%} %>
	   	</tr>
			<%				
						}else{
			%>
		<tr>
				<td align="center">
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>		
				</td>
   			    <%
		         for(int c=0;c<datalist.size();c++)
		         {  
		        	 sr=(EmpDistribution)datalist.get(c);      
		   			%>
		           <td align="center"><%=sr.getMalecount() %></td>
		   			<td align="center"><%=sr.getFemalecount() %></td>
		   			<td align="center"><%=sr.getCount() %></td>
		   		 <%} %>
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
					datalist = rd.getEmpDistributionInfo(twoDept.getDeptID(),monthstr);
			%>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   				 <%
		         for(int c=0;c<datalist.size();c++)
		         {  
		        	 sr=(EmpDistribution)datalist.get(c);      
		   			%>
		           <td align="center"><%=sr.getMalecount() %></td>
		   			<td align="center"><%=sr.getFemalecount() %></td>
		   			<td align="center"><%=sr.getCount() %></td>
		   		 <%} %>
   		</tr>	
			<%
        		}
        		datalist = rd.getEmpDistributionInfo(oneDept.getDeptID(),monthstr);            
        	%>	
        <tr>
   		<td colspan="2"  align="center"><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>  
		   			 <%
		         for(int c=0;c<datalist.size();c++)
		         {  
		        	 sr=(EmpDistribution)datalist.get(c);      
		   			%>
		           <td align="center"><%=sr.getMalecount() %></td>
		   			<td align="center"><%=sr.getFemalecount() %></td>
		   			<td align="center"><%=sr.getCount() %></td>
		   		 <%} %>
   		</tr>			
        <%		
        	}
        %>
   		<tr>
   			<td colspan="3" align="center"><!-- 合计 -->
   			<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/>
   			</td>  
   			<%
   			datalist = rd.getEmpDistributionInfo("Z000000",monthstr);
   			 %>
   			 <%
         for(int c=0;c<datalist.size();c++)
         {  
        	 sr=(EmpDistribution)datalist.get(c);      
   			%>
           <td align="center"><%=sr.getMalecount() %></td>
   			<td align="center"><%=sr.getFemalecount() %></td>
   			<td align="center"><%=sr.getCount() %></td>
   		 <%} %>
   		</tr>
   		 <tr  height="50"> 
   		 <td colspan="<%=(vmonth.size())*(3)+6 %>">
   		 <table> 
   		 <tr>
   		 <% 
   		 for(int c=1;c<(vmonth.size())*(3);c++)
   		 {
   			%>
   			 <td></td>
   			<% 
   		 }
   			%>
         <td align="right"><img src=<%=url%>></td>   
         </tr>
         </table>
         </td>  
         </tr> 
    </table>
