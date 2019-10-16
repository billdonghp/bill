<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.Constants"%>
<%@ page import="com.ait.evs.EvsDeptDefaultGrade"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="codemap_type" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 &gt; 评价进行 &gt; 相对化</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%
String adminId="";
if(admin.getAdminID()!=null){
	adminId=admin.getAdminID();
}
String evDeptId="";
String evPeriodId="";
String evTypeId="";
String evProcessId="";

evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evTypeId = evTypeId.startsWith(",") ? evTypeId.substring(1,evTypeId.length()) : evTypeId;

EvsPeriod evsP=new EvsPeriod();
try{
	if(evPeriodId.equals("")){
		evPeriodId=evsP.getCurrentEvPeriod();
	}
	if(evProcessId.equals("")){
		evProcessId=Constants.EVPROCESS015;
	}
}catch(Exception e){}
List lEvsDept=null;
List lEvsPeriod=null;
List lEvsProcess=null;
List lEvsType=null;
Vector vEvsEmp=null;
EvsPeriod evsPeriod=new EvsPeriod();
EvsEmp evsEmp=new EvsEmp();
EvsProcess evsProcess=new EvsProcess();
EvsMaster evsMaster=new EvsMaster();
try{
	lEvsDept=EvsEmp.getEvEmpDeptList();
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=evsPeriod.getEvTypeByEvPeriodId(evPeriodId);
	lEvsProcess=evsProcess.getProcessByOperate(Constants.EVOPERATE008);
	vEvsEmp=evsMaster.getEvEmpsByMaster(evPeriodId,adminId,evDeptId,evTypeId,evProcessId);
	
}catch(Exception e){}

EvsDeptDefaultGrade evsDDG=null;
evsDDG=new EvsDeptDefaultGrade(evDeptId,evPeriodId,evProcessId,evTypeId,adminId);

String deptGradeName="";
//取得部门等级
if(!evPeriodId.equals("")&&!evDeptId.equals("")){
	deptGradeName=evsEmp.getDeptGradeId(evPeriodId,evDeptId,"");
}
%>
<%
String menu_code="";
String rodeLevel ="";
ArrayList tool_menu=null;
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);

int insertMenu=0;
int updateMenu=0;
int deleteMenu=0;
for(int i=0;i<tool_menu.size();i++){
	toolmenu=(ToolMenu)tool_menu.get(i);
	if(toolmenu.getInsertr()==1){
		insertMenu=1;

	}
	if(toolmenu.getUpdate()==1){
		updateMenu=1;

	}
	if(toolmenu.getDelect()==1){
		deleteMenu=1;

	}
}
%>
<form name="LGFORM" method="POST" action="/evs/evs0203_t.jsp?menu_code=evs0203" >
<input type="hidden" name="flag" value="save">
<input type="hidden" name="menu_code" value="<%=menu_code%>" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="30" valign="middle">
		<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="right" valign="middle"
					style="padding:3 0 3 0 ">
					
					<img name="savebtn" id="savebtn" src="/images/ev/save.gif" border="0" onClick="javascript:save();" style="cursor:hand;visibility:"  alt="保存等级" />
					
					<img name="defaultbtn" id="defaultbtn" src="/images/ev/default.gif" border="0" onClick="javascript:defaultGrade();" style="cursor:hand;visibility:"  alt="相对化" />

					<img name="submitbtn" id="submitbtn" src="/images/ev/submit.gif" border="0" onClick="javascript:submitDefaultGrade();" style="cursor:hand;visibility:"  alt="同意/提交" /></td>
			</tr>
		</table>
		</td>
	</tr>

