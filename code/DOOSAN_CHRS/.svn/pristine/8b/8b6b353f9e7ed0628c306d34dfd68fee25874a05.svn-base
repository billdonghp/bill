<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ taglib uri="/ait-taglib" prefix="ait" %>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<!--META-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--JS-->
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="../css/iframe_default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/prototype/prototype.js"/></script>
<script type="text/javascript" src="/js/deptlist.js"></script>
<script language="javascript">

function importExcel()
{
	url="/arControlServlet?operation=importEmpShiftReport";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}

function downloadImportTemplate()
{
	form1.action = "/arControlServlet?operation=ar_pagecontrol&page=downloadEmpShiftTemplate&menu_code=ar0204";
    form1.submit();
}

function compareDate(beginDate,endDate){

 var str11 =new Date(beginDate.replace("-",",")).getTime();
 
 var str22=new Date(endDate.replace("-",",")).getTime(); 
 
   if(str11>str22)
   return false;
   else 
   return true;
} 

function compare(syear,smonth,eyear,emonth){

   if(syear>eyear){
   alert("查询年月输入有误！");
   return false;
   }else if(syear==eyear){
    if(smonth>emonth)
    {  
     alert("查询年月输入有误！");  
    return false;
     }else{
      return true;
     }
   }
   else
   {
    return true;
   }
}

</script>
</head>

<body>
<form name="form1" method="post">
  <table width="100%" border="1"cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td width="25%"><br>&nbsp;</td>
      <td class="info_title_01" width="20%">排班导入</td>
      <td width="25%">&nbsp;&nbsp;&nbsp;&nbsp;					  
		<a target="_blank" href="/reports/template/shift_data.xls"><ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" /></a> |
		<ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcel();" style="cursor:hand"/>
	  </td>
    </tr>
      </table>
      </td>
    </tr>
    
  </table>
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
