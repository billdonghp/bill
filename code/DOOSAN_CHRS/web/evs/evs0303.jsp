<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 &gt; 统计查看 &gt; 奖金统计</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<%@ include file="inc/evstoolbar_v.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>
<%

String evPeriodId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsPeriod evsPeriod=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsPeriod.getCurrentEvPeriod();
	}catch(Exception e){}
}
List lEvsPeriod=null;

try{
	lEvsPeriod=evsPeriod.getEvsPeriodByYear("");

}catch(Exception e){}
%>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
	  <tr>
		<td height="2" class="title_line_01"></td>
	  </tr>
	  <tr>
		<td height="2" class="title_line_02"></td>
	  </tr>
	  </tr>
		<td height="2" align="right"></td>
	  <tr>
	  <tr>
		<td class="line">
		<table width="60%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
           <tr align="center">
             <td height="30" colspan="2" bgcolor="#F5F5F5">评价期间</td>
            <td width="66%"> 
            <select
			name="evPeriodId" onChange="evPeriodSelect();">

			<option value="">评价期间</option>
			<%
			if(lEvsPeriod!=null){
				int lEvsPeriodSize=lEvsPeriod.size();
				for(int i=0;i<lEvsPeriodSize;i++){
					EvsPeriod evsP=(EvsPeriod)lEvsPeriod.get(i);
				%>
			<option value="<%=evsP.getEvPeriodID()%>"
				<%if(evPeriodId.equals(evsP.getEvPeriodID())){out.print(" selected ");}%>><%=evsP.getEvPeriodName()%></option>
			<%
				}
			}
			%>
		</select></td>
          </tr>
           <tr align="center">
             <td width="10%" bgcolor="#F5F5F5"><input name="radiobutton" type="radio" value="radiobutton"></td>
            <td width="24%" height="30" bgcolor="#F5F5F5"><div align="left">固定基数</div></td>
            <td><input type="text" name="textfield" style="width:100px "></td>
          </tr>
           <tr align="center">
             <td bgcolor="#F5F5F5"><input name="radiobutton" type="radio" value="radiobutton"></td>
            <td height="30" bgcolor="#F5F5F5"><div align="left">固定总金额</div></td>
            <td><input type="text" name="textfield" style="width:100px "></td>
          </tr>
          <tr align="center">
             <td bgcolor="#F5F5F5"><input name="radiobutton" type="radio" value="radiobutton"></td>
            <td height="30" bgcolor="#F5F5F5"><div align="left">工资项作为基数</div></td>
            <td><select name="select" style="width:100px ">
            </select></td>
          </tr>
        </table></td>
	  </tr>
</table>

</body>
</html>
