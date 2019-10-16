
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsRelation"%>
<%@ page import="com.ait.evs.EvsOperate"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 项目流程设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 
 
<%
String evTypeId="";
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
String evPeriodId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
//评价期间评价类型
EvsType evsType=new EvsType(evTypeId,"");
EvsPeriod evsPeriod=new EvsPeriod(evPeriodId);
List lEvsPeriod=null;
List lEvsType=null;
try{
lEvsPeriod=evsPeriod.getEvsPeriodByYear("");
lEvsType=evsPeriod.getEvTypeByEvPeriodId(evPeriodId);
}catch(Exception e){}
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

//评价操作方式
Vector vEvOperate=new Vector();
try{
vEvOperate=SysCodeParser.getCode("EVOPERATE",1);
}catch(Exception e){}
int vEvOperateSize=vEvOperate.size();

%>
<form name="LGFORM" method="post" action="" >
<br>
<table width="100%" border="0"   cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evstoolbar_a.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		 <td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
	 	
		<!-- display basic info -->
		<br>

		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
				<tr>
		<td class="info_title_01" >操作</td>
		<td align="right" height="20" class="info_content_00">
		<%if(lEvsProcess!=null&&lEvsItem!=null){%>
		<input type='checkbox' name='check' value='on' title="操作方式多重选择" checked="checked">多选
		<%}%>
		<img src="/images/btn/addevprocess.gif"
			width="67" height="21" border="0" align="absmiddle" alt=""
			style="cursor:hand"
			onclick="addEvProcess('<%=evTypeId%>','<%=evPeriodId%>');"><!--
	   <img
			src="/images/btn/addevitem.gif" width="67" height="21" border="0"
			align="absmiddle" alt="" style="cursor:hand"
			onclick="addEvItem('<%=evTypeId%>','<%=evPeriodId%>');">
		--></td>
		<td class="info_title_01" >评价期间</td>
		<td class="info_content_00"> 
			<select name="evPeriodId" onChange="evPeriodSelect();">
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
		</select>
		</td>
		<td class="info_title_01" >评价类型</td>
		<td class="info_content_00"> 
		<select name="evTypeId" onChange="evPeriodSelect();">
			<option value="">评价类型</option>
			<%
			if(lEvsType!=null){
				EvsType evsType_period=new EvsType();
				int lEvsTypeSize=lEvsType.size();
				for(int i=0;i<lEvsTypeSize;i++){
					evsType_period=(EvsType)lEvsType.get(i);
				%>
			<option value="<%=evsType_period.getEvTypeID()%>"
				<%if(evTypeId.equals(evsType_period.getEvTypeID())){out.print(" selected ");}%>><%=evsType_period.getEvTypeName()%></option>
			<%
				}
			}
			%>
		</select></td>
	</tr>
			</table>
		  </td>
		</tr>
		</table>
	
	<!-- display 3 level menu -->
	  <%@ include file="inc/evs_nav.jsp"%>
	<!-- display content -->
		<br>
		<input type="hidden" name="menu_code" value="<%=menu_code%>"> 
		<input type="hidden" name="evItemId" value="">
		<input type="hidden" name="evProcessId" value=""> 
		<input type="hidden" name="flag" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					评价流程</td>
			</tr>
		</table>
	
	
 <table width="98%" border="0" align="center" cellpadding="0"
	       cellspacing="1">
	 <tr>
		<td class="line">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	<tr>
		<td class="info_content_01">
		<%if (evPeriodId.equals("")) {%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="18%" class="info_title_01" >请选择评价期间</td>
			</tr></table>
		<%}
		else if (evTypeId.equals("")) {%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="18%" class="info_title_01" >请选择评价类型</td>
			</tr></table>
		<%}
		else {%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="40%" class="info_title_01" >评价流程 | 评价项目</td>
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
				<td width="<%=width%>%" class="info_title_01" ><%=evsItem_tr.getEvItemName()%>
				&nbsp;
				<img src="../images/btn/DeleteB.gif" onclick="delEvItem('<%=evsItem_tr.getEvItemID()%>');" style="cursor:hand" title="删除项目">
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
				<td height="30" class="info_content_01">

				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
					<tr>
						<td colspan="2" class="info_title_01" >
						<input type="hidden" name="evProcessId_tr" value="<%=evsProcessId%>">
						<input type="text" id="evProcessOrder" name="evProcessOrder" value="<%=evsProcess_tr.getEvProcessOrder()%>" style="width:15;" title="流程顺序">
						.
						<%=StringUtil.checkNull(evsProcess_tr.getEvProcessName())%>&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="../images/btn/DeleteB.gif" onclick="delEvPorocess('<%=evsProcessId%>');" style="cursor:hand" title="删除流程">
						</td>
					</tr>
					<tr>

						<td align="center" colspan="2" class="info_content_01" >
						<input type="text" id="evProcessSDate"
							name="evProcessSDate" style="width:80;" onClick="setday(this);"
							value="<%=StringUtil.checkNull(evsProcess_tr.getEvProcessSDate())%>" title="开始时间"></td>

					</tr>
					<tr>
						<td align="center" colspan="2" class="info_content_01" ><input type="text" id="evProcessEDate" 
							name="evProcessEDate" style="width:80;" onClick="setday(this);"
							value="<%=StringUtil.checkNull(evsProcess_tr.getEvProcessEDate())%>" title="结束时间"></td>
					</tr>
				</table>
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
						//构造项目流程信息
						EvsRelation evsRelation=new EvsRelation(evsPeriod,evsType,evsItem_td,evsProcess_tr);
						try{
							evsRelation.getEvRelationByPeriodTypeItemProcess();
						}catch(Exception e){
							
						}
						String evOperateId="";
						EvsOperate evsOperate=null;
						if(evsRelation.getEvsOperate()!=null){
							evOperateId=evsRelation.getEvsOperate().getEvOperateId();
						}
					
					
				%>
				<td class="info_content_01">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<td align="center" class="info_content_01">
							<input type="checkbox" name="<%=evsProcessId%>_<%=evsItemId%>_check" value="true" title="激活" <%if(evsRelation.getIsSave().equals("1")){out.print(" Checked ");}%>  
							onclick="checkOn(this);"  id="<%=evsProcessId%>_check" >&nbsp;
							<select id="<%=evsProcessId%>_operateId"
								name="<%=evsProcessId%>_<%=evsItemId%>_operateId"
								style="width:70;" onchange="operateChange(this);" >
								<option value="">操作方式</option>
								<%
								for(int k=0;k<vEvOperateSize;k++){
								HashMap codemap_opt=(HashMap)vEvOperate.get(k);
								%>
								<option value="<%=codemap_opt.get("code")%>"
									<%if(evOperateId.equals((String)codemap_opt.get("code"))){out.print(" selected ");}%>>
								<%=codemap_opt.get("name")%></option>
								<%}%>
							</select></td>
						</tr>
						<tr>
							<td width="<%=width%>%" align="center" class="info_content_01"><input type="text"
								value="<%=StringUtil.checkNull(evsRelation.getEvMarksValues())%>"
								style="width:90;"
								name="<%=evsProcessId%>_<%=evsItemId%>_marksValues" title="可选分数值"></td>
						</tr>
						<tr>
							<td align="center" class="info_content_01"><input type="text"
								value="<%=StringUtil.checkNull(evsRelation.getEvMarksName())%>"
								style="width:90;"
								name="<%=evsProcessId%>_<%=evsItemId%>_marksName" title="可选分数名"></td>
						</tr>
						<tr>
							<td align="center" class="info_content_01"><input type="text" value="<%=evsRelation.getEvMarkDefault()%>"
								style="width:30;"
								name="<%=evsProcessId%>_<%=evsItemId%>_markDefault" title="默认分数"><input type="text"
								value="<%=evsRelation.getEvProcessProp()%>" style="width:30;"
								name="<%=evsProcessId%>_<%=evsItemId%>_processProp" title="流程百分比"><input type="text"
								value="<%=evsRelation.getEvItemProp()%>" style="width:30;"
								name="<%=evsProcessId%>_<%=evsItemId%>_itemProp" title="项目百分比"></td>
						</tr>
					</table>
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
		<%}%>
