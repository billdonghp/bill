
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
var MENU_CODE='';

function HighLightTR(backColor,textColor,i,menu_code,j){
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
ID2=j;
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
            url='EvControlServlet?operation='+MENU_CODE+'&flag=modify&ID='+ID+'&menu_code='+MENU_CODE;
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
                    url='EvControlServlet?operation='+MENU_CODE+'&flag=delete&ID='+ID+'&menu_code='+MENU_CODE;
                    location.href=url;
            }
            else
            {
            return;
            }
    }

}

