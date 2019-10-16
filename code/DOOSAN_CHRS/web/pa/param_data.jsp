<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.pa.PaParam,com.ait.pa.PaParamItem,com.ait.util.StringUtil,com.ait.sy.bean.AdminBean,com.ait.pa.PaCalc"%>
<html>
<head>
<!-- param_data.jsp -->
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript" src="../js/meizzMonth.js"></script>
<% 	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	String lan = admin1.getLanguagePreference();
	PaParam paParam = new PaParam();
	Vector vlist = new Vector();
	
	String paMonth = request.getParameter("pamonth");
	String statTypeCode = StringUtil.checkNull(request.getParameter("statTypeCode")) ;
	String configureNo = StringUtil.checkNull(request.getParameter("configureNo"));
	String deptid = StringUtil.checkNull(request.getParameter("deptid"));
	String key = StringUtil.checkNull(request.getParameter("key"));
	
	PaCalc pacalc = new PaCalc();
	boolean lockStatus = !pacalc.getPayLockStatus(paMonth,statTypeCode,admin1.getCpnyId()) ;
	
	PaParamItem paramitem = PaParamItem.getPaParamItemDetail(configureNo,paMonth,lan);
	request.setAttribute("paramitem",paramitem);
	vlist = paParam.getPaParamViewList(configureNo,deptid,key);
	request.setAttribute("vlist",vlist);
