<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
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
<%@ include file="/evs/inc/evs0207toolbar_v.jsp"%>
<%@ include file="/evs/inc/evs_nav.jsp"%>
<%
String adminId="";
if(admin.getAdminID()!=null){
	adminId=admin.getAdminID();
}
String evDeptId="";
String evPeriodId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
EvsPeriod evsP=new EvsPeriod();
try{
	if(evPeriodId.equals("")){
		evPeriodId=evsP.getCurrentEvPeriod();
	}
	
}catch(Exception e){}
List lEvsDept=null;
List lEvsPeriod=null;
List lEvsProcess=null;

Vector vEvsEmp=null;
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{
	lEvsDept=EvsEmp.getEvEmpDeptList();
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	vEvsEmp=evsMaster.getEvEmpsByMaster(evPeriodId,adminId,evDeptId);

}catch(Exception e){}
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2" class="title_line_01"></td>
	</tr>
	<tr>
		<td height="2" class="title_line_02"></td>
	</tr>
	<tr align="right">
		<TD align="right">&nbsp;
		<select name="evDeptId"
			onChange="location.href='/evs/evs0207.jsp?menu_code=evs0207&evDeptId='+this.value+'&evPeriodId='+document.LGFORM.evPeriodId.value;">
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
		</select> &nbsp;<select name="evPeriodId"
			onChange="location.href='/evs/evs0207.jsp?menu_code=evs0207&evDeptId='+document.LGFORM.evDeptId.value+'&evPeriodId='+this.value;">

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
		</a></td>
	</tr>
	<tr>
		<td class="line">
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
    
    function save1()
    {	
    	if(checkForm()){
    		document.LGFORM.flag.value="save";
    		document.LGFORM.submit();
		}
    }
    function submit1(){
    	if(checkForm()){
    		document.LGFORM.flag.value="submit";
    		document.LGFORM.submit();
		}
    }
    function checkForm(){
    	//if(document.LGFORM.evDeptId.value==''){
    	//	alert("请先选择评价部门!");
    	//	return false;
    	//}
    	if(document.LGFORM.evPeriodId.value==''){
    		alert("请先选择评价期间!");
    		return false;
    	}
    	return true;
    }
    </script>
