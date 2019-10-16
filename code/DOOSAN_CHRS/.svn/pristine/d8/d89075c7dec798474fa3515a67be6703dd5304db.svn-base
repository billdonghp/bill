<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="com.ait.hrm.bean.*,
	com.ait.hrm.business.*,
	com.ait.util.*,
	com.ait.sy.dao.*,
	com.ait.sqlmap.util.SimpleMap,
	java.util.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/><jsp:useBean id="codemap" class="java.util.HashMap" scope="request"/><jsp:useBean id="affirmDAO" class="com.ait.sy.dao.AffirmDAO" scope="request"/><jsp:useBean id="department" class="com.ait.hrm.bean.Department" scope="request"/><jsp:useBean id="affirmor_list" class="java.util.ArrayList" scope="request" /><jsp:useBean id="affirm" class="com.ait.sy.bean.Affirm" scope="request"/><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CONFIRMOR SETTING</title>
</head>
<body onLoad="onload()">
<%@ include file="/inc/hrtoolbar_back.jsp"%>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
    var menu_code = document.all("menu_code").value;
	url='/sy/sy0106.jsp?menu_code='+menu_code;
    location.href=url;
}
function ChangeCondition(){
    var affirmtypeId = document.all("affirmtypeId").value;
    var deptcodeId = document.all("deptcodeId").value;
	url='/sy/sy0106_a.jsp?menu_code=<%=menu_code%>&affirmtypeId='+affirmtypeId+'&deptcodeId='+deptcodeId;
    location.href=url;
}
function levelchange(affirmid,affirmlevel,levelflag){
    var affirmtypeId = document.all("affirmtypeId").value;
    var deptcodeId = document.all("deptcodeId").value;
	url='/sy/sy0106_t.jsp?menu_code=<%=menu_code%>&levelflag='+levelflag+'&affirmlevel='+affirmlevel+'&affirmid='+affirmid+'&affirmtypeId='+affirmtypeId+'&deptcodeId='+deptcodeId;
    location.href=url;
}
function SearchContent(affirmorid){
    var affirmtypeId = document.all("affirmtypeId").value;
    var deptcodeId = document.all("deptcodeId").value;
	if(affirmorid!=''){
        document.all.layername.style.visibility='visible';
	}else{
        document.all.layername.style.visibility='hidden';
	}
	emp_list.location.href = "/inc/SearchEmployee.jsp?deptcodeId="+deptcodeId+"&affirmorid="+affirmorid+"&affirmtypeId="+affirmtypeId;
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
	SimpleMap map=new SimpleMap();
	List deptList=new ArrayList();
	deptList=HrmServices.getInstance().retrieveDeptTree(map);
	
	
	
	String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
	String deptcodeId = request.getParameter("deptcodeId")!=null?request.getParameter("deptcodeId"):"";
	String affirmor = request.getParameter("affirmor")!=null?request.getParameter("affirmor"):"";
	
	if(!affirmtypeId.equals("") && !deptcodeId.equals("")){
	   affirmor_list =(ArrayList) affirmDAO.getAffirmRelation(deptcodeId,affirmtypeId);
	   } 
%>
<div id='layername' style="position:absolute; top:100; width:350; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; overflow: scroll; border: 1px none #000000; visibility: hidden;">
  <iframe width="350" height="200"  frameborder="0" scrolling="yes" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="emp_list"> </iframe>
</div>
<form name="affirmForm" action="/sy/sy0106_t.jsp">
  <input type="hidden" id="menu_code" value="<%=menu_code%>">
  <p align="left"> <span class="title1">系统管理>决裁>添加</span></p>
  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center">
      <td height="30"  align="center" bgcolor="#F5F5F5">项目名称</td>
      <td height="30"  align="center" bgcolor="#F5F5F5">项目值</td>
    </tr>
    <tr align="center">
      <td height="30"  align="center" >决裁类型</td>
      <td height="30"  align="left"><select name="affirmtype" id="affirmtypeId" style="width: 160px " onChange="javascript:ChangeCondition();">
          <option value="">请选择申请类型</option>
          <%
					try{
						Vector vector = SysCodeParser.getCode("ApplyTypeCode",1);
						for ( int i=0; i < vector.size(); i++)
						{
							codemap = (HashMap) vector.elementAt(i);
				%>
          <option value="<%=codemap.get("code")%>" <%if(codemap.get("code").equals(affirmtypeId)){%> selected <%}%>><%=codemap.get("name")%></option>
          <%	}
					}catch (Exception e){
					}
			%>
        </select></td>
    </tr>
    <tr align="center">
      <td height="30"  align="center" >部门名称</td>
      <td height="30"  align="left"><select name="deptcode" id="deptcodeId" style="width: 160px " onChange="javascript:ChangeCondition();">
          <option value="">请选择部门名称</option>
          <%
	 
	 for(int i=0; i<deptList.size(); i++){
	    department = (Department)deptList.get(i);
	 %>
          <option value="<%=department.getDeptID()%>" <%if(department.getDeptID().equals(deptcodeId)){%> selected<%}%>><%=department.getDeptName()%></option>
          <%}%>
        </select>
      </td>
    </tr>
    <tr align="center">
      <td height="30"  align="center" >决裁者</td>
      <td height="30"  align="left"><input name="affirmor" id="affirmor" value="<%=affirmor%>" type="text" style="width:160px " onKeyUp="SearchContent(this.value)">
    </tr>
    <tr align="center">
      <td align="center" valign="baseline">决裁等级</td>
      <td align="left"  height="170px" valign="top" ><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;" >
          <tr>
            <td height="30"  align="center" bgcolor="#F5F5F5">工号</td>
            <td height="30"  align="center" bgcolor="#F5F5F5">姓名</td>
            <td height="30"  align="center" bgcolor="#F5F5F5">拼音</td>
            <td height="30"  align="center" bgcolor="#F5F5F5">部门</td>
            <td height="30"  align="center" bgcolor="#F5F5F5">等级</td>
            <td height="30"  align="center" bgcolor="#F5F5F5">操作</td>
          </tr>
          <%
		  if(affirmor_list.size()!=0){
		    for (int i= 0;i<affirmor_list.size();i++){
			    affirm = (Affirm) affirmor_list.get(i);
		%>
          <tr>
            <td height="30"  align="center"><%=affirm.getAffirmorID()%></td>
            <td height="30"  align="center"><%=affirm.getAffirmorName()%></td>
            <td height="30"  align="center"><%=affirm.getChinesePinYin()%></td>
            <td height="30"  align="center"><%=affirm.getAffirmorDeptName()%></td>
            <td height="30"  align="center"><%=affirm.getAffirmLevel()%></td>
            <td height="30"  align="center"><a href="javascript:levelchange(<%=affirm.getAffirmorID()%>,<%=affirm.getAffirmLevel()%>,'up');">上移</a>§ <a href="javascript:levelchange(<%=affirm.getAffirmorID()%>,<%=affirm.getAffirmLevel()%>,'down');">下移</a>§ <a href="javascript:levelchange(<%=affirm.getAffirmorID()%>,<%=affirm.getAffirmLevel()%>,'delete');">删除</a></td>
          </tr>
          <%}}else{%>
          <tr>
            <td height="30"  align="center" colspan="6">对不起，没有所需要的值！</td>
          </tr>
          <%}%>
        </table></td>
    </tr>
  </table>
</form>
</body>
</html>
