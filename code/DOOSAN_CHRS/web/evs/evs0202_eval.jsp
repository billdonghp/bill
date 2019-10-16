<%@ page contentType="text/html; charset=UTF-8" language="java"	errorPage=""%>

<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsCraft"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"      scope="session" />
<html>
<head>

<script language="javascript" src="../js/prototype/prototype0202.js" /></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 评价进行 > 个人评价</title>
    <script language="javascript">
    function  Submits() 
    {
  	  //alert(status);
        if (ID=='')
        {
                alert("请在列表中选择要提交的项目.");
                return;
        }
        else
        {
              if(status=='2'){
	        	  alert("提交决裁中，不能重复提交  ");
	              return;
              }else if(status=='3'){
            	  alert("提交已决裁 ，不能重复提交  ");
                  return;
            	 
              }else if(status=='4'){
           	   alert("已提交被否决，不能修改 ");
               return;
           	   }else{
            	  if( confirm("提交后,相关评价成绩将交由相关领导审批 !\n\n      确定要提交吗?") ) {
                      document.form1.action="/evsControlServlet?operation=evssubmitsetupcode&evscodeid="+ID+"&status="+status+"&menu_code=<%=request.getParameter("menu_code")%>";
              	  	document.form1.submit();
                    }else { return;}
                  } ////可删除状态        
        }

    }
    function  Deleter() 
    {

  	  //alert(status);
        if (ID=='')
        {
                alert("请在列表中选择要修改的项目.");
                return;
        }
        else
        {
              if(status=='2'){
        	  alert("提交决裁中，不能删除 ");
              return;
              }else if(status=='3'){
            	  if( confirm("此信息已提交 !\n\n      确定要删除吗?") ) {
                      document.form1.action="/evsControlServlet?operation=evsdeletesetupcodes&evscodeid="+ID+"&status="+status+"&menu_code=<%=request.getParameter("menu_code")%>";
              	  	document.form1.submit();
                    }else { return;}
              }else if(status=='4'){
           	   alert("已提交被否决，不能修改 ");
               return;
           	  }else{
            	  if( confirm("删除后,相关信息也将被清空!\n\n      确定要删除吗?") ) {
                      document.form1.action="/evsControlServlet?operation=evsdeletesetupcode&evscodeid="+ID+"&status="+status+"&menu_code=<%=request.getParameter("menu_code")%>";
              	  	document.form1.submit();
                    }else { return;}
                  } ////可删除状态        
        }

    }
    function Save()
    {
    	 if (ID=='')
         {
                 alert("请在列表中选择要修改的项目.");
                 return;
         }
         else
         {
               if(status=='2'){
	         	  alert("提交决裁中，不能修改 ");
	               return;
               }else if(status=='3'){
            	   alert("已提交决裁，不能修改 ");
	               return;
               }else if(status=='4'){
            	   alert("已提交被否决，不能修改 ");
	               return;
               }else{
             	  if( confirm("确定要修改数据 吗?") ) {
	             		// if(CheckForm2()){
	           	        //  alert("2222");
	           	          document.form1.action="/evsControlServlet?operation=evsjinxingsave&evscodeid="+ID+"&menu_code=<%=request.getParameter("menu_code")%>" ;
	           	          document.form1.submit();
	           	       //}
                      }else { return;}
                   } ////可删除状态        
         }
        
        
    }
function Adds()
{
	
      //alert(document.form1.craftid.value);       
           //alert(document.form1.craftid.value);          
    if(CheckForm()){
       //alert(2222222222);
       document.form1.action= "/evsControlServlet?operation=evsjinxing&menu_code=<%=request.getParameter("menu_code")%>";
       document.form1.submit();
    }
     

      
}

    
 function fillCountEmp(shuLiang)
    {
          
          document.form1.action="/evsControlServlet?operation=evsjinxingshu&shuliang="+shuLiang+"&menu_code=<%=request.getParameter("menu_code")%>";
          document.form1.submit();
          
    }
