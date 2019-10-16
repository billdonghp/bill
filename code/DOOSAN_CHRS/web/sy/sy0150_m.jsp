<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.util.SysCodeParser,com.ait.sy.sy0104.bean.Ent,com.ait.util.StringUtil" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.ait.utils.*"%>
<%@ page import="com.ait.sy.sy0104.bean.*"%>
<%@ page import="com.ait.sy.sy0102.bean.*"%>
<jsp:useBean id="codemap" class="java.util.HashMap" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/default.css">
<script src="../js/prototype.js"></script>
<script type="text/javascript">   
var msg=new Array('<ait:message messageID="alert.sys.authority.authority.edit.emp_user" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.id_invalid" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.enter_id" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_20" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.user" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_50" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.password" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.maximum_length_30" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.select_group" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.edit.select_level" module="sys"></ait:message>');
</script>
</head>
<body>                 
<jsp:include page="/inc/hrnav.jsp"/>
<%@ include file="../inc/importa150.jsp"%>                            
<SCRIPT language=JavaScript
      src="../js/table.js"></SCRIPT>
<script language="javascript">
function checksub()
{
var message=new Array('<ait:message messageID="alert.sys.authority.authority.id_empty" module="sys"></ait:message>',
                   '<ait:message messageID="alert.sys.authority.authority.id_first" module="sys"></ait:message>'
                   );
	if(document.EM2Form.empID.value=="")
	{            
		alert(message[0]);
		document.EM2Form.empID.focus();
		return false;
	}
	if(document.EM2Form.userID.value=="")
	{  
		alert(message[1]);
		document.EM2Form.userID.focus();
		return false;  
	}
	var url="syCheck0104.jsp?flag=update&empID="+document.EM2Form.empID.value+"&userID="+document.EM2Form.userID.value;
	window.open(url,'','width=200,height=50');
	return true;
}


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

	var affirmtypeId = document.all("affirmtypeId").value;
	alert("111");
	var key = document.EM2Form.person_id.value;
	alert(key);
	url='/sy/<%=menu_code%>_m.jsp?menu_code=<%=menu_code%>&affirmtypeId='+affirmtypeId+'&personId='+key;
    location.href=url;
	
}
function updateValue(cell) {
	$('key').value=cell.childNodes[0].firstChild.nodeValue;
	$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
	$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
	layerClose();
}

function ChangeCondition(){
    var affirmtypeId = document.all("affirmtypeId").value;
	var key = document.EM2Form.key.value;
	url='/sy/<%=menu_code%>_m.jsp?menu_code=<%=menu_code%>&affirmtypeId='+affirmtypeId+'&personId='+key;
    location.href=url;
}

</script>

<%	
	menu_code = request.getParameter("menu_code");
Ent ent = new Ent();
	Vector vlist2 = new Vector();
	com.ait.sy.sy0104.bean.Biz Biz2 = new com.ait.sy.sy0104.bean.Biz();
	String no = func.chkNull(request.getParameter("no"));
	String cpnyid=func.chkNull(request.getParameter("cpnyid"));
	String personId=func.chkNull(request.getParameter("personId"));
	String affirmtypeId=func.chkNull(request.getParameter("affirmtypeId"));
	request.setAttribute("key",personId);
	request.setAttribute("affirmtypeId",affirmtypeId);
	//获取裁决类型
	Vector affitypeid=(Vector)func.getAffirmTypeGroup(personId);
	//获取部门信息
	ArrayList depts=(ArrayList)Biz2.getTypeQuanxian(personId,affirmtypeId);
	String cpnyIdGz = request.getParameter("cpnyIdGz") != null? request.getParameter("cpnyIdGz"): "";
%>

<SCRIPT LANGUAGE="JavaScript" src="../js/sy0104_m.js"></SCRIPT>
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
				<td align="left" class="title1" colspan="10"><!-- 修改权限 -->
				担当确认 添加/修改				
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="30" align="left"><TABLE width="100%" border="1" align="center" cellPadding=4 cellSpacing=0 bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" rules=all>
		        <form  name="EM2Form" method="post" action="/Esy0120Control?flag=updataafftypid&menu_code=<%=menu_code%>&no=<%=no%>" onSubmit="return CheckForm()" >
		          <TBODY>
		            <TR>
		              <TD width="35" height="30" align=middle class="info_title_01">
						担当
		              </TD>
		              <td align="center" class="info_content_00" nowrap="nowrap">
				<input id="person_id" name="person_id" type="hidden" value="${person_id }"> 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			</td>		             
		            </TR>
		            <tr>
		              
		              <td width="35" height="30" width="15%" class="info_title_01"><!-- 决裁类型 -->
				<ait:message
					messageID="display.sys.authority.management.edit.approval_type"
					module="sys"></ait:message></td>
	        <td height="30" width="65" class="info_content_00"><select id="affirmtypeId" name="affirmtypeId" style="width: 160px " onChange="javascript:ChangeCondition();">
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
		            
		            <TR>
		              <TD width="35" height="266" align=middle vAlign=top class="info_title_01"><!-- 权限级 -->
						法人区分
		              </TD>
		              <TD width="65" vAlign=top><%@ include file="../sy/zpss_org150up.jsp"%></TD>
		            </TR>
		          </TBODY>                 
		        </FORM>
		      </TABLE></td>
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
</body>
</html>