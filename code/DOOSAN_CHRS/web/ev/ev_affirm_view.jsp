<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ev.bean.EvaluateInfo"%>
<%@ page import="com.ait.ev.bean.EvaluateAffirmor"%>
<%@ page import="com.ait.ev.bean.EvaluateItem"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil,com.ait.util.DateUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ page import="com.ait.util.DateUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="confirmMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="pageControl" class="com.ait.ess.bean.PageControl" scope="request" />
<jsp:useBean id="evaluateList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evaluateInfo" class="com.ait.ev.bean.EvaluateInfo" scope="page" />
<jsp:useBean id="evAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="evAffirmor" class="com.ait.ev.bean.EvaluateAffirmor" scope="page" />
<jsp:useBean id="itemList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="evaluateItem" scope="page" class="com.ait.ev.bean.EvaluateItem" /><html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
#leftnewstd .ellipsis_row2{width:70px}
.ellipsis_row2{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
</style>
</head>
<body>

<script language="javascript1.5" type="text/javascript">
	function showMemo(val) {
	var memo = document.getElementById('memo_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				  ,autoScroll: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
}
function showMemo2(val) {
	var memo = document.getElementById('memo2_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				  ,autoScroll: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 
	
    function findObject(object , tag){
      if(object == null || typeof(object) != "object") return null ;
      var node = object.parentElement ;
      if(node == null) return null ;
      if(node.tagName == tag)
        return node ;
      else
       return findObject(node , tag) ;
    }
    function findRow(object){
       return findObject(object , "TR") ;
    }

function checkAll()
{
  var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
    {
      document.ApplyForm.elements[i].checked = selected;
      document.ApplyForm.elements[i].parentNode.parentNode.style.background=document.ApplyForm.elements[i].checked?"#e7f0ef":"#fff"
    }
  }
  document.ApplyForm.ck_all.value = selected ? "1" : "0";
}
function ChangeColor(o){
o.parentNode.parentNode.style.background=o.checked?"#e7f0ef":"#fff"
}
function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.action = "/evControlServlet";
	document.ApplyForm.content.value="evaluateAffrimView";
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}
//单个决裁
function doAffirm_single(element, flag) {
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
       document.ApplyForm.elements[i].checked = false;
  }  
  var row = findRow(element);  
  var jumpCyc = false;  
  for(k = 0 ; k < row.cells.length; k++){
      var objects = row.cells[k].all;
      for (j = 0; j < objects.length; j++){
        if(objects[j].type=="checkbox"){
           objects[j].checked = !objects[j].disabled;
           jumpCyc = true;
           break;
        }
      }
      if (jumpCyc) break;
  }
  doAffirm(flag);
}
//批量决裁
// flag 1 通过, 2 否决
function doAffirm(flag){
   
    var msg1='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';
	var msg ='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';
	if (flag == "2") {
		msg ='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
		msg1='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	}
	var existSelected = false;//是否有选择的记录
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" 
        && document.ApplyForm.elements[i].checked && !document.ApplyForm.elements[i].disabled)
    {
      var affirmNo = document.ApplyForm.elements[i].value;
      var currenFlag = document.getElementById("opFlag" + affirmNo).value;
      if (currenFlag != "0" && currenFlag != flag) {//当前记录只能通过或否决，与操作不一致，忽略
          document.ApplyForm.elements[i].checked = false;
      } else
       existSelected = true;
    }
  }
  
  for (var i=0; i<size; i++){
  
    if(document.getElementById("affirmNo_"+i) != null){
			tags = document.getElementById("affirmNo_" + i);
			if(tags.checked == true){
			   
			    var s=document.getElementById("total_"+i).value;
			 
				if(s > 100){
		
				   alert("评价总分不得大于100分");
			       return ;
				}
			}
		}
	}	
  
  if (!existSelected){
      alert(msg);   
      return false;
  }
  
  var size = document.ApplyForm.applySize.value ;
  
  for (var i=0; i<size; i++){
  
    if(document.getElementById("affirmNo_"+i) != null){
			tags = document.getElementById("affirmNo_" + i);
			if(tags.checked == true){
			   
			    var s=document.getElementById("total_"+i).value;
			 
				if(s > 100){
		
				   alert("评价总分不得大于100分");
			       return ;
				}
			}
		}
	}	
	if (confirm(msg1)) {
		document.ApplyForm.action = "/evControlServlet";
		document.ApplyForm.operation.value="evaluateAffirm";
		document.ApplyForm.content.value="doEvaluateAffrim";
		document.ApplyForm.flag.value=flag;
		document.ApplyForm.submit();
	}
  
}

