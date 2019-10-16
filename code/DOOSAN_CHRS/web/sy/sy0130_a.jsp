<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="com.ait.hrm.bean.*,
	com.ait.util.StringUtil,
	com.ait.hrm.business.*,
	com.ait.util.*,
	com.ait.sy.dao.*,
	com.ait.sqlmap.util.SimpleMap,
	java.util.*,
	com.ait.sy.sy0106.bean.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<script src="../js/prototype.js"></script>
<jsp:useBean id="codemap" class="java.util.HashMap" scope="request"/>
<jsp:useBean id="affirmDAO" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="department" class="com.ait.hrm.bean.Department" scope="request"/>
<jsp:useBean id="affirmor_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="affirm" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="empList" class="java.util.ArrayList" scope="page"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CONFIRMOR SETTING</title>
</head>
<body >
<%@ include file="../inc/import.jsp"%>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.authority.approval.select_type" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.approval.select_dept" module="sys"></ait:message>');
</script>
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
    var menu_code = document.all("menu_code").value;
	location.href="/sy/<%=menu_code%>.jsp?menu_code="+menu_code+"&strPage="+document.all("x").value+"&bigpage="+document.all("y").value;
}


function ChangeCondition(){
    var affirmtypeId = document.all("affirmtypeId").value;
    var deptcodeId = document.all("deptcodeId").value;
	var empId = document.affirmForm.empId.value;
	url='/sy/<%=menu_code%>_a.jsp?menu_code=<%=menu_code%>&affirmtypeId='+affirmtypeId+'&deptcodeId='+deptcodeId+'&empId='+empId+"&x="+document.all("x").value+"&y="+document.all("y").value;
    
    location.href=url;
}
function levelchange(affirmRelationNo,affirmid,levelflag){
    var affirmtypeId = document.all("affirmtypeId").value;
    var deptcodeId = document.all("deptcodeId").value;
	var empId = document.affirmForm.empId.value;
	url='/sy/sy0106_t.jsp?menu_code=<%=menu_code%>&levelflag='+levelflag+'&affirmRelationNo='+affirmRelationNo+'&affirmid='+affirmid+'&affirmtypeId='+affirmtypeId+'&deptcodeId='+deptcodeId+'&empId='+empId+"&x="+document.all("x").value+"&y="+document.all("y").value;

    location.href=url;
}

function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
}

function updateValue(cell) {			
	$('affirmor').value=cell.childNodes[2].firstChild.nodeValue;
	location.href="/sy/sy0106_emp_t.jsp?menu_code=<%=menu_code%>&affirmor="+cell.childNodes[0].firstChild.nodeValue+"&affirmorid="+cell.childNodes[2].firstChild.nodeValue+"&empId="+document.affirmForm.empId.value+"&deptcodeId="+document.all('deptcodeId').value+"&affirmtypeId="+document.all('affirmtypeId').value+"&x="+document.all("x").value+"&y="+document.all("y").value; 
	
	layerClose();
}

function  addNull(){
    var type = document.affirmForm.affirmtype.value;
	var affirmTarget =document.affirmForm.deptcode.value;
	if(type ==''){
		alert(msg[0]);
		document.affirmForm.affirmtype.focus();
		return;
	}
	if(affirmTarget ==''){
		alert(msg[1]);           
		document.affirmForm.deptcode.focus();
		return;
	}
	location.href="/sy/sy0106_emp_t.jsp?menu_code=<%=menu_code%>&empId="+document.affirmForm.empId.value+"&deptcodeId="+document.all('deptcodeId').value+"&affirmtypeId="+document.all('affirmtypeId').value+"&x="+document.all("x").value+"&y="+document.all("y").value; 

}


var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
	var type = document.affirmForm.affirmtype.value;
	var affirmTarget =document.affirmForm.deptcode.value;
	if(type ==''){
		alert(msg[0]);
		document.affirmForm.affirmtype.focus();
	}
	if(affirmTarget ==''){
		alert(msg[1]);           
		document.affirmForm.deptcode.focus();
	}
	if(type!='' &&affirmTarget!= ''){
		var affirmtypeId = $("affirmtypeId").value;
		var deptcodeId = $("deptcodeId").value;
		var empId = document.affirmForm.empId.value;
		
		if(affirmtypeId!=''){
	     	var url = "/ajaxControlServlet" ;
	     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
			var inputBox = $(id);
			var iBtop  = inputBox.offsetTop;     //文本框的定位点高
			var iBheight  = inputBox.clientHeight;  //文本框本身的高
			var iBleft = inputBox.offsetLeft;    //文本框的定位点宽	
			layer = $('emp_list');
			while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
			
			layer.style.top = iBtop+iBheight+6;
			layer.style.left = iBleft;      
			layer.style.visibility = 'visible';
			new Ajax.Updater({success: layer}, url, {method: 'post', parameters: pars, onFailure: layerClose});	
		}else{
			layer.style.visibility ='hidden';           
		}
	}
}



