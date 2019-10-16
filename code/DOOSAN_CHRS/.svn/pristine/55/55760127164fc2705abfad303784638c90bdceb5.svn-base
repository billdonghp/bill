
function document.onselectstart()
{
    var tmpObj= event.srcElement.tagName;
    if ((tmpObj=="INPUT" || tmpObj=="TEXTAREA") && event.srcElement.readOnly==false)
            return true;
    if (tmpObj.toUpperCase()=="DATEPICKER")
            return true;
    return false;
}

var uu;
var preEl ;
var orgBColor;
var orgTColor;
var ID='';
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
function ChangeTextColor(a_obj,a_color){
        for (i=0;i<a_obj.cells.length;i++){
        a_obj.cells(i).style.color=a_color;
    }
}

//function Add()
 //   {
//        url='/etn/et0201a.jsp?menu_code=<%=menu_code%>';
 //       location.href=url;
//    }
function Add()
{
    //alert(uu);
    if (ID=='')
    {
            alert("请在列表中选择添加相关联的项目.");
    }
    else if(uu==1){
           alert("此项目已关联数据,不能再添加.");
      }
    else
    {
            url='/etn/'+MENU_CODE+'a.jsp?menu_code='+MENU_CODE+'&ID='+ID;
            location.href=url;
    }
}
function Update()
{
    //alert(uu);
    if (ID=='')
    {
            alert("请在列表中选择要修改的项目.");
    }
     else if(uu==0){
           alert("此项目还未关联数据,请先添加.");
      }
    else
    {
            url='/etn/'+MENU_CODE+'m.jsp?menu_code='+MENU_CODE+'&ID='+ID;
            location.href=url;
    }
}
function Delete()
{
            alert("对不起，在此处不能删除任何项目.");
}

function check_u()
{
  uu=1;
}
function check_a()
{
  uu=0;
}

