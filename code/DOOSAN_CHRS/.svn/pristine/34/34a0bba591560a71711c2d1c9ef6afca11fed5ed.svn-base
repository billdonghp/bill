<%@ include file="../inc/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.sysparam.EssSysparam"%>
<%@ page import="com.ait.utils.FormUtil"%>
<%@ page import="com.ait.sy.bean.*"%>
<jsp:useBean id="sysparam" class="com.ait.sysparam.EssSysparam" scope="request" />
<html>
<head>
<title>ess参数设置</title>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<script type="text/javascript">
function Save() {
	document.SysparamForm.operation.value="modify";
	document.SysparamForm.submit();
}
function Search() {
	document.SysparamForm.content.value="essparamSearch";
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
		<input type="hidden" name="content" value="essparam" />
		<input type="hidden" name="menu_code" value="sy0204" />   
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
		           <td class="info_title_05" nowrap="nowrap"><!-- 是否自动向下一级决裁者发邮件 -->  
		            ESS系统是否自动向下一级决裁者发邮件
		           </td>
		          <td class="info_content_00" width="5%"></td>
		          <td class="info_content_00" width="30%"><%= FormUtil.BoolSelect(language,"autoSendMail", sysparam.isAutoSendMail()) %></td>    
		          </tr>    
		          <tr>            
		           <td class="info_title_05" nowrap="nowrap"><!-- 是否自动向下一级决裁者发邮件 -->  
		            行政管理是否自动向下一级决裁者发邮件
		           </td>
		          <td class="info_content_00" width="5%"></td>
		          <td class="info_content_00" width="30%"><%= FormUtil.BoolSelect(language,"gaSendMail", sysparam.isGaSendMail()) %></td>    
		          </tr>               
			      <tr>            
		           <td class="info_title_05" nowrap="nowrap">
		             加班最终决裁后是否自动进行人事确认
		           </td>
		          <td class="info_content_00" width="5%"></td>
		          <td class="info_content_00" width="30%"><%=FormUtil.BoolSelect(language,"otAutoConfirm", sysparam.isOtAutoConfirm())%></td>    
		          </tr> 
		          <tr>            
		           <td class="info_title_05" nowrap="nowrap">
		             休假最终决裁后是否自动进行人事确认
		           </td>
		          <td class="info_content_00" width="5%"></td>
		          <td class="info_content_00" width="30%"><%=FormUtil.BoolSelect(language,"leaveAutoConfirm", sysparam.isLeaveAutoConfirm())%></td>    
		          </tr> 
		          <tr>  
		            <td class="info_title_05"　nowrap="nowrap"><!-- 人事是否可以提前进行确认 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.confirms_earlier" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"preConfirm", sysparam.isPreConfirm())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!-- 人事是否可以在确认后重新确认 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.confirmation_valid" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"reConfirm", sysparam.isReConfirm())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">
		              加班决裁是否可反悔
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"otModifiedAfterAffirm", sysparam.isOtModifiedAfterAffirm())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap">
		              休假决裁是否可反悔
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"leaveModifiedAfterAffirm", sysparam.isLeaveModifiedAfterAffirm())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!--  加班申请是否执行始末时间先后检查-->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.ot_check_start_end" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkOtApplyStartEndTime", sysparam.isCheckOtApplyStartEndTime())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!-- 加班申请是否执行加班时间与加班时间冲突检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.ot_check_overtimes" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkOtApplyOtConflict", sysparam.isCheckOtApplyOtConflict())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!-- 加班申请是否执行加班时间与班次时间冲突检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.ot_check_time_shift" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkOtApplyShiftConflict", sysparam.isCheckOtApplyShiftConflict())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!-- 加班申请是否执行加班时间与休假时间冲突检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.ot_check_work_dayoff" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkOtApplyLeaveConflict", sysparam.isCheckOtApplyLeaveConflict())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!--加班申请是否执行加班时间与值班时间冲突检查  -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.ot_check_work_nighduty" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkOtApplyMatchConflict", sysparam.isCheckOtApplyMatchConflict())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!-- 加班可申请多少天前的加班(-1表示不限制) -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.delay_to_ot" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.IntInput("otApplyDaysBefore", sysparam.getOtApplyDaysBefore())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"><!-- 加班可申请多少天后的加班(-1表示不限制) -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.request_ot_inadvance" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.IntInput("otApplyDaysAfter", sysparam.getOtApplyDaysAfter())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05"　nowrap="nowrap"> <!-- 加班类型手工数据与自动取得值判断标志<br>-1:取手工提交值;&nbsp;0:比对检查;&nbsp;1:取自动判断值; -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.identify_initial_data" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.IntInput("otApplyTypeCheckFlag", sysparam.getOtApplyTypeCheckFlag())%></td>
		          </tr>
		          <tr>            
		            <td class="info_title_05" nowrap="nowrap"><!-- 加班申请是否执行加班上限检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.ceiling_ot_month" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"otApplyMaxHours", sysparam.getOtApplyMaxHours())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05" nowrap="nowrap"><!-- 休假始末时间前后检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.start_end_daysoff" module="sys"></ait:message>
		            </td>  
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkLeaveApplyStartEndTime", sysparam.isCheckLeaveApplyStartEndTime())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05" nowrap="nowrap"><!-- 是否执行休假与加班冲突检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_dayfof_ot" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkLeaveApplyOtConflict", sysparam.isCheckLeaveApplyOtConflict())%></td>
		          </tr>
		          <tr>                   
		            <td class="info_title_05" nowrap="nowrap"><!--是否执行休假与休假冲突检查  -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_overlap_daysoff" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkLeaveApplyLeaveConflict", sysparam.isCheckLeaveApplyLeaveConflict())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05" nowrap="nowrap"><!-- 是否执行休假与值班冲突检查 -->  
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_dayoff_nightduty" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkLeaveApplyMatchConflict", sysparam.isCheckLeaveApplyMatchConflict())%></td>
		          </tr>
		          <tr>           
		            <td class="info_title_05" nowrap="nowrap"="nowrap="nowrap""> 
		              
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_nightduty_time" module="sys"></ait:message>
		            </td> 
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkMatchApplyStartEndTime", sysparam.isCheckMatchApplyStartEndTime())%></td>
		          </tr> 
		          <tr>
		            <td class="info_title_05" nowrap="nowrap"><!-- 是否执行值班与加班冲突检查 -->
		              <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_nightduty_ot" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkMatchApplyOtConflict", sysparam.isCheckMatchApplyOtConflict())%></td>
		          </tr>
		          <tr>
		            <td class="info_title_05" nowrap="nowrap"><!-- 是否执行值班与休假冲突检查 -->
		              <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_nightduty_dayoff" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkMatchApplyLeaveConflict", sysparam.isCheckMatchApplyLeaveConflict())%></td>
		          </tr>
		          <tr>   
		            <td class="info_title_05" nowrap="nowrap"><!-- 是否执行值班与值班冲突检查 -->
		            <ait:message messageID="display.sys.parameter_setting.ess_parameter.check_nigyhtduties" module="sys"></ait:message>
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"checkMatchApplyMatchConflict", sysparam.isCheckMatchApplyMatchConflict())%></td>
		          </tr>
		          <tr>   
		            <td class="info_title_05" nowrap="nowrap"><!--  取决裁者时是否包含自己 -->
		             取决裁者时是否包含自己
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"containsOwner", sysparam.isContainsOwner())%></td>
		          </tr>
		          <tr>   
		            <td class="info_title_05" nowrap="nowrap"><!--  加班修改是否可以通过权限控制 -->
		             加班修改是否可以通过权限控制
		            </td>
		            <td class="info_content_00" width="5%"></td>
		            <td class="info_content_00"><%=FormUtil.BoolSelect(language,"otUpdateControl", sysparam.isOtUpdateControl())%></td>
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
