<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArStaItem"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArStaItemBean">
</jsp:useBean>

<%
String itemNo = request.getParameter("itemNo");
if(request.getParameter("itemNo") !=null && request.getParameter("itemNo").length() > 0){
	ArrayList list=null;
	ArStaItem info=null;
	list=dao.getStaItemList(NumberUtil.parseInt(itemNo));
	info=(ArStaItem)list.get(0);
	String fDate=info.getFromDate();
	int fYear = 0;
	int fMonth = 0;
	if(!fDate.equals("&nbsp;")){
	  fYear= NumberUtil.parseInt(fDate.substring(0,4)) ;
	  fMonth= NumberUtil.parseInt(fDate.substring(fDate.length()-2,fDate.length())) ;
	}

	String tDate=info.getToDate();
	int tYear = 0;
	int tMonth = 0;
	if(!tDate.equals("&nbsp;")){
	  tYear=NumberUtil.parseInt(tDate.substring(0,4));
	  tMonth=NumberUtil.parseInt(tDate.substring(tDate.length()-2,tDate.length()));
	}
	info.setFormYear(fYear) ;
	info.setToYear(tYear) ;
	info.setFormMonth(fMonth) ;
	info.setToMonth(tMonth) ;
	request.setAttribute("arStaItem",info);
}
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function Save()
{
  if(document.form1.staItemId.value=="")
  {//"项目ID不能为空"
  	alert('<ait:message  messageID="alert.att.setting.complete_setting.id_empty" module="ar"/>');
	document.form1.staItemId.focus() ;
	return;
  }
  
  if(document.form1.staItemId.length > 30)
  {//"项目ID长度不能大于30个字符"
  	alert("项目ID长度不能大于30个字符!!!");
  	document.form1.staItemId.focus() ;
	return;
  }
  
  if(document.form1.itemName.value=="")
  {//"项目名称不能为空"
  	alert('<ait:message  messageID="alert.att.setting.complete_setting.name_empty" module="ar"/>');
	document.form1.itemName.focus() ;
	return;
  }
  

  document.form1.action="/arControlServlet?operation=staitemupdate&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}
function checkNumber(id,newValue,oldValue)
{
  if(isNaN(newValue))
  {
    document.form1.all(id).value=newValue;
    return;
  }
  else
  {
    document.form1.all(id).value=oldValue;
    //"数据不可以为数值类型"
    alert('<ait:message  messageID="alert.att.setting.complete_setting.data_numeric" module="ar"/>');
    return;
  }
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
			<%@ include file="/inc/common_toolbar_a.jsp"%>
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
					<td align="left" class="title1" colspan="10"><!--汇总项目-->
					<ait:message  messageID="display.att.setting.setting.complete_items" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01"><!-- 汇总项目ID-->
					<ait:message  messageID="display.att.setting.setting.item_id" module="ar"/></td>
		      <td width="75%" class="info_content_00"><input name="staItemId" type="text"  size="20" maxlength="29" value="${arStaItem.staItemId}">&nbsp;&nbsp;项目ID长度不能大于30个字符,单词之间以_相连</td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"> <!-- 全名称-->
					<ait:message  messageID="display.att.setting.items.edit.name_chinese" module="ar"/></td>
		      <td width="75%" class="info_content_00"><input name="itemName" type="text" size="20" maxlength="25" value="${arStaItem.itemName}" >&nbsp;<c:if test="${error == 'error'}"> <FONT color="red">添加失败,${arStaItem.itemName},与其他项目的开始文字重复</FONT> </c:if></td>
		    </tr>
		    <!-- 英文名称-->
		    <!--  
		    <tr>
		      <td width="15%" class="info_title_01"> 
					<ait:message  messageID="display.att.setting.items.edit.name_english" module="ar"/></td>
		      <td width="75%" class="info_content_00"><input name="itemEnName" type="text" size="10" value=""  ></td>
		    </tr>
		    -->
		    <!-- 韩文名称-->
		    <!--  
		    <tr>
		      <td width="15%" class="info_title_01"> 
					<ait:message  messageID="display.att.setting.items.edit.name_korean" module="ar"/></td>
		      <td width="75%" class="info_content_00"><input name="itemKoName" type="text" size="10" value="" ></td>
		    </tr>
		    -->
			<tr>
		      <td class="info_title_01"><!-- 单位-->
					<ait:message  messageID="display.att.setting.parameter.edit.unit" module="ar"/></td>
		      <td>
		      <ait:codeClass name="unit" codeClass="ItemUnit" selected="${arStaItem.unit}"/>
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 最小单位-->
					<ait:message  messageID="display.att.setting.parameter.edit.minimum_unit" module="ar"/></td>
		      <td class="info_content_00"><select name="minUnit">
		         <option value="1"   <c:if test="${arStaItem.minUnit == 1}">   selected</c:if> >1</option>
		         <option value="0.5" <c:if test="${arStaItem.minUnit == 0.5}"> selected</c:if> >0.5</option>
		      </select></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 开始时间-->
					<ait:message  messageID="display.mutual.start_date_2"/></td>
		      <td class="info_content_00"><input name="fYear" type="text"size="4" maxlength="4" value="<c:if test="${arStaItem.formYear != 0}">${arStaItem.formYear}</c:if>">
		      <!-- 年-->
					<ait:message  messageID="display.mutual.year"/>
		        <input name="fMonth" type="text"  size="2" maxlength="2" value="<c:if test="${arStaItem.formMonth != 0}">${arStaItem.formMonth}</c:if>">
		       <!--月-->
					<ait:message  messageID="display.mutual.month"/>
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!-- 结束时间-->
					<ait:message  messageID="display.mutual.end_date_2"/></td>
		      <td class="info_content_00"><input name="tYear" type="text"  size="4" maxlength="4" value="<c:if test="${arStaItem.toYear != 0}">${arStaItem.toYear}</c:if>">
		      <!-- 年-->
					<ait:message  messageID="display.mutual.year"/>
		        <input name="tMonth" type="text"  size="2" maxlength="2" value="<c:if test="${arStaItem.toMonth != 0}">${arStaItem.toMonth}</c:if>">
		       <!--月-->
					<ait:message  messageID="display.mutual.month"/>
		
		      </td>
		    </tr>
		  </table>
		<input type="hidden" name="itemNo" value="${arStaItem.itemNo}"/>
		<input type="hidden" name="oldItemName" value="${arStaItem.oldItemName}"/>
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
