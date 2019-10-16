<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.DateUtil"%>
<%@ page import="com.ait.ess.bean.EssOverTimeBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="overTimeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essOverTimeBean" class="com.ait.ess.bean.EssOverTimeBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head> 
<body>
<script language="javascript1.5" type="text/javascript">
function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}
function Delete(otno) {
var  msg ='<ait:message messageID="alert.ess.common.delete" module="ess"></ait:message>';
	if (confirm(msg)) {  
		document.ApplyForm.operation.value="delete";
		document.ApplyForm.otTopLimitNo.value=otno;
		document.ApplyForm.submit();  
	}
}
</script> 
<form name="ApplyForm" action="/essControlServlet" method="post">
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="15"></td>
			<td width="11" height="33" valign="TOP" align="RIGHT"><img
				src="../images/tablbk01_r1_c1.gif"></td>
			<td background="../images/tablbk01_r1_c2.gif">
			
				<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_search.jsp"%>
			</td>
			<td width="10" height="33" align="LEFT" valign="TOP"><img
				src="../images/tablbk01_r1_c26.gif"></td>
			<td width="11"></td>
		</tr>
		<tr>
			<td width="15"></td>
			<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
			<td valign="TOP" align="CENTER">
			
			<!-- display basic info -->
			<br>
			<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="title1"><!-- 查询条件 -->
		<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>		
				</td>
			</tr>
		    <tr>        
		      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		        <tr>
		          <td  class="info_title_01" nowrap="nowrap">加班月份</td>
		          <td  class="info_content_00" nowrap="nowrap"><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" /></td>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap"><ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}" /></td>                  
		        </tr>      
		        </table>
		      </td>
			</tr>
			</table>
			
			<!-- display 3 level menu -->
			<%@ include file="../inc/thirdToolBar.jsp"%>
		
			<!-- display content -->
			<br>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">			
					加班上限信息</td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			  <tr>
			    <td class="info_title_01" nowrap>序号</td>
			   	<td class="info_title_01" nowrap><!--工号  --> 
			   	<ait:message messageID="display.mutual.emp_id"></ait:message></td>
				<td class="info_title_01" nowrap><!--加班人  --> 
				<ait:message messageID="display.ess.confirmation.overtime.employee" module="ess"></ait:message>
				</td>
			   <td class="info_title_01" nowrap><!-- 部门 --> <ait:message
					messageID="display.mutual.department"></ait:message></td>
				<td class="info_title_01" nowrap><!--  职位--> <ait:message
					messageID="display.mutual.position"></ait:message></td>
				<td class="info_title_01" nowrap>加班月份</td>	
				<td class="info_title_01" nowrap><!--  申请日期--> <ait:message
					messageID="display.mutual.request_date" module="ess"></ait:message>
				</td>
				<td class="info_title_01" nowrap>原有上限</td>
				<td class="info_title_01" nowrap>申请小时</td>
				<td class="info_title_01" nowrap>加班事由</td>
			    <td class="info_title_01" nowrap><!--  决裁情况-->
			 <ait:message
					messageID="display.mutual.status_2" module="ess"></ait:message>   
			    </td>
			    <td class="info_title_01" nowrap><!--  删除-->
			       <ait:message messageID="display.mutual.delete" module="ess"></ait:message>
			    </td>
			  </tr>
			<%for(int i=0;i<overTimeList.size();i++){
				essOverTimeBean = (EssOverTimeBean) overTimeList.get(i);
			%>
			  <tr align="center">
			    </td>
			    <td class="info_content_01" nowrap><%= i + 1 %></td>
			    <td class="info_content_01" nowrap><%=essOverTimeBean.getEmpId()%></td>
			    <td class="info_content_01" nowrap>
	    <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getChinesePinYin()) %>'  
	       zhContent= '<%=StringUtil.checkNull(essOverTimeBean.getChineseName())%>'  
	       koContent='<%=StringUtil.checkNull(essOverTimeBean.getKoreanname()) %>'>        
	       </ait:content>&nbsp;
			   </td>
			    <td class="info_content_01" nowrap>
			     <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getDeptEnName()) %>'  
	       zhContent= '<%=StringUtil.checkNull(essOverTimeBean.getDeptName())%>'  
	       koContent='<%=StringUtil.checkNull(essOverTimeBean.getDeptKorName())%>'>   
	       </ait:content>&nbsp;	    
			    </td>
			    <td class="info_content_01" nowrap>
			     <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getEnPosition())%>'  
	       zhContent= '<%=StringUtil.checkNull(essOverTimeBean.getPosition())%>'  
	       koContent='<%=StringUtil.checkNull(essOverTimeBean.getKorPosition())%>'>   
	       </ait:content>&nbsp;	 
			   </td>
			    <td class="info_content_01" nowrap><%= essOverTimeBean.getOtPlanMonth() %></td>
			    <td class="info_content_01" nowrap><%=essOverTimeBean.getCreateDate()%></td>
			    <td class="info_content_01" nowrap><%= StringUtil.checkNull(essOverTimeBean.getLimit_ot(),"&nbsp;")%></td>
				<td class="info_content_01" nowrap><%= essOverTimeBean.getOtLength() %></td>
			    <td align="center" nowrap><textarea name="" style=" height: 20px;width:200px " type="_moz"  readonly="readonly" onfocus="this.style.height='40px'" onblur="this.style.height='20px'"><%= StringUtil.checkNull(essOverTimeBean.getOtRemark(),"&nbsp;")  %></textarea></td>
			    <td align="center" nowrap>                              
				    <%
				    essAffirmorList = essOverTimeBean.getAffirmorList();
				    if (essAffirmorList.size() > 0) {
					    for(int j=0;j<essAffirmorList.size();j++){
					    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
					    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
				    		<%=essAffirmor.getAffirmorName() + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
					    	</font><br>
					    <%}
					} else {%>
						&nbsp;
					<%}%>
		    </td>
		    <td class="info_content_01" nowrap>
	   		<%if (essOverTimeBean.getOpFlag() == 3) {%>
	    		<span style="color:red; cursor:pointer;" onClick="Delete(<%=essOverTimeBean.getOtNo() %>);">
	    		<ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand" title="删除" enTitle="delete" /></span>
			<%} else out.print("&nbsp;");%>
			</td>
		  </tr>
		<%}%>
		</table>
		
					  <!-- Page Navigation Start-->
					<ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=ottoplimitaffirm&menu_code=${param.menu_code}&year=${year}&month=${month}&deptID=${deptID}&key=${key}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="15">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
			<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
			<td width="11"></td>
		</tr>
		<tr>
			<td width="15"></td>
			<td width="11" height="5" align="LEFT" valign="TOP"><img
				src="../images/tablbk01_r14_c1.gif"></td>
			<td bgcolor="#569DD1" height="5"></td>
			<td width="10" height="5" align="LEFT" valign="TOP"><img
				src="../images/tablbk01_r14_c26.gif"></td>
			<td width="11"></td>
		</tr>
	</table>

<input type="hidden" name="operation" value="view" />
<input type="hidden" name="otTopLimitNo" value="" />
<input type="hidden" name="content" value="ottoplimitview" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="currentPage" value="${currentPage}" />
</form>
</body>
</html>
