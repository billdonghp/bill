<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.ait.sy.dao.AffirmDAO"%>
<%@page import="com.ait.util.StringUtil"%>
<%@page import="com.ait.util.NumberUtil"%>
<%@page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="menu_code" class="java.lang.String" scope="page"/>
<jsp:useBean id="pc" class="com.ait.utils.PageControl" scope="request"/>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="affirmDept" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="affirmEmp" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="affirm" class="com.ait.sy.bean.Affirm" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap" />
<html>
<head>
<!-- ess_instead_affirm.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/prototype.js"></script>
<script language="javascript">
<!--
var  empidpatrn=/^[A-Za-z]{2}[0-9]{7}$/;
function updateAffirm(){
    //"更新后将更新所有申请但未决裁的信息!\n\n   确认?　"
    if(confirm('<ait:message messageID="alert.sys.authority.approval.update" module="sys"/>')){
	    var newaffirmid=document.ApplyForm.newAffirmId.value;
	    if(newaffirmid==""){
	    	//alert("请选择新决裁者！");
	    	alert('<ait:message messageID="alert.sys.authority.approval.new_approver" module="sys"/>');
	    	return;
	    }
	    if(!empidpatrn.test(newaffirmid)){
	      alert("请输入正确的职号！");
	      document.ApplyForm.newAffirmId.focus();
	      return;
	    }
	    
	    document.ApplyForm.type.value = "instead" ;
	    document.ApplyForm.submit();
    }
}

function recoverAffirm(){
    //"确认要恢复系统默认决裁者?"
    if(confirm('确认要恢复系统默认决裁者?')){
    	document.ApplyForm.newAffirmId.value = '' ;
    	document.ApplyForm.type.value = "recover" ;
	    document.ApplyForm.submit();
    }
}

function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
}

function updateValue(cell,mark) {
			
			$(mark).value=cell.childNodes[0].firstChild.nodeValue;
			$('name').innerHTML=cell.childNodes[1].firstChild.nodeValue;
			document.getElementById('person_id').value=cell.childNodes[2].firstChild.nodeValue;
            layerClose();
}

var time=null;
function selectEMP(condition,id,lleft){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id,lleft);
					},500);  
}

function SearchE(condition,id,lleft){
     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=essSearchInsteadAffirm&condition=" + encodeURIComponent(condition)+"&mark="+id;
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft-lleft;    //文本框的定位点宽	
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		layer = $('emp_list');
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;   
		layer.style.height = 210;         
		layer.style.visibility = 'visible';
		new Ajax.Updater({success: layer}, url, {method: 'post', parameters: pars, onFailure: layerClose});	
}

-->
</script>
<%	AffirmDAO affirmdaodept = new AffirmDAO();
	String deptid=StringUtil.checkNull(request.getParameter("deptid"));
	String empid=StringUtil.checkNull(request.getParameter("empid"));
	String affirmId=StringUtil.checkNull(request.getParameter("affirmId"));
	
	String x = request.getParameter("strPage")!=null?request.getParameter("strPage"):"1";
	String y= request.getParameter("bigpage")!=null?request.getParameter("bigpage"):"1";  
	
	//PageControl pc=new PageControl(10,10);
	int bigpage=pc.getTmpBig(y);
	int strPage=pc.getTmpSmall(x,bigpage);
	String key=request.getParameter("key")!=null?request.getParameter("key"):"";
    String where =request.getParameter("where")!=null?request.getParameter("where"):"";
    pc.setintPage(strPage,bigpage);
    AdminBean adminBean = (AdminBean)session.getAttribute("admin");
    String PERSON_ID = adminBean.getAdminID();
    String cnpy_ID = adminBean.getCpnyId();
	Vector empid_vector = new Vector();
	Vector deptid_vector = new Vector();
	Vector empid_vector_full = affirmdaodept.getEmpList2(PERSON_ID);
	Vector deptid_vector_full = affirmdaodept.getDeptAgentList(PERSON_ID);
	if (empid.equals("") && deptid.equals("")){
		empid_vector = affirmdaodept.getEmpListByAgentAffirm(affirmId,PERSON_ID);
		String affirmSql="";
		if(!affirmId.equals("")){
            affirmSql=" AND AFFIRMOR_ID='"+affirmId+"'";
        }
		String SQL = "  (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
            +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
            +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
            +"      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
            +" WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID "
            +"   AND HDT.FOURTHDEPT = HD.DEPTID " + affirmSql +" and HD.CPNY_ID='"+cnpy_ID
            +"' )ORDER BY FOURTHDEPTID,DEPTID " ;
		pc.setRowCount(SQL);
		deptid_vector = affirmdaodept.getDeptListByAgentAffirm(PERSON_ID,affirmId,pc,cnpy_ID);
	} else if (!empid.equals("") && deptid.equals("")){
		empid_vector = affirmdaodept.getEmpList(empid,cnpy_ID);
	} else if (empid.equals("") && !deptid.equals("")){
		deptid_vector = affirmdaodept.getDeptList(deptid,pc,cnpy_ID);
		String SQL = " (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
            +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
            +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
            +"      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
            +" WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID AND HDT.DEPTID = HD.DEPTID and HD.cpny_ID='"+ cnpy_ID +"') T "
            +" WHERE EXISTS (SELECT B1.DEPTID FROM HR_DEPARTMENT B1 WHERE B1.DEPTID = T.DEPTID "
            +" AND B1.DEPTID = '"+deptid+"' START WITH B1.DEPTID = '"+deptid+"' CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID) "
            +" ORDER BY FOURTHDEPTID,DEPTID ";
		pc.setRowCount(SQL);
	} else {
		empid_vector = affirmdaodept.getEmpList(empid,cnpy_ID);
		String SQL = "  (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
            +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
            +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
            +"      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
            +" WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID AND HDT.DEPTID = HD.DEPTID and HD.cpny_ID='"+ cnpy_ID +"') T "
            +" WHERE EXISTS (SELECT B1.DEPTID FROM HR_DEPARTMENT B1 WHERE B1.DEPTID = T.DEPTID "
            +" AND B1.DEPTID = '"+deptid+"' START WITH B1.DEPTID = '"+deptid+"' CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID) "
            +" ORDER BY FOURTHDEPTID,DEPTID ";
		pc.setRowCount(SQL);
		deptid_vector = affirmdaodept.getDeptList(deptid,pc,cnpy_ID);
	}
	
	String thispage=request.getParameter("strPage")!=null?request.getParameter("strPage"):"1";
	String group=request.getParameter("bigpage")!=null?request.getParameter("bigpage"):"1";
	request.setAttribute("thispage",(NumberUtil.parseInt(group)-1)*10+NumberUtil.parseInt(thispage));
	
	
