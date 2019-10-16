<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="aritem" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<jsp:useBean id="arstaitem" scope="page" class="com.ait.ar.dao.ArStaItemBean">
</jsp:useBean>
<%
ArrayList items=aritem.getItemList();
ArrayList staitems=arstaitem.getStaItemList();

ArrayList paList=arstaitem.getPA_DISTINCT_LIST();
request.setAttribute("paList",paList);
request.setAttribute("items",items);
request.setAttribute("staitems",staitems);
%>
<html>
<head>
  <LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--
function filltext(values) {
	if (opener){
		if (opener.formular_date.form1) {	
			if(opener.formular_date.form1.formular || opener.formular_date.form1.formular)
			{
				if (document.all("obj").value=="condition") {
					var target = opener.formular_date.form1.condition;
				}else{
					var target = opener.formular_date.form1.formular;
				}
				target.value+=values;
			}
			
		}
	}
}
function aaa(a)
{
	document.form1.obj.value=a;
}
//-->
</SCRIPT>
</head>
<body>
  <form name="form1" method="POST">
<table width="100%" height="300" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td  height="25"  class="info_title_01"><!--考勤项目列表-->
					<ait:message  messageID="display.att.setting.formula.items_list" module="ar"/></td>
    <td  class="info_title_01"><!--考勤汇总列表-->
					<ait:message  messageID="display.att.setting.formula.complete_items" module="ar"/></td>
    <td  class="info_title_01"><!--人员基本信息-->
					<ait:message  messageID="display.att.setting.formula.staff_basic_info" module="ar"/></td>
    <td class="info_title_01"><p><!--计算工具-->
					<ait:message  messageID="display.att.setting.formula.calculation_tool" module="ar"/></p>    </td>
  </tr>
  <tr align="center" >
    <td align="center"  style="padding:5 5 5 5 " valign="top">
    <!-- 考勤明细 ATT_ITEM.-->
    <select name="select" id="param_no" size="17" style="width:170px " onClick="filltext('考勤明细.'+this.value)">
	    <c:forEach items="${items}" var="oneResult" varStatus="i">
	    	<option value="${oneResult.itemName}">
	    		<ait:content enContent="${oneResult.itemEnName}" zhContent="${oneResult.itemName}" koContent="${oneResult.itemKorName}"/>
	    	</option>
	    </c:forEach>
    </select>
	</td>
    <td  align="center"  style="padding:5 5 5 5 " valign="top">
    <!-- 考勤汇总 STA_ITEM.-->
	<select name="select" id="param_no" size="17" style="width:170px " onClick="filltext('考勤汇总.'+this.value)">
	    <c:forEach items="${staitems}" var="oneResult" varStatus="i">
	    	<option value="${oneResult.itemName}">
	    		<ait:content enContent="${oneResult.itemEnName}" zhContent="${oneResult.itemName}" koContent="${oneResult.itemKoName}"/>
	    	</option>
	    </c:forEach>
    </select>
	</td>
	<td  align="center"  style="padding:5 5 5 5 " valign="top">
	<!-- 考勤汇总 STA_ITEM.-->
		<select name="select" id="param_no" size="17" style="width:170px " onClick="filltext('考勤汇总.'+this.value)">
			<c:forEach items="${paList}" var="paList">
				<option value="${paList.fieldName}">
		    		<ait:content enContent="${paList.fieldEnName}" zhContent="${paList.fieldName}" koContent="${paList.fieldKorName}"/>
		    	</option>
			</c:forEach>
		</select>
	</td>
    <td style="padding:5 5 5 5 "><table width="100" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><input type="radio" name="radiobutton" value="condition" onClick="aaa(this.value)" checked>
      <!--条件-->
					<ait:message  messageID="display.att.setting.formula.criteria" module="ar"/></td>
        <td><input type="radio" name="radiobutton" value="formular" onClick="aaa(this.value)">
      <!--公式-->
					<ait:message  messageID="display.att.setting.formula.formula" module="ar"/></td>
      </tr>
    </table>
      <table width="200" border="0" cellspacing="0" cellpadding="0">
        <tr align="center">
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="1"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="2"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="3"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="+"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="'"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="4"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="5"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="6"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="-"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="in"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="7"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="8"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="9"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="*"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="and"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="("></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="0"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value=")"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="%"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="or"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="="></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="<"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="between"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value=">"></td>
          <td width="40" height="36"><input name="button" type="button" class="calc" onClick="filltext(this.value)" value="<>"></td>
        </tr>
        <tr align="center">
          <td height="36" colspan="5"><input name="button" type="button" onClick="filltext(' ')" value="Space" style="width:100px "></td>
        </tr>
      </table></td>
  </tr>
</table>
<INPUT TYPE="hidden" NAME="obj" id="obj" value="condition">
  </form>
</body>
</html>
