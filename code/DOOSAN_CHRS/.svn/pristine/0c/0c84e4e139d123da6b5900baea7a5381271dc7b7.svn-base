<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.ar.bean.*,com.ait.ar.entity.*,com.ait.util.*,java.util.*,java.text.*,java.util.Date"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<jsp:useBean id="statistic_daily_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="statistic_daily" class="com.ait.ar.com.ait.gm.DateBean.bean.StatisticsDaily" />
<jsp:useBean id="annualHoliday" class="com.ait.ar.entity.AnnualHoliday" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>考勤日统计</title>
</head>

<%@ include file="../ess/esstoolbar.jsp"%>
<%@ include file="../hr/hr_diaoling_toolbar.jsp"%>
<!-- workflow:  1.get a employee by search engine 
				2.select year(in case the month is the last month of previous year) and month
				3.if statistic_monthly exists, then show the information
-->
<% 
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrent = StringUtil.substring(sDate, 4);
	 int thisYear = Integer.parseInt(yearCurrent);
	 int yearStart = thisYear - 8;
	 
     String y = request.getParameter("year")!=null?request.getParameter("year"):StringUtil.substring(sDate, 4);
	 String m = request.getParameter("month")!=null?request.getParameter("month"):sDate.substring(5,7);
%>	
<div align="left">
<span class="title1">ESS >  考勤查看</span></div>
<form action="essControlServlet" method="post" name="searchForm">
	<input type="hidden" name="operation" value="view">
	<input type="hidden" name="content" value="attendance">
	<input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>"/>
 <table width="100%" height="42" border="0" cellspacing="0" cellpadding="0">
   <tr>
   <td width="41" align="center" class="info_title_01">时间:</td>
   <td width="79"  class="info_title_01"><select name="year">
     <option>请选择年</option>
     <%
		  	  for (int i = yearStart,j=0; j <12; j++,i++){
		  %>
     <option value="<%=i%>" <%if(y.equals(Integer.toString(i))){%> selected <%}%>><%=i%></option>
     <%}%>
   </select></td>
   <td width="79"  class="info_title_01"><select name="month">
     <option>请选择月</option>
     <option value="01" <%if(m.equals("01")){%> selected <%}%>>01</option>
     <option value="02" <%if(m.equals("02")){%> selected <%}%>>02</option>
     <option value="03" <%if(m.equals("03")){%> selected <%}%>>03</option>
     <option value="04" <%if(m.equals("04")){%> selected <%}%>>04</option>
     <option value="05" <%if(m.equals("05")){%> selected <%}%>>05</option>
     <option value="06" <%if(m.equals("06")){%> selected <%}%>>06</option>
     <option value="07" <%if(m.equals("07")){%> selected <%}%>>07</option>
     <option value="08" <%if(m.equals("08")){%> selected <%}%>>08</option>
     <option value="09" <%if(m.equals("09")){%> selected <%}%>>09</option>
     <option value="10" <%if(m.equals("10")){%> selected <%}%>>10</option>
     <option value="11" <%if(m.equals("11")){%> selected <%}%>>11</option>
     <option value="12" <%if(m.equals("12")){%> selected <%}%>>12</option>
   </select></td>
   <td width="100"  class="info_title_01"><input name="submit" type="submit" value="查找" class="search"></td>
   <td width="673"  class="info_title_01">&nbsp;</td>
   </tr>
  </table>
</form>

  <%if(statistic_daily_list.size()>0){%>

</p>

<span class="title1">日考勤统计：</span>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">

<tr align="center">
  <td bgcolor="#F5F5F5">选择</td>
    <td bgcolor="#F5F5F5">日期</td>
    <td height="30" bgcolor="#F5F5F5">工作日状态</td>        
    <td bgcolor="#F5F5F5">考勤情况</td>
    <td bgcolor="#F5F5F5">上班刷卡时间</td>
	<td bgcolor="#F5F5F5">下班刷卡时间</td>      
	<td bgcolor="#F5F5F5">加班时间(小时)</td>
  </tr>
<% 
	Vector leave_type_vector  = new Vector();
	try{
		leave_type_vector = SysCodeParser.getCode("LeaveTypeCode");
	}catch(Exception e){
	}
	for (int i=0;i<statistic_daily_list.size();i++){
		statistic_daily = (StatisticsDaily) statistic_daily_list.get(i);
%>
<tr align="center">
  <td><span class="info_content_01"><%=statistic_daily.getAttendanceDate().substring(5,10)%></span>&nbsp;</td>
  <td height="30"><span class="info_content_01"><%=statistic_daily.getWorkDateStatus()%></span>&nbsp;</td>
    <td><%=statistic_daily.getAttendanceStatus()%>&nbsp;
	  </td>
    <td><span class="info_content_01"><%=statistic_daily.getOndutyTime()%></span>&nbsp;</td>
    <td><span class="info_content_01"><%=statistic_daily.getOffDutyTime()%></span>&nbsp;</td>
	<td class="info_content_01"><%=statistic_daily.getOverTime()%>&nbsp;</td>

  </tr>
<%}%>
</table>

<table width="970"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="33%" valign="top" class="pad_10"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
<br>
          <td class="title1">
<span class="title1">年假统计：</span></td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="1" class="title_line_01"></td>
                    </tr>
                    <tr>
                      <td height="2" class="title_line_02"></td>
                    </tr>
                    <tr>
                      <td height="1"></td>
                    </tr>
                    <tr>
                      <td class="line"><table width="87%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
                          <tr>
                            <td width="38%" height="30" bgcolor="#F5F5F5">本年年假天数</td>
                            <td width="62%" height="30" class="info_content_01"><%=annualHoliday.getHolidayDays()%>&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="30" bgcolor="#F5F5F5">已用年假</td>
                            <td height="30" class="info_content_01"><%=annualHoliday.getUsedHoliday()%>&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="30" bgcolor="#F5F5F5">现有年假</td>
                            <td height="30" class="info_content_01"><%=annualHoliday.getRemainIngHoliday()%>&nbsp;</td>
                          </tr>
                      </table></td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
    <td width="67%" valign="top" class="pad_10"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="97%" class="hrtitle">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="1" class="title_line_01"></td>
                    </tr>
                    <tr>
                      <td height="2" class="title_line_02"></td>
                    </tr>
                    <tr>
                      <td height="1"></td>
                    </tr>
                    <tr>
                      <td class="line">&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
  </tr>
</table>
<div align="left"><%}%></p>


</body>
</html>

