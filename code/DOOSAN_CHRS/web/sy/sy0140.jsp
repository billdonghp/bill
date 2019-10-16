<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  
	import="org.apache.log4j.Logger,
	com.ait.util.SysCodeParser,
	com.ait.util.StringUtil,
	com.ait.util.NumberUtil,
	com.ait.sy.dao.*,
	com.ait.sy.bean.Affirm,
	java.util.*,
	com.ait.sy.sy0106.bean.*" %>
<%@ include file="../inc/taglibs.jsp"%>
<script src="../js/prototype.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
<title>CONFIRMOR SETTING</title>
</head>
<%@ include file="../inc/import.jsp"%>
<jsp:useBean id="affirmDept" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="affirmEmp" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="affirm" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap" />
<body>
<script language="JavaScript" type="text/javascript">
<!-- 

var preEl ;
var orgBColor;
var orgTColor;
var ID='';
var MENU_CODE='<%=menu_code%>';

function HighLightTR(backColor,textColor,i,menu_code){
var t;
if(typeof(preEl)!='undefined') {
preEl.bgColor=orgBColor;

try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
}
var el = event.srcElement;
el = el.parentElement;
orgBColor = el.bgColor;
orgTColor = el.style.color;
el.bgColor=backColor;
try{ChangeTextColor(el,textColor);}catch(e){;}
preEl = el;
ID=i;
MENU_CODE=menu_code
}



function ChangeTextColor(a_obj,a_color){
        for (i=0;i<a_obj.cells.length;i++){
        a_obj.cells(i).style.color=a_color;
    }
}



function band(backColor,textColor,deptid,typeid,empid,affirmor,affirmRelationNo,cpnyIdGz)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;

	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	preEl = el;
	document.affirmForm.deptid.value=deptid;
	document.affirmForm.typeid.value=typeid;
    document.affirmForm.empid.value=empid;
    document.affirmForm.cpnyIdGz.value=cpnyIdGz;
	document.affirmForm.affirmor.value=affirmor;
	document.affirmForm.affirmRelationNo.value=affirmRelationNo;
	document.affirmForm.deleteallbydept.value="false";
}
	
function band2(backColor,textColor,deptid,deleteallbydept)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;

	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;

	preEl = el;

	document.affirmForm.deptid.value=deptid;
	document.affirmForm.deleteallbydept.value=deleteallbydept;
	document.affirmForm.affirmRelationNo.value='none';
	//alert(document.affirmForm.deptid.value);
}


//---------------end-----------------------


    function Add()
    {   
        url="/sy/<%=menu_code%>_a_new.jsp?menu_code=<%=menu_code%>&x="+document.affirmForm.x.value+"&y="+document.affirmForm.y.value;
        location.href=url;
    }
    function Update()
    {   

		if (document.affirmForm.typeid.value=="" )
		 {
            alert(msg[0]);
		  }
		 else
			 {

			 var cpnyIdGz = document.affirmForm.cpnyIdGz.value;
        url='/sy/<%=menu_code%>_a_new.jsp?menu_code=<%=menu_code%>';
		url=url+"&cpnyIdGz="+document.affirmForm.cpnyIdGz.value+
			"&affirmtypeId="+document.affirmForm.typeid.value
			+"&affirmorid="+document.affirmForm.empid.value
			+"&affirmor="+encodeURIComponent(document.affirmForm.affirmor.value)+"&x="+document.affirmForm.x.value+"&y="+document.affirmForm.y.value;
        location.href=url;

			}

    }
    function Delete()
    { 
		if (document.affirmForm.affirmRelationNo.value=="" )
		 {
            alert(msg[1]);
		 }
		 else
		{        
			if( confirm(msg[2]) ) 
			{
				url='/sy/sy0106_t_new.jsp?menu_code=<%=menu_code%>&levelflag=deleteandback&affirmRelationNo='+
					document.affirmForm.affirmRelationNo.value+'&affirmid='+document.affirmForm.affirmor.value
					+'&affirmtypeId='+document.affirmForm.typeid.value+'&cpnyIdGz='+
					document.affirmForm.cpnyIdGz.value+'&empId='+
					"&deleteallbydept="+document.affirmForm.deleteallbydept.value+"&x="+document.affirmForm.x.value+"&y="+document.affirmForm.y.value;
			    location.href=url;
			}
			else
			{
				return;
			}
		}
    }