%>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
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
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->   
		<br>
		<form name="ApplyForm" method="post" action="/essControlServlet">
		    <input type="hidden" name="operation" value="modify" />
			<input type="hidden" name="content" value="insteadAffirm" />
			<input type="hidden" name="type" value="" />
			<input type="hidden" name="menu_code" value="${param.menu_code}"/>
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" height="100%">
			<tr>
				<td align="left" class="title1" colspan="10">代决裁设置		
				</td>
			</tr>
		  <table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center">
		    	<td class="info_title_01"  colspan="2">系统默认决裁者是:<font color="red"><%= admin.getChineseName() %> </font>
		    	&nbsp;&nbsp;恢复数据	
		          <img class="menuaa" src="../images/btn/Save_little.gif" align="absmiddle" onclick="javascript:recoverAffirm();" >
		    	</td>
		    </tr>
		    <tr align="center">
		    	<td class="info_title_01"  colspan="2">代决裁者是:<font color="red">${insteadAffirm.CHINESENAME}</font></td>
		    </tr>
		    <tr align="center" >
			    <td class="info_title_01" >
			    模块
			    <select name="module">
	          	<option value='AR'>考勤</option>
	          	<option value='GA'>总务</option>
	        	</select>
		         </td>
			    <td class="info_title_01"  colspan="2">
		          <!-- 新决裁者 --> 
			      <ait:message messageID="display.ess.authority_management.approval_management.new_approver" module="sys"></ait:message>	   
			         <input type="text"  name="newAffirmId" id="newAffirmId" value="${newAffirmId}" size="10" onKeyUp="javascript:selectEMP(this.value,this.id,150);" style="width:70;height:18">
			         <input type="hidden"  name="person_id" id="person_id" value=''>
			         &nbsp;(<span id="name"></span>)&nbsp;&nbsp;<!--  更新数据--> 
			       <ait:message messageID="display.ess.authority_management.approval_management.update_data" module="sys"></ait:message>	
		          <img class="menuaa" src="../images/btn/Save_little.gif" align="absmiddle" onclick="javascript:updateAffirm();" >
		         </td>
		    </tr>
		  </table>
		</form>
		
		<c:if test="${error != null}">
			<table>
					<tr>
						<td><font color="red">
											<c:if test="${error == 'errorType1'}">所选择的员工,不在权限之内!!!</c:if>
											<c:if test="${error == 'errorType2'}">所选择的员工,与要替换的决裁信息冲突,请先经行决裁,再进行替换!!!</c:if>
											<c:if test="${error == 'errorType0'}" >决裁者替换成功!!!</c:if>
											<c:if test="${error == 'errorType'}" >恢复决裁者成功!!!</c:if>
							</font></td>
					</tr>
			</table>
		</c:if>
		<% 	
				Vector vector = new Vector();
				try{
					vector = SysCodeParser.getCodeInstead("ApplyTypeCode",1);
				}catch (Exception e){
				}
		%>	

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
</table>

</body>
  <div id="emp_list" style="position:absolute;overflow:auto; top:100;width:370; z-index:1;"  >        
  </div>
</html>
