<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.*,com.ait.pa.PaParam,com.ait.pa.PaParamItem,com.ait.util.StringUtil,com.ait.sy.bean.AdminBean"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript" src="../js/meizzMonth.js"></script>
<script language="JavaScript" type="text/JavaScript">
function checkAll()
{
    var selected = document.param_data.ck_all.value == "0" ? true : false;
    var size = document.param_data.checkFlag.length ;
  	for (var i = 0 ; i < size; i ++){
		document.param_data.checkFlag[i].checked = selected ;
	
	}
    document.param_data.ck_all.value = selected ? "1" : "0";
}

function send(target){
	window.parent.document.all("searchUrl").value = "param_data" ;
	document.param_data.flag.value=target;
	document.param_data.submit();
}
function paseValueToAmount(value){   
                if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
                    if(decimalIndex=='-1'){   
                        return false;   
                    }else{   
                        var decimalPart=value.substring(decimalIndex+1,value.length);   
                        if(decimalPart.length>2){   
                            return true;   
                        }else{   
                            return false;   
                        }   
                    }   
                }   
                return false;   
 }  
</script>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr>
		<td align="left">
			&nbsp;&nbsp;&nbsp;
		</td>
		<td width="67" align="center">
			<a href="javascript:send('insert');" >
				<ait:image src="/images/btn/Save.gif" border="0" align="absmiddle"/>
			</a>
		</td>
	</tr>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="param_data" method="post" action="/paControlServlet?operation=pa_bonus_param_data_do&configureNo=${bonusParam.CONFIGURE_NO}&pamonth=${bonusParam.PA_MONTH_STR}&statTypeCode=${bonusParam.STAT_TYPE_CODE}" >
  <input type="hidden" name="ck_all" value="0" />
  <tr align="center">
  	<td height="30"  class="info_title_01" nowrap>
    	<a href="#"onclick="checkAll();" style="color:red;">全选</a>
    </td>
    <td height="30"  align="center" bgcolor="#F5F5F5" class="info_title_01" nowrap>
    	职号
	</td>
   	<td class="info_title_01">${bonusParam.DISTINCT_NAME_2ND}&nbsp;</td>
    <td align="center" bgcolor="#F5F5F5" class="info_title_01" nowrap>
    <!--默认值--><ait:message  messageID="display.pay.maintenance.setting.default" module="pay"/>
    </td>
    <td class="info_title_01" nowrap><!-- 起始年月 -->起始年月</td>
    <td class="info_title_01" nowrap><!-- 期间数值 -->期间数值</td>
    <td class="info_title_01" nowrap><!-- 中止年月 -->中止年月</td>
  </tr>
  	<input type="hidden" name="flag" value="">
		<c:forEach items="${bonusParamDataList}" var="vlist">
		 	<tr align="center" >
			 	<input type="hidden" name="field1" value="${vlist.FIELD1}" />
			 	<input type="hidden" name="field2" value="${vlist.FIELD2}" />
			 	<td  align="center"><input type="checkbox" name="checkFlag" value="check_${vlist.param_date_no}"/></td>
		   		<td align="center">${vlist.EMPID}&nbsp;</td>
	   			<td align="center">${vlist.FIELD2}&nbsp;</td>
		    	<td  align="center"><input type="text" name="return_value" value="" style="width:70px" onblur="textMuli('return_value','checkFlag',this.value);" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
				<td  align="center"><input type="text" name="startMonth" value="" readonly style="width:90px " onblur="textMuli('startMonth','checkFlag',this.value);" onClick="setMonth(this);"></td>
				<td  align="center"><input type="text" name="sdEdValue" value="" style="width:70px" onblur="textMuli('sdEdValue','checkFlag',this.value);" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
				<td  align="center"><input type="text" name="endMonth" value="" readonly style="width:90px " onblur="textMuli('endMonth','checkFlag',this.value);" onClick="setMonth(this);"></td>
		 	</tr>
		 </c:forEach>
	<c:if test="${fn:length(bonusParamDataList) == 0}">
		<SCRIPT LANGUAGE="JavaScript">
			<!--
				alert("所有项目已全部添加");
				//history.back();
			//-->
		</SCRIPT>
	</c:if>
</form>
</table>
</body>
</html>
