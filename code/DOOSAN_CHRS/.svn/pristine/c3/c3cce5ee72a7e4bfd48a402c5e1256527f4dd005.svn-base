<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.utils.FormUtil,com.ait.sqlmap.util.SimpleMap,com.ait.util.NumberUtil,java.util.*"%>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap" scope="page"></jsp:useBean>
<jsp:useBean id="thisyear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="planYear" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="startMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="endMonth" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="paVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="peVersion" class="java.util.ArrayList" scope="request"></jsp:useBean>
<html>
<head>
<title>奖金推算</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
	pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
	year = Integer.parseInt(pamonth.substring(0,4));
	month = Integer.parseInt(pamonth.substring(4,6));
	}

%>

<SCRIPT LANGUAGE="JavaScript">
function Search(){
	if($('paVersion').value=="" || $('paVersion').value==null ){
	alert("请选择已经推算过的基本工资！")
	return;
	}
	if($('peVersion').value=="" || $('peVersion').value==null ){
	alert("请选择已经推算过的补贴！")
	return;
	}
	if($('versionNote').value=="" ||$('versionNote').value==null){
	alert("请输入版本信息！");
	return;
	}
	 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan03Serche&menu_code=pa0403";
	 document.form1.submit();
}

function Save(){
 if($('search').value==""||$('search').value==null){
 alert("请先进行搜索数据！");
 return;
 }
 document.form1.action="/paControlServlet?operation=paPlan&content=payPlan03Save&menu_code=pa0403";
 document.form1.submit();

}

function paseValueToAmount(value){   
            if(value!=null&&value!=''){   
                   if(Number(value)>10){
                    return true;
                   }else{
	                    var decimalIndex=value.indexOf('.');   
	                    if(decimalIndex=='-1'){   
	                        return false;   
	                    }else{   
	                        var decimalPart=value.substring(0,decimalIndex+1);   
	                        if(Number(decimalPart)>10){   
	                            return true;   
	                        }else{   
	                            return false;   
	                        }   
	                    }   
                    }
            }   
                return false;   
 }

</SCRIPT>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);

