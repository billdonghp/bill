<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.*,com.ait.kpa.PaParam,com.ait.kpa.KpaParamItem,com.ait.util.StringUtil,com.ait.sy.bean.AdminBean"%>
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
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	String lan = admin1.getLanguagePreference();
	PaParam paParam = new PaParam();
	Vector vlist = new Vector();
    String param_no = StringUtil.checkNull(request.getParameter("param_no"));
    String actionType = StringUtil.checkNull(request.getParameter("actionType"));
    String deptid = StringUtil.checkNull(request.getParameter("deptid"));
	String key = StringUtil.checkNull(request.getParameter("key"));
	
	KpaParamItem paramitem = KpaParamItem.getPaParamItemDetail(param_no,lan);
	request.setAttribute("paramitem",paramitem);
	vlist = paParam.AddPaParamList(param_no,actionType,deptid,key);
	request.setAttribute("vlist",vlist);
	String paramDefaultValue = paParam.getParamDefaultValue(param_no);
%>
<table width="100%" border="0" cellpadding="0" cellspacing="2">
	<tr>
		<td align="left">
			&nbsp;&nbsp;&nbsp;<!--默认值-->
		<ait:message  messageID="display.pay.maintenance.setting.default" module="pay"/>:
		<%=paramDefaultValue%>
		</td>
		<td width="67" align="center">
			<a href="javascript:send('insert');" >
				<ait:image src="/images/btn/Save.gif" border="0" align="absmiddle"/>
			</a>
		</td>
	</tr>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="param_data" method="post" action="param_data_t.jsp?param_no=<%=param_no%>" >
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5" class="info_title_01" nowrap>
    	${paramitem.distinct_field}
	</td>
   	<td class="info_title_01">${paramitem.distinct_field_2nd}&nbsp;</td>
    <td align="center" bgcolor="#F5F5F5" class="info_title_01" nowrap>
    <!--默认值--><ait:message  messageID="display.pay.maintenance.setting.default" module="pay"/>
    </td>
    <td class="info_title_01" nowrap><!-- 起始年月 -->起始年月</td>
    <td class="info_title_01" nowrap><!-- 期间数值 -->期间数值</td>
    <td class="info_title_01" nowrap><!-- 中止年月 -->中止年月</td>
  </tr>
  	<input type="hidden" name="flag" value="">
	<%if (vlist!=null){%>
		<c:forEach items="${vlist}" var="vlist">
		 	<tr align="center" >
			 	<input type="hidden" name="field1" value="${vlist.field1_value}" />
			 	<input type="hidden" name="field1_en" value="${vlist.field1_en_value}" />
			 	<input type="hidden" name="field1_kor" value="${vlist.field1_kor_value}" />
			 	<input type="hidden" name="field2" value="${vlist.field2_value}" />
			 	<input type="hidden" name="field2_en" value="${vlist.field2_en_value}" />
			 	<input type="hidden" name="field2_kor" value="${vlist.field2_kor_value}" />
		   		<td  align="center">
		   			${vlist.groupId}
		   			&nbsp;
		   		</td>
	   				<td  align="center">
	   					<ait:content enContent="${vlist.field2_en_value}" zhContent="${vlist.field2_value}" koContent="${vlist.field2_kor_value}"/>
	   				&nbsp;
	   				</td>
		    	<td  align="center"><input type="text" name="return_value" value="${vlist.return_value}" style="width:70px"></td>
				<td  align="center"><input type="text" name="startMonth" value="${vlist.startMonth}" readonly style="width:90px " onClick="setMonth(this);"></td>
				<td  align="center"><input type="text" name="sdEdValue" value="${vlist.sdEdValue}" style="width:70px"></td>
				<td  align="center"><input type="text" name="endMonth" value="${vlist.endMonth}" readonly style="width:90px " onClick="setMonth(this);"></td>
		 	</tr>
		 </c:forEach>
 	<%}else {%>
	<SCRIPT LANGUAGE="JavaScript">
		<!--
			alert("所有项目已全部添加");
			history.back();
		//-->
	</SCRIPT>
<%}%>
</form>
</table>
</body>
</html>