function CheckForm() {
	   //   alert("333");
		var a1=document.form1.proficiency.value;
		var a2=document.form1.operationcom.value;
		var a3=document.form1.qualityofwork.value;
		var a4=document.form1.standardaction.value;
		//var a5=document.form1.sheopercyq.value;
		 
		   if(document.form1.proficiency.value==""){
			alert("熟练度不能为空!");
			return false;
		}
		if(document.form1.operationcom.value==""){
			alert("作业基准书不能为空!");
			return false;
		}
		if(document.form1.qualityofwork.value==""){
			alert("作业品质不能为空!");
			return false;
		}
		if(document.form1.standardaction.value==""){
			alert("标准动作不能为空!");
			return false;
		}
		/*if(document.form1.sheopercyq.value==""){
			alert("设备保全不能为空!");
			return false;
		}*/
		if(a1!=""){
			  if(isNaN(a1))
		        {
		         alert("熟练度请输入数字");
		         document.form1.proficiency.focus();
		         document.form1.proficiency1.focus();
		         return false;
		        }
			
		}
		if(a2!=""){
			  if(isNaN(a2))
		        {
		         alert("作业基准书请输入数字");
		         document.form1.operationcom.focus();
		         document.form1.operationcom1.focus();
		         return false;
		        }
			
		}
		if(a3!=""){
			  if(isNaN(a3))
		        {
		         alert("作业品质请输入数字");
		         document.form1.qualityofwork.focus();
		         document.form1.qualityofwork1.focus();
		         return false;
		        }
			
		}
		if(a4!=""){
			  if(isNaN(a4))
		        {
		         alert("标准动作请输入数字");
		         document.form1.standardaction.focus();
		         document.form1.standardaction1.focus();
		         return false;
		        }
			
		}
	
	return true;
}

function CheckForm2() {
		//var a1=document.form1.proficiency.value;
		var a2=document.form1.operationcom.value;
		var a3=document.form1.qualityofwork.value;
		var a4=document.form1.standardaction.value;
		var a5=document.form1.sheopercyq.value;
		 
		  /* if(document.form1.proficiency.value==""){
			alert("熟练度不能为空!");
			return false;
		}*/
		if(document.form1.sheopercyq.value==""){
			alert("设备保全不能为空!");
			return false;
		}
		if(document.form1.operationcom.value==""){
			alert("作业基准书不能为空!");
			return false;
		}
		if(document.form1.qualityofwork.value==""){
			alert("作业品质不能为空!");
			return false;
		}
		if(document.form1.standardaction.value==""){
			alert("标准动作不能为空!");
			return false;
		}
	
		if(a5!=""){
			  if(isNaN(a5))
		        {
		         alert("设备保全请输入数字");
		         document.form1.sheopercyq.focus();
		         document.form1.sheopercyq1.focus();
		         return false;
		        }
			
		}
		if(a2!=""){
			  if(isNaN(a2))
		        {
		         alert("作业基准书请输入数字");
		         document.form1.operationcom.focus();
		         document.form1.operationcom1.focus();
		         return false;
		        }
			
		}
		if(a3!=""){
			  if(isNaN(a3))
		        {
		         alert("作业品质请输入数字");
		         document.form1.qualityofwork.focus();
		         document.form1.qualityofwork1.focus();
		         return false;
		        }
			
		}
		if(a4!=""){
			  if(isNaN(a4))
		        {
		         alert("标准动作请输入数字");
		         document.form1.standardaction.focus();
		         document.form1.standardaction1.focus();
		         return false;
		        }
			
		}
		
	return true;
}