function querybydept(){
	if (document.affirmForm.selectemp.value == "按员工查询")
		document.affirmForm.selectemp.value = "";
	var cpnyIdGz = document.affirmForm.cpnyIdGz.value;
//	url="/sy/<%=menu_code%>.jsp?menu_code=<%=menu_code%>&deptid="+document.affirmForm.selectdept.value+"&empid="+document.affirmForm.selectemp.value+"&affirmId="+document.affirmForm.personId.value;
//	url="/sy/<%=menu_code%>.jsp?menu_code=<%=menu_code%>&cpnyIdGz="+cpnyIdGz+"&affirmId="+document.affirmForm.personId.value;

	location.href=encodeURI(url);
}
var  empidpatrn=/^[A-Za-z]{2}[0-9]{7}$/;
function batch(){
    //"更新后将更新所有申请但未决裁的信息!\n\n   确认?　"
    if(confirm('<ait:message messageID="alert.sys.authority.approval.update" module="sys"/>')){
	    var oldaffirmid=document.affirmForm.oldAffirmId.value;
	    var newaffirmid=document.affirmForm.newAffirmId.value;
	    var affirmRelationNo=document.affirmForm.affirmRelationNo.value;
	    var oldPersonId=document.affirmForm.oldPersonId.value;
	    var newPersonId=document.affirmForm.newPersonId.value;
	    ///alert(oldPersonId);
	    if(oldaffirmid==""){
	    	//alert("请选择决裁者！");
	    	alert('<ait:message messageID="alert.sys.authority.approval.select_approver" module="sys"/>') ;
	    	return;
	    }
	   /*   修改验证
	   if(!empidpatrn.test(oldaffirmid)){
	    alert("请输入正确的职号！");
	    document.affirmForm.oldAffirmId.focus();
	    return;
	    }*/
	    if(newaffirmid==""){
	    	//alert("请选择新决裁者！");
	    	alert('<ait:message messageID="alert.sys.authority.approval.new_approver" module="sys"/>');
	    	return;
	    }
	    /*   修改验证
	    if(!empidpatrn.test(newaffirmid)){
	    alert("请输入正确的职号！");
	    document.affirmForm.newAffirmId.focus();
	    return;
	    }
	    */
	  ///  var isUpdate=(document.affirmForm.check.checked==true)?1:0;
		
		
		 var affirmTypeId = document.affirmForm.affirmTypeId.value;
		url='/sy/sy0106_t_new.jsp?menu_code=<%=menu_code%>&levelflag=batch&affirmid='+oldPersonId+'&newaffirmid='
		+newPersonId+'&affirmRelationNo='+affirmRelationNo+"&x="+document.affirmForm.x.value+"&y="+document.affirmForm.y.value
		+ "&affirmTypeId=" + affirmTypeId;
	    //alert(url) ;
	    location.href=url;
    }
}

function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
}

function updateValue(cell,mark,vname) {
			
			$(mark).value=cell.childNodes[0].firstChild.nodeValue;
			$(vname).value=cell.childNodes[2].firstChild.nodeValue;
            layerClose();
}

var time=null;
function selectEMP(condition,id,lleft,operation,vname){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id,lleft,operation,vname);
					},500);  
}

function SearchE(condition,id,lleft,operation,vname){
     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=" + operation + "&condition=" + encodeURIComponent(condition)+"&mark="+id+"&vname="+vname;
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
		layer.style.height = 48; 
    }else if(size<210){
		layer.style.height = size;  
    }else{
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
    layer.style.visibility = 'visible';
}


