
<%@ page contentType="text/html; charset=UTF-8" language="java"	errorPage=""%>
	
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ page import="com.ait.evs.EvsGxjndj"%>
<%@ page import="com.ait.evs.Gxjsdj"%>
<%@ page import="com.ait.evs.EvsGxjndjBack"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ar.bean.ArDetailBack"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<%@ page import="com.ait.util.DateUtil"%>

<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"      scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />

<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 评价决裁 > 工匠技师决裁</title>
<script language="javascript1.5" type="text/javascript">

function findObject(object , tag){
    if(object == null || typeof(object) != "object") return null ;
    var node = object.parentElement ;
    if(node == null) return null ;
    if(node.tagName == tag)
      return node ;
    else
     return findObject(node , tag) ;
  }
  function findRow(object){
     return findObject(object , "TR") ;
  }
function checkAll()
{
  var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
    {   	
      document.ApplyForm.elements[i].checked = selected;
    }
  }
  document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function Search() {
	//重新搜索时应重置当前页数
	var qryType = document.ApplyForm.qryType.value;
	//document.ApplyForm.action = "/evsControlServlet";
	//document.ApplyForm.content.value="armodifyaffirm";
	//document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}
//单个决裁
function doAffirm_single(element, flag) {
	 
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
       document.ApplyForm.elements[i].checked = false;
  }  
  var row = findRow(element);  
  var jumpCyc = false;  
  for(k = 0 ; k < row.cells.length; k++){
      var objects = row.cells[k].all;
      for (j = 0; j < objects.length; j++){
        if(objects[j].type=="checkbox"){
           objects[j].checked = !objects[j].disabled;
           jumpCyc = true;
           break;
        }
      }
      if (jumpCyc) break;
  }
  doAffirm(flag);
}
//批量决裁
// flag 1 通过, 2 否决
function doAffirm(flag){
    var msg1='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';
	var msg ='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';
	if (flag == "2") {
		msg ='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
		msg1='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	}
	var existSelected = false;//是否有选择的记录
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" 
        && document.ApplyForm.elements[i].checked && !document.ApplyForm.elements[i].disabled)
    {   	
      var affirmNo = document.ApplyForm.elements[i].value;
      var currenFlag = document.getElementById("opFlag" + affirmNo).value;
      //alert("4aa4"+currenFlag);
      if (currenFlag != "0" && currenFlag != flag) {//当前记录只能通过或否决，与操作不一致，忽略
          document.ApplyForm.elements[i].checked = false;
      } else
       existSelected = true;
    }
  }
  if (!existSelected){
      alert(msg);   
      return false;
  }
	if (confirm(msg1)) {
	///	alert("3");

		document.ApplyForm.action="/evsControlServlet?operation=evsGjjsaffirm&menu_code=<%=request.getParameter("menu_code")%>";
		//document.ApplyForm.action = "/essControlServlet";
		//document.ApplyForm.operation.value="modify";
		document.ApplyForm.flag.value=flag;
		document.ApplyForm.submit();
	}
  
}

</script>

</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 
<%! 
String selected(String valueSel, String value){
      return valueSel.equals(value) ? "selected" : "";
    }
 %>
<%
EvsCraft evscraft = new EvsCraft();  
EvsGxjndj evsGxjndj = new EvsGxjndj();  
Gxjsdj evsGxjsdj = new Gxjsdj();  
String qryType = "";
qryType=request.getParameter("qryType")!=null?request.getParameter("qryType"):qryType;
if(qryType.equals("")){
	qryType=qryType+"";
}