function CheckForm1() {
        var b1=document.form1.proficiency;
        var b2=document.form1.proficiency1;
		if (b1!=null)
		{
		    var a1=document.form1.proficiency.value;
			var a2=document.form1.operationcom.value;
			var a3=document.form1.qualityofwork.value;
			var a4=document.form1.standardaction.value;
		   if(document.form1.proficiency.value==""){
			alert("熟练度不能为空!");
			return false;
		}
		if(document.form1.operationcom.value==""){
			alert("作业基准书不能为空!");
			return false;
		}
		if(document.form1.qualityofwork.value==""){
			alert("作业品质不能为空!");
			return false;
		}
		if(document.form1.standardaction.value==""){
			alert("标准动作不能为空!");
			return false;
		}
		if(a1!=""){
			  if(isNaN(a1))
		        {
		         alert("熟练度请输入数字");
		         document.form1.proficiency.focus();
		         document.form1.proficiency1.focus();
		         return false;
		        }
			
		}
		if(a2!=""){
			  if(isNaN(a2))
		        {
		         alert("作业基准书请输入数字");
		         document.form1.operationcom.focus();
		         document.form1.operationcom1.focus();
		         return false;
		        }
			
		}
		if(a3!=""){
			  if(isNaN(a3))
		        {
		         alert("作业品质请输入数字");
		         document.form1.qualityofwork.focus();
		         document.form1.qualityofwork1.focus();
		         return false;
		        }
			
		}
		if(a4!=""){
			  if(isNaN(a4))
		        {
		         alert("标准动作请输入数字");
		         document.form1.standardaction.focus();
		         document.form1.standardaction1.focus();
		         return false;
		        }
		    }
		}
		
     if (b2!=null)
		{
		    var a1=document.form1.proficiency1.value;
			var a2=document.form1.operationcom1.value;
			var a3=document.form1.qualityofwork1.value;
			var a4=document.form1.standardaction1.value;
		   if(document.form1.proficiency1.value==""){
			alert("熟练度不能为空!");
			return false;
		}
		if(document.form1.operationcom1.value==""){
			alert("作业基准不能为空!");
			return false;
		}
		if(document.form1.qualityofwork1.value==""){
			alert("作业品质不能为空!");
			return false;
		}
		if(document.form1.standardaction1.value==""){
			alert("标准动作不能为空!");
			return false;
		}
		if(a1!=""){
			  if(isNaN(a1))
		        {
		         alert("熟练度请输入数字");
		         document.form1.proficiency.focus();
		         document.form1.proficiency1.focus();
		         return false;
		        }
			
		}
		if(a2!=""){
			  if(isNaN(a2))
		        {
		         alert("作业基准书请输入数字");
		         document.form1.operationcom.focus();
		         document.form1.operationcom1.focus();
		         return false;
		        }
			
		}
		if(a3!=""){
			  if(isNaN(a3))
		        {
		         alert("作业品质请输入数字");
		         document.form1.qualityofwork.focus();
		         document.form1.qualityofwork1.focus();
		         return false;
		        }
			
		}
		if(a4!=""){
			  if(isNaN(a4))
		        {
		         alert("标准动作请输入数字");
		         document.form1.standardaction.focus();
		         document.form1.standardaction1.focus();
		         return false;
		        }
		    }
		}
		return true;
}

function Add()
	{
	  document.LGFORM.action="/evsControlServlet?operation=evsircraftadd&menu_code=<%=request.getParameter("menu_code")%>";
	  document.LGFORM.submit();
	}

function fillCraftLine(postObj){ 
  // alert(postObj);
	if(postObj!=""){ 
			var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getCraftLineCmd&craftid='+postObj+"&linepositionObjName=evslinecode"; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'LineOption'},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});               
    }
    else
    {
        postObj = "请选择";
        var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getCraftLineCmd&craftid='+postObj+"&linepositionObjName=evslinecode"; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'LineOption'},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});   
           var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getProcessJcoentCmd&processid='+postObj+"&jcoentpositionObjName=evsjcoentcode"; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'JcoentOption'},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});           
    }
       
    
}

function fillLineAircraft(postObj2){  
//	alert(postObj2);
    
    if(postObj2!=""){ 
		var url = '/ajaxControlServlet'; 
        var pars = 'operation=getProcessJcoentCmd&processid='+postObj2+"&jcoentpositionObjName=evsjcoentcode"; 
        var myAjax = new Ajax.Updater(
                    {success: 'JcoentOption'},
                    url,
                    {method: 'post', parameters: pars,evalScripts:true});               
		}
		else
		{
		    postObj2 = "请选择";
		    var url = '/ajaxControlServlet'; 
		        var pars = 'operation=getProcessJcoentCmd&processid='+postObj2+"&jcoentpositionObjName=evsjcoentcode"; 
		        var myAjax = new Ajax.Updater(
		                    {success: 'JcoentOption'},
		                    url,
		                    {method: 'post', parameters: pars,evalScripts:true});      
		}
}

function fillProcessJcoent(postObj3){   
    
}

function accAdd(arg1,arg2){ 
var r1,r2,m; 
try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
m=Math.pow(10,Math.max(r1,r2)) 
return (arg1*m+arg2*m)/m 
} 


