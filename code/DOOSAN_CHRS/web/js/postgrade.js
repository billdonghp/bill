var ID = "";
var POST_GRADE_YEAR = "";
var preEl;
var orgBColor;
var orgTColor;    
function HighLightTR(backColor, textColor, i, j) {
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
    ID = i ;
    POST_GRADE_YEAR = j ;
}
function ChangeTextColor(a_obj, a_color) {
    for (i = 0; i < a_obj.cells.length; i++) {
        a_obj.cells(i).style.color = a_color;
    }
}
function Add() {
     url = "/paControlServlet?operation=retrievedataforinsertpostgradecmd&menu_code=" + MENU_CODE;
     window.location.href = url;   
}

function Update() {   
    if (ID == "") {
     alert(msg[0]);            
    }else 
    {
        url = "/paControlServlet?operation=retrievedataforupdatepostgradecmd&postgradeid=" + ID + "&menu_code=" + MENU_CODE + "&year=" + POST_GRADE_YEAR;
        location.href = url;       
    } 
}
function Delete() {
	
    if (ID == "") {
        alert(msg[1]);
    } else {
        if (confirm(msg[2])) {
            url = "/paControlServlet?operation=deletepostgradecmd&postgradeid="+ID + "&menu_code=" + MENU_CODE;  
            location.href = url;  
        } else {
            return;
        }
    }
    
}

function Save() {
    if (!CheckForm()) { 
    } else {
        document.EM2Form.submit();         
    }   
}

function Search() {
	
  document.form1.submit();         
}

      
            
   
