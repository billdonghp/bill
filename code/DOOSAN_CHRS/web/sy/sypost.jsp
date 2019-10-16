<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../inc/taglibs.jsp"%>  
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
<SCRIPT language=JavaScript src="../js/post.js"></SCRIPT>                                                            
</head>                                   
<script language="javascript">
MENU_CODE='${param.menu_code}';
function Search()  
{
  document.searchForm.submit();
}
</script>                                                                                                                                                 
<body>                                                  

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
		<%@ include file="../inc/sy_common_toolbar.jsp" %> 
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

		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" height="30" border="0" cellspacing="0"
			cellpadding="0">
			<form action="syControlServlet" method="post" name="searchForm"
				onkeydown="if(event.keyCode==13) searchForm.submit();"><jodd:form
				fromRequest="true">
				<input type="hidden" name="operation" value="viewpostcmd">    
				<input type="hidden" name="menu_code"
					value="<c:out value='${param.menu_code}'/>" />  
					  
				<tr>
    				<td class="title1"   align="left"><!-- 查询条件 -->
    					<ait:message messageID="display.mutual.search_criteria" ></ait:message>
    				</td>  
                    
				<tr>     
					<td>
						<table width="100%" height="30"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<td  class="info_title_01" nowrap><!--职位名称  -->
								<ait:message messageID="display.mutual.position" ></ait:message>
							</td> 
			          		<td class="info_content_00">
			          		<ait:select  dataListName="post_list"  name="post_id"   all="ALL"  />  
							</td>
							<td class="info_title_01" nowrap>  
		                  <c:import url="../inc/common_toolbar_s.jsp" /></td>  
						</tr>  
						</table>  
					</td>
				</tr>
			</jodd:form></form>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--  岗位职务-->
				<ait:message messageID="display.mutual.post" ></ait:message>	
				</td>
			</tr>
		</table>                                                         
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
		      <td  height="30"  class="info_title_01"  width="15%"><!-- 岗位职务编号 -->
		     	<ait:message messageID="display.mutual.no" ></ait:message> 
		      </td>
			  <td  height="30"  class="info_title_01"  width="15%"><!-- 岗位职务ID -->
			<ait:message messageID="display.mutual.id" ></ait:message>  
			  </td>
			  <td  height="30" class="info_title_01"  width="15%"><!--  岗位职务名-->
			  	<ait:message messageID="display.name_chinese" ></ait:message>
			  </td>  
		      <td  height="30"  class="info_title_01"  width="15%"><!--  岗位职务英文名-->
		    	<ait:message messageID="display.name_english" ></ait:message>  
		      </td>
		      <td  height="30"  class="info_title_01"  width="15%"><!-- 隶属职群 -->  
		    	<ait:message messageID="display.sys.salary.group.job_group"  module="sys"></ait:message>  
		      </td>     
			</tr>                   
			<c:forEach items="${postlist}" var="VCtr" varStatus="status"> 
			<tr bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','${VCtr.POST_ID}')">
				<td height="30" align="center" style="color: #666666;" nowrap><c:out value="${VCtr.POST_NO}"/></td>  
				<td align="center" style="color: #666666;" nowrap>${VCtr.POST_ID}</td>              
				<td align="center" style="color: #666666;" nowrap><c:out value="${VCtr.POST_NAME}"/></td>
				<td align="center" style="color: #666666;" nowrap><c:out value="${VCtr.POST_EN_NAME}"/></td>  
				<td align="center" style="color: #666666;" nowrap><ait:content enContent="${VCtr.POST_GROUP_EN_NAME}" zhContent="${VCtr.POST_GROUP_NAME}" koContent="${VCtr.POST_KOR_NAME}"/>&nbsp;</td>    
			</tr>  
			</c:forEach>  
			<c:if test="${fn:length(postlist) <7}">
						<c:forEach var="i" begin="1" end="${7-fn:length(postlist)}" step="1">
							<tr>
								<td class="info_content_01" height="30"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>
								<td class="info_content_01"></td>  
							</tr>
						</c:forEach>
					</c:if>                
		</table>   
          	
	<ait:page       
         link="/syControlServlet"
         parameters="&operation=viewpostcmd&menu_code=${param.menu_code}&post_id=${param.postid}" 
         total="${resultCount}"
         currentpage="${currentPage}"
         pagesize= "${pageSize}"
         beginlabel="paging_prv10"  
         endlabel="paging_next10"  
         prevlabel="paging_prv"                   
         nextlabel="paging_next"
         pagegroupsize="${pageGroupsize}"
         useJS="false"
/>                           
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
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
                           
</body>
</html>