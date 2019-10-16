<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsColumn"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 项目列</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evstoolbar_m.jsp"%>
<%
//评价项目
Vector vEvColumn=null;
int vEvColumnSize=0;
try{
vEvColumn=SysCodeParser.getCode("EVCOLUMN");
}catch(Exception e){}
if(vEvColumn!=null){
	vEvColumnSize=vEvColumn.size();
}
//评价期间
String evPeriodId="";
String evTypeId="";
String evItemId="";
evPeriodId=request.getParameter("ID")!=null?request.getParameter("ID"):evPeriodId;
evTypeId=request.getParameter("ID2")!=null?request.getParameter("ID2"):evTypeId;
evItemId=request.getParameter("ID3")!=null?request.getParameter("ID3"):evItemId;
EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
List lEvsPeriod=null;
EvsItem evsItem=null;
try{
	evsItem=new EvsItem(evPeriodId,evTypeId);
	evsItem.getItemColumn(evItemId);
	lEvsPeriod=evsP.getEvsPeriodByYear("");
	
}catch(Exception e){}
%>
<input type="hidden" value="<%=evsItem.getEvPeriodID()%>" name="evPeriodId">
<input type="hidden" value="<%=evsItem.getEvTypeID()%>" name="evTypeId">
<input type="hidden" value="<%=evsItem.getEvItemID()%>" name="evItemId">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td>
				</td>
			</tr>
			<tr align="center">
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价期间</div>
				</td>
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价类型</div>
				</td>
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价项目</div>
				</td>
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价列</div>
				</td>
			</tr>
			
			<tr>
				<td>
				<div align="center" class="info_content_01">
				<%=StringUtil.checkNull(evsItem.getEvPeriodID())%>&nbsp;
				</div>
				</td>
				<td>
				<div align="center" class="info_content_01">
				<%=StringUtil.checkNull(evsItem.getEvTypeName())%>&nbsp;
				</div>
				</td>
				<td align="CENTER">
				<%=evsItem.getEvItemName()%></td>
				<td><%
					List lEvsColumn=null;
					lEvsColumn=evsItem.getLItemColumns();
					//所有评价列
					List lEvsColumns=null;				
					if(vEvColumn!=null){
						lEvsColumns=new Vector();
						for(int i=0;i<vEvColumnSize;i++){
							HashMap hEvColumn=(HashMap)vEvColumn.get(i);
							EvsColumn evsColumn=new EvsColumn();
							evsColumn.setEvColumnId(hEvColumn.get("code").toString());
							evsColumn.setEvColumnName(hEvColumn.get("name").toString());
							lEvsColumns.add(evsColumn);
					}
					}
					if(lEvsColumns!=null){
			            for(int i=0;i<lEvsColumns.size();i++){
							EvsColumn evsColumn=(EvsColumn)lEvsColumns.get(i);
							String check="";
							if(lEvsColumn.indexOf(evsColumn)>=0){
								check=" checked='true' ";
							}
					%> 
					<input type='checkbox' name='evColumnId' 
						value='<%=evsColumn.getEvColumnId()%>' <%=check%>> 
						<%=evsColumn.getEvColumnName().replaceAll("<br>","")%>
						<br>
				<%}}%>
					</td>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>
</html>
