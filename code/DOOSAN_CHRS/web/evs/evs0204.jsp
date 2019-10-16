<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page import="com.ait.evs.EvsDept" %>
<%@ page import="com.ait.evs.EvsPeriod" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="codemap_type" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 &gt; 评价进行 &gt;部门评价</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<%@ include file="/evs/inc/evstoolbar_m.jsp"%>
<%@ include file="/evs/inc/evs_nav.jsp"%>
<%
EvsDept evsDept=new EvsDept();
String evPeriodId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
List lEvsPeriod=null;
try{
	lEvsPeriod=evsP.getEvsPeriodByYear("");
}catch(Exception e){}
%>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
	  <tr>
		<td height="2" class="title_line_01" colspan="2"></td>
	  </tr>
	  <tr>
		<td height="2" class="title_line_02" colspan="2"></td>
	  </tr>
	  <tr>
		<td >
			<div align="justify">全选&nbsp;&nbsp;<input type="checkbox"
				name="checkall" value="all" onClick="selectall()"></div>
		</td>
		<td height="2" align="right">
			<select name="evPeriodId" onChange="location.href='/evs/evs0204.jsp?menu_code=evs0204&evPeriodId='+this.value;">
                        <option value="">评价期间</option>
                        <%
                        if(lEvsPeriod!=null){
	                        int lEvsPeriodSize=lEvsPeriod.size();
	                        for(int i=0;i<lEvsPeriodSize;i++){
	                        	EvsPeriod evsPeriod=(EvsPeriod)lEvsPeriod.get(i);
	                            %>
	                            <option value="<%=evsPeriod.getEvPeriodID()%>" <%if (evsPeriod.getEvPeriodID().equals(evPeriodId)) {%> selected="selected"<%}%>><%=evsPeriod.getEvPeriodName()%></option>
	                            <% 
	                        }
                        }
                        %>
                    </select>

		</td>

	  </tr>
	  <tr>
		<td class="line" colspan="2">
		<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-164) + ';\">')
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
           <tr align="center">
				<td width="10%" height="30" bgcolor="#F5F5F5">
				<div align="center">选择</div>
				</td>
				<td width="70%" bgcolor="#F5F5F5">
				<div align="center">部门名称</div>
				</td>
            	<td width="20%" bgcolor="#F5F5F5">等级评定</td>
          </tr>
           <%
           
          List levsDept=evsDept.getDeptByPeriodId(evPeriodId);
          int evDeptSize=0;
		if(levsDept!=null){ 
		  evDeptSize=levsDept.size();
		   for(int i=0;i<evDeptSize;i++){
		      evsDept=(EvsDept)levsDept.get(i);
		      String evDeptGradeId="";
		      if(evsDept.getEvDeptGradeID()!=null){
		      	evDeptGradeId=evsDept.getEvDeptGradeID();
		      }
		      
              %>
            <tr align="center">
            <input type="hidden" name="evDeptId<%=i%>" value="<%=evsDept.getEvDeptID()%>">
                  <td height="30" >
                  <div align="center" class="info_content_01">
                  <input type="checkbox" name="check" value="<%=i%>" >&nbsp;</div>
                  </td>
                  <td align="left">
                  <%
					String ev_dept_name="";
					int ev_dept_level=evsDept.getEvDeptLevel();
					
					for(int j=0;j<ev_dept_level;j++){
						ev_dept_name=ev_dept_name+"&nbsp;&nbsp;&nbsp;";
					}
					out.print(ev_dept_name+evsDept.getEvDeptName());
					%>
				</td>
                  <td >
                      <select name="evDeptGradeId<%=i%>">
						<option value="">等级选择</option>
                         <%
                Vector vOpt=null;
                vOpt=SysCodeParser.getCode("EVDEPTGRADE");
                
                if(vOpt!=null){
	                for(int j=0;j<vOpt.size();j++){
	                  codemap_opt=(HashMap)vOpt.get(j);
	                  if(evDeptGradeId.equals((String)codemap_opt.get("code"))){
	                    out.print("<option value="+(String)codemap_opt.get("code")+" selected='true' >"+(String)codemap_opt.get("name")+"</option>");
	                  }else{
	                    out.print("<option value="+(String)codemap_opt.get("code")+">"+(String)codemap_opt.get("name")+"</option>");
	                  }
	                }
                }
                 %>

                </td>
          </tr>
          <%}}%>
        </table>
        	</div></td>
	  </tr>
</table>

</body>
</html>
<script type="text/javascript" language="javaScript">
                      function getValue(t)
                      {
                        try{
							
                          t.value=evsMasterListI.evsMasterListForm.evsMasterList.value;
                        }catch(e){alert(e);}
                      }
                      function selectall()
                      {
                        try{
                          var length=<%=evDeptSize%>;


                          document.LGFORM.checkall.checked = document.LGFORM.checkall.checked|0;

                          if ( length== 0 ){
                            return;
                          }
                          if (length ==1 )
                          {
                            document.LGFORM.check.checked=document.LGFORM.checkall.checked ;
                          }

                          if (length>1)
                          {
                            for (var i = 0; i < length; i++)
                            {

                              document.LGFORM.check[i].checked=document.LGFORM.checkall.checked;
                            }
                          }
                        }catch(e){alert("error:"+e);}
                      }
                      </script>