//评价进行的时候第一次进行显示的时候进行查询
function 	fillend()
	{
	  ///alert(document.form1.craftid.value);        
	var proficiency = document.form1.proficiency.value;
	var operationcom = document.form1.operationcom.value;
	var qualityofwork = document.form1.qualityofwork.value;
	var standardaction = document.form1.standardaction.value; 
	 var pro = proficiency - 0;
	 pro = pro*1;
	  var ope = operationcom - 0;
	 ope = ope*1;
	  var qua = qualityofwork - 0;
	 qua = qua*1;
	  var sta = standardaction - 0;
	 sta = sta*1;
	///document.form1.composite.value = pro + ope + qua + sta;
	document.form1.composite.value = accAdd(accAdd(pro,ope),accAdd(qua,sta));
	if(pro + ope + qua + sta > 10)
	 {
	    alert("分数不能大于10分");
	    return;
	 }else if(pro + ope + qua + sta > 0){

		 var pros=pro + ope + qua + sta;
		 //alert(pros);

		   var url = '/ajaxControlServlet'; 
		 var pars = 'operation=getLineGXjndjo&pro='+pros+"&jcoentpositionObjName=evsjcoentcode"; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'skileidOption'},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});      
	 }
	 
 }
//评价进行的时候第二次进行显示的时候进行查询
function 	fillend2(number)
	{
  //alert(number);
  var craftid_1 = "craftid1_"+number;
  var proficiency_1 = "proficiency1_" + number; 
  var sheopercyq_1 = "sheopercyq1_"+number;
  var operationcom_1 = "operationcom1_"+number;
  var qualityofwork_1 = "qualityofwork1_"+number;
  var standardaction_1 = "standardaction1_"+number;
  var composite_1 = "composite1_"+number; 

  var lineidg =document.getElementById(proficiency_1).value;
    //alert("111111");
	var proficiency1 = document.getElementById(proficiency_1).value;
	var operationcom1 = document.getElementById(operationcom_1).value;
	var qualityofwork1 = document.getElementById(qualityofwork_1).value;
	var standardaction1 = document.getElementById(standardaction_1).value; 
	 var pro1 = proficiency1 - 0;
	 pro1 = pro1*1;
	  var ope1 = operationcom1 - 0;
	 ope1 = ope1*1;
	  var qua1 = qualityofwork1 - 0;
	 qua1 = qua1*1;
	  var sta1 = standardaction1- 0;
	 sta1 = sta1*1;
	
	///document.getElementById(composite_1).value = pro1 + ope1 + qua1 + sta1;
	document.getElementById(composite_1).value = accAdd(accAdd(pro1,ope1),accAdd(qua1,sta1));
	if(pro1 + ope1 + qua1 + sta1 > 10)
	 {
	    alert("分数不能大于10分");
	    return;
	 }else if(pro1 + ope1 + qua1 + sta1 > 0){

		 var pro=pro1 + ope1 + qua1 + sta1;
		 //alert(pro);

		   var url = '/ajaxControlServlet'; 
		 var pars = 'operation=getLineGXjndj&pro='+pro+"&jcoentpositionObjName=evsjcoentcode"; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'skileid2_'+number},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});      
	 }
	 
    
 }
 

//第二次查询

function fillCraftLine2(postObj,number){ 
   //alert(postObj);
   //alert(number);
	if(postObj!=""){ 
			var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getCraftLine2Cmd&craftid='+postObj+"&linepositionObjName=lineid_"+number +"&number="+number; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'LineOption_'+number},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});               
    }
    else
    {
        postObj = "请选择";
        var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getCraftLine2Cmd&craftid='+postObj+"&linepositionObjName=lineid_"+number; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'LineOption_'+number},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});   
	   
        var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getProcessJcoentCmd&processid='+postObj+"&jcoentpositionObjName=jobcontent_"+number; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'JcoentOption2_'+number},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});           
    }
    
    

    
}
//第二次时候查询线用来显示机种 工序的
function fillLineAircraft2(postObj2,number){
  //alert(postObj2);
  //alert(number); 
     
    if(postObj2!=""){ 
			var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getLineProcess2Cmd&lineid='+postObj2+"&processtpositionObjName=process_"+number+"&number="+number ; ; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'JcoentOption2_'+number},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});               
    }
    else
    {
        postObj2 = "请选择";
        var url = '/ajaxControlServlet'; 
	        var pars = 'operation=getLineProcess2Cmd&lineid='+postObj2+"&processtpositionObjName=process_"+number+"&number="+number ; ; 
	        var myAjax = new Ajax.Updater(
	                    {success: 'JcoentOption2_'+number},
	                    url,
	                    {method: 'post', parameters: pars,evalScripts:true});      
    }
}

