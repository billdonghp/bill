<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 评价结果</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%

String evTypeId="";
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
String evPeriodId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
String evEmpID="";
evEmpID=request.getParameter("evEmpID")!=null?request.getParameter("evEmpID"):evEmpID;
//评价项目评价流程
EvsProcess evsProcess=null;
EvsItem evsItem=null;
List lEvsProcess=null;
List lEvsItem=null;

if(!evTypeId.equals("")&&!evPeriodId.equals("")){
	evsProcess=new EvsProcess(evPeriodId,evTypeId);
	evsItem=new EvsItem(evPeriodId,evTypeId);
	try{
	lEvsProcess=evsProcess.getProcessByTypePeriod();
	lEvsItem=evsItem.getItemByTypePeriod();
	}catch(Exception e){}
}



%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td class="line">
		<table width="100%" height="149" border="1" align="center"
			cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7"
			bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td width="20%" height="30" bgcolor="#F5F5F5">评价流程 | 评价项目</td>
				<!--------评价项目(形成表格行)------------------------------------->
				<%
				int	width=0; 
				if(lEvsItem!=null){
					int lEvsItemSize=lEvsItem.size();
					if(lEvsItemSize!=0){
						width=83/lEvsItemSize;
					}
					for(int i=0;i<lEvsItemSize;i++){
						EvsItem evsItem_tr=(EvsItem)lEvsItem.get(i);
						
					
				%>
				<td width="<%=width%>%" bgcolor="#F5F5F5" align="center"><%=evsItem_tr.getEvItemName()%>
				&nbsp;
				</td>

				<%
					}
				}
				%>
				<!--------评价项目------------------------------------------------>

			</tr>
			<!-------评价流程开始(形成表格列)---------------------------------------->
			<%
			if(lEvsProcess!=null){
				int lEvsProcessSize=lEvsProcess.size();
				for(int i=0;i<lEvsProcessSize;i++){
					EvsProcess evsProcess_tr=(EvsProcess)lEvsProcess.get(i);
					String evsProcessId="";
					evsProcessId=evsProcess_tr.getEvProcessID();
					
			%>
			<tr>

				<!-------评价流程信息开始---------------------------------------->
				
						<td colspan="1" bgcolor="#F5F5F5" align="left">
						<%=evsProcess_tr.getEvProcessOrder()%>
						.
						<%=StringUtil.checkNull(evsProcess_tr.getEvProcessName())%>
						</td>
				<!-------评价流程信息结束---------------------------------------->

				<!-------项目流程关系信息开始---------------------------------------------->
				<%
				if(lEvsItem!=null){
					int lEvsItemSize_td=lEvsItem.size();
					for(int j=0;j<lEvsItemSize_td;j++){
						EvsItem evsItem_td=(EvsItem)lEvsItem.get(j);
					
						String evsItemId="";
						evsItemId=evsItem_td.getEvItemID();
						//构造项目流程分数
						com.ait.evs.EvsItemProcessMark evsItemProcessMark=null;
						try{
							evsItemProcessMark=new com.ait.evs.EvsItemProcessMark(evEmpID,evPeriodId);
							evsItemProcessMark.getEvEmpItemProcessMarkByProcessId(evsProcessId,evsItemId);
					
						}catch(Exception e){}						
				%>
				<td align="center">
					<%
					out.print(evsItemProcessMark.getEvItemProcessMark());
					%>
				</td>
				<%
					}
				}
				%>
				<!---------项目流程关系信息结束---------------------------------------------------------------->

			</tr>
			<%
				}
			}
			%>
			<!-------评价流程结束------------------------------------------------->
		</table>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>