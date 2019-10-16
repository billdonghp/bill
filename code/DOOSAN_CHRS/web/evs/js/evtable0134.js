
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
var status ='';

function HighLightTR(backColor,textColor,i,s,menu_code){
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
status = s;
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
            url='/evsControlServlet?operation=evszyzgdjupdate&menu_code='+MENU_CODE+'&ID='+ID+'&ID2='+ID2;
            location.href=url;
    }
}

function setNull(){
		ID='';
		ID2='';
}

