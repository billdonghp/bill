<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" 
	import="java.sql.*,
	com.ait.sy.bean.*,
	com.ait.util.SysCodeParser,
	com.ait.sy.sy0104.bean.*,
	com.ait.sy.sy0104.bean.Ent,
	com.ait.util.StringUtil" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="codemap" class="java.util.HashMap" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
<script src="../js/prototype.js"></script>
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.select_edit_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.select_delete_list" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.associated_deleting" module="sys"></ait:message>');
</script>
</head>
<body>
<jsp:include page="/inc/hrnav.jsp" />
<%@ include file="../inc/import150.jsp"%>
<script language="javascript">
MENU_CODE='<%=menu_code%>';

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	
function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}
function updateValue(cell) {
	$('key').value=cell.childNodes[0].firstChild.nodeValue;
	$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
	$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
	layerClose();
}

function Search()
{

	url="/sy/<%=menu_code%>.jsp?menu_code=<%=menu_code%>&affirmtypeId="+document.form1.affirmtypeId.value+"&person_id="+document.form1.person_id.value+"&cpnyIdGz="+document.form1.cpnyIdGz.value;
	location.href=encodeURI(url);
}



</script>
<%		

		AdminBean adminBean = (AdminBean)session.getAttribute("admin");
		String PERSON_ID = adminBean.getAdminID();
		String cnpy_ID = adminBean.getCpnyId();
		Ent ent = new Ent();
		String cpnyIdGz = request.getParameter("cpnyIdGz") != null? request.getParameter("cpnyIdGz"): "";
		String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
		String person_id = request.getParameter("person_id")!=null?request.getParameter("person_id"):"";
		request.setAttribute("cpnyIdGz", cpnyIdGz);
		request.setAttribute("empid", person_id);
		request.setAttribute("affirmtypeId", affirmtypeId);
		if( !affirmtypeId.equals("") || !person_id.equals("") || !cpnyIdGz.equals("")){
			vlist = Biz.ListSQLUndertake(affirmtypeId,person_id,cpnyIdGz); 
		}else{
			vlist = Biz.ListSQLUndertakeOne(cnpy_ID); 
		}
		
		
%>
<form name="form1" method="post" action=""><input type="hidden"
	name="ck_all" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../sy/inc/sy_toolbar_notUpdateButton.jsp" %>
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
		<%@ include file="../sy/inc/sy_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			
	    <tr>
	      <td colspan="9">
	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	       <%--
	      	<td height="30" class="info_title_01">法人区分</td>
			<td height="30" class="info_content_00"><ait:codeClass
				codeClass="EMP_DIFF" name="cpnyIdGz" selected="${cpnyIdGz}" /></td>
				--%>
				<td width="15%" class="info_title_01" align="center">法人区分</td>
		      <td width="25%" class="info_content_00"><ait:codeClass name="cpnyIdGz" selected="${cpnyIdGz}"	 codeClass="EMP_DIFF" all="all"/></td>
				
			<td height="30" width="15%" class="info_title_01"><!-- 决裁类型 -->
				<ait:message
					messageID="display.sys.authority.management.edit.approval_type"
					module="sys"></ait:message></td>
	        <td height="30" width="35%" class="info_content_00"><select name='affirmtypeId' id="affirmtypeId" style="width: 160px ">
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
			<td class="info_title_01" width="10%">
				<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>
	      	</td>
	        <td align="center" class="info_content_00" nowrap="nowrap">
				<input id="person_id" name="person_id" type="hidden" value="${person_id }"> 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			</td>
			
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
			 </td>
			 </tr>
			 </table>
			 </td>
	        </tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--权限  -->
				   <ait:message messageID="display.sys.authority_management.authority" module="sys"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%"  border="0"  cellpadding="0" cellspacing="0">
		  <tr>
		    <td colspan="2" class="line">
		    <table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		        <tr>
		          <td height="30" class="info_title_01"><!-- 员工号 -->
		          <ait:message messageID="display.mutual.emp_id_2" ></ait:message>   
		          </td>
		          <td height="30" class="info_title_01"><!-- 姓名 -->
		           <ait:message messageID="display.mutual.name"></ait:message>  
		          </td>
		          <td height="30" class="info_title_01">法人</td>		     
		          <td height="30" class="info_title_01">
					决裁类型
		          </td>
		          <td height="30" class="info_title_01">
		          	创建日期
		          </td>  
		        </tr>                    
		        <%
				int listNo = 1 ;
				for ( int i = 0 ; i < vlist.size() ; i++ ){
					ent=(Ent)vlist.elementAt(i) ;
				%>
		        <tr align="center"  bgcolor="#FFFFFF" onClick="HighLightTR('#E7F0EF','black','<%=ent.getLoginNo()%>','<%=menu_code%>','<%=ent.getComfirm_cpnyid()%>','<%=ent.getAffirm_type_id()%>','<%=ent.getPerson_id()%>')">
		        
		          <td ><%=ent.getEmpID()%></td>                
		          <td align="center" style="color: #666666;"><%=ent.getChineseName()%></td>
		          <td align="center" style="color: #666666;"><%=ent.getComfirm_cpnyid()!=null?ent.getComfirm_cpnyid():""%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=ent.getAffirm_type_id()%>&nbsp;</td>
		          <td align="center" style="color: #666666;"><%=ent.getCreateDate()%></td>
		        
		        </tr>
		        <%}%>
		      </table></td>
		  </tr>              
		  <tr>
		  </tr>
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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</form>
</body>
</html>