function onload()
{
if(document.affirmForm.affirmor.value!=""){
       document.all.layername.style.visibility='visible';
	}else{
       document.all.layername.style.visibility='hidden';
	}
}
 //-->
</SCRIPT>
<%


	SimpleMap deptMap=new SimpleMap(); 	

	List deptList=new ArrayList();
	AdminBean adminBean = (AdminBean)session.getAttribute("admin");
	deptMap.put("cpnyId",adminBean.getCpnyId());
	deptList=HrmServices.getInstance().retrieveDeptTree(deptMap);
	
	String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
	String deptcodeId = request.getParameter("deptcodeId")!=null?request.getParameter("deptcodeId"):"";
	String empId = StringUtil.checkNull(request.getParameter("empId"));
	String affirmor = request.getParameter("affirmor")!=null?StringUtil.toCN(request.getParameter("affirmor")):"";
	String affirmorid = StringUtil.checkNull(request.getParameter("affirmorid"));
	
	SimpleMap affirmorMap=new SimpleMap();
	affirmorMap.setString("EMPID",affirmorid) ;
	Object firstAffirmor = HrmServices.getInstance().getSimpleEmpByEmpId(affirmorMap) ;
	request.setAttribute("firstAffirmor",firstAffirmor) ;
	
	SimpleMap empMap = new SimpleMap();
	empMap.put("DEPTID",deptcodeId);
	empMap.put("cpnyId",adminBean.getCpnyId());
	if( !deptcodeId.equals("")){
		empList =(ArrayList) HrmServices.getInstance().retrieveEmpList(empMap);
	 }
	
	if(!empId.equals("") && !affirmtypeId.equals("")){	 	
	   affirmor_list =(ArrayList) affirmDAO.getAffirmRelation(empId,affirmtypeId);
	}
	else if(!affirmtypeId.equals("") && !deptcodeId.equals("")){
		   affirmor_list =(ArrayList) affirmDAO.getAffirmRelation(deptcodeId,affirmtypeId);
	}
	String x = request.getParameter("x")!=null?request.getParameter("x"):"1";
	String y= request.getParameter("y")!=null?request.getParameter("y"):"1";    
	
%>