</table>
<%@ include file="/evs/inc/evs_nav.jsp"%>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2" class="title_line_01"></td>
	</tr>
	<tr>
		<td height="2" class="title_line_02"></td>
	</tr>
	<tr align="right">
		<td ><%if(!StringUtil.checkNull(deptGradeName).equals(""))out.println("部门等级: "+StringUtil.checkNull(deptGradeName));%></td>	
		<TD align="right">&nbsp;
		<select name="evDeptId" onChange="onChangeEvt()">
			<option value="">评价部门</option>
			<%			if(lEvsDept!=null){
							int lEvsDeptSize=lEvsDept.size();
							Hashtable dept_h=new Hashtable();
	                        for ( int i = 0 ; i < lEvsDeptSize; i++ )
	                        {
	                          dept_h = (Hashtable) lEvsDept.get(i);
                          %>
			<option value="<%=(String)dept_h.get("deptId")%>"
				<%if (((String)dept_h.get("deptId")).equals(evDeptId)){%> selected
				<%}%>><%
                            int level=Integer.parseInt((String)dept_h.get("deptLevel"));
                            String deptname = "";
                            for(int j=0;j<level;j++){
                              deptname +="　";
                            }
                            out.print(deptname+(String)dept_h.get("deptName"));

                            %></option>
			<%}}%>
		</select> &nbsp;<select name="evPeriodId" onChange="onChangeEvt()">

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
		&nbsp;
		<!--<td height="30" wigth="15" align="left"><input type="submit" value="评价类型" onClick="Batch();"></td>
		
		
		<select name="evTypeId"
			onChange="location.href='/evs/evs0203.jsp?menu_code=evs0203&evDeptId='+document.LGFORM.evDeptId.value+'&evPeriodId='+document.LGFORM.evPeriodId.value+'&evProcessId='+document.LGFORM.evProcessId.value+'&evTypeId='+document.LGFORM.evTypeId.value;"> 
				<option value="">评价类型</option>-->
						<%
                            if(lEvsType!=null){
                            	int lEvsTypeSize=lEvsType.size();
                            	for(int i=0;i<lEvsTypeSize;i++){
                            		EvsType evsType=(EvsType)lEvsType.get(i);
                              %>
			<input type="checkbox" name="evTypeId"
			value="<%=evsType.getEvTypeID()%>"  onClick="onChangeEvt()" <%= evTypeId.indexOf(evsType.getEvTypeID()) >= 0 ? "checked" : "" %>><%=evsType.getEvTypeName()%>
<%--		<option value="<%=evsType.getEvTypeID()%>"-->
<!--				<%if (evTypeId.equals(evsType.getEvTypeID())) {%>-->
<!--				selected="selected" <%}%>><%=evsType.getEvTypeName()%></option>--%>
			<%}}%>
		<%--<!--</select> --%>
		
		&nbsp;
		<select name="evProcessId"
			onChange="onChangeEvt()">

			<%
                        if(lEvsProcess!=null){
                           	int lEvsProcessSize=lEvsProcess.size();
                          	for(int i=0;i<lEvsProcessSize;i++){
                            		EvsProcess evsProcess2=(EvsProcess)lEvsProcess.get(i);
                              %>
			<option value="<%=evsProcess2.getEvProcessID()%>"
				<%if (evProcessId.equals(evsProcess2.getEvProcessID())) {%>
				selected="selected" <%}%>><%=evsProcess2.getEvProcessName()%></option>
			<%}}%>
		</select>
		
		</a></td>
	</tr>
	<tr>
		<td class="line" colspan="2">
		<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-164) + ';\">')
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" bgcolor="#F5F5F5">
				<td height="30">序号</td>
				<td>工号</td>
				<td>姓名</td>
				<td>部门</td>
				<td>评价类型</td>
				<td>当前状态</td>
				<td>当前得分</td>
				<td>当前等级</td>
				<td>可操作状态</td>
			</tr>
			<%
          if(vEvsEmp!=null){
          	int vEvsEmpSize=vEvsEmp.size();
          	for(int i=0;i<vEvsEmpSize;i++){
          		Hashtable hEvEmp=(Hashtable)vEvsEmp.get(i);
              %>
			<tr align="center">
				<td height="30" class="tablecontent"><%=i+1%></td>
				<td><%=(String)hEvEmp.get("EmpID")%>&nbsp;</td>
				<td><%=StringUtil.toUnicode(StringUtil.toISO((String)hEvEmp.get("EmpName")),"UTF-8")%>&nbsp;</td>
				<td><%=(String)hEvEmp.get("DeptName")%>&nbsp;</td>
				<td><%=(String)hEvEmp.get("EvTypeName")%>&nbsp;</td>
				<td><%=(String)hEvEmp.get("ProcessName")%>&nbsp;</td>
				<td><%=(String)hEvEmp.get("Mark")%>&nbsp;</td>
				<td>
				<%
				if (hEvEmp.get("Operatable").equals("1")) {
				%><input type="hidden" name="evEmpId" value=<%=(String)hEvEmp.get("EmpID")%> >
                      <select name="evEmpGradeId">
						<option value="">等级选择</option>
                         <%
                String evGradeId=StringUtil.checkNull((String)hEvEmp.get("EvGradeId"));       
                Vector vOpt=null;
                try{
                	vOpt=SysCodeParser.getCode("EVEMPGRADE");
                }catch(Exception e){}
                
	                if(vOpt!=null){
		                for(int j=0;j<vOpt.size();j++){
		                  codemap_opt=(HashMap)vOpt.get(j);
		                  if(evGradeId.equals((String)codemap_opt.get("code"))){
		                    out.print("<option value="+(String)codemap_opt.get("code")+" selected='true' >"+(String)codemap_opt.get("name")+"</option>");
		                  }else{
		                    out.print("<option value="+(String)codemap_opt.get("code")+">"+(String)codemap_opt.get("name")+"</option>");
		                  }
		                }
	                }
	            }else{
	            	out.println((String)hEvEmp.get("EvGradeName")+"&nbsp;");
	            }    
                 %>
                 </td>
				<td>
<%
  if (hEvEmp.get("Operatable").equals("1")) {
%>
                    <img alt="可操作" border="0" src="/images/ev/able.gif"/>
<%
  } else {
%>
                    <img alt="不可操作" border="0" src="/images/ev/unable.gif"/>
<%
  }
%>
                  </td>
			</tr>
			<%
          }
        }
          %>
		</table>
		</div>
		</td>
	</tr>