function fillProcessJcoent2(postObj3,number){   
   
}

function fill()
{
  alert("如果修改请先修改工种信息<请选译>！");
  return false;
}
</script>

</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css"> 

<%
EvsCraft evscraft = new EvsCraft();  
String code_name1="";
code_name1=request.getParameter("code_name1")!=null?request.getParameter("code_name1"):code_name1;
if(code_name1.equals("")){
	code_name1=code_name1+"";
}
String  countemp1 ="0";
countemp1=(String)request.getAttribute("countemp")!=null?(String)request.getAttribute("countemp"):countemp1;
int countemp=Integer.parseInt(countemp1);

String  craftid ="";
craftid=(String)request.getAttribute("craftid")!=null?(String)request.getAttribute("craftid"):craftid; 

String  skileid ="";
skileid=(String)request.getAttribute("skileid")!=null?(String)request.getAttribute("skileid"):skileid; 

String  period ="";
period=(String)request.getAttribute("period")!=null?(String)request.getAttribute("period"):period; 

%>
<form name="form1" method="post" action="/evs0202_eval.jsp" method="POST">	
<table width="100%" border="0"   cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evs0202toolbar_v.jsp"%> 
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
		 <td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 个人评价 -->
				 个人评价</td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
	       		 	<td class="info_title_01" width="80">职号</td> 	       		 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.EMPID}&nbsp;</div>
					</td>				
					
					<td class="info_title_01" width="80">姓名</td> 	       		
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.CHINESENAME}&nbsp;</div> 
					</td>					
					<td class="info_title_01" width="80">部门</td> 	       		 	 
					<td height="2" align="right" class="info_content_00">
							<div align="center" height="30" >${searchEmpList.DEPTNAME}&nbsp;</div> 
					</td>
					
					<td class="info_title_01" width="80">部门(所属)</td> 	       		 	 
					<td height="2" align="left" class="info_content_00">
							<div align="left" height="30" >${searchEmpList.DEPTFULLNAME}&nbsp;</div> 
					</td>
					
				</tr>
				<tr>
					<td class="info_title_01" width="80">课</td> 	       		 
					<td height="2" align="left" class="info_content_00">
							<div align="center" height="30" > 
					<input name="deptke" type="text" size="8" value="${searchEmpList.DEPTKE}"> 
						</div>
					</td>
					<td class="info_title_01" width="80">职</td> 	       		 
					<td height="2" align="left" class="info_content_00">
							<div align="center" height="30" > 
					<input name="deptzhi" type="text" size="12" value="${searchEmpList.DEPTZHI}"> 
						</div>
					</td>
					<td class="info_title_01" width="80">组</td> 	       		 
					<td height="2" align="left" class="info_content_00">
							<div align="center" height="30" > 
					<input name="deptzu" type="text" size="15" value="${searchEmpList.DEPTZU}"> 
						</div>
					</td>
				</tr>
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
				<td align="left" class="title1" colspan="10">评价打分</td>
			</tr>
		</table>
	

 <table width="98%" border="0" align="center" cellpadding="0"
	       cellspacing="1">
	 <tr>
		<td class="line">
					 <input type="hidden" name="ev_emp_id"   value="${searchEmpList.PERSON_ID}"/>
					 <input type="hidden" name="ev_emp_name"   value="${searchEmpList.CHINESENAME}"/>
					 <input type="hidden" name="ev_type_id"   value="${searchEmpList.EV_TYPE_ID}"/>
					 <input type="hidden" name="flag" value="save"/>
					 <input type="hidden" name="flag1" value="submit"/> 
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" onClick="HighLightTR('#F0F1F4','black','','')">
			 <td width="12%" class="info_title_01">
				<div align="center">选择</div>
				</td>
				<td width="12%" class="info_title_01">
				<div align="center">年度</div>
				</td>	
				<td width="12%" class="info_title_01">
				<div align="center">工种</div>
				</td>			 
				<td width="10%" class="info_title_01">
				<div align="center">岗位</div>
				</td>				
				<td width="18%" class="info_title_01">
				<div align="center">工序</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">认证时间</div>
				</td>
				 <td width="13%" class="info_title_01">
				<div align="center">熟练度</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">标准动作</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">作业标准遵守</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">作业品质</div>
				</td>
				 <td width="10%" class="info_title_01">
				<div align="center">单项得分</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">工序技能等级</div>
				</td> 
				<td width="10%" class="info_title_01">
				<div align="center">难度系数</div>
				</td> 
				 <td width="10%" class="info_title_01">
				<div align="center">工序得分</div>
				</td>
				 <td width="10%" class="info_title_01">
				<div align="center">累积积分</div>
				</td>
				<td width="38%" class="info_title_01">
				<div align="center">问题点</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">认证人</div>
				</td>
				<td width="10%" class="info_title_01">
				<div align="center">是否复审</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">是否一线通</div>
				</td>
				<td width="13%" class="info_title_01">
				<div align="center">是否保留积分</div>
				</td>
				<td width="8%" class="info_title_01">
				<div align="center">状态</div>
				</td>
			</tr>
			
		             <%  
		                List searchCount = (List)request.getAttribute("searchCount");
		                int number = 0;
		                int affirmNo = 1;
		                String checkState ="";
		                String statuss="";
		                for(int j=0;j<searchCount.size();j++){
			                Map searchCountList = (Map)searchCount.get(j);
			                number = j;
			                String num = String.valueOf(number);
			                request.setAttribute("num",num);
			                checkState ="";
			                
			                if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("2"))
			                     checkState =  "disabled";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("4"))
			                     checkState =  "disabled";
			                if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("1"))
			                	statuss =  "已保存";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("2"))
			                	statuss =  "已提交";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("3"))
			                	statuss =  "已决裁";
			                else if(searchCountList.get("STATUS")!=null&&searchCountList.get("STATUS").equals("4"))
			                	statuss =  "已否决";
			                %>
						<tr	onClick="HighLightTR('#F0F1F4','black','<%=(String)searchCountList.get("SETUPCODENO") %>','<%=(String)searchCountList.get("STATUS")%>')">
						<td class="info_content_09" >
							 	<input type="checkbox" name="affirmNo_${num}" value="<%=(String)searchCountList.get("SETUPCODENO")%>" <%=checkState%> class="check">
							 	<input type="hidden" name="setupcodeNo_${num}" value="<%=(String)searchCountList.get("SETUPCODENO")%>" >
							 </td>
							 <td align="left" class="info_content_00">
						     <ait:select dataListName="searchPeriodList" name="evsperiod1_${num}" selected="<%=(String)searchCountList.get("EV_PERIOD") %>"
								 disabled="false" 	 all="all"	 />
							</td>
							<td align="left" class="info_content_00">
						     <ait:select dataListName="searchCraftList" name="craftid1_${num}" selected="<%=(String)searchCountList.get("CRAFT") %>"
								 disabled="false" onChange="fillCraftLine2(this.value,${num});"	 all="all"	 />
							</td>
							<input type="hidden" name="number" value="${num}"/>  
							<td>
							<div align="center" height="30" id="LineOption_${num}">
							<ait:select dataListName="searchLineList" name="lineid_${num}"  selected="<%=(String)searchCountList.get("LINE") %>"
											 disabled="false" onChange="fill();"	 all="all"/>&nbsp;</div>
							</td>						 
							<!--<td>
							<div align="center" height="30" id="ProcessOption2_${num}"> 
							   <ait:select dataListName="searchProcessList" name="process_${num}" selected="<%=(String)searchCountList.get("PROCESS") %>"
											 disabled="false" onChange="fill();" all="all"/> &nbsp;</div>				 
							</td>						 
							--><td>
							<div align="center" height="30" id="JcoentOption2_${num}"> 
							<ait:select dataListName="searchJobcontentList" name="jobcontent_${num}" selected="<%=(String)searchCountList.get("JOBCONTENT") %>"
											 disabled="false" onChange="fill();" all="all"/>&nbsp;</div>	
							</td>						
							 <td>
							 <div align="center" height="30" >
							 <input name="provedate1_${num}" id="provedate1_${num}" type="text" size="5" value="<%=searchCountList.get("PROVEDATE")%>"> 
							 </div>
							 </td>	
							  <!-- 熟练度 -->							 
							 <td>
							  <div align="center" height="30" >
							  <%
							    if(searchCountList.get("PROFICIENCY") == null || searchCountList.get("PROFICIENCY").equals("") || searchCountList.get("PROFICIENCY").equals("0"))
							    { %>  0    <%
							    }else 
							    {   %>    <input name="proficiency1_${num}" id="proficiency1_${num}" align="right" type="text" size="5" value="<%=searchCountList.get("PROFICIENCY")%>">       
							 <%  }   %> 
							
							</div>
							 </td>		
							  <td>
							 <div align="center" height="30" >
							 <input name="standardaction1_${num}" id="standardaction1_${num}"  align="right" type="text" size="5" value="<%=searchCountList.get("STANDARDACTION")%>"> 
							 </div>
							 </td> 	
							  <!-- 作业标准 -->		
							 <td>
							  <div align="center" height="30" >
							 <input name="operationcom1_${num}" id="operationcom1_${num}" align="right" type="text" size="5" value="<%=searchCountList.get("OPERATIONCOM")%>"> 
							 </div>
							 </td>	
							  <!-- 作业品质 -->		
							  <td>
							 <div align="center" height="30" >
							<input name="qualityofwork1_${num}" id="qualityofwork1_${num}" align="right" type="text" size="5" onblur="fillend2(${num});" value="<%=searchCountList.get("QUALITYOFWORK")%>"> 
							</div>
							 </td>
							 <!-- 以上 单项得分-->
							 <td>
							 <div align="center" height="30" >
							<input name="composite1_${num}" id="composite1_${num}" type="text" readonly size="5" onfocus="fillend2(${num});" value="<%=searchCountList.get("COMPOSITE")%>"> 
							</div>
							 </td>
							 <td> 
							 <div align="center" height="30" id="skileid1_${num}"><%=searchCountList.get("SKILLLEVEL")%></div>
							<!--<div align="center" height="30" id="skileid2_${num}"> 							
							<input name="skileid1_${num}" id="skileid1_${num}" type="text" readonly size="5"  value="<%=searchCountList.get("SKILLLEVEL")%>"> 
							</div>
							 --></td>
							 <td>
							  <div align="center" height="30" id="descriptio1_${num}"><%=searchCountList.get("DESCRIPTIO")%></div>
							
							 <!--<div align="center" height="30" >
							 <input name="descriptio1_${num}" id="descriptio1_${num}"  type="text" readonly size="5"   value="<%=searchCountList.get("DESCRIPTIO")%>"> 
							</div>
							 --></td>
							 <td>
							  <div align="center" height="30" id="skillscore1_${num}"><%=searchCountList.get("SKILLSCORE")%></div>
							  <!--				
							 <div align="center" height="30" >
							 <input name="skillscore1_${num}" id="skillscore1_${num}" type="text" readonly  size="5"  value="<%=searchCountList.get("SKILLSCORE")%>"> 
							 </div>
							 --></td>
							 <td>
							  <div align="center" height="30" id="sumscore1_${num}"><%=searchCountList.get("SUMSCORE")%></div>
							  <!--							 
							 <div align="center" height="30" >
							 <input name="sumscore1_${num}" id="sumscore1_${num}" type="text" size="5" value="<%=searchCountList.get("SUMSCORE")%>"> 
							 </div>
							 --></td>
							 <td>
							 <input name="remark1_${num}" type="text" size="50" value="<%=searchCountList.get("REMARK")%>"> 
							 </td>
							 <td>
							 <input name="proveby1_${num}" type="text" size="8" value="<%=searchCountList.get("PROVEBY")%>"> 
							 </td>
							  <td>
							<ait:select dataListName="searchEvsPurposeList" name="confirmdate1_${num}"   selected="<%=(String)searchCountList.get("CONFIRMDATE") %>"
											 disabled="false"	all="all"/>	
							 </td>
							  <td>
							  <ait:select dataListName="searchEvsPurposeList" name="sheopercyq1_${num}"   selected="<%=(String)searchCountList.get("SHEOPERCYQ") %>"
											 disabled="false"	all="all"/>		
							 </td>
							 
							 <td>
							  <ait:select dataListName="searchEvsPurposeList" name="purpose1_${num}"   selected="<%=(String)searchCountList.get("PURPOSE") %>"
											 disabled="false"	all="all"/>		
							 </td>
							  <td>
							  <%=statuss%>
							  <input name="status1_${num}" type="hidden" size="10" value="<%=searchCountList.get("STATUS")%>"> 
							  
							</td>
							  
						</tr>
						
						<%} %> 
						
			 	<%    if(countemp > 0) {
		                for(int n=0;n<countemp;n++){ %> 
						<tr	onClick="HighLightTR('#F0F1F4','black','','')">
						<td class="info_content_09" >
							 	<input type="checkbox" name="affirmNo" value="<%=affirmNo%>" <%=checkState%>>
							 </td>
							  <td align="left" class="info_content_00">
						     <ait:select dataListName="searchPeriodList" name="evsperiod"  disabled="false" 	 all="all"	 />
							</td>
							<td align="left" class="info_content_00">
							<div align="center" height="30" >
										<ait:select dataListName="searchCraftList" name="craftid"
											 disabled="false"	onChange="fillCraftLine(this.value);"	all="all" />
							&nbsp;</div>
							</td>
							<td>
							<div align="center" height="30" id="LineOption">&nbsp;</div>
							</td>
							<!--<td>
							<div align="center" height="30" id="ProcessOption">&nbsp;</div>
							</td>
						
							--><td>
							<div align="center" height="30" id="JcoentOption">&nbsp;</div>
							</td>
							<td>
							 <div align="center" height="30" ><input name=provedate type="text" size="5" > </div>
							 </td>
							   <!--熟练度 -->
							<td>
							 <div align="center" height="30" ><input name=proficiency type="text" size="5" > </div>
							 </td>
							 <!-- 标准动作 -->
							 <td>
							 <div align="center" height="30" ><input name=standardaction type="text" size="5" > </div>
							 </td>	
							  <!-- 作业基准书遵守分数 -->	
							  <td>
							 <div align="center" height="30" ><input name=operationcom type="text" size="5" > </div>
							 </td>
							   <!-- 作业品质分数 -->	
							  <td>
							 <div align="center" height="30" ><input name=qualityofwork type="text" size="5" onblur="fillend();"> </div>
							 </td>
							 <!-- 单项得分 -->	
							<td>	
							<div align="center" height="30" >						
							    <input name=composite type="text" size="6" onfocus="fillend();"/>	</div>					
							 </td>
							<td>
							  <!-- 工序技能等级 -->	
							    <div align="center" height="30" id="skileidOption">&nbsp;</div>
							
							 </td>	
							   <!-- 工序难度系数 -->		
							  <td>
							  <div align="center" height="30" id="descriptio">&nbsp;</div>
							 </td>		
							 <!-- 工序得分 -->				 						
							 <td>
							 <div align="center" height="30" id="skillscore">&nbsp;</div>
							 </td>
							<!-- 累积得分 -->				 						
							 <td>
							  <div align="center" height="30" id="sumscore">&nbsp;</div>
							 </td>					 
						 	<!-- 问题点 -->		
							 <td>
							 
							<textarea name="REMARK" style=" height: 25px;width:330px " type="_moz"  
							onfocus="this.style.height='50px'" onblur="this.style.height='50px';"></textarea>
                            
							 </td>
							 <!-- 认证人 -->				 						
							 <td>
							 <input name=proveby type="text" size="8" > 
							 </td>	
							 <!-- 复审日期 -->	
							  <td>
							 <div align="center" height="30" >
										<ait:select dataListName="searchEvsPurposeList" name="confirmdate"
											 disabled="false"		all="all" />			&nbsp;</div>
							 </td>			 							
							  <!-- 是否一线通 -->
							   <td>
							 <div align="center" height="30" >
										<ait:select dataListName="searchEvsPurposeList" name="purposeid"
											 disabled="false"		all="all" />			&nbsp;</div>
							 </td>
							 <!-- 是否跨工种 -->
							   <td>
							 <div align="center" height="30" >
										<ait:select dataListName="searchEvsPurposeList" name="sheopercyq"
											 disabled="false"		all="all" />			&nbsp;</div>
							 </td>
							  <!-- 状态 -->
							   <td>
							 <div align="center" height="30" >
											&nbsp;</div>
							 </td>
						</tr>
						
						<%} }%> 
		</table>

		</td>
	</tr>
	</table>
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