%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_pa_plan.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="left">
		<!-- display basic info -->
		<br>
 <form name="form1" action="" method="post">
 <input type="hidden" name="search" value="${search}">
	<table width="55%" border="0" cellpadding="0" cellspacing="1" class="dr_d" >
		<tr align="left">
		<td align="center" class="info_title_01">
		  计划年度
		</td>
		<td align="center" class="info_content_00">
		<select name="planYear" class="pamonth"><%
			for (int i=0;i<2;i++){
			%><option value="<%=year+i%>" <%=(NumberUtil.parseInt(planYear))==(year+1)?"selected":""%>><%=year+i%></option>
			<%}%>
		      </select>
		      <!--年-->
			<ait:message  messageID="display.mutual.year" />
		</td>
		
		  <td align="center" class="info_title_01">
		  基本工资
		  </td>
		<td align="center" class="info_content_00">
		  <select name="paVersion">
		  <option value="">请选择</option>
		  <c:forEach items="${paVersion}" var="test" varStatus="i">
		  <option value="${test.seqid}" <c:if test="${test.seqid==paVersionStr}">selected</c:if>>${test.versionnote}</option>
		  </c:forEach>
		  </select>
		  </td>
		  <td height="15"  align="center" class="info_title_01">
		  补贴
		  </td>
		<td align="center" class="info_content_00">
		  <select name="peVersion">
		  <option value="">请选择</option>
		  <c:forEach items="${peVersion}" var="test" varStatus="i">
		  <option value="${test.seqid}" <c:if test="${test.seqid==peVersionStr}">selected</c:if>>${test.versionnote}</option>
		  </c:forEach>
		  </select>
		  </td>	
		  <td align="center" class="info_title_01">		
		   版本信息
		   </td>
		<td align="center" class="info_content_00">
		   <textarea name="versionNote" style=" height: 30px;width:300px ">${versionNote }</textarea>
		  </td>	 
		</tr>
		</table>
	<br>
	<table width="55%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	 <tr align="center" bgcolor="#F5F5F5">
		      
		     <td nowrap="nowrap" class="info_title_01">
				&nbsp;</td>
			 <c:forEach begin="0" end="11" varStatus="i">
			  <td nowrap="nowrap" class="info_title_01">
				${i.index+1}月</td>	
			</c:forEach>	 
		    </tr>
		    <c:if test="${FRateAnnualsalaryList==null}">
		    <tr>
		    <td nowrap="nowrap" align="center" class="info_title_01" >工厂比率(年薪)</td>
		    <c:forEach begin="0" end="11" varStatus="i">		   
		    <td nowrap="nowrap" align="center" class="info_content_01" ><input name="FRateAnnualsalary_${i.index+1}" type="text" value="0" style="width:50px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		    </c:forEach>	   
		    </tr>
		    </c:if>
		    <c:if test="${FRateAnnualsalaryList!=null}">
		    <tr>
		    <td nowrap="nowrap" align="center" class="info_title_01" >工厂比率(年薪)</td>
		    <c:forEach items="${FRateAnnualsalaryList}" var="test" varStatus="i">		   
		    <td nowrap="nowrap" align="center" class="info_content_01" ><input name="FRateAnnualsalary_${i.index+1}" type="text" value="${test.rate}" style="width:50px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		    </c:forEach>	   
		    </tr>
		    </c:if>
		    <c:if test="${FRateNoAnnualsalaryList==null}">
		    <tr>
		    <td nowrap="nowrap" align="center" class="info_title_01" >工厂比率(非年薪)</td>
		    <c:forEach begin="0" end="11" varStatus="i">		   
		    <td nowrap="nowrap" align="center" class="info_content_01" ><input name="FRateNoAnnualsalary_${i.index+1}" type="text" value="0" style="width:50px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		    </c:forEach>	 		   
		    </tr>
		    </c:if>
		    <c:if test="${FRateNoAnnualsalaryList!=null}">
		    <tr>
		    <td nowrap="nowrap" align="center" class="info_title_01" >工厂比率(非年薪)</td>
		    <c:forEach items="${FRateNoAnnualsalaryList}" var="test" varStatus="i">		   
		    <td nowrap="nowrap" align="center" class="info_content_01" ><input name="FRateNoAnnualsalary_${i.index+1}" type="text" value="${test.rate}" style="width:50px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		    </c:forEach>	 		   
		    </tr>
		    </c:if>
		    <c:if test="${BRateList==null}">
		    <tr>
		    <td nowrap="nowrap" align="center" class="info_title_01" >支社比率</td>
		     <c:forEach begin="0" end="11" varStatus="i">		   
		    <td nowrap="nowrap" align="center" class="info_content_01" ><input name="BRate_${i.index+1}" type="text" value="0" style="width:50px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		    </c:forEach>	 
		    </tr>
		    </c:if>
		    <c:if test="${BRateList!=null}">
		    <tr>
		    <td nowrap="nowrap" align="center" class="info_title_01" >支社比率</td>
		    <c:forEach items="${BRateList}" var="test" varStatus="i">		   
		    <td nowrap="nowrap" align="center" class="info_content_01" ><input name="BRate_${i.index+1}" type="text" value="${test.rate}" style="width:50px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"></td>
		    </c:forEach>	 		   
		    </tr>
		    </c:if>
			
	</table>

		<!-- display 3 level menu -->
		<%@ include file="../pa/inc/common_toolbar.jsp"%>		
		
		<!-- display content -->
	
	 <br>			
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算结果-->
					奖金推算</td>
			</tr>
    </table>
    <br>
    	<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				编号</td>
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				部门</td>
			 <td nowrap="nowrap" class="info_title_01" rowspan="2">
				工资类型</td>		      
		      <td nowrap="nowrap" class="info_title_01" rowspan="2">
				职群</td>	
			  <td nowrap="nowrap" class="info_title_01" rowspan="2">
				职级</td>			   
			  <td nowrap="nowrap" class="info_title_01" rowspan="2">
				工资项目</td>		
			  <c:forEach begin="1" end="12" varStatus="i">			
			  <td nowrap="nowrap" class="info_title_01" colspan="3">
			  ${i.index}月</td>		  
		      </c:forEach>	     
			 </tr>
		     <tr align="center" bgcolor="#F5F5F5">
			 <c:forEach begin="1" end="12" varStatus="i">	
			  <td nowrap="nowrap" class="info_title_01">基数</td>
			  <td nowrap="nowrap" class="info_title_01">比率</td>
			  <td nowrap="nowrap" class="info_title_01">奖金计提</td>			
		    </c:forEach>	 
		     </tr>
			 <c:forEach items="${list}" var="test" varStatus="i">	
			 <tr>
			 <td nowrap="nowrap" class="info_content_00">${i.index+1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BG_NM}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.WAGESTYPE}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.GROUPTYPE}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.POSTYPE}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PAYTYPE}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA1}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BASE2}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE2}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA2}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BASE3}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE3}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA3}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE4}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE4}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA4}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE5}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE5}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA5}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE6}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE6}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA6}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE7}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE7}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA7}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE8}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE8}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA8}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.BASE9}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE9}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA9}</td>		
			 <td nowrap="nowrap" class="info_content_00">${test.BASE10}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE10}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA10}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE11}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE11}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA11}</td>
			 <td nowrap="nowrap" class="info_content_00">${test.BASE12}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.RATE12}</td>	
			 <td nowrap="nowrap" class="info_content_00">${test.PA12}</td>				 				
			 </tr>
			</c:forEach>
		</table>

	</form>	
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