</table>
</body>

</html>
<script language="JavaScript" type="text/javascript" src="">
    
    function defaultGrade()
    {	
    	if(checkForm()){
			location.href="/evs/evs0203_t.jsp?menu_code=evs0203&flag=defaultGrade&evDeptId=<%=evDeptId%>&evPeriodId=<%=evPeriodId%>&evProcessId=<%=evProcessId%>&evTypeId=<%=evTypeId%>";
		}
    }
    function submitDefaultGrade(){
    	if(checkForm()){
    		document.LGFORM.flag.value="submit";
    		document.LGFORM.submit();
			//location.href="/evs/evs0203_t.jsp?menu_code=evs0203&flag=submit&evDeptId=<%=evDeptId%>&evPeriodId=<%=evPeriodId%>&evProcessId=<%=evProcessId%>&evTypeId=<%=evTypeId%>";
		}
    }
    function checkForm(){
    	
    	var evTypeArray = document.getElementsByName("evTypeId");
    	var TypeLen = evTypeArray.length;
    	var typeYN = false;
    	
    	for (i = 0;i< TypeLen;i++)
    	{
	    	 if (evTypeArray [i].checked==true) 
	    	 { 
		    	 typeYN= true; 
		    	 break; 
	    	 }
    	}
    	
    	if(typeYN == false){
    		alert("请选择评价类型！");
    		return false;
    	}
    
    	if(document.LGFORM.evDeptId.value==''){
    		alert("请先选择评价部门!");
    		return false;
    	}
    	if(document.LGFORM.evPeriodId.value==''){
    		alert("请先选择评价期间!");
    		return false;
    	}
    	if(document.LGFORM.evProcessId.value==''){
    		alert("请选择相对化类型!");
    		return false;
    	}

    	return true;
    }
    function save()
    {	
    	if(checkForm()){
    		document.LGFORM.flag.value="save";
    		document.LGFORM.submit();
		}
    }
    
    function onChangeEvt(){
   		var strURL = '/evs/evs0203.jsp?menu_code=evs0203&evDeptId='+document.LGFORM.evDeptId.value+'&evPeriodId='+document.LGFORM.evPeriodId.value+'&evProcessId='+document.LGFORM.evProcessId.value;

    	strURL +='&evTypeId='
    	var typeIds = document.LGFORM.evTypeId
    	for (var i = 0; i < typeIds.length; i++){
    		if(typeIds[i].checked == true){
    			strURL += ","+typeIds[i].value;
    		}
    	}
    	location.href= strURL;
    }
    
       
    </script>
