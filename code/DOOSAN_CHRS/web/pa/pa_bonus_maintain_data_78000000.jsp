<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.util.StringUtil"%>
<%@ page import="com.ait.reports.reportservices.PaReportService,com.ait.sqlmap.util.ObjectBindUtil,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" scope="session" class="com.ait.sy.bean.AdminBean" />
<html>
<head>
<%
	PaReportService service = new PaReportService() ;
	SimpleMap bonusData = new SimpleMap() ;
	
	SimpleMap parameterObject = ObjectBindUtil.getData(request);
	parameterObject.setString("cpnyId", admin.getCpnyId());
	List recordList = null ;
	if (parameterObject.getString("statTypeCode").equals("C_12067_1330306")){
		recordList = service.retrieve780Report0207List(parameterObject) ;	
	}
	else{
		recordList = service.retrieve780Report0211List(parameterObject) ;
	}
	

	if (recordList != null && recordList.size() == 1)
	{
		bonusData = (SimpleMap)recordList.get(0) ;
	}
	
	request.setAttribute("bonusData",bonusData) ;
%>
<title></title>
<%@ include file="../inc/meta.jsp" %>
</head>
<script language="JavaScript" type="text/JavaScript">
	if (window.parent){
		if (window.parent.document.getElementById("deptmentName")) {
			window.parent.document.getElementById("deptmentName").innerHTML = "${bonusData.DEPARTMENT}" ;
		}
		
		if (window.parent.document.getElementById("data_started")) {
			window.parent.document.getElementById("data_started").innerHTML = "${bonusData.DATE_STARTED}" ;
		}
		
		if (window.parent.document.getElementById("name")) {
			window.parent.document.getElementById("name").innerHTML = "${bonusData.CHINESENAME}" ;
		}
	}
function bonus_trim(bonusData)  { return bonusData.replace("," ,"") ;  }

function paCal(){

	document.maintain_data.TOTAL_DEDUCTIONS.value = bonus_trim(document.maintain_data.THIS_ACTUAL_TAX.value) ;
	
	document.maintain_data.THIS_ACTUAL_BONUS.value = bonus_trim(document.maintain_data.THIS_TOTAL_SUPPORT.value) - bonus_trim(document.maintain_data.TOTAL_DEDUCTIONS.value) ; 
}

function save(){
	
	if (document.maintain_data.empId.value == "")
	{
		return ;
	}

	document.maintain_data.fireSubmit(); 
}



</script>

<body>

<table border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="maintain_data" method="post" action="/paControlServlet?operation=updatePaBonusHistory" >
<input type="hidden" name="url" value="pa_bonus_maintain_data_78000000.jsp" />
<input type="hidden" name="tableName" value="PA_BONUS_HISTORY" />
<input type="hidden" name="empId" value="${bonusData.EMPID}" />
<input type="hidden" name="personId" value="${bonusData.PERSON_ID}" />
<input type="hidden" name="statTypeCode" value="${bonusData.ATTENDANCE_PERIOD_CODE}" />
<input type="hidden" name="paMonth" value="${bonusData.PA_MONTH}" />
<input type="hidden" name="bonusTypeCode" value="${bonusData.BONUS_TYPE_CODE}" />
<input type="hidden" name="bonusNo" value="${bonusData.BONUS_NO}" />
  <tr>
  	<td align="right" colspan="2">
  		<input type="checkBox" name="paCalcFlag" />&nbsp;&nbsp;是否自动计算合计项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" onclick="save();" style="cursor:hand" title="保存" enTitle="save" />
  	</td>
  </tr>
  
  <tr align="center" >
    <td nowrap>
    	<table>
    		<tr>
    			<td colspan="2" align="center" class="info_title_01"><font size="5">收入</font></td>
    		</tr>	
    		<tr>
    			<td class="info_title_01">支给合计</td>
    			<td class="info_title_01">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="THIS_TOTAL_SUPPORT" value="${bonusData.THIS_TOTAL_SUPPORT}" required  float/> </td>
    			<td>&nbsp;</td>
    		</tr>
    	</table>
    
    </td>
    
    <td  nowrap>
    	<table>
    		<tr>
    			<td colspan="2" align="center" class="info_title_01"><font size="5">支出</font></td>
    		</tr>
    		<tr>
    			<td class="info_title_01">所得税</td>
    			<td class="info_title_01">实领奖金</td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="THIS_ACTUAL_TAX" value="${bonusData.THIS_ACTUAL_TAX}" required  float/> </td>
    			<td><input type="text" size="8" maxlength="10" name="THIS_ACTUAL_BONUS" value="${bonusData.THIS_ACTUAL_BONUS}" required  float/> </td>
    		</tr>
    	</table>
    </td>
  </tr>
</form>
</table>
</body>
<ait:xjos />
</html>