%>
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
	document.param_data.flag.value=target;
	window.parent.document.all("searchUrl").value = "param_data" ;
	
	document.param_data.submit();
}
function importExcel(configureNo)
{	
	if (configureNo == '')
	{
		alert("请选择输入项目!!!") ;
		return ;
	}
	window.parent.document.all("searchUrl").value = "param_data" ;
	url="/paControlServlet?operation=importParamDataReport$configureNo=" + configureNo;
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function exportExcel(configureNo)
{	
	if (configureNo == '')
	{
		alert("请选择输入项目!!!") ;
		return ;
	}
	window.parent.document.all("searchUrl").value = "param_data" ;
	url="/paControlServlet?operation=exportParamDataReport&configureNo=" + configureNo + "&deptid=" + window.parent.document.all("deptID").value + "&key=" + encodeURI(window.parent.document.all("key").value);
	//alert(url) ;
	window.open(url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function downloadImportTemplate()
{	
	window.parent.document.all("searchUrl").value = "param_data" ;
	param_data.action = "/paControlServlet?operation=downloadParamDateTemplate";
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

function batchDelete()
{
	var deleteFlag = false ;
	var checkBox = document.getElementsByName("checkFlag") ;
	var size = checkBox.length ;	
    for(var z = 0 ; z < size ; z++ )
	{
		if (checkBox[z].checked){
			deleteFlag = checkBox[z].checked ;
			break ;
		}
	}
	
	if (!deleteFlag){
		alert("请选择要删除的数据!!!") ;
		return false ;
	}
	
	if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>'))
	{
		document.param_data.flag.value= "batchDel";
		window.parent.document.all("searchUrl").value = "param_data" ;
	
		document.param_data.submit();	
	}
	
	return false ;
}

</script>

<body>

<table width="100%" border="0" cellpadding="0" cellspacing="2">
<tr>
<td align="left" class="info_content_00">&nbsp;&nbsp;&nbsp;<!--默认值-->
		<ait:message  messageID="display.pay.maintenance.setting.default" module="pay"/>:
		<%= paramitem.getDefault_val() %>
</td>
	<c:choose>
   		<c:when test="${paramitem.distinct_field_id == 'PERSON_ID'}">
   			<td align="right">
   					<% if (lockStatus){ %>
			 		<ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcel('${param.configureNo}');" style="cursor:hand"/> | 
			 		<% } %>
				    <ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" onclick="javascript:downloadImportTemplate();" style="cursor:hand"/> | 
					<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:exportExcel('${param.configureNo}');" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
   		</c:when>
   	</c:choose>

<td width="150">
<% if (lockStatus){ %>
	<a href="param_data_a.jsp?configureNo=<%=configureNo%>&pamonth=<%= paMonth %>"  onclick="return changeSearchUrl() ;"  >
		<ait:image src="/images/btn/Add_little.gif" align="absmiddle"/>
	</a>
	&nbsp;&nbsp;
	<a href="#"  onclick="return batchDelete() ;"  >
		<ait:image src="/images/btn/Delete_little.gif" align="absmiddle" enTitle="delete" />
	</a>
	&nbsp;&nbsp;
	<a href="javascript:send('modify');" >
		<ait:image src="/images/btn/Save.gif" align="absmiddle"/>
	</a>
<% } %>
</td>
</tr>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="param_data" method="post" action="param_data_t.jsp?configureNo=<%=configureNo%>&pamonth=<%=paMonth%>" >
<input type="hidden" name="ck_all" value="0" />
  <tr align="center">
    <td height="30"  class="info_title_01"　nowrap><!--序号-->
					<ait:message  messageID="display.mutual.no"/></td>
    <td height="30"  class="info_title_01" nowrap>
    	<a href="#"onclick="checkAll();" style="color:red;">全选</a>
    </td>
    <td height="30"  class="info_title_01" nowrap>
    	职号
    </td>
	<td class="info_title_01" nowrap>${paramitem.distinct_field_2nd}&nbsp;</td>
   		

    
    <td class="info_title_01" nowrap><!--默认值-->
		<ait:message  messageID="display.pay.maintenance.setting.default" module="pay"/></td>
    <td class="info_title_01" nowrap><!-- 起始年月 -->起始年月</td>
    <td class="info_title_01" nowrap><!-- 期间数值 -->期间数值</td>
    <td class="info_title_01" nowrap><!-- 中止年月 -->中止年月</td>
    <td width="60" class="info_title_01" nowrap>
    	<% if (lockStatus){ %>
	    <a href="javascript:if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>')){window.parent.document.all('searchUrl').value = 'param_data' ; location.href='param_data_t.jsp?flag=delall&configureNo=<%=configureNo%>&pamonth=<%=paMonth%>';};">
	    	<!--全部删除-->
					<ait:message  messageID="display.pay.maintenance.setting.delete_all" module="pay"/>
	    </a>
	    <% } %>
    </td>
  </tr><input type="hidden" name="flag" value="" />
  <%if (vlist.size()!=0){	 %>
  		<% int i=0; %>
  		<c:forEach items="${vlist}" var="vlist">
			<tr align="center" >
				<input type="hidden" name="param_data_no" value="${vlist.param_date_no}" />
				<td  align="center"><%=i+1%></td>
				<td  align="center">
				<input type="checkbox" name="checkFlag" value="${vlist.param_date_no}"/>
			    </td>
			    <td  align="center">${vlist.empId}</td>
			    </td>
				
		    			<td  align="center">
	   					<ait:content enContent="${vlist.field2_en_value}" zhContent="${vlist.field2_value}" koContent="${vlist.field2_kor_value}"/>
		    			&nbsp;</td>
				
				<td  align="center"><input type="text" name="return_value" value="${vlist.return_value}" style="width:70px"  onblur="textMuli('return_value','checkFlag',this.value);"></td>
				<td  align="center"><input type="text" name="startMonth" value="${vlist.startMonth}" readonly style="width:90px " onClick="setMonth(this);" onblur="textMuli('startMonth','checkFlag',this.value);"></td>
				<td  align="center"><input type="text" name="sdEdValue" value="${vlist.sdEdValue}" style="width:70px"  onblur="textMuli('sdEdValue','checkFlag',this.value);"></td>
				<td  align="center"><input type="text" name="endMonth" value="${vlist.endMonth}" readonly style="width:90px " onClick="setMonth(this);" onblur="textMuli('endMonth','checkFlag',this.value);"></td>
				<td  align="center">
					<% if (lockStatus){ %>
					<a href="javascript:if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>')){location.href='param_data_t.jsp?flag=del&configureNo=<%=configureNo%>&param_data_no=${vlist.param_date_no}&pamonth=<%=paMonth%>'};">
					<!--删除-->
					<ait:message  messageID="display.mutual.delete"/>
					</a>
					<% } %>
				</td>
			</tr>
			<%i++; %>
		</c:forEach>
  <%}%>
</form>
</table>
</body>
</html>