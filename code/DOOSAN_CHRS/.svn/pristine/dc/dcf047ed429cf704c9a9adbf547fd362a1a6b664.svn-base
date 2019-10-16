<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<title>考勤查看&gt;个人休假</title>
</head>
<SCRIPT type="text/javascript">

function Save()
{

	document.form1.action="/arControlServlet?operation=ar_vacation_emp_update&menu_code=${param.menu_code}";
	document.form1.fireSubmit(); 
}
function paseValueToAmount(value){   
            if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
	                    if(decimalIndex=='-1'){   
	                        return false;   
	                    }else{   
	                        var decimalPart=value.substring(decimalIndex+1);   
	                       // alert(decimalPart);
	                        if(Number(decimalPart)>10){   
	                            return true;   
	                        }else{   
	                            return false;   
	                        }   
	                    }   
            }   
                return false;   
 }                       
</SCRIPT>
<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%> 
			
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
		
		<!-- display content -->
		<br>
		<form name="form1" method="post" action="">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!--个人休假记录-->
					<ait:message  messageID="display.att.view.vacation.record" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		     <tr>
		      <td width="15%" class="info_title_01"><!--工号-->
					<ait:message  messageID="display.mutual.emp_id"/></td>
		      <td width="85%" class="info_content_00">${result.EMPID}(<ait:content enContent="${result.CHINESE_PINYIN}" zhContent="${result.CHINESENAME}" koContent="${result.KOREANNAME}"/>)</td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!--工作时间-->工作时间</td>
		      <td width="85%" class="info_content_00"><input type="text" size="10" maxlength="10" name="work_year" value="${result.WORK_YEAR}" /></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!--休假类型-->
					<ait:message  messageID="display.att.setting.dayoff.type" module="ar"/></td>
		      <td width="85%" class="info_content_00">
		      <ait:content enContent="${result.VAC_TP_EN}" zhContent="${result.VAC_TP_CN}" koContent="${result.VAC_TP_KOR}"/>&nbsp;</td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!--休假ID-->
					<ait:message  messageID="display.att.view.dayoff.cycle" module="ar"/></td>
		      <td width="85%" class="info_content_00">${result.VAC_ID}</td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
		      <td class="info_content_00">${result.STRT_DATE}</td></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
		      <td class="info_content_00">${result.END_DATE}</td></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--休假天数-->
					<ait:message  messageID="display.att.view.vacation.days" module="ar"/></td>
		      <td class="info_content_00"><input name="tot_vac_cnt" type="text" size="10" value="${result.TOT_VAC_CNT}" maxlength="10"  onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"/></td>
		    </tr>
		  </table>
		  <input type="hidden" name="vacation_no" value="${result.VACATION_NO}"/>
		</form>

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
</form>
</body>
<ait:xjos />
</html>