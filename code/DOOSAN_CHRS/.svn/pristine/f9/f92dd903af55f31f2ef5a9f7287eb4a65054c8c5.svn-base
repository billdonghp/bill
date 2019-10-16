<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.ar.entity.*,com.ait.ar.bean.*,java.util.*,java.util.Date,java.text.*,com.ait.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<jsp:useBean id="calendar_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="calendar" class="com.ait.ar.entity.CompanyCalendar" scope="page"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ESS > 公社日历</title>
</head>


<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="../ess/esstoolbar.jsp"%>
<%@ include file="../hr/hr_diaoling_toolbar.jsp"%>
<br>
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
<form action="essControlServlet" method="post" name="searchForm">
	<input type="hidden" name="operation" value="view">
	<input type="hidden" name="content" value="calendar">	
	<input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>">
<table width="98%" height="41" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="80" align="center" class="info_title_01">日历期间        </td>
	    <td width="85" class="info_title_01"><select name="year">
          <option>请选择年</option>
          <%
		  	  for (int i = yearStart,j=0; j <12; j++,i++){
		  %>
          <option value="<%=i%>" <%if(y.equals(Integer.toString(i))){%> selected <%}%>><%=i%></option>
          <%}%>
        </select></td>
	    <td width="79" class="info_title_01"><select name="month">
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
	    <td width="709" colspan="2" class="info_title_01"><input type="submit" name="search" value="查找日历"></td>
    </tr>


</table>
</form>
<% if (calendar_list.size()>10){
	calendar = (CompanyCalendar) calendar_list.get(7);
	String date = calendar.getCalendarDate();
	String year = date.substring(0,4);
	String month = date.substring(5,7);
	String[] tdbg ={"#FFFFFF","#FEFED3","#E6E602"};
	String[] daystate = {"平时","周休","节假日"};
	String[] jobflag = {"不工作","工作"};

%>
<table width="95%" align="default" border="0" cellpadding="0" cellspacing="0">	 
  <tr>
	<td  height="20"><span class="titlebg"><strong>LSFC<span class="style1"><font color="#CC0000"><%=calendar.getDepartment()%></font> 日历</span></strong></span></td>
  </tr>
</table>

<table width="95%" align="default" border="0" cellpadding="0" cellspacing="0">

  <tr>
	 <td class="line">
		   <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
                <tr align="center">
				<td height="30" colspan="7" class="titlebg">
				 <table width="100%"  border="0"  cellspacing="0" cellpadding="0">
					<tr>
					 <td align="center" width="24%"><strong>
					 <input type="hidden" name="date" value="<%=calendar.getCalendarDate().substring(0,7)%>">
					   &nbsp; <%=calendar.getCalendarDate().substring(0,7)%>&nbsp;
					 </strong></td>
					</tr>
				 </table>
				</td>                
			  </tr>
			  <tr>
				 <td align="center"  width="100" bgcolor="#F5F5F5" height="30"><font color="#FF0000">星期日</font></td>               
				 <td align="center"  width="100" bgcolor="#F5F5F5">星期一</td>
				 <td align="center"  width="100" bgcolor="#F5F5F5">星期二</td>
				 <td align="center"  width="100" bgcolor="#F5F5F5">星期三</td>
				 <td align="center"  width="100" bgcolor="#F5F5F5">星期四</td>
				 <td align="center"  width="100" bgcolor="#F5F5F5">星期五</td>
				 <td align="center"  width="100" bgcolor="#F5F5F5"><font color="#FF0000">星期六</font></td>
			 </tr>
			 <tr>				  
				  <% 
					for(int i=0;i<calendar_list.size();i++){
						calendar = (CompanyCalendar)calendar_list.get(i);
						if(calendar.getCalendarDate()==null){ 
						
				  %>
							<td class="info_content_01">&nbsp;</td>
				  <% 		continue;
				  		}
						else { 
							if(calendar.getCalendarDay().endsWith("日")){ %>
			 </tr><tr>				
							<%} %>
								
							<td width="110" height="30" bgcolor="<%= tdbg[calendar.getCalendarType()]%>">
							<strong><%=calendar.getCalendarDate().substring(8,10)%></strong>
							<%=daystate[calendar.getCalendarType() ] %>|<%= jobflag[calendar.getWorkFlag()]%>
							</td>
						<%
							}
					 }
					 int day = DateUtil.parseWeekDay(calendar.getCalendarDay());
					 while(day<6){					 
				   %>
				   <td>&nbsp;</td>		
				   <%
				   		day++;
					}
				   %>
			  </tr>
	   </table>
	</td>
  </tr>
</table>

 <%}%>
</body>
</html>
