<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/jodd" prefix="jodd" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsMark"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsItemProcessMark"%>
<%@ page import="com.ait.evs.Constants"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="codemap_opt" class="java.util.HashMap" scope="page"/>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 &gt; 评价进行 &gt; 最终等级</title>
</head>
<SCRIPT type="text/javascript">

function checkAll(){

	var selected = document.save.ck_all.value == "0" ? true : false;
  
 	for(var i=0;i<document.save.elements.length;i++){
  
		if(document.save.elements[i].type=="checkbox" && !document.save.elements[i].disabled&&document.save.elements[i].name!='c'){
    
      		document.save.elements[i].checked = selected;
      
		}
    
	}
  
	document.save.ck_all.value = selected ? "1" : "0";
}

function CheckForm(){

	var check="";
	var checks="";	
	checks=document.getElementsByName("selectedTag");
	
	if(checks.length>0){
		
		for(var i=0;i<checks.length;i++){
		
			if(checks[i].checked==true){
			    	
			    check = "checked";
	
			}
		}		  
	
	}

	if(check==""){
		alert("请选择人员！");
		return false;
	}
	
	return true;
}

function Save(){
	
	if(CheckForm()){
		document.save.flag.value="save";
		document.save.submit();
		
	}	
}
function Submit(){
	
	if(CheckForm()){
		document.save.flag.value="submit";
		document.save.submit();
		
	}	
}
</SCRIPT>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%

String masterId =admin.getAdminID();
String evPeriodId="";

String evProcessId=Constants.EVPROCESS015;
List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List<EvsEmp> lEvsEmp=null;
List<EvsProcess> lPreEvsProcess=null;
List<EvsItem> lEvsItem=null;

EvsProcess evsProcess = new EvsProcess();
EvsProcess nextEvsProcess = new EvsProcess();
EvsItem evsItem = new EvsItem();
EvsEmp evsEmp=new EvsEmp();
SimpleMap map=new SimpleMap();
try{
	lEvsDept=EvsEmp.getEvEmpDeptList();
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=EvsEmp.getEvEmpTypeList();
	map = ObjectBindUtil.getData(request);
	
	EvsPeriod evsP=new EvsPeriod();
	if(StringUtil.checkNull(map.getString("PERIODID")).equals("")){
		try{
			evPeriodId=evsP.getCurrentEvPeriod();
		}catch(Exception e){}	
	}
	
	if(request.getParameter("DEPTID")!=null){
		lEvsEmp=evsEmp.getEmps(map);
		lPreEvsProcess=evsProcess.getPreEvsProcess(map);
		nextEvsProcess=evsProcess.getNextEvsProcess(map);
		lEvsItem=evsItem.getItems(map);
	}
}catch(Exception e){}
%>
<%@ include file="inc/evstoolbar_0210.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>

