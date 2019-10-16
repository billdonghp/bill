<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@page import="com.ait.evs.EvsMaster,
                com.ait.evs.EvsPeriod,
                com.ait.evs.EvsEmp,
                com.ait.util.*,
                java.util.*"%>
<%@ page import="java.lang.*"  %>
	<%@ include file="/inc/taglibs.jsp"%>
	
<%
  String empID = "";
  if (admin != null)
    empID = admin.getAdminID();
  else
    throw new JspException("无效访问");


%>

<html>
<head>
<script language="JavaScript"><!--
	function onChangeEvt(){
   		var strURL = '/evs/evs0202.jsp?menu_code=evs0202&evDeptId='+document.LGFORM.evDeptId.value+'&evPeriodId='+document.LGFORM.evPeriodId.value;

    	strURL +='&evTypeId='
    	var typeIds = document.LGFORM.evTypeId
    	for (var i = 0; i < typeIds.length; i++){
    		if(typeIds[i].checked == true){
    			strURL += ","+typeIds[i].value;
    		}
    	}
    	location.href= strURL;
    }	

////页面禁止复制
//function document.onselectstart()
//{
  //  var tmpObj= event.srcElement.tagName;
 //   if ((tmpObj=="INPUT" || tmpObj=="TEXTAREA") && event.srcElement.readOnly==false)
 //           return true;
 //   if (tmpObj.toUpperCase()=="DATEPICKER")
//            return true;
  //  return false;
//}

var preEl ;
var orgBColor;
var orgTColor;
var ID='';
var ID2='';
var MENU_CODE='';
 
function HighLightTR(backColor,textColor,i,i2,i3,menu_code){ 
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
ID2=i2;
ID3=i3;
MENU_CODE=menu_code
}


function ChangeTextColor(a_obj,a_color){
        for (i=0;i<a_obj.cells.length;i++){
        a_obj.cells(i).style.color=a_color;
    }
}


function setNull(){
		ID='';
		ID2='';
}
function Evs(url)
{
 	 
    if (ID=='')
    {
        alert("请在列表中选择要评价的人员.");
        return;
    }
    else
    {	
        //var  evsperiod= document.getElementById("period").value;
        //alert(evsperiod);
        // alert(ID3);
        //if(evsperiod==null||evsperiod=='')
        //{
         //   alert("请选择年度 ");
         //   return;
       // }
       if (ID3=='0')
         {
           alert("已评价完毕,请更改评价类型方可在次评价!!!");
           return;
       }else{
           document.ev0202_t.action="/evsControlServlet?operation=retrieveEvaluate0202&EmpID="+ ID+"&EvTypeId="+ID2;
  	    document.ev0202_t.submit();
    }
      
    }
}
    		
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-158) + ';\">')
//
--></script>
<%
  String period = "";
  String deptID = "";
  String evPeriodId="";
  String evPeriodFirst="";
  //List lEvsDept=null;
  EvsEmp evsEmp=new EvsEmp();
  Calendar c = Calendar.getInstance();
  String year = new Integer(c.get(Calendar.YEAR)).toString();
  EvsPeriod evsPeriod = new EvsPeriod();
  String evDeptId="";
  String GoEmp ="";
  String adminid ="";
  if(admin.getAdminID()!=null&&(admin.getAdminID().equals("1464498")||admin.getAdminID().equals("1464086")||admin.getAdminID().equals("9999901")))
	{	
	  adminid="";
	}
	else{
		adminid = admin.getAdminID();
		}
  GoEmp=request.getParameter("GoEmp")!=null?request.getParameter("GoEmp"):GoEmp;  
  evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;  
  evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
  if (request.getParameter("evDeptId")!=null && request.getParameter("evDeptId").trim().length()!=0)
    deptID = request.getParameter("evDeptId");
  if (request.getParameter("Period")!=null && request.getParameter("Period").trim().length()!=0)
    period = request.getParameter("Period");
  else {
   // period = evsPeriod.getCurrentEvPeriod();
  }
  try{
  	if(evPeriodId.equals("")){
		//evPeriodId=evsPeriod.getCurrentEvPeriod();
  		evPeriodId =year;
	}
  	//lEvsDept=EvsEmp.getEvEmpDeptList(evPeriodId);
  	evPeriodFirst=evsPeriod.getFirstEvPeriodByEvYear(year);
  }catch(Exception e){}
  EvsMaster evsMaster = new EvsMaster();
 // Vector empList_v = evsMaster.getEvEmpsByMaster(period,admin.getAdminID(),evDeptId);
 Vector empList_v = evsMaster.getEvEmpsByMaster(period,GoEmp,evDeptId);

