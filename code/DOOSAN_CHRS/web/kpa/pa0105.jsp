<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:directive.page import="com.ait.sy.common.PaHelper"/>
<jsp:directive.page import="com.ait.pa.PaReport"/>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil"/>
<html>
<head>
<!-- pa0105.jsp -->
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
function SearchContent(condition,id){
		var inputBox = document.getElementById(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		
		layer=document.getElementById("showsearch");
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;
		layer.style.visibility='visible';
		
		document.emp_list.location.href = "./inc/PaSearchEmployee.jsp?content="+encodeURIComponent(condition)+"&id=" + id;
}
function showDept() {
          
          theUrl="/hrmControlServlet?menu_code=pa0105&operation=searchEmpByDept2&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}
<%

	java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(Calendar.YEAR);

	SimpleMap sMap = new SimpleMap();
	sMap = ObjectBindUtil.getData(request);
	if (sMap.get("empID") == null)
		sMap.setString("empID","");
	request.setAttribute("sMap",sMap);
	if("".equals(StringUtil.checkNull(sMap.getString("PaYear")))){
		sMap.setString("PaYear",String.valueOf(year));
	}
%>
</script>
<%
	List listEva;
	List listPun;
	List listRew;
	List ListBon;
	SimpleMap person = new SimpleMap();
	PaReport paReport = new PaReport();
	
	person = (SimpleMap)paReport.RetrievePersonalSalary(sMap);
	listEva=paReport.RetrieveEvaluationInfo(sMap);
	listPun=paReport.RetrievePunishmentInfo(sMap);
	listRew=paReport.RetrieveRewardInfo(sMap);
	ListBon=paReport.RetrieveBonusOfYear(sMap);
	
	request.setAttribute("EvaInfo",listEva);
	request.setAttribute("PunInfo",listPun);
	request.setAttribute("RewInfo",listRew);
	request.setAttribute("BonInfo",ListBon);
	
	request.setAttribute("PaPerson",person);
%>
<body>
<%@ include file="../pa/inc/common_nav.jsp"%>
<div id='showsearch' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
	<iframe id="emp_list" name="emp_list" width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" ></iframe>
</div>
<form  name="searchMonthPa"  method="POST"  action="">
<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
  <tr>
    <td>
      <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr>
          
          <td width="12%" class="info_title_01">工 号</td>
          <td width="18%" class="info_content_01">
          		<input id="empID" name="empID" size="8" value="<c:out value='${sMap.empID}'/>" onKeyUp="SearchContent(this.value,this.id)" />
          		<img src="/images/btn/department.gif" align="absmiddle" onclick="showDept();" style="cursor:hand;">
          		</td>
          <td width="12%" class="info_title_01">姓 名</td>
          <td width="12%" class="info_content_01">${PaPerson.CHINESENAME}</td>
          <td width="12%" height="30" class="info_title_01">工资年度</td>
          <td width="34%" class="info_content_01">
          		<select name="PaYear" ><%
					for (int i=-4;i<=4;i++){
					%><option value="<%=year+i%>" 
						<%if(String.valueOf(year+i).equals(sMap.getString("PaYear"))){ out.print("selected");}%>><%=year+i%></option>
					<%}%>
		        </select>
		      年
		      <img src="/images/btn/p_inquiry.gif" align="absmiddle" onClick="searchMonthPa.submit();" style="cursor:hand;">
		  </td>
          </tr>
      </table>
    </td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
  <tr>
    <td height="1" class="title_line_01"></td>
  </tr>
  <tr>
    <td height="2" class="title_line_02"></td>
  </tr>
  <tr>
    <td height="1">    </td>
  </tr>
  <tr>
    <td>
      <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr>
          <td width="12%" class="info_title_01">部 门</td>
          <td width="18%" class="info_content_01">${PaPerson.DEPTFULLNAME}</td>
          <td width="12%" height="30" class="info_title_01">职位</td>
          <td width="12%" class="info_content_01">${PaPerson.POSITION}</td>
          <td width="12%" height="30" class="info_title_01" >职 务</td>
          <td width="12%" class="info_content_01">${PaPerson.POST}</td>
          <td width="12%" class="info_title_01">入社日期</td>
          <td width="12%" class="info_content_01">${PaPerson.JOINDATE}</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
	  <tr>
	    <td hight="30" class="info_title_01">奖金</td>
	    <td hight="30" class="info_title_01">奖金税</td>
	    <td hight="30" class="info_title_01">实得奖金</td>
	  </tr>
   <c:forEach items="${BonInfo}" var="Bon" varStatus="s">
	  <tr>
	    <td hight="30" class="info_content_01">${Bon.奖金}</td>
	    <td hight="30" class="info_content_01">${Bon.奖金税}</td>
	    <td hight="30" class="info_content_01">${Bon.实得奖金}</td>
	  </tr>
   </c:forEach> 
	  <tr>
	    <td hight="30" class="info_content_01">&nbsp;</td>
	    <td hight="30" class="info_content_01"></td>
	    <td hight="30" class="info_content_01"></td>
	  </tr>
</table>

<table width="100%" border="0" cellpadding="0">
		  <tr>
			<td colspan="2" height="1" class="info_content_01"></td>
		  </tr>
		  <tr>
			<td colspan="2" height="2" class="info_content_01"></td>
		  </tr>
		  <tr>
			<td colspan="2" height="1" class="info_content_01"></td>
		  </tr>
		  <tr>
	        <td colspan="2"><span class="title1">职能评价</span></td>
	      </tr>
  <tr>
    <td>
	    <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      
	      <tr>
	        <td hight="30" class="info_title_01">评价期间</td>
	        <td hight="30" class="info_title_01">评价等级</td>
	      </tr>
	    <c:forEach items="${EvaInfo}" var="Eva" varStatus="s">  
	      <tr>
	        <td class="info_content_01">${Eva.PERIODNAME}</td>
	        <td class="info_content_01">${Eva.GRADENAME}</td>
	      </tr>
	    </c:forEach>  
	      <tr>
	        <td hight="30" class="info_content_01">&nbsp;</td>
	        <td class="info_content_01"></td>
	      </tr>
	    </table>
    </td>
  </tr>
  		  <tr>
			<td colspan="2" height="1" class="info_content_01"></td>
		  </tr>
		  <tr>
			<td colspan="2" height="2" class="info_content_01"></td>
		  </tr>
		  <tr>
			<td colspan="2" height="1" class="info_content_01"></td>
		  </tr>
		  <tr>
	        <td colspan="4"><span class="title1">奖励</span></td>
          </tr>
  <tr>
    <td>
	    <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      <tr>
	        <td hight="30" class="info_title_01">奖励日期</td>
	        <td hight="30" class="info_title_01">奖励类型</td>
	        <td hight="30" class="info_title_01">功绩类型</td>
	        <td hight="30" class="info_title_01">奖励金额</td>
	      </tr>
	   <c:forEach items="${RewInfo}" var="Rew" varStatus="s">  
	      <tr>
	        <td class="info_content_01">${Rew.rewardDate}</td>
	        <td class="info_content_01">${Rew.rewardType}</td>
	        <td class="info_content_01">${Rew.achievementType}</td>
	        <td class="info_content_01">${Rew.rewardBonus}</td>
	      </tr>
	   </c:forEach>
	      <tr>
	        <td hight="30" class="info_content_01">&nbsp;</td>
	        <td class="info_content_01"></td>
	        <td class="info_content_01"></td>
	        <td class="info_content_01"></td>
	      </tr>
	    </table>
    </td>
  </tr>
  		  <tr>
			<td colspan="2" height="1" class="info_content_01"></td>
		  </tr>
		  <tr>
			<td colspan="2" height="2" class="info_content_01"></td>
		  </tr>
		  <tr>
			<td colspan="2" height="1" class="info_content_01"></td>
		  </tr>
		  <tr>
	        <td colspan="4"><span class="title1">惩戒</span></td>
          </tr>
  <tr>
    <td>
	    <table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      <tr>
	        <td hight="30" class="info_title_01">惩戒日期</td>
	        <td hight="30" class="info_title_01">惩戒类型</td>
	        <td hight="30" class="info_title_01">违纪类型</td>
	        <td hight="30" class="info_title_01">惩戒权者</td>
	      </tr>
	   <c:forEach items="${PunInfo}" var="Pun" varStatus="s">
	      <tr>
	        <td hight="30" class="info_content_01">${Pun.punishDate}</td>
	        <td class="info_content_01">${Pun.punishType}</td>
	        <td class="info_content_01">${Pun.violationType}</td>
	        <td class="info_content_01">${Pun.punisher}</td>
	      </tr>
	   </c:forEach>
	      <tr>
	        <td hight="30" class="info_content_01">&nbsp;</td>
	        <td class="info_content_01"></td>
	        <td class="info_content_01"></td>
	        <td class="info_content_01"></td>
	      </tr>
	    </table>
    </td>
  </tr>
</table>

</body>
</html>



