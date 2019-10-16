<%@ page language="java" import="java.util.*,com.ait.util.*,com.ait.hrm.bean.Department" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<jsp:useBean id="rd" class="com.ait.web.ReportDAO"></jsp:useBean>
<jsp:useBean id="sr" class="com.ait.hrm.entity.EmpStatusReport"></jsp:useBean>
 
    <%
      String joinTypeCode=request.getParameter("joinTypeCode");  
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_empstatusreport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
     %>
      <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr align="center" >  <td  rowspan="2" align="center"  colspan="11">     
        <!--组织人员结构-----员工状态分布 -->
        	<ait:message  messageID="report.hrm.employee.status.title.name" module="report"/>
        
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
     		<td align="center"    rowspan="2"  colspan="6"><!-- 员工状态分布 -->
        	<ait:message  messageID="report.hrm.employee.status.status" module="report"/></td>
     		<td align="center"    rowspan="2" colspan="2">%<!-- 工作状态占有 -->
        	<ait:message  messageID="report.hrm.employee.status.work.status" module="report"/></td>  
   		</tr>
   		  <tr>
   		 </tr>
   		<tr>
	   		<td align="center" ><!-- 实习 -->
        	<ait:message  messageID="report.hrm.employee.status.practise" module="report"/></td>
	   		<td align="center" ><!-- 试用 -->
        	<ait:message  messageID="report.hrm.employee.status.probation" module="report"/></td>          
	   		<td align="center" ><!-- 休职 -->
        	<ait:message  messageID="report.hrm.employee.status.relax" module="report"/></td>
	   		<td align="center"><!-- 待职 -->
        	<ait:message  messageID="report.hrm.employee.status.wait" module="report"/></td>
	   		<td align="center" ><!-- 正式 -->
        	<ait:message  messageID="report.hrm.employee.status.formal" module="report"/></td>
	   		<td align="center" ><!-- 小计 -->
        	<ait:message  messageID="report.hrm.employee.status.samll" module="report"/></td>
	   		<td align="center" ><!-- 工作状态 -->
        	<ait:message  messageID="report.hrm.employee.status.work" module="report"/></td>
	        <td align="center" ><!-- 非工作状态 -->
        	<ait:message  messageID="report.hrm.employee.status.not.work" module="report"/></td>           
   		</tr>
   <%---------------------------------------------------------------------- --%>
         <tr>
           <td align="center"  colspan="2"><!--                高管 -->
        	<ait:message  messageID="report.hrm.organize.structure.President" module="report"/></td>
           <%
   				sr = rd.getEmpStatusInfo("Z000000_",joinTypeCode);
   			 %>
            <td align="center" ><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>
            <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
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
						sr = rd.getEmpStatusInfo(threeDept.getDeptID(),joinTypeCode);
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
   			  <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
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
   			 <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
	   	</tr>
			<%				
						}else{
			%>
		<tr>
				<td align="center">
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>		
				</td>
   			 <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
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
					sr = rd.getEmpStatusInfo(twoDept.getDeptID(),joinTypeCode);
			%>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			 <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
   		</tr>	
			<%
        		}
        		sr = rd.getEmpStatusInfo(oneDept.getDeptID(),joinTypeCode);            
        	%>	
        <tr>
   		<td colspan="2"  align="center"><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>  
   			 <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
   		</tr>			
        <%		
        	}
        %>
   		<tr>
   			<td colspan="3" align="center"><!-- 合计 -->
        	<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/></td>
   			<%
   				sr = rd.getEmpStatusInfo("Z000000",joinTypeCode);
   			 %>
   			 <td align="center"><%=sr.getE1() %></td>
   			<td align="center"><%=sr.getE2() %></td>
   			<td align="center"><%=sr.getE4() %></td>
   			<td align="center"><%=sr.getE5() %></td>
   			<td align="center"><%=sr.getE6() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   			<td align="center"><%=sr.getA() %>%</td>
   			<td align="center"><%=sr.getB() %>%</td>
   		</tr>
   		<tr  height="50"> 
   		 <td colspan="11">
   		 <table> 
   		 <tr>
   		 <td></td>
   		 <td></td>
   		 <td></td>
   		  <td></td>
   		 <td></td>  
   		 <td></td> 
         <td align="right" colspan="3" valign="top"><img src=<%=url%>></td> 
         </tr>
         </table>
         </td>  
         </tr> 
    </table>