%>
<form name="ApplyForm" action="" method="post">
<table width="100%" border="0"   cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
 		<%@ include file="../evs/inc/evs_toolbar_modify_affirm.jsp"%>  
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
		          <td width="10%" class="info_title_01"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td width="10%" class="info_content_00"><input type="text" name="sDate" size="10" maxlength="10" value="${sDate}" readonly onClick="setday(this);" /></td>
		          <td width="10%" class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td width="10%" class="info_content_00"><input type="text" name="eDate" size="10" maxlength="10" value="${eDate}" readonly onClick="setday(this);" /></td>    
		       	  <td width="7%" class="info_title_01"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
				  <td width="10%" class="info_content_00">
				     <select name="qryType" onchange="Search();">
				      
				         <option value="0" <%if(qryType.equals("2")){out.print(" selected ");}%>><!-- 未决裁 -->
				     <ait:message messageID="display.ess.approval.pending" module="ess"></ait:message>  
				     </option>        
				     <option value="1" <%if(qryType.equals("3")){out.print(" selected ");}%>>已决裁         </option>    
				     <option value="2" <%if(qryType.equals("4")){out.print(" selected ");}%>>已否决         </option>            
				     </select>
				  </td>                    
		        </tr>  
			</table>
	      </td>
	</tr>
	</table>
	
	<!-- display 3 level menu -->
	  <%@ include file="inc/evs_nav.jsp"%>
	<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					工匠技师决裁</td>
			</tr>
		</table>
	

 <table width="98%" border="0" align="center" cellpadding="0"      cellspacing="1">
	 <tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" onClick="HighLightTR('#F0F1F4','black','','')">
			 <td class="info_title_01" width="20"><a href="#" onclick="checkAll();" style="color:red;"> <!-- 全选 -->
			  <ait:message messageID="display.mutual.select_2" module="ess"></ait:message>  	   
			  </a></td>
			  
				<td width="10%" height="30" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">工匠技师姓名</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价年度</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">工种</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度1</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度2</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度3</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">评价维度4</div>
				</td>
				<td width="15%" class="info_title_01">
				<div align="center">总分</div>
				</td>
				 
				<td class="info_title_01" nowrap>决裁意见</td>      
		    <td class="info_title_01" nowrap><!--  决裁情况-->
			<ait:message	messageID="display.mutual.status_2" module="ess"></ait:message>   
			   </td>
			   
			   
			</tr>
		            
	            <%
	                	SimpleMap parameterObject =new SimpleMap();
	                	parameterObject.set("supervisor", admin.getAdminID());
	                	parameterObject.setString("qryType", qryType);
	                	parameterObject.setString("sDate", sDate);
	        			parameterObject.setString("eDate", eDate);
					  List levStypecodeid=evsGxjndj.getEvsGjjsconfirm(parameterObject);
					 
					  if(levStypecodeid!=null){ 
					     for(int i=0;i<levStypecodeid.size();i++){   
					    	 evsGxjsdj=(Gxjsdj)levStypecodeid.get(i); 
					    	 essAffirmorList = evsGxjsdj.getAffirmorList();
					    	 
				    	    int applyNo = evsGxjsdj.getPkNo1();
						    int affirmNo = 0;
						    String person_id="";
						    int opFlag = evsGxjsdj.getOpFlag();
							    
						    if (opFlag >= 0) {
					    	 for(int j=0;j<essAffirmorList.size();j++){
							    essAffirmor = (EssAffirmor) essAffirmorList.get(j);
							    System.out.print(essAffirmor.getAffirmorId()+"sss"+admin.getAdminID());
						        if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {
						            affirmNo = essAffirmor.getAffirmNo();
						            break ;
							    } 
							  }
					    	 }
					      String checkState = affirmNo > 0 ? "" : "disabled";
				 %>
						<tr	onClick="HighLightTR('#F0F1F4','black','<%=evsGxjsdj.getPkNo1()%>','<%=menu_code%>')">
							 <td class="info_content_09" ><%if (evsGxjsdj.getStatus().equals("2") ) {%>
							 	<input type="checkbox" name="affirmNo" value="<%=affirmNo%>" <%=checkState%>>
							  	 <input type="hidden" name="applyNo<%=affirmNo%>" value="<%=applyNo%>">
							  	<input type="hidden" name="person_id<%=affirmNo%>" value="<%=person_id%>">
							    <input type="hidden" id="opFlag<%=affirmNo%>" name="opFlag<%=affirmNo%>" value="<%=opFlag%>">
							  
							  		 <%} else {%>&nbsp;<%}%>   </td>
							<td height="30" ><div align="center">&nbsp;<%=i+1%></div>
							</td>
							<td><div align="center" height="30" ><%=evsGxjsdj.getChineseName()%>&nbsp;</div>
							</td>
							
							<td><div align="center" height="30" ><%=evsGxjsdj.getEvperiodid()%>&nbsp;</div>
							</td>
							
							<td><div align="center" height="30" ><%=evsGxjsdj.getCodeid()%>&nbsp;</div>
							</td>
							<td><div align="center" height="30" ><%=evsGxjsdj.getPJWD1()%>&nbsp;</div>
							</td>
							<td><div align="center" height="30" ><%=evsGxjsdj.getPJWD2()%>&nbsp;</div>
							</td>
							<td><div align="center" height="30" ><%=evsGxjsdj.getPJWD3()%>&nbsp;</div>
							</td>
							<td><div align="center" height="30" ><%=evsGxjsdj.getPJWD4()%>&nbsp;</div>
							</td>
							
							<td><div align="center" height="30" ><%=evsGxjsdj.getSUMSTORE()%>&nbsp;</div>
							</td>																		
							 <td width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark<%=affirmNo%>" style=" height: 25px;width:100px " type="_moz"
						onfocus="this.style.height='60px'" onblur="this.style.height='25px';"></textarea>
			</td>
			 
		    <td align="center" nowrap>
			    <%
			    for(int j=0;j<essAffirmorList.size();j++){            
			    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);
			    	System.out.println("ddd"+essAffirmor.getAffirmFlag()+"ddd");     %>
			    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
			    	<%= StringUtil.checkNull(essAffirmor.getAffirmorName()) + StringUtil.getString((4 - essAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + StringUtil.checkNull(essAffirmor.getUpdateDate()) %>&nbsp;&nbsp;
		    		<%= String.valueOf(essAffirmor.getAffirmFlag()).equals("0")?"未决裁":"已决裁" %>
			    	<%if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {       
			    		if (evsGxjsdj.getOpFlag() == 0) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">
			    			<!-- 通过 --><ait:message messageID="display.ess.approval.approved" module="ess"></ait:message> 
			    			</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" 
			    			onClick="doAffirm_single(this, 2);">
			    			<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message> </span>
			    		<%} else if (evsGxjsdj.getOpFlag() == 1) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">
			    			<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message></span>
			    		<%} else if (evsGxjsdj.getOpFlag() == 2) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 2);">
			    			<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message></span>
						<%}
					}%>
			    	</font><br>
			    <%}%>
		    </td>
						</tr>
						<%}}%>
		</table>
		</td>
	</tr>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"	height="15">
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
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="flag" value="" />
<input type="hidden" name="content" value="armodifyaffirm" />
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
