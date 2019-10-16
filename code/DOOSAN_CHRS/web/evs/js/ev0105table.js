
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
var proId;
var TypeId;

var MENU_CODE='';

function HighLightTR(backColor,textColor,i,menu_code){
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
MENU_CODE=menu_code
}

function HighLightTR(backColor,textColor,EmpId,PeriodId,menu_code,processId,EvTypeId){
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
ID=EmpId;
ID2=PeriodId;
proId=processId;
TypeId=EvTypeId;
MENU_CODE=menu_code
}
function ChangeTextColor(a_obj,a_color){
        for (i=0;i<a_obj.cells.length;i++){
        a_obj.cells(i).style.color=a_color;
    }
}

function Report(){
	if (ID=='')
	    {
	            alert("请在列表中选择要生成报表的项目.");
	    }
	else
	    {
	    	if (proId!='EVPROCESS010') {
	    		alert("评价尚未完毕，不能生成报表！");
	    		return;
	    	}
	       if(proId=='EVPROCESS010' && TypeId=='EVTYPE002'){
	         theUrl="/reportControlServlet?operation=crystal&reportName=EstimationCommonFirst&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }	
		   if(proId=='EVPROCESS010' && TypeId=='EVTYPE001'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=EstimationCommonSecond&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }	  
		   if(proId=='EVPROCESS010' && TypeId=='EVTYPE004'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=EstimationSurportFirst&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }	
		   if(proId=='EVPROCESS010' && TypeId=='EVTYPE003'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=EstimationSurportSecond&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }
		   if(proId=='EVPROCESS010' && TypeId=='EVTYPE006'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=EstimationBusinessFirst&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }
		   if(proId=='EVPROCESS010' && TypeId=='EVTYPE005'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=EstimationBusinessSecond&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }    
		   if(proId=='EVPROCESS010' && TypeId=='EVTYPE007'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=EstimationQuater&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   } 
	    }
}

function ReportMid(){
	if (ID=='')
	    {
	            alert("请在列表中选择要生成报表的项目.");
	    }
	else
	    {
	    	if (proId!='EVPROCESS001') {
	    		alert("目标设定流程已结束，不能生成报表！");
	    		return;
	    	}
	       if(proId=='EVPROCESS001' && TypeId=='EVTYPE002' || TypeId=='EVTYPE001'){
	         theUrl="/reportControlServlet?operation=crystal&reportName=CommonAchivement&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }	  
		   if(proId=='EVPROCESS001' && TypeId=='EVTYPE003' || TypeId=='EVTYPE004'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=ManagerAchivement&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }
		   if(proId=='EVPROCESS001' && TypeId=='EVTYPE005' || TypeId=='EVTYPE006'){
		   theUrl="/reportControlServlet?operation=crystal&reportName=OperationAchivement&EmpId="+ID+"&PeriodId="+ID2;
		     var name = "";
		     window.open(theUrl,name);   }
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
            url='/evs/'+MENU_CODE+'_m.jsp?menu_code='+MENU_CODE+'&ID='+ID+'&ID2='+ID2;
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
                    url='/evs/'+MENU_CODE+'_t.jsp?flag=del&menu_code='+MENU_CODE+'&ID='+ID+'&ID2='+ID2;
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

