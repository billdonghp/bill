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

  evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;  
  evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
  if (request.getParameter("evDeptId")!=null && request.getParameter("evDeptId").trim().length()!=0)
    deptID = request.getParameter("evDeptId");
  if (request.getParameter("Period")!=null && request.getParameter("Period").trim().length()!=0)
    period = request.getParameter("Period");
  else {
    period = evsPeriod.getCurrentEvPeriod();
  }
  try{
  	if(evPeriodId.equals("")){
		evPeriodId=evsPeriod.getCurrentEvPeriod();
	}
  	//lEvsDept=EvsEmp.getEvEmpDeptList(evPeriodId);
  	evPeriodFirst=evsPeriod.getFirstEvPeriodByEvYear(year);
  }catch(Exception e){}
  EvsMaster evsMaster = new EvsMaster();
  Vector empList_v = evsMaster.getEvEmpsByMaster(period,admin.getAdminID(),evDeptId);

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
		<%@ include file="inc/evstoolbar_0202_a.jsp"%>
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
	       		 	<td class="info_title_01" width="80">评价区间</td> 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >&nbsp;</div>
					</td>
					<td class="info_title_01" width="80">姓名</td> 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${ev_emp_id}&nbsp;</div> 
					</td>
					<td class="info_title_01" width="80">评价类型</td> 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.EV_TYPE_NAME}&nbsp;</div> 
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
<script language="JavaScript">
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
    
function document.onselectstart()
{
    var tmpObj= event.srcElement.tagName;
    if ((tmpObj=="INPUT" || tmpObj=="TEXTAREA") && event.srcElement.readOnly==false)
            return true;
    if (tmpObj.toUpperCase()=="DATEPICKER")
            return true;
    return false;
}

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

function Modify()
{
    if (ID=='')
    {
            alert("请在列表中选择要修改的项目.");
    }
    else
    {
            url='/evs/'+MENU_CODE+'_m.jsp?menu_code='+MENU_CODE+'&ID='+ID;
            location.href=url;
    }
}
function Delete()
{
    if (ID=='')
    {
            alert("请在列表中选择要删除的项目.");
    }
    else
    {
            if( confirm("删除后,相关信息也将被清空!\n\n      确定要删除吗?") ) {
                    url='/evs/'+MENU_CODE+'_t.jsp?flag=del&menu_code='+MENU_CODE+'&ID='+ID;
                    location.href=url;
            }
            else
            {
            return;
            }
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
            //var  evstype = document.getElementById("EvTypeId").value;
            //alert(evstype);
            // alert(ID3);
           if (ID3=='0')
             {
			            alert("已评价完毕,请更改评价类型方可在次评价!!!");
			            return;
           }else{
			            document.ev0202_t.action="/evsControlServlet?operation=retrieveEvaluate0202&EmpID="+ ID+"&Period=<%=period%>&EvTypeId="+ID2;
				  	    document.ev0202_t.submit();
        }
          
        }
    }
    		
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-158) + ';\">')
//-->
</script>
  <form action="/evs/evs0202_t.jsp?menu_code=evs0202" method="POST" name="ev0202_t">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
           <tr align="center">
            <td width="10%" height="30" class="info_title_01">
				<div align="center">序号</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">工种名称</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">技能类型</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">技能Line</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">机种名称</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">工序名称</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">作业内容</div>
				</td>
				<!-- 
				 <td width="13%" class="info_title_01">
				<div align="center">作业类型</div>
				</td> -->
				<td width="13%" class="info_title_01">
				<div align="center">职业资格</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">技能积分</div>
				</td>
				 <td width="30%" class="info_title_01">
				<div align="center">熟练度分数</div>
				</td>
				<td width="30%" class="info_title_01">
				<div align="center">作业基准书</div>
				</td>
				<td width="30%" class="info_title_01">
				<div align="center">作业品质</div>
				</td>
				<td width="30%" class="info_title_01">
				<div align="center">标准动作</div>
				</td>
				<td width="30%" class="info_title_01">
				<div align="center">综合得分</div>
				</td>
				<td width="30%" class="info_title_01">
				<div align="center">技能等级</div>
				</td> 
				<td width="38%" class="info_title_01">
				<div align="center">主要问题点</div>
				</td>
				<td width="38%" class="info_title_01">
				<div align="center">培训目标</div>
				</td>
				<td width="38%" class="info_title_01">
				<div align="center">操作状态</div>
				</td>
          </tr>
<%
  for(int i=0;i<empList_v.size();i++){
    Hashtable emp_h=(Hashtable)empList_v.get(i);
	//String OperateId = emp_h.get("OperateId").toString();
	
  	
%>

              <tr align="center" 
                 onClick="HighLightTR('#F0F1F4','black','<%=emp_h.get("EmpID")%>','<%= emp_h.get("EvTypeId")%>','<%= emp_h.get("Operatable")%>','<%=menu_code%>');">
              <!-- if ('< %=OperateId%>'=='EVOPERATE002'&&'< %=period%>'=='< %=evPeriodFirst%>'){url='evs0202';}else{url='evs0202';} -->
                  <div align="center" height="30" >
                    <td height="30"><%=i+1%></td>
                     </div>
                  <td align="left" class="info_content_00">
							<div align="center" height="30" >
										<ait:select dataListName="searchCraftList" name="craftid"
											 disabled="false"
											onChange="fillCraftLine(this.value);"
											all="all" />
							&nbsp;</div>
							</td>
							 
							<td> 
							<div align="center" height="30" id="StypeOption">&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" id="LineOption">&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" id="AirlraftOption">&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" id="ProcessOption">&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" id="JcoentOption">&nbsp;</div>
							</td>
							<!--  
							 <td>
							 <div align="center" height="30" >
										<ait:select dataListName="searchTypetionList" name="typeionid"
											 disabled="false"
											all="all" />
							&nbsp;</div>
							 </td>-->
							  <td>
							 <div align="center" height="30" >
										<ait:select dataListName="searchEvsQualificationList" name="qualificationid"
											 disabled="false"
											all="all" />
							&nbsp;</div>
							 </td>
							 <td>
							 <div align="center" height="30" ><input name=skillscore type="text" size="5" >&nbsp;</div>
							 </td>
							 <td>
							 <div align="center" height="30" ><input name=proficiency type="text" size="5" >&nbsp;</div>
							 </td>
							 </td>
							 <td>
							 <div align="center" height="30" ><input name=operationcom type="text" size="5" >&nbsp;</div>
							 </td>
							 </td>
							 <td>
							 <div align="center" height="30" ><input name=qualityofwork type="text" size="5" >&nbsp;</div>
							 </td>
							 </td>
							 <td>
							 <div align="center" height="30" ><input name=standardaction type="text" size="5" >&nbsp;</div>
							 </td>
							 </td>
							 <td>
							 <div align="center" height="30" ><input name=composite type="text" size="6" >&nbsp;</div>
							 </td>
							 </td>
							 <td>
							 
							  <div align="center" height="30" >
										<ait:select dataListName="searchEvsSkilelist" name="skileid"
											 disabled="false"
											all="all" />
							&nbsp;</div>
							 </td>
							 <td>
							 <div align="center" height="30" >
	<textarea name="REMARK" style=" height: 25px;width:100px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
                             </div>
							 </td>
							   <td>
							 <div align="center" height="30" >
										<ait:select dataListName="searchEvsPurposeList" name="purposeid"
											 disabled="false"
											all="all" />
							&nbsp;</div>
							 </td>
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
</td>
</tr>
</table>

</body>
</html>
