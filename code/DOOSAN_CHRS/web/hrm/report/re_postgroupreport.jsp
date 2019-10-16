<%@ page language="java" import="java.util.*,com.ait.util.*,com.ait.hrm.bean.Department" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<jsp:useBean id="rd" class="com.ait.web.ReportDAO"></jsp:useBean>   
<jsp:useBean id="sr" class="com.ait.hrm.entity.PostGroupReport"></jsp:useBean>
 
    <%
      String joinTypeCode=request.getParameter("joinTypeCode");    
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=re_postgroupreport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
     %>
       <table	border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
        <tr align="center" >  
        <td  rowspan="2" align="center"  colspan="9">     
        	<!-- 组织人员结构-----职群分布    -->
        	<ait:message  messageID="report.hrm.group.distribute.title.name" module="report"/>
        </td>      
        </tr>
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
     		<td align="center"    rowspan="2"  colspan="5"><!--                职群分布情况    -->
        	<ait:message  messageID="report.hrm.group.distribute.state" module="report"/></td>   
            <td align="center"    rowspan="3"><!--                合计    -->
        	<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/></td>
   		</tr>
   		  <tr>
   		 </tr>
   		<tr>
   		  <td align="center" ><!--                生产职  -->
        	<ait:message  messageID="report.hrm.organize.structure.Manufacture" module="report"/></td>
	   		<td align="center" ><!--                管理职  -->
        	<ait:message  messageID="report.hrm.organize.structure.Management" module="report"/></td>
        	<td align="center" ><!--                技术职  -->
        	<ait:message  messageID="report.hrm.organize.structure.Technic" module="report"/></td>
	   		<td align="center" ><!--                营业职  -->
        	<ait:message  messageID="report.hrm.organize.structure.Sale" module="report"/></td>
	   		<td align="center"><!--                其他职  -->
        	<ait:message  messageID="report.hrm.organize.structure.Other" module="report"/></td>     
   		</tr>            
   <%---------------------------------------------------------------------- --%>
         <tr>
           <td align="center"  colspan="2"><!--                高管 -->
        	<ait:message  messageID="report.hrm.organize.structure.President" module="report"/></td>
           <%
   				sr = rd.getEmpPostGroupInfo("Z000000_",joinTypeCode);
   			 %>
            <td align="center" ><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>
            <td align="center"><%=sr.getPG1() %></td>
   			<td align="center"><%=sr.getPG2() %></td>
   			<td align="center"><%=sr.getPG3() %></td>
   			<td align="center"><%=sr.getPG4() %></td>
   			<td align="center"><%=sr.getPG5() %></td>
   			<td align="center"><%=sr.getCount()%></td>  
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
						sr = rd.getEmpPostGroupInfo(threeDept.getDeptID(),joinTypeCode);     
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
   			        <td align="center"><%=sr.getPG1() %></td>
		   			<td align="center"><%=sr.getPG2() %></td>              
		   			<td align="center"><%=sr.getPG3() %></td>
		   			<td align="center"><%=sr.getPG4() %></td>
		   			<td align="center"><%=sr.getPG5() %></td>
		   			<td align="center"><%=sr.getCount()%></td>
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
   			        <td align="center"><%=sr.getPG1() %></td>
		   			<td align="center"><%=sr.getPG2() %></td>              
		   			<td align="center"><%=sr.getPG3() %></td>
		   			<td align="center"><%=sr.getPG4() %></td>
		   			<td align="center"><%=sr.getPG5() %></td>
		   			<td align="center"><%=sr.getCount()%></td>
	   	</tr>
			<%				
						}else{
			%>
		<tr>
				<td align="center">
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>		
				</td>
		   			 <td align="center"><%=sr.getPG1() %></td>
		   			<td align="center"><%=sr.getPG2() %></td>              
		   			<td align="center"><%=sr.getPG3() %></td>
		   			<td align="center"><%=sr.getPG4() %></td>
		   			<td align="center"><%=sr.getPG5() %></td>
		   			<td align="center"><%=sr.getCount()%></td>
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
					sr = rd.getEmpPostGroupInfo(twoDept.getDeptID(),joinTypeCode);
			%>
   			<td align="center"><!--                科小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>
   			 <td align="center"><%=sr.getPG1() %></td>
   			<td align="center"><%=sr.getPG2() %></td>              
   			<td align="center"><%=sr.getPG3() %></td>
   			<td align="center"><%=sr.getPG4() %></td>
   			<td align="center"><%=sr.getPG5() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   		</tr>	
			<%
        		}
        		sr = rd.getEmpPostGroupInfo(oneDept.getDeptID(),joinTypeCode);            
        	%>	
        <tr>
   		<td colspan="2"  align="center"><!--                部小计 -->
        	<ait:message  messageID="report.hrm.organize.structure.office" module="report"/></td>  
   			 <td align="center"><%=sr.getPG1() %></td>
   			<td align="center"><%=sr.getPG2() %></td>              
   			<td align="center"><%=sr.getPG3() %></td>
   			<td align="center"><%=sr.getPG4() %></td>
   			<td align="center"><%=sr.getPG5() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   		</tr>			
        <%		
        	}
        %>
        
   		<tr>
   			<td colspan="3" align="center"><!-- 合计 -->
        	<ait:message  messageID="report.hrm.organize.structure.sum" module="report"/></td>
   			<%
   				sr = rd.getEmpPostGroupInfo("Z000000",joinTypeCode);
   			 %>
   			 <td align="center"><%=sr.getPG1() %></td>
   			<td align="center"><%=sr.getPG2() %></td>              
   			<td align="center"><%=sr.getPG3() %></td>
   			<td align="center"><%=sr.getPG4() %></td>
   			<td align="center"><%=sr.getPG5() %></td>
   			<td align="center"><%=sr.getCount()%></td>
   		</tr>
   		 <tr  height="50"> 
   		 <td colspan="9">
   		 <table> 
   		 <tr>
   		 <td></td>
   		 <td></td>
   		 <td></td>
   		 <td></td>
         <td align="right" colspan="4"><img src=<%=url%>></td> 
         </tr>
         </table>
         </td>  
         </tr> 
    </table>