</table>
		</td>
	</tr>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">
    
    function evPeriodSelect()
    {	
		document.LGFORM.action="/evs/evs0102.jsp";
		//alert(document.LGFORM.action) ;
		document.LGFORM.submit();
    }
	function delEvPorocess(evProcessId){
		if(confirm("将删除与此流程相关的信息！\n\n\t您确认?")){
			document.LGFORM.flag.value="delEvProcess";
			document.LGFORM.evProcessId.value=evProcessId;
			document.LGFORM.action="/evs/evs0102_t.jsp";
			document.LGFORM.submit();
		}
	}
	function delEvItem(evItemId){
		if(confirm("将删除与此项目相关的信息！\n\n\t您确认?")){
			document.LGFORM.flag.value="delEvItem";
			document.LGFORM.evItemId.value=evItemId;
			document.LGFORM.action="/evs/evs0102_t.jsp";
			document.LGFORM.submit();
		}
	}
	function addEvItem(evTypeId,evPeriodId){
			
			if(evTypeId==''||evPeriodId==''){
				alert("请先选择评价类型与评价期间!");
			}else{
				var theUrl = "/evs/evsItemadd.jsp?evTypeId="+evTypeId+"&evPeriodId="+evPeriodId;
      			var name = "itemAdd";
      			var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=560,height=400,left=10,top=0";
				window.open(theUrl,name,features);
				
			}

	}
	function addEvProcess(evTypeId,evPeriodId){
			
			if(evTypeId==''||evPeriodId==''){
				alert("请先选择评价类型与评价期间!");
			}else{
				var theUrl = "/evs/evsProcessadd.jsp?evTypeId="+evTypeId+"&evPeriodId="+evPeriodId;
      			var name = "processAdd";
      			var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=560,height=400,left=10,top=0";
				window.open(theUrl,name,features);
			}
	}
	function AddSave()
    {	
		
		if(confirm("您确认修改!")){
			if(CheckForm()){
				document.LGFORM.flag.value="update";
				document.LGFORM.action="/evs/evs0102_t.jsp";
				document.LGFORM.submit();
			}
		}
    }
	function CheckForm()
	{	
		<%
		if(lEvsProcess!=null){
		
		%>
		var length=<%=lEvsProcess.size()%>
		
	   if ( length== 0 ){
	     return false;
	   }
	   if (length ==1 )
	   {
	   	 or=document.LGFORM.evProcessOrder.value;
	   	 evProcessSDate=document.LGFORM.evProcessSDate.value;
	   	 evProcessEDate=document.LGFORM.evProcessEDate.value;
	     if(isNaN(or)){
			alert("流程顺序只能为数字!");
			document.LGFORM.evProcessOrder.value="";
			document.LGFORM.evProcessOrder.focus();
			return false;
	     }
	     
	     if(evProcessSDate!=""&&evProcessEDate!=""){
	     	evProcessEDate=evProcessEDate.substring(0,4)+"/"+evProcessEDate.substring(5,7)+"/"+evProcessEDate.substring(8,10);
            evProcessSDate=evProcessSDate.substring(0,4)+"/"+evProcessSDate.substring(5,7)+"/"+evProcessSDate.substring(8,10);
            
            var from = Date.parse(evProcessSDate);
            var to= Date.parse(evProcessEDate);
            
            if(from>to){
            	alert("开始时间不能大于结束时间!");
            	document.LGFORM.evProcessSDate.value="";
			 	document.LGFORM.evProcessSDate.focus();
				return false;
            }
	     }
	     return true;
	   }
		if(length>1){
			for(i=0;i<length;i++){
				order=document.all('evProcessOrder')[i].value;
				
				if(isNaN(order)){
					alert("流程顺序只能为数字!");
					document.all('evProcessOrder')[i].value="";
					document.all('evProcessOrder')[i].focus();
					return false;
				}
				for(j=0;j<length;j++){
					if(i!=j){
						if(order==document.all('evProcessOrder')[j].value){
							
							alert("流程顺序不能重复!");
							document.all('evProcessOrder')[j].focus();
							return false;
						}
					}	
				}
				
			 	evProcessSDate=document.all('evProcessSDate')[i].value;
	   	     	evProcessEDate=document.all('evProcessEDate')[i].value;
			 	if(evProcessSDate!=""&&evProcessEDate!=""){
	     	 		evProcessEDate=evProcessEDate.substring(0,4)+"/"+evProcessEDate.substring(5,7)+"/"+evProcessEDate.substring(8,10);
               		evProcessSDate=evProcessSDate.substring(0,4)+"/"+evProcessSDate.substring(5,7)+"/"+evProcessSDate.substring(8,10);
               		var from = Date.parse(evProcessSDate);
               		var to= Date.parse(evProcessEDate); 
              		if(from>to){
            			alert("开始时间不能大于结束时间!");
            			document.all('evProcessSDate')[i].value="";
			 			document.all('evProcessSDate')[i].focus();
						return false;
             		}
	       		}
			}
			return true;
		}
		<%
		}
		%>
		return false;
	}
	
	function operateChange(t)
	{		
        try{
          if(document.LGFORM.check.checked){

            for(i=0;i<document.all(t.id).length;i++){                
            	document.all(t.id)[i].options[t.selectedIndex].selected=true;
            }
          }
        }catch(e){}
	}
	
	function checkOn(c)
	{		
        try{
          if(document.LGFORM.check.checked){
            <%if(lEvsItem!=null){%>
			var length=<%=lEvsItem.size()%>;
			 c.checked = c.checked|0;
			
			  if (length>1)
			  {
			  	for(i=0;i<length;i++){                
            		document.all(c.id)[i].checked=c.checked;
            	}
			  }
            
          <%}%>
        }
        }catch(e){}
	}
    </script>
