<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<table width="100%" border="0" cellpadding="0">
<form name="positionreport"  method="post">
	<tr  height="25">
		<td align="right">
				<ait:image src="/images/btn/Report.gif"  border="0" align="absmiddle"
          	onclick="showReport('P_EmpId_','hr_positionprove')" style="cursor:hand" />
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
				  
				  <tr>
				    <td width="16%" height="30" nowrap class="info_title_01"><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
				    <td width="16%" nowrap class="info_title_01"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				    <td width="37%" nowrap class="info_title_01"><!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				    <td width="15%" nowrap class="info_title_01"><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				    <td width="16%" nowrap class="info_title_01"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
				  </tr>
			  <c:forEach var="x" begin="1" end="8" step="1">
				  <tr>
				    <td class="info_content_01" nowrap>
				    	<input id="P_EmpId_${x}" name="P_EmpId_${x}" size="8" onKeyUp="SearchContent(this.value,'P_EmpId_${x}');" /></td>
				    <td class="info_content_01" nowrap><div id="name_P_EmpId_${x}"></div></td>
				    <td class="info_content_01" nowrap><div id="dept_P_EmpId_${x}"></div></td>
				    <td class="info_content_01" nowrap><div id="post_P_EmpId_${x}"></div></td>
				    <td class="info_content_01" nowrap><div id="position_P_EmpId_${x}"></div></td>
				  </tr>
			  </c:forEach>
			</table>
		</td>
	</tr>
</form>	
</table>
