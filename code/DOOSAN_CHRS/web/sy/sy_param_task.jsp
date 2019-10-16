<%@ include file="../inc/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.sysparam.TaskSysparam"%>
<%@ page import="com.ait.utils.FormUtil"%>
<%@ page import="com.ait.sy.bean.*"%>
<jsp:useBean id="sysparam" class="com.ait.sysparam.TaskSysparam" scope="request" />
<html>
<head>
<title>任务参数设置</title>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<script type="text/javascript">
function Save() {
	document.SysparamForm.operation.value="modify";
	document.SysparamForm.submit();
}
function Search() {
	document.SysparamForm.content.value="taskparamSearch";
	document.SysparamForm.submit();
}
</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td nowrap="nowrap"="nowrap="nowrap""></td>
		<td nowrap="nowrap" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp" %>
		</td>
		<td  height="33" align="LEFT" valign="TOP" nowrap="nowrap"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td nowrap="nowrap"="nowrap="nowrap""></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		<%@ include file="../sy/inc/sy_toolbar.jsp" %>
		
		<!-- display content -->
				
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<form name="SysparamForm" method="post" action="/syControlServlet">
		  <input type="hidden" name="operation" value="view" />
		  <input type="hidden" name="content" value="taskparam" />
		  <input type="hidden" name="menu_code" value="sy0205" />
		  <input type="hidden" name="description" value="<%=sysparam.getDescription()%>" />
		  <input type="hidden" name="paramName" value="<%=sysparam.getParamName()%>" />
		  <table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
		    <td align="center" class="info_content_01" nowrap="nowrap">
			   法人区分&nbsp;:&nbsp; 
			    <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}"/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>	        
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
		    </td>
	        </tr>
	      </table>	 
		    <tr>
		      <td class="line" nowrap="nowrap">               
		   <table width="100%" height="121"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
		          <%
				   String language=admin.getLanguagePreference();
			     %>    
			     
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">发邮件任务 延迟时间(毫秒) -1:不发送邮件
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("mailDelay", sysparam.getMailDelay())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">发邮件任务 间隔时间(毫秒)
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("mailPeriod", sysparam.getMailPeriod())%></td>
		          </tr>
		         
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">部门备份任务 延迟时间(毫秒) -1:不进行备份
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("deptDelay", sysparam.getDeptDelay())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">部门备份任务 间隔时间(毫秒)
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("deptPeriod", sysparam.getDeptPeriod())%></td>
		          </tr>
		          
		           <tr>
		            <td class="info_title_05"　nowrap="nowrap">刷卡任务 延迟时间(毫秒) -1:不自动读取
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("cardDelay", sysparam.getCardDelay())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">刷卡任务 间隔时间(毫秒)
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("cardPeriod", sysparam.getCardPeriod())%></td>
		          </tr>
		             
		           <tr>
		            <td class="info_title_05"　nowrap="nowrap">ERP接口数据传输任务 延迟时间(毫秒) -1:不自动读取
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("erpDelay", sysparam.getErpDelay())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">ERP接口数据传输任务 间隔时间(毫秒)
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("erpPeriod", sysparam.getErpPeriod())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">就餐接口数据传输任务 延迟时间(毫秒) -1:不自动读取
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("eateryDelay", sysparam.getEateryDelay())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">就餐接口数据传输任务 间隔时间(毫秒)
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("eateryPeriod", sysparam.getEateryPeriod())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">生成决裁Mail任务 延迟时间(毫秒) -1:不自动读取
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("affirmMailDelay", sysparam.getAffirmMailDelay())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">生成决裁Mail任务 间隔时间(毫秒)
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00" width="30%"><%=FormUtil.IntInput("affirmMailPeriod", sysparam.getAffirmMailPeriod())%></td>
		          </tr>
		            
		        </table></td>
		    </tr>         
		</form>      
		</table>

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

</body>
</html>