-->
</script>
<%	AffirmDAONew affirmdaodept = new AffirmDAONew();
//String deptid=StringUtil.checkNull(request.getParameter("deptid"));
	String cpnyIdGz=StringUtil.checkNull(request.getParameter("cpnyIdGz"));
//	String affirmId=StringUtil.checkNull(request.getParameter("affirmId"));
	
	String x = request.getParameter("strPage")!=null?request.getParameter("strPage"):"1";
	String y= request.getParameter("bigpage")!=null?request.getParameter("bigpage"):"1";     
	PageControl pc=new PageControl(10,10);
	int bigpage=pc.getTmpBig(y);
	int strPage=pc.getTmpSmall(x,bigpage);
	String key=request.getParameter("key")!=null?request.getParameter("key"):"";
    String where =request.getParameter("where")!=null?request.getParameter("where"):"";
    pc.setintPage(strPage,bigpage);
    AdminBean adminBean = (AdminBean)session.getAttribute("admin");
    
    request.setAttribute("cpnyIdGz", cpnyIdGz);
    
    String PERSON_ID = adminBean.getAdminID();
    String cnpy_ID = adminBean.getCpnyId();
	Vector empid_vector = new Vector();
	Vector deptid_vector = new Vector();
	Vector empid_vector_full = affirmdaodept.getEmpList1(PERSON_ID);
	Vector deptid_vector_full = affirmdaodept.getDeptList(PERSON_ID);
		String SQL = " select h.cpny_id COMPANY_ID,h.cpny_location AFFIRM_OBJECT_NAME,"
        	+"h.cpny_name AFFIRM_OBJECT_EN_NAME,'' FOURTHDEPTNAME from hr_company h where h.activity = 1 " ;
		pc.setRowCount(SQL);
		deptid_vector = affirmdaodept.getCpnyListByAffirm();
	String thispage=request.getParameter("strPage")!=null?request.getParameter("strPage"):"1";
	String group=request.getParameter("bigpage")!=null?request.getParameter("bigpage"):"1";
	request.setAttribute("thispage",(NumberUtil.parseInt(group)-1)*10+NumberUtil.parseInt(thispage));
%>
                     

<table width="100%" border="0" cellspacing="0" cellpadding="0">