<table width="99%"  border="0" align="center" cellpadding="0" cellspacing="1">
<form action="/evs/evs0212.jsp?menu_code=evs0212" method="Post" name="searchForm">
<jodd:form fromRequest="true">
<input type="hidden" name="MASTERID" value="<%=masterId%>" >
<input type="hidden" name="PROCESSID" value="<%=evProcessId%>" >
	  <tr>
		<td height="2" class="title_line_01"></td>
	  </tr>
	  <tr>
		<td height="2" class="title_line_02"></td>
	  </tr>
	<tr>
		<td height="2" align="right">
		<%--<input type="text" name="GoEmp" id="GoEmp" size="6"> &nbsp;
		<a onClick="location.href='#'+document.searchForm.GoEmp.value;"style="cursor:hand">定位</a> 
		&nbsp;
			
		--%><select name="DEPTID" onChange="searchForm.submit();">
			<option value="">评价部门</option>
			<%if(lEvsDept!=null){
				int lEvsDeptSize=lEvsDept.size();
				Hashtable dept_h=new Hashtable();
                      for ( int i = 0 ; i < lEvsDeptSize; i++ )
                      {
                        dept_h = (Hashtable) lEvsDept.get(i);
                          %>
			<option value="<%=(String)dept_h.get("deptId")%>">
				<%
                   int level=Integer.parseInt((String)dept_h.get("deptLevel"));
                   String deptname = "";
                   for(int j=0;j<level;j++){
                     deptname +="　";
                   }
                   out.print(deptname+(String)dept_h.get("deptName"));
                 %>
            </option>
			<%}}%>
		</select>
		&nbsp;
		<select name="POSITION" onChange="searchForm.submit();">
			<option value="">职位选择</option>
			<%
			
			Set<String> sPosition=Constants.mPosition.keySet();
			Iterator<String> iPosition=sPosition.iterator();
			while(iPosition.hasNext()){
				String key=iPosition.next();
				String value=(String)Constants.mPosition.get(key);
				
				out.print("<option value=\""+key+"\">"+value+"</option>");
				
			}
			%>
		</select>
		&nbsp;
		<select name="PERIODID" onChange="searchForm.submit();">
			<%
             if(lEvsPeriod!=null){
             	int lEvsPeriodSize=lEvsPeriod.size();
             	for(int i=0;i<lEvsPeriodSize;i++){
             		EvsPeriod evsPeriod_sel=(EvsPeriod)lEvsPeriod.get(i);
            %>
			<option value="<%=evsPeriod_sel.getEvPeriodID()%>" <%if (evPeriodId.equals(evsPeriod_sel.getEvPeriodID())) {%>
				selected="selected" <%}%>>
				<%=evsPeriod_sel.getEvPeriodName()%>
				</option>
			<%}}%>
		</select> 
		&nbsp;
		<select name="TYPEID" onChange="searchForm.submit();">
		<option value="">评价类型</option>
			<%
             if(lEvsType!=null){
                 	int lEvsTypeSize=lEvsType.size();
                 	for(int i=0;i<lEvsTypeSize;i++){
                 		EvsType evsType_sel=(EvsType)lEvsType.get(i);
                              %>
			<option value="<%=evsType_sel.getEvTypeID()%>">
				<%=evsType_sel.getEvTypeName()%>
			</option>
			<%}}%>
		</select>
		<img src="/images/btn/search.gif" align="absmiddle" onclick="searchForm.submit();" style="cursor:hand;">
		</td>
	</tr>
</jodd:form>	
</form>
</table>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1">	
	  <tr>
		<td class="line">
