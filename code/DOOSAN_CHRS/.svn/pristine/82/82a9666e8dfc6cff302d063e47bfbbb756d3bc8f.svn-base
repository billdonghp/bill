<%@page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsCommonItem"%>
<%@ page import="com.ait.evs.EvsCommonItemDetail"%>
<%@ page import="com.ait.evs.EvsCommonColumn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 共同项目</title>
</head>
<body>
<%


String evPeriodId="";
String evTypeId="";
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
List lEvsPeriod=null;
List lEvsType=null;

EvsEmp evsEmp=new EvsEmp();
EvsCommonItem evCommonItems=null;
try{
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=evsP.getEvTypeByEvPeriodId(evPeriodId);
	if(evTypeId.equals("")&&lEvsType!=null){
		evTypeId=((EvsType)lEvsType.get(0)).getEvTypeID();
	}
	evCommonItems=new EvsCommonItem(evPeriodId,evTypeId);
}catch(Exception e){}
%>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evstoolbar_0106.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2" class="title_line_01"></td>
	</tr>
	<tr>
		<td height="2" class="title_line_02"></td>
	</tr>
	<tr>
	<form action="/evs/evs0106.jsp?menu_code=evs0106" method="Post"
		name="evs0106">
	<td height="2" align="right">
		<select name="evPeriodId" onChange="evs0106.submit();">
			<%
                            if(lEvsPeriod!=null){
                            	int lEvsPeriodSize=lEvsPeriod.size();
                            	for(int i=0;i<lEvsPeriodSize;i++){
                            		EvsPeriod evsPeriod_sel=(EvsPeriod)lEvsPeriod.get(i);
                              %>
			<option value="<%=evsPeriod_sel.getEvPeriodID()%>"
				<%if (evPeriodId.equals(evsPeriod_sel.getEvPeriodID())) {%>
				selected="selected" <%}%>><%=evsPeriod_sel.getEvPeriodName()%></option>
			<%}}%>
		</select> 
		<select name="evTypeId" onChange="evs0106.submit();">
			<option value="">评价类型</option>
			<%
                            if(lEvsType!=null){
                            	int lEvsTypeSize=lEvsType.size();
                            	for(int i=0;i<lEvsTypeSize;i++){
                            		EvsType evsType_sel=(EvsType)lEvsType.get(i);
                              %>
			<option value="<%=evsType_sel.getEvTypeID()%>"
				<%if (evTypeId.equals(evsType_sel.getEvTypeID())) {%>
				selected="selected" <%}%>><%=evsType_sel.getEvTypeName()%></option>
			<%}}%>
		</select></td>
</form>
</tr>
</table>
<table width="98%"  border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" >
<tr>

 </tr>
 </table> 