//批量修改

function Update(){
    
    var msg1='你确定要修改当前选择的记录？';
	var msg ='请选择要修改的记录。';
	
	var existSelected = false;//是否有选择的记录
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" 
        && document.ApplyForm.elements[i].checked && !document.ApplyForm.elements[i].disabled)
    {
       
      var affirmNo = document.ApplyForm.elements[i].value;
      ///alert(document.getElementById("opFlag" + affirmNo).value);
      var currenFlag = document.getElementById("opFlag" + affirmNo).value;
     
      if ( currenFlag != "0") {//与操作不一致，忽略
          document.ApplyForm.elements[i].checked = false;
      } else
       existSelected = true;
       
    }
  }
  if (!existSelected){
      alert(msg);   
      return false;
  }
  
  var size = document.ApplyForm.applySize.value ;
  
  for (var i=0; i<size; i++){
  
    if(document.getElementById("affirmNo_"+i) != null){
			tags = document.getElementById("affirmNo_" + i);
			if(tags.checked == true){
			   
			    var s=document.getElementById("total_"+i).value;
			    
			    var remark=document.getElementById("remark_"+i).value;
				
				var chinesename=document.getElementById("chinesename_"+i).value;
			 
			    ///alert(s+remark+chinesename);
				if(s > 100){
		
				   alert("评价总分不得大于100分");
			       return ;
				}
				
				if(s == 0 && remark.trim() =='' ){
				
				   alert("员工[" + chinesename + "]总分为零，请填写说明事项！");
			       return ;
				}
			}
		}
	}	
  
	if (confirm(msg1)) {
		document.ApplyForm.action = "/evControlServlet";
		document.ApplyForm.operation.value="evaluateAffirm";
		document.ApplyForm.content.value="updateEvaluateAffirm";

		document.ApplyForm.submit();
	}
  
}

function caclPrice(k){
    var sum = 0;
    for(var i=0;i<=30;i++){
    
	    if(document.getElementById("item"+i+"_"+k) != null){
	      var s=document.getElementById("item"+i+"_"+k);
	   
	       var sv = s.value;
	       if(sv !=''){
	        ///sum = sum + parseInt(sv); 
	         sum = accAdd(sum,sv); 
	       } 
	    }
    }

  ///$("total_" + k).innerHTML = sum;
  $("total_" + k).value = sum;

}

function accAdd(arg1,arg2){ 
var r1,r2,m; 
try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
m=Math.pow(10,Math.max(r1,r2)) 
return (arg1*m+arg2*m)/m 
} 
</script>
<%
   String qryType = request.getAttribute("qryType").toString(); 
%>
<%! String selected(String valueSel, String value){
      return valueSel.equals(value) ? "selected" : "";
    }
 %>