<form name="affirmForm">  
	<tr style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif" style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_all.jsp" %>
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
		
		
		<!-- display 3 level menu -->
		<%@ include file="../sy/inc/sy_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
		    <tr>
			 <td class="info_title_01" align="left"><!-- 决裁者 -->
	          <ait:message messageID="display.mutual.approver" ></ait:message>	
	          </td>
			</tr>
		</table>		
		<% 	
				Vector vector = new Vector();
				try{
					vector = SysCodeParser.getCode("ApplyTypeCode",1);
				}catch (Exception e){
				}
		%>					
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		
		   <tr>
	          <td align="left" colspan="8">
	          <select name="affirmTypeId">
	          	<option >请选择决裁类型</option>
	          <% for(int j=0;j<vector.size();j++){
					codemap = (HashMap) vector.elementAt(j);
					out.println("<option value=\"" + StringUtil.editNbsp((String)codemap.get("code")) + "\">" + StringUtil.editNbsp((String)codemap.get("name")) +"</option>") ; 
				}%>
	        </select>  
	          &nbsp;&nbsp;:&nbsp;&nbsp;
	          决裁者
	          <input type="text"  name="oldAffirmId" id="oldAffirmId" value="" size="7" onKeyUp="javascript:selectEMP(this.value,this.id,200,'syAffirmSearchEmployee','oldPersonId');" style="width:60;height:18">
	          <input type="hidden" name="oldPersonId" id="oldPersonId" value="">
		      &nbsp;&nbsp;:&nbsp;&nbsp;   新决裁者 
		         <input type="text"  name="newAffirmId" id="newAffirmId" value="" size="7" onKeyUp="javascript:selectEMP(this.value,this.id,200,'essSearchEmployee','newPersonId');" style="width:60;height:18">
		         <input type="hidden" name="newPersonId" id="newPersonId" value="">
		         更新数据	       
	          <img src="../images/btn/Save_little.gif" style="cursor:hand" align="absmiddle" onclick="javascript:batch();" ></td>
	      </tr>
	     
		  <tr align="center" style='position: relative; top: expression(this.offsetParent.scrollTop);'>
		    <td height="30"  class="info_title_01"><!-- 序号 -->
		 <ait:message messageID="display.mutual.no" ></ait:message>	   
		    </td>
		    
		     <td height="30"  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
				法人区分
		    </td>
		     
			<% for(int j=0;j<vector.size();j++){
					codemap = (HashMap) vector.elementAt(j);
		    %>
		    <td height="30"  class="info_title_01" nowrap="nowrap">
		    	<ait:content enContent='<%=StringUtil.editNbsp((String)codemap.get("enname"))%>'  zhContent='<%=StringUtil.editNbsp((String)codemap.get("name")) %>'></ait:content>
		    </td>
		    <% 
				//	out.println("<td height=\"30\" class=\"info_title_01\">" + StringUtil.editNbsp((String)codemap.get("name"))+"</td>");
				}%>
		  </tr>
		<% List affirm_list= new ArrayList();                
			
			for(int i=0; i<deptid_vector.size();i++)
			{
				affirmDept = (Affirm)deptid_vector.elementAt(i);
		   %>
		  <tr align="center" >
		    <td height="30"  align="center" 
			onClick="band2('#E7F0EF','black','<%=affirmDept.getAffirmObject()%>','true')" >
					<%=i+1%></td>
			
		    <td height="30"   align="center" style="color: #666666;" nowrap="nowrap">
		    	<%=StringUtil.editNbsp(affirmDept.getAffirmObjectName())%>
		    </td>
		  
		   <%for(int k=0;k<vector.size();k++){		
				codemap = (HashMap) vector.elementAt(k);   
				affirm_list = affirmdaodept.getAffirmRelation(affirmDept.getAffirmObject(),(String)codemap.get("code"),"1");
		   %>
		   <td align="center">
		   <table  align="center">
		     <%if(affirm_list.size()==0){ %>
		           <tr  onClick="band('red','black','<%=affirmDept.getAffirmObject()%>','<%=codemap.get("code")%>','','','')">
				   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>				  		  	   
				   </tr>
		     <%}else{ %>
				   <%for (int j=0;j<affirm_list.size();j++){ 
				     affirm =(Affirm) affirm_list.get(j);%>
				   <tr>
				   <td><%=affirm.getAffirmLevel()%>&nbsp;</td>
				   <td onClick="band('red','black','<%=affirmDept.getAffirmObject()%>','<%=codemap.get("code")%>','<%=affirm.getAffirmorID()%>','<%=affirm.getAffirmorName()%>','<%=affirm.getAffirmRelationNo() %>','<%=affirm.getCompanyID() %>')"><%=StringUtil.checkNull(affirm.getAffirmorName())%></td>			   
				   </tr>
				   <%}%>
			 <%}%>
		   </table>
		   </td> 
		   <%}%>
		</tr>
          <%} %>
		</table>
		<input name="x" value="<%=x%>" type="hidden">
		<input name="y" value="<%=y%>" type="hidden">
		<table><tr>
		<td> <%@ include file = "../inc/pagecontrol.jsp"%></td></tr>
		</table>
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
 <input type="hidden" name="selectemp" id="selectemp" value="">
<input type="hidden" name="deptid" value="">
<input type="hidden" name="typeid" value="">
<input type="hidden" name="cpnyIdGz" value="">
<input type="hidden" name="empid" value="">
<input type="hidden" name="affirmor" value="">
<input type="hidden" name="affirmRelationNo" value="">
<input type="hidden" name="deleteallbydept" value="">
</form>
<br>
  <div id="emp_list" style="position:absolute;overflow:auto; top:100;width:370; z-index:1;"  >        
  </div>
</body>
</html>