<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:99%; height:' + (document.body.clientHeight-162) + ';\">')
//-->
</script>
<form name='evItem'>
<%
if(evCommonItems!=null){
	List lEvCommonItems=null;
	try{
   		lEvCommonItems=evCommonItems.getEvCommonItems();
   	}catch(Exception e){}
   	if(lEvCommonItems!=null){
	   	int evCommonItemSize=lEvCommonItems.size();
	   	for(int i=0;i<evCommonItemSize;i++){
	   		EvsCommonItem evCommonItem=(EvsCommonItem)lEvCommonItems.get(i);
	   		List lEvCommonItemDetail=null;
	   		List lEvCommonColumn=null;
	   		int lEvCommonItemDetailSize=0;
	   		int lEvCommonColumnSize=0;
	   		try{
		   		lEvCommonItemDetail=evCommonItem.getLEvCommonItemDetail();
		   		lEvCommonColumn=evCommonItem.getLItemColumns();
		   		lEvCommonItemDetailSize=lEvCommonItemDetail.size();
		   		lEvCommonColumnSize=lEvCommonColumn.size();
		   }catch(Exception e){}		
	   		
%>
			<table width="98%"  border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" >
			<!--项目名-->
			<tr align="center" bgcolor="#F5F5F5">
				<td colspan="<%=lEvCommonColumnSize+1%>">
				<b><%=(evCommonItem.getEvItemName()).replaceAll("<br>","")%></b>
				</td>
				<td title="赋予共同项目"><div onclick="Endow('<%=evCommonItem.getEvItemId()%>','<%=lEvCommonItemDetailSize%>');" style="cursor:hand"> + 赋予 </div>
				</td>
			</tr>
			<!--项目行标题-->	
			<tr	align="center" bgcolor="#F5F5F5">
				<td width="50" align="center">序号</td>
				<%if(lEvCommonColumn!=null){
				
					for(int j=0;j<lEvCommonColumnSize;j++){
						EvsCommonColumn evCommonColumn=(EvsCommonColumn)lEvCommonColumn.get(j);
						
				%>	
					<!--项目动态列-->
					<td><%=evCommonColumn.getEvColumnName().replaceAll("<br>","")%></td>	
				<%
					}
				}
				%>
				<td width="50" align="center">比重</td>
			</tr>
			
			<!--项目内容-->	
			<%if(lEvCommonItemDetail!=null){
				for(int k=0;k<lEvCommonItemDetailSize;k++){
					EvsCommonItemDetail evCommonItemDetail=(EvsCommonItemDetail)lEvCommonItemDetail.get(k);
					List  lEvCommonDetailColumn=null;
					try{
						lEvCommonDetailColumn=evCommonItemDetail.getLEvCommonColumn();
					}catch(Exception e){}
					
			%>
			<tr onClick="HighLightTR('#99CCFF','black','<%=evCommonItemDetail.getSeqEvCommonItemDetail()%>','<%=menu_code%>')">	
					<!--序号-->
					<td align="center"><%=k+1%></td>
					<%
					if(lEvCommonDetailColumn!=null){
						int lEvCommonDetailColumnSize=lEvCommonDetailColumn.size();
						for(int m=0;m<lEvCommonDetailColumnSize;m++){
							EvsCommonColumn evCommonDetailColumn=(EvsCommonColumn)lEvCommonDetailColumn.get(m);
							
					%>
							<!--项目列-->
							<td><%=StringUtil.checkNull(evCommonDetailColumn.getEvColumnDetail())%></td>
					<%
						}
					}
					%>
					<!--比重-->
					<td align="center" id="<%=evCommonItem.getEvItemId()%>" name="<%=evCommonItem.getEvItemId()%>"><%=evCommonItemDetail.getEvDetailProp()%></td>
			</tr>
			<%
				}
			}
			%>
			</table>
<%
		}
	}
}
%>
</form>
</div>
</body>
</html>
<script language="JavaScript" type="" src="">
	
    function Add()
    {	
		evPeriodId=document.evs0106.evPeriodId.value;
		evTypeId=document.evs0106.evTypeId.value;
		if(evTypeId==''||evPeriodId==''){
			alert("请先选择评价类型与评价期间!");
		}else{
			location.href='/evs/evs0106_a.jsp?menu_code=evs0106&evTypeId='+evTypeId+'&evPeriodId='+evPeriodId;		
		}
    }
	function Modify2()
	{	
		evPeriodId=document.evs0106.evPeriodId.value;
		evTypeId=document.evs0106.evTypeId.value;
		if(evTypeId==''||evPeriodId==''){
			alert("请先选择评价类型与评价期间!");
		}else{
    		if (ID=='')
    		{
            	alert("请在列表中选择要修改的项目");
   	 		}
    		else
    		{	
				location.href='/evs/evs0106_m.jsp?menu_code=evs0106&evTypeId='+evTypeId+'&evPeriodId='+evPeriodId+'&ID='+ID;	
    		}
		}
	}
	function Endow(evItemId,detailSize)
    {	
		if(checkProp1(evItemId,detailSize)){
        	evPeriodId=document.evs0106.evPeriodId.value;
			evTypeId=document.evs0106.evTypeId.value;
		
			if(evTypeId==''||evPeriodId==''){
				alert("请先选择评价类型与评价期间!");
			}else{
				var theUrl = "/evs/evscommon.jsp?menu_code=evs0106&evTypeId="+evTypeId+"&evPeriodId="+evPeriodId+"&evItemId="+evItemId;
 				var name = "CommonItemModify";
      			var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=500,height=350,left=10,top=0";
				window.open(theUrl,name,features);
			}
		}
    }
	function EndowAll()
    {	
		if(checkProp()){
			evPeriodId=document.evs0106.evPeriodId.value;
			evTypeId=document.evs0106.evTypeId.value;
			if(evTypeId==''||evPeriodId==''){
				alert("请先选择评价类型与评价期间!");
			}else{
				
				location.href='/evs/evs0106_t.jsp?menu_code=evs0106&evTypeId='+evTypeId+'&evPeriodId='+evPeriodId+'&flag=endowAll';
			}
		}
    }
	function checkProp1(evItemId,detailSize){
	
	if(detailSize== 0 ){
		return false;
	}
	if (detailSize ==1 )
	{	
		if ((document.all(evItemId).innerHTML)!='100.0')
		{
			alert("项目比重和应为100%！");
			return false;
		}
	}
	
	if (detailSize>1)
	{
		var prop=0;
		for (i=0;i<detailSize;i++)
		{
			prop=prop+document.all(evItemId)[i].innerHTML*1;
		}
		if (prop!=100)
		{
			alert("项目比重和应为100%！");
			return false;
		}
	}
	return true;
	}

    function checkProp(){
		<%
		if(evCommonItems!=null){
			List lEvCommonItems=null;
			try{
   				lEvCommonItems=evCommonItems.getEvCommonItems();
   			}catch(Exception e){}
   			if(lEvCommonItems!=null){
	   			int evCommonItemSize=lEvCommonItems.size();
	   			for(int i=0;i<evCommonItemSize;i++){
	   				EvsCommonItem evCommonItem=(EvsCommonItem)lEvCommonItems.get(i);
	   				
	   				
		%>
		if (document.all.<%=evCommonItem.getEvItemId()%>)
		{
			if (document.all('<%=evCommonItem.getEvItemId()%>')[0])
			{
				var prop=0;
				for (i=0;i<document.all.<%=evCommonItem.getEvItemId()%>.length;i++)
				{
					prop=prop+document.all.<%=evCommonItem.getEvItemId()%>[i].innerHTML*1;
				}
				if (prop!=100.0)
				{
					alert("项目比重和应为100%！");
					return false;
				}
			}
			else
			{
				if ((document.all.<%=evCommonItem.getEvItemId()%>.innerHTML)!='100.0')
				{	
					alert("项目比重和应为100%！");
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		<%
				}
			} 
		}
		%>
		return true;
	}

    </script>