<form name="affirmForm" action="/sy/sy0106_t.jsp">
<input type="hidden" id="menu_code" value="<%=menu_code%>">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_a.jsp"%>  
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

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>   
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >               
			<tr>
				<td align="left" class="title1" colspan="10"><!--决裁者  -->
				<ait:message messageID="display.sys.authority.authority.add.approver" module="sys"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		   <tr align="center">
		    <td height="30" width="15%" class="info_title_01" ><!-- 决裁类型 -->
		    <ait:message messageID="display.sys.authority.management.edit.approval_type" module="sys"></ait:message>
		    </td>
		    <td height="30" width="85%" class="info_content_00"><select name="affirmtype" id="affirmtypeId" style="width: 160px " onChange="javascript:ChangeCondition();">
		            <option value="">select
		            </option>
		            <%
							try{
								Vector vector = SysCodeParser.getCode("ApplyTypeCode",1);
								for ( int i=0; i < vector.size(); i++)
								{
									codemap = (HashMap) vector.elementAt(i);
						%>
		            <option value="<%=codemap.get("code")%>" <%if(codemap.get("code").equals(affirmtypeId)){%> selected <%}%>>
		            <ait:content enContent='<%=StringUtil.checkNull(codemap.get("enname"))%>' zhContent='<%=StringUtil.checkNull(codemap.get("name"))%>'></ait:content>
		            </option>
		            <%	}              
							}catch (Exception e){      
							}
					%>
		          </select></td>
		  </tr>
		   <tr align="center">
		    <td height="30"  class="info_title_01" ><!-- 部门名称 -->
		    <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_name" module="sys"></ait:message>
		    </td>
		    <td height="30"  class="info_content_00">
		    <select name="deptcode" id="deptcodeId" style="width: 160px " onChange="javascript:ChangeCondition();">
		     <option value="">select
		     </option>
			 <%
			 for(int i=0; i<deptList.size(); i++){
			    department = (Department)deptList.get(i);
			 %>
			 <option value="<%=department.getDeptID()%>" <%if(department.getDeptID().equals(deptcodeId)){%> selected<%}%>>
			 <ait:content enContent='<%=StringUtil.checkNull(department.getDeptEnName())%>' 
			  zhContent='<%=StringUtil.checkNull(department.getDeptName())%>'></ait:content>
			 </option>
			 <%}%>        
			</select>
			<select name="empId"  onChange="javascript:ChangeCondition();">
			<option value="">select</option>
			<% 
				for(int i=0; i<empList.size(); i++){
					BasicInfo basicInfo = (BasicInfo) empList.get(i);
			%>
			
			<option value='<%=basicInfo.getPersonId()%>' <% if(basicInfo.getPersonId().equals(empId)){%> selected <%}%>>
			 <ait:content enContent='<%=StringUtil.checkNull(basicInfo.getPinyinName()) %>' 
			  zhContent='<%=StringUtil.checkNull(basicInfo.getChineseName())%>'></ait:content>
			</option>
			<%}%>
			</select>
			</td>
		  </tr>
		   <tr align="center">
		    <td height="30"  class="info_title_01" ><!--决裁者  -->
		    <ait:message messageID="display.sys.authority.authority.add.approver" module="sys"></ait:message>
		    </td>
		    <td height="30"  class="info_content_00">
			<input name="affirmor" id="affirmor" value="<ait:content enContent="${firstAffirmor.CHINESE_PINYIN}" zhContent="${firstAffirmor.CHINESENAME}" koContent="${firstAffirmor.KOREANNAME}"/>" type="text" style="width:160px " onKeyUp="SearchContent(this.value,this.id)">
			<ait:image src="/images/left_menubullet_main_p.gif"  border="0" align="absmiddle" 	onclick="addNull()" style="cursor:hand" title="添加空值" enTitle="add" /> 
		  </tr>
		   <tr align="center">
		    <td class="info_title_01" valign="middle"><!--决裁等级  -->
		    <ait:message messageID="display.sys.authority.management.authority_grade" module="sys"></ait:message>
		    </td>
		    <td class="info_content_00" height="170px" valign="top" >
			   <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;" >
			    <tr>
		           <td height="30"  class="info_title_01"><!-- 员工ID -->
		           <ait:message messageID="display.mutual.emp_id_2"></ait:message>
		           </td>
		           <td height="30"  class="info_title_01"><!--中文姓名  -->
		           <ait:message messageID="display.mutual.name"></ait:message>        
		           </td>
		           <td height="30"  class="info_title_01"><!-- 汉语拼音 -->
		           <ait:message messageID="display.mutual.pin_yin"></ait:message>
		           </td>
		           <td height="30"  class="info_title_01"><!-- 部门 -->
		           <ait:message messageID="display.mutual.department"></ait:message>
		           </td>
		           <td height="30"  class="info_title_01"><!-- 等级 -->
		           <ait:message messageID="display.sys.authority.management.rank" module="sys"></ait:message>
		           </td>
		           <td height="30"  class="info_title_01">是否决裁</td> 
		           <td height="30"  class="info_title_01"><!--操作 -->           
		           <ait:message messageID="display.mutual.operate"></ait:message>
		           </td>
				</tr>
				<%
				  if(affirmor_list.size()!=0){
				    for (int i= 0;i<affirmor_list.size();i++){
					    affirm = (Affirm)affirmor_list.get(i);
				%>
				<tr>
		           <td height="30"  class="info_content_01"><%=affirm.getEmpId()%>&nbsp;</td>
		           <td height="30"  class="info_content_01">
		           <ait:content enContent='<%=StringUtil.checkNull(affirm.getAffirmorName())%>'
		            zhContent='<%=StringUtil.checkNull(affirm.getAffirmorName())%>'></ait:content>  
		           &nbsp;</td>
		           <td height="30"  class="info_content_01"><%=affirm.getChinesePinYin()%>&nbsp;</td>
		           <td height="30"  class="info_content_01">                       
		             <ait:content enContent='<%=StringUtil.checkNull(affirm.getAffirmorDeptEnName())%>' 
		             zhContent='<%=StringUtil.checkNull(affirm.getAffirmorDeptName())%>'></ait:content>
		           &nbsp;</td>
		           <td height="30"  class="info_content_01"><%=affirm.getAffirmLevel()%>&nbsp;</td>
		           <td height="30"  class="info_content_01"><% if(affirm.getAffirmFlag().equals("1")){out.println("是");}else {out.println("否");} ; %>&nbsp;</td>
		           <td height="30"  class="info_content_01">
		           									<a href="javascript:levelchange(<%=affirm.getAffirmRelationNo()%>,'<%=affirm.getAffirmorID()%>','up');">
		           											<img src="../images/btn/up.gif" align="absmiddle"></a>§
				                                   <a href="javascript:levelchange(<%=affirm.getAffirmRelationNo()%>,'<%=affirm.getAffirmorID()%>','down');">
															<img src="../images/btn/down.gif" align="absmiddle"></a>§
												   <a href="javascript:levelchange(<%=affirm.getAffirmRelationNo()%>,'<%=affirm.getAffirmorID()%>','delete');">
															<img src="../images/btn/Delete_little.gif" align="absmiddle"></a></td>
				</tr>
				<%}}else{%>
				<tr><td height="30"  class="info_content_01" colspan="6"><!-- 请选定部门或员工，并设定决裁关系 -->！
			
				<ait:message messageID="alert.sys.authority.approval.approvee_approver"  module="sys"></ait:message>
				</td></tr>
				<%}%>
			</table> </td>
		  </tr>
		</table>
	    <input name="x" value="<%=x%>" type="hidden">
		<input name="y" value="<%=y%>" type="hidden">

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

</form>
<div id="emp_list" style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:1;"  >
        
  </div>
</body>
</html>
 