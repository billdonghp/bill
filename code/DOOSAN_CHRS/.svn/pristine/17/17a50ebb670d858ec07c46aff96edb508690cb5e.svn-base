<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<%@ include file="../inc/meta.jsp" %>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--
function filltext(values) {
	if (opener){
		if (opener.formular_date.data) {
			if(opener.formular_date.data.condition_cn || opener.formular_date.data.formular_cn)
			{
				if (document.all("obj").value=="condition") {
					var target = opener.formular_date.data.condition_cn;
				}else{
					var target = opener.formular_date.data.formular_cn;
				}
				target.value +=values;
			}
		}
	}
}

function reload(statTypeCode)
{
	 window.location.href = "/paControlServlet?operation=pa_bonus_util&statTypeCode=" + statTypeCode ;
}

function aaa(a)
{
	document.all("obj").value=a;
}
-->
</SCRIPT>
</head>
<body>
<table width="100%" height="250" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr align="center">
		<td width="180" height="30"  class="info_title_01"><!--工资计算项目列表-->
					<ait:message  messageID="display.pay.maintenance.formula.list" module="pay"/></td>
		<td width="180" class="info_title_01"><!--工资输入项目列表-->
					<ait:message  messageID="display.pay.maintenance.formula.parameter" module="pay"/></td>
		<td class="info_title_01"><!--工资固定参数列表-->
					<ait:message  messageID="display.pay.maintenance.formula.fixed_parameter" module="pay"/></td>
		<td class="info_title_01""><p><!--计算工具-->
					<ait:message  messageID="display.pay.maintenance.formula.tool" module="pay"/></p>    </td>
	</tr>
	<tr align="center" > 
		<td  align="center"  style="padding:5 5 5 5 " valign="top">
			<select name="select" id="param_no" size="18" style="width:150px " onClick="filltext('计算项目.' + this.value)">
				<c:forEach items="${pa_item_list}" var="item">
					<option value="${item.ITEM_NAME}">
						<ait:content enContent="${item.ITEM_EN_NAME}" zhContent="${item.ITEM_NAME}" koContent="${item.ITEM_KOR_NAME}"/>
					</option>
				</c:forEach>
    		</select>
		</td>
		<td  align="center"  style="padding:5 5 5 5 " valign="top">
				<select name="select" id="param_no" size="18" style="width:150px " onClick="filltext('输入项目.' + this.value)">
			    	<c:forEach items="${pa_param_item_list}" var="param_item">
			    		<option value="${param_item.PARAM_NAME}">
			    			<ait:content enContent="${param_item.PARAM_EN_NAME}" zhContent="${param_item.PARAM_NAME}" koContent="${param_item.PARAM_KOR_NAME}"/>
			    		</option>
			    	</c:forEach>
			    </select>
		</td>
		<td style="padding:5 5 5 5 ">
      			<select name="select" id="distinct" size="18" style="width:150px " onClick="filltext('固定参数.' + this.value)">
        			<c:forEach items="${pa_distinct_list}" var="distinct">
        				<option value="${distinct.FIELD_NAME}">
        					<ait:content enContent="${distinct.FIELD_EN_NAME}" zhContent="${distinct.FIELD_NAME}" koContent="${distinct.FIELD_KOR_NAME}"/>
        				</option>
        			</c:forEach>
      			</select>
      	</td>
    	<td style="padding:5 5 5 5 ">
    	<table width="200" border="0" cellspacing="0" cellpadding="0">
    	<tr align="center">
          <td width="200" height="30" colspan="5"><input type="radio" name="radiobutton" value="condition" onClick="aaa(this.value)" >
      <!--条件-->
					<ait:message  messageID="display.att.setting.formula.criteria" module="ar"/>
        <input type="radio" name="radiobutton" value="formular" onClick="aaa(this.value)" checked>
      <!--公式-->
					<ait:message  messageID="display.att.setting.formula.formula" module="ar"/>
		 </td>
      </tr>
        <tr align="center">
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="1"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="2"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="3"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="+"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="'"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="4"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="5"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="6"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="-"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="in"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="7"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="8"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="9"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="*"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="and"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="("></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="0"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value=")"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="%"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="or"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="="></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext('<')" value="＜"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="between"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext('>')" value="＞" name="r"></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="<>"></td>
        </tr>
        <tr align="center">
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="."></td>
          <td height="36" colspan="3"><input name="button" type="button" onClick="filltext(' ')" value="Space" style="width:100px "></td>
          <td width="40" height="36"><input type="button" class="calc" onClick="filltext(this.value)" value="/"></td>
        </tr>
    </table></td>
  </tr>
</table>
<INPUT TYPE="hidden" NAME="obj" id="obj" value="fomular">
</body>
</html>