<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-130) + ';\">')
//-->
</script>
<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form action="/evs/evs0212_t.jsp?menu_code=evs0212" method="Post" name="save" >     
<input type="hidden" name="ck_all" value="0" />    
<input type="hidden" name="flag" value="" />
<input type="hidden" name="DEPTID_CON" value="<%=map.getString("DEPTID")%>" />    
<input type="hidden" name="PERIODID_CON" value="<%=map.getString("PERIODID")%>" />
<input type="hidden" name="TYPEID_CON" value="<%=map.getString("TYPEID")%>" />
<input type="hidden" name="MASTERID_CON" value="<%=masterId%>" >
<input type="hidden" name="PROCESSID_CON" value="<%=evProcessId%>" >
<input type="hidden" name="POSITION_CON" value="<%=map.getString("POSITION")%>" >
         <tr align="center" bgcolor="#F5F5F5">
          <td height="30" bgcolor="#F5F5F5"><a href="#" onClick="checkAll();">选择</a></td>
          <td bgcolor="#F5F5F5">部门</td>
          <td bgcolor="#F5F5F5">工号</td>
          <td bgcolor="#F5F5F5">姓名</td>
          <td bgcolor="#F5F5F5">岗位职务</td>
          <%--<td bgcolor="#F5F5F5">当前状态</td>
          --%><%
          if(null!=lPreEvsProcess){
	           for(int i=0,j=lPreEvsProcess.size();i<j;i++){
	        		EvsProcess ep=lPreEvsProcess.get(i);
	        		out.print("<td bgcolor=\"#F5F5F5\">");
	        		out.print(ep.getEvProcessName());
	        		out.print("</td>");
	        	}
	        	for(int i=0,j=lPreEvsProcess.size();i<j;i++){
	        		EvsProcess ep=lPreEvsProcess.get(i);
	        		out.print("<td bgcolor=\"#F5F5F5\">");
	        		out.print(ep.getEvProcessName()+"分数");
	        		out.print("</td>");
	        	}
         }
        	//if(null!=nextEvsProcess){
        	//	if(null!=nextEvsProcess.getEvProcessName()){
	        //		out.print("<td bgcolor=\"#F5F5F5\">");
	        //		out.print(nextEvsProcess.getEvProcessName());
	        //		out.print("</td>");
        	//	}
        	//}
          %>
          <td bgcolor="#F5F5F5">总分</td>
          <td bgcolor="#F5F5F5">序列</td>
          <td bgcolor="#F5F5F5">默认等级</td>
          <td bgcolor="#F5F5F5">最终等级</td>
          
          <%
          // if(null!=lEvsItem){
	      //     for(int i=0,j=lEvsItem.size();i<j;i++){
	      //  		EvsItem ei=lEvsItem.get(i);
	      //  		out.print("<td bgcolor=\"#F5F5F5\">");
	      //  		out.print(ei.getEvItemName()+"&nbsp;");
	      //  		out.print("</td>");
	      //  	}
          // }	
          %>
        </tr>
        <%
        if(null!=lEvsEmp){
        for(int i=0,j=lEvsEmp.size();i<j;i++){
        	EvsEmp e=lEvsEmp.get(i);
        	
        %>
        <tr align="center">
             <td height="30" >
             	<%if(e.getOperatable().equals("1")){%>
             	<input type="checkbox" name="selectedTag" value="<%=i%>" class="check" checked />
             	<%}%>
             	<input type="hidden" name="EV_EMP_ID_<%=i%>" value="<%=e.getEvEmpID()%>"/>
             	<input type="hidden" name="EV_TYPE_ID_<%=i%>" value="<%=e.getEvTypeID()%>"/>
             	<input type="hidden" name="EV_PERIOD_ID_<%=i%>" value="<%=e.getEvPeriodID()%>"/>
             	<input type="hidden" name="EV_PROCESS_ID_<%=i%>" value="<%=e.getEvCurrentProcessID()%>"/>
             	<input type="hidden" name="EV_MASTER_<%=i%>" value="<%=admin.getAdminID()%>"/>
             </td>
             <td ><%=e.getEvDeptName()%>&nbsp;</td>
             <td ><%=e.getEvEmpID()%>&nbsp;</td>
             <td ><%=e.getEvEmpName()%>&nbsp;</td>
             <td ><%=e.getEvPostGroupName()%>&nbsp;</td>
             <%--<td ><%=e.getEvCurrentProcessName()%>&nbsp;</td>
             --%><%
               List<EvsMaster> lPreMaster=e.getLPreEvsMaster();
               EvsMaster nextMaster=e.getNextProcessMaster();
               
               if(null!=lPreEvsProcess&&null!=lPreMaster){
	        	  int pCnt=lPreEvsProcess.size()-lPreMaster.size();
		           for(int k=0;k<pCnt;k++){
		        		out.print("<td>&nbsp;</td>");
		        	}
		        	
         		}
         		
         		
	           for(int k=0,m=lPreMaster.size();k<m;k++){
	        		EvsMaster em=lPreMaster.get(k);
	        		out.print("<td >");
	        		out.print(em.getEvMasterName()+"&nbsp;");
	        		out.print("</td>");
	        	}
	        	
	        	
	        	if(null!=lPreEvsProcess&&null!=lPreMaster){
	        	  int pCnt=lPreEvsProcess.size()-lPreMaster.size();
		           for(int k=0;k<pCnt;k++){
		        		out.print("<td>&nbsp;</td>");
		        	}
		        	
         		}
         		
         		
	        	 for(int k=0,m=lPreMaster.size();k<m;k++){
	        		EvsMaster em=lPreMaster.get(k);
	        		out.print("<td >");
	        		out.print(em.getEvMasterProcessMark()+"&nbsp;");
	        		out.print("</td>");
	        	}
	        	
	        	//
	        	
	        	
         		
	        	//if(null!=nextMaster){
	        	//	if(null!=nextMaster.getEvMasterName()){
		        //		out.print("<td >");
		        //		out.print(nextMaster.getEvMasterName()+"&nbsp;");
		        //		out.print("</td>");
	        	//	}
	        	//}
	          %>
	         <td ><%=e.getEvMark()%>&nbsp;</td>
             <td ><%=e.getEvOrder()%>&nbsp;</td>
             <td ><%if(!StringUtil.checkNull(e.getEvDefaultGrade()).equals(StringUtil.checkNull(e.getEvGradeID()))){
             	out.print("<font color='red'>");
             	out.print(e.getEvDefautlGradeName());
             	out.print("</font>");
             }else{
             	out.print(e.getEvDefautlGradeName());
             }%>&nbsp;</td>
             <td><select name="EV_GRADE_ID_<%=i%>">
                         <%
                String evGradeId=StringUtil.checkNull(e.getEvGradeID());       
                Vector vOpt=null;
                try{
                	vOpt=SysCodeParser.getCode("EVEMPGRADE");
                }catch(Exception exce){}
                
                if(vOpt!=null){
	                for(int x=0;x<vOpt.size();x++){
	                  codemap_opt=(HashMap)vOpt.get(x);
	                  if(evGradeId.equals((String)codemap_opt.get("code"))){
	                    out.print("<option value=\""+(String)codemap_opt.get("code")+"\" selected >"+(String)codemap_opt.get("name")+"</option>");
	                  }else{
	                    out.print("<option value=\""+(String)codemap_opt.get("code")+"\">"+(String)codemap_opt.get("name")+"</option>");
	                  }
	                }
                }
                 %>
             </select>
             </td>
              <%
               //List<EvsItemProcessMark> lEvsItemProcessMark=e.getLItemList();
               
	           //for(int k=0,m=lEvsItem.size();k<m;k++){
	          //	EvsItem ei=lEvsItem.get(k);
	        //		List<EvsMark> lEvsMark=ei.getLItemProcessMarks();
	        //		
	        //		out.print("<td >");
	        //		out.print("<select name=\""+ei.getEvItemID()+"_MARK_"+i+"\">");	
	        //		for(int x=0,y=lEvsMark.size();x<y;x++){
	        //			EvsMark eMa=lEvsMark.get(x);
	        //			String select="";
	        //			
	       // 			for(int a=0,b=lEvsItemProcessMark.size();a<b;a++){
	       // 				EvsItemProcessMark eipm=lEvsItemProcessMark.get(a);
	       // 				
	       // 				if((eipm.getEvItemId()).equals(ei.getEvItemID())){
	       // 					if(new Float(eipm.getEvItemProcessMark()).floatValue()==(new Float(eMa.getValue()).floatValue())){
	       // 						select=" selected ";
	       // 					}
	       // 				}
	       // 			}
	       // 			out.print("<option value=\""+eMa.getValue()+"\" "+select+">");
		   //     		out.print(eMa.getName());
		   //     		out.print("</option>");
	       // 		}
	       // 		out.print("</select>");
	       // 		out.print("</select></td>");
	       // 	}
          %>
         </tr>
         <%}}%>
</form>
</table>
</div>
</td>
</tr>
</table>
</body>
</html>
