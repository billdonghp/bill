<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript" src="../js/meizzMonth.js"></script>
<script language="JavaScript" type="text/JavaScript">
function send(target){
	document.param_data.flag.value=target;
	window.parent.document.all("searchUrl").value = "param_data" ;
	document.param_data.submit();
}
function importExcel(param_no)
{	
	if (param_no == '')
	{
		alert("请选择输入项目!!!") ;
		return ;
	}
	window.parent.document.all("searchUrl").value = "param_data" ;
	url="/kpaControlServlet?operation=importPaBonusParamDateReport$param_no=" + param_no;
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}

function exportExcel(param_no)
{	
	if (param_no == '')
	{
		alert("请选择输入项目!!!") ;
		return ;
	}
	window.parent.document.all("searchUrl").value = "param_data" ;
	url="/kpaControlServlet?operation=exportPaBonusParamDataReport&param_no=" + param_no;
	window.open(url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}

function downloadImportTemplate()
{
	window.parent.document.all("searchUrl").value = "param_data" ;
	param_data.action = "/kpaControlServlet?operation=downloadParamDateTemplate";
    param_data.submit();
}

function changeSearchUrl(){
	window.parent.document.all("searchUrl").value = "param_data_a" ;
	//alert(window.parent.document.all("searchUrl").value) ;
	return true ;
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
<td align="left" class="info_content_00">&nbsp;&nbsp;&nbsp;
</td>
	<c:choose>
   		<c:when test="${bonusParam.DISTINCT_FIELD == 'EMPID'}">
   			<td align="right">
			 		<ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcel('${param.param_no}');" style="cursor:hand"/> | 
				    <ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" onclick="javascript:downloadImportTemplate();" style="cursor:hand"/> |
				    <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:exportExcel('${param.param_no}');" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
   		</c:when>
   	</c:choose>

<td width="67">
	<a href="/kpaControlServlet?operation=pa_bonus_param_data_forInsert&menu_code=${param.menu_code}&param_no=${bonusParam.PARAM_NO}" onclick="return changeSearchUrl() ;" >
		<ait:image src="/images/btn/Add_little.gif" align="absmiddle"/>
	</a>
</td>
<td width="67">
	<a href="javascript:send('modify');" >
		<ait:image src="/images/btn/Save.gif" align="absmiddle"/>
	</a>
</td>
</tr>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="param_data" method="post" action="/kpaControlServlet?operation=pa_bonus_param_data_do&param_no=${bonusParam.PARAM_NO}" >
  <tr align="center">
    <td height="30"  class="info_title_01"　nowrap><!--序号-->
					<ait:message  messageID="display.mutual.no"/></td>
	<td height="30"  class="info_title_01" nowrap>
    				<ait:message  messageID="display.mutual.emp_id"/>
    </td>
	<td class="info_title_01" nowrap>
					<ait:message  messageID="display.mutual.name" module="pay"/>
	</td>				
    <td class="info_title_01" nowrap><!--默认值--><ait:message  messageID="display.pay.maintenance.setting.default" module="pay"/></td>
    <td class="info_title_01" nowrap><!-- 起始年月 -->
    	<ait:message  messageID="display.pay.startYearMonth" module="pay"/>
    </td>
    <td class="info_title_01" nowrap><!-- 期间数值 -->
    <ait:message  messageID="display.pay.numerical" module="pay"/>
    </td>
    <td class="info_title_01" nowrap><!-- 中止年月 -->
    <ait:message  messageID="display.pay.endYearMonthe" module="pay"/>
	</td>
    <td width="60" class="info_title_01" nowrap>
	    <a href="javascript:if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>')){location.href='/kpaControlServlet?operation=pa_bonus_param_data_del&param_no=${bonusParam.PARAM_NO}'};">
	    	<!--全部删除-->
					<ait:message  messageID="display.pay.maintenance.setting.delete_all" module="pay"/>
	    </a>
    </td>
  </tr><input type="hidden" name="flag" value="" />
  		<c:forEach items="${bonusParamDataList}" var="vlist" varStatus="i" >
			<tr align="center" >
				<input type="hidden" name="param_data_no" value="${vlist.PARAM_DATA_NO}" />
				<td  align="center">${i.index + 1}</td>
				
				<td  align="center">${vlist.GROUPID}&nbsp;</td>
				<td  align="center">${vlist.FIELD2_VALUE}&nbsp;</td>
				<td  align="center"><input type="text" name="return_value" value="${vlist.RETURN_VALUE}" style="width:70px" ></td>
				<td  align="center"><input type="text" name="startMonth" value="${vlist.START_MONTH}" readonly style="width:90px " onClick="setMonth(this);"></td>
				<td  align="center"><input type="text" name="sdEdValue" value="${vlist.SD_ED_VALUE}" style="width:70px" ></td>
				<td  align="center"><input type="text" name="endMonth" value="${vlist.END_MONTH}" readonly style="width:90px " onClick="setMonth(this);"></td>
				<td  align="center">
					<a href="javascript:if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>')){location.href='/kpaControlServlet?operation=pa_bonus_param_data_del&param_data_no=${vlist.PARAM_DATA_NO}&param_no=${bonusParam.PARAM_NO}';}">
					<!--删除-->
					<ait:message  messageID="display.mutual.delete"/>
					</a>
				</td>
			</tr>
		</c:forEach>
</form>
</table>
</body>
</html>