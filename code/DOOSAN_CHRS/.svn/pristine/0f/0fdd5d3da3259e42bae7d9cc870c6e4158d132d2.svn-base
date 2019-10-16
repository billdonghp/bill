<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.bean.ArShift020"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="item" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<jsp:useBean id="ArShift020" scope="page" class="com.ait.ar.dao.ArShift020Bean">
</jsp:useBean>
<%
HttpSession session1 = request.getSession(true);
AdminBean admin1 = (AdminBean) session.getAttribute("admin");
ArrayList items=item.getItemList();
request.setAttribute("items",items);
ArItem ar=null;
ArShift020 shift020=null;
int shiftNo=Integer.parseInt(request.getParameter("shiftNo"));
ArrayList shifts=ArShift020.getShift020(shiftNo);
request.setAttribute("shifts",shifts);

ArShift020 tempShift=null;
tempShift=(ArShift020)shifts.get(0);
String pkNOS="";

String check=null;

ArrayList starno=new ArrayList();
starno.add("0");
starno.add("1");
starno.add("2");

ArrayList starname=new ArrayList();
	starname.add("当日");
	starname.add("次日");
	starname.add("三日");
	
ArrayList enstarname=new ArrayList();
	enstarname.add("Same Day");
	enstarname.add("2nd Day");
	enstarname.add("3rd Day");
	
ArrayList datatype1=new ArrayList();
datatype1.add(new Integer(1));
datatype1.add(new Integer(2));

ArrayList datatype2=new ArrayList();
if(admin1.getLanguagePreference().equals("zh")){
	datatype2.add("平常");
	datatype2.add("周末");
}else if(admin1.getLanguagePreference().equals("ko")){
	
}else{
	datatype2.add("Weekday");
	datatype2.add("Weekend");
}


String f=null;
String t=null;
String[] fs=null;
String[] ts=null;


%>
<html>
<head>
<title>班次维护</title>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function addrow()
{
var count=parseInt(document.form1.count.value);
count++;
document.form1.count.value=count;
var htm="";
	for(var i=0;i<count;i++)
	{
		htm+="<table width='100%'  border='1'cellspacing='0' cellpadding='10' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF' style='padding: 2px 2px 2px 2px;'>";
		htm+="<tr>";
		htm+="<td width='15%' class='info_content_00'><ait:message  messageID='display.mutual.start_date_2'/></td>";
		htm+="<td width='15%'><input name='fh"+i+"' type='text' size='2' maxlength='2'>:<input name='fm"+i+"' type='text' size='2' maxlength='2'></td>";
		htm+="<td width='15%' class='info_content_00'><ait:message messageID='display.mutual.end_date_2'/></td>";
		htm+="<td width='15%'><input name='th"+i+"' type='text' size='2' maxlength='2'>:<input name='tm"+i+"' type='text' size='2' maxlength='2'></td>";
		htm+="<td width='15%'><select name='itemNo"+i+"'>";
		htm+="<c:forEach items='${items}' var='list'>";
		htm+="<option value='${list.itemNo}'>";
		htm+="<ait:content enContent='${list.itemEnName}' zhContent='${list.itemName}' koContent='${list.itemKorName}'/>";
		htm+="</option>";
		htm+="</c:forEach>";
		htm+="</select></td>";
		htm+="<td width='25%'>";
		htm+="<table width='100%'  border='0' cellspacing='0' cellpadding='0'>";
		htm+="<tr>";
		htm+="<td class='info_content_00' nowrap><ait:message  messageID='display.att.setting.shift.edit.start' module='ar'/></td>";
		htm+="<td class='info_content_00' style='width:200px'><input type='radio' name='fday"+i+"' value='0' checked><ait:message messageID='display.att.setting.shift.add.same_day' module='ar'/>&nbsp;&nbsp;<input type='radio' name='fday"+i+"' value='1'><ait:message messageID='display.att.setting.shift.add.2nd_day' module='ar'/>";
		htm+="<input type='radio' name='fday"+i+"' value='2'><ait:message messageID='display.att.setting.shift.add.3rd_day' module='ar'/></td>";
		htm+="</tr>";
		htm+="<tr>";
		htm+="<td class='info_content_00' nowrap><ait:message  messageID='display.att.setting.shift.edit.end' module='ar'/></td>";
		htm+="<td class='info_content_00' style='width:200px'><input type='radio' name='tday"+i+"' value='0' checked><ait:message messageID='display.att.setting.shift.add.same_day' module='ar'/>&nbsp;&nbsp;<input type='radio' name='tday"+i+"' value='1'><ait:message messageID='display.att.setting.shift.add.2nd_day' module='ar'/>";
		htm+="<input type='radio' name='tday"+i+"' value='2'><ait:message messageID='display.att.setting.shift.add.3rd_day' module='ar'/></td>";
		htm+="</tr>";
		htm+="</table>";
		htm+="</tr>";
		htm+="</table>";
	}
	document.all.createTable.innerHTML=htm;
}
function Detele(id)
{
  if(<%=shifts.size()%>==1)
  {//"确实要删除次班次最后班次段？"
  	if(!confirm('<ait:message  messageID="alert.att.setting.shift.edit.delete_last_part" module="ar"/>'))
  	{return;}
	document.form1.action="/arControlServlet?operation=shift020del&pkno="+id+"&last=1&menu_code=<%=request.getParameter("menu_code")%>";
  }
  else{//"确实要删除次班次段？"
  if(!confirm('<ait:message  messageID="alert.att.setting.shift.edit.delete_this_part" module="ar"/>'))
  {return;}
  document.form1.action="/arControlServlet?operation=shift020del&pkno="+id+"&last=0&menu_code=<%=request.getParameter("menu_code")%>";
  }

  document.form1.submit();
}
function Save()
{
  if(document.form1.shiftId.value=="")
  {//"请填写班次ID"
  	alert('<ait:message  messageID="alert.att.setting.shift.add.id" module="ar"/>');
	return;
  }
  if(document.form1.shiftName.value=="")
  {//"请填写班次名称"
  	alert('<ait:message  messageID="alert.att.setting.item.chinese_name" module="ar"/>');
	return;
  }
  for(var i=0;i<document.form1.elements.length;i++)
  {
  	if(document.form1.elements[i].name.indexOf("fh")!=-1)
	{
		if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入开始时间"
			alert('<ait:message  messageID="alert.att.correct_start_time" module="ar"/>');
			return;
		}
	}
	if(document.form1.elements[i].name.indexOf("fm")!=-1)
	{
	   if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入开始时间"
			alert('<ait:message  messageID="alert.att.correct_start_time" module="ar"/>');
			return;
		}
	}
	if(document.form1.elements[i].name.indexOf("th")!=-1)
	{
		if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入结束时间"
			alert('<ait:message  messageID="alert.att.correct_end_time" module="ar"/>');
			return;
		}
	}
	if(document.form1.elements[i].name.indexOf("tm")!=-1)
	{
		if(isNaN(form1.elements[i].value)||form1.elements[i].value=="")
		{//"请正确输入结束时间"
			alert('<ait:message  messageID="alert.att.correct_end_time" module="ar"/>');
			return;
		}
	}

  }
  document.form1.action="/arControlServlet?operation=shiftedit&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}