<form name="ApplyForm" action="" method="post">
<input type="hidden" name="applySize" value=<%=evaluateList.size()%> />  
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ev/inc/ev_toolbar_affirm.jsp"%>
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
		<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>		
				</td>
		</tr>
	    <tr>
	      <td colspan="9">
	       <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		        <tr>
		            <td nowrap="nowrap" class="info_title_01"><!-- 评价月份-->
	          	评价月份 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	            <ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
	          	
	          </td>
	          
	          <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             部门
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <select name="selectDept">
				<option value="">全部</option>
				<c:forEach items="${deptList}" var="deptList" varStatus="i">
					
					<option value="${deptList.DEPTID}" <c:if test="${deptList.DEPTID==selectDept}">selected</c:if> >
						${deptList.DEPTNAME}
					</option>
				</c:forEach>
			</select></td>
	        <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             职位
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <select name="selectPosition">
				<c:forEach items="${positionList}" var="positionList" varStatus="i">
					
					<option value="${positionList.AFFIRM_POSITION}" <c:if test="${positionList.AFFIRM_POSITION==selectPosition}">selected</c:if>>
						${positionList.AFFIRM_POSITION_NAME}
					</option>
				</c:forEach>
			</select></td>  
			    	  <td width="7%" class="info_title_01"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
			  <td width="10%" class="info_content_00">
				     <select name="qryType">
				         <option value="0" <%= selected("0", qryType) %> ><!-- 全部 -->
				   <ait:message messageID="display.ess.approval.select_all" module="ess"></ait:message>      
				         </option>   
				         <option value="1" <%= selected("1", qryType) %>><!-- 已决裁 -->
				   <ait:message messageID="display.ess.approval.verified" module="ess"></ait:message>        
				         </option>
				         <option value="2" <%= selected("2", qryType) %>><!-- 未决裁 -->
				     <ait:message messageID="display.ess.approval.pending" module="ess"></ait:message>      
				         </option>                
				     </select>
				  </td>                                                                                                 
		          <td  class="info_title_01"><!-- 关键字 -->
		       <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td>                                                                                                                            
		          <td   class="info_content_00">
		          <input type="text" name="key" value="${key}" /></td>  
		        </tr>      
		          
		        </table>
	      </td>
		</tr>
		</table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<%
			if (!errorMsg.equals("")) {
		%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%
			}
		%>
		<!-- display content -->
	
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">上级审批
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		   <td class="info_title_01" width="20"><a href="#" onclick="checkAll();" style="color:red;"> <!-- 全选 -->
			  <ait:message messageID="display.mutual.select_2" module="ess"></ait:message>  	   
			  </a></td>
		   	<td class="info_title_01" >序<br>号</td>
			<td class="info_title_01" nowrap><!--工号  -->职号</td>
		    <td class="info_title_01" nowrap><!--  休假人-->姓名</td>
		    
				
			<td class="info_title_01" nowrap>评价<br>月份
		    
		    </td>	
		    <td class="info_title_01" nowrap><!-- 休假类型 -->
		    部门
		    </td>
		    <td class="info_title_01" nowrap>职位
		    
		    </td>
		    <td nowrap="nowrap" class="info_title_01">
				员工<br>状态</td>	
				
			 <%
		     	for (int k = 0; k < itemList.size(); k++) {
		     		evaluateItem = (EvaluateItem) itemList.get(k);
		     		
		     		String itemName = evaluateItem.getItemName();
		     		String itemProportion = evaluateItem.getItemProportion();
		     		if(!"".equals(itemProportion) && !(itemProportion ==null)){
		     			itemName=itemName+"<br>"+itemProportion+"%";
		     		}
		     %>
							
								<td nowrap="nowrap" class="info_title_01" width="60">
									<%=itemName%></td>

								<%
									
									}
								%>
								
			 <td nowrap="nowrap" class="info_title_01">
				评价<br>总分</td>
			<td nowrap="nowrap" class="info_title_01">
				本次<br>排名</td>	
				
			 <td nowrap="nowrap" class="info_title_01">
				上次<br>排名</td>	
			 <td nowrap="nowrap" class="info_title_01">
				名次<br>变动</td>	 		
			 
			  <td nowrap="nowrap" class="info_title_01">
				说明事项</td>					
		    
		  
		    <td class="info_title_01" nowrap><!--  决裁情况-->
			<ait:message
				messageID="display.mutual.status_2" module="ess"></ait:message>   
			   </td>
			      
		  </tr>
		<%for(int i=0;i<evaluateList.size();i++){                       
			evaluateInfo = (EvaluateInfo) evaluateList.get(i);
		    evAffirmorList = evaluateInfo.getAffirmorList();
		    int applyNo = evaluateInfo.getNo();
		    int affirmNo = 0;
		    String person_id="";
		    int opFlag = evaluateInfo.getOpFlag();
		    if (opFlag >= 0) {
		      for(int j=0;j<evAffirmorList.size();j++){
			    evAffirmor = (EvaluateAffirmor) evAffirmorList.get(j);
		        if (evAffirmor.getAffirmorId().equals(admin.getAdminID())) {
		            affirmNo = evAffirmor.getAffirmNo();
		            break ;
			    } 
			  }  		
			}  
			String checkState = affirmNo > 0 ? "" : "disabled";
		%>
		  <tr align="center">
		    <td class="info_content_10" ><%if (evaluateInfo.getOpFlag()>=0) {%>
		    <input type="checkbox" name="affirmNo_<%=i%>" value="<%=affirmNo%>" <%=checkState%> onclick="ChangeColor(this)">
		    <input type="hidden" name="applyNo_<%=i%>" value="<%=applyNo%>">
		    <input type="hidden" name="person_id<%=person_id%>" value="<%=person_id%>">
		    <input type="hidden" id="opFlag<%=affirmNo%>" name="opFlag<%=affirmNo%>" value="<%=opFlag%>"><%} else {%>&nbsp;<%}%>
		   
		    </td>
		    <td class="info_content_10"><%= i + 1 %></td>
		    <td class="info_content_10">
		    <span class=ellipsis_row title='<%=evaluateInfo.getEMPID() %>'><%=evaluateInfo.getEMPID() %></span>
		    </td>
		    <td class="info_content_10">
		    <span class=ellipsis_row title='<%=evaluateInfo.getCHINESENAME() %>'><%=evaluateInfo.getCHINESENAME() %>
		    <input type="hidden" name="chinesename_<%=i%>" value="<%=evaluateInfo.getCHINESENAME()%>"></span>
		    </td>
			<td class="info_content_10" id="leftnewstd">
			<%=evaluateInfo.getEVALUATE_MONTH() %>
			<input type="hidden" name="yearMonth_<%=i%>" value="<%=evaluateInfo.getEVALUATE_MONTH() %>	">	
			</td>
			<td class="info_content_10" id="leftnewstd">
			<%=evaluateInfo.getDEPTNAME() %>
			<input type="hidden" name="dept_<%=i%>" value="<%=evaluateInfo.getDEPTID() %>	">
				
			</td>
			
			<td class="info_content_10" id="leftnewstd">
			<%=evaluateInfo.getPOST_CODE_NAME() %>
			<input type="hidden" name="post_<%=i%>" value="<%=evaluateInfo.getPOST_CODE() %>	">	
			</td>
			 <td class="info_content_10" nowrap="nowrap"><%=evaluateInfo.getSTATUS() %></td>
			
		       <%      
		       
		        
		       
		       
		       
			                    double itemValue = 0;
		       		    
                            for (int k = 0; k < itemList.size(); k++) {
		     		             evaluateItem = (EvaluateItem) itemList.get(k);
		     		             String itemId = evaluateItem.getItemId();
                                if(itemId.equals("item0")){
                                   itemValue = evaluateInfo.getITEM0();
                                }else if(itemId.equals("item1")){
                                   itemValue = evaluateInfo.getITEM1();
                                }else if(itemId.equals("item2")){
                                   itemValue = evaluateInfo.getITEM2();
                                }else if(itemId.equals("item3")){
                                   itemValue = evaluateInfo.getITEM3();
                                }else if(itemId.equals("item4")){
                                   itemValue = evaluateInfo.getITEM4();
                                }else if(itemId.equals("item5")){
                                   itemValue = evaluateInfo.getITEM5();
                                }else if(itemId.equals("item6")){
                                   itemValue = evaluateInfo.getITEM6();
                                }else if(itemId.equals("item7")){
                                   itemValue = evaluateInfo.getITEM7();
                                }else if(itemId.equals("item8")){
                                   itemValue = evaluateInfo.getITEM8();
                                }else if(itemId.equals("item9")){
                                   itemValue = evaluateInfo.getITEM9();
                                }else if(itemId.equals("item10")){
                                   itemValue = evaluateInfo.getITEM10();
                                }else if(itemId.equals("item11")){
                                   itemValue = evaluateInfo.getITEM11();
                                }else if(itemId.equals("item12")){
                                   itemValue = evaluateInfo.getITEM12();
                                }else if(itemId.equals("item13")){
                                   itemValue = evaluateInfo.getITEM13();
                                }else if(itemId.equals("item14")){
                                   itemValue = evaluateInfo.getITEM14();
                                }else if(itemId.equals("item15")){
                                   itemValue = evaluateInfo.getITEM15();
                                }else if(itemId.equals("item16")){
                                   itemValue = evaluateInfo.getITEM16();
                                }else if(itemId.equals("item17")){
                                   itemValue = evaluateInfo.getITEM17();
                                }else if(itemId.equals("item18")){
                                   itemValue = evaluateInfo.getITEM18();
                                }else if(itemId.equals("item19")){
                                   itemValue = evaluateInfo.getITEM19();
                                }else if(itemId.equals("item20")){
                                   itemValue = evaluateInfo.getITEM20();
                                }else if(itemId.equals("item21")){
                                   itemValue = evaluateInfo.getITEM21();
                                }else if(itemId.equals("item22")){
                                   itemValue = evaluateInfo.getITEM22();
                                }else if(itemId.equals("item23")){
                                   itemValue = evaluateInfo.getITEM23();
                                }else if(itemId.equals("item24")){
                                   itemValue = evaluateInfo.getITEM24();
                                }else if(itemId.equals("item25")){
                                   itemValue = evaluateInfo.getITEM25();
                                }else if(itemId.equals("item26")){
                                   itemValue = evaluateInfo.getITEM26();
                                }else if(itemId.equals("item27")){
                                   itemValue = evaluateInfo.getITEM27();
                                }else if(itemId.equals("item28")){
                                   itemValue = evaluateInfo.getITEM28();
                                }else if(itemId.equals("item29")){
                                   itemValue = evaluateInfo.getITEM29();
                                }else if(itemId.equals("item30")){
                                   itemValue = evaluateInfo.getITEM30();
                                }else if(itemId.equals("item31")){
                                   itemValue = evaluateInfo.getITEM31();
                                }else if(itemId.equals("item32")){
                                   itemValue = evaluateInfo.getITEM32();
                                }else if(itemId.equals("item33")){
                                   itemValue = evaluateInfo.getITEM33();
                                }else if(itemId.equals("item34")){
                                   itemValue = evaluateInfo.getITEM34();
                                }else if(itemId.equals("item35")){
                                   itemValue = evaluateInfo.getITEM35();
                                }else if(itemId.equals("item36")){
                                   itemValue = evaluateInfo.getITEM36();
                                }else if(itemId.equals("item37")){
                                   itemValue = evaluateInfo.getITEM37();
                                }else if(itemId.equals("item38")){
                                   itemValue = evaluateInfo.getITEM38();
                                }else if(itemId.equals("item39")){
                                   itemValue = evaluateInfo.getITEM39();
                                }else if(itemId.equals("item40")){
                                   itemValue = evaluateInfo.getITEM40();
                                }else if(itemId.equals("item41")){
                                   itemValue = evaluateInfo.getITEM41();
                                }else if(itemId.equals("item42")){
                                   itemValue = evaluateInfo.getITEM42();
                                }else if(itemId.equals("item43")){
                                   itemValue = evaluateInfo.getITEM43();
                                }else if(itemId.equals("item44")){
                                   itemValue = evaluateInfo.getITEM44();
                                }else if(itemId.equals("item45")){
                                   itemValue = evaluateInfo.getITEM45();
                                }else if(itemId.equals("item46")){
                                   itemValue = evaluateInfo.getITEM46();
                                }else if(itemId.equals("item47")){
                                   itemValue = evaluateInfo.getITEM47();
                                }else if(itemId.equals("item48")){
                                   itemValue = evaluateInfo.getITEM48();
                                }else if(itemId.equals("item49")){
                                   itemValue = evaluateInfo.getITEM49();
                                }else if(itemId.equals("item50")){
                                   itemValue = evaluateInfo.getITEM50();
                                }else if(itemId.equals("item51")){
                                   itemValue = evaluateInfo.getITEM51();
                                }else if(itemId.equals("item52")){
                                   itemValue = evaluateInfo.getITEM52();
                                }else if(itemId.equals("item53")){
                                   itemValue = evaluateInfo.getITEM53();
                                }else if(itemId.equals("item54")){
                                   itemValue = evaluateInfo.getITEM54();
                                }else if(itemId.equals("item55")){
                                   itemValue = evaluateInfo.getITEM55();
                                }else if(itemId.equals("item56")){
                                   itemValue = evaluateInfo.getITEM56();
                                }else if(itemId.equals("item57")){
                                   itemValue = evaluateInfo.getITEM57();
                                }else if(itemId.equals("item58")){
                                   itemValue = evaluateInfo.getITEM58();
                                }else if(itemId.equals("item59")){
                                   itemValue = evaluateInfo.getITEM59();
                                }else if(itemId.equals("item60")){
                                   itemValue = evaluateInfo.getITEM60();
                                }else if(itemId.equals("item61")){
                                   itemValue = evaluateInfo.getITEM61();
                                }else if(itemId.equals("item62")){
                                   itemValue = evaluateInfo.getITEM62();
                                }else{
                                   itemValue = 0;
                                }
		       		        	
		       		        	
		       	%>
		       <td class="info_content_10" nowrap="nowrap">
		       <input id="<%=itemId%>_<%=i%>" type="text" name="<%=itemId%>_<%=i%>" style="width:50px;" value=<%=NumberUtil.convert(itemValue)%>  onblur=caclPrice(<%=i%>) onChange="if(!(/^(\+|-)?\d+($|\.\d+$)/.test(value))) this.value=''">
		       </td>
		   
		             <%
		   		    	}
		   		    %>
		      
			    
		     
		      
			 
			   <td class="info_content_10" nowrap="nowrap" class="info_content_01"><input id="total_<%=i%>" type="text" name="total_<%=i%>" style="width:50px;" value="<%=NumberUtil.convert(evaluateInfo.getTOTAL()) %>"></td>
			  <td class="info_content_10" id="leftnewstd">
			     <%=StringUtil.checkNull(evaluateInfo.getPLACE()) %>&nbsp;/&nbsp;<%=StringUtil.checkNull(evaluateInfo.getAMOUNT()) %>
			    </td>
			    <td class="info_content_10" id="leftnewstd">
			    <%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_PLACE()) %>&nbsp;/&nbsp;<%=StringUtil.checkNull(evaluateInfo.getLAST_MONTH_AMOUNT()) %>
			    </td>
			       <%  
			           int placeMove = 0;
			           if(NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0)>0){
			           		 placeMove = NumberUtil.parseInt(evaluateInfo.getPLACE(),0) - NumberUtil.parseInt(evaluateInfo.getLAST_MONTH_PLACE(),0);
			           }
		   		       
		   		   %>
		   
		      
			    
			    <td class="info_content_10" id="leftnewstd">
			    <%=placeMove%>&nbsp;
			    </td>
			 
			 
  	           <td class="info_content_10"  width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark_<%=i%>"  style=" height: 25px;width:100px " type="_moz" 
						onfocus="this.style.height='50px'" onblur="this.style.height='25px';"><%=StringUtil.checkNull(evaluateInfo.getREMARK()) %></textarea>
				</td>
		   <!--  <td width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark<%=affirmNo%>" style=" height: 25px;width:100px " type="_moz"
						onfocus="this.style.height='60px'" onblur="this.style.height='25px';"></textarea>			</td> -->

			 
		    <td align="center" nowrap>
			    <%
			    for(int j=0;j<evAffirmorList.size();j++){            
			    	evAffirmor = (EvaluateAffirmor) evAffirmorList.get(j);%>
			    	<font color="<%=(String) colorMap.get(String.valueOf(evAffirmor.getAffirmFlag()))%>">
			    	<%= StringUtil.checkNull(evAffirmor.getAffirmorName()) + StringUtil.getString((4 - evAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + StringUtil.checkNull(evAffirmor.getUpdateDate()) %>&nbsp;&nbsp;
		    		<%= statusMap.get(String.valueOf(evAffirmor.getAffirmFlag()))%>
			    	<%if (evAffirmor.getAffirmorId().equals(admin.getAdminID())) {       
			    		if (evaluateInfo.getOpFlag() == 0) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">
			    			<!-- 通过 --><ait:message messageID="display.ess.approval.approved" module="ess"></ait:message> 
			    			</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" 
			    			onClick="doAffirm_single(this, 2);">
			    			<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message> </span>
			    		<%} else if (evaluateInfo.getOpFlag() == 1) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">
			    			<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message></span>
			    		<%} else if (evaluateInfo.getOpFlag() == 2) {%>
			    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 2);">
			    			<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message></span>
						<%}
					}%>
			    	</font><br>
			    <%}%>
		    </td>
		  </tr>
		<%}%>
		</table>
		            
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(evaluateList) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(evaluateList)}"
					step="1">
					<tr>
						<td class="info_content_10" height="30"></td>
						<td class="info_content_10"></td>
						<td class="info_content_10"></td>
						<td class="info_content_10"></td>
						<td class="info_content_10"></td>
						<td class="info_content_10"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		
		<ait:page       
		               link="/evControlServlet"
		               parameters="&operation=evaluateAffirm&content=evaluateAffrimView&menu_code=${param.menu_code}&year=${year}&month=${month}&selectDept=${selectDept}&selectPosition=${selectPosition}&key=${key}&qryType=${qryType}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->	
		
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

	<input type="hidden" name="operation" value="evaluateAffirm" />
	<input type="hidden" name="content" value="evaluateAffrimView" />
	<input type="hidden" name="flag" value="" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="${currentPage}" />	
	<input type="hidden" name="ck_all" value="0" />
</form>
</body>
</html>