%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 &gt; 评价进行 &gt; 评价打分</title>

</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="inc/evstoolbar_0202.jsp"%>
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
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
				<%
			  List lEvsPeriod=null;
			  lEvsPeriod=evsPeriod.getEvsPeriodByYear("");
			  int grant=0;
			  grant=evsPeriod.getEvsPeriodGrant(empID);
			  
			 
			%>
          <form action="" method="POST" name="ev0202">
          <tr>
          <td class="info_title_01" width="80">部门</td>
		<td class="info_content_00">
		<ait:selEvDept name="evDeptId" evPeriodId="<%=adminid %>" all=" " onChange="ev0202.submit();" selectEvDeptId="<%= evDeptId %>" />
		</td>
		<td class="info_title_01" width="80">姓名:</td>
		<td class="info_content_00">
		<input type="text"	name="GoEmp" id="GoEmp" value ="<%=GoEmp %>" size="6"> &nbsp;<a
			onClick="ev0202.submit();"
			style="cursor:hand">搜索</a>
                    
		</td>
		
          </tr>
          </form>
				
			</table>
		  </td>
		</tr>
		</table>
		<!-- display 3 level menu -->
		<%@ include file="inc/evs_nav.jsp"%>
		
		<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">对下评价</td>
			</tr>
		</table>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1" >
	<tr>
		<td class="line">

  <form action="/evs/evs0202_t.jsp?menu_code=evs0202" method="POST" name="ev0202_t">
		<table width="100%" border="1" align="center" cellpadding="2"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
           <tr align="center">
            <td height="30" class="info_title_01">序号</td>
            <td class="info_title_01">工号</td>
            <td class="info_title_01">姓名</td>
            <td class="info_title_01">评价年度</td>
            <td class="info_title_01">本部</td>
            <td class="info_title_01">部门</td>
            <td class="info_title_01">所属</td>
            <td class="info_title_01">当前状态</td>
            <td class="info_title_01">可操作状态</td>
          </tr>
<%
  for(int i=0;i<empList_v.size();i++){
    Hashtable emp_h=(Hashtable)empList_v.get(i);
	String statuss = "";
	if(emp_h.get("EV_FLAG")!=null&&emp_h.get("EV_FLAG").equals("00")){
		 statuss = "未评价";
	}else if(emp_h.get("EV_FLAG")!=null&&emp_h.get("EV_FLAG").equals("1")){
		 statuss = "已保存";
	}else if(emp_h.get("EV_FLAG")!=null&&emp_h.get("EV_FLAG").equals("2")){
		 statuss = "已提交";
	}else if(emp_h.get("EV_FLAG")!=null&&emp_h.get("EV_FLAG").equals("3")){
		 statuss = "已决裁";
	}
	
  	
%>

              <tr align="center" 
                 onClick="HighLightTR('#F0F1F4','black','<%=emp_h.get("EmpID")%>','<%= emp_h.get("EvTypeId")%>','<%= emp_h.get("Operatable")%>','<%=menu_code%>');">
  		        <td height="30"><%=i+1%></td>
                  <td  nowrap="nowrap" align="center" style="color: #666666;"><%=emp_h.get("EmpID1")%>&nbsp; 
					<input type="hidden" name="EmpID" id="EmpID" value="<%= emp_h.get("EmpID")%>"></td>
                  <td><%=emp_h.get("EmpName")%>&nbsp;
					<input type="hidden" name="EvTypeId" id="EvTypeId" value="<%= emp_h.get("EvTypeId")%>"></td>
                  <td><%= emp_h.get("EvTypeName")%>&nbsp;</td>
				  <td><%= StringUtil.checkNull(emp_h.get("SecondDept")) %>&nbsp;</td>
				  <td><%= StringUtil.checkNull(emp_h.get("ThirdDept")) %>&nbsp;</td>
				  <td><%= StringUtil.checkNull(emp_h.get("SixthlyDept")) %>&nbsp;</td>
                  <td><%= StringUtil.checkNull(statuss) %>&nbsp;</td>
                  <td>
				<%
				  if (emp_h.get("Operatable").equals("1")) {
				%>
				                    <img id="able" alt="可操作" border="0" src="/images/ev/able.gif"/>
				<%
				  } else {
				%>
				                    <img id="unable" alt="不可操作" border="0" src="/images/ev/unable.gif"/>
				<%
				  }
				%>
                  </td>
              </tr>
				<%
				  }
				%>
        </table>
        </div>
        </td>
	  </tr>
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
		<td class="info_content_bg"  height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</form>
	</tr>
</table>


</td>
</tr>
</table>

</body>
</html>