</script>
</head>

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
				<td align="left" class="title1" colspan="10"><!-- 考勤班次-->
					<ait:message  messageID="display.att.setting.shift.shift" module="ar"/></td>
			</tr>
		</table>
		  <table width="100%"  border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="13%" class="info_title_01"><!-- 班次ID-->
					<ait:message  messageID="display.att.setting.shift.edit.id" module="ar"/></td>
		      <td width="18%" class="info_content_00"><input name="shiftId" type="text" id="shiftId" size="15" value="<%=tempShift.getShiftId()%>"></td>
		      <td width="13%" class="info_title_01"><!-- 班次性质-->
					<ait:message  messageID="display.att.setting.shift.edit.time_division" module="ar"/></td>
		      <td width="18%" class="info_content_00"><select name="dataType">
		        <%for(int i=0;i<datatype1.size();i++){
		          check=null;
		          if(((Integer)datatype1.get(i)).intValue()==tempShift.getDataType()){
		            check="selected";
		          }
		        %>
		        <option value="<%=((Integer)datatype1.get(i)).intValue()%>"  <%=check%>>
		        	<%=(String)datatype2.get(i)%>
		        </option>
		        <%}%>
		      </select></td>
		      </tr>
		    <tr>
		      <td width="13%" class="info_title_01"><!-- 班次类型-->
					<ait:message  messageID="display.att.setting.shift.shift_type" module="ar"/></td>
		      <td width="25%" class="info_content_00">
		      <ait:image src="../images/btn/Add_little.gif"  border="0" align="absmiddle" onclick="javascript:addrow();" style="cursor:hand" /></td>
		      <td width="13%" class="info_title_01"><!--班次名称-->
					<ait:message  messageID="display.att.setting.items.edit.name_chinese" module="ar"/></td>
		      <td width="18%" class="info_content_00"><input name="shiftName" type="text" id="shiftName" size="15" value="<%=tempShift.getShiftName()%>"></td>
		      <!-- 英文名称-->
		      <!--  
		      <td width="13%" class="info_title_01">
					<ait:message  messageID="display.att.setting.items.edit.name_english" module="ar"/></td>
		      <td width="18%" class="info_content_00"><input name="shiftEnName" type="text" id="shiftEnName" size="15" value="<%=StringUtil.checkNull(tempShift.getShiftEnName())%>"></td>
		      -->
		      <!-- 韩文名称-->
		      <!--  
		      <td width="13%" class="info_title_01">
					<ait:message  messageID="display.att.setting.items.edit.name_korean" module="ar"/></td>
		      <td width="25%" class="info_content_00"><input name="shiftKoName" type="text" id="shiftKoName" size="15" value="<%=StringUtil.checkNull(tempShift.getShiftKoName())%>"></td>
		      -->
		    </tr>
		    </table>
		    <table width='100%'  border='1'cellspacing='0' cellpadding='10' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF' style='padding: 2px 2px 2px 2px;'>
		    <%for(int i=0;i<shifts.size();i++){
		      shift020=(ArShift020)shifts.get(i);
		      f=shift020.getFromTime();
		      fs=f.split(":");
		      t=shift020.getToTime();
		      ts=t.split(":");
		      pkNOS+=Integer.toString(shift020.getPk_no())+"-";
		    %>
		    <tr>
		      <td class="info_content_00"><!-- 开始时间-->
					<ait:message  messageID="display.mutual.start_date_2"/></td>
		      <td class="info_content_00"><input name='fh<%=shift020.getPk_no()%>' type='text' size='2' maxlength='2' value="<%=fs[0].toString()%>">:<input name='fm<%=shift020.getPk_no()%>' type='text' size='2' maxlength='2' value="<%=fs[1].toString()%>"></td>
		      <td class="info_content_00"><!-- 结束时间-->
					<ait:message  messageID="display.mutual.end_date_2"/></td>
		      <td class="info_content_00"><input name='th<%=shift020.getPk_no()%>' type='text' size='2' maxlength='2' value="<%=ts[0].toString()%>">:<input name='tm<%=shift020.getPk_no()%>' type='text' size='2' maxlength='2' value="<%=ts[1].toString()%>"></td>
		      <td class="info_content_00"><select name="itemNo<%=shift020.getPk_no()%>">
		        <%for(int j=0;j<items.size();j++){
		          ar=(ArItem)items.get(j);
		          check=null;
		          if(shift020.getItem_no()==ar.getItemNo()){
		          check="selected";
		          }
		        %>
		        <option value="<%=ar.getItemNo()%>" <%=check%>>
		        <%if(admin1.getLanguagePreference().equals("zh")){%>
		        	<%=ar.getItemName()%>
		        <%}else if(admin1.getLanguagePreference().equals("ko")){%>
		        	<%=ar.getItemKorName()%>
		        <%}else{%>
		        	<%=ar.getItemEnName()%>
		        <%} %>
		        </option>
		        <%}%>
		      </select></td>
		      <td class="info_content_00">
		        <table width="65%"  border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td class="info_content_00"><!--开始--><ait:message messageID='display.att.setting.shift.edit.start' module='ar'/></td>
		          <td class="info_content_00"><%for(int xx=0;xx<starno.size();xx++){
		          check=null;
		          if(shift020.getFromDayName().equals((String)starname.get(xx))){
		            check="checked";
		          }
		        %>
		      <input type='radio' name='fday<%=shift020.getPk_no()%>' value='<%=(String)starno.get(xx)%>' <%=check%>>
		      <%if(admin1.getLanguagePreference().equals("zh")){%>
		        	<%=(String)starname.get(xx)%>
		        <%}else if(admin1.getLanguagePreference().equals("ko")){%>
		        	
		        <%}else{%>
		        	<%=(String)enstarname.get(xx)%>
		        <%} %>
		      <%}%></td>
		      <td rowspan="2" align="left">
		      	<img src="/images/btn/delete3.jpg" style="cursor:hand" onClick="javascript:Detele('<%=shift020.getPk_no()%>')" title="删除参数">
		      </td>
		        </tr>
		        <tr>
		          <td class="info_content_00"><!--结束--><ait:message messageID='display.att.setting.shift.edit.end' module='ar'/></td>
		          <td class="info_content_00"><%for(int xx=0;xx<starno.size();xx++){
		          check=null;
		          if(shift020.getToDayName().equals((String)starname.get(xx))){
		            check="checked";
		          }
		        %>
		      <input type='radio' name='tday<%=shift020.getPk_no()%>' value='<%=(String)starno.get(xx)%>' <%=check%>>
		      <%if(admin1.getLanguagePreference().equals("zh")){%>
		        	<%=(String)starname.get(xx)%>
		        <%}else if(admin1.getLanguagePreference().equals("ko")){%>
		        	
		        <%}else{%>
		        	<%=(String)enstarname.get(xx)%>
		        <%} %>
		      <%}%></td>
		        </tr>
		      </table>
		       
		      </td>
		    </tr>
		    <%}%>
		  </table>
		  <div id="createTable"></div>
		 <input type="hidden" name="count" value="0">
		 <input type="hidden" name="pkNos" value="<%=pkNOS%>">
		 <input type="hidden" name="shiftNo" value="<%=shiftNo%>">
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

</body>
</html>
