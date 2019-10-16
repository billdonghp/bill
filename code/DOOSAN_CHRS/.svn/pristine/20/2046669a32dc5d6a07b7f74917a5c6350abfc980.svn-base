
var preEl;
var orgBColor;
var orgTColor;
var M_CODE;           
var MENU_CODE;
var CPNYID;
var affirmtypeId;
var personIdDd;
function HighLightTR(backColor, textColor, i, menu_code,cpnyid, m_code,personId) {
    var t;
    if (typeof (preEl) != "undefined") {
        preEl.bgColor = orgBColor;
        try {
            ChangeTextColor(preEl, orgTColor);
        }
        catch (e) {
        }
    }
    var el = event.srcElement;
    el = el.parentElement;
    orgBColor = el.bgColor;
    orgTColor = el.style.color;
    el.bgColor = backColor;
    try {   
        ChangeTextColor(el, textColor);
    }
    catch (e) {
    }
    preEl = el;
    ID = i;
    MENU_CODE = menu_code;
    M_CODE = m_code;
    CPNYID=cpnyid;
    affirmtypeId=affirmtypeId;
    personIdDd = personId;
}

function ChangeTextColor(a_obj, a_color) {
    for (i = 0; i < a_obj.cells.length; i++) {
        a_obj.cells(i).style.color = a_color;
    }
}
function Add() {
	url = "/sy/" + MENU_CODE + "_a.jsp?menu_code=" + MENU_CODE;
    location.href = url;   
}
function Update(msg) {
    if (ID == "") {
        alert(msg[0]);
    } else {
        url = "/sy/" + MENU_CODE + "_m.jsp?no=" + ID + "&menu_code=" + MENU_CODE+"&cpnyid="+CPNYID+"&affirmtypeId="+M_CODE+"&personId="+personIdDd;
        
        location.href = url;
    }
}
function Active() {
    if (ID == "") {
        alert("\u8bf7\u5728\u5217\u8868\u4e2d\u9009\u62e9\u8981\u8f6c\u6362\u7684\u9879\u76ee.");
    } else {
        url = "/inc/Active.asp?ID=" + ID;
        location.href = url;
    }
}
function uP() {
    if (ID == "") {
        alert("\u8bf7\u5728\u5217\u8868\u4e2d\u9009\u62e9\u8981\u4e0a\u79fb\u7684\u9879\u76ee.");
    } else {
        url = "/inc/uP.asp?ID=" + ID;
        location.href = url;
    }
}
function dowN() {
    if (ID == "") {
        alert("\u8bf7\u5728\u5217\u8868\u4e2d\u9009\u62e9\u8981\u4e0b\u79fb\u7684\u9879\u76ee.");
    } else {
        url = "/inc/dowN.asp?ID=" + ID;
        location.href = url;
    }
}
function Delete(msg) {       

    if (ID == "") {
        alert(msg[1]);
    } else {
        if (confirm(msg[2])) {
            url = "/E" + MENU_CODE + "Control?flag=delete&no=" + ID + "&menu_code=" + MENU_CODE;
            location.href = url;
        } else {
            return;
        }
    }
}
function Save() { 
    if (!CheckForm(msg)) {                 
    } else {
        document.EM2Form.submit();
    }   
}
function Report() {
    if (!CheckForm()) {
    } else {
        document.EM2Form.submit();
    }
}
function Reset() {
    document.EM2Form.reset();
}
function del(EVYear) {
    if (ID == "") {
        alert("\u8bf7\u5728\u5217\u8868\u4e2d\u9009\u62e9\u8981\u5220\u9664\u7684\u9879\u76ee.");
    } else {
        if (confirm("\u5220\u9664\u540e,\u76f8\u5173\u4fe1\u606f\u4e5f\u5c06\u88ab\u6e05\u7a7a!\n\n      \u786e\u5b9a\u8981\u5220\u9664\u5417?")) {
            url = "/inc/delete.asp?ID=" + ID + "&EVYear=" + EVYear;
            location.href = url;
        } else {
            return false;
        }
    }
}
function ini(EVYear) {
    if (ID == "") {
        alert("\u8bf7\u5728\u5217\u8868\u4e2d\u9009\u62e9\u8981\u521d\u59cb\u5316\u7684\u9879\u76ee.");
    } else {
        url = "/inc/ini.asp?ID=" + ID + "&EVYear=" + EVYear;
        location.href = url;
    }
}       
function View() {
    if (ID == "") {
        alert("\u8bf7\u5728\u5217\u8868\u4e2d\u9009\u62e9\u8981\u67e5\u770b\u7684\u9879\u76ee.");
    } else {
        url = "/inc/View.asp?ID=" + ID;
        location.href = url;   
    }
}            
function Insert() {
    alert("11111" + MENU_CODE);
    url = "/sy/" + MENU_CODE + "_a.jsp?menu_code=" + MENU_CODE;
    location.href = url;

	//}
}

